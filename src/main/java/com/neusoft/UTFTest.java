package com.neusoft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Jim on 2016/5/24.
 * This class is ...
 */
public class UTFTest {
	final static Logger logger = LoggerFactory.getLogger(UTFTest.class);
	public static void main(String[] args) {
		logger.info(UTFConfig.getParameter("server.host"));
		logger.info(UTFConfig.getParameter("server.port"));
	}
}
