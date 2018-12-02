/**
 * 
 */
package com.wipro.wc.demo.utility;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.wipro.wc.demo.ICrawler;
import com.wipro.wc.demo.exception.WebCrawlerException;


/**
 * @author 
 *
 */
public class WebConnector implements ICrawler {

	
	Document doc = null;
	
	final static Logger logger = Logger.getLogger(WebConnector.class);
	public void runCrawler() {
		// TODO Auto-generated method stub
		
	}

	public Connection getConnection(String domainURL) {
		// TODO Auto-generated method stub
		
		try
		{
		logger.info(" getConnection() starts");
		Connection conn=null;
		
        if(domainURL==null || domainURL.isEmpty())
		{
			logger.error("domainURL is null or empty");
			throw new WebCrawlerException("Please Provide the Domain URL");
		}
		// We are using  USER_AGENT it thinks that the request is using a normal web browser.
		conn  = Jsoup.connect(domainURL).userAgent(USER_AGENT);
		if(conn==null)
		{
			logger.error("Unable to Connection  to the provided URL");
			throw new WebCrawlerException("Please Check the Domain URL ");
		}
		
		// doc = conn.get();
		 // 200 status code indicates that everything is going good.
		 
		 if(conn.response().statusCode() == 200) 
		 {
		 logger.info("Visited  and  Received web page at " + domainURL);
		   }
		//doc = Jsoup.connect(url).get();
		 logger.info(" getConnection() End");
		return conn;
		}catch(Exception ConnEX){
			logger.error("Unable to Connection  to the provided URL");
			throw new WebCrawlerException("Please Check the Domain URL "+ConnEX.toString());
		}
		
	}

	public Document getDocument(Connection conn) {
		 logger.info(" getDocument() Start");
		 try
		 {
		 doc = conn.get();
		 if(doc==null)
     	{
     		logger.error("Unable to get any document from  provided URL");
     		throw new WebCrawlerException("Unable to get any document from  provided URL");
     	}
		 logger.info(" getDocument() Ends");
		 return doc;
	}catch(Exception docEx) {
		 logger.error("Unable to get any document from  provided URL");
  		throw new WebCrawlerException("Unable to get any document from  provided URL:" +docEx.toString());
	}
		 
		
		
	
}

}
