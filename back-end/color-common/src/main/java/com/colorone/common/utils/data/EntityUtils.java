package com.colorone.common.utils.data;

import java.lang.reflect.Method;

/**
 * @author： lee
 * @email：miracleren@gmail.com
 * @date：2023/9/14
 * @备注：实体类处理工具类
 */
public class EntityUtils {
    /**
     * 通过名称获取实体类的值
     *
     * @param entity
     * @param propertyName
     * @return
     */
    public static Object getPropertyValue(Object entity, String propertyName) {
        String[] names = propertyName.split("\\.");
        Object curObject = entity;
        try {
            for (String name : names) {
                Class<?> entityClass = curObject.getClass();
                String getterMethodName = "get" + capitalize(name);
                Method getterMethod = entityClass.getMethod(getterMethodName);
                curObject = getterMethod.invoke(curObject);
            }
        } catch (Exception e) {
            curObject = null;
        }
        return curObject;
    }

    /**
     * 通过名称给实体类赋值
     *
     * @param entity
     * @param propertyName
     * @param value
     */
    public static void setPropertyValue(Object entity, String propertyName, Object value) {
        String[] names = propertyName.split("\\.");
        Object curObject = entity;
        try {
            Method setterMethod = null;
            for (String name : names) {
                Class<?> entityClass = curObject.getClass();
                String setterMethodName = "set" + capitalize(name);
                setterMethod = findSetterMethod(entityClass, setterMethodName, value.getClass());

            }
            if (setterMethod != null)
                setterMethod.invoke(curObject, value);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Method findSetterMethod(Class<?> entityClass, String methodName, Class<?> parameterType) throws
            NoSuchMethodException {
        try {
            return entityClass.getMethod(methodName, parameterType);
        } catch (NoSuchMethodException e) {
            if (entityClass.getSuperclass() != null) {
                return findSetterMethod(entityClass.getSuperclass(), methodName, parameterType);
            } else {
                throw e;
            }
        }
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
    }
}
