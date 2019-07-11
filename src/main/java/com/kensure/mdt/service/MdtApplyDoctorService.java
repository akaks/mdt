package com.kensure.mdt.service;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.mem.MapUtils;
import co.kensure.mem.PageInfo;
import com.kensure.mdt.dao.MdtApplyDoctorMapper;
import com.kensure.mdt.entity.*;
import com.kensure.mdt.entity.resp.ExpertGradeList;
import com.kensure.mdt.service.MdtApplyDoctorService;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


/**
 * MDT参加专家表服务实现类
 */
@Service
public class MdtApplyDoctorService {
	
	@Resource
	private MdtApplyDoctorMapper dao;

	@Resource
	private SysOrgService sysOrgService;

	@Resource
	private MdtGradeItemService mdtGradeItemService;
    
    
    public MdtApplyDoctor selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MdtApplyDoctor> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MdtApplyDoctor> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MdtApplyDoctor obj){
		return dao.insert(obj);
	}
	
	
	public boolean update(MdtApplyDoctor obj){
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

	/**
	 * 从MDT团队中将专家选入MDT专家库
	 * @param applyId
	 * @param mdtTeamInfo
	 */
	public void addApplyDoctor(Long applyId, MdtTeamInfo mdtTeamInfo) {

		MdtApplyDoctor entiy = new MdtApplyDoctor();

		entiy.setApplyId(applyId);
		entiy.setUserId(mdtTeamInfo.getUserId());
		entiy.setName(mdtTeamInfo.getName());
		entiy.setDepartment(mdtTeamInfo.getDepartment());
		entiy.setTitle(mdtTeamInfo.getTitle());
		entiy.setPhone(mdtTeamInfo.getPhone());
		entiy.setPhoneCornet(mdtTeamInfo.getPhoneCornet());

		save(entiy);
	}

    /**
     * 保存MDT拟请专家
     * @param entiy
     */
	public void save(MdtApplyDoctor entiy) {

        List<MdtApplyDoctor> list = selectByApplyIdAndUserId(entiy.getApplyId(), entiy.getUserId());
        if (list.size() > 0) {

            BusinessExceptionUtil.threwException("该专家已存在不可重复添加!");
        }

        entiy.setCreateTime(new Date());
        entiy.setUpdateTime(new Date());
        insert(entiy);
    }

	public List<MdtApplyDoctor> selectByApplyId(Long applyId) {

		Map<String, Object> parameters = MapUtils.genMap("applyId", applyId);
		List<MdtApplyDoctor> list = selectByWhere(parameters);
		return list;
	}

	public List<MdtApplyDoctor> selectByApplyIdAndUserId(Long applyId, Long userId) {

		Map<String, Object> parameters = MapUtils.genMap("applyId", applyId, "userId", userId);
		List<MdtApplyDoctor> list = selectByWhere(parameters);
		return list;
	}

	/**
	 * 根据MDT申请id查询 MDT专家库
	 * @param applyId
	 * @return
	 */
	public List<MdtApplyDoctor> selectList(Long applyId) {

		Map<String, Object> parameters = MapUtils.genMap("applyId", applyId);

		List<MdtApplyDoctor> list = selectByWhere(parameters);

		for (MdtApplyDoctor mdtApplyDoctor : list) {
			SysOrg org = sysOrgService.selectOne(mdtApplyDoctor.getDepartment());
			if (org != null) {
				mdtApplyDoctor.setDepartment(org.getName());
			}
		}

		return list;
	}

    public List<ExpertGradeList> listExpertGrade(Long applyId) {

		Map<String, Object> parameters = MapUtils.genMap("applyId", applyId);

		List<MdtApplyDoctor> list = selectByWhere(parameters);

        List<ExpertGradeList> expertGradeLists = new ArrayList<>();

        for (MdtApplyDoctor mdtApplyDoctor : list) {
			SysOrg org = sysOrgService.selectOne(mdtApplyDoctor.getDepartment());
			if (org != null) {
				mdtApplyDoctor.setDepartment(org.getName());
			}

			ExpertGradeList expertGrade = new ExpertGradeList();
			BeanUtils.copyProperties(mdtApplyDoctor, expertGrade);


            List<MdtGradeItem> gradeItemList = mdtGradeItemService.getMdtGradeItem("1", applyId, expertGrade.getUserId());
            expertGrade.setList(gradeItemList);

            if (!gradeItemList.isEmpty()) {
                expertGrade.setReply("已回复");
                expertGrade.setReplyTime(gradeItemList.get(0).getCreateTime());
            } else {
                expertGrade.setReply("未回复");
            }

            expertGradeLists.add(expertGrade);
        }

		return expertGradeLists;
    }
}
