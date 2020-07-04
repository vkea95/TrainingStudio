package testing

import (
	"fmt"
	"testing"
)

func TestSquare(t *testing.T) {
	inputs := [...]int{1, 2, 3}
	expected := [...]int{1, 4, 0}
	for i := 0; i < len(inputs); i++ {
		ret := square(inputs[i])
		if ret != expected[i] {
			t.Errorf("input is %d, the expected is %d, the actual %d", inputs[i], expected[i], ret)
		}
	}
}
func
func TestErroInCode(t *testing.T) {
	fmt.Println("start Error")
	t.Error("Error")
	fmt.Println("End Error")
}

func TestFatalInCode(t *testing.T) {
	t.Log("Fatal error starts")
	fmt.Println("start Fatal")
	t.Fatal("Error")
	fmt.Println("End Fatal")
}

func square(op int) int {
	return op*op + 1
}
