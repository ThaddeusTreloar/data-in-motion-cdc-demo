# Data In Motion Tour - Deloitte - Jun 2024

This repository contains the code and configuration for the Deloitte Data In Motion Tour, June 2024.

## Dependencies

There are several tools you will need to run this demo.

### Docker

You will also need to ensure that you have a docker instance running on your machine.  
A guide for Docker setup can be found [here](https://docs.docker.com/desktop).

You will also need compose if you are running the start script with docker, instructions for install and setup can be found [here](https://docs.docker.com/compose/install/)

### Confluent Cli

For some of the configuration, you will need the confluent CLI, instructions can be found [here](https://docs.confluent.io/confluent-cli/current/install.html)

### Maven

All of the java binaries are built with maven, instructions for installation and setup can be found [here](https://maven.apache.org/install.html)

### Terraform

There infrastructure lifecycle is managed through terraform, instructions for installation and setup can be found [here](https://developer.hashicorp.com/terraform/tutorials/aws-get-started/install-cli)

## Getting a CC cloud key
  
In order to run this demo you will need a Confluent Cloud cloud api-key. This can be generated from the `confluent` cli app using the following commands.

```
$ confluent login
$ confluent api-key create --resource cloud
```

## Setting up terraform
  
```
export TF_VAR_confluent_cloud_api_key={{your cc key}}
export TF_VAR_confluent_cloud_api_secret={{your cc secret}}
```

By default the terraform script uses the `AWS` cloud provider in the `ap-southeast-2` region,  
and the `sgreg-4` (`ap-southeast-2` equivalent) region for schema registry.
If you want to run this from a different region you can set the following variables in your  
environment before running the `start.sh` script.

```
# Cloud provider
export TF_VAR_cloud_provider={{ AWS | AZURE | GCP }}

# Deployment region
export TF_VAR_deployment_region={{your deployment region}}

# Deployment region
export TF_VAR_schema_registry_region={{your schema registry deployment region}}
```

A list of cloud providers for each region can be found [here](https://docs.confluent.io/cloud/current/clusters/regions.html#cloud-providers-and-regions).

There isn't an online resource for schema registry regions, but you can list and find your region by use the following command:
```
confluent schema-registry region list
```

## Spinning up the deployment

Once you have completed the setup, run the following infrastructure startup command:
```
cc_up.sh
```
This will initialise the Confluent Cloud environment, along with all dependencies.  

## Running the demo

The demo needs to be started in two phases.

### Running the faker 'cdc' producer

This will start the faker producer, which will generate fake data for the `cdc` topic.
It will also register the schemas for the `cdc` topics in the schema registry, required for flink to process the data.

Run the command:
```
faker_up.sh
```

Once you start to see order decisions being made, you can start the flink job.
An example order decision will appear in the logs as such:
```
[main] INFO org.cdc_demo.data_faker.biz.Runner - customer_id: 2, decision: CREATE_ORDER
[main] INFO org.cdc_demo.data_faker.biz.Runner - Created Order Line Item, line_id: 221, body: {"product_id": 3, "order_id": 77, "quantity": 3, "line_total": 201.99}
[main] INFO org.cdc_demo.data_faker.biz.Runner - Created Order Line Item, line_id: 222, body: {"product_id": 23, "order_id": 77, "quantity": 2, "line_total": 38.32}
[main] INFO org.cdc_demo.data_faker.biz.Runner - Created Order Line Item, line_id: 223, body: {"product_id": 39, "order_id": 77, "quantity": 4, "line_total": 316.16}
[main] INFO org.cdc_demo.data_faker.biz.Runner - Created Order Line Item, line_id: 224, body: {"product_id": 57, "order_id": 77, "quantity": 6, "line_total": 568.14}
[main] INFO org.cdc_demo.data_faker.biz.Runner - Created Order, order_id: 77, body: {"customer_id": 2, "shipping_address": 4, "order_date": "2024-06-13", "order_status": "PENDING", "line_item_count": 4, "updated_on": "2024-06-13T03:01:35.123Z", "statement_action": "INSERT"}
```

The faker app will run for the duration of the demo.
Now, open up a second terminal.

### Running the Flink jobs

IN THE SEPARATE TERMINAL (do not stop the faker app), you can start the flink jobs.
The flink jobs can be started by running the following command:
```
flink_up.sh
```

This will start the flink jobs, which will process the `cdc` topic and generate business events.

## Stopping the demo

To stop the demo, stop the faker app by pressing ctrl+c in the terminal running the app. Then, you can run the following command:
```
down.sh
```

This will clean up the Confluent environment, and stop the flink jobs.

Note that sometimes there is a race condition where RBAC roles will be deleted before the environment is fully cleaned up.
If this happens you can delete the environment manually through the Confluent Cloud UI.

Also, be sure to check if any service accounts are still present by running:
```
confluent iam service-account list
```

and then deleting any accounts created by the demo (cluster-manager-sa, spring-app-sa):
```
confluent iam service-account delete <service account id (eg. sa-vv2k95)>
```