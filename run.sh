cd $HOME/Documents/workspace_vscode/poc-performance-concurrency
#java threads
cd java/
echo native threads
javac JavaConcurrency.java
java JavaConcurrency

#java virtual threads
echo virtual threads
javac --enable-preview --release 21 ConcurrencyJavaVirtualThreads.java
java --enable-preview ConcurrencyJavaVirtualThreads

#go goroutines
cd ../go
pwd
go run main.go

#rust threads
cd ../rust/concurrency_poc
pwd
cargo run

#python threads
cd ../../python
pwd
py concurrency_poc.py