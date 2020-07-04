package remote_test

import (
	"fmt"
	"testing"

	cm "github.com/easierway/concurrent_map"
)

func TestConcurrentmap(t *testing.T) {
	m := cm.CreateConcurrentMap(99)
	m.Set(cm.StrKey("key"), 10.9999)
	fmt.Println(m.Get(cm.StrKey("key")))
}

// dependency manamange tool
// godep
// glide ==> much newer
// dep
// brew install glide
// glide init
// glide install => find the package in vendor directory
