# 数据类型

### 基本数据类型(4类8种)

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

### 引用数据类型

1. 类
2. 接口
3. 数组

### 数据类型转换

#### 显式转换

不推荐使用。可能发生精度损失、数据溢出

语法: `数据类型(小) 变量名 = 数据类型(小)数据值;`

`int a = 100L;`

`double b = (int)3.99;`

`int c = a + b;`

#### 隐式转换

数据范围从小到大

long>int>short>byte

double>float

`long a = 100;`,`double b = 3;`