package com.huan.course.util;


import org.apache.log4j.Logger;

public class LoggerUtil {

	private Logger logger=null;
	public LoggerUtil(Class t) {
		logger=Logger.getLogger(t);
	}
	public void info(String msg){
		logger.info(msg);
	}
}
