package com.ruoyi.system.service.petition.impl;


import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.FormFileConstants;
import com.ruoyi.common.constant.PetitionConstans;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.PetitionStatus;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.system.component.petition.PetitionComponent;
import com.ruoyi.system.domain.FormFile;
import com.ruoyi.system.domain.ProcessFlow;
import com.ruoyi.system.domain.petition.Petition;
import com.ruoyi.system.domain.petition.PetitionFile;
import com.ruoyi.system.domain.petition.PetitionSign;
import com.ruoyi.system.dto.petition.PetitionDto;
import com.ruoyi.system.mapper.FormFileMapper;
import com.ruoyi.system.mapper.ProcessFlowMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.petition.PetitionFileMapper;
import com.ruoyi.system.mapper.petition.PetitionMapper;
import com.ruoyi.system.mapper.petition.PetitionSignMapper;
import com.ruoyi.system.service.IProcessFlowService;
import com.ruoyi.system.service.petition.IPetitionService;
import com.ruoyi.system.service.petition.IPetitionSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 签呈审批 服务层实现
 * 
 * @author LXP
 * @date 2020-07-09
 */
@Service
public class PetitionServiceImpl implements IPetitionService
{
	@Autowired
	private PetitionMapper petitionMapper;

	@Autowired
	private SysUserMapper userMapper;

	@Autowired
	private PetitionFileMapper petitionFileMapper;

	@Autowired
	private PetitionComponent petitionComponent;

	@Autowired
	private PetitionConstans petitionConstans;
	@Autowired
	private PetitionSignMapper petitionSignMapper;
	@Autowired
	private IPetitionSignService petitionSignService;
	@Autowired
	private ProcessFlowMapper processFlowMapper;
	@Autowired
	private FormFileMapper formFileMapper;
	
    private static Integer start=1;

    private static Integer end=2;
	/**
     * 查询签呈审批信息
     *
     * @param id 签呈审批ID
     * @return 签呈审批信息
     */
    @Override
	public Petition selectPetitionById(Long id)
	{
	    return petitionMapper.selectPetitionById(id);
	}

	/**
     * 查询签呈审批列表
     *
     * @param petition 签呈审批信息
     * @return 签呈审批集合
     */
	@Override
	public List<Petition> selectPetitionList(Petition petition)
	{
	    return petitionMapper.selectPetitionList(petition);
	}

    /**
     * 新增签呈审批
     *
     * @param
     * @return 结果
     */
	@Override
	@Transactional
	public Long insertPetition(PetitionDto petitionDto, SysUser sysUser)
	{

		Petition petition = petitionDto.getPetition();
		petition.setStatus(PetitionStatus.SAVE.value());
		petition.setFormSta(PetitionStatus.TODO.value());
		if (StringUtils.isNotEmpty(petition.getGmSid())){
			SysUser gm = userMapper.selectUserByLoginName(petition.getGmSid());
			if(gm==null){
				throw new GlobalException("人员：" + petition.getGm() + " 填写有误，请核实后重试。");
			}
			petition.setGm(gm.getUserName());
		}
		if (StringUtils.isNotEmpty(petition.getAcOfficerSid())){
			SysUser acOfficer= userMapper.selectUserByLoginName(petition.getAcOfficerSid());
			if(acOfficer==null){
				throw new GlobalException("人员：" + petition.getAcOfficer() + " 填写有误，请核实后重试。");
			}
			petition.setAcOfficer(acOfficer.getUserName());
		}
		int i = petitionMapper.insertPetition(petition);
		List<PetitionSign> petitionSignList = petitionDto.getPetitionSignList();
		if (petitionSignList!=null){
            for (PetitionSign petitionSign : petitionSignList) {
                if (petitionSign.getSid()!="" && petitionSign.getSid()!=null){
                    String addName = petitionSign.getSid();
                    SysUser user1 = userMapper.selectUserByLoginName(addName);
                    if(user1==null){
                        throw new GlobalException("人员：" + addName + " 填写有误，请核实后重试。");
                    }
					long count = petitionSignList.stream().map(PetitionSign::getSid).distinct().count();
					if (petitionSignList.size() != count) {
						throw new GlobalException("添加核准人失败,有重复添加人员");
					}
                    petitionSign.setPetitionId(petition.getId());
                    petitionSign.setCreateBy(sysUser.getLoginName());
                    petitionSign.setCreateTime(new Date());
                    SysUser user = userMapper.selectUserByLoginName(petitionSign.getSid());
                    petitionSign.setAddName(user.getUserName());
					petitionSign.setSignType(1);
                    petitionSignMapper.insertPetitionSign(petitionSign);
                }
            }
        }

		List<PetitionSign> petitionTrialList = petitionDto.getPetitionTrialList();
		if (petitionTrialList!=null){
			for (PetitionSign petitionTrial : petitionTrialList) {
				if (!petitionTrial.getSid().equals("") && petitionTrial.getSid()!=null){
					String addName = petitionTrial.getSid();
					SysUser user1 = userMapper.selectUserByLoginName(addName);
					if(user1==null){
						throw new GlobalException("人员：" + addName + " 填写有误，请核实后重试。");
					}
					long count = petitionTrialList.stream().map(PetitionSign::getSid).distinct().count();
					if (petitionTrialList.size() != count) {
						throw new GlobalException("添加会审人失败,有重复添加人员");
					}
					petitionTrial.setPetitionId(petition.getId());
					petitionTrial.setCreateBy(sysUser.getLoginName());
					petitionTrial.setCreateTime(new Date());
					SysUser user = userMapper.selectUserByLoginName(petitionTrial.getSid());
					petitionTrial.setAddName(user.getUserName());
					petitionTrial.setSignType(2);
					petitionSignMapper.insertPetitionSign(petitionTrial);
				}
			}
		}
		
		if (i!=0){
			Long id = petition.getId();
			return id;
		}
		return null;

	}

	/**
     * 修改签呈审批
     *
     * @param petition 签呈审批信息
     * @return 结果
     */
	@Override
	public int updatePetition(Petition petition)
	{

        Long id = petition.getId();
        Petition pe = petitionMapper.selectPetitionById(id);
        String creatBy = pe.getCreatBy();
        String loginName = ShiroUtils.getSysUser().getLoginName();
        if (!creatBy.equals(loginName)){
            throw new GlobalException("只允许申请人操作。");
        }
        return petitionMapper.updatePetition(petition);
	}

	/**
     * 删除签呈审批对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deletePetitionByIds(String ids)
	{

		int i = petitionMapper.deletePetitionByIds(Convert.toStrArray(ids));
		petitionFileMapper.deletePetitionFileByOrderIds(Convert.toStrArray(ids));
//		petitionAddauditMapper.deletePetitionAddauditByOrderIds(Convert.toStrArray(ids));
		return i;

	}



	@Override
	public String seleteZJBySid(String zgsid) {
		SysUser user = userMapper.selectUserByLoginName(zgsid);
		if (user!=null||user.equals("")){
			String userName = user.getUserName();
			return userName;
		}
		return null;
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer insertRequisitionFile(List<PetitionFile> petitionFileList) {
			for (PetitionFile petitionFile : petitionFileList) {
				petitionFile.setCreateTime(DateUtils.getNowDate());
				int i = petitionFileMapper.insertPetitionFile(petitionFile);
			}
			return petitionFileList.get(0).getId().intValue();
		}



	/**
	 * 提交表单
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer submit(Long id,SysUser user) {
		Petition petition = petitionMapper.selectPetitionById(id);
		String sid = petition.getSid();
		String deptmanager = petition.getDeptmanager();
		String nextSid = "";
		String Law = "";
		int i = 0;
		if (petition.equals("") || petition == null) {
			throw new GlobalException("提交失败，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		String s = creatComNo(petition);
		Petition p = new Petition();
		p.setComNo(s);
		p.setStatus(1);
		List<Petition> comNos = petitionMapper.selectPetitionList(p);
		if (comNos.size()>0){
			throw new GlobalException("数据异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}else{
			petition.setComNo(s);
		}
		if (petition.getTypeType() == 1 && ! petition.getLaw().contains("2")) {
			//需要走法务
			petition.setStatus(PetitionStatus.WAIT_LAW.value());
			if (petition.getLaw().contains("1")){
				Law = "S01423";
			}else if (petition.getLaw().contains("3")){
				Law = "S00716";
			}
			SysUser user1 = userMapper.selectUserByLoginName(Law);
			petition.setFromSendto(user1.getLoginName());
			petition.setUpdateBy(sid);
			petition.setUpdateDate(new Date());
			petition.setCompany(petition.getCompany());
			i = petitionMapper.updatePetition(petition);
			if (i > 0) {
				petitionComponent.sendEmailAudit(id, nextSid);
			}
			return i;
		}
		/*//特殊情况 不用走部门主管审批
		else if (deptmanager.contains("2")) {
			i = updateAuditNodept(id, user);
			return i;
		}*/ else if (StringUtils.isNotEmpty(deptmanager)) {
			//设置状态和接收人为上级主管
			petition.setStatus(PetitionStatus.WAIT_MANAGER.value());
			nextSid = getZgSid(sid);
			petition.setFromSendto(nextSid);
			petition.setUpdateDate(new Date());
			petition.setUpdateBy(sid);
			i = petitionMapper.updatePetition(petition);
			//更新成功
			if (i > 0) {
				//发送邮件
				petitionComponent.sendEmailAudit(id, nextSid);
				return i;
			} else {
				throw new GlobalException("提交失败，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
			}
		}else{
			return null;
		}

	}

	/**
	 * 法务审核提交
	 * @param
	 * @param sysUser
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer updateFwAudit(PetitionDto petitionDto, SysUser sysUser) {
		Petition pe = petitionDto.getPetition();
		Petition petition = petitionMapper.selectPetitionById(pe.getId());
        if (petition==null){
            throw new GlobalException("提交失败，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
        }
		int i=0;
		//存放下一个人的id
		String sendcode="";
		//存放下一个人的状态
		int status= 0;
		SysUser uu = null;
		Long id = petition.getId();
		String loginName = sysUser.getLoginName();
        if (loginName.equals(petition.getFromSendto())){
		}else{
        	throw new GlobalException("必须法务本人提交。");
		}

		//将当前用户放入已处理完的id,说明已经处理过
		if (StringUtils.isNotEmpty(petition.getHandlesCode())){
			petition.setHandlesCode(petition.getHandlesCode() + "," + sysUser.getUserCode());
		}else {
			petition.setHandlesCode(sysUser.getUserCode());
		}
        
        //获取所有加签人员
        List<PetitionSign> petitionAddauditList = petitionDto.getPetitionAddauditList();
		PetitionSign petitionAddaudit = petitionAddauditList.get(0);
        String sid = petitionAddaudit.getSid();

		//获取所有的核审人
		PetitionSign  ps=new PetitionSign();
		ps.setPetitionId(id);
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignService.selectPetitionSignList(ps);

		//获取所有的会审人
		PetitionSign  pt=new PetitionSign();
		pt.setPetitionId(id);
		pt.setSignType(2);
		List<PetitionSign> petitionTrialList = petitionSignService.selectPetitionSignList(pt);
        //需要加签
        if (StringUtils.isNotEmpty(sid)){
			SysUser user1 = userMapper.selectUserByLoginName(sid);
			uu = user1;
			if(user1==null){
				throw new GlobalException("用户不存在，请重新输入。");
			}
			if (pe.getLawSta()== 3){
				//前加签
				if (pe.getAddauditSta()==1){
					status = PetitionStatus.WAIT_LAW.value();
					i= addaudit(petitionAddauditList,petition,sysUser.getLoginName(),status,sysUser);
					petition.setAddauditSta(pe.getAddauditSta());
					petition.setOldSta(status);
					petition.setBeforeSendToCode(sysUser.getLoginName());
					petition.setBeforeStatus(status);
				}else if (pe.getAddauditSta()==2){
					//后加签
					Integer typeType = petition.getTypeType();
					//订单类型
					switch (typeType){
						//用印
						case 1:
							if(StringUtils.isNotEmpty(petition.getDeptmanagerSid())){
							//判断是否有主管
							SysUser u1 = userMapper.selectUserByLoginName(petition.getDeptmanagerSid());
							sendcode=u1.getLoginName();
							status = PetitionStatus.WAIT_MANAGER.value();
						}else if(StringUtils.isNotEmpty(petition.getGmSid())){
								//判断是否有核审人
								SysUser u1 = userMapper.selectUserByLoginName(petition.getGmSid());
								sendcode=u1.getLoginName();
								status = PetitionStatus.WAIT_GM.value();
						}else if(StringUtils.isNotEmpty(petition.getHr())){
								//判断是否有hr
								String hr = petition.getHr();
								switch (hr){
									case "1" :
										sendcode=petitionConstans.getHrZJ();
										break;
									case "2":
										sendcode=petitionConstans.getHrSKR();
										break;
									case "3":
										sendcode=petitionConstans.getCwWN();
										break;
									case "4":
										sendcode=petitionConstans.getSealHYC();
										break;
									case "5":
										sendcode=petitionConstans.getCwHSH();
										break;
									case "6":
										sendcode=petitionConstans.getSealRL();
										break;
									case "7":
										sendcode=petitionConstans.getCwCPH();
										break;
									case "8":
										sendcode=petitionConstans.getCwCJJ();
										break;
									case "9":
										sendcode=petitionConstans.getCwZL();
										break;
									case "10":
										sendcode=petitionConstans.getSealBYJ();
										break;
									case "11":
										sendcode=petitionConstans.getSealWZY();
										break;
									case "12":
										sendcode=petitionConstans.getSealMHY();
										break;
									case "13":
										sendcode=petitionConstans.getSealCJ();
										break;
									case "14":
										sendcode=petitionConstans.getSealNZ();
										break;
									case "15":
										sendcode=petitionConstans.getCwPGC();
										break;
									case "16":
										sendcode=petitionConstans.getCwCSW();
										break;
									case "17":
										sendcode=petitionConstans.getCwFT();
										break;
									case "18":
										sendcode=petitionConstans.getCwWF();
										break;
									case "20":
										sendcode=petitionConstans.getHrZJ();
										break;
									case "21":
										sendcode=petitionConstans.getCwGT();
										break;
									case "22":
										sendcode=petitionConstans.getCwLDM();
										break;
								}
								status = PetitionStatus.WAIT_HR.value();
						}else if(StringUtils.isNotEmpty(petitionSigns)){
								//判断是否有新增核审人
								PetitionSign petitionSign = petitionSigns.get(0);
								SysUser u1 = userMapper.selectUserByLoginName(petitionSign.getSid());
								sendcode=u1.getUserCode();
								status = PetitionStatus.OTHER_SIGN.value();
						}else if(StringUtils.isNotEmpty(petitionTrialList)){
								//判断是否有新增会审人
								PetitionSign petitionTrial = petitionTrialList.get(0);
								SysUser u1 = userMapper.selectUserByLoginName(petitionTrial.getSid());
								sendcode=u1.getUserCode();
								status = PetitionStatus.OTHER_TRIAL.value();
						}else{
								petitionAddaudit.setLastAdd(1);
								petitionSignMapper.updatePetitionSign(petitionAddaudit);
							}
							i = addaudit(petitionAddauditList, petition, sendcode,status,sysUser);
							petition.setAfterSendToCode(sendcode);
							petition.setAfterStatus(status);
							break;
						default:throw new GlobalException("状态异常");
					}
				}
				petition.setOldSta(status);
				petition.setAddauditSta(pe.getAddauditSta());
				petition.setStatus(PetitionStatus.ADD_AUDIT.value());
				petition.setFromSendto(uu.getLoginName());
				petition.setUpdateDate(new Date());
				petition.setUpdateBy(sysUser.getLoginName());
				String LawIdea = pe.getLawIdea();
				if (LawIdea==null){
					petition.setLawIdea("");
				}else{
					petition.setLawIdea(pe.getLawIdea());
				}
				
				writelog(pe.getId(),sysUser,PetitionStatus.ADD_AUDIT.value(),pe.getLawIdea(),pe.getLawSta(),petition.getTypeType(),petition.getCompany());
				if (pe.getAddauditSta()==1){
					petition.setLawSta(null);
					petition.setLawDate(null);
				}else{
					petition.setLawDate(new Date());
					petition.setLawSta(1);
				}
				i = petitionMapper.updatePetition(petition);
				return i;
			}
        }else {
            //同意
            if (pe.getLawSta().equals(1)){
                petition.setStatus(PetitionStatus.WAIT_MANAGER.value());
                petition.setLawSta(pe.getLawSta());
                String lawIdea = pe.getLawIdea();
                if (StringUtils.isEmpty(lawIdea)){
                    petition.setLawIdea("");
                }else{
                    petition.setLawIdea(pe.getLawIdea());
                }
                petition.setLawDate(new Date());
                petition.setUpdateBy(sysUser.getLoginName());
                petition.setUpdateDate(new Date());
				SysUser user = userMapper.selectUserByLoginName(petition.getSid());
                petition.setFromSendto(user.getZgsid());
                i = petitionMapper.updatePetition(petition);
                if (i>0){
					petitionComponent.sendEmailNode(pe.getId(),petition.getSid(),"法务");
					petitionComponent.sendEmailAudit(pe.getId(),user.getZgsid());
                    writelog(pe.getId(),sysUser,PetitionStatus.WAIT_MANAGER.value(),pe.getLawIdea(),pe.getLawSta(),petition.getTypeType(),petition.getCompany());
                }
                return i;
            }else if (pe.getLawSta().equals(2)){
				//撤回
                petition.setStatus(PetitionStatus.WITHDRAWN.value());
				petition.setFromSendto(petition.getSid());
                petition.setUpdateBy(sysUser.getLoginName());
                petition.setUpdateDate(new Date());
				petition.setLawSta(pe.getLawSta());
				petition.setLawDate(new Date());
                 i = petitionMapper.updatePetition(petition);
                if (i>0){
                    petitionComponent.sendEmailRecall(pe.getId(),petition.getSid());
					writelog(pe.getId(),sysUser,PetitionStatus.WITHDRAWN.value(),pe.getLawIdea(),pe.getLawSta(),petition.getTypeType(),petition.getCompany());
					return i;
                }else{
                    throw new GlobalException("记录状态异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
                }
            } else if (pe.getLawSta().equals(4)){
				//撤回
				petition.setStatus(PetitionStatus.VETO.value());
				petition.setFromSendto("");
				petition.setUpdateBy(sysUser.getLoginName());
				petition.setUpdateDate(new Date());
				petition.setLawSta(pe.getLawSta());
				petition.setLawDate(new Date());
				i = petitionMapper.updatePetition(petition);
				if (i>0){
					petitionComponent.sendEmailRecall(pe.getId(),petition.getSid());
					writelog(pe.getId(),sysUser,PetitionStatus.VETO.value(),pe.getLawIdea(),pe.getLawSta(),petition.getTypeType(),petition.getCompany());
					return i;
				}else{
					throw new GlobalException("记录状态异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
				}
			}
        }
		if (pe.getLawSta() == 2){
			//加签人员签核撤回
			deleteRecord(petition.getId());
		}
        return i;
	}

    @Override
    public List<Petition> selectTodoPetitionList(Petition petition) {
        return petitionMapper.selectTodoPetitionList(petition);
    }

	@Override
	public List<Petition> selectTodoApiPetitionList(Petition petition) {
		return petitionMapper.selectTodoApiPetitionList(petition);
	}

	/*@Override
	public PageDatas<Petition> selectDidPetition(String userName,Integer pageNum,Integer pageSize) {
		ProcessFlow petitionLog=new ProcessFlow();
		petitionLog.setCreateBy(userName);
		List<ProcessFlow> petitionLogs = processFlowMapper.selectProcessFlowList(petitionLog);
		if(StringUtils.isEmpty(petitionLogs)){
//			throw new GlobalException("暂时没有已办事项,请稍后重试");
			petitionLogs.add(petitionLog);
		}
		Set<Petition> set = new LinkedHashSet<>();
		for (ProcessFlow log : petitionLogs) {
			Petition petition = new Petition();
			Long orderId = log.getOrderId();
			petition.setId(orderId);
			set.add(petition);
		}
		ArrayList allList = new ArrayList<>(set);
		allList.sort(Comparator.comparing(Petition::getId).reversed());
		//对之前bug进行修正，进行内存分页
		PageDatas<Petition> paging = PageUtils.paging(pageNum, allList, pageSize);
		List<Petition> dataRes = petitionMapper.selectDidPetitionByOrderIds(paging.getDatas().stream().map(p -> p.getId()).collect(Collectors.toList()));
		petitionComponent.buildList(dataRes);
		paging.setDatas(dataRes);
		return paging;

	}*/


