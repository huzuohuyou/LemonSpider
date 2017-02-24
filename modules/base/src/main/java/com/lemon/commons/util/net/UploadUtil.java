package com.lemon.commons.util.net;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lemon.commons.log.Log;

public class UploadUtil {
	private final static Log log = Log.getLogger(UploadUtil.class);

	//
	// /**
	// * 上传文件
	// */
	// @RequestMapping(value = "/upload", method = RequestMethod.POST)
	// public String upload(MultipartHttpServletRequest request,
	// HttpServletResponse response) throws IOException{
	// Map<String, Object> result = new HashMap<String, Object>();
	// String folder = "";
	// try {
	// folder = request.getSession().getServletContext().getRealPath("/");
	// } catch (Exception e) {
	// result.put("message", "获取folder失败");
	// return ajaxHtml(JsonUtil.getJsonString4JavaPOJO(result), response);
	// }
	// if (StringUtil.isEmpty(folder)) {// step-1 获得文件夹
	// response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
	// if (!result.containsKey("message")) {
	// result.put("message", "处理失败");
	// }
	// return ajaxJson(JsonUtil.getJsonString4JavaPOJO(result), response);
	// }
	// if (handler(request, response, result, folder)) {
	// result.put("status", "success");
	// result.put("message", "上传成功");
	// }else
	// {
	// result.put("status", "fail");
	// result.put("message", "上传失败");
	// }
	// return ajaxHtml(JsonUtil.getJsonString4JavaPOJO(result), response);
	// }
	//

	/**
	 * 处理文件上传
	 */
	public static boolean handler(String url, String fileName, MultipartHttpServletRequest request, HttpServletResponse response) {
		MultipartFile file = request.getFile("file");
		if (file == null) {// step-2 判断file
			return false;
		}
		// String realFilePath = url + File.separator;
		// if (!(new File(realFilePath).exists())) {
		// new File(realFilePath).mkdirs();
		// }
		// String bigRealFilePath = realFilePath + File.separator +
		// fileName.toLowerCase();
		//
		// if (file.getSize() > 0) {
		// File targetFile = new File(bigRealFilePath);
		// try {
		// file.transferTo(targetFile);// 写入目标文件
		// } catch (IllegalStateException e) {
		// log.error("[err] upload failed:", e);
		// return false;
		// } catch (IOException e) {
		// log.error("[err] upload failed:", e);
		// return false;
		// }
		// }

		try {
			SaveFileFromInputStream(file.getInputStream(), url, fileName);
		} catch (IOException e) {
			return false;
		}
		return true;
		// //保存user file
		// UserFileDTO fileDTO = new UserFileDTO(1, new Date(),
		// IpTool.getClientAddress(request), orgFileName, bigRealFilePath, 1);
		// fileDTO.setFileSize(String.valueOf(NumUtil.divideNumber(file.getSize(),
		// 1024*1024)));
		// userFileService.save(fileDTO);
		// return true;
	}

	public static boolean media(String url, String fileName, HttpServletRequest request, HttpServletResponse response) {

		try {
			InputStream aa = request.getInputStream();

			log.error("ccccccccccccccccccccccc" + aa.toString());

			SaveFileFromInputStream(aa, url, fileName);
		} catch (IOException e) {
			log.error("eeeeeeee", e);
			return false;
		}
		return true;
		// //保存user file
		// UserFileDTO fileDTO = new UserFileDTO(1, new Date(),
		// IpTool.getClientAddress(request), orgFileName, bigRealFilePath, 1);
		// fileDTO.setFileSize(String.valueOf(NumUtil.divideNumber(file.getSize(),
		// 1024*1024)));
		// userFileService.save(fileDTO);
		// return true;
	}

	/*
	 * boolean getError(String message, HttpStatus status, Map<String, Object>
	 * result, HttpServletResponse response, Exception e) {
	 * response.setStatus(status.value()); result.put("message", message);
	 * LOG.warn(message, ex); return false; }
	 */

	/** 获取文件大小 **/
	/*
	 * @RequestMapping(value = "/getfilesize")
	 *
	 * @ResponseBody public String getFileSize(HttpServletRequest request) {
	 * Map<String, Object> map = new HashMap<String, Object>(); String fileId =
	 * request.getParameter("fileId"); map.put("file_id", fileId);
	 * List<UserFileDTO> list = userFileService.find(map); if(list.size() != 0){
	 * UserFileDTO file = list.get(0); Long fileLength = new
	 * File(file.getFilePath()).length(); return fileLength.toString(); } return
	 * (new Long(0L)).toString(); }
	 */
	private static void SaveFileFromInputStream(InputStream stream, String path, String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + "/" + filename);
		log.info("11111111111111111");
		byte[] buffer = new byte[1024 * 1024];
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			log.info("11111111111111111");
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}

	//	// AJAX输出，返回null
	//	private String ajax(String content, String type, HttpServletResponse response) {
	//		try {
	//			response.setContentType(type + ";charset=UTF-8");
	//			response.setHeader("Pragma", "No-cache");
	//			response.setHeader("Cache-Control", "no-cache");
	//			response.setDateHeader("Expires", 0);
	//			response.getWriter().write(content);
	//			response.getWriter().flush();
	//		} catch (IOException e) {
	//			log.error("ajax", e);
	//		}
	//		return null;
	//	}
	//
	//	/** AJAX输出HTML，返回null **/
	//	private String ajaxHtml(String html, HttpServletResponse response) {
	//		return ajax(html, "text/html", response);
	//	}
	//
	//	/** AJAX输出json，返回null **/
	//	private String ajaxJson(String json, HttpServletResponse response) {
	//		return ajax(json, "application/json", response);
	//	}

}
