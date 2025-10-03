## EKS Variables ##
variable "cluster_name" { default = "projeto" }

## GLOBAL ENVS ##

## VPC ##
variable "vpc_subnets" {}
variable "vpc_id" {}

## Role ##
variable "role_arn" {
  default = "arn:aws:iam::905418307317:role/c175509a4540172l11767539t1w905418-LabEksClusterRole-6l5ecKzAcqbF"
}
variable "node_role_arn" {
  default = "arn:aws:iam::905418307317:role/c175509a4540172l11767539t1w905418307-LabEksNodeRole-bltw0M491dDz"
}

