resource "aws_db_subnet_group" "rds_snet_group" {
  name       = "rds_snet_group"
  subnet_ids = var.database_subnets

  tags = {
    Name = "rds_snet_group"
  }
}

resource "aws_rds_cluster_parameter_group" "parameter_group" {
  name        = "cluster-rds-${var.rds_engine}-16"
  family      = var.db_parameter_group_family
  description = "RDS cluster parameter group"

  parameter {
    name         = "timezone"
    value        = "America/Sao_Paulo"
    apply_method = "pending-reboot"
  }
}

resource "aws_db_parameter_group" "db_parameter_group" {
  name        = "db-rds-${var.rds_engine}-16"
  family      = var.db_parameter_group_family
  description = "RDS cluster parameter group"
}

resource "aws_rds_cluster" "rds" {
  source_region                         = var.aws_region
  cluster_identifier                    = var.rds_name
  engine                                = var.rds_engine
  engine_version                        = var.rds_engine_version
  db_cluster_parameter_group_name       = aws_rds_cluster_parameter_group.parameter_group.name
  db_instance_parameter_group_name      = aws_db_parameter_group.db_parameter_group.name
  master_username                       = jsondecode(data.aws_secretsmanager_secret_version.secret_user_postgres_atual.secret_string)["username"]
  master_password                       = jsondecode(data.aws_secretsmanager_secret_version.secret_user_postgres_atual.secret_string)["password"]
  allow_major_version_upgrade           = true
  database_name                         = "postgres"
  vpc_security_group_ids                = var.security_group_id
  performance_insights_enabled          = true
  performance_insights_retention_period = 7
  db_subnet_group_name                  = aws_db_subnet_group.rds_snet_group.name
  storage_type                          = var.rds_storage_type
  storage_encrypted                     = true
  deletion_protection                   = false
  backup_retention_period               = 20
  skip_final_snapshot                   = true
  apply_immediately                     = true


  enabled_cloudwatch_logs_exports = ["postgresql", "iam-db-auth-error", "instance"]


  tags = {
    Terraform = "true"
  }
}

resource "aws_rds_cluster_instance" "rds_instances" {
  count                                 = var.rds_instance_count
  identifier                            = "${var.rds_name}-${count.index}"
  cluster_identifier                    = aws_rds_cluster.rds.id
  instance_class                        = var.rds_instance_class
  engine                                = var.rds_engine
  engine_version                        = var.rds_engine_version
  db_subnet_group_name                  = aws_db_subnet_group.rds_snet_group.name
  db_parameter_group_name               = aws_db_parameter_group.db_parameter_group.name
  performance_insights_enabled          = true
  performance_insights_retention_period = 7
  apply_immediately                     = true
  publicly_accessible                   = true

  tags = {
    Terraform = "true"
  }
}
