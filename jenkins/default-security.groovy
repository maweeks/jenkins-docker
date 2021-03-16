#!groovy

// http://tdongsi.github.io/blog/2017/12/30/groovy-hook-script-and-jenkins-configuration-as-code/

import hudson.security.FullControlOnceLoggedInAuthorizationStrategy
import hudson.security.HudsonPrivateSecurityRealm
import javaposse.jobdsl.plugin.GlobalJobDslSecurityConfiguration
import jenkins.model.Jenkins
import jenkins.model.GlobalConfiguration
import jenkins.security.s2m.AdminWhitelistRule
import org.kohsuke.stapler.StaplerProxy

def env = System.getenv()
def jenkins = Jenkins.getInstance()

GlobalConfiguration.all().get(GlobalJobDslSecurityConfiguration.class).useScriptSecurity=false

if (!(jenkins.getSecurityRealm() instanceof HudsonPrivateSecurityRealm)) {
    jenkins.setSecurityRealm(new HudsonPrivateSecurityRealm(false))
}

def user = jenkins.getSecurityRealm().createAccount(env.JENKINS_USER, env.JENKINS_PASS)
user.save()

jenkins.getExtensionList(StaplerProxy.class)
    .get(AdminWhitelistRule.class)
    .masterKillSwitch = false

jenkins.setAuthorizationStrategy(new FullControlOnceLoggedInAuthorizationStrategy())

jenkins.save()
