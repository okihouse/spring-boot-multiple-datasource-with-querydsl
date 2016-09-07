package com.rest.dao.first;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "first_table")
//@Data no lombok
public class First {

	@Id
	@GeneratedValue
	@Column(name = "first_no")
	private Integer firstNo;

	@Column(name = "message")
	private String message;

	public Integer getFirstNo() {
		return firstNo;
	}

	public void setFirstNo(Integer firstNo) {
		this.firstNo = firstNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
