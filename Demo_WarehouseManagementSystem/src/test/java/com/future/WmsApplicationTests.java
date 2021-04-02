package com.future;

import com.future.report.model.vo.SalesInfoForReportBarVo;
import com.future.yw.mapper.YwSalesMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WmsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private YwSalesMapper ywSalesMapper;

    @Test
    void testSalesInfoForReport1BarVo() {
        List<SalesInfoForReportBarVo> infolist = ywSalesMapper.findSalesInfoForReport1BarVo();
        System.out.println(infolist);
    }

}
