/**
 * Copyright &copy; 2013-2017 <a href="https://www.infosouth.com.cn/">Infosouth</a> All rights reserved.
 */
package cn.com.infosouth.arj21.domain;

import javax.validation.constraints.Size;

/**
 * 默认首页-机型数据对比设置Entity
 * @author zy
 * @version 2018-03-21
 */
public class InfoDefaultindexActypecontrastset{
	
	private static final long serialVersionUID = 1L;
	private String id;		//	id
	private String loginAdmin;		// login_admin
	private String statisticstitle;		// statisticstitle
	private String chooseParamnameCol1;		// choose_paramname_col1
	private String chooseParamnameCol2;		// choose_paramname_col2
	private String chooseParamnameCol3;		// choose_paramname_col3
	private String chooseParamnameCol4;		// choose_paramname_col4
	private String jobidCrj900;		// jobid_crj900
	private String jobidB737;		// jobid_b737
	private String jobidA320;		// jobid_a320
	private String jobidB777;		// jobid_b777
	private String jobidA330;		// jobid_a330
	private String modelnameCrj900Col1;		// modelname_crj900_col1
	private String modelnameCrj900Col2;		// modelname_crj900_col2
	private String modelnameCrj900Col3;		// modelname_crj900_col3
	private String modelnameCrj900Col4;		// modelname_crj900_col4
	private String modelnameB737Col1;		// modelname_b737_col1
	private String modelnameB737Col2;		// modelname_b737_col2
	private String modelnameB737Col3;		// modelname_b737_col3
	private String modelnameB737Col4;		// modelname_b737_col4
	private String modelnameA320Col1;		// modelname_a320_col1
	private String modelnameA320Col2;		// modelname_a320_col2
	private String modelnameA320Col3;		// modelname_a320_col3
	private String modelnameA320Col4;		// modelname_a320_col4
	private String modelnameB777Col1;		// modelname_b777_col1
	private String modelnameB777Col2;		// modelname_b777_col2
	private String modelnameB777Col3;		// modelname_b777_col3
	private String modelnameB777Col4;		// modelname_b777_col4
	private String modelnameA330Col1;		// modelname_a330_col1
	private String modelnameA330Col2;		// modelname_a330_col2
	private String modelnameA330Col3;		// modelname_a330_col3
	private String modelnameA330Col4;		// modelname_a330_col4
	private String paramnameCrj900Col1;		// paramname_crj900_col1
	private String paramnameCrj900Col1Statisticsjson;		// paramname_crj900_col1_statisticsjson
	private String paramnameCrj900Col2;		// paramname_crj900_col2
	private String paramnameCrj900Col2Statisticsjson;		// paramname_crj900_col2_statisticsjson
	private String paramnameCrj900Col3;		// paramname_crj900_col3
	private String paramnameCrj900Col3Statisticsjson;		// paramname_crj900_col3_statisticsjson
	private String paramnameCrj900Col4;		// paramname_crj900_col4
	private String paramnameCrj900Col4Statisticsjson;		// paramname_crj900_col4_statisticsjson
	private String paramnameB737Col1;		// paramname_b737_col1
	private String paramnameB737Col1Statisticsjson;		// paramname_b737_col1_statisticsjson
	private String paramnameB737Col2;		// paramname_b737_col2
	private String paramnameB737Col2Statisticsjson;		// paramname_b737_col2_statisticsjson
	private String paramnameB737Col3;		// paramname_b737_col3
	private String paramnameB737Col3Statisticsjson;		// paramname_b737_col3_statisticsjson
	private String paramnameB737Col4;		// paramname_b737_col4
	private String paramnameB737Col4Statisticsjson;		// paramname_b737_col4_statisticsjson
	private String paramnameA320Col1;		// paramname_a320_col1
	private String paramnameA320Col1Statisticsjson;		// paramname_a320_col1_statisticsjson
	private String paramnameA320Col2;		// paramname_a320_col2
	private String paramnameA320Col2Statisticsjson;		// paramname_a320_col2_statisticsjson
	private String paramnameA320Col3;		// paramname_a320_col3
	private String paramnameA320Col3Statisticsjson;		// paramname_a320_col3_statisticsjson
	private String paramnameA320Col4;		// paramname_a320_col4
	private String paramnameA320Col4Statisticsjson;		// paramname_a320_col4_statisticsjson
	private String paramnameB777Col1;		// paramname_b777_col1
	private String paramnameB777Col1Statisticsjson;		// paramname_b777_col1_statisticsjson
	private String paramnameB777Col2;		// paramname_b777_col2
	private String paramnameB777Col2Statisticsjson;		// paramname_b777_col2_statisticsjson
	private String paramnameB777Col3;		// paramname_b777_col3
	private String paramnameB777Col3Statisticsjson;		// paramname_b777_col3_statisticsjson
	private String paramnameB777Col4;		// paramname_b777_col4
	private String paramnameB777Col4Statisticsjson;		// paramname_b777_col4_statisticsjson
	private String paramnameA330Col1;		// paramname_a330_col1
	private String paramnameA330Col1Statisticsjson;		// paramname_a330_col1_statisticsjson
	private String paramnameA330Col2;		// paramname_a330_col2
	private String paramnameA330Col2Statisticsjson;		// paramname_a330_col2_statisticsjson
	private String paramnameA330Col3;		// paramname_a330_col3
	private String paramnameA330Col3Statisticsjson;		// paramname_a330_col3_statisticsjson
	private String paramnameA330Col4;		// paramname_a330_col4
	private String paramnameA330Col4Statisticsjson;		// paramname_a330_col4_statisticsjson
	
