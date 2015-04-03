/**
 * (c) 2003-2015 Pontus Ullgren. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.ullgren.mule.modules.ledmessageboard;

import org.mule.api.annotations.ConnectionStrategy;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;

import com.ullgren.dcmsgboard4j.Message;
import com.ullgren.dcmsgboard4j.Screen;
import com.ullgren.dcmsgboard4j.ScrollingTextMessage;
import com.ullgren.mule.modules.ledmessageboard.strategy.ConnectorConnectionStrategy;

/**
 * Anypoint Connector for the Dream Cheeky LED Message Board
 *
 * @author Pontus Ullgren
 */
@Connector(name="led-message-board", friendlyName="Dream Cheeky LED Message Board")
public class LedMessageBoardConnector
{
    @ConnectionStrategy
    ConnectorConnectionStrategy connectionStrategy;
     
    /**
     * Displays the received text
     *
     * {@sample.xml ../../../doc/led-message-board-connector.xml.sample led-message-board:display-text}
     *
     * @param content Content to be processed
     * @return Some string
     */
    @Processor(friendlyName="Display scrolling text on message board")
    public String displayText(String content) {
        Message message = new ScrollingTextMessage(content);
		Screen s = message.getNextScreen();
		for (int i = 0; s != null; i++) {

			connectionStrategy.getBoard().update(s);
			try {
				Thread.sleep((this.connectionStrategy.getDelay()/2));
			} catch (InterruptedException e) {
				// Do nothing ...
			}
			// Only get a new screen every second loop
			if (i % 2 == 0) {
				s = message.getNextScreen();
			}
		}
        return content;
    }

    public ConnectorConnectionStrategy getConnectionStrategy() {
        return connectionStrategy;
    }

    public void setConnectionStrategy(ConnectorConnectionStrategy connectionStrategy) {
        this.connectionStrategy = connectionStrategy;
    }
}