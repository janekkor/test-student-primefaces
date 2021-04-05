package de.onsite.quickstart.model;

import java.util.List;

/**
 * ItemList class
 *
 */
public class ItemList
{	
	private Long id;
	private String listName;
	private List<Item> items;
	
	public ItemList() {
		//empty for hibernate
	}
	
	public ItemList(Long id, String listName) {
		this.id = id;
		this.listName = listName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder("Test ABC");
		//items.stream().forEach(i -> sBuilder.append(i.toString()));
		return "ItemList [id=" + id + ", listName=" + listName + "items=" + sBuilder.toString() + "]";
	}
}