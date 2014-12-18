@echo off
color c
title HazeScape Eco Reset
"C:\Program Files (x86)\Java\jdk1.7.0_40\bin\java.exe" -Xmx1000m -cp bin;deps/poi.jar;deps/mysql.jar;deps/mina.jar;deps/slf4j.jar;deps/slf4j-nop.jar;deps/jython.jar;log4j-1.2.15.jar; EconomyReset
pause