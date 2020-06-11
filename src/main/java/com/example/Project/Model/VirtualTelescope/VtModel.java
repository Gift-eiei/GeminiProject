package com.example.Project.Model.VirtualTelescope;

import javax.persistence.*;

@Entity(name="Virtual_Telescope")
public class VtModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int telescopeID;
    private int SciencePlanID;
    private String status;

    public int getTelescopeID() {
        return telescopeID;
    }

    public void setTelescopeID(int telescopeID) {
        this.telescopeID = telescopeID;
    }

    public int getSciencePlanID() {
        return SciencePlanID;
    }

    public void setSciencePlanID(int sciencePlanID) {
        SciencePlanID = sciencePlanID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
