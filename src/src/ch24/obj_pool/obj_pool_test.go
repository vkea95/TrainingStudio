package objPool_test

import (
	"errors"
	"fmt"
	"testing"
	"time"
)

type GoProgrammer struct {
}
type ResuableObj struct {
	// Reusable object
}

type ObjPool struct {
	// buffer the resuable objects
	bufferedChan chan *ResuableObj
}

func createObjectPool(size int) *ObjPool {
	objectPool := ObjPool{}
	objectPool.bufferedChan = make(chan *ResuableObj, size)
	for i := 0; i < size; i++ {
		objectPool.bufferedChan <- &ResuableObj{}
	}
	return &objectPool
}

func (p *ObjPool) GetObj(timeout time.Duration) (*ResuableObj, error) {
	select {
	case ret := <-p.bufferedChan:
		return ret, nil
		// TODO: timeout error
	case <-time.After(timeout):
		return nil, errors.New("time out")
	}
}
func (p *ObjPool) ReleastObj(obj *ResuableObj) error {
	select {
	case p.bufferedChan <- obj:
		return nil
		// TODO: avoid overflow, otherwise block will happen
	default:
		return errors.New("overflow")
	}
}

func TestObjPool(t *testing.T) {
	pool := createObjectPool(10)
	// 此处会导致overflow，因为chanel本身是满的嘛。
	// if err := pool.ReleastObj(&ResuableObj{}); err != nil {
	// 	fmt.Println(err)
	// 	t.Error(err)
	// }
	for i := 0; i < 12; i++ {

		if v, err := pool.GetObj(time.Second * 1); err != nil {
			t.Error(err)
			fmt.Println(err)
		} else {
			fmt.Printf("%T\n", v)
			// 此处如果没有放回处理的话，就会在后面的getObj操作中，导致timeout错误
			if err := pool.ReleastObj(v); err != nil {
				fmt.Println(err)
				t.Error(err)
			}
		}
	}
}

// TODO 并不是意味着一定要使用这样的channel来实现，因为还要考虑channel 上lock的问题
// 此外可以将ObjPool的channel对象type定义为interface{},这样就可以放任意数据类型了，
// 但是pull的时候，就需要判断对象的类型来进行进一步实现了
