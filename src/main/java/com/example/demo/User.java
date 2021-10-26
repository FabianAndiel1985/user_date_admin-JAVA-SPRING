package com.example.demo;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
class User {
	
  private @Id @GeneratedValue Long id;
  private String name;
  @OneToMany(mappedBy = "user", 
          cascade = CascadeType.ALL)
  private Set<Appointment> appointments;
  

  public User(String name) {
	this.name = name;
  }

	public Long getId() {
		return id;
	  }

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Appointment> getAppointments() {
		return appointments;
	}
	
	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(appointments, id, name);
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
		return Objects.equals(appointments, other.appointments) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
  }
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", appointments=" + appointments + "]";
	}
    
}