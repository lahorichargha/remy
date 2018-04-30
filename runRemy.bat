@ECHO OFF
REM before running this do mvn assembly:assembly to create the jar
set REMY_VERSION=0.1
set REMY_JAR=target/remy-%REMY_VERSION%-jar-with-dependencies.jar

REM Compile and assembly jar if does not exist
if EXIST %REMY_JAR% goto run
REM Call via command 
cmd /c mvn package

:run
REM Run Remy
echo ------------------------------------------------------------------------------------------------
echo Running remy using %REMY_JAR%
echo ------------------------------------------------------------------------------------------------
java -mx800m -jar %REMY_JAR%