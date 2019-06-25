/*
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2019-6-11
 * 修改内容: 
 */
package com.kensure.mdt.service;

import co.kensure.mem.MapUtils;
import co.kensure.mem.PageInfo;
import com.kensure.mdt.dao.SysUserMapper;
import com.kensure.mdt.entity.SysOrg;
import com.kensure.mdt.entity.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 用户表服务实现类
 * @author fankd created on 2019-6-11
 * @since 
 */
@Service
public class SysUserService {
	
	@Resource
	private SysUserMapper dao;

	@Resource
	private SysOrgService sysOrgService;

	@Resource
	private SysUserRoleService sysUserRoleService;

    public SysUser selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<SysUser> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<SysUser> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(SysUser obj){
		return dao.insert(obj);
	}
	
	
	public boolean update(SysUser obj){
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


    public List<SysUser> selectList(PageInfo page) {

		Map<String, Object> parameters = MapUtils.genMap();
		MapUtils.putPageInfo(parameters, page);

		List<SysUser> userList = selectByWhere(parameters);

		for (SysUser sysUser : userList) {
            SysOrg org = sysOrgService.selectOne(sysUser.getDepartment());
            if (org != null) {
                sysUser.setDepartment(org.getName());
            }
        }

		return userList;
    }

    public long selectListCount(PageInfo page) {

		Map<String, Object> parameters = MapUtils.genMap();

		return selectCountByWhere(parameters);
    }

	public void save(SysUser user) {

		user.setUpdateTime(new Date());

    	if (user.getId() == null) {

			user.setCreateTime(new Date());
			insert(user);
		} else {

    		update(user);
		}
	}

    public void saveRole(Long userId, String checkedStr) {

        sysUserRoleService.delete(userId);

        String[] roleIds = checkedStr.split(",");

        for (String roleIdStr : roleIds) {

            Long roleId = Long.parseLong(roleIdStr);

            sysUserRoleService.save(userId, roleId);
        }
    }
}
