package main.DexClass.Dataset;

public class Header {

	private byte[] magic = new byte[8];
	private int checksum         ;
	private byte[] signature = new byte[20];
	private int file_size        ;
	private int header_size      ;
	private int endian_tag       ;
	private int link_size        ;
	private int link_off         ;
	private int map_off          ;
	private int string_ids_size  ;
	private int string_ids_off   ;
	private int type_ids_size    ;
	private int type_ids_off     ;
	private int proto_ids_size   ;
	private int proto_ids_off    ;
	private int field_ids_size   ;
	private int field_ids_off    ;
	private int method_ids_size  ;
	private int method_ids_off   ;
	private int class_defs_size  ;
	private int class_defs_off   ;
	private int data_size        ;
	private int data_off         ;
	
	private String file_name;

	public byte[] getMagic() {
		return magic;
	}

	public void setMagic(byte[] magic) {
		this.magic = magic;
	}

	public int getChecksum() {
		return checksum;
	}

	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public int getFile_size() {
		return file_size;
	}

	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}

	public int getHeader_size() {
		return header_size;
	}

	public void setHeader_size(int header_size) {
		this.header_size = header_size;
	}

	public int getEndian_tag() {
		return endian_tag;
	}

	public void setEndian_tag(int endian_tag) {
		this.endian_tag = endian_tag;
	}

	public int getLink_size() {
		return link_size;
	}

	public void setLink_size(int link_size) {
		this.link_size = link_size;
	}

	public int getLink_off() {
		return link_off;
	}

	public void setLink_off(int link_off) {
		this.link_off = link_off;
	}

	public int getMap_off() {
		return map_off;
	}

	public void setMap_off(int map_off) {
		this.map_off = map_off;
	}

	public int getString_ids_size() {
		return string_ids_size;
	}

	public void setString_ids_size(int string_ids_size) {
		this.string_ids_size = string_ids_size;
	}

	public int getString_ids_off() {
		return string_ids_off;
	}

	public void setString_ids_off(int string_ids_off) {
		this.string_ids_off = string_ids_off;
	}

	public int getType_ids_size() {
		return type_ids_size;
	}

	public void setType_ids_size(int type_ids_size) {
		this.type_ids_size = type_ids_size;
	}

	public int getType_ids_off() {
		return type_ids_off;
	}

	public void setType_ids_off(int type_ids_off) {
		this.type_ids_off = type_ids_off;
	}

	public int getProto_ids_size() {
		return proto_ids_size;
	}

	public void setProto_ids_size(int proto_ids_size) {
		this.proto_ids_size = proto_ids_size;
	}

	public int getProto_ids_off() {
		return proto_ids_off;
	}

	public void setProto_ids_off(int proto_ids_off) {
		this.proto_ids_off = proto_ids_off;
	}

	public int getField_ids_size() {
		return field_ids_size;
	}

	public void setField_ids_size(int field_ids_size) {
		this.field_ids_size = field_ids_size;
	}

	public int getField_ids_off() {
		return field_ids_off;
	}

	public void setField_ids_off(int field_ids_off) {
		this.field_ids_off = field_ids_off;
	}

	public int getMethod_ids_size() {
		return method_ids_size;
	}

	public void setMethod_ids_size(int method_ids_size) {
		this.method_ids_size = method_ids_size;
	}

	public int getMethod_ids_off() {
		return method_ids_off;
	}

	public void setMethod_ids_off(int method_ids_off) {
		this.method_ids_off = method_ids_off;
	}

	public int getClass_defs_size() {
		return class_defs_size;
	}

	public void setClass_defs_size(int class_defs_size) {
		this.class_defs_size = class_defs_size;
	}

	public int getClass_defs_off() {
		return class_defs_off;
	}

	public void setClass_defs_off(int class_defs_off) {
		this.class_defs_off = class_defs_off;
	}

	public int getData_size() {
		return data_size;
	}

	public void setData_size(int data_size) {
		this.data_size = data_size;
	}

	public int getData_off() {
		return data_off;
	}

	public void setData_off(int data_off) {
		this.data_off = data_off;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
}
