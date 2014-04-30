@echo off

cd C:\muthu\resolve\test\bespoken

call env.bat

 for /f "tokens=1-4 delims=/ " %%i in ("%date%") do (
     set dow=%%i
     set month=%%j
     set day=%%k
     set year=%%l
   )
   set datestr=%month%_%day%_%year%
   
    echo "Start: %datestr%" 
     groovy TestResolve.groovy 
	
	 for /f "tokens=1-4 delims=/ " %%i in ("%date%") do (
     set dow=%%i
     set month=%%j
     set day=%%k
     set year=%%l
   )
   set datestr=%month%_%day%_%year%
   
    echo "Fin: %datestr% "  
