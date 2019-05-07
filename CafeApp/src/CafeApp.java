/* Title: CafeApp.java
 * Author: Ariel Dumagan
 * Desc: A swing based Java app that allows the user to manage a cafe. It lets the user view/modify the inventory, sales, and orders.
 * Currently the inventory tab is the only working feature. I would like to continue working on this project so that I can implement
 * the sales and orders tabs.
 * 
 * Serialization: It currently supports text and XML.
 * 
 * Future Implementation:  In the future, I would like to make this in to a complete Point of Sales system that has 
 * table maps, menu items and is able to perform transactions. I would also like to implement a DB for ease of use and maintenance.
 * 
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Model.MenuItem;
import Controller.ItemManager;

import java.util.ArrayList;

/**
 * DrawingFrame: Extends JFrame and sets up the UI. It has 4 buttons for navigation. Inventory creates a
 * new JFrame in which it will show the inventory in a table. You will be able to search and modify this.
 * 
 * WORK IN PROGRESS: Sales and Orders button. Currently open a Dialog box explaining WIP
 * @author dumag
 *
 */

class DrawingFrame extends JFrame {
	private ArrayList<MenuItem> itemsList;
	
	public void setupUI() {
		setTitle("Cafe Manager");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,100,700,500);
		Container myContainer = getContentPane();
		myContainer.setLayout(new BorderLayout());
				
		// Button Action Listeners
		JButton btnInventory = new JButton("Inventory");
		btnInventory.setBackground(Color.cyan);
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openTable();
			}
		});
		
		JButton btnSales = new JButton("Sales");
		btnSales.setBackground(Color.orange);
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
					    "Feature Not Yet Implemented!",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JButton btnOrders = new JButton("Orders");
		btnOrders.setBackground(Color.LIGHT_GRAY);
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
					    "Feature Not Yet Implemented!",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.red);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// Organize Buttons in a stack
		JPanel stackedPanel = new JPanel(new GridLayout(4,1));
		stackedPanel.add(btnInventory);
		stackedPanel.add(btnSales);
		stackedPanel.add(btnOrders);
		stackedPanel.add(btnExit);
		
		// Get Image for background
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("C:\\Users\\dumag\\workspace\\CafeApp\\Images\\Cafe.jpg"));
		} catch (IOException e1) {
			System.out.println("ERROR! COULD NOT LOAD IMAGE!");
		}
		JLabel cafeIMG = new JLabel(new ImageIcon(myPicture));
		JPanel imagePan = new JPanel();
		
		// Display UI
		imagePan.add(cafeIMG);
		myContainer.add(imagePan, BorderLayout.CENTER);
		myContainer.add(stackedPanel,BorderLayout.WEST);
	
	}
	
	public DrawingFrame(ArrayList<MenuItem> items) {
		setupUI();
		
	}
	
	// Creates a new Window
	/**
	 * OpenTable: creates a new JFrame window that will allow users to view the table
	 * as well as search and modify things on it. They will also be able to save.
	 */
	public static void openTable()
	{
		JFrame frame = new JFrame("Inventory");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        JTextArea textArea = new JTextArea(15, 50);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(Font.getFont(Font.SANS_SERIF));
        
        JScrollPane scroller = new JScrollPane(textArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
        JTextField input = new JTextField(20);
        
        JButton button = new JButton("Enter");
        panel.add(scroller);
        inputpanel.add(input);
        inputpanel.add(button);
        panel.add(inputpanel);
       
        //Header for the Table
        String[] columns = new String[] {
        		"Name", "Type", "Price", "Quantity", "Stock"
        };
        
        // NEED TO FIGURE OUT HOW TO POPULATE TABLE
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        
        JTable table = new JTable(tableModel);
       
        
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        input.requestFocus();
	}

	
}


public class CafeApp {


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Testing if MenuItem class works
		MenuItem j = new MenuItem();
		System.out.println(j.toString());
		j.setName("Coffee");
		j.setType(1);
		j.setPrice(2.99);
		j.setQuantity(3);
		System.out.println(j.toString());
		
		ItemManager IM = new ItemManager();
		File myFile = new File("C:\\Users\\dumag\\workspace\\CafeApp\\Inventory\\myInventory.txt");
		ArrayList<MenuItem> itemsList = IM.inputItemsText(myFile);
		DrawingFrame frm = new DrawingFrame(itemsList);
		frm.setVisible(true);
		
	}

}
