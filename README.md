Project requirements: Java 21 and eclipse latest
1) install docker desktop.
2) import projects into eclipse.
3) execute maven goal
   spring-boot:build-image -DskipTests
4) execute step3 for api-gateway, currency-conversion, currency-exchange and naming-server
5) now we can see 4 images listed in docker.
6) open powershell from start menu. execute below the command
7) docker image list
8) navigate to where you have folder structue imported in your local contains docker-compose.yaml file
9) execute command to make all container up and running.
   
    docker-compose up
11) now test api url
    http://localhost:8761
    http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10
    http://localhost:8765/currency-exchange/from/USD/to/INR
    http://localhost:9411/zipkin/
12) Happy Learning!! 
