package com.silver.bars.order;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.silver.bars.constant.AppConstant;
import com.silver.bars.response.BaseResponseBean;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public final BaseResponseBean handleCustomException(CustomException exp, WebRequest request) {
		
	BaseResponseBean responseBean = new BaseResponseBean();
		
	responseBean.setMessage(AppConstant.CUSTOM_ERROR_MESSAGE);
	logger.debug("Application Encountered CUSTOM Error---------------------");
	exp.printStackTrace();
	responseBean.setStatus(AppConstant.STATUS_FAILED);
	
	return responseBean;
  }
  
  @ExceptionHandler(Exception.class)
  public final BaseResponseBean handleApplicationException(Exception exp, WebRequest request) {
		
	BaseResponseBean responseBean = new BaseResponseBean();
		
	responseBean.setMessage(AppConstant.SYSTEM_ERROR_MESSAGE);
	logger.debug("Application Encountered SYSTEM Error---------------------");
	exp.printStackTrace();
	responseBean.setStatus(AppConstant.STATUS_FAILED);
	
	return responseBean;
  }
  

}
