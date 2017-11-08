package com.seven.demo.util;

import java.io.Serializable;

public class ResultResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1610765900299614696L;
	
	private Boolean result = true;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	

}
