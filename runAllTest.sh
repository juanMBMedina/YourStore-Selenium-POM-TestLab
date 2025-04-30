#!/bin/bash

mvn clean
# Define env vars by default true:
expor USE_GRID=true
export HEADLESS=true
export INSECURE_CERTS=true
#Define Browser and TestSuite:
export BROWSER=chrome
export SUITE=LoginRunner
mvn verify -Dtest=$SUITE
#Define Browser and TestSuite:
export BROWSER=firefox
export SUITE=RegisterRunner
mvn verify -Dtest=$SUITE
#Define Browser and TestSuite:
export BROWSER=edge
export SUITE=AddToCartRunner
mvn verify -Dtest=$SUITE
