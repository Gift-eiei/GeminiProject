package com.example.Project.service;

import com.example.Project.Controller.Object.SciencePlanObj;
import com.example.Project.Model.SciencePlan.SciencePlan;
import com.example.Project.repository.SciencePlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static edu.gemini.app.ocs.model.BaseSciencePlan.STATUS.RUNNING;

@Service
public class SciencePlanService {

    @Autowired
    private SciencePlanRepository sciencePlanRepository;

    public void update(SciencePlan sci, int id) {
        sciencePlanRepository.findById(id).map(
                Sci -> {
                    Sci.setPlanNo(sci.getPlanNo());
                    Sci.setStatus(RUNNING);
                    Sci.setObjectives(sci.getObjectives());
                    Sci.setTelescopeLocation(sci.getTelescopeLocation());
                    Sci.setEndDate(sci.getEndDate());
                    Sci.setStartDate(sci.getStartDate());
                    Sci.setStarSystem(sci.getStarSystem());
                    Sci.setSubmitter(sci.getSubmitter());
                    Sci.setCreator(sci.getCreator());
                    Sci.setFundingInUSD(sci.getFundingInUSD());

                    return sciencePlanRepository.save(Sci);
                }
        ).orElseGet(() -> {
            sci.setSciencePlanID(id);
            return sciencePlanRepository.save(sci);
        });
    }
}
