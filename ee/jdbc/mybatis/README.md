# Mybatis

Version: 3.5.7

[文档](https://mybatis.org/mybatis-3/zh/index.html)

主要依赖

```xml

<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.7</version>
</dependency>
```

## 示例说明

1. 创建数据表，执行[sql](./sql/entity.sql)
2. 创建[实体类](./src/main/java/com/demo/mybatis/entity/Entity.java)
    * Boolean: tinyint
    * BigDecimal: decimal
    * Timestamp: datetime
3. 创建[Mapper接口](./src/main/java/com/demo/mybatis/mapper/EntityMapper.java)
4. 创建[Mapper映射](./src/main/resources/mapper/EntityMapper.xml)
5. 配置[Mybatis](./src/main/resources/mybatis-config.xml)
    * 数据源: [jdbc.properties](./src/main/resources/jdbc.properties)
    * 开启驼峰命名自动映射: mapUnderscoreToCamelCase
    * 事务: 手动提交事务
    * 分页插件: Pagehelper
    * 日志: [log4j2.xml](./src/main/resources/log4j2.xml)
6. 创建[SqlSessionFactory](./src/main/java/com/demo/mybatis/service/EntityService.java)

```
// sqlSessionFactory.openSession()需要手动提交session.commit();
// sqlSessionFactory.openSession(trur) 自动提交

try (SqlSession session = sqlSessionFactory.openSession()) {
  xxxMapper mapper = session.getMapper(xxxMapper.class);
  // 你的应用逻辑代码
}
```

---

## PageHelper

Version: 5.2.1

Mybatis分页插件

主要依赖

```xml

<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>5.2.1</version>
</dependency>
```

[文档](https://pagehelper.github.io/docs/)
