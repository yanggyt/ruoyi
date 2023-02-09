package com.ruoyi.common.enums;

import java.util.Objects;

/**
 * 电子签呈状态信息
 *
 * @version 1.0
 * @date 2022/9/27
 */
public enum PetitionStatus
{
    /** 加签状态 */
    ADD_AUDIT(0, "加签状态"),

    /** 保存未提交 */
    SAVE(1, "保存未提交"),

    /** 待主管审核 */
    WAIT_MANAGER(2, "待主管审核"),

    /** 待印鉴保管人审核 */
    WAIT_HR(3, "待印鉴保管人审核"),

    /** 待财务总账审核 */
    WAIT_FC_MANAGER(4, "待财务总账审核"),

    /** 待财务经理审核 */
    WAIT_CFCO_MANAGER(5, "待财务经理审核"),

    /** 待核准人审核 */
    WAIT_GM(6, "待核准人审核"),

    /** 撤回 */
    WITHDRAWN(7, "撤回"),

    /** 完成 */
    FINISH(8, "完成"),

    /** 提交待法务审核 */
    WAIT_LAW(9, "提交待法务审核"),

    /** 未完成 */
    TODO(10, "未完成"),

    /** 新增核准人审核 */
    OTHER_SIGN(11, "新增核准人审核"),

    /** C类审核人审核 */
    C_WAIT_AUDIT(12, "C类审核人审核"),

    /** 新增会审人审核 */
    OTHER_TRIAL(13, "新增会审人审核"),

    /** 否决 */
    VETO(14, "否决");








    private Integer value;

    private String desc;

    PetitionStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static PetitionStatus from(int value) {
        for (PetitionStatus range : PetitionStatus.values()) {
            if (Objects.equals(range.value, value)) {
                return range;
            }
        }
        return null;
    }
}
