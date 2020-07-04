package until_all_done

import (
	"fmt"
	"runtime"
	"testing"
	"time"
)

func runTask(i int) string {
	time.Sleep(18 * time.Millisecond)
	return fmt.Sprintf("result from %d", i)
}

func AllResponse() string {
	numOfRunner := 10
	ch := make(chan string, numOfRunner)
	for i := 0; i < numOfRunner; i++ {
		go func(i int) {
			ret := runTask(i)
			ch <- ret
		}(i)
	}

	finalRest := ""
	for j := 0; j < numOfRunner; j++ {
		// 循环足够的次数，将channel中的数据取出来，也就是说，如果取不到的话，就会被block住
		finalRest += <-ch + "\n"
	}
	return finalRest
}

// 第一个返回的任务 就返回就好了 如何实现
func TestFirstResponse(t *testing.T) {
	fmt.Println("Before:", runtime.NumGoroutine())
	fmt.Println(AllResponse())
	time.Sleep(time.Second * 1)
	// 后面的结果显示，有部分Goroutine被阻塞在channel中，会造成资源耗尽，memory中
	fmt.Println("after:", runtime.NumGoroutine())
}
