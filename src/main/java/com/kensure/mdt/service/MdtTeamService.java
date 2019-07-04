package com.kensure.mdt.service;

import co.kensure.mem.MapUtils;
import co.kensure.mem.PageInfo;
import com.kensure.mdt.dao.MdtTeamMapper;
import com.kensure.mdt.entity.AuthUser;
import com.kensure.mdt.entity.MdtTeam;
import com.kensure.mdt.entity.MdtTeamInfo;
import com.kensure.mdt.entity.MdtTeamObjective;
import com.kensure.mdt.entity.query.MdtTeamQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * MDT团队表服务实现类
 */
@Service
public class MdtTeamService {
	
	@Resource
	private MdtTeamMapper dao;

	@Resource
	private MdtTeamObjectiveService mdtTeamObjectiveService;
    
    
    public MdtTeam selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MdtTeam> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MdtTeam> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MdtTeam obj){
		obj.setCreateTime(new Date());
		obj.setUpdateTime(new Date());

		return dao.insert(obj);
	}
	
	
	public boolean update(MdtTeam obj){
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
     * 保存、更新 MDT团队及团队建设责任目标
     * @param team
     * @param mdtTeamObjective
     */
	@Transactional
	public void save(MdtTeam team, MdtTeamObjective mdtTeamObjective, AuthUser user) {

    	if (team.getId() == null) {

    	    if (!"3".equals(team.getAuditStatus())) {
			    team.setAuditStatus("0");  // 未审核
            }
			team.setIsDelete("0");  // 未删除
			team.setCreateUserid(user.getId());
			team.setCreateDept(user.getDepartment());

			insert(team);

			mdtTeamObjective.setTeamId(team.getId());
			mdtTeamObjectiveService.save(mdtTeamObjective);
		} else {

    		update(team);

    		// 更新前，先获取第一个设置的MDT团队目标，然后设置id，在进行更新
            MdtTeamObjective objective = mdtTeamObjectiveService.getFirstByTeamId(team.getId());

            mdtTeamObjective.setId(objective.getId());
            mdtTeamObjective.setTeamId(team.getId());
            mdtTeamObjectiveService.save(mdtTeamObjective);
		}
	}

    /**
     * 条件分页查询
     * @param page
     * @param query
     * @return
     */
	public List<MdtTeam> selectList(PageInfo page, MdtTeamQuery query) {

		Map<String, Object> parameters = MapUtils.genMap();
		MapUtils.putPageInfo(parameters, page);

		parameters.put("nameLike", query.getNameLike());
		parameters.put("isDelete", "0");

		List<MdtTeam> list = selectByWhere(parameters);
		return list;
	}

    /**
     * 条件分页查询
     * @param query
     * @return
     */
	public long selectListCount(MdtTeamQuery query) {

		Map<String, Object> parameters = MapUtils.genMap(
		        "isDelete", "0", "nameLike", query.getNameLike());
		return selectCountByWhere(parameters);
	}

    /**
     * 查询所有已审核过的的MDT团队
     * @return
     */
	public List<MdtTeam> findAllMdtTeam() {

		Map<String, Object> parameters = MapUtils.genMap("auditStatus", "3");
		List<MdtTeam> list = selectByWhere(parameters);
		return list;
	}

    /**
     * 逻辑删除
     * @param id
     */
    public void delMdtTeam(Long id) {

        MdtTeam team = new MdtTeam();

        team.setId(id);
        team.setIsDelete("1");  // 未删除

        update(team);
    }

    /**
     * 审核
     * @param team
     */
    public void audit(MdtTeam team) {

        MdtTeam obj = new MdtTeam();
        obj.setId(team.getId());

        // 科主任审核
        if (StringUtils.isNotBlank(team.getAuditResult1())) {

            obj.setAuditResult1(team.getAuditResult1());
            obj.setAuditOpinion1(team.getAuditOpinion1());

            if ("1".equals(team.getAuditResult1())) {

                obj.setAuditStatus("1");
            } else {

                obj.setAuditStatus("9");
            }
        }

        // 医务部主任
        if (StringUtils.isNotBlank(team.getAuditResult2())) {

            obj.setAuditResult2(team.getAuditResult2());
            obj.setAuditOpinion2(team.getAuditOpinion2());

            if ("1".equals(team.getAuditResult2())) {

                obj.setAuditStatus("2");
            } else {

                obj.setAuditStatus("9");
            }
        }

        // 分管院长
        if (StringUtils.isNotBlank(team.getAuditResult3())) {

            obj.setAuditResult3(team.getAuditResult3());
            obj.setAuditOpinion3(team.getAuditOpinion3());

            if ("1".equals(team.getAuditResult3())) {

                obj.setAuditStatus("3");
            } else {

                obj.setAuditStatus("9");
            }
        }

        update(obj);
    }
}
