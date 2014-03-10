package main.DexClass.Dataset;

public class AnnotationDirectoryItem {

	private int class_annotations_off = 0;
	private int fields_size = 0;
	private int annotated_method_size = 0;
	private int annotated_parameters_size = 0;
	private Annotation[] field_annotations = null;
	private Annotation[] method_annotations = null;
	private Annotation[] parameter_annotations = null;
	public int getClass_annotations_off() {
		return class_annotations_off;
	}
	public void setClass_annotations_off(int class_annotations_off) {
		this.class_annotations_off = class_annotations_off;
	}
	public int getFields_size() {
		return fields_size;
	}
	public void setFields_size(int fields_size) {
		this.fields_size = fields_size;
	}
	public int getAnnotated_method_size() {
		return annotated_method_size;
	}
	public void setAnnotated_method_size(int annotated_method_size) {
		this.annotated_method_size = annotated_method_size;
	}
	public int getAnnotated_parameters_size() {
		return annotated_parameters_size;
	}
	public void setAnnotated_parameters_size(int annotated_parameters_size) {
		this.annotated_parameters_size = annotated_parameters_size;
	}
	public Annotation[] getField_annotations() {
		return field_annotations;
	}
	public void setField_annotations(Annotation[] field_annotations) {
		this.field_annotations = field_annotations;
	}
	public Annotation[] getMethod_annotations() {
		return method_annotations;
	}
	public void setMethod_annotations(Annotation[] method_annotations) {
		this.method_annotations = method_annotations;
	}
	public Annotation[] getParameter_annotations() {
		return parameter_annotations;
	}
	public void setParameter_annotations(Annotation[] parameter_annotations) {
		this.parameter_annotations = parameter_annotations;
	}
}
