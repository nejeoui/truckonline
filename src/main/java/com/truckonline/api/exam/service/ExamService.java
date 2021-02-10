package com.truckonline.api.exam.service;

import java.util.List;

import com.truckonline.api.exam.dto.AmplitudeInfo;
import com.truckonline.api.exam.dto.RequestInfo;

/**
 * ExamService
 */
public interface ExamService {

	public List<AmplitudeInfo> computeAmplitudes(RequestInfo activities);

}
