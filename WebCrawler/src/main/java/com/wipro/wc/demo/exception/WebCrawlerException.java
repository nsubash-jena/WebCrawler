/**
 * 
 */
package com.wipro.wc.demo.exception;

/**
 * @author Subash
 *
 */
public class WebCrawlerException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String 	exception;
	

	public WebCrawlerException(String msg) {
		super(msg);
	}

	public WebCrawlerException(String exception, String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
		this.exception = exception;
	}

	

}
