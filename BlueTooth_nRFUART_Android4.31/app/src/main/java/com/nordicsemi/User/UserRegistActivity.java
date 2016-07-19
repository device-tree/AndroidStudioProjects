package com.nordicsemi.User;

import com.nordicsemi.nrfUARTv2.R;
import com.nordicsemi.nrfUARTv2.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class UserRegistActivity extends Activity implements OnClickListener{
	
	private  ImageView getbackregist;
	private Button finishbtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_registing);
		
		getbackregist = (ImageView) findViewById(R.id.get_back_regist);
		finishbtn = (Button) findViewById(R.id.finish_btn);
		getbackregist.setOnClickListener(this);
		finishbtn.setOnClickListener(this);
	
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		switch (v.getId()) {
		case R.id.get_back_regist:
			this.finish();
			break;
		case R.id.finish_btn:
			startActivity(new Intent(this, MainUserUI.class));
			Toast toast = Toast.makeText(this, "登录成功", Toast.LENGTH_LONG);
			toast.show();
			break;
		default:
			break;
		}
		
	}  
	

}
