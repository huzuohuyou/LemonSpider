package com.lemon.commons.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import com.lemon.commons.log.Log;

public class FileUtil {
	private final static Log log = Log.getLogger(FileUtil.class);

	/**
	 * 复制单个文件
	 *
	 * @param oldPath  String 原文件路径 如：c:/fqf.txt
	 * @param newPath  String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public static boolean copyFile(String oldPath, String newPath, boolean overrideDstFile) {
		InputStream inStream = null;
		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				inStream = new FileInputStream(oldPath); // 读入原文件
				File dst = new File(newPath);
				if (dst.exists()) {
					if (overrideDstFile) {
						dst.delete();
					} else {
						return false;
					}
				}
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[10240];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				fs.close();
				inStream.close();

				return true;
			}
		} catch (Exception e) {
			log.error("复制单个文件操作出错", e);
		} finally {
			try {
				if(inStream != null) {
					inStream.close();
				}
			} catch (Exception e) {}
		}
		return false;
	}

	/**
	 * 复制整个文件夹内容
	 *
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			// 如果文件夹不存在 则建立新文件夹
			File newFile=new File(newPath);
			if(!newFile.exists())
			{
				newFile.mkdirs();
			}
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			log.error("复制整个文件夹内容操作出错", e);
		}

	}


	/**
	 * 判断路径对应的文件或文件夹是否存在
	 * @param path
	 * @return
	 */
	public static boolean isExists(String path) {
		File file = new File(path);
		return file!=null && file.exists();
	}


	public static File mkDirectories(String path) {
		if(path==null || "".equals(path)) {
			return null;
		}
		if(path.indexOf(path.length()-1) != '/' ) {
			path = path + "/";
		}
		File file = new File(path);
		file.mkdirs();
		return file;
	}

	/**
	 *  如果要删除的是个目录，而且目录下面有内容的话，delete方法是不能删除的。
	 *  为此我们定制了递归方法实现的能够递归删除子文件的方法。
	 *  根据路径删除指定的目录或文件，无论存在与否
	 *@param sPath  要删除的目录或文件
	 *@return 删除成功返回 true，否则返回 false。
	 */
	public static int deleteFileOrFolder(String path) {
		int deleted = 0;
		File file = new File(path);
		return recursiveDeleteFile(deleted, file);
	}

	private static int recursiveDeleteFile(int deleted, File file) {
		if(file==null || !file.exists()) {
		} else if(file.isFile()) {
			file.delete();
			++deleted;
		} else if(file.isDirectory()) {
			File[] subs = file.listFiles();
			for(File subfile : subs) {
				deleted = recursiveDeleteFile(deleted, subfile);
			}
			file.delete();
			++deleted;
		}
		return deleted;
	}


	public static byte[] readRawData(String fileName) {
		try {
			FileInputStream fin = new FileInputStream(fileName);
			int length = fin.available();
			byte[] buffer = new byte[length];
			fin.read(buffer);
			fin.close();
			return buffer;
		} catch (Exception e) {
			// PLog.e(FileUtil.class, "Fail to read file form SD card, file: " +
			// fileName + ".", e);
		}
		return new byte[]{};
	}

	public static File writeByte2File(String filePath, NSData content) {
		File file = null;
		FileOutputStream output = null;
		try {
			file = createFileCascade(filePath);
			output = new FileOutputStream(file);
			output.write(content.tobytearray());
			output.flush();
		} catch (Exception e) {
			// PLog.e(FileUtil.class, "Fail to write file at path: " +
			// (filePath) + ".", e);
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				// PLog.e(FileUtil.class,
				// "Fail to close fileStream for write file.", e);
			}
		}
		return file;
	}

	public static File createFileCascade(String fileName) {
		String path = fileName;
		int dirend = path.lastIndexOf("/");
		String dirPath = path.substring(0, dirend);
		File dirFile = new File(dirPath);
		dirFile.mkdirs();

		File file = new File(path);
		try {
			file.createNewFile();
		} catch (Exception e) {
			log.error("尝试创建新文件时发生错误，路径：{}", file, e);
		}
		return file;
	}
	
	public static File json2File(String fileName,JSONObject json) throws Exception {
		File jsonFile =createFileCascade(fileName);
		FileWriter fw = new FileWriter(jsonFile);
		fw.write(json.toString());
		fw.close();
		return jsonFile;
	}


	/**
	 * 该方法只能读取jar内的资源，且如果文件大于10M的会有BUG：会有少量中文(或双字节编码的）出现乱码。
	 * @param type
	 * @param resourceName
	 * @return
	 */
	public static String readAllTextInJarResource(Class<?>type, String resourceName) {
		InputStream ins = null;
		BufferedInputStream br = null;
		try {
			ins = type.getClassLoader().getResourceAsStream(resourceName);
			br = new BufferedInputStream(ins);
			StringBuilder sb = new StringBuilder();
			int bytesReaded = 0;
			int bufferSz = 4096;
			byte[] buff = new byte[10240000];
			int head = 0;
			while((bytesReaded=br.read(buff, head, bufferSz)) > 0) {
				head += bytesReaded;
			}
			Charset cset = Charset.forName("utf-8");
			sb.append(new String(buff, 0, head, cset));
			return sb.toString();
		} catch (FileNotFoundException e) {
			log.error("读取文本文件全部内容时错误", e);
		} catch (IOException e) {
			log.error("读取文本文件全部内容时错误", e);
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch(Exception e) {}
			}
			if(ins != null) {
				try {
					ins.close();
				} catch(Exception e) {}
			}
		}
		return null;
	}

	/**
	 *
	 */
	public static String readAllText(String filePath) {
		return FileUtil.readAllText(new File(filePath));
	}

	public static String readAllText(File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
			log.error("读取文本文件全部内容时错误", e);
		} catch (IOException e) {
			log.error("读取文本文件全部内容时错误", e);
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch(Exception e) {}
			}
		}
		return null;
	}


	public static String writeAllText(String content, String path) {
		return writeAllText(content, new File(path));
	}
	public static String writeAllText(String content, File file) {
		int retry = 4;

		while(retry > 0) {
			try {
				FileUtils.writeStringToFile(file, content, false);
				return file.getAbsolutePath();
			} catch (Exception e) {
				e.printStackTrace();
				--retry;
			}
		}

		return null;
	}

	
	/**
	 * 将srcFilePath对应的文件改名或剪切为dstFilePath对应的文件，
	 * 当dstFilePath对应的文件已经存在时，override控制是否覆盖，如果不能覆盖返回false；
	 * 其它情况返回true（renameTo的返回结果）
	 * @param srcFilePath
	 * @param dstFilePath
	 * @param override
	 * @return
	 */
	public static boolean rename(String srcFilePath, String dstFilePath, boolean override) {
		File srcFile = new File(srcFilePath);
		File dstFile = new File(dstFilePath);
		if(dstFile.exists()) {
			if(!override) {
				return false;
			}
			dstFile.delete();
		}
		return srcFile.renameTo(dstFile);
	}

	public static  String getFileMd5(String filePath){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(filePath));
			String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
			return md5;
		} catch (Exception e) {
			log.error("获取file文件[{}]md5错误：{}",filePath,e.getMessage());
		}
		finally {
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}
}
