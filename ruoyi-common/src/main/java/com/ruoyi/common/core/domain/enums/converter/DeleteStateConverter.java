package com.ruoyi.common.core.domain.enums.converter;

import com.ruoyi.common.core.domain.enums.DeleteState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DeleteStateConverter implements AttributeConverter<DeleteState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DeleteState attribute) {
        return null == attribute ? null : attribute.getState();
    }

    @Override
    public DeleteState convertToEntityAttribute(Integer dbData) {
        return DeleteState.of(dbData);
    }
}
