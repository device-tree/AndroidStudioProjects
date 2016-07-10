package com.nordicsemi.User;

import com.nordicsemi.nrfUARTv2.DeviceListActivity;
import com.nordicsemi.nrfUARTv2.BleActivity;
import com.nordicsemi.nrfUARTv2.R;
import com.nordicsemi.nrfUARTv2.R.layout;
import com.nordicsemi.nrfUARTv2.UartService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HowHighActivity extends Activity implements OnClickListener{
	
	/*这里定义的基类要与其类型对应，如ImageView，Button*/
	private ImageView getback;
	private Button nextstepheigh, heigh_up, heigh_down;
	private TextView heightshow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.how_high_activity);
		
		
		
		getback = (ImageView) findViewById(R.id.get_back);
		nextstepheigh = (Button) findViewById(R.id.next_step_heigh);
		
		heigh_down = (Button) findViewById(R.id.heigh_down);
		heigh_up = (Button) findViewById(R.id.heigh_up);
		heightshow = (TextView) findViewById(R.id.height);
		
		getback.setOnClickListener(this);
		nextstepheigh.setOnClickListener(this);
		
		heigh_up.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				heightshow.setText(Integer.valueOf(heightshow.getText().toString()) + 1 + "");//点击+按键加1
//				nextstepheigh.setVisibility(View.VISIBLE);
				
			}
		});
		
		heigh_down.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				heightshow.setText(Integer.valueOf(heightshow.getText().toString()) - 1 + "");//点击-按键减1
//				nextstepheigh.setVisibility(View.VISIBLE);
				
			}
		});
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.get_back:
			this.finish();
			//这里是按键的返回操作
			//或者直接finish();
			//finish(this);这里不要写this，finish里面不能放东西；
			break;
		case R.id.next_step_heigh:
			startActivity(new Intent(this, WhatYourSexActivity.class));
			
			break;

		default:
			break;
		}
		
	}

}
