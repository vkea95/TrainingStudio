package loop_test

import (
	"testing"
)

func TestWhileLoop(t *testing.T) {
	n := 2
	for n < 5 {
		print(n)
	}
}

func TestIfMultiSec(t *testing.T) {
	if a := 1 == 1; a {
		t.Log("1==1")
	}
}
func TestSwitchMultiCase(t *testing.T) {
	t.Log("start TestSwitchMultiCase")
	for i := 0; i < 5; i++ {
		switch i {
		case 0, 2:
			print("even")
		case 1, 3:
			print("odd")
		default:
			print("It is not 0-3")
		}
	}
}

func testSwitchCaseCondition(t *testing.T) {
	for index := 0; index < 5; index++ {
		switch {
		case index%2 == 0:
			t.Log("Even")
		case index%2 == 1:
			t.Log("Odd")
		default:
			t.Log("unknown")
		}
	}
}
