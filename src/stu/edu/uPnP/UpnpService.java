package stu.edu.uPnP;

import org.teleal.cling.UpnpServiceConfiguration;
import org.teleal.cling.controlpoint.ControlPoint;
import org.teleal.cling.protocol.ProtocolFactory;
import org.teleal.cling.registry.Registry;
import org.teleal.cling.transport.Router;

public interface UpnpService {

	public UpnpServiceConfiguration getConfiguration();
	public ProtocolFactory getProtocolFactory();
    public Router getRouter();

    public ControlPoint getControlPoint();
    public Registry getRegistry();

    public void shutdown();
}
