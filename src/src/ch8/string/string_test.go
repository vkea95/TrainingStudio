package string_test

import "testing"

// string: read only byte slice, len returns the number of byte
// the byte array of string could contain any data

func TestString(t *testing.T) {
	var s string
	t.Log(s)
	s = "hello"
	t.Log(len(s))
	// s[1] = '3' it's only read type
	s = "\xE4\xB8\xA5"

	c := []rune(s)
	print(len(c))
	t.Logf("zhong unicode %x", c[0])
	print(s)
}

// strings package
// stringconv package
func TestStringToRunce(t *testing.T) {
	s := "美国华盛顿州西雅图市"
	for _, c := range s {
		t.Logf("%[1]c %[1]d", c)
	}

}