    /**
     *加签审核提交
     * @param petitionDto
     * @return
     */
    @Override
	@Transactional(rollbackFor = Exception.class)
    public int updateaddAudit(PetitionDto petitionDto,SysUser sysUser) {
		String userName = sysUser.getUserName();
		Petition p = petitionDto.getPetition();
        List<PetitionSign> petitionAddauditList = petitionDto.getPetitionAddauditList();
        Long petitionId = p.getId();
        Petition  petition= petitionMapper.selectPetitionById(petitionId);
        int n=0;
        if(petition==null){
            throw new GlobalException("提交失败，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
        }
		PetitionSign petitionAddaudit = petitionAddauditList.get(petitionAddauditList.size() - 1);
		String addName = petitionAddaudit.getAddName();
		if (!addName.equalsIgnoreCase(userName)){
			throw new GlobalException("必须本人签字。");
		}
		if (petitionAddaudit.getResult() == null){
			throw new GlobalException("选择审核意见");
		}

		//将当前用户放入已处理完的id,说明已经处理过
		if (StringUtils.isNotEmpty(petition.getHandlesCode())){
			petition.setHandlesCode(petition.getHandlesCode() + "," + sysUser.getUserCode());
		}else {
			petition.setHandlesCode(sysUser.getUserCode());
		}

		PetitionSign Ad = petitionSignService.selectPetitionSignById(petitionAddaudit.getId());

		//加签人员签核同意
		if (petitionAddaudit.getResult() == 1){
			//判断后加签的id是否为空，为空证明是最后一个人的后加签
			if (Ad.getLastAdd()==null){
				//判断是否是前加签
				if (petition.getAddauditSta()==1){
					petition.setFromSendto(petition.getBeforeSendToCode());
					petition.setStatus(petition.getBeforeStatus());
				}else {
					//后加签赋值
					petition.setFromSendto(petition.getAfterSendToCode());
					petition.setStatus(petition.getAfterStatus());
				}
			}else{
				petition.setFromSendto("");
				petition.setStatus(PetitionStatus.FINISH.value());
				petition.setFormSta(PetitionStatus.FINISH.value());
				petitionAddaudit.setTosend(Ad.getCreateBy());
				petitionAddaudit.setOldStatus(PetitionStatus.FINISH.value());
			}
		}else if (petitionAddaudit.getResult() == 2){
			//加签人员签核撤回
			petition.setStatus(PetitionStatus.WITHDRAWN.value());
			petition.setFromSendto(petition.getSid());
			petitionComponent.sendEmailRecall(petition.getId(),petition.getSid());

		}else if (petitionAddaudit.getResult() == 4){
			//加签人员签核否决
			petition.setStatus(PetitionStatus.VETO.value());
			petition.setFromSendto("");
			petitionComponent.sendEmailRecall(petition.getId(),petition.getSid());

		}
		petitionAddaudit.setResult(petitionAddaudit.getResult());
		petitionAddaudit.setRemark(petitionAddaudit.getRemark());
		petitionAddaudit.setCreateBy(sysUser.getLoginName());
//		petitionAddaudit.setCreateTime(new Date());
		petitionAddaudit.setAddTime(new Date());
		petitionAddaudit.setSid(sysUser.getLoginName());
		petitionAddaudit.setFromsend(sysUser.getLoginName());
		n = petitionSignMapper.updatePetitionSign(petitionAddaudit);
		PetitionSign petitionAddaudit1 = petitionSignMapper.selectPetitionSignById(petitionAddaudit.getId());
//		//petitionComponent.sendEmailAddAuditFinishOne(petition.getId(),petitionAddaudit1.getTosend(),petitionAddaudit.getAddName());
		writelog(petitionId,sysUser,petition.getStatus(),petitionAddaudit.getRemark(),petitionAddaudit.getResult(),petition.getTypeType(),petition.getCompany());

		petition.setUpdateBy(sysUser.getLoginName());
		petition.setUpdateDate(new Date());
		if (n>0){
			if (petitionAddaudit.getResult() == 1){
				if (Objects.equals(petition.getStatus(),PetitionStatus.FINISH.value())){
					petitionComponent.sendEmailAddAuditFinish(petition.getId(),petitionAddaudit1.getTosend());
				}else{
					petitionComponent.sendEmailNode(petition.getId(),petition.getSid(),"加签");
					petitionComponent.sendEmailAudit(petition.getId(),petition.getFromSendto());
				}
				if (Objects.equals(petition.getStatus(), PetitionStatus.WAIT_HR.value())) {
					petitionComponent.sendEmailHRComplete(petition.getId(), petition.getSid());
				}
			}else {
				petitionComponent.sendEmailRecall(petition.getId(),petition.getSid());
			}
		}
		n=petitionMapper.updatePetition(petition);
		if (petitionAddaudit.getResult() == 2){
			//加签人员签核不同意
			deleteRecord(petition.getId());
		}
		return n;
 	}

//    }

	/**
	 * 新增核准人审核提交
	 * @param petitionDto
	 * @param sysUser
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer updateaddSign(PetitionDto petitionDto, SysUser sysUser) {
		int i=0;
		int Status= 99;
		//取出核准人信息
		List<PetitionSign> petitionSignList = petitionDto.getPetitionSignList();
		Petition petition = petitionDto.getPetition();
		List<PetitionSign> pslist=new ArrayList<>();
		//查询出签呈单子原本状态
		Petition pe = petitionMapper.selectPetitionById(petition.getId());
		//公用需要修改的单子对象
		Petition petitionUpdate = new Petition();
		petitionUpdate.setId(pe.getId());
		if(pe==null){
			throw new GlobalException("表单信息有误，请刷新后重试。");
		}
		//同时会有好几个核准人，遍历出核准人列表中名字和当前表单提交名字一致的那个
		for (PetitionSign petitionSign : petitionSignList) {
			String addName = petitionSign.getAddName();
			if(addName.equals(sysUser.getUserName())){
				pslist.add(petitionSign);
			}
		}
		PetitionSign psign = pslist.get(0);
		//获取当前审核的核准人所有信息
		PetitionSign petitionSign = petitionSignMapper.selectPetitionSignById(psign.getId());
		String addName = psign.getAddName();
		if (!addName.equals(sysUser.getUserName())){
			throw new GlobalException("只允许本人填写");
		}

		if (petitionSign.getResult()!=null){
			throw new GlobalException("请勿重复提交");
		}
		//核准人同意签呈进入1
		if (psign.getResult().equals(1)){
			Status = PetitionStatus.OTHER_SIGN.value();
		}else if (psign.getResult().equals(2)){
			Status = PetitionStatus.WITHDRAWN.value();
		}else if (psign.getResult().equals(4)){
			Status = PetitionStatus.VETO.value();
		}
		petitionSign.setCreateBy(sysUser.getLoginName());
		petitionSign.setCreateTime(new Date());
		petitionSign.setResult(psign.getResult());
		petitionSign.setRemark(psign.getRemark());
		petitionSign.setAddTime(new Date());
		i=petitionSignMapper.updatePetitionSign(petitionSign);
		petitionUpdate.setStatus(Status);

		//查询是否所有核准人都已经审核完毕  都审核完则完成表单
		PetitionSign ps=new PetitionSign();
		ps.setPetitionId(pe.getId());
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignMapper.selectPetitionSignList(ps);
		//已审核过的核准人列表
		List<Integer> list =new ArrayList<>();
		for (PetitionSign sign : petitionSigns) {
			if (sign.getResult()!=null){
				if (sign.getResult().equals(1)) {
					list.add(sign.getResult());
				}
			}else {
				petitionUpdate.setFromSendto(sign.getSid());
				break;
			}
		}
		 if (list.size() == petitionSigns.size()){
			 //判断是否有会审人
			 PetitionSign  pt=new PetitionSign();
			 pt.setPetitionId(pe.getId());
			 pt.setSignType(2);
			 List<PetitionSign> petitionTrialList = petitionSignMapper.selectPetitionSignList(pt);
			 //会审人列表不为空取第一个人的为下一个签核人员
			 if(StringUtils.isNotEmpty(petitionTrialList)) {
				 petitionUpdate.setFromSendto(petitionTrialList.get(0).getSid());
				 Status = PetitionStatus.OTHER_TRIAL.value();
				 petitionUpdate.setStatus(Status);
			 } else if (StringUtils.isNotEmpty(pe.getHr())) {
				 //判断是否有印鉴管理人
				 String hr = pe.getHr();
				 switch (hr) {
					 case "1":
						 petitionUpdate.setFromSendto(petitionConstans.getHrZJ());
						 break;
					 case "2":
						 petitionUpdate.setFromSendto(petitionConstans.getHrSKR());
						 break;
					 case "3":
						 petitionUpdate.setFromSendto(petitionConstans.getCwWN());
						 break;
					 case "4":
						 petitionUpdate.setFromSendto(petitionConstans.getSealHYC());
						 break;
					 case "5":
						 petitionUpdate.setFromSendto(petitionConstans.getCwHSH());
						 break;
					 case "6":
						 petitionUpdate.setFromSendto(petitionConstans.getSealRL());
						 break;
					 case "7":
						 petitionUpdate.setFromSendto(petitionConstans.getCwCPH());
						 break;
					 case "8":
						 petitionUpdate.setFromSendto(petitionConstans.getCwCJJ());
						 break;
					 case "9":
						 petitionUpdate.setFromSendto(petitionConstans.getCwZL());
						 break;
					 case "10":
						 petitionUpdate.setFromSendto(petitionConstans.getSealBYJ());
						 break;
					 case "11":
						 petitionUpdate.setFromSendto(petitionConstans.getSealWZY());
						 break;
					 case "12":
						 petitionUpdate.setFromSendto(petitionConstans.getSealMHY());
						 break;
					 case "13":
						 petitionUpdate.setFromSendto(petitionConstans.getSealCJ());
						 break;
					 case "14":
						 petitionUpdate.setFromSendto(petitionConstans.getSealNZ());
						 break;
					 case "15":
						 petitionUpdate.setFromSendto(petitionConstans.getCwPGC());
						 break;
					 case "16":
						 petitionUpdate.setFromSendto(petitionConstans.getCwCSW());
						 break;
					 case "17":
						 petitionUpdate.setFromSendto(petitionConstans.getCwFT());
						 break;
					 case "18":
						 petitionUpdate.setFromSendto(petitionConstans.getCwWF());
						 break;
					 case "19":
						 petitionUpdate.setFromSendto(petitionConstans.getHrSKR());
						 break;
					 case "20":
						 petitionUpdate.setFromSendto(petitionConstans.getHrZJ());
						 break;
					 case "21":
						 petitionUpdate.setFromSendto(petitionConstans.getCwGT());
						 break;
					 case "22":
						 petitionUpdate.setFromSendto(petitionConstans.getCwLDM());
						 break;
				 }
				 Status = PetitionStatus.WAIT_HR.value();
				 petitionUpdate.setStatus(Status);
			 }else{
				 petitionUpdate.setFromSendto("0");
				 Status = PetitionStatus.FINISH.value();
				 petitionUpdate.setStatus(Status);
			} 
		 }
		 //发送邮件
		if (psign.getResult().equals(2)){
			petitionComponent.sendEmailRecall(pe.getId(),pe.getSid());
			petitionUpdate.setFromSendto(pe.getSid());
		}else if (psign.getResult().equals(4)){
			petitionComponent.sendEmailRecall(pe.getId(),pe.getSid());
			petitionUpdate.setFromSendto("");
		}else{
			if (Objects.equals(petitionUpdate.getStatus(),PetitionStatus.FINISH.value())){
				petitionComponent.sendEmailComplete(pe.getId(),pe.getSid());
			}else{
				petitionComponent.sendEmailNode(pe.getId(), pe.getSid(), "新增核准人");
				petitionComponent.sendEmailAudit(pe.getId(),petitionUpdate.getFromSendto());
			}
			if (Objects.equals(petitionUpdate.getStatus(), PetitionStatus.WAIT_HR.value())) {
				petitionComponent.sendEmailHRComplete(pe.getId(), pe.getSid());
			}
		}

		//将当前用户放入已处理完的id,说明已经处理过
		if (StringUtils.isNotEmpty(pe.getHandlesCode())){
			petitionUpdate.setHandlesCode(pe.getHandlesCode() + "," + sysUser.getUserCode());
		}else {
			petitionUpdate.setHandlesCode(sysUser.getUserCode());
		}

		petitionUpdate.setUpdateDate(new Date());
		petitionUpdate.setUpdateBy(sysUser.getLoginName());
		i=petitionMapper.updatePetition(petitionUpdate);
		//发送日志到日志表
		writelog(petitionUpdate.getId(),sysUser,Status,psign.getRemark(),psign.getResult(),pe.getTypeType(),pe.getCompany());
		if (psign.getResult().equals(2)){
			//加签人员签核不同意
			deleteRecord(petition.getId());
		}
		return i;
	}

	/**
	 * 新增会审人审核提交
	 * @param petitionDto
	 * @param sysUser
	 * @return
	 */
	@Override
	public Integer updateaddTrial(PetitionDto petitionDto, SysUser sysUser) {
		int i=0;
		int Status= 99;
		List<PetitionSign> petitionTrialList = petitionDto.getPetitionTrialList();
		Petition petition = petitionDto.getPetition();
		Petition pe = petitionMapper.selectPetitionById(petition.getId());
		//公用需要修改的单子对象
		Petition petitionUpdate = new Petition();
		petitionUpdate.setId(pe.getId());
		if(pe==null){
			throw new GlobalException("表单信息有误，请刷新后重试。");
		}
		List<PetitionSign> ptlist=new ArrayList<>();
		for (PetitionSign petitionTrial : petitionTrialList) {
			String addName = petitionTrial.getAddName();
			if(addName.equals(sysUser.getUserName())){
				ptlist.add(petitionTrial);
			}
		}
		PetitionSign trial = ptlist.get(0);
		PetitionSign Petitiontrial = petitionSignMapper.selectPetitionSignById(trial.getId());
		String addName = trial.getAddName();
		if (!addName.equals(sysUser.getUserName())){
			throw new GlobalException("只允许本人填写");
		}

		if (Petitiontrial.getResult()!=null){
			throw new GlobalException("请勿重复提交");
		}
		if (trial.getResult().equals(1)){
			Status = PetitionStatus.OTHER_TRIAL.value();
		}else if (trial.getResult().equals(2)){
			Status = PetitionStatus.WITHDRAWN.value();
		}else if (trial.getResult().equals(4)){
			Status = PetitionStatus.VETO.value();
		}
		Petitiontrial.setCreateBy(sysUser.getLoginName());
		Petitiontrial.setCreateTime(new Date());
		Petitiontrial.setResult(trial.getResult());
		Petitiontrial.setRemark(trial.getRemark());
		Petitiontrial.setAddTime(new Date());
		i=petitionSignMapper.updatePetitionSign(Petitiontrial);
		petitionUpdate.setStatus(Status);

		//查询是否所有会审人都已经审核完毕  都审核完则完成表单
		PetitionSign pt=new PetitionSign();
		pt.setPetitionId(pe.getId());
		pt.setSignType(2);
		List<PetitionSign> PetitionTrial = petitionSignService.selectPetitionSignList(pt);
		List<Integer> list =new ArrayList<>();
		for (PetitionSign Trial : PetitionTrial) {
			if (Trial.getResult()!=null){
				if (Trial.getResult().equals(1)) {
					list.add(Trial.getResult());
				}
			}else {
				petitionUpdate.setFromSendto(Trial.getSid());
				break;
			}
		}
		if (list.size()==PetitionTrial.size()){
			//判断类型，如果是1需要到hr审核，其余类型结束
			if(pe.getTypeType().equals(1)){
				if (pe.getHr()!=null){
					Integer sta=PetitionStatus.WAIT_HR.value();
					String hr = pe.getHr();
					String sendcode = "";
					switch (hr){
						case "1" :
							sendcode=petitionConstans.getHrZJ();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "2":
							sendcode=petitionConstans.getHrSKR();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "3":
							sendcode=petitionConstans.getCwWN();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "4":
							sendcode=petitionConstans.getSealHYC();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "5":
							sendcode=petitionConstans.getCwHSH();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "6":
							sendcode=petitionConstans.getSealRL();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "7":
							sendcode=petitionConstans.getCwCPH();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "8":
							sendcode=petitionConstans.getCwCJJ();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "9":
							sendcode=petitionConstans.getCwZL();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "10":
							sendcode=petitionConstans.getSealBYJ();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "11":
							sendcode=petitionConstans.getSealWZY();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "12":
							sendcode=petitionConstans.getSealMHY();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "13":
							sendcode=petitionConstans.getSealCJ();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "14":
							sendcode=petitionConstans.getSealNZ();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "15":
							sendcode=petitionConstans.getCwPGC();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "16":
							sendcode=petitionConstans.getCwCSW();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "17":
							sendcode=petitionConstans.getCwFT();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "18":
							sendcode=petitionConstans.getCwWF();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "20":
							sendcode=petitionConstans.getHrZJ();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "21":
							sendcode=petitionConstans.getCwGT();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
						case "22":
							sendcode=petitionConstans.getCwLDM();
							updateSta(petition.getId(),sendcode,petition,sysUser,sta,pe);
							break;
					}
				}
			}else {
				Status = PetitionStatus.FINISH.value();
				petitionUpdate.setFromSendto("0");
				petitionUpdate.setStatus(Status);
			}
		}
		//发送邮件
		if (trial.getResult().equals(2)){
			petitionComponent.sendEmailRecall(pe.getId(),pe.getSid());
			petitionUpdate.setFromSendto(pe.getSid());
		}else if (trial.getResult().equals(4)){
			petitionComponent.sendEmailRecall(pe.getId(),pe.getSid());
			petitionUpdate.setFromSendto("");
		}else {
			petitionComponent.sendEmailComplete(pe.getId(),pe.getSid());
		}
		//将当前用户放入已处理完的id,说明已经处理过
		if (StringUtils.isNotEmpty(pe.getHandlesCode())){
			petitionUpdate.setHandlesCode(pe.getHandlesCode() + "," + sysUser.getUserCode());
		}else {
			petitionUpdate.setHandlesCode(sysUser.getUserCode());
		}
		petitionUpdate.setUpdateDate(new Date());
		petitionUpdate.setUpdateBy(sysUser.getLoginName());
		i=petitionMapper.updatePetition(petitionUpdate);
		writelog(pe.getId(),sysUser,Status,trial.getRemark(),trial.getResult(),pe.getTypeType(),pe.getCompany());
		if (trial.getResult().equals(2)){
			//加签人员签核不同意
			deleteRecord(petition.getId());
		}
		return i;
	}


	/**
	 * C类审核人审核提交
	 * @param
	 * @param sysUser
	 * @return
	 */
	@Override
	public Integer updateAuditAC(PetitionDto peDto, SysUser sysUser) {
		//判断单子是否提交正常
		int i=1;
		//放下一个审核人id字段
		String sendcode="";
		//放当前单子状态的字段
		int Status= 99;
		SysUser uu = null;
		Petition pe = peDto.getPetition();
		//单子当前信息
		Petition petition = petitionMapper.selectPetitionById(pe.getId());
		if (petition==null){
			throw new GlobalException("数据异常");
		}
		if (!sysUser.getLoginName().equalsIgnoreCase(petition.getFromSendto())){
			throw new GlobalException("必须本人签字");
		}
		//判断是否有核审人
		PetitionSign  ps=new PetitionSign();
		ps.setPetitionId(petition.getId());
		List<PetitionSign> petitionSigns = petitionSignService.selectPetitionSignList(ps);
		//判断是否有会审人
		PetitionSign  pt=new PetitionSign();
		pt.setPetitionId(petition.getId());
		pt.setSignType(2);
		List<PetitionSign> petitionTrialList = petitionSignService.selectPetitionSignList(pt);
		//判断是否有新的加签人员
		List<PetitionSign> petitionAddauditList = peDto.getPetitionAddauditList();
		if (pe.getAcOfficerSta().equals(3)){
			for (PetitionSign petitionAddaudit : petitionAddauditList) {
				uu = userMapper.selectUserByLoginName(petitionAddaudit.getSid());
				if(uu==null){
					throw new GlobalException("用户不存在,请重新输入");
				}
			}
			if (pe.getAcOfficerSta()== 3){
				//前加签
				if (pe.getAddauditSta()==1){
					Status = PetitionStatus.C_WAIT_AUDIT.value();
					i= addaudit(petitionAddauditList,petition,sysUser.getLoginName(),Status,sysUser);
					petition.setAddauditSta(pe.getAddauditSta());
					petition.setOldSta(Status);
					petition.setBeforeSendToCode(sysUser.getLoginName());
					petition.setBeforeStatus(Status);
				}else if (pe.getAddauditSta()==2){
					//后加签
					Integer typeType = petition.getTypeType();
					//订单类型
					switch (typeType){
						//用印
						case 3:
							if (StringUtils.isNotEmpty(petition.getGmSid())){
								//判断是否有核审人
								SysUser u1 = userMapper.selectUserByLoginName(petition.getGmSid());
								sendcode=u1.getLoginName();
								Status = PetitionStatus.WAIT_GM.value();
							}else if (StringUtils.isNotEmpty(petitionSigns)){
								//判断是否有新增核审人
								PetitionSign petitionSign = petitionSigns.get(0);
								SysUser u1 = userMapper.selectUserByLoginName(petitionSign.getSid());
								sendcode=u1.getUserCode();
								Status = PetitionStatus.OTHER_SIGN.value();
							}else if(StringUtils.isNotEmpty(petitionTrialList)){
								//判断是否有新增会审人
								PetitionSign petitionTrial = petitionTrialList.get(0);
								SysUser u1 = userMapper.selectUserByLoginName(petitionTrial.getSid());
								sendcode=u1.getUserCode();
								Status = PetitionStatus.OTHER_TRIAL.value();
							}else{
								petitionAddauditList.get(0).setLastAdd(1);
								petitionSignMapper.updatePetitionSign(petitionAddauditList.get(0));
							}
							i =addaudit(petitionAddauditList,petition,sendcode,Status,sysUser);
							petition.setAfterSendToCode(sendcode);
							petition.setAfterStatus(Status);
							petition.setOldSta(Status);
							break;
						default:throw new GlobalException("状态异常");
					}
				}
				petition.setAddauditSta(pe.getAddauditSta());
				petition.setStatus(PetitionStatus.ADD_AUDIT.value());
				petition.setFromSendto(uu.getLoginName());
				petition.setUpdateDate(new Date());
				petition.setUpdateBy(sysUser.getLoginName());
				String AcOfficerIdea = pe.getAcOfficerIdea();
				if (AcOfficerIdea==null){
					petition.setAcOfficerIdea("");
				}else{
					petition.setAcOfficerIdea(pe.getAcOfficerIdea());
				}
				writelog(petition.getId(),sysUser,PetitionStatus.ADD_AUDIT.value(),pe.getAcOfficerIdea(),pe.getAcOfficerSta(),petition.getTypeType(),petition.getCompany());
				if (pe.getAddauditSta()==1){
					petition.setAcOfficerSta(null);
					petition.setAcOfficerDate(null);
				}else{
					petition.setAcOfficerSta(1);
					petition.setAcOfficerDate(new Date());
				}
				i = petitionMapper.updatePetition(petition);
				return i;
			}
		} else if (pe.getAcOfficerSta()==null){
			throw  new GlobalException("请填写审核结果");
		}else if (pe.getAcOfficerSta().equals(1)){
			if (StringUtils.isNotNull(petition.getGmSid())){
				Status=PetitionStatus.WAIT_GM.value();
				String gmSid = petition.getGmSid();
				SysUser user = userMapper.selectUserByLoginName(gmSid);
				sendcode=user.getLoginName();
			}else if (StringUtils.isNotEmpty(petitionSigns)){
				//判断是否有新增核审人
				PetitionSign petitionSign = petitionSigns.get(0);
				SysUser u1 = userMapper.selectUserByLoginName(petitionSign.getSid());
				sendcode=u1.getUserCode();
				Status = PetitionStatus.OTHER_SIGN.value();
			}else if(StringUtils.isNotEmpty(petitionTrialList)){
				//判断是否有新增会审人
				PetitionSign petitionTrial = petitionTrialList.get(0);
				SysUser u1 = userMapper.selectUserByLoginName(petitionTrial.getSid());
				sendcode=u1.getUserCode();
				Status=PetitionStatus.OTHER_TRIAL.value();
			}else{
				//单子完成进行赋值
				Status=PetitionStatus.FINISH.value();
				sendcode=petition.getSid();
				petition.setFormSta(PetitionStatus.FINISH.value());
			}

		}else if (pe.getAcOfficerSta().equals(2)){
			//单子撤回
			Status=PetitionStatus.WITHDRAWN.value();
			sendcode=petition.getSid();
		} else if (pe.getAcOfficerSta().equals(4)){
			//单子否决
			Status=PetitionStatus.VETO.value();
			sendcode="";
		}
		String acOfficerIdea = pe.getAcOfficerIdea();
		if (acOfficerIdea==null){
			petition.setAcOfficerIdea("");
		}
		//将当前用户放入已处理完的id,说明已经处理过
		if (StringUtils.isNotEmpty(petition.getHandlesCode())){
			petition.setHandlesCode(petition.getHandlesCode() + "," + sysUser.getUserCode());
		}else {
			petition.setHandlesCode(sysUser.getUserCode());
		}
		petition.setStatus(Status);
		petition.setAcOfficerIdea(acOfficerIdea);
		petition.setAcOfficerSta(pe.getAcOfficerSta());
		petition.setAcOfficerDate(new Date());
		petition.setFromSendto(sendcode);
		petition.setUpdateDate(new Date());
		petition.setUpdateBy(sysUser.getLoginName());
		i = petitionMapper.updatePetition(petition);
		if (pe.getAcOfficerSta().equals(2)){
			//加签人员签核不同意
			deleteRecord(petition.getId());
		}
		if (i>0){
			if (pe.getAcOfficerSta().equals(1)){
				if (Objects.equals(petition.getStatus(),PetitionStatus.FINISH.value())){
					petitionComponent.sendEmailComplete(pe.getId(),petition.getSid());
				}else{
					petitionComponent.sendEmailNode(petition.getId(),petition.getSid(),"核准人");
					petitionComponent.sendEmailAudit(pe.getId(),sendcode);
				}				
			}else {
				petitionComponent.sendEmailRecall(pe.getId(),petition.getSid());
			}

			writelog(pe.getId(),sysUser,Status,acOfficerIdea,pe.getAcOfficerSta(),petition.getTypeType(),petition.getCompany());
			return i;
		}else {
			throw new GlobalException("提交失败");
		}

	}

	@Override
	public List<Petition> selectCreatePetition(Petition petition) {
		List<Petition> petitions = petitionMapper.selectPetitionList(petition);
		return petitions;
	}

	@Override
	public int WithdrawalOfInitiation(Long id,String cancelRemark,SysUser sysUser) {
		Petition petitionRes = petitionMapper.selectPetitionById(id);

		Petition petitionUpdate = new Petition();
		petitionUpdate.setId(id);
		petitionUpdate.setRecallDate(DateUtils.getNowDate());
		petitionUpdate.setRecallReason(cancelRemark);
		petitionUpdate.setStatus(PetitionStatus.WITHDRAWN.value());
		petitionUpdate.setUpdateDate(new Date());
		petitionUpdate.setUpdateBy(sysUser.getLoginName());
		String sendcode = petitionRes.getSid();
		petitionUpdate.setFromSendto(sendcode);
		return petitionMapper.updatePetition(petitionUpdate);
	}

	/**
	 *主管审核结果提交
	 * @param
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer updateAudit(PetitionDto pedto,SysUser sysuser) {
		Petition pe=pedto.getPetition();
		int i=1;
		Petition petition = petitionMapper.selectPetitionById(pe.getId());
		if (StringUtils.isNull(petition)){
			throw new GlobalException("数据错误");
		}
		//存放下一个人的id
		String sendcode="";
		//存放gm人员
		String gm1Sid = petition.getGmSid();
		//存放下一个人的状态
		int status= 0;
		SysUser uu = null;
		Long id = petition.getId();
		//获取审核意见
		Integer deptmanagerSta = pe.getDeptmanagerSta();
		String sid = petition.getSid();
		SysUser user = userMapper.selectUserByLoginName(sid);
		//获取主管或者代签主管id
		String zgsid =getZgSid(sid);
		//判断主管id和当前提交单子id是否相等
		if(!zgsid.equalsIgnoreCase(sysuser.getLoginName())){
			throw  new GlobalException("必须本人签字");
		}

		//将当前用户放入已处理完的id,说明已经处理过
		if (StringUtils.isNotEmpty(petition.getHandlesCode())){
			petition.setHandlesCode(petition.getHandlesCode() + "," + sysuser.getUserCode());
		}else {
			petition.setHandlesCode(sysuser.getUserCode());
		}
		
		//获取所有的核审人
		PetitionSign  ps=new PetitionSign();
		ps.setPetitionId(id);
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignService.selectPetitionSignList(ps);

		//获取所有的会审人
		PetitionSign  pt=new PetitionSign();
		pt.setPetitionId(id);
		pt.setSignType(2);
		List<PetitionSign> petitionTrialList = petitionSignService.selectPetitionSignList(pt);

		//获取加签人员
		List<PetitionSign> petitionAddauditList = pedto.getPetitionAddauditList();
		if (pe.getDeptmanagerSta().equals(3)){
			for (PetitionSign petitionAddaudit : petitionAddauditList) {
				uu = userMapper.selectUserByLoginName(petitionAddaudit.getSid());
				if(uu==null){
					throw new GlobalException("用户不存在,请重新输入");
				}
			}
			//进入加签状态
			if (pe.getDeptmanagerSta()== 3){
				//前加签
				if (pe.getAddauditSta()==1){
					status = PetitionStatus.WAIT_MANAGER.value();
					i= addaudit(petitionAddauditList,petition,sysuser.getLoginName(),status,sysuser);
					petition.setAddauditSta(pe.getAddauditSta());
					petition.setOldSta(status);
					petition.setBeforeSendToCode(sysuser.getLoginName());
					petition.setBeforeStatus(status);
				}else if (pe.getAddauditSta()==2){
					//后加签
					Integer typeType = petition.getTypeType();
					//订单类型
					switch (typeType){
						//用印
						case 1:
							if(StringUtils.isNotEmpty(petition.getGmSid())){
								//判断是否有gm
								SysUser u1 = userMapper.selectUserByLoginName(petition.getGmSid());
								sendcode=u1.getLoginName();
								status = PetitionStatus.WAIT_GM.value();
							}else if(StringUtils.isNotEmpty(petitionSigns)){
								//判断是否有新增核审人
								PetitionSign petitionSign = petitionSigns.get(0);
								SysUser u1 = userMapper.selectUserByLoginName(petitionSign.getSid());
								sendcode=u1.getUserCode();
								status = PetitionStatus.OTHER_SIGN.value();
							}else if(StringUtils.isNotEmpty(petitionTrialList)){
								//判断是否有新增会审人
								PetitionSign petitionTrial = petitionTrialList.get(0);
								SysUser u1 = userMapper.selectUserByLoginName(petitionTrial.getSid());
								sendcode=u1.getUserCode();
								status = PetitionStatus.OTHER_TRIAL.value();
							}else if(StringUtils.isNotEmpty(petition.getHr())){
							//判断是否有hr
							String hr = petition.getHr();
							switch (hr){
								case "1" :
									sendcode=petitionConstans.getHrZJ();
									break;
								case "2":
									sendcode=petitionConstans.getHrSKR();
									break;
								case "3":
									sendcode=petitionConstans.getCwWN();
									break;
								case "4":
									sendcode=petitionConstans.getSealHYC();
									break;
								case "5":
									sendcode=petitionConstans.getCwHSH();
									break;
								case "6":
									sendcode=petitionConstans.getSealRL();
									break;
								case "7":
									sendcode=petitionConstans.getCwCPH();
									break;
								case "8":
									sendcode=petitionConstans.getCwCJJ();
									break;
								case "9":
									sendcode=petitionConstans.getCwZL();
									break;
								case "10":
									sendcode=petitionConstans.getSealBYJ();
									break;
								case "11":
									sendcode=petitionConstans.getSealWZY();
									break;
								case "12":
									sendcode=petitionConstans.getSealMHY();
									break;
								case "13":
									sendcode=petitionConstans.getSealCJ();
									break;
								case "14":
									sendcode=petitionConstans.getSealNZ();
									break;
								case "15":
									sendcode=petitionConstans.getCwPGC();
									break;
								case "16":
									sendcode=petitionConstans.getCwCSW();
									break;
								case "17":
									sendcode=petitionConstans.getCwFT();
									break;
								case "18":
									sendcode=petitionConstans.getCwWF();
									break;
								case "20":
									sendcode=petitionConstans.getHrZJ();
									break;
								case "21":
									sendcode=petitionConstans.getCwGT();
									break;
								case "22":
									sendcode=petitionConstans.getCwLDM();
									break;
							}
							status = PetitionStatus.WAIT_HR.value();
						}else {
								petitionAddauditList.get(0).setLastAdd(1);
								petitionSignMapper.updatePetitionSign(petitionAddauditList.get(0));
							}
							i = addaudit(petitionAddauditList, petition, sendcode,status,sysuser);
							break;
						case 2:
//							if (petition.getFcManager()!=null){
//								String FcManager = petition.getFcManager();
//								switch (FcManager){
//									case "1":
//										//财务王娜
//										sendcode=petitionConstans.getCwWN();
//										break;
//									case "2":
//										//财务畅佩华
//										sendcode=petitionConstans.getCwCPH();
//										break;
//									case "3":
//										//财务马海云
//										sendcode=petitionConstans.getSealMHY();
//										break;
//									case "4":
//										//财务林佳怡
//										sendcode=petitionConstans.getCwLJY();
//										break;
//									case "5":
//										//财务韩诗惠
//										sendcode=petitionConstans.getCwHSH();
//										break;
//									case "6":
//										//财务张玲
//										sendcode=petitionConstans.getCwZL();
//										break;
//									case "7":
//										//财务夏晶
//										sendcode=petitionConstans.getCwXJ();
//										break;
//									case "8":
//										//财务叶小仙
//										sendcode=petitionConstans.getCwYXX();
//										break;
//									case "9":
//										//财务庞杭艳
//										sendcode=petitionConstans.getCwPHY();
//										break;
//									case "10":
//										//财务王晶
//										sendcode=petitionConstans.getCwWJ();
//										break;
//									case "11":
//										//财务高甜
//										sendcode=petitionConstans.getCwGT();
//										break;
//								};
//								status = PetitionStatus.WAIT_FC_MANAGER.value();
//								i = addaudit(petitionAddauditList, petition, sendcode,status,sysuser);
//							}
							if (StringUtils.isNotEmpty(petition.getGmSid())){
								//判断是否有核审人
								SysUser u1 = userMapper.selectUserByLoginName(petition.getGmSid());
								sendcode=u1.getLoginName();
								status = PetitionStatus.WAIT_GM.value();
							}else if (StringUtils.isNotEmpty(petitionSigns)){
								//判断是否有新增核审人
								PetitionSign petitionSign = petitionSigns.get(0);
								SysUser u1 = userMapper.selectUserByLoginName(petitionSign.getSid());
								sendcode=u1.getUserCode();
								status = PetitionStatus.OTHER_SIGN.value();
							}else if(StringUtils.isNotEmpty(petitionTrialList)){
								//判断是否有新增会审人
								PetitionSign petitionTrial = petitionTrialList.get(0);
								SysUser u1 = userMapper.selectUserByLoginName(petitionTrial.getSid());
								sendcode=u1.getUserCode();
								status = PetitionStatus.OTHER_TRIAL.value();
							}else{
								petitionAddauditList.get(0).setLastAdd(1);
								petitionSignMapper.updatePetitionSign(petitionAddauditList.get(0));
							}
							i =addaudit(petitionAddauditList,petition,sendcode,status,sysuser);
							break;
						case 3:
							if (StringUtils.isNotEmpty(petition.getAcOfficerSid())){
								SysUser u1 = userMapper.selectUserByLoginName(petition.getAcOfficerSid());
								sendcode=u1.getLoginName();
								status = PetitionStatus.C_WAIT_AUDIT.value();
							}else if (StringUtils.isNotEmpty(petition.getGmSid())){
								//判断是否有核审人
								SysUser u1 = userMapper.selectUserByLoginName(petition.getGmSid());
								sendcode=u1.getLoginName();
								status = PetitionStatus.WAIT_GM.value();
							}else if (StringUtils.isNotEmpty(petitionSigns)){
								//判断是否有新增核审人
								PetitionSign petitionSign = petitionSigns.get(0);
								SysUser u1 = userMapper.selectUserByLoginName(petitionSign.getSid());
								sendcode=u1.getUserCode();
								status = PetitionStatus.OTHER_SIGN.value();
							}else if(StringUtils.isNotEmpty(petitionTrialList)){
								//判断是否有新增会审人
								PetitionSign petitionTrial = petitionTrialList.get(0);
								SysUser u1 = userMapper.selectUserByLoginName(petitionTrial.getSid());
								sendcode=u1.getUserCode();
								status = PetitionStatus.OTHER_TRIAL.value();
							}else{
								petitionAddauditList.get(0).setLastAdd(1);
								petitionSignMapper.updatePetitionSign(petitionAddauditList.get(0));
							}
							i =addaudit(petitionAddauditList,petition,sendcode,status,sysuser);
							break;
						default:throw new GlobalException("状态异常");
					}
				}
				petition.setOldSta(status);
				petition.setAfterSendToCode(sendcode);
				petition.setAfterStatus(status);
				petition.setAddauditSta(pe.getAddauditSta());
				petition.setStatus(PetitionStatus.ADD_AUDIT.value());
				petition.setFromSendto(uu.getLoginName());
				petition.setUpdateDate(new Date());
				petition.setUpdateBy(sysuser.getLoginName());
				String deptmanagerIdea = pe.getDeptmanagerIdea();
				if (deptmanagerIdea==null){
					petition.setDeptmanagerIdea("");
				}else{
					petition.setDeptmanagerIdea(pe.getDeptmanagerIdea());
				}
				writelog(pe.getId(),sysuser,PetitionStatus.ADD_AUDIT.value(),pe.getDeptmanagerIdea(),pe.getDeptmanagerSta(),petition.getTypeType(),petition.getCompany());
				if (pe.getAddauditSta()==1){
					petition.setDeptmanagerSta(null);
					petition.setDeptmanageDate(null);
				}else{
					petition.setDeptmanagerSta(1);
					petition.setDeptmanageDate(new Date());
				}
				i = petitionMapper.updatePetition(petition);
				return i;
			}
		} else if (deptmanagerSta==null){
			throw new GlobalException("请填写审核意见");
		}else if (deptmanagerSta==1){
			//判断类型(1 用印签呈 2 请采购签呈 3 其他)
			switch (petition.getTypeType()){
				case 1:
					if (StringUtils.isNotEmpty(petition.getGmSid())){
						SysUser userGm = userMapper.selectUserByLoginName(petition.getGmSid());
						sendcode=userGm.getLoginName();
						petition.setStatus(PetitionStatus.WAIT_GM.value());
					}else if (StringUtils.isNotEmpty(petitionSigns)){
						//判断是否有新增核审人
						PetitionSign petitionSign = petitionSigns.get(0);
						SysUser u1 = userMapper.selectUserByLoginName(petitionSign.getSid());
						sendcode=u1.getUserCode();
						petition.setStatus(PetitionStatus.OTHER_SIGN.value());
					}else if (petition.getHr()!=null){
						//如果没有核准人就到印鉴管理人员
							Integer sta=PetitionStatus.WAIT_HR.value();
							String hr = petition.getHr();
							switch (hr){
								case "1" :
									sendcode=petitionConstans.getHrZJ();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "2":
									sendcode=petitionConstans.getHrSKR();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "3":
									sendcode=petitionConstans.getCwWN();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "4":
									sendcode=petitionConstans.getSealHYC();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "5":
									sendcode=petitionConstans.getCwHSH();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "6":
									sendcode=petitionConstans.getSealRL();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "7":
									sendcode=petitionConstans.getCwCPH();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "8":
									sendcode=petitionConstans.getCwCJJ();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "9":
									sendcode=petitionConstans.getCwZL();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "10":
									sendcode=petitionConstans.getSealBYJ();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "11":
									sendcode=petitionConstans.getSealWZY();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "12":
									sendcode=petitionConstans.getSealMHY();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "13":
									sendcode=petitionConstans.getSealCJ();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "14":
									sendcode=petitionConstans.getSealNZ();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "15":
									sendcode=petitionConstans.getCwPGC();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "16":
									sendcode=petitionConstans.getCwCSW();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "17":
									sendcode=petitionConstans.getCwFT();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "18":
									sendcode=petitionConstans.getCwWF();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "19":
									sendcode=petitionConstans.getHrSKR();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "20":
									sendcode=petitionConstans.getHrZJ();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "21":
									sendcode=petitionConstans.getCwGT();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
								case "22":
									sendcode=petitionConstans.getCwLDM();
									updateSta(petition.getId(),sendcode,petition,sysuser,sta,pe);
									break;
							}
						}
					petition.setUpdateBy(sysuser.getLoginName());
					petition.setUpdateDate(new Date());
					petition.setFromSendto(sendcode);
					petition.setDeptmanagerSta(pe.getDeptmanagerSta());
					petition.setDeptmanageDate(new Date());
					petition.setDeptmanagerIdea(pe.getDeptmanagerIdea());
					i = petitionMapper.updatePetition(petition);
					if(i>0){
						petitionComponent.sendEmailNode(petition.getId(),petition.getSid(),"主管");
//						//petitionComponent.sendEmailAudit(petition.getId(),sendcode);
						if (Objects.equals(petition.getStatus(), PetitionStatus.WAIT_HR.value())) {
							petitionComponent.sendEmailHRComplete(petition.getId(), petition.getSid());
						}
						writelog(pe.getId(),sysuser,petition.getStatus(),pe.getDeptmanagerIdea(),pe.getDeptmanagerSta(),petition.getTypeType(),petition.getCompany());
					}
						break;
				//请采购签呈
				case 2:
//					String FcManager = petition.getFcManager();
//					Integer sta2=PetitionStatus.WAIT_FC_MANAGER.value();
//					switch (FcManager){
//						case "1":
//							//财务王娜
//							sendcode=petitionConstans.getCwWN();
//							i=updateSta(id,sendcode,petition,sysuser,sta2,pe);
//							break;
//						case "2":
//							//财务畅佩华
//							sendcode=petitionConstans.getCwCPH();
//							i=updateSta(id, sendcode, petition, sysuser, sta2,pe);
//							break;
//						case "3":
//							//财务马海云
//							sendcode=petitionConstans.getSealMHY();
//							i=updateSta(id, sendcode, petition, sysuser, sta2,pe);
//							break;
//						case "4":
//							//财务林佳怡
//							sendcode=petitionConstans.getCwLJY();
//							i=updateSta(id, sendcode, petition, sysuser, sta2,pe);
//							break;
//						case "5":
//							//财务韩诗惠
//							sendcode=petitionConstans.getCwHSH();
//							i=updateSta(id, sendcode, petition, sysuser, sta2,pe);
//							break;
//						case "6":
//							//财务张玲
//							sendcode=petitionConstans.getCwZL();
//							i=updateSta(id, sendcode, petition, sysuser, sta2,pe);
//							break;
//						case "7":
//							//财务夏晶
//							sendcode=petitionConstans.getCwXJ();
//							i=updateSta(id, sendcode, petition, sysuser, sta2,pe);
//							break;
//						case "8":
//							//财务叶小仙
//							sendcode=petitionConstans.getCwYXX();
//							i=updateSta(id, sendcode, petition, sysuser, sta2,pe);
//							break;
//						case "9":
//							//财务庞杭艳
//							sendcode=petitionConstans.getCwPHY();
//							i=updateSta(id, sendcode, petition, sysuser, sta2,pe);
//							break;
//						case "10":
//							//财务王晶
//							sendcode=petitionConstans.getCwWJ();
//							i=updateSta(id, sendcode, petition, sysuser, sta2,pe);
//							break;
//						case "11":
//							//财务高甜
//							sendcode=petitionConstans.getCwGT();
//							i=updateSta(id, sendcode, petition, sysuser, sta2,pe);
//							break;
//					};
					if (StringUtils.isNotEmpty(gm1Sid)){
						//判断是否有核审人
						SysUser u1 = userMapper.selectUserByLoginName(gm1Sid);
						if (StringUtils.isNull(u1)){
							throw new GlobalException("人员：" + u1.getUserName() + " 填写有误，请核实后重试。");
						}
						sendcode=u1.getLoginName();
						petition.setStatus(PetitionStatus.WAIT_GM.value());
					}else if (StringUtils.isNotEmpty(petitionSigns)){
						//判断是否有新增核审人
						PetitionSign petitionSign = petitionSigns.get(0);
						SysUser u1 = userMapper.selectUserByLoginName(petitionSign.getSid());
						sendcode=u1.getUserCode();
						petition.setStatus(PetitionStatus.OTHER_SIGN.value());
					}else if(StringUtils.isNotEmpty(petitionTrialList)){
						//判断是否有新增会审人
						PetitionSign petitionTrial = petitionTrialList.get(0);
						SysUser u1 = userMapper.selectUserByLoginName(petitionTrial.getSid());
						sendcode=u1.getUserCode();
						petition.setStatus(PetitionStatus.OTHER_TRIAL.value());
					}else {
						//没有核准人则完成
						petition.setStatus(PetitionStatus.FINISH.value());
					}
					petition.setUpdateDate(new Date());
					petition.setUpdateBy(sysuser.getLoginName());
					petition.setDeptmanager(pe.getDeptmanager());
					petition.setDeptmanageDate(new Date());
					petition.setDeptmanagerSta(pe.getDeptmanagerSta());
					if (StringUtils.isEmpty(sendcode)){
						petition.setFromSendto("0");
					}else {
						petition.setFromSendto(sendcode);
					}

					if (StringUtils.isEmpty(pe.getDeptmanagerIdea())){
						petition.setDeptmanagerIdea("");
					}else{
						petition.setDeptmanagerIdea(pe.getDeptmanagerIdea());
					}

					i = petitionMapper.updatePetition(petition);
					if (i>0){
						if (Objects.equals(petition.getStatus(),PetitionStatus.FINISH.value())){
							petitionComponent.sendEmailComplete(pe.getId(),petition.getSid());
						}else {
							petitionComponent.sendEmailNode(petition.getId(),petition.getSid(),"主管");
							petitionComponent.sendEmailAudit(pe.getId(), sendcode);
						}
						writelog(pe.getId(),sysuser,petition.getStatus(),pe.getDeptmanagerIdea(),pe.getDeptmanagerSta(),petition.getTypeType(),petition.getCompany());
					}
					break;
				//其他
				case 3:
					//如果有会计主管，会计主管进行审核
					if (StringUtils.isNotEmpty(petition.getAcOfficerSid())){
						SysUser u1 = userMapper.selectUserByLoginName(petition.getAcOfficerSid());
						if (StringUtils.isNull(u1)){
							throw new GlobalException("人员：" + petition.getAcOfficer() + " 填写有误，请核实后重试。");
						}
						sendcode=u1.getLoginName();
						petition.setStatus(PetitionStatus.C_WAIT_AUDIT.value());
					}else if (StringUtils.isNotEmpty(gm1Sid)){
						//判断是否有核审人
						SysUser u1 = userMapper.selectUserByLoginName(gm1Sid);
						if (StringUtils.isNull(u1)){
							throw new GlobalException("人员：" + u1.getUserName() + " 填写有误，请核实后重试。");
						}
						sendcode=u1.getLoginName();
						petition.setStatus(PetitionStatus.WAIT_GM.value());
					}else if (StringUtils.isNotEmpty(petitionSigns)){
						//判断是否有新增核审人
						PetitionSign petitionSign = petitionSigns.get(0);
						SysUser u1 = userMapper.selectUserByLoginName(petitionSign.getSid());
						sendcode=u1.getUserCode();
						petition.setStatus(PetitionStatus.OTHER_SIGN.value());
					}else if(StringUtils.isNotEmpty(petitionTrialList)){
						//判断是否有新增会审人
						PetitionSign petitionTrial = petitionTrialList.get(0);
						SysUser u1 = userMapper.selectUserByLoginName(petitionTrial.getSid());
						sendcode=u1.getUserCode();
						petition.setStatus(PetitionStatus.OTHER_TRIAL.value());
					}else {
					//没有核准人则完成
					petition.setStatus(PetitionStatus.FINISH.value());
				}
					petition.setUpdateDate(new Date());
					petition.setUpdateBy(sysuser.getLoginName());
					petition.setDeptmanager(pe.getDeptmanager());
					petition.setDeptmanageDate(new Date());
					petition.setDeptmanagerSta(pe.getDeptmanagerSta());
					if (StringUtils.isEmpty(sendcode)){
						petition.setFromSendto("0");
					}else {
						petition.setFromSendto(sendcode);
					}

					if (StringUtils.isEmpty(pe.getDeptmanagerIdea())){
						petition.setDeptmanagerIdea("");
					}else{
						petition.setDeptmanagerIdea(pe.getDeptmanagerIdea());
					}

					i = petitionMapper.updatePetition(petition);
					if (i>0){
						if (Objects.equals(petition.getStatus(),PetitionStatus.FINISH.value())){
							petitionComponent.sendEmailComplete(pe.getId(),petition.getSid());
						}else {
							petitionComponent.sendEmailNode(petition.getId(),petition.getSid(),"主管");
							petitionComponent.sendEmailAudit(pe.getId(), sendcode);
						}
						writelog(pe.getId(),sysuser,petition.getStatus(),pe.getDeptmanagerIdea(),pe.getDeptmanagerSta(),petition.getTypeType(),petition.getCompany());
					}
					break;
				default:throw new GlobalException("状态异常");
			}
			return i;
		}else if(deptmanagerSta==2){
			String deptmanagerIdea = pe.getDeptmanagerIdea();
			if (deptmanagerIdea==null){
				petition.setDeptmanagerIdea("");
			}else{
				petition.setDeptmanagerIdea(pe.getDeptmanagerIdea());
			}
			petition.setFromSendto(petition.getSid());
			petition.setStatus(PetitionStatus.WITHDRAWN.value());
			petition.setUpdateBy(sysuser.getLoginName());
			petition.setUpdateDate(new Date());
			petition.setDeptmanagerSta(pe.getDeptmanagerSta());
			petition.setDeptmanageDate(new Date());
			i= petitionMapper.updatePetition(petition);
			if (i > 0){
				petitionComponent.sendEmailRecall(id,petition.getSid());
				writelog(pe.getId(),sysuser,PetitionStatus.WITHDRAWN.value(),pe.getDeptmanagerIdea(),pe.getDeptmanagerSta(),petition.getTypeType(),petition.getCompany());
			}else{
				throw new GlobalException("记录状态异常");
			}
		}else if(deptmanagerSta==4){
			String deptmanagerIdea = pe.getDeptmanagerIdea();
			if (deptmanagerIdea==null){
				petition.setDeptmanagerIdea("");
			}else{
				petition.setDeptmanagerIdea(pe.getDeptmanagerIdea());
			}
			petition.setFromSendto("");
			petition.setStatus(PetitionStatus.VETO.value());
			petition.setUpdateBy(sysuser.getLoginName());
			petition.setUpdateDate(new Date());
			petition.setDeptmanagerSta(pe.getDeptmanagerSta());
			petition.setDeptmanageDate(new Date());
			i= petitionMapper.updatePetition(petition);
			if (i > 0){
				petitionComponent.sendEmailRecall(id,petition.getSid());
				writelog(pe.getId(),sysuser,PetitionStatus.VETO.value(),pe.getDeptmanagerIdea(),pe.getDeptmanagerSta(),petition.getTypeType(),petition.getCompany());

			}else{
				throw new GlobalException("记录状态异常");
			}
		}
		if (deptmanagerSta==2){
			//加签人员签核不同意
			deleteRecord(petition.getId());
		}
		return i;
	}
    /**
     * 印鉴保管人审核提交
      * @param
     * @param sysUser
     * @return
     */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer updateAuditHR(PetitionDto peDto, SysUser sysUser) {
		int i=0;
		//存放下一个人的id
		String sencode="";
		//存放当前状态
		int Status = 99;
		SysUser uu = null;
		Petition pe = peDto.getPetition();
		Petition petition = petitionMapper.selectPetitionById(pe.getId());
		if (petition==null){
			throw new GlobalException("数据错误");
		}
		//判断是否有核审人
		PetitionSign  ps=new PetitionSign();
		ps.setPetitionId(pe.getId());
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignService.selectPetitionSignList(ps);
		//判断是否有会审人
		PetitionSign  pt=new PetitionSign();
		pt.setPetitionId(pe.getId());
		pt.setSignType(2);
		List<PetitionSign> petitionTrialList = petitionSignService.selectPetitionSignList(pt);
		//加签
		List<PetitionSign> petitionAddauditList = peDto.getPetitionAddauditList();

		String loginName = sysUser.getLoginName();
		String fromSendto = petition.getFromSendto();
		if (!loginName.equalsIgnoreCase(fromSendto)){
			throw new GlobalException("必须本人签字");
		}


		if (pe.getHrSta().equals(3)) {
			for (PetitionSign petitionAddaudit : petitionAddauditList) {
				uu = userMapper.selectUserByLoginName(petitionAddaudit.getSid());
				if (uu == null) {
					throw new GlobalException("用户不存在,请重新输入");
				}
			}
			//前加签
			if (pe.getAddauditSta()==1){
				Status = PetitionStatus.WAIT_HR.value();
				i= addaudit(petitionAddauditList,petition,sysUser.getLoginName(),Status,sysUser);
				petition.setOldSta(Status);
				petition.setBeforeSendToCode(sysUser.getLoginName());
				petition.setBeforeStatus(Status);
				petition.setOldSta(Status);
				petition.setAddauditSta(pe.getAddauditSta());
				petition.setStatus(PetitionStatus.ADD_AUDIT.value());
				petition.setFromSendto(uu.getLoginName());
				petition.setUpdateDate(new Date());
				petition.setUpdateBy(sysUser.getLoginName());
				petition.setHrSta(null);
				petition.setHrStaDate(null);
				String HrIdea = pe.getHrIdea();
				if (HrIdea==null){
					petition.setHrIdea("");
				}else{
					petition.setHrIdea(pe.getHrIdea());
				}
				writelog(pe.getId(),sysUser,PetitionStatus.ADD_AUDIT.value(),pe.getHrIdea(),pe.getHrSta(),petition.getTypeType(),petition.getCompany());
				i = petitionMapper.updatePetition(petition);
				return i;
			}else{
				throw new GlobalException("不允许设置后加签");
			}
		} else if (pe.getHrSta()==null){
			throw new GlobalException("请填写审核结果");
		} else if (pe.getHrSta().equals(1)){
//			if (StringUtils.isNotEmpty(petitionSigns)){
//				//判断是否有新增核审人
//				PetitionSign petitionSign = petitionSigns.get(0);
//				SysUser u1 = userMapper.selectUserByUserName(petitionSign.getAddName());
//				sencode=u1.getUserCode();
//				Status = PetitionStatus.OTHER_SIGN.value();
//			}else if(StringUtils.isNotEmpty(petitionTrialList)){
//				//判断是否有新增会审人
//				PetitionTrial petitionTrial = petitionTrialList.get(0);
//				SysUser u1 = userMapper.selectUserByUserName(petitionTrial.getAddName());
//				sencode=u1.getUserCode();
//				Status = PetitionStatus.OTHER_TRIAL.value();
//			}else {
				//没有核准人或者会审人则完成
				Status = PetitionStatus.FINISH.value();
				petition.setFormSta(PetitionStatus.FINISH.value());
//			}
		} else if(pe.getHrSta().equals(2)){
			Status = PetitionStatus.WITHDRAWN.value();
			sencode = petition.getSid();
		}else if(pe.getHrSta().equals(4)){
			Status = PetitionStatus.VETO.value();
			sencode = "";
		}
		//将当前用户放入已处理完的id,说明已经处理过
		if (StringUtils.isNotEmpty(petition.getHandlesCode())){
			petition.setHandlesCode(petition.getHandlesCode() + "," + sysUser.getUserCode());
		}else {
			petition.setHandlesCode(sysUser.getUserCode());
		}
		petition.setUpdateBy(sysUser.getLoginName());
		petition.setUpdateDate(new Date());
		petition.setFromSendto(sencode);
		petition.setHrSta(pe.getHrSta());
		petition.setHrStaDate(new Date());
		petition.setStatus(Status);
		String hrIdea = pe.getHrIdea();
		if (hrIdea==null){
			petition.setHrIdea("");
		}
		petition.setHrIdea(pe.getHrIdea());
		i= petitionMapper.updatePetition(petition);
		if (i>0){
			if (pe.getHrSta().equals(1)){
				if (Objects.equals(petition.getStatus(),PetitionStatus.FINISH.value())){
					petitionComponent.sendEmailComplete(pe.getId(),petition.getSid());
				}else{
					petitionComponent.sendEmailNode(petition.getId(),petition.getSid(),"印鉴保管人");
					petitionComponent.sendEmailAudit(pe.getId(),sencode);
				}
			}else if (pe.getHrSta().equals(4)){
				petitionComponent.sendEmailRecall(pe.getId(),petition.getSid());
			}

		}
		//记录日志
		writelog(petition.getId(),sysUser,Status,pe.getHrIdea(),pe.getHrSta(),petition.getTypeType(),petition.getCompany());
		if (pe.getHrSta().equals(2)){
			//加签人员签核不同意
			deleteRecord(petition.getId());
		}
		return i;

	}

	/**
	 * 财务总账审核提交
	 * @param
	 * @param sysUser
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer updateAuditcw(PetitionDto peDto, SysUser sysUser) {
		int i=0;
		Petition pe = peDto.getPetition();
		Petition petition = petitionMapper.selectPetitionById(pe.getId());
		//存放下一个审核的工号
		String sendcode="";
		//存放当前审核状态
		int status = 99;
		SysUser uu = null;
		if (petition==null){
			throw new GlobalException("数据错误");
		}
		List<PetitionSign> petitionAddauditList = peDto.getPetitionAddauditList();

		if (!sysUser.getLoginName().equalsIgnoreCase(petition.getFromSendto())){
			throw new GlobalException("必须本人签字");
		}
		if (pe.getFcManagerSta().equals(3)){
			for (PetitionSign petitionAddaudit : petitionAddauditList) { 
				uu = userMapper.selectUserByLoginName(petitionAddaudit.getSid());
				if(uu==null){
					throw new GlobalException("用户不存在,请重新输入");
				}
			}
			if (pe.getFcManagerSta()== 3){
				//前加签
				if (pe.getAddauditSta()==1){
					status = PetitionStatus.WAIT_FC_MANAGER.value();
					i= addaudit(petitionAddauditList,petition,sysUser.getLoginName(),status,sysUser);
					petition.setAddauditSta(pe.getAddauditSta());
					petition.setOldSta(status);
					petition.setBeforeSendToCode(sysUser.getLoginName());
					petition.setBeforeStatus(status);
				}else if (pe.getAddauditSta()==2){
					//后加签
					Integer typeType = petition.getTypeType();
					//订单类型
					switch (typeType){
						//用印
						case 2:
							if (petition.getCfcoManager()!=null){
								String CfcoManager = petition.getCfcoManager();
								switch (CfcoManager){
									case "2":
										//财务经理庞杭艳
										sendcode=petitionConstans.getCwPHY();
										break;
									case "3":
										//财务经理陈京晶
										sendcode=petitionConstans.getCwCJJ();
										break;
								}
								status = PetitionStatus.WAIT_CFCO_MANAGER.value();
								i = addaudit(petitionAddauditList, petition, sendcode,status,sysUser);
								petition.setAfterSendToCode(sendcode);
								petition.setAfterStatus(status);
								petition.setOldSta(status);
							}
							break;
						default:throw new GlobalException("状态异常");
					}
				}
				petition.setAddauditSta(pe.getAddauditSta());
				petition.setStatus(PetitionStatus.ADD_AUDIT.value());
				petition.setFromSendto(uu.getLoginName());
				petition.setUpdateDate(new Date());
				petition.setUpdateBy(sysUser.getLoginName());
				String FcManagerIdea = pe.getFcManagerIdea();
				if (FcManagerIdea==null){
					petition.setFcManagerIdea("");
				}else{
					petition.setFcManagerIdea(pe.getFcManagerIdea());
				}
				writelog(pe.getId(),sysUser,PetitionStatus.ADD_AUDIT.value(),pe.getFcManagerIdea(),pe.getFcManagerSta(),petition.getTypeType(),petition.getCompany());
				if (pe.getAddauditSta()==1){
					petition.setFcManagerSta(null);
					petition.setFcManagerDate(null);
				}else{
					petition.setFcManagerSta(1);
					petition.setFcManagerDate(new Date());
				}
				i = petitionMapper.updatePetition(petition);
			}
		}else if (pe.getFcManagerSta()==null){
			throw  new GlobalException("请填写审核结果");
		} else if (pe.getFcManagerSta()!=null){
			if (pe.getFcManagerSta().equals(1)){
				//同意
				if(StringUtils.isNotEmpty(petition.getCfcoManager())){
					String cfco = petition.getCfcoManager();
					switch (cfco){
						case "2":
							//财务经理庞杭艳
							sendcode=petitionConstans.getCwPHY();
							break;
						case "3":
							//财务经理陈京晶
							sendcode=petitionConstans.getCwCJJ();
							break;
					}
					status = PetitionStatus.WAIT_CFCO_MANAGER.value();
					petition.setFromSendto(sendcode);
				}
			} else if (pe.getFcManagerSta().equals(2)){
				//撤回
				sendcode=petition.getSid();
				status = PetitionStatus.WITHDRAWN.value();
				petition.setFromSendto(sendcode);
			}else if (pe.getFcManagerSta().equals(4)){
				//否决
				status = PetitionStatus.VETO.value();
				petition.setFromSendto("");
			}
			//将当前用户放入已处理完的id,说明已经处理过
			if (StringUtils.isNotEmpty(petition.getHandlesCode())){
				petition.setHandlesCode(petition.getHandlesCode() + "," + sysUser.getUserCode());
			}else {
				petition.setHandlesCode(sysUser.getUserCode());
			}
			petition.setStatus(status);
			petition.setUpdateBy(sysUser.getLoginName());
			petition.setUpdateDate(new Date());
			petition.setFcManagerSta(pe.getFcManagerSta());
			petition.setFcManagerDate(new Date());
			String fcManagerIdea = pe.getFcManagerIdea();
			if (fcManagerIdea==null){
				petition.setFcManagerIdea("");
			}
			petition.setFcManagerIdea(pe.getFcManagerIdea());
			i = petitionMapper.updatePetition(petition);
			if (i>0){
				if (pe.getFcManagerSta().equals(1)){
					petitionComponent.sendEmailNode(petition.getId(),petition.getSid(),"财务总账");
					petitionComponent.sendEmailAudit(pe.getId(), sendcode);
				}else if (pe.getFcManagerSta().equals(2) || pe.getFcManagerSta().equals(4)){
					petitionComponent.sendEmailRecall(pe.getId(), petition.getSid());
				}
				writelog(pe.getId(),sysUser,status,pe.getFcManagerIdea(),pe.getFcManagerSta(),petition.getTypeType(),petition.getCompany());
			}
		}

		if (pe.getFcManagerSta().equals(2)){
			//加签人员签核不同意
			deleteRecord(petition.getId());
		}

		return i;
	}

	/**
	 * 审核提交财务经理
	 * @param
	 * @param sysUser
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer updateAuditcwManager(PetitionDto peDto, SysUser sysUser) {
		int i=1;
		//存放下一个审核的工号
		String sendcode="";
		//存放当前审核状态
		int status = 99;
		SysUser uu = null;
		Petition pe = peDto.getPetition();
		Petition petition = petitionMapper.selectPetitionById(pe.getId());
		//取出所有核准人信息
		PetitionSign ps=new PetitionSign();
		ps.setPetitionId(pe.getId());
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignMapper.selectPetitionSignList(ps);

		//取出所有会审人信息
		PetitionSign pt=new PetitionSign();
		pt.setPetitionId(pe.getId());
		pt.setSignType(2);
		List<PetitionSign> PetitionTrial = petitionSignMapper.selectPetitionSignList(pt);
		if (petition==null){
			throw new GlobalException("数据异常");
		}
		List<PetitionSign> petitionAddauditList = peDto.getPetitionAddauditList();

		if (!sysUser.getLoginName().equalsIgnoreCase(petition.getFromSendto())){
			throw new GlobalException("必须本人签字");
		}
		if (pe.getCfcoManagerSta().equals(3)){
			for (PetitionSign petitionAddaudit : petitionAddauditList) {
				uu = userMapper.selectUserByLoginName(petitionAddaudit.getSid());
				if(uu==null){
					throw new GlobalException("用户不存在,请重新输入");
				}
			}
			//进入加签状态
			if (pe.getCfcoManagerSta()== 3){
				//前加签
				if (pe.getAddauditSta()==1){
					status = PetitionStatus.WAIT_CFCO_MANAGER.value();
					i= addaudit(petitionAddauditList,petition,sysUser.getLoginName(),status,sysUser);
					petition.setAddauditSta(pe.getAddauditSta());
					petition.setOldSta(status);
					petition.setBeforeSendToCode(sysUser.getLoginName());
					petition.setBeforeStatus(status);
				}else if (pe.getAddauditSta()==2){
					//后加签
					Integer typeType = petition.getTypeType();
					//订单类型
					switch (typeType){
						case 2:
							if (StringUtils.isNotEmpty(petition.getGm())){
								//判断是否有核审人
								SysUser u1 = userMapper.selectUserByUserName(petition.getGm());
								sendcode=u1.getLoginName();
								status = PetitionStatus.WAIT_GM.value();
							}else if (StringUtils.isNotEmpty(petitionSigns)){
								//判断是否有新增核审人
								PetitionSign petitionSign = petitionSigns.get(0);
								SysUser u1 = userMapper.selectUserByUserName(petitionSign.getAddName());
								sendcode=u1.getUserCode();
								status = PetitionStatus.OTHER_SIGN.value();
							}else if(StringUtils.isNotEmpty(PetitionTrial)){
								//判断是否有新增会审人
								PetitionSign petitionTrial = PetitionTrial.get(0);
								SysUser u1 = userMapper.selectUserByUserName(petitionTrial.getAddName());
								sendcode=u1.getUserCode();
								status = PetitionStatus.OTHER_TRIAL.value();
							}else{
								petitionAddauditList.get(0).setLastAdd(1);
								petitionSignMapper.updatePetitionSign(petitionAddauditList.get(0));
							}
							i =addaudit(petitionAddauditList,petition,sendcode,status,sysUser);
							petition.setAfterSendToCode(sendcode);
							petition.setAfterStatus(status);
							petition.setOldSta(status);
							break;
						default:throw new GlobalException("状态异常");
					}
				}
				petition.setAddauditSta(pe.getAddauditSta());
				petition.setStatus(PetitionStatus.ADD_AUDIT.value());
				petition.setFromSendto(uu.getLoginName());
				petition.setUpdateDate(new Date());
				petition.setUpdateBy(sysUser.getLoginName());
				String CfcoManagerIdea = pe.getCfcoManagerIdea();
				if (CfcoManagerIdea==null){
					petition.setCfcoManagerIdea("");
				}else{
					petition.setCfcoManagerIdea(pe.getCfcoManagerIdea());
				}
				writelog(pe.getId(),sysUser,PetitionStatus.ADD_AUDIT.value(),pe.getCfcoManagerIdea(),pe.getCfcoManagerSta(),petition.getTypeType(),petition.getCompany());
				if (pe.getAddauditSta()==1){
					petition.setCfcoManagerSta(null);
					petition.setCfcoManagerDate(null);
				}else{
					petition.setCfcoManagerSta(1);
					petition.setCfcoManagerDate(new Date());
				}
				i = petitionMapper.updatePetition(petition);
				return i;
			}
		} else if (pe.getCfcoManagerSta()==null){
			throw  new GlobalException("请填写审核结果");
		}else if (pe.getCfcoManagerSta()!=null) {
			if (pe.getCfcoManagerSta().equals(1)) {
				if (StringUtils.isNotNull(petition.getGm())) {
					status = PetitionStatus.WAIT_GM.value();
					String gm = petition.getGm();
					SysUser user = userMapper.selectUserByUserName(gm);
					sendcode = user.getLoginName();
					petitionComponent.sendEmailNode(petition.getId(),petition.getSid(),"财务经理");
					petitionComponent.sendEmailAudit(pe.getId(), sendcode);
					petition.setFromSendto(sendcode);
				} else if (StringUtils.isNotEmpty(petitionSigns)) {
					//判断是否有新增核审人
					String sid = petitionSigns.get(0).getSid();
					if (StringUtils.isNotEmpty(sid)) {
						status = PetitionStatus.OTHER_SIGN.value();
						petition.setFromSendto(sid);
						petitionComponent.sendEmailNode(petition.getId(),petition.getSid(),"财务经理");
						petitionComponent.sendEmailAudit(pe.getId(), sid);
					}

				} else if (StringUtils.isNotEmpty(PetitionTrial)) {
					//判断是否有新增会审人
					String sid = PetitionTrial.get(0).getSid();
					if (StringUtils.isNotEmpty(sid)) {
						status = PetitionStatus.OTHER_TRIAL.value();
						petition.setFromSendto(sid);
						petitionComponent.sendEmailNode(petition.getId(),petition.getSid(),"财务经理");
						petitionComponent.sendEmailAudit(pe.getId(), sid);
					}
				} else {
					//完成
					status = PetitionStatus.FINISH.value();
					petition.setFromSendto(petition.getSid());
					//查询是否已有该单号
//				String comNo = creatComNo(petition);
//				Petition p=new Petition();
//				p.setComNo(comNo);
//				List<Petition> comNos = petitionMapper.selectPetitionList(p);
//				if (comNos.size()>0){
//					throw new GlobalException("数据异常");
//				}else{
//					petition.setComNo(comNo);
//				}
					petition.setFormSta(PetitionStatus.FINISH.value());
				}
			} else if (pe.getCfcoManagerSta().equals(2)) {
				status = PetitionStatus.WITHDRAWN.value();
				petition.setFromSendto(petition.getSid());
			} else if (pe.getCfcoManagerSta().equals(4)) {
				status = PetitionStatus.VETO.value();
				petition.setFromSendto("");
			}
			//将当前用户放入已处理完的id,说明已经处理过
			if (StringUtils.isNotEmpty(petition.getHandlesCode())){
				petition.setHandlesCode(petition.getHandlesCode() + "," + sysUser.getUserCode());
			}else {
				petition.setHandlesCode(sysUser.getUserCode());
			}
			petition.setStatus(status);
			petition.setUpdateDate(new Date());
			petition.setUpdateBy(sysUser.getLoginName());
			petition.setCfcoManagerSta(pe.getCfcoManagerSta());
			petition.setCfcoManagerDate(new Date());
			String cfcoManagerIdea = pe.getCfcoManagerIdea();
			if (cfcoManagerIdea == null) {
				petition.setCfcoManagerIdea("");
			}
			petition.setCfcoManagerIdea(pe.getCfcoManagerIdea());
			i = petitionMapper.updatePetition(petition);
			if (i > 0) {
				if (pe.getCfcoManagerSta().equals(1)){
					if (petition.getStatus().equals(PetitionStatus.FINISH.value())){
						petitionComponent.sendEmailComplete(pe.getId(), petition.getSid());
					}
				}else if (pe.getCfcoManagerSta().equals(2) || pe.getCfcoManagerSta().equals(4)){
					petitionComponent.sendEmailRecall(pe.getId(), petition.getSid());
				}
				writelog(pe.getId(), sysUser,status, pe.getCfcoManagerIdea(), pe.getCfcoManagerSta(), petition.getTypeType(), petition.getCompany());
			}

		}
		if (pe.getCfcoManagerSta().equals(2)){
			//加签人员签核不同意
			deleteRecord(petition.getId());
		}

		return i;
	}

	/**
	 * 核准人审核结果提交
	 * @param
	 * @param sysUser
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer updateAuditGM(PetitionDto petitionDto, SysUser sysUser) {
		int i=0;
		//存放下一个审核人的id
		String sendcode="";
		//存放下一个状态
		int Status = 0;

		SysUser uu = null;
		Petition pe = petitionDto.getPetition();
		Long id = pe.getId();
		Petition petition = petitionMapper.selectPetitionById(id);
		if (petition==null){
			throw new GlobalException("数据错误");
		}
		//获取加签人员
		List<PetitionSign> petitionAddauditList = petitionDto.getPetitionAddauditList();
		//获取新增核准人
		PetitionSign ps=new PetitionSign();
		ps.setPetitionId(pe.getId());
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignMapper.selectPetitionSignList(ps);

		//获取新增会审人
		PetitionSign  pt=new PetitionSign();
		pt.setPetitionId(pe.getId());
		pt.setSignType(2);
		List<PetitionSign> petitionTrialList = petitionSignMapper.selectPetitionSignList(pt);

		if (!sysUser.getLoginName().equalsIgnoreCase(petition.getFromSendto())){
			throw new GlobalException("必须本人签字");
		}
		if (pe.getGmSta().equals(3)){
			for (PetitionSign petitionAddaudit : petitionAddauditList) {
				uu = userMapper.selectUserByLoginName(petitionAddaudit.getSid());
				if(uu==null){
					throw new GlobalException("用户不存在,请重新输入");
				}
			}
			//前加签
			if (pe.getAddauditSta()==1){
				Status = PetitionStatus.WAIT_GM.value();
				i= addaudit(petitionAddauditList,petition,sysUser.getLoginName(),Status,sysUser);
				petition.setOldSta(Status);
				petition.setBeforeSendToCode(sysUser.getLoginName());
				petition.setBeforeStatus(Status);
				petition.setAddauditSta(pe.getAddauditSta());
				petition.setStatus(PetitionStatus.ADD_AUDIT.value());
				petition.setFromSendto(uu.getLoginName());
				petition.setUpdateDate(new Date());
				petition.setUpdateBy(sysUser.getLoginName());
				petition.setGmSta(null);
				petition.setGmDate(null);
				String GmIdea = pe.getGmIdea();
				if (GmIdea==null){
					petition.setGmIdea("");
				}else{
					petition.setGmIdea(pe.getGmIdea());
				}
				writelog(pe.getId(),sysUser,PetitionStatus.ADD_AUDIT.value(),pe.getGmIdea(),pe.getGmSta(),petition.getTypeType(),petition.getCompany());
				i = petitionMapper.updatePetition(petition);
				return i;
			}else{
				throw new GlobalException("不允许设置后加签");
			}
		}else if (pe.getGmSta()==null){
			throw new GlobalException("请输入审核结果");
		} else if (pe.getGmSta().equals(1)){
			 if (StringUtils.isNotEmpty(petitionSigns)){
				//判断是否有新增核审人
				PetitionSign petitionSign = petitionSigns.get(0);
				SysUser u1 = userMapper.selectUserByLoginName(petitionSign.getSid());
				sendcode=u1.getUserCode();
				Status = PetitionStatus.OTHER_SIGN.value();
			}else if (StringUtils.isNotEmpty(petition.getHr())){
				 //判断是否有印鉴管理人
				 String hr = petition.getHr();
				 switch (hr){
					 case "1" :
						 sendcode=petitionConstans.getHrZJ();
						 break;
					 case "2":
						 sendcode=petitionConstans.getHrSKR();
						 break;
					 case "3":
						 sendcode=petitionConstans.getCwWN();
						 break;
					 case "4":
						 sendcode=petitionConstans.getSealHYC();
						 break;
					 case "5":
						 sendcode=petitionConstans.getCwHSH();
						 break;
					 case "6":
						 sendcode=petitionConstans.getSealRL();
						 break;
					 case "7":
						 sendcode=petitionConstans.getCwCPH();
						 break;
					 case "8":
						 sendcode=petitionConstans.getCwCJJ();
						 break;
					 case "9":
						 sendcode=petitionConstans.getCwZL();
						 break;
					 case "10":
						 sendcode=petitionConstans.getSealBYJ();
						 break;
					 case "11":
						 sendcode=petitionConstans.getSealWZY();
						 break;
					 case "12":
						 sendcode=petitionConstans.getSealMHY();
						 break;
					 case "13":
						 sendcode=petitionConstans.getSealCJ();
						 break;
					 case "14":
						 sendcode=petitionConstans.getSealNZ();
						 break;
					 case "15":
						 sendcode=petitionConstans.getCwPGC();
						 break;
					 case "16":
						 sendcode=petitionConstans.getCwCSW();
						 break;
					 case "17":
						 sendcode=petitionConstans.getCwFT();
						 break;
					 case "18":
						 sendcode=petitionConstans.getCwWF();
						 break;
					 case "19":
						 sendcode=petitionConstans.getHrSKR();
						 break;
					 case "20":
						 sendcode=petitionConstans.getHrZJ();
						 break;
					 case "21":
						 sendcode=petitionConstans.getCwGT();
						 break;
					 case "22":
						 sendcode=petitionConstans.getCwLDM();
						 break;
				 }
				 Status = PetitionStatus.WAIT_HR.value();
			 }else if(StringUtils.isNotEmpty(petitionTrialList)){
				//判断是否有新增会审人
				 PetitionSign petitionTrial = petitionTrialList.get(0);
				SysUser u1 = userMapper.selectUserByLoginName(petitionTrial.getSid());
				sendcode=u1.getUserCode();
				Status = PetitionStatus.OTHER_TRIAL.value();
			}else{
				//没有下一位审核，审核完成
				Status = PetitionStatus.FINISH.value();
				sendcode="";
				petition.setFormSta(PetitionStatus.FINISH.value());
			}
		}else if (pe.getGmSta().equals(2)){
			Status = PetitionStatus.WITHDRAWN.value();
			sendcode= petition.getSid();
		}else if (pe.getGmSta().equals(4)){
			Status = PetitionStatus.VETO.value();
			sendcode= "";
		}
		//将当前用户放入已处理完的id,说明已经处理过
		if (StringUtils.isNotEmpty(petition.getHandlesCode())){
			petition.setHandlesCode(petition.getHandlesCode() + "," + sysUser.getUserCode());
		}else {
			petition.setHandlesCode(sysUser.getUserCode());
		}
		petition.setFromSendto(sendcode);
		petition.setStatus(Status);
		petition.setUpdateDate(new Date());
		petition.setUpdateBy(sysUser.getLoginName());
		petition.setGmSta(pe.getGmSta());
		petition.setGmDate(new Date());
		String gmIdea = pe.getGmIdea();
		if (gmIdea==null){
			petition.setGmIdea("");
		}
		petition.setGmIdea(gmIdea);
		i = petitionMapper.updatePetition(petition);
		if (i>0){
			if (pe.getGmSta().equals(1)) {
				if (Objects.equals(petition.getStatus(),PetitionStatus.FINISH.value())){
					petitionComponent.sendEmailComplete(id,petition.getSid());
				}else{
					petitionComponent.sendEmailNode(petition.getId(),petition.getSid(),"核准人");
					petitionComponent.sendEmailAudit(id,sendcode);
				}
				if (Objects.equals(petition.getStatus(), PetitionStatus.WAIT_HR.value())) {
					petitionComponent.sendEmailHRComplete(petition.getId(), petition.getSid());
				}
			}else if (pe.getGmSta().equals(2)){
				petitionComponent.sendEmailRecall(id,petition.getSid());
			}
		}else{
			throw new GlobalException("提交失败");
		}
		writelog(id,sysUser,Status,gmIdea,pe.getGmSta(),petition.getTypeType(),petition.getCompany());
		if (pe.getGmSta().equals(2)){
			//加签人员签核不同意
			deleteRecord(petition.getId());
		}
		return i;
	}



	/**
	 * 导出PDF
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> getPetitionMessage(Long id) {
		Petition petition = petitionMapper.selectPetitionById(id);
		if (petition==null){
			return null;
		}
		PetitionSign ps=new PetitionSign();
		ps.setPetitionId(id);
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignMapper.selectPetitionSignList(ps);
		Map<String,Object> result=new HashMap<>();
		//表单自上而下
		Integer type = petition.getTypeType();
		//用印签呈
		if (type==1){
			String comNo = petition.getComNo();
			if (StringUtils.isEmpty(comNo)){
				result.put("comNo","");
			}else{
				result.put("comNo",comNo);
			}

			result.put("explanation",petition.getExplanation());
			String sealtype = " ";

			if(petition.getSealType().contains("1")){
				sealtype = sealtype + " 公章 ";
			}
			if(petition.getSealType().contains("2")){
				sealtype = sealtype + " 法人章 ";
			}
			if(petition.getSealType().contains("3")){
				sealtype = sealtype + " 财务章 ";
			}
			if(petition.getSealType().contains("4")){
				sealtype = sealtype + " 合同章 ";
			}
			if(petition.getSealType().contains("5")){
				sealtype = sealtype + " 法人章(经济部小章) ";
			}
			if(petition.getSealType().contains("6")){
				sealtype = sealtype + " 法人章(经济部大章) ";
			}
			if(petition.getSealType().contains("7")){
				sealtype = sealtype + " 印鉴大章 ";
			}
			if(petition.getSealType().contains("8")){
				sealtype = sealtype + " 印鉴小章 ";
			}
			if(petition.getSealType().contains("9")){
				sealtype = sealtype + " 其他 ";
			}

			result.put("sealtype",sealtype);
			String seal = petition.getSeal();
			if (StringUtils.isNotEmpty(seal)){
				result.put("seal",seal);
			}else{
				result.put("seal","");
			}
			Date sealDate = petition.getSealDate();
			String s ="";
			if(sealDate!=null){
                s = DateUtils.dateTime(sealDate);
            }
			String sealExplain = petition.getSealExplain();
			switch (sealExplain){
				case "1":
					result.put("sealexplan","用印");
					break;
				case "2":
					result.put("sealexplan","外借"+s);
			}
			if (petition.getMoreExplain()==null){
				petition.setMoreExplain("");
			}
			result.put("moreexplanin",petition.getMoreExplain());
			String company = petition.getCompany();
			switch (company){
				case "5":
					result.put("company","杭州");
					result.put("Title","矽力杰半导体技术（杭州）有限公司");
					break;
				case "7":
					result.put("company","西安");
					result.put("Title","西安矽力杰半导体技术有限公司");
					break;
				case "17":
					result.put("company","成都");
					result.put("Title","成都矽力杰半导体技术有限公司");
					break;
				case "19":
					result.put("company","上海");
					result.put("Title","上海矽力杰半导体技术有限公司");
					break;
				case "3":
					result.put("company","韩国");
					result.put("Title","Silergy Korea Limited");
					break;
				case "16":
					result.put("company","印度");
					result.put("Title","Silergy Technologies Private Limited");
					break;
				case "15":
					result.put("company","日本");
					result.put("Title","Silergy Semiconductor Samoa Limited");
					break;
				case "12":
					result.put("company","台湾矽力杰");
					result.put("Title","台湾矽力杰科技股份有限公司");
					break;
				case "6":
					result.put("company","南京");
					result.put("Title","南京矽力微电子技术有限公司");
					break;
				case "13":
					result.put("company","香港");
					result.put("Title","Silergy Semiconductor (Hong Kong) Limited 矽力杰半導體(香港)有限公司");
					break;
				case "14":
					result.put("company","上海矽力杰微电子");
					result.put("Title","上海矽力杰微电子技术有限公司");
					break;
				case "20":
					result.put("company","南京香港");
					result.put("Title","南京矽力微電子(香港)有限公司");
					break;
				case "4":
					result.put("company","美国");
					result.put("Title","Silergy Technology");
					break;
				case "9":
					result.put("company","萨摩亚");
					result.put("Title","Silergy Semiconductor (Samoa) Limited");
					break;
				case "1":
					result.put("company","corp");
					result.put("Title","Silergy Corp");
					break;
				case "21":
					result.put("company","矽望");
					result.put("Title","Silicon Prospect Investment Limited");
					break;
				case "22":
					result.put("company","合肥");
					result.put("Title","合肥矽力杰半导体技术有限公司");
					break;
				case "23":
					result.put("company","澳门");
					result.put("Title","矽力杰半導體(澳門)一人有限公司");
					break;
				case "0":
					result.put("company","其他");
					result.put("Title","矽力杰半导体技术（杭州）有限公司");
					break;
			}
			result.put("dept",petition.getDept());
			result.put("sid",petition.getSid());
			result.put("applyname",petition.getApplyname());
			result.put("applydate", DateUtils.dateTime(petition.getApplydate()));
			String deptmanager = petition.getDeptmanager();
			result.put("deptmanager",deptmanager);
			if (petition.getDeptmanagerIdea()==null){
				result.put("deptidea","");
			}else{
				result.put("deptidea",petition.getDeptmanagerIdea());
			}
			Integer deptmanagerSta = petition.getDeptmanagerSta();
			if (deptmanagerSta==null){
				result.put("deptsta","");
			}else{
				switch (deptmanagerSta){
					case 1:
						result.put("deptsta","同意");
						break;
					case 2:
						result.put("deptsta","不同意");
						break;
				}
			}

			if (petition.getDeptmanageDate()==null){
				result.put("deptdate","");
			}else{
				result.put("deptdate", DateUtils.dateTime(petition.getDeptmanageDate()));
			}
			String law = petition.getLaw();
			switch (law){
				case "2":
				result.put("law","否");
				result.put("lawdate","");
				result.put("lawsta","");
				result.put("lawidea","");
				break;
				case "1":
				result.put("law","是");
				Date lawDate = petition.getLawDate();
				if (lawDate==null){
					result.put("lawdate","");
				}else{
					result.put("lawdate", DateUtils.dateTime(lawDate));
				}
				Integer lawSta = petition.getLawSta();
				if (lawSta==null){
					result.put("lawsta","");
				}else{
					switch (lawSta){
						case 1:
							result.put("lawsta","同意");
							break;
						case 2 :
							result.put("lawsta","不同意");
					}
				}

				String lawIdea = petition.getLawIdea();
				if (StringUtils.isEmpty(lawIdea)){
					result.put("lawidea","");
				}else{
					result.put("lawidea",lawIdea);

				}
				break;
			}

			String hr = petition.getHr();
			if (hr==null){
                result.put("hr","");
            }else{
              switch (hr){
				  case "1":
				  	result.put("hr","周婕");
				  	break;
				  case "2":
					  result.put("hr","沈科如");
					  break;
				  case "3":
					  result.put("hr","王娜");
					  break;
				  case "4":
					  result.put("hr","黃奕晨");
					  break;
				  case "5":
					  result.put("hr","韓詩蕙");
					  break;
				  case "6":
					  result.put("hr","戎玲");
					  break;
				  case "7":
					  result.put("hr","畅佩华");
					  break;
				  case "8":
					  result.put("hr","陈京晶");
					  break;
				  case "9":
					  result.put("hr","张玲");
					  break;
				  case "10":
					  result.put("hr","白永江");
					  break;
				  case "11":
					  result.put("hr","王卓亚");
					  break;
				  case "12":
					  result.put("hr","马海云");
					  break;
				  case "13":
					  result.put("hr","陈君");
					  break;
				  case "14":
					  result.put("hr","宁卓");
					  break;
				  case "15":
					  result.put("hr","潘冠呈");
					  break;
				  case "16":
					  result.put("hr","陳紹偉");
					  break;
				  case "17":
					  result.put("hr","付婷");
					  break;
				  case "18":
					  result.put("hr","王菲");
					  break;
				  case "20":
					  result.put("hr","周婕");
					  break;
				  case "21":
					  result.put("hr","高甜");
					  break;
			  }
            }


			String hrIdea = petition.getHrIdea();
			if (hrIdea==null){
				result.put("hridea","");
			}else{
				result.put("hridea",petition.getHrIdea());
			}
			Integer hrSta = petition.getHrSta();
			if (hrSta==null){
				result.put("hrsta","");
			}else{
				switch (hrSta){
					case 1:
						result.put("hrsta","同意");
						break;
					case 2:
						result.put("hrsta","不同意");
						break;
				}
			}
			if (petition.getHrStaDate()==null){
				result.put("hrdate","");
			}else{
				result.put("hrdate", DateUtils.dateTime(petition.getHrStaDate()));
			}

			if (petition.getGm()==null){
				result.put("gm","");
			}else{
				result.put("gm",petition.getGm());
			}
			if (petition.getGmSta()==null){
				result.put("gmsta","");
			}else{
				switch (petition.getGmSta()){
					case 1:
						result.put("gmsta","同意");
						break;
					case 2:
						result.put("gmsta","不同意");
						break;
				}
			}

			if (petition.getGmDate()==null){
				result.put("gmdate","");
			}else{
				result.put("gmdate", DateUtils.dateTime(petition.getGmDate()));
			}

			if (petition.getGmIdea()==null){
				result.put("gmidea","");
			}else{
				result.put("gmidea",petition.getGmIdea());
			}

			if (petitionSigns.size()>0){
				PetitionSign petitionSign1 = petitionSigns.get(0);
				result.put("gm1",petitionSign1.getAddName());
				Integer result1 = petitionSign1.getResult();
				if (result1!=null){
					switch (result1){
						case 1 :
							result.put("gmsta1","同意");
							break;
						case 2:
							result.put("gmsta1","不同意");
							break;
					}
				}

				String remark = petitionSign1.getRemark();
				if (StringUtils.isNotEmpty(remark)){
					result.put("gmidea1",remark);
				}
				Date auditDate = petitionSign1.getAddTime();
				if (auditDate!=null){
					result.put("gmdate1", DateUtils.dateTime(auditDate));
				}
				if (petitionSigns.size()>1){
					PetitionSign petitionSign2 = petitionSigns.get(1);
					result.put("gm2",petitionSign2.getAddName());
					Integer result2 = petitionSign2.getResult();
					if(result2!=null){
						switch (result2){
							case 1 :
								result.put("gmsta2","同意");
								break;
							case 2:
								result.put("gmsta2","不同意");
								break;
						}
					}
					String remark2 = petitionSign2.getRemark();
					if (StringUtils.isNotEmpty(remark2)){
						result.put("gmidea2",remark);
					}
					Date auditDate2 = petitionSign2.getAddTime();
					if (auditDate2!=null){
						result.put("gmdate2", DateUtils.dateTime(auditDate2));
					}
				}
			}
		}
		if (type==2){
			String comNo = petition.getComNo();
			if (StringUtils.isEmpty(comNo)){
				result.put("comNo","");
			}else {
				result.put("comNo",petition.getComNo());
			}
			result.put("topic",petition.getTopicObj());
			result.put("explanation",petition.getExplanation());
			result.put("curr",petition.getCurr());
			BigDecimal moneyM = petition.getMoneyM();
			result.put("monery",petition.getMoneyM());
			if (petition.getMoreExplain()==null){
				petition.setMoreExplain("");
			}
			result.put("moreexplanin",petition.getMoreExplain());
			String company = petition.getCompany();
			switch (company){
				case "1":
					result.put("company","杭州");
					result.put("Title","矽力杰半导体技术（杭州）有限公司");
					break;
				case "2":
					result.put("company","西安");
					result.put("Title","西安矽力杰半导体技术有限公司");
					break;
				case "3":
					result.put("company","成都");
					result.put("Title","成都矽力杰半导体技术有限公司");
					break;
				case "4":
					result.put("company","上海");
					result.put("Title","上海矽力杰半导体技术有限公司");
					break;
				case "5":
					result.put("company","韩国");
					result.put("Title","Silergy Korea Limited");
					break;
				case "6":
					result.put("company","印度");
					result.put("Title","Silergy Technologies Private Limited");
					break;
				case "7":
					result.put("company","日本");
					result.put("Title","Silergy Semiconductor Samoa Limited");
					break;
				case "8":
					result.put("company","台湾矽力杰");
					result.put("Title","台湾矽力杰科技股份有限公司");
					break;
				case "9":
					result.put("company","南京");
					result.put("Title","南京矽力微电子技术有限公司");
					break;
				case "10":
					result.put("company","香港");
					result.put("Title","Silergy Semiconductor (Hong Kong) Limited 矽力杰半導體(香港)有限公司");
					break;
				case "11":
					result.put("company","上海矽力杰微电子");
					result.put("Title","上海矽力杰微电子技术有限公司");
					break;
				case "12":
					result.put("company","南京香港");
					result.put("Title","南京矽力微電子(香港)有限公司");
					break;
				case "13":
					result.put("company","美国");
					result.put("Title","Silergy Technology");
					break;
				case "14":
					result.put("company","萨摩亚");
					result.put("Title","Silergy Semiconductor (Samoa) Limited");
					break;
				case "15":
					result.put("company","corp");
					result.put("Title","Silergy Corp");
					break;
				case "16":
					result.put("company","矽望");
					result.put("Title","Silicon Prospect Investment Limited");
					break;
				case "17":
					result.put("company","其他");
					result.put("Title","矽力杰半导体技术（杭州）有限公司");
					break;
			}
			result.put("dept",petition.getDept());
			result.put("sid",petition.getSid());
			result.put("applyname",petition.getApplyname());
			result.put("applydate", DateUtils.dateTime(petition.getApplydate()));
			String deptmanager = petition.getDeptmanager();
			result.put("deptmanager",deptmanager);
			if (petition.getDeptmanagerIdea()==null){
				result.put("deptidea","");
			}else{
				result.put("deptidea",petition.getDeptmanagerIdea());
			}

			Integer deptmanagerSta = petition.getDeptmanagerSta();
			if(deptmanagerSta==null){
				result.put("deptsta","");
			}else{
				switch (deptmanagerSta){
					case 1:
						result.put("deptsta","同意");
						break;
					case 2:
						result.put("deptsta","不同意");
						break;
				}
			}
			if (petition.getDeptmanageDate()==null){
				result.put("deptdate","");
			}else{
				result.put("deptdate", DateUtils.dateTime(petition.getDeptmanageDate()));
			}

			String fcManager = petition.getFcManager();
			if (fcManager==null){
				result.put("fc","");
			}else{
				switch (fcManager){
					case "1":
						result.put("fc","王娜");
						break;
					case "2":
						result.put("fc","畅佩华");
						break;
					case "3":
						result.put("fc","马海云");
						break;
					case "4":
						result.put("fc","林佳怡");
						break;
					case "5":
						result.put("fc","韩诗惠");
						break;
					case "6":
						result.put("fc","张玲");
						break;
					case "7":
						result.put("fc","夏晶");
						break;
					case "8":
						result.put("fc","叶小仙");
						break;
					case "9":
						result.put("fc","庞杭艳");
						break;
					case "10":
						result.put("fc","王晶");
						break;
					case "11":
						result.put("fc","高甜");
						break;

				}
			}
			Integer fcSta = petition.getFcManagerSta();
			if (fcSta==null){
				result.put("fcsta","");
			}else{
				switch (fcSta){
					case 1:
						result.put("fcsta","同意");
						break;
					case 2:
						result.put("fcsta","不同意");
						break;
				}
			}
			if (petition.getFcManagerDate()==null){
				result.put("fcdate","");
			}else{
				result.put("fcdate", DateUtils.dateTime(petition.getFcManagerDate()));
			}
			if (petition.getFcManagerIdea()==null){
				result.put("fcidea","");
			}else{
				result.put("fcidea",petition.getFcManagerIdea());
			}
			String cfco = petition.getCfcoManager();
			if(cfco==null){
				result.put("cfco", "");
			}else{
				switch (cfco) {
					case "2":
						result.put("cfco", "庞杭艳");
						break;
					case "3":
						result.put("cfco", "陈京晶");
						break;
				}
			}
			Integer cfcoSta = petition.getCfcoManagerSta();
			if(cfcoSta==null){
				result.put("cfcosta", "");
			}else{
				switch (cfcoSta) {
					case 1:
						result.put("cfcosta", "同意");
						break;
					case 2:
						result.put("cfcosta", "不同意");
						break;
				}
			}
			if (petition.getCfcoManagerDate()==null){
				result.put("cfcodate","");
			}else{
				result.put("cfcodate", DateUtils.dateTime(petition.getCfcoManagerDate()));
			}
			if (petition.getCfcoManagerIdea()==null){
				result.put("cfcoidea","");
			}else{
				result.put("cfcoidea",petition.getCfcoManagerIdea());
			}
			String gm = petition.getGm();
			if (StringUtils.isNotEmpty(gm)){
				result.put("gm",gm);

			}else{
				result.put("gm","");
			}

			Integer gmSta = petition.getGmSta();
			if (gmSta==null){
				result.put("gmsta","");
			}else{
				switch (gmSta){
					case 1:
						result.put("gmsta","同意");
						break;
					case 2:
						result.put("gmsta","不同意");
						break;
				}
			}
			if (petition.getGmIdea()==null){
				result.put("gmidea","");
			}else{
				result.put("gmidea",petition.getGmIdea());
			}
			if (petition.getGmDate()==null){
				result.put("gmdate","");
			}else{
				result.put("gmdate", DateUtils.dateTime(petition.getGmDate()));
			}
			if (petitionSigns.size()>0){
				PetitionSign petitionSign1 = petitionSigns.get(0);
				result.put("gm1",petitionSign1.getAddName());
				Integer result1 = petitionSign1.getResult();
				if(result1!=null){
					switch (result1){
						case 1 :
							result.put("gmsta1","同意");
							break;
						case 2:
							result.put("gmsta1","不同意");
							break;
					}
				}

				String remark = petitionSign1.getRemark();
				if (StringUtils.isNotEmpty(remark)){
					result.put("gmidea1",remark);
				}else{
					result.put("gmidea1","");
				}
				Date auditDate1 = petitionSign1.getAddTime();
				if (auditDate1!=null){
					result.put("gmdate1", DateUtils.dateTime(auditDate1));
				}
				if (petitionSigns.size()>1){
					PetitionSign petitionSign2 = petitionSigns.get(1);
					result.put("gm2",petitionSign2.getAddName());
					Integer result2 = petitionSign2.getResult();
					if(result2!=null){
						switch (result2){
							case 1 :
								result.put("gmsta2","同意");
								break;
							case 2:
								result.put("gmsta2","不同意");
								break;
						}
					}

					String remark2 = petitionSign2.getRemark();
					if (StringUtils.isNotEmpty(remark2)){
						result.put("gmidea2",remark);
					}else{
						result.put("gmidea2","");
					}

					Date auditDate2 = petitionSign2.getAddTime();
					if (auditDate2!=null){
						result.put("gmdate2", DateUtils.dateTime(auditDate2));
					}
				}
			}
		}
		if (type==3){
			result.put("comNo",petition.getComNo());
			result.put("topic",petition.getTopicObj());
			result.put("explanation",petition.getExplanation());
			BigDecimal moneyM = petition.getMoneyM();
			result.put("monery",petition.getMoneyM());
			if (petition.getMoreExplain()==null){
				petition.setMoreExplain("");
			}
			result.put("moreexplanin",petition.getMoreExplain());
			String company = petition.getCompany();
			switch (company){
				case "5":
					result.put("company","杭州");
					result.put("Title","矽力杰半导体技术（杭州）有限公司");
					break;
				case "7":
					result.put("company","西安");
					result.put("Title","西安矽力杰半导体技术有限公司");
					break;
				case "17":
					result.put("company","成都");
					result.put("Title","成都矽力杰半导体技术有限公司");
					break;
				case "19":
					result.put("company","上海");
					result.put("Title","上海矽力杰半导体技术有限公司");
					break;
				case "3":
					result.put("company","韩国");
					result.put("Title","Silergy Korea Limited");
					break;
				case "16":
					result.put("company","印度");
					result.put("Title","Silergy Technologies Private Limited");
					break;
				case "15":
					result.put("company","日本");
					result.put("Title","Silergy Semiconductor Samoa Limited");
					break;
				case "12":
					result.put("company","台湾矽力杰");
					result.put("Title","台湾矽力杰科技股份有限公司");
					break;
				case "6":
					result.put("company","南京");
					result.put("Title","南京矽力微电子技术有限公司");
					break;
				case "13":
					result.put("company","香港");
					result.put("Title","Silergy Semiconductor (Hong Kong) Limited 矽力杰半導體(香港)有限公司");
					break;
				case "14":
					result.put("company","上海矽力杰微电子");
					result.put("Title","上海矽力杰微电子技术有限公司");
					break;
				case "20":
					result.put("company","南京香港");
					result.put("Title","南京矽力微電子(香港)有限公司");
					break;
				case "4":
					result.put("company","美国");
					result.put("Title","Silergy Technology");
					break;
				case "9":
					result.put("company","萨摩亚");
					result.put("Title","Silergy Semiconductor (Samoa) Limited");
					break;
				case "1":
					result.put("company","corp");
					result.put("Title","Silergy Corp");
					break;
				case "21":
					result.put("company","矽望");
					result.put("Title","Silicon Prospect Investment Limited");
					break;
				case "22":
					result.put("company","合肥");
					result.put("Title","合肥矽力杰半导体技术有限公司");
					break;
				case "0":
					result.put("company","其他");
					result.put("Title","矽力杰半导体技术（杭州）有限公司");
					break;
			}
			result.put("dept",petition.getDept());
			result.put("sid",petition.getSid());
			result.put("applyname",petition.getApplyname());
			result.put("applydate", DateUtils.dateTime(petition.getApplydate()));
			String deptmanager = petition.getDeptmanager();
			result.put("deptmanager",deptmanager);
			if (petition.getDeptmanagerIdea()==null){
				result.put("deptidea","");
			}else{
				result.put("deptidea",petition.getDeptmanagerIdea());
			}

			Integer deptmanagerSta = petition.getDeptmanagerSta();
			if(deptmanagerSta==null){
				result.put("deptsta","");
			}else{
				switch (deptmanagerSta){
					case 1:
						result.put("deptsta","同意");
						break;
					case 2:
						result.put("deptsta","不同意");
						break;
				}
			}
			if (petition.getDeptmanageDate()==null){
				result.put("deptdate","");
			}else{
				result.put("deptdate", DateUtils.dateTime(petition.getDeptmanageDate()));
			}
			String acOfficer = petition.getAcOfficer();
			if (acOfficer!=null){
				result.put("acOfficer",acOfficer);
			}
			Integer acOfficerSta = petition.getAcOfficerSta();
			if(acOfficerSta==null){
				result.put("acOfficerSta", "");
			}else{
				switch (acOfficerSta) {
					case 1:
						result.put("acOfficerSta", "同意");
						break;
					case 2:
						result.put("acOfficerSta", "不同意");
						break;
				}
			}
			if (petition.getAcOfficerDate()==null){
				result.put("acOfficerdate","");
			}else{
				result.put("acOfficerdate", DateUtils.dateTime(petition.getAcOfficerDate()));
			}
			if (petition.getAcOfficerIdea()==null){
				result.put("acOfficeridea","");
			}else{
				result.put("acOfficeridea",petition.getAcOfficerIdea());
			}
			String gm = petition.getGm();
			if (StringUtils.isNotEmpty(gm)){
				result.put("gm",gm);

			}else{
				result.put("gm","");
			}

			Integer gmSta = petition.getGmSta();
			if (gmSta==null){
				result.put("gmsta","");
			}else{
				switch (gmSta){
					case 1:
						result.put("gmsta","同意");
						break;
					case 2:
						result.put("gmsta","不同意");
						break;
				}
			}
			if (petition.getGmIdea()==null){
				result.put("gmidea","");
			}else{
				result.put("gmidea",petition.getGmIdea());
			}
			if (petition.getGmDate()==null){
				result.put("gmdate","");
			}else {
				result.put("gmdate", DateUtils.dateTime(petition.getGmDate()));
			}
			if (petitionSigns.size()>0){
				PetitionSign petitionSign1 = petitionSigns.get(0);
				result.put("gm1",petitionSign1.getAddName());
				Integer result1 = petitionSign1.getResult();
				if (result1!=null){
					switch (result1){
						case 1 :
							result.put("gmsta1","同意");
							break;
						case 2:
							result.put("gmsta1","不同意");
							break;
					}
				}
				String remark = petitionSign1.getRemark();
				if (StringUtils.isNotEmpty(remark)){
					result.put("gmidea1",remark);
				}else{
					result.put("gmidea1","");
				}

				Date auditDate1 = petitionSign1.getAddTime();
				if (auditDate1!=null){
					result.put("gmdate1", DateUtils.dateTime(auditDate1));
				}
				if (petitionSigns.size()>1){
					PetitionSign petitionSign2 = petitionSigns.get(1);
					result.put("gm2",petitionSign2.getAddName());
					Integer result2 = petitionSign2.getResult();
					if(result2!=null){
						switch (result2){
							case 1 :
								result.put("gmsta2","同意");
								break;
							case 2:
								result.put("gmsta2","不同意");
								break;
						}
					}

					String remark2 = petitionSign2.getRemark();
					if (StringUtils.isNotEmpty(remark2)){
						result.put("gmidea2",remark);
					}else{
						result.put("gmidea2","");
					}

					Date auditDate2 = petitionSign2.getAddTime();
					if (auditDate2!=null){
						result.put("gmdate2", DateUtils.dateTime(auditDate2));
					}
				}

			}
		}
		return result;
	}

    //不需要主管审批和法务的流程
    private Integer updateStaNodept(Long id,String sendCode,Petition petition,SysUser sysyuser,Integer sta){
        petition.setStatus(sta);
        petition.setUpdateBy(sysyuser.getLoginName());
        petition.setUpdateDate(new Date());
        petition.setFromSendto(sendCode);
        int updatePetition = petitionMapper.updatePetition(petition);
        String deptmanager = petition.getDeptmanager();
        if (updatePetition>0){
            petitionComponent.sendEmailAudit(id,sendCode);
            return updatePetition;
        }else{
            throw new GlobalException("更新错误");
        }

    }
	//获取直接主管(如果有代签主管优先获取代签主管)
	private String getZgSid(String sid){
		SysUser user = userMapper.selectUserByUserCode(sid);
		//直接主管
		String zgsid = user.getZgsid();
		//代签主管
		String agentsid = user.getPurchasesid();
		if (StringUtils.isNotEmpty(agentsid)){
			return agentsid;
		}else{
			return zgsid;
		}
	}

	//主管同意后根据选择不同进行不同流程
	private Integer updateSta(Long id,String sendCode,Petition petition,SysUser sysyuser,Integer sta,Petition pe){
		petition.setStatus(sta);
		petition.setUpdateBy(sysyuser.getLoginName());
		petition.setUpdateDate(new Date());
		petition.setFromSendto(sendCode);
		petition.setDeptmanagerSta(pe.getDeptmanagerSta());
		petition.setDeptmanageDate(new Date());
		String deptmanagerIdea = pe.getDeptmanagerIdea();
		if (deptmanagerIdea==null){
			petition.setDeptmanagerIdea("");
		}
		petition.setDeptmanagerIdea(pe.getDeptmanagerIdea());
		int updatePetition = petitionMapper.updatePetition(petition);
		String law = petition.getLaw();
		String deptmanager = petition.getDeptmanager();
		if (updatePetition>0){
			petitionComponent.sendEmailAudit(id,sendCode);
			writelog(id,sysyuser,sta,deptmanagerIdea,pe.getDeptmanagerSta(),petition.getTypeType(),petition.getCompany());
			return updatePetition;
		}else{
			throw new GlobalException("更新错误");
		}

	}

	private Integer writelog(Long id, SysUser sysyuser,Integer sta,String idea,Integer sta1,Integer type,String company){
		//记录日志
		ProcessFlow petitionLog =new ProcessFlow();
		petitionLog.setOrderId(id);
		petitionLog.setOrderType(type);
		petitionLog.setCreateTime(new Date());
		petitionLog.setCreateBy(sysyuser.getUserName());
		petitionLog.setCreateById(sysyuser.getUserId());
		petitionLog.setAuditResult(sta);
		petitionLog.setStatus(sta1);
//		petitionLog.setConpany(company);
		if (idea==null){
			idea="";
		}
		petitionLog.setRemark(idea);
		int i = processFlowMapper.insertProcessFlow(petitionLog);
		if (i>0){
			return i;
		}else{
			throw new GlobalException("记录日志失败");
		}

	}

	/**
	 * 获取编号  格式:类型+YYYYMM+自增编号
	 * @param petition
	 * @return
	 */
	private String creatComNo(Petition petition){
		//获取类型
		Integer typeType = petition.getTypeType();
		String company = petition.getCompany();
		String com="";
		switch (company){
			//杭州
			case "5":
				com="HZ";
				break;
			case "7":
				//西安
				com="XA";
				break;
			case "17":
				//成都
				com="CD";
				break;
			case "19":
				//上海
				com="PD";
				break;
			case "3":
				//韩国
				com="KR";
				break;
			case "16":
				//印度
				com="IN";
				break;
			case "15":
				//日本
				com="JP";
				break;
			case "12":
				//台湾
				com="HC";
				break;
			case "6":
				//南京
				com="NJ";
				break;
			case "13":
				//香港
				com="HK";
				break;
			case "14":
				//上海矽力杰微电子
				com="PX";
				break;
			case "20":
				//南京香港
				com="NJHK";
				break;
			case "4":
				//美国
				com="US";
				break;
			case "9":
				//萨摩亚
				com="ISOS";
				break;
			case "1":
				com="corp";
				break;
			case "21":
				//矽望
				com="XW";
				break;
			case "22":
				//合肥
				com="HF";
			case "23":
				//澳门
				com="AM";
				break;

		}
		String type="";
		String localTrmSeqNum="";
		Integer i = 1;
		List<String> stringList = petitionMapper.selectPetitionByNow(typeType, company);
		if(!stringList.isEmpty()){
			List<Integer> integerList = new ArrayList<>();
			for(String s :stringList){
				if(!StringUtils.isEmpty(s)){
					if(s.contains("corp") || s.contains("ISOS") || s.contains("NJHK")){
						String substrings = StringUtils.substring(s, 10, 13);
						integerList.add(Integer.valueOf(substrings));
					}else {
						String substrings = StringUtils.substring(s, 8, 11);
						integerList.add(Integer.valueOf(substrings));
					}
				}
			}
			Collections.sort(integerList);
			if(integerList.isEmpty()){
				i = 1;
			}else {
				i = ListUtil.getLastElement(integerList);
			}
		}
		if (typeType==1){
			type="A";
			localTrmSeqNum = getDateNumUtils.getLocalTrmSeqNum(i);
		}else if (typeType==2){
			type="B";
			localTrmSeqNum = getDateNumUtils.getLocalTrmSeqNum(i);
		}else if (typeType==3){
			type="C";
			localTrmSeqNum = getDateNumUtils.getLocalTrmSeqNum(i);
		}
		String comNo=com+localTrmSeqNum+type;
		return comNo;
	}


	//添加加签人员
	private int addaudit(List<PetitionSign> list,Petition petition,String tosend,Integer status,SysUser sysUser){
        int i=1;
        for (PetitionSign petitionAddaudit : list) {
            String sid = petitionAddaudit.getSid();
			SysUser user = userMapper.selectUserByLoginName(sid);
			if (user==null){
				throw new GlobalException("用户不存在,请重新输入");
			}
			String code= user.getLoginName();
            petitionAddaudit.setCreateBy(sysUser.getLoginName());
            petitionAddaudit.setCreateTime(new Date());
            petitionAddaudit.setSid(code);
			petitionAddaudit.setAddName(user.getUserName());
			petitionAddaudit.setPetitionId(petition.getId());
            petitionAddaudit.setFromsend(sysUser.getLoginName());
            petitionAddaudit.setOldStatus(status);
            petitionAddaudit.setTosend(tosend);
			petitionAddaudit.setSignType(3);
			PetitionSign pp=new PetitionSign();
            pp.setSid(sid);
            pp.setPetitionId(petition.getId());
            pp.setSignType(3);
			List<PetitionSign> petitionAddaudits = petitionSignMapper.selectPetitionSignList(pp);
			if (petitionAddaudits.size()>0){
				throw new GlobalException("加签已添加,请勿重复加入");
			}
			i = petitionSignMapper.insertPetitionSign(petitionAddaudit);
			if(i>0){
				petitionComponent.sendEmailAddAudit(petition.getId(),code);
			}
        }
        return i;
    }

	/**
	 * 如果撤回清除单子上所有人审核记录
	 * @param id
	 */
	private int deleteRecord(Long id){
		int i = 0;
		//查询出当前单子
		Petition petition = petitionMapper.selectPetitionById(id);
		i = petitionMapper.updatePeById(petition);
		if (i<0){
			throw new GlobalException("修改人员失败");
		}

		//获取新增核准人
		PetitionSign ps=new PetitionSign();
		ps.setPetitionId(id);
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignMapper.selectPetitionSignList(ps);
		//修改核准人的信息
		for (PetitionSign petitionSign : petitionSigns) {
			petitionSignMapper.updatePeSign(petitionSign);
		}

		//获取新增会审人
		PetitionSign  pt=new PetitionSign();
		pt.setPetitionId(id);
		pt.setSignType(2);
		List<PetitionSign> petitionTrialList = petitionSignMapper.selectPetitionSignList(pt);
		//修改会审人的信息
		for (PetitionSign petitionTrial : petitionTrialList) {
			petitionSignMapper.updatePeSign(petitionTrial);
		}
		//处理加签人员
		i = petitionSignMapper.deletePetitionSignById(petition.getId());
		if (i<0){
			throw new GlobalException("修改加签人员失败");
		}

		return i;
	}

	/**
	 * 上传审批截图进行签核
	 * @param file
	 * @param petitionId
	 * @param sysUser
	 * @return
	 */
	@Override
	public Integer insertPetitionFile(MultipartFile file, long petitionId, SysUser sysUser) {
		String filePath  = "";
		int updateRes = 0;
		int submit=0;
		try {
			String uploadPath = FormFileConstants.PETITION_PATH;
			// 上传文件路径
			String filePath1 = RuoYiConfig.getUploadPath() + uploadPath;
			try {
				filePath = uploadPath + FileUploadUtils.uploads(filePath1, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
			} catch (InvalidExtensionException e) {
				e.printStackTrace();
			}
			if (StringUtils.isEmpty(filePath)){
				throw new GlobalException("上传审批截图出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
			}
		}catch (IOException e){
			throw new GlobalException("上传审批截图出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
		}

			//将原单子查出来
			Petition petition = petitionMapper.selectPetitionById(petitionId);
			if(StringUtils.isNull(petition)){
				throw new GlobalException("上传审批截图过程出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
			}
			String sendToCode = petition.getFromSendto();
			SysUser user = userMapper.selectUserByUserCode(sendToCode);
			PetitionDto petitionDto=new PetitionDto();

		//获取所有的核审人
		PetitionSign  ps=new PetitionSign();
		ps.setPetitionId(petition.getId());
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignService.selectPetitionSignList(ps);

		//获取所有的会审人
		PetitionSign  pt=new PetitionSign();
		pt.setPetitionId(petition.getId());
		pt.setSignType(2);
		List<PetitionSign> petitionTrialList = petitionSignService.selectPetitionSignList(pt);

		//获取所有的加签人
		PetitionSign  add=new PetitionSign();
		add.setPetitionId(petition.getId());
		add.setSignType(3);
		List<PetitionSign> petitionAddauditList = petitionSignService.selectPetitionSignList(add);

		List<PetitionSign> ptlist=new ArrayList<>();
		List<PetitionSign> pslist=new ArrayList<>();
		List<PetitionSign>addlist=new ArrayList<>();
			//模拟待办人处理流程
		switch (petition.getStatus()){
			//加签人员审核
			case 0:
				//构造处理结果
				//会有好几个加签人，遍历出核准人列表中员工编号和当前表单中下一个待审核的人编号一致的那个
				for (PetitionSign petitionAddaudit : petitionAddauditList) {
					String sid = petitionAddaudit.getSid();
					if(sid.equals(petition.getFromSendto())){
						addlist.add(petitionAddaudit);
					}
				}
				PetitionSign PetitionAddauditlist = addlist.get(0);
				PetitionSign PetitionAddaudit = petitionSignMapper.selectPetitionSignById(PetitionAddauditlist.getId());
				PetitionAddaudit.setResult(1);
				PetitionAddaudit.setRemark(sysUser.getUserName() + "已上传审批截图");
				petitionDto.setPetition(petition);
				petitionDto.setPetitionAddauditList(Collections.singletonList(PetitionAddaudit));
				submit = updateaddAudit(petitionDto, user);
				break;
			//主管审核
			case 2:
				//构造处理结果
				petition.setDeptmanagerSta(1);
				petition.setDeptmanagerIdea(sysUser.getUserName() + "已上传审批截图");
				petitionDto.setPetition(petition);
				submit = updateAudit(petitionDto, user);
				break;
			//待印鉴保管人审核
			case 3:
				petition.setHrSta(1);
				petition.setHrIdea(sysUser.getUserName() + "已上传审批截图");
				petitionDto.setPetition(petition);
				submit = updateAuditHR(petitionDto, user);
				break;
			//待财务总账审核
			case 4:
				petition.setFcManagerSta(1);
				petition.setFcManagerIdea(sysUser.getUserName() + "已上传审批截图");
				petitionDto.setPetition(petition);
				submit = updateAuditcw(petitionDto, user);
				break;
			//待财务经理审核
			case 5:
				petition.setCfcoManagerSta(1);
				petition.setCfcoManagerIdea(sysUser.getUserName() + "已上传审批截图");
				petitionDto.setPetition(petition);
				submit = updateAuditcwManager(petitionDto, user);
				break;
			//待核准人审核
			case 6:
				petition.setGmSta(1);
				petition.setGmIdea(sysUser.getUserName() + "已上传审批截图");
				petitionDto.setPetition(petition);
				submit = updateAuditGM(petitionDto, user);
				break;
			//提交待法务审核
			case 9:
				petition.setLawSta(1);
				petition.setLawIdea(sysUser.getUserName() + "已上传审批截图");
				petitionDto.setPetition(petition);
				submit = updateFwAudit(petitionDto, user);
				break;
			//新增核准人审核
			case 11:
				//同时会有好几个核准人，遍历出核准人列表中员工编号和当前表单中下一个待审核的人编号一致的那个
				for (PetitionSign petitionSign : petitionSigns) {
					String sid = petitionSign.getSid();
					if(sid.equals(petition.getFromSendto())){
						pslist.add(petitionSign);
					}
				}
				PetitionSign psign = pslist.get(0);
				//获取当前审核的核准人所有信息
				PetitionSign petitionSign = petitionSignMapper.selectPetitionSignById(psign.getId());
				petitionSign.setResult(1);
				petitionSign.setRemark(sysUser.getUserName() + "已上传审批截图");
				petitionDto.setPetition(petition);
				petitionDto.setPetitionSignList(Collections.singletonList(petitionSign));
				submit = updateaddSign(petitionDto, user);
				break;
			//C类审核人审核
			case 12:
				petition.setAcOfficerSta(1);
				petition.setAcOfficerIdea(sysUser.getUserName() + "已上传审批截图");
				petitionDto.setPetition(petition);
				submit = updateAuditAC(petitionDto, user);
				break;
			//新增会审人审核
			case 13:
				//同时会有好几个会审人，遍历出核准人列表中员工编号和当前表单中下一个待审核的人编号一致的那个
				for (PetitionSign petitionTrial : petitionTrialList) {
					String sid = petitionTrial.getSid();
					if(sid.equals(petition.getFromSendto())){
						ptlist.add(petitionTrial);
					}
				}
				PetitionSign trial = ptlist.get(0);
				PetitionSign Petitiontrial = petitionSignMapper.selectPetitionSignById(trial.getId());
				Petitiontrial.setResult(1);
				Petitiontrial.setRemark(sysUser.getUserName() + "已上传审批截图");
				petitionDto.setPetition(petition);
				petitionDto.setPetitionTrialList(Collections.singletonList(Petitiontrial));
				submit = updateaddTrial(petitionDto, user);
				break;
		}
			if(submit < 0){
				throw new GlobalException("上传审批截图过程出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
			}
			//保存截图文件信息
			Petition Petition = new Petition();
			Petition.setId(petitionId);
			//保存文件名
			Petition.setCloseFileName(file.getOriginalFilename().replaceAll(" ","").replaceAll("&",""));
			//保存文件路径
			Petition.setCloseFile(filePath);
			updateRes = updatePetition(Petition);
		return updateRes;
	}

	/**
	 * 查询文件列表
	 * @param parentId 父id
	 * @param type 类型
	 * @return 文件列表
	 */
	@Override
	public List<FormFile> selectFormFile(Long parentId, Integer type){
		return formFileMapper.selectFormFileList(new FormFile(parentId, type));
	}
}
