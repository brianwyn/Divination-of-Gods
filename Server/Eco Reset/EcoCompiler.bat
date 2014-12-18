@echo off
Color C
"C:\Program Files (x86)\Java\jdk1.7.0_40\bin\javac.exe" -classpath deps/log4j-1.2.15.jar;deps/jython.jar;deps/xstream.jar;deps/mina.jar;deps/mysql.jar;deps/poi.jar;deps/slf4j.jar;deps/slf4j-nop.jar -d bin EconomyReset*.java
pause