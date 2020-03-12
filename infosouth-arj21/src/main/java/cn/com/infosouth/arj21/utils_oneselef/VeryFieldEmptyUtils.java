package cn.com.infosouth.arj21.utils_oneselef;


import java.lang.reflect.Field;

import cn.com.infosouth.arj21.common.CommonResult;

/**
 * @Auther: Tiger
 * @Date: 2019-06-11 18:21
 * @Description:
 */
public class VeryFieldEmptyUtils {

    /**
     * 验证一些必须的字段
     * @param entity 实体
     * @param colmunS  已逗号(,)分隔开的字段
     * @return
     */
    public static <T> Object veryFieldEmpty(T entity, String colmunS){
        try{
            String[] colmuns = colmunS.split(",");
            int len = colmuns.length;
            Class<?> clazz = entity.getClass();
            for(int i = 0; i < len; i++){
                String fieldS = colmuns[i];
                if(fieldS != null){
                    fieldS = fieldS.trim();
                    Field field = clazz.getDeclaredField(fieldS);
                    field.setAccessible(true);
                    Object value = field.get(entity);
                    if(value != null || "".equals(value)){
                        String annoValue =null;
                        return new CommonResult().failed(annoValue == null ? fieldS : annoValue + " 不能为空");
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
 


}
