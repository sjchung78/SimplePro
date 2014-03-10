package main.DexClass;

import handler.LogHandler;

import java.io.BufferedInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import main.ClassBody;
import main.DexClass.Dataset.Annotation;
import main.DexClass.Dataset.AnnotationDirectoryItem;
import main.DexClass.Dataset.AnnotationElement;
import main.DexClass.Dataset.AnnotationItem;
import main.DexClass.Dataset.AnnotationOff;
import main.DexClass.Dataset.AnnotationSetItem;
import main.DexClass.Dataset.AnnotationSetRefItem;
import main.DexClass.Dataset.AnnotationSetRefList;
import main.DexClass.Dataset.ByteCode;
import main.DexClass.Dataset.ClassDataItem;
import main.DexClass.Dataset.ClassDefs;
import main.DexClass.Dataset.CodeItem;
import main.DexClass.Dataset.DebugInfoItem;
import main.DexClass.Dataset.EncodedAnnotation;
import main.DexClass.Dataset.EncodedArray;
import main.DexClass.Dataset.EncodedCatchHandler;
import main.DexClass.Dataset.EncodedCatchHandlerList;
import main.DexClass.Dataset.EncodedField;
import main.DexClass.Dataset.EncodedMethod;
import main.DexClass.Dataset.EncodedTypeAddrPair;
import main.DexClass.Dataset.EncodedValue;
import main.DexClass.Dataset.FieldIds;
import main.DexClass.Dataset.Header;
import main.DexClass.Dataset.MapItem;
import main.DexClass.Dataset.MapList;
import main.DexClass.Dataset.MethodIds;
import main.DexClass.Dataset.ProtoIds;
import main.DexClass.Dataset.StringDataItem;
import main.DexClass.Dataset.StringIds;
import main.DexClass.Dataset.TryItem;
import main.DexClass.Dataset.TypeIds;
import main.DexClass.Dataset.TypeItem;
import main.DexClass.Dataset.TypeList;
import utils.ClassConstants;
import utils.Utils;

public class DexClassBody implements ClassBody {

	private LogHandler logger = null;
	private DataInput dataInput;
	private int index = 8;
	int idx = 0;
	private Header header;
	private StringIds string_ids = new StringIds();
	private TypeIds type_ids = new TypeIds();
	private ProtoIds proto_ids = new ProtoIds();
	private FieldIds field_ids = new FieldIds();
	private MethodIds method_ids = new MethodIds();
	private ClassDefs class_defs = new ClassDefs();
	private MapList maplist = new MapList();
	private TypeList typeList = new TypeList();
	private AnnotationSetRefList asrl = new AnnotationSetRefList();
	private AnnotationSetItem asi = new AnnotationSetItem();
	private ClassDataItem cdi = new ClassDataItem();
	private CodeItem codeItem = new CodeItem();
	private StringDataItem sdi = new StringDataItem();
	private DebugInfoItem[] dii = null;
	private AnnotationItem[] ai = null;
	private EncodedArray[] ear = null;
	private AnnotationDirectoryItem[] adi = null;
	private String logg = "";
	
