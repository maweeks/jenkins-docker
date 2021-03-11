FROM jenkins/jenkins:lts

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
COPY jenkinsFiles/default-user.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY jenkinsFiles/global-seed-job.groovy /usr/share/jenkins/ref/init.groovy.d/

COPY jenkinsFiles/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
