# JmxHeapWatchDog
======================
Jmxが有効なJavaVMへ定期的にアクセスし、ヒープ使用状況をCSV形式で保存します。
Windowsサービスとして起動します。


## インストール
------

64bit JavaVmがインストールされている環境では、installService_amd64.batを実行します。
32bit JavaVMがインストールされている環境では、installService_x86.batを実行します。

実行後、"JmxHeapWatchDog"サービスが作成されるので、Windowsの「コンピュータの管理」から
サービスを起動してください。
また、サービスの起動に失敗し、ログに'[error] Failed creating java 'と出力される場合には
以下の手順を試してください。

1. uninstallService_amd64.batまたはuninstallService_x86.batを実行してサービスをアンインストール
2. テキストエディタでinstallService_amd64.batまたはinstallService_x86.batを開き、環境変数JVM_PATHの値にjvm.dllのフルパス名を入れます。詳細についてはバッチコメント参照
3. 再びinstallService_amd64.batまたはinstallService_x86.batを実行します。

## 使い方
------
#### 監視対象JavaVMでJmxを有効にする
監視対象となるJavaVMの起動時に、下記プロパティを指定します。
ポート番号17999をJmxポートとして使用する場合の例。

> -Dcom.sun.management.jmxremote.port=17999
> -Dcom.sun.management.jmxremote.authenticate=false
> -Dcom.sun.management.jmxremote.ssl=false

Apache Tomcaの場合、tomcatw.exeを起動して"Java"タブの"Java Options"欄に上記設定を追加後、再起動します。

#### JmxHeapWatchingDog側の接続設定
conf/jvms.xmlファイルを編集して接続設定を行います。

````xml:
<?xml version="1.0" encoding="UTF-8"?>
<JavaVMs  interval="10" retry="5">
  <jvmInfo name="javaVm1" host="localhost" port="17999"
    csv_path="C:\Java"></jvmInfo>
  <jvmInfo name="javaVm2" host="127.0.0.1" port="27999"
    csv_path="C:\Java\BBB"></jvmInfo>
</JavaVMs>
````

* JavaVMsタグ interval属性: JmxHeapWatchingDogが監視対象へ接続する際の接続間隔時間（秒）を指定します
* JavaVMsタグ retry属性: 接続試行回数の上限値を指定します
* jvmInfoタグ: 複数指定可能
* jvmInfoタグ name属性: csvファイルのprefix名として使用されます
* jvmInfoタグ host属性: 監視対象のホスト名
* jvmInfoタグ port属性: 監視対象のJMXポート番号
* jvmInfoタグ  csv_path属性: csvファイルの出力先フォルダ

#### JmxHeapWatchingDogサービスを起動
JmxHeapWatchDogサービスを起動します。

### JmxHeapWatchingDogで出力するCSV内容の内容
Jmx経由で取得したメモリ使用状況は下記ファイルに出力されます。

`<jcmInfoタグのname属性>_yyyy-MM-dd.csv`

このファイルへ出力される情報は下記のとおりです。

* datetime: 行出力時の日付時刻
* Total Heap Space init: ヒープ領域全体のinit値
* Total Heap Space used: ヒープ領域全体のused値
* Total Heap Space committed: ヒープ領域全体のcommitted値
* Total Heap Space max: ヒープ領域全体のmax値
* Total NonHeap Space init: 非ヒープ領域全体のinit値
* Total NonHeap Space used: 非ヒープ領域全体のused値
* Total NonHeap Space committed: 非ヒープ領域全体のcommitted値
* Total NonHeap Space max: ヒープ領域全体のmax値
* pending Finalization Count: finalize処理がペンディング状態となっているオブジェクト数


* PS Survivor Space init: Survivor領域全体のinit値
* PS Survivor Space used: Survivor領域全体のused値
* PS Survivor Space committed: Survivor領域全体のcommitted値
* PS Survivor Space max: Survivor領域全体のmax値


* PS Eden Space init: Eden領域全体のinit値
* PS Eden Space used: Eden領域全体のused値
* PS Eden Space committed: Eden領域全体のcommitted値
* PS Eden Space max: Eden領域全体のmax値


* PS Old Gen init: Old Gen領域全体のinit値
* PS Old Gen used: Old Gen領域全体のused値
* PS Old Gen committed: Old Gen領域全体のcommitted値
* PS Old Gen max: Old Gen領域全体のmax値


* PS Perm Gen init: Perm Gen領域全体のinit値
* PS Perm Gen used: Perm Gen領域全体のused値
* PS Perm Gen committed: Perm Gen領域全体のcommitted値
* PS Perm Gen max: Perm Gen領域全体のmax値


* Code Cache init: Code Cache領域全体のinit値
* Code Cache used: Code Cache領域全体のused値
* Code Cache committed: Code Cache領域全体のcommitted値
* Code Cache max: Code Cache領域全体のmax値


* PS MarkSweep count: Garbage CollectorのMarkSweep回数
* PS MarkSweep collectionTime: Garbage Collector総時間
* PS Scavenge count: Garbage CollectorのScavenge回数
* PS Scavenge collectionTime: Garbage CollectorのScavenge総時間

init/used/committed/maxについては <http://docs.oracle.com/javase/jp/6/api/java/lang/management/MemoryUsage.html> 参照。

ライセンス
----------
Licensed under the Apache License, Version 2.0
[Apache]: http://www.apache.org/licenses/LICENSE-2.0