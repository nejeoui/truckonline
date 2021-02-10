package com.truckonline.api.exam;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.truckonline.api.exam.dto.AmplitudeInfo;
import com.truckonline.api.exam.dto.RequestInfo;
import com.truckonline.api.exam.service.ExamService;

@Path("/")
public class AmplitudeRestController {
	
	@Inject
	ExamService examService;
	
	@POST
    @Produces({ MediaType.APPLICATION_JSON})
    public  List<AmplitudeInfo> computeAmplitudes(RequestInfo requestInfo) {
        return examService.computeAmplitudes(requestInfo); 
    }

}
