package com.lemon.commons.file;

import java.io.*;
import java.nio.channels.OverlappingFileLockException;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.lemon.commons.log.Log;

/**
 * ZIP工具包 使用ant包下的zip 解决java.util.zip压缩无法释放资源的问题
 */
public class ZipUtils {
	private final static Log log = Log.getLogger(ZipUtils.class);
	/**
	 * 使用UTF-8编码可以避免压缩中文文件名乱码
	 */
	private static final String CHINESE_CHARSET = "UTF-8";

	/**
	 * 文件读取缓冲区大小
	 */
	private static final int CACHE_SIZE = 10*1024*1024;

	/**
	 * <p>
	 * 压缩文件
	 * </p>
	 *
	 * @param sourceFolder
	 *            压缩文件夹
	 * @param zipFilePath
	 *            压缩文件输出路径（包含文件名，如：
	 *            E:/360/attach/1005/d3fe9678-5b48-4543-aa1e-9c97d1d1d039
	 *            /d3fe9678-5b48-4543-aa1e-9c97d1d1d039.zip
	 * @throws Exception
	 */
	public static void zip(String sourceFolder, String zipFilePath) throws Exception {
		OutputStream out = new FileOutputStream(zipFilePath);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		ZipOutputStream zos = new ZipOutputStream(bos);
		// 解决中文文件名乱码
		zos.setEncoding(CHINESE_CHARSET);
		File file = new File(sourceFolder);
		String basePath = null;
		if (file.isDirectory()) {
			basePath = file.getPath();
		} else {
			basePath = file.getParent();
		}
		zipFile(file, basePath, zos);
		zos.closeEntry();
		zos.close();
		bos.close();
		out.close();
	}

	/**
	 * <p>
	 * 递归压缩文件
	 * </p>
	 *
	 * @param parentFile
	 * @param basePath
	 * @param zos
	 * @throws Exception
	 */
	private static void zipFile(File parentFile, String basePath, ZipOutputStream zos) throws Exception {
		File[] files = new File[0];
		if (parentFile.isDirectory()) {
			files = parentFile.listFiles();
		} else {
			files = new File[1];
			files[0] = parentFile;
		}
		String pathName;
		InputStream is;
		BufferedInputStream bis;
		byte[] cache = new byte[CACHE_SIZE];
		for (File file : files) {
			if (file.isDirectory()) {
				pathName = file.getPath().substring(basePath.length() + 1) + "/";
				zos.putNextEntry(new ZipEntry(pathName));
				zipFile(file, basePath, zos);
			} else {
				pathName = file.getPath().substring(basePath.length() + 1);
				is = new FileInputStream(file);
				bis = new BufferedInputStream(is);
				zos.putNextEntry(new ZipEntry(pathName));
				int nRead = 0;
				while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
					zos.write(cache, 0, nRead);
				}
				bis.close();
				is.close();
			}
		}
	}

	/**
	 * <p>
	 * 解压压缩包
	 * </p>
	 *
	 * @param zipFilePath
	 *            压缩文件路径
	 * @param destDir
	 *            压缩包释放目录
	 * @throws Exception
	 */
	public static boolean unZip(String zipFilePath, String destDir) {
		if(zipFilePath==null || destDir==null) {
			return false;
		}
		boolean rt = false;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			ZipFile zipFile = new ZipFile(zipFilePath, CHINESE_CHARSET);
			Enumeration<?> emu = zipFile.getEntries();
			byte[] cache = new byte[CACHE_SIZE];
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				if (entry.isDirectory()) {
					new File(destDir + entry.getName()).mkdirs();
					continue;
				}
				bis = new BufferedInputStream(zipFile.getInputStream(entry));
				File file = new File(destDir + entry.getName());
				File parentFile = file.getParentFile();
				if (parentFile != null && (!parentFile.exists())) {
					parentFile.mkdirs();
				}
				bos = new BufferedOutputStream(new FileOutputStream(file), CACHE_SIZE);
				int nRead = 0;
				while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
					bos.write(cache, 0, nRead);
				}
				bos.flush();
				bos.close();
				bis.close();
			}
			zipFile.close();
			rt = true;
		} catch (IOException e) {
			log.error("尝试打开zip文件进行解压缩时候失败，文件:{}", zipFilePath, e);
		} catch (Exception e) {
			log.error("在将zip文件{}解压到{}时失败", zipFilePath, destDir, e);
		} finally {
			if(bos != null) {
				try {
					bos.close();
				} catch(Exception e) {}
			}
			if(bis != null) {
				try {
					bis.close();
				} catch(Exception e) {}
			}
		}
		return rt;
	}

	/**
	 * 拼接zip分卷文件
	 * @param dest
	 * @param files
	 */
	public static boolean joint(String dest, List<String> files) {
		if(files==null){
			return false;
		}
		BufferedInputStream bis = null;
		File destFile = null;
		RandomAccessFile accessFile = null;
		try {
			destFile = new File(dest);
			if(!destFile.exists()){
				destFile.createNewFile();
			}
			accessFile = new RandomAccessFile(destFile,"rw");
			accessFile.getChannel().lock();
			byte bytes[] = new byte[CACHE_SIZE];
			int len = -1;
			for (String file:files) {
				bis = new BufferedInputStream(new FileInputStream(file));
				while ((len = bis.read(bytes)) != -1) {
					accessFile.write(bytes, 0, len);
				}
			}
		} catch (FileNotFoundException e) {
			log.error("file not found:{},{}", dest, e.getMessage());
			return false;
		} catch (IOException e) {
			log.error("IO Exception:{},{}", dest, e.getMessage());
			return false;
		}catch (OverlappingFileLockException e){
			log.error("OverlappingFileLock Exception:{},{}", dest, e.getMessage());
			return false;
		}
		finally {
			if(bis != null) {
				try {
					bis.close();
				} catch(Exception e) {}
			}

			if(accessFile != null){
				try {
					accessFile.close();
				} catch (IOException e) {

				}
			}
		}
		return true;
	}

}
