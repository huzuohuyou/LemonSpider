cd src_video;
old=0;
new=0;
time=0;
sleept=10;
echo 'Streaming.......';
echo 'time	total	delta';
while true; do 
new=$(find . -name *.jpg | wc -l);
added=`expr $new - $old`;
echo $time'	'$new'	'$added;
old=$new;  
sleep $sleept; 
time=`expr $time + $sleept`;
done
