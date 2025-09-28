resource "random_password" "password_user_postgres" {
  length           = 20
  special          = true
  override_special = "!#*()-_=+[]"
}

resource "aws_secretsmanager_secret" "secret_user_postgres" {
  name = "rds/${var.rds_name}/postgres"
}

resource "aws_secretsmanager_secret_version" "secret_password_postgres" {
  secret_id = aws_secretsmanager_secret.secret_user_postgres.id
  secret_string = jsonencode({
    username = "postgres"
    password = "${random_password.password_user_postgres.result}"
  })
}

data "aws_secretsmanager_secret" "secret_user_postgres" {
  name = aws_secretsmanager_secret.secret_user_postgres.name
}

data "aws_secretsmanager_secret_version" "secret_user_postgres_atual" {
  secret_id = data.aws_secretsmanager_secret.secret_user_postgres.id
}