# 类

```
class name {
    [public|protected|default|private] [final] [static] dataType field;
    ...
    public name(){
        ...
    }
  [public|protected|default|private] [void|dataType] function name(dataType args){
  ...
  public static void main(String[] args) {
        ...
    }
  }
}
```

类声明顺序:

- 修饰符: `public`、`protected`、`default`、`private`
- `final`: `final`修饰的类没有子类
- 类名: 首字母大写
- 父类: `extends` **一个类只能继承一个直接父类**
- 实现接口:`implements`**可以实现多个接口**
- 类体: `{}`

1. 成员变量
2. 构造方法: 类至少有一个构造函数。如果一个类没有显式声明，Java 编译器会自动提供一个，称为默认无参构造函数。
3. 静态方法: 应该用类名调用，而不需要创建类的实例
4. 成员方法:
5. `this`: `this`是对当前对象的引用——正在调用其方法或构造函数的对象
6. 静态代码块: `static{}`
7. 代码块: `{}`

访问权限修饰符

|作用域|同类|同包|子类|不同包|
|:---:|:---:|:---:|:---:|:---:|
|public|✔|✔|✔|✔|
|protected|✔|✔|✔|✖|
|default|✔|✔|✖|✖|
|private|✔|✖|✖|✖|

## 方法

声明

1. 修饰符
2. `final`: `final`修饰的方法不能被重写
3. `static`: 静态方法
2. 返回类型: void 无返回
3. 方法名称:
4. 参数列表: 可变参数(参数类型...参数名)
    - 传递基本数据类型
    - 传递引用数据类型
5. 方法体: `{}`

* 重载
    * 方法名字相同，而参数不同(参数个数、类型、顺序不一样)
    * 改变返回类型
* 重写
    * 参数列表与被重写方法的参数列表必须完全相同
    * 返回类型返回类型可以不相同，但是必须是父类返回值的派生类
    * 声明为 final 的方法不能被重写
    * 声明为 static 的方法不能被重写，但是能够被再次声明
    * 构造方法不能被重写

## 内部类

1. 静态内部类: 静态嵌套类无权访问封闭类的其他成员
2. 非静态内部类: 非静态嵌套类（内部类）可以访问封闭类的其他成员，即使它们被声明为私有
3. 内部类修饰符private，public，protected
4. 不要对内部类进行序列化。

## 继承

每个类都是Object的子类