package empty_interface

import (
	"fmt"
	"testing"
)

// interface best practice
//1.  single or small interface
// 2.many small interfaces => big interfaces
// 3. only depends on the necessary interface
// empty interface
func DoSthing(p interface{}) {
	// if i, ok := p.(int); ok {
	// 	fmt.Println("Integer", i)
	// } else if i, ok := p.(string); ok {
	// 	fmt.Println("string", i)
	// } else {
	// 	fmt.Println("Unknown")
	// }
	switch i := p.(type) {
	case int:
		fmt.Println("Integer", i)
	case string:
		fmt.Println("string", i)
	default:
		fmt.Println("unknown type")
	}
}

func TestDoSthing(t *testing.T) {
	DoSthing(1)
	DoSthing("PPPP")
	DoSthing(1.2)
}
