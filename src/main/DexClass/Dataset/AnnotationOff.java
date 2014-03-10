package main.DexClass.Dataset;

public class AnnotationOff {
	
	private int[] annotation_off = null;

	public int[] getAnnotation_off() {
		return annotation_off;
	}

	public void setAnnotation_off(int[] annotation_off) {
		this.annotation_off = annotation_off;
	}
	public int getAnnotation_off(int idx){
		if (annotation_off == null || annotation_off.length < idx){
			return -1;
		} else {
			return annotation_off[idx];
		}
	}
}
