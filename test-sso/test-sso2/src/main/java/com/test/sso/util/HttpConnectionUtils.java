package com.test.sso.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * Java原生的API可用于发送HTTP请求，即java.net.URL、java.net.URLConnection，这些API很好用、很常用，
 * 但不够简便；
 * 
 * 1.通过统一资源定位器（java.net.URL）获取连接器（java.net.URLConnection） 2.设置请求的参数 3.发送请求
 * 4.以输入流的形式获取返回内容 5.关闭输入流
 * 
 * @author H__D
 *
 */
public class HttpConnectionUtils {

	// post请求
	public static final String HTTP_POST = "POST";

	// get请求
	public static final String HTTP_GET = "GET";

	// utf-8字符编码
	public static final String CHARSET_UTF_8 = "utf-8";

	// HTTP内容类型。如果未指定ContentType，默认为TEXT/HTML
	public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";

	// HTTP内容类型。相当于form表单的形式，提交数据
	public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";

	// HTTP内容类型。相当于form表单的形式，提交数据
	public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";

	// 请求超时时间
	public static final int SEND_REQUEST_TIME_OUT = 50000;

	// 将读超时时间
	public static final int READ_TIME_OUT = 50000;
	
	//百分比id,上传时的标识
	public static final String UPLOAD_PERCENT_ID = "upload_percent_id";

	/**
	 * @param urlStr
	 *            请求地址
	 * @return 返回内容
	 */
	public static String getMethod(String urlStr) {
		return requestMethod(HTTP_GET, urlStr, null);
	}

	/**
	 * 
	 * @param urlStr
	 *            请求地址
	 * @param body
	 *            请求发送内容
	 * @return 返回内容
	 */
	public static String getMethod(String urlStr, String body) {
		return requestMethod(HTTP_GET, urlStr, body);
	}

	/**
	 * @param urlStr
	 *            请求地址
	 * @return 返回内容
	 */
	public static String postMethod(String urlStr) {
		return requestMethod(HTTP_POST, urlStr, null);
	}

	/**
	 * 
	 * @param urlStr
	 *            请求地址
	 * @param body
	 *            请求发送内容
	 * @return 返回内容
	 */
	public static String postMethod(String urlStr, String body) {
		return requestMethod(HTTP_POST, urlStr, body);
	}

