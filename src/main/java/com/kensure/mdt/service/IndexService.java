package com.kensure.mdt.service;

import com.kensure.mdt.entity.AuthUser;
import com.kensure.mdt.entity.MdtApply;
import com.kensure.mdt.entity.MdtTeam;
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

                content = "MDT申请 审核不通过：" + mdtApply.getApplyStatus();
            } else {

                content = "MDT申请 待审核：" + mdtApply.getApplyStatus();
            }
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
            bean.setContent(content);

            list.add(bean);
        }

        return list;

    }
}
