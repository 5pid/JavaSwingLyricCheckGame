package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

@SuppressWarnings("serial")
public class View extends JFrame {
	static int WIDTH = 700;
	static int HEIGHT = 500;

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

		private JTextField[] answer;

		private Map<String, String> _labels;
		private Map<String, String> _lyricCheck;

		final int height = View.HEIGHT;
		final int width = View.WIDTH - 200;

		TopPanel(EventListener listener) {
			// 초기화
			_labels = new HashMap<String, String>();
			_labels.put("title", "<nothing>");

			// 주요 화면의 노래제목 부분
			_notes = new JPanel() {
				private JLabel[] jlabels;
				private final int TITLE = 0;

				{
					jlabels = new JLabel[1];
					add(jlabels[TITLE] = new JLabel(_labels.get("title")) {
						{
							setHorizontalAlignment(SwingConstants.CENTER);
							setFont(new Font("Sans", Font.BOLD, 16));
						}
					});
					setOpaque(false);
				}

				@Override
				public void updateUI() {
					super.updateUI();
					if (jlabels != null) {
						jlabels[TITLE].setText(_labels.get("title"));
					}
				}

			};

			// 주요 화면의 컨트롤 부분
			_controls = new JPanel() {
				JButton[] jbuttons = new JButton[3];
				// private final int PREV = 0;
				// private final int PLAY = 1;
				// private final int NEXT = 2;

				{
					add(new JButton("stop") {
						{
							setActionCommand("stop");
							addActionListener((ActionListener) listener);
							setFont(new Font("Sans", Font.BOLD, 14));
						}
					});
					add(new JButton("check") {
						{
							setActionCommand("check");
							addActionListener((ActionListener) listener);
							setFont(new Font("Sans", Font.BOLD, 14));
						}
					});
				}

				@Override
				public void updateUI() {
					super.updateUI();
					if (jbuttons != null) {
						// jbuttons[PREV].setText("prev");
						// jbuttons[PLAY].setText("play");
						// jbuttons[NEXT].setText("next");
					}
				}

			};

			// 노래제목이랑 컨트롤 합친 패널
			_top = new JPanel();

			_top.setLayout(new BorderLayout());
			_top.add(_notes, BorderLayout.NORTH);
			_top.add(_controls, BorderLayout.CENTER);

			// 가사창을 위한 변수 선언
			_lyricCheck = new HashMap<String, String>();
			_lyricCheck.put("lyric", "<nothing>");

			// 가사 패널
			try {
				_lyric = new JPanel(new BorderLayout()) {
					// JLabel lyric;
					JScrollPane lyricScroll;
					JTextPane lyric;

					{
						// add(lyric = new JLabel(_lyricCheck.get("lyric")) {
						// });
						lyric = new JTextPane() {
							{
								setContentType("text/html");
								setEditable(false);
								setBounds(0, 0, 200, 200);

								HTMLDocument doc = (HTMLDocument) getDocument();
								HTMLEditorKit editorKit = (HTMLEditorKit) getEditorKit();
								String text = _lyricCheck.get("lyric");
								editorKit.insertHTML(doc, doc.getLength(), text, 0, 0, null);

							}
						};

						lyricScroll = new JScrollPane(lyric);
						lyricScroll.setColumnHeaderView(new JLabel("빈 칸을 채워주세요"));
						lyricScroll.getVerticalScrollBar().setValue(lyricScroll.getVerticalScrollBar().getMaximum());

						setPreferredSize(new Dimension(200, 200));
						add(lyricScroll, BorderLayout.CENTER);
						// 하단 정답화면
						add(new JPanel() {
							{
								answer = new JTextField[4];
								answer[0] = new JTextField(8);
								answer[1] = new JTextField(8);
								answer[2] = new JTextField(8);
								answer[3] = new JTextField(8);

								for (int i = 0; i < answer.length; i++) {
									add(new JLabel((i + 1) + "."));
									add(answer[i]);
								}
							}
						}, BorderLayout.SOUTH);

					}

					@Override
					public void updateUI() {
						super.updateUI();
						if (lyric != null) {
							lyric.setText(_lyricCheck.get("lyric"));
							lyricScroll.getVerticalScrollBar().setValue(0);
						}
					}

				};
			} catch (BadLocationException | IOException e) {
				e.printStackTrace();
			}

			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(width, height));

			add(_top, BorderLayout.NORTH);
			add(_lyric, BorderLayout.CENTER);

