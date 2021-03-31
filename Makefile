.PHONY: help buildAndRun deleteDockerVolume getAdminPassword startDocker stopDocker
include variables.mk

help:                 ## Show this help.
	@fgrep -h "##" $(MAKEFILE_LIST) | fgrep -v fgrep | sed -e 's/\\$$//' | sed -e 's/##//'

buildAndRun:          ## Build the docker image
	@./scripts/buildAndRunDocker.sh

deleteDockerVolume:          ## Build the docker image
	@docker container stop jenkins || true && \
	docker container rm jenkins || true && \
	docker volume rm -f jenkins_home

getAdminPassword:          ## Build the docker image
	@docker exec -u root -it jenkins cat /var/jenkins_home/secrets/initialAdminPassword

startDocker:          ## Start the existing jenkins docker container
	@docker start jenkins

stopDocker:           ## Stop the existing jenkins docker container
	@docker stop jenkins
