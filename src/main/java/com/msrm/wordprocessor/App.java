package com.msrm.wordprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class App {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		List<User> users = null;
		try {
			users = Files.lines(Paths.get("input.txt"))
			             .map(UserFactory::newUser)
			             .collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Future<WordOccurrenceHolder>> tasks = new ArrayList<>();
		users.forEach(user -> {
			tasks.add(service.submit(new WordOccurrenceCounterTask(user)));
		});
		
		tasks.forEach(f -> {
			WordOccurrenceHolder wordOccurrenceHolder;
			try {
				wordOccurrenceHolder = f.get();
				System.out.printf("%d\t%s\t%d%n" ,wordOccurrenceHolder.getId(),wordOccurrenceHolder.getWord(), wordOccurrenceHolder.getCount());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		service.shutdown();
	}

}
