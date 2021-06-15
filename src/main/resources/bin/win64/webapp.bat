@ECHO OFF
SETLOCAL ENABLEDELAYEDEXPANSION

SET APP_HOME=%~dp0..\..

java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -Dapp.home="%APP_HOME%" "%APP_HOME%\cloudslang-webapp-0.0.1.jar"

ENDLOCAL