package model.uri;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import model.Resource;

import org.apache.http.NameValuePair;
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
}
