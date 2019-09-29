docker run --name mysql \
	--restart=always \
	-p 3306:3306 \
	-v /opt/docker-mysql/conf.d:/etc/mysql/conf.d \
	-v /opt/docker-mysql/var/lib/mysql:/var/lib/mysql \
	-e MYSQL_ROOT_PASSWORD=123456 \
	-d mysql:5.7
