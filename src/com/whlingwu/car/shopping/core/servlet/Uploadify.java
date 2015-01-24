/** * 文件名：Uploadify.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.servlet;

/** 项目名称：carShopping 
 * 类名称：Uploadify 
 * jQuery文件上传插件
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午10:31:40 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午10:31:40 
 * 修改备注：
 * @version 1.0* */

public class Uploadify extends HttpServlet{
	private static final long serialVersionUID = 1L;  
	  
    /** 
     * 实现多文件的同时上传 
     */   
    public void doGet(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
         System.out.println("===========");
        //设置接收的编码格式  
        request.setCharacterEncoding("UTF-8");  
        Date date = new Date();//获取当前时间  
        SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMddHHmmss");  
        SimpleDateFormat sdfFolder = new SimpleDateFormat("yyMM");  
//        String newfileName = sdfFileName.format(date);//文件名称  
        String fileRealPath = "";//文件存放真实地址  
          
        String fileRealResistPath = "";//文件存放真实相对路径  
          
        //名称  界面编码 必须 和request 保存一致..否则乱码  
        String name = request.getParameter("name");  
        String id = request.getParameter("id");  //内容的ID,必须先添加内容，然后才能上传图片
//        String newfileName = name;    
           
        String firstFileName="";  
        // 获得容器中上传文件夹所在的物理路径  
        String savePath = this.getServletConfig().getServletContext().getRealPath("/") + "upload\\" + id +"\\";  
        System.out.println("路径" + savePath+"; name:"+name);  
        File file = new File(savePath);  
        if (!file.isDirectory()) {  
            file.mkdirs();  
        }  
  
        try {  
            DiskFileItemFactory fac = new DiskFileItemFactory();  
            ServletFileUpload upload = new ServletFileUpload(fac);  
            upload.setHeaderEncoding("UTF-8");
//            upload.setFileItemFactory(factory)
            System.out.println("request：＝"+request);
            // 获取多个上传文件  
            List fileList = fileList = upload.parseRequest(request);
            System.out.println("fileList:"+fileList);
            // 遍历上传文件写入磁盘  
            Iterator it = fileList.iterator();  
            while (it.hasNext()) {  
            	Object obit = it.next();
            	if(obit instanceof DiskFileItem){
            		System.out.println("xxxxxxxxxxxxx");
	                DiskFileItem item = (DiskFileItem) obit;  
	                  
	                // 如果item是文件上传表单域     
	                // 获得文件名及路径     
	                String fileName = item.getName();  
	                if (fileName != null) {  
	                    firstFileName=item.getName().substring(item.getName().lastIndexOf("\\")+1);  
	                    String formatName = firstFileName.substring(firstFileName.lastIndexOf("."));//获取文件后缀名  
	                    fileRealPath = savePath + fileName;//+ formatName;//文件存放真实地址  
	                      
	                    BufferedInputStream in = new BufferedInputStream(item.getInputStream());// 获得文件输入流  
	                    BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(new File(fileRealPath)));// 获得文件输出流  
	                    Streams.copy(in, outStream, true);// 开始把文件写到你指定的上传文件夹  
	                    //上传成功，则插入数据库  
	                    if (new File(fileRealPath).exists()) {  
	                        //虚拟路径赋值  
	                        fileRealResistPath=sdfFolder.format(date)+"/"+fileRealPath.substring(fileRealPath.lastIndexOf("\\")+1);  
	                        //保存到数据库  
	                        System.out.println("保存到数据库:");  
	                        System.out.println("name:"+name);  
	                        System.out.println("虚拟路径:"+fileRealResistPath);  
	                    }  
	                       
	                }   
            	}
            }   
        } catch (org.apache.commons.fileupload.FileUploadException ex) {
    	   ex.printStackTrace();  
           System.out.println("没有上传文件");  
           return;  
		}   
       response.getWriter().write("1");  
          
    }  
   
    public void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doGet(req, resp);  
    }  
}
