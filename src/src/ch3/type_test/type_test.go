package type_test

import (
	"fmt"
	"math"
	"testing"
)

func TestImplicit(t *testing.T) {

	var a int32 = 1
	// var b int64 = 2
	fmt.Print(math.MaxInt16)
	aPtr := &a
	t.Log(a, aPtr)
	t.Logf("%T %T", a, aPtr)
}

func TestString(t *testing.T) {
	var s string
	t.Error("*" + s)
}
