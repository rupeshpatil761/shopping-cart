package com.shopping.util;

import java.util.Date;

public class GenericResponse {
	
	  private Date timestamp;
	  private String message;
	  private String details;
	  //default value is false
	  private boolean isSuccess;

	  public GenericResponse(Date timestamp, String message, String details) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	  }
	  
	  public GenericResponse(Date timestamp, String message, String details,boolean isSuccess) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	    this.isSuccess = isSuccess;
	  }

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "GenericResponse [timestamp=" + timestamp + ", message=" + message + ", details=" + details
				+ ", isSuccess=" + isSuccess + "]";
	}
}