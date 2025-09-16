#!/bin/bash

echo imageName - $1
echo severity - $2
echo exitCode - $3

trivy image $1 \
    --severity $2 \
    --exit-code $3 \
    --format json -o trivy-iamge-$2-report.json