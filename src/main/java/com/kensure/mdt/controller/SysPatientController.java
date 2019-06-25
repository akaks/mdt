package com.kensure.mdt.controller;

import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowInfo;
import co.kensure.frame.ResultRowsInfo;
import co.kensure.http.RequestUtils;
import co.kensure.mem.PageInfo;
import com.alibaba.fastjson.JSONObject;
import com.kensure.mdt.entity.SysPatient;
import com.kensure.mdt.service.SysPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "patient")
public class SysPatientController {

	@Autowired
	private SysPatientService sysPatientService;

	@ResponseBody
	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo list(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		PageInfo page = JSONObject.parseObject(json.toJSONString(), PageInfo.class);

        List<SysPatient> list = sysPatientService.selectList(page);
		long cont = sysPatientService.selectListCount(page);

		return new ResultRowsInfo(list, cont);
	}

	@ResponseBody
	@RequestMapping(value = "save", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo save(HttpServletRequest req, HttpServletResponse rep) {

		JSONObject json = RequestUtils.paramToJson(req);
		SysPatient patient = JSONObject.parseObject(json.toJSONString(), SysPatient.class);
		sysPatientService.save(patient);
        return new ResultInfo();
	}

	@ResponseBody
	@RequestMapping(value = "get", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo get(HttpServletRequest req, HttpServletResponse rep) {

        Long id = Long.valueOf(req.getParameter("id"));
		SysPatient patient = sysPatientService.selectOne(id);
        return new ResultRowInfo(patient);
	}

	@ResponseBody
	@RequestMapping(value = "delete", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo delete(HttpServletRequest req, HttpServletResponse rep) {

        Long id = Long.valueOf(req.getParameter("id"));
		sysPatientService.delete(id);
        return new ResultInfo();
	}

}
