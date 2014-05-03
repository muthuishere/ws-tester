package com.ws.application



import com.ws.beans.TestSuite
import wslite.soap.*



public class TestResolve {





def getInfo(testargs) {

def cli = new CliBuilder()  
cli.with  
{  
   h(longOpt: 'help', 'Help - Usage Information')  
   e(longOpt: 'environment', 'Test Environment', type: String,args: 1,  required: true)  
   f(longOpt: 'testcase', 'Testcase File', type: String, args: 1, required: true)   
  
}

    def options = cli.parse(testargs)
    if (!options) {
        return
    }
    // Show usage text when -h or --help option is used.
    if (options.h) {
        cli.usage()        
        return
    }
  println "The location is ${options.e}, ${options.f}, ." 
	
	
	def optionsmap = ["testcasefile": options?.t, "environment": options?.e]
   
	println(optionsmap.dump())
	return  optionsmap
  
}
 
 def options= getInfo(args);
 
 def environment = options["environment"] ?: "localdev"
 
 def testCaseFile = options["testcasefile"]
 
 
 
 def activeConfig = new ConfigSlurper(environment).parse(new File('Config.groovy').toURL())

 def lstResults=new ArrayList();
 
 
 //Initialize Test suite assuming result would fail
 def suite=new TestSuite(startTime:new Date(),success:false);
 
 
 
 //Check file Exists
 
 //Parse xml content
 
 //Set Array of positive & negative test
 
 //create object of request , response , Start time , End time , Success Failure

 def input = new File('hello.txt')



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


}