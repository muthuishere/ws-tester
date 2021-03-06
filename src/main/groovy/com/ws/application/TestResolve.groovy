package com.ws.application



import java.util.Date;

import com.ws.beans.TestCase
import com.ws.beans.TestCondition;
import com.ws.beans.TestSuite

import wslite.soap.*



public class TestResolve {

	static def getInfo(testargs) {

		def cli = new CliBuilder()
		cli.with {
			h(longOpt: 'help', 'Help - Usage Information')
			e(longOpt: 'environment', 'Test Environment', type: String,args: 1,  required: false)
			f(longOpt: 'testcase', 'Testcase File', type: String, args: 1, required: true)
			c(longOpt: 'configfile', 'Configuration File', type: String,args: 1,  required: false)
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
		//println "The location is ${options.e}, ${options.f}, ."


		def optionsmap = ["testcasefile": options?.f]
		
		if(options?.e)
			optionsmap["environment"]=options.e
		
			if(options?.c)
			optionsmap["configuration"]=options.c
		
		//println(optionsmap.dump())
		return  optionsmap

	}


	static Test(){
		
		def appConfig = new ConfigSlurper("localdev").parse(com.ws.resources.Config)
		TestWebservice tester= new TestWebservice(
			activeConfig:appConfig
			);
		
		
		TestSuite result= 	tester.startTest('C:\\muthu\\resolve\\test\\ws-tester\\resources\\testcase.xml' )

		println(result.toString());
		
		
		
		
		println(result.toFormattedString());
		
	}
	static main(args) {

		//-f  C:\\muthu\\resolve\\test\\ws-tester\\resources\\testcase.xml  -e localdev 
		//-c C:\\muthu\\resolve\\test\\ws-tester\\resources\\Config.groovy
		

		//	String[] tars="-e localdev -f  'C:\\muthu\\gitworkspace\\ws-tester\\resources\\testcase.xml'";
		def options=getInfo(args)
		def appConfig =null;
		if(options.containsKey("configuration") ) {
			println("has configuration")
			def configFile = new File(options["configuration"].toString())
				if(!configFile.exists()){
					
					getInfo("-h")
					System.exit(1)
				}

			appConfig = new ConfigSlurper("current").parse(configFile.toURL())
			
		}else if(options.containsKey("environment")) {
			println("has environment")
				appConfig = new ConfigSlurper(options["environment"]).parse(com.ws.resources.Config)
		}else{
			getInfo("")
			System.exit(1);
		}
		
		//def options = ["testcasefile": "C:\\muthu\\resolve\\test\\ws-tester\\resources\\airport.xml", "environment": "localdev"]
		
		
		
		TestWebservice tester= new TestWebservice(
			activeConfig:appConfig
			);
		
		TestSuite result= 	tester.startTest(options["testcasefile"] )

		println(result.toString());
		
		
		
		
		println(result.toFormattedString());

	}


}