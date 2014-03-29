package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.jvm;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="JavaVMs")
public class JvmInfoList {
	/**アクセス時間間隔（秒）*/
	@Attribute(name="interval", required=false)
	private int interval = 60;
	/**リトライ上限回数*/
	@Attribute(name="retry", required=false)
	private int retry = 5;
	
	@ElementList(inline=true)
	private List<JvmInfo> list = new ArrayList<JvmInfo>();
	public List<JvmInfo> getList() {
		return list;
	}

	public void setList(List<JvmInfo> list) {
		this.list = list;
	}
	
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getRetry() {
		return retry;
	}
	public void setRetry(int retry) {
		this.retry = retry;
	}
	
}
