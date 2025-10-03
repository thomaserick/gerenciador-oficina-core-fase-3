## IAM ROLES Config ##
module "eks_iam_roles" {
  source = "../modules/eks-iam-roles"
}

## VPC Config ##
module "vpc" {
  source   = "../modules/vpc"
  vpc_cidr = var.vpc_cidr
  vpc_name = var.project_name
}

## EKS Config ##
module "eks" {
  source        = "../modules/eks"
  cluster_name  = var.project_name
  role_arn      = module.eks_iam_roles.cluster_role_arn
  node_role_arn = module.eks_iam_roles.node_role_arn
  vpc_id        = module.vpc.vpc_id
  vpc_subnets   = [module.vpc.vpc_computing_subnet_1a_id, module.vpc.vpc_computing_subnet_1b_id, module.vpc.vpc_computing_subnet_1c_id]

  depends_on = [module.eks_iam_roles]
}

## RDS Config ##
module "rds" {
  source            = "../modules/rds/cluster"
  rds_name          = var.project_name
  vpc_id            = module.vpc.vpc_id
  vpc_cidr          = var.vpc_cidr
  database_subnets  = [module.vpc.vpc_database_subnet_1a_id, module.vpc.vpc_database_subnet_1b_id, module.vpc.vpc_database_subnet_1c_id]
  security_group_id = [module.sg_rds.security_group_id]
  aws_region        = var.aws_region

  depends_on = [module.sg_rds]
}

## SG Config ##
module "sg_rds" {
  source   = "../modules/security_group/sg_rds"
  vpc_cidr = var.vpc_cidr
  vpc_id   = module.vpc.vpc_id
}
