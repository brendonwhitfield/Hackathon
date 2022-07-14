package com.qa.TMTY.domain;

import java.util.Objects;

public class Logins {

	private Long loginsId;
	private String assignerName;
	private String driversName;

	public Logins(String assignerName, String driversName) {

		this.setAssignerName(assignerName);
		this.setDriversName(driversName);
	}

	public Logins(String assignerName, String driversName, Long loginsId) {

		this.setAssignerName(assignerName);
		this.setDriversName(driversName);
		this.setLoginsId(loginsId);
	}

	public Long getLoginsId() {
		return loginsId;
	}

	public void setLoginsId(Long loginsId) {
		this.loginsId = loginsId;
	}

	public String getAssignerName() {
		return assignerName;
	}

	public void setAssignerName(String assignerName) {
		this.assignerName = assignerName;
	}

	public String getDriversName() {
		return driversName;
	}

	public void setDriversName(String driversName) {
		this.driversName = driversName;
	}

	@Override
	public String toString() {
		return "Logins [loginsId=" + loginsId + ", assignerName=" + assignerName + ", driversName=" + driversName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(assignerName, driversName, loginsId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Logins other = (Logins) obj;
		return Objects.equals(assignerName, other.assignerName) && Objects.equals(driversName, other.driversName)
				&& Objects.equals(loginsId, other.loginsId);
	}

}
