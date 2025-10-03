output "vpc_id" {
  value       = aws_vpc.main.id
}

output "vpc_computing_subnet_1a_id" {
  value       = aws_subnet.computing_subnet_1a.id
}
output "vpc_computing_subnet_1b_id" {
  value       = aws_subnet.computing_subnet_1b.id
}
output "vpc_computing_subnet_1c_id" {
  value       = aws_subnet.computing_subnet_1c.id
}

output "vpc_database_subnet_1a_id" {
  value       = aws_subnet.database_subnet_1a.id
}
output "vpc_database_subnet_1b_id" {
  value       = aws_subnet.database_subnet_1b.id
}
output "vpc_database_subnet_1c_id" {
  value       = aws_subnet.database_subnet_1c.id
}