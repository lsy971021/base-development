## Mybatis延迟加载
### mybatis默认没有开启延迟加载，需要在SqlMappingConfig.xml中setting进行配置
### lazyLoadingEnabled:true使用延迟加载

#### 1、在数据与对象进行mapping操作时，只有在真正使用到该对象时，才进行mapping操作，以减少数据库查询开销
#### 2、但是Lazy Load也有缺点，在按需加载时会多次连接数据库，同时会增加数据库的压力。所以在实际使用时，会衡量是否使用延迟加载。
#### 3、resultMap可以实现高级映射(使用association、collection实现一对一及一对多映射),association、collection具备延迟加载功能


## mybatis缓存
### 一级缓存：(默认开启)
同一个session内执行同一个sql语句，只有第一次是从数据库中拿的，之后都是从本地缓存中获取
### 二级缓存：
mapper范围级别的，同一个作用于内多个session之间是可以共享缓存数据的
开启二级缓存：
在pom添加两个依赖   <artifactId>mybatis-ehcache</artifactId>  <artifactId>ehcache</artifactId>
sqlMapperConfig.xml中开启二级缓存  <setting name="cacheEnabled" value="true"/>
mapper映射文件中开启二级缓存 <cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
所有查询都会使用二级缓存，若某个sql语句不希望使用缓存数据，可在属性中添加 useCache="false"
实体类实现序列化 implements Serializable
在mapper的同一namespace中,如果其他insert,update,delete操作数据后需要刷新缓存，如果不执行刷新缓存会出现脏读，默认情况下
flushCache="true"会刷新缓存，改为false则不会
