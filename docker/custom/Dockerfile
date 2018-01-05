FROM ubuntu:14.04
RUN cd ~/
RUN apt-get update && apt-get install -y wget zip unzip
RUN apt-get update && apt-get -y upgrade && apt-get -y install software-properties-common && add-apt-repository ppa:webupd8team/java -y && apt-get update
RUN (echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections) && apt-get install -y oracle-java8-installer oracle-java8-set-default

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV PATH $JAVA_HOME/bin:$PATH

RUN wget http://repo.spring.io/release/org/springframework/xd/spring-xd/1.3.2.RELEASE/spring-xd-1.3.2.RELEASE-dist.zip 
RUN unzip spring-xd-1.3.2.RELEASE-dist.zip
#RUN ./spring-xd-1.3.2.RELEASE/xd/bin/xd-singlenode

CMD ["./spring-xd-1.3.2.RELEASE/xd/bin/xd-singlenode"]
EXPOSE 9393
