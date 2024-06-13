terraform {
  required_providers {
    confluent = {
      source  = "confluentinc/confluent"
      version = "1.65.0"
    }
  }
}

## Infrastructure

provider "confluent" {
  cloud_api_key    = var.confluent_cloud_api_key
  cloud_api_secret = var.confluent_cloud_api_secret
}


resource "confluent_environment" "example_environment" {
    display_name = "ExampleEnvironment"
}

resource "confluent_kafka_cluster" "example_cluster" {
    display_name = "basic_kafka_cluster"
    availability = "SINGLE_ZONE"
    cloud        = var.cloud_provider
    region       = var.deployment_region
    basic {}

    environment {
        id = confluent_environment.example_environment.id
    }

    depends_on = [ confluent_environment.example_environment ]
}

## Service account

resource "confluent_service_account" "cluster-manager" {
  display_name = "cluster-manager-sa"
  description  = "Service Account for managing cluster settings"

  depends_on = [ confluent_kafka_cluster.example_cluster ]
}

resource "confluent_role_binding" "cluster-manager-rbac" {
    principal   = "User:${confluent_service_account.cluster-manager.id}"
    role_name   = "CloudClusterAdmin"
    crn_pattern = confluent_kafka_cluster.example_cluster.rbac_crn

    depends_on = [ confluent_service_account.cluster-manager ]
}

resource "confluent_api_key" "cluster-manager-api-key" {
  display_name = "cluster-manager-api-key"
  description  = "Kafka API Key that is owned by 'cluster-manager' service account"

  owner {
    id          = confluent_service_account.cluster-manager.id
    api_version = confluent_service_account.cluster-manager.api_version
    kind        = confluent_service_account.cluster-manager.kind
  }

  managed_resource {
    id          = confluent_kafka_cluster.example_cluster.id
    api_version = confluent_kafka_cluster.example_cluster.api_version
    kind        = confluent_kafka_cluster.example_cluster.kind

    environment {
      id = confluent_environment.example_environment.id
    }
  }

  depends_on = [ confluent_service_account.cluster-manager ]
}

resource "confluent_service_account" "spring-app" {
  display_name = "spring-app-sa"
  description  = "Service Account for spring app"

  depends_on = [ confluent_kafka_cluster.example_cluster ]
}

resource "confluent_api_key" "spring-app-api-key" {
  display_name = "spring-app-api-key"
  description  = "Kafka API Key that is owned by 'spring-app' service account"

  owner {
    id          = confluent_service_account.spring-app.id
    api_version = confluent_service_account.spring-app.api_version
    kind        = confluent_service_account.spring-app.kind
  }

  managed_resource {
    id          = confluent_kafka_cluster.example_cluster.id
    api_version = confluent_kafka_cluster.example_cluster.api_version
    kind        = confluent_kafka_cluster.example_cluster.kind

    environment {
      id = confluent_environment.example_environment.id
    }
  }

  depends_on = [ confluent_service_account.spring-app ]
}

## ACLs

resource "confluent_kafka_acl" "spring-app-topics-acl" {
  kafka_cluster {
    id = confluent_kafka_cluster.example_cluster.id
  }
  resource_type = "TOPIC"
  resource_name = "*"
  pattern_type  = "LITERAL"
  principal     = format("User:%s", confluent_service_account.spring-app.id)
  host          = "*"
  operation     = "ALL"
  permission    = "ALLOW"

  rest_endpoint      = confluent_kafka_cluster.example_cluster.rest_endpoint

  credentials {
    key = confluent_api_key.cluster-manager-api-key.id
    secret = confluent_api_key.cluster-manager-api-key.secret
  }

  depends_on = [ confluent_api_key.cluster-manager-api-key, confluent_kafka_cluster.example_cluster, confluent_service_account.spring-app ]
}

resource "confluent_kafka_acl" "spring-app-read-group-acl" {
  kafka_cluster {
    id = confluent_kafka_cluster.example_cluster.id
  }
  resource_type = "GROUP"
  resource_name = "*"
  pattern_type  = "LITERAL"
  principal     = format("User:%s", confluent_service_account.spring-app.id)
  host          = "*"
  operation     = "READ"
  permission    = "ALLOW"

  rest_endpoint      = confluent_kafka_cluster.example_cluster.rest_endpoint

  credentials {
    key = confluent_api_key.cluster-manager-api-key.id
    secret = confluent_api_key.cluster-manager-api-key.secret
  }

  depends_on = [ confluent_api_key.cluster-manager-api-key, confluent_kafka_cluster.example_cluster, confluent_service_account.spring-app ]
}
resource "confluent_kafka_acl" "spring-app-describe-group-acl" {
  kafka_cluster {
    id = confluent_kafka_cluster.example_cluster.id
  }
  resource_type = "GROUP"
  resource_name = "*"
  pattern_type  = "LITERAL"
  principal     = format("User:%s", confluent_service_account.spring-app.id)
  host          = "*"
  operation     = "DESCRIBE"
  permission    = "ALLOW"

  rest_endpoint      = confluent_kafka_cluster.example_cluster.rest_endpoint

  credentials {
    key = confluent_api_key.cluster-manager-api-key.id
    secret = confluent_api_key.cluster-manager-api-key.secret
  }

  depends_on = [ confluent_api_key.cluster-manager-api-key, confluent_kafka_cluster.example_cluster, confluent_service_account.spring-app ]
}

