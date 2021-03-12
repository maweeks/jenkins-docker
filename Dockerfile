FROM jenkins/jenkins:lts

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false -Dpermissive-script-security.enabled=true

COPY jenkins/ssh-key /var/jenkins_home/.ssh/id_ed25519
COPY jenkins/*.groovy /usr/share/jenkins/ref/init.groovy.d/

COPY jenkins/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
