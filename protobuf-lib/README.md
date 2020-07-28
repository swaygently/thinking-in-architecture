protobuf-lib
==============

protobuf 定义文件


## 版本号

grpc: 1.30.0
protobuf: 3.12.0
protoc: 3.12.0


## 

```
brew install protobuf
```

将如下地址加入到执行路径：

```
export PAHT="/usr/local/Cellar/protobuf/3.12.3/bin:$PATH"
```

生成proto：

```
/usr/local/Cellar/protobuf/3.12.3/bin/protoc --java_out=src/main/java src/main/resources/user.proto
```

## 使用mvn插件


```
mvn protobuf:compile

mvn protobuf:compile-custom
```

(protobuf maven插件使用参考)[ttps://www.xolstice.org/protobuf-maven-plugin/usage.html]
(Protocol Buffers)[https://developers.google.com/protocol-buffers/docs/overview]
