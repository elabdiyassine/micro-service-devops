#!/bin/bash

# List of services
services=(  "customer-service" "account-service" "discovery-service"   "config-service" "gateway-service" )
 
# Loop through each service
for service in "${services[@]}"
do
    echo "Building $service..."
    cd "$service" || { echo "Failed to navigate to $service directory"; exit 1; }
    mvn clean package -DskipTests || { echo "Failed to build $service"; exit 1; }
    cd ..
    echo "Generated JAR for $service"
done

echo "All services built successfully."


# 1 - mvn clean package -DskipTests
