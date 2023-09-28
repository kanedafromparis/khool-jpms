# 

## TLDR ;

```shell
clear
# use java 8
#sdk use java 8.0.242.hs-adpt 
sdk use java 17.0.5-tem 
sdk use maven 3.8.7
# check
java -version 
mvn --version
```

```shell
clear
mvn clean package
```

```shell
clear 
java \
  -classpath ${PWD}/data-sample-jar/target/data-sample-jar-17.1-SNAPSHOT.jar:${PWD}/data-sample-service-jar/target/data-sample-service-jar-17.1-SNAPSHOT.jar:${PWD}/data-sample-service-impl-jar/target/data-sample-service-impl-jar-17.1-SNAPSHOT.jar:${PWD}/data-app-jar/target/data-app-jar-17.1-SNAPSHOT.jar \
  io.github.kandefromparis.khool.jpms.sample.DataApp
```
