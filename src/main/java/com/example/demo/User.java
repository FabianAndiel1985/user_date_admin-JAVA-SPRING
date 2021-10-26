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
@Table(name = "users")
class User {
	
  private @Id @GeneratedValue Long id;
  private String name;
  @OneToMany(mappedBy = "users", 
          cascade = CascadeType.ALL)
  private Set<Appointment> pages;

  User(String name) {
    this.name = name;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

@Override
public int hashCode() {
	return Objects.hash(id, name);
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
	return Objects.equals(id, other.id) && Objects.equals(name, other.name);
}

@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + "]";
}
   
}