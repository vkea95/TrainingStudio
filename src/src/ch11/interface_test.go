package interface_test

import (
	"fmt"
	"testing"
)

type Programmer interface {
	WriteHelloWorld() string
}

type GoProgrammer struct {
}

// todo: meaning of g
// duck type
func (g *GoProgrammer) WriteHelloWorld() string {
	return "fmt.Println(\"Hello world\")"
}

func TestClient(t *testing.T) {
	var p Programmer

	p = new(GoProgrammer)
	fmt.Println(p.WriteHelloWorld())
}
