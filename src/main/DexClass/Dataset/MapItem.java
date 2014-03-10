package main.DexClass.Dataset;

import utils.ClassConstants;

public class MapItem {

	private int type = 0;
	private int size = 0;
	private int offset = 0;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String toString(){
		String ssize = (size<10?("   "+size):(size<100?("  "+size):(size<1000?" "+size:(""+size))));
		String stype = (type<10?("   "+type):(type<100?("  "+type):(type<1000?" "+type:(""+type))));
		String soff = (offset<10?("   "+offset):(offset<100?("  "+offset):(offset<1000?" "+offset:(""+offset))));
		return "MapItem:\tSize["+ssize+"]\tOffset["+soff+"]\tType["+stype+"]\tDesc["+typeDesc(type)+"]\n";
	}
	private String typeDesc(int type){
		String result = "";
		switch (type){
		case ClassConstants.TYPE_HEADER_ITEM	              : 
			result = "TYPE_HEADER_ITEM	             "; 
			break; 
		case ClassConstants.TYPE_STRING_ID_ITEM	          : 
			result = "TYPE_STRING_ID_ITEM	           "; 
			break;   
		case ClassConstants.TYPE_TYPE_ID_ITEM	            : 
			result =  "TYPE_TYPE_ID_ITEM	             "; 
			break;   
		case ClassConstants.TYPE_PROTO_ID_ITEM	            : 
			result =  "TYPE_PROTO_ID_ITEM	           "; 
			break; 
		case ClassConstants.TYPE_FIELD_ID_ITEM	            : 
			result =  "TYPE_FIELD_ID_ITEM	           "; 
			break; 
		case ClassConstants.TYPE_METHOD_ID_ITEM	          : 
			result =  "TYPE_METHOD_ID_ITEM	           "; 
			break;   
		case ClassConstants.TYPE_CLASS_DEF_ITEM	          : 
			result =  "TYPE_CLASS_DEF_ITEM	           "; 
			break;   
		case ClassConstants.TYPE_MAP_LIST	                : 
			result =  "TYPE_MAP_LIST	                 "; 
			break;   
		case ClassConstants.TYPE_TYPE_LIST	                : 
			result =  "TYPE_TYPE_LIST	               "; 
			break; 
		case ClassConstants.TYPE_ANNOTATION_SET_REF_LIST	  : 
			result =  "TYPE_ANNOTATION_SET_REF_LIST	 "; 
			break; 
		case ClassConstants.TYPE_ANNOTATION_SET_ITEM	      : 
			result =  "TYPE_ANNOTATION_SET_ITEM	     "; 
			break; 
		case ClassConstants.TYPE_CLASS_DATA_ITEM	          : 
			result =  "TYPE_CLASS_DATA_ITEM	         "; 
			break; 
		case ClassConstants.TYPE_CODE_ITEM	                : 
			result =  "TYPE_CODE_ITEM	               "; 
			break; 
		case ClassConstants.TYPE_STRING_DATA_ITEM	        : 
			result =  "TYPE_STRING_DATA_ITEM	         "; 
			break;   
		case ClassConstants.TYPE_DEBUG_INFO_ITEM	          : 
			result =  "TYPE_DEBUG_INFO_ITEM	         "; 
			break; 
		case ClassConstants.TYPE_ANNOTATION_ITEM	          : 
			result =  "TYPE_ANNOTATION_ITEM	         "; 
			break; 
		case ClassConstants.TYPE_ENCODED_ARRAY_ITEM	      : 
			result =  "TYPE_ENCODED_ARRAY_ITEM	       "; 
			break;   
		case ClassConstants.TYPE_ANNOTATIONS_DIRECTORY_ITEM: 
			result =  "TYPE_ANNOTATIONS_DIRECTORY_ITEM"; 
			break; 	
		}
		if (!result.equals("")){
			result = result.trim();
		}
		return result;
	}
}
