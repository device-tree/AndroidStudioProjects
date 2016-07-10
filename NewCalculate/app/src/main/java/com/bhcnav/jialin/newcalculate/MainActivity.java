package com.bhcnav.jialin.newcalculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    TextView tv_display;
    List<StringBuffer> list;
    private double result;
    private double cacheCount;
    char operate;
    private int state;
    private final int STATE_INIT = 0;   //输入第一个数字
    StringBuffer sb;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    /**
    * 程序初始化
    */
    private void init(){
        tv_display=(TextView)findViewById(R.id.tv_display);
        list=new LinkedList<StringBuffer>();
        result = 0;
        cacheCount = 0;
        operate='+';
        state = STATE_INIT;
        sb = new StringBuffer();
        tv_display.setTextSize(100);
        tv_display.setText("0");

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.bt_0:
                updateData('0');
                break;
            case R.id.bt_1:
                updateData('1');
                break;
            case R.id.bt_2:
                updateData('2');
                break;
            case R.id.bt_3:
                updateData('3');
                break;
            case R.id.bt_4:
                updateData('4');
                break;
            case R.id.bt_5:
                updateData('5');
                break;
            case R.id.bt_6:
                updateData('6');
                break;
            case R.id.bt_7:
                updateData('7');
                break;
            case R.id.bt_8:
                updateData('8');
                break;
            case R.id.bt_9:
                updateData('9');
                break;
            case R.id.bt_add:
                updateData('+');
                break;
            case R.id.bt_minus:
                updateData('-');
                break;
            case R.id.bt_multiply:
                updateData('*');
                break;
            case R.id.bt_divide:
                updateData('/');
                break;
            case R.id.bt_point:
                updateData('.');
                break;
            case R.id.bt_delete:
                updateData('d');
                break;
            case R.id.bt_equl:
                updateData('=');
                break;
            case R.id.tv_init:
                init();
                break;
        }
    }


    public void updateData(char ch){
        if((ch <= '9' && ch >= '0') || ch == '.'){
            sb.append(ch);
            tv_display.setText(sb);
        }
        switch (ch){
            case 'd':
                if(sb.length() == 0)
                    break;
                sb.deleteCharAt(sb.length()-1);
                tv_display.setText(sb);
                break;
            case '+':
                if(sb.length() == 0) {
                    init();
                    break;
                }
            cacheCount = Double.parseDouble(sb.toString());
            operator();
            operate='+';
            break;
            case '-':
                if(sb.length() == 0){
                    init();
                    break;
                }
                cacheCount = Double.parseDouble(sb.toString());
                operator();
                operate='-';
                break;
            case '*':
                if(sb.length() == 0){
                    init();
                    break;
                }
                cacheCount = Double.parseDouble(sb.toString());
                operator();
                operate = '*';
                break;
            case '/':
                if(sb.length() == 0){
                    init();
                    break;
                }
                cacheCount =Double.parseDouble(sb.toString());
                operator();
                operate = '/';
                break;
            case '=':
                if(sb.length() == 0){
                    init();
                    break;
                }
                cacheCount = Double.parseDouble(sb.toString());
                operator();
                operate = '+';
                cacheCount = 0;
                result = 0;
                break;
        }
        Log.d(TAG, cacheCount+"#"+result);
    }

    private void operator(){
        String str;
        switch (operate){
            case '+':
                result += cacheCount;
                str = String.valueOf(result);
                sb = new StringBuffer();
                tv_display.setText(str);
                break;
            case '-':
                result -= cacheCount;
                str = String.valueOf(result);
                sb = new StringBuffer();
                tv_display.setText(str);
                break;
            case '*':
                result *= cacheCount;
                str = String.valueOf(result);
                sb = new StringBuffer();
                tv_display.setText(str);
                break;
            case '/':
                result /= cacheCount;
                str = String.valueOf(result);
                sb = new StringBuffer();
                tv_display.setText(str);
                break;
            case '=':
                result += cacheCount;
                str = String.valueOf(result);
                sb = new StringBuffer();
                tv_display.setText(str);
                cacheCount = 0;
                result = 0;
                break;
        }
    }


}
