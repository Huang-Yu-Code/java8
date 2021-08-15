# 反射

## Constructor

1. getConstructors():获取所有构造方法
2. getConstructor(参数类型):获取指定构造方法
3. newInstance(参数): 实例化对象

## Field

1. getDeclaredFields():
2. getDeclaredField(成员属性):
3. setAccessible(true): 忽略权限访问限制
4. get(实例对象): get值
5. set(实例对象, 值): set值

## Method
1. getMethods():获取所有方法
2. getMethod(方法名,参数类型): 获取指定方法
3. invoke(实例对象,参数): 调用方法
