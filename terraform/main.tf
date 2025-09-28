## VPC Config ##
module "vpc" {
    source = "./resources/vpc"
    vpc_cidr = var.vpc_cidr
    vpc_name = "projeto"
}

## EKS Config ##
module "eks" {
  source                    = "./resources/eks"
  vpc_id                    = module.vpc.vpc_id
  vpc_subnets               = [module.vpc.vpc_computing_subnet_1a_id, module.vpc.vpc_computing_subnet_1b_id, module.vpc.vpc_computing_subnet_1c_id]
}

## RDS Config ##
module "rds" {
  source            = "./resources/rds"
  vpc_id            = module.vpc.vpc_id
  vpc_cidr          = var.vpc_cidr
  database_subnets  = [module.vpc.vpc_database_subnet_1a_id, module.vpc.vpc_database_subnet_1b_id, module.vpc.vpc_database_subnet_1c_id]
  security_group_id = [module.sg_rds.security_group_id]
  aws_region        = var.aws_region
  aws_account_id    = var.aws_account_id

  depends_on = [module.sg_rds]
}

## SG Config ##
module "sg_rds" {
  source   = "./resources/security_group/sg_rds"
  vpc_cidr = var.vpc_cidr
  vpc_id   = module.vpc.vpc_id
}