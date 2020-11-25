package terekhin.Services;

public class Variables extends Settings {
    public static String DBUrl = getValue("db.database");
    public static String DBUser = getValue("db.user_name");
    public static String DBPassword = getValue("db.password");
    public static String API_URL = getValue("api.url");
}
