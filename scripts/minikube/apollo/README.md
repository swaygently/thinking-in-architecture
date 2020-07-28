Apollo
=========

## 容器打包


因为minikube里面已经有docker环境，那么就直接在minikube中打包，以下以打包 apollo-protal-server镜像为例：

因为我们要下载的包是v1.6.2版本的，所以以v1.6.2这个版本为例：

```
minikube ssh

git clone --branch v1.6.2 https://gitee.com/nobodyiam/apollo.git

curl -L https://github.com/ctripcorp/apollo/releases/download/v1.6.2/apollo-protal-server-1.6.2-github.zip -o apollo-protal-server-1.6.2-github.zip
unzip apollo-protal-server-1.6.2-github.zip -d apollo/scripts/apollo-on-kubernetes/apollo-protal-server
cd apollo/scripts/apollo-on-kubernetes/apollo-protal-server
rm apollo-portal-1.6.2-sources.jar
mv apollo-portal-1.6.2.jar apollo-portal.jar
docker build -t apollo-protal-server:v1.6.2 .

```

参考如下文档制作镜像：
https://github.com/ctripcorp/apollo/tree/master/scripts/apollo-on-kubernetes

值得注意的是默认的Dockerfile和启动脚本，指定了日志文件的数据路径和环境变量SERVICE_URL的后缀为`.sre`

## 创建数据库

参考Apollo文档...


## 容器测试

参考Dockerfile，可以知道进程的启动脚本和配置文件，可以创建一个测试容器进行测试：

```
docker run -it --name test apollo-config-server:v1.6.2 bash

vi /apollo-config-server/config/application-github.properties
/apollo-config-server/scripts/startup-kubernetes.sh
```

测试没问题的话退出容器并删除它：

```
docker rm test
```

configserver的配置文件，发现用域名好像有点问题

```
spring.datasource.url = jdbc:mysql://10.98.242.221:3306/ApolloConfigDB?characterEncoding=utf8
spring.datasource.username = FillInCorrectUser
spring.datasource.password = FillInCorrectPassword

# apollo-config-server默认启动eurake作为配置中，指定这个配置可以指定不启用eureka server
apollo.eureka.server.enabled=false
```

## 部署到kubernetes集群

这个示例，只模拟了protal来管理一个dev环境，使用了config-server自带的eureka；config-server、admin-server通过数据库中指定的eureka.service.url来注册到eureka，然后protal指定了dev环境的eureka地址。
apollo的配置有多种方式，但是我建议只指定mysql的配置，其他的配置都可以用界面来管理。比如：eureka.service.url，

```
kubectl create namespace sre
kubectl apply -f service-apollo-config-server-dev.yaml
kubectl apply -f service-apollo-admin-server-dev.yaml
kubectl apply -f service-apollo-protal-server.yaml
```


问题排查，apollo的启动脚本指定了日志文件的存储目录为/data/logs，所以可以通过如下命令来查看日志

```
kubectl get pods -n sre
kubectl exec -it ${podName} -n sre -- ls /opt/logs
```


## TODO

以上例子基于v1.6.2，v1.7版本增加了基于原生kubernetes服务发现，不需要使用内置的eureka
