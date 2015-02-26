/**
 * (c) 2003-2015 Pontus Ullgren. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.ullgren.mule.modules.ledmessageboard.strategy;

import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.param.ConnectionKey;

import com.ullgren.dcmsgboard4j.Board;
import com.ullgren.dcmsgboard4j.BoardException;
import com.ullgren.dcmsgboard4j.UsbBoard;

/**
 * Configuration type Strategy
 *
 * @author Pontus Ullgren
 */
@ConnectionManagement(configElementName = "config", friendlyName = "Dream Cheeky LED Message Board Configuration")
public class ConnectorConnectionStrategy
{
	private Board board;
	
	private Integer interfaceNumber;
	
	public Board getBoard() {
		return board;
	}
	
	
	@Connect
    @TestConnectivity
    public void connect(@ConnectionKey Integer interfaceNumber) throws ConnectionException  {
		try {
    		this.board = new UsbBoard();
    		this.interfaceNumber = interfaceNumber;
    		this.board.open();
    	} catch (BoardException ex) {
    		throw new ConnectionException(ConnectionExceptionCode.CANNOT_REACH, "-1", ex.getMessage(), ex);
    	}
    }
    
    @Disconnect
    public void disconnect() {
    	if ( this.board != null ) {
    		this.board.close();
    		this.board = null;
    	}
    }
    
    @ValidateConnection
    public boolean isConnected() {
        return (this.board != null);
    }
  
    @ConnectionIdentifier
    public String connectionId() {
        return interfaceNumber.toString();
    }
}