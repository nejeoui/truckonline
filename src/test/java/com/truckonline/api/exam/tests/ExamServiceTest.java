package com.truckonline.api.exam.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.truckonline.api.exam.configuration.JacksonJsonProvider;
import com.truckonline.api.exam.dto.AmplitudeInfo;
import com.truckonline.api.exam.dto.DriverActivityInfo;
import com.truckonline.api.exam.dto.RequestInfo;
import com.truckonline.api.exam.tests.base.ArquillianBase;

import org.apache.commons.io.IOUtils;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * ExamServiceTest
 */
@RunWith(Arquillian.class)
public class ExamServiceTest extends ArquillianBase {

	private static final ObjectMapper mapper = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

	@Test
	@RunAsClient
	public void test(@ArquillianResource URL url) throws Exception {

		List<DriverActivityInfo> activities = importActivities();
		System.out.println("activities size = "+activities.size());
		RequestInfo req = new RequestInfo();
		req.activities = activities;

		Client client = ClientBuilder.newClient()
				.register(new JacksonJsonProvider());
		assertEquals(url.toString(),"http://127.0.0.1:8080/tests/apis/exam/1.0/");
		Response rsp = client.target(url.toURI())
				.path("/")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(req));

		assertEquals("Check response status", Status.OK.getStatusCode() , rsp.getStatus());

		System.out.println("\n======\n\n RESULT \n\n======\n");

		// List<AmplitudeInfo> result = rsp.readEntity(new GenericType<List<AmplitudeInfo>>() {});
		String json = rsp.readEntity(String.class);
		List<AmplitudeInfo> result = mapper.readValue(json, new TypeReference<List<AmplitudeInfo>>() {});
		result.forEach(System.out::println);

		System.out.println("\n======\n\n REFERENCE \n\n======\n");

		List<AmplitudeInfo> reference = importAmplitudes();
		reference.forEach(System.out::println);

		assertArrayEquals("Check amplitudes", reference.toArray(), result.toArray());
	}

	private List<DriverActivityInfo> importActivities() throws IOException {

		try (InputStream in = getClass().getClassLoader().getResourceAsStream("exports/activities.json")) {

			String json = IOUtils.toString(in, "UTF-8");

			return mapper.readValue(json, new TypeReference<List<DriverActivityInfo>>() {});

		} catch (IOException e) {
			throw e;
		}
	}

	private List<AmplitudeInfo> importAmplitudes() throws IOException {

		try (InputStream in = getClass().getClassLoader().getResourceAsStream("exports/amplitudes.json")) {

			String json = IOUtils.toString(in, "UTF-8");

			return mapper.readValue(json, new TypeReference<List<AmplitudeInfo>>() {});

		} catch (IOException e) {
			throw e;
		}
	}
}
