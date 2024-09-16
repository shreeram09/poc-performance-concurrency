package main

import (
	"fmt"
	"sync"
	"time"
)

func main() {
	numWorkers := 10
	var totalSum int64 = 0
	var wg sync.WaitGroup
	var mu sync.Mutex

	start := time.Now()
	wg.Add(numWorkers)
	for i := 0; i < numWorkers; i++ {
		go func(worker int) {
			defer wg.Done()
			start := worker*100000 + 1
			end := (worker + 1) * 100000
			sum := sumRange(start, end)

			mu.Lock()
			totalSum += sum
			mu.Unlock()
		}(i)
	}

	wg.Wait()
	elapsed := time.Since(start)
	fmt.Println("Total sum:", totalSum)
	fmt.Printf("Total time consumed: %s\n", elapsed)
}

func sumRange(start, end int) int64 {
	var sum int64 = 0
	for i := start; i <= end; i++ {
		sum += int64(i)
	}
	return sum
}
