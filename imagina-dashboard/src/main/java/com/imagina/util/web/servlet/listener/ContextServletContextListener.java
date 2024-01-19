package com.imagina.util.web.servlet.listener;

import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextServletContextListener implements ServletContextListener {

	private static Logger	logger	= LoggerFactory.getLogger(ContextServletContextListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {
		if (logger.isDebugEnabled()) {
			logger.debug("Context destroyed.");
		}
//		CacheManager.getInstance().shutdown();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		URL url;
		//CHECKSTYLE:OFF
		System.out.println("Loading Imagina Dashboard Context...");
		//CHECKSTYLE:ON
		try {
			ServletContext context = arg0.getServletContext();
			System.setProperty("rootPath", context.getRealPath("/"));
			
			url = Loader.getResource("config/imagina-dashboard-log4j.xml");
			DOMConfigurator.configure(url);
			
			if (logger.isDebugEnabled()) {
				logger.debug("Log4j loaded.");
			}
			
		} catch (Exception e) {
			//CHECKSTYLE:OFF
			e.printStackTrace();
			System.out.println("Error loading Imagina Dashboard Context");
			System.exit(0);
			//CHECKSTYLE:ON
		}
	}

}
