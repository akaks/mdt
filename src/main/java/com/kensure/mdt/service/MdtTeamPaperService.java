
package com.kensure.mdt.service;

import co.kensure.mem.MapUtils;
import com.kensure.mdt.dao.MdtTeamPaperMapper;
import com.kensure.mdt.entity.MdtTeamIssue;
import com.kensure.mdt.entity.MdtTeamPaper;
import com.kensure.mdt.service.MdtTeamPaperService;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


/**
 * MDT团队论文表服务实现类
 */
@Service
public class MdtTeamPaperService {
	
	@Resource
	private MdtTeamPaperMapper dao;
    
    
    public MdtTeamPaper selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MdtTeamPaper> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MdtTeamPaper> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MdtTeamPaper obj){
		obj.setCreateTime(new Date());
		obj.setUpdateTime(new Date());
		return dao.insert(obj);
	}
	
	
	public boolean update(MdtTeamPaper obj){
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


	public List<MdtTeamPaper> selectList(Long teamId) {

		Map<String, Object> parameters = MapUtils.genMap("teamId", teamId);

		List<MdtTeamPaper> list = selectByWhere(parameters);
		return list;
	}


	public void save(MdtTeamPaper obj) {

		if (obj.getId() == null) {

			insert(obj);
		} else {

			update(obj);
		}
	}
}
