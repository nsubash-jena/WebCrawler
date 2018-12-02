/**
 * 
 */
package com.wipro.wc.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wipro.wc.demo.exception.WebCrawlerException;
import com.wipro.wc.demo.utility.Utility;
import com.wipro.wc.demo.utility.WebConnector;

/**
 * @author
 *
 */
public class WebCrawlerServic {

	final static Logger logger = Logger.getLogger(WebCrawlerServic.class);

	ICrawler connector = null;
	Document document = null;
	Set<String> linkSites = null;
	Connection connection = null;
	public void runWebCrawler(String URL) {
		logger.info(" runWebCrawler() starts");
		try {

			connector = new WebConnector();
			Connection connection = connector.getConnection(URL);
			document = connector.getDocument(connection);
			if (document == null) {
				logger.error("There is no data found for this web page");
				throw new WebCrawlerException("Please check  the Domain URL or the web site");
			}
			Set<String> contentImage =getImages(document);
			Set<String> contentLinks = getLinks(document);
			Map siteMap=new HashMap();
			siteMap.put("IMAGES", contentImage);
			siteMap.put("LINKS", contentLinks);
			Utility.URLFileWriter(siteMap);
			
			//System.out.println(setImage);
			//System.out.println(setLinks);
			logger.info(" runWebCrawler() Ends");

		} catch (Exception e) {
			logger.error("issue occured while accessing the web data");
			throw new WebCrawlerException("issue occured ........:" + e.toString());
		} finally {
			Utility.terminateConn(connection);
		}

	}

	public synchronized Set<String> getImages(Document document) {
		logger.info(" getImages() starts");
		Elements media = document.select(ICrawler.SRC_TAG);

		Set<String> imageSites = new HashSet<String>();
		for (Element src : media) {
			if (src.tagName().equals("img")) {
				if (logger.isDebugEnabled()) {
					logger.debug(" Tag name:" + src.attr(ICrawler.ABS_IMG_TAG));
				}
				String ImageTag = src.attr(ICrawler.ABS_IMG_TAG);
				String ImageURL = Utility.sourceValidation(ImageTag);
				imageSites.add(ImageURL);

			}

		}
		
		logger.info("Image link lists are :" + imageSites.toString());
		logger.info(" getImages() Ends");
		return imageSites;

	}

	public synchronized Set<String> getLinks(Document document) {
		logger.info(" getLinks() starts");
		Elements links = document.select(ICrawler.HREF_TAG);

		Set<String> linkSet = new HashSet<String>();
		for (Element link : links) {
			if (logger.isDebugEnabled()) {
				logger.debug(" Tag name:" + link.attr(ICrawler.ABS_LINK_TAG));
			}
			String LinkTag = link.attr(ICrawler.ABS_LINK_TAG);
			String linkURL = Utility.sourceValidation(LinkTag);
			if (!linkURL.isEmpty() && linkURL != null) {
				linkSet.add(linkURL);
			}

		}
		logger.info(" links lists are :" + linkSet.toString());
		logger.info(" getLinks() Ends");
		return linkSet;

	}
}
