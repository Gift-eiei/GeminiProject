package com.example.Project.Model.SciencePlan;
import edu.gemini.app.ocs.model.BaseSciencePlan;
import edu.gemini.app.ocs.model.DataProcRequirement;
import jparsec.ephem.Target;
import javax.persistence.*;
import java.util.Date;


@Entity(name = "Science_Plan")
public class SciencePlan extends BaseSciencePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int SciencePlanID;


    private int planNo;


    private String creator;


    private String submitter;


    private double fundingInUSD;

    private int AstronomerID;


    private String objectives;

    private Target.TARGET starSystem;

    private Date startDate;

    private Date endDate;

    private TELESCOPELOC telescopeLocation;

    private STATUS status;

    private VALIDATION validate;


    private DataProcRequirement.TYPE fileType;


    private double fileQuality;

    private DataProcRequirement.COLOR_TYPE colorType;

    private double contrast;


    private double brightness;


    private double saturation;


    public int getAstronomerID() {
        return AstronomerID;
    }

    public void setAstronomerID(int astronomerID) {
        AstronomerID = astronomerID;
    }

    public VALIDATION getValidate() {
        return validate;
    }

    public void setValidate(VALIDATION validate) {
        this.validate = validate;
    }

    public enum VALIDATION {
        VALIDATED, INVALIDATED
    }

    public void setFileType(DataProcRequirement.TYPE fileType) {
        this.fileType = fileType;
    }

    public DataProcRequirement.TYPE getFileType() {
        return fileType;
    }

    public double getFileQuality() {
        return fileQuality;
    }


    public void setFileQuality(double fileQuality) {
        this.fileQuality = fileQuality;
    }

    public DataProcRequirement.COLOR_TYPE getColorType() {
        return colorType;
    }


    public void setColorType(DataProcRequirement.COLOR_TYPE colorType) {
        this.colorType = colorType;
    }


    public double getContrast() {
        return contrast;
    }


    public void setContrast(double contrast) {
        this.contrast = contrast;
    }


    public double getBrightness() {
        return brightness;
    }


    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }


    public double getSaturation() {
        return saturation;
    }


    public void setSaturation(double saturation) {
        this.saturation = saturation;
    }


    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getSciencePlanID() {
        return SciencePlanID;
    }

    public void setSciencePlanID(int sciencePlanID) {
        SciencePlanID = sciencePlanID;
    }


    @Override
    public int getPlanNo() {
        return planNo;
    }

    @Override
    public void setPlanNo(int planNo) {
        this.planNo = planNo;
    }

    @Override
    public String getSubmitter() {
        return submitter;
    }

    @Override
    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    @Override
    public double getFundingInUSD() {
        return fundingInUSD;
    }

    @Override
    public void setFundingInUSD(double fundingInUSD) {
        this.fundingInUSD = fundingInUSD;
    }

    @Override
    public String getObjectives() {
        return objectives;
    }

    @Override
    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    @Override
    public Target.TARGET getStarSystem() {
        return starSystem;
    }

    @Override
    public void setStarSystem(Target.TARGET starSystem) {
        this.starSystem = starSystem;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public TELESCOPELOC getTelescopeLocation() {
        return telescopeLocation;
    }

    @Override
    public void setTelescopeLocation(TELESCOPELOC telescopeLocation) {
        this.telescopeLocation = telescopeLocation;
    }

    @Override
    public STATUS getStatus() {
        return status;
    }

    @Override
    public void setStatus(STATUS status) {
        this.status = status;
    }
}
