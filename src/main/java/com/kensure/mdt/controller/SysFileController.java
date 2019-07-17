package com.kensure.mdt.controller;

import co.kensure.frame.ResultInfo;
import com.kensure.mdt.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "file")
public class SysFileController extends BaseController {

	@Autowired
	private SysFileService sysFileService;

    /**
     * upload
     * @param req
     * @param rep
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "upload", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	public ResultInfo upload(HttpServletRequest req, HttpServletResponse rep, MultipartFile file) {

		Long fileId = Long.parseLong(req.getParameter("fileId"));
		sysFileService.uploadFile(file, fileId);

        return new ResultInfo();
	}


}
