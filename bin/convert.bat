%~dp0\convert.exe %~dp0..\cache\%1.jpg -color-matrix '0,0,1,0,1,0,1,0,0' %~dp0..\temp\%1_swap.jpg
%~dp0\nvcompress -rgb %~dp0..\temp\%1_swap.jpg %~dp0..\temp\%1.dds
py %~dp0\xtx_extract.py %~dp0..\temp\%1.dds
py %~dp0\zlib_compress.py %~dp0..\temp\%1.xtx
mv %~dp0..\temp\%1.xtx.z %~dp0..\_output\titles\0100d870045b6000\romfs\titles\%1\%1.xtx.z
exit