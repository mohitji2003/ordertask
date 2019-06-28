package com.silver.bars.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.silver.bars.aspect.TrackTime;
import com.silver.bars.constant.AppConstant;
import com.silver.bars.model.Order;
import com.silver.bars.order.CustomException;
import com.silver.bars.order.OrderDataStore;
import com.silver.bars.response.BaseResponseBean;
import com.silver.bars.response.OrderRegistrationReply;

import io.swagger.annotations.ApiOperation;

@RestController
public class OrderRegistrationController {
	
	/**
	 * This method is used to Add a Order
	 * @param request Order
	 * @return BaseResponseBean
	 * @throws CustomException 
	 */
	@ApiOperation(value = "Register a Order",response = BaseResponseBean.class)
	@RequestMapping(method = RequestMethod.POST, value="/register/order", produces="application/json")
	@TrackTime
    public BaseResponseBean registerOrder(@RequestBody Order order) throws CustomException{
		OrderRegistrationReply ordResponse = new OrderRegistrationReply();           
        OrderDataStore.getInstance().add(order);
        //We are setting the below value just to reply a message back to the caller
        ordResponse.setOrderId(order.getOrderId());
        ordResponse.setMessage(AppConstant.ORDER_ADD_MESSAGE);
        ordResponse.setStatus(AppConstant.STATUS_SUCCESS);
        return ordResponse;
	}
}
