package utils;

import java.io.DataInput;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Utils {
	public static String form(int a){
		String result = "0x"+ (Integer.toHexString(a).length()==1||Integer.toHexString(a).length()==3?"0"+Integer.toHexString(a):Integer.toHexString(a));
		return result;
	}
	
	public static String getUTF(byte[] ab){
		String str = "";
		try {
			str += new String(ab, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static String getMUTF(byte[] ab){
		String str = "";
		try {
			str += new String(ab, "UTF-16");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static byte[] readBytes(int len, DataInput di) {
		// TODO Auto-generated method stub
		byte[] result = new byte[len];
		for (int i=0;i<len;i++){
			int info;
			try {
				info = di.readUnsignedByte();
				result[i] = (byte) info;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return result;
			}
		}
		return result;
	}
	
	public static byte[] readBytesNull(DataInput di) {
		// TODO Auto-generated method stub
		byte[] sresult = new byte[99999];
		int cnt = 0;
		for (int i=0;i<99999;i++){
			int info;
			try {
				info = di.readUnsignedByte();
				cnt++;
				if (info == 0){
					break;
				}
				sresult[i] = (byte) info;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		byte[] result = new byte[cnt];
		for (int i=0;i<cnt;i++){
			result[i] = sresult[i];
		}
		return result;
	}
	
	public static int endianInt(int ival){
		byte[] buf = breakInt2Byte(ival);
		buf = reverseArr(buf);
		int result = convByte2Int(buf);
		return result;
	}
	
	public static int endianShort(int ival){
		byte[] buf = breakShort2Byte(ival);
		buf = reverseArr(buf);
		int result = convByte2Int(buf);
		return result;
	}
	
	public static byte[] reverseArr(byte[] ba){
		byte[] result = new byte[ba.length];
		for (int i=0;i<ba.length;i++){
			result[ba.length-i-1]=ba[i];
		}
		
		return result;
	}
	public static int convByte2Int(byte[] ba){
		byte[] buf = new byte[4];
		if (ba.length<4) {
			for (int i=0;i<4;i++){
				buf[i] = (buf.length-ba.length-i > 0) ? 0 : ba[i-(buf.length-ba.length)];
			}
		} else {
			for (int i=0;i<4;i++){
				buf[i] = ba[i];
			}
		}
		ByteBuffer wrapper = ByteBuffer.wrap(buf);
		int result = wrapper.getInt();
		return result;
	}
	public static int convULEBint(byte[] ba, int hm){
		String[] sb = new String[hm];
		for (int i=0;i<hm;i++){
			sb[i] = getBinary(ba[i]);
			sb[i] = sb[i].substring(1);
		}
		String sresult = "";
		for (int i=0;i<hm;i++){
			sresult = sb[i] + sresult;
		}
		int result = Integer.parseInt(sresult, 2);
		return result;
	}
	public static int convSLEBint(byte[] ba, int hm, boolean sign){
		String[] sb = new String[hm];
		for (int i=0;i<hm;i++){
			sb[i] = getBinary(ba[i]);
			if (i==hm-1){
				sb[i] = sb[i].substring(2);
			} else {
				sb[i] = sb[i].substring(1);
			}
		}
		String sresult = "";
		for (int i=0;i<hm;i++){
			sresult = sb[i] + sresult;
		}
		if (!sign) {
			sresult = sresult.replace("0", "3");
			sresult = sresult.replace("1", "0");
			sresult = sresult.replace("3", "1");
			char[] ca = sresult.toCharArray();
			for (int i=sresult.length()-1;i>0;i--){
				if (ca[i]=='1'){
					ca[i] = '0';
				} else {
					ca[i] = '1';
					break;
				}		
			}
			sresult = "";
			for (int i=ca.length-1;i>=0;i--){
				sresult = String.valueOf(ca[i])+sresult;
			}
		}
		int result = Integer.parseInt(sresult, 2);
		if (!sign) result = result * (-1);
		return result;
	}
	public static String getBinary(byte bv){
		String result = "";
		int trimval = 0;
		
		for (int i=7;i>=0;i--){
			trimval = bv - (int)Math.pow(2,i);
			if (trimval<0){
				result += "0";
				trimval = bv;
			} else {
				result += "1";
				bv = (byte)trimval;
			}
		}
		return result;
	}
	public static String getBinary(int iv){
		String result = "";
		int trimval = 0;
		
		for (int i=7;i>=0;i--){
			trimval = iv - (int)Math.pow(2,i);
			if (trimval<0){
				result += "0";
				trimval = iv;
			} else {
				result += "1";
				iv = (byte)trimval;
			}
		}
		return result;
	}
	public static byte[] breakInt2Byte(int ival){
		return new byte[] {
		        (byte) (ival >> 24),
		        (byte) (ival >> 16),
		        (byte) (ival >> 8),
		        (byte) ival};
	}
	
	public static byte[] breakShort2Byte(int ival){
		return new byte[] {
		        (byte) (ival >> 8),
		        (byte) ival};
	}
	
	public static int[] readBytes2Int(int len, DataInput di) {
		// TODO Auto-generated method stub
		int[] result = new int[len];
		for (int i=0;i<len;i++){
			int info;
			try {
				info = di.readUnsignedByte();
				result[i] = info;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
