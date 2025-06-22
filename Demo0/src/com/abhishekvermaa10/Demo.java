package com.abhishekvermaa10;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author abhishekvermaa10
 */
public class Demo {
	
	private static final String FILE_NAME = "data.txt";

	public static void main(String[] args) {
		String message = "Like, Share and Subscribe";
		System.out.println("Writing Data");
		saveData(message);
		System.out.println("Reading Data");
		loadData();
	}

	public static void saveData(String message) {
		try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(FILE_NAME))) {
			dataOutputStream.writeUTF(message);
			System.out.println("Writing Data: " + message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadData() {
		try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(FILE_NAME))) {
			String message = dataInputStream.readUTF();
			System.out.println("Reading Data: " + message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
