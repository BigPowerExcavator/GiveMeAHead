package com.four.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

public class CodeInamgeUtil {
	private static int width = 180;// 定义图片的width
	private static int height = 50;// 定义图片的height
	private static int codeCount = 4;// 定义图片上显示验证码的个数
	private static int xx = 30;
	private static int fontHeight = 40;
	private static  int codeY = 40;
	private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
	            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private static Random random = new Random();
	
	/*
	 * 生成一个map集合
	 * code为生成的验证码
	 * codePic为生成的验证码的BufferedImage对象
	 */
	public Map<String , Object>generateCodeAndPic(){
		//定义图像buffer
		BufferedImage bufferImg=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D gd = (Graphics2D)bufferImg.getGraphics();
		// 创建一个随机数生成器类
		
		// 将图像填充为白色
		gd.setColor(new Color(107, 153, 141));
		gd.fillRect(0, 0, width, height);
		
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("微软雅黑", Font.BOLD, fontHeight);
		// 设置字体。
		gd.setFont(font);
		/*// 画边框。
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);*/
		
		// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
		gd.setColor(Color.WHITE);
		for (int i = 0; i < 30; i++) {
			gd=randomSetColor(gd);
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}
		
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		//int red = 0, green = 0, blue = 0;
		
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			//旋转或扭曲
			int rotateOrWarp = random.nextInt(2);
			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random.nextInt(36)]);
			/*// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			// 用随机产生的颜色将验证码绘制到图像中。
			gd.setColor(new Color(red, green, blue));*/
			gd=randomSetColor(gd);
			//gd.drawString(code, (i + 1) * xx, codeY);
			//第一次循环的时候初始化
			if(rotateOrWarp == 0){
				//画旋转文字
	            double radianPercent = 0D;  
	            radianPercent =  Math.PI * (random.nextInt(40)/180D);  
	            if(random.nextBoolean()) radianPercent = -radianPercent;  
	            gd.rotate(radianPercent, (i + 1) * xx + 9, codeY);  
	            gd.drawString(code, (i + 1) * xx, codeY);  
	            gd.rotate(-radianPercent, (i + 1) * xx, codeY);  
			}else{
				gd.drawString(code, (i + 1) * xx, codeY);
			}
			//shear(gd,(i + 2) * xx,codeY);

			// 将产生的四个随机数组合在一起。
			randomCode.append(code);
		}
		Map<String,Object> map  =new HashMap<String,Object>();
		//存放验证码
		map.put("code", randomCode.toString());
		//存放生成的验证码BufferedImage对象
		map.put("codePic", bufferImg);
		return map;
		
	}
	
	private void shear(Graphics2D g, int w1, int h1) {
		shearX(g, w1, h1);
		shearY(g, w1, h1);
	}
	
	private static void shearX(Graphics2D g, int w1, int h1) {
		int period = random.nextInt(2);
 
		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);
 
		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (6.2831853071795862D * (double) phase)
							/ (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}
	}
 
	private void shearY(Graphics2D g, int w1, int h1) {
 
		int period = random.nextInt(8)+8;
 
		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (6.2831853071795862D * (double) phase)
							/ (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}
	}
	
	public Graphics2D randomSetColor(Graphics2D gd) {
		// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
		int red = 0, green = 0, blue = 0;
		red = random.nextInt(255);
		green = random.nextInt(255);
		blue = random.nextInt(255);

		// 用随机产生的颜色将验证码绘制到图像中。
		gd.setColor(new Color(red, green, blue));
		return gd;
	}
}
