FROM openjdk:11-jdk
RUN mkdir /home/lib
ADD ./*.jar /home
ADD ./lib/* /home/lib/
EXPOSE 8080
CMD ["java", "-cp", "/home/lib/*:/home/testapp.jar", "App"]
