@echo off

cd /d %~dp0
cd ..\
java -cp ".;.\conf;.\lib\*" jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.main.Main start