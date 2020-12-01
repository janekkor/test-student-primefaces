package de.onsite.quickstart.model;

/**
 * Item Bean class
 *
 */
public class Item
{	
	private String itemName;
	private Long id;
	
	public Item() {
		//empty
	}	
	
	public Item(String itemName) {
		this.itemName = itemName;
	}
	
	public Item(Long id, String itemName) {
		this.id = id;
		this.itemName = itemName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + "]";
	}
}