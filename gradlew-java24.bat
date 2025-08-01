@echo off
setlocal

:: Set Java home
set JAVA_HOME=%~dp0.java\zulu24.0.2
set PATH=%JAVA_HOME%\bin;%PATH%

:: Run the original gradlew.bat with the new Java
call "%~dp0gradlew.bat" %*