	public DexClassBody(int u4magic, int u4magic2, LogHandler logger) {
		// TODO Auto-generated constructor stub
		header = new Header();
		byte[] m1 = Utils.breakInt2Byte(u4magic);
		byte[] m2 = Utils.breakInt2Byte(u4magic2);
		byte[] magic = new byte[8];
		for (int i=0;i<8;i++){
			if (i<4)
				magic[i] = m1[i];
			else
				magic[i] = m2[i-4];
		}
		header.setMagic(magic);
		this.logger = logger;
	}
	public DexClassBody() {
		// TODO Auto-generated constructor stub
		header = new Header();
	}
	private void setNewDI() {
		// TODO Auto-generated method stub
		try{
			dataInput = null;
			InputStream inputStream = new BufferedInputStream(new FileInputStream(header.getFile_name()));
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			dataInput = dataInputStream;
			index = 0;
		} catch (Exception e){
			
		}
	}
	private void skip(int b){
		if (b<=0) return;
		try {
			dataInput.skipBytes(b);
			index += b;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private int readULEB(){
		// TODO Auto-generated method stub
		byte[] bA = new byte[5];
		byte bVal;
		boolean cont = true;
		int howmany = 0;
		
		try {
			for (int i=0;i<bA.length;i++){
				int info;
				if (!cont) break;
				info = dataInput.readUnsignedByte();
				index += 1; idx += 1;
				bA[i] = info>128?(byte)(info-128):(byte)info;
				bVal = (byte) (info >> 7);
				if (bVal == 0) {
					cont = false;
					howmany = i+1;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result = Utils.convULEBint(bA, howmany);
		return result;
	}
	private int readSLEB(){
		// TODO Auto-generated method stub
		byte[] bA = new byte[5];
		byte bVal;
		boolean cont = true;
		boolean sign = true;
		int howmany = 0;
		
		for (int i=0;i<bA.length;i++){
			int info;
			try {
				if (!cont) break;
				info = dataInput.readUnsignedByte();
				index += 1; idx += 1;
				bA[i] = info>=128?(byte)(info-128):(byte)info;
				bVal = (byte) (info >> 7);
				if (bVal == 0) {
					if (bA[i]>=64){
						bA[i] = (byte)(bA[i]-64);
						sign = false;
					}
					cont = false;
					howmany = i+1;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int result = Utils.convSLEBint(bA, howmany, sign);
		// 음수 양수 2의 보수를 넣어서 계산해야 함...
		return result;
	}
	private int readInt(){
		int result;
		try {
			result = Utils.endianInt(dataInput.readInt());
			index += 4;
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	private int readShort(){
		int result;
		try {
			result = Utils.endianShort(dataInput.readUnsignedShort());
			index += 2;
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	private int readByte(){
		int result;
		try {
			result = dataInput.readUnsignedByte();
			index += 1;
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public void execute(String file, DataInput di) {
		// TODO Auto-generated method stub
		dataInput = di;
		
		try {
			header.setFile_name(file);
			readHeader();
			
			readStringIds();
    		readTypeIds();
    		readProtoIds();
    		readFieldIds();
    		readMethodIds();
    		readClassDefs();
    		
    		setNewDI();
    		//Map List
//    		show(header.getMap_off()-index);
    		skip(header.getMap_off()-index);    		
    		readMapList();
    		setNewDI();
    		
    		int cur = 0;
//    		for (int i=0;i<maplist.getMapItem().length;i++){
//    			if (maplist.getMapItem()[i].getType() == ClassConstants.TYPE_MAP_LIST){
//    				cur = i+1;
//    				break;
//    			}
//    		}
    		
    		for (int i=0;i<maplist.getMapItem().length-cur;i++){
    			int ofs = i+cur;
    			switch (maplist.getMapItem()[i+cur].getType()){
    			case ClassConstants.TYPE_TYPE_LIST	                :
//    				show(maplist.getMapItem()[i+cur].getOffset()-index);
    				skip(maplist.getMapItem()[i+cur].getOffset()-index);
        			readTypeList(ofs);
    				break;
    			case ClassConstants.TYPE_ANNOTATION_SET_REF_LIST	  :
//    				show(maplist.getMapItem()[i+cur].getOffset()-index);
    				skip(maplist.getMapItem()[i+cur].getOffset()-index);
        			readAnnotSetRef(ofs);
    				break;
    			case ClassConstants.TYPE_ANNOTATION_SET_ITEM	      :
//    				show(maplist.getMapItem()[i+cur].getOffset()-index);
    				skip(maplist.getMapItem()[i+cur].getOffset()-index);
        			readAnnotSet(ofs);
    				break;
    			case ClassConstants.TYPE_CLASS_DATA_ITEM	          :
//    				show(maplist.getMapItem()[i+cur].getOffset()-index);
    				skip(maplist.getMapItem()[i+cur].getOffset()-index);
        			readClassData(ofs);
    				break;
    			case ClassConstants.TYPE_CODE_ITEM	                :
//    				show(maplist.getMapItem()[i+cur].getOffset()-index);
    				skip(maplist.getMapItem()[i+cur].getOffset()-index);
        			readCode(ofs);
    				break;
    			case ClassConstants.TYPE_STRING_DATA_ITEM	          :
//    				show(maplist.getMapItem()[i+cur].getOffset()-index);
    				skip(maplist.getMapItem()[i+cur].getOffset()-index);
    				readStrData(ofs);
    				break;
    			case ClassConstants.TYPE_DEBUG_INFO_ITEM	          :
//    				show(maplist.getMapItem()[i+cur].getOffset()-index);
    				skip(maplist.getMapItem()[i+cur].getOffset()-index);
    				readDgInfo(ofs);
    				break;
    			case ClassConstants.TYPE_ANNOTATION_ITEM	          :
//    				show(maplist.getMapItem()[i+cur].getOffset()-index);
    				skip(maplist.getMapItem()[i+cur].getOffset()-index);
    				readAnnotItem(ofs);
    				break;
    			case ClassConstants.TYPE_ENCODED_ARRAY_ITEM	        : 
//    				show(maplist.getMapItem()[i+cur].getOffset()-index);
    				skip(maplist.getMapItem()[i+cur].getOffset()-index);
    				readEncArry(ofs);
    				break;
    			case ClassConstants.TYPE_ANNOTATIONS_DIRECTORY_ITEM	:
//    				show(maplist.getMapItem()[i+cur].getOffset()-index);
    				skip(maplist.getMapItem()[i+cur].getOffset()-index); 
    				readAnnotDir(ofs);
    				break;
    			}
    		}
    		
			
    		
    		System.out.println("end");
		}
		catch (Exception ex){
			
		}
	}
	
	private void readHeader() {
		// TODO Auto-generated method stub
		// DEX File Header analysis
		int checksum = readInt();
		
		byte[] signature = Utils.readBytes(20,dataInput); index += 20;
		String ab = Utils.getUTF(signature);

		int file_size = readInt();
		int header_size = readInt();
		int endian_tag = readInt();
		String dd = Utils.form(endian_tag);
		int link_size = readInt();
		int link_off = readInt();
		int map_off = readInt();
		
		int string_ids_size = readInt();
		int string_ids_off = readInt();
		
		int type_ids_size = readInt();
		int type_ids_off = readInt();
		int proto_ids_size = readInt();
		int proto_ids_off = readInt();
		
		int field_ids_size = readInt();
		int field_ids_off = readInt();
		
		int method_ids_size = readInt();
		int method_ids_off = readInt();
		
		int class_defs_size = readInt();
		int class_defs_off = readInt();
		
		int data_size = readInt();
		int data_off = readInt();
		// Header analyse end.
		
		// Save data for header
		header.setChecksum(checksum);
		header.setSignature(signature);
		header.setFile_size(file_size);
		header.setHeader_size(header_size);
		header.setEndian_tag(endian_tag);
		header.setLink_size       (link_size      );
		header.setLink_off        (link_off       );
		header.setMap_off         (map_off        );
		header.setString_ids_size (string_ids_size);
		header.setString_ids_off  (string_ids_off );
		header.setType_ids_size   (type_ids_size  );
		header.setType_ids_off    (type_ids_off   );
		header.setProto_ids_size  (proto_ids_size );
		header.setProto_ids_off   (proto_ids_off  );
		header.setField_ids_size  (field_ids_size );
		header.setField_ids_off   (field_ids_off  );
		header.setMethod_ids_size (method_ids_size);
		header.setMethod_ids_off  (method_ids_off );
		header.setClass_defs_size (class_defs_size);
		header.setClass_defs_off  (class_defs_off );
		header.setData_size       (data_size      );
		header.setData_off        (data_off       );

	}
	private void readStringIds(){
		int size = header.getString_ids_size();
		int[] stringOffA = new int[size];
		for (int i = 0; i< size; i++) {
			stringOffA[i] = readInt();
		}
		string_ids.setString_data_off(stringOffA);
	}
	private void readTypeIds(){
		int size = header.getType_ids_size();
		int[] descriptor_idx = new int[size];
		for (int i = 0; i< size; i++) {
			descriptor_idx[i] = readInt();
		}
		type_ids.setDescriptor_idx(descriptor_idx);
	}
	private void readProtoIds() {
		// TODO Auto-generated method stub
		int size = header.getProto_ids_size();
		int[] shorty_idx = new int[size];
		int[] return_type_idx = new int[size];
		int[] parameters_off = new int[size];
		for (int i = 0; i< size; i++) {
			shorty_idx[i] = readInt();
			return_type_idx[i] = readInt();
			parameters_off[i] = readInt();
		}
		proto_ids.setShorty_idx(shorty_idx);
		proto_ids.setReturn_type_idx(return_type_idx);
		proto_ids.setParameters_off(parameters_off);
	}
	private void readFieldIds() {
		// TODO Auto-generated method stub
		int size = header.getField_ids_size();
		int[] fieldclass_idx = new int[size];
		int[] fieldtype_idx = new int[size];
		int[] fieldname_idx = new int[size];
		for (int i = 0; i< size; i++) {
			fieldclass_idx[i] = readShort();
			fieldtype_idx[i] = readShort();
			fieldname_idx[i] = readInt();
		}
		field_ids.setClass_idx(fieldclass_idx);
		field_ids.setType_idx(fieldtype_idx);
		field_ids.setName_idx(fieldname_idx);
	}
	private void readMethodIds() {
		// TODO Auto-generated method stub
		int size = header.getMethod_ids_size();
		int[] methodclass_idx = new int[size];
		int[] methodproto_idx = new int[size];
		int[] methodname_idx = new int[size];
		for (int i = 0; i< size; i++) {
			methodclass_idx[i] = readShort();
			methodproto_idx[i] = readShort();
			methodname_idx[i] = readInt();
		}
		method_ids.setClass_idx(methodclass_idx);
		method_ids.setProto_idx(methodproto_idx);
		method_ids.setName_idx(methodname_idx);
	}
	private void readClassDefs() {
		// TODO Auto-generated method stub
		int size = header.getClass_defs_size();
		int[] class_def_class_idx = new int[size];
		int[] class_def_access_flags = new int[size];
		int[] class_def_superclass_idx = new int[size];
		int[] class_def_interfaces_off = new int[size];
		int[] class_def_source_file_idx = new int[size];
		int[] class_def_annotations_off = new int[size];
		int[] class_def_class_data_off = new int[size];
		int[] class_def_static_values_off = new int[size];
		
		for (int i = 0; i< size; i++) {
			class_def_class_idx[i] = readInt();
			class_def_access_flags[i] = readInt();
			class_def_superclass_idx[i] = readInt();
			class_def_interfaces_off[i] = readInt();
			class_def_source_file_idx[i] = readInt();
			class_def_annotations_off[i] = readInt();
			class_def_class_data_off[i] = readInt();
			class_def_static_values_off[i] = readInt();
		}
		
		class_defs.setClass_idx(class_def_class_idx);
		class_defs.setAccess_flags(class_def_access_flags);
		class_defs.setSuperclass_idx(class_def_superclass_idx);
		class_defs.setInterfaces_off(class_def_interfaces_off);
		class_defs.setSource_file_idx(class_def_source_file_idx);
		class_defs.setAnnotations_off(class_def_annotations_off);
		class_defs.setClass_data_off(class_def_class_data_off);
		class_defs.setStatic_values_off(class_def_static_values_off);
	}
	private void readMapList() {
		// TODO Auto-generated method stub
		int static_fields_size = readInt();
		MapItem[] mia = new MapItem[static_fields_size];
		for (int i = 0; i< static_fields_size; i++) {
			MapItem mi = new MapItem();
			mi.setType(readShort());
			int unused = readShort();
			mi.setSize(readInt());
			mi.setOffset(readInt());
			mia[i] = mi;
		}
		maplist.setSize(static_fields_size);
		maplist.setMapItem(mia);
		
		System.out.println(maplist.toString());
	}
	private void readTypeList(int ofs) {
		// TODO Auto-generated method stub
		int[] type_list_size = new int[maplist.getMapItem(ofs).getSize()];
		int[] offseta = new int[maplist.getMapItem(ofs).getSize()];
		int[][] type_idx = new int[maplist.getMapItem(ofs).getSize()][];
		TypeItem[] tia = new TypeItem[maplist.getMapItem(ofs).getSize()];
		
		for (int i=0;i<maplist.getMapItem(ofs).getSize();i++){
			offseta[i] = index;
			type_list_size[i] = readInt();
			
			type_idx[i] = new int[type_list_size[i]];
			int t = 0;
			
			for (int j=0;j<type_list_size[i];j++){
				type_idx[i][j] = readShort();
				t++;
			}
			if (t%2 == 1){
				int unused = readShort();
			}
			tia[i] = new TypeItem();
			tia[i].setType_idx(type_idx[i]);
		}
		
		typeList.setSize(type_list_size);
		typeList.setList(tia);
		typeList.setOffset(offseta);
		/*
		int[] off =  class_defs.getInterfaces_off();
		int[] off2 = proto_ids.getParameters_off();
		
		int[] type_list_size = new int[off.length];
		int[] type_idx = null;
		TypeItem[] tia = new TypeItem[off.length];
		
		for (int i=0;i<off.length;i++){
			if (off[i]==0) continue;
			tia[i] = new TypeItem();
			try{
				DataInput dataInputp = new DataInputStream(new BufferedInputStream(new FileInputStream(header.getFile_name())));
				dataInputp.skipBytes(off[i]);
				type_list_size[i] = Utils.endianInt(dataInputp.readInt());
				type_idx = new int[type_list_size[i]];
				for (int j=0;j<type_list_size[i];j++){
					type_idx[j] = Utils.endianShort(dataInput.readUnsignedShort());
				}
				tia[i].setType_idx(type_idx);
				dataInputp = null;
			} catch (Exception ex){
				System.out.println("Error");
			}
		}
		cls_def_typeList.setSize(type_list_size);
		cls_def_typeList.setList(tia);
		
		type_list_size = new int[off2.length];
		tia = new TypeItem[off2.length];
		for (int i=0;i<off2.length;i++){
			if (off2[i]==0) continue;
			tia[i] = new TypeItem();
			
			try{
				DataInput dataInputp = new DataInputStream(new BufferedInputStream(new FileInputStream(header.getFile_name())));
				dataInputp.skipBytes(off2[i]);
				type_list_size[i] = Utils.endianInt(dataInputp.readInt());
				type_idx = new int[type_list_size[i]];
				for (int j=0;j<type_list_size[i];j++){
					type_idx[j] = Utils.endianShort(dataInput.readUnsignedShort());
				}
				tia[i].setType_idx(type_idx);
				dataInputp = null;
			} catch (Exception ex){
				System.out.println("Error");
			}
		}
		prt_id_typeList.setSize(type_list_size);
		prt_id_typeList.setList(tia);
		*/
	}
	private void readAnnotSetRef(int ofs) {
		// TODO Auto-generated method stub
		int[] annot_size = new int[maplist.getMapItem(ofs).getSize()];
		int[] offseta = new int[maplist.getMapItem(ofs).getSize()];
		int[][] list = new int[maplist.getMapItem(ofs).getSize()][];
		AnnotationSetRefItem[] asri = new AnnotationSetRefItem[maplist.getMapItem(ofs).getSize()];
		
		for (int i=0;i<maplist.getMapItem(ofs).getSize();i++){
			offseta[i] = index;
			annot_size[i] = readInt();
			
			if (annot_size[i]>0)
				list[i] = new int[annot_size[i]];
			
			for (int j=0;j<annot_size[i];j++){
				list[i][j] = readInt();
			}
			asri[i] = new AnnotationSetRefItem();
			asri[i].setAnnotations_off(list[i]);
		}
		
		asrl.setSize(annot_size);
		asrl.setAsri(asri);
		asrl.setOffset(offseta);
	}
	private void readAnnotSet(int ofs) {
		// TODO Auto-generated method stub
		int[] annot_size = new int[maplist.getMapItem(ofs).getSize()];
		int[] offseta = new int[maplist.getMapItem(ofs).getSize()];
		int[][] list = new int[maplist.getMapItem(ofs).getSize()][];
		AnnotationOff[] ao = new AnnotationOff[maplist.getMapItem(ofs).getSize()];
		
		for (int i=0;i<maplist.getMapItem(ofs).getSize();i++){
			offseta[i] = index;
			annot_size[i] = readInt();
			
			if (annot_size[i]>0)
				list[i] = new int[annot_size[i]];
			
			for (int j=0;j<annot_size[i];j++){
				list[i][j] = readInt();
			}
			
			ao[i] = new AnnotationOff();
			ao[i].setAnnotation_off(list[i]);
		}
				
		asi.setSize(annot_size);
		asi.setAo(ao);
		asi.setOffset(offseta);
	}
	private void readClassData(int ofs) {
		// TODO Auto-generated method stub
		int static_fields_size[] = new int[maplist.getMapItem(ofs).getSize()];
		int instance_fields_size[] = new int[maplist.getMapItem(ofs).getSize()];
		int direct_methods_size[] = new int[maplist.getMapItem(ofs).getSize()];
		int virtual_methods_size[] = new int[maplist.getMapItem(ofs).getSize()];
		int field_idx_diff[] = new int[maplist.getMapItem(ofs).getSize()];
		int access_flags[] = new int[maplist.getMapItem(ofs).getSize()];
		EncodedField[][] static_fields = new EncodedField[maplist.getMapItem(ofs).getSize()][];
		EncodedField[][] instance_fields = new EncodedField[maplist.getMapItem(ofs).getSize()][];
		EncodedMethod[][] direct_methods = new EncodedMethod[maplist.getMapItem(ofs).getSize()][];
		EncodedMethod[][] virtual_methods = new EncodedMethod[maplist.getMapItem(ofs).getSize()][];
		
		for (int i=0;i<maplist.getMapItem(ofs).getSize();i++){
			static_fields_size[i] = readULEB();
			instance_fields_size[i] = readULEB();
			direct_methods_size[i] = readULEB();
			virtual_methods_size[i] = readULEB();
			if (static_fields_size[i]>0){
				field_idx_diff = new int[static_fields_size[i]];
				access_flags = new int[static_fields_size[i]];
				static_fields[i] = new EncodedField[static_fields_size[i]];
			}
			for (int j=0;j<static_fields_size[i];j++){
				field_idx_diff[j] = readULEB();
				access_flags[j] = readULEB();
				static_fields[i][j] = new EncodedField();
				static_fields[i][j].setField_idx_diff(field_idx_diff[j]);
				static_fields[i][j].setAccess_flags(access_flags[j]);
			}
			if (instance_fields_size[i]>0){
				field_idx_diff = new int[instance_fields_size[i]];
				access_flags = new int[instance_fields_size[i]];
				instance_fields[i] = new EncodedField[instance_fields_size[i]];
			}
			for (int j=0;j<instance_fields_size[i];j++){
				field_idx_diff[j] = readULEB();
				access_flags[j] = readULEB();
				instance_fields[i][j] = new EncodedField();
				instance_fields[i][j].setField_idx_diff(field_idx_diff[j]);
				instance_fields[i][j].setAccess_flags(access_flags[j]);
			}
			if (direct_methods_size[i]>0){
				int method_idx_diff[] = new int[direct_methods_size[i]];
				access_flags = new int[direct_methods_size[i]];
				int code_off[] = new int[direct_methods_size[i]];
				direct_methods[i] = new EncodedMethod[direct_methods_size[i]];
				for (int j=0;j<direct_methods_size[i];j++){
					method_idx_diff[j] = readULEB();
					access_flags[j] = readULEB();
					code_off[j] = readULEB();
					direct_methods[i][j] = new EncodedMethod();
					direct_methods[i][j].setMethod_idx_diff(method_idx_diff[j]);
					direct_methods[i][j].setAccess_flags(access_flags[j]);
					direct_methods[i][j].setCode_off(code_off[j]);
				}
			}
			
			if (virtual_methods_size[i]>0){
				int[] method_idx_diff = new int[virtual_methods_size[i]];
				access_flags = new int[virtual_methods_size[i]];
				int[] code_off = new int[virtual_methods_size[i]];
				virtual_methods[i] = new EncodedMethod[virtual_methods_size[i]];
				for (int j=0;j<virtual_methods_size[i];j++){
					method_idx_diff[j] = readULEB();
					access_flags[j] = readULEB();
					code_off[j] = readULEB();
					virtual_methods[i][j] = new EncodedMethod();
					virtual_methods[i][j].setMethod_idx_diff(method_idx_diff[j]);
					virtual_methods[i][j].setAccess_flags(access_flags[j]);
					virtual_methods[i][j].setCode_off(code_off[j]);
				}
			}
		}
		cdi.setStatic_fields_size(static_fields_size);
		cdi.setInstance_fields_size(instance_fields_size);
		cdi.setDirect_methods_size(direct_methods_size);
		cdi.setVirtual_methods_size(virtual_methods_size);
		cdi.setStatic_field(static_fields);
		cdi.setInstance_field(instance_fields);
		cdi.setDirect_methods(direct_methods);
		cdi.setVirtual_methods(virtual_methods);
	}
	private void readCode(int ofs) {
		// TODO Auto-generated method stub
		int[] registers_size = new int[maplist.getMapItem(ofs).getSize()];
		int[] ins_size = new int[maplist.getMapItem(ofs).getSize()];
		int[] outs_size = new int[maplist.getMapItem(ofs).getSize()];
		int[] tries_size = new int[maplist.getMapItem(ofs).getSize()];
		int[] debug_info_off = new int[maplist.getMapItem(ofs).getSize()];
		int[] insns_size = new int[maplist.getMapItem(ofs).getSize()];
		
		int[] padding = new int[maplist.getMapItem(ofs).getSize()];
		int[][] insns = new int[maplist.getMapItem(ofs).getSize()][];
		int[][] start_addr = new int[maplist.getMapItem(ofs).getSize()][];
		int[][] insn_count = new int[maplist.getMapItem(ofs).getSize()][];
		int[][] hadler_off = new int[maplist.getMapItem(ofs).getSize()][];
		TryItem[][] tia = new TryItem[maplist.getMapItem(ofs).getSize()][];
		int[] enco_cat_size = new int[maplist.getMapItem(ofs).getSize()];
		int[][] enco_cat_list_size = new int[maplist.getMapItem(ofs).getSize()][];
		int[][][] enco_cat_list_type_idx = new int[maplist.getMapItem(ofs).getSize()][][];
		int[][][] enco_cat_list_addr = new int[maplist.getMapItem(ofs).getSize()][][];
		int[][] enco_cat_list_catch_all = new int[maplist.getMapItem(ofs).getSize()][];
		EncodedCatchHandlerList[] echl = new EncodedCatchHandlerList[maplist.getMapItem(ofs).getSize()];
		
		for (int i=0;i<maplist.getMapItem(ofs).getSize();i++){
			idx = 0;
			registers_size[i] = readShort();
			ins_size[i] = readShort();
			outs_size[i] = readShort();
			tries_size[i] = readShort();
			debug_info_off[i] = readInt();
			insns_size[i] = readInt();
			
			if (insns_size[i]>0)
				insns[i] = new int[insns_size[i]];
			for (int j=0;j<insns_size[i];j++){
				insns[i][j] = readShort();
				idx = idx+2;
			}
			
			if (tries_size[i] != 0 && insns_size[i]%2 == 1){
				padding[i] = readShort();
				idx = idx+2;
			}
						
			if (tries_size[i]>0){
				tia[i] = new TryItem[tries_size[i]];
				start_addr[i] = new int[tries_size[i]];
				insn_count[i] = new int[tries_size[i]];
				hadler_off[i] = new int[tries_size[i]];
			
				for (int j=0;j<tries_size[i];j++){
					tia[i][j] = new TryItem();
					start_addr[i][j] = readInt();
					insn_count[i][j] = readShort();
					hadler_off[i][j] = readShort();
					tia[i][j].setStart_addr(start_addr[i][j]);
					tia[i][j].setInsn_count(insn_count[i][j]);
					tia[i][j].setHandler_off(hadler_off[i][j]);
				}
			}
			
			if (tries_size[i] != 0){
				echl[i] = new EncodedCatchHandlerList();
				enco_cat_size[i] = readULEB();
				echl[i].setSize(enco_cat_size[i]);
				
				if (enco_cat_size[i]>0){
				EncodedCatchHandler[] ech = new EncodedCatchHandler[enco_cat_size[i]];
				enco_cat_list_size[i] = new int[enco_cat_size[i]];
				enco_cat_list_type_idx[i] = new int[enco_cat_size[i]][];
				enco_cat_list_addr[i] = new int[enco_cat_size[i]][];
				enco_cat_list_catch_all[i] = new int[enco_cat_size[i]];
				
					for (int j=0;j<enco_cat_size[i];j++){
						ech[j] = new EncodedCatchHandler();
						enco_cat_list_size[i][j] = readSLEB();
						ech[j].setSize(enco_cat_list_size[i][j]);
						
						int abs_ecs = enco_cat_list_size[i][j];
						if (enco_cat_list_size[i][j]<0){
							abs_ecs = abs_ecs * (-1);
						}
						
						EncodedTypeAddrPair[] etap = new EncodedTypeAddrPair[abs_ecs];
						enco_cat_list_type_idx[i][j] = new int[abs_ecs];
						enco_cat_list_addr[i][j] = new int[abs_ecs];
						for (int k=0;k<abs_ecs;k++){
							etap[k] = new EncodedTypeAddrPair();
							enco_cat_list_type_idx[i][j][k] = readULEB();
							enco_cat_list_addr[i][j][k] = readULEB();
							etap[k].setType_idx(enco_cat_list_type_idx[i][j][k]);
							etap[k].setAddr(enco_cat_list_addr[i][j][k]);
						}
						ech[j].setEtap(etap);
						
						if (enco_cat_list_size[i][j]<=0){
							enco_cat_list_catch_all[i][j] = readULEB();
							ech[j].setCatchAllAddr(enco_cat_list_catch_all[i][j]);
						}
					}
					echl[i].setEch(ech);
				}
			}
			
			switch (idx%4){
			case 1:
				int unused = readByte();
			case 2:
				unused = readByte();
			case 3:
				unused = readByte();
			}
			
		}
		codeItem.setInsns(insns);
		codeItem.setPadding(padding);
		codeItem.setTries(tia);
		codeItem.setEchl(echl);
		
		codeItem.setRegisters_size(registers_size);
		codeItem.setIns_size(ins_size);
		codeItem.setOuts_size(outs_size);
		codeItem.setTries_size(tries_size);
		codeItem.setDebug_info_off(debug_info_off);
		codeItem.setInsns_size(insns_size);
	}
	private void readStrData(int ofs) {
		// TODO Auto-generated method stub
		int[] utf16_size = new int[header.getString_ids_size()];
		byte[][] utf16_string = new byte[header.getString_ids_size()][];
		String[] utfString = new String[header.getString_ids_size()];
		
		for (int i=0;i<header.getString_ids_size();i++){
			utf16_size[i] = readULEB();
			utf16_string[i] = Utils.readBytesNull(dataInput);
			index = index+utf16_string[i].length;
			String a = Utils.getUTF(utf16_string[i]);
			utfString[i] = a;
		}
		sdi.setUtf16_size(utf16_size);
		sdi.setData(utf16_string);
		sdi.setDataS(utfString);
	}
	private void readDgInfo(int ofs) {
		// TODO Auto-generated method stub
		int[] line_start = new int[maplist.getMapItem(ofs).getSize()];
		int[] parameters_size = new int[maplist.getMapItem(ofs).getSize()];
		int[][] parameter_names = new int[maplist.getMapItem(ofs).getSize()][];
		dii = new DebugInfoItem[maplist.getMapItem(ofs).getSize()];
		
		for (int i=0;i<maplist.getMapItem(ofs).getSize();i++){
			line_start[i] = readULEB();
			parameters_size[i] = readULEB();
			parameter_names[i] = new int[parameters_size[i]];
			for (int j=0;j<parameters_size[i];j++){
				parameter_names[i][j] = readULEB() - 1;
			}
			dii[i] = new DebugInfoItem();
			dii[i].setLineStart(line_start[i]);
			dii[i].setParametersSize(parameters_size[i]);
			dii[i].setParameterName(parameter_names[i]);
			
			dii[i].setBc(readByteCode());
		}
	}
	private ByteCode[] readByteCode(){
		int[] op = new int[999999];
		int[][] arg = new int[999999][];
		int i = 0;
		
		while (true){
			op[i] = (byte)readByte();
			if (op[i] == 1 || op[i] == 5 || op[i] == 6){
				arg[i] = new int[1];
				arg[i][0] = (int)readULEB();
			} else if (op[i] == 2){
				arg[i] = new int[1];
				arg[i][0] = (int)readSLEB();
			} else if (op[i] == 3){
				arg[i] = new int[3];
				arg[i][0] = (int)readULEB();
				arg[i][1] = (int)readULEB();
				arg[i][2] = (int)readULEB();
			} else if (op[i] == 4){
				arg[i] = new int[4];
				arg[i][0] = (int)readULEB();
				arg[i][1] = (int)(readULEB()-1);
				arg[i][2] = (int)(readULEB()-1);
				arg[i][3] = (int)(readULEB()-1);
			} else if (op[i] == 9){
				arg[i] = new int[1];
				arg[i][0] = (int)(readULEB()-1);
			} else if (op[i] == 0){
				break;
			}
			i++;
		}
		ByteCode[] bc = new ByteCode[i];
		for (int k=0;k<i;k++){
			bc[k] = new ByteCode();
			bc[k].setOpCodes(op[k]);
			bc[k].setArguments(arg[k]);
		}
		return bc;
	}
	private void readAnnotItem(int ofs) {
		// TODO Auto-generated method stub
		ai = new AnnotationItem[maplist.getMapItem(ofs).getSize()];
		for (int i=0;i<maplist.getMapItem(ofs).getSize();i++){
			ai[i] = new AnnotationItem();
			ai[i].setVisibility(readByte());
			ai[i].setAnnotation(geteanVal());
		}
	}
	private EncodedValue getevVal(){
		EncodedValue ev = new EncodedValue();
		
		ev.setHeader(readByte());
		
		String ba = Utils.getBinary(ev.getHeader());
		String valueArg = ba.substring(0, 3);
		String valueType = ba.substring(3, 8);
		
		ev.setValue_arg(Integer.parseInt(valueArg, 2));
		ev.setValue_type(Integer.parseInt(valueType, 2));
		logg += " EV Arg["+ev.getValue_arg()+"] Type["+ev.getValue_type()+"]";
		ev.setValue(getValue(ev));
		return ev;
	}
	private EncodedArray geteaVal(){
		EncodedArray ea = new EncodedArray();
		ea.setSize(readULEB());
		logg += " EA Size["+ea.getSize()+"]";
		EncodedValue[] evr = new EncodedValue[ea.getSize()];
		for(int i=0;i<ea.getSize();i++){
			evr[i] = getevVal();
		}
		ea.setEvr(evr);
		return ea;
	}
	private EncodedAnnotation geteanVal(){
		EncodedAnnotation ean = new EncodedAnnotation();
		
		ean.setType_idx(readULEB());
		ean.setSize(readULEB());
		logg += " EAN TI["+ean.getType_idx()+"] S["+ean.getSize()+"]";
		AnnotationElement[] aer = new AnnotationElement[ean.getSize()];
		for(int i=0;i<ean.getSize();i++){
			aer[i] = getaeVal();
		}
		ean.setElements(aer);
		return ean;
	}
	private AnnotationElement getaeVal(){
		AnnotationElement ae = new AnnotationElement();
		
		ae.setName_idx(readULEB());
		logg += " AE N["+ae.getName_idx()+"]";
		
		EncodedValue ev = new EncodedValue();
		ev = getevVal();
		ae.setValue(ev);
		
		return ae;
	}
	private Object getValue(EncodedValue ev){
		Object result = null;
		int[] sresult = null;
		int b = 0;
		
		switch (ev.getValue_type()){
		case 0x1c: //encodedArray
			EncodedArray ea = new EncodedArray();
			ea = geteaVal();
			result = ea;
			break;
		case 0x1d: //encodedAnnotation
			EncodedAnnotation ean = new EncodedAnnotation();
			ean = geteanVal();
			result = ean;
			break;
		case 0x00:
			if (ev.getValue_arg() != 0){
				System.out.println("Error!!!!!!!!!");
				System.exit(1);
			}
		case 0x02:
		case 0x03:
		case 0x04:
		case 0x06:
		case 0x10:
		case 0x11:
		case 0x17:
		case 0x18:
		case 0x19:
		case 0x1a:
		case 0x1b:
			int size = ev.getValue_arg()+1;
			sresult = new int[size];
			for (int i=0;i<size;i++){
				sresult[i] = readByte();
				b++;
			}
			result = sresult;
		case 0x1e:
		case 0x1f:
			break;
		default:
			System.out.println("Error!!!!!!!!!");
			System.exit(1);
		}
		logg += " read["+b+"]";
		return result;
	}
	private void readEncArry(int ofs) {
		// TODO Auto-generated method stub
		ear = new EncodedArray[maplist.getMapItem(ofs).getSize()];
//		show(8000);
		logg = "";
		for (int i=0;i<maplist.getMapItem(ofs).getSize();i++){
			ear[i] = geteaVal();
		}
	}
	private void readAnnotDir(int ofs) {
		// TODO Auto-generated method stub
		adi = new AnnotationDirectoryItem[maplist.getMapItem(ofs).getSize()];
		
		for (int i=0;i<maplist.getMapItem(ofs).getSize();i++){
			adi[i] = new AnnotationDirectoryItem();
			adi[i].setClass_annotations_off(readInt());
			adi[i].setFields_size(readInt());
			adi[i].setAnnotated_method_size(readInt());
			adi[i].setAnnotated_parameters_size(readInt());
		
			Annotation[] anno_fields = new Annotation[adi[i].getFields_size()];
			Annotation[] anno_method = new Annotation[adi[i].getAnnotated_method_size()];
			Annotation[] anno_parameter = new Annotation[adi[i].getAnnotated_parameters_size()];
			for (int j=0;j<adi[i].getFields_size();j++){
				anno_fields[j] = new Annotation();
				anno_fields[j].setIdx(readInt());
				anno_fields[j].setOff(readInt());
			}
			for (int j=0;j<adi[i].getAnnotated_method_size();j++){
				anno_method[j] = new Annotation();
				anno_method[j].setIdx(readInt());
				anno_method[j].setOff(readInt());
			}
			for (int j=0;j<adi[i].getAnnotated_parameters_size();j++){
				anno_parameter[j] = new Annotation();
				anno_parameter[j].setIdx(readInt());
				anno_parameter[j].setOff(readInt());
			}
			
			adi[i].setField_annotations(anno_fields);
			adi[i].setMethod_annotations(anno_method);
			adi[i].setParameter_annotations(anno_parameter);
			
		}
		
	}
	private void show(int num){
		
		try{
			int ci = index;
			setNewDI();
			skip(ci-100);
			String d = "";
			String df = "";
			try{
			for (int s=0;s<100;s++){
				int dd = readByte();
				String ds = Utils.form(dd)+"\t";
				df += ds;
				if (s%10==9 || s== num-1) {
					logger.info(df);
					df = "";
				}
			}} catch (Exception e){
				logger.info(df);}
			logger.info("---------------------------------------------1111111111111------------------------------------------------------");
			try{
			for (int s=0;s<num;s++){
				int dd = readByte();
				String ds = Utils.form(dd)+"\t";
				df += ds;
				if (s%10==9 || s== num-1) {
					logger.info(df);
					df = "";
				}
			}} catch (Exception e){
				logger.info(df);}
			logger.info("---------------------------------------------2222222222222-------------------------------------------------");
			try{
			for (int s=0;s<100;s++){
				int dd = readByte();
				String ds = Utils.form(dd)+"\t";
				df += ds;
				if (s%10==9 || s== num-1) {
					logger.info(df);
					df = "";
				}
			}} catch (Exception e){
				logger.info(df);}
			logger.info("-----------------------------------------------------------------------------------------------------------");
			setNewDI();
			skip(ci+num);
		} catch (Exception e){}
	}
}
