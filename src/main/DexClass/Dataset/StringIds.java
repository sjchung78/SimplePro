package main.DexClass.Dataset;

public class StringIds {

	private int[] string_data_off = null;
	private String[] string_id_data = null;

	public int[] getString_data_off() {
		return string_data_off;
	}

	public void setString_data_off(int[] string_data_off) {
		this.string_data_off = string_data_off;
	}
	
	public String[] getString_id_data() {
		return string_id_data;
	}

	public void setString_id_data(String[] string_id_data) {
		this.string_id_data = string_id_data;
	}

	public int getString_data_off(int idx){
		if (string_data_off == null || string_data_off.length < idx){
			return -1;
		} else {
			return string_data_off[idx];
		}
	}
	
	public String getString_id_data(int idx){
		if (string_id_data == null || string_id_data.length < idx){
			return "";
		} else {
			return string_id_data[idx];
		}
	}
}
