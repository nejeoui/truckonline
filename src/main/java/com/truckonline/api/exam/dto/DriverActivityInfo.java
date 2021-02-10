package com.truckonline.api.exam.dto;

import java.time.Instant;
import java.util.Objects;

import com.truckonline.api.exam.dto.Constants.DRIVER_ACTIVITY;

/**
 * DriverActivityInfo
 */
public class DriverActivityInfo {

	private DRIVER_ACTIVITY activity;
	private Instant startDate;
	private Instant endDate;
	private Integer startKm;
	private Integer endKm;
	private String driverUid;
	private String vehicleUid;

	public DRIVER_ACTIVITY getActivity() {
		return activity;
	}

	public void setActivity(DRIVER_ACTIVITY activity) {
		this.activity = activity;
	}

	public DriverActivityInfo activity(DRIVER_ACTIVITY activity) {
		this.activity = activity;
		return this;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public DriverActivityInfo startDate(Instant startDate) {
		this.startDate = startDate;
		return this;
	}

	public Instant getEndDate() {
		return endDate;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}

	public DriverActivityInfo endDate(Instant endDate) {
		this.endDate = endDate;
		return this;
	}

	public Integer getStartKm() {
		return startKm;
	}

	public void setStartKm(Integer startKm) {
		this.startKm = startKm;
	}

	public DriverActivityInfo startKm(Integer startKm) {
		this.startKm = startKm;
		return this;
	}

	public Integer getEndKm() {
		return endKm;
	}

	public void setEndKm(Integer endKm) {
		this.endKm = endKm;
	}

	public DriverActivityInfo endKm(Integer endKm) {
		this.endKm = endKm;
		return this;
	}

	public String getDriverUid() {
		return driverUid;
	}

	public void setDriverUid(String driverUid) {
		this.driverUid = driverUid;
	}

	public DriverActivityInfo driverUid(String driverUid) {
		this.driverUid = driverUid;
		return this;
	}

	public String getVehicleUid() {
		return vehicleUid;
	}

	public void setVehicleUid(String vehicleUid) {
		this.vehicleUid = vehicleUid;
	}

	public DriverActivityInfo vehicleUid(String vehicleUid) {
		this.vehicleUid = vehicleUid;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof DriverActivityInfo))
			return false;
		DriverActivityInfo other = (DriverActivityInfo) o;
		return Objects.equals(activity, other.activity)
				&& Objects.equals(startDate, other.startDate)
				&& Objects.equals(endDate, other.endDate)
				&& Objects.equals(startKm, other.startKm)
				&& Objects.equals(endKm, other.endKm)
				&& Objects.equals(driverUid, other.driverUid)
				&& Objects.equals(vehicleUid, other.vehicleUid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(activity, startDate, endDate, startKm, endKm, driverUid, vehicleUid);
	}

	@Override
	public String toString() {
		return "{" +
				" activity='" + getActivity() + "'" +
				", startDate='" + getStartDate() + "'" +
				", endDate='" + getEndDate() + "'" +
				", startKm='" + getStartKm() + "'" +
				", endKm='" + getEndKm() + "'" +
				", driverUid='" + getDriverUid() + "'" +
				", vehicleUid='" + getVehicleUid() + "'" +
				"}";
	}
}
