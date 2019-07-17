package com.kensure.mdt.service;

import com.kensure.mdt.entity.*;
import com.kensure.mdt.entity.resp.ToSthResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexService {

	@Autowired
	private MdtTeamService mdtTeamService;

	@Autowired
	private MdtApplyService mdtApplyService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private MdtTeamObjectiveService mdtTeamObjectiveService;

	@Autowired
	private MdtTeamAssessService mdtTeamAssessService;

    /**
     * 代办
     * @param user
     * @return
     */
	public List<ToSthResp> doSth(AuthUser user) {


	    List<ToSthResp> list = new ArrayList<>();

	    // MDT团队
        List<MdtTeam> mdtTeams = mdtTeamService.doSth(user);

        for (MdtTeam mdtTeam : mdtTeams) {
            ToSthResp bean = new ToSthResp();

            bean.setId(mdtTeam.getId());
            bean.setType("1");

            String content = "";
            if ("9".equals(mdtTeam.getAuditStatus())) {

                content = "MDT团队申请 审核不通过：" + mdtTeam.getName();
            } else {

                content = "MDT团队申请 待审核：" + mdtTeam.getName();
            }
            bean.setApplyPerson(sysUserService.getUsername(mdtTeam.getCreateUserid()));
            bean.setApplyDate(mdtTeam.getCreateTime());
            bean.setContent(content);

            list.add(bean);
        }

        // MDT申请
        List<MdtApply> mdtApplies = mdtApplyService.doSth(user);

        for (MdtApply mdtApply : mdtApplies) {
            ToSthResp bean = new ToSthResp();

            bean.setId(mdtApply.getId());
            bean.setType("2");
            String content = "";
            if ("9".equals(mdtApply.getApplyStatus())) {

                content = "MDT申请 审核不通过：" + mdtApply.getName();
            } else {

                content = "MDT申请 待审核：" + mdtApply.getName();
            }
            bean.setApplyPerson(sysUserService.getUsername(mdtApply.getCreateUserid()));
            bean.setApplyDate(mdtApply.getCreateTime());
            bean.setContent(content);

            list.add(bean);
        }


        // MDT团队年度
        List<MdtTeam> mdtTeams2 = mdtTeamService.doSth2(user);

        for (MdtTeam mdtTeam : mdtTeams2) {
            ToSthResp bean = new ToSthResp();

            bean.setId(mdtTeam.getId());
            bean.setType("3");

            String content = "";
            if ("2".equals(mdtTeam.getAnnualStatus())) {

                content = "MDT团队年度评估 待审核：" + mdtTeam.getName();
            }

            MdtTeamObjective teamObjective = mdtTeamObjectiveService.getTeamObjective(mdtTeam.getId());
            if (teamObjective == null) {

                bean.setApplyPerson(sysUserService.getUsername(teamObjective.getCreateUserid()));
                bean.setApplyDate(teamObjective.getCreateTime());
            }

            bean.setContent(content);

            list.add(bean);
        }

        // MDT团队满两年
        List<MdtTeam> mdtTeams3 = mdtTeamService.doSth2(user);

        for (MdtTeam mdtTeam : mdtTeams3) {
            ToSthResp bean = new ToSthResp();

            bean.setId(mdtTeam.getId());
            bean.setType("4");

            String content = "";
            if ("2".equals(mdtTeam.getAnnualStatus())) {

                content = "MDT团队满两年评估 待审核：" + mdtTeam.getName();
            }

            MdtTeamAssess teamAssess = mdtTeamAssessService.getTeamAssess(mdtTeam.getId());
            if (teamAssess == null) {

                bean.setApplyPerson(sysUserService.getUsername(teamAssess.getCreateUserid()));
                bean.setApplyDate(teamAssess.getCreateTime());
            }

            bean.setContent(content);

            list.add(bean);
        }

        return list;

    }
}
