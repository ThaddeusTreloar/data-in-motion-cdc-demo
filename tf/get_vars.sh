#!/bin/sh

export SPRING_KAFKA_API_KEY=$(terraform output -json spring-app-credentials | jq -r .api_key)
export SPRING_KAFKA_API_SECRET=$(terraform output -json spring-app-credentials | jq -r .api_secret)
export SPRING_KAFKA_BOOTSTRAP_SERVERS=$(terraform output -json spring-app-credentials | jq -r .bootstrap_servers)
export SPRING_KAFKA_SCHEMA_REGISTRY_URL=$(terraform output -json spring-app-credentials | jq -r .schema_registry_url)
export SPRING_KAFKA_SCHEMA_USER=$(terraform output -json spring-app-credentials | jq -r .schema_user)
export SPRING_KAFKA_SCHEMA_PASS=$(terraform output -json spring-app-credentials | jq -r .schema_pass)

export TF_VAR_environment_id=$(terraform output -json flink-deployment-metadata | jq -r .environment_id)
export TF_VAR_organization_id=$(terraform output -json flink-deployment-metadata | jq -r .organization_id)
export TF_VAR_service_account_id=$(terraform output -json flink-deployment-metadata | jq -r .service_account_id)
export TF_VAR_compute_pool_id=$(terraform output -json flink-deployment-metadata | jq -r .compute_pool_id)
export TF_VAR_api_key=$(terraform output -json flink-deployment-metadata | jq -r .api_key)
export TF_VAR_api_secret=$(terraform output -json flink-deployment-metadata | jq -r .api_secret)
export TF_VAR_catalog=$(terraform output -json flink-deployment-metadata | jq -r .catalog)
export TF_VAR_database=$(terraform output -json flink-deployment-metadata | jq -r .database)
export TF_VAR_rest_endpoint=$(terraform output -json flink-deployment-metadata | jq -r .rest_endpoint)