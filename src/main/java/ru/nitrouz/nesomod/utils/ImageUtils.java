package ru.nitrouz.nesomod.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImageUtils {
    private final static Integer width = 352;
    private final static Integer height = 512;

    public static Boolean resizeImage(File sourceImg, String codeName) {
        BufferedImage origImage;
        try {

            origImage = ImageIO.read(sourceImg);
            int type = origImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : origImage.getType();

            int fheight = height;
            int fwidth = width;

            //Work out the resized width/height
            if (origImage.getHeight() > height || origImage.getWidth() > width) {
                fheight = height;
                int wid = width;
                float sum = (float) origImage.getWidth() / (float) origImage.getHeight();
                fwidth = Math.round(fheight * sum);

                if (fwidth > wid) {
                    //rezise again for the width this time
                    fheight = Math.round(wid / sum);
                    fwidth = wid;
                }
            }

            BufferedImage resizedImage = new BufferedImage(fwidth, fheight, type);
            Graphics2D g = resizedImage.createGraphics();
            g.setComposite(AlphaComposite.Src);

            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g.drawImage(origImage, 0, 0, fwidth, fheight, null);
            g.dispose();

            ImageIO.write(resizedImage, "jpg", new File("."+File.separator+"cache"+File.separator+codeName+".jpg"));

        } catch (IOException ex) {
            Logger.getLogger(ImageUtils.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}