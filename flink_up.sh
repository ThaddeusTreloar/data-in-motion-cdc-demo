#!/bin/sh

export TF_VAR_confluent_cloud_api_key=$CONFLUENT_CLOUD_API_KEY
export TF_VAR_confluent_cloud_api_secret=$CONFLUENT_CLOUD_API_SECRET

cd tf

source get_vars.sh

cd ../flink

terraform init
terraform apply -auto-approve

cd ..