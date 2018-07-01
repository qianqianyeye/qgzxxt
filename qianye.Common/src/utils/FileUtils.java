package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.util.HashMap;


public class FileUtils {
	/**
	 * 判断文件的MimeType的方法
	 */
	public static String getMIMEType(File f) {
		String type = "";
		String fName = f.getName();
		/* 取得文件的扩展名 */
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();
		HashMap<String, String> typeMap = new HashMap<String, String>();
		typeMap.put("acx", "application/internet-property-stream");
		typeMap.put("ai", "application/postscript");
		typeMap.put("aif", "audio/x-aiff");
		typeMap.put("aifc", "audio/x-aiff");
		typeMap.put("aiff", "audio/x-aiff");
		typeMap.put("asf", "video/x-ms-asf");
		typeMap.put("asr", "video/x-ms-asf");
		typeMap.put("asx", "video/x-ms-asf");
		typeMap.put("au", "audio/basic");
		typeMap.put("avi", "video/x-msvideo");
		typeMap.put("axs", "application/olescript");
		typeMap.put("bas", "text/plain");
		typeMap.put("bcpio", "application/x-bcpio");
		typeMap.put("bin", "application/octet-stream");
		typeMap.put("bmp", "image/bmp");
		typeMap.put("c", "text/plain");
		typeMap.put("cat", "application/vnd.ms-pkiseccat");
		typeMap.put("cdf", "application/x-cdf");
		typeMap.put("cer", "application/x-x509-ca-cert");
		typeMap.put("class", "application/octet-stream");
		typeMap.put("clp", "application/x-msclip");
		typeMap.put("cmx", "image/x-cmx");
		typeMap.put("cod", "image/cis-cod");
		typeMap.put("cpio", "application/x-cpio");
		typeMap.put("crd", "application/x-mscardfile");
		typeMap.put("crl", "application/pkix-crl");
		typeMap.put("crt", "application/x-x509-ca-cert");
		typeMap.put("csh", "application/x-csh");
		typeMap.put("css", "text/css");
		typeMap.put("dcr", "application/x-director");
		typeMap.put("der", "application/x-x509-ca-cert");
		typeMap.put("dir", "application/x-director");
		typeMap.put("dll", "application/x-msdownload");
		typeMap.put("dms", "application/octet-stream");
		typeMap.put("doc", "application/msword");
		typeMap.put("dot", "application/msword");
		typeMap.put("dvi", "application/x-dvi");
		typeMap.put("dxr", "application/x-director");
		typeMap.put("eps", "application/postscript");
		typeMap.put("etx", "text/x-setext");
		typeMap.put("evy", "application/envoy");
		typeMap.put("exe", "application/octet-stream");
		typeMap.put("fif", "application/fractals");
		typeMap.put("flr", "x-world/x-vrml");
		typeMap.put("gif", "image/gif");
		typeMap.put("gtar", "application/x-gtar");
		typeMap.put("gz", "application/x-gzip");
		typeMap.put("h", "text/plain");
		typeMap.put("hdf", "application/x-hdf");
		typeMap.put("hlp", "application/winhlp");
		typeMap.put("hqx", "application/mac-binhex40");
		typeMap.put("hta", "application/hta");
		typeMap.put("htc", "text/x-component");
		typeMap.put("htm", "text/html");
		typeMap.put("html", "text/html");
		typeMap.put("htt", "text/webviewhtml");
		typeMap.put("ico", "image/x-icon");
		typeMap.put("ief", "image/ief");
		typeMap.put("iii", "application/x-iphone");
		typeMap.put("ins", "application/x-internet-signup");
		typeMap.put("isp", "application/x-internet-signup");
		typeMap.put("jfif", "image/pipeg");
		typeMap.put("jpe", "image/jpeg");
		typeMap.put("jpeg", "image/jpeg");
		typeMap.put("jpg", "image/jpeg");
		typeMap.put("js", "application/x-javascript");
		typeMap.put("latex", "application/x-latex");
		typeMap.put("lha", "application/octet-stream");
		typeMap.put("lsf", "video/x-la-asf");
		typeMap.put("lsx", "video/x-la-asf");
		typeMap.put("lzh", "application/octet-stream");
		typeMap.put("m13", "application/x-msmediaview");
		typeMap.put("m14", "application/x-msmediaview");
		typeMap.put("m3u", "audio/x-mpegurl");
		typeMap.put("man", "application/x-troff-man");
		typeMap.put("mdb", "application/x-msaccess");
		typeMap.put("me", "application/x-troff-me");
		typeMap.put("mht", "message/rfc822");
		typeMap.put("mhtml", "message/rfc822");
		typeMap.put("mid", "audio/mid");
		typeMap.put("mny", "application/x-msmoney");
		typeMap.put("mov", "video/quicktime");
		typeMap.put("movie", "video/x-sgi-movie");
		typeMap.put("mp2", "video/mpeg");
		typeMap.put("mp3", "audio/mpeg");
		typeMap.put("mpa", "video/mpeg");
		typeMap.put("mpe", "video/mpeg");
		typeMap.put("mpeg", "video/mpeg");
		typeMap.put("mpg", "video/mpeg");
		typeMap.put("mpp", "application/vnd.ms-project");
		typeMap.put("mpv2", "video/mpeg");
		typeMap.put("ms", "application/x-troff-ms");
		typeMap.put("mvb", "application/x-msmediaview");
		typeMap.put("nws", "message/rfc822");
		typeMap.put("oda", "application/oda");
		typeMap.put("p10", "application/pkcs10");
		typeMap.put("p12", "application/x-pkcs12");
		typeMap.put("p7b", "application/x-pkcs7-certificates");
		typeMap.put("p7c", "application/x-pkcs7-mime");
		typeMap.put("p7m", "application/x-pkcs7-mime");
		typeMap.put("p7r", "application/x-pkcs7-certreqresp");
		typeMap.put("p7s", "application/x-pkcs7-signature");
		typeMap.put("pbm", "image/x-portable-bitmap");
		typeMap.put("pdf", "application/pdf");
		typeMap.put("pfx", "application/x-pkcs12");
		typeMap.put("pgm", "image/x-portable-graymap");
		typeMap.put("pko", "application/ynd.ms-pkipko");
		typeMap.put("pma", "application/x-perfmon");
		typeMap.put("pmc", "application/x-perfmon");
		typeMap.put("pml", "application/x-perfmon");
		typeMap.put("pmr", "application/x-perfmon");
		typeMap.put("pmw", "application/x-perfmon");
		typeMap.put("png", "image/png");
		typeMap.put("pnm", "image/x-portable-anymap");
		typeMap.put("pot,", "application/vnd.ms-powerpoint");
		typeMap.put("ppm", "image/x-portable-pixmap");
		typeMap.put("pps", "application/vnd.ms-powerpoint");
		typeMap.put("ppt", "application/vnd.ms-powerpoint");
		typeMap.put("prf", "application/pics-rules");
		typeMap.put("ps", "application/postscript");
		typeMap.put("pub", "application/x-mspublisher");
		typeMap.put("qt", "video/quicktime");
		typeMap.put("ra", "audio/x-pn-realaudio");
		typeMap.put("ram", "audio/x-pn-realaudio");
		typeMap.put("ras", "image/x-cmu-raster");
		typeMap.put("rgb", "image/x-rgb");
		typeMap.put("rmi", "audio/mid");
		typeMap.put("roff", "application/x-troff");
		typeMap.put("rtf", "application/rtf");
		typeMap.put("rtx", "text/richtext");
		typeMap.put("scd", "application/x-msschedule");
		typeMap.put("sct", "text/scriptlet");
		typeMap.put("setpay", "application/set-payment-initiation");
		typeMap.put("setreg", "application/set-registration-initiation");
		typeMap.put("sh", "application/x-sh");
		typeMap.put("shar", "application/x-shar");
		typeMap.put("sit", "application/x-stuffit");
		typeMap.put("snd", "audio/basic");
		typeMap.put("spc", "application/x-pkcs7-certificates");
		typeMap.put("spl", "application/futuresplash");
		typeMap.put("src", "application/x-wais-source");
		typeMap.put("sst", "application/vnd.ms-pkicertstore");
		typeMap.put("stl", "application/vnd.ms-pkistl");
		typeMap.put("stm", "text/html");
		typeMap.put("svg", "image/svg+xml");
		typeMap.put("sv4cpio", "application/x-sv4cpio");
		typeMap.put("sv4crc", "application/x-sv4crc");
		typeMap.put("swf", "application/x-shockwave-flash");
		typeMap.put("t", "application/x-troff");
		typeMap.put("tar", "application/x-tar");
		typeMap.put("tcl", "application/x-tcl");
		typeMap.put("tex", "application/x-tex");
		typeMap.put("texi", "application/x-texinfo");
		typeMap.put("texinfo", "application/x-texinfo");
		typeMap.put("tgz", "application/x-compressed");
		typeMap.put("tif", "image/tiff");
		typeMap.put("tiff", "image/tiff");
		typeMap.put("tr", "application/x-troff");
		typeMap.put("trm", "application/x-msterminal");
		typeMap.put("tsv", "text/tab-separated-values");
		typeMap.put("txt", "text/plain");
		typeMap.put("uls", "text/iuls");
		typeMap.put("ustar", "application/x-ustar");
		typeMap.put("vcf", "text/x-vcard");
		typeMap.put("vrml", "x-world/x-vrml");
		typeMap.put("wav", "audio/x-wav");
		typeMap.put("wcm", "application/vnd.ms-works");
		typeMap.put("wdb", "application/vnd.ms-works");
		typeMap.put("wks", "application/vnd.ms-works");
		typeMap.put("wmf", "application/x-msmetafile");
		typeMap.put("wps", "application/vnd.ms-works");
		typeMap.put("wri", "application/x-mswrite");
		typeMap.put("wrl", "x-world/x-vrml");
		typeMap.put("wrz", "x-world/x-vrml");
		typeMap.put("xaf", "x-world/x-vrml");
		typeMap.put("xbm", "image/x-xbitmap");
		typeMap.put("xla", "application/vnd.ms-excel");
		typeMap.put("xlc", "application/vnd.ms-excel");
		typeMap.put("xlm", "application/vnd.ms-excel");
		typeMap.put("xls", "application/vnd.ms-excel");
		typeMap.put("xlt", "application/vnd.ms-excel");
		typeMap.put("xlw", "application/vnd.ms-excel");
		typeMap.put("xof", "x-world/x-vrml");
		typeMap.put("xpm", "image/x-xpixmap");
		typeMap.put("xwd", "image/x-xwindowdump");
		typeMap.put("z", "application/x-compress");
		typeMap.put("zip", "application/zip");
		type = typeMap.get(end);
		if (type == null) {
			type = "*/*";
		}
		return type;
	}

