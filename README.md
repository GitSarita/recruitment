# Recruitment
Rest Services for Recruitment process.

1. user has to be able to create a job offer and read a single and list all offers.
URL - http://localhost:8080/joboffer
BODY - {
	"jobTitle":"Java Developer",
	"startDate":"2018-01-01"
}

2. candidate has to be able to apply for an offer.
URL - http://localhost:8080/application
BODY - {   "jobId":1,
	"jobTitle":"Java Developer1",
	"email":"palita@c.com"
}

3. user has to be able to read one and list all applications per offer.

a. read one application 
plan to add Oauth for all /admin urls
GET URL : http://localhost:8080/admin/2/application

b. list all applications
GET URL - http://localhost:8080/admin/applications


4. user has to be able to progress the status of an application.
PUT URL - http://localhost:8080//admin/application/2/status?appStatus=HIRED

5. user has to be able to track the number of applications.
GET URL - http://localhost:8080/1/joboffer
returns "noOfApplications": 1,

6. status change triggers a notification (*)
Could not attempt this due to time limitations.
I planned to implement this using RabbitMQ
