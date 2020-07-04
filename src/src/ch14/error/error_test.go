package error_test

import (
	"errors"
	"fmt"
	"testing"
)

// predefine error
var LessThanTwoError = errors.New("ne should >= 2]")
var LargerThanHundredError = errors.New("ne should  < 1000")

func GetFibonacci(n int) ([]int, error) {
	if n < 0 {
		return nil, LessThanTwoError
	}
	if n > 100 {
		return nil, LargerThanHundredError
	}
	fibList := []int{1, 1}
	for i := 2; i < n; i++ {
		fibList = append(fibList, fibList[i-2]+fibList[i-1])
	}
	return fibList, nil
}

func TestGetFibonacci(t *testing.T) {
	if fv, err := GetFibonacci(-10); err != nil {
		if err == LessThanTwoError {
			fmt.Println("It's less")

		}
		fmt.Print("error in GetFibonacci")
	} else {
		fmt.Print(fv)
	}

}
