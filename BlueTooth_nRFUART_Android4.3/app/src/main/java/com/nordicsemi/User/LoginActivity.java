package com.nordicsemi.User;

import com.nordicsemi.Myfiles.FileService;
import com.nordicsemi.nrfUARTv2.BleActivity;
import com.nordicsemi.nrfUARTv2.R;


import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
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
		loginButton.setOnClickListener(new ButtonClickListener());//button被点击后就会调用ButtonClickListener()这个对象处理btn点击行为
		
	}
	//忘记密码点击事件
	private class TextViewClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		}
	}
	
	//这里测试按登录按钮保存数据到手机（文件方式保存）
	//提供一个内部类ButtonClickListener来实现OnClickListener这个借口
	private class ButtonClickListener implements View.OnClickListener{
//	private final class ButtonClickListener implements View.OnClickListener{//如果内部类不需要继承，要加final关键字
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//得到两个文本输入框的id
			EditText accountEditText = (EditText) findViewById(R.id.account_editext);
			EditText passwdeEditText = (EditText) findViewById(R.id.passwd_edit);
			//得到两个文本输入框的内容
			String accountString = accountEditText.getText().toString();
			String passwdString = passwdeEditText.getText().toString();
			
			FileService service = new FileService(getApplicationContext());
			//被抛出的例外在这里实现（try{}catch捕获例外异常）
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
