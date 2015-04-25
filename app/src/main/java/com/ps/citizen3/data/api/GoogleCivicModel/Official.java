package com.ps.citizen3.data.api.GoogleCivicModel;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Official {

    @Expose
    private String name;
    @Expose
    private List<Address> address = new ArrayList<Address>();
    @Expose
    private String party;
    @Expose
    private List<String> phones = new ArrayList<String>();
    @Expose
    private List<String> urls = new ArrayList<String>();
    @Expose
    private String photoUrl;
    @Expose
    private List<Channel> channels = new ArrayList<Channel>();
    @Expose
    private List<String> emails = new ArrayList<String>();

    private Office office;

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
     * The address
     */
    public List<Address> getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(List<Address> address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The party
     */
    public String getParty() {
        return party;
    }

    /**
     *
     * @param party
     * The party
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     *
     * @return
     * The phones
     */
    public List<String> getPhones() {
        return phones;
    }

    /**
     *
     * @param phones
     * The phones
     */
    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    /**
     *
     * @return
     * The urls
     */
    public List<String> getUrls() {
        return urls;
    }

    /**
     *
     * @param urls
     * The urls
     */
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    /**
     *
     * @return
     * The photoUrl
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     *
     * @param photoUrl
     * The photoUrl
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     *
     * @return
     * The channels
     */
    public List<Channel> getChannels() {
        return channels;
    }

    /**
     *
     * @param channels
     * The channels
     */
    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    /**
     *
     * @return
     * The emails
     */
    public List<String> getEmails() {
        return emails;
    }

    /**
     *
     * @param emails
     * The emails
     */
    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}