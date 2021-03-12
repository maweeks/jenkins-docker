import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*

def source = new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource('key')
def ck1 = new BasicSSHUserPrivateKey(CredentialsScope.GLOBAL, java.util.UUID.randomUUID().toString(), 'username', source, 'passphrase', 'description')

SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), ck1)
