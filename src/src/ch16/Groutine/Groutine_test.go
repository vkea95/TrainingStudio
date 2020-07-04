package goroutine_test

import (
	"fmt"
	"testing"
	"time"
)

// Thread vs Groutine
// jdk size default: 1M
// Groutine size default: 2k

func TestGroutine(t *testing.T) {
	for index := 0; index < 10; index++ {
		go func(i int) {
			fmt.Println(i)
		}(index) //pass value not reference
	}
	time.Sleep(time.Millisecond * 50)
}
