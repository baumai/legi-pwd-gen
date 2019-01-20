/*
 * Legibly Password Generator - generates good readable passwords
 * Copyright (C) 2019 Maik Baumann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package de.burgward.legi_pwd_gen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

/**
 * - generates 30 thirteen characters long passwords<br/>
 * - used character groups: A-Z, a-z, 0-9, special: #+,.-;:_=<br/>
 * - but without hardly distinguishable ones like l, 1, O, 0<br/>
 * - the output of each password is split up after four characters for a better
 * readability<br/>
 * - the first six characters contain at least one character of each group of
 * characters, because some systems do not store larger passwords<br/>
 * - every password starts with letters, because some systems cannot handle
 * digits or special signs at the start<br/>
 * - in one single password is no character more than ones<br/>
 * - the randomness is large enough, that the passwords are very unique
 * 
 * @version 0.1.0
 */
public class LegiblyPasswordGenerator {

	private final static int TOTAL_NUMBER_OF_PASSWORDS = 30;

	private final static String[] GRP_LOWER = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p",
			"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	private final static String[] GRP_UPPER = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P",
			"Q", "R", "T", "U", "V", "W", "X", "Y", "Z" };
	private final static String[] GRP_DIGITS = { "2", "3", "4", "6", "7", "8", "9" };
	private final static String[] GRP_SPECIAL = { "#", "+", ".", "-", ":", "_", "=" };

	public static void main(String[] args) {
		System.out.println("START");
		System.out.println(" ");

		LegiblyPasswordGenerator lpg = new LegiblyPasswordGenerator();
		lpg.sysoutCopyright();
		lpg.start();

		System.out.println(" ");
		System.out.println("END");
	}

	private void start() {

		Vector<String> allCharacters = this.concatAllCharacterGroups();

		Vector<String> passwords = this.createAllPasswords(allCharacters);

		this.printPasswords(passwords);
	}

	private boolean isGood(String[] password) {

		if (!areAllCharsUnique(password)) {
			return false;
		}

		if (!isEveryGroupInFirstSixCharacters(password)) {
			return false;
		}

		if (!areOnlyLettersAtStart(password)) {
			return false;
		}

		return true;
	}

	private boolean areOnlyLettersAtStart(String[] password) {

		String firstChar = password[0];

		boolean isInGroup0First = this.isInGroup(firstChar, LegiblyPasswordGenerator.GRP_LOWER);
		boolean isInGroup1First = this.isInGroup(firstChar, LegiblyPasswordGenerator.GRP_UPPER);

		return isInGroup0First || isInGroup1First;
	}

	private boolean isEveryGroupInFirstSixCharacters(String[] password) {
		String[] firstSixChars = Arrays.copyOf(password, 6);

		boolean isInGroup0 = this.isOneInGroup(firstSixChars, LegiblyPasswordGenerator.GRP_LOWER);
		boolean isInGroup1 = this.isOneInGroup(firstSixChars, LegiblyPasswordGenerator.GRP_UPPER);
		boolean isInGroup2 = this.isOneInGroup(firstSixChars, LegiblyPasswordGenerator.GRP_DIGITS);
		boolean isInGroup3 = this.isOneInGroup(firstSixChars, LegiblyPasswordGenerator.GRP_SPECIAL);

		return isInGroup0 && isInGroup1 && isInGroup2 && isInGroup3;
	}

	private boolean isOneInGroup(String[] firstSevenChars, String[] grp) {
		for (String str : firstSevenChars) {
			if (this.isInGroup(str, grp)) {
				return true;
			}
		}
		return false;
	}

	private boolean areAllCharsUnique(String[] password) {
		HashMap<String, String> singleChars = new HashMap<String, String>();
		for (String str : password) {
			singleChars.put(str, str);
		}
		return singleChars.size() == password.length;
	}

	private Vector<String> createAllPasswords(Vector<String> allCharacters) {
		HashMap<String, String> passwords = new HashMap<String, String>();

		while (passwords.size() < LegiblyPasswordGenerator.TOTAL_NUMBER_OF_PASSWORDS) {
			String newPw = createNewPassword(allCharacters);
			passwords.put(newPw, newPw);
		}

		Vector<String> vector = new Vector<String>();
		vector.addAll(passwords.values());
		return vector;
	}

	private void printPasswords(Vector<String> passwords) {
		for (String pw : passwords) {

			// TODO smarter logic to split by four characters
			String p0 = pw.substring(0, 1) + pw.substring(1, 2) + pw.substring(2, 3) + pw.substring(3, 4);
			String p1 = pw.substring(4, 5) + pw.substring(5, 6) + pw.substring(6, 7) + pw.substring(7, 8);
			String p2 = pw.substring(8, 9) + pw.substring(9, 10) + pw.substring(10, 11) + pw.substring(11, 12);
			String p3 = pw.substring(12, 13);

			System.out.println(p0 + " " + p1 + " " + p2 + " " + p3);
		}
	}

	private String createNewPassword(Vector<String> allCharacters) {
		String[] password = new String[13];

		boolean isGoodPassword = false;
		while (!isGoodPassword) {
			password = new String[13];
			for (int i = 0; i < 13; i++) {
				int rnd = new Random().nextInt(allCharacters.size());
				password[i] = allCharacters.get(rnd);
			}
			isGoodPassword = this.isGood(password);
		}

		return String.join("", password);
	}

	private Vector<String> concatAllCharacterGroups() {
		Vector<String> vector = new Vector<String>();

		this.addGroupToVector(LegiblyPasswordGenerator.GRP_LOWER, vector);
		this.addGroupToVector(LegiblyPasswordGenerator.GRP_UPPER, vector);
		this.addGroupToVector(LegiblyPasswordGenerator.GRP_DIGITS, vector);
		this.addGroupToVector(LegiblyPasswordGenerator.GRP_SPECIAL, vector);

		return vector;
	}

	private void addGroupToVector(String[] group, Vector<String> vector) {
		for (String str : group) {
			vector.add(str);
		}
	}

	private boolean isInGroup(String character, String[] group) {
		for (String str : group) {
			if (str.equals(character)) {
				return true;
			}
		}
		return false;
	}

	private void sysoutCopyright() {
		System.out.println("Legibly Password Generator - generates good readable passwords");
		System.out.println("Copyright (C) 2019 Maik Baumann");
		System.out.println("");
		System.out.println("This program is free software: you can redistribute it and/or modify");
		System.out.println("it under the terms of the GNU General Public License as published by");
		System.out.println("the Free Software Foundation, either version 3 of the License, or");
		System.out.println("(at your option) any later version.");
		System.out.println("");
		System.out.println("This program is distributed in the hope that it will be useful,");
		System.out.println("but WITHOUT ANY WARRANTY; without even the implied warranty of");
		System.out.println("MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the");
		System.out.println("GNU General Public License for more details.");
		System.out.println("");
		System.out.println("You should have received a copy of the GNU General Public License");
		System.out.println("along with this program.  If not, see <https://www.gnu.org/licenses/>.");
		System.out.println("");
		System.out.println("");
	}
}