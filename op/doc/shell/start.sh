#!/bin/bash

export JAVA_HOME=$1
export ZHICALL_CONFIG=$2
cd $3/$4/bin
./startup.sh
echo "服务已启动"
