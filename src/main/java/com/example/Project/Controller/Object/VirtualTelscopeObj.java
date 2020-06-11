package com.example.Project.Controller.Object;

public class VirtualTelscopeObj {

    private int SciencePlanID;
    private int telescopeID;
    private String status;

    public VirtualTelscopeObj() {
    }

    public VirtualTelscopeObj(int sciencePlanID, int telescopeID) {
        SciencePlanID = sciencePlanID;
        telescopeID = telescopeID;
    }

    public int getSciencePlanID() {
        return SciencePlanID;
    }

    public void setSciencePlanID(int sciencePlanID) {
        SciencePlanID = sciencePlanID;
    }

    public int getTelescopeID() {
        return telescopeID;
    }

    public void setTelescopeID(int telescopeID) {
        this.telescopeID = telescopeID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        if (status) {
            this.status = "Successful Execution";
        } else {
            this.status = "Unsucessful Execution";
        }
    }

    @Override
    public String toString() {
        return "VirtualTelscopeObj{" +
                "SciencePlanID=" + SciencePlanID +
                ", telescopeID=" + telescopeID +
                ", status='" + status + '\'' +
                '}';
    }
}
