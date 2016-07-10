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
	
	//定义
	private SharedPreferences sharedPreferences;
	private String is_first;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.appstart);
		/*用appstart.xml作为布局文件*/
		
	new Handler().postDelayed(new Runnable(){
		@Override
		public void run(){
			
			/*这里用到数据库，每次调试都卸载在源文件.apk，因为数据会写入数据库*/
			//第一个参数是存储时的名字，第二个参数是文件的打开方式
			sharedPreferences = getSharedPreferences("is_first_login", Context.MODE_PRIVATE);
			is_first = sharedPreferences.getString("is_first", "true");
			//在数据库中检索is_first，没有就默认返回true，这里is_first=true
			
			//判断（首次为true，所以执行引导页，然后设置为false，第二次执行跳过引导页）
			if (is_first.equals("true")) {
				Intent intent = new Intent (AppStart.this, Whatsnew.class);
				startActivity(intent);	
				AppStart.this.finish();//当前页面已经结束
			}else {
				Intent intent = new Intent (AppStart.this, StartLogoActivity.class);
				startActivity(intent);	
				AppStart.this.finish();//当前页面已经结束
			}
		}
	}, 2000);
	/*还可以延时2秒*/
   }
}









