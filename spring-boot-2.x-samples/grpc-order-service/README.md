grpc-order-service
===========

使用Grpc实现的订单服务


## 启动应用

1. 方式一：在项目的根目录下执行如下，打包的时候会把依赖打进去所有不会有依赖问题，

```
mvn clean package
SPRING_PROFILES_ACTIVE=prod java -jar spring-boot-2.x-samples/grpc-order-service/target/grpc-order-service-0.0.1-SNAPSHOT.jar
```

2. 方式二：在子目录执行如下命令，前提是需要解决了依赖关系，需要现将子模块安装到respository

```
cd spring-boot-2.x-samples/grpc-order-service/
mvn spring-boot:run -Dspring-boot.run.profiles=local
```


## 测试

```
grpcurl --plaintext localhost:9090 list
grpcurl --plaintext localhost:9090 list OrderService
grpcurl --plaintext -d '{"cart_id": "1"}' localhost:9090  OrderService/create
```
