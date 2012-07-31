package com.llanox.blocker.sms;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class SMSContentProvider {
	
	
	
	
	   public static List<String> getSMS(Context ctx) {     
		   
	        List<String> list = new ArrayList<String>();
	        Uri uri = Uri.parse("content://sms/inbox");
	        Cursor c = null;
	        try{
	            c = ctx.getContentResolver().query(uri, null, null ,null,null); 
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        try{
	            for (boolean hasData = c.moveToFirst(); hasData; hasData = c.moveToNext()) {
	                final String address = c.getString(c.getColumnIndex("address"));
	                final String body = c.getString(c.getColumnIndexOrThrow("body"));
	                
	                list.add("Number:"+address+"\n"+"Message: " + body);
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	     c.close(); 
	     return list;
	    }

	public static void deleteAllSMSbyNumber(String number, Context ctx) {
	
		
	}

}
