package main.DexClass.Dataset;

public class AnnotationSetRefItem {

	private int[] annotations_off = null;

	public int[] getAnnotations_off() {
		return annotations_off;
	}

	public void setAnnotations_off(int[] annotations_off) {
		this.annotations_off = annotations_off;
	}
	public int getAnnotations_off(int idx){
		if (annotations_off == null || annotations_off.length < idx){
			return -1;
		} else {
			return annotations_off[idx];
		}
	}
}
