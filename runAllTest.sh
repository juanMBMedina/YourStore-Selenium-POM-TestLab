#!/bin/bash

mvn clean
mvn verify -Dbrowser=chrome -Dheadless=true -DwithoutCerts=true -Dtest=LoginRunner
mvn verify -Dbrowser=firefox -Dheadless=true -DwithoutCerts=true -Dtest=RegisterRunner
mvn verify -Dbrowser=edge -Dheadless=true -DwithoutCerts=true -Dtest=AddToCartRunner
