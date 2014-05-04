package com.ws.application

class Test {

	static main(args) {
		
		
				//	String[] tars="-e localdev -f  ";
			def langs = new XmlParser().parse('C:\\muthu\\gitworkspace\\ws-tester\\resources\\testcase.xml')
	
		langs.testcase.each{
		  println it.text()
		}

		
			}
	
}