	public InfoDefaultindexActypecontrastset() {
		super();
	}

	@Size(min=0, max=30, message="login_admin长度必须介于 0 和 30 之间")
	public String getLoginAdmin() {
		return loginAdmin;
	}

	public void setLoginAdmin(String loginAdmin) {
		this.loginAdmin = loginAdmin;
	}
	
	@Size(min=0, max=64, message="statisticstitle长度必须介于 0 和 64 之间")
	public String getStatisticstitle() {
		return statisticstitle;
	}

	public void setStatisticstitle(String statisticstitle) {
		this.statisticstitle = statisticstitle;
	}
	
	@Size(min=0, max=64, message="choose_paramname_col1长度必须介于 0 和 64 之间")
	public String getChooseParamnameCol1() {
		return chooseParamnameCol1;
	}

	public void setChooseParamnameCol1(String chooseParamnameCol1) {
		this.chooseParamnameCol1 = chooseParamnameCol1;
	}
	
	@Size(min=0, max=64, message="choose_paramname_col2长度必须介于 0 和 64 之间")
	public String getChooseParamnameCol2() {
		return chooseParamnameCol2;
	}

	public void setChooseParamnameCol2(String chooseParamnameCol2) {
		this.chooseParamnameCol2 = chooseParamnameCol2;
	}
	
	@Size(min=0, max=64, message="choose_paramname_col3长度必须介于 0 和 64 之间")
	public String getChooseParamnameCol3() {
		return chooseParamnameCol3;
	}

	public void setChooseParamnameCol3(String chooseParamnameCol3) {
		this.chooseParamnameCol3 = chooseParamnameCol3;
	}
	
	@Size(min=0, max=64, message="choose_paramname_col4长度必须介于 0 和 64 之间")
	public String getChooseParamnameCol4() {
		return chooseParamnameCol4;
	}

	public void setChooseParamnameCol4(String chooseParamnameCol4) {
		this.chooseParamnameCol4 = chooseParamnameCol4;
	}
	
	@Size(min=0, max=64, message="jobid_crj900长度必须介于 0 和 64 之间")
	public String getJobidCrj900() {
		return jobidCrj900;
	}

	public void setJobidCrj900(String jobidCrj900) {
		this.jobidCrj900 = jobidCrj900;
	}
	
	@Size(min=0, max=64, message="jobid_b737长度必须介于 0 和 64 之间")
	public String getJobidB737() {
		return jobidB737;
	}

	public void setJobidB737(String jobidB737) {
		this.jobidB737 = jobidB737;
	}
	
