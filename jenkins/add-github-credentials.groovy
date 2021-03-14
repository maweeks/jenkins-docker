#!groovy

import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*

def source = new BasicSSHUserPrivateKey.FileOnMasterPrivateKeySource('/var/jenkins_home/.ssh/id_ed25519')
def ck1 = new BasicSSHUserPrivateKey(CredentialsScope.GLOBAL, 'maweeksGitHub', 'maweeks', source, '', 'SSH Key for GitHub')

SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), ck1)
