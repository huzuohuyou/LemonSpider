package com.lemon.commons.file;

import java.io.File;
import java.io.RandomAccessFile;
/**
 * <p>[SplitFileUtil]描述：文件分割工具类</p>
 *
 */
public class SplitFileUtil {


	/**
	 * <p>拆分文件</p>
	 * @param file 源文件
	 * @param count 拆分的文件个数
	 * @throws Exception
	 */
	public static void split(String file ,int count) throws Exception
	{

		RandomAccessFile raf = new RandomAccessFile(new File(file),"r");
		long length = raf.length();

		long theadMaxSize =  length / count; //每份的大小 ;
		raf.close();

		long offset = 0L;
		for(int i=0;i< count-1;i++) //这里不去处理最后一份
		{
			long fbegin = offset;
			long fend = (i+1) * theadMaxSize;
			offset =write(file,i+1,fbegin,fend);
		}

		if(length- offset>0) //将剩余的都写入最后一份
			write(file,count-1,offset,length);
	}


	/**
	 * <p>指定每份文件的范围写入不同文件</p>
	 * @param file 源文件
	 * @param index 文件顺序标识
	 * @param begin 开始指针位置
	 * @param end 结束指针位置
	 * @return
	 * @throws Exception
	 */
	private static long write(String file,int index,long begin,long end) throws Exception
	{
		RandomAccessFile in = new RandomAccessFile(new File(file),"r");
		RandomAccessFile out = new RandomAccessFile(new File(file+"."+String.format("%03d", index)),"rw");
		byte[] b = new byte[1024];
		int n=0;
		in.seek(begin);//从指定位置读取

		while(in.getFilePointer() <= end && (n= in.read(b))!=-1)
		{
			out.write(b, 0, n);
		}
		long endPointer =in.getFilePointer();
		in.close();
		out.close();
		return endPointer;
	}

	/**
	 * <p>合并文件</p>
	 * @param file 指定合并后的文件
	 * @param tempFiles 分割前的文件名
	 * @param tempCount 文件个数
	 * @throws Exception
	 */
	public static void merge(String file,String tempFiles,int tempCount) throws Exception
	{
		RandomAccessFile ok = new RandomAccessFile(new File(file),"rw");

		for(int i=0;i<tempCount;i++)
		{
			RandomAccessFile read = new RandomAccessFile(new File(tempFiles+"."+String.format("%03d", i+1)),"r");
			byte[] b = new byte[1024];
			int n=0;
			while((n=read.read(b))!= -1)
			{
				ok.write(b, 0, n);
			}
			read.close();
		}
		ok.close();
	}
}