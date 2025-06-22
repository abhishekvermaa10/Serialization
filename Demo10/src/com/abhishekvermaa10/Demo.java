package com.abhishekvermaa10;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author abhishekvermaa10
 */
public class Demo {
	
	private static final String FILE_NAME = "data.ser";
	private static final Object LOCK = new Object();

	public static void main(String[] args) {
		List<User> users = IntStream.rangeClosed(1, 10)
			    .mapToObj(i -> {
			        Gadget gadget = new Gadget("Brand " + i, "Type " + i);
			        return new InfluencerUser(i, "User Name " + i, "P@$$word" + i, i, false, gadget);
			    })
			    .collect(Collectors.toList());
		System.out.println("Writing Data");
		saveData(users);
		System.out.println("Reading Data");
		loadData();
	}

	public static void saveData(List<User> users) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			ExecutorService executorService = Executors.newFixedThreadPool(3);
			for (User user : users) {
				executorService.submit(() -> {
					synchronized (LOCK) {
					try {
						objectOutputStream.writeObject(user);
						System.out.println(Thread.currentThread().getName() + " Writing Data: " + user);
					} catch (IOException e) {
						System.out.println(Thread.currentThread().getName() + " Failure: " + e.getMessage());
					}
					}
				});
			}
			executorService.shutdown();
			executorService.awaitTermination(2, TimeUnit.MINUTES);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadData() {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			while (true) {
				try {
					User user = (User) objectInputStream.readObject();
					System.out.println("Reading Data: " + user);
				} catch (EOFException eof) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
