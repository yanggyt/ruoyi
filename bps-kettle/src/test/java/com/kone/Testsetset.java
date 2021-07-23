package com.kone;

import com.ruoyi.kettle.tools.KettleUtil;
import org.junit.Test;

public class Testsetset {


    @Test
    public void test1() throws Exception {
        KettleUtil.KettleEnv.init();
        KettleUtil util=new KettleUtil();
        util.callTrans("/","text",null,null);
    }
}
