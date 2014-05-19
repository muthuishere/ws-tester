package com.ws.application

import com.ws.beans.ActionTaskResult
import com.ws.beans.ResolveTestCase
import com.ws.beans.StringCondition
import com.ws.beans.TestSuite
import groovy.time.TimeCategory

class Test {

	def static toObject(map, obj) {
		map.each {
			 def field = obj.class.getDeclaredField(it.key.toLowerCase())
			// println(field)
			
			 if (it.value != null) {
				 
				 
				 
					  def keyname=it.key.toLowerCase()
					 obj."$keyname" = it.value
					 println("hi $keyname" +it.value)
				 
			   }
			}
		 return obj;
	  }
	
	
	
	
	static main(args) {
		
		/*
			def mymap=[CONDITION:"BAD", SEVERITY:"CRITICAL", ACTIONTASK:"end", ACTIONID:"4028783145655aaf0145f44be1d43e5e"]
			ActionTaskResult at= new ActionTaskResult();
			toObject(mymap,at)
			println(at.dump())
			
			*/
		def condresponse="""

	<conditions>
	
		<actiontask name="generate end point results">
			<condition> </condition>
			<severity> </severity>
			<completed> </completed>
				<summary> 
					<contains>End Point Reached</contains>
					<notcontains>WTTTT</notcontains>
				</summary>
		
			
		</actiontask>
		
	<actiontask name="retrieve diagnostic parameters">
			<condition>GOOD</condition>
			<severity> </severity>
			<completed> </completed>
				<summary> 
					<contains>End Point Reached</contains>
					<notcontains>WTTTT</notcontains>
				</summary>
		
			
		</actiontask>

		
	</conditions>	
"""
		
		
		ResolveWebService re=new ResolveWebService()
		def responses=re.parsetests( condresponse, new File('C:\\muthu\\resolve\\test\\ws-tester\\resources\\resultresponse.xml').text)
			
			
		TestSuite ts=new TestSuite();
		ts.startTime=new Date();
		
		Date tcTime
		use( TimeCategory ) {
			ts.endTime=new Date()  + 3.minutes
			tcTime=new Date()  + 2.minutes
		}
		ts.success=true;
		ts.completed=true
		
	
			
		ts.testCases.push(new ResolveTestCase(
			name:"LTE.voicediagnostics",
			id:"TC_120",
			startTime:new Date(),
			endTime:tcTime,
			actionTaskVerificationResponses:responses
			
			));
		
		
		String templateContent = this.getClass().getResource( '/com/ws/reports/testsuite-template.html' ).text
		
		println(responses)
		

		def inModel = [testSuite:ts]
		def engine = new groovy.text.GStringTemplateEngine()
		def template = engine.createTemplate(templateContent)
		def writer = new StringWriter()
		template.make(inModel).writeTo(writer)
		writer.flush()
	
		new File("C:\\muthu\\resolve\\test\\ws-tester\\resources\\tmp.html").write(writer.toString())
	
		println "Completed"
			}
	
}
