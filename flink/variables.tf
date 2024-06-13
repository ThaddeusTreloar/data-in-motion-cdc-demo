variable "confluent_cloud_api_key" {
    description = "Confluent Cloud API Key"
    type = string
}

variable "confluent_cloud_api_secret" {
    description = "Confluent Cloud API secret"
    type = string
}

variable "environment_id" {
  description = "Target confluent environment id"
    type = string
}

variable "organization_id" {
  description = "Target confluent organisation id"
    type = string
}

variable "service_account_id" {
  description = "Service account id"
    type = string
}

variable "compute_pool_id" {
  description = "Target confluent compute pool id"
    type = string
}

variable "api_key" {
  description = "Target api key"
    type = string
}

variable "api_secret" {
  description = "Target api secret"
    type = string
}

variable "catalog" {
  description = "Target flink catalog"
    type = string
}

variable "database" {
  description = "Target flink database"
    type = string
}

variable "rest_endpoint" {
  description = "Target flink endpoint"
    type = string
}