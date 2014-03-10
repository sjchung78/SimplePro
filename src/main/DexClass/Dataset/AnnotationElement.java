package main.DexClass.Dataset;


public class AnnotationElement {

	private int name_idx = 0;
	private EncodedValue value = null;
	public int getName_idx() {
		return name_idx;
	}
	public void setName_idx(int name_idx) {
		this.name_idx = name_idx;
	}
	public EncodedValue getValue() {
		return value;
	}
	public void setValue(EncodedValue value) {
		this.value = value;
	}
}
