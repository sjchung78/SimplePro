package main.DexClass.Dataset;

public class MapList {

	private int size = 0;
	private MapItem[] mapItem = null;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public MapItem[] getMapItem() {
		return mapItem;
	}
	public void setMapItem(MapItem[] mapItem) {
		this.mapItem = mapItem;
	}
	public MapItem getMapItem(int idx){
		if (mapItem == null || mapItem.length < idx){
			return new MapItem();
		} else {
			return mapItem[idx];
		}
	}
	public String toString(){
		String result = "MapList. Size:["+size+"]\n";
		for (int i=0;i<size;i++){
			result += "["+(i+1)+"] "+mapItem[i].toString();
		}
		return result;
	}
	
}
