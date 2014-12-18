@echo off
title InsanityX V1 Reopening
echo Reading Compiled Files....They're located in deps/Data/CompiledFiles
color 3
"C:/Program Files (x86)/java/jdk1.8.0_25/bin/java.exe" -Xmx512m -cp bin;deps/poi.jar;deps/netty.jar;deps/mysql.jar;deps/GTLVote.jar;deps/mina.jar;deps/slf4j.jar;deps/slf4j-nop.jar;deps/jython.jar;log4j-1.2.15.jar; server.Server
pause