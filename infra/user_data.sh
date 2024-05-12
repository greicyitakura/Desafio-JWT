#!/bin/bash

sudo su update -y
yum install -y docker
service docker start
usermod -a -G docker ec2-user

docker run -p 80:8080 greyitakura/backend-0.0.1