package main.DexClass.Dataset;

public class AnnotationSetItem {

	private int[] size = null;
	private int[] offset = null;
	private AnnotationOff[] ao = null;
	
	public int[] getOffset() {
		return offset;
	}
	public void setOffset(int[] offset) {
		this.offset = offset;
	}
	public int[] getSize() {
		return size;
	}
	public void setSize(int[] size) {
		this.size = size;
	}
	public AnnotationOff[] getAo() {
		return ao;
	}
	public void setAo(AnnotationOff[] ao) {
		this.ao = ao;
	}
	public int getSize(int idx){
		if (size == null || size.length < idx){
			return -1;
		} else {
			return size[idx];
		}
	}
	public int getOffset(int idx){
		if (offset == null || offset.length < idx){
			return -1;
		} else {
			return offset[idx];
		}
	}
	public AnnotationOff getAo(int idx){
		if (ao == null || ao.length < idx){
			return new AnnotationOff();
		} else {
			return ao[idx];
		}
	}
}
