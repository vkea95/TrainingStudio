package design.com;

/**
 * 1.功能:
 * 根据输入的实时速度,显示当前汽车所在的档位信息:park,1,2,3...
 * 2.已知条件:
 * A.档位:每个档位所对应的速度区间是不一样的,每个档位都有最大速度和最小速度
 * B.档位显示屏:
 * 超过当前档位的最大速度,自动显示高档位
 * 低于当前档位的最小速度,自动显示低档位
 * C.汽车:
 * 汽车可以有多款汽车,每款汽车的档位的速度区间可以不同于其他款汽车
 * 3.示例代码:
 * 本段代码为面向过程的方式完成的,各档位间的耦合度很高,只适用于特定车型.
 * 4.要求:
 * 请以面向对象的方式完成代码,降低各档位间的耦合度,使之可以适用于更多款车辆
 * Created by tclresearchamerica on 6/15/16.
 * Shift car simulation
 * <p>
 * speed	shift
 * x=0		park
 * 0<x<5		1
 * 5<x<10	2
 * 10<x<30	3
 * 30<x<55	4
 * x>55		5
 */
public class test {
    public static void main(String[] args) {
        Car myCar = new Car();
        for (int i = 0; i < 80;i++) {
            myCar.inputSpeed(i);
        }
        for (int i =80;i>=0;i--)
            myCar.inputSpeed(i);
    }
}

class Car {
    public Car() {

    }

    public void inputSpeed(int speed) {
        int shift = changeSpeed(speed);
        switch (shift) {
            case 0:
                System.out.println("park");
                break;
            case 1:
                System.out.print("Gear 1");
                break;
            case 2:
                System.out.print("Gear 2");
                break;
            case 3:
                System.out.print("Gear 3");
                break;
            case 4:
                System.out.print("Gear 4");
                break;
            default:
                System.out.print("Gear 5");
        }

    }

    private int changeSpeed(int speed) {
        if (speed == 0) {
            return 0;
        } else {
            if (speed > 0 && speed < 5) {
                return 1;
            } else {
                if (speed > 5 && speed < 10) {
                    return 2;
                } else {
                    if (speed > 10 && speed < 30) {
                        return 3;
                    } else {
                        if (speed > 30 && speed < 55) {
                            return 4;
                        } else {
                            if (speed > 55) {
                                return 5;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
}
