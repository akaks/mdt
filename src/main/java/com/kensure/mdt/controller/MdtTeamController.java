package com.kensure.mdt.controller;

import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowInfo;
import co.kensure.frame.ResultRowsInfo;
import co.kensure.http.RequestUtils;
import co.kensure.mem.PageInfo;
import com.alibaba.fastjson.JSONObject;
import com.kensure.mdt.entity.MdtTeam;
import com.kensure.mdt.entity.MdtTeamInfo;
import com.kensure.mdt.entity.query.MdtTeamQuery;
import com.kensure.mdt.service.MdtTeamInfoService;
import com.kensure.mdt.service.MdtTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * mdt 团队
 */
@Controller
@RequestMapping(value = "mdtTeam")
public class MdtTeamController {

	@Autowired
	private MdtTeamService mdtTeamService;

	@Autowired
	private MdtTeamInfoService mdtTeamInfoService;

	/**
	 * 分页查询
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findByPage", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo findByPage(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		PageInfo page = JSONObject.parseObject(json.toJSONString(), PageInfo.class);
		MdtTeamQuery query = JSONObject.parseObject(json.toJSONString(), MdtTeamQuery.class);

		List<MdtTeam> list = mdtTeamService.selectList(page, query);
		long cont = mdtTeamService.selectListCount(query);

		return new ResultRowsInfo(list, cont);
	}

	/**
	 * 新增
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo save(HttpServletRequest req, HttpServletResponse rep) {

		JSONObject json = RequestUtils.paramToJson(req);
		MdtTeam team = JSONObject.parseObject(json.toJSONString(), MdtTeam.class);
		mdtTeamService.save(team);
		return new ResultInfo();
	}

	/**
	 * 查看
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "get", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo get(HttpServletRequest req, HttpServletResponse rep) {

		Long id = Long.parseLong(req.getParameter("id"));
		MdtTeam team = mdtTeamService.selectOne(id);
		return new ResultRowInfo(team);
	}


	/**
	 * 查询所有  MDT团队基本信息（多人明细）
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findTeamInfoByTeamId", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo findTeamInfoByTeamId(HttpServletRequest req, HttpServletResponse rep) {

		Long teamId = Long.parseLong(req.getParameter("teamId"));
		List<MdtTeamInfo> list = mdtTeamInfoService.selectList(teamId);
		long cont = mdtTeamInfoService.selectListCount(teamId);

		return new ResultRowsInfo(list, cont);
	}


	/**
	 * 保存  MDT团队基本信息（多人明细）
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveTeamInfo", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo saveTeamInfo(HttpServletRequest req, HttpServletResponse rep) {

		JSONObject json = RequestUtils.paramToJson(req);
		MdtTeamInfo team = JSONObject.parseObject(json.toJSONString(), MdtTeamInfo.class);
		mdtTeamInfoService.save(team);
		return new ResultInfo();
	}

	/**
	 * 查看 MDT团队基本信息（多人明细）
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getTeamInfo", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo getTeamInfo(HttpServletRequest req, HttpServletResponse rep) {

		Long id = Long.parseLong(req.getParameter("id"));
		MdtTeamInfo team = mdtTeamInfoService.selectOne(id);
		return new ResultRowInfo(team);
	}


	/**
	 * 删除 MDT团队基本信息（多人明细）
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delTeamInfo", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo delete(HttpServletRequest req, HttpServletResponse rep) {

		Long id = Long.parseLong(req.getParameter("id"));
		mdtTeamInfoService.delete(id);
		return new ResultInfo();
	}
}
