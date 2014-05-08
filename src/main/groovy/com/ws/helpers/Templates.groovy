package com.ws.helpers

public class Templates {

	public static  String LOGIN_TEMPLATE="""
<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsd="http://www.resolve-systems.com/resolve/WebServiceListener/xsd">
   <soap:Header/>
   <soap:Body>
      <xsd:login>
         <!--Optional:-->
         <xsd:username>#user#</xsd:username>
         <!--Optional:-->
         <xsd:password>#pwd#</xsd:password>
      </xsd:login>
   </soap:Body>
</soap:Envelope>
"""


public static  String RUNBOOK_PARAM_TEMPLATE="""
			<xsd:params>
            <!--Optional:-->
            <xsd1:name>#name#</xsd1:name>
            <!--Optional:-->
            <xsd1:value>#value#</xsd1:value>
         </xsd:params>
"""
public static  String START_RUNBOOK_TEMPLATE="""

<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsd="http://www.resolve-systems.com/resolve/WebServiceListener/xsd" xmlns:xsd1="http://webservice.resolve.com/xsd">
   <soap:Header/>
   <soap:Body>
      <xsd:startRunbook>
         <!--Optional:-->
         <xsd:token>#token#</xsd:token>
         <!--Zero or more repetitions:-->
		<xsd:params>
            <!--Optional:-->
            <xsd1:name>WIKI</xsd1:name>
            <!--Optional:-->
            <xsd1:value>#runbookname#</xsd1:value>
         </xsd:params>         
		<xsd:params>
            <!--Optional:-->
            <xsd1:name>ACTION</xsd1:name>
            <!--Optional:-->
            <xsd1:value>EXECUTEPROCESS</xsd1:value>
         </xsd:params>
		 <xsd:params>
            <!--Optional:-->
            <xsd1:name>PROBLEMID</xsd1:name>
            <!--Optional:-->
            <xsd1:value>NEW</xsd1:value>
         </xsd:params>
			#params#
 		
      </xsd:startRunbook>
   </soap:Body>
</soap:Envelope>
"""
}
