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

resource "confluent_flink_statement" "enriched_customer_data" {
  
  statement = file("${path.module}/sql/insert-enriched-customer-data.fql")

  environment {
    id = var.environment_id
  }

  organization {
    id = var.organization_id
  }

  principal {
    id = var.service_account_id
  }

  compute_pool {
    id = var.compute_pool_id
  }

  credentials {
    key    = var.api_key
    secret = var.api_secret
  }

  properties = {
    "sql.current-catalog"  = var.catalog
    "sql.current-database" = var.database
  }

  rest_endpoint = var.rest_endpoint
}

resource "confluent_flink_statement" "enrich_orders" {
  statement = file("${path.module}/sql/insert-enriched-orders.fql")

  environment {
    id = var.environment_id
  }

  organization {
    id = var.organization_id
  }

  principal {
    id = var.service_account_id
  }

  compute_pool {
    id = var.compute_pool_id
  }

  credentials {
    key    = var.api_key
    secret = var.api_secret
  }

  properties = {
    "sql.current-catalog"  = var.catalog
    "sql.current-database" = var.database
  }

  rest_endpoint = var.rest_endpoint
}

resource "confluent_flink_statement" "order_events" {
  statement = file("${path.module}/sql/insert-order-events.fql")

  environment {
    id = var.environment_id
  }

  organization {
    id = var.organization_id
  }

  principal {
    id = var.service_account_id
  }

  compute_pool {
    id = var.compute_pool_id
  }

  credentials {
    key    = var.api_key
    secret = var.api_secret
  }

  properties = {
    "sql.current-catalog"  = var.catalog
    "sql.current-database" = var.database
  }

  rest_endpoint = var.rest_endpoint

  depends_on = [ 
    confluent_flink_statement.enriched_customer_data,
    confluent_flink_statement.enrich_orders
  ]
}