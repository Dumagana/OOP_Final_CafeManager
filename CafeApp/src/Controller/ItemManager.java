package Controller;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Model.MenuItem;

/**
 * ItemManger: A class that handles all of the serialization of the data.
 * It currently only supports text and XML input/output. 
 * @author dumag
 *
 */
public class ItemManager {
	
	//Input Serialization
	public ArrayList<MenuItem> inputItems(File file) {
		String fname = file.getName().toUpperCase();
		if (fname.endsWith("TXT")) {
			return inputItemsText(file);
		} else if (fname.endsWith("XML")) {
			return inputItemsXML(file);
		} else {
			return null;
		}
	}
	/**
	 * InputItemsText: returns an ArrayList of MenuItem type. It parses
	 * a specified file and stores it in the ArrayList.
	 * @param file
	 * @return
	 */
	public ArrayList<MenuItem> inputItemsText(File file) {
		try {
			Scanner fsc = new Scanner(file);
			String line, name;
			int type, quantity;
			double price;
			boolean stock;
			String[] parts;
			ArrayList<MenuItem> result = new ArrayList<MenuItem>();
			while (fsc.hasNextLine()) {
				
				line = fsc.nextLine();
				parts = line.split(",");
				name = (parts[0]);
				type = Integer.parseInt(parts[1]);
				price = Double.parseDouble(parts[2]);
				quantity = Integer.parseInt(parts[3]);
				stock = Boolean.parseBoolean(parts[4]);
				result.add(new MenuItem(name,type, price, quantity, stock));
			}
			return result;
		} catch (Exception ex) {
			return null;
		}
	}
	/**
	 * InputItemsXML: returns ArrayList of MenuItem type. Reads XML files and stores in
	 * arrayList 
	 * @param file
	 * @return
	 */
	public ArrayList<MenuItem> inputItemsXML(File file) {
		try {
			XMLDecoder xmldec = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
			ArrayList<MenuItem> itemsList = (ArrayList<MenuItem>)xmldec.readObject();
			xmldec.close();
			return itemsList;
		} catch (Exception ex) {
			return null;
		}
	}
	
	// Output Serialization 
	public boolean outputItems(File file,ArrayList<MenuItem> itemsList) {
		String fname = file.getName().toUpperCase();
		if (fname.endsWith("TXT")) {
			return outputItemsText(file,itemsList);
		} else if (fname.endsWith("XML")) {
			return outputItemsXML(file,itemsList);
		} else {
			return false;
		}
	}
	/**
	 * outputITemsText: Returns boolean. It writes to a specified text file
	 * @param file
	 * @param itemsList
	 * @return
	 */
	public boolean outputItemsText(File file, ArrayList<MenuItem> itemsList) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new
					FileWriter(file)));
			for (MenuItem menuItmOBJ : itemsList) {
				pw.println(menuItmOBJ.toStringForTextFile());
			}
			pw.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	/**
	 * putPutItemsXML: Retursn boolean. It writes to a specified XML file
	 * @param file
	 * @param itemsList
	 * @return
	 */
	public boolean outputItemsXML(File file, ArrayList<MenuItem> itemsList) {
		try {
			XMLEncoder xmlenc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
			xmlenc.writeObject(itemsList);
			xmlenc.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}

