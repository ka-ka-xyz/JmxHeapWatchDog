@echo off

cd /d %~dp0
cd ..\

set JVM_PATH="auto"
REM ===========================
REM if you failed starting service and get message "failed creating java"
REM in error log, set path jvm.dll.

REM set JVM_PATH="C:\Program Files\Java\jdk1.7.0_51\jre\bin\server\jvm.dll"


REM �T�[�r�X�N���Ɏ��s���A�G���[���O��"failed creating java"�Ƃ������b�Z�[�W��
REM �o�͂����ꍇ�Ajvm.dll�̃p�X���w�肵�Ă��������B

REM set JVM_PATH="C:\Program Files\Java\jdk1.7.0_51\jre\bin\server\jvm.dll"
REM ===========================


bin\daemon\prunsrv_x86.exe //IS//JmxHeapWatchDog --DisplayName="JmxHeapWatchDog" --Description="JmxHeapWatchDog" --Classpath="%CD%;%CD%\conf;%CD%\lib\*" --LogPath="%CD%\logs" --StdOutput=auto --StdError=auto --StartPath="%CD%" --StartClass="jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.main.Main" --StartMode=jvm --StartParams=start --StopClass="jp.gr.java_conf.ka_ka_xyz.jmx_watchdog.main.Main" --StopMode=jvm  --StopParams=stop --Jvm=%JVM_PATH% --JvmOptions=-Djava.util.logging.config.file="%CD%\conf\logging.properties"
pause