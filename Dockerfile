#
#基于阿里定制openjdk版本
FROM registry.cn-hangzhou.aliyuncs.com/dragonwell/dragonwell8:8.0.0-GA_alpine_x86_64_8u212-b04
#FROM daocloud.io/library/centos:7.6.1810
COPY ruoyi-admin/target/ruoyi-admin.jar /opt

ENV TZ "Asia/Shanghai"
ENV LANG en_US.UTF-8
#java内存依据业务和服务器配置适当调整，Xmx一般1G~2G左右，如果要求高并发建议设置Xms=Xmx
ENV JAVA_OPTS "-server -Xms128M -Xmx1024M -XX:PermSize=128M -XX:MaxPermSize=512M"

EXPOSE 80
CMD ["java", "-jar","/opt/ruoyi-admin.jar"]