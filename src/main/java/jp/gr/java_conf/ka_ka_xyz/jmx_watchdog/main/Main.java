package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.main;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.jvm.manager.JvmConfigManager;
import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.task.JmxRetrieveTask;

/**
 * リモートJVMからMxBeanを取得するサンプル
 * */
public class Main {
	private static final String START = "start";
	private static final String STOP = "stop";
	private static JmxRetrieveTask task =null;
	private static Timer timer = null;
	private static Logger logger = Logger.getLogger(Main.class.getName());
	public static void main(String[] args) throws Exception {

		if(args == null || args.length < 1){
			//logger.info("no argument. do nothing");
			return;
		}
		
		if(START.equalsIgnoreCase(args[0])){
			logger.info("tring to start watchdog.");
			timer = new Timer();
			try{
				int interval = JvmConfigManager.getConfigManager().getInterval();
				task = new JmxRetrieveTask(JvmConfigManager.getConfigManager().getRetryLimit());
				timer.scheduleAtFixedRate(task, 0 ,interval*1000);
			} catch(Throwable t){
				logger.log(Level.SEVERE, "stop thread.", t);
			}
			logger.info("watchdog started successfully.");

		} else if(STOP.equalsIgnoreCase(args[0])){
			logger.info("tring to stop watchdog.");
			task.cancel();
			timer.purge();
			logger.info("watchdog stopped successfully.");
			System.exit(0);
		}
	}

}