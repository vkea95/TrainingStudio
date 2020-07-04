package sync_pool_test

import (
	"fmt"
	"sync"
	"testing"
)

// func TestSyncPool(t *testing.T) {
// 	pool := &sync.Pool{
// 		New: func() interface{} {
// 			fmt.Println("crate a newobject")
// 			return 100
// 		},
// 	}
//
// 	v := pool.Get().(int)
// 	fmt.Println(v)
// 	pool.Put(3)
// 	runtime.GC() //clear the objects in sync.pool
// 	v1, _ := pool.Get().(int)
// 	fmt.Println(v1)
// }

func TestSyncPoolInMultiGroutine(t *testing.T) {
	pool := &sync.Pool{
		New: func() interface{} {
			fmt.Println("crate a newobject")
			return 10
		},
	}

	pool.Put(100)
	pool.Put(100)
	pool.Put(100)

	var wg sync.WaitGroup
	for i := 0; i < 10; i++ {
		wg.Add(1)
		go func(id int) {
			fmt.Println(pool.Get())
			wg.Done()

		}(i)
	}
	wg.Wait()
}

// sync.pool会有锁的问题，要比较生成对象的开销和锁的开销
