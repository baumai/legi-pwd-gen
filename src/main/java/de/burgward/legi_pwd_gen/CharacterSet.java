/*
 * Legibly Password Generator - generates good readable passwords
 * Copyright (C) 2019 Maik Baumann
 *
 * This file is part of LegiblyPasswordGenerator.
 * 
 * LegiblyPasswordGenerator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * LegiblyPasswordGenerator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with LegiblyPasswordGenerator. If not, see <http://www.gnu.org/licenses/>.
*/
package de.burgward.legi_pwd_gen;

import java.util.Collections;
import java.util.Vector;

public class CharacterSet {

	public static final String[] LOWER = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q",
			"r", "s", "t", "u", "v", "w", "x", "y", "z" };
	public static final String[] UPPER = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q",
			"R", "T", "U", "V", "W", "X", "Y", "Z" };
	public static final String[] DIGITS = { "2", "3", "4", "6", "7", "8", "9" };
	public static final String[] SPECIAL = { "#", "+", ".", "-", ":", "_", "=" };

	public static Vector<String> concatOfAllCharacters() {
		Vector<String> vector = new Vector<String>();
		Collections.addAll(vector, LOWER);
		Collections.addAll(vector, UPPER);
		Collections.addAll(vector, DIGITS);
		Collections.addAll(vector, SPECIAL);
		return vector;
	}
}