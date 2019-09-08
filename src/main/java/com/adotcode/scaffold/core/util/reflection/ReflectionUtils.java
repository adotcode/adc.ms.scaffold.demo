package com.adotcode.scaffold.core.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * 反射工具类
 *
 * @author risfeng
 * @date 2019/08/25
 */
@Slf4j
public class ReflectionUtils {

  /**
   * 通过反射, 获得Class定义中声明的泛型参数的类型, 注意泛型必须定义在父类处. 如无法找到, 返回Object.class.
   *
   * @param clazz class类
   * @return the 返回第一个声明的泛型类型. 如果没有,则返回Object.class
   */
  public static Class getClassGenericType(final Class clazz) {
    return getClassGenericType(clazz, 0);
  }

  /**
   * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
   *
   * @param clazz class类
   * @param index 获取第几个泛型参数的类型,默认从0开始,即第一个
   * @return 返回第index个泛型参数类型.
   */
  public static Class getClassGenericType(final Class clazz, final int index) {
    Type genType = clazz.getGenericSuperclass();
    if (!(genType instanceof ParameterizedType)) {
      return Object.class;
    }
    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
    if (index >= params.length || index < 0) {
      log.warn("Index: {}, Size of {}'s Parameterized Type: {}", index, clazz.getSimpleName(),
          params.length);
      return Object.class;
    }
    if (!(params[index] instanceof Class)) {
      log.warn("{} not set the actual class on superclass generic parameter.",
          clazz.getSimpleName());
      return Object.class;
    }
    return (Class) params[index];
  }

  /**
   * 根据注解类型获取实体的Field
   *
   * @param entityClass 实体类型
   * @param annotationClass 注解类型
   * @return 返回第一个有该注解类型的Field, 如果没有则返回null.
   */
  public static Field getFieldByAnnotation(Class entityClass,
      Class<? extends Annotation> annotationClass) {
    Field[] fields = FieldUtils.getAllFields(entityClass);
    for (Field field : fields) {
      if (field.getAnnotation(annotationClass) != null) {
        makeAccessible(field);
        return field;
      }
    }
    return null;
  }

  /**
   * 获取实体的字段
   *
   * @param entityClass 实体类型
   * @param fieldName 字段名称
   * @return 该字段名称对应的字段, 如果没有则返回null.
   */
  public static Field getField(Class entityClass, String fieldName) {
    try {
      Field field = FieldUtils.getField(entityClass, fieldName);
      makeAccessible(field);
      return field;
    } catch (Exception e) {
      log.error("No Such Field Error.", e);
    }
    return null;
  }

  /**
   * 改变private/protected的成员变量为public.
   */
  public static void makeAccessible(Field field) {
    if (!Modifier.isPublic(field.getModifiers()) ||
        !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
      field.setAccessible(true);
    }
  }

}
