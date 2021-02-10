package com.truckonline.api.exam.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Default;

import com.truckonline.api.exam.dto.AmplitudeInfo;
import com.truckonline.api.exam.dto.Constants.AMPLITUDE_TYPE;
import com.truckonline.api.exam.dto.Constants.DRIVER_ACTIVITY;
import com.truckonline.api.exam.dto.DriverActivityInfo;
import com.truckonline.api.exam.dto.RequestInfo;
import com.truckonline.api.exam.service.ExamService;

/**
 * ExamServiceImpl
 */
@Default
public class ExamServiceImpl implements ExamService {

	@Override
	public List<AmplitudeInfo> computeAmplitudes(RequestInfo requestInfo) {
		List<AmplitudeInfo> amplitudeInfos = new ArrayList<AmplitudeInfo>();
		List<DriverActivityInfo> rests = new ArrayList<DriverActivityInfo>();
		int firstRestMin = 0;
		//First Amplitude special case
		if (requestInfo.activities.get(0).getActivity().equals(DRIVER_ACTIVITY.REST)) {
			firstRestMin = calculateMin(requestInfo.activities.get(0).getEndDate(),
					requestInfo.activities.get(0).getStartDate());
			requestInfo.activities.remove(0);
		}
		for (DriverActivityInfo activity : requestInfo.activities) {
			append(activity, amplitudeInfos, rests);
		}
		amplitudeInfos.get(0).setTotalRestMin(amplitudeInfos.get(0).getTotalRestMin() + firstRestMin);
		return amplitudeInfos;
	}

	private void append(DriverActivityInfo activity, List<AmplitudeInfo> amplitudeInfos,
			List<DriverActivityInfo> rests) {
		if (activity.getActivity().equals(DRIVER_ACTIVITY.REST)) {
			rests.add(activity);
		} else {
			if (amplitudeInfos.isEmpty()) {
				// deal with the first amplitude
				if (isLong_Break_Period(rests)) {
					// in case the first amplitude is a Long_Break_Period
					amplitudeInfos.add(getLong_Break_Period(rests));
				} else {
					AmplitudeInfo amplitudeInfo = getService_Period(activity);
					amplitudeInfos.add(amplitudeInfo);
				}
			} else {
				AmplitudeInfo amplitudeInfo = amplitudeInfos.get(amplitudeInfos.size() - 1);
				if (isLong_Break_Period(rests)) {
					amplitudeInfos.add(getLong_Break_Period(rests));
					AmplitudeInfo newAmplitudeInfo = getService_Period(activity);
					amplitudeInfos.add(newAmplitudeInfo);
				} else {
					addActivity(amplitudeInfo, rests);
					addActivity(amplitudeInfo, activity);
				}

			}

		}
	}

	private void addActivity(AmplitudeInfo amplitudeInfo, List<DriverActivityInfo> rests) {
		if (rests == null || rests.isEmpty())
			return;
		for (DriverActivityInfo rest : rests)
			addActivity(amplitudeInfo, rest);
		// clear rests
		rests.clear();
	}

	private AmplitudeInfo getService_Period(DriverActivityInfo activity) {
		AmplitudeInfo amplitudeInfo = new AmplitudeInfo();
		amplitudeInfo.setType(AMPLITUDE_TYPE.SERVICE_PERIOD);
		amplitudeInfo.setStartDate(activity.getStartDate());
		amplitudeInfo.setEndDate(activity.getEndDate());
		amplitudeInfo.driverUid(activity.getDriverUid());
		amplitudeInfo.setTotalKm(activity.getEndKm() - activity.getStartKm());
		int totalMin = calculateMin(activity.getEndDate(), activity.getStartDate());
		switch (activity.getActivity()) {
		case AVAILABILITY: {
			amplitudeInfo.setTotalAvailabilityMin(totalMin);
			break;
		}
		case WORK: {
			amplitudeInfo.setTotalWorkMin(totalMin);
			break;
		}
		case DRIVING: {
			amplitudeInfo.setTotalDrivingMin(totalMin);
			break;
		}

		case OTHER: {
			amplitudeInfo.setTotalOtherMin(totalMin);
			break;
		}

		case REST: {
			amplitudeInfo.setTotalRestMin(totalMin);
			break;
		}

		}

		amplitudeInfo.setTotalServiceMin(totalMin);

		return amplitudeInfo;
	}

