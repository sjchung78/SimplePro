package main.DexClass.Dataset;

public class FieldIds {

	private int[] class_idx = null;
	private int[] type_idx = null;
	private int[] name_idx = null;
	public int[] getClass_idx() {
		return class_idx;
	}
	public void setClass_idx(int[] class_idx) {
		this.class_idx = class_idx;
	}
	public int[] getType_idx() {
		return type_idx;
	}
	public void setType_idx(int[] type_idx) {
		this.type_idx = type_idx;
	}
	public int[] getName_idx() {
		return name_idx;
	}
	public void setName_idx(int[] name_idx) {
		this.name_idx = name_idx;
	}
	public int getClass_idx(int idx){
		if (class_idx == null || class_idx.length < idx){
			return -1;
		} else {
			return class_idx[idx];
		}
	}
	public int getType_idx(int idx){
		if (type_idx == null || type_idx.length < idx){
			return -1;
		} else {
			return type_idx[idx];
		}
	}
	public int getName_idx(int idx){
		if (name_idx == null || name_idx.length < idx){
			return -1;
		} else {
			return name_idx[idx];
		}
	}
}
