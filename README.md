# Dream Cheeky LED Message Board Anypoint Connector

This connector makes it possible to display a message on the [Dream Cheeky LED Message Board](http://dreamcheeky.com/led-message-board) from a Mule application.

# Mule supported versions

Mule 3.6.x

# Dream Cheeky LED Message Board supported versions

Developed for and tested with the [Dream Cheeky LED Message Board](http://dreamcheeky.com/led-message-board).

# Build

This connector depends on [dcmsgboard4j](https://github.com/ullgren/dcmsgboard4j) and [libusbjava](http://libusbjava.sourceforge.net/wp/) and since neither is available in any public maven repo you need to install them into your local maven repository.

```
mvn install:install-file -Dfile=lib/ch.ntb.usb-0.5.9.jar -DgroupId=ch.ntb \
    -DartifactId=usb -Dversion=0.5.9 -Dpackaging=jar
mvn install:install-file -Dfile=lib/dcmsgboard4j-0.1.0.jar -DgroupId=com.ullgren \
    -DartifactId=dcmsgboard4j -Dversion=0.1.0 -Dpackaging=jar
```

The unit test currently also requires a [Dream Cheeky LED Message Board](http://dreamcheeky.com/led-message-board) to be connected to the system or they will fail.

After this you should be able to build the connector.

# Installation 
For beta connectors you can download the source code and build it with devkit to find it available on your local repository. Then you can add it to Studio…<TBD>

For released connectors you can download them from the update site in Studio. 
Open MuleStudio, go to Help → Install New Software and select MuleStudio Cloud Connectors Update Site where you’ll find all avaliable connectors.

# Usage

This connector uses [dcmsgboard4j](https://github.com/ullgren/dcmsgboard4j) and [libusbjava](http://libusbjava.sourceforge.net/wp/) for accessing the USB device.
You will have to have these two java libraries installed in you runtime (or project) and also make sure that you have properly [install and setup libusbjava JNI library](http://libusbjava.sourceforge.net/wp/?page_id=8).

## "Device or resource busy" when running in Linux

On Linux if the connector fail to connect to the USB device try to run to display text using [Jeff Jahr's dcled](http://www.last-outpost.com/~malakai/dcled/) program. If that works try to run with this connector again. If it works this time it is because your system has the usbhid kernel module loaded try unloading it.

```
sudo rmmod usbhid
```

For information about usage our documentation at http://github.com/ullgren/led-message-board-connector.

# Reporting Issues

We use GitHub:Issues for tracking issues with this connector. You can report new issues at this link http://github.com/ullgren/led-message-board-connector/issues.
