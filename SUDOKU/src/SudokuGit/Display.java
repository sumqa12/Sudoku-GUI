package SudokuGit;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
public class Display extends JFrame implements ActionListener, MouseListener, KeyListener{
	
	private static JTextField text = new JTextField();
	private static ArrayList<JTextField> list = new ArrayList<>();
	private static ArrayList<JTextField> fieldList = new ArrayList<>();
	private static ArrayList<String> str1 = new ArrayList<>();
	private static ArrayList<String> str2 = new ArrayList<>();
	private static JButton btn;
	private static int firstVoidInt = 0;
	private static int finalVoidInt = 0;
	private static int listRow = 9;
	private static int listCol = 9;
	
	public Display(String title, String[][] board) {
		setTitle(title);
		setBounds(getx(title),gety(title),getxSize(title),getySize(title));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		if(title.equals("SUDOKU") || title.equals("SOLUTION")){
			Panel(panel);
			boardPaste(board, panel);
			add(panel, BorderLayout.CENTER);
		}else{
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
		fieldList.get(firstVoidInt()).grabFocus();
		System.out.println("first" + firstVoidInt() + "final" + finalVoidInt());
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
		text.addKeyListener(this);
		fieldList.add(text);
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
			return 450;
		}else if(title.equals("SOLUTION")){
			return 1000;
		}else {
			return 260;
		}
	}
	private int gety(String title) {
		if(title.equals("StopWatch")) {
			return 100;
		}else {
			return 250;
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
		
		if(Watch.stopwatch.isActive()) {
			Watch.start.setText("result");
			Watch.start.fire();
		}
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
	private int getRowInt() {
		for(int row = 0;row < listRow;row++) {
			for(int col = 0;col < listCol;col++) {
				if(getFocusInt() == row * listCol + col) {
					return row;
				}
			}
		}
		
		
		return 0;
	}
	private int getColInt() {
		for(int row = 0;row < listRow;row++) {
			for(int col = 0;col < listCol;col++) {
				if(getFocusInt() == row * listCol + col) {
					return col;
				}
			}
		}
		
		return 0;
	}
	private int getRowFinalInt() {
		int rowFinal = getRowInt() * listCol + 8;
		for(int i = rowFinal;i >= rowFinal - 8;i--) {
			if(fieldList.get(i).isEnabled()) {
				rowFinal = i;
				break;
			}
		}
		return rowFinal;
	}
	private int getRowFirstInt() {
		int rowFirst = getRowInt() * listCol;
		for(int i = rowFirst;i <= rowFirst + 8;i++) {
			if(fieldList.get(i).isEnabled()) {
				rowFirst = i;
				break;
			}
		}
		return rowFirst;
	}
	private int getColFinalInt() {
		int colFinal = getColInt() + listRow * 8;
		for(int i = colFinal;i >= getColInt();i -= listRow) {
			if(fieldList.get(i).isEnabled()) {
				colFinal = i;
				break;
			}
		}
		return colFinal;
	}
	private int getColFirstInt() {
		int colFirst = getColInt();
		for(int i = colFirst;i <= colFirst + listRow * 8;i += listRow) {
			if(fieldList.get(i).isEnabled()) {
				colFirst= i;
				break;
			}
		}
		return colFirst;
	}
	private int getFocusInt() {
		int focus = 0;
		for(int i = 0;i <= finalVoidInt();i++) {
			if(fieldList.get(i).hasFocus()) {
				focus = i;
			}
		}
		
		return focus;
	}
	private int rightFocusInt() {
		int focus = 0;
		for(int i = getFocusInt() + 1;i <= getRowFinalInt();i++) {
			if(fieldList.get(i).isEnabled()) {
				focus = i;
				break;
			}
		}
		
		return focus;
	}
	private int leftFocusInt() {
		int focus	= 0;
		for(int i = getFocusInt() - 1;i >= getRowFirstInt();i--) {
			if(fieldList.get(i).isEnabled()) {
				focus = i;
				break;
			}
		}
	
	return focus;
	}
	private int downFocusInt() {
		int focus = 0;
		for(int i = getFocusInt() + listRow;i <= getColFinalInt();i += listRow) {
			if(fieldList.get(i).isEnabled()) {
				focus = i;
				break;
			}
		}
		return focus;
	}
	private int upFocusInt() {
		int focus = 0;
		for(int i = getFocusInt() - listRow;i >= getColFirstInt();i -= listRow) {
			if(fieldList.get(i).isEnabled()) {
				focus = i;
				break;
			}
		}
		return focus;
	}
	private int nextFocusInt() {
		int focus = 0;
		for(int i = getFocusInt()+1;i < fieldList.size();i++) {
			if(fieldList.get(i).isEnabled()) {
				focus = i;
				break;
			}
		}
		return focus;
	}
	private int backFocusInt() {
		int focus = 0;
		for(int i = getFocusInt()-1;i > -1;i--) {
			if(fieldList.get(i).isEnabled()) {
				focus = i;
				break;
			}
		}
		return focus;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		int firi = firstVoidInt();
		int fini = finalVoidInt();
		int gi = getFocusInt();
		int grfiri = getRowFirstInt();
		int grfini = getRowFinalInt();
		int gcfiri = getColFirstInt();
		int gcfini = getColFinalInt();
		int ri = rightFocusInt();
		int li = leftFocusInt();
		int ui = upFocusInt();
		int di = downFocusInt();
		int ni = nextFocusInt();
		int bi = backFocusInt();
		switch(code) {
			case KeyEvent.VK_ENTER:
				if(!(fieldList.get(gi).getText().equals(""))) {
				fieldList.get(gi).setBackground(Color.WHITE);
				fieldList.get(gi).setEnabled(false);
				}
				break;
				
			case KeyEvent.VK_RIGHT:
				if(gi < grfini) { 
					System.out.print(gi + "->");
					System.out.println(ri);
					fieldList.get(ri).grabFocus();
				}else if(gi < fini+1) {
					if(e.isShiftDown()) {
						if(gi == fini) {
							System.out.print(gi + "->");
							System.out.println(firi);
							fieldList.get(firi).grabFocus();
						}else {
							System.out.print(gi + "->");
							System.out.println(ni);
							fieldList.get(ni).grabFocus();
						}
					}
				}
				
				break;
			
			case KeyEvent.VK_LEFT:
				if(gi > grfiri) {
					System.out.print(gi + "->");
					System.out.println(li);
					fieldList.get(li).grabFocus();
				}else if(gi > firi-1) {
					if(e.isShiftDown()) {
						if(gi == firi) {
							System.out.print(gi + "->");
							System.out.println(fini);
							fieldList.get(fini).grabFocus();
						}else {
							System.out.print(gi + "->");
							System.out.println(bi);
							fieldList.get(bi).grabFocus();
						}
					}
				}
				
				break;
			
			case KeyEvent.VK_UP:
				if(gi > gcfiri) {
					System.out.print(gi + "->");
					System.out.println(ui);
					fieldList.get(ui).grabFocus();
				}
				
				break;
				
			case KeyEvent.VK_DOWN:
				if(gi < gcfini) {
					System.out.print(gi + "->");
					System.out.println(di);
					fieldList.get(di).grabFocus();
				}
				
			default: break;
		}
	}
	private int finalVoidInt() {
		for(int i = fieldList.size()-1;i >= 0;i--) {
			if(fieldList.get(i).isEnabled()) {
				finalVoidInt = i;
				break;
			}
		}
		return finalVoidInt;
	}
	private int firstVoidInt() {
		for(int i = 0;i < fieldList.size();i++) {
			if(fieldList.get(i).isEnabled()) {
				firstVoidInt = i;
				break;
			}
		}
		return firstVoidInt;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	
}