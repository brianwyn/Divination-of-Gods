@echo off
color 0a
cd bin
title  Loading client
java -Xmx400m com.Jframe 10 0 highmem members 32
pause