package com.llanox.blocker.sms;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private ListView mListSMS;
	
	// Create an anonymous implementation of OnClickListener.
    private OnItemClickListener mItemSMSListener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
		
			Log.d("MainActivity", "view -->"+view.getClass());
			
			String  itemData = ((TextView) view).getText().toString();
			int msgIndex = itemData.indexOf("Message:");
			int numberIndex = itemData.indexOf("Number:")+"Number:".length();
			
			String number = itemData.substring(numberIndex, msgIndex).trim();
			int deletedSMS = SMSContentProvider.deleteAllSMSbyNumber(number, view.getContext());
		
			Toast.makeText(getApplicationContext(), "Item pressed. Number --> "+number+" Deleted sms "+deletedSMS, Toast.LENGTH_LONG).show();
			
		    
	        updateSMSList(mListSMS);
			
		}

      
        
    };
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        mListSMS = (ListView) findViewById(R.id.msgs_list);
        updateSMSList(mListSMS);             
        mListSMS.setOnItemClickListener(mItemSMSListener);                 
        
    }

	private void updateSMSList(ListView list) {
		List<String> msgList = SMSContentProvider.getSMS(getApplicationContext());       
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, msgList); 
        list.setAdapter(adapter);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public void onClick(View view) {

		
	}
    
    
    
    
 
    
    
    
    
    
    
}
