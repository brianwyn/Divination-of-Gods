// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileOperations.java

import java.io.*;

public class FileOperations
{

    public FileOperations()
    {
    }

    public static final byte[] ReadFile(String s)
    {
        try
        {
            File file = new File(s);
            int i = (int)file.length();
            byte abyte0[] = new byte[i];
            DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
            datainputstream.readFully(abyte0, 0, i);
            datainputstream.close();
            TotalRead++;
            return abyte0;
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    public static final void WriteFile(String s, byte abyte0[])
    {
        try
        {
            (new File((new File(s)).getParent())).mkdirs();
            FileOutputStream fileoutputstream = new FileOutputStream(s);
            fileoutputstream.write(abyte0, 0, abyte0.length);
            fileoutputstream.close();
            TotalWrite++;
            CompleteWrite++;
        }
        catch(Throwable throwable)
        {
		throwable.printStackTrace();
            System.out.println((new StringBuilder()).append("Write Error: ").append(s).toString());
        }
    }

    public static boolean FileExists(String file)
    {
        File f = new File(file);
        return f.exists();
    }

    public static int TotalRead = 0;
    public static int TotalWrite = 0;
    public static int CompleteWrite = 0;

}
