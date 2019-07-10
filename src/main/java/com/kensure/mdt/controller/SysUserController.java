package com.kensure.mdt.controller;

import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowInfo;
import co.kensure.frame.ResultRowsInfo;
import co.kensure.http.RequestUtils;
import co.kensure.mem.PageInfo;
import com.alibaba.fastjson.JSONObject;
import com.kensure.mdt.entity.SysUser;
import com.kensure.mdt.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "user")
public class SysUserController  extends BaseController {

	@Autowired
	private SysUserService sysUserService;

	@ResponseBody
	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo list(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		PageInfo page = JSONObject.parseObject(json.toJSONString(), PageInfo.class);

        List<SysUser> list = sysUserService.selectList(page);
		long cont = sysUserService.selectListCount(page);

		return new ResultRowsInfo(list, cont);
	}

	@ResponseBody
	@RequestMapping(value = "listAll", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo listAll(HttpServletRequest req, HttpServletResponse rep) {
        List<SysUser> list = sysUserService.selectList();
		return new ResultRowsInfo(list);
	}

	@ResponseBody
	@RequestMapping(value = "saveRole", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo save(Long userId, String checkedStr) {

		sysUserService.saveRole(userId, checkedStr);
		return new ResultInfo();
	}

	@ResponseBody
	@RequestMapping(value = "save", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo save(HttpServletRequest req, HttpServletResponse rep) {

		JSONObject json = RequestUtils.paramToJson(req);
		SysUser user = JSONObject.parseObject(json.toJSONString(), SysUser.class);
		sysUserService.save(user);
        return new ResultInfo();
	}

	@ResponseBody
	@RequestMapping(value = "get", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo get(HttpServletRequest req, HttpServletResponse rep) {

        Long id = Long.valueOf(req.getParameter("id"));
        SysUser user = sysUserService.selectOne(id);
        return new ResultRowInfo(user);
	}

}
