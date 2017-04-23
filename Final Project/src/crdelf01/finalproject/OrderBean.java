package crdelf01.finalproject;

import java.io.Serializable;

public class OrderBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int userid;
	private double total;
	private int order_id;
	
	public OrderBean(){
		
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	@Override
	public String toString() {
		return "OrderBean [userid=" + userid + ", total=" + total + ", order_id=" + order_id + "]";
	}
	
	

}
