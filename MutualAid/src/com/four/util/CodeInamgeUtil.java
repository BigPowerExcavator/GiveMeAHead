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
	private static int width = 180;// ����ͼƬ��width
	private static int height = 50;// ����ͼƬ��height
	private static int codeCount = 4;// ����ͼƬ����ʾ��֤��ĸ���
	private static int xx = 30;
	private static int fontHeight = 40;
	private static  int codeY = 40;
	private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
	            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private static Random random = new Random();
	
	/*
	 * ����һ��map����
	 * codeΪ���ɵ���֤��
	 * codePicΪ���ɵ���֤���BufferedImage����
	 */
	public Map<String , Object>generateCodeAndPic(){
		//����ͼ��buffer
		BufferedImage bufferImg=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D gd = (Graphics2D)bufferImg.getGraphics();
		// ����һ���������������
		
		// ��ͼ�����Ϊ��ɫ
		gd.setColor(new Color(107, 153, 141));
		gd.fillRect(0, 0, width, height);
		
		// �������壬����Ĵ�СӦ�ø���ͼƬ�ĸ߶�������
		Font font = new Font("΢���ź�", Font.BOLD, fontHeight);
		// �������塣
		gd.setFont(font);
		/*// ���߿�
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);*/
		
		// �������40�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽��
		gd.setColor(Color.WHITE);
		for (int i = 0; i < 30; i++) {
			gd=randomSetColor(gd);
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}
		
		// randomCode���ڱ��������������֤�룬�Ա��û���¼�������֤��
		StringBuffer randomCode = new StringBuffer();
		//int red = 0, green = 0, blue = 0;
		
		// �������codeCount���ֵ���֤�롣
		for (int i = 0; i < codeCount; i++) {
			//��ת��Ť��
			int rotateOrWarp = random.nextInt(2);
			// �õ������������֤�����֡�
			String code = String.valueOf(codeSequence[random.nextInt(36)]);
			/*// �����������ɫ������������ɫֵ�����������ÿλ���ֵ���ɫֵ������ͬ��
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			// �������������ɫ����֤����Ƶ�ͼ���С�
			gd.setColor(new Color(red, green, blue));*/
			gd=randomSetColor(gd);
			//gd.drawString(code, (i + 1) * xx, codeY);
			//��һ��ѭ����ʱ���ʼ��
			if(rotateOrWarp == 0){
				//����ת����
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

			// ���������ĸ�����������һ��
			randomCode.append(code);
		}
		Map<String,Object> map  =new HashMap<String,Object>();
		//�����֤��
		map.put("code", randomCode.toString());
		//������ɵ���֤��BufferedImage����
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
		// �����������ɫ������������ɫֵ�����������ÿλ���ֵ���ɫֵ������ͬ��
		int red = 0, green = 0, blue = 0;
		red = random.nextInt(255);
		green = random.nextInt(255);
		blue = random.nextInt(255);

		// �������������ɫ����֤����Ƶ�ͼ���С�
		gd.setColor(new Color(red, green, blue));
		return gd;
	}
}
