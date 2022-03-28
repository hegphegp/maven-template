
###[hutool-db数据库名称字段占位符](https://www.bookstack.cn/read/hutool-5.6.0-zh/e1aa00d8eb0ce65b.md)

```
nohup java -DuseEnvParam -DprogramEnv=local -Xmx512m -jar target/maven-template-1.0-SNAPSHOT.jar --db.host=localhost --db.port=3306 --db.username=root --db.password=root --db.name=magic-0.4.8 >/dev/null 2>&1 &
nohup java -DuseEnvParam -DprogramEnv=test -Xmx512m -jar target/maven-template-1.0-SNAPSHOT.jar --db.host=localhost --db.port=3306 --db.username=root --db.password=root --db.name=magic-0.4.8 >/dev/null 2>&1 &
nohup java -DuseEnvParam -DprogramEnv=prod -Xmx512m -jar target/maven-template-1.0-SNAPSHOT.jar --db.host=localhost --db.port=3306 --db.username=root --db.password=root --db.name=magic-0.4.8 >/dev/null 2>&1 &
```