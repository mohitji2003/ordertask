package com.silver.bars.response;

import java.util.List;

import com.silver.bars.model.Order;

public class OrderResponseBean extends BaseResponseBean {
	
	private List<Order> orderList;
	private List<String> orderListText;

	public List<String> getOrderListText() {
		return orderListText;
	}

	public void setOrderListText(List<String> orderListText) {
		this.orderListText = orderListText;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

}