			// check view
			// _notes.setBorder(BorderFactory.createLineBorder(Color.black));

		}

		// 입력한 답변 가져오기
		public ArrayList<String> getInputValue() {
			ArrayList<String> s = new ArrayList<>();
			System.out.println("=====================");
			System.out.println("getInputValue");
			for (int i = 0; i < 4; i++) {
				s.add(answer[i].getText());
				System.out.println(answer[i].getText());
			}
			System.out.println("=====================");
			return s;
		}

		/**
		 * 제목 새로고침
		 */
		public void updateTitle(String title) {
			try {
				_labels.put("title", title);
				_notes.updateUI();
				repaint();
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
			}

		}

		/**
		 * 가사 새로고침
		 */
		public void updateLyric(String val) {
			try {
				System.out.println(val);
				_lyricCheck.put("lyric", val);
				_lyric.updateUI();
				repaint();
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
			}

		}

	}

	/**
	 * 오른쪽 화면 - 파일 목록, 관리
	 */
	public class ListControlPanel extends JPanel {
		JPanel topControl; // 정렬 기능
		ListPanel centerList;

		private JList list;
		private DefaultListModel listModel;

		final int height = View.HEIGHT;
		final int width = View.WIDTH - 500;

		ListControlPanel(EventListener listener, MouseListener mListner) {

			// 정렬창
			topControl = new JPanel() {
				JButton[] jbuttons = new JButton[2];

				{
					add(new JButton("오름차순") {
						{
							setActionCommand("asc");
							addActionListener((ActionListener) listener);
							setFont(new Font("Sans", Font.BOLD, 12));
						}
					});
					add(new JButton("내림차순") {
						{
							setActionCommand("desc");
							addActionListener((ActionListener) listener);
							setFont(new Font("Sans", Font.BOLD, 12));
						}
					});
				}

				@Override
				public void updateUI() {
					super.updateUI();
				}

			};

			centerList = new ListPanel(mListner);

			// b.addActionListener((ActionListener) listner);
			setLayout(new BorderLayout());
			add(topControl, BorderLayout.NORTH);
			add(centerList, BorderLayout.CENTER);

			setPreferredSize(new Dimension(width, height));
		}

		/**
		 * 진짜 리스트뷰
		 */
		public class ListPanel extends JPanel {
			private JList list = null;
			private DefaultListModel listModel;

			public ListPanel(MouseListener listner) {
				super(new BorderLayout());

				FileListLoader fileList = FileListLoader.INSTANCE;
				list = new JList(fileList.getListModel(1));

				// 더블 클릭된 것 확인하기
				list.addMouseListener(listner);

				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list.setSelectedIndex(0);
				list.setVisibleRowCount(5);
				JScrollPane listScrollPane = new JScrollPane(list);

				add(listScrollPane, BorderLayout.CENTER);
			}

			// 테스트중
			public void sortItems(MouseListener listner, int how) {
				// how가 1이면 오름차순, 2이면 내림차순
				if (list != null) {
					remove(list);
				}

				FileListLoader fileList = FileListLoader.INSTANCE;
				list = new JList(fileList.getListModel(how));

				// 더블 클릭된 것 확인하기
				list.addMouseListener(listner);

				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list.setSelectedIndex(0);
				list.setVisibleRowCount(5);
				JScrollPane listScrollPane = new JScrollPane(list);

				add(listScrollPane, BorderLayout.CENTER);

			}

		}
	}

	/**
	 * 점수확인판
	 */
	public class CheckDialog extends JDialog {

		private String[] columns = new String[] { "문제", "정답", "입력값", "정답확인" };
		private final Class[] columnClass = new Class[] { Integer.class, String.class, String.class, Boolean.class };
		JPanel panel = null;

		void initUI(Object[][] data) {
			if (panel != null) {
				remove(panel);
			}
			panel = new JPanel() {
				{
					DefaultTableModel model = new DefaultTableModel(data, columns) {

						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}

						@Override
						public Class<?> getColumnClass(int columnIndex) {
							return columnClass[columnIndex];
						}
					};

					model.fireTableDataChanged();

					JTable table = new JTable(model);
					table.repaint();

					add(new JScrollPane(table) {
						{
							setPreferredSize(new Dimension(400, 90));
						}
					});

					pack();
				}
			};
			setLayout(new BorderLayout());
			setLocationRelativeTo(null);
			setSize(new Dimension(410, 140));
			setTitle("정답확인");

			add(panel);

			setVisible(true);
		}
	}

	public TopPanel topPanel;
	public ListControlPanel listPanel;
	public CheckDialog checkDialog;

	public View(EventListener listner, MouseListener mListner) {
		super("JAVA MP3 player");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setSize(WIDTH, HEIGHT);
				setResizable(false); // 조절 불가능
				setLayout(new BorderLayout());

				add(topPanel = new TopPanel(listner), BorderLayout.CENTER);
				add(listPanel = new ListControlPanel(listner, mListner), BorderLayout.EAST);
				checkDialog = new CheckDialog();
				setDefaultCloseOperation(EXIT_ON_CLOSE);

				// 화면 가운데 띄우기
				Dimension frameSize = getSize();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

				setVisible(true);
			}
		});
	}
}