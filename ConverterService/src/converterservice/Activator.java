package converterservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		this.registerConverterService();
		
		System.out.println("Started: Converter Service");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("Stopped: Converter Service");
	}
	
	public void registerConverterService() {
		Converter converter = new ConverterImpl();
		context.registerService(Converter.class, converter, null);
	}

}
