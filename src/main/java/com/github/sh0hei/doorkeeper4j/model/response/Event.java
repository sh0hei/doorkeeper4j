package com.github.sh0hei.doorkeeper4j.model.response;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends DoorKeeperResponse {

    private String title;

    private Integer id;

    private Date startsAt;

    private Date endsAt;

    private String venueName;

    private String address;

    private Double latitude;

    private Double longtitude;

    private Integer ticketLimit;

    private Date publishedAt;

    private Date updatedAt;

    private String banner;

    private String description;

    private String publicUrl;

    private Integer participants;

    private Integer waitListed;

    private Group group;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Integer getTicketLimit() {
        return ticketLimit;
    }

    public void setTicketLimit(Integer ticketLimit) {
        this.ticketLimit = ticketLimit;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) throws ParseException {
    	this.updatedAt = parse(updatedAt);
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public Integer getWaitListed() {
        return waitListed;
    }

    public void setWaitListed(Integer waitListed) {
        this.waitListed = waitListed;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private static final ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss +0900");
            dateFormat.setLenient(false);
            return dateFormat;
        }
    };

    private static Date parse(String dateString) throws ParseException {
        return DATE_FORMAT.get().parse(dateString);
    }
}
