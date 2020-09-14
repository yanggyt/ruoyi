package com.ruoyi.common.core.domain.enums.propertyeditor;

import com.ruoyi.common.core.domain.enums.ActiveState;

import java.beans.PropertyEditorSupport;

public class ActiveStatePropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try{
            Integer enumValue = Integer.parseInt(text);
            setValue(ActiveState.of(enumValue));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
}
