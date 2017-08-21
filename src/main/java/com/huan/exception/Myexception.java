package com.huan.exception;

public class Myexception extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String msString;
	public Myexception(String msg) {
		// TODO Auto-generated constructor stub
		this.msString=msg;
	}
	public void printMsg(){
		System.err.println(msString);
	}
}
