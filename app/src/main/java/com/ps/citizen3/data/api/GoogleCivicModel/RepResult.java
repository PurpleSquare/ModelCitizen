package com.ps.citizen3.data.api.GoogleCivicModel;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class RepResult {

    @Expose
    private String kind;
    @Expose
    private NormalizedInput normalizedInput;
    @Expose
    private List<Office> offices = new ArrayList<Office>();
    @Expose
    private List<Official> officials = new ArrayList<Official>();

    /**
     *
     * @return
     * The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     *
     * @param kind
     * The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     *
     * @return
     * The normalizedInput
     */
    public NormalizedInput getNormalizedInput() {
        return normalizedInput;
    }

    /**
     *
     * @param normalizedInput
     * The normalizedInput
     */
    public void setNormalizedInput(NormalizedInput normalizedInput) {
        this.normalizedInput = normalizedInput;
    }

    /**
     *
     * @return
     * The offices
     */
    public List<Office> getOffices() {
        return offices;
    }

    /**
     *
     * @param offices
     * The offices
     */
    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    /**
     *
     * @return
     * The officials
     */
    public List<Official> getOfficials() {
        return officials;
    }

    /**
     *
     * @param officials
     * The officials
     */
    public void setOfficials(List<Official> officials) {
        this.officials = officials;
    }

}