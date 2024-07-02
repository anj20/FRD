#!/bin/bash

# Define your Docker Hub username
DOCKER_HUB_USERNAME=anj20

# List of services
services=(
    "user-service"
    "product-service"
    "order-service"
    "api-gateway"
    "service-registry"
    "config-server"
)

# Push each service image
for service in "${services[@]}"; do
    echo "Pushing ${service} image to Docker Hub..."
    docker push ${DOCKER_HUB_USERNAME}/${service}:latest

    # Check if the push was successful
    if [ $? -eq 0 ]; then
        echo "${service} image pushed successfully."
    else
        echo "Failed to push ${service} image."
        exit 1
    fi
done

echo "All images pushed successfully
