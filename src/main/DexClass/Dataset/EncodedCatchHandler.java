package main.DexClass.Dataset;

public class EncodedCatchHandler {

	private int size = 0;
	private EncodedTypeAddrPair[] etap = null;
	private int catchAllAddr = 0;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public EncodedTypeAddrPair[] getEtap() {
		return etap;
	}
	public void setEtap(EncodedTypeAddrPair[] etap) {
		this.etap = etap;
	}
	public int getCatchAllAddr() {
		return catchAllAddr;
	}
	public void setCatchAllAddr(int catchAllAddr) {
		this.catchAllAddr = catchAllAddr;
	}
	public EncodedTypeAddrPair getEtap(int idx){
		if (etap == null || etap.length < idx){
			return new EncodedTypeAddrPair();
		} else {
			return etap[idx];
		}
	}
}
