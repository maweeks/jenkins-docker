# jenkins-docker

A custom jenkins docker image with some plugins and git credentials set up based off the official docker image.

Official jenkins [repo](https://github.com/jenkinsci/docker)
Official jenkins on [docker hub](https://hub.docker.com/r/jenkins/jenkins)

## Setup

```bash
cp jenkins/ssh-key-example jenkins/ssh-key
ssh-keygen -t ed25519
# Update jenkins/ssh-key
# Add jenkins/ssh-key.pub to GitHub (or other provider)
make buildAndRun
```
