package couk.Adamki11s.IO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Convertors {

	public static boolean doesCompile(String regex, String checking) {
		Pattern p = Pattern.compile(regex);
		Matcher match = p.matcher(checking);
		return match.find();
	}

}