resource "confluent_kafka_acl" "spring-app-txid-describe-acl" {
  kafka_cluster {
    id = confluent_kafka_cluster.example_cluster.id
  }
  resource_type = "TRANSACTIONAL_ID"
  resource_name = "*"
  pattern_type  = "LITERAL"
  principal     = format("User:%s", confluent_service_account.spring-app.id)
  host          = "*"
  operation     = "DESCRIBE"
  permission    = "ALLOW"

  rest_endpoint      = confluent_kafka_cluster.example_cluster.rest_endpoint

  credentials {
    key = confluent_api_key.cluster-manager-api-key.id
    secret = confluent_api_key.cluster-manager-api-key.secret
  }

  depends_on = [ confluent_api_key.cluster-manager-api-key, confluent_kafka_cluster.example_cluster, confluent_service_account.spring-app ]
}

resource "confluent_kafka_acl" "spring-app-txid-write-acl" {
  kafka_cluster {
    id = confluent_kafka_cluster.example_cluster.id
  }
  resource_type = "TRANSACTIONAL_ID"
  resource_name = "*"
  pattern_type  = "LITERAL"
  principal     = format("User:%s", confluent_service_account.spring-app.id)
  host          = "*"
  operation     = "WRITE"
  permission    = "ALLOW"

  rest_endpoint      = confluent_kafka_cluster.example_cluster.rest_endpoint

  credentials {
    key = confluent_api_key.cluster-manager-api-key.id
    secret = confluent_api_key.cluster-manager-api-key.secret
  }

  depends_on = [ confluent_api_key.cluster-manager-api-key, confluent_kafka_cluster.example_cluster, confluent_service_account.spring-app ]
}

## Topics

resource "confluent_kafka_topic" "contacts-topic" {
  kafka_cluster {
    id = confluent_kafka_cluster.example_cluster.id
  }
  topic_name         = "contacts"
  rest_endpoint      = confluent_kafka_cluster.example_cluster.rest_endpoint
  partitions_count   = 1

  credentials {
    key = confluent_api_key.cluster-manager-api-key.id
    secret = confluent_api_key.cluster-manager-api-key.secret
  }

  depends_on = [ confluent_service_account.cluster-manager, confluent_api_key.cluster-manager-api-key, confluent_kafka_cluster.example_cluster ]
}

resource "confluent_kafka_topic" "customers-topic" {
  kafka_cluster {
    id = confluent_kafka_cluster.example_cluster.id
  }
  topic_name         = "customers"
  rest_endpoint      = confluent_kafka_cluster.example_cluster.rest_endpoint
  partitions_count   = 1

  credentials {
    key = confluent_api_key.cluster-manager-api-key.id
    secret = confluent_api_key.cluster-manager-api-key.secret
  }

  depends_on = [ confluent_service_account.cluster-manager, confluent_api_key.cluster-manager-api-key, confluent_kafka_cluster.example_cluster ]
}

resource "confluent_kafka_topic" "addresses-topic" {
  kafka_cluster {
    id = confluent_kafka_cluster.example_cluster.id
  }
  topic_name         = "addresses"
  rest_endpoint      = confluent_kafka_cluster.example_cluster.rest_endpoint
  partitions_count   = 1

  credentials {
    key = confluent_api_key.cluster-manager-api-key.id
    secret = confluent_api_key.cluster-manager-api-key.secret
  }

  depends_on = [ confluent_service_account.cluster-manager, confluent_api_key.cluster-manager-api-key, confluent_kafka_cluster.example_cluster ]
}

