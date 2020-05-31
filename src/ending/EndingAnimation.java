package ending;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


public class EndingAnimation extends JFrame {
	private JFrame frame;
	
	// animation
	private JLayeredPane animation;
	
	// slideshow
	private JLabel scrSlideshow;
	private String[] scrArr = {
			"img/scr0.png",
			"img/scr1.png",
			"img/scr2.png",
			"img/scr3.png"
			};
	private Timer tm;
	private int i = 0;
	
	// user picture
	private JLabel player;
	
	// text
	private JLabel text;
	
	// BGM
	Clip clip;
	
	public EndingAnimation() {
		super("Ending");
		frame = new JFrame("Ending");
		
		animation = new JLayeredPane();
		
		scrSlideshow = new JLabel();
		scrSlideshow.setBounds(0, 0, 320, 620);
		
		tm = new Timer(3000, new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				scrSlideshow.setIcon(new ImageIcon(scrArr[i]));
	            i += 1;
	            if(i >= scrArr.length) {
	                i = 0; 
	            }
	        }
		});
		tm.setInitialDelay(0);
		
		player = new JLabel();
		
		text = new JLabel("졸업이다.....");

		frame.setContentPane(animation);
				
		// Window
		//frame.setLayout(null);
		frame.setSize(320, 620);
        frame.setVisible(true);

        startEnding();	// call everything
	}

	
	// ***** METHODS *****
	
	public void startEnding() {
		showSlideshow();
		playBGM();
		
		// Stop bgm when closing
		frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                stopBGM();
            }
        });
		
		drawPlayer();
		showText();
	}
		
	public void playBGM() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("/resources/aud/ending_bgm.wav"));
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception e) {
	    	JOptionPane.showMessageDialog(null, "BGM 읽기 오류", "오류", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void stopBGM() {
		clip.stop();
	}
	
	public void showSlideshow() {
		animation.setPreferredSize(new Dimension(320, 620));
		animation.setLayer(scrSlideshow, 1);	// (component, depth)
		animation.add(scrSlideshow, 1);
		tm.start();
	}
	
	public void drawPlayer() {
		try {
			BufferedImage imgPlayer = ImageIO.read(this.getClass().getResource("/resources/img/user_graduation.png"));
			ImageIcon icon = new ImageIcon(imgPlayer);
			player.setIcon(icon);
			player.setBounds(0, 620-127-15, 129, 127);
			//player.setVerticalAlignment(JLabel.BOTTOM);
			player.setBorder(new EmptyBorder(0,0,38,0));
			animation.setLayer(player, 2);
			animation.add(player, 2);
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "로고 이미지파일 읽기 오류", "오류", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void showText() {
		//text.setHorizontalAlignment(JLabel.CENTER);
		//text.setVerticalAlignment(JLabel.CENTER);
		text.setBounds(50, 300, 300, 100);
		text.setFont(new Font("궁서", Font.ITALIC, 30));
		//scrSlideshow.add(text);
		animation.setLayer(text, 3);
		animation.add(text, 3);
	}
}
