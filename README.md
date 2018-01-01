# spring-xd-modules-practice

spring xd 실행

http://repo.spring.io/release/org/springframework/xd/spring-xd/1.3.2.RELEASE/spring-xd-1.3.2.RELEASE-dist.zip

https://docs.spring.io/spring-xd/docs/1.3.2.RELEASE/reference/html/

http://www.bookstorage.kr:9393/admin-ui/#/jobs/definitions


#################################################################################

spring xd batch job

https://github.com/spring-projects/spring-xd-samples/tree/master/batch-simple

#################################################################################


1. batch-simple.jar spring-xd올린 서버에 올림

scp /Users/ksb/Documents/spring_xd/spring-xd-modules-practice/batch-simple/build/libs/batch-simple.jar ksb@www.bookstorage.kr:/home/ksb/Documents/service/spring-xd/job


2. xd shell 접속 후 잡 등록
module upload --type job --name myjob --file /home/ksb/Documents/service/spring-xd/job/batch-simple.jar

job create --name helloSpringXD --definition "myjob"


#################################################################################