resource "confluent_kafka_topic" "shipments-topic" {
  kafka_cluster {
    id = confluent_kafka_cluster.example_cluster.id
  }
  topic_name         = "shipments"
  rest_endpoint      = confluent_kafka_cluster.example_cluster.rest_endpoint
  partitions_count   = 1

  credentials {
    key = confluent_api_key.cluster-manager-api-key.id
    secret = confluent_api_key.cluster-manager-api-key.secret
  }

  depends_on = [ confluent_service_account.cluster-manager, confluent_api_key.cluster-manager-api-key, confluent_kafka_cluster.example_cluster ]
}

resource "confluent_kafka_topic" "orders-topic" {
  kafka_cluster {
    id = confluent_kafka_cluster.example_cluster.id
  }
  topic_name         = "orders"
  rest_endpoint      = confluent_kafka_cluster.example_cluster.rest_endpoint
  partitions_count   = 1

  credentials {
    key = confluent_api_key.cluster-manager-api-key.id
    secret = confluent_api_key.cluster-manager-api-key.secret
  }

  depends_on = [ confluent_service_account.cluster-manager, confluent_api_key.cluster-manager-api-key, confluent_kafka_cluster.example_cluster ]
}

resource "confluent_kafka_topic" "order-line-items-topic" {
  kafka_cluster {
    id = confluent_kafka_cluster.example_cluster.id
  }
  topic_name         = "order-line-items"
  rest_endpoint      = confluent_kafka_cluster.example_cluster.rest_endpoint
  partitions_count   = 1

  credentials {
    key = confluent_api_key.cluster-manager-api-key.id
    secret = confluent_api_key.cluster-manager-api-key.secret
  }

  depends_on = [ confluent_service_account.cluster-manager, confluent_api_key.cluster-manager-api-key, confluent_kafka_cluster.example_cluster ]
}

resource "confluent_kafka_topic" "products-topic" {
  kafka_cluster {
    id = confluent_kafka_cluster.example_cluster.id
  }
  topic_name         = "products"
  rest_endpoint      = confluent_kafka_cluster.example_cluster.rest_endpoint
  partitions_count   = 1

  credentials {
    key = confluent_api_key.cluster-manager-api-key.id
    secret = confluent_api_key.cluster-manager-api-key.secret
  }

  depends_on = [ confluent_service_account.cluster-manager, confluent_api_key.cluster-manager-api-key, confluent_kafka_cluster.example_cluster ]
}

# Schema Registry

resource "confluent_schema_registry_cluster" "schema-registry" {
  # !!!
  # NOTE THAT THIS RESORUCE WILL BE DEPRECATED IN THE NEXT MAJOR VERSION (2.0)
  # !!!
  package = "ESSENTIALS"

  environment {
    id = confluent_environment.example_environment.id
  }

  region {
    id = var.schema_registry_region
  }
}

resource "confluent_api_key" "spring-app-schema-registry-api-key" {
  display_name = "spring-app-schema-registry-api-key"
  description  = "Schema Registry API Key that is owned by 'spring-app' service account"
  owner {
    id          = confluent_service_account.spring-app.id
    api_version = confluent_service_account.spring-app.api_version
    kind        = confluent_service_account.spring-app.kind
  }

  managed_resource {
    id          = confluent_schema_registry_cluster.schema-registry.id
    api_version = confluent_schema_registry_cluster.schema-registry.api_version
    kind        = confluent_schema_registry_cluster.schema-registry.kind

    environment {
      id = confluent_environment.example_environment.id
    }
  }
}

## Spring App Schema Registry RBAC

resource "confluent_role_binding" "spring-app-sr-read-rb" {
  principal = "User:${confluent_service_account.spring-app.id}"
  role_name = "DeveloperRead"
  crn_pattern = "${confluent_schema_registry_cluster.schema-registry.resource_name}/subject=*"
}

resource "confluent_role_binding" "spring-app-sr-write-rb" {
  principal = "User:${confluent_service_account.spring-app.id}"
  role_name = "DeveloperWrite"
  crn_pattern = "${confluent_schema_registry_cluster.schema-registry.resource_name}/subject=*"
}

resource "confluent_role_binding" "cluster-manager-sr-read-rb" {
  principal = "User:${confluent_service_account.cluster-manager.id}"
  role_name = "DeveloperRead"
  crn_pattern = "${confluent_schema_registry_cluster.schema-registry.resource_name}/subject=*"
}

resource "confluent_role_binding" "cluster-manager-sr-write-rb" {
  principal = "User:${confluent_service_account.cluster-manager.id}"
  role_name = "DeveloperWrite"
  crn_pattern = "${confluent_schema_registry_cluster.schema-registry.resource_name}/subject=*"
}

# Flink

resource "confluent_flink_compute_pool" "core_compute_pool" {
  display_name     = "core_compute_pool"
  cloud            = var.cloud_provider
  region           = var.deployment_region
  max_cfu          = 5
  environment {
    id = confluent_environment.example_environment.id
  }
}

