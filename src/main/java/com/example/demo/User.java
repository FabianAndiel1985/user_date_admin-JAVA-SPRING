package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "user")
class User implements Serializable{
	
  private @Id @GeneratedValue Long id;
  private String name;
  private Appointment appointment;
//  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
//  cascade = CascadeType.ALL)

  
  public User() {}
  
  public User(String name) {
	this.name = name;
  }

	public Long getId() {
		return id;
	  }
	
	public void setId(Long id) {
		this.id=id;
	  }

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(appointment, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(appointment, other.appointment) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", appointments=" + appointment + "]";
	}
    
}