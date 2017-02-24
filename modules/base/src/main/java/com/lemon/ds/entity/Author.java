package com.lemon.ds.entity;

import com.lemon.ds.base.entity.IdSerialEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import static com.lemon.commons.enm.EnumSearchOperator.NotNull;

@Entity
@Table(name = "author", uniqueConstraints={@UniqueConstraint(columnNames={"name", "email", "address"})})
public class Author extends IdSerialEntity {
	@NotNull
	private String name;

	private String email;

	private String address;

	private String country;

	private String city;


	public Author() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}