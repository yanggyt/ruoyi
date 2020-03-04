package cn.com.infosouth.arj21.domain;

import java.util.Date;
import java.util.List;

/**   
 * @ClassName:  DataAuthCondition   
 * @Description:TODO(被授權的數據)   
 * @author: zy
 * @date:   2018年9月3日 上午9:43:24   
 *     
 * @Copyright: 2018 Inc. All rights reserved. 
 * 注意：本内容仅限深圳科信南方技术有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class DataAuthCondition {

	private String condId;
	private String condAcType;
	private String condArn;
	private Date condBeginDateStr;
	private Date condEndDateStr;
	private String condPodStr;
	private String condPoaStr;
	private List<String> condIdList;
	
	
	public String getCondId() {
		return this.condId;
	}
	public void setCondId(String condId) {
		this.condId = condId;
	}
	public String getCondAcType() {
		return this.condAcType;
	}
	public void setCondAcType(String condAcType) {
		this.condAcType = condAcType;
	}
	public String getCondArn() {
		return this.condArn;
	}
	public void setCondArn(String condArn) {
		this.condArn = condArn;
	}
	public Date getCondBeginDateStr() {
		return this.condBeginDateStr;
	}
	public void setCondBeginDateStr(Date condBeginDateStr) {
		this.condBeginDateStr = condBeginDateStr;
	}
	public Date getCondEndDateStr() {
		return this.condEndDateStr;
	}
	public void setCondEndDateStr(Date condEndDateStr) {
		this.condEndDateStr = condEndDateStr;
	}
	public List<String> getCondIdList() {
		return this.condIdList;
	}
	public void setCondIdList(List<String> condIdList) {
		this.condIdList = condIdList;
	}
	public String getCondPodStr() {
		return condPodStr;
	}
	public void setCondPodStr(String condPodStr) {
		this.condPodStr = condPodStr;
	}
	public String getCondPoaStr() {
		return condPoaStr;
	}
	public void setCondPoaStr(String condPoaStr) {
		this.condPoaStr = condPoaStr;
	}

	

}
