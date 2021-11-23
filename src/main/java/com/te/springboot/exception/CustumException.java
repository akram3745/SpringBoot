package com.te.springboot.exception;
@SuppressWarnings("serial")
public class CustumException extends RuntimeException {

	public CustumException(String msg) {
		super(msg);
	}

}
