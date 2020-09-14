package com.ruoyi.common.core.domain.enums.propertyeditor;

import com.ruoyi.common.core.domain.enums.DeleteState;

import java.beans.PropertyEditorSupport;

public class DeleteStatePropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try{
            Integer enumValue = Integer.parseInt(text);
            setValue(DeleteState.of(enumValue));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
}
