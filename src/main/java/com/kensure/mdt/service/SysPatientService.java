package com.kensure.mdt.service;

import co.kensure.mem.MapUtils;
import co.kensure.mem.PageInfo;
import com.kensure.mdt.dao.SysPatientMapper;
import com.kensure.mdt.entity.SysOrg;
import com.kensure.mdt.entity.SysPatient;
import com.kensure.mdt.entity.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 患者信息表服务实现类
 */
@Service
public class SysPatientService {
	
	@Resource
	private SysPatientMapper dao;
    
    
    public SysPatient selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<SysPatient> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<SysPatient> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(SysPatient obj){
		return dao.insert(obj);
	}
	
	
	public boolean update(SysPatient obj){
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


	public List<SysPatient> selectList(PageInfo page) {

		Map<String, Object> parameters = MapUtils.genMap();
		MapUtils.putPageInfo(parameters, page);

		List<SysPatient> list = selectByWhere(parameters);

		return list;
	}

	public long selectListCount(PageInfo page) {

		Map<String, Object> parameters = MapUtils.genMap();

		return selectCountByWhere(parameters);
	}

	public void save(SysPatient patient) {

		patient.setUpdateTime(new Date());

    	if (patient.getId() == null) {

			insert(patient);
		} else {

			update(patient);
		}
	}
}
