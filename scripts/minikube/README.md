minikube测试环境
==========


## minikube基本使用

任何NodePort类型的Service可以通过下面的命令拿到访问端口：

```
kubectl get service $SERVICE --output='jsonpath="{.spec.ports[0].nodePort}"'
```

或者：


```
minikube service [-n NAMESPACE] [--url] NAME
```

启用插件

```
minikube addons enable registry
```

由于这个镜像被强了，所以需要进到主机上去pull并tag一个。默认创建的Registry没有NodePort，所以需要进入到minikube机器去验证。

```
kubectl get svc -n kube-system

minikube ssh
curl http://10.111.187.15/v2/_catalog
```

具体可参考

* Minikube(https://kubernetes.io/docs/setup/learning-environment/minikube/)
