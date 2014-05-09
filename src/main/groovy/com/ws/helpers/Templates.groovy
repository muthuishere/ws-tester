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
/*
 *    Runbook response
 *    
 *    <soapenv:Envelope xmlns:soapenv="http://www.w3.org/2003/05/soap-envelope">
   <soapenv:Body>
      <ns:getRunbookStatusResponse xmlns:ns="http://www.resolve-systems.com/resolve/WebServiceListener/xsd" xmlns:ax21="http://webservice.resolve.com/xsd">
         <ns:return xsi:type="ax21:Pair" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <ax21:name>WIKI</ax21:name>
            <ax21:value>LTE.VoiceDiagnostic</ax21:value>
         </ns:return>
         <ns:return xsi:type="ax21:Pair" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <ax21:name>PROBLEMID</ax21:name>
            <ax21:value>4028782e45655a9d0145e0699b140242</ax21:value>
         </ns:return>
         <ns:return xsi:type="ax21:Pair" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <ax21:name>PROCESSID</ax21:name>
            <ax21:value>4028782e45655a9d0145e0699b310243</ax21:value>
         </ns:return>
         <ns:return xsi:type="ax21:Pair" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <ax21:name>LAST_UPDATED</ax21:name>
            <ax21:value>2014-05-09 09:58:13.329</ax21:value>
         </ns:return>
         <ns:return xsi:type="ax21:Pair" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <ax21:name>STATUS</ax21:name>
            <ax21:value>COMPLETED</ax21:value>
         </ns:return>
         <ns:return xsi:type="ax21:Pair" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <ax21:name>NUMBER</ax21:name>
            <ax21:value>PRB67-133</ax21:value>
         </ns:return>
      </ns:getRunbookStatusResponse>
   </soapenv:Body>
</soapenv:Envelope>
 * 
 * 
 */

public static  String GET_RUNBOOK_STATUS_TEMPLATE="""
<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsd="http://www.resolve-systems.com/resolve/WebServiceListener/xsd">
   <soap:Header/>
   <soap:Body>
      <xsd:getRunbookStatus>
         <!--Optional:-->
         <xsd:token>#token#</xsd:token>
         <!--Optional:-->
         <xsd:processID>#processid#</xsd:processID>
      </xsd:getRunbookStatus>
   </soap:Body>
</soap:Envelope>
"""


/*
 * 
 * <soapenv:Envelope xmlns:soapenv="http://www.w3.org/2003/05/soap-envelope">
   <soapenv:Body>
      <ns:getRunbookResultResponse xmlns:ns="http://www.resolve-systems.com/resolve/WebServiceListener/xsd" xmlns:ax21="http://webservice.resolve.com/xsd">
         <ns:return xsi:type="ax21:Row" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <ax21:columns xsi:type="ax21:Pair">
               <ax21:name>CONDITION</ax21:name>
               <ax21:value>BAD</ax21:value>
            </ax21:columns>
            <ax21:columns xsi:type="ax21:Pair">
               <ax21:name>SEVERITY</ax21:name>
               <ax21:value>CRITICAL</ax21:value>
            </ax21:columns>
            <ax21:columns xsi:type="ax21:Pair">
               <ax21:name>ACTIONTASK</ax21:name>
               <ax21:value>open telnet session</ax21:value>
            </ax21:columns>
            <ax21:columns xsi:type="ax21:Pair">
               <ax21:name>ACTIONID</ax21:name>
               <ax21:value>4028783045655a840145e06a0fdc3d85</ax21:value>
            </ax21:columns>
            <ax21:columns xsi:type="ax21:Pair">
               <ax21:name>ADDRESS</ax21:name>
               <ax21:value xsi:nil="true"/>
            </ax21:columns>
            <ax21:columns xsi:type="ax21:Pair">
               <ax21:name>DURATION</ax21:name>
               <ax21:value>0</ax21:value>
            </ax21:columns>
            <ax21:columns xsi:type="ax21:Pair">
               <ax21:name>COMPLETION</ax21:name>
               <ax21:value>TRUE</ax21:value>
            </ax21:columns>
            <ax21:columns xsi:type="ax21:Pair">
               <ax21:name>LAST UPDATED</ax21:name>
               <ax21:value>2014-05-09 09:56:58.46</ax21:value>
            </ax21:columns>
            <ax21:columns xsi:type="ax21:Pair">
               <ax21:name>SUMMARY</ax21:name>
               <ax21:value>ERROR: Could not establish connection to SRRi1</ax21:value>
            </ax21:columns>
         </ns:return>
</ns:getRunbookResultResponse>
   </soapenv:Body>
</soapenv:Envelope>
 * 
 * 
 */


public static  String GET_RUNBOOK_RESULT_TEMPLATE="""
<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:xsd="http://www.resolve-systems.com/resolve/WebServiceListener/xsd">
   <soap:Header/>
   <soap:Body>
      <xsd:getRunbookResult>
         <!--Optional:-->
         
           <!--Optional:-->
         <xsd:token>#token#</xsd:token>
         <!--Optional:-->
         <xsd:processID>#processid#</xsd:processID>
      </xsd:getRunbookResult>
   </soap:Body>
</soap:Envelope>
"""
}
