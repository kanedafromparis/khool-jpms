# 

## TLDR ;

```shell
# use java 8 and maven 3.8.7
sdk use java 17.0.5-tem 
sdk use maven 3.8.7
# check
java -version 
mvn --version
find . -type f -name "module-info.java" -delete
```

create "blank module-info"

```shell
clear

cat <<EOF >${PWD}/data-app/src/main/java/io/github/kandefromparis/khool/jpms/sample/DataApp.java
package io.github.kandefromparis.khool.jpms.sample;

import io.github.kandefromparis.khool.jpms.sample.model.DataSample;
import io.github.kandefromparis.khool.jpms.sample.service.DataService;

public class DataApp {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        DataService service = (DataService)Class.forName("io.github.kandefromparis.khool.jpms.sample.service.mem.DataMemService").newInstance();
        System.out.println("create");
        String id = service.create(new DataSample());
        System.out.println("created : "+id);
        DataSample dataSample = service.read("0");
        System.out.println("read (null) : "+dataSample);
        dataSample = service.read(id);
        //service.update(new DataSample());
        service.delete(id);
        System.out.println("deleted : "+id);
    }
}

EOF

cat <<EOF >${PWD}/data-sample-mod/src/main/java/module-info.java
//empty version
module io.github.kandefromparis.khool.jpms.sample.model {
}
EOF

cat <<EOF >${PWD}/data-sample-service-mod/src/main/java/module-info.java
//empty version
module io.github.kandefromparis.khool.jpms.sample.service {
}
EOF

cat <<EOF >${PWD}/data-sample-service-impl-mod/src/main/java/module-info.java
//empty version
module io.github.kandefromparis.khool.jpms.sample.service.mem {
}
EOF

cat <<EOF >${PWD}/data-app/src/main/java/module-info.java
//empty version
module io.github.kandefromparis.khool.jpms.sample {
}
EOF


mvn clean package
```

```

```shell
clear
mvn clean package
```


```shell
# package io.github.kandefromparis.khool.jpms.sample.model is not visible
```

so you need to add export informations to our modules.
we will go from data -> service -> impl -> run
So let add export information in model
`exports io.github.kandefromparis.khool.jpms.sample.model;` in [data-sample-mod/src/main/java/module-info.java](data-sample-mod/src/main/java/module-info.java)

```shell
clear
cat <<EOF >${PWD}/data-sample-mod/src/main/java/module-info.java
//export version
module io.github.kandefromparis.khool.jpms.sample.model {
  exports io.github.kandefromparis.khool.jpms.sample.model;
}
EOF
mvn clean package
```

We now see that service *require* access to this module, also this module will be needed by other  therefor we alows it to be transitive (_required will not be need once you use this module_)

```shell
clear
cat <<EOF >${PWD}/data-sample-service-mod/src/main/java/module-info.java
//export version
module io.github.kandefromparis.khool.jpms.sample.service {
    requires transitive io.github.kandefromparis.khool.jpms.sample.model;
    exports io.github.kandefromparis.khool.jpms.sample.service;
}
EOF
mvn clean package
```

This time the implementation do not have vision on service and model module, so we need to define that implementation module need the service module. _model requirement is not needed since it's transitive from service_.

```shell
clear
cat <<EOF >${PWD}/data-sample-service-impl-mod/src/main/java/module-info.java
//export version
module io.github.kandefromparis.khool.jpms.sample.service.mem {
    requires transitive io.github.kandefromparis.khool.jpms.sample.service;
}
EOF
mvn clean package
```

Again we move another step but we still have some building issue with app that do not have access to java

```shell
clear
cat <<EOF >${PWD}/data-app/src/main/java/module-info.java
//export version
module io.github.kandefromparis.khool.jpms.sample {
    requires transitive io.github.kandefromparis.khool.jpms.sample.service;
}
EOF
mvn clean package
```

We have built our module.
Let's run it with _classpath_ (with ALL-UNAMED, that open everything ;-)

```shell
clear 
java \
  -classpath ${PWD}/data-sample-mod/target/data-sample-mod-17.1-SNAPSHOT.jar:${PWD}/data-sample-service-mod/target/data-sample-service-mod-17.1-SNAPSHOT.jar:${PWD}/data-sample-service-impl-mod/target/data-sample-service-impl-mod-17.1-SNAPSHOT.jar:${PWD}/data-app/target/data-app-17.1-SNAPSHOT.jar \
  io.github.kandefromparis.khool.jpms.sample.DataApp
```

if 

```shell
clear
java \
  --module-path ${PWD}/data-sample-mod/target/data-sample-mod-17.1-SNAPSHOT.jar:${PWD}/data-sample-service-mod/target/data-sample-service-mod-17.1-SNAPSHOT.jar:${PWD}/data-sample-service-impl-mod/target/data-sample-service-impl-mod-17.1-SNAPSHOT.jar:${PWD}/data-app/target/data-app-17.1-SNAPSHOT.jar \
   --add-modules io.github.kandefromparis.khool.jpms.sample.service.mem,io.github.kandefromparis.khool.jpms.sample.service,io.github.kandefromparis.khool.jpms.sample.model,io.github.kandefromparis.khool.jpms.sample \
   io.github.kandefromparis.khool.jpms.sample.DataApp
