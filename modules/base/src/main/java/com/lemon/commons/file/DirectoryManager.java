package com.lemon.commons.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yp on 2015/8/25.
 */
public class DirectoryManager {

	private String dir;
	public DirectoryManager(String dir){
		this.dir = dir;
	}

	public List<DirectoryInfo> list(){

		File dirFile = new File( this.dir );
		List<DirectoryInfo> directoryInfoList = new ArrayList<>();
		if(!dirFile.exists()){
			return directoryInfoList;
		}
		if(!dirFile.isDirectory())
			return directoryInfoList;
		File[] found  = dirFile.listFiles();
		if(found != null){
			for (File file : found) {
				if(file.isDirectory()){
					DirectoryInfo directoryInfo = new DirectoryInfo();
					directoryInfo.setName(file.getName());
					directoryInfo.setDir(file.getAbsolutePath().replace( "\\", "/" ));
					directoryInfoList.add(directoryInfo);
				}
			}
		}

		return directoryInfoList;
	}

}
