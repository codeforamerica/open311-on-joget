package org.joget.open311.dataplugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.joget.plugin.base.PluginProperty;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * Default package activator for the OSGI bundle. Refactor and rename as necessary
 * @author Michael Yap
 */
public class DefaultActivator implements BundleActivator
{
    //Meta constants. Modify per use
    public static final String ACTIVATOR_NAME = "Open311 Request Bundle";
    public static final String ACTIVATOR_VER = "1.0.0";
    public static final String ACTIVATOR_DESC = "Open311 Request Bundle";

    protected Collection<ServiceRegistration> registration;

    /**
     * Start method invoked during OSGI container launch. Register your plugins
     * into the registration list.
     * @param context
     */
    public void start(BundleContext context)
    {
        registration = new ArrayList<ServiceRegistration>();
        registration.add(context.registerService(Open311Data.class.getName(), new Open311Data(), null));
        registration.add(context.registerService(CustomFormField.class.getName(), new CustomFormField(), null));

    }

    public void stop(BundleContext context)
    {
        for (ServiceRegistration registration : this.registration)
        {
            registration.unregister();
        }
    }

    public String getName()
    {
        return ACTIVATOR_NAME;
    }

    public String getVersion()
    {
        return ACTIVATOR_VER;
    }

    public String getDescription()
    {
        return ACTIVATOR_DESC;
    }

    public PluginProperty[] getPluginProperties()
    {
        return null;
    }

    public Object execute(Map properties)
    {
        return null;
    }
}
