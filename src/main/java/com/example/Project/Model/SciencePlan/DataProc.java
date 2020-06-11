package com.example.Project.Model.SciencePlan;

import edu.gemini.app.ocs.model.DataProcRequirement;

import javax.persistence.*;


public class DataProc extends DataProcRequirement {

    @Enumerated(EnumType.STRING)
    private TYPE fileType;

    private double fileQuality;
    private DataProcRequirement.COLOR_TYPE colorType;
    private double contrast;
    private double brightness;
    private double saturation;


    @Override
    public void setFileType(TYPE fileType) {
        this.fileType = fileType;
    }

    @Override
    public double getFileQuality() {
        return fileQuality;
    }

    @Override
    public void setFileQuality(double fileQuality) {
        this.fileQuality = fileQuality;
    }

    @Override
    public COLOR_TYPE getColorType() {
        return colorType;
    }

    @Override
    public void setColorType(COLOR_TYPE colorType) {
        this.colorType = colorType;
    }

    @Override
    public double getContrast() {
        return contrast;
    }

    @Override
    public void setContrast(double contrast) {
        this.contrast = contrast;
    }

    @Override
    public double getBrightness() {
        return brightness;
    }

    @Override
    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

    @Override
    public double getSaturation() {
        return saturation;
    }

    @Override
    public void setSaturation(double saturation) {
        this.saturation = saturation;
    }
}
