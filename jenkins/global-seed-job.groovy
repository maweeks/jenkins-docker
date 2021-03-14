#!groovy

import hudson.triggers.SCMTrigger;
import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition
import org.jenkinsci.plugins.workflow.job.WorkflowJob

jobParameters = [
    name:          'Global seed',
    description:   'Generate the next level of seeds from maweeks/jenkins-seeds.',
]

Jenkins jenkins = Jenkins.getInstance()

job = new WorkflowJob(jenkins, jobParameters.name)

job.addTrigger(new SCMTrigger('* * * * *'))
job.description = jobParameters.description
job.setDefinition(new CpsFlowDefinition(
        'node {' +
        '\n    checkout([' +
        "\n        \$class: 'GitSCM'," +
        "\n        branches: [[name: '*/master']]," +
        '\n        extensions: [],' +
        '\n        userRemoteConfigs: ' +
        "\n            [[credentialsId: 'maweeksGitHub', url: 'git@github.com:maweeks/jenkins-seeds.git']]" +
        '\n    ])' +
        "\n    stage('Generate Jobs') {" +
        "\n        jobDsl removedConfigFilesAction: 'DELETE'," +
        "\n            removedJobAction: 'DELETE'," +
        "\n            removedViewAction: 'DELETE'," +
        "\n            targets: 'build/*.groovy'" +
        '\n    }' +
        '\n}'
    )
)

jenkins.save()
jenkins.reload()
