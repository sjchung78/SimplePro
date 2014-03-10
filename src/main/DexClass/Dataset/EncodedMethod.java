package main.DexClass.Dataset;

public class EncodedMethod {

	private int method_idx_diff = 0;
	private int access_flags = 0;
	private int code_off = 0;
	public int getMethod_idx_diff() {
		return method_idx_diff;
	}
	public void setMethod_idx_diff(int method_idx_diff) {
		this.method_idx_diff = method_idx_diff;
	}
	public int getAccess_flags() {
		return access_flags;
	}
	public void setAccess_flags(int access_flags) {
		this.access_flags = access_flags;
	}
	public int getCode_off() {
		return code_off;
	}
	public void setCode_off(int code_off) {
		this.code_off = code_off;
	}
	
}
