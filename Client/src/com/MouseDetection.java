package com;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MouseDetection.java


final class MouseDetection
    implements Runnable
{

    @Override
	public void run()
    {
        while(running) 
        {
            synchronized(syncObject)
            {
                if(coordsIndex < 500)
                {
                    coordsX[coordsIndex] = clientInstance.mouseX;
                    coordsY[coordsIndex] = clientInstance.mouseY;
                    coordsIndex++;
                }
            }
            try
            {
                Thread.sleep(50L);
            }
            catch(Exception _ex) { }
        }
    }

    public MouseDetection(client client1)
    {
        running = true;
        clientInstance = client1;
    }

    private client clientInstance;
    public final Object syncObject = new Object();
    public final int coordsY[] = new int[500];
    public boolean running;
    public final int coordsX[] = new int[500];
    public int coordsIndex;
}
