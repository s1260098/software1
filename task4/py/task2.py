#!/usr/bin/env python3


import argparse, sys, time
from collections import Counter
from itertools import combinations

def get_input():
    parser = argparse.ArgumentParser()
    parser.add_argument('filename', nargs='?')
    args = parser.parse_args()
    if args.filename:
        string = open(args.filename).readlines()
    elif not sys.stdin.isatty():
        string = sys.stdin.readlines()
    else:
        parser.print_help()
        sys.exit()
    return string

def get_products(in_lines):
    count = Counter()
    

    for line in in_lines:
        prods = line.split()[1:]
        for pair in combinations(prods, 2):
            count.update([' '.join(sorted(pair))])

    sorted_count = [(v[0], v[1]) for v in sorted(count.items(), key=lambda kv: (-kv[1], kv[0]))]
    return sorted_count


if __name__ == "__main__":
    lines = get_input()
    tic = time.perf_counter()
    

    num_cust = int(lines[0])
    products = get_products(lines[1: 1 + num_cust])
    num_q = int(lines[1 + num_cust])


for i in range (num_q):
    start, end = [int(x) for x in lines[2 + num_cust + i].split()]
    for j in range(start - 1, end):
        print(products[j][1], products[j][0])
    print()

    toc = time.perf_counter()
    print(f"processing time is {toc-tic:0.4f} seconds")





