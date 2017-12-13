#!/bin/bash

# $1 war包所处地方
# $2 war包名字
# $3 tomcat根目录
# $2 tomcat名字
export JAVA_HOME=/home/java/jdk1.7.0_79
cd $1
cp $2*.war $3/$4/webapps/$2.war
#cd $3/$4/bin
#./startup.sh
#echo "tomcat start"
