/*
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2019-6-25
 * 修改内容: 
 */
package com.kensure.mdt.service;

import co.kensure.mem.MapUtils;
import co.kensure.mem.PageInfo;
import com.kensure.mdt.dao.MdtApplyMapper;
import com.kensure.mdt.entity.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * MDT申请表服务实现类
 * @author fankd created on 2019-6-25
 * @since 
 */
@Service
public class MdtApplyService {
	
	@Resource
	private MdtApplyMapper dao;

	@Resource
	private MdtTeamInfoService mdtTeamInfoService;

	@Resource
	private MdtApplyDoctorService mdtApplyDoctorService;

	@Resource
	private SysFeeService sysFeeService;

	@Resource
	private SysMsgTemplateService sysMsgTemplateService;
    
    
    public MdtApply selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MdtApply> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MdtApply> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MdtApply obj){
		obj.setCreateTime(new Date());
		obj.setUpdateTime(new Date());
		return dao.insert(obj);
	}
	
	
	public boolean update(MdtApply obj){
		obj.setUpdateTime(new Date());
		return dao.update(obj);
	}
    
    public boolean updateByMap(Map<String, Object> params){
		return dao.updateByMap(params);
	}
    
    
	public boolean delete(Long id){
		return dao.delete(id);
	}	
	
    public boolean deleteMulti(Collection<Long> ids){
		return dao.deleteMulti(ids);
	}
    
    public boolean deleteByWhere(Map<String, Object> parameters){
		return dao.deleteByWhere(parameters);
	}


	public void save(MdtApply apply, AuthUser user) {

        MdtApply obj = selectOne(apply.getId());

        if (obj == null) {

        	if (apply.getApplyStatus() == null) {
				apply.setApplyStatus("0");  // "申请人申请" 状态
			}
			apply.setShare("0");  // "分享" 状态
			apply.setIsDelete("0");

			apply.setCreateDept(user.getDepartment());
			apply.setCreateUserid(user.getId());

			insert(apply);
		} else {

			update(apply);
		}
	}

	public List<MdtApply> selectList(PageInfo page) {

		Map<String, Object> parameters = MapUtils.genMap();
		MapUtils.putPageInfo(parameters, page);

		List<MdtApply> list = selectByWhere(parameters);
		return list;
	}

	public long selectListCount() {

		Map<String, Object> parameters = MapUtils.genMap();
		return selectCountByWhere(parameters);
	}

	/**
	 * 从MDT团队中将专家选入MDT专家库
	 * @param teamInfoId
	 * @param applyId
	 */
    public void addApplyDoctorFormTeam(Long teamInfoId, Long applyId) {

        MdtTeamInfo mdtTeamInfo = mdtTeamInfoService.selectOne(teamInfoId);

        mdtApplyDoctorService.addApplyDoctor(applyId, mdtTeamInfo);
    }

	/**
	 * 审核
	 * @param apply
	 */
	public void audit(MdtApply apply) {

		MdtApply obj = new MdtApply();
		obj.setId(apply.getId());

		// 科主任审核
		if (StringUtils.isNotBlank(apply.getAuditResult1())) {

			obj.setAuditResult1(apply.getAuditResult1());
			obj.setAuditOpinion1(apply.getAuditOpinion1());
			obj.setAuditPerson1(apply.getAuditPerson1());
			obj.setAuditTime1(apply.getAuditTime1());

			if ("1".equals(apply.getAuditResult1())) {

				obj.setApplyStatus("2");
			} else {

				obj.setApplyStatus("9");
			}
		}

		// 医务部主任审核
		if (StringUtils.isNotBlank(apply.getAuditResult2())) {

			obj.setAuditResult2(apply.getAuditResult2());
			obj.setAuditOpinion2(apply.getAuditOpinion2());
			obj.setAuditPerson2(apply.getAuditPerson2());
			obj.setAuditTime2(apply.getAuditTime2());

			if ("1".equals(apply.getAuditResult2())) {

				obj.setApplyStatus("3");
			} else {

				obj.setApplyStatus("9");
			}
		}

		update(obj);
	}

    /**
     * 送MDT短信
     * @param apply
     */
    public void sendMsg(MdtApply apply) {

        List<MdtApplyDoctor> mdtApplyDoctors = mdtApplyDoctorService.selectByApplyId(apply.getId());

        SysMsgTemplate template = sysMsgTemplateService.getMsgTemplate("1");
        String content = template.getContent();

        for (MdtApplyDoctor mdtApplyDoctor : mdtApplyDoctors) {

            String phone = mdtApplyDoctor.getPhone();

//            System.out.println(apply.getMdtDate());
//            System.out.println(apply.getMdtLocation());

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String mdtDate = sf.format(apply.getMdtDate());

            content = content.replace("｛专家名字｝", mdtApplyDoctor.getName());
            content = content.replace("｛MDT名称｝", apply.getDiseaseName());
            content = content.replace("｛MDT时间｝", mdtDate);
            content = content.replace("｛MDT申请地点｝", apply.getMdtLocation());

            System.out.println(phone);
            System.out.println(content);
        }
    }

	/**
	 * 计算费用
	 * @param applyId
	 */
	public Long calculateFee(Long applyId) {

		List<MdtApplyDoctor> mdtApplyDoctors = mdtApplyDoctorService.selectByApplyId(applyId);

        Set<String> departments = new TreeSet<>();

		for (MdtApplyDoctor mdtApplyDoctor : mdtApplyDoctors) {
            String department = mdtApplyDoctor.getDepartment();

            departments.add(department);
        }

        int departmentNum = departments.size();

        Long price = sysFeeService.calculateFee(departmentNum);

        return price;

    }

	/**
	 * 开启分享病例
	 * @param applyId
	 */
	public void share(Long applyId) {

		MdtApply apply = new MdtApply();
		apply.setId(applyId);
		apply.setShare("1");

		update(apply);
	}

	public void saveApplySummary(MdtApply obj) {

		update(obj);
	}



	/**
	 * 代办审核的
	 * @return
	 */
	public List<MdtApply> doSth(AuthUser user) {

		Map<String, Object> parameters = MapUtils.genMap();

		String applyStatus = "";

		if (user.getRoleIds().contains("5")) {
			applyStatus = "1";
		} else if (user.getRoleIds().contains("3")) {
			applyStatus = "2";
		} else if (user.getRoleIds().contains("7")) {
			applyStatus = "9";
		}

		parameters.put("applyStatus", applyStatus);
		parameters.put("isDelete", "0");

		List<MdtApply> list = selectByWhere(parameters);
		return list;
	}
}
