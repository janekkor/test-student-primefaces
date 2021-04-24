package de.onsite.quickstart.model;

import java.util.List;

/**
 * Baket class
 *
 */
public class Basket
{	
	private Long id;
	private String basketName;
	private List<Item> items;
	
	public Basket() {
		//empty for hibernate
	}
	
	public Basket(Long id, String basketName) {
		this.id = id;
		this.basketName = basketName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBasketName() {
		return basketName;
	}

	public void setBasketName(String basketName) {
		this.basketName = basketName;
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
		return "Basket [id=" + id + ", basketName=" + basketName + "items=" + sBuilder.toString() + "]";
	}
}