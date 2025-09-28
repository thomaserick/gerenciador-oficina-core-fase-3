## EKS CLUSTER ##

resource "aws_eks_cluster" "cluster" {
  name     = var.cluster_name
  role_arn = "arn:aws:iam::905418307317:role/c175509a4540172l11767539t1w905418-LabEksClusterRole-6l5ecKzAcqbF"
  version  = "1.33"

  vpc_config {
    subnet_ids              = var.vpc_subnets
    security_group_ids      = [aws_security_group.eks_extra.id]
    endpoint_private_access = "true"
    endpoint_public_access  = "true"
  }

  access_config {
    authentication_mode                         = "API"
    bootstrap_cluster_creator_admin_permissions = true
  }

  bootstrap_self_managed_addons = false

  zonal_shift_config {
    enabled = true
  }

  compute_config {
    enabled       = true
    node_pools    = ["general-purpose", "system"]
    node_role_arn = "arn:aws:iam::905418307317:role/c175509a4540172l11767539t1w905418307-LabEksNodeRole-bltw0M491dDz"
  }

  kubernetes_network_config {
    elastic_load_balancing {
      enabled = true
    }
  }

  storage_config {
    block_storage {
      enabled = true
    }
  }

  depends_on = [ aws_security_group.eks_extra ]
}

## EKS SG Adicional ##
resource "aws_security_group" "eks_extra" {
  name        = "eks-${var.cluster_name}-sg"
  description = "Extra SG para o cluster EKS"
  vpc_id      = var.vpc_id

  ingress {
    description      = "Permitir VPC acessar API server"
    from_port        = 443
    to_port          = 443
    protocol         = "tcp"
    cidr_blocks      = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "eks-${var.cluster_name}-sg"
  }
}