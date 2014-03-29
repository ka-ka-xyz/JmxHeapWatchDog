package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.jvm.manager;

import java.util.List;

import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.jvm.JvmInfo;
import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.jvm.JvmInfoList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class JvmConfigManager {
	
	private static JvmConfigManager myself = null;
	private JvmInfoList root = null;
	
	private JvmConfigManager(){}
	
	public static JvmConfigManager getConfigManager(){
		if(myself == null){
			myself = new JvmConfigManager();
			myself.loadInfo();
		}
		return myself;
	}
	
	private void loadInfo(){
		Serializer serializer = new Persister();
		try {
			root = serializer.read(JvmInfoList.class, 
					ClassLoader.getSystemResourceAsStream("jvms.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<JvmInfo> getJvmInfoList(){
		return root.getList();
	}
	public int getInterval(){
		return root.getInterval();
	}
	public int getRetryLimit(){
		return root.getRetry();
	}
}
