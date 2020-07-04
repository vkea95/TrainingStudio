package flexible_reflect_test

import (
	"errors"
	"reflect"
	"testing"
)

func TestDeepEqual(t *testing.T) {
	a := map[int]string{1: "one", 2: "two"}
	b := map[int]string{1: "one", 2: "two"}

	t.Log(reflect.DeepEqual(a, b))

	s1 := []int{1, 2, 3}
	s2 := []int{1, 2, 3}
	s3 := []int{1, 1, 3}
	t.Log("s1 == s2?", reflect.DeepEqual(s1, s2))
	t.Log("s1 == s3?", reflect.DeepEqual(s1, s3))

}

type Employee struct {
	EmployeeID string
	Name       string `format:"normal"`
	Age        int
}

func (e *Employee) UpdateAge(newVal int) {
	e.Age = newVal
}

type Customer struct {
	CustomerID string
	Name       string `format:"normal"`
	Age        int
}

// toto: using interface + map
// pointer type !!!!!!!!!!!!
// st是指针类型，如果要获得他的结构的话，必须使用reflect.TypeOf(st).Elem(  ===》 需要持续理解
func fillBySettings(st interface{}, settings map[string]interface{}) error {
	if reflect.TypeOf(st).Kind() != reflect.Ptr {
		if reflect.TypeOf(st).Elem().Kind() != reflect.Struct {

			return errors.New("The first param should be a pointer to the struct type")
		}
	}
	if settings == nil {
		return errors.New("Setting is nil")

	}
	var (
		field reflect.StructField
		ok    bool
	)
	for k, v := range settings {
		// 到st中寻找是否有k者属性
		if field, ok = (reflect.ValueOf(st)).Elem().Type().FieldByName(k); !ok {
			continue
		}
		if field.Type == reflect.TypeOf(v) {
			vstr := reflect.ValueOf(st)
			// TODO： =====》
			vstr = vstr.Elem()
			vstr.FieldByName(k).Set(reflect.ValueOf(v))
		}
	}
	return nil
}
func TestFillnameAndAge(t *testing.T) {
	settings := map[string]interface{}{"Name": "Mike", "Age": 15}
	e := Employee{}

	if err := fillBySettings(&e, settings); err != nil {
		t.Fatal(err)
	}
	t.Log(e)

	c := new(Customer)

	if err := fillBySettings(c, settings); err != nil {
		t.Fatal(err)
	}
	t.Log(*c)

}
