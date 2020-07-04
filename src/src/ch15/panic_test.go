package panic_test

import (
	"errors"
	"fmt"
	"testing"
)

// panic vis os.exit
// no defer
// no call stack information

func TestPanicExit(t *testing.T) {
	defer func() {
		if err := recover(); err != nil {
			fmt.Println("recovered from ", err)
			// it's not a good way to only log the information
			// 容易形成僵尸服务进程，导致health check失效
			// Let it crash =>best way
		}
		// fmt.Println("defer")
	}()
	fmt.Println("Start")
	panic(errors.New("Something wrong !"))
	// os.exit(-1)
}

// recover
// catch all throwable exception
