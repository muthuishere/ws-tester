// The Adapter for the OCOM service.



import java.util.concurrent.*
import groovy.json.JsonSlurper
import groovy.json.JsonBuilder
import java.security.MessageDigest
import org.apache.http.Header
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.auth.AuthScope
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.conn.ssl.SSLContextBuilder
import org.apache.http.conn.ssl.TrustSelfSignedStrategy
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.conn.ssl.X509HostnameVerifier
import org.apache.http.client.config.RequestConfig
import wslite.soap.*


 
 def activeConfig = new ConfigSlurper("localdev").parse(new File('Config.groovy').toURL())




 def client = new SOAPClient(activeConfig.resolveEndPointURL)
 
if(activeConfig.proxy){
	
	def proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(activeConfig.proxy.host, activeConfig.proxy.port))

	if(activeConfig.proxy.user){
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(activeConfig.proxy.user,activeConfig.proxy.password.toCharArray())
			}
		})
	}
	
	client.httpClient.proxy = proxy
}



def response =client.send(SOAPVersion.V1_2,
            connectTimeout:activeConfig.connectionTimeout,
            readTimeout:activeConfig.readTimeout,
    """<?xml version='1.0' encoding='UTF-8'?>
       <soap:Envelope xmlns:soap='http://www.w3.org/2003/05/soap-envelope' xmlns:xsd='http://www.resolve-systems.com/resolve/WebServiceListener/xsd'>
   <soap:Header/>
   <soap:Body>
      <xsd:login>
         <!--Optional:-->
         <xsd:username>mnavaneethakrishnan</xsd:username>
         <!--Optional:-->
         <xsd:password>Superstar1*</xsd:password>
      </xsd:login>
   </soap:Body>
</soap:Envelope>"""
)


println response.text