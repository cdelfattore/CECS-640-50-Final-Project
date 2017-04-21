package crdelf01.finalproject;

import java.io.Serializable;

public class ShoppingCartItemBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int quantity;
	private int item_id;
	private double price;
	private String name;
	
	public ShoppingCartItemBean() {
		
	}
	
	public ShoppingCartItemBean(int id, int qty, String n, double p){
		this.item_id = id;
		this.quantity = qty;
		this.price = p;
		this.name = n;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ShoppingCartItemBean [quantity=" + quantity + ", item_id=" + item_id + ", price=" + price + ", name="
				+ name + "]";
	}

	
}
