package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.handler.impl;

import java.util.Date;

import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.annotation.Column;

/**MXBeanから取得した情報をCSVへ出力するためのDTO
 * ・・・・・・うん、イケてないって言いたい気分は良く分かる。
 * */
class CsvDataDTO {
	
	CsvDataDTO(){}
	
	@Column(name = "datetime", format="yyyy-MM-dd HH:mm:ss")
	private Date datetime;
	
	@Column(name = "Total Heap Space init")
	private long totalHeapSpaceInit;
	@Column(name = "Total Heap Space used")
	private long totalHeapSpaceUsed;
	@Column(name = "Total Heap Space committed")
	private long totalHeapSpaceComitted;
	@Column(name = "Total Heap Space max")
	private long totalHeapSpaceMax;
	
	@Column(name = "Total NonHeap Space init")
	private long totalNonHeapSpaceInit;
	@Column(name = "Total NonHeap Space used")
	private long totalNonHeapSpaceUsed;
	@Column(name = "Total NonHeap Space committed")
	private long totalNonHeapSpaceComitted;
	@Column(name = "Total NonHeap Space max")
	private long totalNonHeapSpaceMax;
	
	@Column(name = "pending Finalization Count")
	private long pendingFinalizationCount;
	
	
	@Column(name = "PS Survivor Space init")
	private long psSurvivorSpaceInit;
	@Column(name = "PS Survivor Space used")
	private long psSurvivorSpaceUsed;
	@Column(name = "PS Survivor Space committed")
	private long psSurvivorSpaceComitted;
	@Column(name = "PS Survivor Space max")
	private long psSurvivorSpaceMax;
	
	@Column(name = "PS Eden Space init")
	private long psEdenSpaceInit;
	@Column(name = "PS Eden Space used")
	private long psEdenSpaceUsed;
	@Column(name = "PS Eden Space committed")
	private long psEdenSpaceComitted;
	@Column(name = "PS Eden Space max")
	private long psEdenSpaceMax;
	
	@Column(name = "PS Old Gen init")
	private long psOldGenInit;
	@Column(name = "PS Old Gen used")
	private long psOldGenUsed;
	@Column(name = "PS Old Gen committed")
	private long psOldGenComitted;
	@Column(name = "PS Old Gen max")
	private long psOldGenMax;
	
	@Column(name = "PS Perm Gen init")
	private long psPermGenInit;
	@Column(name = "PS Perm Gen used")
	private long psPermGenUsed;
	@Column(name = "PS Perm Gen committed")
	private long psPermGenComitted;
	@Column(name = "PS Perm Gen max")
	private long psPermGenMax;
	
	@Column(name = "Code Cache init")
	private long codeCacheInit;
	@Column(name = "Code Cache used")
	private long codeCacheUsed;
	@Column(name = "Code Cache committed")
	private long codeCacheComitted;
	@Column(name = "Code Cache max")
	private long codeCacheMax;
	
