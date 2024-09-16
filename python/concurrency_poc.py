import threading
import time

def sum_range(start, end):
    return sum(range(start, end + 1))

def worker(start, end, total_sum, lock):
    result = sum_range(start, end)
    with lock:
        total_sum.append(result)

if __name__ == "__main__":
    num_workers = 10
    total_sum = []
    lock = threading.Lock()
    threads = []

    start_time = time.time()
    for i in range(num_workers):
        start = i * 100000 + 1
        end = (i + 1) * 100000
        thread = threading.Thread(target=worker, args=(start, end, total_sum, lock))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    end_time = time.time()
    elapsed_time = end_time - start_time
    print(f"Total sum: {sum(total_sum)}")
    print(f"Total time consumed: {elapsed_time * 1000:.2f} ms")
