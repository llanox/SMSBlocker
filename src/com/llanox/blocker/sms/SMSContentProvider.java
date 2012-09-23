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

	   
	   
	public static int deleteAllSMSbyNumber(String number, Context ctx) {
	
		    Uri deleteUri = Uri.parse("content://sms/");
		    String thread_id= findThreadIdByAddress(number,ctx);
		    
		    return ctx.getContentResolver().delete(deleteUri, "thread_id=?", new String[] {thread_id});
		    
	}



	public static String findThreadIdByAddress(String number, Context ctx) {
		
		Uri uri = Uri.parse("content://sms/inbox"); 
		String thread_id ="";
		
		Cursor c = null;
	        try{
	            c = ctx.getContentResolver().query(uri, new String[] {"thread_id"}, "address=?" ,new String[] {number},null); 
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	      
	        if(c != null && c.moveToFirst()){
	        	
	        	thread_id= c.getString(0);
	        }
	        
	        
	        
		return thread_id;
	}

}
