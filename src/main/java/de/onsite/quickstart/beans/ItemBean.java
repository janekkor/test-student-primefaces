package de.onsite.quickstart.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import de.onsite.quickstart.model.Item;
import de.onsite.quickstart.model.Basket;
import de.onsite.quickstart.service.RestService;


@Component
@SessionScope
public class ItemBean {
	
	@Autowired
	private RestService restService;

	@SuppressWarnings("unused")
	private List<Item> items;
	
	private List<Item> allItems;
	
	@SuppressWarnings("unused")
	private List<Basket> baskets;
	
	private Basket activeBasket;
	
	private boolean editMode = true; 
	
	@PostConstruct
	public void init() {
		baskets = restService.retrieveAllBaskets();
		activeBasket = baskets.get(0);
		allItems = restService.retrieveAllItems();
		items = allItems.stream().filter(x->x.getBasket().getBasketName().equals(activeBasket.getBasketName())).collect(Collectors.toList());
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("itemBaskets", this.baskets);
		System.out.println("ItemBean.init()");
		System.out.println("editMode: " + editMode);
	}
	
	
	public void onEinkaufslisteChange() {
		if (activeBasket != null) {
			System.out.println("Ausgewählte Einkaufsliste: " + activeBasket.getBasketName());
			items = allItems.stream().filter(x->x.getBasket().getBasketName().equals(activeBasket.getBasketName())).collect(Collectors.toList());
		}
	}

	public void addNewItem() {
		if(items == null) {
			items = new ArrayList<Item>();
		}
		items.add(new Item(RandomUtils.nextLong(), "New Item"));
	}
	
	public void saveAllItems() {
		for (Item item : items) {
			if (item.getBasket() == null) {
				item.setBasket(activeBasket);
			}
		}
		restService.saveAllItems(items);
		retrieveAllItemsForCurrentBasket();
		
		FacesContext context = FacesContext.getCurrentInstance();
		if (items == null) {	         
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR , "Error",  "Die Daten wurden nicht gespeichert!") );
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO , "Success",  "Die Daten wurden erfolgreich gespeichert!") );
		}
	}
	
	public void retrieveAllItems() {
		items = restService.retrieveAllItems();
		return;
	}
	
	public void retrieveAllItemsForCurrentBasket() {
		allItems = restService.retrieveAllItems();
		items = restService.retrieveAllItemsForBasketId(activeBasket.getId());
		return;
	}
	
	public void retrieveAllBaskets() {
		baskets = restService.retrieveAllBaskets();
		return;
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
	
	public void buyItem(Long id) {
		assert(id != null);
		Item doneItem = null;
		for (Item item : items) {
			if (id.equals(item.getId())) {
				item.setDone(!item.isDone());
				doneItem = item;
				break;
			}
		}
		
		if (doneItem != null) {
			items.remove(doneItem);
			if (doneItem.isDone()) {
			//add the item to the end of the list
			items.add(doneItem);
			} else {
				//find first done item in the list
				Integer firstDoneItemIndex = -1;
				for (Item item : items) {
					if(item.isDone()) {
						firstDoneItemIndex = items.indexOf(item);
						break;
					}
				}
				if (firstDoneItemIndex == -1) {
					//add the item to the end of the list
					items.add(doneItem);
				} else {
					//add the item to the end of the list of the undone items
					items.add(firstDoneItemIndex, doneItem);
				}
			}
		}
		
	}
	
	public void editModeChange() {
		editMode = !editMode;
		System.out.println("editMode change: " + editMode);
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public List<Basket> getBaskets() {
		return baskets;
	}

	public void setBaskets(List<Basket> baskets) {
		this.baskets = baskets;
	}	

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		System.out.println("editMode set: " + editMode);
	}

	public Basket getActiveBasket() {
		return activeBasket;
	}

	public void setActiveBasket(Basket activeBasket) {
		this.activeBasket = activeBasket;
	}


	public List<Item> getAllItems() {
		return allItems;
	}


	public void setAllItems(List<Item> allItems) {
		this.allItems = allItems;
	}	
}