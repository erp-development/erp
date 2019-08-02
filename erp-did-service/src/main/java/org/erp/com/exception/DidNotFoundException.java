package org.erp.com.exception;

public class DidNotFoundException extends Exception {

	public DidNotFoundException(String did) {
		super(did);
	}
}
