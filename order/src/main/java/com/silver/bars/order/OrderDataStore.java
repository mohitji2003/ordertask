package com.silver.bars.order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.silver.bars.aspect.TrackTime;
import com.silver.bars.constant.AppConstant;
import com.silver.bars.constant.OrderType;
import com.silver.bars.model.Order;


public class OrderDataStore {
	
	private Map<String,Order> orderRecords;
    private static OrderDataStore ordregd = null;
    
    private OrderDataStore(){
    	orderRecords = new ConcurrentHashMap<String, Order>();
    }
    
    /*
     * Creating a singleton object of OrderDatStore
     */
    public static OrderDataStore getInstance() {
        if(ordregd == null) {
        	ordregd = new OrderDataStore();
              return ordregd;
        }else {
            return ordregd;
        }
    }
    
    /*
     * Adding a record into the orderRecords List
     */
    @TrackTime
    public void add(Order std) throws CustomException {
    	
    	if(orderRecords.containsKey(std.getOrderId())) {
    		throw new CustomException(AppConstant.ORDER_REGISTER_FAILED_DUPLICATE);
    	}else if(!OrderType.BUY.toString().equalsIgnoreCase(std.getOrderType()) && !OrderType.SELL.toString().equalsIgnoreCase(std.getOrderType())){
    		throw new CustomException(AppConstant.ORDER_REGISTER_FAILED);
    	}
   		orderRecords.put(std.getOrderId(), std);
    }

    /*
     * Flushing the orderRecords list
     */
    public void deleteAll(){
    	if(orderRecords!=null) {
    		orderRecords.clear();
    	}
    }
	
    /*
     * Deleting the order from the List
     */
    public String deleteOrder(String orderId) throws CustomException {
    	//boolean recordFound = false;
		/*
		 * int count = orderRecords.size(); for(int i=0; i<count; i++) { Order ord =
		 * orderRecords.get(i); if(ord.getOrderId().equals(orderId)){
		 * orderRecords.remove(i);//update the new record recordFound = true; break; } }
		 */
    	
		if(orderRecords.containsKey(orderId)) {
			orderRecords.remove(orderId);
			return AppConstant.ORDER_CANCELLED;
		}else {
			throw new CustomException(AppConstant.ORDER_CANCELLED_FAILED);
		}
	}
	
    /*
     * Getting all the order records
     */
    @TrackTime
    public List<Order> getOrderRecords(OrderType orderType) {
    	
    	List<Order> ordList = new ArrayList<Order>();
    	if(OrderType.ALL == orderType) {
    		ordList = getFilteredRecords(OrderType.BUY);
    		ordList.addAll(getFilteredRecords(OrderType.SELL));
    	}else if(OrderType.BUY == orderType) {
    		ordList = getFilteredRecords(OrderType.BUY);
    	}else if(OrderType.SELL == orderType) {
    		ordList = getFilteredRecords(OrderType.SELL);
    	}
	    return ordList;
	}
    
   /*
    * Returning all BUY records
    */
   @TrackTime
   private  List<Order> getFilteredRecords(OrderType orderType) {
    	
	   //Filtering all BUY records
	   List<Order> orderList = orderRecords.values().stream().filter(ord -> ord.getOrderType().equalsIgnoreCase(orderType.toString())).collect(Collectors.toList());
    	
	   //addQuantityOfSamePrice will add the same price Orders
	   orderList = addQuantityOfSamePrice(orderList);
    	   	
	   //applying sorting
	   if(OrderType.BUY == orderType)
		   orderList.sort((Comparator.comparing(Order::getPrice)).reversed());
	   else
		   orderList.sort((Comparator.comparing(Order::getPrice)));
    	
	   return orderList;
	}
   
   /*
    * Logic to add the same price quantities
    * Also creating orderId text
    */
   private List<Order> addQuantityOfSamePrice(List<Order> ordList) {
	   	Map<Integer, Order> priceMap = new HashMap<Integer, Order>();
	   
		//adding same price record quantity 
	   	for(Order ord : ordList) {
	   		if(priceMap.containsKey(ord.getPrice())) {
	   			Order oldOrd = priceMap.get(ord.getPrice());
	   			oldOrd.setOrderQuantity(oldOrd.getOrderQuantity().add(ord.getOrderQuantity()));
	   			oldOrd.setOrderIdText("order"+oldOrd.getOrderId()+ " + order"+ord.getOrderId());
	   		}else {
	   			ord.setOrderIdText("order "+ord.getOrderId());
	   			priceMap.put(ord.getPrice(),ord);
	   		}
	   	}
	   	return priceMap.values().stream().collect(Collectors.toList());
   }
}
