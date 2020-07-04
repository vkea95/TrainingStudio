package pipefilter

// 空接口可以接受各种数据
type Request interface{}

type Response interface{}

type Filter interface {
	Process(data Request) (Response, error)
}
