package com.silver.bars.order;

public class CustomException extends Exception 
{ 
	String errorCode;
    public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public CustomException(String s) 
    { 
        // Call constructor of parent Exception 
        super(s); 
    } 
	
	public CustomException(String code, Exception e) 
    { 
        // Call constructor of parent Exception 
        super(e);
        this.errorCode = code;
    } 
} 
