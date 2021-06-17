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
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
    
    @Column(name = "name")
	private String name;
    
    @Column(name = "surname")
	private String surname;
    
    @Column(name = "pname")
	private String pname;
    
    
    @ManyToMany(cascade = {CascadeType.REFRESH})
    @JoinTable(
    		name = "room_access",
    		joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
    		inverseJoinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
    		uniqueConstraints = {@UniqueConstraint(columnNames = {"employee_id","room_id"})}
    		)
    @JsonIgnoreProperties("allowedEmployees")
    private List<Room> accessibleRooms;
	
	public Employee() {
		super();
	}
	public Employee(String Name, String Surname, String Pname) {
		super();
		this.setName(Name);
		this.setSurname(Surname);
		this.setPname(Pname); 
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public List<Room> getAccessibleRooms() {
		return accessibleRooms;
	}
	public void setAccessibleRooms(List<Room> accessibleRooms) {
		this.accessibleRooms = accessibleRooms;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessibleRooms == null) ? 0 : accessibleRooms.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Employee other = (Employee) obj;
		if (accessibleRooms == null) {
			if (other.accessibleRooms != null)
				return false;
		} else if (!accessibleRooms.equals(other.accessibleRooms))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pname == null) {
			if (other.pname != null)
				return false;
		} else if (!pname.equals(other.pname))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", surname=" + surname + ", pname=" + pname
				+ ", AccessibleRooms=" + accessibleRooms + "]";
	}

    
}
