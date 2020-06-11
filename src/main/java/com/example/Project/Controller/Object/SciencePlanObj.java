package com.example.Project.Controller.Object;

import edu.gemini.app.ocs.model.BaseSciencePlan;
import edu.gemini.app.ocs.model.DataProcRequirement;
import jparsec.ephem.Target;

import javax.validation.constraints.*;
import java.util.Date;


public class SciencePlanObj {


    private int planNo;

    @NotBlank(message = "This field is required")
    private String creator;


    @NotBlank(message = "This field is required")
    private String submitter;

    private double fundingInUSD;


    @NotBlank(message = "This field is required")
    private String objectives;


    @NotNull(message = "This field is required")
    private Date startDate;

    @NotNull(message = "This field is required")
    private Date endDate;


    private TYPE fileType;

    @Min(0)
    @Max(100)
    private double fileQuality;


    private DataProcRequirement.COLOR_TYPE colorType;

    @Min(0)
    @Max(100)
    private double contrast;

    @Min(0)
    @Max(100)
    private double brightness;

    @Min(0)
    @Max(100)
    private double saturation;


    private BaseSciencePlan.STATUS status;


    private BaseSciencePlan.TELESCOPELOC telescopeLocation;


    private Target.TARGET starSystem;

    public int getPlanNo() {
        return planNo;
    }

    public void setPlanNo(int planNo) {
        this.planNo = planNo;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public double getFundingInUSD() {
        return fundingInUSD;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BaseSciencePlan.STATUS getStatus() {
        return status;
    }

    public void setStatus(BaseSciencePlan.STATUS status) {
        this.status = status;
    }


    public BaseSciencePlan.TELESCOPELOC getTelescopeLocation() {
        return telescopeLocation;
    }

    public Target.TARGET getStarSystem() {
        return starSystem;
    }

    public void setStarSystem(Target.TARGET starSystem) {
        this.starSystem = starSystem;
    }

    public void setTelescopeLocation(BaseSciencePlan.TELESCOPELOC telescopeLocation) {
        this.telescopeLocation = telescopeLocation;
    }

    public void setFundingInUSD(double fundingInUSD) {
        this.fundingInUSD = fundingInUSD;
    }

    public TYPE getFileType() {
        return fileType;
    }

    public void setFileType(TYPE fileType) {
        this.fileType = fileType;
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


    public enum TELESCOPELOC {
        HAWAII("Hawaii"), CHILE("Chile");

        private final String telescope;

        private TELESCOPELOC(String telescope) {
            this.telescope = telescope;
        }

        public String getTelescope() {
            return telescope;
        }


    }

    public enum STATUS {
        COMPLETE("Complete"), RUNNING("Running"), SUBMITTED("Submitted");

        private final String displayStatus;

        private STATUS(String displayStatus) {
            this.displayStatus = displayStatus;
        }

        public String getStatus() {
            return displayStatus;
        }
    }

    public enum TYPE {
        RAW("RAW"), PNG("PNG"), JPEG("JPEG"), TIFF("TIFF");

        private final String type;

        private TYPE(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum COLOR_TYPE {
        BW("Black and White"), COLOR("Color");

        private final String colorType;

        private COLOR_TYPE(String colorType) {
            this.colorType = colorType;
        }
    }

    public enum TARGET {

        SUN("SUN"),
        MERCURY("MERCURY"),
        VENUS("VENUS"),
        EARTH("EARTH"),
        MARS("MARS"),
        JUPITER("JUPITER"),
        SATURN("SATURN"),
        URANUS("URANUS"),
        NEPTUNE("NEPTUNE"),
        Pluto("PLUTO"),
        Moon("MOON"),
        Earth_Moon_Barycente("EARTH MOON"),
        Comet("COMET"),
        Asteroid("ASTEROID"),
        NEO("NEO"),
        Nutation("NUTATION"),
        Libration("LIBRATION"),
        Solar_System_Barycenter("SOLAR SYSTEM"),
        Phobos("PHABOS"),
        Deimos("DEIMOS"),
        Io("IO"),
        Europa("EUROPA"),
        Ganymede("GANYMADE"),
        CALLISTO("CALLISTO"),
        MIMAS("MIMAS"),
        ENCELADUS("ENCELADUS"),
        TETHYS("TETHYS"),
        DIONE("DIONE"),
        RHEA("RHEA"),
        Titan("TITAN"),
        Hyperion("HYPERION"),
        Iapetus("IAPETUS"),
        Miranda("MIRANDA"),
        Ariel("ARIEL"),
        Umbriel("UMBRIEL"),
        Titania("TITANIA"),
        Oberon("OBERON"),
        Triton("TRITON"),
        Nereid("NEREID"),
        Charon("CHARON"),
        Amalthea("AMALTHEA"),
        Thebe("THEBE"),
        Adrastea("ADRASTEA"),
        Metis("METIS"),
        Himalia("HIMALIA"),
        Elara("ELARA"),
        Pasiphae("PASIPHAE"),
        Sinope("SINOPE"),
        Lysithea("LYSITHEA"),
        Carme("CARME"),
        Ananke("ANANKE"),
        Leda("LEDA"),
        Atlas("ATLAS"),
        Prometheus("PROMETHEUS"),
        Pandora("PANDORA"),
        Pan("PAN"),
        Epimetheus("EPIMETHEUS"),
        Janus("JANUS"),
        Telesto("TELESTO"),
        Calypso("CALYPSO"),
        Helene("HELENE"),
        Phoebe("PHOEBE"),
        Cordelia("CORDELIA"),
        Ophelia("OPHELIA"),
        Cressida("CRESSIDA"),
        Bianca("BIANCA"),
        Desdemona("DESDEMONA"),
        Juliet("JULIET"),
        Portia("PORTIA"),
        Rosalind("ROSALIND"),
        Puck("PUCK"),
        Belinda("BELINDA"),
        Naiad("NAIAD"),
        Thalassa("THALASSA"),
        Despina("DESPINA"),
        Galatea("GALATEA"),
        Larissa("LARISSA"),
        Proteus("PROTEUS"),
        Ceres("CERES"),
        Pallas("PALLAS"),
        Vesta("VESTA"),
        Lutetia("LUTETIA"),
        Ida("IDA"),
        Eros("EROS"),
        Davida("DAVIDA"),
        Gaspra("GASPRA"),
        Steins("STEINS"),
        Itokawa("ITOKAWA"),
        P9_Tempel_1("P9_TEMPEL_1"),
        P19_Borrelly("P19_BORRELLY"),
        NOT_A_PLANET("NOT_A_PLANET");
        private String starSystem;

        private TARGET(String starSystem) {
            this.starSystem = starSystem;

        }

        public String getStarSystem() {
            return starSystem;
        }

    }

    @Override
    public String toString() {
        return "SciencePlanObj{" +
                "planNo=" + planNo +
                ", creator='" + creator + '\'' +
                ", submitter='" + submitter + '\'' +
                ", fundingInUSD=" + fundingInUSD +
                ", objectives='" + objectives + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fileType=" + fileType +
                ", fileQuality=" + fileQuality +
                ", colorType=" + colorType +
                ", contrast=" + contrast +
                ", brightness=" + brightness +
                ", saturation=" + saturation +
                ", status=" + status +
                ", telescopeLocation=" + telescopeLocation +
                ", starSystem=" + starSystem +
                '}';
    }
}
