package com.example.Project.Model.ObservingProgram;

import edu.gemini.app.ocs.model.*;
import jparsec.observer.LocationElement;

import javax.persistence.*;
import java.util.ArrayList;

@Entity(name="Obseving_Program")
public class ObservingProgram extends BaseObservingProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ObservingID;
    private int submittedID;
    private int ScienceObserverID;
    private boolean isLightDetectorOn;


    public int getObservingID() {
        return ObservingID;
    }

    public void setObservingID(int observingID) {
        ObservingID = observingID;
    }

    public int getSciencePlanID() {
        return submittedID;
    }

    public void setSciencePlanID(int sciencePlanID) {
        submittedID = sciencePlanID;
    }

    public int getScienceObserverID() {
        return ScienceObserverID;
    }

    public void setScienceObserverID(int scienceObserverID) {
        ScienceObserverID = scienceObserverID;
    }


    @Override
    public boolean isLightDetectorOn() {
        return isLightDetectorOn;
    }

    @Override
    public void setLightDetectorOn(boolean lightDetectorOn) {
        isLightDetectorOn = lightDetectorOn;
    }

}
