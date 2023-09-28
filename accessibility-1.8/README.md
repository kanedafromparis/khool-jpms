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
clear
cd service && mvn clean install ; cd ..
cd app && mvn package ; cd ..
```

```shell
clear
echo "----- classes -----" &&
java \
  -classpath ${PWD}/service/target/classes:${PWD}/app/target/classes \
  io.github.kandefromparis.khool.jpms.sample.App &&
echo "----- classes -----"
```

```shell
echo "-----   jar   -----" &&
java \
  -classpath ${PWD}/service/target/autorisation-18.0-SNAPSHOT.jar:${PWD}/app/target/app-18.0-SNAPSHOT.jar \
  io.github.kandefromparis.khool.jpms.sample.App &&
echo "-----   jar   -----"
```

```shell
jar tf ${PWD}/service/target/autorisation-21.0-SNAPSHOT.jar
```

```shell
jar tf ${PWD}/service/target/app-21.0-SNAPSHOT.jar
```