package main.DexClass.Dataset;

public class ProtoIds {

	private int[] shorty_idx = null;
	private int[] return_type_idx = null;
	private int[] parameters_off = null;
	public int[] getShorty_idx() {
		return shorty_idx;
	}
	public void setShorty_idx(int[] shorty_idx) {
		this.shorty_idx = shorty_idx;
	}
	public int[] getReturn_type_idx() {
		return return_type_idx;
	}
	public void setReturn_type_idx(int[] return_type_idx) {
		this.return_type_idx = return_type_idx;
	}
	public int[] getParameters_off() {
		return parameters_off;
	}
	public void setParameters_off(int[] parameters_off) {
		this.parameters_off = parameters_off;
	}
	
	public int getShorty_idx(int idx){
		if (shorty_idx == null || shorty_idx.length < idx){
			return -1;
		} else {
			return shorty_idx[idx];
		}
	}
	public int getReturn_type_idx(int idx){
		if (return_type_idx == null || return_type_idx.length < idx){
			return -1;
		} else {
			return return_type_idx[idx];
		}
	}
	public int getParameters_off(int idx){
		if (parameters_off == null || parameters_off.length < idx){
			return -1;
		} else {
			return parameters_off[idx];
		}
	}
}
