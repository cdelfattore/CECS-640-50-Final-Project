package crdelf01.finalproject;

import java.io.Serializable;

public class OrderLineBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int quantity;
	private double decimal;
	private int item_id;
	private int order_id;
	
	public OrderLineBean(){
		
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDecimal() {
		return decimal;
	}

	public void setDecimal(double decimal) {
		this.decimal = decimal;
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

	@Override
	public String toString() {
		return "OrderLineBean [quantity=" + quantity + ", decimal=" + decimal + ", item_id=" + item_id + ", order_id="
				+ order_id + "]";
	}
	
	
	
	

}
