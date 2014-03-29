package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.handler;

public interface MXBeanHandler {
	/**MXBeanをセットします。セットされたMXBeanは実装クラス内部で処理され、setDataCsvStringおよびgetHeaderCsvString
	 * メソッドの戻り値を生成するために使用されます*/
	public void setData(Object from);
	/**ヘッダ文字列を戻します*/
	public String getHeaderCsvString();
	/**CSVデータ文字列を戻します*/
	public String getDataCsvString();

}
