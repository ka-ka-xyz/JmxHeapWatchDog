package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.conf.jvm;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * xml設定ファイルのbeanInfoタグに対応するクラスです。
 * MXBean名およびcsv出力時のヘッダ情報を保持します。
 * 
 * */
@Root(name="jvmInfo")
public class JvmInfo {

	/**JavaVM識別名*/
	@Attribute(name="name")
	private String name;
	/**JavaVMホスト名*/
	@Attribute(name="host")
	private String host;
	/**jmxポート番号*/
	@Attribute(name="port")
	private int port;
	/**jmxアクセスID（現在未実装）*/
	@Attribute(name="id", required=false)
	private String id;
	/**jmxアクセスパスワード（現在未実装）*/
	@Attribute(name="password", required=false)
	private String password;

	/**CSV出力先*/
	@Attribute(name="csv_path")
	private String csvPath;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCsvPath() {
		return csvPath;
	}

	public void setCsvPath(String csvPath) {
		this.csvPath = csvPath;
	}

	@Override
	public String toString() {
		return "JvmInfo [name=" + name + ", host=" + host + ", port=" + port
				+ ", id=" + id + ", password=" + password + ", csvPath=" + csvPath + "]";
	}
}
