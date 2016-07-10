package com.nordicsemi.Myfiles;

import java.io.UnsupportedEncodingException;

import com.nordicsemi.nrfUARTv2.UartService;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneStatReceiver extends BroadcastReceiver {

        private static final String TAG = "PhoneStatReceiver";
//        private static MyPhoneStateListener phoneListener = new MyPhoneStateListener();
        private static boolean incomingFlag = false;
        private static String incoming_number = null;
        private UartService mService = null;
        byte[] txValue; 

        @Override
        public void onReceive(Context context, Intent intent) {
        	
        	

                if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){                        
                        incomingFlag = false;
                        String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);        
                        Log.i(TAG, "call OUT:::::::::::::::::::::"+phoneNumber);//��logcat����ʾ����+����           
						try {
							txValue = phoneNumber.getBytes("UTF-8");
//							mService.writeRXCharacteristic(txValue);//��ȡ�������ݵ������������쳣��Ҫ�����쳣
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Log.i(TAG, "I had send phoneNumber to Mabiao!!!!!!!!!!!!!!!!!!!!!");
						
                }else{                        
                        //���������
                        TelephonyManager tm = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);                        
                        switch (tm.getCallState()) {
                        case TelephonyManager.CALL_STATE_RINGING:
                                incomingFlag = true;//��ʶ��ǰ������
                                incoming_number = intent.getStringExtra("incoming_number");
                                Log.i(TAG, "RINGING ::::::::::::::::::::::::"+ incoming_number);//��logcat����ʾ����+����    
							try {
								txValue = incoming_number.getBytes("UTF-8");
//								mService.writeRXCharacteristic(txValue);//��ȡ�������ݵ������������쳣��Ҫ�����쳣
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Log.i(TAG, "I had send phoneNumber to Mabiao!!!!!!!!!!!!!!!!!!!!!");
                                
                                break;
                        case TelephonyManager.CALL_STATE_OFFHOOK:                                
                                if(incomingFlag){
                                        Log.i(TAG, "incoming ACCEPT :"+ incoming_number);
                                }
                                break;
                        
                        case TelephonyManager.CALL_STATE_IDLE:                                
                                if(incomingFlag){
                                        Log.i(TAG, "incoming IDLE");                                
                                }
                                break;
                        } 
                }
        }
}
