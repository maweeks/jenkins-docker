#!groovy

import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.SystemCredentialsProvider
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.jenkins.plugins.awscredentials.AWSCredentialsImpl
import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey

def env = System.getenv()

def awsKey = new AWSCredentialsImpl(CredentialsScope.GLOBAL,
                                    'maweeksAWS',
                                    env.AWS_ACCESS_KEY_ID,
                                    env.AWS_ACCESS_KEY_SECRET,
                                    'AWS credentials for BotUser in maweeks account')

SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), awsKey)

def source = new BasicSSHUserPrivateKey.FileOnMasterPrivateKeySource('/var/jenkins_home/.ssh/id_ed25519')
def githubKey = new BasicSSHUserPrivateKey(CredentialsScope.GLOBAL,
                                            'maweeksGitHub',
                                            'maweeks',
                                            source,
                                            '',
                                            'SSH Key for GitHub')

SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), githubKey)
