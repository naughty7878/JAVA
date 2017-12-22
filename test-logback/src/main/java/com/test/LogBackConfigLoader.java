package com.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * 
 * @author H__D
 *
 */
public class LogBackConfigLoader {

	public static void main(String[] args) throws IOException, JoranException {
		load("D:/logback.xml");

		Logger logger = LoggerFactory.getLogger(LogBackConfigLoader.class);

		logger.debug("现在的时间是 {}", new Date().toString());

		logger.info(" This time is {}", new Date().toString());

		logger.warn(" This time is {}", new Date().toString());

		logger.error(" This time is {}", new Date().toString());

		@SuppressWarnings("unused")
		int n = 1 / 0;
	}

	/**
	 * 加载外部的logback配置文件
	 * 
	 * @param externalConfigFileLocation
	 *            配置文件路径
	 * @throws IOException
	 * @throws JoranException
	 */
	public static void load(String externalConfigFileLocation) throws IOException, JoranException {

		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

		File externalConfigFile = new File(externalConfigFileLocation);

		if (!externalConfigFile.exists()) {

			throw new IOException("Logback External Config File Parameter does not reference a file that exists");

		} else {

			if (!externalConfigFile.isFile()) {
				throw new IOException("Logback External Config File Parameter exists, but does not reference a file");

			} else {

				if (!externalConfigFile.canRead()) {
					throw new IOException("Logback External Config File exists and is a file, but cannot be read.");

				} else {

					JoranConfigurator configurator = new JoranConfigurator();
					configurator.setContext(lc);
					lc.reset();
					configurator.doConfigure(externalConfigFileLocation);

					StatusPrinter.printInCaseOfErrorsOrWarnings(lc);

				}

			}

		}

	}
}
