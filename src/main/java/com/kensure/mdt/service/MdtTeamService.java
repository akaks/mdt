package com.kensure.mdt.service;

import co.kensure.mem.MapUtils;
import co.kensure.mem.PageInfo;
import com.kensure.mdt.dao.MdtTeamMapper;
import com.kensure.mdt.entity.AuthUser;
import com.kensure.mdt.entity.MdtTeam;
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

        MdtTeam obj = selectOne(team.getId());

        // 新增
        if (obj == null) {

    	    if (team.getAuditStatus() == null) {
			    team.setAuditStatus("0");  // 未审核
            }
			team.setIsDelete("0");  // 未删除
			team.setCreateUserid(user.getId());
			team.setCreateDept(user.getDepartment());

			insert(team);

			mdtTeamObjective.setId(null);
			mdtTeamObjective.setTeamId(team.getId());
            mdtTeamObjective.setFlag("1");  // 第一年
			mdtTeamObjectiveService.save(mdtTeamObjective);

		}
		// 修改
		else {

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
		parameters.put("auditStatus", query.getAuditStatus());
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

		Map<String, Object> parameters = MapUtils.genMap("auditStatus", "4");
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

                obj.setAuditStatus("2");
            } else {

                obj.setAuditStatus("9");
            }
        }

        // 医务部主任
        if (StringUtils.isNotBlank(team.getAuditResult2())) {

            obj.setAuditResult2(team.getAuditResult2());
            obj.setAuditOpinion2(team.getAuditOpinion2());

            if ("1".equals(team.getAuditResult2())) {

                obj.setAuditStatus("3");
            } else {

                obj.setAuditStatus("9");
            }
        }

        // 分管院长
        if (StringUtils.isNotBlank(team.getAuditResult3())) {

            obj.setAuditResult3(team.getAuditResult3());
            obj.setAuditOpinion3(team.getAuditOpinion3());

            if ("1".equals(team.getAuditResult3())) {

                obj.setAuditStatus("4");
            } else {

                obj.setAuditStatus("9");
            }
        }

        update(obj);
    }


	/**
	 * 条件分页查询
	 * @param page
	 * @param query
	 * @return
	 */
	public List<MdtTeam> selectAnnualTeamList(PageInfo page, MdtTeamQuery query) {

		Map<String, Object> parameters = MapUtils.genMap();
		MapUtils.putPageInfo(parameters, page);

		parameters.put("nameLike", query.getNameLike());
		parameters.put("annualStatusIn", "1,2,3");
		parameters.put("isDelete", "0");

		List<MdtTeam> list = selectByWhere(parameters);
		return list;
	}

	/**
	 * 条件分页查询
	 * @param query
	 * @return
	 */
	public long selectAnnualTeamCount(MdtTeamQuery query) {

		Map<String, Object> parameters = MapUtils.genMap(
				"isDelete", "0", "annualStatusIn", "1,2,3", "nameLike", query.getNameLike());
		return selectCountByWhere(parameters);
	}

	/**
	 * 发起MDT团队年度评估
	 * @param teamId
	 */
	public void launchAnnualAssess(Long teamId) {

		MdtTeam team = new MdtTeam();
		team.setId(teamId);
		team.setAnnualStatus("1");

		update(team);
	}

	/**
	 * 待审核 MDT团队年度评估
	 * @param teamId
	 */
	public void toAuditAnnualAssess(Long teamId) {

		MdtTeam team = new MdtTeam();
		team.setId(teamId);
		team.setAnnualStatus("2");

		update(team);
	}

	/**
	 * 审核 MDT团队年度评估
	 * @param teamId
	 */
	public void auditAnnualAssess(Long teamId) {

		MdtTeam team = new MdtTeam();
		team.setId(teamId);
		team.setAnnualStatus("3");

		update(team);
	}

	/**
	 * 发起 MDT团队建设期满2年评估
	 * @param teamId
	 */
	public void launchTwoYearAssess(Long teamId) {
		MdtTeam team = new MdtTeam();
		team.setId(teamId);
		team.setTwoYearStatus("1");

		update(team);
	}

	/**
	 * 待审核 MDT团队建设期满2年评估
	 * @param teamId
	 */
	public void toAuditTwoYearAssess(Long teamId) {
		MdtTeam team = new MdtTeam();
		team.setId(teamId);
		team.setTwoYearStatus("2");

		update(team);
	}

	/**
	 * 审核 MDT团队建设期满2年评估
	 * @param teamId
	 * @param result
	 */
	public void auditTwoYearAssess(Long teamId, String result) {
		MdtTeam team = new MdtTeam();
		team.setId(teamId);

		if ("1".equals(result)) {

			team.setTwoYearStatus("3");
		} else if ("0".equals(result)) {

			team.setTwoYearStatus("4");
		}

		update(team);
	}

	/**
	 * 代办审核的
	 * @return
	 */
	public List<MdtTeam> doSth(AuthUser user) {

		Map<String, Object> parameters = MapUtils.genMap();

		String auditStatus = "";

		if (user.getRoleIds().contains("5")) {
			auditStatus = "1";
		} else if (user.getRoleIds().contains("3")) {
			auditStatus = "2";
		} else if (user.getRoleIds().contains("2")) {
			auditStatus = "3";
		} else if (user.getRoleIds().contains("7")) {
			auditStatus = "9";
		}

		parameters.put("auditStatus", auditStatus);
		parameters.put("isDelete", "0");

		List<MdtTeam> list = selectByWhere(parameters);
		return list;
	}

	/**
	 * 团队年度 代办审核的
	 * @return
	 */
	public List<MdtTeam> doSth2(AuthUser user) {

		Map<String, Object> parameters = MapUtils.genMap();

		String annualStatus = "";

		if (user.getRoleIds().contains("3")) {
			annualStatus = "2";
		}

		parameters.put("annualStatus", annualStatus);
		parameters.put("isDelete", "0");

		List<MdtTeam> list = selectByWhere(parameters);
		return list;
	}

	/**
	 * 团队两年度 代办审核的
	 * @return
	 */
	public List<MdtTeam> doSth3(AuthUser user) {

		Map<String, Object> parameters = MapUtils.genMap();

		String twoYearStatus = "";

		if (user.getRoleIds().contains("3")) {
			twoYearStatus = "2";
		}

		parameters.put("twoYearStatus", twoYearStatus);
		parameters.put("isDelete", "0");

		List<MdtTeam> list = selectByWhere(parameters);
		return list;
	}
}
