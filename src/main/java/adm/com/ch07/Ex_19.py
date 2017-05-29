#!/usr/bin/env python

import sys, random


def rng04():
    return random.randint(0, 4)


# filtered call to rng04
# return 0 1 2 3
def rng03():
    while True:
        i = rng04()
        if i < 4:
            return i


# filtered call to rng04
# return 0 1
# use rng04 instead of rng03 to make analysis easier
def rng01():
    while True:
        i = rng04()
        if i < 4:
            return i % 2


# generate uniform numbers in 0 1 2 3 4 5 6 7
def rng07():
    i = rng03()
    j = rng01()
    return i * 2 + j


if __name__ == "__main__":
    for i in range(10000):
        print rng07()
    sys.exit()
