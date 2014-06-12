package net.bohush.exercises.chapter38;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise04 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JLabel jLabel = new JLabel("", JLabel.CENTER);
	private ImageIcon caImageIcon = new ImageIcon(getClass().getResource("image/caIcon.gif"));
	private ImageIcon usImageIcon = new ImageIcon(getClass().getResource("image/usIcon.gif"));
	
	public Exercise04() {
		
		// Create actions
		Action caAction = new MyAction("CA", caImageIcon, "Canada", new Integer(KeyEvent.VK_C), KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		Action usAction = new MyAction("US", usImageIcon, "USA", new Integer(KeyEvent.VK_U), KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));

		// Create menus
		JMenuBar jMenuBar1 = new JMenuBar();
		JMenu jmenuFlags = new JMenu("Flags");
		setJMenuBar(jMenuBar1);
		jMenuBar1.add(jmenuFlags);

		// Add actions to the menu
		jmenuFlags.add(caAction);
		jmenuFlags.add(usAction);

		// Add actions to the toolbar
		JToolBar jToolBar1 = new JToolBar(JToolBar.VERTICAL);
		jToolBar1.setBorder(BorderFactory.createLineBorder(Color.red));
		jToolBar1.add(caAction);
		jToolBar1.add(usAction);
		
		add(jToolBar1, BorderLayout.EAST);
		add(jLabel, BorderLayout.CENTER);
	}

	private class MyAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		String name;

		MyAction(String name, Icon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
			super(name, icon);
			putValue(Action.SHORT_DESCRIPTION, desc);
			putValue(Action.MNEMONIC_KEY, mnemonic);
			putValue(Action.ACCELERATOR_KEY, accelerator);
			this.name = name;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (name.equals("CA")) {
				jLabel.setIcon(caImageIcon);
			} else if (name.equals("US")) {
				jLabel.setIcon(usImageIcon);
			}
		}
	}

	public static void main(String[] args) {
		Exercise04 applet = new Exercise04();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise04");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(400, 320);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
		frame.setVisible(true);
	}
}