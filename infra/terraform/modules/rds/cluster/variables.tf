variable "rds_name" { default = "projeto" }
variable "rds_instance_count" { default = "1" }
variable "rds_engine" { default = "aurora-postgresql" }
variable "rds_engine_version" { default = "16.6" }
variable "rds_instance_class" { default = "db.t3.medium" }
variable "rds_storage_type" { default = "aurora-iopt1" }
variable "db_parameter_group_family" {
  default = "aurora-postgresql16"
}

variable "vpc_id" {}
variable "vpc_cidr" {}
variable "database_subnets" {}
variable "security_group_id" {}
variable "aws_region" {}

