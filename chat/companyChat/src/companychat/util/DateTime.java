package companychat.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateTime {

	public static String getDatetime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(Constant.dateFormat);
		return formatter.format(calendar.getTime());
	}

}
