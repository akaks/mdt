
package com.kensure.mdt.service;

import co.kensure.mem.MapUtils;
import com.kensure.mdt.dao.MdtGradeItemMapper;
import com.kensure.mdt.entity.MdtGradeItem;
import com.kensure.mdt.entity.bo.MdtGradeReq;
import com.kensure.mdt.entity.bo.MdtGradeItemReq;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 评分表服务实现类
 */
@Service
public class MdtGradeItemService {
	
	@Resource
	private MdtGradeItemMapper dao;
    
    
    public MdtGradeItem selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MdtGradeItem> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MdtGradeItem> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MdtGradeItem obj){
		obj.setCreateTime(new Date());
		obj.setUpdateTime(new Date());
		return dao.insert(obj);
	}
	
	
	public boolean update(MdtGradeItem obj){
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
	 * 保存专家评分项目
	 * @param mdtGradeReq
	 */
	@Transactional
	public void saveExpertGrade(MdtGradeReq mdtGradeReq) {

		// 先删除评分
		delGradeItem("1", mdtGradeReq.getApplyId(), mdtGradeReq.getUserId());

		List<MdtGradeItemReq> list = mdtGradeReq.getList();

		for (MdtGradeItemReq mdtGradeItem : list) {

			MdtGradeItem obj = new MdtGradeItem();

			obj.setType("1");  // 1:专家打分
			obj.setApplyId(mdtGradeReq.getApplyId());
			obj.setUserId(mdtGradeReq.getUserId());

			obj.setSysGradeId(mdtGradeItem.getGradeId());
			obj.setGrade(mdtGradeItem.getGrade());
			obj.setDescription(mdtGradeItem.getDescription());
			obj.setMinValue(mdtGradeItem.getMinValue());
			obj.setMaxValue(mdtGradeItem.getMaxValue());

			insert(obj);
		}
	}

	/**
	 * 保存组织科室评分项目
	 * @param mdtGradeReq
	 */
	@Transactional
	public void saveDeptGrade(MdtGradeReq mdtGradeReq) {

		// 先删除评分
		delGradeItem("2", mdtGradeReq.getApplyId(), mdtGradeReq.getUserId());

		List<MdtGradeItemReq> list = mdtGradeReq.getList();

		for (MdtGradeItemReq mdtGradeItem : list) {

			MdtGradeItem obj = new MdtGradeItem();

			obj.setType("2");  // 1:专家打分
			obj.setApplyId(mdtGradeReq.getApplyId());
			obj.setUserId(mdtGradeReq.getUserId());

			obj.setSysGradeId(mdtGradeItem.getGradeId());
			obj.setGrade(mdtGradeItem.getGrade());
			obj.setDescription(mdtGradeItem.getDescription());
			obj.setMinValue(mdtGradeItem.getMinValue());
			obj.setMaxValue(mdtGradeItem.getMaxValue());

			insert(obj);
		}
	}

	/**
	 * 删除评分
	 * @param applyId
	 * @param userId
	 */
	public void delGradeItem(String type,Long applyId, Long userId) {

		Map<String, Object> parameters = MapUtils.genMap("type", type, "applyId", applyId, "userId", userId);

		deleteByWhere(parameters);
	}
}
