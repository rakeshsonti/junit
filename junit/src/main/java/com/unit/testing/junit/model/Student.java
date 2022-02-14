/**
 * 
 */
package com.unit.testing.junit.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rambhajan Sonti
 * 13-Feb-2022
 * 12:07:17 pm
 */
@JsonInclude(Include.NON_NULL)
public class Student {
	private Integer rollNumber;
	private String name;
	private String subject;

	
	/**
	 * 
	 */
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rollNumber
	 * @param name
	 * @param subject
	 */
	public Student(Integer rollNumber, String name, String subject) {
		super();
		this.rollNumber = rollNumber;
		this.name = name;
		this.subject = subject;
	}

	public Integer getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(Integer rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Student [" + (rollNumber != null ? "rollNumber=" + rollNumber + ", " : "")
				+ (name != null ? "name=" + name + ", " : "") + (subject != null ? "subject=" + subject : "") + "]";
	}

}
