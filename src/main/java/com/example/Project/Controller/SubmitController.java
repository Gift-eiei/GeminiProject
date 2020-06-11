package com.example.Project.Controller;

import com.example.Project.Model.SciencePlan.SciencePlan;
import com.example.Project.Model.SciencePlan.SubmittedSciencePlan;
import com.example.Project.Model.User.User;
import com.example.Project.Model.VirtualTelescope.VtModel;
import com.example.Project.repository.SciencePlanRepository;
import com.example.Project.repository.SubmittedRepository;
import com.example.Project.repository.UserRepository;
import com.example.Project.repository.VirtualTelescopeRepository;
import edu.gemini.app.ocs.model.BaseSciencePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static edu.gemini.app.ocs.model.BaseSciencePlan.STATUS.SUBMITTED;

@Controller
@RequestMapping("/SubmitSciencePlan")
public class SubmitController {

    @Autowired
    private SciencePlanRepository sciencePlanRepository;

    @Autowired
    private VirtualTelescopeRepository virtualTelescopeRepository;

    @Autowired
    private SubmittedRepository submittedRepository;

    @Autowired
    private UserRepository userRepository;


    @ModelAttribute("SciencePlan")
    public List<SciencePlan> getCompleteSciencePlan() {

        ArrayList<SciencePlan> submitSP = new ArrayList<SciencePlan>();
        Iterable<SciencePlan> SP = sciencePlanRepository.findAll();
        for (SciencePlan i : SP) {
            if (i.getStatus() == BaseSciencePlan.STATUS.COMPLETE || i.getStatus() == SUBMITTED) {
                submitSP.add(i);
            }
        }
        return submitSP;
    }

    @GetMapping
    public String sendSubmitSp(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByEmail(authentication.getName());
        if (user.getType().trim().replaceAll("\\s", "").toLowerCase().equals("astronomer")) {
            return "SubmitSciencePlan";
        } else {
            redirectAttributes.addFlashAttribute("access", "Only Astronomer can perform this function!");
            return "redirect:/";
        }

    }

    @GetMapping("/{getId}")
    public String showSciencePlan(@PathVariable int getId, Model model, RedirectAttributes redirAttrs) {
        Optional<SciencePlan> SP = sciencePlanRepository.findById(getId);
        Iterable<SubmittedSciencePlan> submit = submittedRepository.findAll();
        if (SP.isPresent()) {
            model.addAttribute("S", SP.get());
            for (SubmittedSciencePlan i : submit) {
                if (i.getSciencePlanID() == getId) {
                    redirAttrs.addFlashAttribute("submit", "This Science Plan has already been submitted!");
                    return "redirect:/SubmitSciencePlan";
                }
            }

        }
        return "submitSP";
    }


    @GetMapping("/SciConfirm/{getId}")
    public ModelAndView sciconfirm(@PathVariable Integer getId) {
        ModelAndView mav = new ModelAndView("submitVT.html");
        Iterable<VtModel> VT = virtualTelescopeRepository.findAll();
        for (VtModel i : VT) {
            if (i.getSciencePlanID() == getId) {
                mav.addObject("VirtualTelscope", i);
            }
        }
        Optional<SciencePlan> SP = sciencePlanRepository.findById(getId);

        if (SP.isPresent()) {
            mav.addObject("SciencePlan", SP.get());
        }
        return mav;
    }

    @GetMapping("/TeleConfirm/{getId}")
    public String confirmBoth(@PathVariable int getId, RedirectAttributes redirAttrs) {
        redirAttrs.addFlashAttribute("confirm", "Science Plan has been submitted!");
        Optional<SciencePlan> submitSp = sciencePlanRepository.findById(getId);
        SubmittedSciencePlan submitObj = new SubmittedSciencePlan(submitSp.get());
        submitObj.setSciencePlanID(getId);
        submittedRepository.save(submitObj);
        sciencePlanRepository.findById(getId).map(
                S -> {
                    S.setStatus(SUBMITTED);
                    return sciencePlanRepository.save(S);
                });
        return "redirect:/SubmitSciencePlan";
    }

    @GetMapping("/CancelPlan/{getId}")
    public String cancel(@PathVariable int getId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("cancel", "Cancel Science Plan submission!");
        return "redirect:/SubmitSciencePlan";
    }
}
