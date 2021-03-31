#!/usr/bin/env bash
echo "Rebuilding jenkins..."
docker container stop jenkins
docker container rm jenkins
docker rmi jenkins
docker build -t jenkins -f Dockerfile . && \
docker run \
    --privileged \
    -p 8081:8080 -d \
    -v jenkins_home:/var/jenkins_home \
    --name jenkins \
    --env-file .env \
    --restart unless-stopped jenkins

# -td
