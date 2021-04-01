package calculatorservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		this.registerCalculatorService();
		
		System.out.println("Started: Calculator Service");

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("Stopped: Calculator Service");
	}
	
	public void registerCalculatorService() {
		Calculator calculator = new CalculatorImpl();
		context.registerService(Calculator.class, calculator, null);
		
	}
}
