package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ServiceLoader;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.bean.MXBeanInfo;
import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.bean.manager.BeanConfigManager;
import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.jvm.JvmInfo;
import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.jvm.manager.JvmConfigManager;
import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.handler.MXBeanHandler;

public class JmxRetrieveTask extends TimerTask{

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final String SEPARATOR = System.getProperty("line.separator");
	private static final String JMXRMI_URL_PROTOCOL = "service:jmx:rmi:///jndi/rmi://";
	private static final String JMXRMI_URL_POSTFIX = "/jmxrmi";
	private static final String JMXBEAN_NAME_TYPE = "java.lang:type=";
	private static final String JMXBEAN_NAME_NAME = ",name=";
	private static final String OUTPUT_FILE_EXTENTION = ".csv";
	private static int RETRY_LIMIT = 5;
	private static int retryCount = 0;
	
	public JmxRetrieveTask(int retryLimit){
		RETRY_LIMIT = retryLimit;
	}
	
	private MBeanServerConnection remote = null;
	private static Logger logger = Logger.getLogger(JmxRetrieveTask.class.getName());
	
	private void retrieveJmxBeans() throws Exception {
		logger.log(Level.FINEST, "start retrieveJmxBeans.");
		List<JvmInfo> jvmInfoList = JvmConfigManager
				.getConfigManager().getJvmInfoList();
		
		List<? extends MXBeanInfo> beanInfoList = BeanConfigManager
				.getConfigManager().getBeanInfoList();
		
		for(JvmInfo jvmInfo : jvmInfoList){
			File csvPath = new File(jvmInfo.getCsvPath());
			String csvFileName = jvmInfo.getName() + "_" + sdf.format(new Date()) + OUTPUT_FILE_EXTENTION;
			if(!csvPath.exists()){
				csvPath.mkdirs();
			}
			File outFile = new File(csvPath, csvFileName);
			boolean isVoidFile = outFile.length() < 1;
			Writer writer = new FileWriter(outFile, true);
			
			JMXConnector connector = this.getConnector(jvmInfo);
			if(connector == null){
				break;
			}
			// リモートJvmへのコネクション作成
			remote = connector
					.getMBeanServerConnection();
			List<Object> beans = new ArrayList<Object>();
			for(MXBeanInfo beanInfo : beanInfoList){
				beans.add(getMxBean(beanInfo.getName(), beanInfo.getBeanType(), beanInfo.getMxBeanClass()));
			}
			ServiceLoader<MXBeanHandler> loader = ServiceLoader.load(MXBeanHandler.class);
			for(MXBeanHandler handler : loader){
				for(Object obj : beans) {
					handler.setData(obj);
					//最初の一回のみヘッダ文字列を追加
					if(isVoidFile){
						writer.write(handler.getHeaderCsvString() + SEPARATOR);
						isVoidFile = false;
					}
				}
				logger.log(Level.FINEST, "write line");
				writer.write(handler.getDataCsvString() + SEPARATOR);
				break;
			}
			writer.flush();
			writer.close();
		}
		logger.log(Level.FINEST, "end retrieveJmxBeans.");
	}
	/**
	 * MxBean名からMXBeanインスタンスを取得します。
	 * @param beanName MxBean名
	 * @return 
	 * */
	private <T> T getMxBean(String name, String beanType, Class<T> val) throws IOException{
		String url;
		if("".equals(name) || name == null){
			url = JMXBEAN_NAME_TYPE + beanType;
		} else {
			url = JMXBEAN_NAME_TYPE + beanType + JMXBEAN_NAME_NAME + name;
		}
		T mpmxbean 
		= ManagementFactory.newPlatformMXBeanProxy(
			remote, 
			url,
			val);
		return mpmxbean;
	}
	
	private JMXConnector getConnector(JvmInfo jvmInfo) throws IOException{
		String serviceUrlStr = JMXRMI_URL_PROTOCOL 
				+ jvmInfo.getHost() + ":" 
				+ jvmInfo.getPort() + JMXRMI_URL_POSTFIX;
		JMXServiceURL target = new JMXServiceURL(serviceUrlStr);
		JMXConnector connector;
		try{
			connector = JMXConnectorFactory.connect(target);
			retryCount = 0;
		} catch(IOException e){
			if(retryCount < RETRY_LIMIT){
				logger.log(Level.WARNING, "failed to connect to jvm: " + serviceUrlStr + ", retry: " 
						+ (++retryCount) + "/" + RETRY_LIMIT);
				return null;
			} else {
				throw e;
			}
		}
		return connector;
	}
	@Override
	public void run() {
		try {
			retrieveJmxBeans();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "failed to retrieve JmxBeans.", e);
			throw new RuntimeException("failed to retrieve.", e);
		}
		
	}


}
