package client

import (
	"ch15/series"
	"fmt"
	"testing"
)

func TestPackage(t *testing.T) {
	fmt.Println(series.GetFib(12))
}

// refer outside function, the first letter of its name must capitalized
// init method
//  import by depend order
//  every package has multiple init function
// every source file could have multiple init functions
// init must be ran before running main function

// call remote TestPackage
