package com.wipro.wc.demo;

import java.io.IOException;

import org.apache.log4j.Logger;
import com.wipro.wc.demo.exception.WebCrawlerException;
import com.wipro.wc.demo.utility.Utility;

public class WebCrawlerWrapper {

	final static Logger logger = Logger.getLogger(WebCrawlerServic.class);
	WebCrawlerServic service = null;

	Utility util = null;

	public void invokeWebSite() {
		// TODO Auto-generated method stub
		logger.info(" invokeWebSite() starts");
		try {

			util = new Utility();
			String URL = util.getURLFromPropFile();
			String domainURL = util.DomainValidation(URL);
			service = new WebCrawlerServic();
			service.runWebCrawler(domainURL);
		} catch (IOException IE) {
			logger.error("Error Occured");
			throw new WebCrawlerException("Error Occured due to :" + IE.getMessage());
		}
		logger.info(" invokeWebSite() Ends");
	}

}
