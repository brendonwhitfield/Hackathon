package com.qa.TMTY.domain;

import java.util.Objects;

public class Delivery {
	
	private Long id;
	private Long snapshot_id;
	private String driver_name; 
	
	// constructor 
	public Delivery(Long id, Long snapshot_id, String driver_name) {
		this.id = id;
		this.snapshot_id = snapshot_id;
		this.driver_name = driver_name;
	}
	
	// constructor without the id 
	public Delivery(Long snapshot_id, String driver_name) {
		this.snapshot_id = snapshot_id;
		this.driver_name = driver_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSnapshot_id() {
		return snapshot_id;
	}

	public void setSnapshot_id(long snapshot_id) {
		this.snapshot_id = snapshot_id;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(driver_name, id, snapshot_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		return Objects.equals(driver_name, other.driver_name) && id == other.id && snapshot_id == other.snapshot_id;
	}
	
	
	
	
	
	

}
