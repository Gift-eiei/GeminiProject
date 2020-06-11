package com.example.Project.Model.SciencePlan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Submitted_Science_Plan")
public class SubmittedSciencePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int submittedID;

    private int SciencePlanID;
    private static SubmittedSciencePlan sp = null;
    private static Object SciencePlan;

    public SubmittedSciencePlan() {

    }

    public SubmittedSciencePlan(SciencePlan sciencePlan) {
        super();
    }

    /**
     * A static method to get an instance of the Summitted Sicnece Plan Object class.
     * Using a singleton class in order to ensure that only one Science Plan is submitted ( No repeat!).
     *
     * @return an instance of the Summitted Sicnece Plan Object class
     */
    public static SubmittedSciencePlan getInstance() {
        if (sp == null)
            sp = new SubmittedSciencePlan((com.example.Project.Model.SciencePlan.SciencePlan) SciencePlan);
        return sp;
    }

    public int getSubmittedID() {
        return submittedID;
    }

    public void setSubmittedID(int submittedID) {
        this.submittedID = submittedID;
    }


    public int getSciencePlanID() {
        return SciencePlanID;
    }

    public void setSciencePlanID(int sciencePlanID) {
        SciencePlanID = sciencePlanID;
    }


}

