#!groovy

import hudson.model.FreeStyleProject
import jenkins.model.Jenkins

Jenkins jenkins = Jenkins.getInstance()
def project = new FreeStyleProject(jenkins, 'Global Seed')

project.setDescription('Generate the next level of seeds from maweeks/jenkins-seeds.')

// project.getBuildersList().add(
//             new ExecuteDslScripts(
//                     new ExecuteDslScripts.ScriptLocation(
//                             null, null,
//                             'scriptText'
//                     ),
//                     false,
//                     RemovedJobAction.DELETE,
//                     RemovedViewAction.DELETE,
//                     LookupStrategy.JENKINS_ROOT
//             )
//     )

project.save()
jenkins.reload()
