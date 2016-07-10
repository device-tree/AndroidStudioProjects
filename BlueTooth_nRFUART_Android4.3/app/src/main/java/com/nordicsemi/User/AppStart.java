package com.nordicsemi.User;

import com.nordicsemi.nrfUARTv2.R;

import android.os.Bundle;
import android.os.Handler;
import android.provider.Contacts;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.WindowManager;

public class AppStart extends Activity{
	
	//����
	private SharedPreferences sharedPreferences;
	private String is_first;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.appstart);
		/*��appstart.xml��Ϊ�����ļ�*/
		
	new Handler().postDelayed(new Runnable(){
		@Override
		public void run(){
			
			/*�����õ����ݿ⣬ÿ�ε��Զ�ж����Դ�ļ�.apk����Ϊ���ݻ�д�����ݿ�*/
			//��һ�������Ǵ洢ʱ�����֣��ڶ����������ļ��Ĵ򿪷�ʽ
			sharedPreferences = getSharedPreferences("is_first_login", Context.MODE_PRIVATE);
			is_first = sharedPreferences.getString("is_first", "true");
			//�����ݿ��м���is_first��û�о�Ĭ�Ϸ���true������is_first=true
			
			//�жϣ��״�Ϊtrue������ִ������ҳ��Ȼ������Ϊfalse���ڶ���ִ����������ҳ��
			if (is_first.equals("true")) {
				Intent intent = new Intent (AppStart.this, Whatsnew.class);
				startActivity(intent);	
				AppStart.this.finish();//��ǰҳ���Ѿ�����
			}else {
				Intent intent = new Intent (AppStart.this, StartLogoActivity.class);
				startActivity(intent);	
				AppStart.this.finish();//��ǰҳ���Ѿ�����
			}
		}
	}, 2000);
	/*��������ʱ2��*/
   }
}









