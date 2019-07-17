
package com.kensure.mdt.service;

import com.kensure.mdt.dao.SysFileItemMapper;
import com.kensure.mdt.entity.SysFileItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 文件项表服务实现类
 */
@Service
public class SysFileItemService {
	
	@Resource
	private SysFileItemMapper dao;
    
    
    public SysFileItem selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<SysFileItem> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<SysFileItem> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(SysFileItem obj){
		obj.setCreateTime(new Date());
		obj.setUpdateTime(new Date());
		return dao.insert(obj);
	}
	
	
	public boolean update(SysFileItem obj){
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
    
    
  

}
