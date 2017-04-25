package crdelf01.finalproject;

import java.io.Serializable;

public class OrderLineBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int quantity;
	private double total;
	private int item_id;
	private int order_id;
	private String itemName;
	private double itemPrice;
	private int order_line_id;
	
	public OrderLineBean(){
		
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double decimal) {
		this.total = decimal;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getOrder_line_id() {
		return order_line_id;
	}

	public void setOrder_line_id(int order_line_id) {
		this.order_line_id = order_line_id;
	}

	@Override
	public String toString() {
		return "OrderLineBean [quantity=" + quantity + ", total=" + total + ", item_id=" + item_id + ", order_id="
				+ order_id + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", order_line_id=" + order_line_id
				+ "]";
	}
}