	/**
	 * 判断指定的文件是否存在。
	 * 
	 * @param fileName
	 *            文件名
	 * @return 存在返回true，不存在返回false。
	 */
	public static boolean isFileExist(String fileName) {
		return new File(fileName).isFile();
	}

	/**
	 * 创建指定的目录。
	 * 
	 * @param file
	 *            要创建的目录
	 * @return 创建成功返回true，否则返回false。
	 */
	public static boolean makeDirectory(File file) {
		File parent = file.getParentFile();
		if (parent != null) {
			return parent.mkdirs();
		}
		return false;
	}

	/**
	 * 创建指定的目录。
	 * 
	 * @param fileName
	 *            要创建的目录的目录名
	 * @return 创建成功返回true，否则返回false。
	 */
	public static boolean makeDirectory(String fileName) {
		File file = new File(fileName);
		return makeDirectory(file);
	}

	/**
	 * 清空指定目录中的文件。但是不会删除该目录下的子目录及其内容。
	 * 
	 * @param directory
	 *            要清空的目录
	 * @return 清空成功返回true,否则返回false。
	 */
	public static boolean clearDirectory(File directory) {
		boolean result = true;
		File[] entries = directory.listFiles();
		for (int i = 0; i < entries.length; i++) {
			if ("log4j_".equals(entries[i].getName())) {
				continue;
			}
			if (!entries[i].delete()) {
				result = false;
			}
		}
		return result;
	}

