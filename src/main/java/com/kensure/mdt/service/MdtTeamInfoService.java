/*
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2019-6-23
 * 修改内容: 
 */
package com.kensure.mdt.service;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.mem.MapUtils;
import com.kensure.mdt.dao.MdtTeamInfoMapper;
import com.kensure.mdt.entity.MdtApplyDoctor;
import com.kensure.mdt.entity.MdtTeamInfo;
import com.kensure.mdt.entity.SysOrg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * MDT团队基本信息表服务实现类
 * @author fankd created on 2019-6-23
 * @since 
 */
@Service
public class MdtTeamInfoService {
	
	@Resource
	private MdtTeamInfoMapper dao;

	@Resource
	private SysOrgService sysOrgService;
    
    
    public MdtTeamInfo selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MdtTeamInfo> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MdtTeamInfo> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MdtTeamInfo obj){
		obj.setCreateTime(new Date());
		obj.setUpdateTime(new Date());
		return dao.insert(obj);
	}
	
	
	public boolean update(MdtTeamInfo obj){
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


	public void save(MdtTeamInfo teamInfo) {

    	if (teamInfo.getId() == null) {

			List<MdtTeamInfo> list = selectByTeamIdAndUserId(teamInfo.getTeamId(), teamInfo.getUserId());
			if (list.size() > 0) {
				BusinessExceptionUtil.threwException("该专家已存在不可重复添加!");
			}

			insert(teamInfo);
		} else {

    		update(teamInfo);
		}
	}

	public List<MdtTeamInfo> selectByTeamIdAndUserId(Long teamId, Long userId) {

		Map<String, Object> parameters = MapUtils.genMap("teamId", teamId, "userId", userId);
		List<MdtTeamInfo> list = selectByWhere(parameters);
		return list;
	}

	public List<MdtTeamInfo> selectList(Long teamId) {

		Map<String, Object> parameters = MapUtils.genMap("teamId", teamId);

		List<MdtTeamInfo> list = selectByWhere(parameters);

		for (MdtTeamInfo mdtTeamInfo : list) {
			SysOrg org = sysOrgService.selectOne(mdtTeamInfo.getDepartment());
			if (org != null) {
				mdtTeamInfo.setDepartment(org.getName());
			}
		}

		return list;
	}

	public long selectListCount(Long teamId) {

		Map<String, Object> parameters = MapUtils.genMap("teamId", teamId);
		return selectCountByWhere(parameters);
	}

}
