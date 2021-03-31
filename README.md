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

## Usage

Using nvm in a build:

```groovy
stage('NVM') {
    steps {
        sh '''
        #!/usr/local/bin/bash
        source ~/.bashrc
        nvm --version
        '''
    }
}
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
- [Docker fix](https://tutorials.releaseworksacademy.com/learn/the-simple-way-to-run-docker-in-docker-for-ci)

  - [repo with fix](https://github.com/releaseworks/jenkins-withdocker)

    ```Dockerfile
    FROM jenkins/jenkins:lts
    MAINTAINER miiro@getintodevops.com
    USER root

    # Install the latest Docker CE binaries
    RUN apt-get update && \
        apt-get -y install apt-transport-https \
        ca-certificates \
        curl \
        gnupg2 \
        software-properties-common && \
        curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
        add-apt-repository \
        "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
        $(lsb_release -cs) \
        stable" && \
    apt-get update && \
    apt-get -y install docker-ce
    ```

- [Other docker link 1](https://www.jenkins.io/doc/book/installing/docker/#installing-docker)
- [Other docker link 2](https://www.docker.com/blog/docker-can-now-run-within-docker/)
- [Other docker link 3](http://jpetazzo.github.io/2015/09/03/do-not-use-docker-in-docker-for-ci/)
- [Other docker link 4](http://blog.teracy.com/2017/09/11/how-to-use-docker-in-docker-dind-and-docker-outside-of-docker-dood-for-local-ci-testing/)
- [Jenkins DIND](https://github.com/ayudadigital/jenkins-dind)
- [Supervisor](https://blog.jayway.com/2015/03/14/docker-in-docker-with-jenkins-and-supervisord/)
