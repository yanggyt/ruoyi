package com.ruoyi.common.core.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DeleteState implements BaseEnum {
    NOT_DELETED(0, "未删除","Not Deleted"),
    DELETED(1, "已删除", "Deleted"),
    ;
    private Integer state;
    private String desc;
    private String en;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    DeleteState(Integer state, String desc, String en) {
        this.state = state;
        this.desc = desc;
        this.en = en;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static DeleteState of(Integer state){
        return Arrays.stream(DeleteState.values())
                .filter(deleteState -> deleteState.getState().equals(state))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    @Override
    public String en() {
        return en;
    }

    @Override
    public String zh() {
        return desc;
    }
}