	@Size(min=0, max=64, message="jobid_a320长度必须介于 0 和 64 之间")
	public String getJobidA320() {
		return jobidA320;
	}

	public void setJobidA320(String jobidA320) {
		this.jobidA320 = jobidA320;
	}
	
	@Size(min=0, max=64, message="jobid_b777长度必须介于 0 和 64 之间")
	public String getJobidB777() {
		return jobidB777;
	}

	public void setJobidB777(String jobidB777) {
		this.jobidB777 = jobidB777;
	}
	
	@Size(min=0, max=64, message="jobid_a330长度必须介于 0 和 64 之间")
	public String getJobidA330() {
		return jobidA330;
	}

	public void setJobidA330(String jobidA330) {
		this.jobidA330 = jobidA330;
	}
	
	@Size(min=0, max=64, message="modelname_crj900_col1长度必须介于 0 和 64 之间")
	public String getModelnameCrj900Col1() {
		return modelnameCrj900Col1;
	}

	public void setModelnameCrj900Col1(String modelnameCrj900Col1) {
		this.modelnameCrj900Col1 = modelnameCrj900Col1;
	}
	
	@Size(min=0, max=64, message="modelname_crj900_col2长度必须介于 0 和 64 之间")
	public String getModelnameCrj900Col2() {
		return modelnameCrj900Col2;
	}

	public void setModelnameCrj900Col2(String modelnameCrj900Col2) {
		this.modelnameCrj900Col2 = modelnameCrj900Col2;
	}
	
	@Size(min=0, max=64, message="modelname_crj900_col3长度必须介于 0 和 64 之间")
	public String getModelnameCrj900Col3() {
		return modelnameCrj900Col3;
	}

	public void setModelnameCrj900Col3(String modelnameCrj900Col3) {
		this.modelnameCrj900Col3 = modelnameCrj900Col3;
	}
	
	@Size(min=0, max=64, message="modelname_crj900_col4长度必须介于 0 和 64 之间")
	public String getModelnameCrj900Col4() {
		return modelnameCrj900Col4;
	}

	public void setModelnameCrj900Col4(String modelnameCrj900Col4) {
		this.modelnameCrj900Col4 = modelnameCrj900Col4;
	}
	
	@Size(min=0, max=64, message="modelname_b737_col1长度必须介于 0 和 64 之间")
	public String getModelnameB737Col1() {
		return modelnameB737Col1;
	}

	public void setModelnameB737Col1(String modelnameB737Col1) {
		this.modelnameB737Col1 = modelnameB737Col1;
	}
	
	@Size(min=0, max=64, message="modelname_b737_col2长度必须介于 0 和 64 之间")
	public String getModelnameB737Col2() {
		return modelnameB737Col2;
	}

	public void setModelnameB737Col2(String modelnameB737Col2) {
		this.modelnameB737Col2 = modelnameB737Col2;
	}
	
	@Size(min=0, max=64, message="modelname_b737_col3长度必须介于 0 和 64 之间")
	public String getModelnameB737Col3() {
		return modelnameB737Col3;
	}

	public void setModelnameB737Col3(String modelnameB737Col3) {
		this.modelnameB737Col3 = modelnameB737Col3;
	}
	
	@Size(min=0, max=64, message="modelname_b737_col4长度必须介于 0 和 64 之间")
	public String getModelnameB737Col4() {
		return modelnameB737Col4;
	}

	public void setModelnameB737Col4(String modelnameB737Col4) {
		this.modelnameB737Col4 = modelnameB737Col4;
	}
	
	@Size(min=0, max=64, message="modelname_a320_col1长度必须介于 0 和 64 之间")
	public String getModelnameA320Col1() {
		return modelnameA320Col1;
	}

	public void setModelnameA320Col1(String modelnameA320Col1) {
		this.modelnameA320Col1 = modelnameA320Col1;
	}
	
	@Size(min=0, max=64, message="modelname_a320_col2长度必须介于 0 和 64 之间")
	public String getModelnameA320Col2() {
		return modelnameA320Col2;
	}

