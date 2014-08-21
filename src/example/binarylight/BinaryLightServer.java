package example.binarylight;

import java.io.File;
import java.io.IOException;

import org.teleal.cling.UpnpService;
import org.teleal.cling.UpnpServiceImpl;
import org.teleal.cling.binding.LocalServiceBindingException;
import org.teleal.cling.binding.annotations.AnnotationLocalServiceBinder;
import org.teleal.cling.model.DefaultServiceManager;
import org.teleal.cling.model.ValidationException;
import org.teleal.cling.model.meta.DeviceDetails;
import org.teleal.cling.model.meta.DeviceIdentity;
import org.teleal.cling.model.meta.Icon;
import org.teleal.cling.model.meta.LocalDevice;
import org.teleal.cling.model.meta.LocalService;
import org.teleal.cling.model.meta.ManufacturerDetails;
import org.teleal.cling.model.meta.ModelDetails;
import org.teleal.cling.model.types.DeviceType;
import org.teleal.cling.model.types.UDADeviceType;
import org.teleal.cling.model.types.UDN;

public class BinaryLightServer implements Runnable {

    public static void main(String[] args) throws Exception {
        // Start a user thread that runs the UPnP stack
        Thread serverThread = new Thread(new BinaryLightServer());
        serverThread.setDaemon(false);
        serverThread.start();
    }

    public void run() {
        try {

            final UpnpService upnpService = new UpnpServiceImpl();

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    upnpService.shutdown();
                }
            });

            // Add the bound local device to the registry
            upnpService.getRegistry().addDevice(createDevice()
            );

        } catch (Exception ex) {
        	ex.printStackTrace();
//            System.err.println("Exception occured: " + ex);
//            ex.printStackTrace(System.err);
//            System.exit(1);
        }
    }

    // DOC: CREATEDEVICE
    LocalDevice createDevice()
            throws ValidationException, LocalServiceBindingException, IOException {

        DeviceIdentity identity =
                new DeviceIdentity(
                        UDN.uniqueSystemIdentifier("Demo Binary Light")
                );

        DeviceType type =
                new UDADeviceType("BinaryLight", 1);

        DeviceDetails details =
                new DeviceDetails(
                        "Friendly Binary Light",
                        new ManufacturerDetails("ACME"),
                        new ModelDetails(
                                "BinLight2000",
                                "A demo light with on/off switch.",
                                "v1"
                        )
                );

        Icon icon =
                new Icon( "image/png", 48, 48, 8, new File("icon.png")
                );

        LocalService<SwitchPower> switchPowerService =
                new AnnotationLocalServiceBinder().read(SwitchPower.class);

        switchPowerService.setManager(
                new DefaultServiceManager(switchPowerService, SwitchPower.class)
        );

        return new LocalDevice(identity, type, details, icon, switchPowerService);

        /* Several services can be bound to the same device:
        return new LocalDevice(
                identity, type, details, icon,
                new LocalService[] {switchPowerService, myOtherService}
        );
        */
        
    }
    // DOC: CREATEDEVICE
}
