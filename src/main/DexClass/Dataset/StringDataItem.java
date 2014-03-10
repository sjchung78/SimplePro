package main.DexClass.Dataset;

public class StringDataItem {

	private int[] utf16_size = null;
	private byte[][] data = null;
	private String[] dataS = null;
	public int[] getUtf16_size() {
		return utf16_size;
	}
	public int getUtf16_size(int idx){
		if (utf16_size == null || utf16_size.length < idx){
			return -1;
		} else {
			return utf16_size[idx];
		}
	}
	public void setUtf16_size(int[] utf16_size) {
		this.utf16_size = utf16_size;
	}
	public byte[][] getData() {
		return data;
	}
	public byte[] getData(int idx){
		if (data == null || data.length < idx){
			return null;
		} else {
			return data[idx];
		}
	}
	public void setData(byte[][] data) {
		this.data = data;
	}
	public String[] getDataS() {
		return dataS;
	}
	public String getDataS(int idx){
		if (dataS == null || dataS.length < idx){
			return "";
		} else {
			return dataS[idx];
		}
	}
	public void setDataS(String[] dataS) {
		this.dataS = dataS;
	}
	public String toString(){
		String result = "";
		for (int i=0;i<dataS.length;i++){
			result += dataS[i]+"\n";
		}
		return result;
	}
}
