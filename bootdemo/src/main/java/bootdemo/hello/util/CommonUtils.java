package bootdemo.hello.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

	public static List<Object> groupMatches(final String text, final String regex){
		List<Object>  lstRet = new ArrayList<Object>();
		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(text);
		if (m.find()){
			// groupCount: グループ 0 はパターン全体を表します。グループ 0 は、このカウントに含まれません。
			for(int i = 0; i <= m.groupCount(); i++){
				lstRet.add(m.group(i));
			}
		}

		return lstRet;
	}

}
