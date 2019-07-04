package com.kensure.mdt.controller;

import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowInfo;
import co.kensure.frame.ResultRowsInfo;
import co.kensure.http.RequestUtils;
import co.kensure.mem.PageInfo;
import com.alibaba.fastjson.JSONObject;
import com.kensure.mdt.entity.SysFee;
import com.kensure.mdt.entity.SysGrade;
import com.kensure.mdt.entity.SysOrg;
import com.kensure.mdt.service.SysFeeService;
import com.kensure.mdt.service.SysGradeService;
import com.kensure.mdt.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统设置
 */
@Controller
@RequestMapping(value = "set")
public class SysSetController {

	@Autowired
	private SysFeeService sysFeeService;

	@Autowired
	private SysGradeService sysGradeService;

	/**
	 * 查询收费情况设置
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "selectSysFee", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo selectSysFee(HttpServletRequest req, HttpServletResponse rep) {
        List<SysFee> list = sysFeeService.selectList();

		return new ResultRowsInfo(list, list.size());
	}

	/**
	  查看收费情况设置
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getSysFee", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo getSysFee(HttpServletRequest req, HttpServletResponse rep) {

		Long id =Long.parseLong( req.getParameter("id"));
        SysFee sysFee = sysFeeService.selectOne(id);

        return new ResultRowInfo(sysFee);
	}

	/**
	 * 保存收费情况设置
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "saveSysFee", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo saveSysFee(HttpServletRequest req, HttpServletResponse rep) {

        JSONObject json = RequestUtils.paramToJson(req);
        SysFee fee = JSONObject.parseObject(json.toJSONString(), SysFee.class);
        sysFeeService.save(fee);
        return new ResultInfo();
	}

	/**
	 * 删除收费情况设置
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delSysFee", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo delSysFee(HttpServletRequest req, HttpServletResponse rep) {

		Long id =Long.parseLong( req.getParameter("id"));
		sysFeeService.delete(id);

		return new ResultInfo();
	}


    /**
     * 查询评分项设置
     * @param req
     * @param rep
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectSysGrade", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
    public ResultInfo selectSysGrade(HttpServletRequest req, HttpServletResponse rep) {
        List<SysGrade> list = sysGradeService.selectList();

        return new ResultRowsInfo(list, list.size());
    }


    /**
     查看收费情况设置
     * @param req
     * @param rep
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getSysGrade", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
    public ResultInfo getSysGrade(HttpServletRequest req, HttpServletResponse rep) {

        Long id =Long.parseLong( req.getParameter("id"));
        SysGrade sysGrade = sysGradeService.selectOne(id);

        return new ResultRowInfo(sysGrade);
    }

    /**
     * 保存收费情况设置
     * @param req
     * @param rep
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveSysGrade", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
    public ResultInfo saveSysGrade(HttpServletRequest req, HttpServletResponse rep) {

        JSONObject json = RequestUtils.paramToJson(req);
        SysGrade grade = JSONObject.parseObject(json.toJSONString(), SysGrade.class);
        sysGradeService.save(grade);
        return new ResultInfo();
    }

    /**
     * 删除收费情况设置
     * @param req
     * @param rep
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delSysGrade", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
    public ResultInfo delSysGrade(HttpServletRequest req, HttpServletResponse rep) {

        Long id =Long.parseLong( req.getParameter("id"));
        sysGradeService.delete(id);

        return new ResultInfo();
    }

}