	public void setModelnameA320Col2(String modelnameA320Col2) {
		this.modelnameA320Col2 = modelnameA320Col2;
	}
	
	@Size(min=0, max=64, message="modelname_a320_col3长度必须介于 0 和 64 之间")
	public String getModelnameA320Col3() {
		return modelnameA320Col3;
	}

	public void setModelnameA320Col3(String modelnameA320Col3) {
		this.modelnameA320Col3 = modelnameA320Col3;
	}
	
	@Size(min=0, max=64, message="modelname_a320_col4长度必须介于 0 和 64 之间")
	public String getModelnameA320Col4() {
		return modelnameA320Col4;
	}

	public void setModelnameA320Col4(String modelnameA320Col4) {
		this.modelnameA320Col4 = modelnameA320Col4;
	}
	
	@Size(min=0, max=64, message="modelname_b777_col1长度必须介于 0 和 64 之间")
	public String getModelnameB777Col1() {
		return modelnameB777Col1;
	}

	public void setModelnameB777Col1(String modelnameB777Col1) {
		this.modelnameB777Col1 = modelnameB777Col1;
	}
	
	@Size(min=0, max=64, message="modelname_b777_col2长度必须介于 0 和 64 之间")
	public String getModelnameB777Col2() {
		return modelnameB777Col2;
	}

	public void setModelnameB777Col2(String modelnameB777Col2) {
		this.modelnameB777Col2 = modelnameB777Col2;
	}
	
	@Size(min=0, max=64, message="modelname_b777_col3长度必须介于 0 和 64 之间")
	public String getModelnameB777Col3() {
		return modelnameB777Col3;
	}

	public void setModelnameB777Col3(String modelnameB777Col3) {
		this.modelnameB777Col3 = modelnameB777Col3;
	}
	
	@Size(min=0, max=64, message="modelname_b777_col4长度必须介于 0 和 64 之间")
	public String getModelnameB777Col4() {
		return modelnameB777Col4;
	}

	public void setModelnameB777Col4(String modelnameB777Col4) {
		this.modelnameB777Col4 = modelnameB777Col4;
	}
	
	@Size(min=0, max=64, message="modelname_a330_col1长度必须介于 0 和 64 之间")
	public String getModelnameA330Col1() {
		return modelnameA330Col1;
	}

	public void setModelnameA330Col1(String modelnameA330Col1) {
		this.modelnameA330Col1 = modelnameA330Col1;
	}
	
	@Size(min=0, max=64, message="modelname_a330_col2长度必须介于 0 和 64 之间")
	public String getModelnameA330Col2() {
		return modelnameA330Col2;
	}

	public void setModelnameA330Col2(String modelnameA330Col2) {
		this.modelnameA330Col2 = modelnameA330Col2;
	}
	
	@Size(min=0, max=64, message="modelname_a330_col3长度必须介于 0 和 64 之间")
	public String getModelnameA330Col3() {
		return modelnameA330Col3;
	}

	public void setModelnameA330Col3(String modelnameA330Col3) {
		this.modelnameA330Col3 = modelnameA330Col3;
	}
	
	@Size(min=0, max=64, message="modelname_a330_col4长度必须介于 0 和 64 之间")
	public String getModelnameA330Col4() {
		return modelnameA330Col4;
	}

	public void setModelnameA330Col4(String modelnameA330Col4) {
		this.modelnameA330Col4 = modelnameA330Col4;
	}
	
	@Size(min=0, max=64, message="paramname_crj900_col1长度必须介于 0 和 64 之间")
	public String getParamnameCrj900Col1() {
		return paramnameCrj900Col1;
	}

	public void setParamnameCrj900Col1(String paramnameCrj900Col1) {
		this.paramnameCrj900Col1 = paramnameCrj900Col1;
	}
	
	@Size(min=0, max=300, message="paramname_crj900_col1_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameCrj900Col1Statisticsjson() {
		return paramnameCrj900Col1Statisticsjson;
	}

