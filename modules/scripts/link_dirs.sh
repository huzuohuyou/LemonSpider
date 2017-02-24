echo '=====================================================';

echo 'found ipad loc as:';
ipadloc=$(find ~/Library/Developer/CoreSimulator/Devices -name PhotoStream);
len=${#ipadloc}
echo 'lenght='$len
echo $ipadloc
if [ $len -gt 185 ]; then
    echo 'found more than one places';
    exit;
elif [ $len -lt 1 ]; then
    echo 'found no one place';
    exit;
fi

rm src_video;
ln -fs $ipadloc src_video;
echo 'src_video soft link ok';
echo '-----------------------------------------------------';

rm dst_video;
if [ $1 -eq 1 ]; then
    ln -fs ./real_dst dst_video
#else
#   ln -fs '/Volumes/SHAREDOCS/data/asset/mp4' dst_video;
#fi
echo 'dst_video soft link ok'

echo '=====================================================';

#ls -l;
