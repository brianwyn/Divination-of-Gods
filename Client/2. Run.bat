@echo off
color 0a
cd bin
title  Loading client
START javaw -Xmx400m com.Jframe 10 0 highmem members 32
exit