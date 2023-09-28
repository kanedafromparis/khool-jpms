# 

## TLDR ;

```shell
# use java 8
sdk use java 8.0.242.hs-adpt 
#sdk use java 8.0.252.hs-adpt
sdk use java 8.0.372-tem
sdk use maven 3.8.7
# check
java -version 
mvn --version
```

```shell
cd hello-service && mvn clean install ; cd ..
cd hello-a && mvn clean install ; cd ..
cd hello-b && mvn clean install ; cd ..
cd app && mvn clean package ; cd ..
```

```shell
clear &&
echo "----- classes -----" &&
java \
  -classpath ${PWD}/hello-service/target/classes:${PWD}/hello-a/target/classes:${PWD}/app/target/classes \
  io.github.kandefromparis.khool.jpms.sample.App &&
java \
  -classpath ${PWD}/hello-service/target/classes:${PWD}/hello-b/target/classes:${PWD}/app/target/classes \
  io.github.kandefromparis.khool.jpms.sample.App &&
echo "----- classes -----"
```

```shell
clear &&
echo "----- classes -----" &&
java \
  -classpath ${PWD}/hello-service/target/classes:${PWD}/hello-a/target/classes:${PWD}/hello-b/target/classes:${PWD}/app/target/classes \
  io.github.kandefromparis.khool.jpms.sample.App &&
java \
  -classpath ${PWD}/hello-service/target/classes:${PWD}/hello-b/target/classes:${PWD}/hello-a/target/classes:${PWD}/app/target/classes \
  io.github.kandefromparis.khool.jpms.sample.App &&
echo "----- classes -----"
```

```shell
clear &&
echo "-----   jar   -----" &&
java \
  -classpath ${PWD}/hello-service/target/hello-service-18.0-SNAPSHOT.jar:${PWD}/hello-a/target/hello-a-18.0-SNAPSHOT.jar:${PWD}/app/target/hello-app-18.0-SNAPSHOT.jar \
  io.github.kandefromparis.khool.jpms.sample.App &&
java \
  -classpath ${PWD}/hello-service/target/hello-service-18.0-SNAPSHOT.jar:${PWD}/hello-b/target/hello-b-18.0-SNAPSHOT.jar:${PWD}/app/target/hello-app-18.0-SNAPSHOT.jar \
  io.github.kandefromparis.khool.jpms.sample.App &&
echo "-----   jar   -----"
```

```shell
clear &&
echo "-----   jar   -----" &&
java \
  -classpath ${PWD}/hello-service/target/hello-service-18.0-SNAPSHOT.jar:${PWD}/hello-a/target/hello-a-18.0-SNAPSHOT.jar:${PWD}/hello-b/target/hello-b-18.0-SNAPSHOT.jar:${PWD}/app/target/hello-app-18.0-SNAPSHOT.jar \
  io.github.kandefromparis.khool.jpms.sample.App &&
java \
  -classpath ${PWD}/hello-service/target/hello-service-18.0-SNAPSHOT.jar:${PWD}/hello-b/target/hello-b-18.0-SNAPSHOT.jar:${PWD}/hello-a/target/hello-a-18.0-SNAPSHOT.jar:${PWD}/app/target/hello-app-18.0-SNAPSHOT.jar \
  io.github.kandefromparis.khool.jpms.sample.App &&
echo "-----   jar   -----"
```