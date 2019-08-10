# Http response generator

This is a web test application written in java for generation http responses with the desired response time and http response code. 

The application has two endpoints: 


http://localhost:8080/test/slowresponse/< delay in millisecond >

http://localhost:8080/test/slowresponse/< delay in millisecond >/< http response code>


* delay in millisecond : The response will be delayed with the given time
* http response code : The desired http response code



### Build java

    mvn build


### Build docker

Copy the testapp.jar file into the Docker directory and follow the instructions found in Docker/Readmy.md file. 
