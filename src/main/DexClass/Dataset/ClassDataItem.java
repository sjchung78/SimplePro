package main.DexClass.Dataset;


public class ClassDataItem {

	private int[] static_fields_size = null;
	private int[] instance_fields_size = null;
	private int[] direct_methods_size = null;
	private int[] virtual_methods_size = null;
	private EncodedField[][] static_field = null;
	private EncodedField[][] instance_field = null;  
	private EncodedMethod[][] direct_methods = null;
	private EncodedMethod[][] virtual_methods = null;
	public int[] getStatic_fields_size() {
		return static_fields_size;
	}
	public int getStatic_fields_size(int idx){
		if (static_fields_size == null || static_fields_size.length < idx){
			return -1;
		} else {
			return static_fields_size[idx];
		}
	}
	public void setStatic_fields_size(int[] static_fields_size) {
		this.static_fields_size = static_fields_size;
	}
	public int[] getInstance_fields_size() {
		return instance_fields_size;
	}
	public int getInstance_fields_size(int idx){
		if (instance_fields_size == null || instance_fields_size.length < idx){
			return -1;
		} else {
			return instance_fields_size[idx];
		}
	}
	public void setInstance_fields_size(int[] instance_fields_size) {
		this.instance_fields_size = instance_fields_size;
	}
	public int[] getDirect_methods_size() {
		return direct_methods_size;
	}
	public int getDirect_methods_size(int idx){
		if (direct_methods_size == null || direct_methods_size.length < idx){
			return -1;
		} else {
			return direct_methods_size[idx];
		}
	}
	public void setDirect_methods_size(int[] direct_methods_size) {
		this.direct_methods_size = direct_methods_size;
	}
	public int[] getVirtual_methods_size() {
		return virtual_methods_size;
	}
	public int getVirtual_methods_size(int idx){
		if (virtual_methods_size == null || virtual_methods_size.length < idx){
			return -1;
		} else {
			return virtual_methods_size[idx];
		}
	}
	public void setVirtual_methods_size(int[] virtual_methods_size) {
		this.virtual_methods_size = virtual_methods_size;
	}
	public EncodedField[][] getStatic_field() {
		return static_field;
	}
	public EncodedField[] getStatic_field(int idx){
		if (static_field == null || static_field.length < idx){
			return null;
		} else {
			return static_field[idx];
		}
	}
	public void setStatic_field(EncodedField[][] static_field) {
		this.static_field = static_field;
	}
	public EncodedField[][] getInstance_field() {
		return instance_field;
	}
	public EncodedField[] getInstance_field(int idx){
		if (instance_field == null || instance_field.length < idx){
			return null;
		} else {
			return instance_field[idx];
		}
	}
	public void setInstance_field(EncodedField[][] instance_field) {
		this.instance_field = instance_field;
	}
	public EncodedMethod[][] getDirect_methods() {
		return direct_methods;
	}
	public EncodedMethod[] getDirect_methods(int idx){
		if (direct_methods == null || direct_methods.length < idx){
			return null;
		} else {
			return direct_methods[idx];
		}
	}
	public void setDirect_methods(EncodedMethod[][] direct_methods) {
		this.direct_methods = direct_methods;
	}
	public EncodedMethod[][] getVirtual_methods() {
		return virtual_methods;
	}
	public EncodedMethod[] getVirtual_methods(int idx){
		if (virtual_methods == null || virtual_methods.length < idx){
			return null;
		} else {
			return virtual_methods[idx];
		}
	}
	public void setVirtual_methods(EncodedMethod[][] virtual_methods) {
		this.virtual_methods = virtual_methods;
	}
	
}
