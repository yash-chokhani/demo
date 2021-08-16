curl:
`curl --location --request GET 'localhost:8080/area/results' \
--header 'Content-Type: application/json' \
--data-raw '{
    "1":{"type":"circle","radius":2},
    "2":{"type":"square","side":4}
}`

response:
`{
"success": true,
"data": [
{
"id": 1,
"area": 12.571428571428571
},
{
"id": 2,
"area": 16.0
}
]
}`

Curl:
`curl --location --request GET 'localhost:8080/area/results/1'`

response: 
`{
"success": true,
"data": {
"id": 1,
"area": 12.571428571428571
}
}`