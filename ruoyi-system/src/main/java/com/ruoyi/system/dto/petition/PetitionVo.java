package com.ruoyi.system.dto.petition;



import com.ruoyi.system.domain.petition.Petition;
import com.ruoyi.system.domain.petition.PetitionFile;

import java.util.List;
import java.util.Map;

/**
 * 电子签呈Vo(主要用于APP)
 * @author: zsy
 * @create: 2022-01-11 09:25
 * @Version 1.0
 **/
public class PetitionVo {

    private Petition petition;
//    private List<PetitionAddaudit> petitionAddauditList;
    private List<PetitionAccountVo> petitionAccountVoList;
    private List<PetitionFile> petitionFileList;
    private Map<String,Object> resultHashMap;

    public Petition getPetition() {
        return petition;
    }

    public void setPetition(Petition petition) {
        this.petition = petition;
    }

//    public List<PetitionAddaudit> getPetitionAddauditList() {
//        return petitionAddauditList;
//    }
//
//    public void setPetitionAddauditList(List<PetitionAddaudit> petitionAddauditList) {
//        this.petitionAddauditList = petitionAddauditList;
//    }

    public List<PetitionAccountVo> getPetitionAccountVoList() {
        return petitionAccountVoList;
    }

    public void setPetitionAccountVoList(List<PetitionAccountVo> petitionAccountVoList) {
        this.petitionAccountVoList = petitionAccountVoList;
    }

    public Map<String, Object> getResultHashMap() {
        return resultHashMap;
    }

    public void setResultHashMap(Map<String, Object> resultHashMap) {
        this.resultHashMap = resultHashMap;
    }

    public List<PetitionFile> getPetitionFileList() {
        return petitionFileList;
    }

    public void setPetitionFileList(List<PetitionFile> petitionFileList) {
        this.petitionFileList = petitionFileList;
    }

    @Override
    public String toString() {
        return "PetitionDtoVo{" +
                "petition=" + petition +
//                ", petitionAddauditList=" + petitionAddauditList +
                ", petitionAccountVoList=" + petitionAccountVoList +
                ", resultHashMap=" + resultHashMap +
                '}';
    }
}
