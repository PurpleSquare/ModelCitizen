package com.ps.citizen3.data.api.GoogleCivicModel;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Office {

    @Expose
    private String name;
    @Expose
    private String divisionId;
    @Expose
    private List<String> levels = new ArrayList<String>();
    @Expose
    private List<String> roles = new ArrayList<String>();
    @Expose
    private List<Integer> officialIndices = new ArrayList<Integer>();

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The divisionId
     */
    public String getDivisionId() {
        return divisionId;
    }

    /**
     *
     * @param divisionId
     * The divisionId
     */
    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    /**
     *
     * @return
     * The levels
     */
    public List<String> getLevels() {
        return levels;
    }

    /**
     *
     * @param levels
     * The levels
     */
    public void setLevels(List<String> levels) {
        this.levels = levels;
    }

    /**
     *
     * @return
     * The roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     *
     * @param roles
     * The roles
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     *
     * @return
     * The officialIndices
     */
    public List<Integer> getOfficialIndices() {
        return officialIndices;
    }

    /**
     *
     * @param officialIndices
     * The officialIndices
     */
    public void setOfficialIndices(List<Integer> officialIndices) {
        this.officialIndices = officialIndices;
    }

}