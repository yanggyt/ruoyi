package com.ruoyi.common.core.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ActiveState implements BaseEnum{

    enabled(1,"活跃"),
    disabled(2,"不活跃"),
    ;

    private Integer state;
    private String desc;

    ActiveState(Integer state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String en() {
        return null;
    }

    @Override
    public String zh() {
        return null;
    }

    public static ActiveState of(Integer value){
        return Arrays.stream(ActiveState.values())
                .filter(activeState -> activeState.getState().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
