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

## Useful links

- [DSL Plugin Docs](https://jenkinsci.github.io/job-dsl-plugin/#)
- [DSL Examples 1](https://dev.astrotech.io/jenkins/job-dsl/examples.html)
- [DSL Examples 2](https://amlanscloud.com/jenkinssample/)
- [DSL Examples 3](https://support.cloudbees.com/hc/en-us/articles/115003908372-Main-differences-between-Freestyle-Scripted-Pipeline-Job-Declarative-Pipeline-Job)
- [JavaDoc](https://javadoc.jenkins.io)
- [Java Examples 1](http://tdongsi.github.io/blog/2017/12/30/groovy-hook-script-and-jenkins-configuration-as-code/)
- [Java Examples 2](https://github.com/hayderimran7/useful-jenkins-groovy-init-scripts)
- [Java Examples 3](https://www.javatips.net/api/hudson.model.freestyleproject)
