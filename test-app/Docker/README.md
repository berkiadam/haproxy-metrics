# Usage in docker


### Build 
    docker build -t 172.30.1.1:5000/mynamespace/test-app:1.1.0 .


### Push to remote registry
    docker push 172.30.1.1:5000/mynamespace/test-app:1.1.0


### Run in docker
    docker run --name test-app -d -p 8080:8080 172.30.1.1:5000/mynamespace/test-app:1.1.0
