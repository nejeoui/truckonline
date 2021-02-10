package com.truckonline.api.exam.dto;

import java.time.Instant;
import java.util.Objects;

import com.truckonline.api.exam.dto.Constants.AMPLITUDE_TYPE;

/**
 * AmplitudeInfo
 */
public class AmplitudeInfo {

	private String driverUid;
	private Instant startDate;
	private Instant endDate;
	private AMPLITUDE_TYPE type;
	private long totalDrivingMin = 0;
	private long totalRestMin = 0;
	private long totalWorkMin = 0;
	private long totalAvailabilityMin = 0;
	private long totalOtherMin = 0;
	private long totalNoDataMin = 0;
	private long totalServiceMin = 0;
	private long totalKm = 0;

	public String getDriverUid() {
		return driverUid;
	}

	public void setDriverUid(String driverUid) {
		this.driverUid = driverUid;
	}

	public AmplitudeInfo driverUid(String driverUid) {
		this.driverUid = driverUid;
		return this;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public AmplitudeInfo startDate(Instant startDate) {
		this.startDate = startDate;
		return this;
	}

	public Instant getEndDate() {
		return endDate;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}

	public AmplitudeInfo endDate(Instant endDate) {
		this.endDate = endDate;
		return this;
	}

	public AMPLITUDE_TYPE getType() {
		return type;
	}

	public void setType(AMPLITUDE_TYPE type) {
		this.type = type;
	}

	public AmplitudeInfo type(AMPLITUDE_TYPE type) {
		this.type = type;
		return this;
	}

	public long getTotalDrivingMin() {
		return totalDrivingMin;
	}

	public void setTotalDrivingMin(long totalDrivingMin) {
		this.totalDrivingMin = totalDrivingMin;
	}

	public AmplitudeInfo totalDrivingMin(long totalDrivingMin) {
		this.totalDrivingMin = totalDrivingMin;
		return this;
	}

	public long getTotalRestMin() {
		return totalRestMin;
	}

	public void setTotalRestMin(long totalRestMin) {
		this.totalRestMin = totalRestMin;
	}

	public AmplitudeInfo totalRestMin(long totalRestMin) {
		this.totalRestMin = totalRestMin;
		return this;
	}

	public long getTotalWorkMin() {
		return totalWorkMin;
	}

	public void setTotalWorkMin(long totalWorkMin) {
		this.totalWorkMin = totalWorkMin;
	}

	public AmplitudeInfo totalWorkMin(long totalWorkMin) {
		this.totalWorkMin = totalWorkMin;
		return this;
	}

	public long getTotalAvailabilityMin() {
		return totalAvailabilityMin;
	}

	public void setTotalAvailabilityMin(long totalAvailabilityMin) {
		this.totalAvailabilityMin = totalAvailabilityMin;
	}

	public AmplitudeInfo totalAvailabilityMin(long totalAvailabilityMin) {
		this.totalAvailabilityMin = totalAvailabilityMin;
		return this;
	}

	public long getTotalOtherMin() {
		return totalOtherMin;
	}

	public void setTotalOtherMin(long totalOtherMin) {
		this.totalOtherMin = totalOtherMin;
	}

	public AmplitudeInfo totalOtherMin(long totalOtherMin) {
		this.totalOtherMin = totalOtherMin;
		return this;
	}

	public long getTotalNoDataMin() {
		return totalNoDataMin;
	}

	public void setTotalNoDataMin(long totalNoDataMin) {
		this.totalNoDataMin = totalNoDataMin;
	}

	public AmplitudeInfo totalNoDataMin(long totalNoDataMin) {
		this.totalNoDataMin = totalNoDataMin;
		return this;
	}

	public long getTotalServiceMin() {
		return totalServiceMin;
	}

	public void setTotalServiceMin(long totalServiceMin) {
		this.totalServiceMin = totalServiceMin;
	}

	public AmplitudeInfo totalServiceMin(long totalServiceMin) {
		this.totalServiceMin = totalServiceMin;
		return this;
	}

	public long getTotalKm() {
		return totalKm;
	}

	public void setTotalKm(long totalKm) {
		this.totalKm = totalKm;
	}

	public AmplitudeInfo totalKm(long totalKm) {
		this.totalKm = totalKm;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof AmplitudeInfo))
			return false;
		AmplitudeInfo other = (AmplitudeInfo) o;
		return Objects.equals(driverUid, other.driverUid)
				&& Objects.equals(startDate, other.startDate)
				&& Objects.equals(endDate, other.endDate)
				&& Objects.equals(type, other.type)
				&& Objects.equals(totalDrivingMin, other.totalDrivingMin)
				&& Objects.equals(totalRestMin, other.totalRestMin)
				&& Objects.equals(totalWorkMin, other.totalWorkMin)
				&& Objects.equals(totalAvailabilityMin, other.totalAvailabilityMin)
				&& Objects.equals(totalOtherMin, other.totalOtherMin)
				&& Objects.equals(totalNoDataMin, other.totalNoDataMin)
				&& Objects.equals(totalServiceMin, other.totalServiceMin)
				&& Objects.equals(totalKm, other.totalKm);
	}

	@Override
	public int hashCode() {
		return Objects.hash(driverUid, startDate, endDate, type, totalDrivingMin, totalRestMin, totalWorkMin, totalAvailabilityMin, totalOtherMin, totalNoDataMin, totalServiceMin, totalKm);
	}

	@Override
	public String toString() {
		return "{" +
				" driverUid='" + getDriverUid() + "'" +
				", startDate='" + getStartDate() + "'" +
				", endDate='" + getEndDate() + "'" +
				", type='" + getType() + "'" +
				", totalDrivingMin='" + getTotalDrivingMin() + "'" +
				", totalRestMin='" + getTotalRestMin() + "'" +
				", totalWorkMin='" + getTotalWorkMin() + "'" +
				", totalAvailabilityMin='" + getTotalAvailabilityMin() + "'" +
				", totalOtherMin='" + getTotalOtherMin() + "'" +
				", totalNoDataMin='" + getTotalNoDataMin() + "'" +
				", totalServiceMin='" + getTotalServiceMin() + "'" +
				", totalKm='" + getTotalKm() + "'" +
				"}";
	}
}
