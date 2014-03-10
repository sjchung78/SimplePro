package main.DexClass.Dataset;

public class TypeList {

	private int[] size = null;
	private TypeItem[] list = null;
	private int[] offset = null;
	
	public int[] getOffset() {
		return offset;
	}
	public void setOffset(int[] offset) {
		this.offset = offset;
	}
	public int[] getSize() {
		return size;
	}
	public void setSize(int[] size) {
		this.size = size;
	}
	public TypeItem[] getList() {
		return list;
	}
	public void setList(TypeItem[] list) {
		this.list = list;
	}
	public int getSize(int idx){
		if (size == null || size.length < idx){
			return -1;
		} else {
			return size[idx];
		}
	}
	public int getOffset(int idx){
		if (offset == null || offset.length < idx){
			return -1;
		} else {
			return offset[idx];
		}
	}
	public TypeItem getList(int idx){
		if (list == null || list.length < idx){
			return new TypeItem();
		} else {
			return list[idx];
		}
	}
}
