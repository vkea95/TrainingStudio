package try_test

import "testing"

//const continuous number setting
const (
	Monday = iota + 1
	Tuesday
)

const (
	Open = 1 << iota
	close
	appending
)

func TestConstTry(t *testing.T) {
	t.Log(Monday, Tuesday)
	t.Log("I'm running")
}
