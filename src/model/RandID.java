package model;

import java.util.Random;

public class RandID {

	public static int randID() {

		String dice = "0123456789";
		String generatedString = "";
		int lengthID = 4;
		Random rand = new Random();

		for (int i = 0; i < lengthID; i++) {
			generatedString += dice.charAt(rand.nextInt(dice.length()));
		}

		return Integer.parseInt(generatedString);
	}

}
