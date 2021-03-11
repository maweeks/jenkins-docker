.PHONY: help build run
include variables.mk

help:                 ## Show this help.
	@fgrep -h "##" $(MAKEFILE_LIST) | fgrep -v fgrep | sed -e 's/\\$$//' | sed -e 's/##//'

buildAndRun:          ## Build the docker image
	@./scripts/buildAndRunDocker.sh
