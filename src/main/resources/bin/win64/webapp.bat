@ECHO OFF
SETLOCAL ENABLEDELAYEDEXPANSION

SET APP_HOME=%~dp0..\..

java -jar -Dapp.home="%APP_HOME%" "%APP_HOME%\cloudslang-webapp-2.0.0.jar"

ENDLOCAL