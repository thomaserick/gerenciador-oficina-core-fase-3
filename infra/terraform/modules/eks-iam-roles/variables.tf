variable "cluster_role_name" {
  description = "Nome do IAM Role para o cluster EKS"
  type        = string
  default     = "eks-cluster-role"
}

variable "node_role_name" {
  description = "Nome do IAM Role para o node group do EKS"
  type        = string
  default     = "eks-node-group-role"
}
