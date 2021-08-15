# JDBC

## [Mysql](./mysql)

依赖

```xml

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.25</version>
</dependency>
```

## 示例说明

1. 配置[mysql连接信息](./mysql/src/main/resources/jdbc.properties)
2. 创建[数据库连接](./mysql/src/main/java/com/demo/mysql/utils/MysqlUtil.java)
3. 创建[实体类](./mysql/src/main/java/com/demo/mysql/entity/Entity.java)
4. 创建[Dao](./mysql/src/main/java/com/demo/mysql/dao/EntityDao.java)
5. 测试[Dao](./mysql/src/test/java/com/demo/mysql/MySqlTest.java)

## [Redis](./redis)

### Jedis

### Lettuce

### Redisson

## Mongodb

[官网](https://www.mongodb.com/zh-cn)

[文档](https://docs.mongodb.com/drivers/java/sync/current/)

依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.mongodb</groupId>
        <artifactId>mongodb-driver-sync</artifactId>
        <version>4.3.0</version>
    </dependency>
</dependencies>
```

## [Mybatis](./mybatis)

## [Hibernate](./hibernate)