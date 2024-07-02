#!/bin/bash

# Define your Docker Hub username
DOCKER_HUB_USERNAME=anj20

# List of services and their respective build contexts
services=(
    "./UserService/UserService"
    "./ProductService/ProductService"
    "./OrderService/OrderService"
    "./ApiGateway/ApiGateway"
    "./ServiceRegistry/ServiceRegistry"
    "./ConfigServer/ConfigServer"
)

# Service names corresponding to the order in services array
service_names=(
    "user-service"
    "product-service"
    "order-service"
    "api-gateway"
    "service-registry"
    "config-server"
)

# Check if lengths of services and service_names are equal
if [ ${#services[@]} -ne ${#service_names[@]} ]; then
    echo "Error: Number of services and service names do not match."
    exit 1
fi

# Loop through the services to build, tag, and push each one
for (( i=0; i<${#services[@]}; i++ )); do
    build_context="${services[$i]}"
    service="${service_names[$i]}"

    echo "Building ${service} image..."
    docker build -t ${service}:latest ${build_context}

    if [ $? -eq 0 ]; then
        echo "${service} image built successfully."
    else
        echo "Failed to build ${service} image."
        exit 1
    fi

    echo "Tagging ${service} image..."
    docker tag ${service}:latest ${DOCKER_HUB_USERNAME}/${service}:latest

    # Check if the tagging was successful
    if [ $? -eq 0 ]; then
        echo "${service} image tagged successfully."
    else
        echo "Failed to tag ${service} image."
        exit 1
    fi
done

echo "All images built and tagged successfully."
