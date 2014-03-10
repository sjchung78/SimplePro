package main;

import handler.LogHandler;

import java.io.*;

import main.DexClass.DexClassBody;
import main.NormalClass.JavaClassBody;
import utils.ClassConstants;
import utils.Utils;

public class SimplePro {

	private DataInput dataInput;
	LogHandler logger = new LogHandler("Main");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "C:\\David\\Course\\Capstone Project\\Sample APK\\classes.dex";
		String file1 = "C:\\z. Work\\workspace\\SimplePro\\bin\\main\\SimplePro.class";
		String file2 = "C:\\David\\Course\\Capstone Project\\Sample APK\\HelloWorld_Unproguard\\classes.dex";
		String file3 = "C:\\David\\Course\\Capstone Project\\Sample APK\\HelloWorld_Proguard\\classes.dex";
		new SimplePro().execute(file);
		
	}
	
	private void execute(String file){
		try {
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			dataInput = dataInputStream;
			logger.info("Start! ["+file+"]");
			int u4magic = dataInput.readInt();
	        if (ClassConstants.MAGIC != u4magic){
	        	int u4magic2 = dataInput.readInt();
	        	
	        	if (ClassConstants.MAGIC_FOR_DEX_0 != u4magic || ClassConstants.MAGIC_FOR_DEX_1 != u4magic2){
	        		System.out.println("Magic number not exists! Not a valid class.");
	        		return;
	        	}
	        	System.out.println(Utils.form(u4magic)+"  "+Utils.form(u4magic2));
	        	
	        	DexClassBody dcb = new DexClassBody(u4magic, u4magic2, logger);
	        	dcb.execute(file, dataInput);
	        } else {
	        	System.out.println("Magic Number = ["+Utils.form(u4magic)+"]");
	        	JavaClassBody jcb = new JavaClassBody(u4magic, logger);
	        	jcb.execute(file, dataInput);
	        }
		} catch (Exception ex){
			
		}
	}
	
	
}
