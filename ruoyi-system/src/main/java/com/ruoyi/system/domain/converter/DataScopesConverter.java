package com.ruoyi.system.domain.converter;

import com.ruoyi.common.annotation.DataScopes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class DataScopesConverter implements AttributeConverter<DataScopes, String> {
    @Override
    public String convertToDatabaseColumn(DataScopes attribute) {
        return attribute != null ? attribute.name() : null;
    }

    @Override
    public DataScopes convertToEntityAttribute(String dbData) {
        return Arrays.stream(DataScopes.values())
                .filter(dataScopes -> dataScopes.name().equals(dbData))
                .findFirst()
                .orElse(DataScopes.DATA_SCOPE_ALL);
    }
}
