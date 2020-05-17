package com.stocksmarket.job.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(QrtzFiredTriggersId.class)
@Table(name = "qrtz_fired_triggers")
public class QrtzFiredTriggers implements SerializableEntity {

	@Column(name = "sched_name")
	private @Id
	String schedName;

	@Column(name = "entry_id")
	private @Id
	String entryId;

	@Basic
	@Column(name = "trigger_name")
	private String triggerName;

	@Basic
	@Column(name = "fired_time")
	private String firedTime;

	@Basic
	@Column(name = "priority")
	private String priority;

	@Basic
	@Column(name = "state")
	private String state;

	@Basic
	@Column(name = "job_name")
	private String jobName;

	public QrtzFiredTriggers() {

	}

	public String getSchedName() {
		return schedName;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}

	public String getEntryId() {
		return entryId;
	}

	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getFiredTime() {
		return firedTime;
	}

	public void setFiredTime(String firedTime) {
		this.firedTime = firedTime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

}
