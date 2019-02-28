package com.appchina.collect.utils;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

/**
 * 图片处理工具类：<br>
 * 功能：缩放图像、切割图像、图像类型转换、彩色转黑白、文字水印、图片水印等
 * 
 * @author Administrator
 */
public class ImageUtils {


	/**
	 * 缩放图像（按比例缩放）
	 * 
	 * @param src
	 *            源图像文件地址
	 * @param result
	 *            缩放后的图像地址
	 * @param scale
	 *            缩放比例
	 * @param flag
	 *            缩放选择:true 放大; false 缩小;
	 */
	public static BufferedImage scale(BufferedImage src, int scale, boolean flag) {
		int type = (src.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		int width = src.getWidth(); // 得到源图宽
		int height = src.getHeight(); // 得到源图长
		if (flag) {// 放大
			width = width * scale;
			height = height * scale;
		} else {// 缩小
			width = width / scale;
			height = height / scale;
		}
		Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		BufferedImage tag = new BufferedImage(width, height, type);
		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null); // 绘制缩小后的图
		g.dispose();
		return tag;
	}

	/**
	 * 缩放图像（按比例缩放）
	 * 
	 * @param srcImageFile
	 *            源图像文件地址
	 * @param result
	 *            缩放后的图像地址
	 * @param flag
	 *            缩放选择:true 放大; false 缩小;
	 */
	public static BufferedImage scale(BufferedImage src, int width, int height) {
		int type = (src.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage tag = new BufferedImage(width, height, type);

		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null); // 绘制缩小后的图
		g.dispose();
		return tag;
	}

