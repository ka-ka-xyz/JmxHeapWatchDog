package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.bean;

import java.lang.management.MemoryPoolMXBean;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * xml設定ファイルのbeanInfoタグに対応するクラスです。
 * MXBean名およびcsv出力時のヘッダ情報を保持します。
 * 
 * */
@Root(name="memoryPoolMxBeanInfo")
public class MemoryPoolMXBeanInfo implements MXBeanInfo {

	/**MXBean名*/
	@Attribute(name="name")
	private String name;
	
	@Attribute(name="bean_type")
	private String beanType;
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getBeanType() {
		return beanType;
	}
	public void setBeanType(String beanType) {
		this.beanType = beanType;
	}
	@Override
	public Class<MemoryPoolMXBean> getMxBeanClass(){
		return java.lang.management.MemoryPoolMXBean.class;
	}
	@Override
	public String toString() {
		return "MemoryPoolMXBeanInfo [name=" + name + "]";
	}
}
