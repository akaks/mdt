
package com.kensure.mdt.service;

import com.kensure.mdt.dao.SysFileMapper;
import com.kensure.mdt.entity.SysFile;
import com.kensure.mdt.entity.SysFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 文件表服务实现类
 */
@Service
public class SysFileService {
	
	@Resource
	private SysFileMapper dao;

	@Autowired
	private SysFileItemService sysFileItemService;
    
    
    public SysFile selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<SysFile> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<SysFile> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(SysFile obj){
        obj.setCreateTime(new Date());
        obj.setUpdateTime(new Date());
		return dao.insert(obj);
	}
	
	
	public boolean update(SysFile obj){
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
    
    public void uploadFile(MultipartFile file,  Long fileId) {

        String path = writeTo(file);

        SysFileItem sysFileItem = new SysFileItem();
		sysFileItem.setFileId(fileId);
		sysFileItem.setFilePath(path);
		sysFileItem.setFileName(file.getName());

		sysFileItemService.insert(sysFileItem);

	}

	private String writeTo(MultipartFile file) {

    	return  "";
	}


}
