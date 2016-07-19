package com.nordicsemi.User;

import com.nordicsemi.Myfiles.FileService;
import com.nordicsemi.nrfUARTv2.BleActivity;
import com.nordicsemi.nrfUARTv2.R;


import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		TextView secretforget = (TextView) this.findViewById(R.id.forget_secret);
		secretforget.setOnClickListener(new TextViewClickListener());
		
		Button loginButton = (Button) this.findViewById(R.id.btn_login);
		loginButton.setOnClickListener(new ButtonClickListener());//button茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆�禄茂驴陆茂驴陆茂驴陆茂驴陆ButtonClickListener()茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆btn茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆�陋
		
	}
	//茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆�录茂驴陆
	private class TextViewClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		}
	}
	
	//茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆�掳茂驴陆茂驴陆茂驴陆�录茂驴陆茂驴陆�楼茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆�碌茂驴陆茂驴陆�禄茂驴陆茂驴陆茂驴陆茂驴陆�录茂驴陆茂驴陆茂驴陆�陆茂驴陆茂驴陆茂驴陆忙拢漏
	//茂驴陆谩鹿漏�禄茂驴陆茂驴陆茂驴陆�虏茂驴陆茂驴陆茂驴陆ButtonClickListener茂驴陆茂驴陆�碌茂驴陆茂驴陆OnClickListener茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆
	private class ButtonClickListener implements View.OnClickListener{
//	private final class ButtonClickListener implements View.OnClickListener{//茂驴陆茂驴陆茂驴陆茂驴陆�虏茂驴陆茂驴陆�虏禄茂驴陆茂驴陆�陋茂驴陆�鲁�拢茂驴陆�陋茂驴陆茂驴陆final茂驴陆�录茂驴陆茂驴陆茂驴陆
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//茂驴陆�碌茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆�卤茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆id
			EditText accountEditText = (EditText) findViewById(R.id.account_editext);
			EditText passwdeEditText = (EditText) findViewById(R.id.passwd_edit);
			//茂驴陆�碌茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆�卤茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆
			String accountString = accountEditText.getText().toString();
			String passwdString = passwdeEditText.getText().toString();
			
			FileService service = new FileService(getApplicationContext());
			//茂驴陆茂驴陆茂驴陆�鲁茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆�碌茂驴陆�拢茂驴陆try{}catch茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆茂驴陆矛鲁拢茂驴陆茂驴陆
			try {
				service.save(accountString, passwdString);
				Toast.makeText(getApplicationContext(), "saved succeed!!!!", 1).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Toast.makeText(getApplicationContext(), "saved faile!!!!", 1).show();
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	

}
