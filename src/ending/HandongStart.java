package ending;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class HandongStart implements ActionListener {
	
			
			static JFrame f1;
		//	JLabel l1,l2;
//			JFrame j1;
			JPanel p1;
			static int c_size = 320;
			static int l_size = 620;
			ImageIcon icon;
			JScrollPane scrollPane;
		
		public HandongStart(){			
			//System.out.print(What is this?");
			
			try {
				InputStream logo = this.getClass().getResourceAsStream("/img/logo.png");
				icon = new ImageIcon(ImageIO.read(logo));
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "/img/logo.png 파일 읽기 오류", "오류", JOptionPane.ERROR_MESSAGE);
			}			
			
			f1 = new JFrame();
			@SuppressWarnings("serial")
			JPanel p1 = new JPanel() {
				public void paintComponent(Graphics g) {
					g.drawImage(icon.getImage(), 0, 0, 168, 54, null);
					setOpaque(false);
					super.paintComponent(g);
				}
			};
			f1.setSize(c_size, l_size); 
			f1.setResizable(false);//창의 크기를 변경하지 못하게
			p1.setBackground(Color.white);
			f1.setTitle("Handong RPG Game");
			f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f1.setLayout(null);
			p1.setLayout(null);
			
			
			JButton b1 = new JButton("New start");
			JButton b2 = new JButton("Resume");
			JButton b3 = new JButton("End");
			b1.setBounds(((c_size-150)/2),280,150,80);
			b2.setBounds(((c_size-150)/2),370,150,80);
			b3.setBounds(((c_size-150)/2),460,150,80);
			
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			
			p1.add(b1);
			p1.add(b2);
			p1.add(b3);
			scrollPane = new JScrollPane(p1);
		    f1.setContentPane(scrollPane);
			//f1.add(p1);
			f1.setVisible(true);
		}

		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand() == "New start") {
				//new GetCharacter();
			}
			if(e.getActionCommand() == "Resume") {
				//불러오기 파
			}
			if(e.getActionCommand() == "End") {
				System.exit(0);
			}
		}
		
	}
