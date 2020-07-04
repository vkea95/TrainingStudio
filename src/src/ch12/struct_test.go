package structure_test

import (
	"fmt"
	"testing"
	"unsafe"
)

// # structure initializing
type Employee struct {
	Id   string
	Name string
	Age  int
}

// define String method for the structure
// when calling this method, the instance will be copied in memory
func (e Employee) String() string {
	fmt.Printf("Address is %x", unsafe.Pointer(&e.Name))
	return fmt.Sprintf("ID: %s-Name: %s-Age:%d", e.Id, e.Name, e.Age)
}

// when calling this method, the instance will not be copied in memory
// func (e *Employee) String() string {
// 	fmt.Printf("Address is %x", unsafe.Pointer(&e.Name))
// 	return fmt.Sprintf("ID: %s-Name: %s-Age:%d", e.Id, e.Name, e.Age)
// }

func TestStructOperations(t *testing.T) {
	e := Employee{"0", "jack", 20}
	fmt.Printf("Address is %x", unsafe.Pointer(&e.Name))
	fmt.Println()
	println(e.String())
	// could call string method with pointer
	e2 := &Employee{"0", "jack", 20}
	println(e2.String()) // . means -> pointer calling
}
