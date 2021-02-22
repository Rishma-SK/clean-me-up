# H1 Clean-me-up
The clean-me-up project is rather ugly and the task is to clean it up to make the code more maintainable and follow good coding
practice.

The application exposes a REST end-point for sending email. 
The contract has not yet been published so you don't need to be backwards compatible.

When you are done you should be rather happy with code. 
If you left things behind - please note that down in a read me file.

Estimated time - You have 7 days.  

You should clean/refactor as much OR as little as you like.  The objective of this assignment is to see your coding skills and how you would go about in designing good code.

Show us your skills!


## Changes made to the clean-me-up project 

````````````````````````````````````````````
1. Created and moved classes into respective packages
2. @Controller annotation changed to @RestController - as we are handling json request & responses
3. Added PostMapping value as "/sendEmail" to specifically indicate the request mapping 
4. ResponseEntity<Void> is changed to display a success message if the email sent successfully.
5. Added a package "exception" to handle exception messages with time, error message
6. In EmailHandler, the validation of the input request segregated and moved into separate method.
7. Additional validations added to check valid email address
8. Constant/Configuration values read from the application.properties files.
9. Removed public access specifiers in POJO class SmtpEmail and changed to private. 
10. Added the lombok dependency in pom.xml and used Lombok getter/setter in POJO classes
11. Added Swagger to provide API documentation
12. Dockerized the application using docker build.
13. Added Junit test cases to test positive & negative scenarios
14. Added Spring profile to retrieve different username, password for different environments
15. Added Spring Actuator to monitor application health metrics
16. Added deployment.yaml file to deploy the docerized spring boot into minikube single-cluster node. 

````````````````````````````````````````````

## Future enhancements to the project

````````````````````````````````````````````

1. Securing the application with Spring Security
2. Actual implementation to send email using Java MailAPI. 
3. Real-time use case of sending email on user registration
4. Persistance into Database for sending email via JMS implementation

````````````````````````````````````````````

## Command to build the application with Maven 

````````````````````````````````````````````
mvn clean package
cd clean-me-up-rest
````````````````````````````````````````````

## Command to build the Docker image
````````````````````````````````````````````
docker build -f Dockerfile -t docker-emailapi:1.1 .
````````````````````````````````````````````

## Command to Run the Docker image

````````````````````````````````````````````
docker run -p 8083:8083 docker-emailapi:1.1

````````````````````````````````````````````

## View the application using Swagger 2 API 

Run the server and browse to

````````````````````````````````````````````
    http://localhost:8083/swagger-ui.html
    
````````````````````````````````````````````

## Sample Valid JSON Request Body

````````````````````````````````````````````````````````
```json
{
	"to_address": "rishma88@gmail.com",
	"subject": "Test Email",
	"content": "Welcome to Code clean up test"
}
```
`````````````````````````````````````````````````````````

## Commands to tag and push the image to Remote repository
````````````````````````````````````````````
docker tag docker-emailapi:1.2 rsankarb/springboot-emailapi:1.2
docker push rsankarb/springboot-emailapi:1.2

````````````````````````````````````````````

## Steps to deploy the application into Minikube cluster

Start the minikube using 

1. Minikube start
2. Minikube dashboard

kubectl cluster-info
````````````````````````````````````````````````````````````````````````````
Kubernetes master is running at https://127.0.0.1:49157
KubeDNS is running at https://127.0.0.1:49157/api/v1/namespaces/kube-system/services/kube-dns:dns/proxy
````````````````````````````````````````````````````````````````````````````

kubectl apply -f deployments.yml
``````````````````````````````````````
deployment.apps/emailapikube unchanged
service/emailapikube created
``````````````````````````````````````
kubectl get pods

``````````````````````````````````````````````````````````````````````
NAME                            READY   STATUS    RESTARTS   AGE
emailapikube-64b9f554d4-86t2l   1/1     Running   2          8m19s
emailapikube-64b9f554d4-mfq45   1/1     Running   1          8m19s
emailapikube-64b9f554d4-vsxdt   1/1     Running   0          3m33s

``````````````````````````````````````````````````````````````````````

kubectl get services

``````````````````````````````````````````````````````````````````
NAME                  TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
emailapikube          NodePort       10.101.190.60   <none>        8083:31002/TCP   11m
``````````````````````````````````````````````````````````````````````````

minikube service emailapikube

````````````````````````````````````````````````````````````````````

|-----------|--------------|-------------|---------------------------|
| NAMESPACE |     NAME     | TARGET PORT |            URL            |
|-----------|--------------|-------------|---------------------------|
| default   | emailapikube |        8083 | http://192.168.49.2:31002 |
|-----------|--------------|-------------|---------------------------|
* Starting tunnel for service emailapikube.
|-----------|--------------|-------------|------------------------|
| NAMESPACE |     NAME     | TARGET PORT |          URL           |
|-----------|--------------|-------------|------------------------|
| default   | emailapikube |             | http://127.0.0.1:54347 |
|-----------|--------------|-------------|------------------------|

````````````````````````````````````````````````````````````````````````

## Open browser to check the application up and running

````````````````````````````````````````````````````````````````````````````
http://127.0.0.1:54347/actuator/health
````````````````````````````````````````````````````````````````````````````

