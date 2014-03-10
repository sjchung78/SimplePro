package main.contents;

public class Constant {
	private int iType = 0;
	private String sType = "";
	private int refVal1 = 0;
	private String refValue1 = "";
	private int refVal2 = 0;
	private String refValue2 = "";
	private byte[] bVal;
	
	public Constant(int iType, int refVal1, int refVal2) {
		super();
		this.iType = iType;
		this.refVal1 = refVal1;
		this.refVal2 = refVal2;
	}
	public Constant(int iType, int refVal1, byte[] bVal, String refValue1) {
		super();
		this.iType = iType;
		this.refVal1 = refVal1;
		this.bVal = bVal;
		this.refValue1 = refValue1;
	}
	
	public void setType()
	{
		switch (iType) {
		case 1:
			sType = "Constant_Utf8";
			break;
		case 2:
			sType = "Error";
			break;
		case 3:
			sType = "Constant_Integer";
			break;
		case 4:
			sType = "Constant_Float";
			break;
		case 5:
			sType = "Constant_Long";
			break;
		case 6:
			sType = "Constant_Double";
			break;
		case 7:
			sType = "Constant_Class";
			break;
		case 8:
			sType = "Constant_String";
			break;
		case 9:
			sType = "Constant_FieldRef";
			break;
		case 10:
			sType = "Constant_MethodRef";
			break;
		case 11:
			sType = "Constant_InterfaceMethodRef";
			break;
		case 12:
			sType = "Constant_NameAndType";
			break;
		default :
			sType = "Error";
		}
	}
	public int getiType() {
		return iType;
	}
	public void setiType(int iType) {
		this.iType = iType;
	}
	public String getsType() {
		return sType;
	}
	public void setsType(String sType) {
		this.sType = sType;
	}
	public String getRefValue1() {
		return refValue1;
	}
	public void setRefValue1(String refValue1) {
		this.refValue1 = refValue1;
	}
	public String getRefValue2() {
		return refValue2;
	}
	public void setRefValue2(String refValue2) {
		this.refValue2 = refValue2;
	}
	public int getRefVal1() {
		return refVal1;
	}
	public void setRefVal1(int refVal1) {
		this.refVal1 = refVal1;
	}
	public int getRefVal2() {
		return refVal2;
	}
	public void setRefVal2(int refVal2) {
		this.refVal2 = refVal2;
	}
	public byte[] getbVal() {
		return bVal;
	}
	public void setbVal(byte[] bVal) {
		this.bVal = bVal;
	}
	
	public String toString(){
		String result = sType+ " ";
		
		switch (iType) {
		case 1:
			result += refValue1;
			break;
		case 3:
		case 4:
		case 7:
		case 8:
			result += refVal1;
			break;
		case 5:
		case 6:
		case 9:
		case 10:
		case 11:
		case 12:
			result += (refVal1 + " " + refVal2);
			break;
		default :
			result = "False";
			break;
		}
		switch (iType){
		case 7:
		case 8:
			result += "      //"+refValue1;
			break;
		case 9:
		case 10:
		case 11:
		case 12:
			result += "      //"+refValue1;
			result += ", " + refValue2;
			break;
		}
		return result;
	}
}
