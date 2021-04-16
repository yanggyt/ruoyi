package com.ruoyi.service;

import com.ruoyi.dto.DrawActivityRequest;
import com.ruoyi.thread.AwardThread;
import com.ruoyi.thread.ThreadPool;
import com.sinosoft.activity.domain.DrawInfo;
import com.sinosoft.activity.domain.DrawRecord;

public class AwardService {
	public void award(DrawRecord gtDrawRecord, DrawActivityRequest request, DrawInfo gtDrawInfo, String mobile, String awardMethod) {
		ThreadPool.awardExecutorService.submit(new AwardThread(gtDrawRecord, request,mobile,awardMethod));
	}
}
