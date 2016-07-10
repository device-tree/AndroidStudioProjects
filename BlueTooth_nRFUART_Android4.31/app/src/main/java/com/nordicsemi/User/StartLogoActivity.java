package com.nordicsemi.User;

import java.util.Timer;
import java.util.TimerTask;

import com.nordicsemi.nrfUARTv2.BleActivity;
import com.nordicsemi.nrfUARTv2.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class StartLogoActivity extends Activity implements OnClickListener {
	
	/*����ʱ����*/
	private Button btnJoinSporta,btnLongin,blebtn;
	private Handler handler;
	
	//Activity����ʱ�����ã�@Override��ʾ��д����������	��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.start_logo_activity);
		/*ʹ��start_logo_activity.xml�ļ�����Ľ��沼��*/
		
		/*�������壬��ʼ���ؼ����൱�ڷ�װһ�������*/
		initview();
		
//		/*�����õ��ö�ʱ��*/
//		Timer time = new Timer();
//		/*�µ�����*/
//		TimerTask timerTask = new TimerTask() {
//			/*���������ʲôrun��@Override������д����������	��*/
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				Message message = Message.obtain(handler, 1);/*���ͱ�ʶ��*/
//				handler.sendMessage(message);/*//����message
//*/			}
//		};
//		time.schedule(timerTask, 1);//1��
//		/*����ķ���*/
//		handler = new Handler(){
//			@Override
//			public void handleMessage(Message msg) {
//				//TODO Auto-generated method stub
//				super.handleMessage(msg);
//				switch (msg.what) {
//				case 1:
//					btnJoinSporta.setVisibility(View.VISIBLE);/*��ʱ�����ʾ������ť*/
//					btnLongin.setVisibility(View.VISIBLE);
//					break;
//
//				default:
//					break;
//				}
//			}
//		};
		
		try {
			Thread.sleep(1000);
			btnJoinSporta.setVisibility(View.VISIBLE);/*��ʱ�����ʾ������ť*/
			btnLongin.setVisibility(View.VISIBLE);	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private void initview() {
		// TODO Auto-generated method stub
		/*�ҵ�ID*/
		btnJoinSporta = (Button) findViewById(R.id.start_btn_join_sporta);
		btnLongin = (Button) findViewById(R.id.start_btn_login);
		blebtn = (Button) findViewById(R.id.btn_to_ble);
		/*���ü����������м������ԣ�*/
		btnJoinSporta.setOnClickListener(this);
		btnLongin.setOnClickListener(this);
		blebtn.setOnClickListener(this);
	}
	
	/*�����Ѿ����ü������ԣ��е���¼��ͻ��������Ĵ�����*/
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start_btn_join_sporta:
			startActivity(new Intent(this, StartInfoActivity.class));//�����ť��������һ��Activity
			break;
			
		case R.id.start_btn_login:
			startActivity(new Intent(this, LoginActivity.class));
			break;	
			
		case R.id.btn_to_ble:
			startActivity(new Intent(this, BleActivity.class));
//			Toast toast = Toast.makeText(this, "���������ҳ��", Toast.LENGTH_SHORT);
//			toast.show();
			break;				

		default:
			break;
		}
	/*�����startActivity();Ҳ����������һ��д*/	
//		Intent intent = new Intent(this, StartInfo.class);
//		startActivity(intent);
	}
	
	
	@Override
	public void onBackPressed() {
	        new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle(R.string.popup_title)
	        .setMessage(R.string.popup_message)
	        .setPositiveButton(R.string.popup_yes, new DialogInterface.OnClickListener()
	            {
	                @Override
	                public void onClick(DialogInterface dialog, int which) {
		                finish();
	            }
	        })
	        .setNegativeButton(R.string.popup_no, null)
	        .show();
	    }
	}

