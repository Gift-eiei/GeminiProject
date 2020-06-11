package com.example.Project.Controller;

import com.example.Project.Controller.Object.VirtualTelscopeObj;
import com.example.Project.Model.SciencePlan.SciencePlan;
import com.example.Project.Model.User.User;
import com.example.Project.Model.VirtualTelescope.VtModel;
import com.example.Project.repository.SciencePlanRepository;
import com.example.Project.repository.UserRepository;
import com.example.Project.repository.VirtualTelescopeRepository;
import edu.gemini.app.ocs.controller.VirtualTelescopeHandler;
import edu.gemini.app.ocs.model.BaseSciencePlan;
import edu.gemini.app.ocs.model.VirtualTelescope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static edu.gemini.app.ocs.model.BaseSciencePlan.STATUS.COMPLETE;

@Controller
@RequestMapping("/VirtualTelescope")
public class VirtualTelescopeController extends VirtualTelescopeHandler {

    @Autowired
    private SciencePlanRepository sciencePlanRepository;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("SciencePlan")
    public List<SciencePlan> getRunningSciencePlan() {

        ArrayList<SciencePlan> RunSP = new ArrayList<SciencePlan>();
        Iterable<SciencePlan> SP = sciencePlanRepository.findAll();
        for (SciencePlan i : SP) {
            if (i.getStatus() == BaseSciencePlan.STATUS.RUNNING) {
                RunSP.add(i);
            }
        }
        return RunSP;
    }


    @GetMapping
    public String showRunningSciencePlan(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByEmail(authentication.getName());
        if (user.getType().trim().replaceAll("\\s", "").toLowerCase().equals("astronomer")) {
            return "VirtualTelescope";
        } else {
            redirectAttributes.addFlashAttribute("access", "Only Astronomer can perform this function!");
            return "redirect:/";
        }

    }

    private VirtualTelscopeObj vtObj = new VirtualTelscopeObj();

    public void save(VirtualTelscopeObj v) {
        this.vtObj = v;
        // System.out.println(s);
    }

    @GetMapping("/{id}")
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("VtResult.html");
        Optional<SciencePlan> runSp = sciencePlanRepository.findById(id);
        VirtualTelescope vt1 = VirtualTelescopeController.getVirtualTelescope(VirtualTelescope.NORTH);
        VirtualTelescope vt2 = VirtualTelescopeController.getVirtualTelescope(VirtualTelescope.SOUTH);
        if (runSp.isPresent()) {
            if (runSp.get().getTelescopeLocation() == BaseSciencePlan.TELESCOPELOC.HAWAII) {
                vt1.setSciencePlan(runSp.get());
                vtObj.setSciencePlanID(runSp.get().getSciencePlanID());
                try {
                    boolean success = vt1.executeSciencePlan();
                    vtObj.setStatus(success);
                } catch (VirtualTelescope.NoSciencePlanException e) {
                    e.printStackTrace();
                }
                save(vtObj);
            } else {
                vt2.setSciencePlan(runSp.get());
                vtObj.setSciencePlanID(runSp.get().getSciencePlanID());
                try {
                    boolean success = vt2.executeSciencePlan();
                    vtObj.setStatus(success);
                } catch (VirtualTelescope.NoSciencePlanException e) {
                    e.printStackTrace();
                }
                save(vtObj);
            }
        }
        mav.addObject("VirtualTelscope", vtObj);
        mav.addObject("SciencePlan", runSp.get());
        return mav;
    }

    @Autowired
    private VirtualTelescopeRepository virtualTelescopeRepository;

    @GetMapping("/confirm")
    public String confirm(RedirectAttributes redirAttrs) {
        VtModel vtModel = new VtModel();
        redirAttrs.addFlashAttribute("confirm", "Observing result has been saved!");
        vtModel.setSciencePlanID(vtObj.getSciencePlanID());
        vtModel.setStatus(vtObj.getStatus());
        virtualTelescopeRepository.save(vtModel);
        sciencePlanRepository.findById(vtObj.getSciencePlanID()).map(
                S -> {
                    S.setStatus(COMPLETE);
                    return sciencePlanRepository.save(S);
                });
        return "redirect:/VirtualTelescope";
    }

    @GetMapping("/NotConfirm")
    public String NotConfirm(RedirectAttributes redirAttrs) {
        VtModel vtModel = new VtModel();
        redirAttrs.addFlashAttribute("NotConfirm", "Select new science plan!");
        return "redirect:/VirtualTelescope";
    }


}

