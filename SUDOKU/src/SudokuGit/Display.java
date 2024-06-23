package SudokuGit;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
public class Display extends JFrame implements ActionListener, MouseListener{
	
	private static JTextField text = new JTextField();
	private static ArrayList<JTextField> list = new ArrayList<>();
	private static ArrayList<String> str1 = new ArrayList<>();
	private static ArrayList<String> str2 = new ArrayList<>();
	private static JButton btn;
	
	public Display(String title, String[][] board) {
		setTitle(title);
		setBounds(getx(title),250,getxSize(title),getySize(title));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		if(title.equals("SUDOKU") || title.equals("SOLUTION")){
			Panel(panel);
			boardPaste(board, panel);
			add(panel, BorderLayout.CENTER);
		}else {
			setResizable(false);
			Button();
			panel.add(btn);
			add(panel, BorderLayout.CENTER);
		}
		setVisible(true);
		for(int i = 0;i < 9;i++) {
			for(int j = 0;j < 9;j++) {
			str1.add(Sudoku.answer[i][j]);
			}
		}
	}
	private void Panel(JPanel panel) {
		
		panel.setLayout(new GridLayout(9,9));
		panel.setBackground(Color.BLACK);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panel.requestFocus();
			}
		});
		
	}
	private void boardPaste(String[][] board, JPanel panel) {
		int cnt = 0;
		for(int i = 0;i < 9;i++) {
			for(int j = 0;j < 9;j++) {
				text = new JTextField();
				Text(board, i, j, cnt);
				highLight(cnt);
				panel.add(text);
				cnt++;
			}
			
		}
		
	}
	private void Text(String[][] board, int i, int j, int cnt) {
		if(board[i][j].equals("0")) {
			text.setText("");
			text.setBackground(Color.LIGHT_GRAY);
		}else {
			text.setText(board[i][j]);
			text.setBackground(Color.WHITE);
			text.setEnabled(false);
		}
		list.add(text);
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 40));
	}
	private void Button() {
		btn = new JButton("Done!");
		btn.setContentAreaFilled(false);
		btn.addActionListener(this);
		btn.setPreferredSize(new Dimension(150, 150));
		btn.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 30));
	}
	private void highLight(int cnt) {
		if(str1.size() != 0) {
				if(!(str1.get(cnt).equals(str2.get(cnt)))) {
				text.setDisabledTextColor(Color.RED);
			}
		}
		
	}
	private int getx(String title) {
		if(title.equals("SUDOKU")) {
			return 500;
		}else if(title.equals("SOLUTION")){
			return 1050;
		}else {
			return 300;
		}
	}
	private int getxSize(String title) {
		if(title.equals("Done")) {
			return 200;
		}else {
			return 550;
		}
	}
	private int getySize(String title) {
		if(title.equals("Done")) {
			return 200;
		}else {
			return 550;
		}
	}
	private int Dialog(String txt, String title) {
		JLabel label = new JLabel(txt);
		label.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 40));
		int option = JOptionPane.showConfirmDialog(label, txt, title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		System.out.println(option);
		return option;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int option;
		list.forEach(s-> str2.add(s.getText()));
		Verifying V = new Verifying();
		if(V.Verifying(str1, str2)) {
			option = Dialog("正解!", "おめでとう!");
			if(option == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}else {
			new Display("SOLUTION", Sudoku.answer);
			Dialog("不正解!", "残念、、、" );
			
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		btn.doClick(10);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	
}