FROM jenkins/jenkins

RUN touch /var/jenkins_home/copy_reference_file.log

USER root
RUN rm /bin/sh && ln -s /bin/bash /bin/sh
COPY jenkins/supervisord.conf /etc/supervisor/conf.d/supervisord.conf

RUN mkdir -p /var/log/supervisor && \
    mkdir -p /var/log/docker && \
    mkdir -p /var/log/jenkins && \
    apt-get update -qq && \
    apt-get -qqy install \
    apt-transport-https \
    ca-certificates \
    build-essential \
    curl \
    gnupg2 \
    libssl-dev \
    lxc \
    ruby \
    shellcheck \
    software-properties-common \
    supervisor && \
    gem install cfn-nag && \
    curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
    add-apt-repository \
    "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
    $(lsb_release -cs) \
    stable" && \
    apt-get update && \
    apt-get -y install docker-ce && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip" && \
    unzip awscliv2.zip && \
    ./aws/install

ENV NVM_DIR /usr/local/nvm

RUN mkdir /usr/local/nvm \
    && chown jenkins: /usr/local/nvm

USER jenkins

COPY jenkins/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt && \
    mkdir /var/jenkins_home/plugins/ && \
    cp -R /usr/share/jenkins/ref/plugins/* /var/jenkins_home/plugins/
COPY jenkins/*.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY jenkins/ssh-key /var/jenkins_home/.ssh/id_ed25519

ENV NODE_VERSION 14.16.0
ENV NVM_DIR /usr/local/nvm
ENV NODE_PATH $NVM_DIR/v$NODE_VERSION/lib/node_modules
ENV PATH      $NVM_DIR/versions/node/v$NODE_VERSION/bin:$PATH

RUN touch ~/.bashrc \
    && curl https://raw.githubusercontent.com/creationix/nvm/v0.38.0/install.sh | bash \
    && source $NVM_DIR/nvm.sh \
    && nvm install $NODE_VERSION \
    && nvm alias default $NODE_VERSION \
    && nvm use default \
    && npm i -g aws-cdk

USER root

RUN gpasswd -a jenkins docker

ADD ./jenkins/wrapdocker /usr/local/bin/wrapdocker
RUN chmod +x /usr/local/bin/wrapdocker

CMD /usr/bin/supervisord -c /etc/supervisor/conf.d/supervisord.conf
