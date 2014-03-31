package jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.handler.impl;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.Date;

import jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.handler.MXBeanHandler;

public class MXBeanHandlerImpl implements MXBeanHandler {

	private CsvDataDTO dto = new CsvDataDTO();
	@Override
	public void setData(Object from){
		if(from == null){return;}
		dto.setDatetime(new Date());
		
		if(from instanceof MemoryMXBean){
			this.setData((MemoryMXBean)from);
		} else if(from instanceof MemoryPoolMXBean){
			this.setData((MemoryPoolMXBean)from);
		} else if(from instanceof GarbageCollectorMXBean){
			this.setData((GarbageCollectorMXBean)from);
		}
	}
	
	private void setData(MemoryMXBean from){
		MemoryUsage heapUsage = from.getHeapMemoryUsage();
		MemoryUsage nonHeapUsage = from.getNonHeapMemoryUsage();
		int pendingFinalizationCount = from.getObjectPendingFinalizationCount();
		
		dto.setTotalHeapSpaceInit(heapUsage.getInit());
		dto.setTotalHeapSpaceUsed(heapUsage.getUsed());
		dto.setTotalHeapSpaceComitted(heapUsage.getCommitted());
		dto.setTotalHeapSpaceMax(heapUsage.getMax());
		
		dto.setTotalNonHeapSpaceInit(nonHeapUsage.getInit());
		dto.setTotalNonHeapSpaceUsed(nonHeapUsage.getUsed());
		dto.setTotalNonHeapSpaceComitted(nonHeapUsage.getCommitted());
		dto.setTotalNonHeapSpaceMax(nonHeapUsage.getMax());
		
		dto.setPendingFinalizationCount(pendingFinalizationCount);
	}
	
	private void setData(MemoryPoolMXBean from){
		
		MemoryUsage usage = from.getUsage();
		if("PS Survivor Space".equals(from.getName())){
			dto.setPsSurvivorSpaceInit(usage.getInit());
			dto.setPsSurvivorSpaceUsed(usage.getUsed());
			dto.setPsSurvivorSpaceComitted(usage.getCommitted());
			dto.setPsSurvivorSpaceMax(usage.getMax());
		} else if("PS Eden Space".equals(from.getName())){
			dto.setPsEdenSpaceInit(usage.getInit());
			dto.setPsEdenSpaceUsed(usage.getUsed());
			dto.setPsEdenSpaceComitted(usage.getCommitted());
			dto.setPsEdenSpaceMax(usage.getMax());
		} else if("PS Old Gen".equals(from.getName())){
			dto.setPsOldGenInit(usage.getInit());
			dto.setPsOldGenUsed(usage.getUsed());
			dto.setPsOldGenComitted(usage.getCommitted());
			dto.setPsOldGenMax(usage.getMax());
		} else if("PS Perm Gen".equals(from.getName())){
			dto.setPsPermGenInit(usage.getInit());
			dto.setPsPermGenUsed(usage.getUsed());
			dto.setPsPermGenComitted(usage.getCommitted());
			dto.setPsPermGenMax(usage.getMax());
		} else if("Code Cache".equals(from.getName())){
			dto.setCodeCacheInit(usage.getInit());
			dto.setCodeCacheUsed(usage.getUsed());
			dto.setCodeCacheComitted(usage.getCommitted());
			dto.setCodeCacheMax(usage.getMax());
		}
	}
	
	private void setData(GarbageCollectorMXBean from){
		
		if("PS MarkSweep".equals(from.getName())){
			dto.setPsMarkSweepCollectionTime(from.getCollectionTime());
			dto.setPsMarkSweepCount(from.getCollectionCount());
		} else if("PS Scavenge".equals(from.getName())){
			dto.setPsScavengeCollectionTime(from.getCollectionTime());
			dto.setPsScavengeCount(from.getCollectionCount());
		}
	}

	@Override
	public String getHeaderCsvString() {
		AnnotationProcessor ap = new AnnotationProcessor(dto);
		return ap.getHeaderCsvString();
	}

	@Override
	public String getDataCsvString() {
		AnnotationProcessor ap = new AnnotationProcessor(dto);
		return ap.getValueCsvString();

	}
	
}