	/**
	 * 缩放图像（按高度和宽度缩放）
	 * 
	 * @param srcImageFile
	 *            源图像文件地址
	 * @param result
	 *            缩放后的图像地址
	 * @param height
	 *            缩放后的高度
	 * @param width
	 *            缩放后的宽度
	 * @param bb
	 *            比例不对时是否需要补白：true为补白; false为不补白;
	 */
	public static BufferedImage scaleWithWhite(BufferedImage src, int height, int width, boolean addWhite) {
		double ratio = 0.0; // 缩放比例
		Image itemp = src.getScaledInstance(width, height, src.SCALE_SMOOTH);
		// 计算比例
		if ((src.getHeight() > height) || (src.getWidth() > width)) {
			if (src.getHeight() > src.getWidth()) {
				ratio = (new Integer(height)).doubleValue() / src.getHeight();
			} else {
				ratio = (new Integer(width)).doubleValue() / src.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
			itemp = op.filter(src, null);
		}
		if (addWhite) {// 补白
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);
			if (width == itemp.getWidth(null))
				g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null),
						itemp.getHeight(null), Color.white, null);
			else
				g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null),
						Color.white, null);
			g.dispose();
			itemp = image;
		}
		return (BufferedImage) itemp;
	}

	/**
	 * 图像切割(按指定起点坐标和宽高切割)
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param result
	 *            切片后的图像地址
	 * @param x
	 *            目标切片起点坐标X
	 * @param y
	 *            目标切片起点坐标Y
	 * @param width
	 *            目标切片宽度
	 * @param height
	 *            目标切片高度
	 */
	public static BufferedImage cut(BufferedImage src, int x, int y, int width, int height) {
		int type = (src.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		int srcWidth = src.getWidth(); // 源图宽度
		int srcHeight = src.getHeight(); // 源图高度

		Image image = src.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
		// 四个参数分别为图像起点坐标和宽高
		ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
		BufferedImage tag = new BufferedImage(width, height, type);
		Graphics g = tag.getGraphics();
		g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
		g.dispose();
		// 输出为文件
		return tag;
	}

	/**
	 * 图像切割(按指定起点坐标和宽高切割)
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param result
	 *            切片后的图像地址
	 * @param x
	 *            目标切片起点坐标X
	 * @param y
	 *            目标切片起点坐标Y
	 * @param width
	 *            目标切片宽度
	 * @param height
	 *            目标切片高度
	 */
	public static BufferedImage cutCenter(BufferedImage src, int width, int height) {
		int type = (src.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		int srcWidth = src.getWidth(); // 源图宽度
		int srcHeight = src.getHeight(); // 源图高度
		Image image = src.getScaledInstance(srcWidth, srcHeight, Image.SCALE_SMOOTH);
		// 四个参数分别为图像起点坐标和宽高
		ImageFilter cropFilter = new CropImageFilter((srcWidth - width) / 2, (srcHeight - height) / 2, width, height);
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
		BufferedImage tag = new BufferedImage(width, height, type);
		Graphics g = tag.getGraphics();
		g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
		g.dispose();

		return tag;
	}

	/**
	 * 图像类型转换：GIF->JPG、GIF->PNG、PNG->JPG、PNG->GIF(X)、BMP->PNG
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param formatName
	 *            包含格式非正式名称的 String：如JPG、JPEG、GIF等
	 * @param destImageFile
	 *            目标图像地址
	 */
	public static void convert(String srcImageFile, String formatName, String destImageFile) {
		try {
			File f = new File(srcImageFile);
			f.canRead();
			f.canWrite();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, formatName, new File(destImageFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 彩色转为黑白
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 */
	public static void gray(String srcImageFile, String destImageFile) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile));
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			src = op.filter(src, null);
			ImageIO.write(src, "JPEG", new File(destImageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给图片添加文字水印(换行)
	 * 
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param fontName
	 *            水印的字体名称
	 * @param fontStyle
	 *            水印的字体样式
	 * @param color
	 *            水印的字体颜色
	 * @param fontSize
	 *            水印的字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public static BufferedImage pressTextWithChangeLine(String pressText, Image src, String fontName, int fontStyle,
                                                        Color color, int fontSize, int x, int y, float alpha, int sumWidth, int offsetHeight) {

		int width = src.getWidth(null);
		int height = src.getHeight(null);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.drawImage(src, 0, 0, width, height, null);
		g.setColor(color);
		g.setFont(new Font(fontName, fontStyle, fontSize));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		// 在指定坐标绘制水印文字
		FontMetrics fm = g.getFontMetrics(new Font(fontName, fontStyle, fontSize));
		// 设置换行操作
		int fontHeight = fm.getHeight(); // 字符的高度
		int offsetLeft = 0;
		int rowIndex = 1;
		for (int i = 0; i < pressText.length(); i++) {
			char c = pressText.charAt(i);
			int charWidth = fm.charWidth(c); // 字符的宽度
			// 另起一行
			if (Character.isISOControl(c) || offsetLeft >= (sumWidth - charWidth)) {
				rowIndex++;
				offsetLeft = 0;
			}
			g.drawString(String.valueOf(c), offsetLeft + x, rowIndex * (fontHeight + offsetHeight) + y); // 把一个个写到图片上
			offsetLeft += charWidth; // 设置下字符的间距
		}
		g.dispose();
		return image;
	}

	/**
	 * 给图片添加文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param fontName
	 *            字体名称
	 * @param fontStyle
	 *            字体样式
	 * @param color
	 *            字体颜色
	 * @param fontSize
	 *            字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public static BufferedImage pressText(String pressText, Image src, String fontName, int fontStyle, Color color,
                                          int fontSize, int x, int y, float alpha) {
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.drawImage(src, 0, 0, width, height, null);
		g.setColor(color);
		g.setFont(new Font(fontName, fontStyle, fontSize));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		// 在指定坐标绘制水印文字
		g.drawString(pressText, x, y);
		g.dispose();
		return image;
	}

	/**
	 * 倒序添加文字
	 * 
	 * @param pressText
	 * @param src
	 * @param fontName
	 * @param fontStyle
	 * @param color
	 * @param fontSize
	 * @param x
	 * @param y
	 * @param alpha
	 * @return
	 */
	public static BufferedImage pressTextRevert(String pressText, Image src, String fontName, int fontStyle,
                                                Color color, int fontSize, int x, int y, float alpha) {
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.drawImage(src, 0, 0, width, height, null);
		g.setColor(color);
		g.setFont(new Font(fontName, fontStyle, fontSize));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		// 在指定坐标绘制水印文字
		g.drawString(pressText, (width - (getLength(pressText) * fontSize)) / 2 + x, (height - fontSize) / 2 + y);
		g.dispose();
		return image;
	}

	/**
	 * 给图片添加图片水印
	 * 
	 * @param pressImg
	 *            水印图片
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param x
	 *            修正值。 默认在中间
	 * @param y
	 *            修正值。 默认在中间
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public static BufferedImage pressImage(Image src_biao, Image src, int x, int y, float alpha) {
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.drawImage(src, 0, 0, width, height, null);
		// 水印文件
		int wideth_biao = src_biao.getWidth(null);
		int height_biao = src_biao.getHeight(null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		g.drawImage(src_biao, x, y, wideth_biao, height_biao, null);
		// 水印文件结束
		g.dispose();
		return image;
	}

	/**
	 * 计算text的长度（一个中文算两个字符）
	 * 
	 * @param text
	 * @return
	 */
	public static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}

	/**
	 * 拼接两张图片，上下拼
	 * 
	 * @param ImageOne
	 * @param ImageTwo
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage createPicByHeight(BufferedImage ImageOne, BufferedImage ImageTwo) throws IOException {
		int type = (ImageOne.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		// 读取第一张图片
		int width = ImageOne.getWidth();// 图片宽度
		int height = ImageOne.getHeight();// 图片高度
		// 从图片中读取RGB
		int[] ImageArrayOne = new int[width * height];
		ImageArrayOne = ImageOne.getRGB(0, 0, width, height, ImageArrayOne, 0, width);

		// 对第二张图片做相同的处理
		int widthTwo = ImageTwo.getWidth();// 图片宽度
		int heightTwo = ImageTwo.getHeight();// 图片高度
		int[] ImageArrayTwo = new int[widthTwo * heightTwo];
		ImageArrayTwo = ImageTwo.getRGB(0, 0, widthTwo, heightTwo, ImageArrayTwo, 0, widthTwo);

		// 生成新图片
		BufferedImage ImageNew = new BufferedImage(width, height + heightTwo, type);
		ImageNew.setRGB(0, 0, width, height, ImageArrayOne, 0, width);
		ImageNew.setRGB(0, height, widthTwo, heightTwo, ImageArrayTwo, 0, width);
		return ImageNew;
	}

	/**
	 * 左右拼
	 * 
	 * @param ImageOne
	 * @param ImageTwo
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage createPicByWidth(BufferedImage ImageOne, BufferedImage ImageTwo) throws IOException {
		int type = (ImageOne.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		// 读取第一张图片
		int width = ImageOne.getWidth();// 图片宽度
		int height = ImageOne.getHeight();// 图片高度
		// 从图片中读取RGB
		int[] ImageArrayOne = new int[width * height];
		ImageArrayOne = ImageOne.getRGB(0, 0, width, height, ImageArrayOne, 0, width);

		// 对第二张图片做相同的处理
		int widthTwo = ImageTwo.getWidth();// 图片宽度
		int heightTwo = ImageTwo.getHeight();// 图片高度
		int[] ImageArrayTwo = new int[widthTwo * heightTwo];
		ImageArrayTwo = ImageTwo.getRGB(0, 0, widthTwo, heightTwo, ImageArrayTwo, 0, widthTwo);

		// 生成新图片
		BufferedImage ImageNew = new BufferedImage(width + widthTwo + 10, height + 20, type);
		ImageNew.setRGB(0, 10, width, height, ImageArrayOne, 0, width);
		ImageNew.setRGB(width + 10, 10, widthTwo, heightTwo, ImageArrayTwo, 0, width);
		return ImageNew;
	}

	/**
	 * 设置图片四个圆角
	 * 
	 * @param image
	 * @param cornerRadius
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage makeRoundedCorner(BufferedImage src, int cornerRadius) throws IOException {
		int type = (src.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		int w = src.getWidth();
		int h = src.getHeight();
		BufferedImage output = new BufferedImage(w, h, type);
		Graphics2D g2 = output.createGraphics();
		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
		g2.setComposite(AlphaComposite.SrcAtop);
		g2.drawImage(src, 0, 0, null);

		g2.dispose();
		return output;
	}

	/**
	 * 获取一行字体的宽度
	 * 
	 * @param text
	 * @param fontName
	 * @param fontStyle
	 * @param fontSize
	 * @return
	 */
	public static int getFontWidth(String text, String fontName, int fontStyle, int fontSize) {
		Font f = new Font(fontName, fontStyle, fontSize);
		FontMetrics fm = new JLabel().getFontMetrics(f);
		int width = fm.stringWidth(text);
		return width;
	}

	public static int getFontHeight(String text, String fontName, int fontStyle, int fontSize, int sumWidth,
                                    int offsetHeight) {
		Font f = new Font(fontName, fontStyle, fontSize);
		FontMetrics fm = new JLabel().getFontMetrics(f);
		// 设置换行操作
		int fontHeight = fm.getHeight(); // 字符的高度
		int offsetLeft = 0;
		int rowIndex = 1;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			int charWidth = fm.charWidth(c); // 字符的宽度
			// 另起一行
			if (Character.isISOControl(c) || offsetLeft >= (sumWidth - charWidth)) {
				rowIndex++;
				offsetLeft = 0;
			}
			offsetLeft += charWidth; // 设置下字符的间距
		}

		return rowIndex * (fontHeight + offsetHeight);
	}

	/**
	 * 给图片打马赛克
	 * 
	 * @param src
	 * @param des
	 * @param mosaicSize
	 * @throws Exception
	 */
	public static void mosaic(String src, String des, int mosaicSize) throws Exception {
		if (StringUtils.isEmpty(src) || StringUtils.isEmpty(des)) {
			return;
		}
		File file = new File(src);
		if (!file.exists()) {
			return;
		}
		BufferedImage img = ImageIO.read(new File(src));
		int imageWidth = img.getWidth(null);
		int imageHeight = img.getHeight(null);

		BufferedImage mosaicPic = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		int x = 0;
		int y = 0;
		int xCnt = 0;
		int yCnt = 0;
		if (imageWidth % mosaicSize == 0) {
			xCnt = imageWidth / mosaicSize;
		} else {
			xCnt = imageWidth / mosaicSize + 1;
		}
		if (imageHeight % mosaicSize == 0) {
			yCnt = imageHeight / mosaicSize;
		} else {
			yCnt = imageHeight / mosaicSize + 1;
		}

		Graphics gs = mosaicPic.getGraphics();
		for (int i = 0; i < xCnt; i++) {
			for (int j = 0; j < yCnt; j++) {
				int mosaicWidth = mosaicSize;
				int mosaicHeight = mosaicSize;
				if (i == xCnt - 1) {
					mosaicWidth = imageWidth - x;
				}
				if (j == yCnt - 1) {
					mosaicHeight = imageHeight - y;
				}
				int centerX = x;
				int centerY = y;
				if (mosaicWidth % 2 == 0) {
					centerX += mosaicWidth / 2;
				} else {
					centerX += (mosaicWidth - 1) / 2;
				}
				if (mosaicHeight % 2 == 0) {
					centerY += mosaicHeight / 2;
				} else {
					centerY += (mosaicHeight - 1) / 2;
				}
				Color color = new Color(img.getRGB(centerX, centerY));
				gs.setColor(color);
				gs.fillRect(x, y, mosaicWidth, mosaicHeight);
				y = y + mosaicSize;
			}
			y = 0;
			x = x + mosaicSize;
		}
		gs.dispose();
		if (StringUtils.indexOf(src, ".jpg") > 0) {
			ImageIO.write(mosaicPic, "jpg", new File(des));
		} else {
			ImageIO.write(mosaicPic, "png", new File(des));
		}
	}
}
