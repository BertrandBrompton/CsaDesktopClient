package model.uri;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class BroadcastUriMaker {
	protected List<NameValuePair> make(LinkedList<NameValuePair> linkedlist, Hashtable<String, String> ht){
		/*
		 * broadcast[content] bleble
			feeds[alumni_email] cs-alumni 
			feeds[email] 1
			feeds[twitter] 1
			page 1
			commit Broadcast
		 */
		linkedlist.add(new BasicNameValuePair("broadcast[content]",ht.get("content")));
		linkedlist.add(new BasicNameValuePair("feeds[alumni_email]",ht.get("alumni_email")));
		linkedlist.add(new BasicNameValuePair("feeds[email]",ht.get("email")));
		linkedlist.add(new BasicNameValuePair("feeds[twitter]",ht.get("twitter")));
		linkedlist.add(new BasicNameValuePair("page",ht.get("page")));
		linkedlist.add(new BasicNameValuePair("commit",ht.get("Broadcast")));
		//TODO: Dont like this implementation since it is hard coded.
		return linkedlist;
	}
}
