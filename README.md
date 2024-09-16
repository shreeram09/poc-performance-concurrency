# poc-performance-concurrency
this demonstrates concurrency performance for 100000 tasks per 10 worker threadpool capacity that calculates sum of numbers from 1 to 1,000,000. Each thread calculates the sum of a portion of the range. The results are aggregated to obtain the total sum.

### prerequisites
- you need to have go, java, python, and rust installed on your system

### execution
```shell
./run.sh
```