package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.bean.manager;

import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.bean.MXBeanInfo;
import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.bean.MXBeanInfoList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class BeanConfigManager {
	
	private static BeanConfigManager myself = null;
	private MXBeanInfoList root = null;
	
	private BeanConfigManager(){}
	
	public static BeanConfigManager getConfigManager(){
		if(myself == null){
			myself = new BeanConfigManager();
			myself.loadInfo();
		}
		return myself;
	}
	
	private void loadInfo(){
		Serializer serializer = new Persister();
		try {
			root = serializer.read(MXBeanInfoList.class, 
					ClassLoader.getSystemResourceAsStream("beans.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.bean.BeanConfigManager#getBeanInfoList()
	 */
	public List<? extends MXBeanInfo> getBeanInfoList(){
		List<? extends MXBeanInfo> mxbl = root.getMemoryMXBeanList();
		List<? extends MXBeanInfo> mpmxbl = root.getMemoryPoolMXBeanList();
		List<? extends MXBeanInfo> gcmxbl = root.getGCMXBeanList();
		List<MXBeanInfo> result = new ArrayList<MXBeanInfo>();
		result.addAll(mxbl);
		result.addAll(mpmxbl);
		result.addAll(gcmxbl);
		return result;
		
	}
}
