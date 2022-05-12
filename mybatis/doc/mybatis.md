## Mybatis延迟加载
### mybatis默认没有开启延迟加载，需要在SqlMappingConfig.xml中setting进行配置
### lazyLoadingEnabled:true使用延迟加载

#### 1、在数据与对象进行mapping操作时，只有在真正使用到该对象时，才进行mapping操作，以减少数据库查询开销
#### 2、但是Lazy Load也有缺点，在按需加载时会多次连接数据库，同时会增加数据库的压力。所以在实际使用时，会衡量是否使用延迟加载。
#### 3、resultMap可以实现高级映射(使用association、collection实现一对一及一对多映射),association、collection具备延迟加载功能
