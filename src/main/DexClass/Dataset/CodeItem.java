package main.DexClass.Dataset;

import java.util.*;

public class CodeItem {
	//private List li = new List();
	private int[] registers_size = null;
	private int[] ins_size = null;
	private int[] outs_size = null;
	private int[] tries_size = null;
	private int[] debug_info_off = null;
	private int[] insns_size = null;
	private int[][] insns = null;
	private int[] padding = null;
	private TryItem[][] tries = null;
	private EncodedCatchHandlerList[] echl = null;
	public int[] getInsns(int idx){
		if (insns == null || insns.length < idx){
			return null;
		} else {
			return insns[idx];
		}
	}
	public TryItem[] getTries(int idx){
		if (tries == null || tries.length < idx){
			return null;
		} else {
			return tries[idx];
		}
	}
	public int[] getRegisters_size() {
		return registers_size;
	}
	public void setRegisters_size(int[] registers_size) {
		this.registers_size = registers_size;
	}
	public int[] getIns_size() {
		return ins_size;
	}
	public void setIns_size(int[] ins_size) {
		this.ins_size = ins_size;
	}
	public int[] getOuts_size() {
		return outs_size;
	}
	public void setOuts_size(int[] outs_size) {
		this.outs_size = outs_size;
	}
	public int[] getTries_size() {
		return tries_size;
	}
	public void setTries_size(int[] tries_size) {
		this.tries_size = tries_size;
	}
	public int[] getDebug_info_off() {
		return debug_info_off;
	}
	public void setDebug_info_off(int[] debug_info_off) {
		this.debug_info_off = debug_info_off;
	}
	public int[] getInsns_size() {
		return insns_size;
	}
	public void setInsns_size(int[] insns_size) {
		this.insns_size = insns_size;
	}
	public int[][] getInsns() {
		return insns;
	}
	public void setInsns(int[][] insns) {
		this.insns = insns;
	}
	public int[] getPadding() {
		return padding;
	}
	public void setPadding(int[] padding) {
		this.padding = padding;
	}
	public TryItem[][] getTries() {
		return tries;
	}
	public void setTries(TryItem[][] tries) {
		this.tries = tries;
	}
	public EncodedCatchHandlerList[] getEchl() {
		return echl;
	}
	public void setEchl(EncodedCatchHandlerList[] echl) {
		this.echl = echl;
	}
	
}
