package com;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MRUNodes.java

import com.sign.signlink;

public final class MRUNodes
{

    public MRUNodes(int i)
    {
        initialCount = i;
        spaceLeft = i;
    }

    public NodeSub insertFromCache(long l)
    {
        NodeSub nodeSub = (NodeSub)nodeCache.findNodeByID(l);
        if(nodeSub != null)
            nodeSubList.insertHead(nodeSub);
        return nodeSub;
    }

    public void removeFromCache(NodeSub nodeSub, long l)
    {
        try
        {
            if(spaceLeft == 0)
            {
                NodeSub nodeSub_1 = nodeSubList.popTail();
                nodeSub_1.unlink();
                nodeSub_1.unlinkSub();
                if(nodeSub_1 == emptyNodeSub)
                {
                    NodeSub nodeSub_2 = nodeSubList.popTail();
                    nodeSub_2.unlink();
                    nodeSub_2.unlinkSub();
                }
            } else
            {
                spaceLeft--;
            }
            nodeCache.removeFromCache(nodeSub, l);
            nodeSubList.insertHead(nodeSub);
            return;
        }
        catch(RuntimeException runtimeexception)
        {
            signlink.reporterror((new StringBuilder()).append("47547, ").append(nodeSub).append(", ").append(l).append(", ").append(2).append(", ").append(runtimeexception.toString()).toString());
        }
        throw new RuntimeException();
    }

    public void unlinkAll()
    {
        do
        {
            NodeSub nodeSub = nodeSubList.popTail();
            if(nodeSub != null)
            {
                nodeSub.unlink();
                nodeSub.unlinkSub();
            } else
            {
                spaceLeft = initialCount;
                return;
            }
        } while(true);
    }

    private final NodeSub emptyNodeSub = new NodeSub();
    private final int initialCount;
    private int spaceLeft;
    private final NodeCache nodeCache = new NodeCache();
    private final NodeSubList nodeSubList = new NodeSubList();
}
