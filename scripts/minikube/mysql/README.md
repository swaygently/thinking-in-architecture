##


自定义数据库镜像，测试用。基于mysql8.0镜像，启动的时候初始化数据库


可以把apollo的数据库初始化文件写入mysqldb-init.sql然后执行如下命令制作镜像：

```
docker build -t examples-apollo-mysqldb:v1.6.2 .
```
