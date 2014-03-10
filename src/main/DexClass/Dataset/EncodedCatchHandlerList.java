package main.DexClass.Dataset;

public class EncodedCatchHandlerList {

	private int size = 0;
	private EncodedCatchHandler[] ech = null;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public EncodedCatchHandler[] getEch() {
		return ech;
	}
	public void setEch(EncodedCatchHandler[] ech) {
		this.ech = ech;
	}
	public EncodedCatchHandler getEch(int idx){
		if (ech == null || ech.length < idx){
			return new EncodedCatchHandler();
		} else {
			return ech[idx];
		}
	}
}
