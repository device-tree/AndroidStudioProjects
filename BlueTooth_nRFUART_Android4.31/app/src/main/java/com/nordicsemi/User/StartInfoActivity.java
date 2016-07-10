package com.nordicsemi.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.nordicsemi.nrfUARTv2.R;

public class StartInfoActivity extends Activity /*implements OnClickListener*/ {
	
	private static final float FLING_MIN_DISTANCE = 0;
	private static final float FLING_MIN_VELOCITY = 0;
	private Button startbutton;
	//Activity����ʱ������
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_info_activity);
		/*��סCtrl�����ֱ�ӵ�start_info_activity�ͻ��ȥstart_info��R�ļ��в鿴id����Ctrl�ٰ����������涨һ�£���ڶ����Ϳ�������.xml�ļ���*/
		
		InitView();
	
	}
	
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {  
        // TODO Auto-generated method stub  
        if(e1.getX() - e2.getX() > FLING_MIN_DISTANCE&&Math.abs(velocityX) > FLING_MIN_VELOCITY)  
        {  
            Intent intent = new Intent(StartInfoActivity.this,StartLogoActivity.class);  
            startActivity(intent);  
             Toast.makeText(this, "��������", Toast.LENGTH_SHORT).show();   
  
        }  
        else if (e2.getX()-e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) >FLING_MIN_VELOCITY) {  
              
            //�л�Activity  
            Intent intent = new Intent(StartInfoActivity.this, HowHighActivity.class);  
            startActivity(intent);  
            Toast.makeText(this, "��������", Toast.LENGTH_SHORT).show();  
        }  
          
        return false;  
    }  
	

	private void InitView() {
		// TODO Auto-generated method stub
		startbutton = (Button) findViewById(R.id.start_info_btn);
		
		startbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(), HowHighActivity.class));
			}
		});
		/*һ������������ǣ�ֱ�Ӽ����󣬰�������setOnClickListener���棬��ʾ��ɫ����ʾ��Ȼ���onClick������onClick��startActivity��ת�Ϳ�����*/
		
	}

//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		switch (v.getId()) {
//		case R.id.start_info_btn:
//			startActivity(new Intent(this, HowHighActivity.class));
//			break;
//
//		default:
//			break;
//		}
//	}
	

}
