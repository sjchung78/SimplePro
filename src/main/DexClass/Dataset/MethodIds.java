package main.DexClass.Dataset;

public class MethodIds {

	private int[] class_idx = null;
	private int[] proto_idx = null;
	private int[] name_idx = null;
	public int[] getClass_idx() {
		return class_idx;
	}
	public void setClass_idx(int[] class_idx) {
		this.class_idx = class_idx;
	}
	public int[] getProto_idx() {
		return proto_idx;
	}
	public void setProto_idx(int[] proto_idx) {
		this.proto_idx = proto_idx;
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
	public int getProto_idx(int idx){
		if (proto_idx == null || proto_idx.length < idx){
			return -1;
		} else {
			return proto_idx[idx];
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
