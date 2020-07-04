package first_response_test

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

func FirstResponse() string {
	numOfRunner := 10
	// 通过定义channel 来完成第一个response 就结束整个任务的想法
	// 加了这个numOfRunner中后， goRoutine不用等待接受者将内容拿走
	// 定义这个buffer的大小numOfRunner之后，GoRoutine只要channel还有buffer，
	// 就Goroutine不用等待，而是直接离开，不会造成堵塞
	ch := make(chan string, numOfRunner)
	for i := 0; i < numOfRunner; i++ {
		go func(i int) {
			ret := runTask(i)
			ch <- ret
		}(i)
	}
	// 通过return，来完成我们需要的第一个最快的response
	return <-ch
}

// 第一个返回的任务 就返回就好了 如何实现
func TestFirstResponse(t *testing.T) {
	fmt.Println("Before:", runtime.NumGoroutine())
	fmt.Println(FirstResponse())
	time.Sleep(time.Second * 1)
	// 后面的结果显示，有部分Goroutine被阻塞在channel中，会造成资源耗尽，memory中
	fmt.Println("after:", runtime.NumGoroutine())
}
