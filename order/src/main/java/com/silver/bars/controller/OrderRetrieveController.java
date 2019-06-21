package com.silver.bars.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.silver.bars.aspect.TrackTime;
import com.silver.bars.constant.AppConstant;
import com.silver.bars.constant.OrderType;
import com.silver.bars.order.CustomException;
import com.silver.bars.order.OrderDataStore;
import com.silver.bars.response.BaseResponseBean;
import com.silver.bars.response.OrderResponseBean;

import io.swagger.annotations.ApiOperation;

@RestController
public class OrderRetrieveController {
		
	/**
	 * This method is used to Retrieve All Order
	 * @param request
	 * @return BaseResponseBean
	 */
	@ApiOperation(value = "Retrieve All Order",response = String.class)
	@RequestMapping(method = RequestMethod.GET, value="/order/allOrders", produces="application/json")
	@TrackTime
    public BaseResponseBean getAllOrders() throws CustomException {
		OrderResponseBean ordResponse = new OrderResponseBean();        
		ordResponse.setOrderList(OrderDataStore.getInstance().getOrderRecords(OrderType.ALL));
		//ordResponse.setOrderListText(OrderDataStore.getInstance().getOrderRecords(OrderType.ALL).stream().map(o -> o.toString()).collect(Collectors.toList()));
		ordResponse.setStatus(AppConstant.STATUS_SUCCESS);
        return ordResponse;
	}
	
	/**
	 * This method is used to Retrieve BUY Order
	 * @param request
	 * @return BaseResponseBean
	 */
	@ApiOperation(value = "Retrieve BUY Order",response = String.class)
	@RequestMapping(method = RequestMethod.GET, value="/order/buyOrders", produces="application/json")
	@TrackTime
    public BaseResponseBean buyOrders() throws CustomException {
		OrderResponseBean ordResponse = new OrderResponseBean();        
		ordResponse.setOrderList(OrderDataStore.getInstance().getOrderRecords(OrderType.BUY));
		ordResponse.setStatus(AppConstant.STATUS_SUCCESS);
        return ordResponse;
	}
	
	/**
	 * This method is used to Retrieve SELL Order
	 * @param request
	 * @return BaseResponseBean
	 */
	@ApiOperation(value = "Retrieve SELL Order",response = String.class)
	@RequestMapping(method = RequestMethod.GET, value="/order/sellOrders", produces="application/json")
	@TrackTime
    public BaseResponseBean sellOrders() throws CustomException {
		OrderResponseBean ordResponse = new OrderResponseBean();        
		ordResponse.setOrderList(OrderDataStore.getInstance().getOrderRecords(OrderType.SELL));
		ordResponse.setStatus(AppConstant.STATUS_SUCCESS);
		ordResponse.setMessage(AppConstant.ORDER_RETRIEVE_MESSAGE);
        return ordResponse;
	}


}
