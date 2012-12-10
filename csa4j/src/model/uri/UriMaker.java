package model.uri;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import model.Config;
import model.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

public class UriMaker {
	
/**
 * My own custom built URI Maker for the CSA App. 
 */
	public List<NameValuePair> make(Hashtable<String, String> ht, Resource type){
		LinkedList<NameValuePair> linkedlist = new LinkedList<NameValuePair>();
			
		if(type == Resource.BROADCAST){
			return new BroadcastUriMaker().make(linkedlist, ht);
		}else if(type == Resource.USER){
			System.out.println("Proceeding to build User Uri...");
			return new UserUriMaker().make(linkedlist, ht);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public URI simple_make(String path, LinkedList<NameValuePair> ll){
		URIBuilder builder = new URIBuilder();
		//builder.setScheme("http").setHost("www.google.com").setPath("/search")
		builder.setScheme(Config.getScheme()).setHost(Config.getHost()).setPort(Config.getPort()).setPath(path);
		for(NameValuePair a : ll){
			builder.setParameter(a.getName(), a.getValue());
		}
		URI uri = null;
		try {
			uri = builder.build();
		//	System.out.println(uri.toString());
		} catch (URISyntaxException e) {
			
			System.out.println("Messageu: " + e);
		}
		//HttpGet httpget = new HttpGet(uri);
		//System.out.println(httpget.getURI());
		return uri;
	}
}
