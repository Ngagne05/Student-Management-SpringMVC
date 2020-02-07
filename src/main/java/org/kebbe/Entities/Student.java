package org.kebbe.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Student implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=2,max=30)
	@NotEmpty
	private String name;
	
	@Column(name="birth_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")		
	private Date birthdate;
	private String email;
	private String photo;
	//for JPA and me
	public Student() {
		super();
	}
	//for me
	public Student(String name, Date birthdate, String email, String photo) {
		super();
		
		this.name = name;
		this.birthdate = birthdate;
		this.email = email;
		this.photo = photo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
