# TRY Apache Flink
## Download and Start Flink
### Download
1. Download a binary from the [downloads page](http://flink.apache.org/downloads.html)
2. Unpack the downloaded archive
```
$ cd <Download Directory>
$ tar xzf flink-*.tgz
```
### Start

3. Start Flink (Start Job manager on **http://localhost:8081**)
```
$ cd flink-1.3.1        # When you downloaded flink 1.3.1
$ bin/start-local.sh
```


## Try this example for API Requests log count
1. Download this repository and assembly
```
# TERMINAL 1:
$ git clone git@github.com:ryoasu/try-flink.git
$ cd <Download Directory>/try-flink
$ sbt assembly
```
2. Launch the server (send access.log)
```
# TERMINAL 2:
$ cd <Download Directory>/try-flink
$ cat access.log  |  nc -lk 9999
```
3. Submit the Flink program
```
# TERMINAL 1:
$ <Download Directory>/flink-1.3.1/bin/flink run target/scala-2.11/try-flink-assembly-1.0.jar --port 9999
```

The .out file will print the counts at the end of each time window as long as words are floating
```
$ tail -f <Download Directory>/flink-1.3.1/log/flink-*-jobmanager-*.out
```

## Details
Click [here](https://ci.apache.org/projects/flink/flink-docs-release-1.3/quickstart/setup_quickstart.html) for details
