package com.ruoyi.bps.service;

//import com.ruoyi.bps.express.request.QueryTrackParam;
//import com.ruoyi.bps.express.response.QueryTrackResp;

import com.kuaidi100.sdk.request.QueryTrackParam;
import com.kuaidi100.sdk.response.QueryTrackResp;

import java.util.List;

public interface IExpressService {

    public List<QueryTrackResp>  QueryTrackExpressMultiList(List<QueryTrackParam> list);
    /**
     * 查询多条物流轨迹
     */
    public String QueryTrackExpressMulti(List<QueryTrackParam> list);

    /**
     * 查询物流轨迹
     */
    public String QueryTrackExpress(QueryTrackParam qt);

    /**
     * 订阅
     */
    public String SubscribeExpress();

    /**
     * 测试快递单号合集
     */
    public List<QueryTrackParam> GetTestQueryTrackParam();
}
