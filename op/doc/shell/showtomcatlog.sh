#!/bin/bash

cd $1/$2/logs
tail -f catalina.out
