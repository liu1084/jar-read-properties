package com.neusoft;

import com.neusoft.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLDecoder;
import java.util.Properties;

public class UTFConfig {
	static final Logger logger = LoggerFactory.getLogger(UTFConfig.class);
	public static Properties prop = null;
	public static String config = "utf.properties";

	protected static void init() {
		try {
			prop = PropertiesUtil.getProperties(getRootClassPath() + config);
		} catch (FileNotFoundException e) {
			logger.error("File: " + config + " is not exist!, detail:");
			logger.error(e.getMessage(), e);
		}
	}

	public static String getParameter(String name) {
		if (prop == null) {
			init();
		}
		if (prop == null) {
			return null;
		} else {
			return prop.getProperty(name);
		}
	}

	protected static Object getInstance(String className) {
		Object obj = null;
		try {
			obj = Class.forName(className).newInstance();
		} catch (Exception e) {
			logger.error("[" + className + "]构造失败！");
			logger.error(e.getMessage(), e);
		}
		return obj;
	}

	/**
	 * 获取存储用户数据的根文件夹
	 * 如果不配置，则会默认使用ClassPath
	 *
	 * @return
	 */
	public static String getRelativeRootPath() {
		String path = getParameter("relativeRootPath");
		if (path != null && !"".equals(path.trim())) {
			path = path + File.separator;
		}
		return path;
	}

	private static String getRootClassPath() {
		String path = null;
		try {
			path = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
			logger.info(path);
			path = URLDecoder.decode(path, "UTF-8");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return path;
	}
}