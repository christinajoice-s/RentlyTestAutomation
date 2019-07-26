package browserFactory;
public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager ;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManagers();
                break;
            case FIREFOX:
               driverManager = new FirefoxDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManagers();
                break;
        }
        return driverManager;

    }
}