#!/bin/bash
set -e

nofile="/home/buildno/springbootdemo"

if [ ! -f $nofile ];then
  no=1
else
  no=$(cat $nofile)
  no=`expr $no + 1`
fi
image=myregistry.com/public/springbootdemo:0.1.$no
docker build -t $image .
docker push $image
docker rmi $image
echo $no > $nofile
