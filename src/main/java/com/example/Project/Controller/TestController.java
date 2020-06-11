package com.example.Project.Controller;


import com.example.Project.Model.SciencePlan.SciencePlan;

import com.example.Project.Model.User.User;
import com.example.Project.repository.SciencePlanRepository;
import com.example.Project.repository.UserRepository;
import com.example.Project.service.SciencePlanService;

import edu.gemini.app.ocs.model.BaseSciencePlan;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import static edu.gemini.app.ocs.model.BaseSciencePlan.STATUS.RUNNING;

@Controller
@RequestMapping("/TestSciencePlan")
public class TestController {

    @InitBinder
    public void bindingPreparation(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
    }

    @Autowired
    private SciencePlanRepository sciencePlanRepository;

    @Autowired
    private SciencePlanService sciencePlanService;

    @Autowired
    private UserRepository userRepository;

    private int id;

    @ModelAttribute("SciencePlan")
    public List<SciencePlan> getRunningSciencePlan() {

        ArrayList<SciencePlan> testSP = new ArrayList<SciencePlan>();
        Iterable<SciencePlan> SP = sciencePlanRepository.findAll();
        for (SciencePlan i : SP) {
            if (i.getStatus() == BaseSciencePlan.STATUS.RUNNING || i.getStatus() == null) {
                testSP.add(i);
            }
        }
        return testSP;
    }

    @GetMapping
    public String showAllSciencePlan(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByEmail(authentication.getName());
        if (user.getType().trim().replaceAll("\\s", "").toLowerCase().equals("astronomer")) {
            return "TestSciencePlan";
        } else {
            redirectAttributes.addFlashAttribute("access", "Only Astronomer can perform this function!");
            return "redirect:/";
        }
    }

    @GetMapping("/ValidatePlan/{getId}")
    public String showSciencePlan(@PathVariable int getId, Model model) {
        id = getId;
        Optional<SciencePlan> SP = sciencePlanRepository.findById(id);
        if (SP.isPresent()) {
            model.addAttribute("S", SP.get());
        }
        return "ValidateSP";
    }

    @GetMapping("/Approve/{getId}")
    public String Approve(@PathVariable int getId, RedirectAttributes redirectAttributes) {
        sciencePlanRepository.findById(getId).map(
                S -> {
                    S.setStatus(RUNNING);
                    return sciencePlanRepository.save(S);
                }
        );
        redirectAttributes.addFlashAttribute("test", "Science Plan is running!");
        return "redirect:/TestSciencePlan";
    }


    @GetMapping("/NotApprove/{id}")
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("updateSciencePlan.html");
        Optional<SciencePlan> updateSp = sciencePlanRepository.findById(id);
        mav.addObject("update", updateSp.get());
        return mav;
    }


    @PostMapping("/NotApprove/{getId}")
    public String notApprove(@PathVariable int getId, @ModelAttribute("update") SciencePlan sci,
                             RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("update", "Science Plan has been updated!");
        sciencePlanService.update(sci, getId);
        return "redirect:/TestSciencePlan/ValidatePlan/" + getId;

    }


}