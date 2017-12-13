#!/bin/bash

# $1 backup包所处地方
# $2 war包名字
# $3 tomcat根目录
# $2 tomcat名字
export JAVA_HOME=/home/java/jdk1.7.0_79
BAK_DIR=$1/$2/`date +%Y%m%d`  
mkdir -p "$BAK_DIR"  
cp $3/$4/webapps/$2.war "$BAK_DIR"/$2_`date +%H%M%S`.war  
rm -rf $3/$4/webapps/*
