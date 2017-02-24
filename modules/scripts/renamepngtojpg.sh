#!/bin/bash

echo "[INFO] change png to jpg file."

echo "开始修改文件夹的png$1"
cd $1
searchdirs=$(ls)
dirIndex=0
for mdir in $searchdirs
 do
   cd ./$mdir
   mypng=$(ls ./|grep ".png")
   change=0
   for file in $mypng
     do
       echo "修改${file}文件"
       zip -d ./$mdir.zip $file
       mv ./$file ./$(echo $file|sed 's/\.png/\.jpg/')
       zip ./$mdir.zip ./$(echo $file|sed 's/\.png/\.jpg/')
       change=$[change+1]
     done;

     if [ $change -gt 0 ]
     then
        echo "${mdir}文件已修改${change}个"
        dirIndex=$[dirIndex+1]
        rm -f 0
     fi
   cd ../
 done;

echo "修改了${dirIndex}个资源"

echo "完成done"