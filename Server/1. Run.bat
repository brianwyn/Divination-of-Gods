@echo off
title Server
echo Running Server...
color 3
java -Xmx512m -cp bin;deps/poi.jar;deps/gson-2.2.4.jar;deps/netty.jar;deps/mysql.jar;deps/GTLVote.jar;deps/mina.jar;deps/slf4j.jar;deps/slf4j-nop.jar;deps/jython.jar;log4j-1.2.15.jar; server.Server
pause