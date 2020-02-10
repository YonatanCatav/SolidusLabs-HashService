# HashService

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

# Requirments
Publish a service on the web with two endpoints:
1. /messages takes a message (a string) as a POST and returns the SHA256 hash digest of
that message (in hexadecimal format)
2. /messages/<hash> is a GET request that returns the original message. A request to a
non-existent<hash> should return a 404 error.
# General Notes

  - I treated the app as the microservice itself  (like the entire app is a microservice that other services will consume)
  - I seperated the hash algorithm implementation and it's derrived from the configuration


# Scale 
  - The solution is designed to be able to scale both vertically and horizantioally.
As the number of requests/second raises, we'll raise the microservice instances number and upgrade the database nodes (using a database that can expand horizantially)
