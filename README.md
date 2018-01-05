# spring-xd-modules-practice

spring xd 실행

http://repo.spring.io/release/org/springframework/xd/spring-xd/1.3.2.RELEASE/spring-xd-1.3.2.RELEASE-dist.zip

https://docs.spring.io/spring-xd/docs/1.3.2.RELEASE/reference/html/

http://www.bookstorage.kr:9393/admin-ui/#/jobs/definitions


#################################################################################

spring xd batch job

https://github.com/spring-projects/spring-xd-samples/tree/master/batch-simple

https://github.com/spring-projects/spring-xd/blob/master/src/docs/asciidoc/Batch-Jobs.asciidoc


#################################################################################


1. batch-simple.jar spring-xd올린 서버에 올림

scp /Users/ksb/Documents/spring_xd/spring-xd-modules-practice/batch-simple/build/libs/batch-simple.jar ksb@www.bookstorage.kr:/home/ksb/Documents/service/spring-xd/job


2. xd shell 접속 후 잡 등록

module upload --type job --name myjob --file /home/ksb/Documents/service/spring-xd/job/batch-simple.jar

job create --name helloSpringXD --definition "myjob"

job deploy helloSpringXD

job launch helloSpringXD


3. 크론 등록

http://www.bookstorage.kr:9393/admin-ui/#/jobs/deployments 접속 후 등록
스트림쪽에 등록됨

한시간마다 크론
0 0 */1 * * *


#################################################################################

참고 : https://github.com/Jared314/docker-ubuntu-14-04-java8/blob/master/Dockerfile

로컬 도커 실행

docker build -t custom/springxd-singlenode .
docker run --name singlenode -d -p 9393:9393 custom/springxd-singlenode


#################################################################################

https://github.com/spring-projects/spring-xd/blob/master/src/docs/asciidoc/Creating-a-Processor-Module.asciidoc

trigger --cron='0 */1 * * * *' --payload=test | processor-simple | log

trigger --cron='0 */1 * * * *' --payload=http://test:secret@localhost:8080/v2/apps/test-service | processor-simple | json-to-tuple | transform --expression='payload.app.tasks[0].host + ":" + payload.app.tasks[0].ports[0]' | log


#################################################################################


stream create --name testtest --definition "trigger --cron='0 */1 * * * *' --payload=processor-simple | processor-simple-100 | log" --deploy
stream destroy --name testtest

stream create --name ticktock --definition "time | log" --deploy
stream destroy --name ticktock


#################################################################################

module list