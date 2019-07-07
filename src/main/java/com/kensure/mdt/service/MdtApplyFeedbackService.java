
package com.kensure.mdt.service;

import co.kensure.mem.MapUtils;
import com.kensure.mdt.dao.MdtApplyFeedbackMapper;
import com.kensure.mdt.entity.MdtApplyDoctor;
import com.kensure.mdt.entity.MdtApplyFeedback;
import com.kensure.mdt.entity.SysOrg;
import com.kensure.mdt.service.MdtApplyFeedbackService;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


/**
 * MDT随访反馈表服务实现类
 */
@Service
public class MdtApplyFeedbackService {
	
	@Resource
	private MdtApplyFeedbackMapper dao;
    
    
    public MdtApplyFeedback selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MdtApplyFeedback> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MdtApplyFeedback> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MdtApplyFeedback obj){
		obj.setCreateTime(new Date());
		obj.setUpdateTime(new Date());
		return dao.insert(obj);
	}
	
	
	public boolean update(MdtApplyFeedback obj){
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


	/**
	 * 根据MDT申请id查询 MDT反馈
	 * @param applyId
	 * @return
	 */
	public List<MdtApplyFeedback> selectList(Long applyId) {

		Map<String, Object> parameters = MapUtils.genMap("applyId", applyId);

		List<MdtApplyFeedback> list = selectByWhere(parameters);
		return list;
	}


	public void save(MdtApplyFeedback obj) {

		if (obj.getId() == null) {

			insert(obj);
		} else {

			update(obj);
		}
	}
}
