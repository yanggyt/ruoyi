package com.ruoyi.content.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 说明：报表管理
 *
 * @author Ma.C
 * @date 2018年8月10日
 */
@Controller
@RequestMapping("/report")
public class ReportController {
    private final static Logger logger = LoggerFactory.getLogger(ReportController.class);

//	@RequestMapping("/reportList")
//	public void reportList(HttpServletRequest request, HttpServletResponse response) {
//		Thread.currentThread().setName(UUID.randomUUID().toString());
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("/micro/report/report");
//		logger.info("进入报表管理界面！");
//		try {
//			response.sendRedirect("http://sales-int.ihxlife.com/gv/dist/index.html");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return;
//	}
//	

}
