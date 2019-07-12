
package com.kensure.mdt.service;

import co.kensure.mem.MapUtils;
import com.kensure.mdt.dao.MdtApplyOpinionMapper;
import com.kensure.mdt.entity.*;
import com.kensure.mdt.service.MdtApplyOpinionService;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


/**
 * MDT专家意见表服务实现类
 */
@Service
public class MdtApplyOpinionService {
	
	@Resource
	private MdtApplyOpinionMapper dao;

	@Resource
	private SysUserService sysUserService;

	@Resource
	private SysOrgService sysOrgService;
    
    
    public MdtApplyOpinion selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MdtApplyOpinion> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MdtApplyOpinion> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MdtApplyOpinion obj){
		obj.setCreateTime(new Date());
		obj.setUpdateTime(new Date());
		return dao.insert(obj);
	}
	
	
	public boolean update(MdtApplyOpinion obj){
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


	public void save(MdtApplyOpinion obj) {

		if (obj.getId() == null) {

			insert(obj);
		} else {

			update(obj);
		}
	}

	public MdtApplyOpinion getApplyOpinion(Long applyId, Long userId) {

		Map<String, Object> parameters = MapUtils.genMap("applyId", applyId, "userId", userId);
		List<MdtApplyOpinion> list = selectByWhere(parameters);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	public List<MdtApplyOpinion> getApplyOpinion(Long applyId) {

		Map<String, Object> parameters = MapUtils.genMap("applyId", applyId);
		List<MdtApplyOpinion> list = selectByWhere(parameters);

		for (MdtApplyOpinion mdtApplyOpinion : list) {

            SysUser sysUser = sysUserService.selectOne(mdtApplyOpinion.getUserId());
            if (sysUser != null) {
                mdtApplyOpinion.setUsername(sysUser.getName());

                SysOrg sysOrg = sysOrgService.selectOne(sysUser.getDepartment());

                if (sysOrg != null) {

                    mdtApplyOpinion.setDepartment(sysOrg.getName());
                }
            }
        }

		return list;
	}
}
