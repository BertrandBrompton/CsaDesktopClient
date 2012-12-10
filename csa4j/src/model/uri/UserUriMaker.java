package model.uri;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class UserUriMaker {
	protected List<NameValuePair> make(LinkedList<NameValuePair> linkedlist, Hashtable<String, String> ht){
		Enumeration<String> e = ht.keys();
		while(e.hasMoreElements()){		
			String key = e.nextElement();
			if(key == "id"){
				linkedlist.add(new BasicNameValuePair("id", ht.get(key)));
			} else {
				String s = "user["+key+"]";
				linkedlist.add(new BasicNameValuePair(s, ht.get(key)));
			}			
		}
		// "user[user_detail_attributes][login]",this.getFirstname()+this.getSurname())
		// "user[user_detail_attributes][password]",this.getFirstname()+this.getSurname())
		linkedlist.add(new BasicNameValuePair("user[user_detail_attributes][login]",ht.get("email")+ht.get("surname")));
		linkedlist.add(new BasicNameValuePair("user[user_detail_attributes][password]",ht.get("firstname")+ht.get("surname")));
		return linkedlist;
	}
}
