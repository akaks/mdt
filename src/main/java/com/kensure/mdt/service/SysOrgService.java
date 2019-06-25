package com.kensure.mdt.service;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.mem.MapUtils;
import co.kensure.mem.PageInfo;
import com.kensure.mdt.dao.SysOrgMapper;
import com.kensure.mdt.entity.SysOrg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 机构表服务实现类
 */
@Service
public class SysOrgService {
	
	@Resource
	private SysOrgMapper dao;
    
    
    public SysOrg selectOne(String id){
    	return dao.selectOne(id);
    }
	
	public List<SysOrg> selectByIds(Collection<String> ids){
		return dao.selectByIds(ids);
	}
	
	public List<SysOrg> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(SysOrg obj){
		return dao.insert(obj);
	}
	
	
	public boolean update(SysOrg obj){
		return dao.update(obj);
	}
    
    public boolean updateByMap(Map<String, Object> params){
		return dao.updateByMap(params);
	}
    
    
	public boolean delete(String id){
		return dao.delete(id);
	}	
	
    public boolean deleteMulti(Collection<Long> ids){
		return dao.deleteMulti(ids);
	}
    
    public boolean deleteByWhere(Map<String, Object> parameters){
		return dao.deleteByWhere(parameters);
	}


	public List<SysOrg> selectList(PageInfo page) {

		Map<String, Object> parameters = MapUtils.genMap();
		MapUtils.putPageInfo(parameters, page);

		List<SysOrg> orgList = selectByWhere(parameters);
		return orgList;
	}

	public long selectListCount() {

		Map<String, Object> parameters = MapUtils.genMap();
		return selectCountByWhere(parameters);
	}

	public List<SysOrg> selectAllList() {

		Map<String, Object> parameters = MapUtils.genMap();
		List<SysOrg> orgList = selectByWhere(parameters);
		return orgList;
	}

	public void save(SysOrg org) {

        SysOrg sysOrg = selectOne(org.getId());

        if (sysOrg != null) {
			BusinessExceptionUtil.threwException("该编号已存在");
		}

		insert(org);
	}
  

}
