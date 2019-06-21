package com.silver.bars.order;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.silver.bars.constant.OrderType;
import com.silver.bars.model.Order;

public class OrderDataStoreTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 /* 
	 * Exercise 1 - Validate correct SELL count Retrieve
	 * user1 and user4 has same price then their quantity will add
	 */
	  @Test public void ValidateExpectedSELLCountRetrieve() { 
		  Order ord1 = new Order().setOrderId("a").setOrderQuantity(new BigDecimal("3.5")).setOrderType("SELL").setPrice(306).setUserId("user1");
		  Order ord2 = new Order().setOrderId("b").setOrderQuantity(new BigDecimal("1.2")).setOrderType("SELL").setPrice(310).setUserId("user2");
		  Order ord3 = new Order().setOrderId("c").setOrderQuantity(new BigDecimal("1.5")).setOrderType("SELL").setPrice(307).setUserId("user3");
		  Order ord4 = new Order().setOrderId("d").setOrderQuantity(new BigDecimal("2.0")).setOrderType("SELL").setPrice(306).setUserId("user4");
		  Order ord5 = new Order().setOrderId("e").setOrderQuantity(new BigDecimal("2.0")).setOrderType("BUY").setPrice(309).setUserId("user5");
		  
		  OrderDataStore orderStore = OrderDataStore.getInstance();
		  orderStore.deleteAll();
		  orderStore.add(ord1);
		  orderStore.add(ord2);
		  orderStore.add(ord3);
		  orderStore.add(ord4);
		  orderStore.add(ord5);
		  
		  List<Order> ordList = orderStore.getOrderRecords(OrderType.SELL);
	
		  //just for reference purpose
		  logger.debug("ValidateExpectedSELLCountRetrieve: {}", ordList);
		 	 
		  assertEquals("ordera + orderd", ordList.get(0).getOrderIdText());
		  assertEquals("5.5", ordList.get(0).getOrderQuantity().toString());
		  
		  assertEquals(3, ordList.size()); 
	  }
	  
	 /* 
	 * Exercise 2 - Validate correct BUY Count Retrieve 
	 * user2 and user3 has same price then their quantity will add
	 */
	  @Test public void ValidateExpectedBUYCountRetrieve() { 
		  Order ord1 = new Order().setOrderId("a").setOrderQuantity(new BigDecimal("3.5")).setOrderType("BUY").setPrice(306).setUserId("user1");
		  Order ord2 = new Order().setOrderId("b").setOrderQuantity(new BigDecimal("1.2")).setOrderType("BUY").setPrice(310).setUserId("user2");
		  Order ord3 = new Order().setOrderId("c").setOrderQuantity(new BigDecimal("1.5")).setOrderType("BUY").setPrice(310).setUserId("user3");
		  Order ord4 = new Order().setOrderId("d").setOrderQuantity(new BigDecimal("2.0")).setOrderType("BUY").setPrice(308).setUserId("user4");
		  Order ord5 = new Order().setOrderId("e").setOrderQuantity(new BigDecimal("2.0")).setOrderType("SELL").setPrice(306).setUserId("user5");
		  
		  OrderDataStore orderStore = OrderDataStore.getInstance();
		  orderStore.deleteAll();
		  orderStore.add(ord1);
		  orderStore.add(ord2);
		  orderStore.add(ord3);
		  orderStore.add(ord4);
		  orderStore.add(ord5);
		  
		  List<Order> ordList = orderStore.getOrderRecords(OrderType.BUY);
		  
	      //just for reference purpose
		  logger.debug("ValidateExpectedBUYCountRetrieve:{}", ordList);
		 
		  assertEquals("orderb + orderc", ordList.get(0).getOrderIdText());
		  assertEquals(3, ordList.size()); 
	  }
	 		
	 /* 
	 * Exercise 3 - Validate correct BUY Order in sorting Order
	 */
	  @Test public void ValidateExpectedBUYSortOrder() { 
		  Order ord1 = new Order().setOrderId("a").setOrderQuantity(new BigDecimal("1.1")).setOrderType("BUY").setPrice(100).setUserId("user1");
		  Order ord2 = new Order().setOrderId("b").setOrderQuantity(new BigDecimal("1.9")).setOrderType("BUY").setPrice(1000).setUserId("user2");
		  Order ord3 = new Order().setOrderId("c").setOrderQuantity(new BigDecimal("1.5")).setOrderType("BUY").setPrice(10).setUserId("user3");
		  Order ord4 = new Order().setOrderId("d").setOrderQuantity(new BigDecimal("1.2")).setOrderType("BUY").setPrice(50).setUserId("user4");
		  Order ord5 = new Order().setOrderId("e").setOrderQuantity(new BigDecimal("1.2")).setOrderType("BUY").setPrice(50).setUserId("user5");
		  Order ord6 = new Order().setOrderId("f").setOrderQuantity(new BigDecimal("1.2")).setOrderType("SELL").setPrice(50).setUserId("user5");
		  
		  OrderDataStore orderStore = OrderDataStore.getInstance();
		  orderStore.deleteAll();
		  orderStore.add(ord1);
		  orderStore.add(ord2);
		  orderStore.add(ord3);
		  orderStore.add(ord4);
		  orderStore.add(ord5);
		  orderStore.add(ord6);
		  
		  List<Order> ordList = orderStore.getOrderRecords(OrderType.BUY);
		  //just for reference purpose
		  logger.debug("ValidateExpectedBUYSortOrder:{}", ordList);
		 
		  assertEquals(1000, ordList.get(0).getPrice()); 
		  assertEquals(10, ordList.get(3).getPrice()); 
	  }
			  
	 /* 
	 * Exercise 4 - Validate correct SELL Order in sorting Order
	 */
	  @Test public void ValidateExpectedSELLSortOrder() { 
		  Order ord1 = new Order().setOrderId("a").setOrderQuantity(new BigDecimal("1.1")).setOrderType("SELL").setPrice(100).setUserId("user1");
		  Order ord2 = new Order().setOrderId("b").setOrderQuantity(new BigDecimal("1.9")).setOrderType("SELL").setPrice(1000).setUserId("user2");
		  Order ord3 = new Order().setOrderId("c").setOrderQuantity(new BigDecimal("1.5")).setOrderType("SELL").setPrice(10).setUserId("user3");
		  Order ord4 = new Order().setOrderId("d").setOrderQuantity(new BigDecimal("1.2")).setOrderType("SELL").setPrice(50).setUserId("user4");
		  Order ord5 = new Order().setOrderId("e").setOrderQuantity(new BigDecimal("1.2")).setOrderType("SELL").setPrice(50).setUserId("user5");
		  Order ord6 = new Order().setOrderId("f").setOrderQuantity(new BigDecimal("1.2")).setOrderType("BUY").setPrice(50).setUserId("user6");
		  
		  OrderDataStore orderStore = OrderDataStore.getInstance();
		  orderStore.deleteAll();
		  orderStore.add(ord1);
		  orderStore.add(ord2);
		  orderStore.add(ord3);
		  orderStore.add(ord4);
		  orderStore.add(ord5);
		  orderStore.add(ord6);
		  
		  List<Order> ordList = orderStore.getOrderRecords(OrderType.SELL);
		  //just for reference purpose
		  logger.debug("ValidateExpectedSELLSortOrder:{}", ordList);
		 
		  assertEquals(10, ordList.get(0).getPrice()); 
		  assertEquals(1000, ordList.get(3).getPrice()); 
	  }


	 /* 
	 * Exercise 5 - Validate correct SELL Amd BUY count Retrieve
	 * user1 and user4 has same price then their quantity will add
	 * user6 and user7 has same price then their quantity will add
	 */
	  @Test public void ValidateExpecteSELLAndBUYCountRetrieve() { 
		  Order ord1 = new Order().setOrderId("a").setOrderQuantity(new BigDecimal("3.5")).setOrderType("SELL").setPrice(306).setUserId("user1");
		  Order ord2 = new Order().setOrderId("b").setOrderQuantity(new BigDecimal("1.2")).setOrderType("SELL").setPrice(310).setUserId("user2");
		  Order ord3 = new Order().setOrderId("c").setOrderQuantity(new BigDecimal("1.5")).setOrderType("SELL").setPrice(307).setUserId("user3");
		  Order ord4 = new Order().setOrderId("d").setOrderQuantity(new BigDecimal("2.0")).setOrderType("SELL").setPrice(306).setUserId("user4");
		  
		  Order ord5 = new Order().setOrderId("e").setOrderQuantity(new BigDecimal("3.5")).setOrderType("BUY").setPrice(306).setUserId("user5");
		  Order ord6 = new Order().setOrderId("f").setOrderQuantity(new BigDecimal("1.2")).setOrderType("BUY").setPrice(310).setUserId("user6");
		  Order ord7 = new Order().setOrderId("g").setOrderQuantity(new BigDecimal("1.5")).setOrderType("BUY").setPrice(310).setUserId("user7");
		  Order ord8 = new Order().setOrderId("h").setOrderQuantity(new BigDecimal("2.0")).setOrderType("BUY").setPrice(308).setUserId("user8");
			  
		  OrderDataStore orderStore = OrderDataStore.getInstance();
		  orderStore.deleteAll();
		  orderStore.add(ord1);
		  orderStore.add(ord2);
		  orderStore.add(ord3);
		  orderStore.add(ord4);
		  orderStore.add(ord5);
		  orderStore.add(ord6);
		  orderStore.add(ord7);
		  orderStore.add(ord8);
	
		  List<Order> ordList = orderStore.getOrderRecords(OrderType.ALL);
		  //just for reference purpose
		  logger.debug("ValidateExpecteSELLAndBUYCountRetrieve:{}", ordList);
		 
		  assertEquals(6, ordList.size()); 
	  }
		
	  
	 /* 
	 * Exercise 6 - Validate Add Order
	 * user1 and user2 has added properly
	 */
	  @Test public void ValidateAddOrder() { 
		  Order ord1 = new Order().setOrderId("a").setOrderQuantity(new BigDecimal("3.5")).setOrderType("SELL").setPrice(306).setUserId("user1");
		  Order ord2 = new Order().setOrderId("b").setOrderQuantity(new BigDecimal("1.2")).setOrderType("BUY").setPrice(310).setUserId("user2");
			  
		  OrderDataStore orderStore = OrderDataStore.getInstance();
		  orderStore.deleteAll();
		  orderStore.add(ord1);
		  orderStore.add(ord2);
		 
		  List<Order> ordList = orderStore.getOrderRecords(OrderType.ALL);
		  //just for reference purpose
		  logger.debug("ValidateAddOrder:{}", ordList);
		 
		  assertEquals(2, ordList.size()); 
	  }
	  
	 /* 
	 * Exercise 7 - Validate Cancelled Order
	 * user2 Order has cancelled properly
	 */
	  @Test public void ValidateCancelValidOrder() { 
		Order ord1 = new Order().setOrderId("a").setOrderQuantity(new BigDecimal("3.5")).setOrderType("SELL").setPrice(306).setUserId("user1");
		Order ord2 = new Order().setOrderId("b").setOrderQuantity(new BigDecimal("1.2")).setOrderType("BUY").setPrice(310).setUserId("user2");
			  
		OrderDataStore orderStore = OrderDataStore.getInstance();
		orderStore.deleteAll();
		orderStore.add(ord1);
		orderStore.add(ord2);
	
		List<Order> ordList = orderStore.getOrderRecords(OrderType.ALL);
		//just for reference purpose
		logger.debug("ValidateCancelValidOrder BEFORE Cancel:{}", ordList);
		 
		assertEquals(2, ordList.size()); 
		 
		try {
			orderStore.deleteOrder(ord2.getOrderId());
			ordList = orderStore.getOrderRecords(OrderType.ALL);
		} catch (CustomException e) {
			  // TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("ValidateCancelValidOrder AFTER Cancel:{}", ordList);
		assertEquals(1, ordList.size()); 
	  }
		
	 /* 
	 * Exercise 8 - Validate invalid Cancelled Order
	 * No user order has cancelled due to invalid orderId
	 */
	  @Test public void ValidateCancelInValidOrder() { 
		Order ord1 = new Order().setOrderId("a").setOrderQuantity(new BigDecimal("3.5")).setOrderType("SELL").setPrice(306).setUserId("user1");
		Order ord2 = new Order().setOrderId("b").setOrderQuantity(new BigDecimal("1.2")).setOrderType("BUY").setPrice(310).setUserId("user2");
			  
		OrderDataStore orderStore = OrderDataStore.getInstance();
		orderStore.deleteAll();
		orderStore.add(ord1);
		orderStore.add(ord2);
	
		List<Order> ordList = orderStore.getOrderRecords(OrderType.ALL);
		//just for reference purpose
		logger.debug("ValidateCancelInValidOrder BEFORE Cancel :{}", ordList);
		 
		assertEquals(2, ordList.size()); 
		 
		try {
			orderStore.deleteOrder("aaa");
		} catch (CustomException e) {
			logger.debug("Cancel: {}", e.getMessage());
		}
		ordList = orderStore.getOrderRecords(OrderType.ALL);
		logger.debug("ValidateCancelInValidOrder AFTER Cancel:{}", ordList);
		assertEquals(2, ordList.size()); 
	  }
	  
	  /* 
	 * Exercise 9 - Validate Invalid OrderType
	 * user2 order has not added due to invalid order type
	 */
	  @Test public void ValidateInValidOrderType() { 
		Order ord1 = new Order().setOrderId("a").setOrderQuantity(new BigDecimal("3.5")).setOrderType("SELL").setPrice(306).setUserId("user1");
		Order ord2 = new Order().setOrderId("b").setOrderQuantity(new BigDecimal("1.2")).setOrderType("HHH").setPrice(310).setUserId("user2");
			  
		OrderDataStore orderStore = OrderDataStore.getInstance();
		orderStore.deleteAll();
		orderStore.add(ord1);
		orderStore.add(ord2);
	
		List<Order> ordList = orderStore.getOrderRecords(OrderType.ALL);
		//just for reference purpose
		logger.debug("ValidateInValidOrderType:{}", ordList);
		 
		assertEquals(1, ordList.size()); 
		 

	  }
}
