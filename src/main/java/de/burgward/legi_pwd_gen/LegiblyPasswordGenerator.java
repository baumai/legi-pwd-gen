/*
 * Legibly Password Generator - generates good readable passwords
 * Copyright (C) 2019 Maik Baumann
 *
 * LegiblyPasswordGenerator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * LegiblyPasswordGenerator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with LegiblyPasswordGenerator.  If not, see <https://www.gnu.org/licenses/>.
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
 * - the first n(default six) characters contain at least one character of each
 * group of characters, because some systems do not store larger passwords<br/>
 * - every password starts with letters, because some systems cannot handle
 * digits or special signs at the start<br/>
 * - in one single password is no character more than ones<br/>
 * - the randomness is large enough, that the passwords are very unique
 * 
 * @version 0.1.3
 */
public class LegiblyPasswordGenerator {

	private static final int TOTAL_NUMBER_OF_PASSWORDS = 30;

	private static final int LENGTH_OF_PASSWORD = 13;

	private static final int LENGTH_OF_FIRST_PART = 6;

	private final Vector<String> allCharacters;

	public LegiblyPasswordGenerator() {
		allCharacters = CharacterSet.concatOfAllCharacters();
	}

	public static void main(String[] args) {
		SystemOutput.sysoutCopyright();
		System.out.println("START");
		System.out.println(" ");

		Vector<String> passwords = new LegiblyPasswordGenerator().generatePasswords(TOTAL_NUMBER_OF_PASSWORDS);

		SystemOutput.sysoutPasswords(passwords);

		System.out.println(" ");
		System.out.println("END");
	}

	private boolean isGood(String[] password) {

		if (!areAllCharsUnique(password)) {
			return false;
		}
		if (!isEveryGroupInFirstCharacters(password)) {
			return false;
		}
		if (!areOnlyLettersAtStart(password)) {
			return false;
		}
		return true;
	}

	private boolean areOnlyLettersAtStart(String[] password) {

		String firstChar = password[0];

		boolean isInGroup0First = isInGroup(firstChar, CharacterSet.LOWER);
		boolean isInGroup1First = isInGroup(firstChar, CharacterSet.UPPER);

		return isInGroup0First || isInGroup1First;
	}

	private boolean isEveryGroupInFirstCharacters(String[] password) {
		String[] firstChars = Arrays.copyOf(password, LENGTH_OF_FIRST_PART);

		boolean isInGroup0 = isOneInGroup(firstChars, CharacterSet.LOWER);
		boolean isInGroup1 = isOneInGroup(firstChars, CharacterSet.UPPER);
		boolean isInGroup2 = isOneInGroup(firstChars, CharacterSet.DIGITS);
		boolean isInGroup3 = isOneInGroup(firstChars, CharacterSet.SPECIAL);

		return isInGroup0 && isInGroup1 && isInGroup2 && isInGroup3;
	}

	private boolean isOneInGroup(String[] firstSevenChars, String[] grp) {
		for (String str : firstSevenChars) {
			if (isInGroup(str, grp)) {
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

	public Vector<String> generatePasswords(int amount) {
		HashMap<String, String> passwords = new HashMap<String, String>();

		while (passwords.size() < amount) {
			String newPw = generatePassword();
			passwords.put(newPw, newPw);
		}

		Vector<String> vector = new Vector<String>();
		vector.addAll(passwords.values());
		return vector;
	}

	public String generatePassword() {
		String[] password = new String[LENGTH_OF_PASSWORD];

		boolean isGoodPassword = false;
		while (!isGoodPassword) {
			password = new String[LENGTH_OF_PASSWORD];
			for (int i = 0; i < LENGTH_OF_PASSWORD; i++) {
				password[i] = getSingleRandomCharacter();
			}
			isGoodPassword = isGood(password);
		}

		return String.join("", password);
	}

	private String getSingleRandomCharacter() {
		int rnd = new Random().nextInt(allCharacters.size());
		return allCharacters.get(rnd);
	}

	private boolean isInGroup(String character, String[] group) {
		for (String str : group) {
			if (str.equals(character)) {
				return true;
			}
		}
		return false;
	}
}