	public void setParamnameCrj900Col1Statisticsjson(String paramnameCrj900Col1Statisticsjson) {
		this.paramnameCrj900Col1Statisticsjson = paramnameCrj900Col1Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_crj900_col2长度必须介于 0 和 64 之间")
	public String getParamnameCrj900Col2() {
		return paramnameCrj900Col2;
	}

	public void setParamnameCrj900Col2(String paramnameCrj900Col2) {
		this.paramnameCrj900Col2 = paramnameCrj900Col2;
	}
	
	@Size(min=0, max=300, message="paramname_crj900_col2_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameCrj900Col2Statisticsjson() {
		return paramnameCrj900Col2Statisticsjson;
	}

	public void setParamnameCrj900Col2Statisticsjson(String paramnameCrj900Col2Statisticsjson) {
		this.paramnameCrj900Col2Statisticsjson = paramnameCrj900Col2Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_crj900_col3长度必须介于 0 和 64 之间")
	public String getParamnameCrj900Col3() {
		return paramnameCrj900Col3;
	}

	public void setParamnameCrj900Col3(String paramnameCrj900Col3) {
		this.paramnameCrj900Col3 = paramnameCrj900Col3;
	}
	
	@Size(min=0, max=300, message="paramname_crj900_col3_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameCrj900Col3Statisticsjson() {
		return paramnameCrj900Col3Statisticsjson;
	}

	public void setParamnameCrj900Col3Statisticsjson(String paramnameCrj900Col3Statisticsjson) {
		this.paramnameCrj900Col3Statisticsjson = paramnameCrj900Col3Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_crj900_col4长度必须介于 0 和 64 之间")
	public String getParamnameCrj900Col4() {
		return paramnameCrj900Col4;
	}

	public void setParamnameCrj900Col4(String paramnameCrj900Col4) {
		this.paramnameCrj900Col4 = paramnameCrj900Col4;
	}
	
	@Size(min=0, max=300, message="paramname_crj900_col4_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameCrj900Col4Statisticsjson() {
		return paramnameCrj900Col4Statisticsjson;
	}

	public void setParamnameCrj900Col4Statisticsjson(String paramnameCrj900Col4Statisticsjson) {
		this.paramnameCrj900Col4Statisticsjson = paramnameCrj900Col4Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_b737_col1长度必须介于 0 和 64 之间")
	public String getParamnameB737Col1() {
		return paramnameB737Col1;
	}

	public void setParamnameB737Col1(String paramnameB737Col1) {
		this.paramnameB737Col1 = paramnameB737Col1;
	}
	
	@Size(min=0, max=300, message="paramname_b737_col1_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameB737Col1Statisticsjson() {
		return paramnameB737Col1Statisticsjson;
	}

	public void setParamnameB737Col1Statisticsjson(String paramnameB737Col1Statisticsjson) {
		this.paramnameB737Col1Statisticsjson = paramnameB737Col1Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_b737_col2长度必须介于 0 和 64 之间")
	public String getParamnameB737Col2() {
		return paramnameB737Col2;
	}

	public void setParamnameB737Col2(String paramnameB737Col2) {
		this.paramnameB737Col2 = paramnameB737Col2;
	}
	
	@Size(min=0, max=300, message="paramname_b737_col2_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameB737Col2Statisticsjson() {
		return paramnameB737Col2Statisticsjson;
	}

	public void setParamnameB737Col2Statisticsjson(String paramnameB737Col2Statisticsjson) {
		this.paramnameB737Col2Statisticsjson = paramnameB737Col2Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_b737_col3长度必须介于 0 和 64 之间")
	public String getParamnameB737Col3() {
		return paramnameB737Col3;
	}

	public void setParamnameB737Col3(String paramnameB737Col3) {
		this.paramnameB737Col3 = paramnameB737Col3;
	}
	
	@Size(min=0, max=300, message="paramname_b737_col3_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameB737Col3Statisticsjson() {
		return paramnameB737Col3Statisticsjson;
	}

	public void setParamnameB737Col3Statisticsjson(String paramnameB737Col3Statisticsjson) {
		this.paramnameB737Col3Statisticsjson = paramnameB737Col3Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_b737_col4长度必须介于 0 和 64 之间")
	public String getParamnameB737Col4() {
		return paramnameB737Col4;
	}

	public void setParamnameB737Col4(String paramnameB737Col4) {
		this.paramnameB737Col4 = paramnameB737Col4;
	}
	
	@Size(min=0, max=300, message="paramname_b737_col4_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameB737Col4Statisticsjson() {
		return paramnameB737Col4Statisticsjson;
	}

	public void setParamnameB737Col4Statisticsjson(String paramnameB737Col4Statisticsjson) {
		this.paramnameB737Col4Statisticsjson = paramnameB737Col4Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_a320_col1长度必须介于 0 和 64 之间")
	public String getParamnameA320Col1() {
		return paramnameA320Col1;
	}

	public void setParamnameA320Col1(String paramnameA320Col1) {
		this.paramnameA320Col1 = paramnameA320Col1;
	}
	
	@Size(min=0, max=300, message="paramname_a320_col1_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameA320Col1Statisticsjson() {
		return paramnameA320Col1Statisticsjson;
	}

	public void setParamnameA320Col1Statisticsjson(String paramnameA320Col1Statisticsjson) {
		this.paramnameA320Col1Statisticsjson = paramnameA320Col1Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_a320_col2长度必须介于 0 和 64 之间")
	public String getParamnameA320Col2() {
		return paramnameA320Col2;
	}

	public void setParamnameA320Col2(String paramnameA320Col2) {
		this.paramnameA320Col2 = paramnameA320Col2;
	}
	
	@Size(min=0, max=300, message="paramname_a320_col2_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameA320Col2Statisticsjson() {
		return paramnameA320Col2Statisticsjson;
	}

	public void setParamnameA320Col2Statisticsjson(String paramnameA320Col2Statisticsjson) {
		this.paramnameA320Col2Statisticsjson = paramnameA320Col2Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_a320_col3长度必须介于 0 和 64 之间")
	public String getParamnameA320Col3() {
		return paramnameA320Col3;
	}

	public void setParamnameA320Col3(String paramnameA320Col3) {
		this.paramnameA320Col3 = paramnameA320Col3;
	}
	
	@Size(min=0, max=300, message="paramname_a320_col3_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameA320Col3Statisticsjson() {
		return paramnameA320Col3Statisticsjson;
	}

	public void setParamnameA320Col3Statisticsjson(String paramnameA320Col3Statisticsjson) {
		this.paramnameA320Col3Statisticsjson = paramnameA320Col3Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_a320_col4长度必须介于 0 和 64 之间")
	public String getParamnameA320Col4() {
		return paramnameA320Col4;
	}

	public void setParamnameA320Col4(String paramnameA320Col4) {
		this.paramnameA320Col4 = paramnameA320Col4;
	}
	
	@Size(min=0, max=300, message="paramname_a320_col4_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameA320Col4Statisticsjson() {
		return paramnameA320Col4Statisticsjson;
	}

	public void setParamnameA320Col4Statisticsjson(String paramnameA320Col4Statisticsjson) {
		this.paramnameA320Col4Statisticsjson = paramnameA320Col4Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_b777_col1长度必须介于 0 和 64 之间")
	public String getParamnameB777Col1() {
		return paramnameB777Col1;
	}

	public void setParamnameB777Col1(String paramnameB777Col1) {
		this.paramnameB777Col1 = paramnameB777Col1;
	}
	
	@Size(min=0, max=300, message="paramname_b777_col1_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameB777Col1Statisticsjson() {
		return paramnameB777Col1Statisticsjson;
	}

	public void setParamnameB777Col1Statisticsjson(String paramnameB777Col1Statisticsjson) {
		this.paramnameB777Col1Statisticsjson = paramnameB777Col1Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_b777_col2长度必须介于 0 和 64 之间")
	public String getParamnameB777Col2() {
		return paramnameB777Col2;
	}

	public void setParamnameB777Col2(String paramnameB777Col2) {
		this.paramnameB777Col2 = paramnameB777Col2;
	}
	
	@Size(min=0, max=300, message="paramname_b777_col2_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameB777Col2Statisticsjson() {
		return paramnameB777Col2Statisticsjson;
	}

	public void setParamnameB777Col2Statisticsjson(String paramnameB777Col2Statisticsjson) {
		this.paramnameB777Col2Statisticsjson = paramnameB777Col2Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_b777_col3长度必须介于 0 和 64 之间")
	public String getParamnameB777Col3() {
		return paramnameB777Col3;
	}

	public void setParamnameB777Col3(String paramnameB777Col3) {
		this.paramnameB777Col3 = paramnameB777Col3;
	}
	
	@Size(min=0, max=300, message="paramname_b777_col3_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameB777Col3Statisticsjson() {
		return paramnameB777Col3Statisticsjson;
	}

	public void setParamnameB777Col3Statisticsjson(String paramnameB777Col3Statisticsjson) {
		this.paramnameB777Col3Statisticsjson = paramnameB777Col3Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_b777_col4长度必须介于 0 和 64 之间")
	public String getParamnameB777Col4() {
		return paramnameB777Col4;
	}

	public void setParamnameB777Col4(String paramnameB777Col4) {
		this.paramnameB777Col4 = paramnameB777Col4;
	}
	
	@Size(min=0, max=300, message="paramname_b777_col4_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameB777Col4Statisticsjson() {
		return paramnameB777Col4Statisticsjson;
	}

	public void setParamnameB777Col4Statisticsjson(String paramnameB777Col4Statisticsjson) {
		this.paramnameB777Col4Statisticsjson = paramnameB777Col4Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_a330_col1长度必须介于 0 和 64 之间")
	public String getParamnameA330Col1() {
		return paramnameA330Col1;
	}

	public void setParamnameA330Col1(String paramnameA330Col1) {
		this.paramnameA330Col1 = paramnameA330Col1;
	}
	
	@Size(min=0, max=300, message="paramname_a330_col1_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameA330Col1Statisticsjson() {
		return paramnameA330Col1Statisticsjson;
	}

	public void setParamnameA330Col1Statisticsjson(String paramnameA330Col1Statisticsjson) {
		this.paramnameA330Col1Statisticsjson = paramnameA330Col1Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_a330_col2长度必须介于 0 和 64 之间")
	public String getParamnameA330Col2() {
		return paramnameA330Col2;
	}

	public void setParamnameA330Col2(String paramnameA330Col2) {
		this.paramnameA330Col2 = paramnameA330Col2;
	}
	
	@Size(min=0, max=300, message="paramname_a330_col2_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameA330Col2Statisticsjson() {
		return paramnameA330Col2Statisticsjson;
	}

	public void setParamnameA330Col2Statisticsjson(String paramnameA330Col2Statisticsjson) {
		this.paramnameA330Col2Statisticsjson = paramnameA330Col2Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_a330_col3长度必须介于 0 和 64 之间")
	public String getParamnameA330Col3() {
		return paramnameA330Col3;
	}

	public void setParamnameA330Col3(String paramnameA330Col3) {
		this.paramnameA330Col3 = paramnameA330Col3;
	}
	
	@Size(min=0, max=300, message="paramname_a330_col3_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameA330Col3Statisticsjson() {
		return paramnameA330Col3Statisticsjson;
	}

	public void setParamnameA330Col3Statisticsjson(String paramnameA330Col3Statisticsjson) {
		this.paramnameA330Col3Statisticsjson = paramnameA330Col3Statisticsjson;
	}
	
	@Size(min=0, max=64, message="paramname_a330_col4长度必须介于 0 和 64 之间")
	public String getParamnameA330Col4() {
		return paramnameA330Col4;
	}

	public void setParamnameA330Col4(String paramnameA330Col4) {
		this.paramnameA330Col4 = paramnameA330Col4;
	}
	
	@Size(min=0, max=300, message="paramname_a330_col4_statisticsjson长度必须介于 0 和 300 之间")
	public String getParamnameA330Col4Statisticsjson() {
		return paramnameA330Col4Statisticsjson;
	}

	public void setParamnameA330Col4Statisticsjson(String paramnameA330Col4Statisticsjson) {
		this.paramnameA330Col4Statisticsjson = paramnameA330Col4Statisticsjson;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	 
	
}