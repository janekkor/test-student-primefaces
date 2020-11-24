package de.onsite.quickstart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.onsite.quickstart.model.Item;
import de.onsite.quickstart.model.Student;


/**
 * My first boot REST client
 *
 */
@Component
public class RestService 
{

        @Value("${restServer.urlWithPort}")
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
		
		public List<Item> retrieveAllItems() {
        	RestTemplate restTemplate = new RestTemplate();
        	ResponseEntity<List<Item>> itemResponse =
        	        restTemplate.exchange(restServerURLWithPort + "/items",
        	                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Item>>() {
        	            });
        	List<Item> items = itemResponse.getBody();
        	return items;
        }
}