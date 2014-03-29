package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.bean;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="MXBeans")
public class MXBeanInfoList {

	@ElementList(inline=true, required=false)
	private List<MemoryMXBeanInfo> mmxs = new ArrayList<MemoryMXBeanInfo>();
	@ElementList(inline=true, required=false)
	private List<MemoryPoolMXBeanInfo> mpmxs = new ArrayList<MemoryPoolMXBeanInfo>();
	@ElementList(inline=true, required=false)
	private List<GarbageCollectorMXBeanInfo> gcmxs = new ArrayList<GarbageCollectorMXBeanInfo>();
	
	public List<MemoryMXBeanInfo> getMemoryMXBeanList() {
		return mmxs;
	}

	public void setMemoryMXBeanList(List<MemoryMXBeanInfo> list) {
		this.mmxs = list;
	}
	
	public List<MemoryPoolMXBeanInfo> getMemoryPoolMXBeanList() {
		return mpmxs;
	}

	public void setMemoryPoolMXBeanList(List<MemoryPoolMXBeanInfo> list) {
		this.mpmxs = list;
	}
	
	public List<GarbageCollectorMXBeanInfo> getGCMXBeanList() {
		return gcmxs;
	}

	public void setGCMXBeanList(List<GarbageCollectorMXBeanInfo> gcmxs) {
		this.gcmxs = gcmxs;
	}

	/**名前に当てはまるMxBean情報を戻します。
	 * 該当するMxBeanInfoが存在しない場合、nullを戻します。*/
	public MXBeanInfo getInfoByName(String name){
		if(name == null){return null;}
		for(MemoryMXBeanInfo info : mmxs){
			if(name.equals(info.getName())){
				return info;
			}
		}
		for(MemoryPoolMXBeanInfo info : mpmxs){
			if(name.equals(info.getName())){
				return info;
			}
		}
		for(GarbageCollectorMXBeanInfo info : gcmxs){
			if(name.equals(info.getName())){
				return info;
			}
		}
		return null;
	}

}
