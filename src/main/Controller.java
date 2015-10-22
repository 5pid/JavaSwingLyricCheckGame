package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JList;

import jaco.mp3.player.MP3Player;

//class Controller implements ActionListener, BasicPlayerListener {
class Controller implements ActionListener, MouseListener {
	View view;
	HashMap<String, String> itemsMap;
	MP3Player player;
	LyricChecker lChecker;

	public Controller() {
		view = new View(this, this);
		// player.addBasicPlayerListener(this);
		player = new MP3Player();
		player.setRepeat(true);

		itemsMap = FileListLoader.INSTANCE.getMp3FileMap();

	}

	public static void main(String[] args) {
		new Controller();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			try {
				switch (e.getActionCommand()) {
				case "stop":
					if (!player.isStopped()) {
						player.stop();
						System.out.println("stop!");
						break;
					} else if (player.isPaused()) {
						player.play();
						break;
					} else {

					}
				default:
					System.out.println("mur-r-r-r...");
					break;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		JList list = (JList) evt.getSource();
		if (evt.getClickCount() == 2) {
			int index = list.locationToIndex(evt.getPoint());
			String item = (String) list.getSelectedValue();

			System.out.println("선택한 노래: " + item);

			if (player.isStopped()) {
				player = new MP3Player(new File(itemsMap.get(item)));
				lChecker = new LyricChecker(item);
				String lyric = lChecker.getLyric();

				view.topPanel.updateTitle(item);
				view.topPanel.updateLyric(lyric);
				
				System.out.println(lyric);
				
				player.play();
			} else if (player.isPaused()) {
				player.play();
			} else {
				// 재생중인경우
				player.stop();
				player = new MP3Player(new File(itemsMap.get(item)));
				lChecker = new LyricChecker(item);
				String lyric = lChecker.getLyric();

				view.topPanel.updateTitle(item);
				view.topPanel.updateLyric(lyric);
				System.out.println(lyric);
				
				player.play();
			}

		} else {
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}