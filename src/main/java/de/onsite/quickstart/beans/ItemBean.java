package de.onsite.quickstart.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import de.onsite.quickstart.model.Item;
import de.onsite.quickstart.service.RestService;


@Component
@SessionScope
public class ItemBean {

	@Autowired
	private RestService restService;

	@SuppressWarnings("unused")
	private List<Item> items;
	
	@PostConstruct
	public void init() {
		items = restService.retrieveAllItems();
	}

	public void addNewItem() {
		if(items == null) {
			items = new ArrayList<Item>();
		}
		items.add(new Item(RandomUtils.nextLong(), "New Item"));
	}
	
	public void saveAllItems() {
		items = restService.saveAllItems(items);
		
		FacesContext context = FacesContext.getCurrentInstance();
		if (items == null) {	         
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR , "Error",  "Die Daten wurden nicht gespeichert!") );
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO , "Success",  "Die Daten wurden erfolgreich gespeichert!") );
		}
	}
	
	public void retrieveAllItems() {
		items = restService.retrieveAllItems();
	}
	
	public void deleteItem(Long id) {
		assert(id != null);
		List<Item> tempItems = new ArrayList<Item>();
		for (Item item : items) {
			if (id.equals(item.getId())) {
				tempItems.add(item);
			}
		}
		items.removeAll(tempItems);
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}	
}