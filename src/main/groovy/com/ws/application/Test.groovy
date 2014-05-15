package com.ws.application

import com.ws.beans.ActionTaskResult
import com.ws.beans.ResolveTestCase
import com.ws.beans.StringCondition

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
		
		
	</conditions>	
"""
		def resultresponse="""

"""
		
		ResolveWebService re=new ResolveWebService()
		re.parsetests( condresponse, new File('C:\\muthu\\resolve\\test\\ws-tester\\resources\\resultresponse.xml').text)
			
			


			}
	
}
