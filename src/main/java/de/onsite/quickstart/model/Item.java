package de.onsite.quickstart.model;

/**
 * Item Bean class
 *
 */
public class Item
{	
	private Long id;
	private String itemName;
	private boolean done;
	
	public Item() {
		//empty
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


	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", done=" + done + "]";
	}
}