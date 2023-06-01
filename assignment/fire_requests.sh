#!/bin/bash

while true; do
    for i in {1..1000}; do
        curl -X POST -H "Content-Type: application/json" -d '{
            "id":1,
            "unixTs":1,
            "userId":1,
            "eventName":"login"
        }' http://localhost:8080/logger/log &
    done
    wait
done