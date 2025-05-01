#!/bin/bash
set -e  # Stop execution if any command fails

echo "Starting Selenium Grid..."
docker-compose -f docker-compose.selenium.yml up -d selenium-hub chrome-node firefox-node edge-node

echo "Running mvn clean verify..."
mvn clean verify

# Define global environment variables
export USE_GRID=true
export HEADLESS=true
export INSECURE_CERTS=true

# Define browser and test suite combinations in an associative array
declare -A TESTS=(
  ["chrome"]="LoginRunner"
  ["firefox"]="RegisterRunner"
  ["edge"]="AddToCartRunner"
)

# Function to run tests
run_test() {
  local browser=$1
  local suite=$2
  export BROWSER=$browser
  export SUITE=$suite
  echo "Running tests with browser: $BROWSER and suite: $SUITE"
  mvn verify -Dtest=$SUITE
}

# Execute tests for each combination
for browser in "${!TESTS[@]}"; do
  run_test "$browser" "${TESTS[$browser]}"
done

echo "Stopping Selenium Grid..."
docker-compose -f docker-compose.selenium.yml down

echo "Script completed successfully."
