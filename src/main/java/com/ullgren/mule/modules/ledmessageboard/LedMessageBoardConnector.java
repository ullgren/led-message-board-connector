/**
 * (c) 2003-2015 Pontus Ullgren. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.ullgren.mule.modules.ledmessageboard;

import org.mule.api.annotations.ConnectionStrategy;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Default;

import com.ullgren.dcmsgboard4j.Message;
import com.ullgren.dcmsgboard4j.Screen;
import com.ullgren.dcmsgboard4j.ScrollingTextMessage;
import com.ullgren.dcmsgboard4j.TestpatternMessage;
import com.ullgren.dcmsgboard4j.TextMessage;
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
     * Displays the received text scrolling from the right to the left.
     *
     * {@sample.xml ../../../doc/led-message-board-connector.xml.sample led-message-board:display-text}
     *
     * @param content Text that should be displayed
     * @param delay Delay in milliseconds before updating the screen. Determines the speed the message will be scrolled with.
     */
    @Processor(friendlyName="Display scrolling text on the message board")
    public void displayText(String content, @Default("0") Long delay) {
        Message message = new ScrollingTextMessage(content);
		delay = (delay<=0?new Long(this.connectionStrategy.getDelay()):delay);
		displayChangingMessage(message, delay);
        return;
    }

    
    /**
     * Displays a series of test patterns
     *
     * {@sample.xml ../../../doc/led-message-board-connector.xml.sample led-message-board:test-pattern}
     *
     * @param delay Delay in milliseconds before updating the screen. Determines the speed of changes in the test pattern.
     */
    @Processor(friendlyName="Display a series of test patterns on the message board")
    public void testPattern(@Default("0") Long delay) {
        Message message = new TestpatternMessage();
		delay = (delay<=0?new Long(this.connectionStrategy.getDelay()):delay);
		displayChangingMessage(message, delay);
        return;
    }

    
    /**
     * Displays a static text in the given amount of time. Max text length depends on font size.
     *
     * {@sample.xml ../../../doc/led-message-board-connector.xml.sample led-message-board:display-static-text}
     *
     * @param content Text that should be displayed
     * @param duration The time, in milliseconds, the text should be shown.
     */
    @Processor(friendlyName="Display static text on the message board")
    public void displayStaticText(String content, @Default("500") Long duration) {
        Message message = new TextMessage(content);
		Screen s = message.getNextScreen();
		long now = System.currentTimeMillis();
		long endTime = now+duration;
		while( now < endTime ) {
			connectionStrategy.getBoard().update(s);
			try {
				long sleepTime = endTime-now;
				// We need to refresh the screen at least every 200 milliseconds or it will go blank
				Thread.sleep((sleepTime>200?200:sleepTime));
			} catch (InterruptedException e) {
				// Do nothing ...
			}
			now = System.currentTimeMillis();
		}
		return;
    }
    
    private void displayChangingMessage(Message message, Long delayBetweenScreens) {
    	Screen s = message.getNextScreen();
		for (int i = 0; s != null; i++) {

			connectionStrategy.getBoard().update(s);
			try {
				Thread.sleep((delayBetweenScreens/2));
			} catch (InterruptedException e) {
				// Do nothing ...
			}
			// Only get a new screen every second loop
			if (i % 2 == 0) {
				s = message.getNextScreen();
			}
		}
    }
    
    public ConnectorConnectionStrategy getConnectionStrategy() {
        return connectionStrategy;
    }

    public void setConnectionStrategy(ConnectorConnectionStrategy connectionStrategy) {
        this.connectionStrategy = connectionStrategy;
    }
}