
@echo off
REM cd C:\muthu\resolve\test\bespoken

call env.bat

REM   set datestr=%month%_%day%_%year%
   
   REM  echo "Start: %datestr%" 
     groovy TestResolve.groovy -e localdev -t "C:\muthu\resolve\test\ws-tester\sample\testcase.xml"
	
   REM set datestr=%month%_%day%_%year%
   
    REM echo "Fin: %datestr% "  
