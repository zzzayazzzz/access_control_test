package com.test.access_control_task.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
    @Column(name = "name")
	private String name;
    
    @ManyToMany(cascade = {CascadeType.REFRESH})
    @JoinTable(
    		name = "room_access",
    		joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
    		inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
    		uniqueConstraints = {@UniqueConstraint(columnNames = {"room_id","employee_id"})}
    		)
    @JsonIgnoreProperties("accessibleRooms")
    private List<Employee> allowedEmployees;
	
	public Room() {
		super();
	}
	public Room(String Name) {
		super();
		this.setName(Name);

	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Employee> getAllowedEmployees() {
		return allowedEmployees;
	}
	public void setAllowedEmployees(List<Employee> allowedEmployees) {
		this.allowedEmployees = allowedEmployees;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allowedEmployees == null) ? 0 : allowedEmployees.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (allowedEmployees == null) {
			if (other.allowedEmployees != null)
				return false;
		} else if (!allowedEmployees.equals(other.allowedEmployees))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", allowedEmployees=" + allowedEmployees + "]";
//		return "Room [id=" + id + ", name=" + name + "]";
	}
}
