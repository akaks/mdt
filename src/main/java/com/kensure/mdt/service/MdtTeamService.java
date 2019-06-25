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

import co.kensure.mem.MapUtils;
import co.kensure.mem.PageInfo;
import com.kensure.mdt.dao.MdtTeamMapper;
import com.kensure.mdt.entity.MdtTeam;
import com.kensure.mdt.entity.query.MdtTeamQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * MDT团队表服务实现类
 * @author fankd created on 2019-6-23
 * @since 
 */
@Service
public class MdtTeamService {
	
	@Resource
	private MdtTeamMapper dao;
    
    
    public MdtTeam selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MdtTeam> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MdtTeam> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MdtTeam obj){
		return dao.insert(obj);
	}
	
	
	public boolean update(MdtTeam obj){
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


	public void save(MdtTeam team) {

    	if (team.getId() == null) {

			team.setCreateTime(new Date());
			team.setAuditStatus("0");
			insert(team);
		} else {

    		update(team);
		}
	}

	public List<MdtTeam> selectList(PageInfo page, MdtTeamQuery query) {

		Map<String, Object> parameters = MapUtils.genMap();
		MapUtils.putPageInfo(parameters, page);

		parameters.put("nameLike", query.getNameLike());

		List<MdtTeam> list = selectByWhere(parameters);
		return list;
	}

	public long selectListCount(MdtTeamQuery query) {

		Map<String, Object> parameters = MapUtils.genMap("nameLike", query.getNameLike());
		return selectCountByWhere(parameters);
	}
}
