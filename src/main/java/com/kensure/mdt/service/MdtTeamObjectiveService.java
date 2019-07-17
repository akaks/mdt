
package com.kensure.mdt.service;

import co.kensure.mem.MapUtils;
import com.kensure.mdt.dao.MdtTeamObjectiveMapper;
import com.kensure.mdt.entity.AuthUser;
import com.kensure.mdt.entity.MdtTeamObjective;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * MDT团队建设责任目标服务实现类
 */
@Service
public class MdtTeamObjectiveService {
	
	@Resource
	private MdtTeamObjectiveMapper dao;

	@Resource
	private MdtTeamService mdtTeamService;
    
    
    public MdtTeamObjective selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MdtTeamObjective> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MdtTeamObjective> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MdtTeamObjective obj){
		obj.setCreateTime(new Date());
		obj.setUpdateTime(new Date());
		return dao.insert(obj);
	}
	
	
	public boolean update(MdtTeamObjective obj){
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

	@Transactional
	public void save(MdtTeamObjective teamObjective, AuthUser user) {

		if (teamObjective.getId() == null) {

			teamObjective.setCreateUserid(user.getId());
			teamObjective.setCreateDept(user.getDepartment());
			insert(teamObjective);

			mdtTeamService.toAuditAnnualAssess(teamObjective.getTeamId());
		} else {

			update(teamObjective);
		}
	}

	/**
	 * 获取第一个设置的MDT团队目标
	 * @param teamId
	 * @return
	 */
	public MdtTeamObjective getFirstByTeamId(Long teamId) {

		Map<String, Object> parameters = MapUtils.genMap("teamId", teamId, "flag", "1");
		List<MdtTeamObjective> list = selectByWhere(parameters);

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}


	public MdtTeamObjective getTeamObjective(Long teamId) {

		Map<String, Object> parameters = MapUtils.genMap("teamId", teamId, "flag", "2");
		List<MdtTeamObjective> list = selectByWhere(parameters);

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
