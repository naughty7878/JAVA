package com.test;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 日志过滤器
 * @author H__D
 * @date 2019-07-12 01:22:26
 *
 */
public class SampleFilter extends Filter<ILoggingEvent> {

	@Override
	public FilterReply decide(ILoggingEvent event) {
		
		// 判断日志名是否包含monitor
		if (event.getLoggerName().contains("monitor")) {
			return FilterReply.ACCEPT;
		} 
		return FilterReply.DENY;

	}
}
