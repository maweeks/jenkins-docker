#!/usr/bin/env bash
echo "Rebuilding jenkins..."
docker container stop jenkins
docker container rm jenkins
docker rmi jenkins
docker build -t jenkins -f Dockerfile . && \
docker run \
    -p 8081:8080 -d \
    -v /var/run/docker.sock:/var/run/docker.sock \
    --name jenkins \
    --env-file .env \
    --restart unless-stopped jenkins
