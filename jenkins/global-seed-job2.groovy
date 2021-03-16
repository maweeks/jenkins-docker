#!groovy

import hudson.model.FreeStyleProject;
import hudson.plugins.git.BranchSpec;
import hudson.plugins.git.extensions.impl.CleanBeforeCheckout;
import hudson.plugins.git.extensions.impl.CloneOption;
import hudson.plugins.git.extensions.impl.SparseCheckoutPath;
import hudson.plugins.git.extensions.impl.SparseCheckoutPaths;
import hudson.plugins.git.GitSCM;
import hudson.plugins.git.UserRemoteConfig;
import hudson.scm.SCM;
import hudson.triggers.SCMTrigger;
import jenkins.model.Jenkins;
import hudson.tasks.Shell;

def jobParameters = [
    name:                'Global_seed_x',
    description:         'Generate seeds from maweeks/jenkins-seeds.',
    repository:          'git@github.com:maweeks/jenkins-seeds.git',
    branch:              'master',
    credentialId:        'maweeksGitHub',
    jenkinsfile:         'build/*.groovy',
    scmTriggerFrequency: '* * * * *'
]

Jenkins jenkins = Jenkins.getInstance()
def project = new FreeStyleProject(jenkins, jobParameters.name)

project.setDescription(jobParameters.description)
project.addTrigger(new SCMTrigger(jobParameters.scmTriggerFrequency))

def branchConfig              = [new BranchSpec(jobParameters.branch)]
def userConfig                = [new UserRemoteConfig(jobParameters.repository, null, null, jobParameters.credentialId)]
def cleanBeforeCheckOutConfig = new CleanBeforeCheckout()
def sparseCheckoutPathConfig  = new SparseCheckoutPaths([new SparseCheckoutPath(jobParameters.jenkinsfile)])
def cloneConfig               = new CloneOption(false, true, null, 3)
def extensionsConfig          = [cleanBeforeCheckOutConfig, sparseCheckoutPathConfig, cloneConfig]
def scm                       = new GitSCM(userConfig, branchConfig, false, [], null, null, extensionsConfig)

project.setScm(scm);

project.getBuildersList().add(new Shell("echo 'Shell command'"));
// TODO: Add build step
// project.getBuildersList().add(new HelloWorldBuilder("Bobbly"));
// project.getBuildersList().add(
//             new PipelineGenerationStep('','','','','','','',false,''
//             )
//     )
// TODO: Add build step

project.save()
jenkins.reload()
