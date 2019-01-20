/*
 * Legibly Password Generator - generates good readable passwords
 * Copyright (C) 2019 Maik Baumann
 *
 * This file is part of LegiblyPasswordGenerator.
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

import java.util.Vector;

public class SystemOutput {

	private static final int SPACES_EVERY_N_CHARS = 4;

	public static void sysoutPasswords(Vector<String> passwords) {
		for (String pw : passwords) {
			String withSpaces = withSpaces(pw);
			System.out.println(withSpaces);
		}
	}

	private static String withSpaces(String stringToSplit) {
		if (stringToSplit != null && stringToSplit.length() > SPACES_EVERY_N_CHARS) {

			StringBuilder builder = new StringBuilder();
			int fourthCount = 0;
			for (int i = 0; i < stringToSplit.length(); i++) {
				builder.append(stringToSplit.substring(i, i + 1));
				fourthCount++;
				if (fourthCount == SPACES_EVERY_N_CHARS) {
					builder.append(" ");
					fourthCount = 0;
				}
			}
			stringToSplit = builder.toString();
		}
		return stringToSplit;
	}

	public static void sysoutCopyright() {
		System.out.println("Legibly Password Generator - generates good readable passwords");
		System.out.println("Copyright (C) 2019 Maik Baumann");
		System.out.println("");
		System.out.println("LegiblyPasswordGenerator is free software: you can redistribute it and/or modify");
		System.out.println("it under the terms of the GNU General Public License as published by");
		System.out.println("the Free Software Foundation, either version 3 of the License, or");
		System.out.println("(at your option) any later version.");
		System.out.println("");
		System.out.println("LegiblyPasswordGenerator is distributed in the hope that it will be useful,");
		System.out.println("but WITHOUT ANY WARRANTY; without even the implied warranty of");
		System.out.println("MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the");
		System.out.println("GNU General Public License for more details.");
		System.out.println("");
		System.out.println("You should have received a copy of the GNU General Public License");
		System.out.println("along with LegiblyPasswordGenerator.  If not, see <https://www.gnu.org/licenses/>.");
		System.out.println("");
		System.out.println("");
	}
}
