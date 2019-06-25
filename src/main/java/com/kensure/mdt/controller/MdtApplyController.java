package com.kensure.mdt.controller;

import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowsInfo;
import co.kensure.http.RequestUtils;
import co.kensure.mem.PageInfo;
import com.alibaba.fastjson.JSONObject;
import com.kensure.mdt.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "mdtApply")
public class MdtApplyController {

	@Autowired
	private SysOrgService sysOrgService;

	@ResponseBody
	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo list(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		PageInfo page = JSONObject.parseObject(json.toJSONString(), PageInfo.class);

		List list = new ArrayList();

		Map map = new HashMap<>();
		map.put("a1","住院");
		map.put("a2","吴可");
		map.put("a3","MDT申请表");
		map.put("a4","男");
		map.put("a5","2001-01-03");
		map.put("a6","17728162821");
		map.put("a7","心血管科");
		map.put("a8","2019-06-01");
		map.put("a9","2019121212");
		map.put("a10","张力");


		list.add(map);

		return new ResultRowsInfo(list, list.size());

//        List<SysOrg> list = sysOrgService.selectList(page);
//		long cont = sysOrgService.selectListCount();
//
//		return new ResultRowsInfo(list, cont);
	}


	@ResponseBody
	@RequestMapping(value = "list3", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo list3(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		PageInfo page = JSONObject.parseObject(json.toJSONString(), PageInfo.class);

		List list = new ArrayList();

		Map map = new HashMap<>();
		map.put("a1","住院");
		map.put("a2","心血管科");
		map.put("a3","赵三");
		map.put("a4","9812421321");
		map.put("a5","男");
		map.put("a6","2001-01-03");
		map.put("a7","17728162821");
		map.put("a8","2019-06-21");
		map.put("a9","3楼805");
		map.put("a10","MDT申请表");


		list.add(map);

		return new ResultRowsInfo(list, list.size());
	}
	@ResponseBody
	@RequestMapping(value = "list4", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo list4(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		PageInfo page = JSONObject.parseObject(json.toJSONString(), PageInfo.class);

		List list = new ArrayList();

		Map map = new HashMap<>();
		map.put("a1","001");
		map.put("a2","厉科");
		map.put("a3","心脑血管科");
		map.put("a4","主任医师");
		map.put("a5","139201921829");

		list.add(map);

		return new ResultRowsInfo(list, list.size());
	}

	@ResponseBody
	@RequestMapping(value = "list5", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo list5(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		PageInfo page = JSONObject.parseObject(json.toJSONString(), PageInfo.class);

		List list = new ArrayList();

		Map map = new HashMap<>();
		map.put("a1","厉科");
		map.put("a2","2019-06-22 11:01:09");
		map.put("a3","已回复");
		map.put("a4","5");

		list.add(map);

		Map map2 = new HashMap<>();
		map2.put("a1","吴杰");
		map2.put("a2","2019-06-22 11:55:11");
		map2.put("a3","已回复");
		map2.put("a4","5");

		list.add(map2);

		return new ResultRowsInfo(list, list.size());
	}

}
