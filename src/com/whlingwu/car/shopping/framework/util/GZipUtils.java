/** * 文件名：GZipUtils.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.framework.util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/** 项目名称：carShopping 
 * 类名称：GZipUtils 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午11:20:28 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午11:20:28 
 * 修改备注：
 * @version 1.0* */

public class GZipUtils {
	 public static final int BUFFER = 1024;   
	    public static final String EXT = ".gz";   
	  
	    /**  
	     * 数据压缩  
	     *   
	     * @param data  
	     * @return  
	     * @throws Exception  
	     */  
	    public static byte[] compress(byte[] data) throws IOException {   
	        ByteArrayInputStream bais = new ByteArrayInputStream(data);   
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();   
	        // 压缩   
	        compress(bais, baos);   
	  
	        byte[] output = baos.toByteArray();   
	  
	        baos.flush();   
	        baos.close();   
	  
	        bais.close();   
	  
	        return output;   
	    }   
	  
	    /**  
	     * 文件压缩  
	     *   
	     * @param file  
	     * @throws Exception  
	     */  
	    public static void compress(File file) throws Exception {   
	        compress(file, true);   
	    }   
	  
	    /**  
	     * 文件压缩  
	     *   
	     * @param file  
	     * @param delete  
	     *            是否删除原始文件  
	     * @throws Exception  
	     */  
	    public static void compress(File file, boolean delete) throws Exception {   
	        FileInputStream fis = new FileInputStream(file);   
	        FileOutputStream fos = new FileOutputStream(file.getPath() + EXT);   
	  
	        compress(fis, fos);   
	  
	        fis.close();   
	        fos.flush();   
	        fos.close();   
	  
	        if (delete) {   
	            file.delete();   
	        }   
	    }   
	  
	    /**  
	     * 数据压缩  
	     *   
	     * @param is  
	     * @param os  
	     * @throws Exception  
	     */  
	    public static void compress(InputStream is, OutputStream os)   
	            throws IOException {   
	  
	        GZIPOutputStream gos = new GZIPOutputStream(os);   
	  
	        int count;   
	        byte data[] = new byte[BUFFER];   
	        while ((count = is.read(data, 0, BUFFER)) != -1) {   
	            gos.write(data, 0, count);   
	        }   
	  
	        gos.finish();   
	  
	        gos.flush();   
	        gos.close();   
	    }   
	  
	    /**  
	     * 文件压缩  
	     *   
	     * @param path  
	     * @throws Exception  
	     */  
	    public static void compress(String path) throws Exception {   
	        compress(path, true);   
	    }   
	  
	    /**  
	     * 文件压缩  
	     *   
	     * @param path  
	     * @param delete  
	     *            是否删除原始文件  
	     * @throws Exception  
	     */  
	    public static void compress(String path, boolean delete) throws Exception {   
	        File file = new File(path);   
	        compress(file, delete);   
	    }   
	  
	    /**  
	     * 数据解压缩  
	     *   
	     * @param data  
	     * @return  
	     * @throws Exception  
	     */  
	    public static byte[] decompress(byte[] data) throws Exception {   
	        ByteArrayInputStream bais = new ByteArrayInputStream(data);   
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();   
	  
	        // 解压缩   
	  
	        decompress(bais, baos);   
	        data = baos.toByteArray();   
	  
	        baos.flush();   
	        baos.close();   
	  
	        bais.close();   
	  
	        return data;   
	    }   
	  
	    /**  
	     * 文件解压缩  
	     *   
	     * @param file  
	     * @throws Exception  
	     */  
	    public static void decompress(File file) throws Exception {   
	        decompress(file, true);   
	    }   
	  
	    /**  
	     * 文件解压缩  
	     *   
	     * @param file  
	     * @param delete  
	     *            是否删除原始文件  
	     * @throws Exception  
	     */  
	    public static void decompress(File file, boolean delete) throws Exception {   
	        FileInputStream fis = new FileInputStream(file);   
	        FileOutputStream fos = new FileOutputStream(file.getPath().replace(EXT,   
	                ""));   
	        decompress(fis, fos);   
	        fis.close();   
	        fos.flush();   
	        fos.close();   
	  
	        if (delete) {   
	            file.delete();   
	        }   
	    }   
	  
	    /**  
	     * 数据解压缩  
	     *   
	     * @param is  
	     * @param os  
	     * @throws Exception  
	     */  
	    public static void decompress(InputStream is, OutputStream os)   
	            throws Exception {   
	  
	        GZIPInputStream gis = new GZIPInputStream(is);   
	        int count;   
	        byte data[] = new byte[BUFFER];   
	        while ((count = gis.read(data, 0, BUFFER)) != -1) {   
	            os.write(data, 0, count);   
	        }   
	  
	        gis.close();   
	    }   
	  
	    /**  
	     * 文件解压缩  
	     *   
	     * @param path  
	     * @throws Exception  
	     */  
	    public static void decompress(String path) throws Exception {   
	        decompress(path, true);   
	    }   
	  
	    /**  
	     * 文件解压缩  
	     *   
	     * @param path  
	     * @param delete  
	     *            是否删除原始文件  
	     * @throws Exception  
	     */  
	    public static void decompress(String path, boolean delete) throws Exception {   
	        File file = new File(path);   
	        decompress(file, delete);   
	    }   

}

