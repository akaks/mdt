package com.kensure.mdt.service;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.mem.MapUtils;
import co.kensure.mem.PageInfo;
import com.kensure.mdt.dao.SysUserMapper;
import com.kensure.mdt.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 用户表服务实现类
 */
@Service
public class SysUserService {
	
	@Resource
	private SysUserMapper dao;

	@Resource
	private SysOrgService sysOrgService;

	@Resource
	private SysUserRoleService sysUserRoleService;

	@Resource
	private SysRoleService sysrRoleService;

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
		obj.setCreateTime(new Date());
		obj.setUpdateTime(new Date());
		return dao.insert(obj);
	}
	
	
	public boolean update(SysUser obj){
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

    public List<SysUser> selectList() {

		Map<String, Object> parameters = MapUtils.genMap();
		List<SysUser> userList = selectByWhere(parameters);
		return userList;
    }

    public long selectListCount(PageInfo page) {

		Map<String, Object> parameters = MapUtils.genMap();

		return selectCountByWhere(parameters);
    }

	/**
	 * 保存
	 * @param user
	 */
	public void save(SysUser user) {

    	if (user.getId() == null) {

    		user.setPassword("123456");

			insert(user);
		} else {

    		update(user);
		}
	}

	/**
	 * 保存用户角色
	 * @param userId
	 * @param checkedStr
	 */
    public void saveRole(Long userId, String checkedStr) {

        sysUserRoleService.delete(userId);

        String[] roleIds = checkedStr.split(",");

        for (String roleIdStr : roleIds) {

            Long roleId = Long.parseLong(roleIdStr);

            sysUserRoleService.save(userId, roleId);
        }
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public AuthUser login(String username, String password) {

		Map<String, Object> parameters = MapUtils.genMap("name", username, "password", password);

        List<SysUser> list = selectByWhere(parameters);

        if (list.isEmpty()) {
            BusinessExceptionUtil.threwException("账号或者密码错误");
        }

        SysUser sysUser = list.get(0);

        AuthUser user = new AuthUser();
        BeanUtils.copyProperties(sysUser, user);

        List<SysUserRole> userRoles = sysUserRoleService.getSysUserRoleByUserId(user.getId());

        String roleIds = "";  // 角色id取所有
        String roleLevel = "100";  // 角色级别取最大

        for (SysUserRole userRole : userRoles) {

            SysRole sysRole = sysrRoleService.selectOne(userRole.getRoleId());

            roleIds += sysRole.getId() + ",";

            if (roleLevel.compareTo(sysRole.getLevel()) < 0) {
                roleLevel = sysRole.getLevel();
            }
        }

        user.setRoleIds(roleIds);
        user.setRoleLevel(roleLevel);

        return user;

    }
}
