package com.kh.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
	slf4j
	 - simple Logging Facade for java
	 - slf4j 통해서 구체화된 loggin의존을 제어함.
*/
public class Slf4jTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(Slf4jTest.class);

	public static void main(String[] args) {
		// LOG.fatal("message-fatal");	// slf4j에서는 fatal레벨이 없다
		LOG.error("message-error");
		LOG.warn("message-warn");
		LOG.info("message-info");
		LOG.debug("message-debug");
		LOG.trace("message-trace");
	}

}
