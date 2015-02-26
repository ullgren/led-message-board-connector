# Dream Cheeky LED Message Board Anypoint Connector

This connector makes it possible to display a message on the [Dream Cheeky LED Message Board](http://dreamcheeky.com/led-message-board) from a Mule application.

# Mule supported versions

Mule 3.6.x

# Dream Cheeky LED Message Board supported versions

Developed for and tested with the [Dream Cheeky LED Message Board](http://dreamcheeky.com/led-message-board).


# Installation 
For beta connectors you can download the source code and build it with devkit to find it available on your local repository. Then you can add it to Studio…<TBD>

For released connectors you can download them from the update site in Studio. 
Open MuleStudio, go to Help → Install New Software and select MuleStudio Cloud Connectors Update Site where you’ll find all avaliable connectors.

#Usage

This connector uses [dcmsgboard4j](https://github.com/ullgren/dcmsgboard4j) and [libusbjava](http://libusbjava.sourceforge.net/wp/) for accessing the USB device.
You will have to have these two java libraries installed in you runtime (or project) and also make sure that you have properly [install and setup libusbjava JNI library](http://libusbjava.sourceforge.net/wp/?page_id=8).

On Linux if the connector fail to connect to the USB device try to run to display text using [Jeff Jahr's dcled](http://www.last-outpost.com/~malakai/dcled/) program. If that works try to run with this connector again. If it works this time it is because your system has the usbhid kernel module loaded try unloading it.

For information about usage our documentation at http://github.com/ullgren/led-message-board-connector.

# Reporting Issues

We use GitHub:Issues for tracking issues with this connector. You can report new issues at this link http://github.com/ullgren/led-message-board-connector/issues.
