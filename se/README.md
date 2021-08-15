# JavaSE

## 面向对象编程

1. 封装
2. 继承
3. 多态

## 注释

### 单行注释

```java
// 单行注释
```

### 多行注释

```java
/*
多
行
注
释
*/
```

### 文档注释

```
/** 文档注释
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
```

|注释|含义|
|:---:|:---:|
|@author|作者|
|@version|版本|
|@since|Java版本|

## 关键字

1.完全小写字母

2.代码高亮提示

|关键字|含义|
|:---:|:---:|
|abstract|表明类或者成员方法具有抽象属性|
|assert|用来进行程序调试||
|boolean|基本数据类型之一，布尔类型|
|break|提前跳出一个块|
|byte|基本数据类型之一，字节类型|
|case|用在switch语句之中，表示其中的一个分支|
|catch|用在异常处理中，用来捕捉异常|
|char|基本数据类型之一，字符类型|
|class|类|
|const|保留关键字，没有具体含义|
|continue|回到一个块的开始处|
|default|默认，例如，用在switch语句中，表明一个默认的分支|
|do|用在do-while循环结构中|
|double|基本数据类型之一，双精度浮点数类型|
|else|用在条件语句中，表明当条件不成立时的分支|
|enum|枚举|
|extends|表明一个类型是另一个类型的子类型，这里常见的类型有类和接口|
|final|用来说明最终属性，表明一个类不能派生出子类，或者成员方法不能被覆盖，或者成员域的值不能被改变|
|finally|用于处理异常情况，用来声明一个基本肯定会被执行到的语句块|
|float|基本数据类型之一，单精度浮点数类型|
|for|一种循环结构的引导词|
|goto|保留关键字，没有具体含义|
|if|条件语句的引导词|
|implements|表明一个类实现了给定的接口|
|import|表明要访问指定的类或包|
|instanceof|用来测试一个对象是否是指定类型的实例对象|
|int|基本数据类型之一，整数类型|
|interface|接口|
|long|基本数据类型之一，长整数类型|
|native|用来声明一个方法是由与计算机相关的语言（如C/C++/FORTRAN语言）实现的|
|new|用来创建新实例对象|
|package|包|
|private|一种访问控制方式：私用模式|
|protected|一种访问控制方式：保护模式|
|public|一种访问控制方式：共用模式|
|return|从成员方法中返回数据|
|short|基本数据类型之一,短整数类型|
|static|表明具有静态属性|
|strictfp|用来声明FP_strict（单精度或双精度浮点数）表达式遵循IEEE 754算术规范|
|super|表明当前对象的父类型的引用或者父类型的构造方法|
|switch|分支语句结构的引导词|
|synchronized|表明一段代码需要同步执行|
|this|指向当前实例对象的引用|
|throw|抛出一个异常|
|throws|声明在当前定义的成员方法中所有需要抛出的异常|
|transient|声明不用序列化的成员域|
|try|尝试一个可能抛出异常的程序块|
|void|声明当前成员方法没有返回值|
|volatile|表明两个或者多个变量必须同步地发生变化|
|while|用在循环结构中 |

## 标识符

* 类名:大驼峰(首字母大写)
* 方法名:小驼峰(首字母小写，其余单词首字母大写)
* 变量名:小驼峰(首字母小写，其余单词首字母大写)

## 常量

1.字符串(字符串用双引号)

`"HelloWorld"`

2.整数

`100`,`0`

在数字文字中使用下划线字符`1_000_000`

在数字的开头或结尾、与浮点数小数点相邻、在F或L后缀之前、在需要一串数字的位置

3.浮点数

`2.1f`,`2.10`

4.字符(字符用单引号)

`A`,`a`

5.布尔(布尔值只有 true、false)

`true`,`false`

6.空(空常量)

`null`

7. 常量值的名称用大写字母拼写,如果名称由多个单词组成，则这些单词之间用下划线 (_) 分隔

## 变量

语法:`数据类型 变量名 = 数据值;`

命名规则

* 变量名区分大小写
* 英文字母(A-z)、数字(0-9)、$(美元符号)、_(下划线)
* 不能以数字开头
* 不能是关键字

注意

* 实例变量: 每个实例对象都是唯一的
* 静态变量(static): 无论该类已实例化多少次，都存在该变量的一个副本,应该用类名调用，而不需要创建类的实例
* 局部变量: 局部变量仅作用于声明它们的局部区域，如方法体，代码块
* 参数: 参数总是被归类为“变量”而不是“字段
* 右侧数据值不能超过左侧数据类型的范围

`byte a = 127;`
`short b = 12345;`
`int c = 100;`
`long d = 100L;`

## 数据类型

基本数据类型(4类8种)

1. 整形
    * `byte`
    * `short`
    * `int`
    * `long`
