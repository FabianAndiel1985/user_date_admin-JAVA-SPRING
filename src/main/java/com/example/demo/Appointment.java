package com.example.demo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
      
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;

@Entity
@Table(name = "appointments")
class Appointment implements Serializable {

	  private @Id @GeneratedValue Long id;
	  private String title;
	  @Column(unique = true)
	  private LocalDateTime startTime;
	  @Column(unique = true)
	  private LocalDateTime endTime;

	  
	  @ManyToOne(fetch = FetchType.LAZY, optional = false)
	  @JoinColumn(name = "user_id", nullable = false)
	  private User user;
  
	  
	  public Appointment()  {
		  
	  }
	  
	  
	public Appointment(String title, LocalDateTime startTime, LocalDateTime endTime, User user) {
		this.title = title;
		this.startTime = startTime;
		this.endTime = endTime;
		this.user = user;
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
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime; 
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(endTime, id, startTime, title, user);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(endTime, other.endTime) && Objects.equals(id, other.id)
				&& Objects.equals(startTime, other.startTime) && Objects.equals(title, other.title)
				&& Objects.equals(user, other.user);
	}


	@Override
	public String toString() {
		return "Appointment [id=" + id + ", title=" + title + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", user=" + user + "]";
	}
	
	
	  
 
}