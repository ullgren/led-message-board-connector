package com.ullgren.dcmsgboard4j;

public class UsbBoard implements Board {

    @Override
    public void update(Screen screen) {
            System.out.println("========================");
            for(int y=0; y<Board.SIZE_Y; y++) {
                    System.out.print("|");
                    for(int x=0; x<Board.SIZE_X; x++) {
                            System.out.print(screen.get(x, y)?"*":"\u00B7");
                    }
                    System.out.println("|");
            }
            System.out.println("========================");

    }

    @Override
    public void open() throws BoardException {
            // TODO Auto-generated method stub
            
    }

    @Override
    public void close() throws BoardException {
            // TODO Auto-generated method stub
            
    }

}
