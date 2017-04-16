package crdelf01.finalproject;

import java.io.Serializable;

public class ItemBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private double price;
	private String description;
	private int item_id;
	
	public ItemBean(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	@Override
	public String toString() {
		return "ItemBean [name=" + name + ", price=" + price + ", description=" + description + ", item_id=" + item_id
				+ "]";
	}

}
