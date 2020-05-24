package com.showme.beans;

import java.sql.Date;
import java.sql.Time;

import org.springframework.stereotype.Component;

@Component
public class Message {

	private int id;
	private int senderId;
	private String message;
	private int receiverId;
	private boolean view;
	private Date date;
	private Time time;
	
	private String mediaType;
	private String mediaDescription;
	private Integer mediaId;
	
	private String mediaFilePath;
	private String mediaFileName;
	
	private String mediaPicture;
	private String mediaDocument;
	private String mediaVideo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public boolean isView() {
		return view;
	}
	public void setView(boolean view) {
		this.view = view;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getMediaDescription() {
		return mediaDescription;
	}
	public void setMediaDescription(String mediaDescription) {
		this.mediaDescription = mediaDescription;
	}
	public Integer getMediaId() {
		return mediaId;
	}
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}
	public String getMediaFilePath() {
		return mediaFilePath;
	}
	public void setMediaFilePath(String mediaFilePath) {
		this.mediaFilePath = mediaFilePath;
	}
	public String getMediaFileName() {
		return mediaFileName;
	}
	public void setMediaFileName(String mediaFileName) {
		this.mediaFileName = mediaFileName;
	}
	public String getMediaPicture() {
		return mediaPicture;
	}
	public void setMediaPicture(String mediaPicture) {
		this.mediaPicture = mediaPicture;
	}
	public String getMediaDocument() {
		return mediaDocument;
	}
	public void setMediaDocument(String mediaDocument) {
		this.mediaDocument = mediaDocument;
	}
	public String getMediaVideo() {
		return mediaVideo;
	}
	public void setMediaVideo(String mediaVideo) {
		this.mediaVideo = mediaVideo;
	}
	
	
}
