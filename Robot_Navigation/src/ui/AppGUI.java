package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.FileDetails;
import parser.FileParser;

/**
 * Creates GUI for Application
 * @author apoorva
 *
 */
public class AppGUI {

	public static JMenuBar addMenuBar(final JFrame mainFrame){

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		final FileParser parseFile = new FileParser();
		// Create and add simple menu item to one of the drop down menu
		JMenuItem newAction = new JMenuItem("New");
		JMenuItem openAction = new JMenuItem("Open");
		JMenuItem exitAction = new JMenuItem("Exit");

		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.addSeparator();
		fileMenu.add(exitAction);

		newAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Clean the board
			}
		});

		openAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String extType[] = { "txt" };
				JFileChooser chooser = new JFileChooser();
				JFrame frame = new JFrame();
				FileFilter filter = new FileNameExtensionFilter("Text File",	extType);
				chooser.setFileFilter(filter);
				chooser.setDialogTitle("Opens Text File");

				int returnVal = chooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: "+ chooser.getSelectedFile().getName());
					FileDetails fileDetail = new FileDetails();
					fileDetail.setFileName(chooser.getSelectedFile().getName());
					fileDetail.setFilePath(chooser.getSelectedFile().getAbsolutePath());

					if (chooser.getSelectedFile() != null && parseFile != null) {
					parseFile.parseDataFile(fileDetail);
					mainFrame.add(new DrawBoard());
					}	
				
				}						

			}
		});
		return menuBar;

	}
}