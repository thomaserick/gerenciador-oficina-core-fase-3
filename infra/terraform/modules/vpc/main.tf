locals {
  vpc_octets = split(".", var.vpc_cidr)
  vpc_prefix = "${local.vpc_octets[0]}.${local.vpc_octets[1]}"
}

#### VPC ####
resource "aws_vpc" "main" {
  cidr_block = var.vpc_cidr
  enable_dns_hostnames = true
  enable_dns_support = true
  tags = {
    Name = "vpc-${var.vpc_name}"
    Terraform = "true"
  }
}

#### SUBNETS ####
resource "aws_subnet" "computing_subnet_1a" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "${local.vpc_prefix}.0.0/24"
  availability_zone = "us-east-1a"
  map_public_ip_on_launch = true

  tags = {
    Name = "sn-${var.vpc_name}-computing-1a"
    "kubernetes.io/role/internal-elb" = 1
    "karpenter.sh/discovery" = "projeto"
    "kubernetes.io/role/cni" = 1
  }
}

resource "aws_subnet" "computing_subnet_1b" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "${local.vpc_prefix}.1.0/24"
  availability_zone = "us-east-1b"
  map_public_ip_on_launch = true

  tags = {
    Name = "sn-${var.vpc_name}-computing-1b"
    "kubernetes.io/role/internal-elb" = 1
    "karpenter.sh/discovery" = "projeto"
    "kubernetes.io/role/cni" = 1
  }
}

resource "aws_subnet" "computing_subnet_1c" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "${local.vpc_prefix}.2.0/24"
  availability_zone = "us-east-1c"
  map_public_ip_on_launch = true

  tags = {
    Name = "sn-${var.vpc_name}-computing-1c"
    "kubernetes.io/role/internal-elb" = 1
    "karpenter.sh/discovery" = "projeto"
    "kubernetes.io/role/cni" = 1
  }
}

resource "aws_subnet" "database_subnet_1a" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "${local.vpc_prefix}.3.0/24"
  availability_zone = "us-east-1a"

  tags = {
    Name = "sn-${var.vpc_name}-database-1a"
  }
}

resource "aws_subnet" "database_subnet_1b" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "${local.vpc_prefix}.4.0/24"
  availability_zone = "us-east-1b"

  tags = {
    Name = "sn-${var.vpc_name}-database-1b"
  }
}

resource "aws_subnet" "database_subnet_1c" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "${local.vpc_prefix}.5.0/24"
  availability_zone = "us-east-1c"

  tags = {
    Name = "sn-${var.vpc_name}-database-1c"
  }
}

resource "aws_subnet" "dmz_subnet_1a" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "${local.vpc_prefix}.6.0/24"
  availability_zone = "us-east-1a"

  tags = {
    Name = "sn-${var.vpc_name}-dmz-1a"
  }
}

resource "aws_subnet" "dmz_subnet_1b" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "${local.vpc_prefix}.7.0/24"
  availability_zone = "us-east-1b"

  tags = {
    Name = "sn-${var.vpc_name}-dmz-1b"
  }
}

resource "aws_subnet" "dmz_subnet_1c" {
  vpc_id     = aws_vpc.main.id
  cidr_block = "${local.vpc_prefix}.8.0/24"
  availability_zone = "us-east-1c"

  tags = {
    Name = "sn-${var.vpc_name}-dmz-1c"
  }
}

#### INTERNET GATEWAY ####
resource "aws_internet_gateway" "gw" {
  vpc_id = aws_vpc.main.id

  tags = {
    Name = "ig-vpc-${var.vpc_name}"
  }
}

#### ROUTE TABLE ####
resource "aws_route_table" "computing_rtb" {
  vpc_id = aws_vpc.main.id

  tags = {
    Name = "rtb-${var.vpc_name}-computing"
  }
}
resource "aws_route_table_association" "computing-1a" {
  subnet_id      = aws_subnet.computing_subnet_1a.id
  route_table_id = aws_route_table.computing_rtb.id
  depends_on = [ aws_route_table.computing_rtb ]
}
resource "aws_route_table_association" "computing-1b" {
  subnet_id      = aws_subnet.computing_subnet_1b.id
  route_table_id = aws_route_table.computing_rtb.id
  depends_on = [ aws_route_table.computing_rtb ]
}
resource "aws_route_table_association" "computing-1c" {
  subnet_id      = aws_subnet.computing_subnet_1c.id
  route_table_id = aws_route_table.computing_rtb.id
  depends_on = [ aws_route_table.computing_rtb ]
}

resource "aws_route" "nat_gateway_computing" {
  route_table_id            = aws_route_table.computing_rtb.id
  destination_cidr_block    = "0.0.0.0/0"
  gateway_id = aws_internet_gateway.gw.id
  depends_on = [ aws_route_table.dmz_rtb ]
}

resource "aws_route_table" "database_rtb" {
  vpc_id = aws_vpc.main.id

  tags = {
    Name = "rtb-${var.vpc_name}-database"
  }
}

resource "aws_route_table_association" "database-1a" {
  subnet_id      = aws_subnet.database_subnet_1a.id
  route_table_id = aws_route_table.database_rtb.id
  depends_on = [ aws_route_table.database_rtb ]
}
resource "aws_route_table_association" "database-1b" {
  subnet_id      = aws_subnet.database_subnet_1b.id
  route_table_id = aws_route_table.database_rtb.id
  depends_on = [ aws_route_table.database_rtb ]
}
resource "aws_route_table_association" "database-1c" {
  subnet_id      = aws_subnet.database_subnet_1c.id
  route_table_id = aws_route_table.database_rtb.id
  depends_on = [ aws_route_table.database_rtb ]
}

resource "aws_route" "nat_gateway_database" {
  route_table_id            = aws_route_table.database_rtb.id
  destination_cidr_block    = "0.0.0.0/0"
  gateway_id = aws_internet_gateway.gw.id
  depends_on = [ aws_route_table.dmz_rtb ]
}

resource "aws_route_table" "dmz_rtb" {
  vpc_id = aws_vpc.main.id

  tags = {
    Name = "rtb-${var.vpc_name}-dmz"
  }

  depends_on = [ aws_internet_gateway.gw ]
}
resource "aws_route_table_association" "dmz-1a" {
  subnet_id      = aws_subnet.dmz_subnet_1a.id
  route_table_id = aws_route_table.dmz_rtb.id
  depends_on = [ aws_route_table.dmz_rtb ]
}
resource "aws_route_table_association" "dmz-1b" {
  subnet_id      = aws_subnet.dmz_subnet_1b.id
  route_table_id = aws_route_table.dmz_rtb.id
  depends_on = [ aws_route_table.dmz_rtb ]
}
resource "aws_route_table_association" "dmz-1c" {
  subnet_id      = aws_subnet.dmz_subnet_1c.id
  route_table_id = aws_route_table.dmz_rtb.id
  depends_on = [ aws_route_table.dmz_rtb ]
}

resource "aws_route" "internet_gateway_dmz" {
  route_table_id            = aws_route_table.dmz_rtb.id
  destination_cidr_block    = "0.0.0.0/0"
  gateway_id = aws_internet_gateway.gw.id
  depends_on = [ aws_route_table.dmz_rtb ]
}