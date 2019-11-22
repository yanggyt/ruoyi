package com.ruoyi.common.core.domain.enums.converter;

import com.ruoyi.common.core.domain.enums.ActiveState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ActiveStateConverter implements AttributeConverter<ActiveState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ActiveState activeState) {
        return null;
    }

    @Override
    public ActiveState convertToEntityAttribute(Integer integer) {
        return null;
    }
}
