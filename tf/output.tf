output "spring-app-credentials" {
    value = {
        api_key = confluent_api_key.spring-app-api-key.id
        api_secret = confluent_api_key.spring-app-api-key.secret
        bootstrap_servers = confluent_kafka_cluster.example_cluster.bootstrap_endpoint
        schema_registry_url = confluent_schema_registry_cluster.schema-registry.rest_endpoint
        schema_user = confluent_api_key.spring-app-schema-registry-api-key.id
        schema_pass = confluent_api_key.spring-app-schema-registry-api-key.secret
    }

    sensitive = true
}

output "flink-deployment-metadata" {
  value = {
    environment_id = confluent_environment.example_environment.id
    organization_id = data.confluent_organization.organization.id
    service_account_id = confluent_service_account.cluster-manager.id
    compute_pool_id = confluent_flink_compute_pool.core_compute_pool.id
    api_key = confluent_api_key.cluster-manager-flink-api-key.id
    api_secret = confluent_api_key.cluster-manager-flink-api-key.secret
    catalog = confluent_environment.example_environment.display_name
    database = confluent_kafka_cluster.example_cluster.display_name
    rest_endpoint = data.confluent_flink_region.core_compute_pool_region.rest_endpoint
  }

  sensitive = true
}