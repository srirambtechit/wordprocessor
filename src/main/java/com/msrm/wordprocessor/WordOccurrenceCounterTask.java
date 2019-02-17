package com.msrm.wordprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class WordOccurrenceCounterTask implements Callable<WordOccurrenceHolder> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	public WordOccurrenceCounterTask(User user) {
		super();
		this.user = user;
	}

	@Override
	public WordOccurrenceHolder call() throws Exception  {
		WordOccurrenceHolder wordOccurrenceHolder = new WordOccurrenceHolder();
		wordOccurrenceHolder.setId(user.getId());
		wordOccurrenceHolder.setWord(user.getWord());
		
		try {
			Thread.sleep((long) (Math.random()*1000));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		String file = "content-file.txt";
		try {
			long count = Files.lines(Paths.get(file))
			                 .flatMap(line -> Arrays.stream(line.split(" ")))
			                 .map(str -> str.trim().toLowerCase())
			                 .filter(word -> user.getWord().toLowerCase().contains(word))
			                 .count();
			wordOccurrenceHolder.setCount((int) count);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wordOccurrenceHolder;
	}

}
