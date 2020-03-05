package cn.com.infosouth.arj21.controller.csvmanager;

import java.util.List;

import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.infosouth.arj21.domain.InfoFlight;
import cn.com.infosouth.arj21.service.IInfoFlightService;
import cn.com.infosouth.common.annotation.Log;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.common.core.domain.AjaxResult;
import cn.com.infosouth.common.core.page.TableDataInfo;
import cn.com.infosouth.common.enums.BusinessType;
import cn.com.infosouth.common.utils.poi.ExcelUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 航班信息Controller
 * 
 * @author kxnf
 * @date 2020-03-04
 */
@Controller
@RequestMapping("/arj21/csvmanager")
public class InfoFlightController extends BaseController {
	private String prefix = "arj21/csvmanager";

	@Autowired
	private IInfoFlightService infoFlightService;

	@RequiresPermissions("arj21:flight:view")
	@GetMapping()
	public String flight() {
		return prefix + "/flight";
	}

	/**
	 * 查询航班信息列表
	 */
	@RequiresPermissions("arj21:flight:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(InfoFlight infoFlight) {
		startPage();
		List<InfoFlight> list = infoFlightService.selectInfoFlightList(infoFlight);
		return getDataTable(list);
	}

	/**
	 * 导出航班信息列表
	 */
	@RequiresPermissions("arj21:flight:export")
	@Log(title = "航班信息", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(InfoFlight infoFlight) {
		List<InfoFlight> list = infoFlightService.selectInfoFlightList(infoFlight);
		ExcelUtil<InfoFlight> util = new ExcelUtil<InfoFlight>(InfoFlight.class);
		return util.exportExcel(list, "flight");
	}

	/**
	 * 新增航班信息
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存航班信息
	 */
	@RequiresPermissions("arj21:flight:add")
	@Log(title = "航班信息", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(InfoFlight infoFlight) {
		return toAjax(infoFlightService.insertInfoFlight(infoFlight));
	}

	/**
	 * 修改航班信息
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap) {
		InfoFlight infoFlight = infoFlightService.selectInfoFlightById(id);
		mmap.put("infoFlight", infoFlight);
		return prefix + "/edit";
	}

	/**
	 * 修改保存航班信息
	 */
	@RequiresPermissions("arj21:flight:edit")
	@Log(title = "航班信息", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(InfoFlight infoFlight) {
		return toAjax(infoFlightService.updateInfoFlight(infoFlight));
	}

	/**
	 * 删除航班信息
	 */
	@RequiresPermissions("arj21:flight:remove")
	@Log(title = "航班信息", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(infoFlightService.deleteInfoFlightByIds(ids));
	}





}
