package pipefilter

import (
	"errors"
	"strings"
)

var SplitFIlterWrongFormatError = errors.New("input data should be string")

type SplitFilter struct {
	delimiter string
}

// TODO 此处使用的是别名？ ==》总之就是用来实例化SplitFilter的
func NewSplitFilter(delimiter string) *SplitFilter {
	return &SplitFilter{delimiter}

}

// 在此处定义一个叫做process的方法
func (sf *SplitFilter) Process(data Request) (Response, error) {
	str, ok := data.(string) // check data format & type, 轻质数据类型转换
	if !ok {
		return nil, SplitFIlterWrongFormatError
	}
	parts := strings.Split(str, sf.delimiter)
	return parts, nil
}
