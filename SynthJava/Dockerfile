FROM centos:8
RUN cd /etc/yum.repos.d/
RUN sed -i 's/mirrorlist/#mirrorlist/g' /etc/yum.repos.d/CentOS-*
RUN sed -i 's|#baseurl=http://mirror.centos.org|baseurl=http://vault.centos.org|g' /etc/yum.repos.d/CentOS-*
RUN yum -y update
RUN yum -y groupinstall "Development tools"
RUN dnf -y install java-17-openjdk java-17-openjdk-devel
RUN yum -y install alsa-lib openssl wget
RUN alternatives --set java /usr/lib/jvm/java-17-openjdk-17.0.1.0.12-2.el8_5.x86_64/bin/java
RUN alternatives --set javac /usr/lib/jvm/java-17-openjdk-17.0.1.0.12-2.el8_5.x86_64/bin/javac
COPY . /app/
WORKDIR /app
RUN sh mvnw clean dependency:copy-dependencies
RUN sh mvnw package && cp /app/target/syntezator-0.0.1-SNAPSHOT.jar /app.jar
RUN rm -rf /app
WORKDIR /
CMD ["java", "-jar", "app.jar"]