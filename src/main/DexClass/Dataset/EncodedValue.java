package main.DexClass.Dataset;

public class EncodedValue {

	private int value_type = -1;
	private int value_arg = -1;
	private int header = -1;
	private int[] value_ir = null;
	private EncodedArray value_ea = null;
	private EncodedAnnotation value_ean = null;
	
	public int getValue_arg() {
		return value_arg;
	}
	public void setValue_arg(int value_arg) {
		this.value_arg = value_arg;
	}
	public int getHeader() {
		return header;
	}
	public void setHeader(int header) {
		this.header = header;
	}
	public int getValue_type() {
		return value_type;
	}
	public void setValue_type(int value_type) {
		this.value_type = value_type;
	}
	public int[] getValue_ir() {
		return value_ir;
	}
	public void setValue_ir(int[] value_ir) {
		this.value_ir = value_ir;
	}
	public EncodedArray getValue_ea() {
		return value_ea;
	}
	public void setValue_ea(EncodedArray value_ea) {
		this.value_ea = value_ea;
	}
	public EncodedAnnotation getValue_ean() {
		return value_ean;
	}
	public void setValue_ean(EncodedAnnotation value_ean) {
		this.value_ean = value_ean;
	}
	public Object getValue(){
		Object result = null;
		if (value_type == 0x1C){
			result = value_ea;
		} else if (value_type == 0x1D){
			result = value_ean;
		} else {
			result = value_ir;
		}
		return result;
	}
	public void setValue(Object obj){
		if (obj == null){
			return;
		}
		if (value_type == 0x1C){
			value_ea = (EncodedArray)obj;
		} else if (value_type == 0x1D){
			value_ean = (EncodedAnnotation)obj;
		} else {
			value_ir = (int[])obj;
		}
	}
}
