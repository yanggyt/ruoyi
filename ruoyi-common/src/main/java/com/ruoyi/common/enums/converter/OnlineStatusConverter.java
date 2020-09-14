package com.ruoyi.common.enums.converter;

import com.ruoyi.common.enums.OnlineStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OnlineStatusConverter implements AttributeConverter<OnlineStatus, String> {
    @Override
    public String convertToDatabaseColumn(OnlineStatus attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public OnlineStatus convertToEntityAttribute(String dbData) {
        return OnlineStatus.valueOf(dbData);
    }
}
