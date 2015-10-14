package view;

import generall.Track;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class View extends JFrame {
	static int WIDTH = 700;
	static int HEIGHT = 500;

	// Icons used for buttons
	private ImageIcon iconOpen = new ImageIcon(getClass().getResource(
			"/images/Open.png"));
	private ImageIcon iconPlay = new ImageIcon(getClass().getResource(
			"/images/Play.gif"));
	private ImageIcon iconStop = new ImageIcon(getClass().getResource(
			"/images/Stop.gif"));
	private ImageIcon iconPause = new ImageIcon(getClass().getResource(
			"/images/Pause.png"));

	/**
	 * 중앙화면
	 * 
	 * <pre>
	 * - 제목/가수 
	 * - 재생 등 각종 정보(프로그래스, 정지, ...)
	 * - 가사 화면
	 * </pre>
	 */
	public class TopPanel extends JPanel {
		private JPanel _notes;
		private JPanel _controls;
		private JPanel _lyric;
		private JPanel _top;

		private Map<String, String> _labels;

		final int height = View.HEIGHT;
		final int width = View.WIDTH - 200;

		@SuppressWarnings("serial")
		TopPanel(EventListener listener) {
			// 초기화
			_labels = new HashMap<String, String>();
			_labels.put("title", "<nothing>");
			_labels.put("artist", "<nothing>");

			_notes = new JPanel() {
				private JLabel[] jlabels;
				private final int TITLE = 0;
				private final int ARTIST = 1;
				{
					setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
					jlabels = new JLabel[2];
					add(jlabels[TITLE] = new JLabel(_labels.get("title")));
					add(jlabels[ARTIST] = new JLabel(_labels.get("artist")));
					setOpaque(false);
				}

				@Override
				public void updateUI() {
					super.updateUI();
					if (jlabels != null) {
						jlabels[TITLE].setText(_labels.get("title"));
						jlabels[ARTIST].setText(_labels.get("artist"));
					}
				}
			};

			_controls = new JPanel() {
				JButton[] jbuttons = new JButton[3];
				private final int PREV = 0;
				private final int PLAY = 1;
				private final int NEXT = 2;
				{
					add(new JButton("prev") {
						{
							setActionCommand("prev");
							addActionListener((ActionListener) listener);
							setFont(new Font("Sans", Font.BOLD, 14));
							// setIcon(iconOpen);
							setEnabled(false);
						}
					});
					add(new JButton("play") {
						{
							setActionCommand("play");
							addActionListener((ActionListener) listener);
							setFont(new Font("Sans", Font.BOLD, 14));
							setIcon(iconPlay);
						}
					});
					add(new JButton("next") {
						{
							setActionCommand("next");
							addActionListener((ActionListener) listener);
							setFont(new Font("Sans", Font.BOLD, 14));
							// setIcon(iconOpen);
							setEnabled(false);
						}
					});

					add(new JButton("open") {
						{
							setActionCommand("open");
							addActionListener((ActionListener) listener);
							setFont(new Font("Sans", Font.BOLD, 14));
							setIcon(iconOpen);
						}
					});

				}

				@Override
				public void updateUI() {
					super.updateUI();
					if (jbuttons != null) {
						jbuttons[PREV].setText("prev");
						jbuttons[PLAY].setText("play");
						jbuttons[NEXT].setText("next");
					}
				}

			};

			// 레이아웃 추가
			_lyric = new JPanel();
			_top = new JPanel();

			_top.setLayout(new BorderLayout());
			_top.add(_notes, BorderLayout.NORTH);
			_top.add(_controls, BorderLayout.CENTER);

			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(width, height));

			add(_top, BorderLayout.NORTH);
			add(_lyric, BorderLayout.CENTER);

			// check view
			_lyric.setBorder(BorderFactory.createLineBorder(Color.black));
			_top.setBorder(BorderFactory.createLineBorder(Color.black));
			_controls.setBorder(BorderFactory.createLineBorder(Color.black));
			_notes.setBorder(BorderFactory.createLineBorder(Color.black));
		}

		public void updateNotes(Track track) {
			try {
				_labels.put("title", track.getTitle());
				_labels.put("artist", track.getArtist());
				_notes.updateUI();
				repaint();
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
			}

		}
	}

	/**
	 * 가사 화면
	 */
	public class LyricPanel extends JPanel {
		JPanel lyricPanel;

		final int height = View.HEIGHT - 200;
		final int width = View.WIDTH - 200;

		LyricPanel(EventListener listner) {
			lyricPanel = new JPanel();

			// b.addActionListener((ActionListener) listner);
			lyricPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			setPreferredSize(new Dimension(width, height));

			setBackground(Color.black);

		}

	}

	/**
	 * 오른쪽 화면 - 파일 목록
	 */
	public class ListPanel extends JPanel {
		JPanel topControl; // 정렬 기능
		JPanel centerList; // 목록

		final int height = View.HEIGHT;
		final int width = View.WIDTH - 500;

		ListPanel(EventListener listner) {
			topControl = new JPanel();
			centerList = new JPanel();

			topControl.setBorder(BorderFactory.createLineBorder(Color.black));
			centerList.setBorder(BorderFactory.createLineBorder(Color.black));

			// b.addActionListener((ActionListener) listner);
			setLayout(new BorderLayout());
			add(topControl, BorderLayout.NORTH);
			add(centerList, BorderLayout.CENTER);

			setPreferredSize(new Dimension(width, height));
		}
	}

	public ListPanel listPanel;

	public TopPanel topPanel;
	public LyricPanel LyricPanel;

	public View(EventListener listner) {
		super("JAVA MP3 player");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setSize(WIDTH, HEIGHT);
				setResizable(false);
				setLayout(new BorderLayout());

				add(topPanel = new TopPanel(listner), BorderLayout.CENTER);
				add(listPanel = new ListPanel(listner), BorderLayout.EAST);
				setDefaultCloseOperation(EXIT_ON_CLOSE);

				setVisible(true);
			}
		});
	}
}