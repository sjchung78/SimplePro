package main.DexClass.Dataset;


public class EncodedAnnotation {

	private int type_idx = 0;
	private int size = 0;
	private AnnotationElement[] elements = null;
	public int getType_idx() {
		return type_idx;
	}
	public void setType_idx(int type_idx) {
		this.type_idx = type_idx;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public AnnotationElement[] getElements() {
		return elements;
	}
	public void setElements(AnnotationElement[] elements) {
		this.elements = elements;
	}
}
