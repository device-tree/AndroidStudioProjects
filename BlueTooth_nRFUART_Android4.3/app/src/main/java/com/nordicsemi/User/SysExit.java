package com.nordicsemi.User;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class SysExit {
	
	//����һ��public static��list������activity 
    public static List activityList = new ArrayList(); 
    
    
      //finish����list�е�activity������ǰ�����е�Activity
    public static void exit(){    
    	
        int siz=activityList.size();     
        for(int i=0;i<siz;i++){        
            if(activityList.get(i)!=null){            
                ((Activity) activityList.get(i)).finish();        
                }     
            } 
    }
    
    //�Լ�����һ����ʱ����
    private void delay(int ms){
		try {
            Thread.currentThread();
			Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
	 }	
	
	
	
	

}
