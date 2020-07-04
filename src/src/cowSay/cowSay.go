package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
)

func main() {
	info, _ := os.Stdin.Stat()

	fmt.Println(info.Mode())
	fmt.Println(os.ModeCharDevice)
	if info.Mode()&os.ModeCharDevice != 0 {
		fmt.Println("The command is intended to work with pipes.")
		fmt.Println("Usage: fortune | gocowsay")
		return
	}

	reader := bufio.NewReader(os.Stdin)
	var output []rune

	for {
		input, _, err := reader.ReadRune()
		if err != nil && err == io.EOF {
			break
		}
		output = append(output, input)
	}
	var cow = `         \  ^__^
            \ (oo)\_______
  	    (__)\       )\/\
  	        ||----w |
  	        ||     ||
  		`
	fmt.Println(cow)
	for j := 0; j < len(output); j++ {
		fmt.Printf("%c", output[j])
	}
}
