provider "aws"{
  region = "us-east-1"
}

resource "aws_security_group" "securitygroup" {
  name = "securitygroup"
  description = "permitir acesso HTTP e acesso internet"

  ingress {
    from_port = 80
    to_port = 80
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port = 443
    to_port = 443
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port = 0
    to_port = 65535
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_key_pair" "terraform-aws-keypair" {
  key_name = "terraform-aws-keypair"
  public_key = file("~/.ssh/id_rsa.pub")
}

resource "aws_instance" "servidor" {
  ami           = "ami-07caf09b362be10b8"
  instance_type = "t2.nano"
  user_data = file("user_data.sh")
  key_name = aws_key_pair.terraform-aws-keypair.key_name
  vpc_security_group_ids = [aws_security_group.securitygroup.id]
}