package main.DexClass.Dataset;

public class TryItem {

	private int start_addr = 0;
	private int insn_count = 0;
	private int handler_off = 0;
	public int getStart_addr() {
		return start_addr;
	}
	public void setStart_addr(int start_addr) {
		this.start_addr = start_addr;
	}
	public int getInsn_count() {
		return insn_count;
	}
	public void setInsn_count(int insn_count) {
		this.insn_count = insn_count;
	}
	public int getHandler_off() {
		return handler_off;
	}
	public void setHandler_off(int handler_off) {
		this.handler_off = handler_off;
	}
	
}
