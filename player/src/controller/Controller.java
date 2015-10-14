package controller;

import generall.Track;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import model.Model;
import view.View;

class Controller implements ActionListener, BasicPlayerListener {
	View view;
	Model model;

	BasicPlayer player;

	public Controller() {
		view = new View(this);
		model = new Model();
		player = new BasicPlayer();
		player.addBasicPlayerListener(this);
	}

	public static void main(String[] args) {
		new Controller();
	}

	private void openFiles() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("Music", "mp3",
				"ogg", "flac"));
		chooser.setMultiSelectionEnabled(true);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File[] files = chooser.getSelectedFiles();
			try {
				for (File f : files) {
					model.playlist.add(f);
				}
			} catch (Exception e) {
			}

			for (Track t : model.playlist.getTracks()) {
				System.out.println(t);
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			try {
				switch (e.getActionCommand()) {
				case "play":
					switch (player.getStatus()) {
					case BasicPlayer.STOPPED:
						player.open(model.playlist.getCurrent().getFile());
					case BasicPlayer.PAUSED:
						player.play();
						break;
					case BasicPlayer.PLAYING:
						player.pause();
						break;
					}
					break;
				case "open":
					openFiles();
					view.topPanel.updateNotes(model.playlist.getCurrent());
					if (player.getStatus() != player.PLAYING) {
						player.open(model.playlist.getCurrent().getFile());
						player.play();
					}
					break;
				case "next":
					model.playlist.next();
					view.topPanel.updateNotes(model.playlist.getCurrent());
					player.open(model.playlist.getCurrent().getFile());
					player.play();
					break;
				case "prev":
					model.playlist.prev();
					view.topPanel.updateNotes(model.playlist.getCurrent());
					player.open(model.playlist.getCurrent().getFile());
					player.play();
					break;
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
	public void opened(Object stream, Map properties) {
		System.out.println(properties);
	}

	@Override
	public void progress(int bytesread, long microseconds, byte[] pcmdata,
			Map properties) {
	}

	public void stateUpdated(BasicPlayerEvent event) {
		try {
			switch (event.getCode()) {
			case BasicPlayerEvent.STOPPED:
				model.playlist.next();
				view.topPanel.updateNotes(model.playlist.getCurrent());
				player.open(model.playlist.getCurrent().getFile());
				player.play();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public void setController(BasicController controller) {
	}
}