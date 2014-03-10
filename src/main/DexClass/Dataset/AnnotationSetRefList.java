package main.DexClass.Dataset;

public class AnnotationSetRefList {

	private int[] size = null;
	private int[] offset = null;
	private AnnotationSetRefItem[] asri = null;
	
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
	public AnnotationSetRefItem[] getAsri() {
		return asri;
	}
	public void setAsri(AnnotationSetRefItem[] asri) {
		this.asri = asri;
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
	public AnnotationSetRefItem getAsri(int idx){
		if (asri == null || asri.length < idx){
			return new AnnotationSetRefItem();
		} else {
			return asri[idx];
		}
	}
}
