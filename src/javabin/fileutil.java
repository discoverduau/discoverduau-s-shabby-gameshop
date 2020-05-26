package javabin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class fileutil {
	public static String readTxt() {
		String line = new String();
		File f = new File("info.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			while(sc.hasNextLine()) {
				line = sc.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//JOptionPane.showMessageDialog(null, "Cannot find the info.txt");
		}
		return line;
	}
	public static void wirteTxt(String text) {
		File f = new File("info.txt");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//JOptionPane.showMessageDialog(null, "Cannot find the info.txt");
			}
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot find the info.txt");
		}
		try {
			fw.write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot find the info.txt");
		}
		try {
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "flush error!");
		}
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "close error!");
		}
	}
}
