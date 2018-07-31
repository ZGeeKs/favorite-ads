#!/bin/bash

set -ex

REGISTRY=$1
IMAGE_NAME=$2
DOCKER_USER=$3
DOCKER_PASS=$4
TAG=$5

docker build -t ${REGISTRY}/${IMAGE_NAME}:$TAG .
docker login -u $DOCKER_USER -p $DOCKER_PASS         
docker tag ${REGISTRY}/${IMAGE_NAME}:$TAG ${REGISTRY}/${IMAGE_NAME}:latest
docker push ${REGISTRY}/${IMAGE_NAME}
docker tag ${REGISTRY}/${IMAGE_NAME}:latest ${REGISTRY}/${IMAGE_NAME}:$TAG
docker push ${REGISTRY}/${IMAGE_NAME}