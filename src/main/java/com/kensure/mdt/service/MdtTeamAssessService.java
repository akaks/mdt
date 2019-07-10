
package com.kensure.mdt.service;

import co.kensure.mem.MapUtils;
import com.kensure.mdt.dao.MdtTeamAssessMapper;
import com.kensure.mdt.entity.MdtTeamAssess;
import com.kensure.mdt.entity.MdtTeamObjective;
import com.kensure.mdt.service.MdtTeamAssessService;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


/**
 * MDT团队建设期满2年评估表服务实现类
 */
@Service
public class MdtTeamAssessService {
	
	@Resource
	private MdtTeamAssessMapper dao;
    
    
    public MdtTeamAssess selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MdtTeamAssess> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MdtTeamAssess> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MdtTeamAssess obj){
		obj.setCreateTime(new Date());
		obj.setUpdateTime(new Date());
		return dao.insert(obj);
	}
	
	
	public boolean update(MdtTeamAssess obj){
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


	public MdtTeamAssess getTeamAssess(Long teamId) {

		Map<String, Object> parameters = MapUtils.genMap("teamId", teamId);
		List<MdtTeamAssess> list = selectByWhere(parameters);

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void save(MdtTeamAssess obj) {

		if (obj.getId() == null) {

			insert(obj);
		} else {

			update(obj);
		}
	}
}
