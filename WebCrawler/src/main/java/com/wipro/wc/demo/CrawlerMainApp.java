/**
 * 
 */
package com.wipro.wc.demo;

import java.io.IOException;

import org.apache.log4j.Logger;


/**
 * @author 
 *
 */
public class CrawlerMainApp {

	/**
	 * @param args
	 */
	
	final static Logger logger = Logger.getLogger(CrawlerMainApp.class);
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		logger.info(" Application Started from Main Method");
		WebCrawlerWrapper wrapper=new WebCrawlerWrapper();
		wrapper.invokeWebSite();
		
		logger.info("Main Method Ends");
			
		  
	}
	}

