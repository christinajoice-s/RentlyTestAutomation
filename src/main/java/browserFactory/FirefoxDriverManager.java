package browserFactory;

import java.io.File;


import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends DriverManager {
	
	 private static GeckoDriverService chService;

	protected void startService() {
		 if (null == chService) {
	            try {
	                chService = new GeckoDriverService.Builder()
	                    .usingDriverExecutable(new File("/usr/bin/geckodriver"))
	                    .usingAnyFreePort()
	                    .build();
	                chService.start();	
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	}

	@Override
	protected void stopService() {
		 if (null != chService && chService.isRunning())
	            chService.stop();

	}

	@Override
	protected void createDriver() {
		 DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	       FirefoxOptions  options = new FirefoxOptions();
	        options.addArguments("test-type");
	        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
	        driver = new FirefoxDriver(chService, capabilities);
	}

}
