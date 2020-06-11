package com.example.Project.Controller;

import com.example.Project.Model.ObservingProgram.ObservingProgram;
import com.example.Project.Model.SciencePlan.SciencePlan;
import com.example.Project.Model.SciencePlan.SubmittedSciencePlan;
import com.example.Project.Model.User.User;
import com.example.Project.repository.ObservingRepository;
import com.example.Project.repository.SciencePlanRepository;
import com.example.Project.repository.SubmittedRepository;
import com.example.Project.repository.UserRepository;
import edu.gemini.app.ocs.model.BaseSciencePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ValidateSciencePlan")
public class ValidateController {


    @Autowired
    private SciencePlanRepository sciencePlanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObservingRepository observingRepository;

    @Autowired
    private SubmittedRepository submittedRepository;

    @ModelAttribute("SciencePlan")
    public List<SciencePlan> getSubmittedSciencePlan() {

        ArrayList<SciencePlan> validateSP = new ArrayList<SciencePlan>();
        Iterable<SciencePlan> SP = sciencePlanRepository.findAll();
        for (SciencePlan i : SP) {
            if (i.getStatus() == BaseSciencePlan.STATUS.SUBMITTED) {
                validateSP.add(i);
            }
        }
        return validateSP;
    }

    @GetMapping
    public String sendSubmittedSp(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByEmail(authentication.getName());
        if (user.getType().trim().replaceAll("\\s", "").toLowerCase().equals("scienceobserver")) {
            return "ValidateSciencePlan";
        } else {
            redirectAttributes.addFlashAttribute("accessObserver", "Only Science Observer can access this function!");
            return "redirect:/";
        }

    }


    @GetMapping("/{getId}")
    public String showSciencePlan(@PathVariable int getId, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        Optional<SciencePlan> SP = sciencePlanRepository.findById(getId);
        if (SP.isPresent()) {
            model.addAttribute("S", SP.get());
        }
        return "SPforValidation";

    }

    @GetMapping("/Approve/{getId}")
    public String validateSciencePlan(@PathVariable int getId, RedirectAttributes redirectAttributes, Model model, Authentication authentication) {
        ObservingProgram observingProgram = new ObservingProgram();
        User user = userRepository.findByEmail(authentication.getName());
        Iterable<SubmittedSciencePlan> submittedSciencePlan = submittedRepository.findAll();
        for (SubmittedSciencePlan s : submittedSciencePlan) {
            if (s.getSciencePlanID() == getId) {
                observingProgram.setScienceObserverID(user.getId());
                observingProgram.setSciencePlanID(s.getSubmittedID());
                observingRepository.save(observingProgram);
            }
        }
        sciencePlanRepository.findById(getId).map(
                S -> {
                    S.setValidate(SciencePlan.VALIDATION.VALIDATED);
                    return sciencePlanRepository.save(S);
                }
        );
        redirectAttributes.addFlashAttribute("approve", "Successfully Validate");
        return "redirect:/ValidateSciencePlan";
    }

    @GetMapping("/NotApprove/{getId}")
    public String InvalidateSciencePlan(@PathVariable int getId, RedirectAttributes redirectAttributes, Model model) {
        SciencePlan sp = new SciencePlan();
        sciencePlanRepository.findById(getId).map(
                S -> {
                    S.setValidate(SciencePlan.VALIDATION.INVALIDATED);
                    return sciencePlanRepository.save(S);
                }
        );
        redirectAttributes.addFlashAttribute("disapprove", "Warning Invalidate!");
        return "redirect:/ValidateSciencePlan";
    }
}
