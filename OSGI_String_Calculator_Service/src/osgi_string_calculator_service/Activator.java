package osgi_string_calculator_service;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import osgi_string_calculator_services.ConvertorService;
import osgi_string_calculator_imp.ConvertorServiceImp;

public class Activator implements BundleActivator {
    
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception 
	{
		Activator.context = bundleContext;
		System.out.println("OSGI Convertor Service Starting...");
		
		this.registryConverterService();
	    
	    System.out.println("OSGI Convertor Service Started");
	}

	public void stop(BundleContext bundleContext) throws Exception 
	{
		Activator.context = null;
		System.out.println("OSGi Convertor Service Stopped!");
		
	}
	
	private void registryConverterService() 
	{
		ConvertorService service = new ConvertorServiceImp();
		context.registerService(ConvertorService.class, service, null);
	}

}
