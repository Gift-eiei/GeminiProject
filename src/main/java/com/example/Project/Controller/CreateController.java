package com.example.Project.Controller;

import com.example.Project.Controller.Object.SciencePlanObj;
import com.example.Project.Model.SciencePlan.DataProc;
import com.example.Project.Model.SciencePlan.SciencePlan;
import com.example.Project.Model.User.User;
import com.example.Project.repository.SciencePlanRepository;
import com.example.Project.repository.UserRepository;
import com.example.Project.service.SciencePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/CreateSciencePlan")
public class CreateController {


    @InitBinder
    public void bindingPreparation(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
    }

    @Autowired
    private SciencePlanRepository sciencePlanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SciencePlanService sciencePlanService;


    @ModelAttribute("SciencePlan")
    public SciencePlanObj SciencePlanObj() {
        return new SciencePlanObj();
    }

    @GetMapping
    public String sciencePlanForm(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByEmail(authentication.getName());

        if (user.getType().trim().replaceAll("\\s", "").toLowerCase().equals("astronomer")) {
            return "createSciencePlan";
        } else {
            redirectAttributes.addFlashAttribute("access", "Only Astronomer can perform this function!");
            return "redirect:/";
        }

    }

    SciencePlan s = new SciencePlan();

    public void save(SciencePlan s) {
        this.s = s;
        // System.out.println(s);
    }

    @GetMapping("/confirm")
    public String confirm(RedirectAttributes redirAttrs) {
        sciencePlanRepository.save(s);
        redirAttrs.addFlashAttribute("success", "Science Plan is submitted");
        return "redirect:/CreateSciencePlan";
    }

    @GetMapping("/delete")
    public String delete(RedirectAttributes redirAttrs) {
        s = null;
        redirAttrs.addFlashAttribute("fail", "Science Plan is cancelled");
        return "redirect:/CreateSciencePlan";
    }

    @GetMapping("/update")
    public ModelAndView update() {
        ModelAndView mav = new ModelAndView("createSciencePlan.html");
        mav.addObject("SciencePlan", this.s);
        return mav;
    }


    @PostMapping
    public String createSciencePlan(@Valid @ModelAttribute("SciencePlan") SciencePlanObj scienceplan, Errors errors, Model model,
                                    BindingResult result, Authentication authentication) {


        User user = userRepository.findByEmail(authentication.getName());

        ArrayList dataprocs = new ArrayList();

        DataProc dp = new DataProc();
        dp.setFileQuality(scienceplan.getFileQuality());
        dp.setColorType(scienceplan.getColorType());
        dp.setContrast(scienceplan.getContrast());
        dp.setSaturation(scienceplan.getSaturation());
        dataprocs.add(dp);
        //dataProcRepository.save(dp);

        SciencePlan sp = new SciencePlan();
        sp.setPlanNo(scienceplan.getPlanNo());
        sp.setCreator(scienceplan.getCreator());
        sp.setSubmitter(scienceplan.getSubmitter());
        sp.setFundingInUSD(scienceplan.getFundingInUSD());
        sp.setObjectives(scienceplan.getObjectives());
        sp.setStarSystem(scienceplan.getStarSystem());
        sp.setStartDate(scienceplan.getStartDate());
        sp.setEndDate(scienceplan.getEndDate());
        sp.setTelescopeLocation(scienceplan.getTelescopeLocation());
        sp.setStatus(scienceplan.getStatus());
        sp.setFileQuality(scienceplan.getFileQuality());
        sp.setColorType(scienceplan.getColorType());
        sp.setContrast(scienceplan.getContrast());
        sp.setSaturation(scienceplan.getSaturation());
        sp.setAstronomerID(user.getId());
        // sp.setDataProcRequirements(dataprocs);

        if (errors.hasErrors()) {
            return "createSciencePlan";
        }

        save(sp);

        return "createdSciencePlanVerify";


    }


}
