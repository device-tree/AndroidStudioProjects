package com.nordicsemi.Myfiles;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.Context;

public class FileService {

	private Context context;
	public FileService(Context context) {
		this.context = context;
	}

/**	@��APPд���ݳ��ļ���
	@param	accountString
	@param	accountString
 	@throws Exception
	**/
	public void save(String accountString, String passwdString) throws Exception {
		// TODO Auto-generated method stub
		//����ᷢ�����⣬Ȼ����throws Exception�׳���ǰ���Activity��ȥʵ��
		FileOutputStream outStreamaccount = context.openFileOutput("account.txt", context.MODE_PRIVATE); 
		FileOutputStream outStreampasswd = context.openFileOutput("passwd.txt", context.MODE_PRIVATE);
		outStreamaccount.write(accountString.getBytes());
		outStreampasswd.write(passwdString.getBytes());
		outStreamaccount.close();
		outStreampasswd.close();
	}
	
/**	//���ļ��ж����ݽ�app
 * @param filename�ļ���
 * @return �ļ�����
 * @throws Exception
**/	
	public String read(String filename) throws Exception{
		
		FileInputStream inStream = context.openFileInput(filename);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = inStream.read(buffer)) != -1){//���ݶ������浽����buffer��
			outStream.write(buffer, 0, len);//�ٽ�����buffer�е�����д���ڴ�
		}//ͨ�����ѭ�������ļ��е����ݶ����ڴ���ȥ
		
		//����ڴ�����ݱ��浽����data�У�����õ��������൱��ǰ�汣��ʱgetBytes()�����õ�������
		byte[] data = outStream.toByteArray();
		return new String(data);//string��ת����ϵͳĬ�ϱ�����ַ���

	} 
	

}
