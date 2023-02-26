# How to run example
## Requirements
- JDK 17!!
- Wildfly
- Postgres Database (docker-compose + setup sql script provided)
- IntelliJ, or some other Java-IDE
- (optional, but required for full feature-set):
    - nodeJS
    - Discord + Discord Application: https://discord.com/developers/applications

## How to run
0) Get source code from: https://github.com/SimonFischer04/POSEMediator (or class drive)
1) (download dependencies + start DiscordProxy using):
```shell
npm install
npm run dev
```
2) backend: generate MapStruct mappers:
```shell
mvn clean install
```
3) start backend (spring-application)
4) generate MicroProfile client in frontend (jsf-ui):
```shell
mvn clean compile
```
5) start JSF UI / Deploy to wildfly