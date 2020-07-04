package interface_test

import (
	"fmt"
	"math/rand"
	"testing"
	"time"
)

// func: return mul values
// parameters value ref
// func could be the value of variable
// func could be the parameter & reurn value
func returnMultiValues() (int, int) {
	// bug: forget to add (int,int)
	return rand.Intn(10), rand.Intn(20)
}

func timeSpend(inner func(op int) int) func(op int) int {
	return func(n int) int {
		start := time.Now()
		ret := inner(n)
		fmt.Println("time spent:", time.Since(start).Seconds())
		return ret
	}
}
func slowFunc(op int) int {
	time.Sleep(time.Second * 1)
	return op
}

func TestFn(t *testing.T) {
	a, _ := returnMultiValues()
	t.Log(a)
	tsSF := timeSpend(slowFunc)
	t.Log(tsSF(10))
}

// // TODO:
// Multiple parameters
func Sum(ops ...int) int {
	ret := 0
	for _, op := range ops {
		ret += op
	}
	// , strs ...string
	// for _, str := range strs {
	// 	fmt.Print(str)
	// }

	return ret
}

func TestVarSum(t *testing.T) {
	t.Log(Sum(1, 2, 3, 3, 4))
	t.Log(Sum(1, 2, 3, 3))
	fmt.Print(Sum(1, 2, 3, 3, 4))
}

// defer function

func TestDefer(t *testing.T) {
	// anonymous
	// defer function could be run beforing exting
	// similar with try exception
	// relase reources or lock
	// defer func() {
	// 	fmt.Println("Clear resources")
	// }()
	defer Clear()
	fmt.Println("started")
	// panic means exception
	panic("Fatal error") // defer coudl be running
}
func Clear() {
	fmt.Println("Clear resources")
}
