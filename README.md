# JmxHeapWatchDog
======================
This application run as windows service, accesses regularly to external Java VM (hotsopt) 
which enabled Jmx and save heap usage in csv file.


Sorry for terrible English.

## Install
------

Open bat folder and execute bat/installService_amd64.bat when you use 64 bit Java VM or execute bat/installService_x86.bat when you use 32 bit Java VM.

After execute an batch, "JmxHeapWatchDog" service is created. Then start this service.
If you failed to start up service and logged as '[error] Failed creating java ', 
you should try these procedure.

1. Execute bat/uninstallService_amd64.bat or bat/uninstallService_x86.bat and un-install "JmxHeapWatchDog" service.
2. Open installService_amd64.bat or installService_x86.bat using text editor and set JVM_PATH for full path name of jvm.dll. Read comments of batch file for detai.
3. re-execute installService_amd64.bat or installService_x86.bat

## How To Use
------
#### 1. Enable Jmx on Java VM
Set These java properties when star Java VM (When you use port 17999 as Jmx port).

> -Dcom.sun.management.jmxremote.port=17999
> -Dcom.sun.management.jmxremote.authenticate=false
> -Dcom.sun.management.jmxremote.ssl=false

If you use Apache Tomca, execute tomcatw.exe and set these parameters in "Java Options" on "Java" Tab.

#### 2. Connection Settings For JmxHeapWatchDog
Edit conf/jvms.xml file using text editor and modify settings.

````xml:
<?xml version="1.0" encoding="UTF-8"?>
<JavaVMs  interval="10" retry="5">
  <jvmInfo name="javaVm1" host="localhost" port="17999"
    csv_path="C:\Java"></jvmInfo>
  <jvmInfo name="javaVm2" host="127.0.0.1" port="27999"
    csv_path="C:\Java\BBB"></jvmInfo>
</JavaVMs>
````

* JavaVMs Tag, interval attribute: JmxHeapWatchDog's access interval time (second) to external Java VM.
* JavaVMs Tag retry attribute: max count of retry.
* jvmInfo Tag: multiple tags allowed.
* jvmInfo Tag, name attribute: used as prefix name of csv file.
* jvmInfo Tag, host attribute: host name of external Java VM.
* jvmInfo Tag, port attribute: Jmx port number of external Java VM.
* jvmInfo Tag, csv_path attribute: base path of output csv file.

#### 3. Start Up JmxHeapWatchDog Service
Start JmxHeapWatchDog service.

### About Csv file of JmxHeapWatchDog
Memory usage of external Java VM is output to csv file of bellow file name. 

`<name attribute of jvmInfo tag>_yyyy-MM-dd.csv`

csv header list.

* datetime: datetime of output.
* Total Heap Space init: initial value of total heap space.
* Total Heap Space used: used value of total heap space.
* Total Heap Space committed: committed value of total heap space.
* Total Heap Space max: max value of total heap space.
* Total NonHeap Space init: initial value of total nonheap space.
* Total NonHeap Space used: used value of total nonheap space.
* Total NonHeap Space committed: committed value of total nonheap space.
* Total NonHeap Space max: max value of total nonheap space.
* pending Finalization Count: object count which under pending finalization.


* PS Survivor Space init: initial value of survivor space.
* PS Survivor Space used: used value of survivor space.
* PS Survivor Space committed: committed value of survivor space.
* PS Survivor Space max: max value of survivor space.


* PS Eden Space init: initial value of eden space.
* PS Eden Space used: used value of eden space.
* PS Eden Space committed: committed value of eden space.
* PS Eden Space max: max value of eden space.


* PS Old Gen init: initial value of old generation space.
* PS Old Gen used: used value of old generation space.
* PS Old Gen committed: committed value of old generation space.
* PS Old Gen max: max value of old generation space.


* PS Perm Gen init: initial value of permanent generation space.
* PS Perm Gen used: used value of permanent generation space.
* PS Perm Gen committed: committed value of permanent generation space.
* PS Perm Gen max: max value of permanent generation space.


* Code Cache init: initial value of code cache.
* Code Cache used: used value of code cache.
* Code Cache committed: committed value of code cache.
* Code Cache max: max value of code cache.


* PS MarkSweep count: MarkSweep count of Garbage Collection. 
* PS MarkSweep collectionTime: total time of MarkSweep Garbage Collection.
* PS Scavenge count: Scavenge count of Garbage Collection.
* PS Scavenge collectionTime: total time of Scavenge Garbage Collection. 

see detail about init/used/committed/max in <http://docs.oracle.com/javase/6/api/java/lang/management/MemoryUsage.html> 

License
----------
Licensed under the Apache License, Version 2.0
[Apache]: http://www.apache.org/licenses/LICENSE-2.0