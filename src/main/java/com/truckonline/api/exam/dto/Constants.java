package com.truckonline.api.exam.dto;

/**
 * Constants
 */
public class Constants {

	public static enum DRIVER_ACTIVITY { DRIVING, REST, WORK, AVAILABILITY, OTHER };
	public static enum AMPLITUDE_TYPE { SERVICE_PERIOD, LONG_BREAK_PERIOD };

	/**
	 * Durée minimum d'une amplitude de coupure
	 */
	public static final long LONG_BREAK_MINIMUM_DURATION_MIN = 540;	// 9h
}
