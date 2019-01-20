nohup java -jar 自己的springboot项目.jar >日志文件名.log 2>&1 &
nohup java -jar gpsserver-1.0.jar &
netstat -tunlp |grep 8000
ps -ef|grep java

公有 DNS (IPv4)
ec2-3-17-57-67.us-east-2.compute.amazonaws.com

3.17.57.67
172.31.20.71
VPC ID:vpc-89a6a9e1
子网 ID :subnet-c9076bb3

承载数据库实例的 VPC 之外的 EC2 实例和设备将连接到该数据库实例。
您还必须选择一个或多个 VPC 安全组来指定哪些
EC2 实例和设备可以连接到该数据库实例。