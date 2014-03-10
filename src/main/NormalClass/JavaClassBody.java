package main.NormalClass;

import handler.LogHandler;

import java.io.DataInput;
import java.io.IOException;

import main.ClassBody;
import main.contents.Constant;
import main.contents.ConstantPool;
import utils.Utils;

public class JavaClassBody implements ClassBody{

	private DataInput dataInput;
	private LogHandler logger;
	
	public JavaClassBody(int u4magic, LogHandler logger) {
		// TODO Auto-generated constructor stub
		this.logger = logger;
	}

	public void execute(String file, DataInput di) {
		dataInput = di;
		
		try {
			
//			ClassFile {
//		    	u4 magic;
//		    	u2 minor_version;
//		    	u2 major_version;
//		    	u2 constant_pool_count;
//		    	cp_info constant_pool[constant_pool_count-1];
//		    	u2 access_flags;
//		    	u2 this_class;
//		    	u2 super_class;
//		    	u2 interfaces_count;
//		    	u2 interfaces[interfaces_count];
//		    	u2 fields_count;
//		    	field_info fields[fields_count];
//		    	u2 methods_count;
//		    	method_info methods[methods_count];
//		    	u2 attributes_count;
//		    	attribute_info attributes[attributes_count];
//		    }
			
	        // Read and check the version numbers.
	        int u2minorVersion = dataInput.readUnsignedShort();
	        int u2majorVersion = dataInput.readUnsignedShort();
	        System.out.println("Version = major["+u2majorVersion+"] minor["+u2minorVersion+"]");
	        
	        // Read the constant pool. Note that the first entry is not used.
	        int u2constantPoolCount = dataInput.readUnsignedShort();
	        System.out.println("Constant pool number = " + (u2constantPoolCount-1));
	        
	        String a = "";
	        Constant con = null;
	        ConstantPool consPool = new ConstantPool(u2constantPoolCount-1);
	        
	        for (int index = 1; index < u2constantPoolCount; index++)
	        {
	        	int info1, info2;
	        	int tag = dataInput.readUnsignedByte();
	        	if (tag==5||tag==6){
	        		info1 = dataInput.readInt();
	        		info2 = dataInput.readInt();
	        		
	        		con = new Constant(tag, info1, info2);
	        		consPool.add(con);
	        		
	        	} else if (tag==7||tag==8){
	        		info1 = dataInput.readUnsignedShort();
	        		con = new Constant(tag, info1, 0);
	        		consPool.add(con);
	        		
	        	} else if (tag==9||tag==10||tag==11||tag==12){
	        		info1 = dataInput.readUnsignedShort();
	        		info2 = dataInput.readUnsignedShort();
	        		con = new Constant(tag, info1, info2);
	        		consPool.add(con);
	        	} else if (tag==3||tag==4){
	        		info1 = dataInput.readInt();
	        		con = new Constant(tag, info1, 0);
	        		consPool.add(con);
	        	} else if (tag==1){
	        		info1 = dataInput.readUnsignedShort();
	        		
	        		byte[] ab = Utils.readBytes(info1, dataInput);
	        		a += Utils.getUTF(ab);
	        		con = new Constant(tag, info1, ab, a);
	        		a = "";
	        		consPool.add(con);
	        	} else {
	        		System.out.println("Error");
	        	}
	        }
	        consPool.setting();
	        System.out.println(consPool.toString());
	        
	        // Read the general class information.
	        int u2accessFlags = dataInput.readUnsignedShort();
	        int u2thisClass   = dataInput.readUnsignedShort();
	        int u2superClass  = dataInput.readUnsignedShort();
	
	        System.out.println(Utils.form(u2accessFlags) + " " + Utils.form(u2thisClass) + " " + Utils.form(u2superClass));
	        
	        // Read the interfaces.
	        int u2interfacesCount = dataInput.readUnsignedShort();
	        System.out.println("Interface count : " + u2interfacesCount);
	        
	        int[] u2interfaces = new int[u2interfacesCount];
	        for (int index = 0; index < u2interfacesCount; index++)
	        {
	        	int tag = dataInput.readUnsignedByte();
	        	u2interfaces[index] = dataInput.readUnsignedShort();
	        	System.out.println("  Interface["+(index+1)+"] "+ Utils.form(tag)+ " " + Utils.form(u2interfaces[index]));
	        }
	
	        // Read the fields.
	        int u2fieldsCount = dataInput.readUnsignedShort();
	        System.out.println("Field count : " + u2fieldsCount);
	        
	        for (int index = 0; index < u2fieldsCount; index++)
	        {
	        	// Read the general field information.
		        int u2accessFlags2     = dataInput.readUnsignedShort();
		        int u2nameIndex       = dataInput.readUnsignedShort();
		        int u2descriptorIndex = dataInput.readUnsignedShort();
		        // Read the field attributes.
		        int u2attributesCount = dataInput.readUnsignedShort();
		        
		        System.out.println("  Field["+(index+1)+"] ["+ Utils.form(u2accessFlags2)+" "+Utils.form(u2nameIndex)+" "+Utils.form(u2descriptorIndex)+"]  Attribute Count : " + u2attributesCount);

		        for (int innerindex=0; innerindex<u2attributesCount;innerindex++)
		        {
		        	int attNameIndex = dataInput.readUnsignedShort();
		        	int attLength = dataInput.readInt();
		        	
		        	byte[] ab = Utils.readBytes(attLength,dataInput);
	        		a += Utils.getUTF(ab);
	        		
		        	System.out.println("  Field["+(innerindex+1)+"] UTF [" + a + "]");
	        		a="";
		        }
	        }
	        
	
	        // Read the methods.
	        int u2methodsCount = dataInput.readUnsignedShort();
	        System.out.println("Method count : "+u2methodsCount);
	        
	        for (int index = 0; index < u2methodsCount; index++)
	        {
	        	// Read the general method information.
		        int u2accessFlags3     = dataInput.readUnsignedShort();
		        int u2nameIndex3       = dataInput.readUnsignedShort();
		        int u2descriptorIndex3 = dataInput.readUnsignedShort();
		        // Read the method attributes.
		        int u2attributesCount3 = dataInput.readUnsignedShort();
		        System.out.println("Method ["+Utils.form(u2accessFlags3)+" "+Utils.form(u2nameIndex3)+" "+Utils.form(u2descriptorIndex3)+"]  Method Attribute count : "+u2attributesCount3);

		        for (int innerindex=0; innerindex<u2attributesCount3;innerindex++)
		        {
		        	int attNameIndex = dataInput.readUnsignedShort();
		        	int attLength = dataInput.readInt();
		        	
		        	byte[] ab = Utils.readBytes(attLength, dataInput);
	        		a += Utils.getUTF(ab);
	        		
	        		System.out.println("  Method["+(innerindex+1)+"] UTF [" + a + "]");
	        		a="";
		        }
	        }
	        
	        int attLength = dataInput.readUnsignedShort();
        	System.out.println("Attribute count : "+attLength);
        	
        	for (int innerindex=0; innerindex<attLength;innerindex++)
	        {
	        	int attNameIndex = dataInput.readUnsignedShort();
	        	int attLength2 = dataInput.readInt();
	        	System.out.println(Utils.form(attNameIndex)+" "+Utils.form(attLength2));
	        	
	        	byte[] ab = Utils.readBytes(attLength2, dataInput);
        		a += Utils.getUTF(ab);
        		
	        	System.out.println("  Attribute["+(innerindex+1)+"] Attribute UTF [" + a + "]");
        		a="";
	        }
        	
        	try{
        		int test = dataInput.readUnsignedByte();
        	} catch (Exception ex){
        		System.out.println("Test.. This error is ok.");
        	}
		} catch (Exception ex) {
			System.out.println("Exception");
		}
	}
}