	public static boolean clearNoSelectDirectory(File directory, String dateStr) {
		boolean result = true;
		File[] entries = directory.listFiles();
		for (int i = 0; i < entries.length; i++) {
			// System.out.println("获取的所有名称："+entries[i].getName());
			if ("log4j_".equals(entries[i].getName())) {
				continue;
			}
			String nowDatelog = "log4j_" + dateStr + ".log";
			if (nowDatelog.equals(entries[i].getName())) {
				continue;
			}

			if (!entries[i].delete()) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * 清空指定目录中的文件。但是不会删除该目录下的子目录及其内容。
	 * 
	 * @param directoryName
	 *            要清空的目录名
	 * @return 清空成功返回true,否则返回false。
	 */
	public static boolean clearDirectory(String directoryName) {
		File dir = new File(directoryName);
		return clearDirectory(dir);
	}

	/**
	 * 删除指定文件
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		return deleteFile(new File(fileName));
	}

	/**
	 * 删除指定文件
	 * 
	 * @param file
	 *            文件
	 * @return
	 */
	public static boolean deleteFile(File file) {
		if (file.isFile() && file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 删除指定目录及其中的所有内容。
	 * 
	 * @param dirName
	 *            要删除的目录的目录名
	 * @return 删除成功时返回true，否则返回false。
	 */
	public static boolean deleteDirectory(String dirName) {
		return deleteDirectory(new File(dirName));
	}

	/**
	 * 删除指定目录及其中的所有内容。
	 * 
	 * @param dir
	 *            要删除的目录
	 * @return 删除成功时返回true，否则返回false。
	 */
	public static boolean deleteDirectory(File dir) {
		if ((dir == null) || !dir.isDirectory()) {
			throw new IllegalArgumentException("Argument " + dir
					+ " is not a directory. ");
		}

		File[] entries = dir.listFiles();
		int sz = entries.length;

		for (int i = 0; i < sz; i++) {
			if (entries[i].isDirectory()) {
				if (!deleteDirectory(entries[i])) {
					return false;
				}
			} else {
				if (!entries[i].delete()) {
					return false;
				}
			}
		}

		if (!dir.delete()) {
			return false;
		}
		return true;
	}

	/**
	 * 得到文件的后缀名
	 * 
	 * @param fileName
	 *            文件名
	 * @return 后缀名
	 */
	public static String getTypePart(String fileName) {
		int point = fileName.lastIndexOf('.');
		int length = fileName.length();
		if (point == -1 || point == length - 1) {
			return "";
		} else {
			return fileName.substring(point + 1, length);
		}
	}

	/**
	 * 得到文件的后缀名
	 * 
	 * @param file
	 *            文件
	 * @return 后缀名
	 */
	public static String getFileType(File file) {
		return getTypePart(file.getName());
	}

	/**
	 * 得到文件的名字部分。 实际上就是路径中的最后一个路径分隔符后的部分。
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件名中的名字部分
	 */
	public static String getNamePart(String fileName) {
		int point = getPathLsatIndex(fileName);
		int length = fileName.length();
		if (point == -1) {
			return fileName;
		} else if (point == length - 1) {
			int secondPoint = getPathLsatIndex(fileName, point - 1);
			if (secondPoint == -1) {
				if (length == 1) {
					return fileName;
				} else {
					return fileName.substring(0, point);
				}
			} else {
				return fileName.substring(secondPoint + 1, point);
			}
		} else {
			return fileName.substring(point + 1);
		}
	}

	/**
	 * 后去文件名称，如：文件xxx.doc，则得到的值为xxx
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileName(String file) {
		return file.substring(0, file.lastIndexOf('.'));
	}

	/**
	 * 得到路径分隔符在文件路径中最后出现的位置。
	 * 
	 * @param fileName
	 *            文件路径
	 * @return 路径分隔符在路径中最后出现的位置，没有出现时返回-1。
	 */
	public static int getPathLsatIndex(String fileName) {
		int point = fileName.lastIndexOf('/');
		if (point == -1) {
			point = fileName.lastIndexOf('\\');
		}
		return point;
	}

	/**
	 * 得到路径分隔符在文件路径中指定位置前最后出现的位置。
	 * 
	 * @param fileName
	 *            文件路径
	 * @param fromIndex
	 *            开始查找的位置
	 * @return 路径分隔符在路径中指定位置前最后出现的位置，没有出现时返回-1。
	 */
	public static int getPathLsatIndex(String fileName, int fromIndex) {
		int point = fileName.lastIndexOf('/', fromIndex);
		if (point == -1) {
			point = fileName.lastIndexOf('\\', fromIndex);
		}
		return point;
	}

	/**
	 * 得到文件名中的父路径部分。 对两种路径分隔符都有效。 不存在时返回""。
	 * 如果文件名是以路径分隔符结尾的则不考虑该分隔符，例如"/path/"返回""。
	 * 
	 * @param fileName
	 *            文件名
	 * @return 父路径，不存在或者已经是父目录时返回""
	 */
	public static String getPathPart(String fileName) {
		int point = getPathLsatIndex(fileName);
		int length = fileName.length();
		if (point == -1) {
			return "";
		} else if (point == length - 1) {
			int secondPoint = getPathLsatIndex(fileName, point - 1);
			if (secondPoint == -1) {
				return "";
			} else {
				return fileName.substring(0, secondPoint);
			}
		} else {
			return fileName.substring(0, point);
		}
	}

	/**
	 * 读取指定文件的内容
	 * 
	 * @param path
	 *            为要读取文件的绝对路径
	 * @param charsetName
	 *            文件的编码格式
	 * @return 以行读取文件后的内容。
	 * @throws IOException
	 */
	public static String getFileContent(String path, String charsetName)
			throws IOException {
		String filecontent = "";
		try {
			File f = new File(path);
			if (f.exists()) {
				// FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream(path), charsetName)); // 建立BufferedReader对象，并实例化为br
				String line = br.readLine(); // 从文件读取一行字符串
				// 判断读取到的字符串是否不为空
				while (line != null) {
					filecontent += line + "\n";
					line = br.readLine(); // 从文件中继续读取一行数据
				}
				br.close(); // 关闭BufferedReader对象
				// fr.close(); //关闭文件
			}

		} catch (IOException e) {
			throw e;
		}
		return filecontent;
	}

	public static String getHtmlFileContent(String path, String charsetName)
			throws IOException {
		String filecontent = "";
		FileInputStream inputStream = null;
		InputStreamReader inputReader = null;
		BufferedReader br = null;
		try {
			File f = new File(path);
			if (f.exists()) {
				// FileReader fr = new FileReader(path);
				inputStream = new FileInputStream(path);
				inputReader = new InputStreamReader(inputStream, charsetName);
				br = new BufferedReader(inputReader); // 建立BufferedReader对象，并实例化为br
				String line = br.readLine(); // 从文件读取一行字符串
				// 判断读取到的字符串是否不为空
				while (line != null) {
					filecontent += line + "<br/>";
					line = br.readLine(); // 从文件中继续读取一行数据
				}
				br.close(); // 关闭BufferedReader对象
				// fr.close(); //关闭文件
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (inputReader != null) {
				inputReader.close();
			}
			if (br != null) {
				br.close();
			}
		}
		return filecontent;
	}

	/**
	 * @param in
	 *            源文件
	 * @param out
	 *            目标文件
	 * @return
	 * @throws Exception
	 */
	public static boolean CopyFile(File in, File out) throws Exception {
		try {
			FileInputStream fis = new FileInputStream(in);
			FileOutputStream fos = new FileOutputStream(out);
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
			fis.close();
			fos.close();
			return true;
		} catch (IOException ie) {
			ie.printStackTrace();
			return false;
		}
	}

	/**
	 * @param infile
	 *            源文件名
	 * @param outfile
	 *            目标文件名
	 * @return
	 * @throws Exception
	 */
	public static boolean CopyFile(String infile, String outfile)
			throws Exception {
		try {
			File in = new File(infile);
			File out = new File(outfile);
			return CopyFile(in, out);
		} catch (IOException ie) {
			ie.printStackTrace();
			return false;
		}

	}

	/**
	 * 将文件内容转换为二进制
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] getFileToByte(File file) {
		byte[] by = new byte[(int) file.length()];
		try {
			InputStream is = new FileInputStream(file);
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			byte[] bb = new byte[2048];
			int ch;
			ch = is.read(bb);
			while (ch != -1) {
				bytestream.write(bb, 0, ch);
				ch = is.read(bb);
			}
			by = bytestream.toByteArray();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return by;
	}

	public static void getByteToFile(byte[] bfile, String fileFullName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			file = new File(fileFullName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * 把Blob类型转换为byte数组类型
	 * 
	 * @param blob
	 * 
	 * @return
	 */

	public static  byte[] blobToBytes(Blob blob) {
		BufferedInputStream is = null;
		try {
			is = new BufferedInputStream(blob.getBinaryStream());
			byte[] bytes = new byte[(int) blob.length()];
			int len = bytes.length;
			int offset = 0;
			int read = 0;
			while (offset < len
					&& (read = is.read(bytes, offset, len - offset)) >= 0) {
				offset += read;
			}
			return bytes;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				is.close();
				is = null;
			} catch (IOException e) {
				return null;
			}
		}

	}
	/**
	 * InputStream=>byte[]
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static byte[] InputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}

	/**
	 * 文件大小转换
	 * 
	 * @param size
	 * @return
	 */
	public static String convertFileSize(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;

		float f = (float) size / kb;
		return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);

		// 文件大小转换为 KB单位

		// if (size >= gb) {
		// return String.format("%.1f GB", (float) size / gb);
		// } else if (size >= mb) {
		// float f = (float) size / mb;
		// return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
		// } else if (size >= kb) {
		// float f = (float) size / kb;
		// return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
		// } else{
		// return String.format("%d B", size);
		// }

	}


	
	/**
	 * 将二进制数据转换为文件内容
	 * @param filePaths  文件路径
	 * @param content 二进制内容数据
	 */
	public static void binaryDataToFile(String filePaths, byte[] content){
		try {
			File file = new File(filePaths);
			String parentPath = file.getParent(); 
			File filedir =new File(parentPath);    
			//如果文件夹不存在则创建    
			if  (!filedir.exists()  && !filedir.isDirectory())      
			{       
				filedir.mkdirs();    
			} 
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			/**
			 * 将二进制数据写入文件中
			 */
			FileOutputStream fop = new FileOutputStream(file);
			fop.write(content);
			fop.flush();
			fop.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取文件夹下文件名
	 * @param filepath 文件夹路径
	 * */
	public static String[] GetFileName(String filepath)
	{
		File file = new File(filepath);
		return file.list();
	}
	/**
	 * 读取文件内容
	 * @param filepath 文件路径
	 * */
	  public static String GetFileString(String filepath){
		  String fileContent = "";     
		    try   
		    {       
		        File f = new File(filepath);      
		        if(f.isFile()&&f.exists())  
		        {       
		            InputStreamReader read = new InputStreamReader(new FileInputStream(f),"gbk");       
		            BufferedReader reader=new BufferedReader(read);       
		            String line;       
		            while ((line = reader.readLine()) != null)   
		            {        
		                fileContent += line;       
		            }         
		            read.close();      
		        }     
		    } catch (Exception e)   
		    {         
		        e.printStackTrace();     
		    }     
		    return fileContent;   
	    }
}
