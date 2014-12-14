package com.smart.shortfilms.vo;

import java.io.Serializable;

public class ShortFilm implements Serializable,Comparable<ShortFilm> {

    private Integer id;
	
	private String title;
	
	private String yId;
	
	private long rDate;
	
	private long views;

	private long likes;
	
	private long disLikes;
	
	private String duration;
	
	private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getyId() {
        return yId;
    }

    public void setyId(String yId) {
        this.yId = yId;
    }

    public long getrDate() {
        return rDate;
    }

    public void setrDate(long rDate) {
        this.rDate = rDate;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getDisLikes() {
        return disLikes;
    }

    public void setDisLikes(long disLikes) {
        this.disLikes = disLikes;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
	public int compareTo(ShortFilm o) {
		return this.getTitle().compareToIgnoreCase(o.getTitle());
	}
}
