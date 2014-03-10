package main.DexClass.Dataset;

public class TypeIds {

	private int[] descriptor_idx = null;

	public int[] getDescriptor_idx() {
		return descriptor_idx;
	}

	public void setDescriptor_idx(int[] descriptor_idx) {
		this.descriptor_idx = descriptor_idx;
	}
	
	public int getDescriptor_idx(int idx){
		if (descriptor_idx == null || descriptor_idx.length < idx){
			return -1;
		} else {
			return descriptor_idx[idx];
		}
	}
}
