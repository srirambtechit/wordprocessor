package com.msrm.wordprocessor;

public class UserFactory {

	public static User newUser(String detail) {
		if (detail == null)
			throw new IllegalArgumentException();

		String[] details = detail.split("\\|");

		if (details.length < 3)
			throw new IllegalArgumentException();

		User user = new User();
		user.setId(Integer.valueOf(details[0]));
		user.setName(details[1]);
		user.setWord(details[2]);
		return user;
	}

	public static User newUser(Integer id, String name, String word) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setWord(word);
		return user;
	}

}