	private int calculateMin(Instant endDate, Instant startDate) {
		return (int) (endDate.getEpochSecond() - startDate.getEpochSecond()) / 60;

	}

	private AmplitudeInfo getLong_Break_Period(List<DriverActivityInfo> rests) {
		AmplitudeInfo amplitudeInfo = new AmplitudeInfo();
		amplitudeInfo.setType(AMPLITUDE_TYPE.LONG_BREAK_PERIOD);
		amplitudeInfo.setStartDate(rests.get(0).getStartDate());
		amplitudeInfo.setEndDate(rests.get(rests.size() - 1).getEndDate());
		amplitudeInfo.driverUid(rests.get(0).getDriverUid());
		int totalRestMin = 0, totalNoDataMin = 0, totalKm = 0;
		for (int i = 0; i < rests.size(); i++) {
			totalRestMin += calculateMin(rests.get(i).getEndDate(), rests.get(i).getStartDate());
			totalKm += rests.get(i).getEndKm() - rests.get(i).getStartKm();
			if (i > 0)
				totalNoDataMin += calculateMin(rests.get(i).getStartDate(), rests.get(i - 1).getEndDate());
		}
		amplitudeInfo.setTotalRestMin(totalRestMin);
		amplitudeInfo.setTotalNoDataMin(totalNoDataMin);
		amplitudeInfo.setTotalKm(totalKm);
		amplitudeInfo.setTotalServiceMin(0);
		rests.clear();
		return amplitudeInfo;
	}

	private boolean isLong_Break_Period(List<DriverActivityInfo> rests) {
		if (rests == null || rests.isEmpty()) {
			return false;
		}
		return (rests.get(rests.size() - 1).getEndDate().getEpochSecond()
				- rests.get(0).getStartDate().getEpochSecond()) >= 9 * 60 * 60;

	}

	private void addActivity(AmplitudeInfo amplitudeInfo, DriverActivityInfo activity) {
		int totalNoDataMin = calculateMin(activity.getStartDate(), amplitudeInfo.getEndDate());
		amplitudeInfo.setTotalNoDataMin(amplitudeInfo.getTotalNoDataMin() + totalNoDataMin);
		int totalMin = calculateMin(activity.getEndDate(), activity.getStartDate());
		amplitudeInfo.setTotalKm(amplitudeInfo.getTotalKm() + (activity.getEndKm() - activity.getStartKm()));
		amplitudeInfo.setEndDate(activity.getEndDate());
		switch (activity.getActivity()) {
		case AVAILABILITY: {
			amplitudeInfo.setTotalAvailabilityMin(amplitudeInfo.getTotalAvailabilityMin() + totalMin);
			break;
		}
		case WORK: {
			amplitudeInfo.setTotalWorkMin(amplitudeInfo.getTotalWorkMin() + totalMin);
			break;
		}
		case DRIVING: {
			amplitudeInfo.setTotalDrivingMin(amplitudeInfo.getTotalDrivingMin() + totalMin);
			break;
		}

		case OTHER: {
			amplitudeInfo.setTotalOtherMin(amplitudeInfo.getTotalOtherMin() + totalMin);
			break;
		}

		case REST: {
			amplitudeInfo.setTotalRestMin(amplitudeInfo.getTotalRestMin() + totalMin);
			break;
		}

		}

		if (!activity.getActivity().equals(DRIVER_ACTIVITY.REST))
			amplitudeInfo.setTotalServiceMin(amplitudeInfo.getTotalServiceMin() + totalMin);
	}
}
