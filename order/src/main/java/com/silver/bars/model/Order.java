package com.silver.bars.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Order {

	private String orderId;
	private String userId;
	private BigDecimal orderQuantity;
	private int price;
	// better to have relation as enum
	private String orderType;
	private String orderIdText;
	
	public String getOrderIdText() {
		return orderIdText;
	}
	public void setOrderIdText(String orderIdText) {
		this.orderIdText = orderIdText;
	}
	
	@JsonIgnore
	public String getOrderId() {
		return orderId;
	}
	public Order setOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public Order setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public BigDecimal getOrderQuantity() {
		return orderQuantity;
	}
	public Order setOrderQuantity(BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
		return this;
	}
	public int getPrice() {
		return price;
	}
	public Order setPrice(int price) {
		this.price = price;
		return this;
	}
	public String getOrderType() {
		return orderType;
	}
	public Order setOrderType(String orderType) {
		this.orderType = orderType;
		return this;
	}
	public Order() {
	}
	@Override
	public String toString() {
		return "Order:"+ orderQuantity + " kg for £"+ price + " //"+orderIdText;
	}
	
	
	
	
	
}
