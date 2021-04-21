package com.ruoyi.web.vo.draw;

import com.ruoyi.web.vo.Result;
import com.sinosoft.activity.domain.DrawRecord;

import java.util.List;

public class PrizeResult extends Result {
    private List<Prize> prizes;

    private List<DrawRecord>  record;

    public List<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    public List<DrawRecord> getRecord() {
        return record;
    }

    public void setRecord(List<DrawRecord> record) {
        this.record = record;
    }
}
