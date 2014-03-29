package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.handler.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.annotation.Column;
/**DTOのアノテーション情報を元にCSVの値とヘッダ文字列を取得するためのクラス*/
class AnnotationProcessor {

	private List<Field> fields = new ArrayList<Field>();
	private List<AnnotatedColumn> annotatedFields = new ArrayList<AnnotatedColumn>();
	private CsvDataDTO dto;

	AnnotationProcessor(CsvDataDTO dto) {
		if(dto == null){throw new IllegalArgumentException("Argument is null");}
		this.dto = dto;
		@SuppressWarnings("rawtypes")
		Class clazz = dto.getClass();
		if (clazz != null) {
			getFields(clazz);
			for (Field field : fields) {
				registerFieldAnnotation(field);
			}
		}
	}
	@SuppressWarnings("rawtypes")
	private void getFields(Class clazz) {
		if (clazz == null) {
			return;
		}
		Field[] temp = clazz.getDeclaredFields();
		if (temp != null) {
			for (Field field : temp) {
				fields.add(field);
			}
		}
	}

	private void registerFieldAnnotation(Field field) {
		Column column = field.getAnnotation(Column.class);
		if (column != null) {
			String colName = column.name();
			String format = column.format();
			AnnotatedColumn temp = new AnnotatedColumn(field.getName(), colName, format);
			annotatedFields.add(temp);
		}

	}
	
	public String getValueCsvString(){

		StringBuffer rtn = new StringBuffer();
		try {
			for(AnnotatedColumn anotatedField : this.annotatedFields){
				PropertyDescriptor pd = new PropertyDescriptor(anotatedField.getPropertyName(), dto.getClass());
				Method read = pd.getReadMethod();
				Object temp = read.invoke(dto, (Object[])null);
				if(temp instanceof Date){
					SimpleDateFormat sdf = new SimpleDateFormat(anotatedField.getFormat());
					rtn.append(sdf.format((Date)temp));
				} else {
					rtn.append(String.valueOf(temp));
				}
				rtn.append(",");
			}
			
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int index = rtn.lastIndexOf(",");
		if(index > 0){
			rtn = rtn.deleteCharAt(index);
		} 
		return new String(rtn);
	}

	public String getHeaderCsvString() {
		StringBuffer rtn = new StringBuffer();
		for(AnnotatedColumn anotatedField : this.annotatedFields){
			rtn.append(anotatedField.getHeaderName()).append(",");
		}
		int index = rtn.lastIndexOf(",");
		if(index > 0){
			rtn = rtn.deleteCharAt(index);
		} 
		return new String(rtn);
	}
	
	private class AnnotatedColumn{
		private String propertyName;
		private String headerName;
		private String format;
		public AnnotatedColumn(String propertyName, String headerName, String format){
			this.propertyName = propertyName;
			this.headerName = headerName;
			this.format = format;
		}
		public String getPropertyName(){return this.propertyName;}
		public String getHeaderName(){return this.headerName;}
		public String getFormat(){return this.format;}
	}



}