# dit-interview-project
This would be a public repo for general public use with no copyright

Steps to get started
1. db setup 
2. change db credentials in application.properties file based on your mysql credentials
3. execute db-schema.sql in your database.

4. start the spring boot application
5. it should start without any exception

## Below are the curl request for all the APIs

###For /hello
curl --location --request GET 'localhost:8080/v1/hello'

{
"success": true,
"error": null,
"data": "Hello, World!"
}

### For /login
curl --location --request GET 'localhost:8080/v1/login?username=test5&password=root'
{
"success": true,
"error": null
}

### For /create
curl --location --request POST 'localhost:8080/v1/create' --header 'Content-Type: application/json' --data-raw '{
"firstname": "test1",
"lastname": "test",
"username": "test6",
"password": "root"
}'

{
"success": true,
"error": null,
"data": {
"id": 5
}
}

### for Authenticate
curl --location --request GET 'localhost:8080/authenticate' --header 'Authorization: bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0NSIsImV4cCI6MTYyNDc5OTA2MSwiaWF0IjoxNjI0NzgxMDYxfQ.SlyJ_1j-92P3xWDibJP8ZWQK3-sA55bq7I0uKGyBZlYfNSta6UGa8KKu_14Kv3QImxtPIL7OUKjVgsHGgt0vkQ'

{
"success": true,
"error": null,
"data": {
"authorized": true
}
}