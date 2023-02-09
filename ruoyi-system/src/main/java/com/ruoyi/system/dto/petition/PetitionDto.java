package com.ruoyi.system.dto.petition;


import com.ruoyi.system.domain.petition.Petition;
import com.ruoyi.system.domain.petition.PetitionSign;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author lxp
 * @Date 16:11
 **/
public class PetitionDto {
    private Petition petition;
    private List<PetitionSign>  petitionAddauditList;
    private List<PetitionSign> petitionSignList;
    private List<PetitionSign> petitionTrialList;
    private List<PetitionAccountVo> petitionAccountVoList;
    
    private Map<String,Object> resultHashMap;
    
    private ArrayList<LinkedHashMap<String, Object>> reviewLists;

    public List<PetitionSign> getPetitionSignList() {
        return petitionSignList;
    }

    public void setPetitionSignList(List<PetitionSign> petitionSignList) {
        this.petitionSignList = petitionSignList;
    }

    public List<PetitionAccountVo> getPetitionAccountVoList() {
        return petitionAccountVoList;
    }

    public void setPetitionAccountVoList(List<PetitionAccountVo> petitionAccountVoList) {
        this.petitionAccountVoList = petitionAccountVoList;
    }

    @Override
    public String toString() {
        return "PetitionDto{" +
                "petition=" + petition +
                ", petitionAddauditList=" + petitionAddauditList +
                ", petitionSignList=" + petitionSignList +
                ", petitionAccountVoList=" + petitionAccountVoList +
                '}';
    }

    public List<PetitionSign> getPetitionTrialList() {
        return petitionTrialList;
    }

    public void setPetitionTrialList(List<PetitionSign> petitionTrialList) {
        this.petitionTrialList = petitionTrialList;
    }
    
    public Petition getPetition() {
        return petition;
    }

    public void setPetition(Petition petition) {
        this.petition = petition;
    }

    public List<PetitionSign> getPetitionAddauditList() {
        return petitionAddauditList;
    }

    public void setPetitionAddauditList(List<PetitionSign> petitionAddauditList) {
        this.petitionAddauditList = petitionAddauditList;
    }

    public Map<String, Object> getResultHashMap() {
        return resultHashMap;
    }

    public void setResultHashMap(Map<String, Object> resultHashMap) {
        this.resultHashMap = resultHashMap;
    }

    public ArrayList<LinkedHashMap<String, Object>> getReviewLists() {
        return reviewLists;
    }

    public void setReviewLists(ArrayList<LinkedHashMap<String, Object>> reviewLists) {
        this.reviewLists = reviewLists;
    }
}
