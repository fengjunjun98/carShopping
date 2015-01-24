/** * 文件名：ValidateImage.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.core.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 项目名称：carShopping 
 * 类名称：ValidateImage 
 * 验证码
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午10:32:07 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午10:32:07 
 * 修改备注：
 * @version 1.0* */

public class ValidateImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int imageWidth = 70;
	private int imageHeight = 20;
	private int codeNumber = 4;
	private int backGround = 30;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		createCode(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void createCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = new BufferedImage(imageWidth,
				imageHeight, BufferedImage.TYPE_INT_BGR);
		Graphics2D g = bufferedImage.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, imageWidth - 1, imageHeight - 1);
		Random random = new Random();
		for (int i = 0; i < backGround; i++) {
			int red = random.nextInt(256);
			int green = random.nextInt(256);
			int blue = random.nextInt(256);
			g.setColor(new Color(red, green, blue));
			int start_X = random.nextInt(imageWidth);
			int start_Y = random.nextInt(imageHeight);
			if (random.nextInt(5) % 3 == 0) {
				int r = random.nextInt(10);
				int startAngle = random.nextInt(360);
				int arcAngle = random.nextInt(360);
				g.drawArc(start_X, start_Y, r, r, startAngle, arcAngle);
			} else {
				int change_X = random.nextInt() % 6;
				int change_Y = random.nextInt() % 6;
				g.drawLine(start_X, start_Y, start_X + change_X, start_Y
						+ change_Y);
			}
		}
		StringBuilder ValidateCode = new StringBuilder();
		// g.setFont(new Font("Blackoak Std", Font.BOLD, imageHeight-2));
		g.setFont(new Font("Tahoma", Font.BOLD, imageHeight - 3));
		for (int i = 0; i < codeNumber; i++) {
			int red = random.nextInt(100) + 50;
			int green = random.nextInt(100) + 50;
			int blue = random.nextInt(100) + 50;
			g.setColor(new Color(red, green, blue));
			String code = null;
			int type = random.nextInt(3);
			if (type == 1) {
				code = String.valueOf((char) (random.nextInt(26) + 65));
			} else if (type == 2) {
				code = String.valueOf((char) (random.nextInt(26) + 97));
			} else {
				code = String.valueOf(random.nextInt(10));
			}
			ValidateCode.append(code);
			g.drawString(code, imageWidth / codeNumber * i + 4, 16);
		}
		System.out.println("vcode="+ValidateCode.toString());
		request.getSession().setAttribute("validateCode",
				ValidateCode.toString());
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(bufferedImage, "jpeg", sos);
		bufferedImage = null;
		sos.close();
		random = null;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

