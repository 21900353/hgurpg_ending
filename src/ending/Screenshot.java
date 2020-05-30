package ending;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Screenshot {
	// Component로 frame을 보낼 것
	static void captureScreen(Component cmp) {
		Rectangle rect = cmp.getBounds();
		 
	    try {
	        String fileName = scrFilenameNumbering();
	        BufferedImage scr = 
	        		new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_ARGB);
	        cmp.paint(scr.getGraphics());

	        ImageIO.write(scr, "png", new File(fileName));
	    } catch (Exception ex) {
	    	JOptionPane.showMessageDialog(null, "스크린샷 오류", "오류", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private static String scrFilenameNumbering() {
		int num = 0;
		String filename = "img/scr" + num + ".png";
		
		File f = new File(filename);
		while (f.exists()) {
			filename = "img/scr" + ++num + ".png";
			f = new File(filename);
		}
		
		return filename;
	}
}
