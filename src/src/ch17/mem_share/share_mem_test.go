package mem_sahre

import (
	"fmt"
	"sync"
	"testing"
	"time"
)

// unthread safe
func TestCounter(t *testing.T) {
	counter := 0
	for i := 0; i < 5000; i++ {
		go func() {
			counter++
		}()
	}

	time.Sleep(1 * time.Second)
	fmt.Println("Counter = ", counter)
}

func TestCounterThreadSafe(t *testing.T) {
	var mut sync.Mutex
	counter := 0
	for i := 0; i < 5000; i++ {
		go func() {
			defer func() {
				mut.Unlock()
			}()
			mut.Lock()
			counter++
		}()
	}
	// if comment out sleep then the result is incorrect
	time.Sleep(1 * time.Second)
	fmt.Println("Counter = ", counter)
}
func TestCounterWaitGroup(t *testing.T) {
	var mut sync.Mutex
	var wg sync.WaitGroup
	counter := 0
	for i := 0; i < 5000; i++ {
		go func() {
			wg.Add(1)
			defer func() {
				mut.Unlock()
			}()
			mut.Lock()
			counter++
			wg.Done()
		}()
	}

	wg.Wait()
	// if comment out sleep then the result is incorrect
	// time.Sleep(1 * time.Second)
	fmt.Println("Counter = ", counter)
}
