package com.ws.application

import com.ws.beans.ResolveTestCase
import com.ws.beans.TestCondition

class Test {

	static main(args) {
		
		
		def response="""
<testsuite>
<testcase>
<name>TC_001</name>
	<request>
		<resolverunbook name="LTE.VoiceDiagnostic">
		
	
		
			<params>
				<!-- Iterate one or many-->	
				<param name="MSISDN" value="447830750719"/>
				
				
			</params>
		</resolverunbook>
		<!-- RAW request Content if required -->
		<content></content>
		
	</request>
	<verify>
	<!-- Iterate one or many-->
		<param><![CDATA[
		return
		]]></param>
		
	</verify>
	
	
</testcase>
<testcase>
<name>TC_002</name>
	<request>
		
		<!-- RAW request Content -->
		<content>
		<![CDATA[
		<?xml version='1.0' encoding='UTF-8'?>
       <soap:Envelope xmlns:soap='http://www.w3.org/2003/05/soap-envelope' xmlns:xsd='http://www.resolve-systems.com/resolve/WebServiceListener/xsd'>
   <soap:Header/>
   <soap:Body>
      <xsd:login>
         <!--Optional:-->
         <xsd:username>mnavaneethakrishnan</xsd:username>
         <!--Optional:-->
         <xsd:password>Superstr1*</xsd:password>
      </xsd:login>
   </soap:Body>
</soap:Envelope>
		]]>
		</content>
	</request>
	<verifyexception>	
		<param><![CDATA[  Error when user was authenticated  ]]></param>
		
	</verifyexception>
	
	
</testcase>
</testsuite>
"""
		
def tws=new ResolveWebService()
tws.resolveToken="XXXX"
println(tws.parseTestSuite(response))

			}
	
}
