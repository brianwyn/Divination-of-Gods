package com;
/**********************************************

@author: Google411 

06/11/2011 -- 9/19/2011

CacheDownloader.java
Version: 2.00.12


$$ If you want to disable the pipup messages just null out these lines

Line: 102 - 
cacheHasUpdated();
Line: 108 - 
cacheHasUpdated();
With
Line: 102 - 
//cacheHasUpdated();
Line: 108 - 
//cacheHasUpdated();

<¤>¿<¤> Google411 Me - Revised by Kozs


**********************************************/

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.Enumeration;

import javax.swing.*;

import com.sign.signlink;

import java.awt.event.*;
import java.net.*;
import java.io.*;

public class CacheDownloader {

        private client client;
		client frame;
        private final int BUFFER = 1024;
		/* OPTIONS START HERE *///----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        /* OPTION 1 */private final int VERSION = 1 ;   // Version of cache, make it +1 if you updated your cache on your remote server
		/* OPTION 2 */private final int pauseHandlerDelay = 1000 ;   // 1000  = 1 second. This is for when ever the pauseHandler void is called. It just pauses the entire code for the amount of time set.
        /* OPTION 3*/private String cacheLink = "https://dl.dropboxusercontent.com/s/99nmukjhfy8x50x/InsanityCacheV6.zip" ;   // URL of cache on remote server. IE: "https://www.dropbox.com/s/7twpxumputx7e8w/Divination%20of%20Gods%20-%20The%20Game%20v%201.5.rar?dl=1" Add to dropbox or FileDen
		/* OPTION 4 */private String cacheDir = "C:/" ;   // Local link to cache directory - Same as sign.signlink.findcachedir() - Remember trailing '/' 
		/* OPTIONS END HERE *///--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        private String fileToExtract = getCacheDir() + getArchivedName();
        public CacheDownloader(client client) {
                this.client = client;
        }

        private void drawLoadingText(String text) {
                client.drawLoadingText(35, text);
        }
		
        private void drawLoadingText(int amount, String text) {
                client.drawLoadingText(amount, text);
        }

        private String getCacheLink() {
                return cacheLink;
        }
		
        private String getCacheDir() {
                return cacheDir;
        }

        private int getCacheVersion() {
                return VERSION;
        }
		
		private int pauseHandlerDelay1000Equals1Second() {
				return pauseHandlerDelay;
		}
	
