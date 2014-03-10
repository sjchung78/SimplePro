package main.DexClass.Dataset;

public class TypeItem {

	private int[] type_idx = null;

	public int[] getType_idx() {
		return type_idx;
	}

	public void setType_idx(int[] type_idx) {
		this.type_idx = type_idx;
	}
	
	public int getType_idx(int idx){
		if (type_idx == null || type_idx.length < idx){
			return -1;
		} else {
			return type_idx[idx];
		}
	}
}
