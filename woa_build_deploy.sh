#!/bin/bash
echo 'svn update'

cd  /home/svn/hongganju2/codes/WOA
#svn up -r 293
svn update

echo 'building!'
cd  /home/svn/hongganju2/codes/WOA
ant

echo 'backup WOA'
WOA='/home/backstage/WOA/apache-tomcat-6.0.26/webapps'
cd /home/backstage/WOA/apache-tomcat-6.0.26/webapps/
#tar cvf hongganju`date +%m%d$(date|cut -c 25-28)`.tar  $WOA
tar -czf woa_`date -d today +"%Y-%m-%d_%H-%M-%S"`.tar.gz WOA

echo 'copy  deploy'
rm  -rf /home/backstage/apache-tomcat-6.0.26/webapps/WOA
build='/home/svn/hongganju2/codes/WOA/deploy/**' 
deploy='/home/backstage/WOA/apache-tomcat-6.0.26/webapps/WOA/'

#alias cpdir='cp -Rf'
cp -Rf  $build $deploy



cd /home/backstage/WOA/apache-tomcat-6.0.26/webapps/WOA/WEB-INF/classes/
#cp  /home/svn/hongganju2/codes/WOA/jdbc.properties  .  -f
#cp /home/svn/hongganju2/codes/WOA/hbase-site.xml  . -f 
#cp /home/svn/hongganju2/codes/WOA/ptconfig.properties  . -f
#cp /home/svn/hongganju2/codes/WOA/log4j.properties  . -f
#cp /home/svn/hongganju2/codes/WOA/web.xml  ../ -f

echo 'reboot tomcat'
/home/backstage/WOA/apache-tomcat-6.0.26/bin/startup.sh
