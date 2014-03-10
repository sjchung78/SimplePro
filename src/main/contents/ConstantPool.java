package main.contents;


public class ConstantPool {
	public Constant[] constant;
	private int lastIndex = 0;
	
	public ConstantPool(int num)
	{
		constant = new Constant[num];
	}
	
	public boolean add(Constant con){
		boolean result = false;
		
		if (constant[lastIndex] != null){
			System.out.println("Error");
		}
		
		constant[lastIndex++] = con;
		
		result = true;
		return result;
	}
	
	public boolean del(int index){
		boolean result = false;
		
		for (int i= index;i<lastIndex;i++){
			constant[i] = constant[i+1];
		}
		constant[lastIndex--] = null;
		
		result = true;
		return result;
	}
	
	public Constant get(int index){
		return constant[index];
	}
	
	public void setting(){
		for (int i=0;i<constant.length;i++){
			constant[i].setType();
			this.setVals(constant[i]);
		}
	}
	
	private void setVals(Constant con){
		if (con.getiType() == 7 || con.getiType() == 8){
			String refVal = getRealVal(constant[con.getRefVal1()-1], constant[con.getRefVal1()-1].getiType(), 0);
			con.setRefValue1(refVal);
		} else if (con.getiType() == 9 || con.getiType() == 10 || con.getiType() == 11 || con.getiType() == 12){
			String refVal1 = getRealVal(constant[con.getRefVal1()-1], constant[con.getRefVal1()-1].getiType(), 0);
			String refVal2 = getRealVal(constant[con.getRefVal2()-1], constant[con.getRefVal2()-1].getiType(), 1);
			con.setRefValue1(refVal1);
			con.setRefValue2(refVal2);
		}
	}
	
	private String getRealVal(Constant con, int type, int index){
		String result = "";
		
		if (type == 7||type ==8){
			result += getRealVal(constant[con.getRefVal1()-1], constant[con.getRefVal1()-1].getiType(), 2);
		} else if( type==9||type==10||type==11||type==12){
			result += getRealVal(constant[con.getRefVal1()-1], constant[con.getRefVal1()-1].getiType(), 2);
			result += getRealVal(constant[con.getRefVal2()-1], constant[con.getRefVal2()-1].getiType(), 2);
		} else {
			result += con.getRefValue1();
			result += con.getRefValue2();
		}
		return result;
	}
	
	public String toString(){
		String result = "";
		for (int i=0;i<lastIndex;i++){
			result += "  Const["+(i+1)+"] " + constant[i].toString() + "\n";
		}
		return result;
	}
}
