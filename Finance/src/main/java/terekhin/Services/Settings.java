package terekhin.Services;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

class Settings {


    static String getValue(String key)
    {
        return getValue(key,String.class);
    }

    private static <T> T getValue(String key, Class<T> type) {
        InputStream _input = Settings.class.getClassLoader().getResourceAsStream("config.properties");
        return getValue(_input,key,type);
    }

    private static <T> T getValue(InputStream _input,String key,Class<T> type){
        try {
            if (isNullOrEmpty(key)) return (T) type.getClass();

            Properties _prop = new Properties();
            _prop.load(_input);

            String value = _prop.getProperty(key);

            if (isNullOrWhitespace(value)) return (T) type.getClass();

            if (type.isAssignableFrom(Date.class)) {
                return (T) (new SimpleDateFormat("dd/MM/yyyy")).parse(value);
            }
            else if(type.isAssignableFrom(String.class))
            {
                return (T) value;

            } else {
                Method _valueOfMethod = type.getDeclaredMethod("valueOf", String.class);
                return (T) _valueOfMethod.invoke(type.getClass(), value);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            return (T) type.getClass();
        }
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    private static boolean isNullOrWhitespace(String s) {
        return s == null || isWhitespace(s);

    }
    private static boolean isWhitespace(String s) {
        int length = s.length();
        if (length > 0) {
            for (int start = 0, middle = length / 2, end = length - 1; start <= middle; start++, end--) {
                if (s.charAt(start) > ' ' || s.charAt(end) > ' ') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
