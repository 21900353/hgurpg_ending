package ending;

import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


public class EndingAnimation extends JFrame {
	// slideshow
	private JLabel scrSlideshow;
	private String[] scrArr = {
			"img/scr0.png",
			"img/scr1.png",
			"img/scr2.png"
			};
	Timer tm;
	private int i = 0;
	
	// user picture
	private JLabel player;
	
	// text
	private JLabel text;
	
	// BGM
	Clip clip;
	
	public EndingAnimation() {
		super("Ending");
		scrSlideshow = new JLabel();
		scrSlideshow.setBounds(0, 0, 320, 620);
		
		tm = new Timer(2000, new ActionListener() {
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
		
		text = new JLabel("그 땐 그랬지...");

		scrSlideshow.setLayout(new BorderLayout());
		add(scrSlideshow);
				
		// Window
		setLayout(null);
        setSize(320, 620);
        setVisible(true);

        startEnding();	// call everything
	}

	
	// ***** METHODS *****
	
	public void startEnding() {
		tm.start();
		playBGM();
		
		// Stop bgm when closing
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                stopBGM();
            }
        });
		
		drawPlayer();
		//showText();
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
	
	public void drawPlayer() {
		try {
			BufferedImage imgPlayer = ImageIO.read(this.getClass().getResource("/resources/img/user_graduation.png"));
			ImageIcon icon = new ImageIcon(imgPlayer);
			player.setIcon(icon);
			player.setVerticalAlignment(JLabel.BOTTOM);
			player.setBorder(new EmptyBorder(0,0,38,0));
			scrSlideshow.add(player);
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "로고 이미지파일 읽기 오류", "오류", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void showText() {
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setVerticalAlignment(JLabel.CENTER);
		text.setFont(new Font("궁서", Font.ITALIC, 40));
		scrSlideshow.add(text);
	}
}
