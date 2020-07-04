package operator_test

import "testing"

func TestCompareArray(t *testing.T) {
	a := [...]int{1, 2, 3, 4}
	b := [...]int{2, 3, 1, 4}
	d := [...]int{1, 2, 3, 4}
	println(a == b)
	println(a == d)
}

// compare array: the length & values must be same then return true
// bit operatiron
// &^

func TestBitClear(t *testing.T) {
	a := 7
	a = a &^ 0
	print(a)
	print("this is in TestBitClear2 ")

}
