@echo off
echo.
echo [–≈œ¢] ÷¥––Web°£
echo.

cd %~dp0
cd ../infosouth-admin/target

set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar %JAVA_OPTS% infosouth-admin.jar

cd bin
pause