use std::sync::{Arc, Mutex};
use std::thread;
use std::time::Instant;

fn main() {
    let num_workers = 10;
    let total_sum = Arc::new(Mutex::new(0u64));
    let mut handles = vec![];

    let start = Instant::now();
    for worker in 0..num_workers {
        let total_sum = Arc::clone(&total_sum);
        let handle = thread::spawn(move || {
            let start = worker * 100000 + 1;
            let end = (worker + 1) * 100000;
            let sum = sum_range(start, end);

            let mut total_sum = total_sum.lock().unwrap();
            *total_sum += sum;
        });
        handles.push(handle);
    }

    for handle in handles {
        handle.join().unwrap();
    }

    let total_sum = total_sum.lock().unwrap();
    let duration = start.elapsed();
    println!("Total sum: {}", *total_sum);
    println!("Total time consumed: {:?}", duration);
}

fn sum_range(start: usize, end: usize) -> u64 {
    let mut sum = 0;
    for i in start..=end {
        sum += i as u64;
    }
    sum
}
