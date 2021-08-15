package com.github.codingob.se.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class ReflectionDemo {
    public static void main(String[] args) {
        // 第一种Object.getClass()
        Class<?> entityClass = new Entity().getClass();
        System.out.println(entityClass);

        // 第二种xxx.class
        System.out.println(entityClass == Entity.class);

        // 第三种Class.forName(package.xxx.class)(推荐)
        try {
            System.out.println(entityClass==Class.forName("com.github.codingob.se.reflection.Entity"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 通过反射实例化对象
        Entity instance = null;

        // Constructor
        try {
            // 获取所有构造方法getConstructors()
            Constructor<?>[] constructors = entityClass.getConstructors();
            for (Constructor<?> constructor: constructors) {
                System.out.println(constructor);
            }
            // 获取指定构造方法getConstructor(参数类型)
            Constructor<?> constructor = entityClass.getConstructor(Long.class,String.class);

            // 实例对象newInstance(参数)
            instance = (Entity) constructor.newInstance(1L,"反射");
            System.out.println(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Field
        try {
            // 获取所有成员属性getDeclaredFields()
            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field);
            }
            // 获取指定成员属性getDeclaredField(成员属性)
            Field field = entityClass.getDeclaredField("id");
            // 忽略权限限定
            field.setAccessible(true);
            // get属性
            System.out.println(field.get(instance));
            // set属性
            field.set(instance,100L);
            System.out.println(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Method
        try {
            // 获取所有方法getMethods()
            Method[] methods = entityClass.getMethods();
            for (Method method: methods) {
                System.out.println(method);
            }
            // 获取指定方法getMethod(方法名,参数类型)
            Method method = entityClass.getMethod("setId", Long.class);
            // 调用方法invoke(实例对象,参数)
            method.invoke(instance, 10L);
            System.out.println(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

