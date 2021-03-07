echo "创建Mysql5.7服务docker容器，Mysql服务做了基础的性能优化"
echo "请将docker-mysql目录提前拷贝到/opt目录下，数据保存到/opt/docker-mysql目录"
echo "数据库初始化密码：123456"
echo "第一次创建容器后需要进入docker登陆mysql导入初始化SQL脚本"
echo "登陆docker命令如：docker exec -it mysql sh"
echo "登陆mysql命令如：mysql -uroot -p123456"
echo "导入数据命令如：>source ry_xxx.sql"
echo "导入数据命令如：>source quartz.sql"
docker run --name mysql \
	--restart=always \
	-p 3306:3306 \
	-v /opt/docker-mysql/conf.d:/etc/mysql/conf.d \
	-v /opt/docker-mysql/var/lib/mysql:/var/lib/mysql \
	-e MYSQL_ROOT_PASSWORD=123456 \
	-d mysql:5.7
