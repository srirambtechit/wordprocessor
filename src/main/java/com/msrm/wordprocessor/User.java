package com.msrm.wordprocessor;

public class User {

	private int id;
	private String name;
	private String word;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", word=" + word + "]";
	}

}
