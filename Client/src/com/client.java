package com;
import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.*;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import java.util.zip.*;
import java.applet.Applet;
import java.awt.Dimension;
import java.net.URL;
import java.net.URLConnection;
import java.applet.AppletContext;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Scanner;
import java.net.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.io.File;
import java.io.IOException;
import java.awt.event.*;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import javax.swing.filechooser.FileFilter;

import com.sign.signlink;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

public class client extends RSApplet {
	
    /**
	 * 
	 */
	 
	private static final long serialVersionUID = 1L;
	public RSFont newSmallFont, newRegularFont, newBoldFont, newFancyFont;
    public Sprite[] chatImages = new Sprite[6];
		public boolean isFullScreen = false;
	public boolean ohpbar = false;
	public AlertHandler alertHandler;
	public Sprite alertBack;
	public Sprite alertBorder;
	public Sprite alertBorderH;
	public int frontLight = 68;
	public int backLight = 820;
	public int rightLight = 0;
	public int middleLight = -1; // Cannot be 0
	public int leftLight = 0;
	public boolean newGameFrame = false;
		public String indexLocation(int cacheIndex, int index) {
		return signlink.findcachedir() + "index" + cacheIndex + "/" + (index != -1 ? index + ".gz" : "");
	}
		
	
		
		public void drawSmoothLoading(int i, String s)
	    {
	        for(float f = LP; f < (float)i; f = (float)((double)f + 0.29999999999999999D))
	            drawLoadingText((int)f, s);

	        LP = i;
	    }

		 public void drawOnBankInterface() {
    if ((openInterfaceID == 5292) && (RSInterface.interfaceCache[27000].message.equals("1"))) {
      int i = Integer.parseInt(RSInterface.interfaceCache[27001].message);
      int j = Integer.parseInt(RSInterface.interfaceCache[27002].message);

      for (int k = 0; k <= i; k++) {
        RSInterface.interfaceCache[(22025 + k)].sprite1 = new Sprite("Bank/TAB 3");
        RSInterface.interfaceCache[(22025 + k)].tooltip = new StringBuilder().append("Click here to select tab ").append(k + 1).toString();
      }
      for (int k = i + 1; k <= 8; k++) {
        RSInterface.interfaceCache[(22024 + k)].sprite1 = new Sprite("");
        RSInterface.interfaceCache[(22024 + k)].tooltip = "";
      }
      if (i != 8) {
        RSInterface.interfaceCache[(22025 + i)].sprite1 = new Sprite("Bank/TAB 4");
        RSInterface.interfaceCache[(22025 + i)].tooltip = "Drag an item here to create a new tab";
      }

      if (j == -1) {
        RSInterface.interfaceCache[22024].sprite1 = new Sprite("Bank/TAB 1");
      } else if (j > 0) {
        RSInterface.interfaceCache[(22024 + j)].sprite1 = new Sprite("Bank/TAB 2");
        RSInterface.interfaceCache[22024].sprite1 = new Sprite("Bank/TAB 1");
      } else {
        RSInterface.interfaceCache[22024].sprite1 = new Sprite("Bank/TAB 0");
      }

      RSInterface.interfaceCache[27000].message = "0";
    }
  }

	
	public void repackCacheIndex(int cacheIndex) {
		System.out.println("Started repacking index " + cacheIndex + ".");
		int indexLength = new File(indexLocation(cacheIndex, -1)).listFiles().length;
		File[] file = new File(indexLocation(cacheIndex, -1)).listFiles();
		try {
			for (int index = 0; index < indexLength; index++) {
				int fileIndex = Integer.parseInt(getFileNameWithoutExtension(file[index].toString()));
				byte[] data = fileToByteArray(cacheIndex, fileIndex);
				if(data != null && data.length > 0) {
					decompressors[cacheIndex].method234(data.length, data, fileIndex);
					System.out.println("Repacked " + fileIndex + ".");
				} else {
					System.out.println("Unable to locate index " + fileIndex + ".");
				}
			}
		} catch(Exception e) {
			System.out.println("Error packing cache index " + cacheIndex + ".");
		}
		System.out.println("Finished repacking " + cacheIndex + ".");
	}

	public byte[] fileToByteArray(int cacheIndex, int index) {
		try {
			if (indexLocation(cacheIndex, index).length() <= 0 || indexLocation(cacheIndex, index) == null) {
				return null;
			}
			File file = new File(indexLocation(cacheIndex, index));
			byte[] fileData = new byte[(int)file.length()];
			FileInputStream fis = new FileInputStream(file);
			fis.read(fileData);
			fis.close();
			return fileData;
		} catch(Exception e) {
			return null;
		}
	}
	public int positions[] = new int[2000];
	public int landScapes[] = new int[2000];
	public int objects[] = new int[2000];

	public static String getFileNameWithoutExtension(String fileName) {
		File tmpFile = new File(fileName);
		tmpFile.getName();
		int whereDot = tmpFile.getName().lastIndexOf('.');
		if (0 < whereDot && whereDot <= tmpFile.getName().length() - 2) {
			return tmpFile.getName().substring(0, whereDot);
		}
		return "";
	}
		public static String chatColorHex = "00FFFF";
	public static int getChatColor() {
		int convertHexCode = Integer.parseInt(chatColorHex, 16);
		return convertHexCode;

	}

		public String getRank(int i){
		switch(i){
			case 1:
			return "Sir";
			case 2:
			return "Lady";
			case 3:
			return "King";
			case 4:
			return "Queen";
			case 5:
			return "Gangster";
			case 6:
			return "Peedo";
			case 7:
			return "Demon";
			case 8:
			return "Angel";
			case 9:
			return "Unstoppable";
			case 10:
			return "Nerd";
			case 11:
			return "Psychopath";
			case 12:
			return "Immortal";
			case 13:
			return "Helper";
			case 14:
			return "Hell Raiser";
			case 15:
			return "Baron";
			case 16:
			return "Duke";
			case 17:
			return "Lady";
			case 18:
			return "Dame";
			case 19:
			return "Dudette";
			case 20:
			return "Baroness";
			case 21:
			return "Countess";
			case 22:
			return "Overlordess";
			case 23:
			return "Duchess";
			case 24:
			return "Queen";		
			case 25:
			return "Donator";		
			case 26:
			return "Extreme Donator";		
			case 27:
			return "Veteran";		
			case 28:
			return "Owner";		
			case 29:
			return "Co-Owner";
			case 30:
			return "Trusted-Dice";
			case 31:
			return "Trusted";
			case 32:
			return "Dicer";
			case 33:
			return "AdminLikeBaws";		
			case 34:
			return "The best";		
			case 35:
			return "Gladiator";	
			case 36:
			return "Survivor";	
			case 37:
			return "Dominator";
			case 38:
			return "The Boss";
		}
		return "";
	}
	



	public void setNewMaps() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(signlink.findcachedir()+"Data/Maps/names.txt"));
			String s;
			int D = 0; 
			while ((s = in.readLine()) != null)  {
				positions[D] = Integer.parseInt(s.substring(s.indexOf("=")+1,s.indexOf("(")));
				landScapes[D] = Integer.parseInt(s.substring(s.indexOf("(")+1,s.indexOf(")")));
				objects[D] = Integer.parseInt(s.substring(s.indexOf("[")+1,s.indexOf("]")));
					D++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		public byte[] GetMap(int Index) {
		try {
			File Map = new File(signlink.findcachedir()+"Data/Maps/"+Index+".gz");
			byte[] aByte = new byte[(int)Map.length()];
			FileInputStream Fis = new FileInputStream(Map);
				Fis.read(aByte);
			//	System.out.println(""+Index+" aByte = ["+aByte+"]!");
				Fis.close();
			return aByte;
		} catch(Exception e) {
			return null;
		}
	}


	public void maps() {
		for(int MapIndex = 0; MapIndex < 5173; MapIndex++) {
			byte[] abyte0 = GetMap(MapIndex);
			if(abyte0 != null && abyte0.length > 0) {
				decompressors[4].method234(abyte0.length, abyte0, MapIndex);
			}
		}
	}

	public static final byte[] ReadFile(String s, boolean antiLeech) {
		try {
			byte abyte0[];
			File file = new File(s);
			int i = (int)file.length();
			abyte0 = new byte[i];
			DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
			datainputstream.readFully(abyte0, 0, i);
			datainputstream.close();
			return abyte0;
		} catch(Exception e) {
			System.out.println((new StringBuilder()).append("Read Error: ").append(s).toString());
			return null;
		}
	}

	
	private void setAutoCastOff() {
		autoCast = false;
		autocastId = 0;
		sendPacket185(6666,-1,135);
		pushMessage("Autocast spell cleared.", 0, "");
	}
	
	private void sendData(String inputString) {
		stream.createFrame(103);
		stream.writeWordBigEndian(inputString.length() - 1);
		stream.writeString(inputString.substring(2));
	}
	
	public void preloadModels() {
		File file = new File(signlink.findcachedir() + "/Models/");
		File afile[] = file.listFiles();
		for(int i = 0; i < afile.length; i++) {
			String s = afile[i].getName();
			byte abyte0[] = ReadFile(signlink.findcachedir() + "/Models/" + s);
			Model.method460(abyte0, Integer.parseInt(getFileNameWithoutExtension(s)));
		}

	}
	
	private static void openUpWebSite(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url)); 	
		} catch (Exception e) {
			
		}
	}
	
	public void hitmarkDraw(Entity e, int hitLength, int markType, int icon, int damage, int move, int opacity, int focusPlayer, int focusNPC) {
		hitLength *= 2;
		if (damage > 0) {
			if(icon >= 0 && icon < hitMarkIcons.length)
				hitMarkIcons[icon].drawSprite1(spriteDrawX - 34, spriteDrawY - 14 + move, opacity);
			int focusType = (e == myPlayer || (focusPlayer != 0 && e == playerArray[focusPlayer]) || (focusNPC != 0 && e == npcArray[focusNPC])) ? 0 : 1;
			hitMarkBacks[markType][focusType][0].drawSprite1(spriteDrawX - 12, spriteDrawY - 12 + move, opacity);
			for (int i = 1; i <= hitLength; i++)
				hitMarkBacks[markType][focusType][1].drawSprite1(spriteDrawX - 12 + i*4, spriteDrawY - 12 + move, opacity);
			hitMarkBacks[markType][focusType][2].drawSprite1(spriteDrawX - 8 + hitLength*4, spriteDrawY - 12 + move, opacity);

			if(hitLength == 6)
				normalFont.drawText(0xffffff, String.valueOf(damage), spriteDrawY + 3 + move, spriteDrawX+4);
			else if(hitLength == 4)
				normalFont.drawText(0xffffff, String.valueOf(damage), spriteDrawY + 3 + move, spriteDrawX);
			else
				normalFont.drawText(0xffffff, String.valueOf(damage), spriteDrawY + 3 + move, spriteDrawX-4);
		} else {
			hitMarkIcons[0].drawSprite1(spriteDrawX - 12, spriteDrawY - 14 + move, opacity);
		}
	}
	
	private void drawGameFrameOnFullScreen() {
		if(toggleFullscreen) {
			drawChatArea(main3DArea,xPaddingchatBackImage,yPaddingchatBackImage + getChatBackIncreaseY());
			drawMinimap(main3DArea,xPaddingmapBackImage + getMapIncreaseX(),yPaddingmapBackImage + getMapIncreaseY());
			drawTabArea(main3DArea,xPaddingtabBack + getTabIncreaseX(),yPaddingtabBack + getTabIncreaseY());
			if (!contextMenuOpen) {
				processRightClick();
				drawTooltip();
			} else {
				drawMenu(4,4);
			}
		}
	}
	
	public static String capitalizeFirstChar(String s) {
		try {
			String n;
			if(s != "")
				return n = (s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase()).trim();
		} catch (Exception e) {
		}
		return s;
	}
	
	public boolean quickChat = false;
	public static boolean displayScrollbar;
	public void displayItemSearch() {
		try {
			quickChat = false;
			if (amountOrNameInput != "") {
				itemSearch(amountOrNameInput);
			}
			DrawingArea.setDrawingArea(121, 8, 512, 7);
		//	search.drawSprite(18, 18);
			for (int j = 0; j < totalItemResults; j++) {
				int x = super.mouseX;
				int y = super.mouseY;
				final int yPos = 21 + j * 14 - itemResultScrollPos;
				if (yPos > 0 && yPos < 210) {
					String n = itemResultNames[j];
					for(int i = 0; i <= 20; i++)
						if(n.contains("<img="+i+">"))
							n = n.replaceAll("<img="+i+">", "");
					boldFont.method591(capitalizeFirstChar(n), 78, 0xA05A00, yPos);
					boldFont.method591(capitalizeFirstChar(n), 78, 0xA05A00, yPos);
					if(x > 74 && x < 495 && y > 338+yPos-13 && y < 338+yPos+2) {
						DrawingArea.method335(0x807660, yPos - 12, 424, 15, 60, 75);
						Sprite itemImg = ItemDef.getSprite(itemResultIDs[j], 1, 0);
						if(itemImg != null)
							itemImg.drawSprite(22, 20);
						GEItemId = itemResultIDs[j];
					}
				}
			}
			DrawingArea.drawPixels(113, 8, 74, 0x807660, 2);
			DrawingArea.defaultDrawingAreaSize();
			if (totalItemResults > 8) {
				displayScrollbar = true;
			//	drawScrollbar(112, itemResultScrollPos, 8, 496, totalItemResults * 14);
				//drawScrollbar(112, itemResultScrollPos, 8, 496, totalItemResults * 14 + 12, 0);
			} else {
				displayScrollbar = false;
			}
			boolean showMatches = true; showMatches = true;
			if (amountOrNameInput.length() == 0) {
				boldFont.method382(0xA05A00, 259, "Grand Exchange Item Search", 30, false);
				boldFont.method382(0xA05A00, 259, "To search for an item, start by typing part of it's name.", 80, false);
				boldFont.method382(0xA05A00, 259, "Then, simply select the item you want from the results on the display.", 80+15, false);
				//chatTextDrawingArea.drawText(0xffffff, amountOrNameInput + "*",  32, 133);
				showMatches = false;
			}
			if (totalItemResults == 0 && showMatches) {
				boldFont.method382(0xA05A00, 259, "No matching items found", 80, false);
			}
			DrawingArea.method335(0x807660, 121, 506, 15, 120, 7);//box
			//chatTextDrawingArea.drawText(0, "<img=8>", 133, 12);
			boldFont.method591(amountOrNameInput + "*", 28, 0xffffff, 133);
			//chatTextDrawingArea.drawText(0xffffff, amountOrNameInput + "*", 133, 122);
			DrawingArea.method339(121, 0x807660, 506, 7);//line
			drawClose(496, 122, 496, 345+112, 496+19, 361+112);
			drawClose(496, 122, 496, 345+112, 496+19, 361+112);
			drawClose(496, 122, 496, 345+112, 496+19, 361+112);
			//Search.drawSprite(11, 122); DISABLED
		} catch (Exception e) {
			
		}
	}
	public void drawClose(int x, int y, int x2, int y2, int x3, int y3) {
		//cacheSprite[31].drawSprite(x, y);
		if (super.mouseX >= x2 && super.mouseX <= x3 && super.mouseY >= y2 && super.mouseY <= y3) {
			//cacheSprite[32].drawSprite(x, y);
		}
	}
	
	public ItemList getItemList(int i) {
		for (int j = 0; j < ItemList.length; j++) {
			if (ItemList[j] != null) {
				if (ItemList[j].itemId == i) {
					return ItemList[j];				
				}		
			}		
		}
		return null;
	}
	
	public int getItemShopValue(int itemId) {
		for (int i = 0; i < 24470; i++) {
			if (ItemList[i] != null) {
				if (ItemList[i].itemId == itemId) {
					return (int)ItemList[i].ShopValue;
				}
			}	
		}
		return 0;
	}
	
	public void loadItemPrices(String filename) {
		try {
			Scanner s = new Scanner(new File("" + filename));
			while (s.hasNextLine()) {
				String[] line = s.nextLine().split(" ");
				ItemList temp = getItemList(Integer.parseInt(line[0]));
				if (temp != null)
					temp.ShopValue = Integer.parseInt(line[1]);
			}		
		} catch (IOException e) {
			e.printStackTrace();		
		}
	}
	
	public boolean loadItemList(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[20];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader(""+FileName));
		} catch(FileNotFoundException fileex) {
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			return false;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("item")) {
					int[] Bonuses = new int[12];
					for (int i = 0; i < 12; i++) {
						if (token3[(6 + i)] != null) {
							Bonuses[i] = Integer.parseInt(token3[(6 + i)]);
						} else {
							break;
						}
					}
					double[] soaking = new double[3];
					try {
					for (int i = 18; i < 21; i++) {
						if (token3[i] != null) {
							//soaking[i - 18] = Double.parseDouble(token3[i]);
						} else {
							break;
						}
					}
					} catch (Exception _ex) {}
					newItemList(Integer.parseInt(token3[0]), token3[1].replaceAll("_", " "), token3[2].replaceAll("_", " "), Double.parseDouble(token3[4]));
				}
			} else {
				if (line.equals("[ENDOFITEMLIST]")) {
					try { characterfile.close(); } catch(IOException ioexception) { }
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return false;
	}
	
	public void newItemList(int ItemId, String ItemName, String ItemDescription, double ShopValue) {
		int slot = -1;
		for (int i = 0; i < 24470; i++) {
			if (ItemList[i] == null) {
				slot = i;
				break;
			}
		}
		if(slot == -1) return;		// no free slot found
		ItemList newItemList = new ItemList(ItemId);
		newItemList.itemName = ItemName;
		newItemList.itemDescription = ItemDescription;
		newItemList.ShopValue = ShopValue;
		ItemList[slot] = newItemList;
	}
	
	public ItemList ItemList[] = new ItemList[24470];
	
	public static int totalItemResults;
	public String itemResultNames[] = new String[100];
	public int itemResultIDs[] = new int[100];
	public static int itemResultScrollPos;
	public Sprite[] scrollPart = new Sprite[16];
	public void itemSearch(String n) {
		if (n == null || n.length() == 0) {
			totalItemResults = 0;
			return;
		}
		String searchName = n;
		String searchParts[] = new String[100];
		int totalResults = 0;
		do {
			int k = searchName.indexOf(" ");
			if (k == -1)
				break;
			String part = searchName.substring(0, k).trim();
			if (part.length() > 0)
				searchParts[totalResults++] = part.toLowerCase();
			searchName = searchName.substring(k + 1);
		} while (true);
		searchName = searchName.trim();
		if (searchName.length() > 0)
			searchParts[totalResults++] = searchName.toLowerCase();
		totalItemResults = 0;
		label0: for (int id = 0; id < ItemDef.totalItems; id++) {
			ItemDef item = ItemDef.forID(id);
			if (item.certTemplateID != -1 || item.lendTemplateID != -1 || item.name == null || item.name == "Picture" || item.certID == 18786 || item.name == "Null")
				continue;
			String result = item.name.toLowerCase();
			for (int idx = 0; idx < totalResults; idx++)
				if (result.indexOf(searchParts[idx]) == -1) 
					continue label0;
			
			//int value = getItemShopValue(id);
			
			if(ItemList[id].ShopValue != 0) {
					itemResultNames[totalItemResults] = result;
					itemResultIDs[totalItemResults] = id;
					totalItemResults++;
			}
			if (totalItemResults >= itemResultNames.length)
				return;
		}
	}
	
	private void load3DArea() {
		Texture.method365(getScreenWidth(), getScreenHeight());
		fullScreenTextureArray = Texture.anIntArray1472;
		Texture.method365(519, 165);
		anIntArray1180 = Texture.anIntArray1472;
		Texture.method365(246, 335);
		anIntArray1181 = Texture.anIntArray1472;
		Texture.method365(main3DAreaWidth, main3DAreaHeight);
		anIntArray1182 = Texture.anIntArray1472;
		int ai[] = new int[9];
		for(int i8 = 0; i8 < 9; i8++)
		{
			int k8 = 128 + i8 * 32 + 15;
			int l8 = 600 + k8 * 3;
			int i9 = Texture.anIntArray1470[k8];
			ai[i8] = l8 * i9 >> 16;
		}

		WorldController.method310(600, 900, main3DAreaWidth, main3DAreaHeight, ai);
	}
	
	private void switchToNormal() {
		toggleFullscreen = false;
		sendFrame36(652,0);
		recreateClientFrame(false,765,503,false,1,false);
		welcomeScreenRaised = true;
	}
	
	private void switchToFullScreen() {
		toggleFullscreen = !toggleFullscreen;
		sendFrame36(652,toggleFullscreen ? 1 : 0);
		if(toggleFullscreen)
			recreateClientFrame(true,getScreenWidth(),getScreenHeight(),false,0,true);
		else {
			recreateClientFrame(false,765,503,false,1,false);
			welcomeScreenRaised = true;
		}
	}
	
	public void recreateClientFrame(boolean undecorative, int width, int height, boolean resizable, int displayMode, boolean toggle) {
		main3DAreaWidth = (displayMode == 1) ? 512 : instance.getScreenWidth();
		main3DAreaHeight = (displayMode == 1) ? 334 : client.getScreenHeight();
		load3DArea();
		instance.recreateClientFrame(undecorative,width,height,resizable);
		System.out.println("displayMode: " + displayMode);

		main3DArea = new RSImageProducer(main3DAreaWidth, main3DAreaHeight, getGameComponent());
		
		super.mouseX = super.mouseY = -1;
	}
	
	public static int getChatBackIncreaseY() {
		return chatIncreaseY = toggleFullscreen ? (getScreenHeight() - 503) : 0;
	}
	
	private int getMapIncreaseX() {
		return toggleFullscreen ? (getScreenWidth() - 765 + 52) : 0;
	}
	
	private int getMapIncreaseY() {
		return toggleFullscreen ? 4 : 0;
	}
	
	private int getTabIncreaseX() {
		return toggleFullscreen ? (getScreenWidth() - 765) : 0;
	}
	
	private int getTabIncreaseY() {
		return toggleFullscreen ? (getScreenHeight() - 503) : 0;
	}
	
	private int getScreenWidth() {
		int i = 0;
		if(toggleFullscreen) {
			toolkit = Toolkit.getDefaultToolkit();
			screenSize = toolkit.getScreenSize();
			i = (int)screenSize.getWidth();
		} else {
			i = 765;
		}
		return i;
	}
	
	private static int getScreenHeight() {
		int i = 0;
		if(toggleFullscreen) {
			toolkit = Toolkit.getDefaultToolkit();
			screenSize = toolkit.getScreenSize();
			i = (int)screenSize.getHeight();
		} else {
			i = 503;
		}
		return i;
	}
	
	private int yPaddingchatBackImage = 338, xPaddingchatBackImage = 0;
	private int yPaddingmapBackImage = 0, xPaddingmapBackImage = 516;
	private int xPaddingtabBack = 519, yPaddingtabBack = 168;
	public static int chatIncreaseY = 0, chatIncreaseX = 0;
	public static boolean toggleFullscreen;
	private static Toolkit toolkit;
	private static Dimension screenSize;
	private int main3DAreaWidth = 512, main3DAreaHeight = 334;
	public static client instance;
	//
	
	public Sprite magicAuto;
	public boolean autoCast = false;
	private int autocastId = 0;
	public int MapX, MapY;
	public static int spellID = 0;
	public static String defaultserverip = "127.0.0.1";
	public static String serverip = ""+defaultserverip+"";
	public static int port = 43594;
	
	private static String intToKOrMilLongName(int i) {
		String s = String.valueOf(i);
		for (int k = s.length() - 3; k > 0; k -= 3)
			s = s.substring(0, k) + "," + s.substring(k);
		if (s.length() > 8)
			s = "@gre@" + s.substring(0, s.length() - 8) + " million @whi@("
					+ s + ")";
		else if (s.length() > 4)
			s = "@cya@" + s.substring(0, s.length() - 4) + "K @whi@(" + s + ")";
		return " " + s;
	}

	public final String methodR(int j) {
		if (j >= 0 && j < 10000)
			return String.valueOf(j);
		if (j >= 10000 && j < 10000000)
			return j / 1000 + "K";
		if (j >= 10000000 && j < 999999999)
			return j / 1000000 + "M";
		if (j >= 999999999)
			return "*";
		else
			return "?";
	}
	
	public static final byte[] ReadFile(String s) {
		try {
			byte abyte0[];
			File file = new File(s);
			int i = (int)file.length();
			abyte0 = new byte[i];
			DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
			datainputstream.readFully(abyte0, 0, i);
			datainputstream.close();
			return abyte0;
		} catch(Exception e) {
			System.out.println((new StringBuilder()).append("Read Error: ").append(s).toString());
			return null;
		}
	}

public void models() {
		for(int ModelIndex = 0; ModelIndex < 50000; ModelIndex++) {
			byte[] abyte0 = getModel(ModelIndex);
			if(abyte0 != null && abyte0.length > 0) {
			decompressors[1].method234(abyte0.length, abyte0, ModelIndex);
			pushMessage("Model added successfully!", 0, "");
			}
		}
	}
public byte[] getModel(int Index) {
		try {
			File Model = new File("./Models/"+Index+".gz");
			byte[] aByte = new byte[(int)Model.length()];
			FileInputStream fis = new FileInputStream(Model);
			fis.read(aByte);
			pushMessage("aByte = ["+aByte+"]!", 0, "");
			fis.close();
			return aByte;
		}
		catch(Exception e)
		{return null;}
	}
	
	
	private void stopMidi() {
		if (SoundProvider.getSingleton().midiSequencer.isRunning())
			SoundProvider.getSingleton().midiSequencer.stop();
		signlink.midifade = 0;
		signlink.midi = "stop";
	}
	
	private boolean menuHasAddFriend(int j) {
		if(j < 0)
			return false;
		int k = menuActionID[j];
		if(k >= 2000)
			k -= 2000;
		return k == 337;
	}
	
	public void drawChannelButtons(int paddingX, int paddingY) { 
		if(toggleFullscreen)
			chatArea[2].drawSprite(5 + paddingX, 142 + paddingY);
		
		String text[] = { "On", "Friends", "Off", "Hide" };
		int textColor[] = { 65280, 0xffff00, 0xff0000, 65535 };
		if(cButtonCPos != 32 && cButtonCPos != 55 && cButtonCPos != 57) { // cheap hax 
		switch(cButtonCPos) {
			case 0:
				chatButtons[1].drawSprite(5 + paddingX, 142 + paddingY);
				break;
			case 1:
				chatButtons[1].drawSprite(71 + paddingX, 142 + paddingY);
				break;
			case 2:
		
				chatButtons[1].drawSprite(137 + paddingX, 142 + paddingY);
				break;
			case 3:
				chatButtons[1].drawSprite(203 + paddingX, 142 + paddingY);
				break;
			case 4:
				chatButtons[1].drawSprite(269 + paddingX, 142 + paddingY);
				break;
			case 5:
				chatButtons[1].drawSprite(335 + paddingX, 142 + paddingY);
				break;
		}
		if(cButtonHPos == cButtonCPos) {
			switch(cButtonHPos) {
				case 0:
					chatButtons[2].drawSprite(5 + paddingX, 142 + paddingY);
					break;
				case 1:
					chatButtons[2].drawSprite(71 + paddingX, 142 + paddingY);
					break;
				case 2:
					chatButtons[2].drawSprite(137 + paddingX, 142 + paddingY);
					break;
				case 3:
					chatButtons[2].drawSprite(203 + paddingX, 142 + paddingY);
					break;
				case 4:
					chatButtons[2].drawSprite(269 + paddingX, 142 + paddingY);
					break;
				case 5:
					chatButtons[2].drawSprite(335 + paddingX, 142 + paddingY);
					break;
				case 6:
					chatButtons[3].drawSprite(404 + paddingX, 142 + paddingY);
					break;
			}
		} else {
			switch(cButtonHPos) {
				case 0:
					chatButtons[0].drawSprite(5 + paddingX, 142 + paddingY);
					break;
				case 1:
					chatButtons[0].drawSprite(71 + paddingX, 142 + paddingY);
					break;
				case 2:
					chatButtons[0].drawSprite(137 + paddingX, 142 + paddingY);
					break;
				case 3:
					chatButtons[0].drawSprite(203 + paddingX, 142 + paddingY);
					break;
				case 4:
					chatButtons[0].drawSprite(269 + paddingX, 142 + paddingY);
					break;
				case 5:
					chatButtons[0].drawSprite(335 + paddingX, 142 + paddingY);
					break;
				case 6:
					chatButtons[3].drawSprite(404 + paddingX, 142 + paddingY);
					break;
			}
		}
		smallFont.method389(true, 425 + paddingX, 0xffffff, "Report Abuse", 157 + paddingY);
		smallFont.method389(true, 26 + paddingX, 0xffffff, "All", 157 + paddingY);
		smallFont.method389(true, 86 + paddingX, 0xffffff, "Game", 157 + paddingY);
		
		smallFont.method389(true, 150 + paddingX, 0xffffff, "Public", 152 + paddingY);
		smallFont.method389(true, 212 + paddingX, 0xffffff, "Private", 152 + paddingY);
		smallFont.method389(true, 286 + paddingX, 0xffffff, "Clan", 152 + paddingY);
		smallFont.method389(true, 349 + paddingX, 0xffffff, "Trade", 152 + paddingY);
		smallFont.method382(textColor[publicChatMode], 164 + paddingX, text[publicChatMode], 163 + paddingY, true);
		smallFont.method382(textColor[privateChatMode], 230 + paddingX, text[privateChatMode], 163 + paddingY, true);
		smallFont.method382(textColor[clanChatMode], 296 + paddingX, text[clanChatMode], 163 + paddingY, true);
		smallFont.method382(textColor[tradeMode], 362 + paddingX, text[tradeMode], 163 + paddingY, true);
	}
	}

	public static String capitalize(String s) {
		
		for (int i = 0; i < s.length(); i++) {
			if (i == 0) {
				s = String.format( "%s%s",
						 Character.toUpperCase(s.charAt(0)),
						 s.substring(1) );
			}
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				if (i + 1 < s.length()) {
					s = String.format( "%s%s%s",
							 s.subSequence(0, i+1),
							 Character.toUpperCase(s.charAt(i + 1)),
							 s.substring(i+2) );
				}
			}
		}
		return s;
	}
	public static Jframe frame; // no then it will crash in webclient L
	private void drawChatArea(RSImageProducer chatback, int paddingX, int paddingY) { 
		int maximumScrollRange = 210;
		byte minimumScrollRange = 0;
		chatback.initDrawingArea();
		Texture.anIntArray1472 = anIntArray1180;
		if(toggleFullscreen) {
			if(!messagePromptRaised && aString844 == null && backDialogID == -1 && dialogID == -1 && inputDialogState == 0)
				DrawingArea.drawAlphaGradient(7 + paddingX, 7 + paddingY,505, 130, 0xB5B5B5, 0, 70);
			else
				chatArea[1].drawSprite1(paddingX, paddingY,128);
		} else
				chatArea[0].drawSprite(paddingX, paddingY);
		
		drawChannelButtons(paddingX, paddingY);
		RSFont textDrawingArea = newRegularFont;
		if(messagePromptRaised) {
			boldFont.drawText(0, aString1121, 60 + paddingY, 259);
			boldFont.drawText(128, promptInput + "*", 80 + paddingY, 259);
		} else if(inputDialogState == 1) {
			boldFont.drawText(0, "Enter amount:", 60 + paddingY, 259);
			boldFont.drawText(128, amountOrNameInput + "*", 80 + paddingY, 259);
		} else if(inputDialogState == 2) {
			boldFont.drawText(0, "Enter name:", 60 + paddingY, 259);
			boldFont.drawText(128, amountOrNameInput + "*", 80 + paddingY, 259);
		} else if(aString844 != null) {
			boldFont.drawText(0, aString844, 60 + paddingY, 259);
			boldFont.drawText(128, "Click to continue", 80 + paddingY, 259);
		} else if(backDialogID != -1) {
			drawInterface(0, 20, RSInterface.interfaceCache[backDialogID], 20 + paddingY);
		} else if(dialogID != -1) {
			drawInterface(0, 20, RSInterface.interfaceCache[dialogID], 20 + paddingY);
		} else {
			int totalLines = 0;
			DrawingArea.setDrawingArea(122 + paddingY, 8 + paddingX, 497 + paddingX, 7 + paddingY);
			for(int k = 0; k < 500; k++)
			if(chatMessages[k] != null) {
				int chatType = chatTypes[k];
				int yPos = (70 - (totalLines - 3) * 14) + anInt1089 + 5;
				String s1 = chatNames[k];
				byte byte0 = 0;
				if (s1 != null && s1.startsWith("@cr1@")) {
					s1 = s1.substring(5);
					byte0 = 1;
				} 
				if (s1 != null && s1.startsWith("@cr2@")) {
					s1 = s1.substring(5);
					byte0 = 2;
				} 
				if (s1 != null && s1.startsWith("@cr3@")) {
					s1 = s1.substring(5);
					byte0 = 3;
				} 
				if (s1 != null && s1.startsWith("@cr4@")) {
					s1 = s1.substring(5);
					byte0 = 4;
				} 
				if (s1 != null && s1.startsWith("@cr5@")) {
					s1 = s1.substring(5);
					byte0 = 5;
				} 
				if (s1 != null && s1.startsWith("@cr6@")) {
					s1 = s1.substring(5);
					byte0 = 6;
				}
				if(chatType == 0) {
					if (chatTypeView == 5 || chatTypeView == 0) {
						if(yPos > minimumScrollRange && yPos < maximumScrollRange) {
							textDrawingArea.drawBasicString(chatMessages[k], 11, yPos+paddingY, 0, -1);
							//(false, 11, 0, chatMessages[k], yPos + paddingY);//chat color enabled
						}
						totalLines++;
					}
				}
				if((chatType == 1 || chatType == 2) && (chatType == 1 || publicChatMode == 0 || publicChatMode == 1 && isFriendOrSelf(s1))) {
					if (chatTypeView == 1 || chatTypeView == 0) {
						if(yPos > minimumScrollRange && yPos < maximumScrollRange) {
							int xPos = 11;
							if(byte0 > 0) {
								modIcons[byte0-1].drawSprite(xPos + 1, yPos - 12 + paddingY);
								xPos += 14;
							}
							textDrawingArea.drawBasicString(s1 + ":", xPos, yPos+paddingY, toggleFullscreen ? 0xffffff : 0, -1);
							//textDrawingArea.method385(toggleFullscreen ? 0xffffff : 0, s1 + ":", yPos + paddingY, xPos);
							xPos += textDrawingArea.getTextWidth(s1) + 8;
							textDrawingArea.drawBasicString(chatMessages[k], xPos, yPos+paddingY,  toggleFullscreen ? 0xffffff : 255, -1);				
							
							
							//textDrawingArea.method389(false, xPos, toggleFullscreen ? 0xffffff : 255, chatMessages[k], yPos + paddingY);
						}
						totalLines++;
					}
				}
				if((chatType == 3 || chatType == 7) && (splitPrivateChat == 0 || chatTypeView == 2) && (chatType == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s1))) {
					if (chatTypeView == 2 || chatTypeView == 0) {
						if(yPos > minimumScrollRange && yPos < maximumScrollRange) {
							int k1 = 11;
							textDrawingArea.drawBasicString("From", k1, yPos+paddingY, 0, -1);
							k1 += textDrawingArea.getTextWidth("From ");
							if(byte0 > 0) {
								modIcons[byte0-1].drawSprite(k1 + 1, yPos - 12 + paddingY);
								k1 += 12;
							}
							textDrawingArea.drawBasicString(s1 + ":", k1, yPos+paddingY, 0, -1);
							//textDrawingArea.method385(0, s1 + ":", yPos + paddingY, k1);
							k1 += textDrawingArea.getTextWidth(s1) + 8;
							textDrawingArea.drawBasicString(chatMessages[k], k1, yPos+paddingY, toggleFullscreen ? 0xFF5256 : 0x800000, -1);
							//textDrawingArea.method385(toggleFullscreen ? 0xFF5256 : 0x800000, chatMessages[k], yPos + paddingY, k1);
						}
						totalLines++;
					}
				}
				if(chatType == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s1))) {
					if (chatTypeView == 3 || chatTypeView == 0) {
						if(yPos > minimumScrollRange && yPos < maximumScrollRange) {
							//textDrawingArea.method385(0x800080, s1 + " " + chatMessages[k], yPos + paddingY, 11);
							textDrawingArea.drawBasicString(s1 + " "+ chatMessages[k], 11, yPos + paddingY, 0x800080, -1);
						}
						totalLines++;
					}
				}
				if(chatType == 5 && splitPrivateChat == 0 && privateChatMode < 2) {
					if (chatTypeView == 2 || chatTypeView == 0) {
						if(yPos > minimumScrollRange && yPos < maximumScrollRange) {
							//textDrawingArea.method385(toggleFullscreen ? 0xFF5256 : 0x800000, chatMessages[k], yPos + paddingY, 11);
							textDrawingArea.drawBasicString(chatMessages[k], 11, yPos+paddingY, toggleFullscreen ? 0xFF5256 : 0x800000, -1);
						}
						totalLines++;
					}
				}
				if(chatType == 6 && (splitPrivateChat == 0 || chatTypeView == 2) && privateChatMode < 2) {
					if (chatTypeView == 2 || chatTypeView == 0) {
						if(yPos > minimumScrollRange && yPos < maximumScrollRange) {
							//textDrawingArea.method385(0, "To " + s1 + ":", yPos + paddingY, 11);
							textDrawingArea.drawBasicString("To " + s1 + ":", 11, yPos+paddingY, 0, -1);
							//textDrawingArea.method385(toggleFullscreen ? 0xFF5256 : 0x800000, chatMessages[k], yPos + paddingY, 15 + textDrawingArea.getTextWidth("To :" + s1));
							textDrawingArea.drawBasicString(chatMessages[k], 15 + textDrawingArea.getTextWidth("To :" + s1), yPos+paddingY, toggleFullscreen ? 0xFF5256 : 0x800000, -1);
						}
						totalLines++;
					}
				}
				if(chatType == 8 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s1))) {
					if (chatTypeView == 3 || chatTypeView == 0) {
						if(yPos > minimumScrollRange && yPos < maximumScrollRange) {
							//textDrawingArea.method385(0x7e3200, s1 + " " + chatMessages[k], yPos + paddingY, 11);
							textDrawingArea.drawBasicString(s1 + " " + chatMessages[k], 11, yPos+paddingY, 0x7e3200, -1);
						}
						totalLines++;
					}
					if(chatType == 11 && (clanChatMode == 0)) {
						if (chatTypeView == 11) {
						if(yPos > minimumScrollRange && yPos < maximumScrollRange) {
							//textDrawingArea.method385(0x7e3200, s1 + " " + chatMessages[k], yPos + paddingY, 11);
							textDrawingArea.drawBasicString(s1 + " " + chatMessages[k], 11, yPos+paddingY, 0x7e3200, -1);
						}
						totalLines++;
					}
					if(chatType == 12) {
					if(chatTypeView == 11 || chatTypeView == 0) {
						if(yPos > 3 && yPos < 130) {
							String username = (chatRights[k] > 0 ? "<img=" + (chatRights[k] - 1) + ">" : "") + TextClass.fixName(chatNames[k]);
							String message = "<col=800000>" + chatMessages[k] + "</col>";
							}
						totalLines++;
					}
				
				}
				}
				}
				/*	if(chatType == 12) {
						if(yPos > minimumScrollRange && yPos < maximumScrollRange)
							//textDrawingArea.method385(0x7e3200, chatMessages[k] + " @blu@" + s1, yPos + paddingY, 11);
							textDrawingArea.drawBasicString(chatMessages[k] + " @blu@" + s1, 11, yPos+paddingY, 0x7e3200, -1);
						}
						totalLines++;
					}
				}*/
				if(chatType == 9) {
					if(yPos > minimumScrollRange && yPos < maximumScrollRange) {
						//textDrawingArea.method385(toggleFullscreen ? 0x7FA9FE : 0x314952, "[" + chatMessages[k] + "]", yPos + paddingY, 11);
						textDrawingArea.drawBasicString("[" + chatMessages[k] + "]", 11, yPos+paddingY, toggleFullscreen ? 0x7FA9FE : 0x314952, -1);
					}
					totalLines++;
				}
				if(chatType == 16) {
					if(chatTypeView == 11 || chatTypeView == 0) {
						if(yPos > 3 && yPos < 130) {
							String username = (chatRights[k] > 0 ? "<img=" + (chatRights[k] - 1) + ">" : "") + TextClass.fixName(chatNames[k]);
							String message = "<col=800000>" + chatMessages[k] + "</col>";
							}
						totalLines++;
					}
				}
			}
			DrawingArea.defaultDrawingAreaSize();
			chatScrollTotalLength = totalLines * 14 + 7 + 5;

			if(chatScrollTotalLength < 111)
				chatScrollTotalLength = 111;
		
			drawScrollbar(113, chatScrollTotalLength - anInt1089 - 113, 7 + paddingY, 497, chatScrollTotalLength,true);
			String s;
			if(myPlayer != null && myPlayer.name != null)
				s = myPlayer.name;
			else
				s = myUsername;
			textDrawingArea.drawBasicString(s + ":", 11, 133 + paddingY, toggleFullscreen ? 0xffffff : 0, -1);
			//textDrawingArea.method385(toggleFullscreen ? 0xffffff : 0, s + ":", 133 + paddingY, 11);
			textDrawingArea.drawBasicString(inputString + "*", 12 + textDrawingArea.getTextWidth(s + ": "), 133 + paddingY, toggleFullscreen ? 0x7FA9FF : 255, -1);
			//textDrawingArea.drawChatInput(toggleFullscreen ? 0x7FA9FF : 255, 12 + textDrawingArea.getTextWidth(s + ": "), inputString + "*", 133 + paddingY, false);
			DrawingArea.method339(121 + paddingY, toggleFullscreen ? 0xffffff : 0x807660, 506, 7);
		}
		if(contextMenuOpen && !toggleFullscreen) {
			drawMenu(0, 338);
		}
		main3DArea.initDrawingArea();
		Texture.anIntArray1472 = anIntArray1182;
	}

	
	public static void main(String args[]) {
		try {
			nodeID = 10;
			portOff = 0;
			setHighMem();
			new Jframe(args);
			isMembers = true;
			signlink.storeid = 32;
			signlink.startpriv(InetAddress.getByName(serverip));
			//client client1 = new client();
		} catch (Exception exception) {
		}
	}
	
	/*public void init() { 
		try {
			nodeID = 10;//friends list order
			portOff = 0;
			setHighMem();//sets high or low detail
			isMembers = true;
			signlink.storeid = 32;
			signlink.startpriv(InetAddress.getLocalHost());
			instance = this;
			initClientFrame(503, 765);//this it? 510 770
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public void init() {
        try {
            nodeID = 10;
            portOff = 0;
			//setHighMem();
            setHighMem();
            isMembers = true;
            signlink.storeid = 32;
            signlink.startpriv(InetAddress.getLocalHost());
			instance = this;
           initClientFrame(503, 765);
        } 
        catch (Exception exception) {
            return;
        }
    }
	
	public void startRunnable(Runnable runnable, int i) {
		if(i > 10)
			i = 10;
		if(signlink.mainapp != null) {
			signlink.startthread(runnable, i);
		} else {
			super.startRunnable(runnable, i);
		}
	}

	public Socket openSocket(int port) throws IOException {
			return new Socket(InetAddress.getByName(serverip), port);
	}

	private void processMenuClick() {
		if (activeInterfaceType != 0)
			return;
		int j = super.clickMode3;
		if (spellSelected == 1 && super.saveClickX >= 503
				&& super.saveClickY >= 160 && super.saveClickX <= 765
				&& super.saveClickY <= 205)
			j = 0;
		if (contextMenuOpen) {
			if (j != 1) {
				int k = super.mouseX;
				int j1 = super.mouseY;
				if (menuScreenArea == 0) {
					k -= 4;
					j1 -= 4;
				}
				if (menuScreenArea == 1) {
					k -= 519;// 519
					j1 -= 168;
				}
				if (menuScreenArea == 2) {
					k -= 5;
					j1 -= 338;
				}
				if (menuScreenArea == 3) {
					k -= 519;// 519
					j1 -= 0;
				}
				if (k < menuOffsetX - 10 || k > menuOffsetX + menuWidth + 10
						|| j1 < menuOffsetY - 10
						|| j1 > menuOffsetY + menuHeight + 10) {
					contextMenuOpen = false;
					if (menuScreenArea == 1) {
					}
					if (menuScreenArea == 2)
						inputTaken = true;
				}
			}
			if (j == 1) {
				int l = menuOffsetX;
				int k1 = menuOffsetY;
				int i2 = menuWidth;
				int k2 = super.saveClickX;
				int l2 = super.saveClickY;
				if (menuScreenArea == 0) {
					k2 -= 4;
					l2 -= 4;
				}
				if (menuScreenArea == 1) {
					k2 -= 519;// 519
					l2 -= 168;
				}
				if (menuScreenArea == 2) {
					k2 -= 5;// 17
					l2 -= 338;
				}
				if (menuScreenArea == 3) {
					k2 -= 519;// 519
					l2 -= 0;
				}
				int i3 = -1;
				for (int j3 = 0; j3 < menuActionRow; j3++) {
					int k3 = k1 + 31 + (menuActionRow - 1 - j3) * 15;
					if (k2 > l && k2 < l + i2 && l2 > k3 - 13 && l2 < k3 + 3)
						i3 = j3;
				}
				System.out.println(i3);
				if (i3 != -1)
					doAction(i3);
				contextMenuOpen = false;
				if (menuScreenArea == 1) {
				}
				if (menuScreenArea == 2) {
					inputTaken = true;
				}
			}
		} else {
			if (j == 1 && menuActionRow > 0) {
				int i1 = menuActionID[menuActionRow - 1];
				if (i1 == 632 || i1 == 78 || i1 == 867 || i1 == 431 || i1 == 53
						|| i1 == 74 || i1 == 454 || i1 == 539 || i1 == 493
						|| i1 == 847 || i1 == 447 || i1 == 1125) {
					int l1 = menuActionCmd2[menuActionRow - 1];
					int j2 = menuActionCmd3[menuActionRow - 1];
					RSInterface class9 = RSInterface.interfaceCache[j2];
					if (class9.aBoolean259 || class9.aBoolean235) {
						aBoolean1242 = false;
						anInt989 = 0;
						anInt1084 = j2;
						anInt1085 = l1;
						activeInterfaceType = 2;
						anInt1087 = super.saveClickX;
						anInt1088 = super.saveClickY;
						if (RSInterface.interfaceCache[j2].parentID == openInterfaceID)
							activeInterfaceType = 1;
						if (RSInterface.interfaceCache[j2].parentID == backDialogID)
							activeInterfaceType = 3;
						return;
					}
				}
			}
			if (j == 1
					&& (anInt1253 == 1 || menuHasAddFriend(menuActionRow - 1))
					&& menuActionRow > 2)
				j = 2;
			if (j == 1 && menuActionRow > 0)
				doAction(menuActionRow - 1);
			if (j == 2 && menuActionRow > 0)
				determineMenuSize();

		}
	}


	public static int totalRead = 0;

	

	public void method22() {
		try {
			anInt985 = -1;
			aClass19_1056.removeAll();
			aClass19_1013.removeAll();
			Texture.method366();
			unlinkMRUNodes();
			worldController.initToNull();
			System.gc();
			for (int i = 0; i < 4; i++) {
				aClass11Array1230[i].method210();
			}
			for (int l = 0; l < 4; l++) {
				for (int k1 = 0; k1 < 104; k1++) {
					for (int j2 = 0; j2 < 104; j2++) {
						byteGroundArray[l][k1][j2] = 0;
					}
				}
			}
			ObjectManager objectManager = new ObjectManager(byteGroundArray,intGroundArray);
			int k2 = aByteArrayArray1183.length;
			for (int i1 = 0; i1 < k2; i1++) {
				for (int i2 = 0; i2 < 2000; i2++) {
					if (anIntArray1234[i1] == positions[i2]) {
						anIntArray1235[i1] = landScapes[i2];
						anIntArray1236[i1] = objects[i2];
					}
				}
			}
			stream.createFrame(0);
			if (!aBoolean1159) {
				for (int i3 = 0; i3 < k2; i3++) {
					int i4 = (anIntArray1234[i3] >> 8) * 64 - baseX;
					int k5 = (anIntArray1234[i3] & 0xff) * 64 - baseY;
					byte abyte0[] = aByteArrayArray1183[i3];
					System.out.println("Floormaps: "+anIntArray1235[i3]);
					if (FileOperations.FileExists(signlink.findcachedir() + "Data/Maps/" + anIntArray1235[i3] + ".dat"))
						abyte0 = FileOperations.ReadFile(signlink.findcachedir() + "Data/Maps/" + anIntArray1235[i3] + ".dat");
					if (abyte0 != null)
						objectManager.method180(abyte0, k5, i4, (anInt1069 - 6) * 8, (anInt1070 - 6) * 8, aClass11Array1230);
				}
				for (int j4 = 0; j4 < k2; j4++) {
					int l5 = (anIntArray1234[j4] >> 8) * 62 - baseX;
					int k7 = (anIntArray1234[j4] & 0xff) * 62 - baseY;
					byte abyte2[] = aByteArrayArray1183[j4];
					if (abyte2 == null && anInt1070 < 800)
						objectManager.method174(k7, 64, 64, l5);
				}
				anInt1097++;
				if (anInt1097 > 160) {
					anInt1097 = 0;
					stream.createFrame(238);
					stream.writeWordBigEndian(96);
				}
				stream.createFrame(0);
				for (int i6 = 0; i6 < k2; i6++) {
					byte abyte1[] = aByteArrayArray1247[i6];
					//System.out.println("Object Maps: "+anIntArray1236[i6]);
					if (FileOperations.FileExists(signlink.findcachedir() + "Data/Maps/" + anIntArray1236[i6] + ".dat"))
						abyte1 = FileOperations.ReadFile(signlink.findcachedir() + "Data/Maps/" + anIntArray1236[i6] + ".dat");
					if (abyte1 != null) {
						int l8 = (anIntArray1234[i6] >> 8) * 64 - baseX;
						int k9 = (anIntArray1234[i6] & 0xff) * 64 - baseY;
						objectManager.method190(l8, aClass11Array1230, k9, worldController, abyte1);
					}
				}
			}
			if (aBoolean1159) {
				for (int j3 = 0; j3 < 4; j3++) {
					for (int k4 = 0; k4 < 13; k4++) {
						for (int j6 = 0; j6 < 13; j6++) {
							int l7 = anIntArrayArrayArray1129[j3][k4][j6];
							if (l7 != -1) {
								int i9 = l7 >> 24 & 3;
								int l9 = l7 >> 1 & 3;
								int j10 = l7 >> 14 & 0x3ff;
								int l10 = l7 >> 3 & 0x7ff;
								int j11 = (j10 / 8 << 8) + l10 / 8;
								for (int l11 = 0; l11 < anIntArray1234.length; l11++) {
									if (anIntArray1234[l11] != j11 || aByteArrayArray1183[l11] == null)
									continue;
										objectManager.method179(i9, l9, aClass11Array1230, k4 * 8, (j10 & 7) * 8, aByteArrayArray1183[l11], (l10 & 7) * 8, j3, j6 * 8);
									break;
								}
							}
						}
					}
				}
				for (int l4 = 0; l4 < 13; l4++) {
					for (int k6 = 0; k6 < 13; k6++) {
						int i8 = anIntArrayArrayArray1129[0][l4][k6];
						if (i8 == -1)
							objectManager.method174(k6 * 8, 8, 8, l4 * 8);
					}
				}
				stream.createFrame(0);
				for (int l6 = 0; l6 < 4; l6++) {
					for (int j8 = 0; j8 < 13; j8++) {
						for (int j9 = 0; j9 < 13; j9++) {
							int i10 = anIntArrayArrayArray1129[l6][j8][j9];
							if (i10 != -1) {
								int k10 = i10 >> 24 & 3;
								int i11 = i10 >> 1 & 3;
								int k11 = i10 >> 14 & 0x3ff;
								int i12 = i10 >> 3 & 0x7ff;
								int j12 = (k11 / 8 << 8) + i12 / 8;
								for (int k12 = 0; k12 < anIntArray1234.length; k12++) {
									if (anIntArray1234[k12] != j12 || aByteArrayArray1247[k12] == null)
										continue;
									if (FileOperations.FileExists(signlink.findcachedir() + "Data/Maps/" + anIntArray1235[k12] + ".dat"))
											FileOperations.ReadFile(signlink.findcachedir() + "Data/Maps/" + anIntArray1235[k12] + ".dat");
									objectManager.method183(aClass11Array1230, worldController, k10, j8 * 8, (i12 & 7) * 8, l6, aByteArrayArray1247[k12], (k11 & 7) * 8, i11, j9 * 8);
									break;
								}
							}
						}
					}
				}
			}
			stream.createFrame(0);
			objectManager.method171(aClass11Array1230, worldController);
			main3DArea.initDrawingArea();
			stream.createFrame(0);
			int k3 = ObjectManager.anInt145;
			if (k3 > plane)
				k3 = plane;
			if (k3 < plane - 1)
				k3 = plane - 1;
			if (lowMem)
				worldController.method275(ObjectManager.anInt145);
			else
				worldController.method275(0);
			for (int i5 = 0; i5 < 104; i5++) {
				for (int i7 = 0; i7 < 104; i7++)
					spawnGroundItem(i5, i7);
			}
			anInt1051++;
			if (anInt1051 > 98) {
				anInt1051 = 0;
				stream.createFrame(150);
			}
			method63();
		} catch (Exception exception) {
		}
		ObjectDef.mruNodes1.unlinkAll();
		if (super.gameFrame != null) {
			stream.createFrame(210);
			stream.writeDWord(0x3f008edd);
		}
	
		System.gc();
		Texture.method367();
		onDemandFetcher.method566();
		int k = (anInt1069 - 6) / 8 - 1;
		int j1 = (anInt1069 + 6) / 8 + 1;
		int i2 = (anInt1070 - 6) / 8 - 1;
		int l2 = (anInt1070 + 6) / 8 + 1;
		if (aBoolean1141) {
			k = 49;
			j1 = 50;
			i2 = 49;
			l2 = 50;
		}
		for (int l3 = k; l3 <= j1; l3++) {
			for (int j5 = i2; j5 <= l2; j5++) {
				if (l3 == k || l3 == j1 || j5 == i2 || j5 == l2) {
					int j7 = onDemandFetcher.method562(0, j5, l3);
					if (j7 != -1)
						onDemandFetcher.method560(j7, 3);
					int k8 = onDemandFetcher.method562(1, j5, l3);
					if (k8 != -1)
						onDemandFetcher.method560(k8, 3);
				}
			}
		}
	}


	private void unlinkMRUNodes()
	{
		ObjectDef.mruNodes1.unlinkAll();
		ObjectDef.mruNodes2.unlinkAll();
		EntityDef.mruNodes.unlinkAll();
		ItemDef.mruNodes2.unlinkAll();
		ItemDef.mruNodes1.unlinkAll();
		Player.mruNodes.unlinkAll();
		SpotAnim.aMRUNodes_415.unlinkAll();
	}

	private void method24(int i)
	{
		int ai[] = miniMapImage.myPixels;
		int j = ai.length;
		for(int k = 0; k < j; k++)
			ai[k] = 0;

		for(int l = 1; l < 103; l++)
		{
			int i1 = 24628 + (103 - l) * 512 * 4;
			for(int k1 = 1; k1 < 103; k1++)
			{
				if((byteGroundArray[i][k1][l] & 0x18) == 0)
					worldController.method309(ai, i1, i, k1, l);
				if(i < 3 && (byteGroundArray[i + 1][k1][l] & 8) != 0)
					worldController.method309(ai, i1, i + 1, k1, l);
				i1 += 4;
			}

		}

		int j1 = ((238 + (int)(Math.random() * 20D)) - 10 << 16) + ((238 + (int)(Math.random() * 20D)) - 10 << 8) + ((238 + (int)(Math.random() * 20D)) - 10);
		int l1 = (238 + (int)(Math.random() * 20D)) - 10 << 16;
		miniMapImage.method343();
		for(int i2 = 1; i2 < 103; i2++)
		{
			for(int j2 = 1; j2 < 103; j2++)
			{
				if((byteGroundArray[i][j2][i2] & 0x18) == 0)
					method50(i2, j1, j2, l1, i);
				if(i < 3 && (byteGroundArray[i + 1][j2][i2] & 8) != 0)
					method50(i2, j1, j2, l1, i + 1);
			}

		}
		try {
			main3DArea.initDrawingArea();
			totalMapFunctions = 0;
			for(int k2 = 0; k2 < 104; k2++)
			{
				for(int l2 = 0; l2 < 104; l2++)
				{
					int i3 = worldController.method303(plane, k2, l2);
					if(i3 != 0)
					{
						i3 = i3 >> 14 & 0x7fff;
						int j3 = ObjectDef.forID(i3).anInt746;
						if(j3 >= 0)
						{
							int k3 = k2;
							int l3 = l2;
							if(j3 != 22 && j3 != 29 && j3 != 34 && j3 != 36 && j3 != 46 && j3 != 47 && j3 != 48)
							{
								byte byte0 = 104;
								byte byte1 = 104;
								int ai1[][] = aClass11Array1230[plane].anIntArrayArray294;
								for(int i4 = 0; i4 < 10; i4++)
								{
									int j4 = (int)(Math.random() * 4D);
									if(j4 == 0 && k3 > 0 && k3 > k2 - 3 && (ai1[k3 - 1][l3] & 0x1280108) == 0)
										k3--;
									if(j4 == 1 && k3 < byte0 - 1 && k3 < k2 + 3 && (ai1[k3 + 1][l3] & 0x1280180) == 0)
										k3++;
									if(j4 == 2 && l3 > 0 && l3 > l2 - 3 && (ai1[k3][l3 - 1] & 0x1280102) == 0)
										l3--;
									if(j4 == 3 && l3 < byte1 - 1 && l3 < l2 + 3 && (ai1[k3][l3 + 1] & 0x1280120) == 0)
										l3++;
								}

							}
							currentMapFunctions[totalMapFunctions] = mapFunctions[j3];
							mapFunctionX[totalMapFunctions] = k3;
							mapFunctionY[totalMapFunctions] = l3;
							totalMapFunctions++;
						}
					}
				}

			}
		} catch (NullPointerException ex) {
			
		}
	}

	private void spawnGroundItem(int i, int j)
	{
		NodeList class19 = groundArray[plane][i][j];
		if(class19 == null)
		{
			worldController.method295(plane, i, j);
			return;
		}
		int k = 0xfa0a1f01;
		Object obj = null;
		for(Item item = (Item)class19.reverseGetFirst(); item != null; item = (Item)class19.reverseGetNext())
		{
			ItemDef itemDef = ItemDef.forID(item.ID);
			int l = itemDef.value;
			if(itemDef.stackable)
				l *= item.anInt1559 + 1;
//	notifyItemSpawn(item, i + baseX, j + baseY);
	
			if(l > k)
			{
				k = l;
				obj = item;
			}
		}

		class19.insertTail(((Node) (obj)));
		Object obj1 = null;
		Object obj2 = null;
		for(Item class30_sub2_sub4_sub2_1 = (Item)class19.reverseGetFirst(); class30_sub2_sub4_sub2_1 != null; class30_sub2_sub4_sub2_1 = (Item)class19.reverseGetNext())
		{
			if(class30_sub2_sub4_sub2_1.ID != ((Item) (obj)).ID && obj1 == null)
				obj1 = class30_sub2_sub4_sub2_1;
			if(class30_sub2_sub4_sub2_1.ID != ((Item) (obj)).ID && class30_sub2_sub4_sub2_1.ID != ((Item) (obj1)).ID && obj2 == null)
				obj2 = class30_sub2_sub4_sub2_1;
		}

		int i1 = i + (j << 7) + 0x60000000;
		worldController.method281(i, i1, ((Animable) (obj1)), method42(plane, j * 128 + 64, i * 128 + 64), ((Animable) (obj2)), ((Animable) (obj)), plane, j);
	}

	private void method26(boolean flag)
	{
		for(int j = 0; j < npcCount; j++)
		{
			NPC npc = npcArray[npcIndices[j]];
			int k = 0x20000000 + (npcIndices[j] << 14);
			if(npc == null || !npc.isVisible() || npc.desc.aBoolean93 != flag)
				continue;
			int l = npc.x >> 7;
			int i1 = npc.y >> 7;
			if(l < 0 || l >= 104 || i1 < 0 || i1 >= 104)
				continue;
			if(npc.anInt1540 == 1 && (npc.x & 0x7f) == 64 && (npc.y & 0x7f) == 64)
			{
				if(anIntArrayArray929[l][i1] == anInt1265)
					continue;
				anIntArrayArray929[l][i1] = anInt1265;
			}
			if(!npc.desc.aBoolean84)
				k += 0x80000000;
			worldController.method285(plane, npc.anInt1552, method42(plane, npc.y, npc.x), k, npc.y, (npc.anInt1540 - 1) * 64 + 60, npc.x, npc, npc.aBoolean1541);
		}
	}



	@SuppressWarnings("unused")
	private void loadError()
	{
		String s = "ondemand";//was a constant parameter
		System.out.println(s);
		try
		{
			getAppletContext().showDocument(new URL(getCodeBase(), "loaderror_" + s + ".html"));
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		do
			try
			{
				Thread.sleep(1000L);
			}
			catch(Exception _ex) { }
		while(true);
	}
	
	private int hoverSpriteId = -1;
	private void buildInterfaceMenu(int xPadding, RSInterface rsinterface, int xPos, int yPadding, int yPos, int yScrollPoint) {
		try {
			if(rsinterface.type != 0 || rsinterface.children == null || rsinterface.isMouseoverTriggered)
				return;
			if(xPos < xPadding || yPos < yPadding || xPos > xPadding + rsinterface.width || yPos > yPadding + rsinterface.height)
				return;
			int totalChildren = rsinterface.children.length;
			for(int childIndex = 0; childIndex < totalChildren; childIndex++) {
				int xSpritePos = rsinterface.childX[childIndex] + xPadding;
				int ySpritePos = (rsinterface.childY[childIndex] + yPadding) - yScrollPoint;
				RSInterface rsinterfacechildren = RSInterface.interfaceCache[rsinterface.children[childIndex]];
				xSpritePos += rsinterfacechildren.anInt263;
				ySpritePos += rsinterfacechildren.anInt265;
				if((rsinterfacechildren.mOverInterToTrigger >= 0 || rsinterfacechildren.textHoverColour != 0)
				&& xPos >= xSpritePos 
				&& xPos < xSpritePos + rsinterfacechildren.width 
				&& yPos >= ySpritePos 
				&& yPos < ySpritePos + rsinterfacechildren.height) {
					if(rsinterfacechildren.mOverInterToTrigger >= 0) {
						hoverChildId = rsinterfacechildren.mOverInterToTrigger;
						hoverSpriteId = hoverChildId;
					} else {
						hoverChildId = rsinterfacechildren.id;
						hoverSpriteId = hoverChildId;
					}
				}
				if (rsinterfacechildren.type == 8 &&  xPos >= xSpritePos && xPos < xSpritePos + rsinterfacechildren.width && yPos >= ySpritePos && yPos < ySpritePos + rsinterfacechildren.height) {
					hoverToggleChildId = rsinterfacechildren.id;//
				}
				
				if(rsinterfacechildren.type == 0)
				{
					buildInterfaceMenu(xSpritePos, rsinterfacechildren, xPos, ySpritePos, yPos, rsinterfacechildren.scrollPosition);
					if(rsinterfacechildren.scrollMax > rsinterfacechildren.height)
						method65(xSpritePos + rsinterfacechildren.width, rsinterfacechildren.height, xPos, yPos, rsinterfacechildren, ySpritePos, true, rsinterfacechildren.scrollMax);
				} else
				{
					if(rsinterfacechildren.atActionType == 1 && xPos >= xSpritePos && yPos >= ySpritePos && xPos < xSpritePos + rsinterfacechildren.width && yPos < ySpritePos + rsinterfacechildren.height)
					{
						boolean flag = false;
						if(rsinterfacechildren.contentType != 0)
							flag = buildFriendsListMenu(rsinterfacechildren);
						if(!flag)
						{
							menuActionName[menuActionRow] = rsinterfacechildren.tooltip + ((myPlayer.playerRights != 0) ? ", @gre@(@whi@" + rsinterfacechildren.id + "@gre@)" : "");
							menuActionID[menuActionRow] = 315;
							menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
							menuActionRow++;
						}
					}
					if(rsinterfacechildren.atActionType == 2 && spellSelected == 0 && xPos >= xSpritePos && yPos >= ySpritePos && xPos < xSpritePos + rsinterfacechildren.width && yPos < ySpritePos + rsinterfacechildren.height) {
						String s = rsinterfacechildren.selectedActionName;
						if(s.indexOf(" ") != -1)
							s = s.substring(0, s.indexOf(" "));
if (rsinterfacechildren.spellName.endsWith("Rush") 
						|| rsinterfacechildren.spellName.endsWith("Burst") 
						|| rsinterfacechildren.spellName.endsWith("Blitz") 
						|| rsinterfacechildren.spellName.endsWith("Barrage") && !rsinterfacechildren.spellName.equals("Miasmic Barrage")) {
							menuActionName[menuActionRow] = "Autocast @gre@" + rsinterfacechildren.spellName + ((myPlayer.playerRights != 0) ? ", " + rsinterfacechildren.id : "");
							menuActionID[menuActionRow] = 104;
							menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
							menuActionRow++;
						}
						menuActionName[menuActionRow] = s + " @gre@" + rsinterfacechildren.spellName + ((myPlayer.playerRights != 0) ? ", " + rsinterfacechildren.id : "");
						menuActionID[menuActionRow] = 626;
						menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
						menuActionRow++;					
						if (rsinterfacechildren.spellName.endsWith("strike") 
						|| rsinterfacechildren.spellName.endsWith("bolt") 
						|| rsinterfacechildren.spellName.equals("Crumble undead") 
						|| rsinterfacechildren.spellName.endsWith("blast") 
						|| rsinterfacechildren.spellName.endsWith("wave") 
						|| rsinterfacechildren.spellName.equals("Claws of Guthix") 
						|| rsinterfacechildren.spellName.equals("Flames of Zamorak") 
						|| rsinterfacechildren.spellName.equals("Magic Dart")) {
							menuActionName[menuActionRow] = "Autocast @gre@" + rsinterfacechildren.spellName + ((myPlayer.playerRights != 0) ? ", " + rsinterfacechildren.id : "");
							menuActionID[menuActionRow] = 104;
							menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
							menuActionRow++;
						}
					}
					if(rsinterfacechildren.atActionType == 3 && xPos >= xSpritePos && yPos >= ySpritePos && xPos < xSpritePos + rsinterfacechildren.width && yPos < ySpritePos + rsinterfacechildren.height)
					{
						menuActionName[menuActionRow] = "Close" + ((myPlayer.playerRights != 0) ? ", " + rsinterfacechildren.id : "");
						menuActionID[menuActionRow] = 200;
						menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
						menuActionRow++;
					}
					if(rsinterfacechildren.atActionType == 4 && xPos >= xSpritePos && yPos >= ySpritePos && xPos < xSpritePos + rsinterfacechildren.width && yPos < ySpritePos + rsinterfacechildren.height) {				
						menuActionName[menuActionRow] = rsinterfacechildren.tooltip + ((myPlayer.playerRights != 0) ? ", @gre@(@whi@" + rsinterfacechildren.id + "@gre@)" : "");
						menuActionID[menuActionRow] = 169;
						menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
						menuActionRow++;
						if (rsinterfacechildren.hoverText != null) {
							//drawHoverBox(xPos, yPadding, rsinterfacechildren.hoverText);
							//System.out.println("DRAWING INTERFACE: " + rsinterfacechildren.hoverText);
						}
					}
					if(rsinterfacechildren.atActionType == 5 && xPos >= xSpritePos && yPos >= ySpritePos && xPos < xSpritePos + rsinterfacechildren.width && yPos < ySpritePos + rsinterfacechildren.height) {
						if(rsinterfacechildren.tooltip.toLowerCase().startsWith("transform")) {
						rsinterfacechildren.itemActions = new String[5];
						rsinterfacechildren.itemActions[0] = "Transform";
						rsinterfacechildren.itemActions[1] = "Transform-5";
						rsinterfacechildren.itemActions[2] = "Transform-10";
						rsinterfacechildren.itemActions[3] = "Transform-all";
						rsinterfacechildren.itemActions[4] = "Transform-X";
							if(rsinterfacechildren.itemActions != null) {
								for(int j4 = 4; j4 >= 0; j4--)
									if(rsinterfacechildren.itemActions[j4] != null) {
										menuActionName[menuActionRow] = rsinterfacechildren.tooltip.replaceAll("Transform",rsinterfacechildren.itemActions[j4]);
										if(j4 == 0)
											menuActionID[menuActionRow] = 632;//1
										if(j4 == 1)
											menuActionID[menuActionRow] = 78;//5
										if(j4 == 2)
											menuActionID[menuActionRow] = 867;//10
										if(j4 == 3)
											menuActionID[menuActionRow] = 431;//all
										if(j4 == 4)
											menuActionID[menuActionRow] = 53;//x
										menuActionCmd1[menuActionRow] = rsinterfacechildren.itemSpriteId1;
										menuActionCmd2[menuActionRow] = rsinterfacechildren.itemSpriteIndex;
										menuActionCmd3[menuActionRow] = rsinterfacechildren.parentID;
										menuActionRow++;
									}
							}
						} else {
							menuActionName[menuActionRow] = rsinterfacechildren.tooltip + ((myPlayer.playerRights != 0) ? ", @gre@(@whi@" + rsinterfacechildren.id + "@gre@)" : "");
							menuActionID[menuActionRow] = 646;
							menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
							menuActionRow++;
						}
					}
					if(rsinterfacechildren.atActionType == 6 && !aBoolean1149 && xPos >= xSpritePos && yPos >= ySpritePos && xPos < xSpritePos + rsinterfacechildren.width && yPos < ySpritePos + rsinterfacechildren.height)
					{
						menuActionName[menuActionRow] = rsinterfacechildren.tooltip + ((myPlayer.playerRights != 0) ? ", @gre@(@whi@" + rsinterfacechildren.id + "@gre@)" : "");
						menuActionID[menuActionRow] = 679;
						menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
						menuActionRow++;
					}
					if(rsinterfacechildren.type == 2)
					{
						int k2 = 0;
						for(int l2 = 0; l2 < rsinterfacechildren.height; l2++)
						{
							for(int i3 = 0; i3 < rsinterfacechildren.width; i3++)
							{
								int j3 = xSpritePos + i3 * (32 + rsinterfacechildren.invSpritePadX);
								int k3 = ySpritePos + l2 * (32 + rsinterfacechildren.invSpritePadY);
								if(k2 < 20)
								{
									j3 += rsinterfacechildren.spritesX[k2];
									k3 += rsinterfacechildren.spritesY[k2];
								}
								if(xPos >= j3 && yPos >= k3 && xPos < j3 + 32 && yPos < k3 + 32)
								{
									mouseInvInterfaceIndex = k2;
									lastActiveInvInterface = rsinterfacechildren.id;
									if(rsinterfacechildren.inv[k2] > 0)
									{
										ItemDef itemDef = ItemDef.forID(rsinterfacechildren.inv[k2] - 1);
										if(itemSelected == 1 && rsinterfacechildren.isInventoryInterface)
										{
											if(rsinterfacechildren.id != anInt1284 || k2 != itemSlotUsedOn)
											{
												menuActionName[menuActionRow] = "Use " + selectedItemName + " with @lre@" + itemDef.name;
												menuActionID[menuActionRow] = 870;
												menuActionCmd1[menuActionRow] = itemDef.id;
												menuActionCmd2[menuActionRow] = k2;
												menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
												menuActionRow++;
											}
										} else
										if(spellSelected == 1 && rsinterfacechildren.isInventoryInterface)
										{
											if((spellUsableOn & 0x10) == 16)
											{
												menuActionName[menuActionRow] = spellTooltip + " @lre@" + itemDef.name;
												menuActionID[menuActionRow] = 543;
												menuActionCmd1[menuActionRow] = itemDef.id;
												menuActionCmd2[menuActionRow] = k2;
												menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
												menuActionRow++;
											}
										} else
										{
											if(rsinterfacechildren.isInventoryInterface)
											{
												for(int l3 = 4; l3 >= 3; l3--)
													if(itemDef.itemActions != null && itemDef.itemActions[l3] != null)
													{
														menuActionName[menuActionRow] = itemDef.itemActions[l3] + " @lre@" + itemDef.name;
														if(l3 == 3)
															menuActionID[menuActionRow] = 493;
														if(l3 == 4)
															menuActionID[menuActionRow] = 847;
														menuActionCmd1[menuActionRow] = itemDef.id;
														menuActionCmd2[menuActionRow] = k2;
														menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
														menuActionRow++;
													} else
													if(l3 == 4)
													{
														menuActionName[menuActionRow] = "Drop @lre@" + itemDef.name;
														menuActionID[menuActionRow] = 847;
														menuActionCmd1[menuActionRow] = itemDef.id;
														menuActionCmd2[menuActionRow] = k2;
														menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
														menuActionRow++;
													}

											}
											if(rsinterfacechildren.usableItemInterface)
											{
												if(openInterfaceID == 24700) {
													menuActionName[menuActionRow] = "Offer @lre@" + itemDef.name;
													menuActionID[menuActionRow] = 454;
													menuActionCmd1[menuActionRow] = itemDef.id;
												} else {
													menuActionName[menuActionRow] = "Use @lre@" + itemDef.name;
													menuActionID[menuActionRow] = 447;
													menuActionCmd1[menuActionRow] = itemDef.id;
												}
												//k2 = inventory spot
												//System.out.println(k2);
												menuActionCmd2[menuActionRow] = k2;
												menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
												menuActionRow++;
											}
											if(rsinterfacechildren.isInventoryInterface && itemDef.itemActions != null)
											{
												for(int i4 = 2; i4 >= 0; i4--)
													if(itemDef.itemActions[i4] != null)
													{
														menuActionName[menuActionRow] = itemDef.itemActions[i4] + " @lre@" + itemDef.name;
														if(i4 == 0)
															menuActionID[menuActionRow] = 74;
														if(i4 == 1)
															menuActionID[menuActionRow] = 454;
														if(i4 == 2)
															menuActionID[menuActionRow] = 539;
														menuActionCmd1[menuActionRow] = itemDef.id;
														menuActionCmd2[menuActionRow] = k2;
														menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
														menuActionRow++;
													}

											}
											if(rsinterfacechildren.itemActions != null)
											{
												for(int j4 = 4; j4 >= 0; j4--)
													if(rsinterfacechildren.itemActions[j4] != null)
													{
														menuActionName[menuActionRow] = rsinterfacechildren.itemActions[j4] + " @lre@" + itemDef.name;
														if(j4 == 0)
															menuActionID[menuActionRow] = 632;//1
														if(j4 == 1)
															menuActionID[menuActionRow] = 78;//5
														if(j4 == 2)
															menuActionID[menuActionRow] = 867;//10
														if(j4 == 3)
															menuActionID[menuActionRow] = 431;//all
														if(j4 == 4)
															menuActionID[menuActionRow] = 53;//x
														menuActionCmd1[menuActionRow] = itemDef.id;
														menuActionCmd2[menuActionRow] = k2;
														menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
														menuActionRow++;
													}

											}
											if (openInterfaceID == 5292) {
											}
											else {
											if (openInterfaceID != 16600) {
											menuActionName[menuActionRow] = "Examine @lre@" + itemDef.name + ((myPlayer.playerRights != 0) ? ", @gre@(@whi@" + (rsinterfacechildren.inv[k2] - 1) + "@gre@)" : "");
											menuActionID[menuActionRow] = 1125;
											menuActionCmd1[menuActionRow] = itemDef.id;
											menuActionCmd2[menuActionRow] = k2;
											menuActionCmd3[menuActionRow] = rsinterfacechildren.id;
											menuActionRow++;
										}
										}
									}
									}
								}
								k2++;
							}

						}

					}
				}
			}
		} catch(NullPointerException ex) {
			
		}
	}	
		private Sprite scrollBar1;
	private Sprite scrollBar2;
		public void drawScrollbar(int j, int k, int l, int i1, int j1) {
		scrollBar1.drawSprite(i1, l);
		scrollBar2.drawSprite(i1, (l + j) - 16);
		DrawingArea.drawPixels(j - 32, l + 16, i1, 0x000001, 16);
		DrawingArea.drawPixels(j - 32, l + 16, i1, 0x3d3426, 15);
		DrawingArea.drawPixels(j - 32, l + 16, i1, 0x342d21, 13);
		DrawingArea.drawPixels(j - 32, l + 16, i1, 0x2e281d, 11);
		DrawingArea.drawPixels(j - 32, l + 16, i1, 0x29241b, 10);
		DrawingArea.drawPixels(j - 32, l + 16, i1, 0x252019, 9);
		DrawingArea.drawPixels(j - 32, l + 16, i1, 0x000001, 1);
		int k1 = ((j - 32) * j) / j1;
		if(k1 < 8)
			k1 = 8;
		int l1 = ((j - 32 - k1) * k) / (j1 - j);
		DrawingArea.drawPixels(k1, l + 16 + l1, i1, barFillColor, 16);
		DrawingArea.method341(l + 16 + l1, 0x000001, k1, i1);
		DrawingArea.method341(l + 16 + l1, 0x817051, k1, i1 + 1);
		DrawingArea.method341(l + 16 + l1, 0x73654a, k1, i1 + 2);
		DrawingArea.method341(l + 16 + l1, 0x6a5c43, k1, i1 + 3);
		DrawingArea.method341(l + 16 + l1, 0x6a5c43, k1, i1 + 4);
		DrawingArea.method341(l + 16 + l1, 0x655841, k1, i1 + 5);
		DrawingArea.method341(l + 16 + l1, 0x655841, k1, i1 + 6);
		DrawingArea.method341(l + 16 + l1, 0x61553e, k1, i1 + 7);
		DrawingArea.method341(l + 16 + l1, 0x61553e, k1, i1 + 8);
		DrawingArea.method341(l + 16 + l1, 0x5d513c, k1, i1 + 9);
		DrawingArea.method341(l + 16 + l1, 0x5d513c, k1, i1 + 10);
		DrawingArea.method341(l + 16 + l1, 0x594e3a, k1, i1 + 11);
		DrawingArea.method341(l + 16 + l1, 0x594e3a, k1, i1 + 12);
		DrawingArea.method341(l + 16 + l1, 0x514635, k1, i1 + 13);
		DrawingArea.method341(l + 16 + l1, 0x4b4131, k1, i1 + 14);
		DrawingArea.method339(l + 16 + l1, 0x000001, 15, i1);
		DrawingArea.method339(l + 17 + l1, 0x000001, 15, i1);
		DrawingArea.method339(l + 17 + l1, 0x655841, 14, i1);
		DrawingArea.method339(l + 17 + l1, 0x6a5c43, 13, i1);
		DrawingArea.method339(l + 17 + l1, 0x6d5f48, 11, i1);
		DrawingArea.method339(l + 17 + l1, 0x73654a, 10, i1);
		DrawingArea.method339(l + 17 + l1, 0x76684b, 7, i1);
		DrawingArea.method339(l + 17 + l1, 0x7b6a4d, 5, i1);
		DrawingArea.method339(l + 17 + l1, 0x7e6e50, 4, i1);
		DrawingArea.method339(l + 17 + l1, 0x817051, 3, i1);
		DrawingArea.method339(l + 17 + l1, 0x000001, 2, i1);
		DrawingArea.method339(l + 18 + l1, 0x000001, 16, i1);
		DrawingArea.method339(l + 18 + l1, 0x564b38, 15, i1);
		DrawingArea.method339(l + 18 + l1, 0x5d513c, 14, i1);
		DrawingArea.method339(l + 18 + l1, 0x625640, 11, i1);
		DrawingArea.method339(l + 18 + l1, 0x655841, 10, i1);
		DrawingArea.method339(l + 18 + l1, 0x6a5c43, 7, i1);
		DrawingArea.method339(l + 18 + l1, 0x6e6046, 5, i1);
		DrawingArea.method339(l + 18 + l1, 0x716247, 4, i1);
		DrawingArea.method339(l + 18 + l1, 0x7b6a4d, 3, i1);
		DrawingArea.method339(l + 18 + l1, 0x817051, 2, i1);
		DrawingArea.method339(l + 18 + l1, 0x000001, 1, i1);
		DrawingArea.method339(l + 19 + l1, 0x000001, 16, i1);
		DrawingArea.method339(l + 19 + l1, 0x514635, 15, i1);
		DrawingArea.method339(l + 19 + l1, 0x564b38, 14, i1);
		DrawingArea.method339(l + 19 + l1, 0x5d513c, 11, i1);
		DrawingArea.method339(l + 19 + l1, 0x61553e, 9, i1);
		DrawingArea.method339(l + 19 + l1, 0x655841, 7, i1);
		DrawingArea.method339(l + 19 + l1, 0x6a5c43, 5, i1);
		DrawingArea.method339(l + 19 + l1, 0x6e6046, 4, i1);
		DrawingArea.method339(l + 19 + l1, 0x73654a, 3, i1);
		DrawingArea.method339(l + 19 + l1, 0x817051, 2, i1);
		DrawingArea.method339(l + 19 + l1, 0x000001, 1, i1);
		DrawingArea.method339(l + 20 + l1, 0x000001, 16, i1);
		DrawingArea.method339(l + 20 + l1, 0x4b4131, 15, i1);
		DrawingArea.method339(l + 20 + l1, 0x544936, 14, i1);
		DrawingArea.method339(l + 20 + l1, 0x594e3a, 13, i1);
		DrawingArea.method339(l + 20 + l1, 0x5d513c, 10, i1);
		DrawingArea.method339(l + 20 + l1, 0x61553e, 8, i1);
		DrawingArea.method339(l + 20 + l1, 0x655841, 6, i1);
		DrawingArea.method339(l + 20 + l1, 0x6a5c43, 4, i1);
		DrawingArea.method339(l + 20 + l1, 0x73654a, 3, i1);
		DrawingArea.method339(l + 20 + l1, 0x817051, 2, i1);
		DrawingArea.method339(l + 20 + l1, 0x000001, 1, i1);
		DrawingArea.method341(l + 16 + l1, 0x000001, k1, i1 + 15);
		DrawingArea.method339(l + 15 + l1 + k1, 0x000001, 16, i1);
		DrawingArea.method339(l + 14 + l1 + k1, 0x000001, 15, i1);
		DrawingArea.method339(l + 14 + l1 + k1, 0x3f372a, 14, i1);
		DrawingArea.method339(l + 14 + l1 + k1, 0x443c2d, 10, i1);
		DrawingArea.method339(l + 14 + l1 + k1, 0x483e2f, 9, i1);
		DrawingArea.method339(l + 14 + l1 + k1, 0x4a402f, 7, i1);
		DrawingArea.method339(l + 14 + l1 + k1, 0x4b4131, 4, i1);
		DrawingArea.method339(l + 14 + l1 + k1, 0x564b38, 3, i1);
		DrawingArea.method339(l + 14 + l1 + k1, 0x000001, 2, i1);
		DrawingArea.method339(l + 13 + l1 + k1, 0x000001, 16, i1);
		DrawingArea.method339(l + 13 + l1 + k1, 0x443c2d, 15, i1);
		DrawingArea.method339(l + 13 + l1 + k1, 0x4b4131, 11, i1);
		DrawingArea.method339(l + 13 + l1 + k1, 0x514635, 9, i1);
		DrawingArea.method339(l + 13 + l1 + k1, 0x544936, 7, i1);
		DrawingArea.method339(l + 13 + l1 + k1, 0x564b38, 6, i1);
		DrawingArea.method339(l + 13 + l1 + k1, 0x594e3a, 4, i1);
		DrawingArea.method339(l + 13 + l1 + k1, 0x625640, 3, i1);
		DrawingArea.method339(l + 13 + l1 + k1, 0x6a5c43, 2, i1);
		DrawingArea.method339(l + 13 + l1 + k1, 0x000001, 1, i1);
		DrawingArea.method339(l + 12 + l1 + k1, 0x000001, 16, i1);
		DrawingArea.method339(l + 12 + l1 + k1, 0x443c2d, 15, i1);
		DrawingArea.method339(l + 12 + l1 + k1, 0x4b4131, 14, i1);
		DrawingArea.method339(l + 12 + l1 + k1, 0x544936, 12, i1);
		DrawingArea.method339(l + 12 + l1 + k1, 0x564b38, 11, i1);
		DrawingArea.method339(l + 12 + l1 + k1, 0x594e3a, 10, i1);
		DrawingArea.method339(l + 12 + l1 + k1, 0x5d513c, 7, i1);
		DrawingArea.method339(l + 12 + l1 + k1, 0x61553e, 4, i1);
		DrawingArea.method339(l + 12 + l1 + k1, 0x6e6046, 3, i1);
		DrawingArea.method339(l + 12 + l1 + k1, 0x7b6a4d, 2, i1);
		DrawingArea.method339(l + 12 + l1 + k1, 0x000001, 1, i1);
		DrawingArea.method339(l + 11 + l1 + k1, 0x000001, 16, i1);
		DrawingArea.method339(l + 11 + l1 + k1, 0x4b4131, 15, i1);
		DrawingArea.method339(l + 11 + l1 + k1, 0x514635, 14, i1);
		DrawingArea.method339(l + 11 + l1 + k1, 0x564b38, 13, i1);
		DrawingArea.method339(l + 11 + l1 + k1, 0x594e3a, 11, i1);
		DrawingArea.method339(l + 11 + l1 + k1, 0x5d513c, 9, i1);
		DrawingArea.method339(l + 11 + l1 + k1, 0x61553e, 7, i1);
		DrawingArea.method339(l + 11 + l1 + k1, 0x655841, 5, i1);
		DrawingArea.method339(l + 11 + l1 + k1, 0x6a5c43, 4, i1);
		DrawingArea.method339(l + 11 + l1 + k1, 0x73654a, 3, i1);
		DrawingArea.method339(l + 11 + l1 + k1, 0x7b6a4d, 2, i1);
		DrawingArea.method339(l + 11 + l1 + k1, 0x000001, 1, i1);
	}

	public void drawScrollbar(int totalHeight, int currentPos, int yPos, int xPos, int ScrollsTotalLength,boolean hdStyle) {
		double repeats = (totalHeight - 32) / 5;
		for(int i = 0; i <= repeats; i++)
			scrollBar[0 + (hdStyle && toggleFullscreen ? 4 : 0)].drawSprite(xPos, (yPos + 16) + (i * 5));
			
		int scrollLength = ((totalHeight - 32) * totalHeight) / ScrollsTotalLength;
		if(scrollLength < 8)
			scrollLength = 8;
		int calcScrollPos = ((totalHeight - 32 - scrollLength) * currentPos) / (ScrollsTotalLength - totalHeight);
		
		int frontStartY = (yPos + 16 + calcScrollPos);
		int frontFinishY = (yPos + 16 + calcScrollPos + scrollLength - 5);
		for(int frontY = frontStartY; frontY < frontFinishY; frontY += 5)
			scrollBar[2 + (hdStyle && toggleFullscreen ? 4 : 0)].drawSprite(xPos, frontY);
		
		scrollBar[1 + (hdStyle && toggleFullscreen ? 4 : 0)].drawSprite(xPos,frontStartY);
		scrollBar[3 + (hdStyle && toggleFullscreen ? 4 : 0)].drawSprite(xPos,frontFinishY);
		scrollArrow[0 + (hdStyle && toggleFullscreen ? 2 : 0)].drawSprite(xPos, yPos);
		scrollArrow[1 + (hdStyle && toggleFullscreen ? 2 : 0)].drawSprite(xPos, (yPos + totalHeight) - 16);
	}

	private void updateNPCs(Stream stream, int i)
	{
		anInt839 = 0;
		anInt893 = 0;
		method139(stream);
		method46(i, stream);
		method86(stream);
		for(int k = 0; k < anInt839; k++)
		{
			int l = anIntArray840[k];
			if(npcArray[l].anInt1537 != loopCycle)
			{
				npcArray[l].desc = null;
				npcArray[l] = null;
			}
		}

		if(stream.currentOffset != i)
		{
			signlink.reporterror(myUsername + " size mismatch in getnpcpos - pos:" + stream.currentOffset + " psize:" + i);
			throw new RuntimeException("eek");
		}
		for(int i1 = 0; i1 < npcCount; i1++)
			if(npcArray[npcIndices[i1]] == null)
			{
				signlink.reporterror(myUsername + " null entry in npc list - pos:" + i1 + " size:" + npcCount);
				throw new RuntimeException("eek");
			}

	}

	private int cButtonHPos;
	private int cButtonCPos;

	private void processChatModeClick(int paddingX,int paddingY) {
		int clickX = super.saveClickX - paddingX;
		int clickY = super.saveClickY - paddingY;
		int mouseX = super.mouseX - paddingX;
		int mouseY = super.mouseY - paddingY;
		if(mouseX >= 5 && mouseX <= 61 && mouseY >= 482 && mouseY <= 503) {
			cButtonHPos = 0;
			inputTaken = true;
		} else if(mouseX >= 71 && mouseX <= 127 && mouseY >= 482 && mouseY <= 503) {
			cButtonHPos = 1;
			inputTaken = true;
		} else if(mouseX >= 137 && mouseX <= 193 && mouseY >= 482 && mouseY <= 503) {
			cButtonHPos = 2;
			inputTaken = true;
		} else if(mouseX >= 203 && mouseX <= 259 && mouseY >= 482 && mouseY <= 503) {
			cButtonHPos = 3;
			inputTaken = true;
		} else if(mouseX >= 269 && mouseX <= 325 && mouseY >= 482 && mouseY <= 503) {
			cButtonHPos = 4;
			inputTaken = true;
		} else if(mouseX >= 335 && mouseX <= 391 && mouseY >= 482 && mouseY <= 503) {
			cButtonHPos = 5;
			inputTaken = true;
		} else if(mouseX >= 404 && mouseX <= 515 && mouseY >= 482 && mouseY <= 503) {
			cButtonHPos = 6;
			inputTaken = true;
		} else {
			cButtonHPos = -1;
			inputTaken = true;
		}
		if(super.clickMode3 == 1) {
			if(clickX >= 5 && clickX <= 61 && clickY >= 482 && clickY <= 505) {
				cButtonCPos = 0;
				chatTypeView = 0;
				inputTaken = true;
			} else if(clickX >= 71 && clickX <= 127 && clickY >= 482 && clickY <= 505) {
				cButtonCPos = 1;
				chatTypeView = 5;
				inputTaken = true;
			} else if(clickX >= 137 && clickX <= 193 && clickY >= 482 && clickY <= 505) {
				cButtonCPos = 2;
				chatTypeView = 1;
				inputTaken = true;
			} else if(clickX >= 203 && clickX <= 259 && clickY >= 482 && clickY <= 505) {
				cButtonCPos = 3;
				chatTypeView = 2;
				inputTaken = true;
			} else if(clickX >= 269 && clickX <= 325 && clickY >= 482 && clickY <= 505) {
				cButtonCPos = 4;
				chatTypeView = 11;
				inputTaken = true;
			} else if(clickX >= 335 && clickX <= 391 && clickY >= 482 && clickY <= 505) {
				cButtonCPos = 5;
				chatTypeView = 3;
				inputTaken = true;
			} else if(clickX >= 404 && clickX <= 515 && clickY >= 482 && clickY <= 505) {
				if(openInterfaceID == -1 && myPlayer.playerRights >= 1) {
					clearTopInterfaces();
					reportAbuseInput = "";
					canMute = false;
					for(int i = 0; i < RSInterface.interfaceCache.length; i++) {
						if(RSInterface.interfaceCache[i] == null || RSInterface.interfaceCache[i].contentType != 600)
							continue;
						reportAbuseInterfaceID = openInterfaceID = RSInterface.interfaceCache[i].parentID;
						break;
					}
				} else {
					pushMessage("Please close the interface you have open before using 'report abuse'", 0, "");
				}
			}
		}
	}

	private void method33(int i)
	{
		int j = Varp.cache[i].anInt709;
		if(j == 0)
			return;
		int k = variousSettings[i];
		if(j == 1)
		{
			if(k == 1)
				Texture.method372(0.90000000000000002D);
			if(k == 2)
				Texture.method372(0.80000000000000004D);
			if(k == 3)
				Texture.method372(0.69999999999999996D);
			if(k == 4)
				Texture.method372(0.59999999999999998D);
			ItemDef.mruNodes1.unlinkAll();
			welcomeScreenRaised = true;
		}
		if(j == 3)
		{
			boolean flag1 = musicEnabled;
			if(k == 0)
			{
				adjustVolume(musicEnabled, 0);
				musicEnabled = true;
			}
			if(k == 1)
			{
				adjustVolume(musicEnabled, -400);
				musicEnabled = true;
			}
			if(k == 2)
			{
				adjustVolume(musicEnabled, -800);
				musicEnabled = true;
			}
			if(k == 3)
			{
				adjustVolume(musicEnabled, -1200);
				musicEnabled = true;
			}
			if(k == 4)
				musicEnabled = false;
			if(musicEnabled != flag1 && !lowMem)
			{
				if(musicEnabled)
				{
					nextSong = currentSong;
					onDemandFetcher.method558(2, nextSong);
				} else
				{
					stopMidi();
				}
				prevSong = 0;
			}
		}
		if(j == 4)
		{
			if(k == 0)
			{
				aBoolean848 = true;
				setWaveVolume(0);
			}
			if(k == 1)
			{
				aBoolean848 = true;
				setWaveVolume(-400);
			}
			if(k == 2)
			{
				aBoolean848 = true;
				setWaveVolume(-800);
			}
			if(k == 3)
			{
				aBoolean848 = true;
				setWaveVolume(-1200);
			}
			if(k == 4)
				aBoolean848 = false;
		}
		if(j == 5)
			anInt1253 = k;
		if(j == 6)
			anInt1249 = k;
		if(j == 8)
		{
			splitPrivateChat = k;
			inputTaken = true;
		}
		if(j == 9)
			anInt913 = k;
	}

	private void updateEntities() {
		try{
			int anInt974 = 0;
			for(int j = -1; j < playerCount + npcCount; j++) {
			Object obj;
			if(j == -1)
				obj = myPlayer;
			else
			if(j < playerCount)
				obj = playerArray[playerIndices[j]];
			else
				obj = npcArray[npcIndices[j - playerCount]];
			if(obj == null || !((Entity) (obj)).isVisible())
				continue;
			if(obj instanceof NPC) {
				EntityDef entityDef = ((NPC)obj).desc;
				if(entityDef.childrenIDs != null)
					entityDef = entityDef.method161();
				if(entityDef == null)
					continue;
			}
			if(j < playerCount) {
				int l = 30;
				Player player = (Player)obj;
				if(player.headIcon >= 0) {
					npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
					if(spriteDrawX > -1) {
						if (player.skullIcon < 2) {
							skullIcons[player.skullIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
							l += 25;
						}
						if (player.headIcon < 18) {
							headIcons[player.headIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
							l += 18;
						}
						if (player.skullIcon > 1 && player.skullIcon < 7) {
							bountyHunter[player.skullIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
							l += 30;
						}
					}
				}
				if(j >= 0 && hintType == 10 && hintArrowPlayerID == playerIndices[j]) {
					npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
					if(spriteDrawX > -1)
						headIconsHint[player.hintIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
				}
				int col = 0x3399ff;
				if (player.playerRights > 0)
						newSmallFont.drawCenteredString("<img="
								+ (player.playerRights - 1) + "> ",
								spriteDrawX - 5, spriteDrawY - 15, col, 100);
			} else {
				EntityDef entityDef_1 = ((NPC)obj).desc;
				if(entityDef_1.anInt75 >= 0 && entityDef_1.anInt75 < headIcons.length) {
					npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
					if(spriteDrawX > -1)
						headIcons[entityDef_1.anInt75].drawSprite(spriteDrawX - 12, spriteDrawY - 30);
				}
				if(hintType == 1 && hintArrowNPCID == npcIndices[j - playerCount] && loopCycle % 20 < 10) {
					npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
					if(spriteDrawX > -1)
						headIconsHint[0].drawSprite(spriteDrawX - 12, spriteDrawY - 28);
				}
			}
			if(((Entity) (obj)).textSpoken != null && (j >= playerCount || publicChatMode == 0 || publicChatMode == 3 || publicChatMode == 1 && isFriendOrSelf(((Player)obj).name)))
			{
				npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height);
				if(spriteDrawX > -1 && anInt974 < anInt975)
				{
					anIntArray979[anInt974] = boldFont.method384(((Entity) (obj)).textSpoken) / 2;
					anIntArray978[anInt974] = boldFont.anInt1497;
					anIntArray976[anInt974] = spriteDrawX;
					anIntArray977[anInt974] = spriteDrawY;
					anIntArray980[anInt974] = ((Entity)(Entity)obj).anInt1513;
					anIntArray981[anInt974] = ((Entity)(Entity)obj).anInt1531;
					anIntArray982[anInt974] = ((Entity)(Entity)obj).textCycle;
					aStringArray983[anInt974++] = ((Entity)(Entity)obj).textSpoken;
					if(anInt1249 == 0 && ((Entity) (obj)).anInt1531 >= 1 && ((Entity) (obj)).anInt1531 <= 3)
					{
						anIntArray978[anInt974] += 10;
						anIntArray977[anInt974] += 5;
					}
					if(anInt1249 == 0 && ((Entity) (obj)).anInt1531 == 4)
						anIntArray979[anInt974] = 60;
					if(anInt1249 == 0 && ((Entity) (obj)).anInt1531 == 5)
						anIntArray978[anInt974] += 5;
				}
			}
			if(((Entity) (obj)).loopCycleStatus > loopCycle)
			{
				try{
					npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
					if(spriteDrawX > -1)
					{
						if(healthBars) {
							int amount = 56;
							int j2 = (((Entity)(Entity)obj).currentHealth * amount) / ((Entity)(Entity)obj).maxHealth;
							if(j2 > amount)
								j2 = amount;
							HPBarEmpty.drawSprite(spriteDrawX - 28, spriteDrawY - 3);
							HPBarFull = new Sprite("HITPOINTS_0", j2, 7);
							HPBarFull.drawSprite(spriteDrawX - 28, spriteDrawY - 3);
						} else {
							int i1 = (((Entity) (obj)).currentHealth * 30) / ((Entity) (obj)).maxHealth;
							if(i1 > 30)
								i1 = 30;
							DrawingArea.drawPixels(5, spriteDrawY - 3, spriteDrawX - 15, 65280, i1);
							DrawingArea.drawPixels(5, spriteDrawY - 3, (spriteDrawX - 15) + i1, 0xff0000, 30 - i1);
						}
					}
				}catch(Exception ex){ }
			}
				for(int j1 = 0; j1 < 4; j1++)
					if(((Entity) (obj)).hitsLoopCycle[j1] > loopCycle) {
						npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height / 2);
						if(spriteDrawX > -1) {
							//spriteDrawY = 20 + 
							switch (j1) {
								case 1:
								spriteDrawY += 20;
								break;
								case 2:
								spriteDrawY += 25;
								break;
								case 3:
								spriteDrawY += 45;
								break;
							}
							Entity e = ((Entity) (obj));
							if (e.hitmarkMove[j1] > -30) {
									e.hitmarkMove[j1]--;
								if (e.hitmarkTrans[j1] > 0)
									e.hitmarkTrans[j1] -= 5;
								hitmarkDraw(e,String.valueOf(e.hitMarkDamage[j1]).length(), e.hitMarkTypes[j1], e.hitmarkIcon[j1], e.hitMarkDamage[j1], e.hitmarkMove[j1], e.hitmarkTrans[j1], e.hitMarkFocusPlayer[j1], e.hitMarkFocusNPC[j1]);
							}
						}
					}
			}
			for(int k = 0; k < anInt974; k++) {
				int k1 = anIntArray976[k];
				int l1 = anIntArray977[k];
				int j2 = anIntArray979[k];
				int k2 = anIntArray978[k];
				boolean flag = true;
				while(flag) 
				{
					flag = false;
					for(int l2 = 0; l2 < k; l2++)
						if(l1 + 2 > anIntArray977[l2] - anIntArray978[l2] && l1 - k2 < anIntArray977[l2] + 2 && k1 - j2 < anIntArray976[l2] + anIntArray979[l2] && k1 + j2 > anIntArray976[l2] - anIntArray979[l2] && anIntArray977[l2] - anIntArray978[l2] < l1)
						{
							l1 = anIntArray977[l2] - anIntArray978[l2];
							flag = true;
						}

				}
				spriteDrawX = anIntArray976[k];
				spriteDrawY = anIntArray977[k] = l1;
				String s = aStringArray983[k];
				if(anInt1249 == 0)
				{
					int i3 = 0xffff00;
					if(anIntArray980[k] < 6)
						i3 = anIntArray965[anIntArray980[k]];
					if(anIntArray980[k] == 6)
						i3 = anInt1265 % 20 >= 10 ? 0xffff00 : 0xff0000;
					if(anIntArray980[k] == 7)
						i3 = anInt1265 % 20 >= 10 ? 65535 : 255;
					if(anIntArray980[k] == 8)
						i3 = anInt1265 % 20 >= 10 ? 0x80ff80 : 45056;
					if(anIntArray980[k] == 9) {
						int j3 = 150 - anIntArray982[k];
						if(j3 < 50)
							i3 = 0xff0000 + 1280 * j3;
						else
						if(j3 < 100)
							i3 = 0xffff00 - 0x50000 * (j3 - 50);
						else
						if(j3 < 150)
							i3 = 65280 + 5 * (j3 - 100);
					}
					if(anIntArray980[k] == 10) {
						int k3 = 150 - anIntArray982[k];
						if(k3 < 50)
							i3 = 0xff0000 + 5 * k3;
						else
						if(k3 < 100)
							i3 = 0xff00ff - 0x50000 * (k3 - 50);
						else
						if(k3 < 150)
							i3 = (255 + 0x50000 * (k3 - 100)) - 5 * (k3 - 100);
					}
					if(anIntArray980[k] == 11) {
						int l3 = 150 - anIntArray982[k];
						if(l3 < 50)
							i3 = 0xffffff - 0x50005 * l3;
						else
						if(l3 < 100)
							i3 = 65280 + 0x50005 * (l3 - 50);
						else
						if(l3 < 150)
							i3 = 0xffffff - 0x50000 * (l3 - 100);
					}
					if(anIntArray981[k] == 0) {
						boldFont.drawText(0, s, spriteDrawY + 1, spriteDrawX);
						boldFont.drawText(i3, s, spriteDrawY, spriteDrawX);
					}
					if(anIntArray981[k] == 1) {
						boldFont.method386(0, s, spriteDrawX, anInt1265, spriteDrawY + 1);
						boldFont.method386(i3, s, spriteDrawX, anInt1265, spriteDrawY);
					}
					if(anIntArray981[k] == 2) {
						boldFont.method387(spriteDrawX, s, anInt1265, spriteDrawY + 1, 0);
						boldFont.method387(spriteDrawX, s, anInt1265, spriteDrawY, i3);
					}
					if(anIntArray981[k] == 3) {
						boldFont.method388(150 - anIntArray982[k], s, anInt1265, spriteDrawY + 1, spriteDrawX, 0);
						boldFont.method388(150 - anIntArray982[k], s, anInt1265, spriteDrawY, spriteDrawX, i3);
					}
					if(anIntArray981[k] == 4) {
						int i4 = boldFont.method384(s);
						int k4 = ((150 - anIntArray982[k]) * (i4 + 100)) / 150;
						DrawingArea.setDrawingArea(334, spriteDrawX - 50, spriteDrawX + 50, 0);
						boldFont.method385(0, s, spriteDrawY + 1, (spriteDrawX + 50) - k4);
						boldFont.method385(i3, s, spriteDrawY, (spriteDrawX + 50) - k4);
						DrawingArea.defaultDrawingAreaSize();
					}
					if(anIntArray981[k] == 5) {
						int j4 = 150 - anIntArray982[k];
						int l4 = 0;
						if(j4 < 25)
							l4 = j4 - 25;
						else
						if(j4 > 125)
							l4 = j4 - 125;
						DrawingArea.setDrawingArea(spriteDrawY + 5, 0, 512, spriteDrawY - boldFont.anInt1497 - 1);
						boldFont.drawText(0, s, spriteDrawY + 1 + l4, spriteDrawX);
						boldFont.drawText(i3, s, spriteDrawY + l4, spriteDrawX);
						DrawingArea.defaultDrawingAreaSize();
					}
				} else {
					boldFont.drawText(0, s, spriteDrawY + 1, spriteDrawX);
					boldFont.drawText(0xffff00, s, spriteDrawY, spriteDrawX);
				}
			}
		} catch(Exception e){ }
	}

	private void delFriend(long l)
	{
		try
		{
			if(l == 0L)
				return;
			for(int i = 0; i < friendsCount; i++)
			{
				if(friendsListAsLongs[i] != l)
					continue;
				friendsCount--;
				for(int j = i; j < friendsCount; j++)
				{
					friendsList[j] = friendsList[j + 1];
					friendsNodeIDs[j] = friendsNodeIDs[j + 1];
					friendsListAsLongs[j] = friendsListAsLongs[j + 1];
				}

				stream.createFrame(215);
				stream.writeQWord(l);
				break;
			}
		}
		catch(RuntimeException runtimeexception)
		{
			signlink.reporterror("18622, " + false + ", " + l + ", " + runtimeexception.toString());
			throw new RuntimeException();
		}
	}

	private void drawIventoryStones(int paddingX,int paddingY) {
		paddingX = paddingX + 2;//tabSpace
		if(toggleFullscreen) {
			inventoryStones[2].drawSprite(1 + paddingX, 261 + paddingY);
			inventoryStones[2].drawSprite(31 + paddingX, 261 + paddingY);
			inventoryStones[2].drawSprite(61 + paddingX, 261 + paddingY);
			inventoryStones[2].drawSprite(91 + paddingX, 261 + paddingY);
			inventoryStones[2].drawSprite(121 + paddingX, 261 + paddingY);
			inventoryStones[2].drawSprite(151 + paddingX, 261 + paddingY);
			inventoryStones[2].drawSprite(181 + paddingX, 261 + paddingY);
			inventoryStones[2].drawSprite(211 + paddingX, 261 + paddingY);
			
			inventoryStones[2].drawSprite(1 + paddingX, 298 + paddingY);
			inventoryStones[2].drawSprite(31 + paddingX, 298 + paddingY);
			inventoryStones[2].drawSprite(61 + paddingX, 298 + paddingY);
			inventoryStones[2].drawSprite(91 + paddingX, 298 + paddingY);
			inventoryStones[2].drawSprite(121 + paddingX, 298 + paddingY);
			inventoryStones[2].drawSprite(151 + paddingX, 298 + paddingY);
			inventoryStones[2].drawSprite(181 + paddingX,298 + paddingY);
			inventoryStones[2].drawSprite(211 + paddingX, 298 + paddingY);
		}
		//if(!contextMenuOpen) {
		if(newGameFrame) { 
			//System.out.println("game frame");
			if(tabInterfaceIDs[0] != -1)
				inventoryStones[tabIDHover == 0 ? 4 : 3].drawSprite(1 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[14] != -1)
				inventoryStones[tabIDHover == 1 ? 4 : 3].drawSprite(31 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[1] != -1)
				inventoryStones[tabIDHover == 2 ? 4 : 3].drawSprite(61 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[2] != -1)
				inventoryStones[tabIDHover == 3 ? 4 : 3].drawSprite(91 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[3] != -1)
				inventoryStones[tabIDHover == 4 ? 4 : 3].drawSprite(121 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[4] != -1)
				inventoryStones[tabIDHover == 5 ? 4 : 3].drawSprite(151 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[5] != -1)
				inventoryStones[tabIDHover == 6 ? 4 : 3].drawSprite(181 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[6] != -1)
				inventoryStones[tabIDHover == 7 ? 4 : 3].drawSprite(211 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
				
			if(tabInterfaceIDs[16] != -1)
				inventoryStones[tabIDHover == 14 ? 4 : 3].drawSprite(1 + paddingX, 298 + paddingY);
			if(tabInterfaceIDs[9] != -1)
				inventoryStones[tabIDHover == 8 ? 4 : 3].drawSprite(31 + paddingX, 298 + paddingY);
			if(tabInterfaceIDs[8] != -1)
				inventoryStones[tabIDHover == 9 ? 4 : 3].drawSprite(61 + paddingX, 298 + paddingY);
			if(tabInterfaceIDs[7] != -1)
				inventoryStones[tabIDHover == 10 ? 4 : 3].drawSprite(91 + paddingX, 298 + paddingY);
			if(tabInterfaceIDs[11] != -1)
				inventoryStones[tabIDHover == 11 ? 4 : 3].drawSprite(121 + paddingX, 298 + paddingY);
			if(tabInterfaceIDs[12] != -1)
				inventoryStones[tabIDHover == 12 ? 4 : 3].drawSprite(151 + paddingX, 298 + paddingY);
			if(tabInterfaceIDs[13] != -1)
				inventoryStones[tabIDHover == 13 ? 4 : 3].drawSprite(181 + paddingX,298 + paddingY);
			if(tabInterfaceIDs[15] != -1)
				inventoryStones[tabIDHover == 15 ? 4 : 3].drawSprite(211 + paddingX, 298 + paddingY);
		} else {
			if(tabInterfaceIDs[0] != -1)
				inventoryStones[tabIDHover == 0 ? 1 : 2].drawSprite(1 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[14] != -1)
				inventoryStones[tabIDHover == 1 ? 1 : 2].drawSprite(31 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[1] != -1)
				inventoryStones[tabIDHover == 2 ? 1 : 2].drawSprite(61 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[2] != -1)
				inventoryStones[tabIDHover == 3 ? 1 : 2].drawSprite(91 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[3] != -1)
				inventoryStones[tabIDHover == 4 ? 1 : 2].drawSprite(121 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[4] != -1)
				inventoryStones[tabIDHover == 5 ? 1 : 2].drawSprite(151 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[5] != -1)
				inventoryStones[tabIDHover == 6 ? 1 : 2].drawSprite(181 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
			if(tabInterfaceIDs[6] != -1)
				inventoryStones[tabIDHover == 7 ? 1 : 2].drawSprite(211 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
				
			if(tabInterfaceIDs[16] != -1)
				inventoryStones[tabIDHover == 14 ? 1 : 2].drawSprite(1 + paddingX, 298 + paddingY);
			if(tabInterfaceIDs[9] != -1)
				inventoryStones[tabIDHover == 8 ? 1 : 2].drawSprite(31 + paddingX, 298 + paddingY);
			if(tabInterfaceIDs[8] != -1)
				inventoryStones[tabIDHover == 9 ? 1 : 2].drawSprite(61 + paddingX, 298 + paddingY);
			if(tabInterfaceIDs[7] != -1)
				inventoryStones[tabIDHover == 10 ? 1 : 2].drawSprite(91 + paddingX, 298 + paddingY);
			if(tabInterfaceIDs[11] != -1)
				inventoryStones[tabIDHover == 11 ? 1 : 2].drawSprite(121 + paddingX, 298 + paddingY);
			if(tabInterfaceIDs[12] != -1)
				inventoryStones[tabIDHover == 12 ? 1 : 2].drawSprite(151 + paddingX, 298 + paddingY);
			if(tabInterfaceIDs[13] != -1)
				inventoryStones[tabIDHover == 13 ? 1 : 2].drawSprite(181 + paddingX,298 + paddingY);
			if(tabInterfaceIDs[15] != -1)
				inventoryStones[tabIDHover == 15 ? 1 : 2].drawSprite(211 + paddingX, 298 + paddingY);
		//}
		}
		if(invOverlayInterfaceID == -1 && focusTab != -1) {
			try {
			if(tabInterfaceIDs[tabID] != -1 && !newGameFrame) {
				if(tabID == 0)
					inventoryStones[0].drawSprite(1 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
				if(tabID == 14)
					inventoryStones[0].drawSprite(31 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
				if(tabID == 1)
					inventoryStones[0].drawSprite(61 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
				if(tabID == 2)
					inventoryStones[0].drawSprite(91 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
				if(tabID == 3)
					inventoryStones[0].drawSprite(121 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
				if(tabID == 4)
					inventoryStones[0].drawSprite(151 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
				if(tabID == 5)
					inventoryStones[0].drawSprite(181 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
				if(tabID == 6)
					inventoryStones[0].drawSprite(211 + paddingX, (toggleFullscreen ? 261 : 0) + paddingY);
				if(tabID == 16)
					inventoryStones[0].drawSprite(1 + paddingX, 298 + paddingY);
				if(tabID == 8)
					inventoryStones[0].drawSprite(31 + paddingX, 298 + paddingY);
				if(tabID == 9)
					inventoryStones[0].drawSprite(61 + paddingX, 298 + paddingY);
				if(tabID == 7)
					inventoryStones[0].drawSprite(91 + paddingX, 298 + paddingY);
				if(tabID == 11)
					inventoryStones[0].drawSprite(121 + paddingX, 298 + paddingY);
				if(tabID == 12)
					inventoryStones[0].drawSprite(151 + paddingX, 298 + paddingY);
				if(tabID == 13)
					inventoryStones[0].drawSprite(181 + paddingX, 298 + paddingY);
				if(tabID == 15)
					inventoryStones[0].drawSprite(211 + paddingX, 298 + paddingY);
			} else if (tabInterfaceIDs[tabID] != -1 && newGameFrame) {
				int specialPaddingX = paddingX;
				int specialPaddingY = paddingY;
				if(!toggleFullscreen) {
					specialPaddingX = 0;
					specialPaddingY = 0;
				}
				if(tabID == 0)
					inventoryStones[5].drawSprite(1 -6 + specialPaddingX, (toggleFullscreen ? 261 : 0) - 5 + specialPaddingY);
				if(tabID == 14)
					inventoryStones[5].drawSprite(31 - 6 + specialPaddingX, (toggleFullscreen ? 261 : 0) - 5 + specialPaddingY);
				if(tabID == 1)
					inventoryStones[5].drawSprite(61 - 6 + specialPaddingX, (toggleFullscreen ? 261 : 0) - 5 + specialPaddingY);
				if(tabID == 2)
					inventoryStones[5].drawSprite(91 - 6 + specialPaddingX, (toggleFullscreen ? 261 : 0) - 5 + specialPaddingY);
				if(tabID == 3)
					inventoryStones[5].drawSprite(121 - 6 + specialPaddingX, (toggleFullscreen ? 261 : 0) - 5 + specialPaddingY);
				if(tabID == 4)
					inventoryStones[5].drawSprite(151 - 6 + specialPaddingX, (toggleFullscreen ? 261 : 0) - 5 + specialPaddingY);
				if(tabID == 5)
					inventoryStones[5].drawSprite(181 - 6 + specialPaddingX, (toggleFullscreen ? 261 : 0) - 5 + specialPaddingY);
				if(tabID == 6)
					inventoryStones[5].drawSprite(211 - 6 + specialPaddingX, (toggleFullscreen ? 261 : 0) - 5 + specialPaddingY);
				if(tabID == 16)
					inventoryStones[5].drawSprite(1 - 6 + specialPaddingX, 298 - 5 + specialPaddingY);
				if(tabID == 8)
					inventoryStones[5].drawSprite(31 - 6 + specialPaddingX, 298 - 5 + specialPaddingY);
				if(tabID == 9)
					inventoryStones[5].drawSprite(61 - 6 + specialPaddingX, 298 - 5 + specialPaddingY);
				if(tabID == 7)
					inventoryStones[5].drawSprite(91 - 6 + specialPaddingX, 298 - 5 + specialPaddingY);
				if(tabID == 11)
					inventoryStones[5].drawSprite(121 - 6 + specialPaddingX, 298 - 5 + specialPaddingY);
				if(tabID == 12)
					inventoryStones[5].drawSprite(151 - 6 + specialPaddingX, 298 - 5 + specialPaddingY);
				if(tabID == 13)
					inventoryStones[5].drawSprite(181 - 6 + specialPaddingX, 298 - 5 + specialPaddingY);
				if(tabID == 15)
					inventoryStones[5].drawSprite(211 - 6 + specialPaddingX, 298 - 5 + specialPaddingY);
			}
		} catch(Exception e) { tabID = 1; }
	}
	}
	
	private void drawSideIcons(int paddingX, int paddingY) {
		if(tabIconFlash != -1)
			tabAreaAltered = true;
		if(tabAreaAltered) {
			if(tabIconFlash != -1 && tabIconFlash == tabID) {
				tabIconFlash = -1;
				stream.createFrame(120);
				stream.writeWordBigEndian(tabID);
			}
			tabAreaAltered = false;
		}
		if(invOverlayInterfaceID == -1) {
			if(!newGameFrame) {
			if(tabInterfaceIDs[0] != -1 && (tabIconFlash != 0 || loopCycle % 20 < 10))
				sideIcons[0].drawSprite(3 + 5 + paddingX, (toggleFullscreen ? 261 : 0) + 9 + paddingY);//Combat
			if(tabInterfaceIDs[14] != -1 && (tabIconFlash != 1 || loopCycle % 20 < 10))
				sideIcons[3].drawSprite(33 + 4 + paddingX, (toggleFullscreen ? 261 : 0) + 9 + paddingY);//skills
			if(tabInterfaceIDs[1] != -1 && (tabIconFlash != 2 || loopCycle % 20 < 10))
				sideIcons[1].drawSprite(63 + 4 + paddingX, (toggleFullscreen ? 261 : 0) + 8 + paddingY);//quests
			if(tabInterfaceIDs[2] != -1 && (tabIconFlash != 14 || loopCycle % 20 < 10))
				sideIcons[2].drawSprite(93 + 4 + paddingX, (toggleFullscreen ? 261 : 0) + 8 + paddingY);//task system
			if(tabInterfaceIDs[3] != -1 && (tabIconFlash != 3 || loopCycle % 20 < 10))
				sideIcons[4].drawSprite(123 + 4 + paddingX, (toggleFullscreen ? 261 : 0) + 9 + paddingY);//inventory
			if(tabInterfaceIDs[4] != -1 && (tabIconFlash != 4 || loopCycle % 20 < 10))
				sideIcons[5].drawSprite(153 + 6 + paddingX, (toggleFullscreen ? 261 : 0) + 8 + paddingY);//equipment
			if(tabInterfaceIDs[5] != -1 && (tabIconFlash != 5 || loopCycle % 20 < 10))
				sideIcons[6].drawSprite(183 + 4 + paddingX, (toggleFullscreen ? 261 : 0) + 9 + paddingY);//prayer
			if(tabInterfaceIDs[6] != -1 && (tabIconFlash != 6 || loopCycle % 20 < 10))
				sideIcons[7].drawSprite(213 + 4 + paddingX, (toggleFullscreen ? 261 : 0) + 9 + paddingY);//magic
				
			if(tabInterfaceIDs[14] != -1 && (tabIconFlash != 7 || loopCycle % 20 < 10))
				sideIcons[8].drawSprite(3 + 4 + paddingX, 298 + 10 + paddingY);//Guide
			if(tabInterfaceIDs[9] != -1 && (tabIconFlash != 9 || loopCycle % 20 < 10))
				sideIcons[9].drawSprite(33 + 5 + paddingX, 298 + 9 + paddingY);//friends
			if(tabInterfaceIDs[8] != -1 && (tabIconFlash != 8 || loopCycle % 20 < 10))
				sideIcons[10].drawSprite(63 + 7 + paddingX, 298 + 9 + paddingY);//ignore
			if(tabInterfaceIDs[7] != -1 && (tabIconFlash != 7 || loopCycle % 20 < 10))
				sideIcons[11].drawSprite(93 + 4 + paddingX, 298 + 10 + paddingY);//clan
			if(tabInterfaceIDs[11] != -1 && (tabIconFlash != 11 || loopCycle % 20 < 10))
				sideIcons[12].drawSprite(123 + 4 + paddingX, 298 + 9 + paddingY);//option
			if(tabInterfaceIDs[12] != -1 && (tabIconFlash != 12 || loopCycle % 20 < 10))
				sideIcons[13].drawSprite(153 + 4 + paddingX, 298 + 9 + paddingY);//emotes
			if(tabInterfaceIDs[13] != -1 && (tabIconFlash != 13 || loopCycle % 20 < 10))
				sideIcons[14].drawSprite(183 + 3 + paddingX, 298 + 8 + paddingY);//music
			if(tabInterfaceIDs[15] != -1 && (tabIconFlash != 15 || loopCycle % 20 < 10))
				sideIcons[15].drawSprite(213 + 4 + paddingX, 298 + 11 + paddingY);//notes
			} else {
				int specialPaddingX = paddingX;
				int specialPaddingY = paddingY;
				if(!toggleFullscreen) {
					specialPaddingX = 0;
					specialPaddingY = 0;
				}
				if(tabInterfaceIDs[0] != -1 && (tabIconFlash != 0 || loopCycle % 20 < 10))
					sideIcons[16].drawAdvancedSprite(3 + 5 - 5 + specialPaddingX, (toggleFullscreen ? 261 : 0) + 9 - 6 + specialPaddingY);//Combat
				if(tabInterfaceIDs[14] != -1 && (tabIconFlash != 1 || loopCycle % 20 < 10))
					sideIcons[19].drawAdvancedSprite(33 + 4 - 5 + specialPaddingX, (toggleFullscreen ? 261 : 0) + 9 - 6 + specialPaddingY);//skills
				if(tabInterfaceIDs[1] != -1 && (tabIconFlash != 2 || loopCycle % 20 < 10))
					sideIcons[17].drawAdvancedSprite(63 + 4 - 5 + specialPaddingX, (toggleFullscreen ? 261 : 0) + 8 - 6 + specialPaddingY);//quests
				if(tabInterfaceIDs[2] != -1 && (tabIconFlash != 14 || loopCycle % 20 < 10))
					sideIcons[18].drawAdvancedSprite(93 + 4 - 5 + specialPaddingX, (toggleFullscreen ? 261 : 0) + 8 - 6 + specialPaddingY);//task system
				if(tabInterfaceIDs[3] != -1 && (tabIconFlash != 3 || loopCycle % 20 < 10))
					sideIcons[20].drawAdvancedSprite(123 + 4 - 5 + specialPaddingX, (toggleFullscreen ? 261 : 0) + 9 - 6 + specialPaddingY);//inventory
				if(tabInterfaceIDs[4] != -1 && (tabIconFlash != 4 || loopCycle % 20 < 10))
					sideIcons[21].drawAdvancedSprite(153 + 6 - 5 + specialPaddingX, (toggleFullscreen ? 261 : 0) + 8 - 6 + specialPaddingY);//equipment
				if(tabInterfaceIDs[5] != -1 && (tabIconFlash != 5 || loopCycle % 20 < 10))
					sideIcons[22].drawAdvancedSprite(183 + 4 - 5 + specialPaddingX, (toggleFullscreen ? 261 : 0) + 9 - 6 + specialPaddingY + specialPaddingY);//prayer
				if(tabInterfaceIDs[6] != -1 && (tabIconFlash != 6 || loopCycle % 20 < 10))
					sideIcons[23].drawAdvancedSprite(213 + 4 - 5 + specialPaddingX, (toggleFullscreen ? 261 : 0) + 9 - 6 + specialPaddingY);//magic
					
				if(tabInterfaceIDs[14] != -1 && (tabIconFlash != 7 || loopCycle % 20 < 10))
					sideIcons[24].drawAdvancedSprite(3 + 4 - 5 + specialPaddingX, 298 + 10 - 6 + specialPaddingY);//Guide
				if(tabInterfaceIDs[9] != -1 && (tabIconFlash != 9 || loopCycle % 20 < 10))
					sideIcons[25].drawAdvancedSprite(33 + 5 - 5 + specialPaddingX, 298 + 9 - 6 + specialPaddingY);//friends
				if(tabInterfaceIDs[8] != -1 && (tabIconFlash != 8 || loopCycle % 20 < 10))
					sideIcons[26].drawAdvancedSprite(63 + 7 - 5 + specialPaddingX, 298 + 9 - 6 + specialPaddingY);//ignore
				if(tabInterfaceIDs[7] != -1 && (tabIconFlash != 7 || loopCycle % 20 < 10))
					sideIcons[27].drawAdvancedSprite(93 + 4 - 5 + specialPaddingX, 298 + 10 - 6 + specialPaddingY);//clan
				if(tabInterfaceIDs[11] != -1 && (tabIconFlash != 11 || loopCycle % 20 < 10))
					sideIcons[28].drawAdvancedSprite(123 + 4 - 5 + specialPaddingX, 298 + 9 - 6 + specialPaddingY);//option
				if(tabInterfaceIDs[12] != -1 && (tabIconFlash != 12 || loopCycle % 20 < 10))
					sideIcons[29].drawAdvancedSprite(153 + 4 - 5 + specialPaddingX, 298 + 9 - 6 + specialPaddingY);//emotes
				if(tabInterfaceIDs[13] != -1 && (tabIconFlash != 13 || loopCycle % 20 < 10))
					sideIcons[14].drawSprite(183 + 3 + paddingX, 298 + 8 + paddingY);//music
				if(tabInterfaceIDs[15] != -1 && (tabIconFlash != 15 || loopCycle % 20 < 10))
					sideIcons[30].drawAdvancedSprite(213 + 4 - 5 + specialPaddingX, 298 + 11 - 6 + specialPaddingY);//notes
			}
		}
	}

	private void drawTabArea(RSImageProducer tabImage, int paddingX,int paddingY) {
		tabImage.initDrawingArea();
		Texture.anIntArray1472 = anIntArray1181;
		if(toggleFullscreen) {
			if(focusTab != -1)
				tabAreaSprite[1].drawSprite1(45-23 + paddingX ,55-23 + paddingY - (toggleFullscreen ? 46 : 0),192);
		} else
			if(newGameFrame) {
				tabAreaSprite[2].drawSprite(0, 0);
			} else
			tabAreaSprite[0].drawSprite(0 + paddingX,0 + paddingY);
		
		drawIventoryStones(paddingX,paddingY);
		drawSideIcons(paddingX,paddingY);
		
		if(invOverlayInterfaceID != -1)
			drawInterface(0, 28 + paddingX, RSInterface.interfaceCache[invOverlayInterfaceID], 37 - (toggleFullscreen ? 46 : 0) + paddingY);
		else if(tabInterfaceIDs[tabID] != -1 && focusTab != -1)
			drawInterface(0, 28 + paddingX, RSInterface.interfaceCache[tabInterfaceIDs[tabID]], 37 - (toggleFullscreen ? 46 : 0) + paddingY);
		if(contextMenuOpen && !toggleFullscreen)
			drawMenu(519, 168);//Tab area
		Texture.anIntArray1472 = anIntArray1182;
	}
	
	private void drawRightFrameArea() {
		rightFrame.initDrawingArea();
		rightFrameSprite.drawSprite(0,0);
		if(contextMenuOpen && !toggleFullscreen) {
			drawMenu(516, 168);
		}
		rightFrame.drawGraphics(168, super.graphics, 516);
		main3DArea.initDrawingArea();
	}
	
	
	
	private void method37(int j) {
		if(Texture.anIntArray1480[17] >= j) {
				Background background = Texture.aBackgroundArray1474s[17];
				int k = background.anInt1452 * background.anInt1453 - 1;
				//fire cape apparently?
				int j1 = background.anInt1452 * anInt945 * 2;
				byte abyte0[] = background.aByteArray1450;
				byte abyte3[] = aByteArray912;
				for(int i2 = 0; i2 <= k; i2++)
					abyte3[i2] = abyte0[i2 - j1 & k];

				background.aByteArray1450 = abyte3;
				aByteArray912 = abyte0;
				Texture.method370(17);
				anInt854++;
				if(anInt854 > 1235) {
					anInt854 = 0;
					stream.createFrame(226);
					stream.writeWordBigEndian(0);
					int l2 = stream.currentOffset;
					stream.writeWord(58722);
					stream.writeWordBigEndian(240);
					stream.writeWord((int)(Math.random() * 65536D));
					stream.writeWordBigEndian((int)(Math.random() * 256D));
					if((int)(Math.random() * 2D) == 0)
						stream.writeWord(51825);
					stream.writeWordBigEndian((int)(Math.random() * 256D));
					stream.writeWord((int)(Math.random() * 65536D));
					stream.writeWord(7130);
					stream.writeWord((int)(Math.random() * 65536D));
					stream.writeWord(61657);
					stream.writeBytes(stream.currentOffset - l2);
				}
			}
			if(Texture.anIntArray1480[24] >= j) {
				Background background_1 = Texture.aBackgroundArray1474s[24];
				int l = background_1.anInt1452 * background_1.anInt1453 - 1;
				int k1 = background_1.anInt1452 * anInt945 * 2;
				byte abyte1[] = background_1.aByteArray1450;
				byte abyte4[] = aByteArray912;
				for(int j2 = 0; j2 <= l; j2++)
					abyte4[j2] = abyte1[j2 - k1 & l];

				background_1.aByteArray1450 = abyte4;
				aByteArray912 = abyte1;
				Texture.method370(24);
			}
			if(Texture.anIntArray1480[34] >= j) {
				Background background_2 = Texture.aBackgroundArray1474s[34];
				int i1 = background_2.anInt1452 * background_2.anInt1453 - 1;
				int l1 = background_2.anInt1452 * anInt945 * 2;
				byte abyte2[] = background_2.aByteArray1450;
				byte abyte5[] = aByteArray912;
				for(int k2 = 0; k2 <= i1; k2++)
					abyte5[k2] = abyte2[k2 - l1 & i1];

				background_2.aByteArray1450 = abyte5;
				aByteArray912 = abyte2;
				Texture.method370(34);
			}
			if(Texture.anIntArray1480[40] >= j)
			{
				Background background_2 = Texture.aBackgroundArray1474s[40];
				int i1 = background_2.anInt1452 * background_2.anInt1453 - 1;
				int l1 = background_2.anInt1452 * anInt945 * 1;
				byte abyte2[] = background_2.aByteArray1450;
				byte abyte5[] = aByteArray912;
				for(int k2 = 0; k2 <= i1; k2++)
					abyte5[k2] = abyte2[k2 - l1 & i1];

				background_2.aByteArray1450 = abyte5;
				aByteArray912 = abyte2;
				Texture.method370(40);
			}
		
	}

	private void method38() {
		for(int i = -1; i < playerCount; i++) {
			int j;
			if(i == -1)
				j = myPlayerIndex;
			else
				j = playerIndices[i];
			Player player = playerArray[j];
			if(player != null && player.textCycle > 0) {
				player.textCycle--;
				if(player.textCycle == 0)
					player.textSpoken = null;
			}
		}
		for(int k = 0; k < npcCount; k++) {
			int l = npcIndices[k];
			NPC npc = npcArray[l];
			if(npc != null && npc.textCycle > 0) {
				npc.textCycle--;
				if(npc.textCycle == 0)
					npc.textSpoken = null;
			}
		}
	}

	private void calcCameraPos() {
		int i = anInt1098 * 128 + 64;
		int j = anInt1099 * 128 + 64;
		int k = method42(plane, j, i) - anInt1100;
		if(xCameraPos < i) {
			xCameraPos += anInt1101 + ((i - xCameraPos) * anInt1102) / 1000;
			if(xCameraPos > i)
				xCameraPos = i;
		}
		if(xCameraPos > i) {
			xCameraPos -= anInt1101 + ((xCameraPos - i) * anInt1102) / 1000;
			if(xCameraPos < i)
				xCameraPos = i;
		}
		if(zCameraPos < k) {
			zCameraPos += anInt1101 + ((k - zCameraPos) * anInt1102) / 1000;
			if(zCameraPos > k)
				zCameraPos = k;
		}
		if(zCameraPos > k) {
			zCameraPos -= anInt1101 + ((zCameraPos - k) * anInt1102) / 1000;
			if(zCameraPos < k)
				zCameraPos = k;
		}
		if(yCameraPos < j) {
			yCameraPos += anInt1101 + ((j - yCameraPos) * anInt1102) / 1000;
			if(yCameraPos > j)
				yCameraPos = j;
		}
		if(yCameraPos > j) {
			yCameraPos -= anInt1101 + ((yCameraPos - j) * anInt1102) / 1000;
			if(yCameraPos < j)
				yCameraPos = j;
		}
		i = anInt995 * 128 + 64;
		j = anInt996 * 128 + 64;
		k = method42(plane, j, i) - anInt997;
		int l = i - xCameraPos;
		int i1 = k - zCameraPos;
		int j1 = j - yCameraPos;
		int k1 = (int)Math.sqrt(l * l + j1 * j1);
		int l1 = (int)(Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
		int i2 = (int)(Math.atan2(l, j1) * -325.94900000000001D) & 0x7ff;
		if(l1 < 128)
			l1 = 128;
		if(l1 > 383)
			l1 = 383;
		if(yCameraCurve < l1) {
			yCameraCurve += anInt998 + ((l1 - yCameraCurve) * anInt999) / 1000;
			if(yCameraCurve > l1)
				yCameraCurve = l1;
		}
		if(yCameraCurve > l1) {
			yCameraCurve -= anInt998 + ((yCameraCurve - l1) * anInt999) / 1000;
			if(yCameraCurve < l1)
				yCameraCurve = l1;
		}
		int j2 = i2 - xCameraCurve;
		if(j2 > 1024)
			j2 -= 2048;
		if(j2 < -1024)
			j2 += 2048;
		if(j2 > 0) {
			xCameraCurve += anInt998 + (j2 * anInt999) / 1000;
			xCameraCurve &= 0x7ff;
		}
		if(j2 < 0) {
			xCameraCurve -= anInt998 + (-j2 * anInt999) / 1000;
			xCameraCurve &= 0x7ff;
		}
		int k2 = i2 - xCameraCurve;
		if(k2 > 1024)
			k2 -= 2048;
		if(k2 < -1024)
			k2 += 2048;
		if(k2 < 0 && j2 > 0 || k2 > 0 && j2 < 0)
			xCameraCurve = i2;
	}

	private void drawMenu(int xOffSet, int yOffSet) {
		int xPos = menuOffsetX - (xOffSet - 4);
		int yPos = (-yOffSet + 4) + menuOffsetY;
		int menuW = menuWidth;
		int menuH = menuHeight + 1;
		inputTaken = true;
		tabAreaAltered = true;
		//DrawingArea.drawPixels(height, yPos, xPos, color, width);
		//DrawingArea.fillPixels(xPos, width, height, color, yPos);
		DrawingArea.drawPixels(menuH - 4, yPos + 2, xPos, 0x706a5e, menuW);
		DrawingArea.drawPixels(menuH - 2, yPos + 1, xPos + 1, 0x706a5e, menuW - 2);
		DrawingArea.drawPixels(menuH, yPos, xPos + 2, 0x706a5e, menuW - 4);
		DrawingArea.drawPixels(menuH - 2, yPos + 1, xPos + 3, 0x2d2822, menuW - 6);
		DrawingArea.drawPixels(menuH - 4, yPos + 2, xPos + 2, 0x2d2822, menuW - 4);
		DrawingArea.drawPixels(menuH - 6, yPos + 3, xPos + 1, 0x2d2822, menuW - 2);
		DrawingArea.drawPixels(menuH - 22, yPos + 19, xPos + 2, 0x524a3d, menuW - 4);
		DrawingArea.drawPixels(menuH - 22, yPos + 20, xPos + 3, 0x524a3d, menuW - 6);
		DrawingArea.drawPixels(menuH - 23, yPos + 20, xPos + 3, 0x2b271c, menuW - 6);
		DrawingArea.fillPixels(xPos + 3, menuW - 6, 1, 0x2a291b, yPos + 2);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x2a261b, yPos + 3);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x252116, yPos + 4);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x211e15, yPos + 5);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x1e1b12, yPos + 6);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x1a170e, yPos + 7);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 2, 0x15120b, yPos + 8);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x100d08, yPos + 10);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x090a04, yPos + 11);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x080703, yPos + 12);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x090a04, yPos + 13);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x070802, yPos + 14);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x090a04, yPos + 15);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x070802, yPos + 16);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x090a04, yPos + 17);
		DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x2a291b, yPos + 18);
		DrawingArea.fillPixels(xPos + 3, menuW - 6, 1, 0x564943, yPos + 19);
		boldFont.method385(0xc6b895, "Choose Option", yPos + 14, xPos + 3);
		int mouseX = super.mouseX - (xOffSet);
		int mouseY = (-yOffSet) + super.mouseY;
		for(int l1 = 0; l1 < menuActionRow; l1++) {
			int textY = yPos + 31 + (menuActionRow - 1 - l1) * 15;
			int disColor = 0xc6b895;
			if(mouseX > xPos && mouseX < xPos + menuW && mouseY > textY - 13 && mouseY < textY + 3) {
				DrawingArea.drawPixels(15, textY - 11, xPos + 3, 0x6f695d, menuWidth - 6);
				disColor = 0xeee5c6;
			}
			boldFont.method389(true, xPos + 3, disColor, menuActionName[l1], textY);
		}
	}

	private void addFriend(long l) {
		try {
			if(l == 0L)
				return;
			if(friendsCount >= 100 && anInt1046 != 1) {
				pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "");
				return;
			}
			if(friendsCount >= 200) {
				pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "");
				return;
			}
			String s = TextClass.fixName(TextClass.nameForLong(l));
			for(int i = 0; i < friendsCount; i++)
				if(friendsListAsLongs[i] == l) {
					pushMessage(s + " is already on your friend list", 0, "");
					return;
				}
			for(int j = 0; j < ignoreCount; j++)
				if(ignoreListAsLongs[j] == l) {
					pushMessage("Please remove " + s + " from your ignore list first", 0, "");
					return;
				}

			if(s.equals(myPlayer.name)) {
				return;
			} else {
				friendsList[friendsCount] = s;
				friendsListAsLongs[friendsCount] = l;
				friendsNodeIDs[friendsCount] = 0;
				friendsCount++;
				stream.createFrame(188);
				stream.writeQWord(l);
				return;
			}
		} catch(RuntimeException runtimeexception) {
			signlink.reporterror("15283, " + (byte)68 + ", " + l + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}

	private int method42(int i, int j, int k) {
		int l = k >> 7;
		int i1 = j >> 7;
		if(l < 0 || i1 < 0 || l > 103 || i1 > 103)
			return 0;
		int j1 = i;
		if(j1 < 3 && (byteGroundArray[1][l][i1] & 2) == 2)
			j1++;
		int k1 = k & 0x7f;
		int l1 = j & 0x7f;
		int i2 = intGroundArray[j1][l][i1] * (128 - k1) + intGroundArray[j1][l + 1][i1] * k1 >> 7;
		int j2 = intGroundArray[j1][l][i1 + 1] * (128 - k1) + intGroundArray[j1][l + 1][i1 + 1] * k1 >> 7;
		return i2 * (128 - l1) + j2 * l1 >> 7;
	}

	private static String intToKOrMil(int j) {
		if(j < 0x186a0)
			return String.valueOf(j);
		if(j < 0x989680)
			return j / 1000 + "K";
		else
			return j / 0xf4240 + "M";
	}

	private void resetLogout() {
		try {
			if(socketStream != null)
				socketStream.close();
		}
		catch(Exception _ex) { }
		socketStream = null;
		loggedIn = false;
		alertHandler.alert = null;
		loginScreenState = 0;
		//myUsername = "";
		//myPassword = "";
		unlinkMRUNodes();
		worldController.initToNull();
		for(int i = 0; i < 4; i++)
			aClass11Array1230[i].method210();
		System.gc();
		stopMidi();
		currentSong = -1;
		nextSong = -1;
		prevSong = 0;
		autocastId = 0;
		autoCast = false;
		turnOffPrayers();
		orbToggle[1] = false;
		if(toggleFullscreen)
			switchToNormal();
	}

	private void method45() {
		aBoolean1031 = true;
		for(int j = 0; j < 7; j++) {
			anIntArray1065[j] = -1;
			for(int k = 0; k < IDK.length; k++) {
				if(IDK.cache[k].aBoolean662 || IDK.cache[k].anInt657 != j + (aBoolean1047 ? 0 : 7))
					continue;
				anIntArray1065[j] = k;
				break;
			}
		}
	}
	
	private void method46(int i, Stream stream) {
		while(stream.bitPosition + 21 < i * 8) {
			int k = stream.readBits(14);
			if(k == 16383)
				break;
			if(npcArray[k] == null)
				npcArray[k] = new NPC();
			NPC npc = npcArray[k];
			npcIndices[npcCount++] = k;
			npc.anInt1537 = loopCycle;
			int l = stream.readBits(5);
			if(l > 15)
				l -= 32;
			int i1 = stream.readBits(5);
			if(i1 > 15)
				i1 -= 32;
			int j1 = stream.readBits(1);
			npc.desc = EntityDef.forID(stream.readBits(14));
			int k1 = stream.readBits(1);
			if(k1 == 1)
				anIntArray894[anInt893++] = k;
			npc.anInt1540 = npc.desc.aByte68;
			npc.anInt1504 = npc.desc.anInt79;
			npc.anInt1554 = npc.desc.walkAnim;
			npc.anInt1555 = npc.desc.anInt58;
			npc.anInt1556 = npc.desc.anInt83;
			npc.anInt1557 = npc.desc.anInt55;
			npc.anInt1511 = npc.desc.standAnim;
			npc.setPos(myPlayer.smallX[0] + i1, myPlayer.smallY[0] + l, j1 == 1);
		}
		stream.finishBitAccess();
	}

	public void processGameLoop() {
		if(rsAlreadyLoaded || loadingError || genericLoadingError)
			return;
		loopCycle++;
		if(!loggedIn)
			processLoginScreenInput();
		else
			mainGameProcessor();
		processOnDemandQueue();
	}

	private void method47(boolean flag) {
		if(myPlayer.x >> 7 == destX && myPlayer.y >> 7 == destY)
			destX = 0;
		int j = playerCount;
		if(flag)
			j = 1;
		for(int l = 0; l < j; l++) {
			Player player;
			int i1;
			if(flag) {
				player = myPlayer;
				i1 = myPlayerIndex << 14;
			} else {
				player = playerArray[playerIndices[l]];
				i1 = playerIndices[l] << 14;
			}
			if(player == null || !player.isVisible())
				continue;
			player.aBoolean1699 = (lowMem && playerCount > 50 || playerCount > 200) && !flag && player.anInt1517 == player.anInt1511;
			int j1 = player.x >> 7;
			int k1 = player.y >> 7;
			if(j1 < 0 || j1 >= 104 || k1 < 0 || k1 >= 104)
				continue;
			if(player.aModel_1714 != null && loopCycle >= player.anInt1707 && loopCycle < player.anInt1708) {
				player.aBoolean1699 = false;
				player.anInt1709 = method42(plane, player.y, player.x);
				worldController.method286(plane, player.y, player, player.anInt1552, player.anInt1722, player.x, player.anInt1709, player.anInt1719, player.anInt1721, i1, player.anInt1720);
				continue;
			}
			if((player.x & 0x7f) == 64 && (player.y & 0x7f) == 64) {
				if(anIntArrayArray929[j1][k1] == anInt1265)
					continue;
				anIntArrayArray929[j1][k1] = anInt1265;
			}
			player.anInt1709 = method42(plane, player.y, player.x);
			worldController.method285(plane, player.anInt1552, player.anInt1709, i1, player.y, 60, player.x, player, player.aBoolean1541);
		}
	}

	private boolean promptUserForInput(RSInterface class9) {
		int j = class9.contentType;
		if(anInt900 == 2) {
			if(j == 201) {
				inputTaken = true;
				inputDialogState = 0;
				messagePromptRaised = true;
				promptInput = "";
				friendsListAction = 1;
				aString1121 = "Enter name of friend to add to list";
			}
			if(j == 202) {
				inputTaken = true;
				inputDialogState = 0;
				messagePromptRaised = true;
				promptInput = "";
				friendsListAction = 2;
				aString1121 = "Enter name of friend to delete from list";
			}
		}
		if(j == 205) {
			anInt1011 = 250;
			return true;
		}
		if(j == 501) {
			inputTaken = true;
			inputDialogState = 0;
			messagePromptRaised = true;
			promptInput = "";
			friendsListAction = 4;
			aString1121 = "Enter name of player to add to list";
		}
		if(j == 502) {
			inputTaken = true;
			inputDialogState = 0;
			messagePromptRaised = true;
			promptInput = "";
			friendsListAction = 5;
			aString1121 = "Enter name of player to delete from list";
		}
		if(j == 550) {
			inputTaken = true;
			inputDialogState = 0;
			messagePromptRaised = true;
			promptInput = "";
			friendsListAction = 6;
			aString1121 = "Enter the name of the chat you wish to join";
		}
		if(j >= 300 && j <= 313) {
			int k = (j - 300) / 2;
			int j1 = j & 1;
			int i2 = anIntArray1065[k];
			if(i2 != -1) {
				do {
					if(j1 == 0 && --i2 < 0)
						i2 = IDK.length - 1;
					if(j1 == 1 && ++i2 >= IDK.length)
						i2 = 0;
				} while(IDK.cache[i2].aBoolean662 || IDK.cache[i2].anInt657 != k + (aBoolean1047 ? 0 : 7));
				anIntArray1065[k] = i2;
				aBoolean1031 = true;
			}
		}
		if(j >= 314 && j <= 323) {
			int l = (j - 314) / 2;
			int k1 = j & 1;
			int j2 = anIntArray990[l];
			if(k1 == 0 && --j2 < 0)
				j2 = anIntArrayArray1003[l].length - 1;
			if(k1 == 1 && ++j2 >= anIntArrayArray1003[l].length)
				j2 = 0;
			anIntArray990[l] = j2;
			aBoolean1031 = true;
		}
		if(j == 324 && !aBoolean1047) {
			aBoolean1047 = true;
			method45();
		}
		if(j == 325 && aBoolean1047) {
			aBoolean1047 = false;
			method45();
		}
		if(j == 326) {
			stream.createFrame(101);
			stream.writeWordBigEndian(aBoolean1047 ? 0 : 1);
			for(int i1 = 0; i1 < 7; i1++)
				stream.writeWordBigEndian(anIntArray1065[i1]);

			for(int l1 = 0; l1 < 5; l1++)
				stream.writeWordBigEndian(anIntArray990[l1]);

			return true;
		}
		System.out.println(""+j);
		if(j == 612)
			canMute = !canMute;
		if(j == 613)
			canBan = !canBan;
		if(j >= 602 && j <= 616) {
			clearTopInterfaces();
			if(reportAbuseInput.length() > 0 && myPlayer.playerRights > 0 && myPlayer.playerRights != 4) {
			
			String[] commands = {"mute","unmute","jail","unjail","ban","unban","","ipmute","unipmute","ipban","unipban"};
				String inputString = "::"+(commands[j-602])+" "+reportAbuseInput;
				stream.createFrame(103);
				stream.writeWordBigEndian(inputString.length() - 1);
				stream.writeString(inputString.substring(2));
			}
		
		}
		return false;
	}
	private void method49(Stream stream) {
		for(int j = 0; j < anInt893; j++) {
			int k = anIntArray894[j];
			Player player = playerArray[k];
			int l = stream.readUnsignedByte();
			if((l & 0x40) != 0)
				l += stream.readUnsignedByte() << 8;
			method107(l, k, stream, player);
		}
	}	
	
	private void method50(int i, int k, int l, int i1, int j1) {
		int k1 = worldController.method300(j1, l, i);
		if(k1 != 0) {
			int l1 = worldController.method304(j1, l, i, k1);
			int k2 = l1 >> 6 & 3;
			int i3 = l1 & 0x1f;
			int k3 = k;
			if(k1 > 0)
				k3 = i1;
			int ai[] = miniMapImage.myPixels;
			int k4 = 24624 + l * 4 + (103 - i) * 512 * 4;
			int i5 = k1 >> 14 & 0x7fff;
			ObjectDef class46_2 = ObjectDef.forID(i5);
			if(class46_2.anInt758 != -1) {
				Background background_2 = mapScenes[class46_2.anInt758];
				if(background_2 != null) {
					int i6 = (class46_2.anInt744 * 4 - background_2.anInt1452) / 2;
					int j6 = (class46_2.anInt761 * 4 - background_2.anInt1453) / 2;
					background_2.drawBackground(48 + l * 4 + i6, 48 + (104 - i - class46_2.anInt761) * 4 + j6);
				}
			} else {
				if(i3 == 0 || i3 == 2)
					if(k2 == 0) {
						ai[k4] = k3;
						ai[k4 + 512] = k3;
						ai[k4 + 1024] = k3;
						ai[k4 + 1536] = k3;
					} else if(k2 == 1) {
						ai[k4] = k3;
						ai[k4 + 1] = k3;
						ai[k4 + 2] = k3;
						ai[k4 + 3] = k3;
					} else if(k2 == 2) {
						ai[k4 + 3] = k3;
						ai[k4 + 3 + 512] = k3;
						ai[k4 + 3 + 1024] = k3;
						ai[k4 + 3 + 1536] = k3;
					} else if(k2 == 3) {
						ai[k4 + 1536] = k3;
						ai[k4 + 1536 + 1] = k3;
						ai[k4 + 1536 + 2] = k3;
						ai[k4 + 1536 + 3] = k3;
					}
				if(i3 == 3)
					if(k2 == 0)
						ai[k4] = k3;
					else if(k2 == 1)
						ai[k4 + 3] = k3;
					else if(k2 == 2)
						ai[k4 + 3 + 1536] = k3;
					else if(k2 == 3)
						ai[k4 + 1536] = k3;
				if(i3 == 2)
					if(k2 == 3) {
						ai[k4] = k3;
						ai[k4 + 512] = k3;
						ai[k4 + 1024] = k3;
						ai[k4 + 1536] = k3;
					} else if(k2 == 0) {
						ai[k4] = k3;
						ai[k4 + 1] = k3;
						ai[k4 + 2] = k3;
						ai[k4 + 3] = k3;
					} else if(k2 == 1) {
						ai[k4 + 3] = k3;
						ai[k4 + 3 + 512] = k3;
						ai[k4 + 3 + 1024] = k3;
						ai[k4 + 3 + 1536] = k3;
					} else if(k2 == 2) {
						ai[k4 + 1536] = k3;
						ai[k4 + 1536 + 1] = k3;
						ai[k4 + 1536 + 2] = k3;
						ai[k4 + 1536 + 3] = k3;
					}
			}
		}
		k1 = worldController.method302(j1, l, i);
		if(k1 != 0) {
			int i2 = worldController.method304(j1, l, i, k1);
			int l2 = i2 >> 6 & 3;
			int j3 = i2 & 0x1f;
			int l3 = k1 >> 14 & 0x7fff;
			ObjectDef class46_1 = ObjectDef.forID(l3);
			if(class46_1.anInt758 != -1) {
				Background background_1 = mapScenes[class46_1.anInt758];
				if(background_1 != null) {
					int j5 = (class46_1.anInt744 * 4 - background_1.anInt1452) / 2;
					int k5 = (class46_1.anInt761 * 4 - background_1.anInt1453) / 2;
					background_1.drawBackground(48 + l * 4 + j5, 48 + (104 - i - class46_1.anInt761) * 4 + k5);
				}
			} else if(j3 == 9) {
				int l4 = 0xeeeeee;
				if(k1 > 0)
					l4 = 0xee0000;
				int ai1[] = miniMapImage.myPixels;
				int l5 = 24624 + l * 4 + (103 - i) * 512 * 4;
				if(l2 == 0 || l2 == 2) {
					ai1[l5 + 1536] = l4;
					ai1[l5 + 1024 + 1] = l4;
					ai1[l5 + 512 + 2] = l4;
					ai1[l5 + 3] = l4;
				} else {
					ai1[l5] = l4;
					ai1[l5 + 512 + 1] = l4;
					ai1[l5 + 1024 + 2] = l4;
					ai1[l5 + 1536 + 3] = l4;
				}
			}
		}
		k1 = worldController.method303(j1, l, i);
		if(k1 != 0) {
			int j2 = k1 >> 14 & 0x7fff;
			ObjectDef class46 = ObjectDef.forID(j2);
			if(class46.anInt758 != -1) {
				Background background = mapScenes[class46.anInt758];
				if(background != null) {
					int i4 = (class46.anInt744 * 4 - background.anInt1452) / 2;
					int j4 = (class46.anInt761 * 4 - background.anInt1453) / 2;
					background.drawBackground(48 + l * 4 + i4, 48 + (104 - i - class46.anInt761) * 4 + j4);
				}
			}
		}
	}

	private void loadTitleScreen() {
	
		new Background(titleStreamLoader, "titlebutton", 0);
		aBackgroundArray1152s = new Background[12];
		int j = 0;
		try {
			j = Integer.parseInt(getParameter("fl_icon"));
		} catch(Exception _ex) {
		}
		if(j == 0) {
			for(int k = 0; k < 12; k++)
				aBackgroundArray1152s[k] = new Background(titleStreamLoader, "runes", k);

		} else {
			for(int l = 0; l < 12; l++)
				aBackgroundArray1152s[l] = new Background(titleStreamLoader, "runes", 12 + (l & 3));

		}
		aClass30_Sub2_Sub1_Sub1_1201 = new Sprite(128, 265);
		aClass30_Sub2_Sub1_Sub1_1202 = new Sprite(128, 265);
		System.arraycopy(aRSImageProducer_1110.anIntArray315, 0, aClass30_Sub2_Sub1_Sub1_1201.myPixels, 0, 33920);

		System.arraycopy(aRSImageProducer_1111.anIntArray315, 0, aClass30_Sub2_Sub1_Sub1_1202.myPixels, 0, 33920);

		anIntArray851 = new int[256];
		for(int k1 = 0; k1 < 64; k1++)
			anIntArray851[k1] = k1 * 0x40000;

		for(int l1 = 0; l1 < 64; l1++)
			anIntArray851[l1 + 64] = 0xff0000 + 1024 * l1;

		for(int i2 = 0; i2 < 64; i2++)
			anIntArray851[i2 + 128] = 0xffff00 + 4 * i2;

		for(int j2 = 0; j2 < 64; j2++)
			anIntArray851[j2 + 192] = 0xffffff;

		anIntArray852 = new int[256];
		for(int k2 = 0; k2 < 64; k2++)
			anIntArray852[k2] = k2 * 1024;

		for(int l2 = 0; l2 < 64; l2++)
			anIntArray852[l2 + 64] = 65280 + 4 * l2;

		for(int i3 = 0; i3 < 64; i3++)
			anIntArray852[i3 + 128] = 65535 + 0x40000 * i3;

		for(int j3 = 0; j3 < 64; j3++)
			anIntArray852[j3 + 192] = 0xffffff;

		anIntArray853 = new int[256];
		for(int k3 = 0; k3 < 64; k3++)
			anIntArray853[k3] = k3 * 4;

		for(int l3 = 0; l3 < 64; l3++)
			anIntArray853[l3 + 64] = 255 + 0x40000 * l3;

		for(int i4 = 0; i4 < 64; i4++)
			anIntArray853[i4 + 128] = 0xff00ff + 1024 * i4;

		for(int j4 = 0; j4 < 64; j4++)
			anIntArray853[j4 + 192] = 0xffffff;
		anIntArray850 = new int[256];
		anIntArray1190 = new int[32768];
		anIntArray1191 = new int[32768];
		anIntArray828 = new int[32768];
		randomizeBackground(null);
		drawLoadingText(10, "");
		if(!aBoolean831) {
			drawFlames = true;
			aBoolean831 = true;
			startRunnable(this, 2);
		}
	}

	private static void setHighMem() {
		WorldController.lowMem = false;
		Texture.lowMem = false;
		lowMem = false;
		ObjectManager.lowMem = false;
		ObjectDef.lowMem = false;
	}
	
	private void loadingStages() {
		if(lowMem && loadingStage == 2 && ObjectManager.anInt131 != plane) {
			main3DArea.initDrawingArea();
			normalFont.drawText(0, "Loading - please wait.", 151, 257);
			normalFont.drawText(0xffffff, "Loading - please wait.", 150, 256);
			main3DArea.drawGraphics(toggleFullscreen ? 0 : 4, super.graphics, toggleFullscreen ? 0 : 4);
			loadingStage = 1;
			aLong824 = System.currentTimeMillis();
		}
		if(loadingStage == 1) {
			int j = method54();
			if(j != 0 && System.currentTimeMillis() - aLong824 > 0x57e40L) {
				signlink.reporterror(myUsername + " glcfb " + aLong1215 + "," + j + "," + lowMem + "," + decompressors[0] + "," + onDemandFetcher.getNodeCount() + "," + plane + "," + anInt1069 + "," + anInt1070);
				aLong824 = System.currentTimeMillis();
			}
		}
		if(loadingStage == 2 && plane != anInt985) {
			anInt985 = plane;
			method24(plane);
		}
	}

	private int method54() {
		for(int i = 0; i < aByteArrayArray1183.length; i++) {
			if(aByteArrayArray1183[i] == null && anIntArray1235[i] != -1)
				return -1;
			if(aByteArrayArray1247[i] == null && anIntArray1236[i] != -1)
				return -2;
		}
		boolean flag = true;
		for(int j = 0; j < aByteArrayArray1183.length; j++) {
			byte abyte0[] = aByteArrayArray1247[j];
			if(abyte0 != null) {
				int k = (anIntArray1234[j] >> 8) * 64 - baseX;
				int l = (anIntArray1234[j] & 0xff) * 64 - baseY;
				if(aBoolean1159) {
					k = 10;
					l = 10;
				}
				flag &= ObjectManager.method189(k, abyte0, l);
			}
		}
		if(!flag)
			return -3;
		if(aBoolean1080) {
			return -4;
		} else {
			loadingStage = 2;
			ObjectManager.anInt131 = plane;
			method22();
			stream.createFrame(121);
			return 0;
		}
	}

	private void method55()
	{
		for(Animable_Sub4 class30_sub2_sub4_sub4 = (Animable_Sub4)aClass19_1013.reverseGetFirst(); class30_sub2_sub4_sub4 != null; class30_sub2_sub4_sub4 = (Animable_Sub4)aClass19_1013.reverseGetNext())
			if(class30_sub2_sub4_sub4.anInt1597 != plane || loopCycle > class30_sub2_sub4_sub4.anInt1572)
				class30_sub2_sub4_sub4.unlink();
			else
			if(loopCycle >= class30_sub2_sub4_sub4.anInt1571)
			{
				if(class30_sub2_sub4_sub4.anInt1590 > 0)
				{
					NPC npc = npcArray[class30_sub2_sub4_sub4.anInt1590 - 1];
					if(npc != null && npc.x >= 0 && npc.x < 13312 && npc.y >= 0 && npc.y < 13312)
						class30_sub2_sub4_sub4.method455(loopCycle, npc.y, method42(class30_sub2_sub4_sub4.anInt1597, npc.y, npc.x) - class30_sub2_sub4_sub4.anInt1583, npc.x);
				}
				if(class30_sub2_sub4_sub4.anInt1590 < 0)
				{
					int j = -class30_sub2_sub4_sub4.anInt1590 - 1;
					Player player;
					if(j == unknownInt10)
						player = myPlayer;
					else
						player = playerArray[j];
					if(player != null && player.x >= 0 && player.x < 13312 && player.y >= 0 && player.y < 13312)
						class30_sub2_sub4_sub4.method455(loopCycle, player.y, method42(class30_sub2_sub4_sub4.anInt1597, player.y, player.x) - class30_sub2_sub4_sub4.anInt1583, player.x);
				}
				class30_sub2_sub4_sub4.method456(anInt945);
				worldController.method285(plane, class30_sub2_sub4_sub4.anInt1595, (int)class30_sub2_sub4_sub4.aDouble1587, -1, (int)class30_sub2_sub4_sub4.aDouble1586, 60, (int)class30_sub2_sub4_sub4.aDouble1585, class30_sub2_sub4_sub4, false);
			}

	}

	public AppletContext getAppletContext()
	{
		if(signlink.mainapp != null)
			return signlink.mainapp.getAppletContext();
		else
			return super.getAppletContext();
	}

	//private void drawLogo() {
		//sbyte abyte0[] = titleStreamLoader.getDataForName("title.dat");
	//	Sprite sprite = new Sprite(abyte0, this);
	/*Sprite sprite = new Sprite("background");
		aRSImageProducer_1110.initDrawingArea();
		sprite.method346(0, 0);
		aRSImageProducer_1111.initDrawingArea();
		sprite.method346(-637, 0);
		aRSImageProducer_1107.initDrawingArea();
		sprite.method346(-128, 0);
		aRSImageProducer_1108.initDrawingArea();
		sprite.method346(-202, -371);
		aRSImageProducer_1109.initDrawingArea();
		sprite.method346(-202, -171);
		aRSImageProducer_1112.initDrawingArea();
		sprite.method346(0, -265);
		aRSImageProducer_1113.initDrawingArea();
		sprite.method346(-562, -265);
		aRSImageProducer_1114.initDrawingArea();
		sprite.method346(-128, -171);
		aRSImageProducer_1115.initDrawingArea();
		sprite.method346(-562, -171);

		aRSImageProducer_1110.initDrawingArea();
		aRSImageProducer_1111.initDrawingArea();
		aRSImageProducer_1107.initDrawingArea();
		aRSImageProducer_1108.initDrawingArea();
		aRSImageProducer_1109.initDrawingArea();
		aRSImageProducer_1112.initDrawingArea();
		aRSImageProducer_1113.initDrawingArea();
		aRSImageProducer_1114.initDrawingArea();
		aRSImageProducer_1115.initDrawingArea();*/
		/*sprite = new Sprite(titleStreamLoader, "logo", 0); // shows rs banner on top of client in middle
		aRSImageProducer_1107.initDrawingArea();
		sprite.drawSprite(382 - sprite.myWidth / 2 - 128, 18);
		*/

		/*sprite = null;
		System.gc();
	}*/
	
	private void drawLogo() {
		//byte abyte0[] = titleStreamLoader.getDataForName("title.dat");
		//Sprite sprite = new Sprite(abyte0, this);
		Sprite sprite = new Sprite("background");
		aRSImageProducer_1110.initDrawingArea();
	        sprite.method346(0, 0);
	        aRSImageProducer_1111.initDrawingArea();
	        sprite.method346(-637, 0);
	        aRSImageProducer_1107.initDrawingArea();
	        sprite.method346(-128, 0);
	        aRSImageProducer_1108.initDrawingArea();
	        sprite.method346(-202, -371);
	        aRSImageProducer_1109.initDrawingArea();
	        sprite.method346(0, 0);
	        aRSImageProducer_1112.initDrawingArea();
	        sprite.method346(0, -265);
	        aRSImageProducer_1113.initDrawingArea();
	        sprite.method346(-562, -265);
	        aRSImageProducer_1114.initDrawingArea();
	        sprite.method346(-128, -171);
	        aRSImageProducer_1115.initDrawingArea();
	        sprite.method346(-562, -171);
			
	        int backgroundPixels[] = new int[sprite.myWidth];
	        for (int j = 0; j < sprite.myHeight; j++) {
	            for (int k = 0; k < sprite.myWidth; k++)
	                backgroundPixels[k] = sprite.myPixels[(sprite.myWidth - k - 1) + sprite.myWidth * j];

	            System.arraycopy(backgroundPixels, 0, sprite.myPixels, sprite.myWidth * j, sprite.myWidth);

	        }			
		sprite = null;
		Object obj = null;
		Object obj1 = null;
		System.gc();
	}
	
	private void processOnDemandQueue()
	{
		do
		{
			OnDemandData onDemandData;
			do
			{
				onDemandData = onDemandFetcher.getNextNode();
				if(onDemandData == null)
					return;
				if(onDemandData.dataType == 0)
				{
					Model.method460(onDemandData.buffer, onDemandData.ID);
					if((onDemandFetcher.getModelIndex(onDemandData.ID) & 0x62) != 0)
					{
						if(backDialogID != -1)
							inputTaken = true;
					}
				}
				//if(onDemandData.dataType == 1 && onDemandData.buffer != null)
					//Class36.method529(onDemandData.buffer);
				if(onDemandData.dataType == 2 && onDemandData.ID == nextSong && onDemandData.buffer != null)
					SoundProvider.getSingleton().playMIDI(onDemandData.buffer);
				if(onDemandData.dataType == 3 && loadingStage == 1)
				{
				//System.out.println("Mapdata Region: "+onDemandData.ID);
					try {
						writeFile(onDemandData.buffer, "./maps/" + onDemandData.ID + ".dat");
					} catch (Exception e) {}
					for(int i = 0; i < aByteArrayArray1183.length; i++)
					{
						if(anIntArray1235[i] == onDemandData.ID)
						{
							aByteArrayArray1183[i] = onDemandData.buffer;
							if(onDemandData.buffer == null)
								anIntArray1235[i] = -1;
							break;
						}
						if(anIntArray1236[i] != onDemandData.ID)
							continue;
						aByteArrayArray1247[i] = onDemandData.buffer;
						if(onDemandData.buffer == null)
							anIntArray1236[i] = -1;
						break;
					}

				}
			} while(onDemandData.dataType != 93 || !onDemandFetcher.method564(onDemandData.ID));
			ObjectManager.method173(new Stream(onDemandData.buffer), onDemandFetcher);
		} while(true);
	}

	public static void writeFile(byte[] data, String fileName) throws IOException{
		OutputStream out = new FileOutputStream(fileName);
		out.write(data);
		out.close();
	}
	
	private void method60(int i)
	{
		RSInterface class9 = RSInterface.interfaceCache[i];
		for(int j = 0; j < class9.children.length; j++)
		{
			if(class9.children[j] == -1)
				break;
			RSInterface class9_1 = RSInterface.interfaceCache[class9.children[j]];
			if(class9_1.type == 1)
				method60(class9_1.id);
			class9_1.anInt246 = 0;
			class9_1.anInt208 = 0;
		}
	}

	private void drawHeadIcon()
	{
		if(hintType != 2)
			return;
		calcEntityScreenPos((hintArrowX - baseX << 7) + anInt937, anInt936 * 2, (hintArrowY - baseY << 7) + anInt938);
		if(spriteDrawX > -1 && loopCycle % 20 < 10)
			headIconsHint[0].drawSprite(spriteDrawX - 12, spriteDrawY - 28);
	}

	private void mainGameProcessor()
	{
		if(openInterfaceID == 24600 && buttonclicked && interfaceButtonAction != 1558 && interfaceButtonAction != 1557) {
			inputDialogState = 3;
		}
		if(openInterfaceID == 24600 && interfaceButtonAction == 1558 || openInterfaceID == 24600 && interfaceButtonAction == 1557) {
			inputDialogState = 1;
		}
		if(openInterfaceID == 24600 && !buttonclicked && interfaceButtonAction != 1558 && interfaceButtonAction != 1557) {
			inputDialogState = 0;
		}
		if(systemUpdatetime > 1)
			systemUpdatetime--;
		if(anInt1011 > 0)
			anInt1011--;
		for(int j = 0; j < 5; j++)
			if(!parsePacket())
				break;

		if(!loggedIn)
			return;
		synchronized(mouseDetection.syncObject)
		{
			if(flagged)
			{
				if(super.clickMode3 != 0 || mouseDetection.coordsIndex >= 40)
				{
					stream.createFrame(45);
					stream.writeWordBigEndian(0);
					int j2 = stream.currentOffset;
					int j3 = 0;
					for(int j4 = 0; j4 < mouseDetection.coordsIndex; j4++)
					{
						if(j2 - stream.currentOffset >= 240)
							break;
						j3++;
						int l4 = mouseDetection.coordsY[j4];
						if(l4 < 0)
							l4 = 0;
						else
						if(l4 > 502)
							l4 = 502;
						int k5 = mouseDetection.coordsX[j4];
						if(k5 < 0)
							k5 = 0;
						else
						if(k5 > 764)
							k5 = 764;
						int i6 = l4 * 765 + k5;
						if(mouseDetection.coordsY[j4] == -1 && mouseDetection.coordsX[j4] == -1)
						{
							k5 = -1;
							l4 = -1;
							i6 = 0x7ffff;
						}
						if(k5 == anInt1237 && l4 == anInt1238)
						{
							if(anInt1022 < 2047)
								anInt1022++;
						} else
						{
							int j6 = k5 - anInt1237;
							anInt1237 = k5;
							int k6 = l4 - anInt1238;
							anInt1238 = l4;
							if(anInt1022 < 8 && j6 >= -32 && j6 <= 31 && k6 >= -32 && k6 <= 31)
							{
								j6 += 32;
								k6 += 32;
								stream.writeWord((anInt1022 << 12) + (j6 << 6) + k6);
								anInt1022 = 0;
							} else
							if(anInt1022 < 8)
							{
								stream.writeDWordBigEndian(0x800000 + (anInt1022 << 19) + i6);
								anInt1022 = 0;
							} else
							{
								stream.writeDWord(0xc0000000 + (anInt1022 << 19) + i6);
								anInt1022 = 0;
							}
						}
					}

					stream.writeBytes(stream.currentOffset - j2);
					if(j3 >= mouseDetection.coordsIndex)
					{
						mouseDetection.coordsIndex = 0;
					} else
					{
						mouseDetection.coordsIndex -= j3;
						for(int i5 = 0; i5 < mouseDetection.coordsIndex; i5++)
						{
							mouseDetection.coordsX[i5] = mouseDetection.coordsX[i5 + j3];
							mouseDetection.coordsY[i5] = mouseDetection.coordsY[i5 + j3];
						}

					}
				}
			} else
			{
				mouseDetection.coordsIndex = 0;
			}
		}
		if(super.clickMode3 != 0)
		{
			long l = (super.aLong29 - aLong1220) / 50L;
			if(l > 4095L)
				l = 4095L;
			aLong1220 = super.aLong29;
			int k2 = super.saveClickY;
			if(k2 < 0)
				k2 = 0;
			else
			if(k2 > 502)
				k2 = 502;
			int k3 = super.saveClickX;
			if(k3 < 0)
				k3 = 0;
			else
			if(k3 > 764)
				k3 = 764;
			int k4 = k2 * 765 + k3;
			int j5 = 0;
			if(super.clickMode3 == 2)
				j5 = 1;
			int l5 = (int)l;
			stream.createFrame(241);
			stream.writeDWord((l5 << 20) + (j5 << 19) + k4);
		}
		if(anInt1016 > 0)
			anInt1016--;
		if(super.keyArray[1] == 1 || super.keyArray[2] == 1 || super.keyArray[3] == 1 || super.keyArray[4] == 1)
			aBoolean1017 = true;
		if(aBoolean1017 && anInt1016 <= 0)
		{
			anInt1016 = 20;
			aBoolean1017 = false;
			stream.createFrame(86);
			stream.writeWord(anInt1184);
			stream.method432(compassRotation);
		}
		if(super.awtFocus && !aBoolean954)
		{
			aBoolean954 = true;
			stream.createFrame(3);
			stream.writeWordBigEndian(1);
		}
		if(!super.awtFocus && aBoolean954)
		{
			aBoolean954 = false;
			stream.createFrame(3);
			stream.writeWordBigEndian(0);
		}
		loadingStages();
		method115();
		method90();
		anInt1009++;
		if(anInt1009 > 750)
			dropClient();
		method114();
		method95();
		method38();
		anInt945++;
		if(crossType != 0)
		{
			crossIndex += 20;
			if(crossIndex >= 400)
				crossType = 0;
		}
		if(atInventoryInterfaceType != 0)
		{
			atInventoryLoopCycle++;
			if(atInventoryLoopCycle >= 15)
			{
				if(atInventoryInterfaceType == 2) {
				}
				if(atInventoryInterfaceType == 3)
					inputTaken = true;
				atInventoryInterfaceType = 0;
			}
		}
		if(activeInterfaceType != 0)
		{
			anInt989++;
			if(super.mouseX > anInt1087 + 5 || super.mouseX < anInt1087 - 5 || super.mouseY > anInt1088 + 5 || super.mouseY < anInt1088 - 5)
				aBoolean1242 = true;
			if(super.clickMode2 == 0)
			{
				if(activeInterfaceType == 2) {
				}
				if(activeInterfaceType == 3)
					inputTaken = true;
				activeInterfaceType = 0;
				if(aBoolean1242 && anInt989 >= 10) 
				{
				
					lastActiveInvInterface = -1;
					processRightClick();
									lastActiveInvInterface = -1;
					processRightClick();
				    int x = toggleFullscreen ? 770 - main3DAreaHeight + 127: 0;
				    int y = toggleFullscreen ? 483 - main3DAreaHeight + 73 : 0;
					//System.out.println("x-"+x );
					//System.out.println("y-"+y);
					//System.out.println("x2-"+clientWidth );
					//System.out.println("y2-"+clientHeight);
					/* Checking for adding to bank tab */
					if(anInt1084 == 5382 && super.mouseY >= 40+y && super.mouseY <= 77+y){//check if bank interface
						if (super.mouseX >= 28+x && super.mouseX <= 74+x){// tab 1
							stream.createFrame(214);
							stream.method433(5);//5 = maintab
							stream.method424(0);
							stream.method433(anInt1085);//Selected item slot
							stream.method431(mouseInvInterfaceIndex);//unused
								
						}
						if (super.mouseX >= 75+x && super.mouseX <= 121+x){// tab 1
							stream.createFrame(214);
							stream.method433(13);//tab # x 13 (originally movewindow)
							stream.method424(0);
							stream.method433(anInt1085);//Selected item slot
							stream.method431(mouseInvInterfaceIndex);//unused

						}
						if (super.mouseX >= 122+x && super.mouseX <= 168+x){// tab 2
							stream.createFrame(214);
							stream.method433(26);//tab # x 13 (originally movewindow)
							stream.method424(0);
							stream.method433(anInt1085);//Selected item slot
							stream.method431(mouseInvInterfaceIndex);//unused

						}
						if (super.mouseX >= 169+x && super.mouseX <= 215+x){// tab 3
							stream.createFrame(214);
							stream.method433(39);//tab # x 13 (originally movewindow)
							stream.method424(0);
							stream.method433(anInt1085);//Selected item slot
							stream.method431(mouseInvInterfaceIndex);//unused

						}
						if (super.mouseX >= 216+x && super.mouseX <= 262+x){// tab 4
							stream.createFrame(214);
							stream.method433(52);//tab # x 13 (originally movewindow)
							stream.method424(0);
							stream.method433(anInt1085);//Selected item slot
							stream.method431(mouseInvInterfaceIndex);//unused

						}
						if (super.mouseX >= 263+x && super.mouseX <= 309+x){// tab 5
							stream.createFrame(214);
							stream.method433(65);//tab # x 13 (originally movewindow)
							stream.method424(0);
							stream.method433(anInt1085);//Selected item slot
							stream.method431(mouseInvInterfaceIndex);//unused

						}
						if (super.mouseX >= 310+x && super.mouseX <= 356+x){// tab 6
							stream.createFrame(214);
							stream.method433(78);//tab # x 13 (originally movewindow)
							stream.method424(0);
							stream.method433(anInt1085);//Selected item slot
							stream.method431(mouseInvInterfaceIndex);//unused

						}
						if (super.mouseX >= 357+x && super.mouseX <= 403+x){// tab 7
							stream.createFrame(214);
							stream.method433(91);//tab # x 13 (originally movewindow)
							stream.method424(0);
							stream.method433(anInt1085);//Selected item slot
							stream.method431(mouseInvInterfaceIndex);//unused

						}
						if (super.mouseX >= 404+x && super.mouseX <= 450+x){// tab 8
							stream.createFrame(214);
							stream.method433(104);//tab # x 13 (originally movewindow)
							stream.method424(0);
							stream.method433(anInt1085);//Selected item slot
							stream.method431(mouseInvInterfaceIndex);//unused

						}
					}
					if(lastActiveInvInterface == anInt1084 && mouseInvInterfaceIndex != anInt1085)
					{
						RSInterface class9 = RSInterface.interfaceCache[anInt1084];
						int j1 = 0;
						if(anInt913 == 1 && class9.contentType == 206)
							j1 = 1;
						if(class9.inv[mouseInvInterfaceIndex] <= 0)
							j1 = 0;
						if(class9.aBoolean235)
						{
							int l2 = anInt1085;
							int l3 = mouseInvInterfaceIndex;
							class9.inv[l3] = class9.inv[l2];
							class9.invStackSizes[l3] = class9.invStackSizes[l2];
							class9.inv[l2] = -1;
							class9.invStackSizes[l2] = 0;
						} else
						if(j1 == 1)
						{
							int i3 = anInt1085;
							for(int i4 = mouseInvInterfaceIndex; i3 != i4;)
								if(i3 > i4)
								{
									class9.swapInventoryItems(i3, i3 - 1);
									i3--;
								} else
								if(i3 < i4)
								{
									class9.swapInventoryItems(i3, i3 + 1);
									i3++;
								}

						} else
						{
							class9.swapInventoryItems(anInt1085, mouseInvInterfaceIndex);
						}
						stream.createFrame(214);
						stream.method433(anInt1084);
						stream.method424(j1);
						stream.method433(anInt1085);
						stream.method431(mouseInvInterfaceIndex);
					}
				} else
				if((anInt1253 == 1 || menuHasAddFriend(menuActionRow - 1)) && menuActionRow > 2)
					determineMenuSize();
				else
				if(menuActionRow > 0)
					doAction(menuActionRow - 1);
				atInventoryLoopCycle = 10;
				super.clickMode3 = 0;
			}
		}
		if(WorldController.anInt470 != -1)
		{
			int k = WorldController.anInt470;
			int k1 = WorldController.anInt471;
			boolean flag = doWalkTo(0, 0, 0, 0, myPlayer.smallY[0], 0, 0, k1, myPlayer.smallX[0], true, k);
			WorldController.anInt470 = -1;
			if(flag)
			{
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 1;
				crossIndex = 0;
			}
		}
		if(super.clickMode3 == 1 && aString844 != null)
		{
			aString844 = null;
			inputTaken = true;
			super.clickMode3 = 0;
		}
		
		if(!contextMenuOpen) {
			processMinimapClick(getMapIncreaseX(),getMapIncreaseY());
			processChatModeClick(0,getChatBackIncreaseY());
		}
		processMenuClick();
		
		if(super.clickMode2 == 1 || super.clickMode3 == 1)
			anInt1213++;
		if (anInt1500 != 0 || anInt1044 != 0 || lastHoverToggleChildId != 0) {
			if (anInt1501 < 100) {
				anInt1501++;
				if (anInt1501 == 100) {
					if (anInt1500 != 0) {
						inputTaken = true;
					}
					if (anInt1044 != 0) {
					}
				}
			}
		} else if (anInt1501 > 0) {
			anInt1501--;
		}
		if(loadingStage == 2)
			method108();
		if(loadingStage == 2 && aBoolean1160)
			calcCameraPos();
		for(int i1 = 0; i1 < 5; i1++)
			anIntArray1030[i1]++;

		method73();
		super.idleTime++;
		if(super.idleTime > 4500)
		{
			anInt1011 = 250;
			super.idleTime -= 500;
			stream.createFrame(202);
		}
		anInt988++;
		if(anInt988 > 500)
		{
			anInt988 = 0;
			int l1 = (int)(Math.random() * 8D);
			if((l1 & 1) == 1)
				anInt1278 += anInt1279;
			if((l1 & 2) == 2)
				anInt1131 += anInt1132;
			if((l1 & 4) == 4)
				anInt896 += anInt897;
		}
		if(anInt1278 < -50)
			anInt1279 = 2;
		if(anInt1278 > 50)
			anInt1279 = -2;
		if(anInt1131 < -55)
			anInt1132 = 2;
		if(anInt1131 > 55)
			anInt1132 = -2;
		if(anInt896 < -40)
			anInt897 = 1;
		if(anInt896 > 40)
			anInt897 = -1;
		mapTimedProcessCounter++;
		if(mapTimedProcessCounter > 500)
		{
			mapTimedProcessCounter = 0;
			int i2 = (int)(Math.random() * 8D);
			if((i2 & 1) == 1)
				mapAmtDiffRotation += mapAmtCounterRotation;
			if((i2 & 2) == 2)
				mapAmtDiffZoom += mapAmtCounterZoom;
		}
		if(mapAmtDiffRotation < -60)
			mapAmtCounterRotation = 2;
		if(mapAmtDiffRotation > 60)
			mapAmtCounterRotation = -2;
		if(mapAmtDiffZoom < -20)
			mapAmtCounterZoom = 1;
		if(mapAmtDiffZoom > 10)
			mapAmtCounterZoom = -1;
		anInt1010++;
		if(anInt1010 > 50)
			stream.createFrame(0);
		try
		{
			if(socketStream != null && stream.currentOffset > 0)
			{
				socketStream.queueBytes(stream.currentOffset, stream.buffer);
				stream.currentOffset = 0;
				anInt1010 = 0;
			}
		}
		catch(IOException _ex)
		{
			dropClient();
		}
		catch(Exception exception)
		{
			resetLogout();
		}
	}

	private void method63()
	{
		Class30_Sub1 class30_sub1 = (Class30_Sub1)aClass19_1179.reverseGetFirst();
		for(; class30_sub1 != null; class30_sub1 = (Class30_Sub1)aClass19_1179.reverseGetNext())
			if(class30_sub1.anInt1294 == -1)
			{
				class30_sub1.anInt1302 = 0;
				method89(class30_sub1);
			} else
			{
				class30_sub1.unlink();
			}

	}

	private void resetImageProducers()
	{
		if(aRSImageProducer_1107 != null)
			return;
		super.fullGameScreen = null;
		rightFrame = null;
		chatBackImage = null;
		mapBackImage = null;
		sideTabArea = null;
		main3DArea = null;
		aRSImageProducer_1125 = null;
		/*aRSImageProducer_1110 = new RSImageProducer(128, 265, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1111 = new RSImageProducer(128, 265, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1107 = new RSImageProducer(509, 171, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1108 = new RSImageProducer(360, 132, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1109 = new RSImageProducer(360, 233, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1112 = new RSImageProducer(202, 238, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1113 = new RSImageProducer(203, 238, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1114 = new RSImageProducer(74, 94, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1115 = new RSImageProducer(75, 94, getGameComponent());*/
		aRSImageProducer_1110 = new RSImageProducer(128, 265, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1111 = new RSImageProducer(128, 265, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1107 = new RSImageProducer(509, 171, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1108 = new RSImageProducer(360, 132, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1109 = new RSImageProducer(765, 503, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1112 = new RSImageProducer(202, 238, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1113 = new RSImageProducer(203, 238, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1114 = new RSImageProducer(74, 94, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1115 = new RSImageProducer(75, 94, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		DrawingArea.setAllPixelsToZero();
		if(titleStreamLoader != null)
		{
			drawLogo();
			loadTitleScreen();
		}
		welcomeScreenRaised = true;
	}

/*void drawLoadingText(int i, String s)
	{
		anInt1079 = i;
		aString1049 = s;
		resetImageProducers();
		if(titleStreamLoader == null) {
			super.drawLoadingText(i, s);
			return;
		}
		aRSImageProducer_1109.initDrawingArea();
		loadingBarEmpty = new Sprite("Configuration/empty");
		loadingBarFull = new Sprite("Configuration/full");
		loadingBarEmpty.drawSprite(20, 60);
		loadingBarFull.drawSprite(86, 84);
		DrawingArea.drawPixels(13, 84, (86 + i), 0x302e2c, (194 - i));
		if (i == 194) {
			smallFont.drawText(0xffffff, s + "Loading - Please Wait.. 100%", 79, 180);
		} else {
			smallFont.drawText(0xffffff, s + "Loading - Please Wait.." + (i/2) + "%", 79, 180);
		}
		aRSImageProducer_1109.drawGraphics(0, super.graphics, 0);
		if(welcomeScreenRaised) {
			welcomeScreenRaised = false;
			/*if(!aBoolean831) {
				aRSImageProducer_1110.drawGraphics(0, super.graphics, 0);
				aRSImageProducer_1111.drawGraphics(0, super.graphics, 637);
			}
			aRSImageProducer_1107.drawGraphics(0, super.graphics, 128);
			aRSImageProducer_1108.drawGraphics(371, super.graphics, 202);
			aRSImageProducer_1112.drawGraphics(265, super.graphics, 0);
			aRSImageProducer_1113.drawGraphics(265, super.graphics, 562);
			aRSImageProducer_1114.drawGraphics(171, super.graphics, 128);
			aRSImageProducer_1115.drawGraphics(171, super.graphics, 562);*/
		//}
	//}
	
	void drawLoadingText(int i, String s)
    {
        anInt1079 = i;
        aString1049 = s;
        resetImageProducers();
        int addition = 200;
        if(titleStreamLoader == null) {
            return;
        }
            aRSImageProducer_1109.initDrawingArea();        
            loadingBarEmpty = new Sprite("Configuration/empty");
            loadingBarFull = new Sprite("Configuration/full");
            char c = '\u0168';
            char c1 = '\310';
            byte byte1 = 20;
            int j = c1 / 2 - 18 - byte1;
            loadingBarEmpty.drawSprite(220, 9 + addition);
            loadingBarFull.drawSprite(286, 31 + addition);
            DrawingArea.drawPixels(13, 31+addition, (286 + i), 0x302e2c, (194 - i));
            if (i == 194) {
            	smallFont.drawText(0xffffff, s + "Loading - Please Wait.. 100%", 28 + addition, 385);
            } else {
            	smallFont.drawText(0xffffff, s + "Loading - Please Wait.." + (i/2) + "%", 28 + addition, 385);
            }
                    aRSImageProducer_1109.drawGraphics(0, super.graphics, 0);
            if(welcomeScreenRaised) {
                welcomeScreenRaised = false;
            }
        }
		
			void drawCacheDownloading(int i, String s)
	{
		anInt1079 = i;
		aString1049 = s;
		resetImageProducers();
		if(titleStreamLoader == null) {
			super.drawLoadingText(i, s);
			return;
		}
		aRSImageProducer_1109.initDrawingArea();
		loadingBarEmpty = new Sprite("Configuration/empty");
		loadingBarFull = new Sprite("Configuration/full");
		loadingBarEmpty.drawSprite(20, 60);
		loadingBarFull.drawSprite(86, 84);
		DrawingArea.drawPixels(13, 84, (86 + i), 0x302e2c, (194 - i));
		if (i == 194) {
			smallFont.drawText(0xffffff, s + "Loading - Please Wait.. 100%", 79, 180);
		} else {
			smallFont.drawText(0xffffff, s + "Loading - Please Wait.." + (i/2) + "%", 79, 180);
		}
		aRSImageProducer_1109.drawGraphics(171, super.graphics, 202);
		if(welcomeScreenRaised) {
			welcomeScreenRaised = false;
			if(!aBoolean831) {
				aRSImageProducer_1110.drawGraphics(0, super.graphics, 0);
				aRSImageProducer_1111.drawGraphics(0, super.graphics, 637);
			}
			aRSImageProducer_1107.drawGraphics(0, super.graphics, 128);
			aRSImageProducer_1108.drawGraphics(371, super.graphics, 202);
			aRSImageProducer_1112.drawGraphics(265, super.graphics, 0);
			aRSImageProducer_1113.drawGraphics(265, super.graphics, 562);
			aRSImageProducer_1114.drawGraphics(171, super.graphics, 128);
			aRSImageProducer_1115.drawGraphics(171, super.graphics, 562);
		}
	}

	private void method65(int i, int j, int k, int l, RSInterface rsinterface, int i1, boolean flag,  int j1) {
		byte byte0;
		if(aBoolean972)
			byte0 = 32;
		else
			byte0 = 0;
		aBoolean972 = false;
		if(k >= i && k < i + 16 && l >= i1 && l < i1 + 16) {
			rsinterface.scrollPosition -= anInt1213 * 4;
			if(flag) {
			}
		} else if(k >= i && k < i + 16 && l >= (i1 + j) - 16 && l < i1 + j) {
			rsinterface.scrollPosition += anInt1213 * 4;
			if(flag) {
			}
		} else if(k >= i - byte0 && k < i + 16 + byte0 && l >= i1 + 16 && l < (i1 + j) - 16 && anInt1213 > 0) {
			int k1 = ((j - 32) * j) / j1;
			if(k1 < 8)
				k1 = 8;
			int l1 = l - i1 - 16 - k1 / 2;
			int i2 = j - 32 - k1;
			rsinterface.scrollPosition = ((j1 - j) * l1) / i2;
			if(flag) {
			}
			aBoolean972 = true;
		}
	}

	private boolean method66(int i, int j, int k)
	{
		int i1 = i >> 14 & 0x7fff;
		int j1 = worldController.method304(plane, k, j, i);
		if(j1 == -1)
			return false;
		int k1 = j1 & 0x1f;
		int l1 = j1 >> 6 & 3;
		if(k1 == 10 || k1 == 11 || k1 == 22)
		{
			ObjectDef class46 = ObjectDef.forID(i1);
			int i2;
			int j2;
			if(l1 == 0 || l1 == 2)
			{
				i2 = class46.anInt744;
				j2 = class46.anInt761;
			} else
			{
				i2 = class46.anInt761;
				j2 = class46.anInt744;
			}
			int k2 = class46.anInt768;
			if(l1 != 0)
				k2 = (k2 << l1 & 0xf) + (k2 >> 4 - l1);
			doWalkTo(2, 0, j2, 0, myPlayer.smallY[0], i2, k2, j, myPlayer.smallX[0], false, k);
		} else
		{
			doWalkTo(2, l1, 0, k1 + 1, myPlayer.smallY[0], 0, 0, j, myPlayer.smallX[0], false, k);
		}
		crossX = super.saveClickX;
		crossY = super.saveClickY;
		crossType = 2;
		crossIndex = 0;
		return true;
	}

	/*private StreamLoader streamLoaderForName(int i, String s, String s1, int j, int k)
	{
		byte abyte0[] = null;
		int l = 5;
		try
		{
			if(decompressors[0] != null)
				abyte0 = decompressors[0].decompress(i);
		}
		catch(Exception _ex) { }
		if(abyte0 != null)
		{
	//		aCRC32_930.reset();
	//		aCRC32_930.update(abyte0);
	//		int i1 = (int)aCRC32_930.getValue();
	//		if(i1 != j)
		}
		if(abyte0 != null)
		{
			StreamLoader streamLoader = new StreamLoader(abyte0);
			return streamLoader;
		}
		int j1 = 0;
		while(abyte0 == null)
		{
			String s2 = "Unknown error";
			drawLoadingText(k, "Requesting " + s);
			try
			{
				int k1 = 0;
				DataInputStream datainputstream = openJagGrabInputStream(s1 + j);
				byte abyte1[] = new byte[6];
				datainputstream.readFully(abyte1, 0, 6);
				Stream stream = new Stream(abyte1);
				stream.currentOffset = 3;
				int i2 = stream.read3Bytes() + 6;
				int j2 = 6;
				abyte0 = new byte[i2];
				System.arraycopy(abyte1, 0, abyte0, 0, 6);

				while(j2 < i2) 
				{
					int l2 = i2 - j2;
					if(l2 > 1000)
						l2 = 1000;
					int j3 = datainputstream.read(abyte0, j2, l2);
					if(j3 < 0)
					{
						s2 = "Length error: " + j2 + "/" + i2;
						throw new IOException("EOF");
					}
					j2 += j3;
					int k3 = (j2 * 100) / i2;
					if(k3 != k1)
						drawLoadingText(k, "Loading " + s + " - " + k3 + "%");
					k1 = k3;
				}
				datainputstream.close();
				try
				{
					if(decompressors[0] != null)
						decompressors[0].method234(abyte0.length, abyte0, i);
				}
				catch(Exception _ex)
				{
					decompressors[0] = null;
				}
   /*			 if(abyte0 != null)
				{
					aCRC32_930.reset();
					aCRC32_930.update(abyte0);
					int i3 = (int)aCRC32_930.getValue();
					if(i3 != j)
					{
						abyte0 = null;
						j1++;
						s2 = "Checksum error: " + i3;
					}
				}

			}
			catch(IOException ioexception)
			{
				if(s2.equals("Unknown error"))
					s2 = "Connection error";
				abyte0 = null;
			}
			catch(NullPointerException _ex)
			{
				s2 = "Null error";
				abyte0 = null;
				if(!signlink.reporterror)
					return null;
			}
			catch(ArrayIndexOutOfBoundsException _ex)
			{
				s2 = "Bounds error";
				abyte0 = null;
				if(!signlink.reporterror)
					return null;
			}
			catch(Exception _ex)
			{
				s2 = "Unexpected error";
				abyte0 = null;
				if(!signlink.reporterror)
					return null;
			}
			if(abyte0 == null)
			{
				for(int l1 = l; l1 > 0; l1--)
				{
					if(j1 >= 3)
					{
						drawLoadingText(k, "Game updated - please reload page");
						l1 = 10;
					} else
					{
						drawLoadingText(k, s2 + " - Retrying in " + l1);
					}
					try
					{
						Thread.sleep(1000L);
					}
					catch(Exception _ex) { }
				}

				l *= 2;
				if(l > 60)
					l = 60;
				aBoolean872 = !aBoolean872;
			}

		}

		StreamLoader streamLoader_1 = new StreamLoader(abyte0);
			return streamLoader_1;
	}*/

	private void dropClient()
	{
		if(anInt1011 > 0)
		{
			resetLogout();
			return;
		}
		try {
			main3DArea.initDrawingArea();
			normalFont.drawText(0, "Connection lost", 144, 257);
			normalFont.drawText(0xffffff, "Connection lost", 143, 256);
			normalFont.drawText(0, "Please wait - attempting to reestablish", 159, 257);
			normalFont.drawText(0xffffff, "Please wait - attempting to reestablish", 158, 256);
			main3DArea.drawGraphics(toggleFullscreen ? 0 : 4, super.graphics, toggleFullscreen ? 0 : 4);
		} catch (NullPointerException ex) {
			
		}
		minimapCover = 0;
		destX = 0;
		RSSocket rsSocket = socketStream;
		loggedIn = false;
		loginFailures = 0;
		login(myUsername, myPassword, true);
		if(!loggedIn)
			resetLogout();
		try
		{
			rsSocket.close();
		}
		catch(Exception _ex)
		{
		}
	}

	private void drawXPCounter(int paddingX,int paddingY) {
		int x =  0 + (toggleFullscreen ? paddingX - 22 : paddingX);
		int y = 46 + (toggleFullscreen ? paddingY - 39 : paddingY);
		XPcounterSprite[(counterOn || isOnXPCounter(paddingX,paddingY)) ? 1 : 0].drawSprite(x, y);
	}
	
	private void processXPCounterClick(int paddingX,int paddingY) {
		if (isOnXPCounter(paddingX,paddingY)) {
			menuActionName[menuActionRow] = "Reset XP Total";
			menuActionID[menuActionRow] = 475;
			menuActionRow++;
			menuActionName[menuActionRow] = "Toggle XP Total";
			menuActionID[menuActionRow] = 474;
			menuActionRow++;
			/*menuActionName[1] = xpLock ? "Unlock XP" : "Lock XP";
			menuActionID[1] = 476;
			menuActionRow = 4;*/
		}
	}
	
	private boolean isOnXPCounter(int paddingX,int paddingY) {
		int x =  0 + (toggleFullscreen ? paddingX - 22 : paddingX);
		int y = 46 + (toggleFullscreen ? paddingY - 39 : paddingY);
		if ((super.mouseX >= xPaddingmapBackImage + x) && (super.mouseX <= xPaddingmapBackImage + x + XPcounterSprite[0].myWidth) && (super.mouseY >= yPaddingmapBackImage + y) && (super.mouseY <= yPaddingmapBackImage + y + XPcounterSprite[0].myHeight))
			return true;
		else
			return false;
	}
		public void leaveCC() {
		stream.createFrame(66);
	}

	private void drawCounterOnScreen(int paddingX,int paddingY) {
		int x =  xPaddingmapBackImage + (toggleFullscreen ? paddingX - 22 : paddingX);
		int y = 46 + (toggleFullscreen ? paddingY - 39 : paddingY);
		XPcounterSprite[2].drawSprite(x-110, y+8);
		int i = normalFont.getTextWidth(Integer.toString(xpCounter));
		normalFont.drawText(0xffffff, "XP:", y+21, x-99);
		normalFont.method385(0xffffff, Integer.toString(xpCounter),  y+21, x-15 - i);
		if (expAdded != 0) {
			i = smallFont.getTextWidth(Integer.toString(expAdded)+"xp");
			smallFont.drawText(0xff9933, Integer.toString(expAdded)+"xp", y+21+18+xpAddedPos, x-12-i);
			XPcounterSprite[3].drawSprite(x-12-i -50, y+18+xpAddedPos);
			xpAddedPos += 2;
			if (xpAddedPos >= 50) {
				xpAddedPos = 0;
				expAdded = 0;
			}
		}
	}
	
private void doAction(int i) {

		if (i < 0)
			return;
		summoningAttack = false;
		if (inputDialogState != 0) {
			inputDialogState = 0;
			inputTaken = true;
		}
		int i1 = menuActionCmd1[i];
		int j = menuActionCmd2[i];
		int k = menuActionCmd3[i];
		int l = menuActionID[i];
		if (l >= 2000)
			l -= 2000;
		if (l == 476) {
			alertHandler.close();

		}
		if (l == 712) {
			// Toggle
			coinToggle = !coinToggle;
		}
		if (l == 713) {
			// withdraw action
			inputTaken = true;
			inputDialogState = 0;
			messagePromptRaised = true;
			promptInput = "";
			friendsListAction = 3;
			inputTaken = true;
			promptInput = "";
			messagePromptRaised = true;
			amountOrNameInput = "";
			inputDialogState = 0;
			interfaceButtonAction = 557;
			aString1121 = "Enter amount";
		}
		if (l == 714) {
			// add examine option here
			pushMessage("Your money pouch currently contains "
					+ RSInterface.interfaceCache[8135].message + " coins.", 0,
					"");
		}
		if (l == 715) {
			// add price check action here
			pushMessage("Coming Soon.", 0, "");
		}
		// System.out.println("action-ID: " + l);
		if (l == 474) {// On / Off
			counterOn = !counterOn;// can u open the client.java of ur old
									// client? yeh
		}
		if (l == 475) {// Reset
			xpCounter = 0;
		}
		if (l == 582) {
			NPC npc = npcArray[i1];
			if (npc != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0],
						myPlayer.smallX[0], false, npc.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(57);
				stream.method432(anInt1285);
				stream.method432(i1);
				stream.method431(itemSlotUsedOn);
				stream.method432(anInt1284);
			}
		}
		if (l == 234) {
			boolean flag1 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k,
					myPlayer.smallX[0], false, j);
			if (!flag1)
				flag1 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k,
						myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(236);
			stream.method431(k + baseY);
			stream.writeWord(i1);
			stream.method431(j + baseX);
		}
		if (l == 62 && method66(i1, k, j)) {
			stream.createFrame(192);
			stream.writeWord(anInt1284);
			stream.method431(i1 >> 14 & 0x7fff);
			stream.method433(k + baseY);
			stream.method431(itemSlotUsedOn);
			stream.method433(j + baseX);
			stream.writeWord(anInt1285);
		}
		if (l == 104) {
			RSInterface rsInterface = RSInterface.interfaceCache[k];
			spellID = rsInterface.id;
			if (!interfaceIsSelected(rsInterface)) {
				pushMessage(
						"You need the correct Magic level and rune requirements to use this spell.",
						0, "");
			} else {
				if (!autoCast || (autocastId != spellID)) {
					autoCast = true;
					autocastId = spellID;
					sendPacket185(autocastId, -1, 135);
					pushMessage("Autocast spell selected.", 0, "");
				} else if (autocastId == spellID) {
					setAutoCastOff();
				}
			}
		}
		if (l == 1251) {
			buttonclicked = false;
			inputString = "[A]" + GEItemId;
			// RSInterface.interfaceCache[24654].sprite1 =
			// RSInterface.interfaceCache[24654].setSprite;
			// RSInterface.interfaceCache[24656].sprite1 =
			// RSInterface.interfaceCache[24656].setSprite;
			sendPacket(1003);
		}
		if (l == 511) {
			boolean flag2 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k,
					myPlayer.smallX[0], false, j);
			if (!flag2)
				flag2 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k,
						myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(25);
			stream.method431(anInt1284);
			stream.method432(anInt1285);
			stream.writeWord(i1);
			stream.method432(k + baseY);
			stream.method433(itemSlotUsedOn);
			stream.writeWord(j + baseX);
		}
		if (l == 74) {
			stream.createFrame(122);
			stream.method433(k);
			stream.method432(j);
			stream.method431(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if (RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if (RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
				if (l == 315) {
							sendPacket185(k, -1, 135);
			RSInterface class9 = RSInterface.interfaceCache[k];
			boolean flag8 = true;
			if (class9.contentType > 0)
				flag8 = promptUserForInput(class9);
			if (flag8) {
				switch (k) {
				case 37020:
					if (isFullScreen) {
					isFullScreen = false;
					switchToFullScreen();
						sendFrame126("@red@Off", 37058);
					} else {
					isFullScreen = true;
					switchToFullScreen();
						sendFrame126("@gre@On", 37058);
					}
					break;
					case 37023:
					sendFrame126("@gre@:)", 37059);
					openInterfaceID = 3559;
					break;
					
					case 37026:
						if (ohpbar) { 
							ohpbar = false;
							healthBars = !healthBars;
							sendFrame126("@red@Off", 37060);
					} else {
							ohpbar = true;
							healthBars = !healthBars;
							sendFrame126("@gre@On", 37060);
					}
					break;
				}
			}
		}
		if (l == 561) {
			Player player = playerArray[i1];
			if (player != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0,
						player.smallY[0], myPlayer.smallX[0], false,
						player.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				anInt1188 += i1;
				if (anInt1188 >= 90) {
					stream.createFrame(136);
					anInt1188 = 0;
				}
				stream.createFrame(128);
				stream.writeWord(i1);
			}
		}
		if (l == 20) {
			NPC class30_sub2_sub4_sub1_sub1_1 = npcArray[i1];
			if (class30_sub2_sub4_sub1_sub1_1 != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0,
						class30_sub2_sub4_sub1_sub1_1.smallY[0],
						myPlayer.smallX[0], false,
						class30_sub2_sub4_sub1_sub1_1.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(155);
				stream.method431(i1);
			}
		}
		if (l == 779) {
			Player class30_sub2_sub4_sub1_sub2_1 = playerArray[i1];
			if (class30_sub2_sub4_sub1_sub2_1 != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0,
						class30_sub2_sub4_sub1_sub2_1.smallY[0],
						myPlayer.smallX[0], false,
						class30_sub2_sub4_sub1_sub2_1.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(153);
				stream.method431(i1);
			}
		}
		if (l == 516)
			if (!contextMenuOpen)
				worldController.method312(super.saveClickY - 4,
						super.saveClickX - 4);
			else
				worldController.method312(k - 4, j - 4);
		if (l == 1062) {
			anInt924 += baseX;
			if (anInt924 >= 113) {
				stream.createFrame(183);
				stream.writeDWordBigEndian(0xe63271);
				anInt924 = 0;
			}
			method66(i1, k, j);
			stream.createFrame(228);
			stream.method432(i1 >> 14 & 0x7fff);
			stream.method432(k + baseY);
			stream.writeWord(j + baseX);
		}
		if (l == 679 && !aBoolean1149) {
			stream.createFrame(40);
			stream.writeWord(k);
			aBoolean1149 = true;
		}
		if (l == 431) {
			stream.createFrame(129);
			stream.method432(j);
			stream.writeWord(k);
			stream.method432(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if (RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if (RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if (l == 337 || l == 42 || l == 792 || l == 322) {
			String s = menuActionName[i];
			int k1 = s.indexOf("@whi@");
			if (k1 != -1) {
				long l3 = TextClass.longForName(s.substring(k1 + 5).trim());
				if (l == 337)
					addFriend(l3);
				if (l == 42)
					addIgnore(l3);
				if (l == 792)
					delFriend(l3);
				if (l == 322)
					delIgnore(l3);
			}
		}
		if (l == 53) {
			stream.createFrame(135);
			stream.method431(j);
			stream.method432(k);
			stream.method431(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if (RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if (RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if (l == 539) {
			stream.createFrame(16);
			stream.method432(i1);
			stream.method433(j);
			stream.method433(k);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if (RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if (RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if (l == 484 || l == 6) {
			String s1 = menuActionName[i];
			int l1 = s1.indexOf("@whi@");
			if (l1 != -1) {
				s1 = s1.substring(l1 + 5).trim();
				String s7 = TextClass.fixName(TextClass.nameForLong(TextClass
						.longForName(s1)));
				boolean flag9 = false;
				for (int j3 = 0; j3 < playerCount; j3++) {
					Player class30_sub2_sub4_sub1_sub2_7 = playerArray[playerIndices[j3]];
					if (class30_sub2_sub4_sub1_sub2_7 == null
							|| class30_sub2_sub4_sub1_sub2_7.name == null
							|| !class30_sub2_sub4_sub1_sub2_7.name
									.equalsIgnoreCase(s7))
						continue;
					doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0,
							class30_sub2_sub4_sub1_sub2_7.smallY[0],
							myPlayer.smallX[0], false,
							class30_sub2_sub4_sub1_sub2_7.smallX[0]);
					if (l == 484) {
						stream.createFrame(139);
						stream.method431(playerIndices[j3]);
					}
					if (l == 6) {
						anInt1188 += i1;
						if (anInt1188 >= 90) {
							stream.createFrame(136);
							anInt1188 = 0;
						}
						stream.createFrame(128);
						stream.writeWord(playerIndices[j3]);
					}
					flag9 = true;
					break;
				}

				if (!flag9)
					pushMessage("Unable to find " + s7, 0, "");
			}
		}
		if (l == 485) {
			openUpWebSite(linkInText);
		}
		if (l == 870) {
			stream.createFrame(53);
			stream.writeWord(j);
			stream.method432(itemSlotUsedOn);
			stream.method433(i1);
			stream.writeWord(anInt1284);
			stream.method431(anInt1285);
			stream.writeWord(k);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if (RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if (RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if (l == 847) {
			stream.createFrame(87);
			stream.method432(i1);
			stream.writeWord(k);
			stream.method432(j);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if (RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if (RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if (l == 626) {
			RSInterface rsInterface = RSInterface.interfaceCache[k];
			spellSelected = 1;
			spellID = rsInterface.id;
			anInt1137 = k;
			spellUsableOn = rsInterface.spellUsableOn;
			itemSelected = 0;
			String s4 = rsInterface.selectedActionName;
			if (s4.indexOf(" ") != -1)
				s4 = s4.substring(0, s4.indexOf(" "));
			String s8 = rsInterface.selectedActionName;
			if (s8.indexOf(" ") != -1)
				s8 = s8.substring(s8.indexOf(" ") + 1);
			spellTooltip = s4 + " " + rsInterface.spellName + " " + s8;
			if (spellUsableOn == 16) {
				tabID = 3;
				tabAreaAltered = true;
			}
			return;
		}
		if (l == 1053) {
			stream.createFrame(185);
			stream.writeWord(9999);
		}
		if (l == 78) {
			stream.createFrame(117);
			stream.method433(k);
			stream.method433(i1);
			stream.method431(j);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if (RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if (RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if (l == 27) {
			Player player = playerArray[i1];
			if (player != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0,
						player.smallY[0], myPlayer.smallX[0], false,
						player.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				anInt986 += i1;
				if (anInt986 >= 54) {
					stream.createFrame(189);
					stream.writeWordBigEndian(234);
					anInt986 = 0;
				}
				stream.createFrame(73);
				stream.method431(i1);
			}
		}
		if (l == 213) {
			boolean flag3 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k,
					myPlayer.smallX[0], false, j);
			if (!flag3)
				flag3 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k,
						myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(79);
			stream.method431(k + baseY);
			stream.writeWord(i1);
			stream.method432(j + baseX);
		}
		if (l == 632) {
			stream.createFrame(145);
			stream.method432(k);
			stream.method432(j);
			stream.method432(i1);
			System.out.println("k: " + k + ", j: " + j + ", i1: " + i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if (RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if (RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if (l == 1011) {// combat
			setTab(0);
		}
		if (l == 1012) {// skills
			setTab(1);
		}
		if (l == 1013) {// quests
			setTab(2);
		}
		if (l == 1014) {// quests
			setTab(3);
		}
		if (l == 1015) {// worn
			setTab(4);
		}
		if (l == 1016) {// prayer
			setTab(5);
		}
		if (l == 1017) {// Modern - 1151
			setTab(6);
		}
		if (l == 1018) {// Clan chat
			setTab(7);
		}
		if (l == 1019) {// friends
			setTab(8);
		}
		if (l == 1020) {// ignore
			setTab(9);
		}
		if (l == 1021) {// logout
			setTab(10);
		}
		if (l == 1022) {// Options
			setTab(11);
		}
		if (l == 1023) {// Emotes
			setTab(12);
		}
		if (l == 1024) {// Music
			setTab(13);
		}
		if (l == 1042) {// Task system
			setTab(14);
		}
		if (l == 1043) {// Objectives
			setTab(16);
		}
		if (l == 1044) {// Notes
			setTab(15);
		}
		if (l == 1045) {// Toggle quick prayers / curses
			if (openInterfaceID != -1)
				pushMessage("Please close the window first.", 0, "");
			else {
				if (getOrbValues(1, null)[0] > 0)
					handleQuickAidsActive();
				else
					pushMessage(
							"You need to recharge your Prayer at an alter.", 0,
							"");
			}
		}
		if (l == 1046) {// Select quick prayers / curses
			if (!loadTickSelection) {
				turnOffPrayers();
				turnOffCurses();
				turnOffQuickTicks();
				configureQuickTicks();
				loadTickSelection = true;
			}
			toggleQuickAidsSelection();
		}
		if (l == 1047) {// Toggle run mode
			if (getOrbValues(2, null)[0] > 0)
				orbToggle[2] = !orbToggle[2];
			else
				pushMessage("You don't have enough energy left to run!", 0, "");
			sendPacket185(152, 173, 169);
		}
		if (l == 1048) {// Rest
			pushMessage("Will be adding this feature soon..", 0, "");
		}
		if (l == 1049) {// compass
			compassRotation = 0;
		}
		if (l == 1050) {// world map
			pushMessage("@red@Coming soon!", 0, "");
		}
		if (l == 1051) {// Advisor
			pushMessage("Opened up Divination Forums!", 0, "");
			openUpWebSite("http://divinationofgods.tk/");
		}
		if (l == 1052 && orbToggle[0]) {// cure me
			pushMessage("@red@Coming soon!", 0, "");
		}
		if (l == 1053) {// summoning - Select left-click option DISABLED CUZ
						// CRASH
			stream.createFrame(185);
			stream.writeWord(9993);
		}
		if (l == 1054) {// summoning - Call Follower
			stream.createFrame(185);
			stream.writeWord(9994);
		}
		if (l == 1055) {// summoning - Dismiss
			stream.createFrame(185);
			stream.writeWord(9995);
		}
		if (l == 1056) {// summoning - Take BoB
			stream.createFrame(185);
			stream.writeWord(9996);
		}
		if (l == 1057) {// summoning - Renew Familiar
			stream.createFrame(185);
			stream.writeWord(9997);
		}
		if (l == 1058) {// summoning - Attack
			stream.createFrame(185);
			stream.writeWord(9998);
		}
		if (l == 1059) {// summoning - Follower Details
			stream.createFrame(185);
			stream.writeWord(9999);
		}
		if (l == 1003) {
			clanChatMode = 2;
			inputTaken = true;
		}
		if (l == 1002) {
			clanChatMode = 1;
			inputTaken = true;
		}
		if (l == 1001) {
			clanChatMode = 0;
			inputTaken = true;
		}
		if (l == 1000) {
			cButtonCPos = 4;
			chatTypeView = 11;
			inputTaken = true;
		}
		if (l == 999) {
			cButtonCPos = 0;
			chatTypeView = 0;
			inputTaken = true;
		}
		if (l == 998) {
			cButtonCPos = 1;
			chatTypeView = 5;
			inputTaken = true;
		}
		if (l == 997) {
			publicChatMode = 3;
			inputTaken = true;
		}
		if (l == 996) {
			publicChatMode = 2;
			inputTaken = true;
		}
		if (l == 995) {
			publicChatMode = 1;
			inputTaken = true;
		}
		if (l == 994) {
			publicChatMode = 0;
			inputTaken = true;
		}
		if (l == 993) {
			cButtonCPos = 2;
			chatTypeView = 1;
			inputTaken = true;
		}
		if (l == 992) {
			privateChatMode = 2;
			inputTaken = true;
		}
		if (l == 991) {
			privateChatMode = 1;
			inputTaken = true;
		}
		if (l == 990) {
			privateChatMode = 0;
			inputTaken = true;
		}
		if (l == 989) {
			cButtonCPos = 3;
			chatTypeView = 2;
			inputTaken = true;
		}
		if (l == 987) {
			tradeMode = 2;
			inputTaken = true;
		}
		if (l == 986) {
			tradeMode = 1;
			inputTaken = true;
		}
		if (l == 985) {
			tradeMode = 0;
			inputTaken = true;
		}
		if (l == 984) {
			cButtonCPos = 5;
			chatTypeView = 3;
			inputTaken = true;
		}
		if (l == 983) {
			duelMode = 2;
			inputTaken = true;
		}
		if (l == 982) {
			duelMode = 1;
			inputTaken = true;
		}
		if (l == 981) {
			duelMode = 0;
			inputTaken = true;
		}
		if (l == 980) {
			cButtonCPos = 6;
			chatTypeView = 4;
			inputTaken = true;
		}
		if (l == 493) {
			stream.createFrame(75);
			stream.method433(k);
			stream.method431(j);
			stream.method432(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if (RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if (RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if (l == 652) {
			boolean flag4 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k,
					myPlayer.smallX[0], false, j);
			if (!flag4)
				flag4 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k,
						myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(156);
			stream.method432(j + baseX);
			stream.method431(k + baseY);
			stream.method433(i1);
		}
		if (l == 94) {
			boolean flag5 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k,
					myPlayer.smallX[0], false, j);
			if (!flag5)
				flag5 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k,
						myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(181);
			stream.method431(k + baseY);
			stream.writeWord(i1);
			stream.method431(j + baseX);
			stream.method432(anInt1137);
		}
		if (l == 647) {
			stream.createFrame(213);
			stream.writeWord(k);
			stream.writeWord(j);
			switch (k) {
			case 18304:
				if (j == 0) {
					inputTaken = true;
					inputDialogState = 0;
					messagePromptRaised = true;
					promptInput = "";
					friendsListAction = 8;
					aString1121 = "Enter your clan chat title";
				}
				break;
			}
		}
		if (l == 646) {
			stream.createFrame(185);
			stream.writeWord(k);
			RSInterface class9_2 = RSInterface.interfaceCache[k];
			if (class9_2.valueIndexArray != null
					&& class9_2.valueIndexArray[0][0] == 5) {
				int i2 = class9_2.valueIndexArray[0][1];
				if (variousSettings[i2] != class9_2.requiredValues[0]) {
					variousSettings[i2] = class9_2.requiredValues[0];
					method33(i2);
					needDrawTabArea = true;
				}
			}
			switch (k) {
			// clan chat
			case 18129:
				if (RSInterface.interfaceCache[18135].message.toLowerCase()
						.contains("join")) {
					inputTaken = true;
					inputDialogState = 0;
					messagePromptRaised = true;
					promptInput = "";
					friendsListAction = 6;
					aString1121 = "Enter the name of the chat you wish to join";
				} else {
					leaveCC(); // sends packet 66 > LEave cc
				}
				break;
			case 18132:
				openInterfaceID = 18300;
				break;
			case 18526:
				inputTaken = true;
				inputDialogState = 0;
				messagePromptRaised = true;
				promptInput = "";
				friendsListAction = 9;
				aString1121 = "Enter a name to add";
				break;
			case 18527:
				inputTaken = true;
				inputDialogState = 0;
				messagePromptRaised = true;
				promptInput = "";
				friendsListAction = 10;
				aString1121 = "Enter a name to add";
				break;
			}
		}
		if (l == 225) {
			NPC class30_sub2_sub4_sub1_sub1_2 = npcArray[i1];
			if (class30_sub2_sub4_sub1_sub1_2 != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0,
						class30_sub2_sub4_sub1_sub1_2.smallY[0],
						myPlayer.smallX[0], false,
						class30_sub2_sub4_sub1_sub1_2.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				anInt1226 += i1;
				if (anInt1226 >= 85) {
					stream.createFrame(230);
					stream.writeWordBigEndian(239);
					anInt1226 = 0;
				}
				stream.createFrame(17);
				stream.method433(i1);
			}
		}
		if (l == 965) {
			NPC npc = npcArray[i1];
			if (npc != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0],
						myPlayer.smallX[0], false, npc.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				anInt1134++;
				if (anInt1134 >= 96) {
					stream.createFrame(152);
					stream.writeWordBigEndian(88);
					anInt1134 = 0;
				}
				stream.createFrame(21);
				stream.writeWord(i1);
			}
		}
		if (l == 413) {
			NPC npc = npcArray[i1];
			if (npc != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0],
						myPlayer.smallX[0], false, npc.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(131);
				stream.method433(i1);
				stream.method432(anInt1137);
			}
		}
		if (l == 200)
			clearTopInterfaces();
		if (l == 1025) {
			NPC npc = npcArray[i1];
			if (npc != null) {
				EntityDef entityDef = npc.desc;
				if (entityDef.childrenIDs != null)
					entityDef = entityDef.method161();
				if (entityDef != null) {
					String s9;
					if (entityDef.description != null)
						s9 = new String(entityDef.description);
					else
						s9 = "It's a " + entityDef.name + ".";
					pushMessage(s9, 0, "");
				}
			}
		}
		if (l == 900) {
			method66(i1, k, j);
			stream.createFrame(252);
			stream.method433(i1 >> 14 & 0x7fff);
			stream.method431(k + baseY);
			stream.method432(j + baseX);
		}
		if (l == 412) {
			NPC npc = npcArray[i1];
			if (npc != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0],
						myPlayer.smallX[0], false, npc.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(72);
				stream.method432(i1);
				// System.out.println("i1: " + i1);
			}
		}
		if (l == 365) {
			Player player = playerArray[i1];
			if (player != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0,
						player.smallY[0], myPlayer.smallX[0], false,
						player.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(249);
				stream.method432(i1);
				stream.method431(anInt1137);
			}
		}
		if (l == 729) {
			Player player = playerArray[i1];
			if (player != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0,
						player.smallY[0], myPlayer.smallX[0], false,
						player.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(39);
				stream.method431(i1);
			}
		}
		if (l == 577) {
			Player player = playerArray[i1];
			if (player != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0,
						player.smallY[0], myPlayer.smallX[0], false,
						player.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(139);
				stream.method431(i1);
			}
		}
		if (l == 956 && method66(i1, k, j)) {
			stream.createFrame(35);
			stream.method431(j + baseX);
			stream.method432(anInt1137);
			stream.method432(k + baseY);
			stream.method431(i1 >> 14 & 0x7fff);
		}
		if (l == 567) {
			boolean flag6 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k,
					myPlayer.smallX[0], false, j);
			if (!flag6)
				flag6 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k,
						myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(23);
			stream.method431(k + baseY);
			stream.method431(i1);
			stream.method431(j + baseX);
		}
		if (l == 867) {
			if ((i1 & 3) == 0)
				anInt1175++;
			if (anInt1175 >= 59) {
				stream.createFrame(200);
				stream.writeWord(25501);
				anInt1175 = 0;
			}
			stream.createFrame(43);
			stream.method431(k);
			stream.method432(i1);
			stream.method432(j);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if (RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if (RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if (l == 543) {
			stream.createFrame(237);
			stream.writeWord(j);
			stream.method432(i1);
			stream.writeWord(k);
			stream.method432(anInt1137);
			System.out.println("j: " + j + ", i1: " + i1 + ", k: " + k
					+ ", anInt1137: " + anInt1137);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if (RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if (RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if (l == 606) {
			String s2 = menuActionName[i];
			int j2 = s2.indexOf("@whi@");
			if (j2 != -1)
				if (openInterfaceID == -1) {
					clearTopInterfaces();
					reportAbuseInput = s2.substring(j2 + 5).trim();
					canMute = false;
					canBan = false;
					for (int i3 = 0; i3 < RSInterface.interfaceCache.length; i3++) {
						if (RSInterface.interfaceCache[i3] == null
								|| RSInterface.interfaceCache[i3].contentType != 600)
							continue;
						reportAbuseInterfaceID = openInterfaceID = RSInterface.interfaceCache[i3].parentID;
						break;
					}

				} else {
					pushMessage(
							"Please close the interface you have open before using 'report abuse'",
							0, "");
				}
		}
		if (l == 491) {
			Player player = playerArray[i1];
			if (player != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0,
						player.smallY[0], myPlayer.smallX[0], false,
						player.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(14);
				stream.method432(anInt1284);
				stream.writeWord(i1);
				stream.writeWord(anInt1285);
				stream.method431(itemSlotUsedOn);
			}
		}
		if (l == 639) {
			String s3 = menuActionName[i];
			int k2 = s3.indexOf("@whi@");
			if (k2 != -1) {
				long l4 = TextClass.longForName(s3.substring(k2 + 5).trim());
				int k3 = -1;
				for (int i4 = 0; i4 < friendsCount; i4++) {
					if (friendsListAsLongs[i4] != l4 && l != 640)
						continue;
					k3 = i4;
					break;
				}
				System.out.println("k3: " + k3);
				if (k3 != -1) {
					inputTaken = true;
					inputDialogState = 0;
					messagePromptRaised = true;
					promptInput = "";
					friendsListAction = 3;
					aLong953 = friendsListAsLongs[k3];
					aString1121 = "Enter message to send to " + friendsList[k3];
				}
			}
		}
		if (l == 454) {
			stream.createFrame(41);
			stream.writeWord(i1);
			stream.method432(j);
			stream.method432(k);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if (RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if (RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if (l == 478) {
			NPC class30_sub2_sub4_sub1_sub1_7 = npcArray[i1];
			if (class30_sub2_sub4_sub1_sub1_7 != null) {
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0,
						class30_sub2_sub4_sub1_sub1_7.smallY[0],
						myPlayer.smallX[0], false,
						class30_sub2_sub4_sub1_sub1_7.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				if ((i1 & 3) == 0)
					anInt1155++;
				if (anInt1155 >= 53) {
					stream.createFrame(85);
					stream.writeWordBigEndian(66);
					anInt1155 = 0;
				}
				stream.createFrame(18);
				stream.method431(i1);
			}
		}
		if (l == 113) {
			method66(i1, k, j);
			stream.createFrame(70);
			stream.method431(j + baseX);
			stream.writeWord(k + baseY);
			stream.method433(i1 >> 14 & 0x7fff);
		}
		if (l == 872) {
			method66(i1, k, j);
			stream.createFrame(234);
			stream.method433(j + baseX);
			stream.method432(i1 >> 14 & 0x7fff);
			stream.method433(k + baseY);
		}
		if (l == 502) {
			method66(i1, k, j);
			stream.createFrame(132);
			stream.method433(j + baseX);
			stream.writeWord(i1 >> 14 & 0x7fff);
			stream.method432(k + baseY);
		}
		if (l == 1125) {
			ItemDef itemDef = ItemDef.forID(i1);
			RSInterface class9_4 = RSInterface.interfaceCache[k];
			String s5;
			if (itemDef.description != null)
				s5 = new String(itemDef.description);
			else if (openInterfaceID != 5292 && openInterfaceID != 2700) {
				s5 = "It's a " + itemDef.name + " ModelId: " + itemDef.modelID
						+ " .";
				pushMessage(s5, 0, "");
			}
		}
		if (l == 169) {
			sendPacket185(k, -1, 169);
		}
		if (l == 447) {
			itemSelected = 1;
			itemSlotUsedOn = j;
			anInt1284 = k;
			anInt1285 = i1;
			selectedItemName = ItemDef.forID(i1).name;
			spellSelected = 0;
			summoningAttack = false;
			return;
		}
		if (l == 1226) {
			int j1 = i1 >> 14 & 0x7fff;
			ObjectDef class46 = ObjectDef.forID(j1);
			String s10;
			if (class46.description != null)
				s10 = new String(class46.description);
			else
				s10 = "It's a " + class46.name + ".";
			pushMessage(s10, 0, "");
		}
		if (l == 244) {
			boolean flag7 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k,
					myPlayer.smallX[0], false, j);
			if (!flag7)
				flag7 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k,
						myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(253);
			stream.method431(j + baseX);
			stream.method433(k + baseY);
			stream.method432(i1);
		}
		if (l == 1448) {
			ItemDef itemDef_1 = ItemDef.forID(i1);
			String s6;
			if (itemDef_1.description != null)
				s6 = new String(itemDef_1.description);
			else
				s6 = "It's a " + itemDef_1.name + ".";
			pushMessage(s6, 0, "");
		}
		itemSelected = 0;
		spellSelected = 0;

	}
	
	private void method70()
	{
		anInt1251 = 0;
		int j = (myPlayer.x >> 7) + baseX;
		int k = (myPlayer.y >> 7) + baseY;
		if(j >= 3053 && j <= 3156 && k >= 3056 && k <= 3136)
			anInt1251 = 1;
		if(j >= 3072 && j <= 3118 && k >= 9492 && k <= 9535)
			anInt1251 = 1;
		if(anInt1251 == 1 && j >= 3139 && j <= 3199 && k >= 3008 && k <= 3062)
			anInt1251 = 0;
	}

	public void run() {
		if(drawFlames) {
			drawFlames();
		} else {
			super.run();
		}
	}

	private void build3dScreenMenu()
	{
		if(itemSelected == 0 && spellSelected == 0)
		{
			menuActionName[menuActionRow] = "Walk here";
			menuActionID[menuActionRow] = 516;
			menuActionCmd2[menuActionRow] = super.mouseX;
			menuActionCmd3[menuActionRow] = super.mouseY;
			menuActionRow++;
		}
		int j = -1;
		for(int k = 0; k < Model.anInt1687; k++)
		{
			int l = Model.anIntArray1688[k];
			int i1 = l & 0x7f;
			int j1 = l >> 7 & 0x7f;
			int k1 = l >> 29 & 3;
			int l1 = l >> 14 & 0x7fff;
			if(l == j)
				continue;
			j = l;
			if(k1 == 2 && worldController.method304(plane, i1, j1, l) >= 0)
			{
				ObjectDef class46 = ObjectDef.forID(l1);
				if(class46.childrenIDs != null)
					class46 = class46.method580();
				if(class46 == null)
					continue;
				if(itemSelected == 1)
				{
					menuActionName[menuActionRow] = "Use " + selectedItemName + " with @cya@" + class46.name;
					menuActionID[menuActionRow] = 62;
					menuActionCmd1[menuActionRow] = l;
					menuActionCmd2[menuActionRow] = i1;
					menuActionCmd3[menuActionRow] = j1;
					menuActionRow++;
				} else
				if(spellSelected == 1)
				{
					if((spellUsableOn & 4) == 4)
					{
						menuActionName[menuActionRow] = spellTooltip + " @cya@" + class46.name;
						menuActionID[menuActionRow] = 956;
						menuActionCmd1[menuActionRow] = l;
						menuActionCmd2[menuActionRow] = i1;
						menuActionCmd3[menuActionRow] = j1;
						menuActionRow++;
					}
				} else
				{
					if(class46.itemActions != null)
					{
						for(int i2 = 4; i2 >= 0; i2--)
							if(class46.itemActions[i2] != null)
							{
								menuActionName[menuActionRow] = class46.itemActions[i2] + " @cya@" + class46.name;
								if(i2 == 0)
									menuActionID[menuActionRow] = 502;
								if(i2 == 1)
									menuActionID[menuActionRow] = 900;
								if(i2 == 2)
									menuActionID[menuActionRow] = 113;
								if(i2 == 3)
									menuActionID[menuActionRow] = 872;
								if(i2 == 4)
									menuActionID[menuActionRow] = 1062;
								menuActionCmd1[menuActionRow] = l;
								menuActionCmd2[menuActionRow] = i1;
								menuActionCmd3[menuActionRow] = j1;
								menuActionRow++;
							}

					}
					menuActionName[menuActionRow] = "Examine @cya@" + class46.name + ((myPlayer.playerRights != 0) ? ", " + " @gre@(@whi@" + l1 + "@gre@) (@whi@" + (i1 + baseX) + "," + (j1 + baseY) + "@gre@)" : "");
					menuActionID[menuActionRow] = 1226;
					menuActionCmd1[menuActionRow] = class46.type << 14;
					menuActionCmd2[menuActionRow] = i1;
					menuActionCmd3[menuActionRow] = j1;
					menuActionRow++;
				}
			}
			if(k1 == 1)
			{
				NPC npc = npcArray[l1];
				if(npc.desc.aByte68 == 1 && (npc.x & 0x7f) == 64 && (npc.y & 0x7f) == 64)
				{
					for(int j2 = 0; j2 < npcCount; j2++)
					{
						NPC npc2 = npcArray[npcIndices[j2]];
						if(npc2 != null && npc2 != npc && npc2.desc.aByte68 == 1 && npc2.x == npc.x && npc2.y == npc.y)
							buildAtNPCMenu(npc2.desc, npcIndices[j2], j1, i1);
					}

					for(int l2 = 0; l2 < playerCount; l2++)
					{
						Player player = playerArray[playerIndices[l2]];
						if(player != null && player.x == npc.x && player.y == npc.y)
							buildAtPlayerMenu(i1, playerIndices[l2], player, j1);
					}

				}
				buildAtNPCMenu(npc.desc, l1, j1, i1);
			}
			if(k1 == 0)
			{
				Player player = playerArray[l1];
				if((player.x & 0x7f) == 64 && (player.y & 0x7f) == 64)
				{
					for(int k2 = 0; k2 < npcCount; k2++)
					{
						NPC class30_sub2_sub4_sub1_sub1_2 = npcArray[npcIndices[k2]];
						if(class30_sub2_sub4_sub1_sub1_2 != null && class30_sub2_sub4_sub1_sub1_2.desc.aByte68 == 1 && class30_sub2_sub4_sub1_sub1_2.x == player.x && class30_sub2_sub4_sub1_sub1_2.y == player.y)
							buildAtNPCMenu(class30_sub2_sub4_sub1_sub1_2.desc, npcIndices[k2], j1, i1);
					}

					for(int i3 = 0; i3 < playerCount; i3++)
					{
						Player class30_sub2_sub4_sub1_sub2_2 = playerArray[playerIndices[i3]];
						if(class30_sub2_sub4_sub1_sub2_2 != null && class30_sub2_sub4_sub1_sub2_2 != player && class30_sub2_sub4_sub1_sub2_2.x == player.x && class30_sub2_sub4_sub1_sub2_2.y == player.y)
							buildAtPlayerMenu(i1, playerIndices[i3], class30_sub2_sub4_sub1_sub2_2, j1);
					}

				}
				buildAtPlayerMenu(i1, l1, player, j1);
			}
			if(k1 == 3)
			{
				NodeList class19 = groundArray[plane][i1][j1];
				if(class19 != null)
				{
					for(Item item = (Item)class19.getFirst(); item != null; item = (Item)class19.getNext())
					{
						ItemDef itemDef = ItemDef.forID(item.ID);
						if(itemSelected == 1)
						{
							menuActionName[menuActionRow] = "Use " + selectedItemName + " with @lre@" + itemDef.name;
							menuActionID[menuActionRow] = 511;
							menuActionCmd1[menuActionRow] = item.ID;
							menuActionCmd2[menuActionRow] = i1;
							menuActionCmd3[menuActionRow] = j1;
							menuActionRow++;
						} else
						if(spellSelected == 1)
						{
							if((spellUsableOn & 1) == 1)
							{
								menuActionName[menuActionRow] = spellTooltip + " @lre@" + itemDef.name;
								menuActionID[menuActionRow] = 94;
								menuActionCmd1[menuActionRow] = item.ID;
								menuActionCmd2[menuActionRow] = i1;
								menuActionCmd3[menuActionRow] = j1;
								menuActionRow++;
							}
						} else
						{
							for(int j3 = 4; j3 >= 0; j3--)
								if(itemDef.groundActions != null && itemDef.groundActions[j3] != null)
								{
									menuActionName[menuActionRow] = itemDef.groundActions[j3] + " @lre@" + itemDef.name;
									if(j3 == 0)
										menuActionID[menuActionRow] = 652;
									if(j3 == 1)
										menuActionID[menuActionRow] = 567;
									if(j3 == 2)
										menuActionID[menuActionRow] = 234;
									if(j3 == 3)
										menuActionID[menuActionRow] = 244;
									if(j3 == 4)
										menuActionID[menuActionRow] = 213;
									menuActionCmd1[menuActionRow] = item.ID;
									menuActionCmd2[menuActionRow] = i1;
									menuActionCmd3[menuActionRow] = j1;
									menuActionRow++;
								} else
								if(j3 == 2)
								{
									menuActionName[menuActionRow] = "Take @lre@" + itemDef.name;
									menuActionID[menuActionRow] = 234;
									menuActionCmd1[menuActionRow] = item.ID;
									menuActionCmd2[menuActionRow] = i1;
									menuActionCmd3[menuActionRow] = j1;
									menuActionRow++;
								}
						if (openInterfaceID != 16600) {
							menuActionName[menuActionRow] = "Examine @lre@" + itemDef.name + ((myPlayer.playerRights != 0) ? ", @gre@(@whi@" + item.ID + "@gre@)" : "");
							menuActionID[menuActionRow] = 1448;
							menuActionCmd1[menuActionRow] = item.ID;
							menuActionCmd2[menuActionRow] = i1;
							menuActionCmd3[menuActionRow] = j1;
							menuActionRow++;
						}
					}
					}

				}
			}
		}
	}

	public void cleanUpForQuit()
	{
		HPBarFull = null;
		HPBarEmpty = null;
		signlink.reporterror = false;
		try
		{
			if(socketStream != null)
				socketStream.close();
		}
		catch(Exception _ex) { }
		socketStream = null;
		stopMidi();
		if(mouseDetection != null)
			mouseDetection.running = false;
		mouseDetection = null;
		onDemandFetcher.disable();
		onDemandFetcher = null;
		aStream_834 = null;
		stream = null;
		aStream_847 = null;
		inStream = null;
		anIntArray1234 = null;
		aByteArrayArray1183 = null;
		aByteArrayArray1247 = null;
		anIntArray1235 = null;
		anIntArray1236 = null;
		intGroundArray = null;
		byteGroundArray = null;
		worldController = null;
		aClass11Array1230 = null;
		anIntArrayArray901 = null;
		anIntArrayArray825 = null;
		bigX = null;
		bigY = null;
		aByteArray912 = null;
		sideTabArea = null;
		mapEdgeIP = null;
		leftFrame = null;
		topFrame = null;
		rightFrame = null;
		mapBackImage = null;
		main3DArea = null;
		chatBackImage = null;
		aRSImageProducer_1123 = null;
		aRSImageProducer_1124 = null;
		aRSImageProducer_1125 = null;
		/* Null pointers for custom sprites */
		backgroundFix = null;
		worldSwitcherBg = null;
		gameframeSwitcherBg = null;
		checkMark = null;
		optionsHover = null;
		loadingBarFull = null;
		loadingBarEmpty = null;
				alertBack = null;
		alertBorder = null;
		alertBorderH = null;
		chatArea = null;
		chatButtons = null;
		newChatButtons = null;
		tabArea = null;
		mapArea = null;
		magicAuto = null;
		/**/
		emptyOrb = null;
		NewMapBack = null;
		emptyFOrb = null;
		hoveredEmpty = null;
		hoveredFEmpty = null;
		runIcon1 = null;
		runIcon2 = null;
		runOrb1 = null;
		runOrb2 = null;
		hitPointsFill = null;
		hitPointsIcon = null;
		prayerFill = null;
		prayerIcon = null;
		sideIcons = null;
		compass = null;
		headIcons = null;
		cursesIcons = null;
		skullIcons = null;
		headIconsHint = null;
		crosses = null;
		mapDotItem = null;
		mapDotNPC = null;
		mapDotPlayer = null;
		mapDotFriend = null;
		mapDotTeam = null;
		mapScenes = null;
		mapFunctions = null;
		anIntArrayArray929 = null;
		playerArray = null;
		playerIndices = null;
		anIntArray894 = null;
		aStreamArray895s = null;
		anIntArray840 = null;
		npcArray = null;
		npcIndices = null;
		groundArray = null;
		aClass19_1179 = null;
		aClass19_1013 = null;
		aClass19_1056 = null;
		menuActionCmd2 = null;
		menuActionCmd3 = null;
		menuActionID = null;
		menuActionCmd1 = null;
		menuActionName = null;
		variousSettings = null;
		mapFunctionX = null;
		mapFunctionY = null;
		currentMapFunctions = null;
		miniMapImage = null;
		friendsList = null;
		friendsListAsLongs = null;
		friendsNodeIDs = null;
		aRSImageProducer_1110 = null;
		aRSImageProducer_1111 = null;
		aRSImageProducer_1107 = null;
		aRSImageProducer_1108 = null;
		aRSImageProducer_1109 = null;
		aRSImageProducer_1112 = null;
		aRSImageProducer_1113 = null;
		aRSImageProducer_1114 = null;
		aRSImageProducer_1115 = null;
		multiOverlay = null;
		nullLoader();
		ObjectDef.nullLoader();
		EntityDef.nullLoader();
		ItemDef.nullLoader();
		Flo.cache = null;
		IDK.cache = null;
		RSInterface.interfaceCache = null;
		DummyClass.cache = null;
		Animation.anims = null;
		SpotAnim.cache = null;
		SpotAnim.aMRUNodes_415 = null;
		Varp.cache = null;
		super.fullGameScreen = null;
		Player.mruNodes = null;
		Texture.nullLoader();
		WorldController.nullLoader();
		Model.nullLoader();
		Class36.nullLoader();
		System.gc();
	}
	public boolean openBOX = false;
	public String donatorPayment = "";
	private void printDebug()
	{
		System.out.println("============");
		System.out.println("flame-cycle:" + anInt1208);
		if(onDemandFetcher != null)
			System.out.println("Od-cycle:" + onDemandFetcher.onDemandCycle);
		System.out.println("loop-cycle:" + loopCycle);
		System.out.println("draw-cycle:" + anInt1061);
		System.out.println("ptype:" + pktType);
		System.out.println("psize:" + pktSize);
		if(socketStream != null)
			socketStream.printDebug();
		super.shouldDebug = true;
	}

	Component getGameComponent() {
		if(signlink.mainapp != null)
			return signlink.mainapp;
		if(super.gameFrame != null)
			return super.gameFrame;
		else
			return this;
	}
	
		public void sendString(int identifier, String text) {
		text = identifier + "," + text;
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");

		System.out.println(text.length() + 1);

		System.out.println(text);
		stream.createFrame(127);
		if (text != null) {
			stream.writeWordBigEndian(text.length() + 1);
			stream.writeString(text);
		}
	}


	public static boolean hasUnvalidString;
	public static void containsLetter(String s) {
		if ( s == null ) {
			return;
		}
		boolean letterFound = false;
		for (int i = 0; !letterFound && i < s.length(); i++) {
		letterFound = letterFound || Character.isLetter(s.charAt(i));
		}
		if (letterFound == true) {
			hasUnvalidString = true;
		}
	}	
	
	
	
	private void method73() {
		do {
			int j = readCharacter();
			if(j == -1)
				break;
				if(openInterfaceID != -1 && openInterfaceID == 21595) {//New user
				boolean flag1 = false;
				for(int i2 = 0; i2 < validUserPassChars.length(); i2++) {
					if(j != validUserPassChars.charAt(i2))
						continue;
					flag1 = true;
					break;
				}

				if(j == 8 && newUserString.length() > 0) {
					newUserString = newUserString.substring(0, newUserString.length() - 1);
				}
				if(flag1  && newUserString.length() < 20) {
					newUserString += (char)j;
					newUserString = newUserString.toLowerCase();
				}
				if(newUserString.length() > 20)
					newUserString = newUserString.substring(0, 20);
				if(j == 13 || j == 10)
					changeUserSubmit();
			}
			if(openInterfaceID != -1 && openInterfaceID == 22595) {//newPasswordString
				boolean flag1 = false;
				for(int i2 = 0; i2 < validUserPassChars.length(); i2++) {
					if(j != validUserPassChars.charAt(i2))
						continue;
					flag1 = true;
					break;
				}

				if(j == 8 && newPasswordString.length() > 0) {
					newPasswordString = newPasswordString.substring(0, newPasswordString.length() - 1);
					sendFrame126("Password Strength: " + strengthCheck(newPasswordString),22601);
				}
				if(flag1  && newPasswordString.length() < 20) {
					newPasswordString += (char)j;
					newPasswordString = newPasswordString.toLowerCase();
					sendFrame126("Password Strength: " + strengthCheck(newPasswordString),22601);
				}
				if(newPasswordString.length() > 20)
					newPasswordString = newPasswordString.substring(0, 20);
				if(j == 13 || j == 10)
					changePasswordSubmit();
			} else if(openInterfaceID != -1 && openInterfaceID == reportAbuseInterfaceID) {
				if(j == 8 && reportAbuseInput.length() > 0)
					reportAbuseInput = reportAbuseInput.substring(0, reportAbuseInput.length() - 1);
				if((j >= 97 && j <= 122 || j >= 65 && j <= 90 || j >= 48 && j <= 57 || j == 32) && reportAbuseInput.length() < 12)
					reportAbuseInput += (char)j;
			} else if(messagePromptRaised) {
				if(j >= 32 && j <= 122 && promptInput.length() < 80) {
					promptInput += (char)j;
					inputTaken = true;
				}
				if(j == 8 && promptInput.length() > 0) {
					promptInput = promptInput.substring(0, promptInput.length() - 1);
					inputTaken = true;
				}
				if(j == 13 || j == 10) {
					messagePromptRaised = false;
					inputTaken = true;
					if(friendsListAction == 1) {
						long l = TextClass.longForName(promptInput);
						addFriend(l);
					}
					if(friendsListAction == 2 && friendsCount > 0) {
						long l1 = TextClass.longForName(promptInput);
						delFriend(l1);
					}
					
						if(interfaceButtonAction == 557 && promptInput.length() > 0) {
						boolean allowedValues = false;	
						if (promptInput.toLowerCase().contains("k")) {
							promptInput = promptInput.replaceAll("k", "000");
							allowedValues = true;
						} else if (promptInput.toLowerCase().contains("m")) {
							promptInput = promptInput.replaceAll("m", "000000");
							allowedValues = true;
						} else if (promptInput.toLowerCase().contains("b")) {
							promptInput = promptInput.replaceAll("b", "000000000");
							allowedValues = true;
						}
						if(allowedValues == false) {
							containsLetter(promptInput);
						}
						if (hasUnvalidString == true) {
							pushMessage("Dont try and abuse", 0, "");
							return;
						} else {
						int amount = 0;
						amount = Integer.parseInt(promptInput);
						if(amount < 0)
							return;
							inputString = "::withdraw "+promptInput;
							sendPacket(103);
						
						}
						}
					if(friendsListAction == 3 && promptInput.length() > 0) {
						stream.createFrame(126);
						stream.writeWordBigEndian(0);
						int k = stream.currentOffset;
						stream.writeQWord(aLong953);
						TextInput.method526(promptInput, stream);
						stream.writeBytes(stream.currentOffset - k);
						promptInput = TextInput.processText(promptInput);
						//promptInput = Censor.doCensor(promptInput);
						pushMessage(promptInput, 6, TextClass.fixName(TextClass.nameForLong(aLong953)));
						if(privateChatMode == 2) {
							privateChatMode = 1;
							stream.createFrame(95);
							stream.writeWordBigEndian(publicChatMode);
							stream.writeWordBigEndian(privateChatMode);
							stream.writeWordBigEndian(tradeMode);
						}
					}
					if(friendsListAction == 4 && ignoreCount < 100) {
						long l2 = TextClass.longForName(promptInput);
						addIgnore(l2);
					}
					if(friendsListAction == 5 && ignoreCount > 0) {
						long l3 = TextClass.longForName(promptInput);
						delIgnore(l3);
					}
					if(friendsListAction == 6) {
						long l3 = TextClass.longForName(promptInput);
						chatJoin(l3);
					}
				}
			} else if(inputDialogState == 1) {
				if (j >= 48 && j <= 57 && amountOrNameInput.length() < 10) {
					amountOrNameInput += (char) j;
					inputTaken = true;
				}
				if ((!amountOrNameInput.toLowerCase().contains("k") && !amountOrNameInput.toLowerCase().contains("m") && !amountOrNameInput.toLowerCase().contains("b")) && (j == 107 || j == 109) || j == 98) {
					amountOrNameInput += (char) j;
					inputTaken = true;
				}
				if(j == 8 && amountOrNameInput.length() > 0) {//delete
					amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
					inputTaken = true;
				}
				if(j == 13 || j == 10) {//enter
					if (amountOrNameInput.length() > 0) {
						if (amountOrNameInput.toLowerCase().contains("k")) {
							amountOrNameInput = amountOrNameInput.replaceAll("k", "000");
						} else if (amountOrNameInput.toLowerCase().contains("m")) {
							amountOrNameInput = amountOrNameInput.replaceAll("m", "000000");
						} else if (amountOrNameInput.toLowerCase().contains("b")) {
							amountOrNameInput = amountOrNameInput.replaceAll("b", "000000000");
						}
						int amount = 0;
						amount = Integer.parseInt(amountOrNameInput);
						stream.createFrame(208);
						stream.writeDWord(amount);
					}
					inputDialogState = 0;
					inputTaken = true;
				}
			} else if (inputDialogState == 3) {
				if (j == 10) {
					totalItemResults = 0;
					amountOrNameInput = "";
					inputDialogState =0;
					inputTaken = true;
				}
				if(j == 13 || j == 10) {
				if(amountOrNameInput.length() == 0) {
				buttonclicked = false;
				interfaceButtonAction = 0;
				}
				}
				if (j >= 32 && j <= 122 && amountOrNameInput.length() < 40) {
					amountOrNameInput += (char) j;
					inputTaken = true;
				}
				if (j == 8 && amountOrNameInput.length() > 0) {
					amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
					inputTaken = true;
				}
			} else if(inputDialogState == 2) {
				if(j >= 32 && j <= 122 && amountOrNameInput.length() < 12) {
					amountOrNameInput += (char)j;
					inputTaken = true;
				}
				if(j == 8 && amountOrNameInput.length() > 0) {
					amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
					inputTaken = true;
				}
				if(j == 13 || j == 10) {
					if(amountOrNameInput.length() > 0) {
						stream.createFrame(60);
						stream.writeQWord(TextClass.longForName(amountOrNameInput));
					}
					inputDialogState = 0;
					inputTaken = true;
				}
			} else if(backDialogID == -1) {
				if(j >= 32 && j <= 122 && inputString.length() < 80) {
					inputString += (char)j;
					inputTaken = true;
				}
				if(j == 8 && inputString.length() > 0) {
					inputString = inputString.substring(0, inputString.length() - 1);
					inputTaken = true;
				}
				if (j == 9) tabToReplyPm();
				if((j == 13 || j == 10) && inputString.length() > 0) {
				if(myPlayer.playerRights == 3) {
					if(inputString.startsWith("::back")) {
						try {
							String[] args = inputString.split(" ");
							pushMessage("Opening interface " + args[1] + ".",0,"");
							backDialogID = Integer.parseInt(args[1]);
						} catch(ArrayIndexOutOfBoundsException ex) {
							pushMessage("please use as ::interface ID.",0,"");
						}
					}
					}
						
						if(inputString.equals("clientdrop") && myPlayer.playerRights == 3)
							dropClient();
						if(inputString.equals("::lag") && myPlayer.playerRights == 3)
							printDebug();
						if(inputString.equals("::prefetchmusic") && myPlayer.playerRights == 3) {
							for(int j1 = 0; j1 < onDemandFetcher.getVersionCount(2); j1++)
								onDemandFetcher.method563((byte)1, 2, j1);

						}
						if(inputString.equals("::testsp")) {
					int x = baseX + (myPlayer.x - 6 >> 7);
									int y = baseY + (myPlayer.y - 6 >> 7);
					stream.createFrame(132);
					stream.method433(x);//players x
					stream.writeWord(409);//praying at alter > infi prayer
					stream.method432(y);//players y

						}
						if(inputString.equals("::fpson"))
							fpsOn = true;
						if(inputString.equals("::fpsoff"))
							fpsOn = false;
						 if (inputString.startsWith("::chatcolor")) {
                    	String args[] = inputString.split(" ");
                    	if(args[1].length() == 6)
                    		chatColorHex = args[1];
                    	else
                    		pushMessage("The hex code must be a 6-digit-number.", 0, "");
                    }
						if(inputString.equals("::dataon"))
							clientData = true;
						if(inputString.equals("::dataoff"))
							clientData = false;
						if(inputString.equals("::noclip")) {
							for(int k1 = 0; k1 < 4; k1++) {
								for(int i2 = 1; i2 < 103; i2++) {
									for(int k2 = 1; k2 < 103; k2++)
										aClass11Array1230[k1].anIntArrayArray294[i2][k2] = 0;

								}
							}
						}
						if(inputString.equalsIgnoreCase("::tabs")) {
							if(functionKeys) {
								functionKeys = false;
								pushMessage("You are now using the new tab system!", 0, "");
								pushMessage("F1 - Inventory F2 - Equipment F3 - Prayer F4 - Mage F5 - Combat", 0, "");
							} else {
								functionKeys = true;
								pushMessage("You are now using the old tab system!", 0, "");
								pushMessage("F1 - Combat F2 - Skills F3 - Quest F4 - Inventory F5 - Equipment", 0, "");
								pushMessage("F6 - Prayer F7 - Mage F8 - Clan F9 - Friend F10 - Ignore ", 0, "");
							}
						}
						if(inputString.equalsIgnoreCase("::hits")) {
							if(newDamage) {
								newDamage = false;
								pushMessage("Old school hits activated", 0, "");
							} else {
								newDamage = true;
								pushMessage("Newer hits activated", 0, "");
							}
						}
					if(inputString.startsWith("/"))
						inputString = "::" + inputString;
					if(inputString.equals("add model") && myPlayer.playerRights == 3) {
						try {
							int ModelIndex = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter model ID", "Model", 3));
							byte[] abyte0 = getModel(ModelIndex);
							if(abyte0 != null && abyte0.length > 0) {
								decompressors[1].method234(abyte0.length, abyte0, ModelIndex);
								pushMessage("Model: [" + ModelIndex + "] added successfully!", 0, "");
							} else {
								pushMessage("Unable to find the model. "+ModelIndex, 0, "");
							}
						} catch(Exception e) {
							pushMessage("Syntax - ::add model <path>", 0, "");
						}
					}
				/*	if(inputString.startsWith("::getcode"))
						inputString = "::getcode "+donatorPayment;
					if(inputString.startsWith("::setcode"))
						openBOX = true;*/
					if(inputString.startsWith("::")) {
					if(inputString.startsWith("::changeusername")) {
							try {
								String arg = inputString.substring(17);
								newUserString = (inputString.length() > 17) ? arg : "";
							} catch (StringIndexOutOfBoundsException ex) {
								newUserString = "";
							}
							openInterfaceID = 21595;
							pushMessage("Type your new Username, be sure to not make spelling mistakes.", 0, "");
						}else {
							stream.createFrame(103);
							stream.writeWordBigEndian(inputString.length() - 1);
							stream.writeString(inputString.substring(2));
						}
					} else {
						String s = inputString.toLowerCase();	
						int j2 = 0;
						if(s.startsWith("yellow:"))
						{
							j2 = 0;
							inputString = inputString.substring(7);
						} else if(s.startsWith("red:"))
						{
							j2 = 1;
							inputString = inputString.substring(4);
						} else if(s.startsWith("green:"))
						{
							j2 = 2;
							inputString = inputString.substring(6);
						} else if(s.startsWith("cyan:"))
						{
							j2 = 3;
							inputString = inputString.substring(5);
						} else if(s.startsWith("purple:"))
						{
							j2 = 4;
							inputString = inputString.substring(7);
						} else if(s.startsWith("white:"))
						{
							j2 = 5;
							inputString = inputString.substring(6);
						} else if(s.startsWith("flash1:"))
						{
							j2 = 6;
							inputString = inputString.substring(7);
						} else if(s.startsWith("flash2:"))
						{
							j2 = 7;
							inputString = inputString.substring(7);
						} else if(s.startsWith("flash3:"))
						{
							j2 = 8;
							inputString = inputString.substring(7);
						} else if(s.startsWith("glow1:"))
						{
							j2 = 9;
							inputString = inputString.substring(6);
						} else if(s.startsWith("glow2:"))
						{
							j2 = 10;
							inputString = inputString.substring(6);
						} else if(s.startsWith("glow3:"))
						{
							j2 = 11;
							inputString = inputString.substring(6);
						}
						s = inputString.toLowerCase();
						int i3 = 0;
						if(s.startsWith("wave:"))
						{
							i3 = 1;
							inputString = inputString.substring(5);
						} else if(s.startsWith("wave2:"))
						{
							i3 = 2;
							inputString = inputString.substring(6);
						} else if(s.startsWith("shake:"))
						{
							i3 = 3;
							inputString = inputString.substring(6);
						} else if(s.startsWith("scroll:"))
						{
							i3 = 4;
							inputString = inputString.substring(7);
						} else if(s.startsWith("slide:"))
						{
							i3 = 5;
							inputString = inputString.substring(6);
						}
						stream.createFrame(4);
						stream.writeWordBigEndian(0);
						int j3 = stream.currentOffset;
						stream.method425(i3);
						stream.method425(j2);
						aStream_834.currentOffset = 0;
						TextInput.method526(inputString, aStream_834);
						stream.method441(0, aStream_834.buffer, aStream_834.currentOffset);
						stream.writeBytes(stream.currentOffset - j3);
						inputString = TextInput.processText(inputString);
						//inputString = Censor.doCensor(inputString);
						myPlayer.textSpoken = inputString;
						myPlayer.anInt1513 = j2;
						myPlayer.anInt1531 = i3;
						myPlayer.textCycle = 150;
						if (myPlayer.playerRights == 1) {
							pushMessage(myPlayer.textSpoken, 2, "@cr1@"+myPlayer.name);
						} else if (myPlayer.playerRights == 2) {
							pushMessage(myPlayer.textSpoken, 2, "@cr2@"+myPlayer.name);
						} else if (myPlayer.playerRights == 3) {
							pushMessage(myPlayer.textSpoken, 2, "@cr3@"+myPlayer.name);
						} else if (myPlayer.playerRights == 4) {
							pushMessage(myPlayer.textSpoken, 2, "@cr4@"+myPlayer.name);
						} else if (myPlayer.playerRights == 5) {
							pushMessage(myPlayer.textSpoken, 2, "@cr5@"+myPlayer.name);
						} else if (myPlayer.playerRights == 6) {
							pushMessage(myPlayer.textSpoken, 2, "@cr6@"+myPlayer.name);
						} else {
							pushMessage(myPlayer.textSpoken, 2, myPlayer.name);
						}
						if(publicChatMode == 2)
						{
							publicChatMode = 3;
							stream.createFrame(95);
							stream.writeWordBigEndian(publicChatMode);
							stream.writeWordBigEndian(privateChatMode);
							stream.writeWordBigEndian(tradeMode);
						}
					}
					inputString = "";
					inputTaken = true;
				}
			}
		} while(true);
	}
	public static boolean functionKeys = true;
	public static boolean healthBars = true;
	public static boolean newDamage = true;
	
	private void buildPublicChat(int j)
	{
		int l = 0;
		for(int i1 = 0; i1 < 500; i1++)
		{
			if(chatMessages[i1] == null)
				continue;
			if(chatTypeView != 1)
				continue;
			int j1 = chatTypes[i1];
			String s = chatNames[i1];
			int k1 = (70 - l * 14 + 42) + anInt1089 + 4 + 5;
			if(k1 < -23)
				break;
			if (s != null && s.startsWith("@cr1@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr2@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr3@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr4@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr5@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr6@"))
				s = s.substring(5);
			if((j1 == 1 || j1 == 2) && (j1 == 1 || publicChatMode == 0 || publicChatMode == 1 && isFriendOrSelf(s))) {
			if(j > k1 - 14 && j <= k1 && !s.equals(myPlayer.name)) {
				if(myPlayer.playerRights >= 1) {
					menuActionName[menuActionRow] = "Report abuse @whi@" + s;
					menuActionID[menuActionRow] = 606;
					menuActionRow++;
				}
				menuActionName[menuActionRow] = "Add ignore @whi@" + s;
				menuActionID[menuActionRow] = 42;
				menuActionRow++;
				menuActionName[menuActionRow] = "Add friend @whi@" + s;
				menuActionID[menuActionRow] = 337;
				menuActionRow++;
			}
			l++;
			}
		}
	}

	private void buildFriendChat(int j)
	{
		int l = 0;
		for(int i1 = 0; i1 < 500; i1++) {
			if(chatMessages[i1] == null)
				continue;
			if(chatTypeView != 2)
				continue;
			int j1 = chatTypes[i1];
			String s = chatNames[i1];
			int k1 = (70 - l * 14 + 42) + anInt1089 + 4 + 5;
			if(k1 < -23)
				break;
			if (s != null && s.startsWith("@cr1@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr2@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr3@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr4@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr5@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr6@"))
				s = s.substring(5);
			if((j1 == 5 || j1 == 6) && (splitPrivateChat == 0 || chatTypeView == 2) && (j1 == 6 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
				l++;
			if((j1 == 3 || j1 == 7) && (splitPrivateChat == 0 || chatTypeView == 2) && (j1 == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
			{
				if(j > k1 - 14 && j <= k1) {
					if(myPlayer.playerRights >= 1) {
						menuActionName[menuActionRow] = "Report abuse @whi@" + s;
						menuActionID[menuActionRow] = 606;
						menuActionRow++;
					}
					menuActionName[menuActionRow] = "Add ignore @whi@" + s;
					menuActionID[menuActionRow] = 42;
					menuActionRow++;
					menuActionName[menuActionRow] = "Add friend @whi@" + s;
					menuActionID[menuActionRow] = 337;
					menuActionRow++;
				}
			l++;
			}
		}
	}

	private void buildDuelorTrade(int j) {
		int l = 0;
		for(int i1 = 0; i1 < 500; i1++) {
			if(chatMessages[i1] == null)
				continue;
			if(chatTypeView != 3 && chatTypeView != 4)
				continue;
			int j1 = chatTypes[i1];
			String s = chatNames[i1];
			int k1 = (70 - l * 14 + 42) + anInt1089 + 4 + 5;
			if(k1 < -23)
				break;
			if (s != null && s.startsWith("@cr1@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr2@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr3@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr4@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr5@"))
				s = s.substring(5);
			if (s != null && s.startsWith("@cr6@"))
				s = s.substring(5);
			if(chatTypeView == 3 && j1 == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s))) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = "Accept trade @whi@" + s;
					menuActionID[menuActionRow] = 484;
					menuActionRow++;
				}
				l++;
			}
			if(j1 == 9)
			{
				if(j > k1 - 14 && j <= k1)
				{
					String displayLink = ((s.length() > 30) ? (s.substring(0,25) + "...") : (s));
					menuActionName[menuActionRow] = "Open page: " + displayLink;
					menuActionID[menuActionRow] = 485;
					menuActionRow++;
					linkInText = s;
				}
				l++;
			}
			
			if(chatTypeView == 4 && j1 == 8 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s))) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = "Accept challenge @whi@" + s;
					menuActionID[menuActionRow] = 6;
					menuActionRow++;
				}
				l++;
			}
			if(j1 == 12) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = "Go-to @blu@" + s;
					menuActionID[menuActionRow] = 915;
					menuActionRow++;
				}
				l++;
			}
		}
	}

	private void buildChatAreaMenu(int paddingY) {
		int totalLines = 0;
		for(int typeIndex = 0; typeIndex < 500; typeIndex++) {
		String s = chatNames[typeIndex];
			if(chatMessages[typeIndex] == null)
				continue;
			int chatType = chatTypes[typeIndex];
			int scrollYPos = (70 - totalLines * 14 + 42) + anInt1089 + 4 + 5;
			if(scrollYPos < -23)
				break;
			String name = chatNames[typeIndex];
			if(chatTypeView == 1) {
				buildPublicChat(paddingY);
				break;
			}
			if(chatTypeView == 2) {
				buildFriendChat(paddingY);
				break;
			}
			if(chatTypeView == 3 || chatTypeView == 4) {
				buildDuelorTrade(paddingY);
				break;
			}
			if(chatTypeView == 5) {
				break;
			}
			if (name != null && name.startsWith("@cr1@")) 
				name = name.substring(5);
			if (name != null && name.startsWith("@cr2@")) 
				name = name.substring(5);
			if (name != null && name.startsWith("@cr3@")) 
				name = name.substring(5);
			if (name != null && name.startsWith("@cr4@")) 
				name = name.substring(5);
			if (name != null && name.startsWith("@cr5@")) 
				name = name.substring(5);
			if (name != null && name.startsWith("@cr6@")) 
				name = name.substring(5);
			if(chatType == 0)
				totalLines++;
			if((chatType == 1 || chatType == 2) && (chatType == 1 || publicChatMode == 0 || publicChatMode == 1 && isFriendOrSelf(name))) {
				if(myPlayer != null) {
				if(paddingY > scrollYPos - 14 && paddingY <= scrollYPos && !name.equals(myPlayer.name)) {
					if(myPlayer.playerRights >= 1) {
						menuActionName[menuActionRow] = "Report abuse @whi@" + name;
						menuActionID[menuActionRow] = 606;
						menuActionRow++;
					}
					menuActionName[menuActionRow] = "Add ignore @whi@" + name;
					menuActionID[menuActionRow] = 42;
					menuActionRow++;
					menuActionName[menuActionRow] = "Add friend @whi@" + name;
					menuActionID[menuActionRow] = 337;
					menuActionRow++;
				}
				totalLines++;
			}
			}
			if((chatType == 3 || chatType == 7) && splitPrivateChat == 0 && (chatType == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(name))) {
				if(paddingY > scrollYPos - 14 && paddingY <= scrollYPos) {
					if(myPlayer.playerRights >= 1) {
						menuActionName[menuActionRow] = "Report abuse @whi@" + name;
						menuActionID[menuActionRow] = 606;
						menuActionRow++;
					}
					menuActionName[menuActionRow] = "Add ignore @whi@" + name;
					menuActionID[menuActionRow] = 42;
					menuActionRow++;
					menuActionName[menuActionRow] = "Add friend @whi@" + name;
					menuActionID[menuActionRow] = 337;
					menuActionRow++;
				}
				totalLines++;
			}
			if(chatType == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(name))) {
				if(paddingY > scrollYPos - 14 && paddingY <= scrollYPos) {
					menuActionName[menuActionRow] = "Accept trade @whi@" + name;
					menuActionID[menuActionRow] = 484;
					menuActionRow++;
				}
				totalLines++;
			}
			if(chatType == 9) {
				if(paddingY > scrollYPos - 14 && paddingY <= scrollYPos) {
					String displayLink = ((name.length() > 30) ? (name.substring(0,25) + "...") : (name));
					menuActionName[menuActionRow] = "Open page: " + displayLink;
					menuActionID[menuActionRow] = 485;
					menuActionRow++;
					linkInText = name;
				}
				totalLines++;
			}
			if((chatType == 5 || chatType == 6) && splitPrivateChat == 0 && privateChatMode < 2)
				totalLines++;
			if(chatType == 8 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(name))) {
				if(paddingY > scrollYPos - 14 && paddingY <= scrollYPos) {
					menuActionName[menuActionRow] = "Accept challenge @whi@" + name;
					menuActionID[menuActionRow] = 6;
					menuActionRow++;
				}
				totalLines++;
			}
		}
	}	
	
	private void drawFriendsListOrWelcomeScreen(RSInterface class9)
	{
		int j = class9.contentType;
		if(j >= 1 && j <= 100 || j >= 701 && j <= 800)
		{
			if(j == 1 && anInt900 == 0)
			{
				class9.message = "Loading friend list";
				class9.atActionType = 0;
				return;
			}
			if(j == 1 && anInt900 == 1)
			{
				class9.message = "Connecting to friendserver";
				class9.atActionType = 0;
				return;
			}
			if(j == 2 && anInt900 != 2)
			{
				class9.message = "Please wait...";
				class9.atActionType = 0;
				return;
			}
			int k = friendsCount;
			if(anInt900 != 2)
				k = 0;
			if(j > 700)
				j -= 601;
			else
				j--;
			if(j >= k)
			{
				class9.message = "";
				class9.atActionType = 0;
				return;
			} else
			{
				class9.message = friendsList[j];
				class9.atActionType = 1;
				return;
			}
		}
		if(j >= 101 && j <= 200 || j >= 801 && j <= 900)
		{
			int l = friendsCount;
			if(anInt900 != 2)
				l = 0;
			if(j > 800)
				j -= 701;
			else
				j -= 101;
			if(j >= l)
			{
				class9.message = "";
				class9.atActionType = 0;
				return;
			}
			if(friendsNodeIDs[j] == 0)
				class9.message = "@red@Offline";
			else if(friendsNodeIDs[j] == nodeID)
				class9.message = "@gre@Online"/* + (friendsNodeIDs[j] - 9)*/;
			else
				class9.message = "@red@Offline"/* + (friendsNodeIDs[j] - 9)*/;
			class9.atActionType = 1;
			return;
		}
		if(j == 203)
		{
			int i1 = friendsCount;
			if(anInt900 != 2)
				i1 = 0;
			class9.scrollMax = i1 * 15 + 20;
			if(class9.scrollMax <= class9.height)
				class9.scrollMax = class9.height + 1;
			return;
		}
		if(j >= 401 && j <= 500)
		{
			if((j -= 401) == 0 && anInt900 == 0)
			{
				class9.message = "Loading ignore list";
				class9.atActionType = 0;
				return;
			}
			if(j == 1 && anInt900 == 0)
			{
				class9.message = "Please wait...";
				class9.atActionType = 0;
				return;
			}
			int j1 = ignoreCount;
			if(anInt900 == 0)
				j1 = 0;
			if(j >= j1)
			{
				class9.message = "";
				class9.atActionType = 0;
				return;
			} else
			{
				class9.message = TextClass.fixName(TextClass.nameForLong(ignoreListAsLongs[j]));
				class9.atActionType = 1;
				return;
			}
		}
		if(j == 503)
		{
			class9.scrollMax = ignoreCount * 15 + 20;
			if(class9.scrollMax <= class9.height)
				class9.scrollMax = class9.height + 1;
			return;
		}
		if(j == 327)
		{
			class9.modelRotation1 = 150;
			class9.modelRotation2 = (int)(Math.sin((double)loopCycle / 40D) * 256D) & 0x7ff;
			if(aBoolean1031)
			{
				for(int k1 = 0; k1 < 7; k1++)
				{
					int l1 = anIntArray1065[k1];
					if(l1 >= 0 && !IDK.cache[l1].method537())
						return;
				}

				aBoolean1031 = false;
				Model aclass30_sub2_sub4_sub6s[] = new Model[7];
				int i2 = 0;
				for(int j2 = 0; j2 < 7; j2++)
				{
					int k2 = anIntArray1065[j2];
					if(k2 >= 0)
					{
					//Model.readAndDump(IDK.cache[k2].method538().modelID);
						aclass30_sub2_sub4_sub6s[i2++] = IDK.cache[k2].method538();
						
					}
				}

				Model model = new Model(i2, aclass30_sub2_sub4_sub6s);
				for(int l2 = 0; l2 < 5; l2++)
					if(anIntArray990[l2] != 0)
					{
						model.method476(anIntArrayArray1003[l2][0], anIntArrayArray1003[l2][anIntArray990[l2]]);
						if(l2 == 1)
							model.method476(anIntArray1204[0], anIntArray1204[anIntArray990[l2]]);
					}

				model.method469();
				model.method470(Animation.anims[myPlayer.anInt1511].anIntArray353[0]);
				model.method479(64, 850, -30, -50, -30, true);
				class9.anInt233 = 5;
				class9.mediaID = 0;
				RSInterface.method208(aBoolean994, model);
			}
			return;
		}
		if(j == 328) {
			RSInterface rsInterface = class9;
			int verticleTilt = 150;
			int animationSpeed = (int)(Math.sin((double)loopCycle / 40D) * 256D) & 0x7ff;
			rsInterface.modelRotation1 = verticleTilt;
			rsInterface.modelRotation2 = animationSpeed;
			if(aBoolean1031) {
				Model characterDisplay = myPlayer.method452();
				for(int l2 = 0; l2 < 5; l2++)
					if(anIntArray990[l2] != 0) {
						characterDisplay.method476(anIntArrayArray1003[l2][0], anIntArrayArray1003[l2][anIntArray990[l2]]);
						if(l2 == 1)
							characterDisplay.method476(anIntArray1204[0], anIntArray1204[anIntArray990[l2]]);
					}
				int staticFrame = myPlayer.anInt1511;
				characterDisplay.method469();
				characterDisplay.method470(Animation.anims[staticFrame].anIntArray353[0]);
				//characterDisplay.method479(64, 850, -30, -50, -30, true);
				rsInterface.anInt233 = 5;
				rsInterface.mediaID = 0;
				RSInterface.method208(aBoolean994, characterDisplay);
			}
			return;
		}
		if(j == 324)
		{
			if(aClass30_Sub2_Sub1_Sub1_931 == null)
			{
				aClass30_Sub2_Sub1_Sub1_931 = class9.sprite1;
				aClass30_Sub2_Sub1_Sub1_932 = class9.sprite2;
			}
			if(aBoolean1047)
			{
				class9.sprite1 = aClass30_Sub2_Sub1_Sub1_932;
				return;
			} else
			{
				class9.sprite1 = aClass30_Sub2_Sub1_Sub1_931;
				return;
			}
		}
		if(j == 325)
		{
			if(aClass30_Sub2_Sub1_Sub1_931 == null)
			{
				aClass30_Sub2_Sub1_Sub1_931 = class9.sprite1;
				aClass30_Sub2_Sub1_Sub1_932 = class9.sprite2;
			}
			if(aBoolean1047)
			{
				class9.sprite1 = aClass30_Sub2_Sub1_Sub1_931;
				return;
			} else
			{
				class9.sprite1 = aClass30_Sub2_Sub1_Sub1_932;
				return;
			}
		}
		if(j == 600)
		{
			class9.message = reportAbuseInput;
			if(loopCycle % 20 < 10)
			{
				class9.message += "|";
				return;
			} else
			{
				class9.message += " ";
				return;
			}
		}
		if(j > 600 && j < 621)
		{
		int id = j-601;
		String[] mod_options = {
		"Moderator Options:",
		"Mute user",
		"Unmute user",
		"Jail user",
		"Unjail user",
		"Ban user",
		"Unban user"
		};
		String[] admin_options = {
		"Admin Options:",
		"Mute users IP",
		"Un-Mute users IP",
		"Ban users IP",
		"Un-Ban users IP",
		"NULL",
		};
		if(id < mod_options.length)
			if(id == 0)
				class9.message = mod_options[id];
			else
				class9.message = (id)+": "+mod_options[id];
		else if(id < mod_options.length+admin_options.length && myPlayer.playerRights > 1)
			if(id-mod_options.length == 0)
				class9.message = admin_options[id-mod_options.length];
			else
				class9.message = (id-mod_options.length)+": "+admin_options[id-mod_options.length];
		}
		if(j == 650 || j == 655)
			if(anInt1193 != 0)
			{
				String s;
				if(daysSinceLastLogin == 0)
					s = "earlier today";
				else
				if(daysSinceLastLogin == 1)
					s = "yesterday";
				else
					s = daysSinceLastLogin + " days ago";
				class9.message = "You last logged in " + s + " from: " + signlink.dns;
			} else
			{
				class9.message = "";
			}
		if(j == 651)
		{
			if(unreadMessages == 0)
			{
				class9.message = "0 unread messages";
				class9.textColor = 0xffff00;
			}
			if(unreadMessages == 1)
			{
				class9.message = "1 unread message";
				class9.textColor = 65280;
			}
			if(unreadMessages > 1)
			{
				class9.message = unreadMessages + " unread messages";
				class9.textColor = 65280;
			}
		}
		if(j == 652)
			if(daysSinceRecovChange == 201)
			{
				if(membersInt == 1)
					class9.message = "@yel@This is a non-members world: @whi@Since you are a member we";
				else
					class9.message = "";
			} else
			if(daysSinceRecovChange == 200)
			{
				class9.message = "You have not yet set any password recovery questions.";
			} else
			{
				String s1;
				if(daysSinceRecovChange == 0)
					s1 = "Earlier today";
				else
				if(daysSinceRecovChange == 1)
					s1 = "Yesterday";
				else
					s1 = daysSinceRecovChange + " days ago";
				class9.message = s1 + " you changed your recovery questions";
			}
		if(j == 653)
			if(daysSinceRecovChange == 201)
			{
				if(membersInt == 1)
					class9.message = "@whi@recommend you use a members world instead. You may use";
				else
					class9.message = "";
			} else
			if(daysSinceRecovChange == 200)
				class9.message = "We strongly recommend you do so now to secure your account.";
			else
				class9.message = "If you do not remember making this change then cancel it immediately";
		if(j == 654)
		{
			if(daysSinceRecovChange == 201)
				if(membersInt == 1)
				{
					class9.message = "@whi@this world but member benefits are unavailable whilst here.";
					return;
				} else
				{
					class9.message = "";
					return;
				}
			if(daysSinceRecovChange == 200)
			{
				class9.message = "Do this from the 'account management' area on our front webpage";
				return;
			}
			class9.message = "Do this from the 'account management' area on our front webpage";
		}
	}

	private void drawSplitPrivateChat(int paddingX, int paddingY) {
		if(splitPrivateChat == 0)
			return;
		TextDrawingArea textdrawingarea = normalFont;
		int i = 0 ;
		if(systemUpdatetime != 0)
			i = 1;
		for(int j = 0; j < 100; j++)
			if(chatMessages[j] != null)
			{
				int k = chatTypes[j];
				String s = chatNames[j];
				byte byte1 = 0;
				if (s != null && s.startsWith("@cr1@")) {
					s = s.substring(5);
					byte1 = 1;
				} 
				if (s != null && s.startsWith("@cr2@")) {
					s = s.substring(5);
					byte1 = 2;
				} 
				if (s != null && s.startsWith("@cr3@")) {
					s = s.substring(5);
					byte1 = 3;
				} 
				if (s != null && s.startsWith("@cr4@")) {
					s = s.substring(5);
					byte1 = 4;
				} 
				if (s != null && s.startsWith("@cr5@")) {
					s = s.substring(5);
					byte1 = 5;
				}
				if (s != null && s.startsWith("@cr6@")) {
					s = s.substring(5);
					byte1 = 6;
				}
			if((k == 3 || k == 7) && (k == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
			{
				int l = (329 + paddingY) - i * 13;
				int k1 = 4;
				textdrawingarea.method385(0, "From", l, k1);
				textdrawingarea.method385(65535, "From", l - 1, k1);
				k1 += textdrawingarea.getTextWidth("From ");
				if(byte1 > 0) {
					modIcons[byte1-1].drawSprite(k1, l - 12);
					k1 += 14;
				}
				textdrawingarea.method385(0, s + ": " + chatMessages[j], l, k1);
				textdrawingarea.method385(65535, s + ": " + chatMessages[j], l - 1, k1);
				if(++i >= 5)
					return;
			}
			if(k == 5 && privateChatMode < 2)
			{
				try
				{
					int i1 = (329 + paddingY) - i * 13;
					textdrawingarea.method385(0, chatMessages[j], i1, 4);
					textdrawingarea.method385(65535, chatMessages[j], i1 - 1, 4);
					if(++i >= 5)
						return;
				} catch(ArrayIndexOutOfBoundsException ex) {
					
				}
			}
			if(k != 6 || privateChatMode >= 2)
				continue;
			int j1 = (329 + paddingY) - i * 13;
			textdrawingarea.method385(0, "To " + s + ": " + chatMessages[j], j1, 4);
			textdrawingarea.method385(65535, "To " + s + ": " + chatMessages[j], j1 - 1, 4);
			if(++i >= 5)
				return;
		}

	}
	
	public void pushMessage(String s, int i, String s1) {
		if(i == 0 && dialogID != -1) {
			aString844 = s;
			super.clickMode3 = 0;
		}
		if(backDialogID == -1)
			inputTaken = true;
		for(int j = 499; j > 0; j--) {
			chatTypes[j] = chatTypes[j - 1];
			chatNames[j] = chatNames[j - 1];
			chatMessages[j] = chatMessages[j - 1];
			chatRights[j] = chatRights[j - 1];
		}
		chatTypes[0] = i;
		chatNames[0] = s1;
		chatMessages[0] = s;
		chatRights[0] = rights;
	}
	
	static int focusTab = 3;
	public static void setTab(int id) {
		if(focusTab == id && toggleFullscreen)
			focusTab = -1;
		else
			focusTab = id;
		tabID = id;
		tabAreaAltered = true;
	}
	
	private void processTabClick(int paddingX, int paddingY) {
		if(invOverlayInterfaceID == -1) {
			if(super.mouseX >= 521 + paddingX && super.mouseX <= 550 + paddingX && super.mouseY >= 169 + (toggleFullscreen ? 261 : 0) + paddingY && super.mouseY < 205 + (toggleFullscreen ? 261 : 0) + paddingY && tabInterfaceIDs[0] != -1) {
				//setTab(0);
				menuActionName[1] = "Combat Styles";
				menuActionID[1] = 1011;
				menuActionRow = 2;
				tabIDHover = 0;
			} else if(super.mouseX >= 552 + paddingX && super.mouseX <= 581 + paddingX && super.mouseY >= 168 + (toggleFullscreen ? 261 : 0) + paddingY && super.mouseY < 205 + (toggleFullscreen ? 261 : 0) + paddingY && tabInterfaceIDs[14] != -1) {
				//setTab(14);
				menuActionName[1] = "Stats";
				menuActionID[1] = 1042;//needs changing.
				menuActionRow = 2;
				tabIDHover = 1;
			} else if(super.mouseX >= 582 + paddingX && super.mouseX <= 611 + paddingX && super.mouseY >= 168 + (toggleFullscreen ? 261 : 0) + paddingY && super.mouseY < 205 + (toggleFullscreen ? 261 : 0) + paddingY && tabInterfaceIDs[1] != -1) {
				//setTab(1);
				menuActionName[1] = "Journal";
				menuActionID[1] = 1012;
				menuActionRow = 2;
				tabIDHover = 2;
			} else if(super.mouseX >= 612 + paddingX && super.mouseX <= 641 + paddingX && super.mouseY >= 168 + (toggleFullscreen ? 261 : 0) + paddingY && super.mouseY < 203 + (toggleFullscreen ? 261 : 0) + paddingY && tabInterfaceIDs[2] != -1) {
				//setTab(2);
				menuActionName[1] = "Staff List";
				menuActionID[1] = 1013;
				menuActionRow = 2;
				tabIDHover = 3;
			} else if(super.mouseX >= 642 + paddingX && super.mouseX <= 671 + paddingX && super.mouseY >= 168 + (toggleFullscreen ? 261 : 0) + paddingY && super.mouseY < 205 + (toggleFullscreen ? 261 : 0) + paddingY && tabInterfaceIDs[3] != -1) {
				//setTab(3);
				menuActionName[1] = "Inventory";
				menuActionID[1] = 1014;
				menuActionRow = 2;
				tabIDHover = 4;
			} else if(super.mouseX >= 672 + paddingX && super.mouseX <= 701 + paddingX && super.mouseY >= 168 + (toggleFullscreen ? 261 : 0) + paddingY && super.mouseY < 205 + (toggleFullscreen ? 261 : 0) + paddingY && tabInterfaceIDs[4] != -1) {
				//setTab(4);
				menuActionName[1] = "Worn Equipment";
				menuActionID[1] = 1015;
				menuActionRow = 2;
				tabIDHover = 5;
			} else if(super.mouseX >= 702 + paddingX && super.mouseX <= 731 + paddingX && super.mouseY >= 169 + (toggleFullscreen ? 261 : 0) + paddingY && super.mouseY < 205 + (toggleFullscreen ? 261 : 0) + paddingY && tabInterfaceIDs[5] != -1) {
				//setTab(5);
				menuActionName[1] = "Prayer List";
				menuActionID[1] = 1016;
				menuActionRow = 2;
				tabIDHover = 6;
			} else if(super.mouseX >= 732 + paddingX && super.mouseX <= 761 + paddingX && super.mouseY >= 169 + (toggleFullscreen ? 261 : 0) + paddingY && super.mouseY < 205 + (toggleFullscreen ? 261 : 0) + paddingY && tabInterfaceIDs[6] != -1) {
				//setTab(6);
				menuActionName[1] = "Magic Spellbook";
				menuActionID[1] = 1017;
				menuActionRow = 2;
				tabIDHover = 7;
			} else if(super.mouseX >= 522 + paddingX && super.mouseX <= 551 + paddingX && super.mouseY >= 466 + paddingY && super.mouseY < 503 + paddingY && tabInterfaceIDs[16] != -1) {
				//setTab(16);
				menuActionName[1] = "Achievments";
				menuActionID[1] = 1043;
				menuActionRow = 2;
				tabIDHover = 14;
			} else if(super.mouseX >= 552 + paddingX && super.mouseX <= 581 + paddingX && super.mouseY >= 466 + paddingY && super.mouseY < 503 + paddingY && tabInterfaceIDs[8] != -1) {
				//setTab(8);
				menuActionName[1] = "Friends List";
				menuActionID[1] = 1019;
				menuActionRow = 2;
				tabIDHover = 8;
			} else if(super.mouseX >= 582 + paddingX && super.mouseX <= 611 + paddingX && super.mouseY >= 466 + paddingY && super.mouseY < 503 + paddingY && tabInterfaceIDs[9] != -1) {
				//setTab(9);
				menuActionName[1] = "Ignore List";
				menuActionID[1] = 1020;
				menuActionRow = 2;
				tabIDHover = 9;
			} else if(super.mouseX >= 612 + paddingX && super.mouseX <= 641 + paddingX && super.mouseY >= 466 + paddingY && super.mouseY < 503 + paddingY && tabInterfaceIDs[7] != -1) {
				//setTab(7);
				menuActionName[1] = "Clan Chat";
				menuActionID[1] = 1018;
				menuActionRow = 2;
				tabIDHover = 10;
			} else if(super.mouseX >= 642 + paddingX && super.mouseX <= 671 + paddingX && super.mouseY >= 466 + paddingY && super.mouseY < 503 + paddingY && tabInterfaceIDs[11] != -1) {
				//setTab(11);
				menuActionName[1] = "Options";
				menuActionID[1] = 1022;
				menuActionRow = 2;
				tabIDHover = 11;
			} else if(super.mouseX >= 672 + paddingX && super.mouseX <= 701 + paddingX && super.mouseY >= 466 + paddingY && super.mouseY < 503 + paddingY && tabInterfaceIDs[12] != -1) {
				//setTab(12);
				menuActionName[1] = "Emotes";
				menuActionID[1] = 1023;
				menuActionRow = 2;
				tabIDHover = 12;
			} else if(super.mouseX >= 702 + paddingX && super.mouseX <= 731 + paddingX && super.mouseY >= 466 + paddingY && super.mouseY < 502 + paddingY && tabInterfaceIDs[13] != -1) {
				//setTab(13);
				menuActionName[1] = "Summoning";
				menuActionID[1] = 1024;
				menuActionRow = 2;
				tabIDHover = 13;
			} else if(super.mouseX >= 732 + paddingX && super.mouseX <= 761 + paddingX && super.mouseY >= 466 + paddingY && super.mouseY < 502 + paddingY && tabInterfaceIDs[15] != -1) {
				//setTab(15);
				menuActionName[1] = "Squeal Of Fortune";
				menuActionID[1] = 1044;
				menuActionRow = 2;
				tabIDHover = 15;
			} else if(super.mouseX >= 523 + paddingX && super.mouseX <= 550 + paddingX && super.mouseY >= 466 + paddingY && super.mouseY < 502 + paddingY && tabInterfaceIDs[13] != -1) {
				//setTab(13);
				menuActionName[1] = "Achievments";
				menuActionID[1] = 1064;
				menuActionRow = 2;
				tabIDHover = 16;
			} else {
				tabIDHover = -1;
				tabAreaAltered = true;
			}
		}
	}

	private void resetImageProducers2() {
		if(chatBackImage != null)
			return;
		nullLoader();
		rightFrame = null;
		super.fullGameScreen = null;
		aRSImageProducer_1107 = null;
		aRSImageProducer_1108 = null;
		aRSImageProducer_1109 = null;
		aRSImageProducer_1110 = null;
		aRSImageProducer_1111 = null;
		aRSImageProducer_1112 = null;
		aRSImageProducer_1113 = null;
		aRSImageProducer_1114 = null;
		aRSImageProducer_1115 = null;
		chatBackImage = new RSImageProducer(xPaddingtabBack, yPaddingtabBack, getGameComponent());
		mapBackImage = new RSImageProducer(miniMapWidth, miniMapHeight, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		mapBack[0].drawBackground(0, 0);
		rightFrame = new RSImageProducer(3, 170, getGameComponent());
		sideTabArea = new RSImageProducer(246, 335, getGameComponent());
		main3DArea = new RSImageProducer(512, 334, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		new RSImageProducer(496, 50, getGameComponent());
		new RSImageProducer(269, 37, getGameComponent());
		aRSImageProducer_1125 = new RSImageProducer(249, 45, getGameComponent());
		welcomeScreenRaised = true;
	}

private String getDocumentBaseHost()
	{
		if(signlink.mainapp != null)
			return signlink.mainapp.getDocumentBase().getHost().toLowerCase();
		if(super.gameFrame != null)
			return "";
		else
			return "";
	}

	private void drawMapSprites(Sprite sprite, int x, int y) {
	if (sprite == null)
	return;
		try {
			int xPadding = 0, yPadding = 0;
			if(toggleFullscreen) {
				xPadding = xPaddingmapBackImage + getMapIncreaseX();
				yPadding = yPaddingmapBackImage + getMapIncreaseY();
			}
			int rotation = compassRotation + mapAmtDiffRotation & 0x7ff;
			int distance = x * x + y * y;
			if(distance > 6400)
				return;
			int spriteX = (Model.modelIntArray1[rotation] * 256) / (mapAmtDiffZoom + 256);
			int spriteY = (Model.modelIntArray2[rotation] * 256) / (mapAmtDiffZoom + 256);
			int drawX = y * spriteX + x * spriteY >> 16;
			int drawY = y * spriteY - x * spriteX >> 16;
			int finalX = ((mapSpritesX + drawX) - sprite.maxWidth / 2) + 4;
			int finalY = ((mapSpritesY - drawY) - sprite.maxHeight / 2) - 4;//86
			//if(distance > 2500) {
				//sprite.drawWithinSprite(finalX, finalY, (toggleFullscreen ? miniMapBack[2] : miniMapBack[1]));
			//} else {
				sprite.drawSprite(finalX + xPadding, finalY + yPadding);
			//}
		} catch(ArrayIndexOutOfBoundsException ex) {
			
		}
	}
	
	private void drawMapArrows(Sprite sprite, int x, int y) {
		int distance = x * x + y * y;
		int xPadding = 0, yPadding = 0;
		if(toggleFullscreen) {
			xPadding = xPaddingmapBackImage + getMapIncreaseX();
			yPadding = yPaddingmapBackImage + getMapIncreaseY();
		}
		if(distance > 4225 && distance < 90000) {
		} else {
			drawMapSprites(sprite, x + xPadding, y + yPadding);
		}
	}
	
	public boolean isInArea(int clickX, int clickY, int width, int height)
	{
		if(super.mouseX > clickX && super.mouseY > clickY && super.mouseX < clickX + width && super.mouseY < clickY + height)
			return true;
		else
			return false;
	}
	
	public boolean clickInArea(int clickX, int clickY, int width, int height)
	{
		if(super.saveClickX > clickX && super.saveClickY > clickY && super.saveClickX < clickX + width && super.saveClickY < clickY + height)
			return true;
		else
			return false;
	}

	public boolean inCircle(int circleX, int circleY, int radius)
	{
		return java.lang.Math.pow((circleX+radius - super.mouseX),2) + java.lang.Math.pow((circleY+radius - super.mouseY),2) < java.lang.Math.pow(radius,2);
	}
	
	private void rightClickMapArea(int paddingX, int paddingY) {
		if(!toggleFullscreen) {
		if(!coinToggle){
			if (super.mouseX >= 512 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118) {
				menuActionName[4] = coinToggle ? "Toggle money pouch" : "Toggle money pouch";
				menuActionID[4] = 712;
				menuActionName[3] = "Withdraw money pouch";
				menuActionID[3] = 713;
				promptInput = "";
				menuActionName[2] = "Examine money pouch";
				menuActionID[2] = 714;
				menuActionName[1] = "Price checker";
				menuActionID[1] = 715;
				menuActionRow = 5;
			}
		} else if(coinToggle){
			if (super.mouseX >= 436 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118) {
				menuActionName[4] = coinToggle ? "Toggle money pouch" : "Toggle money pouch";
				menuActionID[4] = 712;
				menuActionName[3] = "Withdraw money pouch";
				menuActionID[3] = 713;
				menuActionName[2] = "Examine money pouch";
				menuActionID[2] = 714;
				menuActionName[1] = "Price checker";
				menuActionID[1] = 715;
				menuActionRow = 5;
			}
		}
		}
		if(!contextMenuOpen) {
			processXPCounterClick(getMapIncreaseX(),getMapIncreaseY());
			if(isInArea(xPaddingmapBackImage + (toggleFullscreen ? 207-52 : 207) + paddingX,0 + paddingY,21,21)) {
				menuActionName[menuActionRow] = "Advisor";
				menuActionID[menuActionRow] = 1051;
				menuActionRow++;
				advisorHover = (super.clickMode2 == 1) ? 2: 1;
			} else {
				advisorHover = 0;
			}
			if(isInArea(xPaddingmapBackImage + (toggleFullscreen ? 228-52 : 228) + paddingX,0 + paddingY,21,21) && (tabInterfaceIDs[10] != -1 && invOverlayInterfaceID == -1)) {
				menuActionName[menuActionRow] = "Exit";
				menuActionID[menuActionRow] = 1021;
				menuActionRow++;
				exitHover = (super.clickMode2 == 1) ? 2: 1;
			} else {
				exitHover = 0;
			}
			if(isInArea(xPaddingmapBackImage + (toggleFullscreen ? -44 : 145) + mapMovedX + paddingX,yPaddingmapBackImage + (toggleFullscreen ? 38 : 10) + mapMovedY + paddingY,57,34) && orbToggle[0]) {
				menuActionName[menuActionRow] = "Use cure (p)";
				menuActionID[menuActionRow] = 1052;
				menuActionRow++;
				orbHover = 0;
			} else if(isInArea(xPaddingmapBackImage + (toggleFullscreen ? -47 : 161) + mapMovedX + paddingX,yPaddingmapBackImage + (toggleFullscreen ? 72 : 49) + mapMovedY + paddingY,57,34)) {
				String prayerType = (prayerInterfaceType == 5608) ? "prayers" : "curses";
				boolean inProcess = (tabInterfaceIDs[5] == 17200 || tabInterfaceIDs[5] == 17234);
				menuActionName[menuActionRow] = (inProcess ? "Finish" : "Select") + " " + "quick " + prayerType + (inProcess ? " selection" : "");
				menuActionID[menuActionRow] = 1046;
				menuActionRow++;
				menuActionName[menuActionRow] = "Turn quick " + prayerType + " " + (orbToggle[1] ? "off" : "on");
				menuActionID[menuActionRow] = 1045;
				menuActionRow++;
				orbHover = 1;
			} else if(isInArea(xPaddingmapBackImage + (toggleFullscreen ? -35 : 161) + mapMovedX + paddingX,yPaddingmapBackImage + (toggleFullscreen ? 106 : 88) + mapMovedY + paddingY,57,34)) {
				menuActionName[menuActionRow] = "Rest";
				menuActionID[menuActionRow] = 1048;
				menuActionRow++;
				menuActionName[menuActionRow] = "Turn run mode " + (orbToggle[2] ? "off" : "on");
				menuActionID[menuActionRow] = 1047;
				menuActionRow++;
				orbHover = 2;
			} else if(isInArea(xPaddingmapBackImage + (toggleFullscreen ? -12 : 145) + mapMovedX + paddingX,yPaddingmapBackImage + (toggleFullscreen ? 135 : 127) + mapMovedY + paddingY,57,34)) {
				menuActionName[menuActionRow] = "Select left-click option";
				menuActionID[menuActionRow] = 1053;
				menuActionRow++; 
				menuActionName[menuActionRow] = "Call Follower";
				menuActionID[menuActionRow] = 1054;
				menuActionRow++;
				menuActionName[menuActionRow] = "Dismiss";
				menuActionID[menuActionRow] = 1055;
				menuActionRow++;
				menuActionName[menuActionRow] = "Take BoB";
				menuActionID[menuActionRow] = 1056;
				menuActionRow++;
				menuActionName[menuActionRow] = "Renew Familiar";
				menuActionID[menuActionRow] = 1057;
				menuActionRow++;
				menuActionName[menuActionRow] = "Attack";
				menuActionID[menuActionRow] = 1058;
				menuActionRow++;
				menuActionName[menuActionRow] = "Follower Details";
				menuActionID[menuActionRow] = 1059;
				menuActionRow++;
				orbHover = 3;
			} else {
				if(orbHover != -1)
					orbHover = -1;
			}
			if(inCircle(xPaddingmapBackImage + (toggleFullscreen ? 30 : 11) + paddingX,yPaddingmapBackImage + (toggleFullscreen ? 9 : 8) + paddingY,17)) {
				menuActionName[menuActionRow] = "Face north";
				menuActionID[menuActionRow] = 1049;
				menuActionRow++;
			}
			if(inCircle((toggleFullscreen ? 670 : 526) + paddingX,(toggleFullscreen ? 138 : 124),17)) {
				//menuActionName[menuActionRow] = "Vote4Cash";
				menuActionName[menuActionRow] = "World map";
				menuActionID[menuActionRow] = 1050;
				menuActionRow++;
				mapHover = 1;
			} else {
				mapHover = 0;
			}
		}
	}

	private void processChatButtonClick(int paddingX, int paddingY)
	{
		if(super.mouseX >= 5 + paddingX && super.mouseX <= 61 + paddingX && super.mouseY >= 482 + paddingY && super.mouseY <= 503 + paddingY)
		{
			menuActionName[menuActionRow] = "View All";
			menuActionID[menuActionRow] = 999;
			menuActionRow++;
		} else if(super.mouseX >= 71 + paddingX && super.mouseX <= 127 + paddingX && super.mouseY >= 482 + paddingY && super.mouseY <= 503 + paddingY)
		{
			menuActionName[menuActionRow] = "View Game";
			menuActionID[menuActionRow] = 998;
			menuActionRow++;
		} else if(super.mouseX >= 137 + paddingX && super.mouseX <= 193 + paddingX && super.mouseY >= 482 + paddingY && super.mouseY <= 503 + paddingY)
		{
			menuActionName[menuActionRow] = "Hide public";
			menuActionID[menuActionRow] = 997;
			menuActionRow++;
			menuActionName[menuActionRow] = "Off public";
			menuActionID[menuActionRow] = 996;
			menuActionRow++;
			menuActionName[menuActionRow] = "Friends public";
			menuActionID[menuActionRow] = 995;
			menuActionRow++;
			menuActionName[menuActionRow] = "On public";
			menuActionID[menuActionRow] = 994;
			menuActionRow++;
			menuActionName[menuActionRow] = "View public";
			menuActionID[menuActionRow] = 993;
			menuActionRow++;
		} else if(super.mouseX >= 203 + paddingX && super.mouseX <= 259 + paddingX && super.mouseY >= 482 + paddingY && super.mouseY <= 503 + paddingY)
		{
			menuActionName[menuActionRow] = "Off private";
			menuActionID[menuActionRow] = 992;
			menuActionRow++;
			menuActionName[menuActionRow] = "Friends private";
			menuActionID[menuActionRow] = 991;
			menuActionRow++;
			menuActionName[menuActionRow] = "On private";
			menuActionID[menuActionRow] = 990;
			menuActionRow++;
			menuActionName[menuActionRow] = "View private";
			menuActionID[menuActionRow] = 989;
			menuActionRow++;
		} else if(super.mouseX >= 269 + paddingX && super.mouseX <= 325 + paddingX && super.mouseY >= 482 + paddingY && super.mouseY <= 503 + paddingY)
		{
			menuActionName[menuActionRow] = "Off clan chat";
			menuActionID[menuActionRow] = 1003;
			menuActionRow++;
			menuActionName[menuActionRow] = "Friends clan chat";
			menuActionID[menuActionRow] = 1002;
			menuActionRow++;
			menuActionName[menuActionRow] = "On clan chat";
			menuActionID[menuActionRow] = 1001;
			menuActionRow++;
			menuActionName[menuActionRow] = "View clan chat";
			menuActionID[menuActionRow] = 1000;
			menuActionRow++;
		} else if(super.mouseX >= 335 + paddingX && super.mouseX <= 391 + paddingX && super.mouseY >= 482 + paddingY && super.mouseY <= 503 + paddingY)
		{
			menuActionName[menuActionRow] = "Off trade";
			menuActionID[menuActionRow] = 987;
			menuActionRow++;
			menuActionName[menuActionRow] = "Friends trade";
			menuActionID[menuActionRow] = 986;
			menuActionName[menuActionRow] = "On trade";
			menuActionID[menuActionRow] = 985;
			menuActionRow++;
			menuActionName[menuActionRow] = "View trade";
			menuActionID[menuActionRow] = 984;
			menuActionRow++;
		} else if(super.mouseX >= 404 && super.mouseX <= 514 && super.mouseY >= 480 + paddingY && super.mouseY <= 501 + paddingY) {
			menuActionName[menuActionRow] = "Report abuse";
			menuActionID[menuActionRow] = 606;
			menuActionRow++;
		}
	}

	public void processRightClick() {
		if (activeInterfaceType != 0) {
			return;
		}
		menuActionName[0] = "Cancel";
		menuActionID[0] = 1107;
		menuActionRow = 1;
		if (fullscreenInterfaceID != -1) {
			hoverChildId = 0;
			hoverToggleChildId = 0;
			buildInterfaceMenu(8, RSInterface.interfaceCache[fullscreenInterfaceID], super.mouseX, 8, super.mouseY, 0);
			if (hoverChildId != lastHoverChildId) {
				lastHoverChildId = hoverChildId;
			}
			if (hoverToggleChildId != lastHoverToggleChildId) {
				lastHoverToggleChildId = hoverToggleChildId;
			}
			return;
		}
		buildSplitPrivateChatMenu(0,getChatBackIncreaseY());
		hoverChildId = 0;
		hoverToggleChildId = 0;
		int width = toggleFullscreen ? getScreenWidth() : 516;
		int height = toggleFullscreen ? getScreenHeight() : 338;	
		if (super.mouseX > 0 && super.mouseY > 0 && super.mouseX < width && super.mouseY < height
		&& !(!toggleFullscreen && super.mouseX > 519 + getTabIncreaseX() && super.mouseY > 168 + getTabIncreaseY() && super.mouseX < 519 + 246 + getTabIncreaseX() && super.mouseY < 168 + 335 + getTabIncreaseY())//Is not in inventory
		&& !(toggleFullscreen && focusTab != -1
		//X end and Y start
		&& super.mouseX > (45-23 + xPaddingtabBack + getTabIncreaseX())
		&& super.mouseY > (55-23-46 + yPaddingtabBack + getTabIncreaseY())
		&& super.mouseX < (45-23 + xPaddingtabBack + getTabIncreaseX() + 204)
		&& super.mouseY < (55-23-46 + yPaddingtabBack + getTabIncreaseY() + 275)
		)//Is not in inventory
		&& !(super.mouseX > 0 && super.mouseY > 338 + getChatBackIncreaseY() && super.mouseX < 517 && super.mouseY < 503 + getChatBackIncreaseY())//Is not in chat area
		&& !(super.mouseX > xPaddingmapBackImage + getMapIncreaseX() + (toggleFullscreen ? -25 : 0) && super.mouseY > 0 && super.mouseX < xPaddingmapBackImage + getMapIncreaseX() + 249 && super.mouseY < yPaddingmapBackImage + getMapIncreaseY() + 168)//Is not in map
		) {
			if (openInterfaceID != -1) {
				RSInterface rsInterface = RSInterface.interfaceCache[openInterfaceID];
				if(toggleFullscreen) {
					int interfaceWidth = toggleFullscreen ? getScreenWidth() : 516;
					int interfaceHeight = toggleFullscreen ? getScreenHeight() : 338;
					buildInterfaceMenu(4 + (interfaceWidth - rsInterface.width) / 2, rsInterface, super.mouseX, 4 + (interfaceHeight - rsInterface.height) / 2, super.mouseY, 0);
				} else
					buildInterfaceMenu(4, rsInterface, super.mouseX, 4, super.mouseY, 0);
			} else {
				build3dScreenMenu();
			}
		}
		if (hoverChildId != lastHoverChildId) {
			lastHoverChildId = hoverChildId;
		}
		if (hoverToggleChildId != lastHoverToggleChildId) {
			lastHoverToggleChildId = hoverToggleChildId;
		}
		hoverChildId = 0;
		hoverToggleChildId = 0;
		if(super.mouseX > 548 + getTabIncreaseX() && super.mouseY > 207 - (toggleFullscreen ? 46 : 0) + getTabIncreaseY() && super.mouseX < 740 + getTabIncreaseX() && super.mouseY < 468 + getTabIncreaseY()) {
			if(invOverlayInterfaceID != -1) {
				buildInterfaceMenu(547 + getTabIncreaseX(), RSInterface.interfaceCache[invOverlayInterfaceID], super.mouseX, 205 - (toggleFullscreen ? 46 : 0) + getTabIncreaseY(), super.mouseY, 0);
			} else if(tabInterfaceIDs[tabID] != -1) {
				if(focusTab != -1)
					buildInterfaceMenu(547 + getTabIncreaseX(), RSInterface.interfaceCache[tabInterfaceIDs[tabID]], super.mouseX, 205 - (toggleFullscreen ? 46 : 0) + getTabIncreaseY(), super.mouseY, 0);
			}
		}
		if (hoverChildId != anInt1048) {
			tabAreaAltered = true;
			anInt1048 = hoverChildId;
		}
		if (hoverToggleChildId != anInt1044) {
			tabAreaAltered = true;
			anInt1044 = hoverToggleChildId;
		}
		hoverChildId = 0;
		hoverToggleChildId = 0;
		if(super.mouseX > 0 && super.mouseY > 338 + getChatBackIncreaseY() && super.mouseX < 490 && super.mouseY < 503 + getChatBackIncreaseY()) {
			if(backDialogID != -1) {
				buildInterfaceMenu(20, RSInterface.interfaceCache[backDialogID], super.mouseX, 358 + getChatBackIncreaseY(), super.mouseY, 0);
			} else if(super.mouseY < 503 + getChatBackIncreaseY() && super.mouseX < 490 + getChatBackIncreaseY()) {
				buildChatAreaMenu(super.mouseY - (yPaddingchatBackImage + getChatBackIncreaseY()));
			}
		}
		if (backDialogID != -1 && hoverChildId != anInt1039) {
			inputTaken = true;
			anInt1039 = hoverChildId;
		}
		if (backDialogID != -1 && hoverToggleChildId != anInt1500) {
			inputTaken = true;
			anInt1500 = hoverToggleChildId;
		}
		/* Enable custom right click areas */
		
		processChatButtonClick(0, getChatBackIncreaseY());
		rightClickMapArea(getMapIncreaseX(),getMapIncreaseY());
		processTabClick(getTabIncreaseX(),getTabIncreaseY());
alertHandler.processMouse(super.mouseX, super.mouseY);
		/**/
		boolean flag = false;
		while (!flag) {
			flag = true;
			for (int j = 0; j < menuActionRow - 1; j++) {
				if (menuActionID[j] < 1000 && menuActionID[j + 1] > 1000) {
					String s = menuActionName[j];
					menuActionName[j] = menuActionName[j + 1];
					menuActionName[j + 1] = s;
					int k = menuActionID[j];
					menuActionID[j] = menuActionID[j + 1];
					menuActionID[j + 1] = k;
					k = menuActionCmd2[j];
					menuActionCmd2[j] = menuActionCmd2[j + 1];
					menuActionCmd2[j + 1] = k;
					k = menuActionCmd3[j];
					menuActionCmd3[j] = menuActionCmd3[j + 1];
					menuActionCmd3[j + 1] = k;
					k = menuActionCmd1[j];
					menuActionCmd1[j] = menuActionCmd1[j + 1];
					menuActionCmd1[j + 1] = k;
					flag = false;
				}
			}
		}
	}
public void WhatWorldIsWhatIp () { 
		if(world == 1){
		serverip = "127.0.0.1";
		port = 43594;
		} else {
		if(world == 2){
		serverip = "127.0.0.1";
		port = 43594;
		}
		}
		}
		
	private int method83(int i, int j, int k)
	{
		int l = 256 - k;
		return ((i & 0xff00ff) * l + (j & 0xff00ff) * k & 0xff00ff00) + ((i & 0xff00) * l + (j & 0xff00) * k & 0xff0000) >> 8;
	}

		private void login(String s, String s1, boolean flag) {
		signlink.errorname = s;
		String s2 = "error";
		try {
			if(myUsername.length() < 1 && myPassword.length() < 1) {
				loginMessage2 = "Please enter a valid username & password";
				return;
			} else if(myUsername.length() < 1) {
				loginMessage2 = "Please enter a valid username";
				return;
			} else if(myPassword.length() < 1) {
				loginMessage2 = "Please enter a valid password";
				return;
			}
			if (!flag) {
				loginMessage1 = "";
				loginMessage2 = "@whi@Connecting to server...";
				drawLoginScreen(true);
			}
			//socketStream = new RSSocket(this, openSocket(43594 + portOff));
			socketStream = new RSSocket(this, openSocket(43594 + portOff));
			WhatWorldIsWhatIp();
			long l = TextClass.longForName(s2);
			int i = (int) (l >> 16 & 31L);
			stream.currentOffset = 0;
			stream.writeWordBigEndian(14);
			stream.writeWordBigEndian(i);
			socketStream.queueBytes(2, stream.buffer);
			for (int j = 0; j < 8; j++)
				socketStream.read();

			int k = socketStream.read();
			int i1 = k;
			if (k == 0) {
				socketStream.flushInputStream(inStream.buffer, 8);
				inStream.currentOffset = 0;
				aLong1215 = inStream.readQWord();
				int ai[] = new int[4];
				ai[0] = (int) (Math.random() * 99999999D);
				ai[1] = (int) (Math.random() * 99999999D);
				ai[2] = (int) (aLong1215 >> 32);
				ai[3] = (int) aLong1215;
				stream.currentOffset = 0;
				stream.writeWordBigEndian(10);
				stream.writeDWord(ai[0]);
				stream.writeDWord(ai[1]);
				stream.writeDWord(ai[2]);
				stream.writeDWord(ai[3]);
				stream.writeDWord(66662);
				stream.writeString(s);
				stream.writeString(s1);
				stream.doKeys();
				aStream_847.currentOffset = 0;
				if (flag)
					aStream_847.writeWordBigEndian(18);
				else
					aStream_847.writeWordBigEndian(16);
				aStream_847.writeWordBigEndian(stream.currentOffset + 36 + 1
						+ 1 + 2);
				aStream_847.writeWordBigEndian(558);
				aStream_847.writeWord(145451);
				aStream_847.writeWordBigEndian(6);
				for (int l1 = 0; l1 < 9; l1++)
					aStream_847.writeDWord(expectedCRCs[l1]);

				aStream_847.writeBytes(stream.buffer, stream.currentOffset, 0);
				stream.encryption = new ISAACRandomGen(ai);
				for (int j2 = 0; j2 < 4; j2++)
					ai[j2] += 50;

				encryption = new ISAACRandomGen(ai);
				socketStream.queueBytes(aStream_847.currentOffset,
						aStream_847.buffer);
				k = socketStream.read();
				stream.writeDWord(352461264); // UID ANTI CHEATCLIENT DONT REMOVE THIS SHIT
			}
										if (isFullScreen) {
					sendFrame126("@red@Off", 37058);
					sendFrame126("@red@Off", 37059);
					sendFrame126("@red@Off", 37060);
					sendFrame126("@red@Off", 37061);
					sendFrame126("@red@Off", 37062);
					sendFrame126("@red@Off", 37063);
					sendFrame126("@red@Off", 37064);
					sendFrame126("@red@Off", 37065);
				} else {
					sendFrame126("@red@Off", 37058);
					sendFrame126("@red@Off", 37059);
					sendFrame126("@red@Off", 37060);
					sendFrame126("@red@Off", 37061);
					sendFrame126("@red@Off", 37062);
					sendFrame126("@red@Off", 37063);
					sendFrame126("@red@Off", 37064);
					sendFrame126("@red@Off", 37065);
				}
			if (k == 1) {
				try {
					Thread.sleep(2000L);
				} catch (Exception _ex) {
				}
				login(s, s1, flag);
				return;
			}
			if(k == 2)
			{
				setDetails();
				int pright = socketStream.read();
				flagged = socketStream.read() == 1;
				aLong1220 = 0L;
				anInt1022 = 0;
				mouseDetection.coordsIndex = 0;
				super.awtFocus = true;
				aBoolean954 = true;
				loggedIn = true;
				stream.currentOffset = 0;
				inStream.currentOffset = 0;
				pktType = -1;
				anInt841 = -1;
				anInt842 = -1;
				anInt843 = -1;
				pktSize = 0;
				anInt1009 = 0;
				systemUpdatetime = 0;
				anInt1011 = 0;
				hintType = 0;
				menuActionRow = 0;
				contextMenuOpen = false;
				super.idleTime = 0;
				for(int j1 = 0; j1 < 100; j1++)
					chatMessages[j1] = null;
				summoningAttack = false;
				itemSelected = 0;
				spellSelected = 0;
				loadingStage = 0;
				anInt1062 = 0;
				anInt1278 = (int)(Math.random() * 100D) - 50;
				anInt1131 = (int)(Math.random() * 110D) - 55;
				anInt896 = (int)(Math.random() * 80D) - 40;
				mapAmtDiffRotation = (int)(Math.random() * 120D) - 60;
				mapAmtDiffZoom = (int)(Math.random() * 30D) - 20;
				compassRotation = (int)(Math.random() * 20D) - 10 & 0x7ff;
				minimapCover = 0;
				anInt985 = -1;
				destX = 0;
				destY = 0;
				playerCount = 0;
				npcCount = 0;
				for (int i2 = 0; i2 < maxPlayers; i2++) {
					playerArray[i2] = null;
					aStreamArray895s[i2] = null;
				}

				for (int k2 = 0; k2 < 16384; k2++)
					npcArray[k2] = null;

				myPlayer = playerArray[myPlayerIndex] = new Player();
				myPlayer.playerRights = pright;// Sets your player rights.
				aClass19_1013.removeAll();
				aClass19_1056.removeAll();
				for (int l2 = 0; l2 < 4; l2++) {
					for (int i3 = 0; i3 < 104; i3++) {
						for (int k3 = 0; k3 < 104; k3++)
							groundArray[l2][i3][k3] = null;

					}

				}

				aClass19_1179 = new NodeList();
				fullscreenInterfaceID = -1;
				anInt900 = 0;
				friendsCount = 0;
				dialogID = -1;
				backDialogID = -1;
				openInterfaceID = -1;
				invOverlayInterfaceID = -1;
				walkableInterfaceID = -1;
				aBoolean1149 = false;
				tabID = 3;
				inputDialogState = 0;
				contextMenuOpen = false;
				messagePromptRaised = false;
				aString844 = null;
				displayOverLayIcon = 0;
				tabIconFlash = -1;
				aBoolean1047 = true;
				method45();
				for (int j3 = 0; j3 < 5; j3++)
					anIntArray990[j3] = 0;

				for (int l3 = 0; l3 < 5; l3++) {
					atPlayerActions[l3] = null;
					atPlayerArray[l3] = false;
				}

				anInt1175 = 0;
				anInt1134 = 0;
				anInt986 = 0;
				anInt1288 = 0;
				anInt924 = 0;
				anInt1188 = 0;
				anInt1155 = 0;
				anInt1226 = 0;
				@SuppressWarnings("unused")
				int anInt941 = 0;
				@SuppressWarnings("unused")
				int anInt1260 = 0;
				resetImageProducers2();
				return;
			}
										if (isFullScreen) {
					sendFrame126("@red@Off", 37059);
					sendFrame126("@red@Off", 37061);
					sendFrame126("@red@Off", 37062);
					sendFrame126("@red@Off", 37063);
					sendFrame126("@red@Off", 37064);
					sendFrame126("@red@Off", 37065);
				} else {
					sendFrame126("@red@Off", 37059);
					sendFrame126("@red@Off", 37061);
					sendFrame126("@red@Off", 37062);
					sendFrame126("@red@Off", 37063);
					sendFrame126("@red@Off", 37064);
					sendFrame126("@red@Off", 37065);
				}
			if (k == 3) {
				loginMessage1 = "";
				loginMessage2 = "@whi@Invalid username or password.";
				return;
			}
			if (k == 4) {
				loginMessage1 = "@whi@Your account has been disabled.";
				loginMessage2 = "@whi@Please check your message-center for details.";
				return;
			}
			if (k == 5) {
				loginMessage1 = "@whi@Your account is already logged in.";
				loginMessage2 = "@whi@If you have just left another world be patient.";
				return;
			}
			if (k == 6) {
				loginMessage1 = "@whi@Divinationhas been updated!";
				loginMessage2 = "@whi@Please reload this page.";
				return;
			}
			if (k == 7) {
				loginMessage1 = "@whi@This world is full.";
				loginMessage2 = "@whi@Please use a different world.";
				return;
			}
			if (k == 8) {
				loginMessage1 = "@whi@Unable to connect.";
				loginMessage2 = "@whi@Login server offline.";
				return;
			}
			if (k == 9) {
				loginMessage1 = "@whi@Login limit exceeded.";
				loginMessage2 = "@whi@Too many connections from your address.";
				return;
			}
			if (k == 10) {
				loginMessage1 = "@whi@Unable to connect.";
				loginMessage2 = "@whi@Bad session id.";
				return;
			}
			if (k == 11) {
				loginMessage1 = "@whi@Login server rejected session.";
				loginMessage2 = "@whi@Please try again.";
				return;
			}
			if (k == 12) {
				loginMessage1 = "@whi@You need a members account to login to this world.";
				loginMessage2 = "@whi@Please subscribe, or use a different world.";
				return;
			}
			if (k == 13) {
				loginMessage1 = "@whi@Could not complete login.";
				loginMessage2 = "@whi@Please try using a different world.";
				return;
			}
			if (k == 14) {
				loginMessage1 = "@whi@The server is being updated.";
				loginMessage2 = "@whi@Please wait 1 minute and try again.";
				return;
			}
			if(k == 15)
			{
				loggedIn = true;
				stream.currentOffset = 0;
				inStream.currentOffset = 0;
				pktType = -1;
				anInt841 = -1;
				anInt842 = -1;
				anInt843 = -1;
				pktSize = 0;
				anInt1009 = 0;
				systemUpdatetime = 0;
				menuActionRow = 0;
				contextMenuOpen = false;
				aLong824 = System.currentTimeMillis();
				return;
			}
			if (k == 16) {
				loginMessage1 = "@whi@Login attempts exceeded.";
				loginMessage2 = "@whi@Please wait 1 minute and try again.";
				return;
			}
			if (k == 17) {
				loginMessage1 = "@whi@You are standing in a members-only area.";
				loginMessage2 = "@whi@To play on this world move to a free area first";
				return;
			}
			if (k == 20) {
				loginMessage1 = "@whi@Invalid loginserver requested";
				loginMessage2 = "@whi@Please try using a different world.";
				return;
			}
			if (k == 21) {
				for (int k1 = socketStream.read(); k1 >= 0; k1--) {
					loginMessage1 = "@whi@You have only just left another world";
					loginMessage2 = "@whi@Your profile will be transferred in: "
							+ k1 + " seconds";
					drawLoginScreen(true);
					try {
						Thread.sleep(1000L);
					} catch (Exception _ex) {
					}
				}

				login(s, s1, flag);
				return;
			}
			if (k == -1) {
				if (i1 == 0) {
					if (loginFailures < 2) {
						try {
							Thread.sleep(2000L);
						} catch (Exception _ex) {
						}
						loginFailures++;
						login(s, s1, flag);
						return;
					} else {
						loginMessage1 = "@whi@Invalid UID Specified.";
						loginMessage2 = "@whi@Make sure your using correct client.";
						return;
					}
				} else {
					loginMessage1 = "@whi@No response from server";
					loginMessage2 = "@whi@Please try using a different world.";
					return;
				}
			} else {
				// System.out.println("response:" + k);
				loginMessage1 = "@whi@Unexpected server response";
				loginMessage2 = "@whi@Please try using a different world.";
				return;
			}
		} catch (IOException _ex) {
			loginMessage1 = "";
		}
		loginMessage2 = "@whi@Error connecting to server.";
	}

	private boolean doWalkTo(int i, int j, int k, int i1, int j1, int k1, int l1, int i2, int j2, boolean flag, int k2) {
		byte byte0 = 104;
		byte byte1 = 104;
		for(int l2 = 0; l2 < byte0; l2++) {
			for(int i3 = 0; i3 < byte1; i3++) {
				anIntArrayArray901[l2][i3] = 0;
				anIntArrayArray825[l2][i3] = 0x5f5e0ff;
			}
		}
		int j3 = j2;
		int k3 = j1;
		anIntArrayArray901[j2][j1] = 99;
		anIntArrayArray825[j2][j1] = 0;
		int l3 = 0;
		int i4 = 0;
		bigX[l3] = j2;
		bigY[l3++] = j1;
		boolean flag1 = false;
		int j4 = bigX.length;
		int ai[][] = aClass11Array1230[plane].anIntArrayArray294;
		while(i4 != l3) 
		{
			j3 = bigX[i4];
			k3 = bigY[i4];
			i4 = (i4 + 1) % j4;
			if(j3 == k2 && k3 == i2)
			{
				flag1 = true;
				break;
			}
			if(i1 != 0)
			{
				if((i1 < 5 || i1 == 10) && aClass11Array1230[plane].method219(k2, j3, k3, j, i1 - 1, i2))
				{
					flag1 = true;
					break;
				}
				if(i1 < 10 && aClass11Array1230[plane].method220(k2, i2, k3, i1 - 1, j, j3))
				{
					flag1 = true;
					break;
				}
			}
			if(k1 != 0 && k != 0 && aClass11Array1230[plane].method221(i2, k2, j3, k, l1, k1, k3))
			{
				flag1 = true;
				break;
			}
			int l4 = anIntArrayArray825[j3][k3] + 1;
			if(j3 > 0 && anIntArrayArray901[j3 - 1][k3] == 0 && (ai[j3 - 1][k3] & 0x1280108) == 0)
			{
				bigX[l3] = j3 - 1;
				bigY[l3] = k3;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3 - 1][k3] = 2;
				anIntArrayArray825[j3 - 1][k3] = l4;
			}
			if(j3 < byte0 - 1 && anIntArrayArray901[j3 + 1][k3] == 0 && (ai[j3 + 1][k3] & 0x1280180) == 0)
			{
				bigX[l3] = j3 + 1;
				bigY[l3] = k3;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3 + 1][k3] = 8;
				anIntArrayArray825[j3 + 1][k3] = l4;
			}
			if(k3 > 0 && anIntArrayArray901[j3][k3 - 1] == 0 && (ai[j3][k3 - 1] & 0x1280102) == 0)
			{
				bigX[l3] = j3;
				bigY[l3] = k3 - 1;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3][k3 - 1] = 1;
				anIntArrayArray825[j3][k3 - 1] = l4;
			}
			if(k3 < byte1 - 1 && anIntArrayArray901[j3][k3 + 1] == 0 && (ai[j3][k3 + 1] & 0x1280120) == 0)
			{
				bigX[l3] = j3;
				bigY[l3] = k3 + 1;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3][k3 + 1] = 4;
				anIntArrayArray825[j3][k3 + 1] = l4;
			}
			if(j3 > 0 && k3 > 0 && anIntArrayArray901[j3 - 1][k3 - 1] == 0 && (ai[j3 - 1][k3 - 1] & 0x128010e) == 0 && (ai[j3 - 1][k3] & 0x1280108) == 0 && (ai[j3][k3 - 1] & 0x1280102) == 0)
			{
				bigX[l3] = j3 - 1;
				bigY[l3] = k3 - 1;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3 - 1][k3 - 1] = 3;
				anIntArrayArray825[j3 - 1][k3 - 1] = l4;
			}
			if(j3 < byte0 - 1 && k3 > 0 && anIntArrayArray901[j3 + 1][k3 - 1] == 0 && (ai[j3 + 1][k3 - 1] & 0x1280183) == 0 && (ai[j3 + 1][k3] & 0x1280180) == 0 && (ai[j3][k3 - 1] & 0x1280102) == 0)
			{
				bigX[l3] = j3 + 1;
				bigY[l3] = k3 - 1;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3 + 1][k3 - 1] = 9;
				anIntArrayArray825[j3 + 1][k3 - 1] = l4;
			}
			if(j3 > 0 && k3 < byte1 - 1 && anIntArrayArray901[j3 - 1][k3 + 1] == 0 && (ai[j3 - 1][k3 + 1] & 0x1280138) == 0 && (ai[j3 - 1][k3] & 0x1280108) == 0 && (ai[j3][k3 + 1] & 0x1280120) == 0)
			{
				bigX[l3] = j3 - 1;
				bigY[l3] = k3 + 1;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3 - 1][k3 + 1] = 6;
				anIntArrayArray825[j3 - 1][k3 + 1] = l4;
			}
			if(j3 < byte0 - 1 && k3 < byte1 - 1 && anIntArrayArray901[j3 + 1][k3 + 1] == 0 && (ai[j3 + 1][k3 + 1] & 0x12801e0) == 0 && (ai[j3 + 1][k3] & 0x1280180) == 0 && (ai[j3][k3 + 1] & 0x1280120) == 0)
			{
				bigX[l3] = j3 + 1;
				bigY[l3] = k3 + 1;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3 + 1][k3 + 1] = 12;
				anIntArrayArray825[j3 + 1][k3 + 1] = l4;
			}
		}
		anInt1264 = 0;
		if(!flag1)
		{
			if(flag)
			{
				int i5 = 100;
				for(int k5 = 1; k5 < 2; k5++)
				{
					for(int i6 = k2 - k5; i6 <= k2 + k5; i6++)
					{
						for(int l6 = i2 - k5; l6 <= i2 + k5; l6++)
							if(i6 >= 0 && l6 >= 0 && i6 < 104 && l6 < 104 && anIntArrayArray825[i6][l6] < i5)
							{
								i5 = anIntArrayArray825[i6][l6];
								j3 = i6;
								k3 = l6;
								anInt1264 = 1;
								flag1 = true;
							}

					}

					if(flag1)
						break;
				}

			}
			if(!flag1)
				return false;
		}
		i4 = 0;
		bigX[i4] = j3;
		bigY[i4++] = k3;
		int l5;
		for(int j5 = l5 = anIntArrayArray901[j3][k3]; j3 != j2 || k3 != j1; j5 = anIntArrayArray901[j3][k3])
		{
			if(j5 != l5)
			{
				l5 = j5;
				bigX[i4] = j3;
				bigY[i4++] = k3;
			}
			if((j5 & 2) != 0)
				j3++;
			else
			if((j5 & 8) != 0)
				j3--;
			if((j5 & 1) != 0)
				k3++;
			else
			if((j5 & 4) != 0)
				k3--;
		}
//	if(cancelWalk) { return i4 > 0; }
	

		if(i4 > 0)
		{
			int k4 = i4;
			if(k4 > 25)
				k4 = 25;
			i4--;
			int k6 = bigX[i4];
			int i7 = bigY[i4];
			anInt1288 += k4;
			if(anInt1288 >= 92)
			{
				stream.createFrame(36);
				stream.writeDWord(0);
				anInt1288 = 0;
			}
			if(i == 0)
			{
				stream.createFrame(164);
				stream.writeWordBigEndian(k4 + k4 + 3);
			}
			if(i == 1)
			{
				stream.createFrame(248);
				stream.writeWordBigEndian(k4 + k4 + 3 + 14);
			}
			if(i == 2)
			{
				stream.createFrame(98);
				stream.writeWordBigEndian(k4 + k4 + 3);
			}
			stream.method433(k6 + baseX);
			destX = bigX[0];
			destY = bigY[0];
			for(int j7 = 1; j7 < k4; j7++)
			{
				i4--;
				stream.writeWordBigEndian(bigX[i4] - k6);
				stream.writeWordBigEndian(bigY[i4] - i7);
			}

			stream.method431(i7 + baseY);
			stream.method424(super.keyArray[5] != 1 ? 0 : 1);
			return true;
		}
		return i != 1;
	}

	private void method86(Stream stream)
	{
		for(int j = 0; j < anInt893; j++)
		{
			int k = anIntArray894[j];
			NPC npc = npcArray[k];
			int l = stream.readUnsignedByte();
			if((l & 0x10) != 0)
			{
				int i1 = stream.method434();
				if(i1 == 65535)
					i1 = -1;
				int i2 = stream.readUnsignedByte();
				if(i1 == npc.anim && i1 != -1)
				{
					int l2 = Animation.anims[i1].anInt365;
					if(l2 == 1)
					{
						npc.anInt1527 = 0;
						npc.anInt1528 = 0;
						npc.anInt1529 = i2;
						npc.anInt1530 = 0;
					}
					if(l2 == 2)
						npc.anInt1530 = 0;
				} else
				if(i1 == -1 || npc.anim == -1 || Animation.anims[i1].anInt359 >= Animation.anims[npc.anim].anInt359)
				{
					npc.anim = i1;
					npc.anInt1527 = 0;
					npc.anInt1528 = 0;
					npc.anInt1529 = i2;
					npc.anInt1530 = 0;
					npc.anInt1542 = npc.smallXYIndex;
				}
			}
			if((l & 8) != 0)
			{
				int damage = stream.method426();
				int type = stream.method427();
				int icon = stream.readUnsignedByte();
				int focusNPC = stream.readUnsignedByte();
				npc.updateHitData(type, damage, loopCycle, icon, 0, focusNPC);
				npc.loopCycleStatus = loopCycle + 300;
				npc.currentHealth = stream.method426();
				npc.maxHealth = stream.readUnsignedByte();
			}
			if((l & 0x80) != 0)
			{
				npc.anInt1520 = stream.readUnsignedWord();
				int k1 = stream.readDWord();
				npc.anInt1524 = k1 >> 16;
				npc.anInt1523 = loopCycle + (k1 & 0xffff);
				npc.anInt1521 = 0;
				npc.anInt1522 = 0;
				if(npc.anInt1523 > loopCycle)
					npc.anInt1521 = -1;
				if(npc.anInt1520 == 65535)
					npc.anInt1520 = -1;
			}
			if((l & 0x20) != 0)
			{
				npc.interactingEntity = stream.readUnsignedWord();
				if(npc.interactingEntity == 65535)
					npc.interactingEntity = -1;
			}
			if((l & 1) != 0)
			{
				npc.textSpoken = stream.readString();
				npc.textCycle = 100;
				//entityMessage(npc);
			}
			if((l & 0x40) != 0)
			{
				int damage = stream.method427();
				int type = stream.method428();
				int icon = stream.readUnsignedByte();
				int focusNPC = stream.readUnsignedByte();
				npc.updateHitData(type, damage, loopCycle, icon, 0, focusNPC);
				npc.loopCycleStatus = loopCycle + 300;
				npc.currentHealth = stream.method428();
				npc.maxHealth = stream.method427();
			}
			if((l & 2) != 0)
			{
				npc.desc = EntityDef.forID(stream.method436());
				npc.anInt1540 = npc.desc.aByte68;
				npc.anInt1504 = npc.desc.anInt79;
				npc.anInt1554 = npc.desc.walkAnim;
				npc.anInt1555 = npc.desc.anInt58;
				npc.anInt1556 = npc.desc.anInt83;
				npc.anInt1557 = npc.desc.anInt55;
				npc.anInt1511 = npc.desc.standAnim;
			}
			if((l & 4) != 0)
			{
				npc.anInt1538 = stream.method434();
				npc.anInt1539 = stream.method434();
			}
		}
	}

	private void buildAtNPCMenu(EntityDef entityDef, int i, int j, int k)
	{
		if(menuActionRow >= 400)
			return;
		if(entityDef.childrenIDs != null)
			entityDef = entityDef.method161();
		if(entityDef == null)
			return;
		if(!entityDef.aBoolean84)
			return;
		String s = entityDef.name;
		if(entityDef.combatLevel != 0)
			s = s + combatDiffColor(myPlayer.combatLevel, entityDef.combatLevel) + " (level:" + entityDef.combatLevel + ")";
		if(summoningAttack) {
			menuActionName[menuActionRow] = "Use familiar attack with @whi@" + s;
			menuActionID[menuActionRow] = 582;
			menuActionCmd1[menuActionRow] = i;
			menuActionCmd2[menuActionRow] = k;
			menuActionCmd3[menuActionRow] = j;
			menuActionRow++;
			return;
		}
		if(itemSelected == 1)
		{
			menuActionName[menuActionRow] = "Use " + selectedItemName + " with @yel@" + s;
			menuActionID[menuActionRow] = 582;
			menuActionCmd1[menuActionRow] = i;
			menuActionCmd2[menuActionRow] = k;
			menuActionCmd3[menuActionRow] = j;
			menuActionRow++;
			return;
		}
		if(spellSelected == 1)
		{
			if((spellUsableOn & 2) == 2)
			{
				menuActionName[menuActionRow] = spellTooltip + " @yel@" + s;
				menuActionID[menuActionRow] = 413;
				menuActionCmd1[menuActionRow] = i;
				menuActionCmd2[menuActionRow] = k;
				menuActionCmd3[menuActionRow] = j;
				menuActionRow++;
			}
		} else
		{
			if(entityDef.itemActions != null)
			{
				for(int l = 4; l >= 0; l--)
					if(entityDef.itemActions[l] != null && !entityDef.itemActions[l].equalsIgnoreCase("attack"))
					{
						menuActionName[menuActionRow] = entityDef.itemActions[l] + " @yel@" + s;
						if(l == 0)
							menuActionID[menuActionRow] = 20;
						if(l == 1)
							menuActionID[menuActionRow] = 412;
						if(l == 2)
							menuActionID[menuActionRow] = 225;
						if(l == 3)
							menuActionID[menuActionRow] = 965;
						if(l == 4)
							menuActionID[menuActionRow] = 478;
						menuActionCmd1[menuActionRow] = i;
						menuActionCmd2[menuActionRow] = k;
						menuActionCmd3[menuActionRow] = j;
						menuActionRow++;
					}

			}
			if(entityDef.itemActions != null)
			{
				for(int i1 = 4; i1 >= 0; i1--)
					if(entityDef.itemActions[i1] != null && entityDef.itemActions[i1].equalsIgnoreCase("attack"))
					{
						char c = '\0';
						if(entityDef.combatLevel > myPlayer.combatLevel)
							c = '\u07D0';
						menuActionName[menuActionRow] = entityDef.itemActions[i1] + " @yel@" + s;
						if(i1 == 0)
							menuActionID[menuActionRow] = 20 + c;
						if(i1 == 1)
							menuActionID[menuActionRow] = 412 + c;
						if(i1 == 2)
							menuActionID[menuActionRow] = 225 + c;
						if(i1 == 3)
							menuActionID[menuActionRow] = 965 + c;
						if(i1 == 4)
							menuActionID[menuActionRow] = 478 + c;
						menuActionCmd1[menuActionRow] = i;
						menuActionCmd2[menuActionRow] = k;
						menuActionCmd3[menuActionRow] = j;
						menuActionRow++;
					}

			}
			menuActionName[menuActionRow] = "Examine @yel@" + s + ((myPlayer.playerRights != 0) ? ", @gre@(@whi@" + entityDef.type + "@gre@)" : "");
			menuActionID[menuActionRow] = 1025;
			menuActionCmd1[menuActionRow] = i;
			menuActionCmd2[menuActionRow] = k;
			menuActionCmd3[menuActionRow] = j;
			menuActionRow++;
		}
	}

	private void buildAtPlayerMenu(int i, int j, Player player, int k)
	{
		if(player == myPlayer)
			return;
		if(menuActionRow >= 400)
			return;
		String s;
		if (player.skill == 0) {
			s = player.name
					+ combatDiffColor(myPlayer.combatLevel, player.combatLevel)
					+ " (level-" + player.combatLevel + ")";
		} else {
			s = "@or2@" + getRank(player.skill) + "@whi@ " + player.name
					+ combatDiffColor(myPlayer.combatLevel, player.combatLevel)
					+ " (level-" + player.combatLevel + ")";
		}
		if(summoningAttack) {
			menuActionName[menuActionRow] = "Use familiar attack with @whi@" + s;
			menuActionID[menuActionRow] = 491;
			menuActionCmd1[menuActionRow] = j;
			menuActionCmd2[menuActionRow] = i;
			menuActionCmd3[menuActionRow] = k;
			menuActionRow++;
		}
		if(itemSelected == 1)
		{
			menuActionName[menuActionRow] = "Use " + selectedItemName + " with @whi@" + s;
			menuActionID[menuActionRow] = 491;
			menuActionCmd1[menuActionRow] = j;
			menuActionCmd2[menuActionRow] = i;
			menuActionCmd3[menuActionRow] = k;
			menuActionRow++;
		} else
		{
			for(int index = 4; index >= 0; index--)
				if(atPlayerActions[index] != null)
				{
					menuActionName[menuActionRow] = atPlayerActions[index] + " @whi@" + s;
					char c = '\0';
					if(atPlayerActions[index].equalsIgnoreCase("attack"))
					{
						if(player.combatLevel > myPlayer.combatLevel)
							c = '\u07D0';
						if(myPlayer.team != 0 && player.team != 0)
							if(myPlayer.team == player.team)
								c = '\u07D0';
							else
								c = '\0';
					} else
					if(atPlayerArray[index])
						c = '\u07D0';
					if(index == 0)
						menuActionID[menuActionRow] = 561 + c;
					if(index == 1)
						menuActionID[menuActionRow] = 779 + c;
					if(index == 2)
						menuActionID[menuActionRow] = 27 + c;
					if(index == 3)
						menuActionID[menuActionRow] = 577 + c;
					if(index == 4)
						menuActionID[menuActionRow] = 729 + c;
					menuActionCmd1[menuActionRow] = j;
					menuActionCmd2[menuActionRow] = i;
					menuActionCmd3[menuActionRow] = k;
					menuActionRow++;
				}
			if(spellSelected == 1)
			{
				if((spellUsableOn & 8) == 8)
				{
					menuActionName[menuActionRow] = spellTooltip + " @whi@" + s;
					menuActionID[menuActionRow] = 365;
					menuActionCmd1[menuActionRow] = j;
					menuActionCmd2[menuActionRow] = i;
					menuActionCmd3[menuActionRow] = k;
					menuActionRow++;
				}
			}
		}
		for(int i1 = 0; i1 < menuActionRow; i1++)
			if(menuActionID[i1] == 516)
			{
				menuActionName[i1] = "Walk here @whi@" + s;
				return;
			}

	}

	private void method89(Class30_Sub1 class30_sub1)
	{
		int i = 0;
		int j = -1;
		int k = 0;
		int l = 0;
		if(class30_sub1.anInt1296 == 0)
			i = worldController.method300(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
		if(class30_sub1.anInt1296 == 1)
			i = worldController.method301(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
		if(class30_sub1.anInt1296 == 2)
			i = worldController.method302(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
		if(class30_sub1.anInt1296 == 3)
			i = worldController.method303(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
		if(i != 0)
		{
			int i1 = worldController.method304(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298, i);
			j = i >> 14 & 0x7fff;
			k = i1 & 0x1f;
			l = i1 >> 6;
		}
		class30_sub1.anInt1299 = j;
		class30_sub1.anInt1301 = k;
		class30_sub1.anInt1300 = l;
	}

	private void method90() {
		for (int i = 0; i < anInt1062; i++)
			if (anIntArray1250[i] <= 0) {
				boolean flag1 = false;
				try {
					if (anIntArray1207[i] == anInt874
							&& anIntArray1241[i] == anInt1289) {
						if (!replayWave())
							flag1 = true;
					} else {
						Stream stream = Sounds.method241(anIntArray1241[i],
								anIntArray1207[i]);
						if (System.currentTimeMillis()
								+ (long) (stream.currentOffset / 22) > aLong1172
								+ (long) (anInt1257 / 22)) {
							anInt1257 = stream.currentOffset;
							aLong1172 = System.currentTimeMillis();
							if (saveWave(stream.buffer, stream.currentOffset)) {
								anInt874 = anIntArray1207[i];
								anInt1289 = anIntArray1241[i];
							} else {
								flag1 = true;
							}
						}
					}
				} catch (Exception exception) {
				}
				if (!flag1 || anIntArray1250[i] == -5) {
					anInt1062--;
					for (int j = i; j < anInt1062; j++) {
						anIntArray1207[j] = anIntArray1207[j + 1];
						anIntArray1241[j] = anIntArray1241[j + 1];
						anIntArray1250[j] = anIntArray1250[j + 1];
					}

					i--;
				} else {
					anIntArray1250[i] = -5;
				}
			} else {
				anIntArray1250[i]--;
			}

		if (prevSong > 0) {
			prevSong -= 20;
			if (prevSong < 0)
				prevSong = 0;
			if (prevSong == 0 && musicEnabled && !lowMem) {
				nextSong = currentSong;
				onDemandFetcher.method558(2, nextSong);
			}
		}
	}
	
	public Sprite coinOrb;
public Sprite coinPart;
public boolean coinToggle = false;
public Sprite coinOrbPart;

public void drawCoinParts(){
	if(!coinToggle){
	if(super.mouseX >= 512 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118){
		coinOrbPart = new Sprite("coinpart hover");
	} else {
		coinOrbPart = new Sprite("coinpart normal");
		}
	}
	coinOrbPart.drawSprite(505+4+4, 93-11);
}

public void drawCoinOrb(){//512 nontoggle
if(!toggleFullscreen) {
	if(!coinToggle) {//436,87 534 x 118 y
		if(super.mouseX >= 515 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118){
			coinOrb = new Sprite("coin simple hover");
		} else {
			coinOrb = new Sprite("coin simple normal");
		}
	} else if(coinToggle) {
		if(super.mouseX >= 436 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118){
			coinOrb = new Sprite("coin simple hover toggle");
			coinPart = new Sprite("coin complex hover toggle");
		} else {
			coinOrb = new Sprite("coin simple normal toggle");
			coinPart = new Sprite("coin complex normal toggle");
		}
	}
		coinOrb.drawSprite(520-300-200-20-4+4, 47+37);//+37

	if(super.mouseX >= 436 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118) {
		coinPart = new Sprite("coin complex hover toggle");
		coinOrb = new Sprite("coin simple hover");
	}
}
}

	private boolean replayWave() {
		return signlink.wavereplay();
	}
		public String fName;
	public String dir;
		private boolean unZipFile() {
		try {
			ZipFile zipfile = new ZipFile((new StringBuilder()).append(
					signlink.findcachedir()).append(dir).append(fName)
					.toString());
			int length = zipfile.size();
			int i = 0;
			for (Enumeration enumeration = zipfile.entries(); enumeration
			.hasMoreElements();) {
				ZipEntry zipentry = (ZipEntry) enumeration.nextElement();
				drawCacheDownloading(5, "Unpacking files "
						+ ((int) ((++i * 100) / length)) + "%");
				if (zipentry.isDirectory()) {

					System.out.println((new StringBuilder()).append(
					"Creating Directory: ").append(zipentry.getName())
					.toString());
					(new File((new StringBuilder()).append(
							signlink.findcachedir() + dir).append(
									zipentry.getName()).toString())).mkdirs();
				} else {
					if (zipentry.getName().contains("/")) {
						String s4 = zipentry.getName().replaceAll(
								zipentry.getName().substring(
										zipentry.getName().indexOf("/")), "");
						File f = new File(signlink.findcachedir() + dir + s4);
						if (!f.exists())
							f.mkdirs();
					}

					System.out.println((new StringBuilder()).append(
					"Extracting File: ").append(zipentry.getName())
					.toString());
					writeStream(zipfile.getInputStream(zipentry),
							new BufferedOutputStream(new FileOutputStream(
									(new StringBuilder()).append(
											signlink.findcachedir())
											.append(dir).append(
													zipentry.getName())
													.toString())));
				}
			}

			zipfile.close();
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}
	
		public void deleteFile() {
		try {
			File file = new File((new StringBuilder()).append(
					signlink.findcachedir()).append(dir).append(fName)
					.toString());
			file.delete();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
		public void writeStream(InputStream inputstream, OutputStream outputstream)
	throws IOException {
		byte abyte0[] = new byte[4096];
		int i;
		while ((i = inputstream.read(abyte0)) >= 0) {
			outputstream.write(abyte0, 0, i);
		}
		inputstream.close();
		outputstream.close();
	}
				private StreamLoader streamLoaderForName(int i, String s, String s1, int j,
			int k) {
		File location = new File(getCacheDir());
		byte abyte0[] = null;
		int l = 5;
		try {
			if (decompressors[0] != null) {
				abyte0 = decompressors[0].decompress(i);
			}
		} catch (Exception exception) {
		}
		if (abyte0 == null) {
			drawCacheDownloading(15, "Downloading Cache");
			String url = "https://dl.dropboxusercontent.com/s/99nmukjhfy8x50x/InsanityCacheV6.zip";
			if (Math.random() > 0.5) {
				url = "https://dl.dropboxusercontent.com/s/99nmukjhfy8x50x/InsanityCacheV6.zip";
				System.out.println("InsanityX - Download Cache? Or does the cache exist? Checking now..");
			}
			/*
			 * if(Math.random() > 0.5) url =
			 * "https://dl.dropboxusercontent.com/s/99nmukjhfy8x50x/InsanityCacheV6.zip";
			 */
			downloadcache(url, "InsanityCacheV6.zip", "", "InsanityCacheV6");
			// abyte0 = decompressors[0].decompress(i);
			try {
				String s2 = signlink.findcachedir();
				File file = new File((new StringBuilder()).append(s2).append(
				"main_file_cache.dat").toString());
				/*
				 * if(file.exists() && file.length() > 0x3200000L) {
				 * file.delete(); }
				 */
				signlink.cache_dat = new RandomAccessFile((new StringBuilder())
						.append(s2).append("main_file_cache.dat").toString(),
				"rw");
				for (int j1 = 0; j1 < 5; j1++) {
					signlink.cache_idx[j1] = new RandomAccessFile(
							(new StringBuilder()).append(s2).append(
							"main_file_cache.idx").append(j1)
							.toString(), "rw");
				}

				if (signlink.cache_dat != null) {
					for (int k1 = 0; k1 < 5; k1++) {
						decompressors[k1] = new Decompressor(
								signlink.cache_dat, signlink.cache_idx[k1],
								k1 + 1);
					}

				}
				if (decompressors[0] != null) {
					abyte0 = decompressors[0].decompress(i);
				}
			} catch (Exception exception1) {
			}
		}
		if (abyte0 == null)
			
		if (abyte0 != null) {
			StreamLoader streamLoader = new StreamLoader(abyte0, s);
			return streamLoader;
		}
		int i1 = 0;
		do {
			if (abyte0 != null) {
				break;
			}
			String s3 = "Unknown error";
			drawLoadingText(k, (new StringBuilder()).append("Requesting ")
					.append(s).toString());
			Object obj = null;
			try {
				int l1 = 0;
				DataInputStream datainputstream = openJagGrabInputStream((new StringBuilder())
						.append(s1).append(j).toString());
				byte abyte1[] = new byte[6];
				datainputstream.readFully(abyte1, 0, 6);
				Stream stream1 = new Stream(abyte1);
				stream1.currentOffset = 3;
				int j2 = stream1.read3Bytes() + 6;
				int k2 = 6;
				abyte0 = new byte[j2];
				System.arraycopy(abyte1, 0, abyte0, 0, 6);
				while (k2 < j2) {
					int l2 = j2 - k2;
					if (l2 > 1000) {
						l2 = 1000;
					}
					int i3 = datainputstream.read(abyte0, k2, l2);
					if (i3 < 0) {
						s3 = (new StringBuilder()).append("Length error: ")
						.append(k2).append("/").append(j2).toString();
						throw new IOException("EOF");
					}
					k2 += i3;
					int j3 = (k2 * 100) / j2;
					if (j3 != l1) {
						drawLoadingText(k, (new StringBuilder()).append(
						"Loading ").append(s).append(" - ").append(j3)
						.append("%").toString());
					}
					l1 = j3;
				}
				datainputstream.close();
				try {
					if (decompressors[0] != null) {
						decompressors[0].method234(abyte0.length, abyte0, i);
					}
				} catch (Exception exception4) {
					decompressors[0] = null;
				}
			} catch (IOException ioexception) {
				if (s3.equals("Unknown error")) {
					s3 = "Connection error";
					String url = "https://dl.dropboxusercontent.com/s/99nmukjhfy8x50x/InsanityCacheV6.zip";
					downloadcache(url, "InsanityCacheV6.zip", "", "InsanityCacheV6");
				}
				abyte0 = null;
			} catch (NullPointerException nullpointerexception) {
				s3 = "Null error";
				abyte0 = null;
				if (!signlink.reporterror) {
					return null;
				}
			} catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
				s3 = "Bounds error";
				abyte0 = null;
				if (!signlink.reporterror) {
					return null;
				}
			} catch (Exception exception2) {
				s3 = "Unexpected error";
				abyte0 = null;
				if (!signlink.reporterror) {
					return null;
				}
			}
			if (abyte0 == null) {
				for (int i2 = l; i2 > 0; i2--) {
					if (i1 >= 3) {
						drawLoadingText(k, "Game updated - please reload page");
						i2 = 10;
					} else {
						drawLoadingText(k, (new StringBuilder()).append(s3)
								.append(" - Retrying in ").append(i2)
								.toString());
					}
					try {
						Thread.sleep(1000L);
					} catch (Exception exception3) {
					}
				}

				l *= 2;
				if (l > 60) {
					l = 60;
				}
				aBoolean872 = !aBoolean872;
			}
		} while (true);
			StreamLoader streamLoader = new StreamLoader(abyte0, s);
			return streamLoader;
	}
		private String getCacheDir() {
		return signlink.findcachedir();
	}
		public void downloadcache(String s, String s1, String s2, String s3) {
		dir = s2;
		fName = s1;
		// unZipFile();
		boolean delete = true;
		try {
			URLConnection urlconnection = (new URL(s)).openConnection();
			String as[] = s.split("/");
			File file = new File(as[as.length - 1]);
			int i = urlconnection.getContentLength();
			File f = new File(signlink.findcachedir() + dir + "/InsanityCacheV6.zip");
			System.out.println(i + " : " + f.length());
			if (f.exists() && f.length() == i) {
				unZipFile();
				return;
			}
			InputStream inputstream = urlconnection.getInputStream();
			try {
				(new File((new StringBuilder()).append(signlink.findcachedir())
						.append(dir).toString())).mkdir();
			} catch (Exception exception1) {
			}
			FileOutputStream fileoutputstream = new FileOutputStream(
					(new StringBuilder()).append(signlink.findcachedir())
					.append(dir).append(file).toString());
			int j = 0;
			byte abyte0[] = new byte[4096];
			long startTime = System.currentTimeMillis();
			int k;
			while ((k = inputstream.read(abyte0)) != -1) {
				fileoutputstream.write(abyte0, 0, k);
				j += k;
				int l = (int) (((double) j / (double) i) * 100D);
				int downloadSpeed = (int) ((j / 1024) / (1 + ((System
						.currentTimeMillis() - startTime) / 1000)));
				drawCacheDownloading(l, (new StringBuilder()).append(
						"Downloading Cache - ").append(l).append(
								"% @ " + downloadSpeed + " Kb/s").toString());
			}
			if (i != j) {
				inputstream.close();
				fileoutputstream.close();
			} else {
				drawCacheDownloading(5, "Unpacking files...");
				inputstream.close();
				fileoutputstream.close();
				if (unZipFile())
					delete = false;
				drawCacheDownloading(10, "Unpacking was complete");
			//		setNewMaps();
			//maps();
			}
		} catch (Exception exception) {
			delete = false;
			System.err.println("Error connecting to server.");
			exception.printStackTrace();
		}
		if (delete)
			deleteFile();
	}

	private boolean saveWave(byte abyte0[], int i) {
		return abyte0 == null || signlink.wavesave(abyte0, i);
	}

	void startUp()
	{
	new Thread(new MemoryMonitor()).start();
		drawCacheDownloading(20, "Starting Client - Hold on..");
		//new CacheDownloader(this).downloadCache();
		//drawLoadingText(40, "Checking models");	
		
	if(signlink.sunjava)
			super.minDelay = 5;
		if(aBoolean993)
		{
 //		   rsAlreadyLoaded = true;
 //		   return;
		}
		aBoolean993 = true;
		boolean flag = true;
		String s = getDocumentBaseHost();
		if(signlink.cache_dat != null)
		{
			for(int i = 0; i < 5; i++)
				decompressors[i] = new Decompressor(signlink.cache_dat, signlink.cache_idx[i], i + 1);
		}
		try {
						//maps();
			titleStreamLoader = streamLoaderForName(1, "title screen", "title", expectedCRCs[1], 25);
			smallFont = new TextDrawingArea(false, "p11_full", titleStreamLoader);
			normalFont = new TextDrawingArea(false, "p12_full", titleStreamLoader);
			boldFont = new TextDrawingArea(false, "b12_full", titleStreamLoader);
			fancyFont = new TextDrawingArea(true, "q8_full", titleStreamLoader);//new CLIENT*/


			bigHit = new TextDrawingArea(true, "critical_full", titleStreamLoader); 
			newSmallFont = new RSFont(false, "p11_full", titleStreamLoader);
            newRegularFont = new RSFont(false, "p12_full", titleStreamLoader);
            newBoldFont = new RSFont(false, "b12_full", titleStreamLoader);
            newFancyFont = new RSFont(true, "q8_full", titleStreamLoader);
			newSmallFont.unpackChatImages(chatImages);
            newRegularFont.unpackChatImages(chatImages);
            newBoldFont.unpackChatImages(chatImages);
            newFancyFont.unpackChatImages(chatImages);
			drawLogo();
			loadTitleScreen();
			StreamLoader configArchive = streamLoaderForName(2, "config", "config", expectedCRCs[2], 30);
			StreamLoader interfaceArchive = streamLoaderForName(3, "interface", "interface", expectedCRCs[3], 35);
			StreamLoader mediaArchive = streamLoaderForName(4, "2d graphics", "media", expectedCRCs[4], 40);
			StreamLoader textureArchive = streamLoaderForName(6, "textures", "textures", expectedCRCs[6], 45);
			StreamLoader chatFilterArchive = streamLoaderForName(7, "chat system", "wordenc", expectedCRCs[7], 50);
			StreamLoader soundArchive = streamLoaderForName(8, "sound effects", "sounds", expectedCRCs[8], 55);
			            for (int l4 = 0; l4 < 5; l4++) {
                chatImages[l4] = new Sprite("Player/MODICON " + l4);
            }
			byteGroundArray = new byte[4][104][104];
			intGroundArray = new int[4][105][105];
			worldController = new WorldController(intGroundArray);
			for(int j = 0; j < 4; j++)
				aClass11Array1230[j] = new Class11();
			miniMapImage = new Sprite(512, 512);
			StreamLoader streamLoader_6 = streamLoaderForName(5, "update list", "versionlist", expectedCRCs[5], 60);
			drawSmoothLoading(80, "");
			
			//maps();
			//repackCacheIndex(2);
			//repackCacheIndex(1);
			//models = index1
			//animations = index2
			//sounds = index3
			//maps = index4
			onDemandFetcher = new OnDemandFetcher();
			onDemandFetcher.start(streamLoader_6, this);
			Class36.method528(onDemandFetcher.getAnimCount());
			Model.method459(onDemandFetcher.getModelCount(), onDemandFetcher);
			preloadModels();
			DataBase.loadAnimations();
			//models();
			//DataBase.loadObjects(); //Just checking somthing
			//DataBase.loadModels();
			drawLoadingText(80, "");
						Sprite[] clanIcons = new Sprite[9];
			for(int index = 0; index < clanIcons.length; index++) {
				//clanIcons[index] = new Sprite(streamLoader_2, "clanicons", index);
				clanIcons[index] = new Sprite("GameFrame/Icons/" + index);
			}
			/* Custom sprite unpacking */
							loginBox = new Sprite("title");
				loginBoxHover = new Sprite("box_hover");
				loginHover = new Sprite("loginHover");
				loginHoverWorld = new Sprite("login_hover2");
			infinityIcon = new Sprite("infinity");
			for(int ig = 0; ig < chatArea.length; ig++)
				chatArea[ig] = new Sprite("chatarea " + ig);
			for(int gomlbro = 0; gomlbro < newChatButtons.length; gomlbro++)
				newChatButtons[gomlbro] = new Sprite("newchatbuttons " +gomlbro);
			for(int iiig = 0; iiig < tabArea.length; iiig++)
				tabArea[iiig] = new Sprite("tabarea " + iiig);
			
			HPBarFull = new Sprite("HITPOINTS_0");
			HPBarEmpty = new Sprite("HITPOINTS_1");
			
			mapArea = new Sprite("maparea");
			backgroundFix = new Sprite("backgroundfix");
			worldSwitcherBg = new Sprite("worldswitcherbg");
			gameframeSwitcherBg = new Sprite("gameframeswitcherbg");
			checkMark = new Sprite("checkmarkl");
			optionsHover = new Sprite("optionshover");
			magicAuto = new Sprite("autocast");
			loadOrbs();
			multiOverlay = new Sprite(mediaArchive, "overlay_multiway", 0);
			/**/
			for(int i = 0; i < mapBack.length; i++)
				mapBack[i] = new Background(mediaArchive, "mapback", i);
			
			for(int j5 = 0; j5 < tabAreaSprite.length; j5++)
				tabAreaSprite[j5] = new Sprite("tabarea " + j5);
			for(int c1 = 0; c1 <= 3; c1++)
				chatButtons[c1] = new Sprite(mediaArchive, "chatbuttons", c1);
				
			for(int loop = 0; loop < inventoryStones.length; loop++)
				inventoryStones[loop] = new Sprite("sideicon/inventorystones " + loop);
				
							for(int bh = 2; bh < 7; bh++) {
				bountyHunter[bh] = new Sprite("bounty "+bh+"");
			}
				
			for(int j3 = 0; j3 < sideIcons.length; j3++)
				sideIcons[j3] = new Sprite("sideicon/sideicons " + j3);
			compass = new Sprite(mediaArchive, "compass", 0);

			for(int j78 = 0; j78 < exitButton.length; j78++)
				exitButton[j78] = new Sprite("exit " + j78);

			for(int j79 = 0; j79 < advisorButton.length; j79++)
				advisorButton[j79] = new Sprite("advisor " + j79);
			
			for(int ghtr = 0; ghtr < worldMapIcon.length; ghtr++)
				worldMapIcon[ghtr] = new Sprite("mapIcon " + ghtr);
			
			for(int i4 = 0; i4 < hitMarkBacks.length; i4++)
				for(int i5 = 0; i5 < hitMarkBacks[0].length; i5++)
					for(int i6 = 0; i6 < hitMarkBacks[0][0].length; i6++)
						hitMarkBacks[i4][i5][i6] = new Sprite("/hits/back/" + i4 + "/"+ i5 +"/hit " + i6);
				
			for(int i4 = 0; i4 < hitMarkIcons.length; i4++)
				hitMarkIcons[i4] = new Sprite("/Hits/icons/icon " + i4);
			
			NewMapBack = new Sprite("mapback");
			emptyOrb = new Sprite("emptyorb");
			emptyFOrb = new Sprite("emptyforb");
			hoveredEmpty = new Sprite("hoveredempty");
			hoveredFEmpty = new Sprite("hoveredfempty");
			runIcon1 = new Sprite("runicon1");
			runIcon2 = new Sprite("runicon2");
			runOrb1 = new Sprite("runorb1");
			runOrb2 = new Sprite("runorb2");
			hitPointsFill = new Sprite("hitpointsfill");
			hitPointsIcon = new Sprite("hitpointsicon");
			prayerFill = new Sprite("prayerfill");
			prayerIcon = new Sprite("prayericon");
			mapEdge = new Sprite(mediaArchive, "mapedge", 0);
			mapEdge.method345();
			try
			{
				for(int i = 0; i < XPcounterSprite.length; i++)
					XPcounterSprite[i] = new Sprite("xp/XP " + i);
			}
			catch(Exception _ex) {}
			try
			{
				for(int k3 = 0; k3 < 100; k3++)
					mapScenes[k3] = new Background(mediaArchive, "mapscene", k3);
			}
			catch(Exception _ex) { }
			try
			{
				for(int l3 = 0; l3 < 100; l3++)
					mapFunctions[l3] = new Sprite(mediaArchive, "mapfunction", l3);
			}
			catch(Exception _ex) { }
			try
			{
				for(int h1 = 0; h1 < 6; h1++)
					headIconsHint[h1] = new Sprite(mediaArchive, "headicons_hint", h1);
			} catch(Exception _ex) { }
			try {
				for(int j4 = 0; j4 < 18; j4++)
					//headIcons[j4] = new Sprite(mediaArchive, "headicons_prayer", j4);
					headIcons[j4] = new Sprite("curses/Prayer "+j4);
				for(int j45 = 0; j45 < 3; j45++)
					skullIcons[j45] = new Sprite(mediaArchive, "headicons_pk", j45 );
				for(int j45 = 9; j45 < 17; j45++)
					cursesIcons[j45] = new Sprite("curses/Prayer "+j45);				
					
			}
			catch(Exception _ex) { }
			mapFlag = new Sprite(mediaArchive, "mapmarker", 0);
			mapMarker = new Sprite(mediaArchive, "mapmarker", 1);
			for(int k4 = 0; k4 < 8; k4++)
				crosses[k4] = new Sprite(mediaArchive, "cross", k4);

			mapDotItem = new Sprite(mediaArchive, "mapdots", 0);
			mapDotNPC = new Sprite(mediaArchive, "mapdots", 1);
			mapDotPlayer = new Sprite(mediaArchive, "mapdots", 2);
			mapDotFriend = new Sprite(mediaArchive, "mapdots", 3);
			mapDotTeam = new Sprite(mediaArchive, "mapdots", 4);
			mapDotClan = new Sprite(mediaArchive, "mapdots", 5);
						alertBack = new Sprite("alertback");
			alertBorder = new Sprite("alertborder");
			alertBorderH = new Sprite("alertborderh");

			for(int loop = 0; loop < scrollBar.length; loop++)
				scrollBar[loop] = new Sprite("./scrollbar/scrollbar " + loop);
			
			for(int loop = 0; loop < scrollArrow.length; loop++)
				scrollArrow[loop] = new Sprite("./scrollbar/scrollArrow " + loop);
			
			rightFrameSprite = new Sprite(mediaArchive, "screenframe", 2);
			
			for(int l4 = 0; l4 < 6; l4++)
				modIcons[l4] = new Sprite("Player/MODICON "+l4+"");

			Sprite sprite = new Sprite(mediaArchive, "screenframe", 0);
			leftFrame = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new Sprite(mediaArchive, "screenframe", 1);
			topFrame = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new Sprite(mediaArchive, "mapedge", 0);
			mapEdgeIP = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);

			int i5 = (int)(Math.random() * 21D) - 10;
			int j5 = (int)(Math.random() * 21D) - 10;
			int k5 = (int)(Math.random() * 21D) - 10;
			int l5 = (int)(Math.random() * 41D) - 20;
			for(int functionIndex = 0; functionIndex < 100; functionIndex++) {
				if(mapFunctions[functionIndex] != null)
					mapFunctions[functionIndex].method344(i5 + l5, j5 + l5, k5 + l5);
				if(mapScenes[functionIndex] != null)
					mapScenes[functionIndex].method360(i5 + l5, j5 + l5, k5 + l5);
			}
			//	setNewMaps();
			//maps();
			setLowMem();
			drawSmoothLoading(122, "");
		//	loadItemPrices(signlink.findcachedir()+"prices.txt"); 
		//	loadItemList(signlink.findcachedir()+"item.cfg"); 
			Texture.method368(textureArchive);
			Texture.method372(0.80000000000000004D);
			Texture.method367();
			drawSmoothLoading(168, "");
			Animation.unpackConfig(configArchive);
			ObjectDef.unpackConfig(configArchive);
			OverLayFlo317.load(configArchive);
			Flo.unpackConfig(configArchive);
			ItemDef.unpackConfig(configArchive);
			EntityDef.unpackConfig(configArchive);
			IDK.unpackConfig(configArchive);
			SpotAnim.unpackConfig(configArchive);
			Varp.unpackConfig(configArchive);
			VarBit.unpackConfig(configArchive);
			ItemDef.isMembers = isMembers;
			if(!lowMem)
			{
				byte abyte0[] = soundArchive.getDataForName("sounds.dat");
				
				Stream stream = new Stream(abyte0);
				Sounds.unpack(stream);
				
			}
			drawSmoothLoading(183, "");
			TextDrawingArea allFonts[] = {
					smallFont, normalFont, boldFont, fancyFont
			};
/*RSFont allFonts[] = { NEW INTERFACES TEXT ENGINE
					newSmallFont, newRegularFont, newBoldFont, newFancyFont
			};*/
			RSInterface.fonts = allFonts;
			RSInterface.unpack(interfaceArchive, allFonts, mediaArchive);
			drawSmoothLoading(187, "");
			RSInterface.unpackCustom(interfaceArchive, allFonts);
			drawSmoothLoading(200, "");
			/*
			//Taken this out, causing seirous lag -.-
			for(int derp = 0; derp < 25000; derp++)
			{
				ItemDef itemdef = ItemDef.forID(derp);
				writeTemp(itemdef.name);
			}*/
			readDetails();
			renderMapAndCompassHalfs();
			load3DArea();
			Censor.loadConfig(chatFilterArchive);
			mouseDetection = new MouseDetection(this);
			startRunnable(mouseDetection, 10);
			Animable_Sub5.clientInstance = this;
			ObjectDef.clientInstance = this;
			EntityDef.clientInstance = this;
			System.gc();
			super.setCursor(0);
			return;
		} catch(Exception exception) {
			signlink.reporterror("loaderror " + aString1049 + " " + anInt1079);
			exception.printStackTrace();
		}
		loadingError = true;
	}
	
	public void renderMapAndCompassHalfs() {
		for(int loopHeight = 0; loopHeight < 33; loopHeight++) {
			int k6 = 999;
			int loopWidth = 0;
			for(; loopWidth < 33; loopWidth++) {
				if(mapBack[2].aByteArray1450[loopWidth + loopHeight * 33] == 0) {
					if(k6 == 999)
						k6 = loopWidth;
					continue;
				}
				if(k6 == 999)
					continue;
				break;
			}
			compassPixelCutLeft[loopHeight] = k6;//left ( it's heigth)
			compassPixelCutRight[loopHeight] = loopWidth - k6;//right (it's width)
		}
		for(int loopHeight = 0; loopHeight < 152; loopHeight++) {
			int j7 = 999;
			int finalWidth = 0;
			for(int loopWidth = 0; loopWidth < 152; loopWidth++) {
				if(mapBack[1].aByteArray1450[loopWidth + loopHeight * 152] == 0) {
					if(j7 == 999)
						j7 = loopWidth;
					continue;
				}
				if(j7 == 999)
					continue;
				finalWidth = loopWidth;//999 - 999 = 0
				break;
			}

			mapImagePixelCutLeft[loopHeight - 0] = j7 - 0;
			if((finalWidth - j7) == 0) {
				finalWidth = 152;
				j7 = 0;
			}
			mapImagePixelCutRight[loopHeight - 0] = finalWidth - j7;
		}
	}
	
	public static void writeTemp(String tempData) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("./derp.txt", true));
			bw.write(tempData);
			bw.newLine();
			bw.flush();
		} catch(IOException IOE) {}
	}
	
	private void method91(Stream stream, int i)
	{
		while(stream.bitPosition + 10 < i * 8)
		{
			int j = stream.readBits(11);
			if(j == 2047)
				break;
			if(playerArray[j] == null)
			{
				playerArray[j] = new Player();
				if(aStreamArray895s[j] != null)
					playerArray[j].updatePlayer(aStreamArray895s[j]);
			}
			playerIndices[playerCount++] = j;
			Player player = playerArray[j];
			player.anInt1537 = loopCycle;
			int k = stream.readBits(1);
			if(k == 1)
				anIntArray894[anInt893++] = j;
			int l = stream.readBits(1);
			int i1 = stream.readBits(5);
			if(i1 > 15)
				i1 -= 32;
			int j1 = stream.readBits(5);
			if(j1 > 15)
				j1 -= 32;
			player.setPos(myPlayer.smallX[0] + j1, myPlayer.smallY[0] + i1, l == 1);
		}
		stream.finishBitAccess();
	}

	private int mapDrawAreaX = 0;
	private int mapDrawAreaY = 516;
	private int mapPaddingFromTop = toggleFullscreen ? 5 : 9;
	private int mapPaddingFromLeft = toggleFullscreen ? 5 : 35;
	private int mapPaddingFromBottom = toggleFullscreen ? 5 : 7;
	private int mapPaddingFromRight = toggleFullscreen ? 5 : 62;
	private int miniMapWidth = toggleFullscreen ? 163 : 249;//172
	private int miniMapHeight = toggleFullscreen ? 163 : 168;//156
	private int mapSpritesX = 107;
	private int mapSpritesY = 88;
	private int mapMovedX = 29;
	private int mapMovedY = 4;
	
	int mapInnerSizeWidth = miniMapWidth - (mapPaddingFromLeft + mapPaddingFromRight);
	int mapInnerSizeHeight = miniMapHeight - (mapPaddingFromTop + mapPaddingFromBottom);
	
	private void processMinimapClick(int paddingX,int paddingY) { 
		if(minimapCover != 0)
			return;
		if(super.clickMode3 == 1)
		{
			int mapClickX = super.saveClickX - mapPaddingFromLeft - mapDrawAreaY -  paddingX;
			int mapClickY = super.saveClickY - mapPaddingFromTop - mapDrawAreaX -  paddingY;
					if(!coinToggle){
			if (super.mouseX >= 512 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118) {
				menuActionName[4] = coinToggle ? "Toggle money pouch" : "Toggle money pouch";
				menuActionID[4] = 712;
				menuActionName[3] = "Withdraw money pouch";
				menuActionID[3] = 713;
				menuActionName[2] = "Examine money pouch";
				menuActionID[2] = 714;
				menuActionName[1] = "Price checker";
				menuActionID[1] = 715;
				menuActionRow = 5;
			}
		} else if(coinToggle){
			if (super.mouseX >= 436 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118) {
				menuActionName[4] = coinToggle ? "Toggle money pouch" : "Toggle money pouch";
				menuActionID[4] = 712;
				menuActionName[3] = "Withdraw money pouch";
				menuActionID[3] = 713;
				menuActionName[2] = "Examine money pouch";
				menuActionID[2] = 714;
				menuActionName[1] = "Price checker";
				menuActionID[1] = 715;
				menuActionRow = 5;
			}
		}
			if(mapClickX >= 0 && mapClickY >= 0 && mapClickX < mapInnerSizeWidth && mapClickY < mapInnerSizeHeight)
			{
				mapClickX -= 73;
				mapClickY -= 75;
				int k = compassRotation + mapAmtDiffRotation & 0x7ff;
				int i1 = Texture.anIntArray1470[k];
				int j1 = Texture.anIntArray1471[k];
				i1 = i1 * (mapAmtDiffZoom + 256) >> 8;
				j1 = j1 * (mapAmtDiffZoom + 256) >> 8;
				int k1 = mapClickY * i1 + mapClickX * j1 >> 11;
				int l1 = mapClickY * j1 - mapClickX * i1 >> 11;
				int i2 = myPlayer.x + k1 >> 7;
				int j2 = myPlayer.y - l1 >> 7;
				boolean flag1 = doWalkTo(1, 0, 0, 0, myPlayer.smallY[0], 0, 0, j2, myPlayer.smallX[0], true, i2);
				if(flag1)
				{
					stream.writeWordBigEndian(mapClickX);
					stream.writeWordBigEndian(mapClickY);
					stream.writeWord(compassRotation);
					stream.writeWordBigEndian(57);
					stream.writeWordBigEndian(mapAmtDiffRotation);
					stream.writeWordBigEndian(mapAmtDiffZoom);
					stream.writeWordBigEndian(89);
					stream.writeWord(myPlayer.x);
					stream.writeWord(myPlayer.y);
					stream.writeWordBigEndian(anInt1264);
					stream.writeWordBigEndian(63);
				}
			}
			anInt1117++;
			if(anInt1117 > 1151)
			{
				anInt1117 = 0;
				stream.createFrame(246);
				stream.writeWordBigEndian(0);
				int l = stream.currentOffset;
				if((int)(Math.random() * 2D) == 0)
					stream.writeWordBigEndian(101);
				stream.writeWordBigEndian(197);
				stream.writeWord((int)(Math.random() * 65536D));
				stream.writeWordBigEndian((int)(Math.random() * 256D));
				stream.writeWordBigEndian(67);
				stream.writeWord(14214);
				if((int)(Math.random() * 2D) == 0)
					stream.writeWord(29487);
				stream.writeWord((int)(Math.random() * 65536D));
				if((int)(Math.random() * 2D) == 0)
					stream.writeWordBigEndian(220);
				stream.writeWordBigEndian(180);
				stream.writeBytes(stream.currentOffset - l);
			}
		}
	}

	private String interfaceIntToString(int j) {
		if(j < 0x3b9ac9ff)
			return String.valueOf(j);
		else
			return "*";
	}

	private void showErrorScreen()
	{
		Graphics g = getGameComponent().getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 765, 503);
		method4(1);
		if(loadingError)
		{
			aBoolean831 = false;
			g.setFont(new Font("Helvetica", 1, 16));
			g.setColor(Color.yellow);
			int k = 35;
			g.drawString("Sorry, an error has occured whilst loading RuneScape", 30, k);
			k += 50;
			g.setColor(Color.white);
			g.drawString("To fix this try the following (in order):", 30, k);
			k += 50;
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", 1, 12));
			g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, k);
			k += 30;
			g.drawString("2: Try clearing your web-browsers cache from tools->internet options", 30, k);
			k += 30;
			g.drawString("3: Try using a different game-world", 30, k);
			k += 30;
			g.drawString("4: Try rebooting your computer", 30, k);
			k += 30;
			g.drawString("5: Try selecting a different version of Java from the play-game menu", 30, k);
		}
		if(genericLoadingError)
		{
			aBoolean831 = false;
			g.setFont(new Font("Helvetica", 1, 20));
			g.setColor(Color.white);
			g.drawString("Error - unable to load game!", 50, 50);
			g.drawString("To play Divination of Gods make sure you play from", 50, 100);
			g.drawString("http://www.divinationofgods.cu.cc", 50, 150);
		}
		if(rsAlreadyLoaded)
		{
			aBoolean831 = false;
			g.setColor(Color.yellow);
			int l = 35;
			g.drawString("Error a copy of Divination of Gods already appears to be loaded", 30, l);
			l += 50;
			g.setColor(Color.white);
			g.drawString("To fix this try the following (in order):", 30, l);
			l += 50;
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", 1, 12));
			g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, l);
			l += 30;
			g.drawString("2: Try rebooting your computer, and reloading", 30, l);
			l += 30;
		}
	}

	public URL getCodeBase() {		
		try {
			return new URL(serverip +":" + (80 + portOff));
		} catch(Exception _ex) {
		}
		return null;
	}
	public static String cursorInfo[] = {
		"Walk-to", "Take", "Attack", "Use", "Open", "Talk-to", "Climb-up", 
		"Climb-down", "Chop", "Smelt", "Mine", "Cast Ice Barrage on", "Eat", "Drink", "Cast High level alchemy", "Wear", "Wield"
	};

	private void method95() {
		for(int j = 0; j < npcCount; j++) {
			int k = npcIndices[j];
			NPC npc = npcArray[k];
			if(npc != null)
				method96(npc);
		}
	}

	private void method96(Entity entity)
	{
		if(entity.x < 128 || entity.y < 128 || entity.x >= 13184 || entity.y >= 13184)
		{
			entity.anim = -1;
			entity.anInt1520 = -1;
			entity.anInt1547 = 0;
			entity.anInt1548 = 0;
			entity.x = entity.smallX[0] * 128 + entity.anInt1540 * 64;
			entity.y = entity.smallY[0] * 128 + entity.anInt1540 * 64;
			entity.method446();
		}
		if(entity == myPlayer && (entity.x < 1536 || entity.y < 1536 || entity.x >= 11776 || entity.y >= 11776))
		{
			entity.anim = -1;
			entity.anInt1520 = -1;
			entity.anInt1547 = 0;
			entity.anInt1548 = 0;
			entity.x = entity.smallX[0] * 128 + entity.anInt1540 * 64;
			entity.y = entity.smallY[0] * 128 + entity.anInt1540 * 64;
			entity.method446();
		}
		if(entity.anInt1547 > loopCycle)
			method97(entity);
		else
		if(entity.anInt1548 >= loopCycle)
			method98(entity);
		else
			method99(entity);
		method100(entity);
		method101(entity);
	}

	private void method97(Entity entity)
	{
		int i = entity.anInt1547 - loopCycle;
		int j = entity.anInt1543 * 128 + entity.anInt1540 * 64;
		int k = entity.anInt1545 * 128 + entity.anInt1540 * 64;
		entity.x += (j - entity.x) / i;
		entity.y += (k - entity.y) / i;
		entity.anInt1503 = 0;
		if(entity.anInt1549 == 0)
			entity.turnDirection = 1024;
		if(entity.anInt1549 == 1)
			entity.turnDirection = 1536;
		if(entity.anInt1549 == 2)
			entity.turnDirection = 0;
		if(entity.anInt1549 == 3)
			entity.turnDirection = 512;
	}

	private void method98(Entity entity)
	{
		if(entity.anInt1548 == loopCycle || entity.anim == -1 || entity.anInt1529 != 0 || entity.anInt1528 + 1 > Animation.anims[entity.anim].method258(entity.anInt1527))
		{
			int i = entity.anInt1548 - entity.anInt1547;
			int j = loopCycle - entity.anInt1547;
			int k = entity.anInt1543 * 128 + entity.anInt1540 * 64;
			int l = entity.anInt1545 * 128 + entity.anInt1540 * 64;
			int i1 = entity.anInt1544 * 128 + entity.anInt1540 * 64;
			int j1 = entity.anInt1546 * 128 + entity.anInt1540 * 64;
			entity.x = (k * (i - j) + i1 * j) / i;
			entity.y = (l * (i - j) + j1 * j) / i;
		}
		entity.anInt1503 = 0;
		if(entity.anInt1549 == 0)
			entity.turnDirection = 1024;
		if(entity.anInt1549 == 1)
			entity.turnDirection = 1536;
		if(entity.anInt1549 == 2)
			entity.turnDirection = 0;
		if(entity.anInt1549 == 3)
			entity.turnDirection = 512;
		entity.anInt1552 = entity.turnDirection;
	}

	private void method99(Entity entity)
	{
		entity.anInt1517 = entity.anInt1511;
		if(entity.smallXYIndex == 0)
		{
			entity.anInt1503 = 0;
			return;
		}
		if(entity.anim != -1 && entity.anInt1529 == 0)
		{
			Animation animation = Animation.anims[entity.anim];
			if(entity.anInt1542 > 0 && animation.anInt363 == 0)
			{
				entity.anInt1503++;
				return;
			}
			if(entity.anInt1542 <= 0 && animation.anInt364 == 0)
			{
				entity.anInt1503++;
				return;
			}
		}
		int i = entity.x;
		int j = entity.y;
		int k = entity.smallX[entity.smallXYIndex - 1] * 128 + entity.anInt1540 * 64;
		int l = entity.smallY[entity.smallXYIndex - 1] * 128 + entity.anInt1540 * 64;
		if(k - i > 256 || k - i < -256 || l - j > 256 || l - j < -256)
		{
			entity.x = k;
			entity.y = l;
			return;
		}
		if(i < k)
		{
			if(j < l)
				entity.turnDirection = 1280;
			else
			if(j > l)
				entity.turnDirection = 1792;
			else
				entity.turnDirection = 1536;
		} else
		if(i > k)
		{
			if(j < l)
				entity.turnDirection = 768;
			else
			if(j > l)
				entity.turnDirection = 256;
			else
				entity.turnDirection = 512;
		} else
		if(j < l)
			entity.turnDirection = 1024;
		else
			entity.turnDirection = 0;
		int i1 = entity.turnDirection - entity.anInt1552 & 0x7ff;
		if(i1 > 1024)
			i1 -= 2048;
		int j1 = entity.anInt1555;
		if(i1 >= -256 && i1 <= 256)
			j1 = entity.anInt1554;
		else
		if(i1 >= 256 && i1 < 768)
			j1 = entity.anInt1557;
		else
		if(i1 >= -768 && i1 <= -256)
			j1 = entity.anInt1556;
		if(j1 == -1)
			j1 = entity.anInt1554;
		entity.anInt1517 = j1;
		int k1 = 4;
		if(entity.anInt1552 != entity.turnDirection && entity.interactingEntity == -1 && entity.anInt1504 != 0)
			k1 = 2;
		if(entity.smallXYIndex > 2)
			k1 = 6;
		if(entity.smallXYIndex > 3)
			k1 = 8;
		if(entity.anInt1503 > 0 && entity.smallXYIndex > 1)
		{
			k1 = 8;
			entity.anInt1503--;
		}
		if(entity.aBooleanArray1553[entity.smallXYIndex - 1])
			k1 <<= 1;
		if(k1 >= 8 && entity.anInt1517 == entity.anInt1554 && entity.anInt1505 != -1)
			entity.anInt1517 = entity.anInt1505;
		if(i < k)
		{
			entity.x += k1;
			if(entity.x > k)
				entity.x = k;
		} else
		if(i > k)
		{
			entity.x -= k1;
			if(entity.x < k)
				entity.x = k;
		}
		if(j < l)
		{
			entity.y += k1;
			if(entity.y > l)
				entity.y = l;
		} else
		if(j > l)
		{
			entity.y -= k1;
			if(entity.y < l)
				entity.y = l;
		}
		if(entity.x == k && entity.y == l)
		{
			entity.smallXYIndex--;
			if(entity.anInt1542 > 0)
				entity.anInt1542--;
		}
	}

	private void method100(Entity entity)
	{
		if(entity.anInt1504 == 0)
			return;
		if(entity.interactingEntity != -1 && entity.interactingEntity < 32768)
		{
			NPC npc = npcArray[(entity.interactingEntity > npcArray.length) ? (npcArray.length-1) : entity.interactingEntity];
			if(npc != null)
			{
				int i1 = entity.x - npc.x;
				int k1 = entity.y - npc.y;
				if(i1 != 0 || k1 != 0)
					entity.turnDirection = (int)(Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
			}
		}
		if(entity.interactingEntity >= 32768)
		{
			int j = entity.interactingEntity - 32768;
			if(j == unknownInt10)
				j = myPlayerIndex;
			Player player = playerArray[j];
			if(player != null)
			{
				int l1 = entity.x - player.x;
				int i2 = entity.y - player.y;
				if(l1 != 0 || i2 != 0)
					entity.turnDirection = (int)(Math.atan2(l1, i2) * 325.94900000000001D) & 0x7ff;
			}
		}
		if((entity.anInt1538 != 0 || entity.anInt1539 != 0) && (entity.smallXYIndex == 0 || entity.anInt1503 > 0))
		{
			int k = entity.x - (entity.anInt1538 - baseX - baseX) * 64;
			int j1 = entity.y - (entity.anInt1539 - baseY - baseY) * 64;
			if(k != 0 || j1 != 0)
				entity.turnDirection = (int)(Math.atan2(k, j1) * 325.94900000000001D) & 0x7ff;
			entity.anInt1538 = 0;
			entity.anInt1539 = 0;
		}
		int l = entity.turnDirection - entity.anInt1552 & 0x7ff;
		if(l != 0)
		{
			if(l < entity.anInt1504 || l > 2048 - entity.anInt1504)
				entity.anInt1552 = entity.turnDirection;
			else
			if(l > 1024)
				entity.anInt1552 -= entity.anInt1504;
			else
				entity.anInt1552 += entity.anInt1504;
			entity.anInt1552 &= 0x7ff;
			if(entity.anInt1517 == entity.anInt1511 && entity.anInt1552 != entity.turnDirection)
			{
				if(entity.anInt1512 != -1)
				{
					entity.anInt1517 = entity.anInt1512;
					return;
				}
				entity.anInt1517 = entity.anInt1554;
			}
		}
	}

	private void method101(Entity entity)
	{
		try {
			entity.aBoolean1541 = false;
			if (entity.anInt1517 != -1 && entity.anInt1517 != 65283) {
                        Animation animation = Animation.anims[entity.anInt1517];
                        entity.anInt1519++;
                        if (entity.anInt1518 < animation.anInt352 && entity.anInt1519 > animation.method258(entity.anInt1518)) {
                                entity.anInt1519 = 1;//this is the frame delay. 0 is what it's normally at. higher number = faster animations.
                                entity.anInt1518++;
                        }
                        if (entity.anInt1518 >= animation.anInt352) {
                                entity.anInt1519 = 1;
                                entity.anInt1518 = 0;
                        }
                }
			if(entity.anInt1520 != -1 && loopCycle >= entity.anInt1523)
			{
				if(entity.anInt1521 < 0)
					entity.anInt1521 = 0;
				Animation animation_1 = SpotAnim.cache[entity.anInt1520].aAnimation_407;
				for(entity.anInt1522++; entity.anInt1521 < animation_1.anInt352 && entity.anInt1522 > animation_1.method258(entity.anInt1521); entity.anInt1521++)
					entity.anInt1522 -= animation_1.method258(entity.anInt1521);

				if(entity.anInt1521 >= animation_1.anInt352 && (entity.anInt1521 < 0 || entity.anInt1521 >= animation_1.anInt352))
					entity.anInt1520 = -1;
			}
			if(entity.anim != -1 && entity.anInt1529 <= 1)
			{
				Animation animation_2 = Animation.anims[entity.anim];
				if(animation_2.anInt363 == 1 && entity.anInt1542 > 0 && entity.anInt1547 <= loopCycle && entity.anInt1548 < loopCycle)
				{
					entity.anInt1529 = 1;
					return;
				}
			}
			if(entity.anim != -1 && entity.anInt1529 == 0)
			{
				Animation animation_3 = Animation.anims[entity.anim];
				for(entity.anInt1528++; entity.anInt1527 < animation_3.anInt352 && entity.anInt1528 > animation_3.method258(entity.anInt1527); entity.anInt1527++)
					entity.anInt1528 -= animation_3.method258(entity.anInt1527);

				if(entity.anInt1527 >= animation_3.anInt352)
				{
					entity.anInt1527 -= animation_3.anInt356;
					entity.anInt1530++;
					if(entity.anInt1530 >= animation_3.anInt362)
						entity.anim = -1;
					if(entity.anInt1527 < 0 || entity.anInt1527 >= animation_3.anInt352)
						entity.anim = -1;
				}
				entity.aBoolean1541 = animation_3.aBoolean358;
			}
			if(entity.anInt1529 > 0)
				entity.anInt1529--;
		} catch(NullPointerException ex) {
			
		}
	}

	private void drawGameScreen()
	{
		if (fullscreenInterfaceID != -1 && (loadingStage == 2 || super.fullGameScreen != null)) {
			if (loadingStage == 2) {
				method119(anInt945, fullscreenInterfaceID);
				if (openInterfaceID != -1) {
					method119(anInt945, openInterfaceID);
				}
				anInt945 = 0;
				resetAllImageProducers();
				super.fullGameScreen.initDrawingArea();
				Texture.anIntArray1472 = fullScreenTextureArray;
				DrawingArea.setAllPixelsToZero();
				welcomeScreenRaised = true;
				if (openInterfaceID != -1) {
					RSInterface rsInterface_1 = RSInterface.interfaceCache[openInterfaceID];
					if (rsInterface_1.width == 512 && rsInterface_1.height == 334 && rsInterface_1.type == 0) {
						rsInterface_1.width = 765;
						rsInterface_1.height = 503;
					}
					drawInterface(0, 0, rsInterface_1, 8);//used for fullscreen
				}
				RSInterface rsInterface = RSInterface.interfaceCache[fullscreenInterfaceID];
				if (rsInterface.width == 512 && rsInterface.height == 334 && rsInterface.type == 0) {
					rsInterface.width = 765;
					rsInterface.height = 503;
				}
				drawInterface(0, 0, rsInterface, 8);

				if (!contextMenuOpen) {
					processRightClick();
					drawTooltip();
				} else {
					drawMenu(4,4);
				}
			}
			drawCount++;
			super.fullGameScreen.drawGraphics(0, super.graphics, 0);
			return;
		} else {
			if (drawCount != 0) {
				resetImageProducers2();
			}
		}
		if(welcomeScreenRaised) {
			welcomeScreenRaised = false;
			if(!toggleFullscreen) {
				topFrame.drawGraphics(0, super.graphics, 0);
				leftFrame.drawGraphics(4, super.graphics, 0);
			}
			inputTaken = true;
			tabAreaAltered = true;
			if(loadingStage != 2) {
				main3DArea.drawGraphics(toggleFullscreen ? 0 : 4, super.graphics, toggleFullscreen ? 0 : 4);
				mapBackImage.drawGraphics(mapDrawAreaX, super.graphics, mapDrawAreaY);
			}
		}
		if(contextMenuOpen)
			drawMenu(4,4);
		if(contextMenuOpen && menuScreenArea == 1) {
		}
		if(invOverlayInterfaceID != -1)
		{
			boolean flag1 = method119(anInt945, invOverlayInterfaceID);
			if(flag1) {
			}
		}
		if(atInventoryInterfaceType == 2) {
		}
		if(activeInterfaceType == 2) {
		}
		if(!toggleFullscreen) {
			drawRightFrameArea();
			drawTabArea(sideTabArea,0,0);
			sideTabArea.drawGraphics(yPaddingtabBack, super.graphics, xPaddingtabBack);//519
			main3DArea.initDrawingArea();
		}
		if(backDialogID == -1)
		{
			aClass9_1059.scrollPosition = chatScrollTotalLength - anInt1089 - 110;
			if(super.mouseX > 497 && super.mouseX < 512 && super.mouseY > 342 + getChatBackIncreaseY())
				method65(494, 110, super.mouseX - 0, super.mouseY - 348 - getChatBackIncreaseY(), aClass9_1059, 0, false, chatScrollTotalLength);
			int i = chatScrollTotalLength - 110 - aClass9_1059.scrollPosition;
			if(i < 0)
				i = 0;
			if(i > chatScrollTotalLength - 110)
				i = chatScrollTotalLength - 110;
			if(anInt1089 != i) {
				anInt1089 = i;
				inputTaken = true;
			}
		}
		if(backDialogID != -1) {
			boolean flag2 = method119(anInt945, backDialogID);
			if(flag2)
				inputTaken = true;
		}
		if(atInventoryInterfaceType == 3)
			inputTaken = true;
		if(activeInterfaceType == 3)
			inputTaken = true;
		if(aString844 != null)
			inputTaken = true;
		if(contextMenuOpen && menuScreenArea == 2)
			inputTaken = true;
		if(inputTaken) {
			if(!toggleFullscreen) {
				drawChatArea(toggleFullscreen ? main3DArea : chatBackImage,0,0);
				chatBackImage.drawGraphics(yPaddingchatBackImage, super.graphics, xPaddingchatBackImage);
				main3DArea.initDrawingArea();
			}
			inputTaken = false;
		}
		if(loadingStage == 2)
			method146();
		if(loadingStage == 2) {
			if(!toggleFullscreen) {
				drawMinimap(mapBackImage,0,0);
				mapBackImage.drawGraphics(yPaddingmapBackImage + getMapIncreaseY(), super.graphics, xPaddingmapBackImage + getMapIncreaseX());//545
			}
		}
		if(tabIconFlash != -1)
			tabAreaAltered = true;
		if(tabAreaAltered)
		{
			if(tabIconFlash != -1 && tabIconFlash == tabID)
			{
				tabIconFlash = -1;
				stream.createFrame(120);
				stream.writeWordBigEndian(tabID);
			}
			tabAreaAltered = false;
			aRSImageProducer_1125.initDrawingArea();
			main3DArea.initDrawingArea();
		}
		anInt945 = 0;
	}

	private boolean buildFriendsListMenu(RSInterface class9)
	{
		int i = class9.contentType;
		if(i >= 1 && i <= 200 || i >= 701 && i <= 900)
		{
			if(i >= 801)
				i -= 701;
			else
			if(i >= 701)
				i -= 601;
			else
			if(i >= 101)
				i -= 101;
			else
				i--;
			menuActionName[menuActionRow] = "Remove @whi@" + friendsList[i];
			menuActionID[menuActionRow] = 792;
			menuActionRow++;
			menuActionName[menuActionRow] = "Message @whi@" + friendsList[i];
			menuActionID[menuActionRow] = 639;
			menuActionRow++;
			return true;
		}
		if(i >= 401 && i <= 500)
		{
			menuActionName[menuActionRow] = "Remove @whi@" + class9.message;
			menuActionID[menuActionRow] = 322;
			menuActionRow++;
			return true;
		} else
		{
			return false;
		}
	}

	private void method104()
	{
		Animable_Sub3 class30_sub2_sub4_sub3 = (Animable_Sub3)aClass19_1056.reverseGetFirst();
		for(; class30_sub2_sub4_sub3 != null; class30_sub2_sub4_sub3 = (Animable_Sub3)aClass19_1056.reverseGetNext())
			if(class30_sub2_sub4_sub3.anInt1560 != plane || class30_sub2_sub4_sub3.aBoolean1567)
				class30_sub2_sub4_sub3.unlink();
			else
			if(loopCycle >= class30_sub2_sub4_sub3.anInt1564)
			{
				class30_sub2_sub4_sub3.method454(anInt945);
				if(class30_sub2_sub4_sub3.aBoolean1567)
					class30_sub2_sub4_sub3.unlink();
				else
					worldController.method285(class30_sub2_sub4_sub3.anInt1560, 0, class30_sub2_sub4_sub3.anInt1563, -1, class30_sub2_sub4_sub3.anInt1562, 60, class30_sub2_sub4_sub3.anInt1561, class30_sub2_sub4_sub3, false);
			}

	}
	
	public void drawBlackBox(int xPos, int yPos) {
		DrawingArea.drawPixels(71, yPos - 1, xPos - 2, 0x726451, 1);
		DrawingArea.drawPixels(69, yPos, xPos + 174, 0x726451, 1);
		DrawingArea.drawPixels(1, yPos - 2, xPos - 2, 0x726451, 178);
		DrawingArea.drawPixels(1, yPos + 68, xPos, 0x726451, 174);
		DrawingArea.drawPixels(71, yPos - 1, xPos - 1, 0x2E2B23, 1);
		DrawingArea.drawPixels(71, yPos - 1, xPos + 175, 0x2E2B23, 1);
		DrawingArea.drawPixels(1, yPos - 1, xPos, 0x2E2B23, 175);
		DrawingArea.drawPixels(1, yPos + 69, xPos, 0x2E2B23, 175);
		DrawingArea.method335(0, yPos, 174, 68, 220, xPos);
	}
	
	/* NEW INTERFACE TEXT ENGINE - private void drawInterface(int yScrollPoint, int xPadding, RSInterface rsInterface, int yPadding) {
		try {
			if(rsInterface.type != 0 || rsInterface.children == null)
				return;
			if(rsInterface.isMouseoverTriggered && lastHoverChildId != rsInterface.id && anInt1048 != rsInterface.id && anInt1039 != rsInterface.id)
				return;
			int xTopPos = DrawingArea.topX;
			int yTopPos = DrawingArea.topY;
			int xBottomPos = DrawingArea.bottomX;
			int yBottomPos = DrawingArea.bottomY;
			DrawingArea.setDrawingArea(yPadding + rsInterface.height, xPadding, xPadding + rsInterface.width, yPadding);
			int totalChildren = rsInterface.children.length;
			for(int childIndex = 0; childIndex < totalChildren; childIndex++) {
				int xSpritePos = rsInterface.childX[childIndex] + xPadding;
				int ySpritePos = (rsInterface.childY[childIndex] + yPadding) - yScrollPoint;
				RSInterface rsChildren = RSInterface.interfaceCache[rsInterface.children[childIndex]];
				xSpritePos += rsChildren.anInt263;
				ySpritePos += rsChildren.anInt265;
				if(rsChildren.contentType > 0)
					drawFriendsListOrWelcomeScreen(rsChildren);
				//here
				int[] IDs = {
					1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249, 1258, 1267, 1274, 1283, 1573,
					1290, 1299, 1308, 1315, 1324, 1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388,
					1397, 1404, 1583, 12038, 1414, 1421, 1430, 1437, 1446, 1453, 1460, 1469, 15878,
					1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512, 1521, 1530, 1544, 1553,
					1563, 1593, 1635, 12426, 12436, 12446, 12456, 6004, 18471,
					/* Ancients *
					12940, 12988, 13036, 12902, 12862, 13046, 12964, 13012, 13054, 12920, 12882, 13062,
					12952, 13000, 13070, 12912, 12872, 13080, 12976, 13024, 13088, 12930, 12892, 13096
				};
				for(int m5 = 0; m5 < IDs.length; m5++) {
					if(rsChildren.id == IDs[m5] + 1) {
						if(m5 > 61)
							drawBlackBox(xSpritePos + 1, ySpritePos);
						else
							drawBlackBox(xSpritePos, ySpritePos + 1);
					}
				}
				int[] runeChildren = {
					1202, 1203, 1209, 1210, 1211, 1218, 1219, 1220, 1227, 1228, 1234, 1235, 1236, 1243, 1244, 1245,
					1252, 1253, 1254, 1261, 1262, 1263, 1270, 1271, 1277, 1278, 1279, 1286, 1287, 1293, 1294, 1295,
					1302, 1303, 1304, 1311, 1312, 1318, 1319, 1320, 1327, 1328, 1329, 1336, 1337, 1343, 1344, 1345,
					1352, 1353, 1354, 1361, 1362, 1363, 1370, 1371, 1377, 1378, 1384, 1385, 1391, 1392, 1393, 1400,
					1401, 1407, 1408, 1410, 1417, 1418, 1424, 1425, 1426, 1433, 1434, 1440, 1441, 1442, 1449, 1450,
					1456, 1457, 1463, 1464, 1465, 1472, 1473, 1474, 1481, 1482, 1488, 1489, 1490, 1497, 1498, 1499,
					1506, 1507, 1508, 1515, 1516, 1517, 1524, 1525, 1526, 1533, 1534, 1535, 1547, 1548, 1549, 1556,
					1557, 1558, 1566, 1567, 1568, 1576, 1577, 1578, 1586, 1587, 1588, 1596, 1597, 1598, 1605, 1606,
					1607, 1616, 1617, 1618, 1627, 1628, 1629, 1638, 1639, 1640, 6007, 6008, 6011, 8673, 8674, 12041,
					12042, 12429, 12430, 12431, 12439, 12440, 12441, 12449, 12450, 12451, 12459, 12460, 15881, 15882,
					15885, 18474, 18475, 18478
				};
				for(int r = 0; r < runeChildren.length; r++)
					if(rsChildren.id == runeChildren[r])
						rsChildren.modelZoom = 775;
				if(rsChildren.type == 0) {
					if(rsChildren.scrollPosition > rsChildren.scrollMax - rsChildren.height)
						rsChildren.scrollPosition = rsChildren.scrollMax - rsChildren.height;
					if(rsChildren.scrollPosition < 0)
						rsChildren.scrollPosition = 0;
					drawInterface(rsChildren.scrollPosition, xSpritePos, rsChildren, ySpritePos);
					if(rsChildren.scrollMax > rsChildren.height)
						drawScrollbar(rsChildren.height, rsChildren.scrollPosition, ySpritePos, xSpritePos + rsChildren.width, rsChildren.scrollMax,false);
				} else if(rsChildren.type != 1)
					if(rsChildren.type == 2) {
						int spriteIndex = 0;
						for(int l3 = 0; l3 < rsChildren.height; l3++) {
							for(int l4 = 0; l4 < rsChildren.width; l4++) {
								int k5 = xSpritePos + l4 * (32 + rsChildren.invSpritePadX);
								int j6 = ySpritePos + l3 * (32 + rsChildren.invSpritePadY);
								if(spriteIndex < 20) {
									k5 += rsChildren.spritesX[spriteIndex];
									j6 += rsChildren.spritesY[spriteIndex];
								}
								if(rsChildren.inv[spriteIndex] > 0) {
									int k6 = 0;
									int j7 = 0;
									int inventoryItemId = rsChildren.inv[spriteIndex] - 1;
									if(k5 > DrawingArea.topX - 32 && k5 < DrawingArea.bottomX && j6 > DrawingArea.topY - 32 && j6 < DrawingArea.bottomY || activeInterfaceType != 0 && anInt1085 == spriteIndex) {
										int selectedColour = 0;
										if(itemSelected == 1 && itemSlotUsedOn == spriteIndex && anInt1284 == rsChildren.id)
											selectedColour = 0xffffff;
										Sprite itemSprite = ItemDef.getSprite(inventoryItemId, rsChildren.invStackSizes[spriteIndex], selectedColour);
										if(itemSprite != null) {
											if(activeInterfaceType != 0 && anInt1085 == spriteIndex && anInt1084 == rsChildren.id) {
												k6 = super.mouseX - anInt1087;
												j7 = super.mouseY - anInt1088;
												if(k6 < 5 && k6 > -5)
													k6 = 0;
												if(j7 < 5 && j7 > -5)
													j7 = 0;
												if(anInt989 < 5) {
													k6 = 0;
													j7 = 0;
												}
												itemSprite.drawSprite1(k5 + k6, j6 + j7, 128);
												if(j6 + j7 < DrawingArea.topY && rsInterface.scrollPosition > 0) {
													int i10 = (anInt945 * (DrawingArea.topY - j6 - j7)) / 3;
													if(i10 > anInt945 * 10)
														i10 = anInt945 * 10;
													if(i10 > rsInterface.scrollPosition)
														i10 = rsInterface.scrollPosition;
													rsInterface.scrollPosition -= i10;
													anInt1088 += i10;
												}
												if(j6 + j7 + 32 > DrawingArea.bottomY && rsInterface.scrollPosition < rsInterface.scrollMax - rsInterface.height) {
													int j10 = (anInt945 * ((j6 + j7 + 32) - DrawingArea.bottomY)) / 3;
													if(j10 > anInt945 * 10)
														j10 = anInt945 * 10;
													if(j10 > rsInterface.scrollMax - rsInterface.height - rsInterface.scrollPosition)
														j10 = rsInterface.scrollMax - rsInterface.height - rsInterface.scrollPosition;
													rsInterface.scrollPosition += j10;
													anInt1088 -= j10;
												}
											} else if(atInventoryInterfaceType != 0 && atInventoryIndex == spriteIndex && atInventoryInterface == rsChildren.id)
												itemSprite.drawSprite1(k5, j6, 128);
											else
												itemSprite.drawSprite(k5, j6);
											if(rsChildren.parentID  == 3824) {
												infinityIcon.drawSprite(k5, j6);
											} else if(itemSprite.maxWidth == 33 || rsChildren.invStackSizes[spriteIndex] != 1) {
												int k10 = rsChildren.invStackSizes[spriteIndex];
												if(k10 >= 1)
													newSmallFont.drawBasicString(intToKOrMil(k10), k5 + k6, j6 + 9 + j7, 0xFFFF00, 0);
												if(k10 >= 100000)
													newSmallFont.drawBasicString(intToKOrMil(k10), k5 + k6, j6 + 9 + j7, 0xFFFFFF, 0);
												if(k10 >= 10000000)
													newSmallFont.drawBasicString(intToKOrMil(k10), k5 + k6, j6 + 9 + j7, 0x00FF80, 0);
												if(k10 >= 1000000000)
													newSmallFont.drawBasicString(intToKOrMil(k10), k5 + k6, j6 + 9 + j7, 0xFF0000, 0);
											}
										}
									}
								} else if(rsChildren.sprites != null && spriteIndex < 20) {
									Sprite sprite = rsChildren.sprites[spriteIndex];
									if(sprite != null)
										sprite.drawSprite(k5, j6);
								}
								spriteIndex++;
							}
						}
					} else if(rsChildren.type == 3) {
						boolean flag = false;
						if(anInt1039 == rsChildren.id || anInt1048 == rsChildren.id || lastHoverChildId == rsChildren.id)
							flag = true;
						int j3;
						if(interfaceIsSelected(rsChildren)) {
							j3 = rsChildren.anInt219;
							if(flag && rsChildren.anInt239 != 0)
								j3 = rsChildren.anInt239;
						} else {
							j3 = rsChildren.textColor;
							if(flag && rsChildren.textHoverColour != 0)
								j3 = rsChildren.textHoverColour;
						}
						if(rsChildren.opacity == 0) {
							if(rsChildren.aBoolean227)
								DrawingArea.drawPixels(rsChildren.height, ySpritePos, xSpritePos, j3, rsChildren.width);
							else
								DrawingArea.fillPixels(xSpritePos, rsChildren.width, rsChildren.height, j3, ySpritePos);
						} else if(rsChildren.aBoolean227)
							DrawingArea.method335(j3, ySpritePos, rsChildren.width, rsChildren.height, 256 - (rsChildren.opacity & 0xff), xSpritePos);
						else
							DrawingArea.method338(ySpritePos, rsChildren.height, 256 - (rsChildren.opacity & 0xff), j3, rsChildren.width, xSpritePos);
					} else if(rsChildren.type == 4) {
						RSFont textDrawingArea = rsChildren.textDrawingAreas;
						String s = rsChildren.message;
						boolean flag1 = false;
						if(anInt1039 == rsChildren.id || anInt1048 == rsChildren.id || lastHoverChildId == rsChildren.id)
							flag1 = true;
						int i4;
						if(interfaceIsSelected(rsChildren)) {
							i4 = rsChildren.anInt219;
							if(flag1 && rsChildren.anInt239 != 0)
								i4 = rsChildren.anInt239;
							if(rsChildren.disabledText.length() > 0)
								s = rsChildren.disabledText;
						} else {
							i4 = rsChildren.textColor;
							if(flag1 && rsChildren.textHoverColour != 0)
								i4 = rsChildren.textHoverColour;
						}
						if(rsChildren.atActionType == 6 && aBoolean1149) {
							s = "Please wait...";
							i4 = rsChildren.textColor;
						}
						if(DrawingArea.width == 519) {
							if(i4 == 0xffff00)
								i4 = 255;
							if(i4 == 49152)
								i4 = 0xffffff;
						}
						if((rsChildren.parentID == 1151) || (rsChildren.parentID == 12855)) {
							switch (i4) {
								case 16773120: i4 = 0xFE981F; break;
								case 7040819: i4 = 0xAF6A1A; break;
							}
						}
						for(int l6 = ySpritePos + textDrawingArea.baseCharacterHeight; s.length() > 0; l6 += textDrawingArea.baseCharacterHeight) {
							if(s.indexOf("%") != -1) {
								do {
									int k7 = s.indexOf("%1");
									if(k7 == -1)
										break;
									if(rsChildren.id < 4000 || rsChildren.id > 5000 && rsChildren.id !=13921 && rsChildren.id !=13922 && rsChildren.id !=12171 && rsChildren.id !=12172)
										s = s.substring(0, k7) + methodR(extractInterfaceValues(rsChildren, 0)) + s.substring(k7 + 2);
									else
										s = s.substring(0, k7) + interfaceIntToString(extractInterfaceValues(rsChildren, 0)) + s.substring(k7 + 2);
								} while(true);
								do {
									int l7 = s.indexOf("%2");
									if(l7 == -1)
										break;
									s = s.substring(0, l7) + interfaceIntToString(extractInterfaceValues(rsChildren, 1)) + s.substring(l7 + 2);
								} while(true);
								do {
									int i8 = s.indexOf("%3");
									if(i8 == -1)
										break;
									s = s.substring(0, i8) + interfaceIntToString(extractInterfaceValues(rsChildren, 2)) + s.substring(i8 + 2);
								} while(true);
								do {
									int j8 = s.indexOf("%4");
									if(j8 == -1)
										break;
									s = s.substring(0, j8) + interfaceIntToString(extractInterfaceValues(rsChildren, 3)) + s.substring(j8 + 2);
								} while(true);
								do {
									int k8 = s.indexOf("%5");
									if(k8 == -1)
										break;
									s = s.substring(0, k8) + interfaceIntToString(extractInterfaceValues(rsChildren, 4)) + s.substring(k8 + 2);
								} while(true);
							}
							int l8 = s.indexOf("\\n");
							String s1;
							if(l8 != -1) {
								s1 = s.substring(0, l8);
								s = s.substring(l8 + 2);
							} else {
								s1 = s;
								s = "";
							}
							if(rsChildren.centerText){
								//textDrawingArea.method382(i4, xSpritePos + rsChildren.width / 2, s1, l6, rsChildren.textShadow);
								textDrawingArea.drawCenteredString(s1, xSpritePos + rsChildren.width / 2, l6, i4,(rsChildren.textShadow? 0: -1));
							} else {
								//textDrawingArea.method389(rsChildren.textShadow, xSpritePos, i4, s1, l6);
								textDrawingArea.drawBasicString(s1, xSpritePos , l6, i4,(rsChildren.textShadow? 0: -1));
							}
							
						}
					} else if(rsChildren.type == 5 || rsChildren.type == 10) {
						Sprite sprite = null;
						if(rsChildren.itemSpriteId1 != -1 && rsChildren.sprite1 == null) {
							rsChildren.sprite1 = ItemDef.getSprite(rsChildren.itemSpriteId1, 1, (rsChildren.itemSpriteZoom1 == -1) ? 0 : -1,rsChildren.itemSpriteZoom1);
							rsChildren.sprite2 = ItemDef.getSprite(rsChildren.itemSpriteId2, 1, (rsChildren.itemSpriteZoom2 == -1) ? 0 : -1,rsChildren.itemSpriteZoom2);
							//rsChildren.sprite2 = ItemDef.getSprite(rsChildren.itemSpriteId2, rsChildren.invStackSizes[spriteIndex], selectedColour);
							if(rsChildren.greyScale)
								rsChildren.sprite1.greyScale();
						}
						if(interfaceIsSelected(rsChildren) || hoverSpriteId == rsChildren.id)
							sprite = rsChildren.sprite2;
						else
							sprite = rsChildren.sprite1;
						if(spellSelected == 1 && rsChildren.id == spellID && spellID != 0 && sprite != null) {
							sprite.drawSprite(xSpritePos, ySpritePos, 0xffffff);
						} else {
							if (sprite != null)
								if(rsChildren.type == 5)
									sprite.drawSprite(xSpritePos, ySpritePos);
								else
									sprite.drawSprite1(xSpritePos, ySpritePos, rsChildren.opacity);								
						}
						if(autoCast && rsChildren.id == autocastId)
							magicAuto.drawSprite(xSpritePos-3, ySpritePos-2);
					} else if(rsChildren.type == 6) {
						int k3 = Texture.textureInt1;
						int j4 = Texture.textureInt2;
						Texture.textureInt1 = xSpritePos + rsChildren.width / 2;
						Texture.textureInt2 = ySpritePos + rsChildren.height / 2;
						int i5 = Texture.anIntArray1470[rsChildren.modelRotation1] * rsChildren.modelZoom >> 16;
						int l5 = Texture.anIntArray1471[rsChildren.modelRotation1] * rsChildren.modelZoom >> 16;
						boolean flag2 = interfaceIsSelected(rsChildren);
						int i7;
						if(flag2)
							i7 = rsChildren.anInt258;
						else
							i7 = rsChildren.anInt257;
						Model model;
						if(i7 == -1) {
							model = rsChildren.method209(-1, -1, flag2);
						} else {
							Animation animation = Animation.anims[i7];
							model = rsChildren.method209(animation.anIntArray354[rsChildren.anInt246], animation.anIntArray353[rsChildren.anInt246], flag2);
						}
						if(model != null)
							model.method482(rsChildren.modelRotation2, 0, rsChildren.modelRotation1, 0, i5, l5);
						Texture.textureInt1 = k3;
						Texture.textureInt2 = j4;
					
					} else if(rsChildren.type == 7) {
						RSFont textDrawingArea_1 = rsChildren.textDrawingAreas;
						int k4 = 0;
						for(int j5 = 0; j5 < rsChildren.height; j5++) {
							for(int i6 = 0; i6 < rsChildren.width; i6++) {
								if(rsChildren.inv[k4] > 0) {
									ItemDef itemDef = ItemDef.forID(rsChildren.inv[k4] - 1);
									String s2 = itemDef.name;
									if(itemDef.stackable || rsChildren.invStackSizes[k4] != 1)
										s2 = s2 + " x" + intToKOrMilLongName(rsChildren.invStackSizes[k4]);
									int i9 = xSpritePos + i6 * (115 + rsChildren.invSpritePadX);
									int k9 = ySpritePos + j5 * (12 + rsChildren.invSpritePadY);
									if(rsChildren.centerText)
										textDrawingArea_1.drawCenteredString(s2, i9 + rsChildren.width / 2, k9, rsChildren.textColor, (rsChildren.textShadow ? 0: -1));
									else
										textDrawingArea_1.drawBasicString(s2, i9, k9, rsChildren.textColor,(rsChildren.textShadow ? 0: -1)); 
								}
								k4++;
							}
						}
					}  else if (rsChildren.type == 8 && (anInt1500 == rsChildren.id || anInt1044 == rsChildren.id || lastHoverToggleChildId == rsChildren.id) && anInt1501 == 100) {
						int boxWidth = 0;
						int boxHeight = 0;
						TextDrawingArea textDrawingArea_2 = normalFont;
						for (String s1 = rsChildren.message; s1.length() > 0;) {
							int l7 = s1.indexOf("\\n");
							String s4;
							if (l7 != -1) {
								s4 = s1.substring(0, l7);
								s1 = s1.substring(l7 + 2);
							} else {
								s4 = s1;
								s1 = "";
							}
							int j10 = textDrawingArea_2.getTextWidth(s4);
							if (j10 > boxWidth) {
								boxWidth = j10;
							}
							boxHeight += textDrawingArea_2.anInt1497 + 1;
						}
						boxWidth += 6;
						boxHeight += 7;
						int xPos = (ySpritePos + rsChildren.width) - 5 - boxWidth;
						int yPos = xSpritePos + rsChildren.height + 5;
						if (xPos < ySpritePos + 5) {
							xPos = ySpritePos + 5;
						}
						if (xPos + boxWidth > yScrollPoint + rsInterface.width) {
							xPos = (yScrollPoint + rsInterface.width) - boxWidth;
						}
						if (yPos + boxHeight > xPadding + rsInterface.height) {
							yPos = (xPadding + rsInterface.height) - boxHeight;
						}
						DrawingArea.drawPixels(boxHeight, yPos, xPos, 0xFFFFA0, boxWidth);
						DrawingArea.fillPixels(xPos, boxWidth, boxHeight, 0, yPos);
						String s2 = rsChildren.message;
						for (int j11 = yPos + textDrawingArea_2.anInt1497 + 2; s2.length() > 0; j11 += textDrawingArea_2.anInt1497 + 1) {//anInt1497
							int newLineIndex = s2.indexOf("\\n");
							String s5;
							if (newLineIndex != -1) {
								s5 = s2.substring(0, newLineIndex);
								s2 = s2.substring(newLineIndex + 2);
							} else {
								s5 = s2;
								s2 = "";
							}
							textDrawingArea_2.method389(false, xPos + 3, 0, s5, j11);
						}
					} else if (rsChildren.type == 9) {
						drawHoverBox(xSpritePos, ySpritePos, rsChildren.message);
					}
			}
			DrawingArea.setDrawingArea(yBottomPos, xTopPos, xBottomPos, yTopPos);
		} catch(NullPointerException ex) {
			
		}
	}*/
	
		private void drawInterface(int yScrollPoint, int xPadding, RSInterface rsInterface, int yPadding) {
		try {
			if(rsInterface.type != 0 || rsInterface.children == null)
				return;
			if(rsInterface.isMouseoverTriggered && lastHoverChildId != rsInterface.id && anInt1048 != rsInterface.id && anInt1039 != rsInterface.id)
				return;
				
			int xTopPos = DrawingArea.topX;
			int yTopPos = DrawingArea.topY;
			int xBottomPos = DrawingArea.bottomX;
			int yBottomPos = DrawingArea.bottomY;
			DrawingArea.setDrawingArea(yPadding + rsInterface.height, xPadding, xPadding + rsInterface.width, yPadding);
			int totalChildren = rsInterface.children.length;
			for(int childIndex = 0; childIndex < totalChildren; childIndex++) {
				int xSpritePos = rsInterface.childX[childIndex] + xPadding;
				int ySpritePos = (rsInterface.childY[childIndex] + yPadding) - yScrollPoint;
				RSInterface rsChildren = RSInterface.interfaceCache[rsInterface.children[childIndex]];
				xSpritePos += rsChildren.anInt263;
				ySpritePos += rsChildren.anInt265;
				if(rsChildren.contentType > 0)
					drawFriendsListOrWelcomeScreen(rsChildren);
				
				int[] IDs = {
					1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249, 1258, 1267, 1274, 1283, 1573,
					1290, 1299, 1308, 1315, 1324, 1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388,
					1397, 1404, 1583, 12038, 1414, 1421, 1430, 1437, 1446, 1453, 1460, 1469, 15878,
					1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512, 1521, 1530, 1544, 1553,
					1563, 1593, 1635, 12426, 12436, 12446, 12456, 6004, 18471,
					/* Ancients */
					12940, 12988, 13036, 12902, 12862, 13046, 12964, 13012, 13054, 12920, 12882, 13062,
					12952, 13000, 13070, 12912, 12872, 13080, 12976, 13024, 13088, 12930, 12892, 13096
				};
				for(int m5 = 0; m5 < IDs.length; m5++) {
					if(rsChildren.id == IDs[m5] + 1) {
						if(m5 > 61)
							drawBlackBox(xSpritePos + 1, ySpritePos);
						else
							drawBlackBox(xSpritePos, ySpritePos + 1);
					}
				}
				int[] runeChildren = {
					1202, 1203, 1209, 1210, 1211, 1218, 1219, 1220, 1227, 1228, 1234, 1235, 1236, 1243, 1244, 1245,
					1252, 1253, 1254, 1261, 1262, 1263, 1270, 1271, 1277, 1278, 1279, 1286, 1287, 1293, 1294, 1295,
					1302, 1303, 1304, 1311, 1312, 1318, 1319, 1320, 1327, 1328, 1329, 1336, 1337, 1343, 1344, 1345,
					1352, 1353, 1354, 1361, 1362, 1363, 1370, 1371, 1377, 1378, 1384, 1385, 1391, 1392, 1393, 1400,
					1401, 1407, 1408, 1410, 1417, 1418, 1424, 1425, 1426, 1433, 1434, 1440, 1441, 1442, 1449, 1450,
					1456, 1457, 1463, 1464, 1465, 1472, 1473, 1474, 1481, 1482, 1488, 1489, 1490, 1497, 1498, 1499,
					1506, 1507, 1508, 1515, 1516, 1517, 1524, 1525, 1526, 1533, 1534, 1535, 1547, 1548, 1549, 1556,
					1557, 1558, 1566, 1567, 1568, 1576, 1577, 1578, 1586, 1587, 1588, 1596, 1597, 1598, 1605, 1606,
					1607, 1616, 1617, 1618, 1627, 1628, 1629, 1638, 1639, 1640, 6007, 6008, 6011, 8673, 8674, 12041,
					12042, 12429, 12430, 12431, 12439, 12440, 12441, 12449, 12450, 12451, 12459, 12460, 15881, 15882,
					15885, 18474, 18475, 18478
				};
				for(int r = 0; r < runeChildren.length; r++)
					if(rsChildren.id == runeChildren[r])
						rsChildren.modelZoom = 775;
				if(rsChildren.type == 0) {
				drawOnBankInterface();
					if(rsChildren.scrollPosition > rsChildren.scrollMax - rsChildren.height)
						rsChildren.scrollPosition = rsChildren.scrollMax - rsChildren.height;
					if(rsChildren.scrollPosition < 0)
						rsChildren.scrollPosition = 0;
					drawInterface(rsChildren.scrollPosition, xSpritePos, rsChildren, ySpritePos);
					if(rsChildren.scrollMax > rsChildren.height)
						drawScrollbar(rsChildren.height, rsChildren.scrollPosition, ySpritePos, xSpritePos + rsChildren.width, rsChildren.scrollMax,false);
				} else if(rsChildren.type != 1)
					if(rsChildren.type == 2) {
						int spriteIndex = 0;
						for(int l3 = 0; l3 < rsChildren.height; l3++) {
							for(int l4 = 0; l4 < rsChildren.width; l4++) {
								int k5 = xSpritePos + l4 * (32 + rsChildren.invSpritePadX);
								int j6 = ySpritePos + l3 * (32 + rsChildren.invSpritePadY);
								if(spriteIndex < 20) {
									k5 += rsChildren.spritesX[spriteIndex];
									j6 += rsChildren.spritesY[spriteIndex];
								}
								if(rsChildren.inv[spriteIndex] > 0) {
									int k6 = 0;
									int j7 = 0;
									int inventoryItemId = rsChildren.inv[spriteIndex] - 1;
									if(k5 > DrawingArea.topX - 32 && k5 < DrawingArea.bottomX && j6 > DrawingArea.topY - 32 && j6 < DrawingArea.bottomY || activeInterfaceType != 0 && anInt1085 == spriteIndex) {
										int selectedColour = 0;
										if(itemSelected == 1 && itemSlotUsedOn == spriteIndex && anInt1284 == rsChildren.id)
											selectedColour = 0xffffff;
									Sprite itemSprite = ItemDef.getSprite(inventoryItemId, rsChildren.invStackSizes[spriteIndex], selectedColour);
										ItemDef itemDef = ItemDef.forID(inventoryItemId);
										if(itemDef.barrows != null){
											itemSprite = new Sprite(itemDef.barrows, 0);
										}
										
										if(itemSprite != null) {
											if(activeInterfaceType != 0 && anInt1085 == spriteIndex && anInt1084 == rsChildren.id) {
												k6 = super.mouseX - anInt1087;
												j7 = super.mouseY - anInt1088;
												if(k6 < 5 && k6 > -5)
													k6 = 0;
												if(j7 < 5 && j7 > -5)
													j7 = 0;
												if(anInt989 < 10) {
													k6 = 0;
													j7 = 0;
												}
												itemSprite.drawSprite1(k5 + k6, j6 + j7, 128);
												if(j6 + j7 < DrawingArea.topY && rsInterface.scrollPosition > 0) {
													int i10 = (anInt945 * (DrawingArea.topY - j6 - j7)) / 3;
													if(i10 > anInt945 * 10)
														i10 = anInt945 * 10;
													if(i10 > rsInterface.scrollPosition)
														i10 = rsInterface.scrollPosition;
													rsInterface.scrollPosition -= i10;
													anInt1088 += i10;
												}
												if(j6 + j7 + 32 > DrawingArea.bottomY && rsInterface.scrollPosition < rsInterface.scrollMax - rsInterface.height) {
													int j10 = (anInt945 * ((j6 + j7 + 32) - DrawingArea.bottomY)) / 3;
													if(j10 > anInt945 * 10)
														j10 = anInt945 * 10;
													if(j10 > rsInterface.scrollMax - rsInterface.height - rsInterface.scrollPosition)
														j10 = rsInterface.scrollMax - rsInterface.height - rsInterface.scrollPosition;
													rsInterface.scrollPosition += j10;
													anInt1088 -= j10;
												}
											} else if(atInventoryInterfaceType != 0 && atInventoryIndex == spriteIndex && atInventoryInterface == rsChildren.id)
												itemSprite.drawSprite1(k5, j6, 128);
											else
												itemSprite.drawSprite(k5, j6);
											if(itemSprite.maxWidth == 33 || rsChildren.invStackSizes[spriteIndex] != 1) {
												int k10 = rsChildren.invStackSizes[spriteIndex];
												smallFont.method385(0,
														intToKOrMil(k10), j6 + 10
																+ j7, k5 + 1 + k6);
												if (k10 >= 1)
													smallFont.method385(0xFFFF00,
															intToKOrMil(k10), j6
																	+ 9 + j7, k5
																	+ k6);
												if (k10 >= 100000)
													smallFont.method385(0xFFFFFF,
															intToKOrMil(k10), j6
																	+ 9 + j7, k5
																	+ k6);
												if (k10 >= 10000000)
													smallFont.method385(0x00FF80,
															intToKOrMil(k10), j6
																	+ 9 + j7, k5
																	+ k6);
											}
										}
									}
								} else if(rsChildren.sprites != null && spriteIndex < 20) {
									Sprite sprite = rsChildren.sprites[spriteIndex];
									if(sprite != null)
										sprite.drawSprite(k5, j6);
								}
								spriteIndex++;
							}
						}
					} else if(rsChildren.type == 3) {
						boolean flag = false;
						if(anInt1039 == rsChildren.id || anInt1048 == rsChildren.id || lastHoverChildId == rsChildren.id)
							flag = true;
						int j3;
						if(interfaceIsSelected(rsChildren)) {
							j3 = rsChildren.anInt219;
							if(flag && rsChildren.anInt239 != 0)
								j3 = rsChildren.anInt239;
						} else {
							j3 = rsChildren.textColor;
							if(flag && rsChildren.textHoverColour != 0)
								j3 = rsChildren.textHoverColour;
						}
						if(rsChildren.opacity == 0) {
							if(rsChildren.aBoolean227)
								DrawingArea.drawPixels(rsChildren.height, ySpritePos, xSpritePos, j3, rsChildren.width);
							else
								DrawingArea.fillPixels(xSpritePos, rsChildren.width, rsChildren.height, j3, ySpritePos);
						} else if(rsChildren.aBoolean227)
							DrawingArea.method335(j3, ySpritePos, rsChildren.width, rsChildren.height, 256 - (rsChildren.opacity & 0xff), xSpritePos);
						else
							DrawingArea.method338(ySpritePos, rsChildren.height, 256 - (rsChildren.opacity & 0xff), j3, rsChildren.width, xSpritePos);
					} else if(rsChildren.type == 4) {
						TextDrawingArea textDrawingArea = rsChildren.textDrawingAreas;
						String s = rsChildren.message;
						boolean flag1 = false;
						if(anInt1039 == rsChildren.id || anInt1048 == rsChildren.id || lastHoverChildId == rsChildren.id)
							flag1 = true;
						int i4;
						if(interfaceIsSelected(rsChildren)) {
							i4 = rsChildren.anInt219;
							if(flag1 && rsChildren.anInt239 != 0)
								i4 = rsChildren.anInt239;
							if(rsChildren.disabledText.length() > 0)
								s = rsChildren.disabledText;
						} else {
							i4 = rsChildren.textColor;
							if(flag1 && rsChildren.textHoverColour != 0)
								i4 = rsChildren.textHoverColour;
						}
						if(rsChildren.atActionType == 6 && aBoolean1149) {
							s = "Please wait...";
							i4 = rsChildren.textColor;
						}
						if(DrawingArea.width == 519) {
							if(i4 == 0xffff00)
								i4 = 255;
							if(i4 == 49152)
								i4 = 0xffffff;
						}
						if((rsChildren.parentID == 1151) || (rsChildren.parentID == 12855)) {
							switch (i4) {
								case 16773120: i4 = 0xFE981F; break;
								case 7040819: i4 = 0xAF6A1A; break;
							}
						}
						for(int l6 = ySpritePos + textDrawingArea.anInt1497; s.length() > 0; l6 += textDrawingArea.anInt1497) {
							if(s.indexOf("%") != -1) {
								do {
									int k7 = s.indexOf("%1");
									if(k7 == -1)
										break;
									if(rsChildren.id < 4000 || rsChildren.id > 5000 && rsChildren.id !=13921 && rsChildren.id !=13922 && rsChildren.id !=12171 && rsChildren.id !=12172)
										s = s.substring(0, k7) + methodR(extractInterfaceValues(rsChildren, 0)) + s.substring(k7 + 2);
									else
										s = s.substring(0, k7) + interfaceIntToString(extractInterfaceValues(rsChildren, 0)) + s.substring(k7 + 2);
								} while(true);
								do {
									int l7 = s.indexOf("%2");
									if(l7 == -1)
										break;
									s = s.substring(0, l7) + interfaceIntToString(extractInterfaceValues(rsChildren, 1)) + s.substring(l7 + 2);
								} while(true);
								do {
									int i8 = s.indexOf("%3");
									if(i8 == -1)
										break;
									s = s.substring(0, i8) + interfaceIntToString(extractInterfaceValues(rsChildren, 2)) + s.substring(i8 + 2);
								} while(true);
								do {
									int j8 = s.indexOf("%4");
									if(j8 == -1)
										break;
									s = s.substring(0, j8) + interfaceIntToString(extractInterfaceValues(rsChildren, 3)) + s.substring(j8 + 2);
								} while(true);
								do {
									int k8 = s.indexOf("%5");
									if(k8 == -1)
										break;
									s = s.substring(0, k8) + interfaceIntToString(extractInterfaceValues(rsChildren, 4)) + s.substring(k8 + 2);
								} while(true);
							}
							int l8 = s.indexOf("\\n");
							String s1;
							if(l8 != -1) {
								s1 = s.substring(0, l8);
								s = s.substring(l8 + 2);
							} else {
								s1 = s;
								s = "";
							}
							if(rsChildren.centerText)
								textDrawingArea.method382(i4, xSpritePos + rsChildren.width / 2, s1, l6, rsChildren.textShadow);
							else
								textDrawingArea.method389(rsChildren.textShadow, xSpritePos, i4, s1, l6);
						}
					} else if(rsChildren.type == 5 || rsChildren.type == 10) {
						Sprite sprite = null;
						if(rsChildren.itemSpriteId1 != -1 && rsChildren.sprite1 == null) {
							rsChildren.sprite1 = ItemDef.getSprite(rsChildren.itemSpriteId1, 1, (rsChildren.itemSpriteZoom1 == -1) ? 0 : -1,rsChildren.itemSpriteZoom1);
							rsChildren.sprite2 = ItemDef.getSprite(rsChildren.itemSpriteId2, 1, (rsChildren.itemSpriteZoom2 == -1) ? 0 : -1,rsChildren.itemSpriteZoom2);
							//rsChildren.sprite2 = ItemDef.getSprite(rsChildren.itemSpriteId2, rsChildren.invStackSizes[spriteIndex], selectedColour);
							if(rsChildren.greyScale)
								rsChildren.sprite1.greyScale();
						}
						if(interfaceIsSelected(rsChildren) || hoverSpriteId == rsChildren.id)
							sprite = rsChildren.sprite2;
						else
							sprite = rsChildren.sprite1;
						if(spellSelected == 1 && rsChildren.id == spellID && spellID != 0 && sprite != null) {
							sprite.drawSprite(xSpritePos, ySpritePos, 0xffffff);
						} else {
							if (sprite != null)
								if(rsChildren.type == 5)
									sprite.drawSprite(xSpritePos, ySpritePos);
								else
									sprite.drawSprite1(xSpritePos, ySpritePos, rsChildren.opacity);								
						}
						if(autoCast && rsChildren.id == autocastId)
							magicAuto.drawSprite(xSpritePos-3, ySpritePos-2);
					} else if(rsChildren.type == 6) {
						int k3 = Texture.textureInt1;
						int j4 = Texture.textureInt2;
						Texture.textureInt1 = xSpritePos + rsChildren.width / 2;
						Texture.textureInt2 = ySpritePos + rsChildren.height / 2;
						int i5 = Texture.anIntArray1470[rsChildren.modelRotation1] * rsChildren.modelZoom >> 16;
						int l5 = Texture.anIntArray1471[rsChildren.modelRotation1] * rsChildren.modelZoom >> 16;
						boolean flag2 = interfaceIsSelected(rsChildren);
						int i7;
						if(flag2)
							i7 = rsChildren.anInt258;
						else
							i7 = rsChildren.anInt257;
						Model model;
						if(i7 == -1) {
							model = rsChildren.method209(-1, -1, flag2);
						} else {
							Animation animation = Animation.anims[i7];
							model = rsChildren.method209(animation.anIntArray354[rsChildren.anInt246], animation.anIntArray353[rsChildren.anInt246], flag2);
						}
						if(model != null)
							model.method482(rsChildren.modelRotation2, 0, rsChildren.modelRotation1, 0, i5, l5);
						Texture.textureInt1 = k3;
						Texture.textureInt2 = j4;
					
					} else if(rsChildren.type == 7) {
						TextDrawingArea textDrawingArea_1 = rsChildren.textDrawingAreas;
						int k4 = 0;
						for(int j5 = 0; j5 < rsChildren.height; j5++) {
							for(int i6 = 0; i6 < rsChildren.width; i6++) {
								if(rsChildren.inv[k4] > 0) {
									ItemDef itemDef = ItemDef.forID(rsChildren.inv[k4] - 1);
									String s2 = itemDef.name;
									if(itemDef.stackable || rsChildren.invStackSizes[k4] != 1)
										s2 = s2 + " x" + intToKOrMilLongName(rsChildren.invStackSizes[k4]);
									int i9 = xSpritePos + i6 * (115 + rsChildren.invSpritePadX);
									int k9 = ySpritePos + j5 * (12 + rsChildren.invSpritePadY);
									if(rsChildren.centerText)
										textDrawingArea_1.method382(rsChildren.textColor, i9 + rsChildren.width / 2, s2, k9, rsChildren.textShadow);
									else
										textDrawingArea_1.method389(rsChildren.textShadow, i9, rsChildren.textColor, s2, k9);
								}
								k4++;
							}
						}
					}  else if (rsChildren.type == 8 && (anInt1500 == rsChildren.id || anInt1044 == rsChildren.id || lastHoverToggleChildId == rsChildren.id) && anInt1501 == 100) {
						int boxWidth = 0;
						int boxHeight = 0;
						TextDrawingArea textDrawingArea_2 = normalFont;
						for (String s1 = rsChildren.message; s1.length() > 0;) {
							int l7 = s1.indexOf("\\n");
							String s4;
							if (l7 != -1) {
								s4 = s1.substring(0, l7);
								s1 = s1.substring(l7 + 2);
							} else {
								s4 = s1;
								s1 = "";
							}
							int j10 = textDrawingArea_2.getTextWidth(s4);
							if (j10 > boxWidth) {
								boxWidth = j10;
							}
							boxHeight += textDrawingArea_2.anInt1497 + 1;
						}
						boxWidth += 6;
						boxHeight += 7;
						int xPos = (ySpritePos + rsChildren.width) - 5 - boxWidth;
						int yPos = xSpritePos + rsChildren.height + 5;
						if (xPos < ySpritePos + 5) {
							xPos = ySpritePos + 5;
						}
						if (xPos + boxWidth > yScrollPoint + rsInterface.width) {
							xPos = (yScrollPoint + rsInterface.width) - boxWidth;
						}
						if (yPos + boxHeight > xPadding + rsInterface.height) {
							yPos = (xPadding + rsInterface.height) - boxHeight;
						}
						DrawingArea.drawPixels(boxHeight, yPos, xPos, 0xFFFFA0, boxWidth);
						DrawingArea.fillPixels(xPos, boxWidth, boxHeight, 0, yPos);
						String s2 = rsChildren.message;
						for (int j11 = yPos + textDrawingArea_2.anInt1497 + 2; s2.length() > 0; j11 += textDrawingArea_2.anInt1497 + 1) {//anInt1497
							int newLineIndex = s2.indexOf("\\n");
							String s5;
							if (newLineIndex != -1) {
								s5 = s2.substring(0, newLineIndex);
								s2 = s2.substring(newLineIndex + 2);
							} else {
								s5 = s2;
								s2 = "";
							}
							textDrawingArea_2.method389(false, xPos + 3, 0, s5, j11);
						}
					} else if (rsChildren.type == 9) {
						drawHoverBox(xSpritePos, ySpritePos, rsChildren.message);
					}
			}
			DrawingArea.setDrawingArea(yBottomPos, xTopPos, xBottomPos, yTopPos);
		} catch(NullPointerException ex) {
			
		}
	}
	
	public void drawHoverBox(int xPos, int yPos, String text) {
		String[] results = text.split("\n");
		int height = (results.length * 16) + 6;
		int width;
		width = smallFont.getTextWidth(results[0]) + 6;
		for(int i = 1; i < results.length; i++)
			if(width <= smallFont.getTextWidth(results[i]) + 6)
				width = smallFont.getTextWidth(results[i]) + 6;
		DrawingArea.drawPixels(height, yPos, xPos, 0xFFFFA0, width);
		DrawingArea.fillPixels(xPos, width, height, 0, yPos);
		yPos += 14;
		for(int i = 0; i < results.length; i++) {
			smallFont.method389(false, xPos + 3, 0, results[i], yPos);
			yPos += 16;
		}
	}

	private void randomizeBackground(Background background) {
		int j = 256;
		for(int k = 0; k < anIntArray1190.length; k++)
			anIntArray1190[k] = 0;

		for(int l = 0; l < 5000; l++) {
			int i1 = (int)(Math.random() * 128D * (double)j);
			anIntArray1190[i1] = (int)(Math.random() * 256D);
		}
		for(int j1 = 0; j1 < 20; j1++) {
			for(int k1 = 1; k1 < j - 1; k1++) {
				for(int i2 = 1; i2 < 127; i2++) {
					int k2 = i2 + (k1 << 7);
					anIntArray1191[k2] = (anIntArray1190[k2 - 1] + anIntArray1190[k2 + 1] + anIntArray1190[k2 - 128] + anIntArray1190[k2 + 128]) / 4;
				}

			}
			int ai[] = anIntArray1190;
			anIntArray1190 = anIntArray1191;
			anIntArray1191 = ai;
		}
		if(background != null) {
			int l1 = 0;
			for(int j2 = 0; j2 < background.anInt1453; j2++) {
				for(int l2 = 0; l2 < background.anInt1452; l2++)
					if(background.aByteArray1450[l1++] != 0) {
						int i3 = l2 + 16 + background.anInt1454;
						int j3 = j2 + 16 + background.anInt1455;
						int k3 = i3 + (j3 << 7);
						anIntArray1190[k3] = 0;
					}
			}
		}
	}

	private void method107(int i, int j, Stream stream, Player player)
	{
		if((i & 0x400) != 0)
		{
			player.anInt1543 = stream.method428();
			player.anInt1545 = stream.method428();
			player.anInt1544 = stream.method428();
			player.anInt1546 = stream.method428();
			player.anInt1547 = stream.method436() + loopCycle;
			player.anInt1548 = stream.method435() + loopCycle;
			player.anInt1549 = stream.method428();
			player.method446();
		}
		if((i & 0x100) != 0)
		{
			player.anInt1520 = stream.method434();
			int k = stream.readDWord();
			player.anInt1524 = k >> 16;
			player.anInt1523 = loopCycle + (k & 0xffff);
			player.anInt1521 = 0;
			player.anInt1522 = 0;
			if(player.anInt1523 > loopCycle)
				player.anInt1521 = -1;
			if(player.anInt1520 == 65535)
				player.anInt1520 = -1;
		}
		if((i & 8) != 0)
		{
			int l = stream.method434();
			if(l == 65535)
				l = -1;
			int i2 = stream.method427();
			if(l == player.anim && l != -1)
			{
				int i3 = Animation.anims[l].anInt365;
				if(i3 == 1)
				{
					player.anInt1527 = 0;
					player.anInt1528 = 0;
					player.anInt1529 = i2;
					player.anInt1530 = 0;
				}
				if(i3 == 2)
					player.anInt1530 = 0;
			} else
			if(l == -1 || player.anim == -1 || Animation.anims[l].anInt359 >= Animation.anims[player.anim].anInt359)
			{
				player.anim = l;
				player.anInt1527 = 0;
				player.anInt1528 = 0;
				player.anInt1529 = i2;
				player.anInt1530 = 0;
				player.anInt1542 = player.smallXYIndex;
			}
		}
		if((i & 4) != 0)
		{
			player.textSpoken = stream.readString();
			if(player.textSpoken.charAt(0) == '~')
			{
				player.textSpoken = player.textSpoken.substring(1);
				pushMessage(player.textSpoken, 2, player.name);
			} else
			if(player == myPlayer)
				pushMessage(player.textSpoken, 2, player.name);
			player.anInt1513 = 0;
			player.anInt1531 = 0;
			player.textCycle = 150;
		}
		if((i & 0x80) != 0)
		{
			//right fucking here
			int i1 = stream.method434();
			int myRights = stream.readUnsignedByte();
			int j3 = stream.method427();
			int k3 = stream.currentOffset;
			if(player.name != null && player.visible)
			{
				long l3 = TextClass.longForName(player.name);
				boolean flag = false;
				if(myRights <= 1)
				{
					for(int i4 = 0; i4 < ignoreCount; i4++)
					{
						if(ignoreListAsLongs[i4] != l3)
							continue;
						flag = true;
						break;
					}

				}
				if(!flag && anInt1251 == 0)
					try
					{
						aStream_834.currentOffset = 0;
						stream.method442(j3, 0, aStream_834.buffer);
						aStream_834.currentOffset = 0;
						String s = TextInput.method525(j3, aStream_834);
						//s = Censor.doCensor(s);
						player.textSpoken = s;
						player.anInt1513 = i1 >> 8;
						player.privelage = myRights;
						player.anInt1531 = i1 & 0xff;
						player.textCycle = 150;
						if(myRights > 0)
							pushMessage(s, 1, "@cr"+ myRights +"@" + player.name);
						else
							pushMessage(s, 2, player.name);
					} catch(Exception exception) {
						signlink.reporterror("cde2");
					}
			}
			stream.currentOffset = k3 + j3;
		}
		if((i & 1) != 0)
		{
			player.interactingEntity = stream.method434();
			if(player.interactingEntity == 65535)
				player.interactingEntity = -1;
		}
		if((i & 0x10) != 0)
		{
			int j1 = stream.method427();
			byte abyte0[] = new byte[j1];
			Stream stream_1 = new Stream(abyte0);
			stream.readBytes(j1, 0, abyte0);
			aStreamArray895s[j] = stream_1;
			player.updatePlayer(stream_1);
		}
		if((i & 2) != 0)
		{
			player.anInt1538 = stream.method436();
			player.anInt1539 = stream.method434();
		}
		
		if((i & 0x20) != 0)
		{
			int damage = stream.readUnsignedByte();
			int type = stream.method426();
			int icon = stream.readUnsignedByte();
			int focusPlayer = stream.readUnsignedByte();
			player.updateHitData(type, damage, loopCycle, icon, focusPlayer, 0);
			player.loopCycleStatus = loopCycle + 300;
			player.currentHealth = stream.method427();
			player.maxHealth = stream.readUnsignedByte();
		}
		if((i & 0x200) != 0)
		{
			int damage = stream.readUnsignedByte();
			int type = stream.method428();
			int icon = stream.readUnsignedByte();
			int focusPlayer = stream.readUnsignedByte();
			player.updateHitData(type, damage, loopCycle, icon, focusPlayer, 0);
			player.loopCycleStatus = loopCycle + 300;
			player.currentHealth = stream.readUnsignedByte();
			player.maxHealth = stream.method427();
		}
	}

	private void method108()
	{
		try
		{
			int j = myPlayer.x + anInt1278;
			int k = myPlayer.y + anInt1131;
			if(anInt1014 - j < -500 || anInt1014 - j > 500 || anInt1015 - k < -500 || anInt1015 - k > 500)
			{
				anInt1014 = j;
				anInt1015 = k;
			}
			if(anInt1014 != j)
				anInt1014 += (j - anInt1014) / 16;
			if(anInt1015 != k)
				anInt1015 += (k - anInt1015) / 16;
			if(super.keyArray[1] == 1)
				anInt1186 += (-24 - anInt1186) / 2;
			else
			if(super.keyArray[2] == 1)
				anInt1186 += (24 - anInt1186) / 2;
			else
				anInt1186 /= 2;
			if(super.keyArray[3] == 1)
				anInt1187 += (12 - anInt1187) / 2;
			else
			if(super.keyArray[4] == 1)
				anInt1187 += (-12 - anInt1187) / 2;
			else
				anInt1187 /= 2;
			  compassRotation = compassRotation + anInt1186 / 2 & 0x7ff;
			  anInt1184 += anInt1187 / 2;
			if(anInt1184 < 128)
				anInt1184 = 128;
			if(anInt1184 > 383)
				anInt1184 = 383;
			int l = anInt1014 >> 7;
			int i1 = anInt1015 >> 7;
			int j1 = method42(plane, anInt1015, anInt1014);
			int k1 = 0;
			if(l > 3 && i1 > 3 && l < 100 && i1 < 100)
			{
				for(int l1 = l - 4; l1 <= l + 4; l1++)
				{
					for(int k2 = i1 - 4; k2 <= i1 + 4; k2++)
					{
						int l2 = plane;
						if(l2 < 3 && (byteGroundArray[1][l1][k2] & 2) == 2)
							l2++;
						int i3 = j1 - intGroundArray[l2][l1][k2];
						if(i3 > k1)
							k1 = i3;
					}

				}

			}
			anInt1005++;
			if(anInt1005 > 1512)
			{
				anInt1005 = 0;
				stream.createFrame(77);
				stream.writeWordBigEndian(0);
				int i2 = stream.currentOffset;
				stream.writeWordBigEndian((int)(Math.random() * 256D));
				stream.writeWordBigEndian(101);
				stream.writeWordBigEndian(233);
				stream.writeWord(45092);
				if((int)(Math.random() * 2D) == 0)
					stream.writeWord(35784);
				stream.writeWordBigEndian((int)(Math.random() * 256D));
				stream.writeWordBigEndian(64);
				stream.writeWordBigEndian(38);
				stream.writeWord((int)(Math.random() * 65536D));
				stream.writeWord((int)(Math.random() * 65536D));
				stream.writeBytes(stream.currentOffset - i2);
			}
			int j2 = k1 * 192;
			if(j2 > 0x17f00)
				j2 = 0x17f00;
			if(j2 < 32768)
				j2 = 32768;
			if(j2 > anInt984)
			{
				anInt984 += (j2 - anInt984) / 24;
				return;
			}
			if(j2 < anInt984)
			{
				anInt984 += (j2 - anInt984) / 80;
			}
		}
		catch(Exception _ex)
		{
			signlink.reporterror("glfc_ex " + myPlayer.x + "," + myPlayer.y + "," + anInt1014 + "," + anInt1015 + "," + anInt1069 + "," + anInt1070 + "," + baseX + "," + baseY);
			throw new RuntimeException("eek");
		}
	}

	public void processDrawing()
	{
		if(rsAlreadyLoaded || loadingError || genericLoadingError)
		{
			showErrorScreen();
			return;
		}
		anInt1061++;
		if(!loggedIn)
			drawLoginScreen(false);
		else
			drawGameScreen();
		anInt1213 = 0;
	}

	private boolean isFriendOrSelf(String s)
	{
		if(s == null)
			return false;
		for(int i = 0; i < friendsCount; i++)
			if(s.equalsIgnoreCase(friendsList[i]))
				return true;
		return s.equalsIgnoreCase(myPlayer.name);
	}

	private static String combatDiffColor(int i, int j)
	{
		int k = i - j;
		if(k < -9)
			return "@red@";
		if(k < -6)
			return "@or3@";
		if(k < -3)
			return "@or2@";
		if(k < 0)
			return "@or1@";
		if(k > 9)
			return "@gre@";
		if(k > 6)
			return "@gr3@";
		if(k > 3)
			return "@gr2@";
		if(k > 0)
			return "@gr1@";
		else
			return "@yel@";
	}

	private void setWaveVolume(int i)
	{
		signlink.wavevol = i;
	}

	private void draw3dScreen()
	{
	alertHandler.processAlerts();
		drawSplitPrivateChat(0,getChatBackIncreaseY());
		if(crossType == 1)
		{
			crosses[crossIndex / 100].drawSprite(crossX - 8 - 4, crossY - 8 - 4);
			anInt1142++;
			if(anInt1142 > 67)
			{
				anInt1142 = 0;
				stream.createFrame(78);
			}
		}
		if(crossType == 2)
			crosses[4 + crossIndex / 100].drawSprite(crossX - 8 - 4, crossY - 8 - 4);
		if(walkableInterfaceID != -1) {
			method119(anInt945, walkableInterfaceID);
			if(walkableInterfaceID == 197 && toggleFullscreen)
				drawInterface(0, 0 + getMapIncreaseX() , RSInterface.interfaceCache[walkableInterfaceID], 0 - 255 + 50 + getMapIncreaseY());
			else
				drawInterface(0, 0, RSInterface.interfaceCache[walkableInterfaceID], 0);
		}
		if(openInterfaceID != -1) {
			method119(anInt945, openInterfaceID);
			RSInterface rsInterface = RSInterface.interfaceCache[openInterfaceID];
			int width = toggleFullscreen ? getScreenWidth() : 516;
			int height = toggleFullscreen ? getScreenHeight() : 338;
			if(toggleFullscreen)
				drawInterface(0, 0 + (width - rsInterface.width) / 2, rsInterface, 0 + (height - rsInterface.height) / 2);
			else
				drawInterface(0, 0, rsInterface, 0);//first 1
		}
		method70();
		if(displayOverLayIcon == 1)
			multiOverlay.drawSprite(toggleFullscreen ? xPaddingmapBackImage + getMapIncreaseX() - 50 : 472, toggleFullscreen ? 1 : 296 + getMapIncreaseY());
		if(openBOX)
		{
			openBOX = false;
			donatorPayment = inputbox();
		}
		if(fpsOn)
		{
			char c = '\u01FB';
			int k = 20;
			int i1 = 0xffff00;
			if(super.fps < 15)
				i1 = 0xff0000;
			normalFont.method380("Fps:" + super.fps, c, i1, k);
			k += 15;
			Runtime runtime = Runtime.getRuntime();
			int j1 = (int)((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
			i1 = 0xffff00;
			if(j1 > 0x2000000 && lowMem)
				i1 = 0xff0000;
			normalFont.method380("Mem:" + j1 + "k", c, 0xffff00, k);
			k += 15;
		}
		int x = baseX + (myPlayer.x - 6 >> 7);
		int y = baseY + (myPlayer.y - 6 >> 7);
		if (clientData)
		{
			if(super.fps < 15) {
			}
			normalFont.method385(0xffff00, "Fps: " + super.fps, 285, 5);
			Runtime runtime = Runtime.getRuntime();
			int j1 = (int)((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
			if(j1 > 0x2000000 && lowMem) {
			}
			normalFont.method385(0xffff00, "Mem: " + j1 + "k", 299, 5);
			normalFont.method385(0xffff00, "Mouse X: " + super.mouseX + " , Mouse Y: " + super.mouseY, 314, 5);
			normalFont.method385(0xffff00, "Coords: " + x + ", " + y, 329, 5);
		}
		if(systemUpdatetime != 0)
		{
			int j = systemUpdatetime / 50;
			int l = j / 60;
			j %= 60;
			if(j < 10)
				normalFont.method385(0xffff00, "System update in: " + l + ":0" + j, 329, 4);
			else
				normalFont.method385(0xffff00, "System update in: " + l + ":" + j, 329, 4);
			anInt849++;
			if(anInt849 > 75)
			{
				anInt849 = 0;
				stream.createFrame(148);
			}
		}
		if(openInterfaceID == 22595) {//	
			normalFont.method385(0xffffff, newPasswordString + ((loopCycle % 20 < 10) ? "|" : " "), (334-149)/2 + 70, (512-370- normalFont.getTextWidth(newPasswordString + " ")	)/2 + 221);
		}		
		if(openInterfaceID == 21595) {//	
			normalFont.method385(0xffffff, newUserString + ((loopCycle % 20 < 10) ? "|" : " "), (334-149)/2 + 70, (512-370- normalFont.getTextWidth(newUserString + " ")	)/2 + 221);
		}	
		if(counterOn)
			drawCounterOnScreen(getMapIncreaseX(),getMapIncreaseY());
		if(!contextMenuOpen) {
			processRightClick();
			drawTooltip();
		} else
			drawMenu(4,4);
	}

	private void addIgnore(long l)
	{
		try
		{
			if(l == 0L)
				return;
			if(ignoreCount >= 100)
			{
				pushMessage("Your ignore list is full. Max of 100 hit", 0, "");
				return;
			}
			String s = TextClass.fixName(TextClass.nameForLong(l));
			for(int j = 0; j < ignoreCount; j++)
				if(ignoreListAsLongs[j] == l)
				{
					pushMessage(s + " is already on your ignore list", 0, "");
					return;
				}
			for(int k = 0; k < friendsCount; k++)
				if(friendsListAsLongs[k] == l)
				{
					pushMessage("Please remove " + s + " from your friend list first", 0, "");
					return;
				}

			ignoreListAsLongs[ignoreCount++] = l;
			stream.createFrame(133);
			stream.writeQWord(l);
			return;
		}
		catch(RuntimeException runtimeexception)
		{
			signlink.reporterror("45688, " + l + ", " + 4 + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}

	private void method114()
	{
		for(int i = -1; i < playerCount; i++)
		{
			int j;
			if(i == -1)
				j = myPlayerIndex;
			else
				j = playerIndices[i];
			Player player = playerArray[j];
			if(player != null)
				method96(player);
		}

	}

	private void method115()
	{
		if(loadingStage == 2)
		{
			for(Class30_Sub1 class30_sub1 = (Class30_Sub1)aClass19_1179.reverseGetFirst(); class30_sub1 != null; class30_sub1 = (Class30_Sub1)aClass19_1179.reverseGetNext())
			{
				if(class30_sub1.anInt1294 > 0)
					class30_sub1.anInt1294--;
				if(class30_sub1.anInt1294 == 0)
				{
					if(class30_sub1.anInt1299 < 0 || ObjectManager.method178(class30_sub1.anInt1299, class30_sub1.anInt1301))
					{
						method142(class30_sub1.anInt1298, class30_sub1.anInt1295, class30_sub1.anInt1300, class30_sub1.anInt1301, class30_sub1.anInt1297, class30_sub1.anInt1296, class30_sub1.anInt1299);
						class30_sub1.unlink();
					}
				} else
				{
					if(class30_sub1.anInt1302 > 0)
						class30_sub1.anInt1302--;
					if(class30_sub1.anInt1302 == 0 && class30_sub1.anInt1297 >= 1 && class30_sub1.anInt1298 >= 1 && class30_sub1.anInt1297 <= 102 && class30_sub1.anInt1298 <= 102 && (class30_sub1.anInt1291 < 0 || ObjectManager.method178(class30_sub1.anInt1291, class30_sub1.anInt1293)))
					{
						method142(class30_sub1.anInt1298, class30_sub1.anInt1295, class30_sub1.anInt1292, class30_sub1.anInt1293, class30_sub1.anInt1297, class30_sub1.anInt1296, class30_sub1.anInt1291);
						class30_sub1.anInt1302 = -1;
						if(class30_sub1.anInt1291 == class30_sub1.anInt1299 && class30_sub1.anInt1299 == -1)
							class30_sub1.unlink();
						else
						if(class30_sub1.anInt1291 == class30_sub1.anInt1299 && class30_sub1.anInt1292 == class30_sub1.anInt1300 && class30_sub1.anInt1293 == class30_sub1.anInt1301)
							class30_sub1.unlink();
					}
				}
			}

		}
	}
	
	
	//stops the click from going over sprite - idiots wrong (sigex)
	private void determineMenuSize() {
		int i = boldFont.getTextWidth("Choose Option");
		for(int j = 0; j < menuActionRow; j++)
		{
			int k = boldFont.getTextWidth(menuActionName[j]);
			if(k > i)
				i = k;
		}
		i += 8;
		int l = 15 * menuActionRow + 21;
		int paddingRight = 8;
		int paddingBottom = 8;
		int width = toggleFullscreen ? getScreenWidth() : 765;
		int height = toggleFullscreen ? getScreenHeight() : 503;
		if(super.saveClickX > 0 && super.saveClickY > 0 && super.saveClickX < width && super.saveClickY < height) {
			int x = super.saveClickX - 0 - i / 2;
			if(x < 0){
				x = 0;
			} else if(x + i > width - paddingRight)
				x = width - paddingRight - i;
			int y = super.saveClickY - 0;
			if(y + l > width) {
				y = width - l;
			}
			if(y < 0) {
				y = 0;
			} else if(y + l > height - paddingBottom)
				y = height - paddingBottom - l;
			if(l > height)
				y = 0;
			contextMenuOpen = true;
			menuOffsetX = x;
			menuOffsetY = y;
			menuWidth = i;
			menuHeight = 15 * menuActionRow + 22;
		}
	}

	private void method117(Stream stream)
	{
		stream.initBitAccess();
		int j = stream.readBits(1);
		if(j == 0)
			return;
		int k = stream.readBits(2);
		if(k == 0)
		{
			anIntArray894[anInt893++] = myPlayerIndex;
			return;
		}
		if(k == 1)
		{
			int l = stream.readBits(3);
			myPlayer.moveInDir(false, l);
			int k1 = stream.readBits(1);
			if(k1 == 1)
				anIntArray894[anInt893++] = myPlayerIndex;
			return;
		}
		if(k == 2)
		{
			int i1 = stream.readBits(3);
			myPlayer.moveInDir(true, i1);
			int l1 = stream.readBits(3);
			myPlayer.moveInDir(true, l1);
			int j2 = stream.readBits(1);
			if(j2 == 1)
				anIntArray894[anInt893++] = myPlayerIndex;
			return;
		}
		if(k == 3)
		{
			plane = stream.readBits(2);
			int j1 = stream.readBits(1);
			int i2 = stream.readBits(1);
			if(i2 == 1)
				anIntArray894[anInt893++] = myPlayerIndex;
			int k2 = stream.readBits(7);
			int l2 = stream.readBits(7);
			myPlayer.setPos(l2, k2, j1 == 1);
		}
	}
	
	private int[] anIntArray828;

	private void nullLoader()
	{
		aBoolean831 = false;
		while(drawingFlames)
		{
			aBoolean831 = false;
			try
			{
				Thread.sleep(50L);
			}
			catch(Exception _ex) { }
		}
		aBackgroundArray1152s = null;
		anIntArray851 = null;
		anIntArray850 = null;
		anIntArray852 = null;
		anIntArray828 = null;
		anIntArray853 = null;
		anIntArray1190 = null;
		anIntArray1191 = null;
		aClass30_Sub2_Sub1_Sub1_1201 = null;
		aClass30_Sub2_Sub1_Sub1_1202 = null;
	}

	private boolean method119(int i, int j)
	{
		boolean flag1 = false;
		RSInterface class9 = RSInterface.interfaceCache[j];
		if(class9 != null)
			try {
				for(int k = 0; k < class9.children.length; k++)
				{
					if(class9.children[k] == -1)
						break;
					RSInterface class9_1 = RSInterface.interfaceCache[class9.children[k]];
					if(class9_1.type == 1)
						flag1 |= method119(i, class9_1.id);
					if(class9_1.type == 6 && (class9_1.anInt257 != -1 || class9_1.anInt258 != -1))
					{
						boolean flag2 = interfaceIsSelected(class9_1);
						int l;
						if(flag2)
							l = class9_1.anInt258;
						else
							l = class9_1.anInt257;
						if(l != -1)
						{
							Animation animation = Animation.anims[l];
							for(class9_1.anInt208 += i; class9_1.anInt208 > animation.method258(class9_1.anInt246);)
							{
								class9_1.anInt208 -= animation.method258(class9_1.anInt246) + 1;
								class9_1.anInt246++;
								if(class9_1.anInt246 >= animation.anInt352)
								{
									class9_1.anInt246 -= animation.anInt356;
									if(class9_1.anInt246 < 0 || class9_1.anInt246 >= animation.anInt352)
										class9_1.anInt246 = 0;
								}
								flag1 = true;
							}

						}
					}
				}
				return flag1;
			} catch(NullPointerException ex) {
				return flag1;
			}
		else
			return flag1;
	}

	private int method120()
	{
		int j = 3;
		if(yCameraCurve < 310)
		{
			int k = xCameraPos >> 7;
			int l = yCameraPos >> 7;
			int i1 = myPlayer.x >> 7;
			int j1 = myPlayer.y >> 7;
			if((byteGroundArray[plane][k][l] & 4) != 0)
				j = plane;
			int k1;
			if(i1 > k)
				k1 = i1 - k;
			else
				k1 = k - i1;
			int l1;
			if(j1 > l)
				l1 = j1 - l;
			else
				l1 = l - j1;
			if(k1 > l1)
			{
				int i2 = (l1 * 0x10000) / k1;
				int k2 = 32768;
				while(k != i1) 
				{
					if(k < i1)
						k++;
					else
					if(k > i1)
						k--;
					if((byteGroundArray[plane][k][l] & 4) != 0)
						j = plane;
					k2 += i2;
					if(k2 >= 0x10000)
					{
						k2 -= 0x10000;
						if(l < j1)
							l++;
						else
						if(l > j1)
							l--;
						if((byteGroundArray[plane][k][l] & 4) != 0)
							j = plane;
					}
				}
			} else
			{
				int j2 = (k1 * 0x10000) / l1;
				int l2 = 32768;
				while(l != j1) 
				{
					if(l < j1)
						l++;
					else
					if(l > j1)
						l--;
					if((byteGroundArray[plane][k][l] & 4) != 0)
						j = plane;
					l2 += j2;
					if(l2 >= 0x10000)
					{
						l2 -= 0x10000;
						if(k < i1)
							k++;
						else
						if(k > i1)
							k--;
						if((byteGroundArray[plane][k][l] & 4) != 0)
							j = plane;
					}
				}
			}
		}
		if((byteGroundArray[plane][myPlayer.x >> 7][myPlayer.y >> 7] & 4) != 0)
			j = plane;
		return j;
	}

	private int method121()
	{
		int j = method42(plane, yCameraPos, xCameraPos);
		if(j - zCameraPos < 800 && (byteGroundArray[plane][xCameraPos >> 7][yCameraPos >> 7] & 4) != 0)
			return plane;
		else
			return 3;
	}

	private void delIgnore(long l)
	{
		try
		{
			if(l == 0L)
				return;
			for(int j = 0; j < ignoreCount; j++)
				if(ignoreListAsLongs[j] == l)
				{
					ignoreCount--;
					System.arraycopy(ignoreListAsLongs, j + 1, ignoreListAsLongs, j, ignoreCount - j);

					stream.createFrame(74);
					stream.writeQWord(l);
					return;
				}

			return;
		}
		catch(RuntimeException runtimeexception)
		{
			signlink.reporterror("47229, " + 3 + ", " + l + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}
	
	
	private void chatJoin(long l) {
		try {
			if(l == 0L)
				return;
			stream.createFrame(60);
			stream.writeQWord(l);
			return;
		}
		catch(RuntimeException runtimeexception)
		{
			signlink.reporterror("47229, " + 3 + ", " + l + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	
	}
	
	public String getParameter(String s)
	{
		if(signlink.mainapp != null)
			return signlink.mainapp.getParameter(s);
		else
			return super.getParameter(s);
	}

	private void adjustVolume(boolean flag, int i)
	{
		signlink.midivol = i;
		if(flag)
			signlink.midi = "voladjust";
	}

	private int extractInterfaceValues(RSInterface class9, int j)
	{
		if(class9.valueIndexArray == null || j >= class9.valueIndexArray.length)
			return -2;
		try
		{
			int ai[] = class9.valueIndexArray[j];
			int k = 0;
			int l = 0;
			int i1 = 0;
			do
			{
				int j1 = ai[l++];
				int k1 = 0;
				byte byte0 = 0;
				if(j1 == 0)
					return k;
				if(j1 == 1)
					k1 = currentStats[ai[l++]];
				if(j1 == 2)
					k1 = maxStats[ai[l++]];
				if(j1 == 3)
					k1 = currentExp[ai[l++]];
				if(j1 == 4)
				{
					RSInterface class9_1 = RSInterface.interfaceCache[ai[l++]];
					int k2 = ai[l++];
					if(k2 >= 0 && k2 < ItemDef.totalItems && (!ItemDef.forID(k2).membersObject || isMembers))
					{
						for(int j3 = 0; j3 < class9_1.inv.length; j3++)
							if(class9_1.inv[j3] == k2 + 1)
								k1 += class9_1.invStackSizes[j3];

					}
				}
				if(j1 == 5)
					k1 = variousSettings[ai[l++]];
				if(j1 == 6)
					k1 = anIntArray1019[maxStats[ai[l++]] - 1];
				if(j1 == 7)
					k1 = (variousSettings[ai[l++]] * 100) / 46875;
				if(j1 == 8)
					k1 = myPlayer.combatLevel;
				if(j1 == 9)
				{
					for(int l1 = 0; l1 < Skills.skillsCount; l1++)
						if(Skills.skillEnabled[l1])
							k1 += maxStats[l1];

				}
				if(j1 == 10)
				{
					RSInterface class9_2 = RSInterface.interfaceCache[ai[l++]];
					int l2 = ai[l++] + 1;
					if(l2 >= 0 && l2 < ItemDef.totalItems && (!ItemDef.forID(l2).membersObject || isMembers))
					{
						for(int k3 = 0; k3 < class9_2.inv.length; k3++)
						{
							if(class9_2.inv[k3] != l2)
								continue;
							k1 = 0x3b9ac9ff;
							break;
						}

					}
				}
				if(j1 == 11)
					k1 = energy;
				if(j1 == 12)
					k1 = weight;
				if(j1 == 13)
				{
					int i2 = variousSettings[ai[l++]];
					int i3 = ai[l++];
					k1 = (i2 & 1 << i3) == 0 ? 0 : 1;
				}
				if(j1 == 14)
				{
					int j2 = ai[l++];
					VarBit varBit = VarBit.cache[j2];
					int l3 = varBit.anInt648;
					int i4 = varBit.anInt649;
					int j4 = varBit.anInt650;
					int k4 = anIntArray1232[j4 - i4];
					k1 = variousSettings[l3] >> i4 & k4;
				}
				if(j1 == 15)
					byte0 = 1;
				if(j1 == 16)
					byte0 = 2;
				if(j1 == 17)
					byte0 = 3;
				if(j1 == 18)
					k1 = (myPlayer.x >> 7) + baseX;
				if(j1 == 19)
					k1 = (myPlayer.y >> 7) + baseY;
				if(j1 == 20)
					k1 = ai[l++];
				if(byte0 == 0)
				{
					if(i1 == 0)
						k += k1;
					if(i1 == 1)
						k -= k1;
					if(i1 == 2 && k1 != 0)
						k /= k1;
					if(i1 == 3)
						k *= k1;
					i1 = 0;
				} else
				{
					i1 = byte0;
				}
			} while(true);
		}
		catch(Exception _ex)
		{
			return -1;
		}
	}

	/*private void drawTooltip() {
		if(menuActionRow < 2 && itemSelected == 0 && spellSelected == 0)
			return;
		String s;
		if(itemSelected == 1 && menuActionRow < 2)
			s = "Use " + selectedItemName + " with...";
		else if(spellSelected == 1 && menuActionRow < 2)
			s = spellTooltip + "...";
		else
			s = menuActionName[menuActionRow - 1];
		if(menuActionRow > 2)
			s = s + "@whi@ / " + (menuActionRow - 2) + " more options";
		boldFont.method390(4, 0xffffff, s, loopCycle / 1000, 15);
	}*/
	
	private void drawTooltip()
	{
		String s = "";
		if(itemSelected == 1 && menuActionRow < 2)
			s = "Use " + selectedItemName + " with...";
		else
		if(spellSelected == 1 && menuActionRow < 2)
			s = spellTooltip + "...";
		else
			s = menuActionName[menuActionRow - 1];
		if(menuActionRow > 2)
			s = s + "@whi@ / " + (menuActionRow - 2) + " more options";
		boldFont.method390(4, 0xffffff, s, loopCycle / 1000, 15);
		boolean hasFoundCursor = false;
		for (int i1 = 0; i1 < cursorInfo.length; i1++) {
			if (menuActionName[menuActionRow - 1].startsWith(cursorInfo[i1])) {
				super.setCursor(i1);
				hasFoundCursor = true;
			}
		}
		if (!hasFoundCursor)
		super.setCursor(0);
	}
	

	public Sprite[] backingOrb = new Sprite[4];
	public Sprite[] genericOrbs = new Sprite[7];
	public Sprite[] healthOrbs = new Sprite[3];
	public Sprite[] prayerOrbs = new Sprite[3];
	public Sprite[] energyOrbs = new Sprite[7];
	public Sprite[] summoningOrbs = new Sprite[4];
	public boolean[] orbToggle = new boolean[4];
	private int orbHover = -1;
	private double[] lastOrbAmount = new double[4];
	public void loadOrbs() {
		
		for(int i = 0; i < genericOrbs.length; i++)
			genericOrbs[i] = new Sprite("Minimap Orbs/orb " + i);
			
		for(int i = 0; i < healthOrbs.length; i++)
			healthOrbs[i] = new Sprite("Minimap Orbs/health/orb " + i);
			
		for(int i = 0; i < prayerOrbs.length; i++)
			prayerOrbs[i] = new Sprite("Minimap Orbs/prayer/orb " + i);
			
		for(int i = 0; i < energyOrbs.length; i++)
			energyOrbs[i] = new Sprite("Minimap Orbs/energy/orb " + i);	
			
		for(int i = 0; i < summoningOrbs.length; i++)
			summoningOrbs[i] = new Sprite("Minimap Orbs/summoning/orb " + i);		
	}
	
	private void drawOrbs(int paddingX, int paddingY) {
		createOrbs((toggleFullscreen ? -44 : 145) + mapMovedX + paddingX,(toggleFullscreen ? 38 : 10) + mapMovedY + paddingY,0, healthOrbs);
		createOrbs((toggleFullscreen ? -47 : 161) + mapMovedX + paddingX,(toggleFullscreen ? 72 : 49) + mapMovedY + paddingY,1, prayerOrbs);
		createOrbs((toggleFullscreen ? -35 : 161) + mapMovedX + paddingX,(toggleFullscreen ? 106 : 88) + mapMovedY + paddingY,2, energyOrbs);
		// SUMMONING ORB CAUSES CRASH
		createOrbs((toggleFullscreen ? -12 : 145) + mapMovedX + paddingX,(toggleFullscreen ? 135 : 127) + mapMovedY + paddingY,3, summoningOrbs);
	}
	
	private void createOrbs(int xdraw, int ydraw, int orbID, Sprite[] orbSprites) { // this creates the orb
		int orbIconID = ((orbToggle[orbID] && orbID == 2) || (orbID == 3 && summoningAttack)) ? 3 : 0;
		int current = getOrbValues(orbID,orbSprites[orbIconID])[0];
		int total = getOrbValues(orbID,orbSprites[orbIconID])[1];
		double percentage = (current * 100 / total);
		double pixelPercent = 27 - (percentage * 27 / 100);
		int backingSprite = (orbID == 0) ? ((orbToggle[0]) ? 4 : 3) : 2;
		int textColour = getOrbTextColor(percentage);
		
		if(orbToggle[orbID] && getOrbValues(orbID,null)[0] <= 0)
			orbToggle[orbID] = false;
		
		genericOrbs[(orbHover == orbID ? 1 : 0) + (toggleFullscreen ? 5: 0)].drawSprite(xdraw, ydraw);
		smallFont.method382(textColour, xdraw + (toggleFullscreen ? 13 : 41), "" + current, ydraw + 26, true);
		
		if((percentage != lastOrbAmount[orbID])) {
			backingOrb[orbID] = new Sprite("Minimap Orbs/orb " + backingSprite, genericOrbs[backingSprite].myWidth,(int)pixelPercent);
			lastOrbAmount[orbID] = percentage;
		}
		orbSprites[orbToggle[orbID] ? 2 : 1].drawSprite((toggleFullscreen ? 24 : 0) +xdraw + 3, ydraw + 3);
		if(backingOrb[orbID] != null)
			backingOrb[orbID].drawSprite((toggleFullscreen ? 24 : 0) +xdraw + 3, ydraw + 3);
		orbSprites[orbIconID].drawSprite1((toggleFullscreen ? 24 : 0) +xdraw, ydraw,(textColour == 0xff0000 && current != 0) ? ((loopCycle % 40 < 20) ? (64 + (loopCycle % 128)) : 0) : (256));
	}
	
	private int[] getOrbValues(int type, Sprite orbSprite) {
		int[] values = new int[2];
		int offsetX = 0, offsetY = 0;
		switch(type) {
			case 0://Health
				values[0] = Integer.parseInt(RSInterface.interfaceCache[4016].message);
				values[1] = Integer.parseInt(RSInterface.interfaceCache[4017].message.replaceAll("%", ""));
				offsetX = 9;
				offsetY = 10;
				break;

			case 1://Prayer
				values[0] = Integer.parseInt(RSInterface.interfaceCache[4012].message);
				values[1] = Integer.parseInt(RSInterface.interfaceCache[4013].message.replaceAll("%", ""));
				offsetX = 7;
				offsetY = 7;
				break;
				
			case 2://Energy
				values[0] = Integer.parseInt(RSInterface.interfaceCache[149].message.replaceAll("%", ""));
				values[1] = 100;
				offsetX = 10;
				offsetY = 8;
				break;
			case 3://Summoning
				values[0] = Integer.parseInt(RSInterface.interfaceCache[19178].message);; // 29800 
				values[1] = Integer.parseInt(RSInterface.interfaceCache[19179].message);; //29801
				offsetX = 7;
				offsetY = 7;
				break;
			
			default:
				return null;
		}
		try {
			if(offsetX > 0 && offsetY > 0) {
				orbSprite.configXDrawOffset(offsetX);
				orbSprite.configYDrawOffset(offsetY);
			}
		} catch(NullPointerException ex) {
			
		}
		return values;
	}

	public int getOrbTextColor(double percentage) {
		if(percentage >= 75)
			return 65280;
		if(percentage >= 50 && percentage <= 74)
			return 0xffff00;
		return (percentage < 25 || percentage > 49) ? 0xff0000 : 0xff981f;
	}	

	private void drawMinimap(RSImageProducer mapImage, int paddingX, int paddingY) {
		mapImage.initDrawingArea();

		int mapRotation = compassRotation + mapAmtDiffRotation & 2047;
		int mapCenterX = 48 + myPlayer.x / 32;
		int mapCenterY = 464 - myPlayer.y / 32;

		switch(minimapCover) {
			case 2:
				compass.drawRotatableSprite(33, compassRotation, compassPixelCutRight, 256, compassPixelCutLeft, 25, (toggleFullscreen ? 9 : 8) + paddingY, (toggleFullscreen ? 30 : 11) + paddingX, 33, 25);
				main3DArea.initDrawingArea();
				return;
		}
		miniMapImage.drawRotatableSprite(mapInnerSizeHeight, mapRotation, mapImagePixelCutRight, 256 + mapAmtDiffZoom, mapImagePixelCutLeft, mapCenterY, mapPaddingFromTop + paddingY, mapPaddingFromLeft + paddingX, mapInnerSizeWidth, mapCenterX);
		for(int loopIndex = 0; loopIndex < totalMapFunctions; loopIndex++) {
			int x = (mapFunctionX[loopIndex] * 4 + 2) - myPlayer.x / 32;
			int y = (mapFunctionY[loopIndex] * 4 + 2) - myPlayer.y / 32;
			drawMapSprites(currentMapFunctions[loopIndex], x, y);
		}
		for(int loopXIndex = 0; loopXIndex < 104; loopXIndex++) {
			for(int loopYIndex = 0; loopYIndex < 104; loopYIndex++) {
				NodeList nodeList = groundArray[plane][loopXIndex][loopYIndex];
				if(nodeList != null) {
					int x = (loopXIndex * 4 + 2) - myPlayer.x / 32;
					int y = (loopYIndex * 4 + 2) - myPlayer.y / 32;
					drawMapSprites(mapDotItem, x, y);
				}
			}
		}
		for(int loopIndex = 0; loopIndex < npcCount; loopIndex++) {
			NPC npc = npcArray[npcIndices[loopIndex]];
			if(npc != null && npc.isVisible()) {
				EntityDef entityDef = npc.desc;
				if(entityDef.childrenIDs != null)
					entityDef = entityDef.method161();
				if(entityDef != null && entityDef.aBoolean87 && entityDef.aBoolean84) {
					int x = npc.x / 32 - myPlayer.x / 32;
					int y = npc.y / 32 - myPlayer.y / 32;
					drawMapSprites(mapDotNPC, x, y);
				}
			}
		}
		for(int playerIndex = 0; playerIndex < playerCount; playerIndex++) {
			Player player = playerArray[playerIndices[playerIndex]];
			if(player != null && player.isVisible()) {
				int x = player.x / 32 - myPlayer.x / 32;
				int y = player.y / 32 - myPlayer.y / 32;
				boolean isInFriends = false;
				boolean isInClan = false;
				for (int clanIndex = 0; clanIndex < clanList.length; clanIndex++) {
					if (clanList[clanIndex] == null)
						continue;
					if (!clanList[clanIndex].equalsIgnoreCase(player.name))
						continue;
					isInClan = true;
					break;
				}
				long playerNameLong = TextClass.longForName(player.name);
				for(int friendIndex = 0; friendIndex < friendsCount; friendIndex++) {
					if(playerNameLong != friendsListAsLongs[friendIndex] || friendsNodeIDs[friendIndex] == 0)
						continue;
					isInFriends = true;
					break;
				}
				boolean isInTeam = false;
				if(myPlayer.team != 0 && player.team != 0 && myPlayer.team == player.team)
					isInTeam = true;
				if(isInFriends)
					drawMapSprites(mapDotFriend, x, y);
				else if(isInClan)
					drawMapSprites(mapDotClan, x, y);
				else if(isInTeam)
					drawMapSprites(mapDotTeam, x, y);
				else
					drawMapSprites(mapDotPlayer, x, y);
			}
		}
		if(hintType != 0 && loopCycle % 20 < 10) {
			if(hintType == 1 && hintArrowNPCID >= 0 && hintArrowNPCID < npcArray.length) {
				NPC npc = npcArray[hintArrowNPCID];
				if(npc != null) {
					int cordX = npc.x / 32 - myPlayer.x / 32;
					int cordY = npc.y / 32 - myPlayer.y / 32;
					drawMapArrows(mapMarker, cordY, cordX);
				}
			}
			if(hintType == 2) {
				int cordX = ((hintArrowX - baseX) * 4 + 2) - myPlayer.x / 32;
				int cordY = ((hintArrowY - baseY) * 4 + 2) - myPlayer.y / 32;
				drawMapArrows(mapMarker, cordY, cordX);
			}
			if(hintType == 10 && hintArrowPlayerID >= 0 && hintArrowPlayerID < playerArray.length) {
				Player player = playerArray[hintArrowPlayerID];
				if(player != null) {
					int cordX = player.x / 32 - myPlayer.x / 32;
					int cordY = player.y / 32 - myPlayer.y / 32;
					drawMapArrows(mapMarker, cordY, cordX);
				}
			}
		}
		if(destX != 0) {
			int walkingToX = (destX * 4 + 2) - myPlayer.x / 32;
			int walkingToY = (destY * 4 + 2) - myPlayer.y / 32;
			drawMapSprites(mapFlag, walkingToX, walkingToY);
		}
		if(!toggleFullscreen)
			if(!newGameFrame) {
			mapBack[0].drawBackground(paddingX, paddingY);
			} else {
				NewMapBack.drawAdvancedSprite(paddingX, paddingY);
			}
		else {
			mapBack[3].drawBackground(30 + paddingX, 4 + paddingY);
			mapBack[4].drawBackground(25 + paddingX, 4 + paddingY);
			mapBack[4].drawBackground(150 + paddingX, 133 + paddingY);
		}
		compass.drawRotatableSprite(33, compassRotation, compassPixelCutRight, 256, compassPixelCutLeft, 25, (toggleFullscreen ? 9 : 8) + paddingY, (toggleFullscreen ? 30 : 11) + paddingX, 33, 25);
		drawOrbs(paddingX,paddingY);
		drawCoinOrb();
		advisorButton[advisorHover].drawSprite((toggleFullscreen ? 207-52 : 207) + paddingX,(toggleFullscreen ? 4 : 0) + paddingY);
		drawXPCounter(paddingX,paddingY);
		if(tabInterfaceIDs[10] != -1 && invOverlayInterfaceID == -1)
			exitButton[exitHover].drawSprite((toggleFullscreen ? 228-52 : 228) + paddingX,(toggleFullscreen ? 4 : 0) + paddingY);
		worldMapIcon[mapHover].drawSprite((toggleFullscreen ? 154 : 10) + paddingX,(toggleFullscreen ? 138 : 124) + paddingY);

		DrawingArea.drawPixels(3, 76 + mapPaddingFromTop + paddingY, 76 + mapPaddingFromLeft + paddingX, 0xffffff, 3);
		if(contextMenuOpen && !toggleFullscreen)
			drawMenu(516, 0);
		main3DArea.initDrawingArea();
	}



	private void npcScreenPos(Entity entity, int i) {
		calcEntityScreenPos(entity.x, i, entity.y);
	}

	private void calcEntityScreenPos(int i, int j, int l) {
		if(i < 128 || l < 128 || i > 13056 || l > 13056) {
			spriteDrawX = -1;
			spriteDrawY = -1;
			return;
		}
		int i1 = method42(plane, l, i) - j;
		i -= xCameraPos;
		i1 -= zCameraPos;
		l -= yCameraPos;
		int j1 = Model.modelIntArray1[yCameraCurve];
		int k1 = Model.modelIntArray2[yCameraCurve];
		int l1 = Model.modelIntArray1[xCameraCurve];
		int i2 = Model.modelIntArray2[xCameraCurve];
		int j2 = l * l1 + i * i2 >> 16;
		l = l * i2 - i * l1 >> 16;
		i = j2;
		j2 = i1 * k1 - l * j1 >> 16;
		l = i1 * j1 + l * k1 >> 16;
		i1 = j2;
		if(l >= 50) {
			spriteDrawX = Texture.textureInt1 + (i << 9) / l;
			spriteDrawY = Texture.textureInt2 + (i1 << 9) / l;
		} else {
			spriteDrawX = -1;
			spriteDrawY = -1;
		}
	}

	private void buildSplitPrivateChatMenu(int paddingX, int paddingY)
	{
		if(splitPrivateChat == 0)
			return;
		int drawLineY = 0;
		if(systemUpdatetime != 0)
			drawLineY = 1;
		for(int indexLine = 0; indexLine < 100; indexLine++)
		{
			if(chatMessages[indexLine] == null)
				continue;
			int type = chatTypes[indexLine];
			String name = chatNames[indexLine];
			if(name != null && name.startsWith("@cr1@"))
				name = name.substring(5);
			if(name != null && name.startsWith("@cr2@"))
				name = name.substring(5);
			if(name != null && name.startsWith("@cr3@"))
				name = name.substring(5);
			if(name != null && name.startsWith("@cr4@"))
				name = name.substring(5);
			if(name != null && name.startsWith("@cr5@"))
				name = name.substring(5);
			if(name != null && name.startsWith("@cr6@"))
				name = name.substring(5);

			if((type == 3 || type == 7) && (type == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(name)))
			{
				int l = (329 - drawLineY * 13) - paddingY;
				if(super.mouseX > 4 && super.mouseY - 4 > l - 10 && super.mouseY - 4 <= l + 3)
				{
					int textWidth = normalFont.getTextWidth("From:  " + name + chatMessages[indexLine]) + 25;
					if(textWidth > 450)
						textWidth = 450;
					if(super.mouseX < 4 + textWidth)
					{
						if(myPlayer.playerRights >= 1)
						{
							menuActionName[menuActionRow] = "Report abuse @whi@" + name;
							menuActionID[menuActionRow] = 606;
							menuActionRow++;
						}
						menuActionName[menuActionRow] = "Add ignore @whi@" + name;
						menuActionID[menuActionRow] = 2042;
						menuActionRow++;
						menuActionName[menuActionRow] = "Add friend @whi@" + name;
						menuActionID[menuActionRow] = 2337;
						menuActionRow++;
						menuActionName[menuActionRow] = "Message @whi@" + name;
						menuActionID[menuActionRow] = 639;
						menuActionRow++;
					}
				}
				if(++drawLineY >= 5)
					return;
			}
			if((type == 5 || type == 6) && privateChatMode < 2 && ++drawLineY >= 5)
				return;
		}

	}

	private void method130(int j, int k, int l, int i1, int j1, int k1,
						   int l1, int i2, int j2)
	{
		Class30_Sub1 class30_sub1 = null;
		for(Class30_Sub1 class30_sub1_1 = (Class30_Sub1)aClass19_1179.reverseGetFirst(); class30_sub1_1 != null; class30_sub1_1 = (Class30_Sub1)aClass19_1179.reverseGetNext())
		{
			if(class30_sub1_1.anInt1295 != l1 || class30_sub1_1.anInt1297 != i2 || class30_sub1_1.anInt1298 != j1 || class30_sub1_1.anInt1296 != i1)
				continue;
			class30_sub1 = class30_sub1_1;
			break;
		}

		if(class30_sub1 == null)
		{
			class30_sub1 = new Class30_Sub1();
			class30_sub1.anInt1295 = l1;
			class30_sub1.anInt1296 = i1;
			class30_sub1.anInt1297 = i2;
			class30_sub1.anInt1298 = j1;
			method89(class30_sub1);
			aClass19_1179.insertHead(class30_sub1);
		}
		class30_sub1.anInt1291 = k;
		class30_sub1.anInt1293 = k1;
		class30_sub1.anInt1292 = l;
		class30_sub1.anInt1302 = j2;
		class30_sub1.anInt1294 = j;
	}

	private boolean interfaceIsSelected(RSInterface class9)
	{
		if(class9.valueCompareType == null)
			return false;
		for(int i = 0; i < class9.valueCompareType.length; i++)
		{
			int j = extractInterfaceValues(class9, i);
			int k = class9.requiredValues[i];
			if(class9.valueCompareType[i] == 2)
			{
				if(j >= k)
					return false;
			} else
			if(class9.valueCompareType[i] == 3)
			{
				if(j <= k)
					return false;
			} else
			if(class9.valueCompareType[i] == 4)
			{
				if(j == k)
					return false;
			} else
			if(j != k)
				return false;
		}

		return true;
	}

	private DataInputStream openJagGrabInputStream(String s)
		throws IOException
	{
 //	   if(!aBoolean872)
 //		   if(signlink.mainapp != null)
 //			   return signlink.openurl(s);
 //		   else
 //			   return new DataInputStream((new URL(getCodeBase(), s)).openStream());
		if(aSocket832 != null)
		{
			try
			{
				aSocket832.close();
			}
			catch(Exception _ex) { }
			aSocket832 = null;
		}
		aSocket832 = openSocket(43594);
		aSocket832.setSoTimeout(10000);
		java.io.InputStream inputstream = aSocket832.getInputStream();
		OutputStream outputstream = aSocket832.getOutputStream();
		outputstream.write(("JAGGRAB /" + s + "\n\n").getBytes());
		return new DataInputStream(inputstream);
	}

	private void method134(Stream stream)
	{
		int j = stream.readBits(8);
		if(j < playerCount)
		{
			for(int k = j; k < playerCount; k++)
				anIntArray840[anInt839++] = playerIndices[k];

		}
		if(j > playerCount)
		{
			signlink.reporterror(myUsername + " Too many players");
			throw new RuntimeException("eek");
		}
		playerCount = 0;
		for(int l = 0; l < j; l++)
		{
			int i1 = playerIndices[l];
			Player player = playerArray[i1];
			int j1 = stream.readBits(1);
			if(j1 == 0)
			{
				playerIndices[playerCount++] = i1;
				player.anInt1537 = loopCycle;
			} else
			{
				int k1 = stream.readBits(2);
				if(k1 == 0)
				{
					playerIndices[playerCount++] = i1;
					player.anInt1537 = loopCycle;
					anIntArray894[anInt893++] = i1;
				} else
				if(k1 == 1)
				{
					playerIndices[playerCount++] = i1;
					player.anInt1537 = loopCycle;
					int l1 = stream.readBits(3);
					player.moveInDir(false, l1);
					int j2 = stream.readBits(1);
					if(j2 == 1)
						anIntArray894[anInt893++] = i1;
				} else
				if(k1 == 2)
				{
					playerIndices[playerCount++] = i1;
					player.anInt1537 = loopCycle;
					int i2 = stream.readBits(3);
					player.moveInDir(true, i2);
					int k2 = stream.readBits(3);
					player.moveInDir(true, k2);
					int l2 = stream.readBits(1);
					if(l2 == 1)
						anIntArray894[anInt893++] = i1;
				} else
				if(k1 == 3)
					anIntArray840[anInt839++] = i1;
			}
		}
	}

	public int world = 1;
	public String spec = " Regular";
	public int goToInterface = 0;
	private void drawLoginScreen(boolean flag) {
		if (goToInterface == 1) {
			drawWorldSwitcher();
			return;
		} else if (goToInterface == 2) {
			drawGameframeSwitcher();
			return;
		} else {
		resetImageProducers();
		//System.out.println("draw login screen method");
		aRSImageProducer_1109.initDrawingArea();
		//int x = 49;
		loginBox.drawSprite(254, 140);
		//char c = '\u0168';
		//char c1 = '\310';
		backgroundFix.drawSprite(0, 0);
	//	smallFont.method389(true,15,0xFFFF00,"Mouse X: " + super.mouseX + " , Mouse Y: " + super.mouseY, 15);
		// Welcome screen
		if ((this.mouseX >= 293) && (this.mouseX <= 473) && (this.mouseY >= 258) && (this.mouseY <= 282)) {
			loginHover.drawSprite(293, 256);
		}
		if ((this.mouseX >= 287) && (this.mouseX <= 482) && (this.mouseY >= 318) && (this.mouseY <= 346)) { //world select
			optionsHover.drawSprite(287, 318);
		}
		if ((this.mouseX >= 287) && (this.mouseX <= 482) && (this.mouseY >= 372) && (this.mouseY <= 401)) { //gameframe toggle
			optionsHover.drawSprite(287, 372);
		}
		if((this.mouseX >= 332) && (this.mouseX <= 429) && (this.mouseY >= 286) && (this.mouseY <= 299)) {
			this.newSmallFont.drawBasicString("<u=16777215>Recover Password</u>", 335, 297, 16777215, -1);
		} else {
			this.newSmallFont.drawBasicString("<u=13948360>Recover Password</u>", 335, 297, 13948360, -1);
		}
		this.smallFont.method382(16777215, 335, "Gaphics:", 338, true);
		if(world == 1) {
			this.smallFont.method382(16777215, 431, "Low", 338, true);
		} else {
			this.smallFont.method382(16777215, 431, "High", 338, true);
		}
		
		this.smallFont.method382(16777215, 336, "Gameframe:", 392, true);
		if(newGameFrame) {
			this.smallFont.method382(16777215, 432, "Newest", 392, true);
		} else {
			this.smallFont.method382(16777215, 432, "Regular", 392, true);
		}
		
		this.smallFont.method382(16777215, 385, this.loginMessage1, 283-48, true);
        this.smallFont.method382(16777215, 385, this.loginMessage2, 297-48, true);
        this.smallFont.method389(false, 281, 16777215, new StringBuilder().append("").append(capitalize(myUsername)).append(((this.loginScreenCursorPos == 0 ? 1 : 0) & (loopCycle % 40 < 20 ? 1 : 0)) != 0 ? "|" : "").toString(), 167);
        this.smallFont.method389(true, 281, 16777215, new StringBuilder().append("").append(TextClass.passwordAsterisks(myPassword)).append(((this.loginScreenCursorPos == 1 ? 1 : 0) & (loopCycle % 40 < 20 ? 1 : 0)) != 0 ? "|" : "").toString(), 214);
		//aRSImageProducer_1109.drawGraphics(135, super.graphics, 251);
		//aRSImageProducer_1109.drawGraphics(171, super.graphics, 202);
		aRSImageProducer_1109.drawGraphics(0, super.graphics, 0);
		if (welcomeScreenRaised) {
			welcomeScreenRaised = false;
			/*aRSImageProducer_1107.drawGraphics(0, super.graphics, 128);
			aRSImageProducer_1108.drawGraphics(371, super.graphics, 202);
			aRSImageProducer_1112.drawGraphics(265, super.graphics, 0);
			aRSImageProducer_1113.drawGraphics(265, super.graphics, 562);
			aRSImageProducer_1114.drawGraphics(171, super.graphics, 128);
			aRSImageProducer_1115.drawGraphics(171, super.graphics, 562);*/
		}
		}
	}
	
	int drawCheckMark = 0;
	public void drawWorldSwitcher() {
		worldSwitcherBg.drawSprite(0, 0);
		resetImageProducers();
		aRSImageProducer_1109.initDrawingArea();
		smallFont.method389(true,15,0xFFFF00,"Mouse X: " + super.mouseX + " , Mouse Y: " + super.mouseY, 15);
		this.smallFont.method382(16777215, 331, "Graphics", 172, true);
		this.smallFont.method382(16777215, 436, "Selected", 172, true);
		
		this.normalFont.method382(16777215, 399, "Low Graphics", 210, true);
		
		this.normalFont.method382(16777215, 399, "High Graphics", 246, true);
		
		if(drawCheckMark == 0) {
			checkMark.drawSprite(455, 202);
			setLowMem();
		} else if (drawCheckMark == 1) {
			checkMark.drawSprite(455, 239);
			setHighMem();
		}
		
		this.newSmallFont.drawBasicString("<u=3337795>Apply!</u>", 360, 350, 3337795, -1);
		
		aRSImageProducer_1109.drawGraphics(0, super.graphics, 0);
		if (welcomeScreenRaised) {
			welcomeScreenRaised = false;
		}
	}
	
	int gfDrawCheckmark = 0;
	public void drawGameframeSwitcher() {
		resetImageProducers();
		aRSImageProducer_1109.initDrawingArea();
		gameframeSwitcherBg.drawSprite(0, 0);
	//	smallFont.method389(true,15,0xFFFF00,"Mouse X: " + super.mouseX + " , Mouse Y: " + super.mouseY, 15);
		this.smallFont.method382(16777215, 331, "Gameframe", 172, true);
		this.smallFont.method382(16777215, 436, "Selected", 172, true);
		
		this.normalFont.method382(16777215, 326, "Old", 227, true);
		this.normalFont.method382(16777215, 326, "Gameframe", 239, true);
		
		this.normalFont.method382(16777215, 326, "New", 288, true);
		this.normalFont.method382(16777215, 326, "Gameframe", 300, true);
		
		if(gfDrawCheckmark == 0) {
			checkMark.drawSprite(456, 219);
		} else if(gfDrawCheckmark == 1) {
			checkMark.drawSprite(456, 291);
		} else {
			gfDrawCheckmark = 0;
		}
		
		this.newSmallFont.drawBasicString("<u=3337795>Apply!</u>", 360, 350, 3337795, -1);
		aRSImageProducer_1109.drawGraphics(0, super.graphics, 0);
		if (welcomeScreenRaised) {
			welcomeScreenRaised = false;
		}
	}
	
	private int anInt1040;
	private int anInt1041;
	private int[] anIntArray850;
	
	private void doFlamesDrawing()
	{
		char c = '\u0100';
		if(anInt1040 > 0)
		{
			for(int i = 0; i < 256; i++)
				if(anInt1040 > 768)
					anIntArray850[i] = method83(anIntArray851[i], anIntArray852[i], 1024 - anInt1040);
				else
				if(anInt1040 > 256)
					anIntArray850[i] = anIntArray852[i];
				else
					anIntArray850[i] = method83(anIntArray852[i], anIntArray851[i], 256 - anInt1040);

		} else
		if(anInt1041 > 0)
		{
			for(int j = 0; j < 256; j++)
				if(anInt1041 > 768)
					anIntArray850[j] = method83(anIntArray851[j], anIntArray853[j], 1024 - anInt1041);
				else
				if(anInt1041 > 256)
					anIntArray850[j] = anIntArray853[j];
				else
					anIntArray850[j] = method83(anIntArray853[j], anIntArray851[j], 256 - anInt1041);

		} else
		{
			System.arraycopy(anIntArray851, 0, anIntArray850, 0, 256);

		}
		System.arraycopy(aClass30_Sub2_Sub1_Sub1_1201.myPixels, 0, aRSImageProducer_1110.anIntArray315, 0, 33920);

		int i1 = 0;
		int j1 = 1152;
		for(int k1 = 1; k1 < c - 1; k1++)
		{
			int l1 = (anIntArray969[k1] * (c - k1)) / c;
			int j2 = 22 + l1;
			if(j2 < 0)
				j2 = 0;
			i1 += j2;
			for(int l2 = j2; l2 < 128; l2++)
			{
				int j3 = anIntArray828[i1++];
				if(j3 != 0)
				{
					int l3 = j3;
					int j4 = 256 - j3;
					j3 = anIntArray850[j3];
					int l4 = aRSImageProducer_1110.anIntArray315[j1];
					aRSImageProducer_1110.anIntArray315[j1++] = ((j3 & 0xff00ff) * l3 + (l4 & 0xff00ff) * j4 & 0xff00ff00) + ((j3 & 0xff00) * l3 + (l4 & 0xff00) * j4 & 0xff0000) >> 8;
				} else
				{
					j1++;
				}
			}

			j1 += j2;
		}

		aRSImageProducer_1110.drawGraphics(0, super.graphics, 0);
		System.arraycopy(aClass30_Sub2_Sub1_Sub1_1202.myPixels, 0, aRSImageProducer_1111.anIntArray315, 0, 33920);

		i1 = 0;
		j1 = 1176;
		for(int k2 = 1; k2 < c - 1; k2++)
		{
			int i3 = (anIntArray969[k2] * (c - k2)) / c;
			int k3 = 103 - i3;
			j1 += i3;
			for(int i4 = 0; i4 < k3; i4++)
			{
				int k4 = anIntArray828[i1++];
				if(k4 != 0)
				{
					int i5 = k4;
					int j5 = 256 - k4;
					k4 = anIntArray850[k4];
					int k5 = aRSImageProducer_1111.anIntArray315[j1];
					aRSImageProducer_1111.anIntArray315[j1++] = ((k4 & 0xff00ff) * i5 + (k5 & 0xff00ff) * j5 & 0xff00ff00) + ((k4 & 0xff00) * i5 + (k5 & 0xff00) * j5 & 0xff0000) >> 8;
				} else
				{
					j1++;
				}
			}

			i1 += 128 - k3;
			j1 += 128 - k3 - i3;
		}

		aRSImageProducer_1111.drawGraphics(0, super.graphics, 637);
	}
	


public void drawFlames() {
		drawingFlames = true;
		try {
			long l = System.currentTimeMillis();
			int i = 0;
			int j = 20;
			while (aBoolean831) {
				anInt1208++;
				// calcFlamesPosition();
				// calcFlamesPosition();
				doFlamesDrawing();
				if (++i > 10) {
					long l1 = System.currentTimeMillis();
					int k = (int) (l1 - l) / 10 - j;
					j = 40 - k;
					if (j < 5)
						j = 5;
					i = 0;
					l = l1;
				}
				try {
					Thread.sleep(j);
				} catch (Exception _ex) {
				}
			}
		} catch (Exception _ex) {
		}
		drawingFlames = false;
	}

	public void raiseWelcomeScreen() {
		welcomeScreenRaised = true;
	}

	private void method137(Stream stream, int j)
	{
	try {
		if(j == 84)
		{
			int k = stream.readUnsignedByte();
			int j3 = anInt1268 + (k >> 4 & 7);
			int i6 = anInt1269 + (k & 7);
			int l8 = stream.readUnsignedWord();
			int k11 = stream.readUnsignedWord();
			int l13 = stream.readUnsignedWord();
			if(j3 >= 0 && i6 >= 0 && j3 < 104 && i6 < 104)
			{
				NodeList class19_1 = groundArray[plane][j3][i6];
				if(class19_1 != null)
				{
					for(Item class30_sub2_sub4_sub2_3 = (Item)class19_1.reverseGetFirst(); class30_sub2_sub4_sub2_3 != null; class30_sub2_sub4_sub2_3 = (Item)class19_1.reverseGetNext())
					{
						if(class30_sub2_sub4_sub2_3.ID != (l8 & 0x7fff) || class30_sub2_sub4_sub2_3.anInt1559 != k11)
							continue;
						class30_sub2_sub4_sub2_3.anInt1559 = l13;
						break;
					}

					spawnGroundItem(j3, i6);
				}
			}
			return;
		}
		if(j == 105)
		{
			int l = stream.readUnsignedByte();
			int k3 = anInt1268 + (l >> 4 & 7);
			int j6 = anInt1269 + (l & 7);
			int i9 = stream.readUnsignedWord();
			int l11 = stream.readUnsignedByte();
			int i14 = l11 >> 4 & 0xf;
			int i16 = l11 & 7;
			if(myPlayer.smallX[0] >= k3 - i14 && myPlayer.smallX[0] <= k3 + i14 && myPlayer.smallY[0] >= j6 - i14 && myPlayer.smallY[0] <= j6 + i14 && aBoolean848 && !lowMem && anInt1062 < 50)
			{
				anIntArray1207[anInt1062] = i9;
				anIntArray1241[anInt1062] = i16;
				anIntArray1250[anInt1062] = Sounds.anIntArray326[i9];
				anInt1062++;
			}
		}
		if(j == 215)
		{
			int i1 = stream.method435();
			int l3 = stream.method428();
			int k6 = anInt1268 + (l3 >> 4 & 7);
			int j9 = anInt1269 + (l3 & 7);
			int i12 = stream.method435();
			int j14 = stream.readUnsignedWord();
			if(k6 >= 0 && j9 >= 0 && k6 < 104 && j9 < 104 && i12 != unknownInt10)
			{
				Item class30_sub2_sub4_sub2_2 = new Item();
				class30_sub2_sub4_sub2_2.ID = i1;
				class30_sub2_sub4_sub2_2.anInt1559 = j14;
				if(groundArray[plane][k6][j9] == null)
					groundArray[plane][k6][j9] = new NodeList();
				groundArray[plane][k6][j9].insertHead(class30_sub2_sub4_sub2_2);
				spawnGroundItem(k6, j9);
			}
			return;
		}
		if(j == 156)
		{
			int j1 = stream.method426();
			int i4 = anInt1268 + (j1 >> 4 & 7);
			int l6 = anInt1269 + (j1 & 7);
			int k9 = stream.readUnsignedWord();
			if(i4 >= 0 && l6 >= 0 && i4 < 104 && l6 < 104)
			{
				NodeList class19 = groundArray[plane][i4][l6];
				if(class19 != null)
				{
					for(Item item = (Item)class19.reverseGetFirst(); item != null; item = (Item)class19.reverseGetNext())
					{
						if(item.ID != (k9 & 0x7fff))
							continue;
						item.unlink();
						break;
					}

					if(class19.reverseGetFirst() == null)
						groundArray[plane][i4][l6] = null;
					spawnGroundItem(i4, l6);
				}
			}
			return;
		}
		if(j == 160)
		{
			int k1 = stream.method428();
			int j4 = anInt1268 + (k1 >> 4 & 7);
			int i7 = anInt1269 + (k1 & 7);
			int l9 = stream.method428();
			int j12 = l9 >> 2;
			int k14 = l9 & 3;
			int j16 = anIntArray1177[j12];
			int j17 = stream.method435();
			if(j4 >= 0 && i7 >= 0 && j4 < 103 && i7 < 103)
			{
				int j18 = intGroundArray[plane][j4][i7];
				int i19 = intGroundArray[plane][j4 + 1][i7];
				int l19 = intGroundArray[plane][j4 + 1][i7 + 1];
				int k20 = intGroundArray[plane][j4][i7 + 1];
				if(j16 == 0)
				{
					Object1 class10 = worldController.method296(plane, j4, i7);
					if(class10 != null)
					{
						int k21 = class10.uid >> 14 & 0x7fff;
						if(j12 == 2)
						{
							class10.aClass30_Sub2_Sub4_278 = new Animable_Sub5(k21, 4 + k14, 2, i19, l19, j18, k20, j17, false);
							class10.aClass30_Sub2_Sub4_279 = new Animable_Sub5(k21, k14 + 1 & 3, 2, i19, l19, j18, k20, j17, false);
						} else
						{
							class10.aClass30_Sub2_Sub4_278 = new Animable_Sub5(k21, k14, j12, i19, l19, j18, k20, j17, false);
						}
					}
				}
				if(j16 == 1)
				{
					Object2 class26 = worldController.method297(j4, i7, plane);
					if(class26 != null)
						class26.aClass30_Sub2_Sub4_504 = new Animable_Sub5(class26.uid >> 14 & 0x7fff, 0, 4, i19, l19, j18, k20, j17, false);
				}
				if(j16 == 2)
				{
					Object5 class28 = worldController.method298(j4, i7, plane);
					if(j12 == 11)
						j12 = 10;
					if(class28 != null)
						class28.aClass30_Sub2_Sub4_521 = new Animable_Sub5(class28.uid >> 14 & 0x7fff, k14, j12, i19, l19, j18, k20, j17, false);
				}
				if(j16 == 3)
				{
					Object3 class49 = worldController.method299(i7, j4, plane);
					if(class49 != null)
						class49.aClass30_Sub2_Sub4_814 = new Animable_Sub5(class49.uid >> 14 & 0x7fff, k14, 22, i19, l19, j18, k20, j17, false);
				}
			}
			return;
		}
		if(j == 147)
		{
			int l1 = stream.method428();
			int k4 = anInt1268 + (l1 >> 4 & 7);
			int j7 = anInt1269 + (l1 & 7);
			int i10 = stream.readUnsignedWord();
			byte byte0 = stream.method430();
			int l14 = stream.method434();
			byte byte1 = stream.method429();
			int k17 = stream.readUnsignedWord();
			int k18 = stream.method428();
			int j19 = k18 >> 2;
			int i20 = k18 & 3;
			int l20 = anIntArray1177[j19];
			byte byte2 = stream.readSignedByte();
			int l21 = stream.readUnsignedWord();
			byte byte3 = stream.method429();
			Player player;
			if(i10 == unknownInt10)
				player = myPlayer;
			else
				player = playerArray[i10];
			if(player != null)
			{
				ObjectDef class46 = ObjectDef.forID(l21);
				int i22 = intGroundArray[plane][k4][j7];
				int j22 = intGroundArray[plane][k4 + 1][j7];
				int k22 = intGroundArray[plane][k4 + 1][j7 + 1];
				int l22 = intGroundArray[plane][k4][j7 + 1];
				Model model = class46.method578(j19, i20, i22, j22, k22, l22, -1);
				if(model != null)
				{
					method130(k17 + 1, -1, 0, l20, j7, 0, plane, k4, l14 + 1);
					player.anInt1707 = l14 + loopCycle;
					player.anInt1708 = k17 + loopCycle;
					player.aModel_1714 = model;
					int i23 = class46.anInt744;
					int j23 = class46.anInt761;
					if(i20 == 1 || i20 == 3)
					{
						i23 = class46.anInt761;
						j23 = class46.anInt744;
					}
					player.anInt1711 = k4 * 128 + i23 * 64;
					player.anInt1713 = j7 * 128 + j23 * 64;
					player.anInt1712 = method42(plane, player.anInt1713, player.anInt1711);
					if(byte2 > byte0)
					{
						byte byte4 = byte2;
						byte2 = byte0;
						byte0 = byte4;
					}
					if(byte3 > byte1)
					{
						byte byte5 = byte3;
						byte3 = byte1;
						byte1 = byte5;
					}
					player.anInt1719 = k4 + byte2;
					player.anInt1721 = k4 + byte0;
					player.anInt1720 = j7 + byte3;
					player.anInt1722 = j7 + byte1;
				}
			}
		}
		if(j == 151)
		{
			int i2 = stream.method426();
			int l4 = anInt1268 + (i2 >> 4 & 7);
			int k7 = anInt1269 + (i2 & 7);
			int j10 = stream.method434();
			int k12 = stream.method428();
			int i15 = k12 >> 2;
			int k16 = k12 & 3;
			int l17 = anIntArray1177[i15];
			if(l4 >= 0 && k7 >= 0 && l4 < 104 && k7 < 104)
				method130(-1, j10, k16, l17, k7, i15, plane, l4, 0);
			return;
		}
		if(j == 4)
		{
			int j2 = stream.readUnsignedByte();
			int i5 = anInt1268 + (j2 >> 4 & 7);
			int l7 = anInt1269 + (j2 & 7);
			int k10 = stream.readUnsignedWord();
			int l12 = stream.readUnsignedByte();
			int j15 = stream.readUnsignedWord();
			if(i5 >= 0 && l7 >= 0 && i5 < 104 && l7 < 104)
			{
				i5 = i5 * 128 + 64;
				l7 = l7 * 128 + 64;
				Animable_Sub3 class30_sub2_sub4_sub3 = new Animable_Sub3(plane, loopCycle, j15, k10, method42(plane, l7, i5) - l12, l7, i5);
				aClass19_1056.insertHead(class30_sub2_sub4_sub3);
			}
			return;
		}
		if(j == 44)
		{
			int k2 = stream.method436();
			int j5 = stream.readUnsignedWord();
			int i8 = stream.readUnsignedByte();
			int l10 = anInt1268 + (i8 >> 4 & 7);
			int i13 = anInt1269 + (i8 & 7);
			if(l10 >= 0 && i13 >= 0 && l10 < 104 && i13 < 104)
			{
				Item class30_sub2_sub4_sub2_1 = new Item();
				class30_sub2_sub4_sub2_1.ID = k2;
				class30_sub2_sub4_sub2_1.anInt1559 = j5;
				if(groundArray[plane][l10][i13] == null)
					groundArray[plane][l10][i13] = new NodeList();
				groundArray[plane][l10][i13].insertHead(class30_sub2_sub4_sub2_1);
				spawnGroundItem(l10, i13);
			}
			return;
		}
		if(j == 101)
		{
			int l2 = stream.method427();
			int k5 = l2 >> 2;
			int j8 = l2 & 3;
			int i11 = anIntArray1177[k5];
			int j13 = stream.readUnsignedByte();
			int k15 = anInt1268 + (j13 >> 4 & 7);
			int l16 = anInt1269 + (j13 & 7);
			if(k15 >= 0 && l16 >= 0 && k15 < 104 && l16 < 104)
				method130(-1, -1, j8, i11, l16, k5, plane, k15, 0);
			return;
		}
		if(j == 117)
		{
			int i3 = stream.readUnsignedByte();
			int l5 = anInt1268 + (i3 >> 4 & 7);
			int k8 = anInt1269 + (i3 & 7);
			int j11 = l5 + stream.readSignedByte();
			int k13 = k8 + stream.readSignedByte();
			int l15 = stream.readSignedWord();
			int i17 = stream.readUnsignedWord();
			int i18 = stream.readUnsignedByte() * 4;
			int l18 = stream.readUnsignedByte() * 4;
			int k19 = stream.readUnsignedWord();
			int j20 = stream.readUnsignedWord();
			int i21 = stream.readUnsignedByte();
			int j21 = stream.readUnsignedByte();
			if(l5 >= 0 && k8 >= 0 && l5 < 104 && k8 < 104 && j11 >= 0 && k13 >= 0 && j11 < 104 && k13 < 104 && i17 != 65535)
			{
				l5 = l5 * 128 + 64;
				k8 = k8 * 128 + 64;
				j11 = j11 * 128 + 64;
				k13 = k13 * 128 + 64;
				Animable_Sub4 class30_sub2_sub4_sub4 = new Animable_Sub4(i21, l18, k19 + loopCycle, j20 + loopCycle, j21, plane, method42(plane, k8, l5) - i18, k8, l5, l15, i17);
				class30_sub2_sub4_sub4.method455(k19 + loopCycle, k13, method42(plane, k13, j11) - l18, j11);
				aClass19_1013.insertHead(class30_sub2_sub4_sub4);
			}
		}
		}catch(Exception superuberfail) {}
	}

	private static void setLowMem()
	{
		WorldController.lowMem = true;
		Texture.lowMem = true;
		lowMem = true;
		ObjectManager.lowMem = true;
		ObjectDef.lowMem = true;
	}

	private void method139(Stream stream)
	{
		stream.initBitAccess();
		int k = stream.readBits(8);
		if(k < npcCount)
		{
			for(int l = k; l < npcCount; l++)
				anIntArray840[anInt839++] = npcIndices[l];

		}
		if(k > npcCount)
		{
			signlink.reporterror(myUsername + " Too many npcs");
			throw new RuntimeException("eek");
		}
		npcCount = 0;
		for(int i1 = 0; i1 < k; i1++)
		{
			int j1 = npcIndices[i1];
			NPC npc = npcArray[j1];
			int k1 = stream.readBits(1);
			if(k1 == 0)
			{
				npcIndices[npcCount++] = j1;
				npc.anInt1537 = loopCycle;
			} else
			{
				int l1 = stream.readBits(2);
				if(l1 == 0)
				{
					npcIndices[npcCount++] = j1;
					npc.anInt1537 = loopCycle;
					anIntArray894[anInt893++] = j1;
				} else
				if(l1 == 1)
				{
					npcIndices[npcCount++] = j1;
					npc.anInt1537 = loopCycle;
					int i2 = stream.readBits(3);
					npc.moveInDir(false, i2);
					int k2 = stream.readBits(1);
					if(k2 == 1)
						anIntArray894[anInt893++] = j1;
				} else
				if(l1 == 2)
				{
					npcIndices[npcCount++] = j1;
					npc.anInt1537 = loopCycle;
					int j2 = stream.readBits(3);
					npc.moveInDir(true, j2);
					int l2 = stream.readBits(3);
					npc.moveInDir(true, l2);
					int i3 = stream.readBits(1);
					if(i3 == 1)
						anIntArray894[anInt893++] = j1;
				} else
				if(l1 == 3)
					anIntArray840[anInt839++] = j1;
			}
		}

	}
	
	public void handleStupidWorldSwitch() {

			if(world > 1){ // Makes sure you can only go to 2 worlds.
		world = 1;
		}else{
		world += 1;
		}
		if(world == 2){
		serverip = "127.0.0.1";
		port = 43594;
		spec =" Regular World";
		signlink.setuid();
		} else {
		if(world == 1){
		serverip = "127.0.0.1";
		port = 43594;
		signlink.setuid();
		spec =" @red@Developers World";
		}
		}
		if(world < 0 || world > 7)
			spec = " Invalid world";
			}
	
	private void resetImage() {
        DrawingArea.setAllPixelsToZero();
    }
			

		private void processLoginScreenInput() {
		if (this.loginScreenState == 0) {
			int l = super.myHeight / 2 + 20;
			l += 20;
			int i = this.myHeight / 2 - 40;
			i += 30;
			i += 25;
			resetImage();
			if (super.clickMode3 == 1 && goToInterface == 0 && super.saveClickX >= 275
					&& super.saveClickX <= 492 && super.saveClickY >= 150
					&& super.saveClickY <= 175)
				loginScreenCursorPos = 0;
			if (super.clickMode3 == 1 && goToInterface == 0 &&  super.mouseX >= 275
					&& super.mouseX <= 492 && super.mouseY >= 198
					&& super.mouseY <= 224)
				loginScreenCursorPos = 1;
			if (super.clickMode3 == 1 && goToInterface == 0 && super.saveClickX >= 293
					&& super.saveClickX <= 473 && super.saveClickY >= 258
					&& super.saveClickY <= 282) {
				loginFailures = 0;
				if ((myUsername.length() > 0) && (myPassword.length() > 0)) {
					login(myUsername, myPassword, false);
				} else {
					this.loginScreenCursorPos = 0;
					this.loginMessage1 = "Username & Password";
					this.loginMessage2 = "Must be more than 1 character";
				}
				if (this.loggedIn)
					return;
			}
			if(super.clickMode3 == 1  && goToInterface == 0 && super.saveClickX >= 332 && super.saveClickX <= 429 && super.saveClickY >= 286 && super.saveClickY <= 299) {
				launchURL("https://www.divinationofgods.cu.cc");
			}
			//switch to world selector
			if(super.clickMode3 == 1 && goToInterface == 0 && super.saveClickX >= 287 && super.saveClickX <= 482 && super.saveClickY >= 318 && super.saveClickY <= 346) {
				goToInterface = 1;
			}
			
			//switch to gameframe selector
			if(super.clickMode3 == 1 && goToInterface == 0 && super.saveClickX >= 287 && super.saveClickX <= 482 && super.saveClickY >= 372 && super.saveClickY <= 401) {
				goToInterface = 2;
			}
			
			// select world 1 in world selector (regular)
			if (super.clickMode3 == 1 && goToInterface == 1
					&& super.saveClickX >= 450 && super.saveClickX <= 475
					&& super.saveClickY >= 197 && super.saveClickY <= 219) {
				drawCheckMark = 0;
				setLowMem();
			}

			// select world 2 in world selector (developer)
			if (super.clickMode3 == 1 && goToInterface == 1
					&& super.saveClickX >= 450 && super.saveClickX <= 475
					&& super.saveClickY >= 234 && super.saveClickY <= 256) {
				drawCheckMark = 1;
				setHighMem();
			}

			// select regular gameframe
			if (super.clickMode3 == 1 && goToInterface == 2
					&& super.saveClickX >= 450 && super.saveClickX <= 475
					&& super.saveClickY >= 213 && super.saveClickY <= 240) {
				gfDrawCheckmark = 0;
				newGameFrame = false;
			}

			// select newest gameframe
			if (super.clickMode3 == 1 && goToInterface == 2
					&& super.saveClickX >= 450 && super.saveClickX <= 475
					&& super.saveClickY >= 283 && super.saveClickY <= 311) {
				gfDrawCheckmark = 1;
				newGameFrame = true;
			}

			// go back to regular login screen from world switcher
			if (super.clickMode3 == 1 && goToInterface == 1
					&& super.saveClickX >= 357 && super.saveClickX <= 395
					&& super.saveClickY >= 338 && super.saveClickY <= 352) {
				goToInterface = 0;
			}

			// go back to regular login screen from gameframe switcher
			if (super.clickMode3 == 1 && goToInterface == 2
					&& super.saveClickX >= 357 && super.saveClickX <= 395
					&& super.saveClickY >= 338 && super.saveClickY <= 352) {
				goToInterface = 0;
			}
			
			do {
				int l1 = readChar(-796);
				if (l1 == -1)
					break;
				boolean flag1 = false;
				for (int i2 = 0; i2 < validUserPassChars.length(); i2++) {
					if (l1 != validUserPassChars.charAt(i2))
						continue;
					flag1 = true;
					break;
				}

				if (loginScreenCursorPos == 0) {
					if (l1 == 8 && myUsername.length() > 0)
						myUsername = myUsername.substring(0,
								myUsername.length() - 1);
					if (l1 == 9 || l1 == 10 || l1 == 13)
						loginScreenCursorPos = 1;
					if (flag1)
						myUsername += (char) l1;
					if (myUsername.length() > 12)
						myUsername = myUsername.substring(0, 12);
				} else if (loginScreenCursorPos == 1) {
					if (l1 == 8 && myPassword.length() > 0)
						myPassword = myPassword.substring(0,
								myPassword.length() - 1);
					if (l1 == 9 || l1 == 10 || l1 == 13)
						loginScreenCursorPos = 0;
					if (flag1)
						myPassword += (char) l1;
					if (myPassword.length() > 20)
						myPassword = myPassword.substring(0, 20);
				}
			} while (true);
			return;
		}
		if (loginScreenState == 3) {
			int k = super.myWidth / 2;
			int j1 = super.myHeight / 2 + 50;
			j1 += 20;
			if (super.clickMode3 == 1 && super.saveClickX >= k - 75
					&& super.saveClickX <= k + 75
					&& super.saveClickY >= j1 - 20
					&& super.saveClickY <= j1 + 20)
				loginScreenState = 0;
		}
		/*if(loginMessage2.equals("@whi@Error connecting to server.")){ 
				loginMessage2 = "@whi@Error connecting to server.";
		}
		if (loginScreenState == 0) {
				int yoffset = 36;
				screenHover[0] = super.mouseX >= 273 && super.mouseX <= 490 && super.mouseY >= 194 + yoffset && super.mouseY <= 219 + yoffset;
				screenHover[1] = super.mouseX >= 273 && super.mouseX <= 490 && super.mouseY >= 240 + yoffset && super.mouseY <= 265 + yoffset;
				screenHover[2] = super.mouseX >= 253 && super.mouseX <= 454 && super.mouseY >= 296 + yoffset && super.mouseY <= 321 + yoffset;
				screenHover[3] = super.mouseX >= 455 && super.mouseX <= 600 && super.mouseY >= 296 + yoffset && super.mouseY <= 321 + yoffset;
				if (super.clickMode3 == 1 && super.saveClickX >= 273
						&& super.saveClickX <= 490 && super.saveClickY >= 194 + yoffset
						&& super.saveClickY <= 219 + yoffset)
					loginScreenCursorPos = 0;
				if (super.clickMode3 == 1 && super.mouseX >= 273
						&& super.mouseX <= 490 && super.mouseY >= 240 + yoffset
						&& super.mouseY <= 265 + yoffset)
					loginScreenCursorPos = 1;
				if (super.clickMode3 == 1 && super.saveClickX >= 253
						&& super.saveClickX <= 454 && super.saveClickY >= 296 + yoffset
						&& super.saveClickY <= 321 + yoffset) {
					loginFailures = 0;
					login(myUsername, myPassword, false);
					if (loggedIn)
						return;
				}
								if (super.clickMode3 == 1 && super.saveClickX >= 455
						&& super.saveClickX <= 600 && super.saveClickY >= 296 + yoffset
						&& super.saveClickY <= 321 + yoffset) {
		int x = 49;
		loginBox.drawSprite(0 + x, 0);
		handleStupidWorldSwitch();
				}
				do {
					int l1 = readChar(-796);
					if (l1 == -1)
						break;
					boolean flag1 = false;
					for (int i2 = 0; i2 < validUserPassChars.length(); i2++) {
						if (l1 != validUserPassChars.charAt(i2))
							continue;
						flag1 = true;
						break;
					}

					if (loginScreenCursorPos == 0) {
						if (l1 == 8 && myUsername.length() > 0)
							myUsername = myUsername.substring(0,
									myUsername.length() - 1);
						if (l1 == 9 || l1 == 10 || l1 == 13) {
							loginScreenCursorPos = 1;
						}
						if (flag1)
							myUsername += (char) l1;
						if (myUsername.length() > 12)
							myUsername = myUsername.substring(0, 12);
					} else if (loginScreenCursorPos == 1) {
						if (l1 == 8 && myPassword.length() > 0)
							myPassword = myPassword.substring(0,
									myPassword.length() - 1);
						if (l1 == 9 || l1 == 10 || l1 == 13) {
							if (myPassword.length() > 0
									&& myUsername.length() > 0) {
								login(myUsername, myPassword, false);
							} else {
								loginScreenCursorPos = 0;
							}
						}
						if (flag1)
							myPassword += (char) l1;
						if (myPassword.length() > 20)
							myPassword = myPassword.substring(0, 20);
					}
				} while (true);
				return;
			}*/
	}

	private void method142(int i, int j, int k, int l, int i1, int j1, int k1
	)
	{
		if(i1 >= 1 && i >= 1 && i1 <= 102 && i <= 102)
		{
			if(lowMem && j != plane)
				return;
			int i2 = 0;
			if(j1 == 0)
				i2 = worldController.method300(j, i1, i);
			if(j1 == 1)
				i2 = worldController.method301(j, i1, i);
			if(j1 == 2)
				i2 = worldController.method302(j, i1, i);
			if(j1 == 3)
				i2 = worldController.method303(j, i1, i);
			if(i2 != 0)
			{
				int i3 = worldController.method304(j, i1, i, i2);
				int j2 = i2 >> 14 & 0x7fff;
				int k2 = i3 & 0x1f;
				int l2 = i3 >> 6;
				if(j1 == 0)
				{
					worldController.method291(i1, j, i, (byte)-119);
					ObjectDef class46 = ObjectDef.forID(j2);
					if(class46.aBoolean767)
						aClass11Array1230[j].method215(l2, k2, class46.aBoolean757, i1, i);
				}
				if(j1 == 1)
					worldController.method292(i, j, i1);
				if(j1 == 2)
				{
					worldController.method293(j, i1, i);
					ObjectDef class46_1 = ObjectDef.forID(j2);
					if(i1 + class46_1.anInt744 > 103 || i + class46_1.anInt744 > 103 || i1 + class46_1.anInt761 > 103 || i + class46_1.anInt761 > 103)
						return;
					if(class46_1.aBoolean767)
						aClass11Array1230[j].method216(l2, class46_1.anInt744, i1, i, class46_1.anInt761, class46_1.aBoolean757);
				}
				if(j1 == 3)
				{
					worldController.method294(j, i, i1);
					ObjectDef class46_2 = ObjectDef.forID(j2);
					if(class46_2.aBoolean767 && class46_2.hasActions)
						aClass11Array1230[j].method218(i, i1);
				}
			}
			if(k1 >= 0)
			{
				int j3 = j;
				if(j3 < 3 && (byteGroundArray[1][i1][i] & 2) == 2)
					j3++;
				ObjectManager.method188(worldController, k, i, l, j3, aClass11Array1230[j], intGroundArray, i1, k1, j);
			}
		}
	}

	private void updatePlayers(int i, Stream stream)
	{
		anInt839 = 0;
		anInt893 = 0;
		method117(stream);
		method134(stream);
		method91(stream, i);
		method49(stream);
		for(int k = 0; k < anInt839; k++)
		{
			int l = anIntArray840[k];
			if(playerArray[l].anInt1537 != loopCycle)
				playerArray[l] = null;
		}

		if(stream.currentOffset != i)
		{
			signlink.reporterror("Error packet size mismatch in getplayer pos:" + stream.currentOffset + " psize:" + i);
			throw new RuntimeException("eek");
		}
		for(int i1 = 0; i1 < playerCount; i1++)
			if(playerArray[playerIndices[i1]] == null)
			{
				signlink.reporterror(myUsername + " null entry in pl list - pos:" + i1 + " size:" + playerCount);
				throw new RuntimeException("eek");
			}

	}

	private void setCameraPos(int j, int k, int l, int i1, int j1, int k1)
	{
		int l1 = 2048 - k & 0x7ff;
		int i2 = 2048 - j1 & 0x7ff;
		int j2 = 0;
		int k2 = 0;
		int l2 = j;
		if(l1 != 0)
		{
			int i3 = Model.modelIntArray1[l1];
			int k3 = Model.modelIntArray2[l1];
			int i4 = k2 * k3 - l2 * i3 >> 16;
			l2 = k2 * i3 + l2 * k3 >> 16;
			k2 = i4;
		}
		if(i2 != 0)
		{
/* xxx			if(cameratoggle){
				if(zoom == 0)
				zoom = k2;
			  if(lftrit == 0)
				lftrit = j2;
			  if(fwdbwd == 0)
				fwdbwd = l2;
			  k2 = zoom;
			  j2 = lftrit;
			  l2 = fwdbwd;
			}
*/
			int j3 = Model.modelIntArray1[i2];
			int l3 = Model.modelIntArray2[i2];
			int j4 = l2 * j3 + j2 * l3 >> 16;
			l2 = l2 * l3 - j2 * j3 >> 16;
			j2 = j4;
		}
		xCameraPos = l - j2;
		zCameraPos = i1 - k2;
		yCameraPos = k1 - l2;
		yCameraCurve = k;
		xCameraCurve = j1;
	}

	public void updateStrings(String str, int i) {
		switch(i) {
			case 1675: sendFrame126(str, 17508); break;//Stab
			case 1676: sendFrame126(str, 17509); break;//Slash
			case 1677: sendFrame126(str, 17510); break;//Cursh
			case 1678: sendFrame126(str, 17511); break;//Magic
			case 1679: sendFrame126(str, 17512); break;//Range
			case 1680: sendFrame126(str, 17513); break;//Stab
			case 1681: sendFrame126(str, 17514); break;//Slash
			case 1682: sendFrame126(str, 17515); break;//Crush
			case 1683: sendFrame126(str, 17516); break;//Magic
			case 1684: sendFrame126(str, 17517); break;//Range
			case 1686: sendFrame126(str, 17518); break;//Strength
			case 1687: sendFrame126(str, 17519); break;//Prayer
		}
	}

	private void changePasswordSubmit() {
		if(newPasswordString.length() <= 0)
			pushMessage("Please type the new password you would like first.", 0 ," ");
		else if(newPasswordString == myPassword) {
			pushMessage("This is your current password, no changes where made.", 0 ," ");
		} else {
			sendData("::changepassword " + newPasswordString);
			myPassword = newPasswordString;
			openInterfaceID = -1;
			setDetails();
		}
	}
	private void changeUserSubmit() {
		if(newUserString.length() <= 0)
			pushMessage("Please type the new username you would like first.", 0 ," ");
		if(newUserString == myUsername) {
			pushMessage("This is your current username, no changes where made.", 0 ," ");	
			return;
			}
			sendData("::changeusername " + newUserString);
			//myUsername = newUserString;
			openInterfaceID = -1;
			setDetails();
		}
	
	
	
	public String strengthCheck(String newPassword) {
		Pattern[] pattern = new Pattern[3];
		Matcher matcher;
		pattern[0] = Pattern.compile("[a-zA-Z]");
		pattern[1] = Pattern.compile("\\d");
		pattern[2] = Pattern.compile("[^a-zA-Z0-9]");
		int level = 0, i = 0;
		// find the strength
		while(i != 3) {
			matcher = pattern[i].matcher(newPassword);
			if(matcher.find())
				level++;
			i++;
		}
		String strength = "None";
		if(level == 1 && newPassword.length() < 6)
			strength = "@red@Very weak";
		else if (level == 1 && newPassword.length() < 9)
			strength = "@or2@Weak";
		else if (level == 1 && newPassword.length() >= 9 || level == 2 && newPassword.length() < 9)
			strength = "@or3@Medium";
		else if (level == 2 && newPassword.length() >= 9 || level == 3 && newPassword.length() < 6)
			strength = "@yel@Strong";
		else if (level == 3 && newPassword.length() < 9)
			strength = "@gre@Very strong";
		else if (level == 3 && newPassword.length() >= 9)
			strength = "@blu@Best";
		// check for sequences
		pattern[0] = Pattern.compile("[a-zA-Z]{3,}");
		pattern[1] = Pattern.compile("[0-9]{3,}");
		boolean sequence = false;
		i = 0;
		while (i != 2) {
			matcher = pattern[i].matcher(newPassword);
			while(matcher.find()) {
				if(sequenceCheck(matcher.group(),i)) {
					sequence = true;
					break;
				}
			}
			i++;
			System.out.println(i);
		}
		return strength + "." + (sequence ? "@whi@Tip: Don't use easy sequences." : "");
	}
	
	boolean sequenceCheck(String s,int n) {
		s = s.toLowerCase();
		String sequence;
		if(n == 0)
			sequence = "abcdefghijklmnopqrstuvwxyz";
		else
			sequence = "0123456789";
		Pattern pattern;
		Matcher matcher;
		int b = 0,e = 3;
		while(!s.substring(b,e).equals("")) {
			pattern = Pattern.compile(s.substring(b,e));
			matcher = pattern.matcher(sequence);
			if (matcher.find())
				return true;
			b++;
			e++;
			if(e > s.length())
				return false;
		}
		return false;
	}
	
	public void sendFrame126(String str,int i) {
		RSInterface.interfaceCache[i].message = str;
		if(RSInterface.interfaceCache[i].parentID == tabInterfaceIDs[tabID]) {
		}
	}

	public void sendPacket185(int button,int toggle,int type) {
		switch(type) {
			case 135:
				RSInterface class9 = RSInterface.interfaceCache[button];
				boolean flag8 = true;
				if(class9.contentType > 0)
					flag8 = promptUserForInput(class9);
				if(flag8) {
					if(button >= 17202 && button <= 17227)
						togglePrayerState(button);
					switch(button) {
						case 22597:
							changePasswordSubmit();
							break;
							case 21597:
							changeUserSubmit();
							break;
						case 17231://Quick prayr confirm
							saveQuickSelection();
							break;
						case 19144:
							sendFrame248(15106,3213);
							method60(15106);
							inputTaken = true;
							break;
						case 22762:
							openInterfaceID = 23471;//Pouch interface
							break;
						case 23475:
							openInterfaceID = 22760;//Scroll interface
							break;
						default:
							stream.createFrame(185);
							stream.writeWord(button);
							break;
						
					}
				}
				break;
			case 646:
				stream.createFrame(185);
				stream.writeWord(button);
				RSInterface class9_2 = RSInterface.interfaceCache[button];
				if(class9_2.valueIndexArray != null && class9_2.valueIndexArray[0][0] == 5) {
					toggle = class9_2.valueIndexArray[0][1];
					if(variousSettings[toggle] != class9_2.requiredValues[0]) {
						variousSettings[toggle] = class9_2.requiredValues[0];
						method33(toggle);
					}
				}
				break;
			case 169:
				stream.createFrame(185);
				stream.writeWord(button);
				RSInterface class9_3 = RSInterface.interfaceCache[button];
				if(class9_3.valueIndexArray != null && class9_3.valueIndexArray[0][0] == 5) {
					toggle = class9_3.valueIndexArray[0][1];
					variousSettings[toggle] = 1 - variousSettings[toggle];
					method33(toggle);
					if(button >= 25000 && button <= 25050) {
						handePrayerCorrection(button);
					}
					switch(button) {
						//functionKeys
						case 26007://Toggle function keys
							functionKeys = !functionKeys;
							setDetails();
							break;
						case 26008://Toggle halth bars
							healthBars = !healthBars;
							setDetails();
							break;
						case 26010://Toggle x10 damage
							newDamage = !newDamage;
							setDetails();
							break;
						case 152:
							if(variousSettings[toggle] == 1)
								orbToggle[2] = true;
							else
								orbToggle[2] = false;
							break;
						case 19136:
							if(toggle == 0)
								sendFrame36(173,toggle);
							if(toggle == 1)
								sendPacket185(153,173,646);
							break;
						case 25842://More options
							toggle = (openInterfaceID == 26000) ? 0 : 1;
							sendFrame36(175,toggle);
							if(toggle == 1)
								openInterfaceID = 26000;
							else
								openInterfaceID = -1;
						break;
						case 26012://Fullscreen
							switchToFullScreen();
							break;
					}
				}
				break;
		}
		if(button == 2458 && toggle == -1 && type == 135) {
			loginMessage2 = "Enter Your Username & Password!";
		}
		System.out.println("button: " + button + ", toggle: " + toggle + ", type: " + type);
	}
	
	private void configureQuickTicks() {
		if(prayerInterfaceType == 5608) {
			if(getQuickPrayersSet() > 0) {
				for(int index = 0; index < quickPrayers.length; index++) {
					if(quickPrayers[index] == 1) {
						if(index >= 0 && index <= 25) {
							if(getOrbValues(1,null)[1] >= prayerLevelRequirements[index]) {
								int[] types = getPrayerTypeForIndex(index);
								if(types != null) {
									for(int g = 0; g < rangeAndMagePray.length; g++) {
										if(index == rangeAndMagePray[g]) {
											types = rangeAndMagePrayOff;
										}
									}
									for(int i = 0; i < types.length; i++) {
										if(index != types[i]) {
											if(index == 24 || index == 25) {
												types = superiorPray;
											}
											quickPrayers[types[i]] = 0;
											variousSettings[quickConfigIDs[types[i]]] = 0;
											method33(quickConfigIDs[types[i]]);
											if(dialogID != -1)
												inputTaken = true;
										} else {
											quickPrayers[index] = 1;
											variousSettings[quickConfigIDs[index]] = quickPrayers[index];
											method33(quickConfigIDs[index]);
											if(dialogID != -1)
												inputTaken = true;
										}
									}
								} else {
									quickPrayers[index] = 1;
									variousSettings[quickConfigIDs[index]] = quickPrayers[index];
									method33(quickConfigIDs[index]);
									if(dialogID != -1)
										inputTaken = true;
								}
							}
						}
					}
				}
			}
		} else if(prayerInterfaceType == 22500) {
			if(getQuickCursesSet() > 0) {
				for(int index = 0; index < quickCurses.length; index++) {
					if(quickCurses[index] == 1) {
						if(index >= 0 && index <= 20) {
							if(getOrbValues(1,null)[1] >= curseLevelRequirements[index]) {
								System.out.println("woop: " + getQuickCursesSet() + ", index: " + index);
								int[] types = getCurseTypeForIndex(index);
								if(types != null) {
									for(int i = 0; i < types.length; i++) {
										if(index != types[i]) {
											quickCurses[types[i]] = 0;
											variousSettings[quickConfigIDs[types[i]]] = 0;
											method33(quickConfigIDs[types[i]]);
											if(dialogID != -1)
												inputTaken = true;
										}
									}
								}
								quickCurses[index] =  1;
								variousSettings[quickConfigIDs[index]] = quickCurses[index];
								method33(quickConfigIDs[index]);
								if(dialogID != -1)
									inputTaken = true;
							}
						}
					}
				}
			}
		}
	}
	
	public void readDetails()
	{
		String line = "";
		String token = "";
		String token2 = "";
		int ReadMode = 0;
		boolean EndOfFile = false;
		String[] token3 = new String[30];
		BufferedReader details = null;
		try
		{
			details = new BufferedReader(new FileReader(signlink.findcachedir() + "/details2.ini"));
			try
			{
				line = details.readLine();
			} catch(IOException ioexception)
			{
				System.out.println("Error reading details2.ini");
			}
			while(EndOfFile == false && line != null)
			{
				line = line.trim();
				int spot = line.indexOf("=");
				if(spot > -1)
				{
					token = line.substring(0, spot);
					token = token.trim();
					token2 = line.substring(spot + 1);
					token2 = token2.trim();
					token3 = token2.split("\t");
					switch(ReadMode)
					{
						case 1:
							if(token.equals("username")) {
								myUsername = token2;
							}
							if(token.equals("password"))
								myPassword = token2;
							//if(token.equals("server"))
							//	serverip = token2;
							if(token.equals("keys"))
								functionKeys = token2.equals("0") ? false : true;
							if(token.equals("health"))
								healthBars = token2.equals("0") ? false : true;
							if(token.equals("damage"))
								newDamage = token2.equals("0") ? false : true;
							break;
						case 2:
							if (token.equals("prayer")) {
								for (int j = 0; j < token3.length; j++) {
									quickPrayers[j] = Integer.parseInt(token3[j]);						
								}
							}
							break;
						case 3:
							if (token.equals("curse")) {
								for (int j = 0; j < token3.length; j++) {
									quickCurses[j] = Integer.parseInt(token3[j]);						
								}
							}
							break;
						
					}
				} else {
					if(line.equals("[ACCOUNT]"))
						ReadMode = 1;
					else if(line.equals("[PRAYERS]"))
						ReadMode = 2;
					else if(line.equals("[CURSES]"))
						ReadMode = 3;
					if(line.equals("[EOF]"))
						try {
							details.close();
						} catch(IOException ioexception)
						{
							ioexception.printStackTrace();
						}
				}
				try
				{
					line = details.readLine();
				} catch(IOException ioexception1)
				{
					EndOfFile = true;
				}
			}
		} catch(FileNotFoundException file) {
			System.out.println("Couldn't find details2.ini");
		}
		try {
			if(details != null)
				details.close();
		} catch(NullPointerException ex) {
			ex.printStackTrace();
		} catch(IOException ioexception) {
			ioexception.printStackTrace();
		}
	}
	
	public void setDetails()
	{
		BufferedWriter details = null;
		try
		{
			details = new BufferedWriter(new FileWriter(signlink.findcachedir()+"/details2.ini"));
			saveValue(details,"[ACCOUNT]","nothing",false);
			saveValue(details,"username",myUsername,false);
			saveValue(details,"password",myPassword,false);
			//saveValue(details,"server",serverip,false);
			saveValue(details,"keys",functionKeys ? "1" : "0",false);
			saveValue(details,"health",healthBars ? "1" : "0",false);
			saveValue(details,"damage",newDamage ? "1" : "0",true);

			saveValue(details,"[PRAYERS]","nothing",false);
			String stringSave = "";
			for(int i = 0; i < quickPrayers.length; i++)	{
				stringSave = stringSave + quickPrayers[i] + "\t";
			}
			saveValue(details,"prayer",stringSave,true);
			
			saveValue(details,"[CURSES]","nothing",false);
			stringSave = "";
			for(int i = 0; i < quickCurses.length; i++)	{
				stringSave = stringSave + quickCurses[i] + "\t";
			}
			saveValue(details,"curse",stringSave,true);
			
			//Ends
			details.newLine();
			details.write("[EOF]", 0, 5);
			details.newLine();
			details.newLine();
			details.close();
		} catch(IOException io) {
			(new File(signlink.findcachedir()+"/details2.ini")).delete();
			System.out.println("Error while writing details2.ini");
		}
	}
	
	private int[] quickPrayers = new int[26];
	private int[] quickCurses = new int[20];
	private boolean loadTickSelection = false;
	
	public static void saveValue(BufferedWriter buffer, String value,String variable,boolean last)
	{
		try
		{
			if(value == null || variable.equals("null"))
				System.out.println("Error: saveValue had no identifier.");
			if(variable == null || variable.equals("null"))
				variable = "0";
			if(value.startsWith("[") && value.endsWith("]"))
			{
				buffer.write(value, 0, value.length());
			} else {
				value =  value + " = ";
				buffer.write(value, 0, value.length());
				buffer.write(variable, 0, variable.length());
			}
			buffer.newLine();
			if(last)
				buffer.newLine();
		}
		catch(IOException ioexception) {
			ioexception.printStackTrace();
		}
		catch(NullPointerException nullpointerexception)
		{
			nullpointerexception.printStackTrace();
		}
	}
	
	public int[] oldPrayerButtons = {5609,5610,5611,18000,18002,5612,5613,5614,5615,5616,5617,18004,18006,5618,5619,5620,5621,5622,5623,18008,18010,683,684,685,18012,18014};
	public void handePrayerCorrection(int button) {
		int index = button - 25000;
		if(index > 0)
			index = index/2;
		sendPacket185(oldPrayerButtons[index],-1,169);
	}
	
	public void tabToReplyPm() {
        String name = null;
        for (int k = 0; k < 100; k++) {
            if (chatMessages[k] == null) {
                continue;
            }
            int l = chatTypes[k];
            if (l == 3 || l == 7) {
                name = chatNames[k];
                break;
            }
        }

        if (name == null) {
            pushMessage("You haven't received any messages to which you can reply.", 0, "");
            return;
        }

        if (name.startsWith("@cr")) {
            name = name.substring(5);
        }

        long nameAsLong = TextClass.longForName(name.trim());
        int k3 = -1;
        for (int i4 = 0; i4 < friendsCount; i4++) {
            if (friendsListAsLongs[i4] != nameAsLong) continue;
            k3 = i4;
            break;
        }

        if (k3 != -1) {
            if (friendsNodeIDs[k3] > 0) {
                inputTaken = true;
                inputDialogState = 0;
                messagePromptRaised = true;
                promptInput = "";
                friendsListAction = 3;
                aLong953 = friendsListAsLongs[k3];
                aString1121 = "Enter message to send to " + friendsList[k3];
            } else {
                pushMessage("That player is currently offline.", 0, "");
            }
        }
    }
	
	private void saveQuickSelection() {
		setDetails();
		tabInterfaceIDs[5] = prayerInterfaceType;
	}
	
	private void handleQuickAidsActive() {
		if(prayerInterfaceType == 5608) {
			if(getQuickPrayersSet() > 0) {
				if(!orbToggle[1])
					for(int i = 0; i < quickPrayers.length; i++) {
						if(quickPrayers[i] == 1) {
							sendPacket185((i * 2) + 25000,-1,169);
						}
					}
				else
					turnOffPrayers();
				orbToggle[1] = !orbToggle[1];
			} else {
				if(orbToggle[1]) {
					turnOffPrayers();
					orbToggle[1] = false;
				} else {
					pushMessage("You don't have any quick prayers selected.", 0, "");
					pushMessage("Right-click the Prayer button next to the minimap to select some.", 0, "");
				}
			}
		} else if(prayerInterfaceType == 22500) {
			if(getQuickCursesSet() > 0) {
				if(!orbToggle[1])
					for(int i = 0; i < quickCurses.length; i++) {
						if(quickCurses[i] == 1) {
							sendPacket185((i * 2) + 22503,-1,169);
						}
					}
				else
					turnOffCurses();
				orbToggle[1] = !orbToggle[1];
			} else {
				if(orbToggle[1]) {
					turnOffCurses();
					orbToggle[1] = false;
				} else {
					pushMessage("You don't have any quick curses selected.", 0, "");
					pushMessage("Right-click the Curses button next to the minimap to select some.", 0, "");
				}
			}
		}
	}
	
	private int getQuickPrayersSet() {
		int amount = 0;
		for(int i = 0; i < quickPrayers.length; i++)
			if(quickPrayers[i] == 1) {
				amount++;
			}
		return amount;
	}
	
	private int getQuickCursesSet() {
		int amount = 0;
		for(int i = 0; i < quickCurses.length; i++)
			if(quickCurses[i] == 1) {
				amount++;
			}
		return amount;
	}
	
	private void turnOffPrayers() {
		int toggle = -1;
		for(int i = 0; i < quickPrayers.length; i++) {
			RSInterface rsInterface = RSInterface.interfaceCache[(i * 2) + 25000];
			if(rsInterface.valueIndexArray != null && rsInterface.valueIndexArray[0][0] == 5) {
				toggle = rsInterface.valueIndexArray[0][1];
				if(variousSettings[toggle] == 1)
					sendPacket185((i * 2) + 25000,-1,169);
			}
		}
	}
	
	private void turnOffCurses() {
		int toggle = -1;
		for(int i = 0; i < quickCurses.length; i++) {
			RSInterface rsInterface = RSInterface.interfaceCache[(i * 2) + 22503];
			if(rsInterface.valueIndexArray != null && rsInterface.valueIndexArray[0][0] == 5) {
				toggle = rsInterface.valueIndexArray[0][1];
				if(variousSettings[toggle] == 1)
					sendPacket185((i * 2) + 22503,-1,169);
			}
		}
	}
	
	private void turnOffQuickTicks() {
		int toggle = -1;
		for(int i = 0; i < quickCurses.length; i++) {
			RSInterface rsInterface = RSInterface.interfaceCache[17202 + i];
			if(rsInterface.valueIndexArray != null && rsInterface.valueIndexArray[0][0] == 5) {
				toggle = rsInterface.valueIndexArray[0][1];
				if(variousSettings[toggle] == 1)
					sendPacket185(17202 + i,-1,169);
			}
		}
	}

	public void toggleQuickAidsSelection() {
		boolean inProcess = (tabInterfaceIDs[5] == 17200 || tabInterfaceIDs[5] == 17234);
		if(inProcess)
			saveQuickSelection();
		else {
			if(prayerInterfaceType == 5608) {
				if(!inProcess) {
					tabInterfaceIDs[5] = 17200;
					tabAreaAltered = true;
				} else {
					tabInterfaceIDs[5] = 5608;
					tabAreaAltered = true;
				}
			} else if(prayerInterfaceType == 22500) {
				if(!inProcess) {
					tabInterfaceIDs[5] = 17234;
					tabAreaAltered = true;
				} else {
					tabInterfaceIDs[5] = 22500;
					tabAreaAltered = true;
				}
			}
			tabID = 5;
		}
	}

	public final int[] curseLevelRequirements = {50,50,52,54,56,59,62,65,68,71,74,76,78,80,82,84,86,89,92,95};
	public final String[] curseName = {
		"Protect Item", "Sap Warrior", "Sap Ranger", "Sap Mage", "Sap Spirit",
		"Berserker", "Deflect Summoning", "Deflect Magic","Deflect Missiles", "Deflect Melee",
		"Leech Attack", "Leech Ranged", "Leech Magic", "Leech Defence", "Leech Strength",
		"Leech Energy", "Leech Special Attack", "Wrath", "Soul Split", "Turmoil"
	};
	public int[] quickConfigIDs = {630,631,632,633,634,635,636,637,638,639,640,641,642,643,644,645,646,647,648,649,650,651,652,653,654,655,656};
	public final int[] prayerLevelRequirements = {1,4,7,8,9,10,13,16,19,22,25,26,27,28,31,34,37,40,43,44,45,46,49,52,60,70};
	public final String[] prayerName = {
		"Thick Skin", "Burst of Strength", "Clarity of Thought", "Sharp Eye", "Mystic Will", "Rock Skin", "Superhuman Strength", "Improved Reflexes","Rapid Restore", 
		"Rapid Heal", "Protect Item", "Hawk Eye", "Mystic Lore", "Steel Skin", "Ultimate Strength", "Incredible Reflexes","Protect from Magic", "Protect from Missiles",
		"Protect from Melee","Eagle Eye", "Mystic Might", "Retribution", "Redemption", "Smite", "Chivalry", "Piety"
	};
	private int[] defPray = {0,5,13,24,25};
	private int[] strPray = {1,3,4,6,11,12,14,19,20,24,25};
	private int[] atkPray = {2,3,4,7,11,12,15,19,20,24,25};
	private int[] rangeAndMagePray = {3,4,11,12,19,20,24,25};
	private int[] rangeAndMagePrayOff = {1,2,3,4,6,7,11,12,14,15,19,20,24,25};
	private int[] headPray = {16,17,18,21,22,23};
	private int[] superiorPray = {0,1,2,3,4,5,6,7,11,12,13,14,15,19,20,24,25};
	private int[][] prayer = {defPray,strPray,atkPray,rangeAndMagePray,headPray};	
	
	private int[] getPrayerTypeForIndex(int index) {
		int[] type = null;
		for(int i = 0; i < prayer.length; i++) {
			for(int il = 0; il < prayer[i].length; il++) {
				if(index == prayer[i][il]) {
					type =  prayer[i];
				}
			}
		}
		return type;
	}
	
	private int[] sapCurse = {1,2,3,4,19};
	private int[] leechCurse = {10,11,12,13,14,15,16,19};
	private int[] deflectCurse = {7,8,9,17,18};
	
	private int[] getCurseTypeForIndex(int index) {
		int[] types = null;
		for(int g = 0; g < leechCurse.length; g++) {
			if(index == leechCurse[g])
				types = sapCurse;
		}
		for(int g = 0; g < sapCurse.length; g++) {
			if(index == sapCurse[g])
				types = leechCurse;
		}
		for(int g = 0; g < deflectCurse.length; g++) {
			if(index == deflectCurse[g])
				types = deflectCurse;
		}
		if(index == 6) {
			int[] type = {17,18};
			types = type;
		}
		if(index == 17 || index == 18) {
			int[] type = {6,7,8,9,17,18};
			types = type;
		}
		if(index == 19)  {
			int[] turmoilCurseOff = {1,2,3,4,10,11,12,13,14,15,16,19};
			types = turmoilCurseOff;
		}
		return types;
	}
	
	public void togglePrayerState(int button) {
		int index = button - 17202;
		if(prayerInterfaceType == 5608) {
			if(getOrbValues(1,null)[1] >= prayerLevelRequirements[index]) {
				int[] types = getPrayerTypeForIndex(index);
				if(types != null) {
					for(int g = 0; g < rangeAndMagePray.length; g++) {
						if(index == rangeAndMagePray[g]) {
							types = rangeAndMagePrayOff;
						}
					}
					for(int i = 0; i < types.length; i++) {
						if(index != types[i]) {
							if(index == 24 || index == 25) {
								types = superiorPray;
							}
							quickPrayers[types[i]] = 0;
							variousSettings[quickConfigIDs[types[i]]] = 0;
							method33(quickConfigIDs[types[i]]);
							if(dialogID != -1)
								inputTaken = true;
						} else {
							quickPrayers[index] = (quickPrayers[index] + 1) % 2;
							variousSettings[quickConfigIDs[index]] = quickPrayers[index];
							method33(quickConfigIDs[index]);
							if(dialogID != -1)
								inputTaken = true;
						}
					}
				} else {
					quickPrayers[index] = (quickPrayers[index] + 1) % 2;
					variousSettings[quickConfigIDs[index]] = quickPrayers[index];
					method33(quickConfigIDs[index]);
					if(dialogID != -1)
						inputTaken = true;
				}
			} else {
				pushMessage("You need a Prayer level of atleast " + prayerLevelRequirements[index] + " to use " + prayerName[index] + ".", 0, "");
			}
		} else if(prayerInterfaceType == 22500) {
			if(getOrbValues(1,null)[1] >= curseLevelRequirements[index]) {
				int[] types = getCurseTypeForIndex(index);
				if(types != null) {
					for(int i = 0; i < types.length; i++) {
						if(index != types[i]) {
							quickCurses[types[i]] = 0;
							variousSettings[quickConfigIDs[types[i]]] = 0;
							method33(quickConfigIDs[types[i]]);
							if(dialogID != -1)
								inputTaken = true;
						}
					}
				}
				quickCurses[index] = (quickCurses[index] + 1) % 2;
				variousSettings[quickConfigIDs[index]] = quickCurses[index];
				method33(quickConfigIDs[index]);
				if(dialogID != -1)
					inputTaken = true;
			} else {
				pushMessage("You need a Prayer level of atleast " + curseLevelRequirements[index] + " to use " + curseName[index] + ".", 0, "");
			}
		}
	}

	public void sendFrame36(int id,int state) {
		anIntArray1045[id] = state;
		if(variousSettings[id] != state) {
			variousSettings[id] = state;
			method33(id);
			if(dialogID != -1)
				inputTaken = true;
		}
	}

	public void sendFrame219() {
		if(invOverlayInterfaceID != -1) {
			invOverlayInterfaceID = -1;
			tabAreaAltered = true;
		}
		if(backDialogID != -1) {
			backDialogID = -1;
			inputTaken = true;
		}
		if(inputDialogState != 0) {
			inputDialogState = 0;
			inputTaken = true;
		}
		openInterfaceID = -1;
		aBoolean1149 = false;
	}

	public void sendFrame248(int interfaceID,int sideInterfaceID) 	{
		if(backDialogID != -1) {
			backDialogID = -1;
			inputTaken = true;
		}
		if(inputDialogState != 0) {
			inputDialogState = 0;
			inputTaken = true;
		}
		openInterfaceID = interfaceID;
		invOverlayInterfaceID = sideInterfaceID;
		tabAreaAltered = true;
		aBoolean1149 = false;
	}

	private int editedTextFrame = 0;
	private String newText = "";
	private boolean parsePacket() {
		if(socketStream == null)
			return false;
		try {
			int i = socketStream.available();
			if(i == 0)
				return false;
			if(pktType == -1) {
				socketStream.flushInputStream(inStream.buffer, 1);
				pktType = inStream.buffer[0] & 0xff;
				if(encryption != null)
					pktType = pktType - encryption.getNextKey() & 0xff;
				pktSize = SizeConstants.packetSizes[pktType];
				i--;
			}
			if(pktSize == -1)
				if(i > 0) {
					socketStream.flushInputStream(inStream.buffer, 1);
					pktSize = inStream.buffer[0] & 0xff;
					i--;
				} else {
					return false;
				}
			if(pktSize == -2)
				if(i > 1) {
					socketStream.flushInputStream(inStream.buffer, 2);
					inStream.currentOffset = 0;
					pktSize = inStream.readUnsignedWord();
					i -= 2;
				} else {
					return false;
				}
			if(i < pktSize)
				return false;
			inStream.currentOffset = 0;
			socketStream.flushInputStream(inStream.buffer, pktSize);
			anInt1009 = 0;
			anInt843 = anInt842;
			anInt842 = anInt841;
			anInt841 = pktType;
			switch(pktType) {
				case 81:
					updatePlayers(pktSize, inStream);
					if(openInterfaceID == 26000) {
						sendFrame36(650,functionKeys ? 1 : 0);
						sendFrame36(651,healthBars ? 1 : 0);
						sendFrame36(652,newDamage ? 1 : 0);
					}
					sendFrame36(175,(openInterfaceID == 26000) ? 1 : 0);
					sendFrame36(653,toggleFullscreen ? 1 : 0);
					aBoolean1080 = false;
					pktType = -1;
					return true;
					
				case 176:
					daysSinceRecovChange = inStream.method427();
					unreadMessages = inStream.method435();
					membersInt = inStream.readUnsignedByte();
					anInt1193 = inStream.method440();
					daysSinceLastLogin = inStream.readUnsignedWord();
					if(anInt1193 != 0 && openInterfaceID == -1) {
						signlink.dnslookup(TextClass.method586(anInt1193));
						clearTopInterfaces();
						char c = '\u028A';
						if(daysSinceRecovChange != 201 || membersInt == 1)
							c = '\u028F';
						reportAbuseInput = "";
						canMute = false;
						canBan = false;
						for(int k9 = 0; k9 < RSInterface.interfaceCache.length; k9++) {
							if(RSInterface.interfaceCache[k9] == null || RSInterface.interfaceCache[k9].contentType != c)
								continue;
							openInterfaceID = RSInterface.interfaceCache[k9].parentID;
							
						}
					}
					pktType = -1;
					return true;
					
				case 64:
					anInt1268 = inStream.method427();
					anInt1269 = inStream.method428();
					for(int j = anInt1268; j < anInt1268 + 8; j++) {
						for(int l9 = anInt1269; l9 < anInt1269 + 8; l9++)
							if(groundArray[plane][j][l9] != null) {
								groundArray[plane][j][l9] = null;
								spawnGroundItem(j, l9);
							}
					}
					for(Class30_Sub1 class30_sub1 = (Class30_Sub1)aClass19_1179.reverseGetFirst(); class30_sub1 != null; class30_sub1 = (Class30_Sub1)aClass19_1179.reverseGetNext())
						if(class30_sub1.anInt1297 >= anInt1268 && class30_sub1.anInt1297 < anInt1268 + 8 && class30_sub1.anInt1298 >= anInt1269 && class30_sub1.anInt1298 < anInt1269 + 8 && class30_sub1.anInt1295 == plane)
							class30_sub1.anInt1294 = 0;
					pktType = -1;
					return true;
					
				case 185:
					int k = inStream.method436();
					RSInterface.interfaceCache[k].anInt233 = 3;
					if(myPlayer.desc == null)
						RSInterface.interfaceCache[k].mediaID = (myPlayer.anIntArray1700[0] << 25) + (myPlayer.anIntArray1700[4] << 20) + (myPlayer.equipment[0] << 15) + (myPlayer.equipment[8] << 10) + (myPlayer.equipment[11] << 5) + myPlayer.equipment[1];
					else
						RSInterface.interfaceCache[k].mediaID = (int)(0x12345678L + myPlayer.desc.type);
					pktType = -1;
					return true;
					
				/* Clan chat packet */

					
				case 107:
					aBoolean1160 = false;
					for(int l = 0; l < 5; l++)
						aBooleanArray876[l] = false;
					pktType = -1;
					return true;
					
				case 72:
					int i1 = inStream.method434();
					RSInterface class9 = RSInterface.interfaceCache[i1];
					for(int k15 = 0; k15 < class9.inv.length; k15++) {
						class9.inv[k15] = -1;
						class9.inv[k15] = 0;
					}
					pktType = -1;
					return true;
					
				case 214:
					ignoreCount = pktSize / 8;
					for(int j1 = 0; j1 < ignoreCount; j1++)
						ignoreListAsLongs[j1] = inStream.readQWord();
					pktType = -1;
					return true;
					
				case 166:
					aBoolean1160 = true;
					anInt1098 = inStream.readUnsignedByte();
					anInt1099 = inStream.readUnsignedByte();
					anInt1100 = inStream.readUnsignedWord();
					anInt1101 = inStream.readUnsignedByte();
					anInt1102 = inStream.readUnsignedByte();
					if(anInt1102 >= 100) {
						xCameraPos = anInt1098 * 128 + 64;
						yCameraPos = anInt1099 * 128 + 64;
						zCameraPos = method42(plane, yCameraPos, xCameraPos) - anInt1100;
					}
					pktType = -1;
					return true;
					
				/*case 134:					
				int skillIndex = inStream.readUnsignedByte();
					int i10 = inStream.method439();
					int l15 = inStream.readUnsignedByte();
					int xp = currentExp[skillIndex];
					currentExp[skillIndex] = i10;
					currentStats[skillIndex] = l15;
					maxStats[skillIndex] = 1;
					xpCounter += currentExp[skillIndex]-xp;
					expAdded = currentExp[skillIndex]-xp;
					for(int k20 = 0; k20 < 98; k20++)
						if(i10 >= anIntArray1019[k20])
							maxStats[skillIndex] = k20 + 2;
					pktType = -1;
					return true;*/
					case 134:
					needDrawTabArea = true;
					int k1 = inStream.readUnsignedByte();
					int i10 = inStream.method439();
					int l15 = inStream.readUnsignedByte();
					int xp = currentExp[k1];
					currentExp[k1] = i10;
					currentStats[k1] = l15;
					maxStats[k1] = 1;
					xpCounter += currentExp[k1]-xp;
					expAdded = currentExp[k1]-xp;
			for(int k20 = 0; k20 < 98; k20++)
				if(i10 >= anIntArray1019[k20])
					maxStats[k1] = k20 + 2;
					pktType = -1;
					return true;
					
				case 71:
					int l1 = inStream.readUnsignedWord();
					int j10 = inStream.method426();
					if(l1 == 65535)
						l1 = -1;
					if(j10 == 5) {
						loadTickSelection = false;
						if(orbToggle[1] && prayerInterfaceType != l1) {
							turnOffPrayers();
							turnOffCurses();
							turnOffQuickTicks();
							orbToggle[1] = !orbToggle[1];
						}
						prayerInterfaceType = l1;
					}
					tabInterfaceIDs[j10] = l1;
				tabAreaAltered = true;
					pktType = -1;
					return true;
					
				case 74:
					int i2 = inStream.method434();
					if(i2 == 65535)
						i2 = -1;
					if(i2 != currentSong && musicEnabled && !lowMem && prevSong == 0) {
						nextSong = i2;
						onDemandFetcher.method558(2, nextSong);
					}
					currentSong = i2;
					pktType = -1;
					return true;
					
				case 121:
					int j2 = inStream.method436();
					int k10 = inStream.method435();
					if(musicEnabled && !lowMem) {
						nextSong = j2;
						onDemandFetcher.method558(2, nextSong);
						prevSong = k10;
					}
					pktType = -1;
					return true;
					
				case 109:
					resetLogout();
					pktType = -1;
					return false;
					
				case 70:
					int k2 = inStream.readSignedWord();
					int l10 = inStream.method437();
					int i16 = inStream.method434();
					RSInterface class9_5 = RSInterface.interfaceCache[i16];
					class9_5.anInt263 = k2;
					class9_5.anInt265 = l10;
					pktType = -1;
					return true;
					
				case 73:
				case 241:
					int l2 = anInt1069;
					int i11 = anInt1070;
					if(pktType == 73) {
						l2 = inStream.method435();
						i11 = inStream.readUnsignedWord();
						aBoolean1159 = false;
					}
					if(pktType == 241) {
						i11 = inStream.method435();
						inStream.initBitAccess();
						for(int j16 = 0; j16 < 4; j16++) {
							for(int l20 = 0; l20 < 13; l20++) {
								for(int j23 = 0; j23 < 13; j23++) {
									int i26 = inStream.readBits(1);
									if(i26 == 1)
										anIntArrayArrayArray1129[j16][l20][j23] = inStream.readBits(26);
									else
										anIntArrayArrayArray1129[j16][l20][j23] = -1;
								}
							}
						}
						inStream.finishBitAccess();
						l2 = inStream.readUnsignedWord();
						aBoolean1159 = true;
					}
					if(anInt1069 == l2 && anInt1070 == i11 && loadingStage == 2) {
						pktType = -1;
						return true;
					}
					anInt1069 = l2;
					anInt1070 = i11;
					baseX = (anInt1069 - 6) * 8;
					baseY = (anInt1070 - 6) * 8;
					aBoolean1141 = (anInt1069 / 8 == 48 || anInt1069 / 8 == 49) && anInt1070 / 8 == 48;
					if(anInt1069 / 8 == 48 && anInt1070 / 8 == 148)
						aBoolean1141 = true;
					loadingStage = 1;
					aLong824 = System.currentTimeMillis();
					main3DArea.initDrawingArea();
					normalFont.drawText(0, "Divination of Gods Is Loading - please wait.", 151, 257);
					normalFont.drawText(0xffffff, "Divination Is Loading - please wait.", 150, 256);
					main3DArea.drawGraphics(toggleFullscreen ? 0 : 4, super.graphics, toggleFullscreen ? 0 : 4);
					if(pktType == 73) {
						int k16 = 0;
						for(int i21 = (anInt1069 - 6) / 8; i21 <= (anInt1069 + 6) / 8; i21++) {
							for(int k23 = (anInt1070 - 6) / 8; k23 <= (anInt1070 + 6) / 8; k23++)
								k16++;
						}
						aByteArrayArray1183 = new byte[k16][];
						aByteArrayArray1247 = new byte[k16][];
						anIntArray1234 = new int[k16];
						anIntArray1235 = new int[k16];
						anIntArray1236 = new int[k16];
						k16 = 0;
						for(int l23 = (anInt1069 - 6) / 8; l23 <= (anInt1069 + 6) / 8; l23++) {
							for(int j26 = (anInt1070 - 6) / 8; j26 <= (anInt1070 + 6) / 8; j26++) {
								anIntArray1234[k16] = (l23 << 8) + j26;
								if(aBoolean1141 && (j26 == 49 || j26 == 149 || j26 == 147 || l23 == 50 || l23 == 49 && j26 == 47)) {
									anIntArray1235[k16] = -1;
									anIntArray1236[k16] = -1;
									k16++;
								} else {
									int k28 = anIntArray1235[k16] = onDemandFetcher.method562(0, j26, l23);
									if(k28 != -1)
										onDemandFetcher.method558(3, k28);
									int j30 = anIntArray1236[k16] = onDemandFetcher.method562(1, j26, l23);
									if(j30 != -1)
										onDemandFetcher.method558(3, j30);
									k16++;
								}
							}
						}
					}
					if(pktType == 241) {
						int l16 = 0;
						int ai[] = new int[676];
						for(int i24 = 0; i24 < 4; i24++) {
							for(int k26 = 0; k26 < 13; k26++) {
								for(int l28 = 0; l28 < 13; l28++) {
									int k30 = anIntArrayArrayArray1129[i24][k26][l28];
									if(k30 != -1) {
										int k31 = k30 >> 14 & 0x3ff;
										int i32 = k30 >> 3 & 0x7ff;
										int k32 = (k31 / 8 << 8) + i32 / 8;
										for(int j33 = 0; j33 < l16; j33++) {
											if(ai[j33] != k32)
												continue;
											k32 = -1;
											
										}
										if(k32 != -1)
											ai[l16++] = k32;
									}
								}
							}
						}
						aByteArrayArray1183 = new byte[l16][];
						aByteArrayArray1247 = new byte[l16][];
						anIntArray1234 = new int[l16];
						anIntArray1235 = new int[l16];
						anIntArray1236 = new int[l16];
						for(int l26 = 0; l26 < l16; l26++) {
							int i29 = anIntArray1234[l26] = ai[l26];
							int l30 = i29 >> 8 & 0xff;
							int l31 = i29 & 0xff;
							int j32 = anIntArray1235[l26] = onDemandFetcher.method562(0, l31, l30);
							if(j32 != -1)
								onDemandFetcher.method558(3, j32);
							int i33 = anIntArray1236[l26] = onDemandFetcher.method562(1, l31, l30);
							if(i33 != -1)
								onDemandFetcher.method558(3, i33);
						}
					}
					int i17 = baseX - anInt1036;
					int j21 = baseY - anInt1037;
					anInt1036 = baseX;
					anInt1037 = baseY;
					for(int j24 = 0; j24 < 16384; j24++) {
						NPC npc = npcArray[j24];
						if(npc != null) {
							for(int j29 = 0; j29 < 10; j29++) {
								npc.smallX[j29] -= i17;
								npc.smallY[j29] -= j21;
							}
							npc.x -= i17 * 128;
							npc.y -= j21 * 128;
						}
					}
					for(int i27 = 0; i27 < maxPlayers; i27++) {
						Player player = playerArray[i27];
						if(player != null) {
							for(int i31 = 0; i31 < 10; i31++) {
								player.smallX[i31] -= i17;
								player.smallY[i31] -= j21;
							}
							player.x -= i17 * 128;
							player.y -= j21 * 128;
						}
					}
					aBoolean1080 = true;
					byte byte1 = 0;
					byte byte2 = 104;
					byte byte3 = 1;
					if(i17 < 0) {
						byte1 = 103;
						byte2 = -1;
						byte3 = -1;
					}
					byte byte4 = 0;
					byte byte5 = 104;
					byte byte6 = 1;
					if(j21 < 0) {
						byte4 = 103;
						byte5 = -1;
						byte6 = -1;
					}
					for(int k33 = byte1; k33 != byte2; k33 += byte3) {
						for(int l33 = byte4; l33 != byte5; l33 += byte6) {
							int i34 = k33 + i17;
							int j34 = l33 + j21;
							for(int k34 = 0; k34 < 4; k34++)
								if(i34 >= 0 && j34 >= 0 && i34 < 104 && j34 < 104)
									groundArray[k34][k33][l33] = groundArray[k34][i34][j34];
								else
									groundArray[k34][k33][l33] = null;
						}
					}
					for(Class30_Sub1 class30_sub1_1 = (Class30_Sub1)aClass19_1179.reverseGetFirst(); class30_sub1_1 != null; class30_sub1_1 = (Class30_Sub1)aClass19_1179.reverseGetNext()) {
						class30_sub1_1.anInt1297 -= i17;
						class30_sub1_1.anInt1298 -= j21;
						if(class30_sub1_1.anInt1297 < 0 || class30_sub1_1.anInt1298 < 0 || class30_sub1_1.anInt1297 >= 104 || class30_sub1_1.anInt1298 >= 104)
							class30_sub1_1.unlink();
					}
					if(destX != 0) {
						destX -= i17;
						destY -= j21;
					}
					aBoolean1160 = false;
					pktType = -1;
					return true;
					
				case 208:
					int i3 = inStream.method437();
					if(i3 >= 0)
						method60(i3);
					walkableInterfaceID = i3;
					pktType = -1;
					return true;
					
				case 99:
					minimapCover = inStream.readUnsignedByte();
					pktType = -1;
					return true;
					
				case 75:
					int j3 = inStream.method436();
					int j11 = inStream.method436();
					RSInterface.interfaceCache[j11].anInt233 = 2;
					RSInterface.interfaceCache[j11].mediaID = j3;
					pktType = -1;
					return true;
					
				case 114:
					systemUpdatetime = inStream.method434() * 30;
					pktType = -1;
					return true;
					
				case 60:
					anInt1269 = inStream.readUnsignedByte();
					anInt1268 = inStream.method427();
					while(inStream.currentOffset < pktSize) {
						int k3 = inStream.readUnsignedByte();
						method137(inStream, k3);
					}
					pktType = -1;
					return true;
					
				case 35:
					int l3 = inStream.readUnsignedByte();
					int k11 = inStream.readUnsignedByte();
					int j17 = inStream.readUnsignedByte();
					int k21 = inStream.readUnsignedByte();
					aBooleanArray876[l3] = true;
					anIntArray873[l3] = k11;
					anIntArray1203[l3] = j17;
					anIntArray928[l3] = k21;
					anIntArray1030[l3] = 0;
					pktType = -1;
					return true;
					
				case 174:
					int i4 = inStream.readUnsignedWord();
					int l11 = inStream.readUnsignedByte();
					int k17 = inStream.readUnsignedWord();
					musicEnabled = true;
					if(aBoolean848 && !lowMem && anInt1062 < 50) {
						anIntArray1207[anInt1062] = i4;
						anIntArray1241[anInt1062] = l11;
						anIntArray1250[anInt1062] = k17 + Sounds.anIntArray326[i4];
						anInt1062++;
					//System.out.println("Packet 174");
				//		nextSong = currentSong;
				//	onDemandFetcher.method558(2, nextSong);
					}
					pktType = -1;
					return true;
					
				case 104:
					int j4 = inStream.method427();
					int i12 = inStream.method426();
					String s6 = inStream.readString();
					if(j4 >= 1 && j4 <= 5) {
						if(s6.equalsIgnoreCase("null"))
							s6 = null;
						atPlayerActions[j4 - 1] = s6;
						atPlayerArray[j4 - 1] = i12 == 0;
					}
					pktType = -1;
					return true;
					
				case 78:
					destX = 0;
					pktType = -1;
					return true;
					
				case 253:
					String s = inStream.readString();
					if(s.toLowerCase().contains("oh dear you are dead!")) {
						if(orbToggle[1]) {
							turnOffPrayers();
							turnOffCurses();
							orbToggle[1] = false;
						}
					}
					if(s.toLowerCase().contains("@autocastoff")) {
						setAutoCastOff();
						break;
					}
					if(s.toLowerCase().contains("you have been poisoned")) {
						orbToggle[0] = true;
					}
					if(s.toLowerCase().contains("you are no longer poisoned") || s.toLowerCase().contains("your antipoison potion") || s.toLowerCase().contains("antidote")) {
						orbToggle[0] = false;
					}
					if(s.endsWith(":tradereq:")) {
						String s3 = s.substring(0, s.indexOf(":"));
						long l17 = TextClass.longForName(s3);
						boolean flag2 = false;
						for(int j27 = 0; j27 < ignoreCount; j27++) {
							if(ignoreListAsLongs[j27] != l17)
								continue;
							flag2 = true;
							
						}
						if(!flag2 && anInt1251 == 0)
							pushMessage("wishes to trade with you.", 4, s3);
					} else if (s.endsWith(":ooo33:")) {
						String s4 = s.substring(0, s.indexOf(":"));
						TextClass.longForName(s4);
						pushMessage("Clan: ", 8, s4);	
					} else if(s.endsWith("#url#")) {
						String link = s.substring(0, s.indexOf("#"));
						pushMessage("Join us at: ", 9, link);
					} else if(s.startsWith("#010#")) {
						shoutYell = s.substring(5);
						nameYell = "";
						if(s.contains("@cr1@"))
							nameYell = "1";
						if(s.contains("@cr2@"))
							nameYell = "2";
						if(s.contains("@cr3@"))
							nameYell = "3";
						if(s.contains("@cr4@"))
							nameYell = "4";
						if(s.contains("@cr5@"))
							nameYell = "5";
						if(s.contains("@cr6@"))
							nameYell = "6";
						pushMessage(shoutYell, 16, nameYell);
					} else if(s.contains("#link-") && s.endsWith("#")) {
						int firstIndex = s.indexOf('-');
						int lastIndex = s.lastIndexOf('#');
						String link = s.substring(firstIndex + 1,lastIndex);
						pushMessage(s.substring(0,s.indexOf('#')), 9, link);
					} else if(s.endsWith(":duelreq:")) {
						String s4 = s.substring(0, s.indexOf(":"));
						long l18 = TextClass.longForName(s4);
						boolean flag3 = false;
						for(int k27 = 0; k27 < ignoreCount; k27++) {
							if(ignoreListAsLongs[k27] != l18)
								continue;
							flag3 = true;
							
						}
						if(!flag3 && anInt1251 == 0)
							pushMessage("wishes to duel with you.", 8, s4);
					} else if(s.endsWith(":chalreq:")) {
						String s5 = s.substring(0, s.indexOf(":"));
						long l19 = TextClass.longForName(s5);
						boolean flag4 = false;
						for(int l27 = 0; l27 < ignoreCount; l27++) {
							if(ignoreListAsLongs[l27] != l19)
								continue;
							flag4 = true;
							
						}
						if(!flag4 && anInt1251 == 0) {
							String s8 = s.substring(s.indexOf(":") + 1, s.length() - 9);
							pushMessage(s8, 8, s5);
						}
					} else {
						pushMessage(s, 0, "");
					}
					pktType = -1;
					return true;
					
				case 1:
					for(int k4 = 0; k4 < playerArray.length; k4++)
						if(playerArray[k4] != null)
							playerArray[k4].anim = -1;
					for(int j12 = 0; j12 < npcArray.length; j12++)
						if(npcArray[j12] != null)
							npcArray[j12].anim = -1;
					pktType = -1;
					return true;
					
				case 50:
					long l4 = inStream.readQWord();
					int i18 = inStream.readUnsignedByte();
					String s7 = TextClass.fixName(TextClass.nameForLong(l4));
					for(int k24 = 0; k24 < friendsCount; k24++) {
						if(l4 != friendsListAsLongs[k24])
							continue;
						if(friendsNodeIDs[k24] != i18) {
							friendsNodeIDs[k24] = i18;
							if(i18 >= 2) {
								pushMessage(s7 + " has logged in.", 5, "");
							}
							if(i18 <= 1) {
								pushMessage(s7 + " has logged out.", 5, "");
							}
						}
						s7 = null;
						
					}
					if(s7 != null && friendsCount < 200) {
						friendsListAsLongs[friendsCount] = l4;
						friendsList[friendsCount] = s7;
						friendsNodeIDs[friendsCount] = i18;
						friendsCount++;
					}
					for(boolean flag6 = false; !flag6;) {
						flag6 = true;
						for(int k29 = 0; k29 < friendsCount - 1; k29++)
							if(friendsNodeIDs[k29] != nodeID && friendsNodeIDs[k29 + 1] == nodeID || friendsNodeIDs[k29] == 0 && friendsNodeIDs[k29 + 1] != 0) {
								int j31 = friendsNodeIDs[k29];
								friendsNodeIDs[k29] = friendsNodeIDs[k29 + 1];
								friendsNodeIDs[k29 + 1] = j31;
								String s10 = friendsList[k29];
								friendsList[k29] = friendsList[k29 + 1];
								friendsList[k29 + 1] = s10;
								long l32 = friendsListAsLongs[k29];
								friendsListAsLongs[k29] = friendsListAsLongs[k29 + 1];
								friendsListAsLongs[k29 + 1] = l32;
								flag6 = false;
							}
					}
					pktType = -1;
					return true;
					
				case 110:
					if(tabID == 12) {
					}
					energy = inStream.readUnsignedByte();
					pktType = -1;
					return true;
					
				case 254:
					hintType = inStream.readUnsignedByte();
					if(hintType == 1)
						hintArrowNPCID = inStream.readUnsignedWord();
					if(hintType >= 2 && hintType <= 6) {
						if(hintType == 2) {
							anInt937 = 64;
							anInt938 = 64;
						}
						if(hintType == 3) {
							anInt937 = 0;
							anInt938 = 64;
						}
						if(hintType == 4) {
							anInt937 = 128;
							anInt938 = 64;
						}
						if(hintType == 5) {
							anInt937 = 64;
							anInt938 = 0;
						}
						if(hintType == 6) {
							anInt937 = 64;
							anInt938 = 128;
						}
						hintType = 2;
						hintArrowX = inStream.readUnsignedWord();
						hintArrowY = inStream.readUnsignedWord();
						anInt936 = inStream.readUnsignedByte();
					}
					if(hintType == 10)
						hintArrowPlayerID = inStream.readUnsignedWord();
					pktType = -1;
					return true;
					
				case 248:
					int i5 = inStream.method435();
					int k12 = inStream.readUnsignedWord();
					if(backDialogID != -1) {
						backDialogID = -1;
						inputTaken = true;
					}
					if(inputDialogState != 0) {
						inputDialogState = 0;
						inputTaken = true;
					}
					openInterfaceID = i5;
					invOverlayInterfaceID = k12;
				tabAreaAltered = true;
					aBoolean1149 = false;
					pktType = -1;
					return true;
					
				case 79:
					int j5 = inStream.method434();
					int l12 = inStream.method435();
					RSInterface class9_3 = RSInterface.interfaceCache[j5];
					if(class9_3 != null && class9_3.type == 0) {
						if(l12 < 0)
							l12 = 0;
						if(l12 > class9_3.scrollMax - class9_3.height)
							l12 = class9_3.scrollMax - class9_3.height;
						class9_3.scrollPosition = l12;
					}
					pktType = -1;
					return true;
					
				case 68:
					for(int k5 = 0; k5 < variousSettings.length; k5++)
						if(variousSettings[k5] != anIntArray1045[k5]) {
							variousSettings[k5] = anIntArray1045[k5];
							method33(k5);
						}
					pktType = -1;
					return true;
					
				case 196:
					long longForName = inStream.readQWord();
					int j18 = inStream.readDWord();
					int rights = inStream.readUnsignedByte();
					boolean flag5 = false;
					if(rights <= 1) {
						for(int l29 = 0; l29 < ignoreCount; l29++) {
							if(ignoreListAsLongs[l29] != longForName)
								continue;
							flag5 = true;
							
						}
					}
					if(!flag5 && anInt1251 == 0)
						try {
							anIntArray1240[anInt1169] = j18;
							anInt1169 = (anInt1169 + 1) % 100;
							String s9 = TextInput.method525(pktSize - 13, inStream);
							if (rights == 1) {
								pushMessage(s9, 7, "@cr1@"+TextClass.fixName(TextClass.nameForLong(longForName)));
							} else if (rights == 2) {
								pushMessage(s9, 7, "@cr2@"+TextClass.fixName(TextClass.nameForLong(longForName)));
							} else if (rights == 3) {
								pushMessage(s9, 7, "@cr3@"+TextClass.fixName(TextClass.nameForLong(longForName)));
							} else if (rights == 4) {
								pushMessage(s9, 7, "@cr4@"+TextClass.fixName(TextClass.nameForLong(longForName)));
							} else if (rights == 5) {
								pushMessage(s9, 7, "@cr5@"+TextClass.fixName(TextClass.nameForLong(longForName)));
							} else if (rights == 6) {
								pushMessage(s9, 7, "@cr6@"+TextClass.fixName(TextClass.nameForLong(longForName)));
							} else {
								pushMessage(s9, 3, TextClass.fixName(TextClass
									.nameForLong(longForName)));
							}
						} catch(Exception exception1) {
							signlink.reporterror("cde1");
						}
					pktType = -1;
					return true;
					
				case 85:
					anInt1269 = inStream.method427();
					anInt1268 = inStream.method427();
					pktType = -1;
					return true;
					
				case 24:
					tabIconFlash = inStream.method428();
					if(tabIconFlash == tabID) {
						if(tabIconFlash == 3)
							tabID = 1;
						else
							tabID = 3;
					}
					pktType = -1;
					return true;
					
				case 246:
					int frameId = inStream.method434();
					int zoom = inStream.readUnsignedWord();
					int itemId = inStream.readUnsignedWord();
					if(itemId == 65535) {
						RSInterface.interfaceCache[frameId].anInt233 = 0;
						pktType = -1;
						return true;
					} else {
						ItemDef itemDef = ItemDef.forID(itemId);
						RSInterface.interfaceCache[frameId].anInt233 = 4;
						RSInterface.interfaceCache[frameId].mediaID = itemId;
						RSInterface.interfaceCache[frameId].modelRotation1 = itemDef.modelRotation1;
						RSInterface.interfaceCache[frameId].modelRotation2 = itemDef.modelRotation2;
						RSInterface.interfaceCache[frameId].modelZoom = (itemDef.modelZoom * 100) / zoom;
						pktType = -1;
						return true;
					}
					
				case 171:
					boolean flag1 = inStream.readUnsignedByte() == 1;
					int j13 = inStream.readUnsignedWord();
					RSInterface.interfaceCache[j13].isMouseoverTriggered = flag1;
					pktType = -1;
					return true;
					
				case 142:
					int j6 = inStream.method434();
					method60(j6);
					if(backDialogID != -1) {
						backDialogID = -1;
						inputTaken = true;
					}
					if(inputDialogState != 0) {
						inputDialogState = 0;
						inputTaken = true;
					}
					invOverlayInterfaceID = j6;
				tabAreaAltered = true;
					openInterfaceID = -1;
					aBoolean1149 = false;
					pktType = -1;
					return true;
					
				case 126:
					newText = inStream.readString();
					String text = newText;
					editedTextFrame = inStream.method435();
					int frame = editedTextFrame;
					if (text.startsWith("www.")) {
						launchURL(text);
						pktType = -1;
						return true;
					}
					if(text.startsWith("full"))
					{
						String[] args = text.split(" ");
						int id1 = Integer.parseInt(args[1]);
						int id2 = Integer.parseInt(args[2]);
						fullscreenInterfaceID = id1;
						openInterfaceID = id2;
						pktType = -1;
						return true;
					}
					updateStrings(text, frame);
					sendFrame126(text, frame);
					if (frame >= 18144 && frame <= 18244) {
						clanList[frame - 18144] = text;
					}
					pktType = -1;
					return true;
					
				case 206:
					publicChatMode = inStream.readUnsignedByte();
					privateChatMode = inStream.readUnsignedByte();
					tradeMode = inStream.readUnsignedByte();
				inputTaken = true;
					pktType = -1;
					return true;
					
				case 240:
					if(tabID == 12) {
					}
					weight = inStream.readSignedWord();
					pktType = -1;
					return true;
					
				case 8:
					int k6 = inStream.method436();
					int l13 = inStream.readUnsignedWord();
					RSInterface.interfaceCache[k6].anInt233 = 1;
					RSInterface.interfaceCache[k6].mediaID = l13;
					pktType = -1;
					return true;
					
				case 122:
					int l6 = inStream.method436();
					int i14 = inStream.method436();
					int i19 = i14 >> 10 & 0x1f;
					int i22 = i14 >> 5 & 0x1f;
					int l24 = i14 & 0x1f;
					RSInterface.interfaceCache[l6].textColor = (i19 << 19) + (i22 << 11) + (l24 << 3);
					pktType = -1;
					return true;
					
				case 53:
				int i7 = inStream.readUnsignedWord();
					RSInterface class9_1 = RSInterface.interfaceCache[i7];
					int j19 = inStream.readUnsignedWord();
					for(int j22 = 0; j22 < j19; j22++) {
						int i25 = inStream.readUnsignedByte();
						if(i25 == 255)
							i25 = inStream.method440();
						class9_1.inv[j22] = inStream.method436();
						class9_1.invStackSizes[j22] = i25;
					}
					for(int j25 = j19; j25 < class9_1.inv.length; j25++) {
						class9_1.inv[j25] = 0;
						class9_1.invStackSizes[j25] = 0;
					}
					pktType = -1;
					return true;
					
				case 230:
					int j7 = inStream.method435();
					int j14 = inStream.readUnsignedWord();
					int k19 = inStream.readUnsignedWord();
					int k22 = inStream.method436();
					RSInterface.interfaceCache[j14].modelRotation1 = k19;
					RSInterface.interfaceCache[j14].modelRotation2 = k22;
					RSInterface.interfaceCache[j14].modelZoom = j7;
					pktType = -1;
					return true;
					
				case 221:
					anInt900 = inStream.readUnsignedByte();
				pktType = -1;
					return true;
					
				case 177:
					aBoolean1160 = true;
					anInt995 = inStream.readUnsignedByte();
					anInt996 = inStream.readUnsignedByte();
					anInt997 = inStream.readUnsignedWord();
					anInt998 = inStream.readUnsignedByte();
					anInt999 = inStream.readUnsignedByte();
					if(anInt999 >= 100) {
						int k7 = anInt995 * 128 + 64;
						int k14 = anInt996 * 128 + 64;
						int i20 = method42(plane, k14, k7) - anInt997;
						int l22 = k7 - xCameraPos;
						int k25 = i20 - zCameraPos;
						int j28 = k14 - yCameraPos;
						int i30 = (int)Math.sqrt(l22 * l22 + j28 * j28);
						yCameraCurve = (int)(Math.atan2(k25, i30) * 325.94900000000001D) & 0x7ff;
						xCameraCurve = (int)(Math.atan2(l22, j28) * -325.94900000000001D) & 0x7ff;
						if(yCameraCurve < 128)
							yCameraCurve = 128;
						if(yCameraCurve > 383)
							yCameraCurve = 383;
					}
					pktType = -1;
					return true;
					
				case 249:
					anInt1046 = inStream.method426();
					unknownInt10 = inStream.method436();
					pktType = -1;
					return true;
					
				case 65:
					updateNPCs(inStream, pktSize);
					pktType = -1;
					return true;
					
				case 27:
					messagePromptRaised = false;
					inputDialogState = 1;
					amountOrNameInput = "";
					inputTaken = true;
					pktType = -1;
					return true;
					
				case 187:
					messagePromptRaised = false;
					inputDialogState = 2;
					amountOrNameInput = "";
					inputTaken = true;
					pktType = -1;
					return true;
					
				case 97:
					int l7 = inStream.readUnsignedWord();
					method60(l7);
					if(invOverlayInterfaceID != -1) {
						invOverlayInterfaceID = -1;
						tabAreaAltered = true;
					}
					if(backDialogID != -1) {
						backDialogID = -1;
						inputTaken = true;
					}
					if(inputDialogState != 0) {
						inputDialogState = 0;
						inputTaken = true;
					}
					openInterfaceID = l7;
					aBoolean1149 = false;
					pktType = -1;
					return true;
					
				case 218:
					int i8 = inStream.method438();
					dialogID = i8;
					inputTaken = true;
					pktType = -1;
					return true;
					
				case 87:
					int j8 = inStream.method434();
					int l14 = inStream.method439();
					anIntArray1045[j8] = l14;
					if(variousSettings[j8] != l14) {
						variousSettings[j8] = l14;
						method33(j8);
						if(dialogID != -1)
							inputTaken = true;
					}
					pktType = -1;
					return true;
					
				case 36:
					int k8 = inStream.method434();
					byte byte0 = inStream.readSignedByte();
					anIntArray1045[k8] = byte0;
					if(k8 == 152) {//running
						if(byte0 == 1)
							orbToggle[2] = true;
						else
							orbToggle[2] = false;
					}
					if(variousSettings[k8] != byte0) {
						variousSettings[k8] = byte0;
						method33(k8);
						if(dialogID != -1)
							inputTaken = true;
					}
					pktType = -1;
					return true;
					
				case 61:
					displayOverLayIcon = inStream.readUnsignedByte();
					pktType = -1;
					return true;
					
				case 200:
					int l8 = inStream.readUnsignedWord();
					int i15 = inStream.readSignedWord();
					RSInterface class9_4 = RSInterface.interfaceCache[l8];
					class9_4.anInt257 = i15;
					if(i15 == -1) {
						class9_4.anInt246 = 0;
						class9_4.anInt208 = 0;
					}
					pktType = -1;
					return true;
					
				case 219:
					if(invOverlayInterfaceID != -1) {
						invOverlayInterfaceID = -1;
						tabAreaAltered = true;
					}
					if(backDialogID != -1) {
						backDialogID = -1;
						inputTaken = true;
					}
					if(inputDialogState != 0) {
						inputDialogState = 0;
						inputTaken = true;
					}
					openInterfaceID = -1;
					aBoolean1149 = false;
					pktType = -1;
					return true;
					
				case 34:
					needDrawTabArea = true;
					int i9 = inStream.readUnsignedWord();
					RSInterface class9_2 = RSInterface.interfaceCache[i9];
					while (inStream.currentOffset < pktSize) {
						int j20 = inStream.method422();
						int i23 = inStream.readUnsignedWord();
						int l25 = inStream.readUnsignedByte();
						if (l25 == 255)
							l25 = inStream.readDWord();
						if (j20 >= 0 && j20 < class9_2.inv.length) {
							class9_2.inv[j20] = i23;
							class9_2.invStackSizes[j20] = l25;
						}
					}
					pktType = -1;
					return true;
					
					
				case 4:
				case 44:
				case 84:
				case 101:
				case 105:
				case 117:
				case 147:
				case 151:
				case 156:
				case 160:
				case 215:
					method137(inStream, pktType);
					pktType = -1;
					return true;
					
				case 106:
					tabID = inStream.method427();
				tabAreaAltered = true;
					pktType = -1;
					return true;
					
				case 164:
					int j9 = inStream.method434();
					method60(j9);
					if(invOverlayInterfaceID != -1) {
						invOverlayInterfaceID = -1;
						tabAreaAltered = true;
					}
					backDialogID = j9;
					inputTaken = true;
					openInterfaceID = -1;
					aBoolean1149 = false;
					pktType = -1;
					return true;
				case 123:
					pktType = -1;
					return true;
			}
			signlink.reporterror("T1 - " + pktType + "," + pktSize + " - " + anInt842 + "," + anInt843);
			//resetLogout();
		} catch(IOException _ex) {
			dropClient();
		} catch(Exception exception) {
			String s2 = "T2 - " + pktType + "," + anInt842 + "," + anInt843 + " - " + pktSize + "," + (baseX + myPlayer.smallX[0]) + "," + (baseY + myPlayer.smallY[0]) + " - ";
			for(int j15 = 0; j15 < pktSize && j15 < 50; j15++)
				s2 = s2 + inStream.buffer[j15] + ",";
			signlink.reporterror(s2);
			if(pktType == 126) {
				System.out.println("Text editing packet send, but cant be received");
				System.out.println("ID:"+editedTextFrame+", text:"+newText+"");
			}
		}
		pktType = -1;
		return true;
	}

	private void method146() {
		anInt1265++;
		method47(true);
		method26(true);
		method47(false);
		method26(false);
		method55();
		method104();
		if(!aBoolean1160) {
			int i = anInt1184;
			if(anInt984 / 256 > i)
				i = anInt984 / 256;
			if(aBooleanArray876[4] && anIntArray1203[4] + 128 > i)
				i = anIntArray1203[4] + 128;
			int k = compassRotation + anInt896 & 0x7ff;
			setCameraPos(600 + i * (toggleFullscreen ? 2 : 3), i, anInt1014, method42(plane, myPlayer.y, myPlayer.x) - 50, k, anInt1015);
		}
		int j;
		if(!aBoolean1160)
			j = method120();
		else
			j = method121();
		int l = xCameraPos;
		int i1 = zCameraPos;
		int j1 = yCameraPos;
		int k1 = yCameraCurve;
		int l1 = xCameraCurve;
		for(int i2 = 0; i2 < 5; i2++)
			if(aBooleanArray876[i2]) {
				int j2 = (int)((Math.random() * (double)(anIntArray873[i2] * 2 + 1) - (double)anIntArray873[i2]) + Math.sin((double)anIntArray1030[i2] * ((double)anIntArray928[i2] / 100D)) * (double)anIntArray1203[i2]);
				if(i2 == 0)
					xCameraPos += j2;
				if(i2 == 1)
					zCameraPos += j2;
				if(i2 == 2)
					yCameraPos += j2;
				if(i2 == 3)
					xCameraCurve = xCameraCurve + j2 & 0x7ff;
				if(i2 == 4) {
					yCameraCurve += j2;
					if(yCameraCurve < 128)
						yCameraCurve = 128;
					if(yCameraCurve > 383)
						yCameraCurve = 383;
				}
			}
		int k2 = Texture.anInt1481;
		Model.aBoolean1684 = true;
		Model.anInt1687 = 0;
		Model.anInt1685 = super.mouseX - (toggleFullscreen ? 0 : 4);
		Model.anInt1686 = super.mouseY - (toggleFullscreen ? 0 : 4);
		DrawingArea.setAllPixelsToZero();
		DrawingArea.drawPixels(getScreenHeight(), 0, 0, 0xC8C0A8, getScreenWidth());
		worldController.method313(xCameraPos, yCameraPos, xCameraCurve, zCameraPos, j, yCameraCurve);
		worldController.clearObj5Cache();
		updateEntities();
		drawHeadIcon();
		method37(k2);
		draw3dScreen();
		if(!toggleFullscreen) {
				drawCoinParts();
				if(coinToggle){
			int cash;
			String Cash = RSInterface.interfaceCache[8135].message;
			cash = Integer.parseInt(Cash);
			coinPart.drawSprite(400+11+32+4, 87);
			smallFont.method382(getMoneyOrbColor(cash), 485, RSInterface.interfaceCache[8134].message+"", 102, true);
		}
		}
		drawGameFrameOnFullScreen();
		main3DArea.drawGraphics(toggleFullscreen ? 0 : 4, super.graphics, toggleFullscreen ? 0 : 4);
		xCameraPos = l;
		zCameraPos = i1;
		yCameraPos = j1;
		yCameraCurve = k1;
		xCameraCurve = l1;
	}

void sendPacket(int packet) {
		if (packet == 103) {
		stream.createFrame(103);
		stream.writeWordBigEndian(inputString.length() - 1);
		stream.writeString(inputString.substring(2));
		System.out.println(inputString);
		System.out.println(promptInput);
		inputString = "";
		promptInput = "";
		interfaceButtonAction = 0;
		}
	}
	
	
	private int cButtonHCPos;
public int interfaceButtonAction = 0;
private int[] anIntArray851;
	
		public int getMoneyOrbColor(int cashAmount){
		if(cashAmount >= 100000 && cashAmount <= 999999){
			return 0xffffff;
		} else {
			return 65280;
		}
	}

	public void clearTopInterfaces() {
		stream.createFrame(130);
		if (invOverlayInterfaceID != -1) {
			invOverlayInterfaceID = -1;
			aBoolean1149 = false;
			tabAreaAltered = true;
		}
		if (backDialogID != -1) {
			backDialogID = -1;
			inputTaken = true;
			aBoolean1149 = false;
		}
		openInterfaceID = -1;
		fullscreenInterfaceID = -1;
	}

	public float LP;
	private final int[] anIntArray969;
	
	public client() {
	LP = 0.0F;
	alertHandler = new AlertHandler(this);
		inventoryStones = new Sprite[6];
		tabSpace = ((getScreenWidth() - 765) >= 246*2);
		mapBack = new Background[5];
		tabIDHover = -1;
		tabAreaSprite = new Sprite[3];
		linkInText = "";
		chatArea = new Sprite[4];
		tabArea = new Sprite[2];
		XPcounterSprite = new Sprite[5];
		fullscreenInterfaceID = -1;
		chatRights = new int[500];
		chatTypeView = 0;
		clanChatMode = 0;
		cButtonHPos = -1;
		cButtonHCPos = -1;
		cButtonCPos = 0;
		WhatWorldIsWhatIp();
		//handleStupidWorldSwitch();
				world = 1;
				spec =" Regular World";
				serverip = "127.0.0.1";
				port = 43594;
				signlink.setuid();
		anIntArrayArray825 = new int[104][104];
		friendsNodeIDs = new int[200];
		groundArray = new NodeList[4][104][104];
		aBoolean831 = false;
		aStream_834 = new Stream(new byte[5000]);
		npcArray = new NPC[16384];
		npcIndices = new int[16384];
		anIntArray840 = new int[1000];
		aStream_847 = Stream.create();
		aBoolean848 = true;
		openInterfaceID = -1;
		currentExp = new int[Skills.skillsCount];
		aBoolean872 = false;
		anIntArray873 = new int[5];
		anInt874 = -1;
		aBooleanArray876 = new boolean[5];
		drawFlames = false;
		reportAbuseInput = "";
		unknownInt10 = -1;
		contextMenuOpen = false;
		inputString = "";
		maxPlayers = 2048;
		myPlayerIndex = 2047;
		playerArray = new Player[40000];
		playerIndices = new int[maxPlayers];
		anIntArray894 = new int[maxPlayers];
		aStreamArray895s = new Stream[maxPlayers];
		anInt897 = 1;
		anIntArrayArray901 = new int[104][104];
		anInt902 = 0x766654;
		aByteArray912 = new byte[16384];
		currentStats = new int[Skills.skillsCount];
		ignoreListAsLongs = new long[100];
		loadingError = false;
		anInt927 = 0x332d25;
		anIntArray928 = new int[5];
		anIntArrayArray929 = new int[104][104];
		chatTypes = new int[500];
		chatNames = new String[500];
		chatMessages = new String[500];
		chatButtons = new Sprite[4];
		newChatButtons = new Sprite[4];
		sideIcons = new Sprite[31];
		aBoolean954 = true;
		friendsListAsLongs = new long[200];
		currentSong = -1;
		drawingFlames = false;
		spriteDrawX = -1;
		spriteDrawY = -1;
		compassPixelCutLeft = new int[33];
		anIntArray969 = new int[256];
		decompressors = new Decompressor[5];
		variousSettings = new int[2000];
		aBoolean972 = false;
		anInt975 = 50;
		anIntArray976 = new int[anInt975];
		anIntArray977 = new int[anInt975];
		anIntArray978 = new int[anInt975];
		anIntArray979 = new int[anInt975];
		anIntArray980 = new int[anInt975];
		anIntArray981 = new int[anInt975];
		anIntArray982 = new int[anInt975];
		aStringArray983 = new String[anInt975];
		anInt985 = -1;
		hitMarkBacks = new Sprite[6][2][3];//6 types,2 shades,3 parts
		hitMarkIcons = new Sprite[20];
		anIntArray990 = new int[6];
		aBoolean994 = false;
		anInt1002 = 0x23201b;
		amountOrNameInput = "";
		aClass19_1013 = new NodeList();
		aBoolean1017 = false;
		walkableInterfaceID = -1;
		anIntArray1030 = new int[5];
		aBoolean1031 = false;
		mapFunctions = new Sprite[100];
		dialogID = -1;
		maxStats = new int[Skills.skillsCount];
		anIntArray1045 = new int[2000];
		aBoolean1047 = true;
		mapImagePixelCutLeft = new int[miniMapHeight - mapPaddingFromTop];
		mapImagePixelCutRight = new int[miniMapHeight - mapPaddingFromTop];
		tabIconFlash = -1;
		aClass19_1056 = new NodeList();
		compassPixelCutRight = new int[33];
		aClass9_1059 = new RSInterface();
		mapScenes = new Background[100];
		barFillColor = 0x4d4233;
		anIntArray1065 = new int[7];
		mapFunctionX = new int[1000];
		mapFunctionY = new int[1000];
		aBoolean1080 = false;
		friendsList = new String[200];
		inStream = Stream.create();
		expectedCRCs = new int[9];
		menuActionCmd2 = new int[500];
		menuActionCmd3 = new int[500];
		menuActionID = new int[500];
		menuActionCmd1 = new int[500];
		headIcons = new Sprite[20];
		bountyHunter = new Sprite[10];
		cursesIcons = new Sprite[20];
		skullIcons = new Sprite[20];
		headIconsHint = new Sprite[20];
		tabAreaAltered = false;
		aString1121 = "";
		atPlayerActions = new String[5];
		atPlayerArray = new boolean[5];
		anIntArrayArrayArray1129 = new int[4][13][13];
		anInt1132 = 2;
		currentMapFunctions = new Sprite[1000];
		aBoolean1141 = false;
		aBoolean1149 = false;
		crosses = new Sprite[8];
		musicEnabled = true;
		needDrawTabArea = false;
		loggedIn = false;
		canMute = false;
		canBan = false;
		aBoolean1159 = false;
		aBoolean1160 = false;
		mapAmtCounterZoom = 1;
		myUsername = "";
		myPassword = "";
		genericLoadingError = false;
		reportAbuseInterfaceID = -1;
		aClass19_1179 = new NodeList();
		anInt1184 = 128;
		invOverlayInterfaceID = -1;
		stream = Stream.create();
		menuActionName = new String[500];
		anIntArray1203 = new int[5];
		anIntArray1207 = new int[50];
		mapAmtCounterRotation = 2;
		chatScrollTotalLength = 78;
		promptInput = "";
		modIcons = new Sprite[7];
		tabID = 3;
		inputTaken = false;
		songChanging = true;
		aClass11Array1230 = new Class11[4];
		aBoolean1233 = false;
		anIntArray1240 = new int[100];
		anIntArray1241 = new int[50];
		aBoolean1242 = false;
		anIntArray1250 = new int[50];
		rsAlreadyLoaded = false;
		welcomeScreenRaised = false;
		messagePromptRaised = false;
		loginMessage1 = "";
		loginMessage2 = "Enter your username & password.";
		backDialogID = -1;
		anInt1279 = 2;
		bigX = new int[4000];
		bigY = new int[4000];
		anInt1289 = -1;
	}

	private Sprite HPBarFull;
	private Sprite HPBarEmpty;
		private boolean[] screenHover = {
	false,false, false, false	
	};
	private Sprite loginBox;
	private Sprite loginBoxHover;
	private Sprite loginHover;
	private Sprite loginHoverWorld;
	public int rights;
	private Background titleBox;
	private Sprite boxHover;
	private Background titleButton;
	public String name;
	public String message;
	public String clanname;
	private final int[] chatRights;
	public int chatTypeView;
	public int clanChatMode;
	public int duelMode;
	/* Declare custom sprites */
	public Sprite backgroundFix;
	public Sprite worldSwitcherBg;
	public Sprite gameframeSwitcherBg;
	public Sprite checkMark;
	public Sprite optionsHover;
	private Sprite loadingBarFull;
	private Sprite loadingBarEmpty;
	private Sprite emptyOrb;
	private Sprite emptyFOrb;
	private Sprite hoveredEmpty;
	private Sprite hoveredFEmpty;
	private Sprite runIcon1;
	private Sprite runIcon2;
	private Sprite runOrb1;
	private Sprite runOrb2;
	private Sprite hitPointsFill;
	private Sprite hitPointsIcon;
	private Sprite prayerFill;
	private Sprite prayerIcon;
	private Sprite[] chatArea;
	private Sprite[] chatButtons;
	private Sprite[] newChatButtons;
	private Sprite[] tabArea;
	private Sprite mapArea;
	/**/
	private Sprite[] tabAreaSprite;
	private RSImageProducer leftFrame;
	private RSImageProducer topFrame;
	private RSImageProducer rightFrame;
	private int ignoreCount;
	private long aLong824;
	private int[][] anIntArrayArray825;
	private int[] friendsNodeIDs;
	private NodeList[][][] groundArray;
	private int[] anIntArray829;
	private volatile boolean aBoolean831;
	private Socket aSocket832;
	private int loginScreenState;
	private Stream aStream_834;
	private NPC[] npcArray;
	private int npcCount;
	private int[] npcIndices;
	private int anInt839;
	private int[] anIntArray840;
	private int anInt841;
	private int anInt842;
	private int anInt843;
	private String aString844;
	private int privateChatMode;
	private Stream aStream_847;
	private boolean aBoolean848;
	private static int anInt849;
	
	private int[] anIntArray852;
	private int[] anIntArray853;
	private static int anInt854;
	private int hintType;
	public static int openInterfaceID;
	private int xCameraPos;
	private int zCameraPos;
	private int yCameraPos;
	private int yCameraCurve;
	private int xCameraCurve;
	private final int[] currentExp;
	private Sprite mapFlag;
	private Sprite mapMarker;
	private boolean aBoolean872;
	private final int[] anIntArray873;
	private int anInt874;
	private final boolean[] aBooleanArray876;
	private int weight;
	private MouseDetection mouseDetection;
	private volatile boolean drawFlames;
	private String reportAbuseInput;
	private int unknownInt10;
	private boolean contextMenuOpen;
	private int hoverChildId;
	private String inputString;
	private final int maxPlayers;
	private final int myPlayerIndex;
	private Player[] playerArray;
	private int playerCount;
	private int[] playerIndices;
	private int anInt893;
	private int[] anIntArray894;
	private Stream[] aStreamArray895s;
	private int anInt896;
	private int anInt897;
	private int friendsCount;
	private int anInt900;
	private int[][] anIntArrayArray901;
	private final int anInt902;
	private byte[] aByteArray912;
	private int anInt913;
	private int crossX;
	private int crossY;
	private int crossIndex;
	private int crossType;
	private int plane;
	private final int[] currentStats;
	private static int anInt924;
	private final long[] ignoreListAsLongs;
	private boolean loadingError;
	private final int anInt927;
	private final int[] anIntArray928;
	private int[][] anIntArrayArray929;
	private Sprite aClass30_Sub2_Sub1_Sub1_931;
	private Sprite aClass30_Sub2_Sub1_Sub1_932;
	private int hintArrowPlayerID;
	private int hintArrowX;
	private int hintArrowY;
	private int anInt936;
	private int anInt937;
	private int anInt938;
	private static int anInt940;
	private final int[] chatTypes;
	private final String[] chatNames;
	private final String[] chatMessages;
	private int anInt945;
	private WorldController worldController;
	private Sprite[] sideIcons;
	private int menuScreenArea;
	private int menuOffsetX;
	private int menuOffsetY;
	private int menuWidth;
	private int menuHeight;
	private long aLong953;
	private boolean aBoolean954;
	private long[] friendsListAsLongs;
	private String[] clanList = new String[100];
	private int currentSong;
	private static int nodeID = 10;
	static int portOff;
	static boolean clientData;
	private static boolean isMembers = true;
	private static boolean lowMem;
	private volatile boolean drawingFlames;
	private int spriteDrawX;
	private int spriteDrawY;
	private final int[] anIntArray965 = {
		0xffff00, 0xff0000, 65280, 65535, 0xff00ff, 0xffffff
	};
	private Background aBackground_966;
	private Background aBackground_967;
	private final int[] compassPixelCutLeft;
	final Decompressor[] decompressors;
	public int variousSettings[];
	private boolean aBoolean972;
	private final int anInt975;
	private final int[] anIntArray976;
	private final int[] anIntArray977;
	private final int[] anIntArray978;
	private final int[] anIntArray979;
	private final int[] anIntArray980;
	private final int[] anIntArray981;
	private final int[] anIntArray982;
	private final String[] aStringArray983;
	private int anInt984;
	private int anInt985;
	private static int anInt986;
	private Sprite[][][] hitMarkBacks;
	private Sprite[] hitMarkIcons;
	private int anInt988;
	private int anInt989;
	private final int[] anIntArray990;
	private static boolean aBoolean993;
	private final boolean aBoolean994;
	private int anInt995;
	private int anInt996;
	private int anInt997;
	private int anInt998;
	private int anInt999;
	private ISAACRandomGen encryption;
	private Sprite mapEdge;
	private Sprite multiOverlay;
	private final int anInt1002;
static final int[][] anIntArrayArray1003 = {
{ 6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433, 2983,
54193 },
{ 8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153,
56621, 4783, 1341, 16578, 35003, 25239 },
{ 25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094,
10153, 56621, 4783, 1341, 16578, 35003 },
{ 4626, 11146, 6439, 12, 4758, 10270 },
{ 4550, 20165, 43678, 16895 ,28416 ,12231 ,947 ,60359, 32433 } };
	private int tabIDHover;
	private String amountOrNameInput;
	private static int anInt1005;
	private int daysSinceLastLogin;
	private int pktSize;
	private int pktType;
	private int anInt1009;
	private int anInt1010;
	private int anInt1011;
	private NodeList aClass19_1013;
	private int anInt1014;
	private int anInt1015;
	private int anInt1016;
	private boolean aBoolean1017;
	private int walkableInterfaceID;
	private static final int[] anIntArray1019;
	private int minimapCover;
	private int anInt1022;
	private int loadingStage;
	private Sprite[] scrollBar = new Sprite[8];
	private Sprite[] scrollArrow = new Sprite[4];
	private Sprite rightFrameSprite;
	private int lastHoverChildId;
	private Background backBase1;
	private Background backBase2;
	private Background backHmid1;
	private final int[] anIntArray1030;
	private boolean aBoolean1031;
	private Sprite[] mapFunctions;
	private int baseX;
	private int baseY;
	private int anInt1036;
	private int anInt1037;
	private int loginFailures;
	private int anInt1039;
	private int dialogID;
	private final int[] maxStats;
	private final int[] anIntArray1045;
	private int anInt1046;
	private boolean aBoolean1047;
	private int anInt1048;
	private String aString1049;
	private static int anInt1051;
	public final int[] mapImagePixelCutLeft;
	private StreamLoader titleStreamLoader;
	private int tabIconFlash;
	private int displayOverLayIcon;
	private NodeList aClass19_1056;
	private final int[] compassPixelCutRight;
	public final RSInterface aClass9_1059;
	private Background[] mapScenes;
	private static int anInt1061;
	private int anInt1062;
	private final int barFillColor;
	private int friendsListAction;
	private final int[] anIntArray1065;
	private int mouseInvInterfaceIndex;
	private int lastActiveInvInterface;
	private OnDemandFetcher onDemandFetcher;
	private int anInt1069;
	private int anInt1070;
	private int totalMapFunctions;
	private int[] mapFunctionX;
	private int[] mapFunctionY;
	private Sprite mapDotItem;
	private Sprite mapDotNPC;
	private Sprite mapDotPlayer;
	private Sprite mapDotFriend;
	private Sprite mapDotTeam;
	private Sprite mapDotClan;
	private int anInt1079;
	private boolean aBoolean1080;
	private String[] friendsList;
	private Stream inStream;
	private int anInt1084;
	private int anInt1085;
	private int activeInterfaceType;
	private int anInt1087;
	private int anInt1088;
	public static int anInt1089;
	private final int[] expectedCRCs;
	private int[] menuActionCmd2;
	private int[] menuActionCmd3;
	public int[] menuActionID;
	private int[] menuActionCmd1;
	private Sprite[] headIcons;
	public Sprite[] bountyHunter;
	private Sprite[] cursesIcons;
	private Sprite[] skullIcons;
	private Sprite[] headIconsHint;
	private static int anInt1097;
	private int anInt1098;
	private int anInt1099;
	private int anInt1100;
	private int anInt1101;
	private int anInt1102;
	public static boolean tabAreaAltered;
	private int systemUpdatetime;
	private RSImageProducer aRSImageProducer_1107;
	private RSImageProducer aRSImageProducer_1108;
	private RSImageProducer aRSImageProducer_1109;
	private RSImageProducer aRSImageProducer_1110;
	private RSImageProducer aRSImageProducer_1111;
	private RSImageProducer aRSImageProducer_1112;
	private RSImageProducer aRSImageProducer_1113;
	private RSImageProducer aRSImageProducer_1114;
	private RSImageProducer aRSImageProducer_1115;
	private static int anInt1117;
	private int membersInt;
	private String aString1121;
	private Sprite compass;
	private RSImageProducer aRSImageProducer_1123;
	private RSImageProducer aRSImageProducer_1124;
	private RSImageProducer aRSImageProducer_1125;
	public static Player myPlayer;
	private final String[] atPlayerActions;
	private final boolean[] atPlayerArray;
	private final int[][][] anIntArrayArrayArray1129;
	public static final int[] tabInterfaceIDs = {
		-1, -1, -1, -1, -1, -1, -1, -1, 
		-1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1
	};
	private int anInt1131;
	private int anInt1132;
	public int menuActionRow;
	private static int anInt1134;
	private int spellSelected;
	private int anInt1137;
	private int spellUsableOn;
	private String spellTooltip;
	private Sprite[] currentMapFunctions;
	private boolean aBoolean1141;
	private static int anInt1142;
	private int energy;
	private boolean aBoolean1149;
	private Sprite[] crosses;
	private boolean musicEnabled;
	private Background[] aBackgroundArray1152s;
	public static boolean needDrawTabArea;
	private int unreadMessages;
	private static int anInt1155;
	private static boolean fpsOn;
	public boolean loggedIn;
	private boolean canMute;
	private boolean canBan;
	private boolean aBoolean1159;
	private boolean aBoolean1160;
	static int loopCycle;
	private static final String validUserPassChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
	private RSImageProducer sideTabArea;
	private RSImageProducer mapEdgeIP;
	private RSImageProducer mapBackImage;
	private RSImageProducer main3DArea;
	private RSImageProducer chatBackImage;
	private int daysSinceRecovChange;
	private RSSocket socketStream;
	private int anInt1169;
	private int mapAmtDiffZoom;
	private int mapAmtCounterZoom;
	private long aLong1172;
	private String myUsername;
	private String myPassword;
	private static int anInt1175;
	private boolean genericLoadingError;
	private final int[] anIntArray1177 = {
		0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 
		2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
		2, 2, 3
	};
	private boolean tabSpace;
	private int mapHover;
	private int advisorHover;
	private int exitHover;
	private int reportAbuseInterfaceID;
	private NodeList aClass19_1179;
	private int[] anIntArray1180;
	private int[] anIntArray1181;
	private int[] anIntArray1182;
	private byte[][] aByteArrayArray1183;
	private int anInt1184;
	private int compassRotation;
	private int anInt1186;
	private int anInt1187;
	private static int anInt1188;
	private int invOverlayInterfaceID;
	private int[] anIntArray1190;
	private int[] anIntArray1191;
	private Stream stream;
	private int anInt1193;
	private int splitPrivateChat;
	private Background[] mapBack;
	private Sprite NewMapBack;
	public String[] menuActionName;
	private Sprite aClass30_Sub2_Sub1_Sub1_1201;
	private Sprite aClass30_Sub2_Sub1_Sub1_1202;
	private final int[] anIntArray1203;
	private String nameYell = "", shoutYell = "";
	static final int[] anIntArray1204 = {
		9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145, 
		58654, 5027, 1457, 16565, 34991, 25486
	};
	private Sprite[] inventoryStones;
	private static boolean flagged;
	private final int[] anIntArray1207;
	private int anInt1208;
	private int mapAmtDiffRotation;
		private final Sprite[] modIcons;
	private int mapAmtCounterRotation;
	public static int chatScrollTotalLength;
	private String promptInput;
	private int anInt1213;
	private int[][][] intGroundArray;
	private long aLong1215;
	private int loginScreenCursorPos;
	//private final Background[] modIcons;
	private long aLong1220;
	public static int tabID;
	private int hintArrowNPCID;
	public static boolean inputTaken;
	private int inputDialogState;
	private static int anInt1226;
	private int nextSong;
	private boolean songChanging;
	private final int[] mapImagePixelCutRight;
	private Class11[] aClass11Array1230;
	public static int anIntArray1232[];
	private boolean aBoolean1233;
	private int[] anIntArray1234;
	private int[] anIntArray1235;
	private int[] anIntArray1236;
	private int anInt1237;
	private int anInt1238;
	public final int anInt1239 = 100;
	private final int[] anIntArray1240;
	private final int[] anIntArray1241;
	private boolean aBoolean1242;
	private int atInventoryLoopCycle;
	private int atInventoryInterface;
	private int atInventoryIndex;
	private int atInventoryInterfaceType;
	private byte[][] aByteArrayArray1247;
	private int tradeMode;
	private int anInt1249;
	private final int[] anIntArray1250;
	private int anInt1251;
	private final boolean rsAlreadyLoaded;
	private int anInt1253;
	private int mapTimedProcessCounter;
	private boolean welcomeScreenRaised;
	private boolean messagePromptRaised;
	private int anInt1257;
	private byte[][][] byteGroundArray;
	private int prevSong;
	private int destX;
	private int destY;
	private Sprite miniMapImage;
	private int anInt1264;
	private int anInt1265;
	public int GEItemId = 0;
	public boolean buttonclicked = false;
	private String loginMessage1;
	private String loginMessage2;
	private int anInt1268;
	private int anInt1269;
	public TextDrawingArea smallFont;
	public TextDrawingArea normalFont;
	public TextDrawingArea boldFont;
	public TextDrawingArea fancyFont, bigHit, smallHit;
	private int anInt1275;
	private int backDialogID;
	private int anInt1278;
	private int anInt1279;
	private int[] bigX;
	private int[] bigY;
	private int itemSelected;
	private int itemSlotUsedOn;
	private int anInt1284;
	private int anInt1285;
	private String selectedItemName;
	private int publicChatMode;
	private static int anInt1288;
	private int anInt1289;
	public static int anInt1290;
	public boolean runClicked = false;
	public int drawCount;
	public int fullscreenInterfaceID;
	public int anInt1044;//377
	public int lastHoverToggleChildId;//377
	public int hoverToggleChildId;//377
	public int anInt1500;//377
	public Sprite infinityIcon;
	public int anInt1501;//377
	public int[] fullScreenTextureArray;
	private boolean counterOn, xpLock;
	private boolean summoningAttack;
	private int xpAddedPos, expAdded, xpCounter;
	private Sprite[] XPcounterSprite;
	private Sprite[] exitButton = new Sprite[3];
	private Sprite[] worldMapIcon = new Sprite[2];;
	private Sprite[] advisorButton = new Sprite[3];
	private String linkInText;
	private int prayerInterfaceType;
	private String newPasswordString = "";
	private String newUserString = "";
	public void resetAllImageProducers() {
		if (super.fullGameScreen != null) {
			return;
		}
		chatBackImage = null;
		mapBackImage = null;
		sideTabArea = null;
		main3DArea = null;
		aRSImageProducer_1123 = null;
		aRSImageProducer_1124 = null;
		aRSImageProducer_1125 = null;
		aRSImageProducer_1107 = null;
		aRSImageProducer_1108 = null;
		aRSImageProducer_1109 = null;
		aRSImageProducer_1110 = null;
		aRSImageProducer_1111 = null;
		aRSImageProducer_1112 = null;
		aRSImageProducer_1113 = null;
		aRSImageProducer_1114 = null;
		aRSImageProducer_1115 = null;
		super.fullGameScreen = new RSImageProducer(765, 503, getGameComponent());
		welcomeScreenRaised = true;
	}
	
	
	public void launchURL(String url) { 
		String osName = System.getProperty("os.name"); 
		try { 
			if (osName.startsWith("Mac OS")) { 
				Class fileMgr = Class.forName("com.apple.eio.FileManager"); 
				Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] {String.class}); 
				openURL.invoke(null, new Object[] {url});
			} else if (osName.startsWith("Windows")) 
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url); 
			else { //assume Unix or Linux
				String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape", "safari" }; 
			String browser = null; 
			for (int count = 0; count < browsers.length && browser == null; count++) 
				if (Runtime.getRuntime().exec(new String[] {"which", browsers[count]}).waitFor() == 0)
					browser = browsers[count]; 
			if (browser == null) {
				 throw new Exception("Could not find web browser"); 
			} else
				 Runtime.getRuntime().exec(new String[] {browser, url});
			}
		} catch (Exception e) { 
			pushMessage("Failed to open URL.", 0, "");
		}
	}

	static  {
		anIntArray1019 = new int[99];
		int i = 0;
		for(int j = 0; j < 99; j++) {
			int l = j + 1;
			int i1 = (int)((double)l + 300D * Math.pow(2D, (double)l / 7D));
			i += i1;
			anIntArray1019[j] = i / 4;
		}
		anIntArray1232 = new int[32];
		i = 2;
		for(int k = 0; k < 32; k++) {
			anIntArray1232[k] = i - 1;
			i += i;
		}
	}
	public static int getCheckup(String s)
	{
		char[] UIDFletcherInput = {
			'A','B','C','D','E','F','G','H','I','J',
			'K','L','M','N','O','P','Q','R','S','T',
			'U','V','W','X','Y','Z','a','b','c','d',
			'e','f','g','h','i','j','k','l','m','n',
			'o','p','q','r','s','t','u','v','w','x',
			'y','z','0','1','2','3','4','5','6','7',
			'8','9'};
		int[] UIDFletcherOutput = {
			10,11,12,13,14,15,16,17,18,19,
			20,21,22,23,24,25,26,27,28,29,
			30,31,32,33,34,35,36,37,38,39,
			40,41,42,43,44,45,46,47,48,49,
			50,51,52,53,54,55,56,57,58,59,
			60,61,62,63,64,65,66,67,68,69,
			70,71};
		int UID = 0;
		
		for(int i1 = 0; i1 < s.length(); i1++)
			for(int i2 = 0; i2 < UIDFletcherInput.length; i2++)
			{
				if(UIDFletcherInput[i2] == s.charAt(i1))
					{
					UID+=UIDFletcherOutput[i2];
					}
			}
		return UID;
	}
	public String inputbox(){
		//String str = JOptionPane.showInputDialog(null, "Enter Payment ID","Donation retriever", 1);
		return "";
	}
}