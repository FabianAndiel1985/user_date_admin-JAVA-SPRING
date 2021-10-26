package com.example.demo;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "appointments")
class Appointment {

  private @Id @GeneratedValue Long id;
  private String title;
  @Temporal(TemporalType.TIMESTAMP)
  private Date startTime;
  @Temporal(TemporalType.TIMESTAMP)
  private Date endTime;
  @ManyToOne()
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  
public Appointment(Long id, String title, Date startTime, Date endTime) {
	this.id = id;
	this.title = title;
	this.startTime = startTime;
	this.endTime = endTime;
}

public Long getId() {
	return id;
}

public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Date getStartTime() {
	return startTime;
}
public void setStartTime(Date startTime) {
	this.startTime = startTime;
}
public Date getEndTime() {
	return endTime;
}
public void setEndTime(Date endTime) {
	this.endTime = endTime;
}
  
 
}