package extension_test

import (
	"fmt"
	"testing"
)

type Pet struct {
}

func (p *Pet) Speak() {
	fmt.Println("Pet speak")
}

func (p *Pet) SpeakTo(host string) {
	p.Speak()
	fmt.Println(" ", host)
}

type Dog struct {
	Pet
}

func (d *Dog) Speak() {
	fmt.Println("Dog speak")
}

func (d *Dog) SpeakTo(host string) {
	fmt.Println("Dog's ", host)
}

func TestDog(t *testing.T) {
	dog := new(Dog)
	dog.SpeakTo("Jian")
}
// Go doesn't support 继承

// empty can display any type of data
// empty interface_test
//v, ok :=p.(int) //ok= ture, then change successfully