	@Column(name = "PS MarkSweep count")
	private long psMarkSweepCount;
	@Column(name = "PS MarkSweep collectionTime")
	private long psMarkSweepCollectionTime;
	@Column(name = "PS Scavenge count")
	private long psScavengeCount;
	@Column(name = "PS Scavenge collectionTime")
	private long psScavengeCollectionTime;
	
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public long getTotalHeapSpaceInit() {
		return totalHeapSpaceInit;
	}
	public void setTotalHeapSpaceInit(long totalHeapSpaceInit) {
		this.totalHeapSpaceInit = totalHeapSpaceInit;
	}
	public long getTotalHeapSpaceUsed() {
		return totalHeapSpaceUsed;
	}
	public void setTotalHeapSpaceUsed(long totalHeapSpaceUsed) {
		this.totalHeapSpaceUsed = totalHeapSpaceUsed;
	}
	public long getTotalHeapSpaceComitted() {
		return totalHeapSpaceComitted;
	}
	public void setTotalHeapSpaceComitted(long totalHeapSpaceComitted) {
		this.totalHeapSpaceComitted = totalHeapSpaceComitted;
	}
	public long getTotalHeapSpaceMax() {
		return totalHeapSpaceMax;
	}
	public void setTotalHeapSpaceMax(long totalHeapSpaceMax) {
		this.totalHeapSpaceMax = totalHeapSpaceMax;
	}
	public long getTotalNonHeapSpaceInit() {
		return totalNonHeapSpaceInit;
	}
	public void setTotalNonHeapSpaceInit(long totalNonHeapSpaceInit) {
		this.totalNonHeapSpaceInit = totalNonHeapSpaceInit;
	}
	public long getTotalNonHeapSpaceUsed() {
		return totalNonHeapSpaceUsed;
	}
	public void setTotalNonHeapSpaceUsed(long totalNonHeapSpaceUsed) {
		this.totalNonHeapSpaceUsed = totalNonHeapSpaceUsed;
	}
	public long getTotalNonHeapSpaceComitted() {
		return totalNonHeapSpaceComitted;
	}
	public void setTotalNonHeapSpaceComitted(long totalNonHeapSpaceComitted) {
		this.totalNonHeapSpaceComitted = totalNonHeapSpaceComitted;
	}
	public long getTotalNonHeapSpaceMax() {
		return totalNonHeapSpaceMax;
	}
	public void setTotalNonHeapSpaceMax(long totalNonHeapSpaceMax) {
		this.totalNonHeapSpaceMax = totalNonHeapSpaceMax;
	}
	public long getPendingFinalizationCount() {
		return pendingFinalizationCount;
	}
	public void setPendingFinalizationCount(long pendingFinalizationCount) {
		this.pendingFinalizationCount = pendingFinalizationCount;
	}
	public long getPsSurvivorSpaceInit() {
		return psSurvivorSpaceInit;
	}
	public void setPsSurvivorSpaceInit(long psSurvivorSpaceInit) {
		this.psSurvivorSpaceInit = psSurvivorSpaceInit;
	}
	public long getPsSurvivorSpaceUsed() {
		return psSurvivorSpaceUsed;
	}
	public void setPsSurvivorSpaceUsed(long psSurvivorSpaceUsed) {
		this.psSurvivorSpaceUsed = psSurvivorSpaceUsed;
	}
	public long getPsSurvivorSpaceComitted() {
		return psSurvivorSpaceComitted;
	}
	public void setPsSurvivorSpaceComitted(long psSurvivorSpaceComitted) {
		this.psSurvivorSpaceComitted = psSurvivorSpaceComitted;
	}
	public long getPsSurvivorSpaceMax() {
		return psSurvivorSpaceMax;
	}
	public void setPsSurvivorSpaceMax(long psSurvivorSpaceMax) {
		this.psSurvivorSpaceMax = psSurvivorSpaceMax;
	}
	public long getPsEdenSpaceInit() {
		return psEdenSpaceInit;
	}
	public void setPsEdenSpaceInit(long psEdenSpaceInit) {
		this.psEdenSpaceInit = psEdenSpaceInit;
	}
	public long getPsEdenSpaceUsed() {
		return psEdenSpaceUsed;
	}
	public void setPsEdenSpaceUsed(long psEdenSpaceUsed) {
		this.psEdenSpaceUsed = psEdenSpaceUsed;
	}
	public long getPsEdenSpaceComitted() {
		return psEdenSpaceComitted;
	}
	public void setPsEdenSpaceComitted(long psEdenSpaceComitted) {
		this.psEdenSpaceComitted = psEdenSpaceComitted;
	}
	public long getPsEdenSpaceMax() {
		return psEdenSpaceMax;
	}
	public void setPsEdenSpaceMax(long psEdenSpaceMax) {
		this.psEdenSpaceMax = psEdenSpaceMax;
	}
	public long getPsOldGenInit() {
		return psOldGenInit;
	}
	public void setPsOldGenInit(long psOldGenInit) {
		this.psOldGenInit = psOldGenInit;
	}
	public long getPsOldGenUsed() {
		return psOldGenUsed;
	}
	public void setPsOldGenUsed(long psOldGenUsed) {
		this.psOldGenUsed = psOldGenUsed;
	}
	public long getPsOldGenComitted() {
		return psOldGenComitted;
	}
	public void setPsOldGenComitted(long psOldGenComitted) {
		this.psOldGenComitted = psOldGenComitted;
	}
	public long getPsOldGenMax() {
		return psOldGenMax;
	}
	public void setPsOldGenMax(long psOldGenMax) {
		this.psOldGenMax = psOldGenMax;
	}
	public long getPsPermGenInit() {
		return psPermGenInit;
	}
	public void setPsPermGenInit(long psPermGenInit) {
		this.psPermGenInit = psPermGenInit;
	}
	public long getPsPermGenUsed() {
		return psPermGenUsed;
	}
	public void setPsPermGenUsed(long psPermGenUsed) {
		this.psPermGenUsed = psPermGenUsed;
	}
	public long getPsPermGenComitted() {
		return psPermGenComitted;
	}
	public void setPsPermGenComitted(long psPermGenComitted) {
		this.psPermGenComitted = psPermGenComitted;
	}
	public long getPsPermGenMax() {
		return psPermGenMax;
	}
	public void setPsPermGenMax(long psPermGenMax) {
		this.psPermGenMax = psPermGenMax;
	}
	public long getCodeCacheInit() {
		return codeCacheInit;
	}
	public void setCodeCacheInit(long codeCacheInit) {
		this.codeCacheInit = codeCacheInit;
	}
	public long getCodeCacheUsed() {
		return codeCacheUsed;
	}
	public void setCodeCacheUsed(long codeCacheUsed) {
		this.codeCacheUsed = codeCacheUsed;
	}
	public long getCodeCacheComitted() {
		return codeCacheComitted;
	}
	public void setCodeCacheComitted(long codeCacheComitted) {
		this.codeCacheComitted = codeCacheComitted;
	}
	public long getCodeCacheMax() {
		return codeCacheMax;
	}
	public void setCodeCacheMax(long codeCacheMax) {
		this.codeCacheMax = codeCacheMax;
	}
	public long getPsMarkSweepCount() {
		return psMarkSweepCount;
	}
	public void setPsMarkSweepCount(long psMarkSweepCount) {
		this.psMarkSweepCount = psMarkSweepCount;
	}
	public long getPsMarkSweepCollectionTime() {
		return psMarkSweepCollectionTime;
	}
	public void setPsMarkSweepCollectionTime(long psMarkSweepCollectionTime) {
		this.psMarkSweepCollectionTime = psMarkSweepCollectionTime;
	}
	public long getPsScavengeCount() {
		return psScavengeCount;
	}
	public void setPsScavengeCount(long psScavengeCount) {
		this.psScavengeCount = psScavengeCount;
	}
	public long getPsScavengeCollectionTime() {
		return psScavengeCollectionTime;
	}
	public void setPsScavengeCollectionTime(long psScavengeCollectionTime) {
		this.psScavengeCollectionTime = psScavengeCollectionTime;
	}
	
}
