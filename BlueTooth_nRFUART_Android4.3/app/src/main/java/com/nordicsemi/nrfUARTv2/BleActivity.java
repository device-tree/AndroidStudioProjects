/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nordicsemi.nrfUARTv2;

import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//�����¼�RadioGroup.OnCheckedChangeListener
public class BleActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
	
    private static final int REQUEST_SELECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final int UART_PROFILE_READY = 10;
    public static final String TAG = "nRFUART";
    private static final int UART_PROFILE_CONNECTED = 20;//�˳���ʾ
    private int mState = UART_PROFILE_DISCONNECTED;////�˳���ʾ
    
    private static final int UART_PROFILE_DISCONNECTED = 21;
    private static final int STATE_OFF = 10;
    boolean flag = true;

    TextView mRemoteRssiVal;
    TextView current_speed_textview1, current_speed_textview2, current_speed_textview3, current_speed_textview4, current_speed_textview5, current_speed_textview6;
    TextView average_speed1, average_speed2, average_speed3, average_speed4, average_speed5, average_speed6;
    TextView max_speed1, max_speed2, max_speed3, max_speed4, max_speed5, max_speed6;
    TextView elevation1, elevation2, elevation3, elevation4, elevation5, elevation6, elevation7;
    TextView mileage1, mileage2, mileage3, mileage4, mileage5, mileage6;
    TextView average_peispeed1, average_peispeed2, average_peispeed3, average_peispeed4, average_peispeed5, average_peispeed6;
    TextView use_time1, use_time2, use_time3, use_time4, use_time5;
    
    TextView rise1, rise2, rise3, rise4, rise5, rise6, rise7;
    TextView fall1, fall2, fall3, fall4, fall5, fall6, fall7;
    TextView grade1, grade2, grade3;
    
    RelativeLayout relativeLayout_rise, relativeLayout_rise_total;
    RadioGroup mRg;
    
    private UartService mService = null;
    private BluetoothDevice mDevice = null;
    private BluetoothAdapter mBtAdapter = null;
    //private ListView messageListView;
    //private ArrayAdapter<String> listAdapter;
    private Button btnConnectDisconnect, btnSend, btnRequestStart, btnRequestStop, btnRequestPause;
    private EditText edtMessage;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_mabiao);
        
        //����������Ҫ���ұ��ػ�����������������һ��ϵͳĬ�Ͽ��õ������豸
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        // ��ʾ��������û���ҵ�����Ӳ�����������ڵ�����
        /*���û������������ʾ����������*/
        if (mBtAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        
      //=====================================================================================UI�ĳ�ʼ������ʼ��
        //messageListView = (ListView) findViewById(R.id.listMessage);
        //listAdapter = new ArrayAdapter<String>(this, R.layout.message_detail);
        //messageListView.setAdapter(listAdapter);
        //messageListView.setDivider(null);
        btnConnectDisconnect=(Button) findViewById(R.id.btn_select);
//        btnSend= (Button) findViewById(R.id.sendButton);
        btnRequestStart = (Button) findViewById(R.id.start_requst_Button);
        btnRequestStop = (Button) findViewById(R.id.stop_requst_Button);
        btnRequestPause = (Button) findViewById(R.id.pause_requst_Button);
        
        current_speed_textview1 = (TextView) findViewById(R.id.current_speed_textview1);
        current_speed_textview2 = (TextView) findViewById(R.id.current_speed_textview2);
        current_speed_textview3 = (TextView) findViewById(R.id.current_speed_textview3);
        current_speed_textview4 = (TextView) findViewById(R.id.current_speed_textview4);
        current_speed_textview5 = (TextView) findViewById(R.id.current_speed_textview5);
        current_speed_textview6 = (TextView) findViewById(R.id.current_speed_textview6);
        average_speed1 = (TextView) findViewById(R.id.average_speed1); 
        average_speed2 = (TextView) findViewById(R.id.average_speed2); 
        average_speed3 = (TextView) findViewById(R.id.average_speed3); 
        average_speed4 = (TextView) findViewById(R.id.average_speed4); 
        average_speed5 = (TextView) findViewById(R.id.average_speed5); 
        average_speed6 = (TextView) findViewById(R.id.average_speed6); 
        max_speed1 = (TextView) findViewById(R.id.max_speed1);
        max_speed2 = (TextView) findViewById(R.id.max_speed2);
        max_speed3 = (TextView) findViewById(R.id.max_speed3);
        max_speed4 = (TextView) findViewById(R.id.max_speed4);
        max_speed5 = (TextView) findViewById(R.id.max_speed5);
        max_speed6 = (TextView) findViewById(R.id.max_speed6);
        elevation1 = (TextView) findViewById(R.id.elevation1);
        elevation2 = (TextView) findViewById(R.id.elevation2);
        elevation3 = (TextView) findViewById(R.id.elevation3);
        elevation4 = (TextView) findViewById(R.id.elevation4);
        elevation5 = (TextView) findViewById(R.id.elevation5);
        elevation6 = (TextView) findViewById(R.id.elevation6);
        elevation7 = (TextView) findViewById(R.id.elevation7);
        
        rise1 = (TextView) findViewById(R.id.rise1);
        rise2 = (TextView) findViewById(R.id.rise2);
        rise3 = (TextView) findViewById(R.id.rise3);
        rise4 = (TextView) findViewById(R.id.rise4);
        rise5 = (TextView) findViewById(R.id.rise5);
        rise6 = (TextView) findViewById(R.id.rise6);
        rise7 = (TextView) findViewById(R.id.rise7);
        fall1 = (TextView) findViewById(R.id.fall1);
        fall2 = (TextView) findViewById(R.id.fall2);
        fall3 = (TextView) findViewById(R.id.fall3);
        fall4 = (TextView) findViewById(R.id.fall4);
        fall5 = (TextView) findViewById(R.id.fall5);
        fall6 = (TextView) findViewById(R.id.fall6);
        fall7 = (TextView) findViewById(R.id.fall7);
        
        mileage1 = (TextView) findViewById(R.id.mileage1);
        mileage2 = (TextView) findViewById(R.id.mileage2);
        mileage3 = (TextView) findViewById(R.id.mileage3);
        mileage4 = (TextView) findViewById(R.id.mileage4);
        mileage5 = (TextView) findViewById(R.id.mileage5);
        mileage6 = (TextView) findViewById(R.id.mileage6);
        
        average_peispeed1 = (TextView) findViewById(R.id.average_peispeed1);
        average_peispeed2 = (TextView) findViewById(R.id.average_peispeed2);
        average_peispeed3 = (TextView) findViewById(R.id.average_peispeed3);
        average_peispeed4 = (TextView) findViewById(R.id.average_peispeed4);
        average_peispeed5 = (TextView) findViewById(R.id.average_peispeed5);
        average_peispeed6 = (TextView) findViewById(R.id.average_peispeed6);
        
        use_time1 = (TextView) findViewById(R.id.use_time1);
        use_time2 = (TextView) findViewById(R.id.use_time2);
        use_time3 = (TextView) findViewById(R.id.use_time3);
        use_time4 = (TextView) findViewById(R.id.use_time4);
        use_time5 = (TextView) findViewById(R.id.use_time5);
        
        grade1 = (TextView) findViewById(R.id.grade1);
        grade2 = (TextView) findViewById(R.id.grade2);
        grade3 = (TextView) findViewById(R.id.grade3);
        
        relativeLayout_rise = (RelativeLayout) findViewById(R.id.relativeLayout7);
        relativeLayout_rise_total = (RelativeLayout) findViewById(R.id.relativeLayout4);
        
//        edtMessage = (EditText) findViewById(R.id.sendText);//����ע���˷��������ı���    	
        //=====================================================================================UI�ĳ�ʼ������β��
        
        service_init();//�����ʼ��
        
        // Handler Disconnect & Connect button
        btnConnectDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBtAdapter.isEnabled()) {//���û�п��������ǿ���ͨ������Ĵ��������û�����
                	
                    Log.i(TAG, "onClick - BT not enabled yet");
                    //����һ��Intent������������һ��activity������ʾ�û���������  
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, REQUEST_ENABLE_BT);//����һ��activity������Ӧ��Ҫ����������������з���
                }
                else {
                	if (btnConnectDisconnect.getText().equals("Connect")){//���ﰴ��connect��ť
                		
                		//Connect button pressed, open DeviceListActivity class, with popup windows that scan for devices
                		//���connect��ť������DeviceListActivity��ѡ���������Ӻ󷵻�BleActivity
            			Intent newIntent = new Intent(BleActivity.this, DeviceListActivity.class);
            			startActivityForResult(newIntent, REQUEST_SELECT_DEVICE);
        			} else {
        				//Disconnect button pressed
        				if (mDevice!=null)
        				{
        					mService.disconnect();
        				}
        			}
                }
            }
        });
        //���¿�ʼ��ť��������������ָ������
        btnRequestStart.setOnClickListener
        (
        	new View.OnClickListener() 
        	{
				public void onClick(View v) 
				{
					
					btnRequestStart.setVisibility(View.GONE);//����Լ�����
					btnRequestPause.setVisibility(View.VISIBLE);//��ʾ
					btnRequestStop.setVisibility(View.VISIBLE);			
					
					byte StartValue[] = new byte[]{0x7C, 0x33, 0x00 ,0x01, 0x01, 0x00, 0x02, 0x00, 0x00, 0x01, 0x00, 0x01};//��ȡ�豸�������� ������ 02
					mService.writeRXCharacteristic(StartValue);					
				}
        	}
        );
        //����ֹͣ��ť,����һ����������ָ��
        btnRequestStop.setOnClickListener
        (
    		new View.OnClickListener() 
    		{
        		public void onClick(View v)
        		{
					// TODO Auto-generated method stub
					btnRequestStart.setVisibility(View.VISIBLE);
					btnRequestPause.setVisibility(View.GONE);
					btnRequestStop.setVisibility(View.GONE);
					
					byte StopValue[] = new byte[]{0x7C, 0x33, 0x00 ,0x01, 0x01, 0x00, 0x02, 0x00, 0x00, 0x01, 0x00, 0x01};//��ȡ�豸��������
					mService.writeRXCharacteristic(StopValue);
			    }
    		}
        );
        //������ͣ��ť������һ����������ָ��
        btnRequestPause.setOnClickListener
        (
        		new View.OnClickListener() 
        		{
					public void onClick(View v) 
					{
						if (flag) 
						{
							btnRequestPause.setText("Start");
							flag = false;
						}
						else 
							{
								btnRequestPause.setText("Pause");
								flag = true;								
							}				
						byte PauseValue[] = new byte[]{0x7C, 0x33, 0x00 ,0x01, 0x01, 0x00, 0x02, 0x00, 0x00, 0x01, 0x00, 0x01};//��ȡ�豸��������
						mService.writeRXCharacteristic(PauseValue);
						Log.i(TAG, "Pause Button is clicked......clicked.........clicked..........clicked");		
					}
        		}
        );
    }
    
	//UART service connected/disconnected----------------���ڷ������ӺͶϿ�
    private ServiceConnection mServiceConnection = new ServiceConnection() {
    	
        public void onServiceConnected(ComponentName className, IBinder rawBinder) {
        		mService = ((UartService.LocalBinder) rawBinder).getService();
        		Log.d(TAG, "onServiceConnected mService= " + mService);
        		if (!mService.initialize()) {
                    Log.e(TAG, "Unable to initialize Bluetooth");
                    finish();
                }
        }

        public void onServiceDisconnected(ComponentName classname) {
       ////     mService.disconnect(mDevice);
        		mService = null;
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        //Handler events that received from UART service
        public void handleMessage(Message msg) {
        }
    };

    //ע��һ��BroadcastReceiver(�㲥������)���������ղ��ҵ��������豸��Ϣ
    private final BroadcastReceiver UARTStatusChangeReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            final Intent mIntent = intent;
           //*********************//
            //UartService.ACTION_GATT_CONNECTED---------------��ʾ���ӳɹ�
            if (action.equals(UartService.ACTION_GATT_CONNECTED)) {
            	 runOnUiThread(new Runnable() {
                     public void run() {
                         	String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                             Log.d(TAG, "UART_CONNECT_MSG");
                             btnConnectDisconnect.setText("Disconnect");//�����Ϻ�ťConnect��ΪDisconnect
//                             edtMessage.setEnabled(true);
//                             btnSend.setEnabled(true);
                             
                             //��ȡÿ���豸�����ƺ�MAC��ַ��ӵ�����������myArrayAdapter��
//                             ((TextView) findViewById(R.id.deviceName)).setText(mDevice.getName()+ " - ready");//�Ѿ���xml��ע��deviceName
                             showMessage(mDevice.getName()+ " - ready");
                             //listAdapter.add("["+currentDateTimeString+"] Connected to: "+ mDevice.getName());//��ʾ���ӵ�ָ���豸
                        	 //messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);//�б���Ϣ�Ử��
                             mState = UART_PROFILE_CONNECTED;
                     }
            	 });
            }
           
          //*********************//��Disconnect�Ͽ�����
            if (action.equals(UartService.ACTION_GATT_DISCONNECTED)) {
            	 runOnUiThread(new Runnable() {
                     public void run() {
                    	 	 String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                             Log.d(TAG, "UART_DISCONNECT_MSG");
                             btnConnectDisconnect.setText("Connect");
//                             edtMessage.setEnabled(false);//����Ҫע�ͣ�û��ע�ͻ�ֹͣ���У���Ϊǰ���Ѿ���edtMessageע���ˣ�����Ͳ��ܶ�edtMessage���в���
//                             btnSend.setEnabled(false);//����ͬ��
                             //�����½���ʾ�豸û����
//                             ((TextView) findViewById(R.id.deviceName)).setText("Not Connected");//��ע��deviceName
                             showMessage("Not Connected");//��toast����ʾ"Not Connected"
                             //���б�����ʾ����
                             //listAdapter.add("["+currentDateTimeString+"] Disconnected to: "+ mDevice.getName());
                             
                             mState = UART_PROFILE_DISCONNECTED;
                             mService.close();
                            //setUiState();
                     }
                 });
            }
            
          //*********************//
          //UartService���ڷ���
            if (action.equals(UartService.ACTION_GATT_SERVICES_DISCOVERED)) {
             	 mService.enableTXNotification(); 
            }
            
          //*********************//����UartService������ʾ��listView��(�����޸ĳ���ʾ��TextView��)
            if (action.equals(UartService.ACTION_DATA_AVAILABLE)) {
            	
                final byte[] rxValue = intent.getByteArrayExtra(UartService.EXTRA_DATA);//���յ����ݣ��ֽ����ͣ�
//                final int[] rxValue = intent.getIntArrayExtra(UartService.EXTRA_DATA);//���յ����ݣ��ֽ����ͣ���������ȶ�
                
                //��runOnUiThread�������߳�  
//                runOnUiThread(new Runnable() {//??????????????????????????????????????
//                public void run() {/////////////////

                	//�����ҵ�֡ͷ�±�
                	int headnum = 0, i = 0;
                	
                	for(i = 0; i < rxValue.length; i++)
                	{
                		if(rxValue[i] == 0x7C)
                			headnum = i;
                	}
                	
//            		��У��,������У������
            		boolean parity = false;  //��ʼ�жϱ��
                	int sum = 0;
                	
            		int datalen = rxValue[headnum + 8];//get effect data length
            		for (i = headnum; i < headnum + datalen + 12; i++) {//��������֡��ʱ��ȫ��������У��λ����Ҫ��Ӻ�
            			sum += rxValue[i];
            		}
            		
            		//��żУ��
            		if (sum % 2 == 0){
            			parity = false; //ż��
            		} else {
            			parity = true;	//����
            		}
            		
					//�Ѿ������������valuesum��1����ĿΪ��������parity=true������parity=false����ʾ��ż��������
	    				if (parity == false) { 
	//    					Toast.makeText(getApplicationContext(), "���ݷ��ͳ���parity == false", Toast.LENGTH_SHORT).show();
	    					Log.w(TAG, "--------------����У�鲻��������parity == false----------------");
	    					}
	    				else {
	    						
	    					switch (rxValue[headnum + 6]) {	
	    						case 0x0A:
	    							Log.i(TAG, "Mabiao sending acknowledgement!!!!!!!!!!!!!!!!!!0x0A");//����Ӧ��
	    							break;
	    						case 0x0B:
	    							//�����get�����������ĵ��������Ϣ
	    							Log.i(TAG, "Mabiao request to send device parameter!!!!!!!!!!!!!!!!!0x0B");//�����豸����
	    							break;
	    						case 0x0C:
	    							Log.i(TAG, "Mabiao request to send device status!!!!!!!!!!!!!!!!!!!0x0C");//�����豸״̬
	    							break;
								case 0x0D:
									Log.i(TAG, "Mabiao request to send device location information!!!!!!!!!0x0D");//����λ����Ϣ
									break;
								case 0x0E:
									Log.i(TAG, "Mabiao request to send Track statistics!!!!!!!!!!!!!0x0E");//���͹켣ͳ��
									break;
								case 0x0F:
									Log.i(TAG, "Mabiao request Storage path to upload!!!!!!!!!!!!!!!0x0F");//�洢�켣�ϴ�
									break;
								case 0x10:
									Log.i(TAG, "Mabiao Data reception is completed!!!!!!!!!!!!!!!!!!0x10");//���ݽ������״̬
									break;
								case 0x11:
									Log.i(TAG, "Mabiao request Start timming!!!!!!!!!!!!!!!!!!0x11");//��ʼ��ʱ
									break;
								case 0x12:
									Log.i(TAG, "Mabiao request Pause timming!!!!!!!!!!!!!!!!!!0x12");//��ͣ��ʱ
									break;
								case 0x13:
									Log.i(TAG, "Mabiao request Stop timming!!!!!!!!!!!!!!!!!!0x13");//ֹͣ��ʱ
									break;
								case 0x14:
									Log.i(TAG, "show current_speed!!!!!!!!!!!!!!!!!!20");//��ǰ�ٶ�
									try {
	//									String text = new String(temp, "UTF-8");//���ֽ�������ת���ַ�������
	//									for (int i = 9 + headnum, j = 0; i < rxValue[8] + headnum + 9; j++, i++) {
	//										byte[] temp = null;
	//										temp[j] = rxValue[i];
	//									}
										current_speed_textview1.setText(""+(rxValue[headnum + 9]-0x30));
										current_speed_textview2.setText(""+(rxValue[headnum + 9 + 1]-0x30));
										current_speed_textview3.setText(""+(rxValue[headnum + 9 + 2]-0x30));
										current_speed_textview4.setText(""+(rxValue[headnum + 9 + 3]-0x30));
	//									current_speed_textview5.setText(""+(rxValue[headnum + 9 + 4]-0x30));//�Ӷ���-0x30��������С����
										current_speed_textview6.setText(""+(rxValue[headnum + 9 + 5]-0x30));
										Log.i(TAG, "I have received  current_speed!!!!!!!!!!!!!!!!!!!!!!!20");
									} catch (Exception e) {
										Log.e(TAG, e.toString());
										Log.i(TAG, "get speed error!");
									}
									break;
								case 0x15:
									Log.i(TAG, "show average_speed!!!!!!!!!!!!!!!!!!21");//ƽ���ٶ�
									try {
										average_speed1.setText(""+(rxValue[headnum + 9]-0x30));
										average_speed2.setText(""+(rxValue[headnum + 9 + 1]-0x30));
										average_speed3.setText(""+(rxValue[headnum + 9 + 2]-0x30));
										average_speed4.setText(""+(rxValue[headnum + 9 + 3]-0x30));
										average_speed5.setText(""+(rxValue[headnum + 9 + 4]-0x30));
										average_speed6.setText(""+(rxValue[headnum + 9 + 5]-0x30));
										Log.i(TAG, "I have received  average_speed!!!!!!!!!!!!!!!!!!!!!!!21");
									} catch (Exception e) {
										Log.e(TAG, e.toString());
									}
									break;
								case 0x16:
									Log.i(TAG, "show elevation!!!!!!!!!!!!!!!!!!22");//����
									try {
										elevation1.setText(""+(rxValue[headnum + 9]-0x30));
										elevation2.setText(""+(rxValue[headnum + 9 + 1]-0x30));
										elevation3.setText(""+(rxValue[headnum + 9 + 2]-0x30));
										elevation4.setText(""+(rxValue[headnum + 9 + 3]-0x30));
										elevation5.setText(""+(rxValue[headnum + 9 + 4]-0x30));
										elevation6.setText(""+(rxValue[headnum + 9 + 5]-0x30));
										elevation7.setText(""+(rxValue[headnum + 9 + 6]-0x30));
										Log.i(TAG, "I have received  elevation!!!!!!!!!!!!!!!!!!!!!!!22");
									} catch (Exception e) {
										Log.e(TAG, e.toString());
									}
									break;
								case 0x17:
									Log.i(TAG, "show max_speed!!!!!!!!!!!!!!!!!!23");//����ٶ�
									try {
										max_speed1.setText(""+(rxValue[headnum + 9]-0x30));
										max_speed2.setText(""+(rxValue[headnum + 9 + 1]-0x30));
										max_speed3.setText(""+(rxValue[headnum + 9 + 2]-0x30));
										max_speed4.setText(""+(rxValue[headnum + 9 + 3]-0x30));
										max_speed5.setText(""+(rxValue[headnum + 9 + 4]-0x30));
										max_speed6.setText(""+(rxValue[headnum + 9 + 5]-0x30));
										Log.i(TAG, "I have received  max_speed!!!!!!!!!!!!!!!!!!!!!!!23");
									} catch (Exception e) {
										Log.e(TAG, e.toString());
									}
									break;
								case 0x18:
									Log.i(TAG, " !!!!!!!!!!!!!!!!!!24");//
									break;
								case 0x19:
									Log.i(TAG, " !!!!!!!!!!!!!!!!!!25");//
									break;
								case 0x1A:
									Log.i(TAG, " !!!!!!!!!!!!!!!!!!26");//
									break;
								case 0x1B:
									Log.i(TAG, " !!!!!!!!!!!!!!!!!!27");//
									break;
								case 0x1C:
									Log.i(TAG, " !!!!!!!!!!!!!!!!!!28");//
									break;
								case 0x1D:
									Log.i(TAG, " !!!!!!!!!!!!!!!!!!29");//
									break;
								case 0x1E:
									Log.i(TAG, " !!!!!!!!!!!!!!!!!!30");//
									break;
								case 0x1F:
									Log.i(TAG, "show grade!!!!!!!!!!!!!!!!!!31");//�¶�
									try {
										grade1.setText(""+(rxValue[headnum + 9]-0x30));
										grade2.setText(""+(rxValue[headnum + 9 + 1]-0x30));
										grade3.setText(""+(rxValue[headnum + 9 + 2]-0x30));
										Log.i(TAG, "I have received  grade!!!!!!!!!!!!!!!!!!!!!!!32");
									} catch (Exception e) {
										// TODO: handle exception
										Log.e(TAG, e.toString());
									}
									
									break;
								case 0x20:
									Log.i(TAG, "show average_peispeed!!!!!!!!!!!!!!!!!!32");//ƽ������
									try {
										average_peispeed1.setText(""+(rxValue[headnum + 9]-0x30));
										average_peispeed2.setText(""+(rxValue[headnum + 9 + 1]-0x30));
										average_peispeed3.setText(""+(rxValue[headnum + 9 + 2]-0x30));
										average_peispeed4.setText(""+(rxValue[headnum + 9 + 3]-0x30));
										average_peispeed5.setText(""+(rxValue[headnum + 9 + 4]-0x30));
										average_peispeed6.setText(""+(rxValue[headnum + 9 + 5]-0x30));
										Log.i(TAG, "I have received  average_peispeed!!!!!!!!!!!!!!!!!!!!!!!32");
									} catch (Exception e) {
										Log.e(TAG, e.toString());
									}
									break;
								case 0x21:
									Log.i(TAG, " !!!!!!!!!!!!!!!!!!33");//
									break;
								case 0x22:
									Log.i(TAG, " !!!!!!!!!!!!!!!!!!34");//
									break;
								case 0x23:
									Log.i(TAG, "show mileage!!!!!!!!!!!!!!!!!!35");//���
									try {
										mileage1.setText(""+(rxValue[headnum + 9]-0x30));
										mileage2.setText(""+(rxValue[headnum + 9 + 1]-0x30));
										mileage3.setText(""+(rxValue[headnum + 9 + 2]-0x30));
										mileage4.setText(""+(rxValue[headnum + 9 + 3]-0x30));
										mileage5.setText(""+(rxValue[headnum + 9 + 4]-0x30));
										mileage6.setText(""+(rxValue[headnum + 9 + 5]-0x30));
										Log.i(TAG, "I have received  mileage!!!!!!!!!!!!!!!!!!!!!!!37");
									} catch (Exception e) {
										Log.e(TAG, e.toString());
									}
									break;
								case 0x24:
									Log.i(TAG, "show use_time!!!!!!!!!!!!!!!!!!36");//��ʱ
									try {
										use_time1.setText(""+(rxValue[headnum + 9]-0x30));
										use_time2.setText(""+(rxValue[headnum + 9 + 1]-0x30));
										use_time3.setText(""+(rxValue[headnum + 9 + 2]-0x30));
										use_time4.setText(""+(rxValue[headnum + 9 + 3]-0x30));
										use_time5.setText(""+(rxValue[headnum + 9 + 4]-0x30));
										Log.i(TAG, "I have received  use_time!!!!!!!!!!!!!!!!!!!!!!!37");
									} catch (Exception e) {
										Log.e(TAG, e.toString());
									}
									
									break;
								case 0x25:
									Log.i(TAG, "show rise!!!!!!!!!!!!!!!!!!37");//����
									try {
										rise1.setText(""+(rxValue[headnum + 9]-0x30));
										rise2.setText(""+(rxValue[headnum + 9 + 1]-0x30));
										rise3.setText(""+(rxValue[headnum + 9 + 2]-0x30));
										rise4.setText(""+(rxValue[headnum + 9 + 3]-0x30));
										rise5.setText(""+(rxValue[headnum + 9 + 4]-0x30));
										rise6.setText(""+(rxValue[headnum + 9 + 5]-0x30));
										rise7.setText(""+(rxValue[headnum + 9 + 6]-0x30));
										Log.i(TAG, "I have received  rise!!!!!!!!!!!!!!!!!!!!!!!37");
									} catch (Exception e) {
										Log.e(TAG, e.toString());
									}
									break;
								case 0x26:
									Log.i(TAG, "show fall!!!!!!!!!!!!!!!!!!38");//�½�
									try {
										fall1.setText(""+(rxValue[headnum + 9]-0x30));
										fall2.setText(""+(rxValue[headnum + 9 + 1]-0x30));
										fall3.setText(""+(rxValue[headnum + 9 + 2]-0x30));
										fall4.setText(""+(rxValue[headnum + 9 + 3]-0x30));
										fall5.setText(""+(rxValue[headnum + 9 + 4]-0x30));
										fall6.setText(""+(rxValue[headnum + 9 + 5]-0x30));
										fall7.setText(""+(rxValue[headnum + 9 + 6]-0x30));
										Log.i(TAG, "I have received  fall!!!!!!!!!!!!!!!!!!!!!!!38");
									} catch (Exception e) {
										Log.e(TAG, e.toString());
									}
									break;
									
								default:
									break;
								}//switch-case
 					
	    						
	    					}//if~else
                	
					
//	        				�����������Իش�����20���ֽڵ�ʱ���õĴ��루֮ǰ��ʲô���ݾͻش�ʲô���ݣ�����У��ɹ������ݲŻش���				
        				callback_send_message(rxValue);	   

//                }//run()

//                });//runOnUiThread()
            }//if(){}
            
            //********************************//
            if (action.equals(UartService.DEVICE_DOES_NOT_SUPPORT_UART)){
            	showMessage("Device doesn't support UART. Disconnecting");
            	mService.disconnect();
            }
        }//onReceive()
    };//new BroadcastReceiver()���������ղ��ҵ��������豸��Ϣ�ൽ�����
    

    private void service_init() {
        Intent bindIntent = new Intent(this, UartService.class);
        bindService(bindIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
  
        LocalBroadcastManager.getInstance(this).registerReceiver(UARTStatusChangeReceiver, makeGattUpdateIntentFilter());
    }
    


	//����intentfilter���㲥���պ���
    private static IntentFilter makeGattUpdateIntentFilter() {
    	
        final IntentFilter intentFilter = new IntentFilter();// ע����� BroadcastReceiver 
        
        intentFilter.addAction(UartService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(UartService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(UartService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(UartService.ACTION_DATA_AVAILABLE);
        intentFilter.addAction(UartService.DEVICE_DOES_NOT_SUPPORT_UART);
        return intentFilter;
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
    	 super.onDestroy();
        Log.d(TAG, "onDestroy()");
        
        try {
        	//ʹ��unregisterReceiver������ע�����BroadcastReceiver����֤��Դ����ȷ���ա�
        	LocalBroadcastManager.getInstance(this).unregisterReceiver(UARTStatusChangeReceiver);
        } catch (Exception ignore) {
            Log.e(TAG, ignore.toString());
        } 
        unbindService(mServiceConnection);
        mService.stopSelf();
        mService= null;       
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        if (!mBtAdapter.isEnabled()) {
            Log.i(TAG, "onResume - BT not enabled yet");
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
        switch (requestCode) {

        case REQUEST_SELECT_DEVICE:
        	//When the DeviceListActivity return, with the selected device address
            if (resultCode == Activity.RESULT_OK && data != null) {
                String deviceAddress = data.getStringExtra(BluetoothDevice.EXTRA_DEVICE);
                mDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceAddress);
               
                Log.d(TAG, "... onActivityResultdevice.address==" + mDevice + "mserviceValue" + mService);
//                ((TextView) findViewById(R.id.deviceName)).setText(mDevice.getName()+ " - connecting");//�������ʾ��textview�е�����
//                Toast.makeText(getApplicationContext(), mDevice.getName()+ " - connecting", Toast.LENGTH_SHORT).show();
//                showMessage(mDevice.getName()+ " - connecting");//����ֱ�����������ʵ��
                mService.connect(deviceAddress);                           
            }
            break;
        case REQUEST_ENABLE_BT:
            // When the request to enable Bluetooth returns
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Bluetooth has turned on ", Toast.LENGTH_SHORT).show();

            } else {
                // User did not enable Bluetooth or an error occurred
                Log.d(TAG, "BT not enabled");
                Toast.makeText(this, "Problem in BT Turning ON ", Toast.LENGTH_SHORT).show();
                finish();
            }
            break;
        default:
            Log.e(TAG, "wrong request code");
            break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
       
    }

    
    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    
    
//		�����������Իش�����20���ֽڵ�ʱ���õĴ���
    public void callback_send_message(byte[] rxValue) {

		Log.i(TAG, "I have received data ׼���ش�����!!!!!!!!!!!!!!!!!!!!!!!");
		int length = rxValue.length;
		byte[] temp1 = new byte[20];
		byte[] temp2 = new byte[20];
		
		if (length > 20) {
			
			for (int i = 0; i < 20; i++) {
				temp1[i]=rxValue[i];
			}
			mService.writeRXCharacteristic(temp1);//����mService�����еķ��ͷ���writeRXCharacteristic����������
			
			for (int i = 20, j = 0; i < length; i++, j++) {
				temp2[j]=rxValue[i];
			}
			mService.writeRXCharacteristic(temp2);	
			
			Log.i(TAG, "I have send send send send send back back back back back !!!!!!!!!!!�ش����ͳɹ�!!!!!!!!!!!!");
//			Toast.makeText(getApplicationContext(), "�ش����ͳɹ�", 1).show();
		}else {
			//mService.writeRXCharacteristic(rxValue);
			//Log.i(TAG, "I have send send send send send back back back back back !!!!!!!!!!!!�ش����ͳɹ�!!!!!!!!!!!");
//			Toast.makeText(getApplicationContext(), "�ش����ͳɹ�", 1).show();
		}  
    	
	}

    
    //����ע���˳���ʾ�Ĺ���
/*    @Override
    public void onBackPressed() {
        if (mState == UART_PROFILE_CONNECTED) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            showMessage("nRFUART's running in background.\n             Disconnect to exit");
        }
        else {
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
    }*/
}
