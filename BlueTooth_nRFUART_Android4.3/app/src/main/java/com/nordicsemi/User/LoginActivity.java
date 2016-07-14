package com.nordicsemi.User;

import com.nordicsemi.Myfiles.FileService;
import com.nordicsemi.nrfUARTv2.BleActivity;
import com.nordicsemi.nrfUARTv2.R;


import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
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
		loginButton.setOnClickListener(new ButtonClickListener());//button�������ͻ����ButtonClickListener()���������btn�����Ϊ
		
	}
	//�����������¼�
	private class TextViewClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		}
	}
	
	//������԰���¼��ť�������ݵ��ֻ����ļ���ʽ���棩
	//�ṩһ���ڲ���ButtonClickListener��ʵ��OnClickListener������
	private class ButtonClickListener implements View.OnClickListener{
//	private final class ButtonClickListener implements View.OnClickListener{//����ڲ��಻��Ҫ�̳У�Ҫ��final�ؼ���
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//�õ������ı�������id
			EditText accountEditText = (EditText) findViewById(R.id.account_editext);
			EditText passwdeEditText = (EditText) findViewById(R.id.passwd_edit);
			//�õ������ı�����������
			String accountString = accountEditText.getText().toString();
			String passwdString = passwdeEditText.getText().toString();
			
			FileService service = new FileService(getApplicationContext());
			//���׳�������������ʵ�֣�try{}catch���������쳣��
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
