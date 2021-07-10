import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener, MouseListener{
	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	JPanel reset_panel = new JPanel();
	JButton reset_button = new JButton();
	JButton[] buttons = new JButton[9];
	boolean player1_turn;
	
	TicTacToe(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		//background color of top text field
		textfield.setBackground(new Color(255, 255, 255));
		//color of title text or turn callout LIGHT BLUE
		textfield.setForeground(new Color(95, 176, 255));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("TicTacToe");
		textfield.setOpaque(true);
		
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800, 100);
		
		reset_panel.setLayout(new BorderLayout());
		reset_panel.setBounds(0, 600, 200, 200);
		
		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			buttons[i].setEnabled(false);
			buttons[i].setBackground(Color.green);
		}
		
		button_panel.setLayout(new GridLayout(3, 3));
		
		title_panel.add(textfield);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);
		
		reset_button.setSize(50, 50);
		reset_button.setText("New Game");
		reset_button.addActionListener(this);
		reset_panel.add(reset_button);
		frame.add(reset_panel, BorderLayout.SOUTH);
		
		firstTurn();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==reset_button) {
			TicTacToe reset = new TicTacToe();
		}
		for (int i = 0; i < 9; i++) {
			if (e.getSource() == buttons[i]) {
				if (player1_turn) {
					if (buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(95, 176, 255)); //LIGHT BLUE
						buttons[i].setText("X");
						player1_turn = false;
						textfield.setForeground(new Color(252, 0, 109));
						textfield.setText("O's Turn");
						check();
					}
				}
				else {
					if (buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(252, 0, 109)); //LIGHT MAGENTA
						buttons[i].setText("O");
						player1_turn = true;
						textfield.setForeground(new Color(95, 176, 255));
						textfield.setText("X's Turn");
						check();
					}
				}
			}
		}
	}
	
	public void firstTurn() {
		//delays start of game by 2 seconds
		try {
			Thread.sleep(2000);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		//disables buttons until after 2 seconds have passed
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(true);
			buttons[i].addMouseListener(this);
		}
		
		//generates random number to determine which goes first
		if(random.nextInt(2)==0) {
			player1_turn = true;
			textfield.setText("X's Turn");
		} else {
			player1_turn = false;
			textfield.setText("O's Turn");
		}
	}
	
	public void check() {
		//horizontal x wins
		if ((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[2].getText() =="X")) {
			xWins(0, 1, 2);
		}
		if ((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() =="X")) {
			xWins(3, 4, 5);
		}
		if ((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() =="X")) {
			xWins(6, 7, 8);
		}

		//vertical x wins
		if ((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() =="X")) {
			xWins(0, 3, 6);
		}
		if ((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() =="X")) {
			xWins(1, 4, 7);
		}
		if ((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() =="X")) {
			xWins(2, 5, 8);
		}

		//diagonal x wins
		if ((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText() =="X")) {
			xWins(0, 4, 8);
		}
		if ((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() =="X")) {
			xWins(2, 4, 6);
		}
		
		/********/
		//horizontal o wins
		if ((buttons[0].getText() == "O") && (buttons[1].getText() == "O") && (buttons[2].getText() =="O")) {
			oWins(0, 1, 2);
		}
		if ((buttons[3].getText() == "O") && (buttons[4].getText() == "O") && (buttons[5].getText() =="O")) {
			oWins(3, 4, 5);
		}
		if ((buttons[6].getText() == "O") && (buttons[7].getText() == "O") && (buttons[8].getText() =="O")) {
			oWins(6, 7, 8);
		}

		//vertical o wins
		if ((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() =="O")) {
			oWins(0, 3, 6);
		}
		if ((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() =="O")) {
			oWins(1, 4, 7);
		}
		if ((buttons[2].getText() == "O") && (buttons[5].getText() == "O") && (buttons[8].getText() =="O")) {
			oWins(2, 5, 8);
		}

		//diagonal o wins
		if ((buttons[0].getText() == "O") && (buttons[4].getText() == "O") && (buttons[8].getText() =="O")) {
			oWins(0, 4, 8);
		}
		if ((buttons[2].getText() == "O") && (buttons[4].getText() == "O") && (buttons[6].getText() =="O")) {
			oWins(2, 4, 6);
		}
	}
	
	public void xWins(int a, int b, int c) {
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		buttons[a].setBackground(Color.green);
		buttons[b].setBackground(Color.green);
		buttons[c].setBackground(Color.green);
		textfield.setText("X wins");
	}
	public void oWins(int a, int b, int c) {
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		buttons[a].setBackground(Color.green);
		buttons[b].setBackground(Color.green);
		buttons[c].setBackground(Color.green);
		textfield.setText("O wins");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
