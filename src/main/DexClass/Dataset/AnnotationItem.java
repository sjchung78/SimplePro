package main.DexClass.Dataset;


public class AnnotationItem {

	private int visibility = -1;
	private EncodedAnnotation annotation = null;
	public int getVisibility() {
		return visibility;
	}
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	public EncodedAnnotation getAnnotation() {
		return annotation;
	}
	public void setAnnotation(EncodedAnnotation annotation) {
		this.annotation = annotation;
	}
}
