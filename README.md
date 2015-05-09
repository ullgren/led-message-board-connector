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

# Usage

This connector uses [dcmsgboard4j](https://github.com/ullgren/dcmsgboard4j) and [libusbjava](http://libusbjava.sourceforge.net/wp/) for accessing the USB device.
You will have to have these two java libraries installed in you runtime (or project) and also make sure that you have properly [install and setup libusbjava JNI library](http://libusbjava.sourceforge.net/wp/?page_id=8).

## "Device or resource busy" when running in Linux

On Linux if the connector fail to connect to the USB device with this message try to display som text using [Jeff Jahr's dcled](http://www.last-outpost.com/~malakai/dcled/) program. If that works then try to run with Mule and the connector again. If it works this time it is because your system has the usbhid kernel module loaded so try unloading it.

```
sudo rmmod usbhid
```

For information about usage our documentation at http://github.com/ullgren/led-message-board-connector.

# Reporting Issues

We use GitHub:Issues for tracking issues with this connector. You can report new issues at this link http://github.com/ullgren/led-message-board-connector/issues.

# Third-Party Code

This project contains certain third-party code (including code which may be made available to you in source code form). Ownership, use, warranty and modification rights with respect to any such designated code is listed below.

## Anypoint DevKit

Copyright (c) 2003-2015 MuleSoft Inc.
Powered by Mule. MuleSoft is Open for Integration.
http://www.MuleSoft.com
License: [CPAL-1.0](http://www.MuleSoft.com/CPAL/)

## Java libusb / libusb-win32 wrapper

Copyright (c) 2005-2006 Andreas Schläpfer
License: [LGPL 2.1](https://svn.code.sf.net/p/libusbjava/code/trunk/java/LGPL.txt)

## Dream Cheeky USB Message Board for Java library

Copyright (c) 2014-2015 Pontus Ullgren
License: [LGPL 2.1](https://github.com/ullgren/dcmsgboard4j/blob/master/LICENSE)

