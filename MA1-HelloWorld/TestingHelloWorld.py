#!/usr/bin/env python3
"""
    TestHelloWorld.py

    This is an incredibly wonky way to test your Java via Python
    Crandall is still working out how to automate JUnit properly.
    So, instead of JUnit, he has a small Python script to hack this one.
    Yes, this is very much a hack!
    Do as I say, not as I do on this one.

"""

from sys import exit
from subprocess import Popen, PIPE

if __name__ == "__main__":
    print("Testing the MA1 hello world with a python3 script")
    process = Popen(["java HelloWorld", "", "."], stdout=PIPE, shell=True)
    (output, err) = process.communicate()   # Start external program
                                            #  STDOUT is piped to 'output'
                                            #  STDERR is piped to 'err'
    exit_code = process.wait()              # Wait for process to finish
                                            #  Exit code is '0' for success
    output = str(output, 'utf-8')           # Change output to a "true" string
                                            #  Comes as a byte string
    output = output.strip()                 # Remove whitespace from end output

    print("Hello World output: {0}".format(output))
    output = output.lower()                 # Lower case the output


    if "hello" in output and "world" in output:
        print("Done - Success: 'hello' and 'world' found in output")
        exit(0)
    else:
        print("Done - Fail: 'hello' or 'world' not found in output")
        exit(1)
    print("Should not reach here, so exiting with error")
    exit(1)


