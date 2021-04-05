package de.onsite.quickstart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.onsite.quickstart.model.Item;
import de.onsite.quickstart.model.ItemList;
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
        
        RestTemplate restTemplate = new RestTemplate();
        
		public List<Student> retrieveAllStudents() {
        	ResponseEntity<List<Student>> studentResponse =
        	        restTemplate.exchange(restServerURLWithPort + "/students",
        	                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        	            });
        	List<Student> students = studentResponse.getBody();
        	return students;
        }
		
		public List<Item> retrieveAllItems() {
        	ResponseEntity<List<Item>> itemResponse =
        	        restTemplate.exchange(restServerURLWithPort + "/items",
        	                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Item>>() {
        	            });
        	List<Item> items = itemResponse.getBody();
        	System.out.println("All items in RestService read from REST: " + items);
        	return items;
        }
		
		public List<Item> retrieveAllItemsForItemList(Long id) {
			System.out.println("ItemList id=" + id);
	        RestTemplate restTemplate = new RestTemplate();
	   	 
	        // Data attached to the request.
	        HttpEntity<Long> requestBody = new HttpEntity<>(id);			
        	ResponseEntity<List<Item>> itemResponse =
        	        restTemplate.exchange(restServerURLWithPort + "/itemsForItemList",
        	                    HttpMethod.POST, requestBody, new ParameterizedTypeReference<List<Item>>() {
        	            });
        	List<Item> items = itemResponse.getBody();
        	System.out.println("All items in RestService read from REST: " + items);
        	return items;
        }		
		
		public List<ItemList> retrieveAllItemLists() {
        	ResponseEntity<List<ItemList>> itemListResponse =
        	        restTemplate.exchange(restServerURLWithPort + "/itemLists",
        	                    HttpMethod.GET, null, new ParameterizedTypeReference<List<ItemList>>() {
        	            });
        	List<ItemList> itemLists = itemListResponse.getBody();
        	System.out.println("All item lists in RestService read from REST: " + itemLists);
        	return itemLists;
        }

		public Student retrieveStudentById(Long id) {
			Map<String, Long> param = new HashMap<>();
			param.put("id", id);
			
			Student student = restTemplate.getForObject(restServerURLWithPort + "/student", Student.class, param);
					
			return student;
		}
		
		public List<Item> saveAllItems(List<Item> items) {
			List<Item> resultItems = null;
			
	        RestTemplate restTemplate = new RestTemplate();
	 
	        // Data attached to the request.
	        HttpEntity<List<Item>> requestBody = new HttpEntity<>(items);
	 
	        // Send request with POST method.
	        ResponseEntity<List<Item>> result 
	             = restTemplate.exchange(restServerURLWithPort + "/itemsUpdate",
 	                    HttpMethod.POST, requestBody, new ParameterizedTypeReference<List<Item>>() {
 	            });
	 
	        System.out.println("Status code:" + result.getStatusCode());
	 
	        // Code = 200.
	        if (result.getStatusCode() == HttpStatus.OK) {
	        	resultItems = result.getBody();
	            System.out.println("Result items after save: " + resultItems);
	        }
			
			return resultItems;
		}
}