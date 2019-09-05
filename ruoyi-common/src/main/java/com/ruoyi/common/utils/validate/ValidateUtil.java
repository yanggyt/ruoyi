package com.ruoyi.common.utils.validate;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Bean验证
 *
 * @author solo
 * @date 2019/09/05.
 */
public class ValidateUtil {
    /**
     * 验证参数是否合法(JSR303标准，参考地址：https://www.ibm.com/developerworks/cn/java/j-lo-jsr303/)
     *
     * @param obj 校验对象
     * @return 返回所有验证未通过的消息
     */
    public static List<String> validate(Object obj) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violationSet = validator.validate(obj);
        List<String> list = new ArrayList<>();
        if (violationSet.size() > 0) {
            for (ConstraintViolation violation : violationSet) {
                list.add(violation.getMessage());
            }
        }
        return list;
    }
}