	/**
	 * 
	 * @param requestType
	 *            请求类型
	 * @param urlStr
	 *            请求地址
	 * @param body
	 *            请求发送内容
	 * @return 返回内容
	 */
	public static String requestMethod(String requestType, String urlStr, String body) {

		// 是否有http正文提交
		boolean isDoInput = false;
		if (body != null && body.length() > 0)
			isDoInput = true;
		OutputStream outputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;
		try {
			// 统一资源
			URL url = new URL(urlStr);
			// 连接类的父类，抽象类
			URLConnection urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			if (isDoInput) {
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setRequestProperty("Content-Length", String.valueOf(body.length()));
			}
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpURLConnection.setDoInput(true);
			// 设置一个指定的超时值（以毫秒为单位）
			httpURLConnection.setConnectTimeout(SEND_REQUEST_TIME_OUT);
			// 将读超时设置为指定的超时，以毫秒为单位。
			httpURLConnection.setReadTimeout(READ_TIME_OUT);
			// Post 请求不能使用缓存
			httpURLConnection.setUseCaches(false);
			// 设置字符编码
			httpURLConnection.setRequestProperty("Accept-Charset", CHARSET_UTF_8);
			// 设置内容类型
			httpURLConnection.setRequestProperty("Content-Type", CONTENT_TYPE_JSON_URL);
			// 设定请求的方法，默认是GET
			httpURLConnection.setRequestMethod(requestType);

			// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
			// 如果在已打开连接（此时 connected 字段的值为 true）的情况下调用 connect 方法，则忽略该调用。
			httpURLConnection.connect();

			if (isDoInput) {
				outputStream = httpURLConnection.getOutputStream();
				outputStreamWriter = new OutputStreamWriter(outputStream);
				outputStreamWriter.write(body);
				outputStreamWriter.flush();// 刷新
			}
			if (httpURLConnection.getResponseCode() >= 300) {
				throw new Exception(
						"HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
			}

			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				inputStream = httpURLConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(inputStreamReader);

				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
					resultBuffer.append("\n");
				}
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 关闭流

			try {
				if (outputStreamWriter != null) {
					outputStreamWriter.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultBuffer.toString().trim();
	}

	/**
	 * 将map集合的键值对转化成：key1=value1&key2=value2 的形式
	 * 
	 * @param parameterMap
	 *            需要转化的键值对集合
	 * @return 字符串
	 */
	public static String convertStringParamter(Map parameterMap) {
		StringBuffer parameterBuffer = new StringBuffer();
		if (parameterMap != null) {
			Iterator iterator = parameterMap.keySet().iterator();
			String key = null;
			String value = null;
			while (iterator.hasNext()) {
				key = (String) iterator.next();
				if (parameterMap.get(key) != null) {
					value = (String) parameterMap.get(key);
				} else {
					value = "";
				}
				parameterBuffer.append(key).append("=").append(value);
				if (iterator.hasNext()) {
					parameterBuffer.append("&");
				}
			}
		}
		return parameterBuffer.toString();
	}
	
	
	
	/**
	 * 单文件上传的方法
	 * 
	 * @param actionUrl
	 *            ：上传的路径
	 * @param uploadFilePaths：需要上传的文件路径，数组
	 * @return
	 */
	@SuppressWarnings("finally")
	public static String uploadSingleFile(String actionUrl, InputStream inputStreams, String originalFilename) {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";

		DataOutputStream ds = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;

		try {
			// 统一资源
			URL url = new URL(actionUrl);
			// 连接类的父类，抽象类
			URLConnection urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpURLConnection.setDoInput(true);
			// 设置是否向httpUrlConnection输出
			httpURLConnection.setDoOutput(true);
			// Post 请求不能使用缓存
			httpURLConnection.setUseCaches(false);
			// 设定请求的方法，默认是GET
			httpURLConnection.setRequestMethod("POST");
			// 设置字符编码连接参数
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			// 设置字符编码
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			// 设置请求内容类型
			httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

			// 设置DataOutputStream
			ds = new DataOutputStream(httpURLConnection.getOutputStream());
			
			
			String uploadFile = "123.jpg";
			if(originalFilename != null){
				
				uploadFile = originalFilename;
			}
			
			String filename = uploadFile.substring(uploadFile.lastIndexOf("//") + 1);
			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes("Content-Disposition: form-data; " + "name=\"file\";filename=\"" + filename
					+ "\"" + end);
			ds.writeBytes(end);
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int length = -1;
			while ((length = inputStreams.read(buffer)) != -1) {
				ds.write(buffer, 0, length);
			}
			ds.writeBytes(end);
			/* close streams */
			inputStreams.close();
			
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
			/* close streams */
			ds.flush();
			if (httpURLConnection.getResponseCode() >= 300) {
				throw new Exception(
						"HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
			}

			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				inputStream = httpURLConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(inputStreamReader);
				tempLine = null;
				resultBuffer = new StringBuffer();
				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
					resultBuffer.append("\n");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ds != null) {
				try {
					ds.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return resultBuffer.toString().trim();
		}
	}

	/**
	 * 多文件上传的方法
	 * 
	 * @param actionUrl
	 *            ：上传的路径
	 * @param uploadFilePaths：需要上传的文件路径，数组
	 * @return
	 */
	@SuppressWarnings("finally")
	public static String uploadFile(String actionUrl, String[] uploadFilePaths) {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";

		DataOutputStream ds = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;

		try {
			// 统一资源
			URL url = new URL(actionUrl);
			// 连接类的父类，抽象类
			URLConnection urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpURLConnection.setDoInput(true);
			// 设置是否向httpUrlConnection输出
			httpURLConnection.setDoOutput(true);
			// Post 请求不能使用缓存
			httpURLConnection.setUseCaches(false);
			// 设定请求的方法，默认是GET
			httpURLConnection.setRequestMethod("POST");
			// 设置字符编码连接参数
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			// 设置字符编码
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			// 设置请求内容类型
			httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

			// 设置DataOutputStream
			ds = new DataOutputStream(httpURLConnection.getOutputStream());
			for (int i = 0; i < uploadFilePaths.length; i++) {
				String uploadFile = uploadFilePaths[i];
				String filename = uploadFile.substring(uploadFile.lastIndexOf("//") + 1);
				ds.writeBytes(twoHyphens + boundary + end);
				ds.writeBytes("Content-Disposition: form-data; " + "name=\"file" + i + "\";filename=\"" + filename
						+ "\"" + end);
				ds.writeBytes(end);
				FileInputStream fStream = new FileInputStream(uploadFile);
				int bufferSize = 1024;
				byte[] buffer = new byte[bufferSize];
				int length = -1;
				while ((length = fStream.read(buffer)) != -1) {
					ds.write(buffer, 0, length);
				}
				ds.writeBytes(end);
				/* close streams */
				fStream.close();
			}
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
			/* close streams */
			ds.flush();
			if (httpURLConnection.getResponseCode() >= 300) {
				throw new Exception(
						"HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
			}

			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				inputStream = httpURLConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(inputStreamReader);
				tempLine = null;
				resultBuffer = new StringBuffer();
				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
					resultBuffer.append("\n");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ds != null) {
				try {
					ds.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return resultBuffer.toString().trim();
		}
	}

	/**
	 * 
	 * @param urlPath
	 *            下载路径
	 * @param downloadDir
	 *            下载存放目录
	 * @return 返回下载文件
	 */
	@SuppressWarnings("finally")
	public static File downloadFile(String urlPath, String downloadDir) {
		File file = null;
		try {
			// 统一资源
			URL url = new URL(urlPath);
			// 连接类的父类，抽象类
			URLConnection urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
			// 设定请求的方法，默认是GET
			httpURLConnection.setRequestMethod("POST");
			// 设置字符编码
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
			httpURLConnection.connect();

			// 文件大小
			int fileLength = httpURLConnection.getContentLength();

			// 文件名
			String filePathUrl = httpURLConnection.getURL().getFile();
			
			String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);
			
			System.out.println("file length---->" + fileLength);

			BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());

			String path = downloadDir + File.separatorChar + fileFullName;
			file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			OutputStream out = new FileOutputStream(file);
			int size = 0;
			int len = 0;
			byte[] buf = new byte[1024];
			while ((size = bin.read(buf)) != -1) {
				len += size;
				out.write(buf, 0, size);
				// 打印下载百分比
				// System.out.println("下载了-------> " + len * 100 / fileLength +
				// "%\n");
			}
			bin.close();
			out.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return file;
		}

	}

	public static void main(String[] args) {
		String str = "";
		System.out.println(getMethod("http://wwww.baidu.com", str ));

		// 上传文件测试
		//String str = uploadFile("http://localhost:8080/app/img/uploadImage",new String[] { "C:/Users/H__D/Desktop/IMG_0109.JPG","C:/Users/H__D/Desktop/IMG_0109.JPG" });
		//System.out.println(str);

		// 下载文件测试 
		//downloadFile("http://localhost:8080/file/20160811/1470917717419_294.zip", "C:\\Users\\H__D\\Desktop");
		//downloadFile("http://localhost:8080/files/20160811/20160811210035_284.zip", "C:\\Users\\H__D\\Desktop");

	}

}
