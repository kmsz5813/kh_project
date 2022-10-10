package com.myweb.home.main.model;

public class WordDTO {
	private int ranking;
	private String word;
	private int frequency;
	
	
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	@Override
	public String toString() {
		return "WordDTO [ranking=" + ranking + ", word=" + word + ", frequency=" + frequency + "]";
	}
	
	
	
	
}
