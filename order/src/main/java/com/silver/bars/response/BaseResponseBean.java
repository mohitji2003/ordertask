package com.silver.bars.response;

import org.springframework.stereotype.Component;

/**
 * This is the base response bean that has common fields required in all Response Bean classes
 * @author Mohit Goel
 *
 */
@Component
public class BaseResponseBean {

	//generic response message 
	private String message;
	//status of the response FAILED/SUCCESS
	private String status;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "BaseResponseBean [message=" + message + ", status=" + status + "]";
	}
}
