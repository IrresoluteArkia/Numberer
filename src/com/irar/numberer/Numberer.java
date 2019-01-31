package com.irar.numberer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Numberer {
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("No Target!!!");
			return;
		}
		File targetDir = new File(args[0]);
		File[] targets = targetDir.listFiles();
		int j = ("" + targets.length).length();
		List<String> skip = new ArrayList<>();
		int i = 0;
		for(File target : targets) {
			boolean finished = false;
			while(!finished) {
				String name = target.getName();
				String path = target.getPath();
				path = path.substring(0, path.length() - name.length());
				String ext = name.substring(name.lastIndexOf('.'));
				File renamed = new File(path + getNumber(i, j) + ext);
				if(skip.contains(renamed.getName())) {
					i--;
					finished = true;
				}else if(renamed.getName().equals(target.getName())) {
					finished = true;
				}else if(!renamed.exists()) {
					System.out.println("Renamed " + name + " to " + renamed.getName());
					target.renameTo(renamed);
					finished = true;
				}else {
					skip.add(renamed.getName());
				}
				i++;
			}
		}
	}

	/**
	 * Makes a {@link String} with the integer <code>i</code> formatted to have at least <code>j</code> characters, 
	 * for example: <code>getNumber(452, 7)</code> will return the {@link String} <code>"0000452"</code>
	 * @param i Integer to format
	 * @param j Minimum number of characters to be in the returned {@link String}
	 * @return A {@link String} representation of the integer <code>i</code>, formatted as stated above
	 */
	private static String getNumber(int i, int j) {
		String zs = xOf("0", j - ("" + i).length());
		return zs + i;
	}

	/**
	 * Makes a {@link String} containing <code>i</code> of <code>string</code>.
	 * 
	 * <p>Note that if <code>i</code> is less than or equal to 0, an empty {@link String} will be returned,
	 * @param string
	 * @param i
	 * @return A {@link String} formatted as stated above
	 */
	private static String xOf(String string, int i) {
		String s = "";
		for(int j = 0; j < i; j++) {
			s += string;
		}
		return s;
	}
}
