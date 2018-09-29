#!/usr/bin/env python3
# Author: AnalogMan
# Modified Date: 2018-09-19
# Purpose: Compresses an input file with zLib

import sys, os, zlib, argparse

def main():
    # Arg parser for program options
    parser = argparse.ArgumentParser(description='Compress files with zLib')
    parser.add_argument('filename', help='Path to uncompressed file')

    # Check passed arguments
    args = parser.parse_args()

    # Check if required files exist
    if os.path.isfile(args.filename) == False:
        print('File cannot be found\n')
        return 1
    
    filename = args.filename
    with open(filename, 'rb') as f:
        compressed = zlib.compress(f.read())
    with open(filename + '.z', 'wb') as f:
        f.write(compressed)

if __name__ == "__main__":
    main()