/*
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2019-6-10
 * 修改内容: 
 */
package com.kensure.mdt.service;

import co.kensure.mem.MapUtils;
import com.kensure.mdt.dao.SysRoleMapper;
import com.kensure.mdt.entity.SysRole;
import com.kensure.mdt.entity.Tree;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 角色表服务实现类
 * @author fankd created on 2019-6-10
 * @since 
 */
@Service
public class SysRoleService {
	
	@Resource
	private SysRoleMapper dao;

	@Resource
	private SysRoleMenuService sysRoleMenuService;

	@Resource
	private SysUserRoleService sysUserRoleService;
    
    
    public SysRole selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<SysRole> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<SysRole> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(SysRole obj){
		return dao.insert(obj);
	}
	
	
	public boolean update(SysRole obj){
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


	public List<SysRole> findAll() {

        Map<String, Object> parameters = MapUtils.genMap();
        List<SysRole> roleList = selectByWhere(parameters);

        return roleList;
    }

    public void save(Long roleId, String checkedStr) {

        sysRoleMenuService.delete(roleId);

        String[] menuIds = checkedStr.split(",");

        for (String menuIdStr : menuIds) {

            Long menuId = Long.parseLong(menuIdStr);

            sysRoleMenuService.save(roleId, menuId);
        }
    }

    public List<Tree> readEmpRoles(Long userId) {


        Map<String, Object> parameters = MapUtils.genMap();
        List<SysRole> roleList = selectByWhere(parameters);

        List<Long> roleIds = sysUserRoleService.getRoleIdByUserId(userId);

//        List<Role> roles = empDao.get(uuid).getRoles();
        List<Tree> trees = new ArrayList();
//        List<Role> roleList = roleDao.getList(null, null, null);
        for (SysRole role : roleList) {
            Tree tree = new Tree();
            tree.setId(String.valueOf(role.getId()));
            tree.setText(role.getName());
            if(roleIds.contains(role.getId())){
                tree.setChecked(true);
            }
            trees.add(tree);
        }

        return trees;
    }
}
