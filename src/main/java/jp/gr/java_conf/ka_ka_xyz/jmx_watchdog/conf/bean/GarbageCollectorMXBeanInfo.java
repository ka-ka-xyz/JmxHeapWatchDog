package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.bean;

import java.lang.management.GarbageCollectorMXBean;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="gcMXBeanInfo")
public class GarbageCollectorMXBeanInfo implements MXBeanInfo {
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
	public Class<GarbageCollectorMXBean> getMxBeanClass(){
		return java.lang.management.GarbageCollectorMXBean.class;
	}
	
	@Override
	public String toString() {
		return "GarbageCollectorMXBeanInfo [name=" + name + "]";
	}
}
