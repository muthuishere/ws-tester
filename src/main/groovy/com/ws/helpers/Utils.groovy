package com.ws.helpers

public class Utils {

	static def exportReport(def testSuite,def filepath){
		
		
		
		String templateContent = this.getClass().getResource( Constants.RESOLVE_TESTCASE_TEMPLATE ).text
		
				def inModel = [testSuite:testSuite]
				def engine = new groovy.text.GStringTemplateEngine()
				def template = engine.createTemplate(templateContent)
				def writer = new StringWriter()
				template.make(inModel).writeTo(writer)
				writer.flush()
				new File(filepath).write(writer.toString())
	}
}
