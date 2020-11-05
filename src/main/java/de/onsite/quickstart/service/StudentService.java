package de.onsite.quickstart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.onsite.quickstart.model.Student;


/**
 * My first boot REST client
 *
 */
@Component
public class StudentService 
{

        @Value("${restServer.studentURLWithPort}")
        private String restServerURLWithPort;
        
        
		public List<Student> retrieveAllStudents() {
        	RestTemplate restTemplate = new RestTemplate();
        	ResponseEntity<List<Student>> studentResponse =
        	        restTemplate.exchange(restServerURLWithPort + "/students",
        	                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        	            });
        	List<Student> students = studentResponse.getBody();
        	return students;
        }
		
}