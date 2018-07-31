package com.techprimers.security.jwtsecurity.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class WorkHour {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
    private long id;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date workDate;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern = "HH:mm")
	private Time inputTime;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern = "HH:mm")
	private Time outputTime;
	
	@JoinColumn(name="user_id")
	@ManyToOne(cascade = CascadeType.ALL)
	private JwtUser user;
	
	public WorkHour(){
		
	}
	
	public WorkHour(Date workDate, Time inputTime, Time outputTime, JwtUser user) {
		this.workDate = workDate;
		this.inputTime = inputTime;
		this.outputTime = outputTime;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public Time getInputTime() {
		return inputTime;
	}

	public void setInputTime(Time inputTime) {
		this.inputTime = inputTime;
	}

	public Time getOutputTime() {
		return outputTime;
	}

	public void setOutputTime(Time outputTime) {
		this.outputTime = outputTime;
	}

	public JwtUser getUser() {
		return user;
	}

	public void setUser(JwtUser user) {
		this.user = user;
	}
}