2. 浮点数
    * `float`
    * `double`
3. 字符
    - `char`
4. 布尔
    * `boolean`(`true`,`false`)

|数据类型|字节|表示范围|---|默认值|
|:---:|:---:|:---:|:---:|:---:|
|byte|1|-2<sup>7</sup>~2<sup>7</sup>-1|-128~127|0|
|short|2|-2<sup>15</sup>~-2<sup>15</sup>|-32,768~32,767|0|
|int|4|-2<sup>31</sup>~2<sup>31</sup>-1|-|0|
|long|8|-2<sup>63</sup>~2<sup>63</sup>-1|-|0L|
|float|4|-2<sup>63</sup>~2<sup>63</sup>-1|-|0.0f|
|double|8|-2<sup>63</sup>~2<sup>63</sup>-1|-|0.0d|
|char|2|-|0~ 65,535|'\u0000'|
|boolean|8|-|-|false|

引用数据类型

* 类
* 接口
* 数组

### 数据类型转换

* 显式转换（不推荐使用。可能发生精度损失、数据溢出）
    * 语法: `数据类型(小) 变量名 = 数据类型(小)数据值;`
* 隐式转换(数据范围从小到大)
    * `double>float`
    * `long>int>short>byte`

## 运算符

算术运算符

* `+`: 相加
* `-`: 相减
* `*`: 相乘
* `/`: 求模
* `%`: 求余
* `++`: `i++`:先+后用,`++i`:先用后+
* `--`: 同上

赋值运算符

* `=`
* `+=`
* `-+`
* `*=`
* `/=`
* `%=`

比较运算符

* `==`
* `<`
* `>`
* `<=`
* `>=`
* `!=`

逻辑运算符

* `&&`
* `||`
* `!`

三元运算符

`条件表达式(boolean) ? 表达式: 表达式`

位运算符

* `<<`: 左移
* `>>`: 右移
* `>>>`:无符号右移
* `&`: 与
* `|`: 或
* `^`: 异或
* `~`: 位非

## 控制结构

* if
* if-else
* switch
* for
* while
* do-while
* break
    * break用于循环时，跳出循环
    * break用于switch语句中，终止switch语句
* continue
    * continue用在循环中，跳出本次循环，继续执行下一次循环

## [数组](./src/main/java/com/github/codingob/se/array)

## [类](./src/main/java/com/github/codingob/se/clazz)

## [接口](./src/main/java/com/github/codingob/se/interfaces)

## Stream

## 枚举

关键字:`enum`

```java
public enum WeekDay {
    /**
     * 星期
     */
    Monday("星期一", 1),
    Tuesday("星期二", 2),
    Wednesday("星期三", 3),
    Thursday("星期四", 4),
    Friday("星期五", 5),
    Saturday("星期六", 6),
    Sunday("星期七", 7);

    private final String day;
    private final int index;

    WeekDay(String day, int index) {
        this.day = day;
        this.index = index;
    }

}
```

## 注解

常用注解

* @Override:重写方法
* @SuppressWarnings:压制警告，抑制警告
* @Deprecated:表示方法已经过时,方法上有横线，使用时会有警告

元注解

* @Retention:注解保留阶段
    * source:源码
    * class:字节码文件
    * runtime:运行时
* @Documented:注解表明这个注解应该被 javadoc工具记录
* @Target:注解的作用目标
    * TYPE:接口、类、枚举、注解
    * FIELD:字段、枚举的常量
    * METHOD:方法
    * PARAMETER:方法参数
    * CONSTRUCTOR:构造函数
    * LOCAL_VARIABLE:局部变量
    * ANNOTATION_TYPE:注解
    * PACKAGE:包
* @Inherited:说明子类可以继承父类中的该注解
* @Repeatable:被元注解@Repeatable修饰的注解，可以在同一个地方使用多次

## JavaBean

标准的代码--JavaBean（标准的类）

一个标准的类通常要拥有下面四个部分

* 所有的成员变量都要使用private关键字修饰
* 为每一个成员变量编写一对getter/setter方法
* 编写一个无参数的构造方法
* 编写一对全参数的构造方法

```java
public class JavaBean {
    private int id;
    private String name;
    private int age;

    public JavaBean() {

    }

    public JavaBean(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

## 泛型

## [集合](./src/main/java/com/github/codingob/se/colleticon)

## [反射](./src/main/java/com/github/codingob/se/reflection)

## [异常](././src/main/java/com/github/codingob/se/exception)

## [IO](./src/main/java/com/github/codingob/se/io)

## [Net](./src/main/java/com/github/codingob/se/net)

## [Socket](./src/main/java/com/github/codingob/se/socket)

## [多线程](./src/main/java/com/github/codingob/se/juc)