		private String localCacheFile() {
				return getCacheDir() + getArchivedName();
		}
        public CacheDownloader downloadCache() {
                try {
                File location = new File(getCacheDir());
                File version = new File(getCacheDir() + "/cacheVersion" + getCacheVersion() + ".dat");
                if(!location.exists()) {
						cacheHasUpdated();
                        downloadFile(getCacheLink(), getArchivedName());
                        BufferedWriter versionFile = new BufferedWriter(new FileWriter(getCacheDir() + "/cacheVersion" + getCacheVersion() + ".dat"));
                        versionFile.close();
                } else {
                        if(!version.exists()) {
								cacheHasUpdated();
                                downloadFile(getCacheLink(), getArchivedName());
                                BufferedWriter versionFile = new BufferedWriter(new FileWriter(getCacheDir() + "/cacheVersion" + getCacheVersion() + ".dat"));
                                versionFile.close();
                        } else {
                                return null;
                        }
                }
                } catch(Exception e) {
                }
                return null;
        }			
			public void cacheHasUpdated() {
				JOptionPane.showMessageDialog(frame, "It seems you either, 1. Have no cache on localhost or, 2. You have an older version of the cache. Click OK to download the new cache.", "Cache Version Invalid", JOptionPane.WARNING_MESSAGE);
				}
        private void downloadFile(String adress, String localFileName) {
                OutputStream out = null;
                URLConnection conn;
                InputStream in = null;
                try {
                        URL url = new URL(adress);
                        out = new BufferedOutputStream(
                        new FileOutputStream(getCacheDir() + "/" +localFileName)); 
                        conn = url.openConnection();
                        in = conn.getInputStream(); 
                        byte[] data = new byte[BUFFER]; 
                        int numRead;
                        long numWritten = 0;
                        int length = conn.getContentLength();
                        while((numRead = in.read(data)) != -1) {
                                out.write(data, 0, numRead);
                                numWritten += numRead;
                                int percentage = (int)(((double)numWritten / (double)length) * 100D);
                                drawLoadingText(percentage, "Downloading Cache " + percentage + "%");
                        }
                        System.out.println(localFileName + "\t" + numWritten);
                        drawLoadingText("Finished downloading "+getArchivedName()+"!");
                } catch (Exception exception) {
                        exception.printStackTrace();
                } finally {
                        try {
                                if (in != null) {
                                        in.close();
                                }
                                if (out != null) {
                                        out.close();
                                }
                        } catch (IOException ioe) {
                        }
                }
				fileExists();
        }
        private String getArchivedName() {
                int lastSlashIndex = getCacheLink().lastIndexOf('/');
                if (lastSlashIndex >= 0 
                        && lastSlashIndex < getCacheLink().length() -1) { 
                        return getCacheLink().substring(lastSlashIndex + 1);
                } else {
                        System.err.println("error retreiving archivaed name.");
                }
                return "";
        }
	public void fileExists() {
    File file=new File(localCacheFile());
    boolean exists = file.exists();
    if (!exists) {
      System.out.println("The cache was not downloaded correctly.");
	  System.out.println("Please download it manually at:");
	  System.out.println(localCacheFile());
	  System.out.println("File Directory:");
	  System.out.println(getCacheDir());
	  pauseHandler();
	cacheDownloadError();
    }else {
      drawLoadingText("Your cache is downloaded and ready to un-zip!");
	  pauseHandler();
	  unZip();	  
    }
	}
	        private void unZip() {
            try {
                    InputStream in = 
                    new BufferedInputStream(new FileInputStream(fileToExtract));
                ZipInputStream zin = new ZipInputStream(in);
                ZipEntry e;
                while((e=zin.getNextEntry()) != null) {
                               if(e.isDirectory()) {
                        (new File(getCacheDir() + e.getName())).mkdir();
                               } else {
                    if (e.getName().equals(fileToExtract)) {
                        unzip(zin, fileToExtract);
                        break;
                    }
                               unzip(zin, getCacheDir() + e.getName());
                    }
					drawLoadingText("[UN-ZIP]: " + e.getName());
                }
                zin.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
			pauseHandler();
			unNeededCacheExists();
        }
        private void unzip(ZipInputStream zin, String s) 
                throws IOException {
                FileOutputStream out = new FileOutputStream(s);
                byte [] b = new byte[BUFFER];
                int len = 0;

                while ((len = zin.read(b)) != -1) {
                        out.write(b,0,len);
				}
        }
	public void unNeededCacheExists() {
    File file=new File(localCacheFile());
    boolean exists = file.exists();
    if (!exists) {
      System.out.println("Your cache was not downloaded correctly.");
	  System.out.println("Please try to re re run the client.");
    }else {
      System.out.println("Your cache is on your HDD");
	  System.out.println("Auto Cache Deleter Attempting to delete...");
	  delete();	  
    }
	}
	    public void delete() {	
    	try{
    		File file = new File(localCacheFile());
    		if(file.delete()){
    			drawLoadingText("[SUCCESS]" +file.getName()+ " was deleted!");
				System.out.println("[SUCCESS]" +file.getName()+ " was deleted!");
    		}else{
    			drawLoadingText("[ERROR]" +file.getName()+ " was not deleted.");
				System.out.println("[ERROR]" +file.getName()+ " was not deleted.");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	public void cacheDownloadError() {
		try {
            drawLoadingText("Cache Download Error - Contact an admin");
			}
			catch(Exception e){
    		e.printStackTrace();
    	}
			}
  public void pauseHandler() {
     try {
       Thread.currentThread().sleep(pauseHandlerDelay1000Equals1Second());
       }
     catch (InterruptedException e) {
       e.printStackTrace();
       }
     }  
}
/*************************
@author Google - Revised by Kozs
*************************/