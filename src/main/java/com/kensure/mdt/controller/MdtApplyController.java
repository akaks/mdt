package com.kensure.mdt.controller;

import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowInfo;
import co.kensure.frame.ResultRowsInfo;
import co.kensure.http.RequestUtils;
import co.kensure.mem.PageInfo;
import com.alibaba.fastjson.JSONObject;
import com.kensure.mdt.entity.*;
import com.kensure.mdt.entity.bo.MdtGradeReq;
import com.kensure.mdt.service.*;
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

/**
 * MDT申请
 */
@Controller
@RequestMapping(value = "mdtApply")
public class MdtApplyController {

	@Autowired
	private MdtApplyService mdtApplyService;

	@Autowired
	private MdtApplyDoctorService mdtApplyDoctorService;

	@Autowired
	private SysGradeService sysGradeService;

	@Autowired
	private MdtGradeItemService mdtGradeItemService;

	@Autowired
	private MdtApplyFeedbackService mdtApplyFeedbackService;

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

		List<MdtApply> list = mdtApplyService.selectList(page);
		long cont = mdtApplyService.selectListCount();

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
		MdtApply apply = JSONObject.parseObject(json.toJSONString(), MdtApply.class);
		mdtApplyService.save(apply);
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
		MdtApply apply = mdtApplyService.selectOne(id);
		return new ResultRowInfo(apply);
	}

	/**
	 * 审核
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "audit", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo audit(HttpServletRequest req, HttpServletResponse rep) {

		JSONObject json = RequestUtils.paramToJson(req);
		MdtApply apply = JSONObject.parseObject(json.toJSONString(), MdtApply.class);

		mdtApplyService.audit(apply);
		return new ResultInfo();
	}

	/**
	 * 从MDT团队中将专家选入MDT专家库
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addApplyDoctorFormTeam", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo addApplyDoctorFormTeam(HttpServletRequest req, HttpServletResponse rep) {

		Long teamInfoId = Long.parseLong(req.getParameter("teamInfoId"));
		Long applyId = Long.parseLong(req.getParameter("applyId"));

		mdtApplyService.addApplyDoctorFormTeam(teamInfoId, applyId);
		return new ResultInfo();
	}

	/**
	 * 添加MDT拟请专家
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addApplyDoctor", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo addApplyDoctor(HttpServletRequest req, HttpServletResponse rep) {

		JSONObject json = RequestUtils.paramToJson(req);
		MdtApplyDoctor entiy = JSONObject.parseObject(json.toJSONString(), MdtApplyDoctor.class);

		mdtApplyDoctorService.save(entiy);
		return new ResultInfo();
	}

	/**
	 * 查看MDT拟请专家
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getApplyDoctor", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo getApplyDoctor(HttpServletRequest req, HttpServletResponse rep) {

		Long id = Long.parseLong(req.getParameter("id"));
		MdtApplyDoctor entiy = mdtApplyDoctorService.selectOne(id);
		return new ResultRowInfo(entiy);
	}

	/**
	 * 查询MDT拟请专家
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listApplyDoctorByApplyId", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo listApplyDoctorByApplyId(HttpServletRequest req, HttpServletResponse rep) {

		Long applyId = Long.parseLong(req.getParameter("applyId"));

		List<MdtApplyDoctor> list = mdtApplyDoctorService.selectList(applyId);
		return new ResultRowsInfo(list, list.size());
	}

	/**
	 * 删除MDT拟请专家
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delApplyDoctorById", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo delApplyDoctorById(HttpServletRequest req, HttpServletResponse rep) {

		Long id = Long.parseLong(req.getParameter("id"));

		mdtApplyDoctorService.delete(id);
		return new ResultInfo();
	}


	/**
	 * 发送MDT通知短信
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "sendMsg", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo sendMsg(HttpServletRequest req, HttpServletResponse rep) {

		JSONObject json = RequestUtils.paramToJson(req);
		MdtApply apply = JSONObject.parseObject(json.toJSONString(), MdtApply.class);

		mdtApplyService.sendMsg(apply);
		return new ResultInfo();
	}


	/**
	 * 获取评分项目
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getGradeItem", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo getExpertGradeItem(HttpServletRequest req, HttpServletResponse rep) {

		String type = req.getParameter("type");

        List<SysGrade> list = sysGradeService.getExpertGradeItem(type);
        return new ResultRowsInfo(list);
	}

	/**
	 * 保存专家评分项目
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveExpertGrade", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo saveExpertGrade(HttpServletRequest req, HttpServletResponse rep, MdtGradeReq mdtGradeReq) {

		mdtGradeItemService.saveExpertGrade(mdtGradeReq);

        return new ResultInfo();
	}

	/**
	 * 保存组织科室评分项目
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveDeptGrade", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo saveDeptGrade(HttpServletRequest req, HttpServletResponse rep, MdtGradeReq mdtGradeReq) {

		mdtGradeItemService.saveDeptGrade(mdtGradeReq);

        return new ResultInfo();
	}

	/**
	 * 计算费用
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "calculateFee", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo calculateFee(HttpServletRequest req, HttpServletResponse rep, MdtGradeReq mdtGradeReq) {

		Long applyId = Long.parseLong(req.getParameter("applyId"));
        Long price = mdtApplyService.calculateFee(applyId);

        return new ResultRowInfo(price);
	}

	/**
	 * 查询反馈
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "selectFeedbackList", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo selectFeedbackList(HttpServletRequest req, HttpServletResponse rep, MdtGradeReq mdtGradeReq) {

		Long applyId = Long.parseLong(req.getParameter("applyId"));
		List<MdtApplyFeedback> list = mdtApplyFeedbackService.selectList(applyId);

		return new ResultRowsInfo(list, list.size());
	}

	/**
	 * 保存反馈
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveFeedback", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo saveFeedback(HttpServletRequest req, HttpServletResponse rep, MdtGradeReq mdtGradeReq) {


		JSONObject json = RequestUtils.paramToJson(req);
		MdtApplyFeedback obj = JSONObject.parseObject(json.toJSONString(), MdtApplyFeedback.class);

		mdtApplyFeedbackService.save(obj);

		return new ResultInfo();
	}

	/**
	 * 查看反馈
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getFeedback", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo getFeedback(HttpServletRequest req, HttpServletResponse rep, MdtGradeReq mdtGradeReq) {

		Long id = Long.parseLong(req.getParameter("id"));
		MdtApplyFeedback mdtApplyFeedback = mdtApplyFeedbackService.selectOne(id);

		return new ResultRowInfo(mdtApplyFeedback);
	}

	/**
	 * 删除反馈
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delFeedback", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo delFeedback(HttpServletRequest req, HttpServletResponse rep, MdtGradeReq mdtGradeReq) {

		Long id = Long.parseLong(req.getParameter("id"));
		mdtApplyFeedbackService.delete(id);

		return new ResultInfo();
	}














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
