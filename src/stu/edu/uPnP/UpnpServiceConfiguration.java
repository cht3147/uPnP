package stu.edu.uPnP;

import java.util.concurrent.Executor;

import org.teleal.cling.binding.xml.DeviceDescriptorBinder;
import org.teleal.cling.binding.xml.ServiceDescriptorBinder;
import org.teleal.cling.model.Namespace;
import org.teleal.cling.transport.spi.DatagramIO;
import org.teleal.cling.transport.spi.DatagramProcessor;
import org.teleal.cling.transport.spi.GENAEventProcessor;
import org.teleal.cling.transport.spi.MulticastReceiver;
import org.teleal.cling.transport.spi.NetworkAddressFactory;
import org.teleal.cling.transport.spi.SOAPActionProcessor;
import org.teleal.cling.transport.spi.StreamClient;
import org.teleal.cling.transport.spi.StreamServer;

public interface UpnpServiceConfiguration {

	// NETWORK
	/**
	 * 提供網路接口, port and multicast setting which are used by the uPnP stack
	 * @return
	 */
    public NetworkAddressFactory createNetworkAddressFactory();

    public DatagramProcessor getDatagramProcessor();
    public SOAPActionProcessor getSoapActionProcessor();
    public GENAEventProcessor getGenaEventProcessor();

    public StreamClient createStreamClient();
    public MulticastReceiver createMulticastReceiver(NetworkAddressFactory naf);
    public DatagramIO createDatagramIO(NetworkAddressFactory naf);
    public StreamServer createStreamServer(NetworkAddressFactory naf);

    public Executor getMulticastReceiverExecutor();
    public Executor getDatagramIOExecutor();
    public Executor getStreamServerExecutor();

    // DESCRIPTORS
    public DeviceDescriptorBinder getDeviceDescriptorBinderUDA10();
    public ServiceDescriptorBinder getServiceDescriptorBinderUDA10();

    // PROTOCOL
    public Executor getAsyncProtocolExecutor();
    public Executor getSyncProtocolExecutor();

    // REGISTRY
    public Namespace getNamespace();
    public Executor getRegistryMaintainerExecutor();
    public Executor getRegistryListenerExecutor();
}
