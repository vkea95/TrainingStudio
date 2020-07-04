package pipefilter

import "errors"

var negateFormatWrongMsg = errors.New("wrong format for input data")

type NegateFilter struct {
}

func CreateNegateFilter() *NegateFilter {
	// todo rember to add {}
	return &NegateFilter{}
}

// 使用request这个空的interface 来表示可以接收一切的数据个是
func (zf *NegateFilter) Process(data Request) (Response, error) {
	parts, ok := data.([]int)
	if !ok {
		// 使用error new出来的对象，来存储和返回错误
		return nil, negateFormatWrongMsg
	}
	ret := []int{}
	for _, part := range parts {
		// append 是对切片 slice 进行操作
		ret = append(ret, -1*part)
	}
	//  不要忘记返回nil error
	return ret, nil
}