data "confluent_flink_region" "core_compute_pool_region" {
  cloud   = var.cloud_provider
  region  = var.deployment_region
}

data "confluent_organization" "organization" {}


resource "confluent_role_binding" "cluster-manager-flink-rbac" {
    principal   = "User:${confluent_service_account.cluster-manager.id}"
    role_name   = "FlinkDeveloper"
    crn_pattern = confluent_environment.example_environment.resource_name

    depends_on = [ confluent_service_account.cluster-manager ]
}

resource "confluent_api_key" "cluster-manager-flink-api-key" {
  display_name = "cluster-manager-flink-api-key"
  description  = "Flink API Key that is owned by 'cluster-manager' service account"
  owner {
    id          = confluent_service_account.cluster-manager.id
    api_version = confluent_service_account.cluster-manager.api_version
    kind        = confluent_service_account.cluster-manager.kind
  }

  managed_resource {
    id          = data.confluent_flink_region.core_compute_pool_region.id
    api_version = data.confluent_flink_region.core_compute_pool_region.api_version
    kind        = data.confluent_flink_region.core_compute_pool_region.kind

    environment {
      id = confluent_environment.example_environment.id
    }
  }
}


resource "confluent_flink_statement" "enriched_orders_table" {
  statement = file("${path.module}/sql/create-enriched-orders.fql")

  environment {
    id = confluent_environment.example_environment.id
  }

  organization {
    id = data.confluent_organization.organization.id
  }

  compute_pool {
    id = confluent_flink_compute_pool.core_compute_pool.id
  }

  principal {
    id = confluent_service_account.cluster-manager.id
  }
  credentials {
    key    = confluent_api_key.cluster-manager-flink-api-key.id
    secret = confluent_api_key.cluster-manager-flink-api-key.secret
  }

  properties = {
    "sql.current-catalog"  = confluent_environment.example_environment.display_name
    "sql.current-database" = confluent_kafka_cluster.example_cluster.display_name
  }

  rest_endpoint = data.confluent_flink_region.core_compute_pool_region.rest_endpoint

  depends_on = [ 
    confluent_role_binding.cluster-manager-flink-rbac,
    confluent_role_binding.cluster-manager-sr-read-rb, 
    confluent_role_binding.cluster-manager-sr-write-rb
  ]
}

resource "confluent_flink_statement" "enriched_customers_table" {
  statement = file("${path.module}/sql/create-enriched-customers.fql")

  environment {
    id = confluent_environment.example_environment.id
  }

  organization {
    id = data.confluent_organization.organization.id
  }

  principal {
    id = confluent_service_account.cluster-manager.id
  }

  compute_pool {
    id = confluent_flink_compute_pool.core_compute_pool.id
  }
  credentials {
    key    = confluent_api_key.cluster-manager-flink-api-key.id
    secret = confluent_api_key.cluster-manager-flink-api-key.secret
  }

  properties = {
    "sql.current-catalog"  = confluent_environment.example_environment.display_name
    "sql.current-database" = confluent_kafka_cluster.example_cluster.display_name
  }

  rest_endpoint = data.confluent_flink_region.core_compute_pool_region.rest_endpoint

  depends_on = [ 
    confluent_role_binding.cluster-manager-flink-rbac, 
    confluent_role_binding.cluster-manager-sr-read-rb, 
    confluent_role_binding.cluster-manager-sr-write-rb 
  ]
}

resource "confluent_flink_statement" "order_events_table" {
  statement = file("${path.module}/sql/create-order-events.fql")

  environment {
    id = confluent_environment.example_environment.id
  }

  organization {
    id = data.confluent_organization.organization.id
  }

  principal {
    id = confluent_service_account.cluster-manager.id
  }

  compute_pool {
    id = confluent_flink_compute_pool.core_compute_pool.id
  }

  credentials {
    key    = confluent_api_key.cluster-manager-flink-api-key.id
    secret = confluent_api_key.cluster-manager-flink-api-key.secret
  }

  properties = {
    "sql.current-catalog"  = confluent_environment.example_environment.display_name
    "sql.current-database" = confluent_kafka_cluster.example_cluster.display_name
  }

  rest_endpoint = data.confluent_flink_region.core_compute_pool_region.rest_endpoint

  depends_on = [ 
    confluent_role_binding.cluster-manager-flink-rbac, 
    confluent_role_binding.cluster-manager-sr-read-rb, 
    confluent_role_binding.cluster-manager-sr-write-rb 
  ]
}