```

We encounter issu because we did not _open_, let's correct our module

```shell
clear
java \
  --module-path ${PWD}/data-sample-mod/target/data-sample-mod-17.1-SNAPSHOT.jar:${PWD}/data-sample-service-mod/target/data-sample-service-mod-17.1-SNAPSHOT.jar:${PWD}/data-sample-service-impl-mod/target/data-sample-service-impl-mod-17.1-SNAPSHOT.jar:${PWD}/data-app/target/data-app-17.1-SNAPSHOT.jar \
  --add-modules io.github.kandefromparis.khool.jpms.sample.service.mem,io.github.kandefromparis.khool.jpms.sample.service,io.github.kandefromparis.khool.jpms.sample.model,io.github.kandefromparis.khool.jpms.sample \
  --add-opens io.github.kandefromparis.khool.jpms.sample.service.mem/io.github.kandefromparis.khool.jpms.sample.service.mem=io.github.kandefromparis.khool.jpms.sample \
  io.github.kandefromparis.khool.jpms.sample.DataApp
```

this is the "dirty way" by using "--add-opens"

```
# reminder
java
    --module-path mod1:mod2:...
    --add-modules owner1:owner2:...
    --add-opens owner/owner=intruder
    --module intruder
```

A better way if it was foreseen from the beginning define it properly from the `module-info.java`

```shell
clear
cat <<EOF >${PWD}/data-sample-service-impl-mod/src/main/java/module-info.java
//open version
module io.github.kandefromparis.khool.jpms.sample.service.mem {
    requires transitive io.github.kandefromparis.khool.jpms.sample.service;
    opens io.github.kandefromparis.khool.jpms.sample.service.mem;    
}
EOF
mvn clean package
```

```shell
    java \
      --module-path ${PWD}/data-sample-mod/target/data-sample-mod-17.1-SNAPSHOT.jar:${PWD}/data-sample-service-mod/target/data-sample-service-mod-17.1-SNAPSHOT.jar:${PWD}/data-sample-service-impl-mod/target/data-sample-service-impl-mod-17.1-SNAPSHOT.jar:${PWD}/data-app/target/data-app-17.1-SNAPSHOT.jar \
      --add-modules io.github.kandefromparis.khool.jpms.sample.service.mem,io.github.kandefromparis.khool.jpms.sample.service,io.github.kandefromparis.khool.jpms.sample.model,io.github.kandefromparis.khool.jpms.sample \
      io.github.kandefromparis.khool.jpms.sample.DataApp
```
# 
```shell
clear
cat <<EOF >${PWD}/data-sample-service-impl-mod/src/main/java/module-info.java
//provide version
module io.github.kandefromparis.khool.jpms.sample.service.mem {
    requires transitive io.github.kandefromparis.khool.jpms.sample.service;
    provides io.github.kandefromparis.khool.jpms.sample.service.DataService
      with     io.github.kandefromparis.khool.jpms.sample.service.mem.DataMemService;
}
EOF

cat <<EOF >${PWD}/data-app/src/main/java/module-info.java
//uses version
module io.github.kandefromparis.khool.jpms.sample {
  requires io.github.kandefromparis.khool.jpms.sample.service;
  uses io.github.kandefromparis.khool.jpms.sample.service.DataService;
}
EOF
mvn clean package
```

```shell
clear
cat <<EOF >${PWD}/data-app/src/main/java/io/github/kandefromparis/khool/jpms/sample/DataApp.java
package io.github.kandefromparis.khool.jpms.sample;

import io.github.kandefromparis.khool.jpms.sample.model.DataSample;
import io.github.kandefromparis.khool.jpms.sample.service.DataService;

import java.util.ServiceLoader;

public class DataApp {
public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        ServiceLoader<DataService> dataService = ServiceLoader.load(DataService.class);
        DataService service = dataService.findFirst().get();
        System.out.println("create");
        String id = service.create(new DataSample());
        System.out.println("created : "+id);
        DataSample dataSample = service.read("0");
        System.out.println("read (null) : "+dataSample);
        dataSample = service.read(id);
        //service.update(new DataSample());
        service.delete(id);
        System.out.println("deleted : "+id);
    }
}

EOF

mvn clean package
```
```shell
java \
  --module-path ${PWD}/data-sample-mod/target/data-sample-mod-17.3-SNAPSHOT.jar:${PWD}/data-sample-service-mod/target/data-sample-service-mod-17.3-SNAPSHOT.jar:${PWD}/data-sample-service-impl-mod/target/data-sample-service-impl-mod-17.3-SNAPSHOT.jar:${PWD}/data-app/target/data-app-17.3-SNAPSHOT.jar \
  --add-modules io.github.kandefromparis.khool.jpms.sample.service.mem,io.github.kandefromparis.khool.jpms.sample.service,io.github.kandefromparis.khool.jpms.sample.model,io.github.kandefromparis.khool.jpms.sample \
  io.github.kandefromparis.khool.jpms.sample.DataApp
```