package com.silver.bars.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.silver.bars.aspect.TrackTime;
import com.silver.bars.constant.AppConstant;
import com.silver.bars.order.CustomException;
import com.silver.bars.order.OrderDataStore;
import com.silver.bars.response.BaseResponseBean;

import io.swagger.annotations.ApiOperation;

@RestController
public class OrderCancelController {
	/**
	 * This method is used to Cancel a Order
	 * @param request
	 * @return BaseResponseBean
	 * @throws CustomException 
	 */
	@ApiOperation(value = "Cancel a Order",response = String.class)
	@RequestMapping(method = RequestMethod.DELETE, value="/cancel/order/{orderId}", produces="application/json")
	@TrackTime
    public BaseResponseBean cancelOrder(@PathVariable("orderId") String orderId) throws CustomException {
		BaseResponseBean ordResponse = new BaseResponseBean();           
		ordResponse.setMessage(OrderDataStore.getInstance().deleteOrder(orderId));
		ordResponse.setStatus(AppConstant.STATUS_SUCCESS);
        return ordResponse;
	}

}
