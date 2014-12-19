package com;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.Com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSInterface.java
import java.net.InetAddress;
import java.net.Socket;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
public final class RSInterface {
  public static void addSprite(int paramInt1, int paramInt2, URL paramURL, int l)
  {
    RSInterface localRSInterface = new RSInterface();
    localRSInterface.id = paramInt1;
    localRSInterface.parentID = paramInt1;
    localRSInterface.type = 5;
    localRSInterface.atActionType = 0;
    localRSInterface.contentType = 0;
    localRSInterface.opacity = 0;
    localRSInterface.mOverInterToTrigger = 52;
   // localRSInterface.sprite1 = new Sprite(paramURL);
   // localRSInterface.sprite2 = localRSInterface.sprite1;
    localRSInterface.width = 512;
    localRSInterface.height = 334;
	addSprite(paramInt1, paramInt2, ""+paramURL+"", 0);
  }	
 public static void TitleShop(TextDrawingArea TDA[]) {
    RSInterface Interface = addTabInterface(16450);
	/** Title Shop Interface Made by
	@author Dr House
	**/
    setChildren(71, Interface);
	
    addSprite(16451, 0, "Interfaces/Loyalty/TITLE");
    addText(16476, "My Points:", TDA, 0, 0xFF9900, false, true);
	addText(16477, "Currently viewing: Divination Title Shop", TDA, 1, 0xFF9900, false, true);
    addText(16478, "Titles", TDA, 0, 0xFF9900, false, true);
    addText(16479, "Re-Colour", TDA, 0, 0xFF9900, false, true);
    addText(16480, "Costumes", TDA, 0, 0xFF9900, false, true);
    addText(16481, "Spins", TDA, 0, 0xFF9900, false, true);
    addText(16482, "500000", TDA, 0, 0xFF9900, false, true);
    addText(16483, "Buy", TDA, 0, 0xFF9900, false, true);
    addText(16484, "Cancel", TDA, 0, 0xFF9900, false, true);
	addText(16521, "Task", TDA, 0, 0xFF9900, false, true);
	addText(16522, "Selected:", TDA, 0, 0xFF9900, false, true);
	
	//Buttoms
	
	addHoverButton(16452, "", 4, 101, 58, "Confirm Selection", -1, 16453, 1);
	addHoveredButton(16453, "Interfaces/Loyalty/HOVER", 0, 101, 58, 16601);
	addHoverButton(16454, "", 4, 101, 58, "Confirm Selection", -1, 16455, 1);
	addHoveredButton(16455, "Interfaces/Loyalty/HOVER", 0, 101, 58, 16602);
	addHoverButton(16456, "", 4, 101, 58, "Confirm Selection", -1, 16457, 1);
	addHoveredButton(16457, "Interfaces/Loyalty/HOVER", 0, 101, 58, 16603);
	addHoverButton(16458, "", 4, 101, 58, "Confirm Selection", -1, 16459, 1);
	addHoveredButton(16459, "Interfaces/Loyalty/HOVER", 0, 101, 58, 16604);
	addHoverButton(16460, "", 4, 101, 58, "Confirm Selection", -1, 16461, 1);
	addHoveredButton(16461, "Interfaces/Loyalty/HOVER", 0, 101, 58, 16605);
	addHoverButton(16462, "", 4, 101, 58, "Confirm Selection", -1, 16463, 1);
	addHoveredButton(16463, "Interfaces/Loyalty/HOVER", 0, 101, 58, 16606);
	addHoverButton(16464, "", 4, 101, 58, "Confirm Selection", -1, 16465, 1);
	addHoveredButton(16465, "Interfaces/Loyalty/HOVER", 0, 101, 58, 16607);
	addHoverButton(16466, "", 4, 101, 58, "Confirm Selection", -1, 16467, 1);
	addHoveredButton(16467, "Interfaces/Loyalty/HOVER", 0, 101, 58, 16608);
	addHoverButton(16468, "", 4, 101, 58, "Confirm Selection", -1, 16469, 1);
	addHoveredButton(16469, "Interfaces/Loyalty/HOVER", 0, 101, 58, 16609);
	addHoverButton(16470, "", 4, 101, 58, "Confirm Selection", -1, 16471, 1);
	addHoveredButton(16471, "Interfaces/Loyalty/HOVER", 0, 101, 58, 16610);
	addHoverButton(16472, "", 4, 101, 58, "Confirm Selection", -1, 16473, 1);
	addHoveredButton(16473, "Interfaces/Loyalty/HOVER", 0, 101, 58, 16611);
	addHoverButton(16474, "", 4, 101, 58, "Confirm Selection", -1, 16475, 1);
	addHoveredButton(16475, "Interfaces/Loyalty/HOVER", 0, 101, 58, 16612);
	addHoverButton(16510, "", 4, 101, 58, "Confirm Selection", -1, 16511, 1);
	addHoveredButton(16511, "Interfaces/Loyalty/HOVER", 1, 101, 58, 16613);
	addHoverButton(16512, "", 4, 101, 58, "Confirm Selection", -1, 16513, 1);
	addHoveredButton(16513, "Interfaces/Loyalty/HOVER", 1, 101, 58, 16614);
	addHoverButton(16514, "", 4, 120, 36, "Titles", -1, -1, 1);		//Titles Shop
	addHoverButton(16515, "", 4, 120, 36, "Costumes", -1, -1, 1);	//Costumes Shop
	addHoverButton(16516, "", 4, 120, 36, "Recolour", -1, -1, 1);	//Recolours Shop
	addHoverButton(16517, "", 4, 120, 36, "Spins", -1, -1, 1);	//Spins Shop
	addHoverButton(16520, "", 4, 120, 36, "Task", -1, -1, 1);	//Task Shop
	addHoverButton(16518, "", 4, 11, 11, "Close", -1, 16519, 1);
	addHoveredButton(16519, "Interfaces/Loyalty/HOVER", 7, 11, 11, 16665);
	
	
	//Titles
	
	addText(16486, "Sir", TDA, 0, 0xFFFFD0, false, true);
	addText(16487, "Lady", TDA, 0, 0xFFFFD0, false, true);
	addText(16488, "King", TDA, 0, 0xFFFFD0, false, true);
	addText(16489, "Queen", TDA, 0, 0xFFFFD0, false, true);
	addText(16490, "Gangster", TDA, 0, 0xFFFFD0, false, true);
	addText(16491, "Peedo", TDA, 0, 0xFFFFD0, false, true);
	addText(16492, "Demon", TDA, 0, 0xFFFFD0, false, true);
	addText(16493, "Angel", TDA, 0, 0xFFFFD0, false, true);
	addText(16494, "Unstoppable", TDA, 0, 0xFFFFD0, false, true);
	addText(16495, "Nerd", TDA, 0, 0xFFFFD0, false, true);
	addText(16496, "Psychopath", TDA, 0, 0xFFFFD0, false, true);
	addText(16497, "Immortal", TDA, 0, 0xFFFFD0, false, true);
 
	//Prices
	
	addText(16498, "1k Points", TDA, 0, 0xFF9900, false, true);
	addText(16499, "1k Points", TDA, 0, 0xFF9900, false, true);
	addText(16500, "3k Points", TDA, 0, 0xFF9900, false, true);
	addText(16501, "3k Points", TDA, 0, 0xFF9900, false, true);
	addText(16502, "5k Points", TDA, 0, 0xFF9900, false, true);
	addText(16503, "8k Points", TDA, 0, 0xFF9900, false, true);
	addText(16504, "10k Points", TDA, 0, 0xFF9900, false, true);
	addText(16505, "10k Points", TDA, 0, 0xFF9900, false, true);
	addText(16506, "15k Points", TDA, 0, 0xFF9900, false, true);
	addText(16507, "20k Points", TDA, 0, 0xFF9900, false, true);
	addText(16508, "25k Points", TDA, 0, 0xFF9900, false, true);
	addText(16509, "30k Points", TDA, 0, 0xFF9900, false, true);
	
    setBounds(16451, 10, 10, 0, Interface); //Background
	//Buttoms {
	//Sir
	setBounds(16452, 162, 47, 1, Interface);
    setBounds(16453, 162, 47, 2, Interface);
	//Lady
    setBounds(16454, 263, 47, 3, Interface);
    setBounds(16455, 263, 47, 4, Interface);
	//King
    setBounds(16456, 364, 47, 5, Interface);
    setBounds(16457, 364, 47, 6, Interface);
	
	//Queen
    setBounds(16458, 162, 107, 7, Interface);
    setBounds(16459, 162, 107, 8, Interface);
	//Gangstar
    setBounds(16460, 263, 107, 9, Interface);
    setBounds(16461, 263, 107, 10, Interface);
	//Peedo
    setBounds(16462, 364, 107, 11, Interface);
    setBounds(16463, 364, 107, 12, Interface);
	
	//Demon
    setBounds(16464, 162, 167, 13, Interface);
    setBounds(16465, 162, 167, 14, Interface);
	//Angel
    setBounds(16466, 263, 167, 15, Interface);
    setBounds(16467, 263, 167, 16, Interface);
	//Unstoppable
    setBounds(16468, 364, 167, 17, Interface);
    setBounds(16469, 364, 167, 18, Interface);
	
	//Nerd
    setBounds(16470, 162, 227, 19, Interface);
	setBounds(16471, 162, 227, 20, Interface);
	//Psychopath
    setBounds(16472, 263, 227, 21, Interface);
    setBounds(16473, 263, 227, 22, Interface);
	//Ninja
    setBounds(16474, 364, 227, 23, Interface);
    setBounds(16475, 364, 227, 24, Interface);
	// }
	
	//Texts (not titles) {
    setBounds(16476, 38, 26, 25, Interface);
    setBounds(16477, 142, 23, 60, Interface);
    setBounds(16478, 63, 56, 61, Interface);
    setBounds(16479, 63, 126, 28, Interface);
    setBounds(16480, 63, 90, 29, Interface);
    setBounds(16481, 63, 162, 30, Interface);
    setBounds(16482, 94, 26, 31, Interface);
    setBounds(16483, 323, 291, 58, Interface);
    setBounds(16484, 403, 291, 59, Interface);
	//}
	
	//Titles {
	setBounds(16486, 199, 60, 34, Interface);
    setBounds(16487, 301, 60, 35, Interface);
    setBounds(16488, 403, 60, 36, Interface);
    setBounds(16489, 199, 120, 37, Interface);
    setBounds(16490, 301, 120, 38, Interface);
    setBounds(16491, 403, 120, 39, Interface);
    setBounds(16492, 199, 180, 40, Interface);
    setBounds(16493, 301, 180, 41, Interface);
    setBounds(16494, 399, 180, 42, Interface);
    setBounds(16495, 199, 240, 43, Interface);
	setBounds(16496, 299, 240, 44, Interface);
    setBounds(16497, 403, 240, 45, Interface);
	//}
	
	//Prices {
    setBounds(16498, 291, 85, 46, Interface);
    setBounds(16499, 190, 85, 47, Interface);
    setBounds(16500, 392, 85, 48, Interface);
    setBounds(16501, 291, 145, 49, Interface);
    setBounds(16502, 186, 145, 50, Interface);
    setBounds(16503, 392, 145, 51, Interface);
    setBounds(16504, 291, 205, 52, Interface);
    setBounds(16505, 190, 205, 53, Interface);
    setBounds(16506, 392, 205, 54, Interface);
    setBounds(16507, 190, 265, 55, Interface);
    setBounds(16508, 291, 265, 56, Interface);
    setBounds(16509, 392, 265, 57, Interface);
	//}
	
	//Buy/Cancel Buttom
	setBounds(16510, 291, 286, 32, Interface);
    setBounds(16511, 291, 286, 33, Interface);
    setBounds(16512, 378, 286, 26, Interface);
    setBounds(16513, 378, 286, 27, Interface);
	setBounds(16514, 20, 45, 62, Interface);
    setBounds(16515, 20, 81, 63, Interface);
    setBounds(16516, 20, 116, 64, Interface);
    setBounds(16517, 20, 149, 65, Interface);
	setBounds(16518, 480, 17, 66, Interface);
    setBounds(16519, 480, 17, 67, Interface);
	setBounds(16520, 20, 185, 68, Interface);
    setBounds(16521, 63, 198, 69, Interface);
    setBounds(16522, 139, 290, 70, Interface);
	
	}
	
	public static void CostumeShop(TextDrawingArea TDA[]) {
    RSInterface Interface = addTabInterface(40450);
	/** Costume Shop Interface Made by
	@author Dr House
	**/
    setChildren(71, Interface);
	
    addSprite(40451, 0, "Interfaces/Loyalty/COSTUME");
    addText(40476, "My Points:", TDA, 0, 0xFF9900, false, true);
	addText(40477, "Currently viewing: Divination Costume Shop", TDA, 1, 0xFF9900, false, true);
    addText(40478, "Titles", TDA, 0, 0xFF9900, false, true);
    addText(40479, "Re-Colour", TDA, 0, 0xFF9900, false, true);
    addText(40480, "Costumes", TDA, 0, 0xFF9900, false, true);
    addText(40481, "Spins", TDA, 0, 0xFF9900, false, true);
    addText(40482, "500000", TDA, 0, 0xFF9900, false, true);
    addText(40483, "Buy", TDA, 0, 0xFF9900, false, true);
    addText(40484, "Cancel", TDA, 0, 0xFF9900, false, true);
	addText(40521, "Task", TDA, 0, 0xFF9900, false, true);
	addText(40522, "Selected:", TDA, 0, 0xFF9900, false, true);
	
	//Buttoms
	
	addHoverButton(40452, "", 4, 101, 58, "Select Costume", -1, 40453, 1);
	addHoveredButton(40453, "Interfaces/Loyalty/HOVER", 2, 101, 58, 40650);
	addHoverButton(40454, "", 4, 101, 58, "Select Costume", -1, 40455, 1);
	addHoveredButton(40455, "Interfaces/Loyalty/HOVER", 2, 101, 58, 40651);
	addHoverButton(40456, "", 4, 101, 58, "Select Costume", -1, 40457, 1);
	addHoveredButton(40457, "Interfaces/Loyalty/HOVER", 2, 101, 58, 40652);
	addHoverButton(40458, "", 4, 101, 58, "Select Costume", -1, 40459, 1);
	addHoveredButton(40459, "Interfaces/Loyalty/HOVER", 2, 101, 58, 40653);
	addHoverButton(40460, "", 4, 101, 58, "Select Costume", -1, 40461, 1);
	addHoveredButton(40461, "Interfaces/Loyalty/HOVER", 2, 101, 58, 40654);
	addHoverButton(40462, "", 4, 101, 58, "Select Costume ", -1, 40463, 1);
	addHoveredButton(40463, "Interfaces/Loyalty/HOVER", 2, 101, 58, 40655);
	addHoverButton(40464, "", 4, 101, 58, "Select Costume ", -1, 40465, 1);
	addHoveredButton(40465, "Interfaces/Loyalty/HOVER", 2, 101, 58, 40656);
	addHoverButton(40466, "", 4, 101, 58, "Select Costume", -1, 40467, 1);
	addHoveredButton(40467, "Interfaces/Loyalty/HOVER", 2, 101, 58, 40657);
	addHoverButton(40468, "", 4, 101, 58, "Select Costume", -1, 40469, 1);
	addHoveredButton(40469, "Interfaces/Loyalty/HOVER", 2, 101, 58, 40658);
	addHoverButton(40470, "", 4, 101, 58, "Select Costume", -1, 40471, 1);
	addHoveredButton(40471, "Interfaces/Loyalty/HOVER", 2, 101, 58, 40659);
	addHoverButton(40472, "", 4, 101, 58, "Select Costume", -1, 40473, 1);
	addHoveredButton(40473, "Interfaces/Loyalty/HOVER", 2, 101, 58, 40660);
	addHoverButton(40474, "", 4, 101, 58, "Select Costume", -1, 40475, 1);
	addHoveredButton(40475, "Interfaces/Loyalty/HOVER", 2, 101, 58, 40661);
	addHoverButton(40510, "", 4, 101, 58, "Confirm Selection", -1, 40511, 1);
	addHoveredButton(40511, "Interfaces/Loyalty/HOVER", 1, 101, 58, 40662);
	addHoverButton(40512, "", 4, 101, 58, "Confirm Selection", -1, 40513, 1);
	addHoveredButton(40513, "Interfaces/Loyalty/HOVER", 1, 101, 58, 40663);
	
	addHoverButton(40514, "", 4, 120, 36, "Titles", -1, -1, 1);		//Titles Shop
	addHoverButton(40515, "", 4, 120, 36, "Costumes", -1, -1, 1);	//Costumes Shop
	addHoverButton(40516, "", 4, 120, 36, "Recolour", -1, -1, 1);	//Recolours Shop
	addHoverButton(40517, "", 4, 120, 36, "Spins", -1, -1, 1);	//Spins Shop
	addHoverButton(40520, "", 4, 120, 36, "Task", -1, -1, 1);	//Task Shop
	addHoverButton(40518, "", 4, 11, 11, "Close", -1, 40519, 1);
	addHoveredButton(40519, "Interfaces/Loyalty/HOVER", 7, 11, 11, 40664);
	
	//Costumes
	
	addText(40486, "Frog ", TDA, 0, 0xFFFFD0, false, true);
	addText(40487, "Mime", TDA, 0, 0xFFFFD0, false, true);
	addText(40488, "Zombie", TDA, 0, 0xFFFFD0, false, true);
	addText(40489, "Warlock", TDA, 0, 0xFFFFD0, false, true);
	addText(40490, "Jester", TDA, 0, 0xFFFFD0, false, true);
	addText(40491, "Skeleton", TDA, 0, 0xFFFFD0, false, true);
	addText(40492, "Sled", TDA, 0, 0xFFFFD0, false, true);
	addText(40493, "Basket", TDA, 0, 0xFFFFD0, false, true);
	addText(40494, "Witchdoctor", TDA, 0, 0xFFFFD0, false, true);
	addText(40495, "Santa", TDA, 0, 0xFFFFD0, false, true);
	addText(40496, "Grim Reaper", TDA, 0, 0xFFFFD0, false, true);
	addText(40497, "Investigator", TDA, 0, 0xFFFFD0, false, true);
 
	//Prices
	
	addText(40498, "1k Points", TDA, 0, 0xFF9900, false, true);
	addText(40499, "1k Points", TDA, 0, 0xFF9900, false, true);
	addText(40500, "2k Points", TDA, 0, 0xFF9900, false, true);
	addText(40501, "3k Points", TDA, 0, 0xFF9900, false, true);
	addText(40502, "4k Points", TDA, 0, 0xFF9900, false, true);
	addText(40503, "5k Points", TDA, 0, 0xFF9900, false, true);
	addText(40504, "7.5k Points", TDA, 0, 0xFF9900, false, true);
	addText(40505, "7.5k Points", TDA, 0, 0xFF9900, false, true);
	addText(40506, "13k Points", TDA, 0, 0xFF9900, false, true);
	addText(40507, "15k Points", TDA, 0, 0xFF9900, false, true);
	addText(40508, "15k Points", TDA, 0, 0xFF9900, false, true);
	addText(40509, "17.5k Points", TDA, 0, 0xFF9900, false, true);
	
    setBounds(40451, 10, 10, 0, Interface); //Background
	//Buttoms {
	//Sir
	setBounds(40452, 162, 47, 1, Interface);
    setBounds(40453, 162, 47, 2, Interface);
	//Lady
    setBounds(40454, 263, 47, 3, Interface);
    setBounds(40455, 263, 47, 4, Interface);
	//King
    setBounds(40456, 364, 47, 5, Interface);
    setBounds(40457, 364, 47, 6, Interface);
	
	//Queen
    setBounds(40458, 162, 107, 7, Interface);
    setBounds(40459, 162, 107, 8, Interface);
	//Gangstar
    setBounds(40460, 263, 107, 9, Interface);
    setBounds(40461, 263, 107, 10, Interface);
	//Peedo
    setBounds(40462, 364, 107, 11, Interface);
    setBounds(40463, 364, 107, 12, Interface);
	
	//Demon
    setBounds(40464, 162, 167, 13, Interface);
    setBounds(40465, 162, 167, 14, Interface);
	//Angel
    setBounds(40466, 263, 167, 15, Interface);
    setBounds(40467, 263, 167, 16, Interface);
	//Unstoppable
    setBounds(40468, 364, 167, 17, Interface);
    setBounds(40469, 364, 167, 18, Interface);
	
	//Nerd
    setBounds(40470, 162, 227, 19, Interface);
	setBounds(40471, 162, 227, 20, Interface);
	//Psychopath
    setBounds(40472, 263, 227, 21, Interface);
    setBounds(40473, 263, 227, 22, Interface);
	//Ninja
    setBounds(40474, 364, 227, 23, Interface);
    setBounds(40475, 364, 227, 24, Interface);
	// }
	
	//Texts (not costumes) {
    setBounds(40476, 38, 26, 25, Interface);
    setBounds(40477, 142, 23, 26, Interface);
    setBounds(40478, 63, 56, 27, Interface);
    setBounds(40479, 63, 126, 28, Interface);
    setBounds(40480, 63, 90, 29, Interface);
    setBounds(40481, 63, 162, 30, Interface);
    setBounds(40482, 94, 26, 31, Interface);
    setBounds(40483, 323, 291, 60, Interface);
    setBounds(40484, 403, 291, 61, Interface);
	//}
	
	//Costumes {
	setBounds(40486, 199, 60, 34, Interface);
    setBounds(40487, 301, 60, 35, Interface);
    setBounds(40488, 403, 60, 36, Interface);
    setBounds(40489, 199, 120, 37, Interface);
    setBounds(40490, 301, 120, 38, Interface);
    setBounds(40491, 403, 120, 39, Interface);
    setBounds(40492, 199, 180, 40, Interface);
    setBounds(40493, 301, 180, 41, Interface);
    setBounds(40494, 399, 180, 42, Interface);
    setBounds(40495, 199, 240, 43, Interface);
	setBounds(40496, 299, 240, 44, Interface);
    setBounds(40497, 403, 240, 45, Interface);
	//}
	
	//Prices {
    setBounds(40498, 291, 85, 46, Interface);
    setBounds(40499, 190, 85, 47, Interface);
    setBounds(40500, 392, 85, 48, Interface);
    setBounds(40501, 291, 145, 49, Interface);
    setBounds(40502, 186, 145, 50, Interface);
    setBounds(40503, 392, 145, 51, Interface);
    setBounds(40504, 291, 205, 52, Interface);
    setBounds(40505, 190, 205, 53, Interface);
    setBounds(40506, 392, 205, 54, Interface);
    setBounds(40507, 190, 265, 55, Interface);
    setBounds(40508, 291, 265, 56, Interface);
    setBounds(40509, 392, 265, 57, Interface);
	//}
	
	//Buy/Cancel Buttom
	setBounds(40510, 291, 286, 32, Interface);
    setBounds(40511, 291, 286, 33, Interface);
    setBounds(40512, 378, 286, 58, Interface);
    setBounds(40513, 378, 286, 59, Interface);
	setBounds(40514, 20, 45, 62, Interface);
    setBounds(40515, 20, 81, 63, Interface);
    setBounds(40516, 20, 116, 64, Interface);
    setBounds(40517, 20, 149, 65, Interface);
	setBounds(40518, 480, 17, 66, Interface);
    setBounds(40519, 480, 17, 67, Interface);
	setBounds(40520, 20, 185, 68, Interface);
    setBounds(40521, 63, 198, 69, Interface);
    setBounds(40522, 139, 290, 70, Interface);
	
	}
	

	public static void RecolourShop(TextDrawingArea TDA[]) {
    RSInterface Interface = addTabInterface(44450);
	/** Costume Shop Interface Made by
	@author Dr House
	**/
    setChildren(70, Interface);
	
    addSprite(44451, 0, "Interfaces/Loyalty/COLOUR");
    addText(44476, "My Points:", TDA, 0, 0xFF9900, false, true);
	addText(44477, "Currently viewing: Divination Recolour Shop", TDA, 1, 0xFF9900, false, true);
    addText(44478, "Titles", TDA, 0, 0xFF9900, false, true);
    addText(44479, "Re-Colour", TDA, 0, 0xFF9900, false, true);
    addText(44480, "Costumes", TDA, 0, 0xFF9900, false, true);
    addText(44481, "Spins", TDA, 0, 0xFF9900, false, true);
    addText(44482, "500000", TDA, 0, 0xFF9900, false, true);
    addText(44483, "Buy", TDA, 0, 0xFF9900, false, true);
    addText(44484, "Cancel", TDA, 0, 0xFF9900, false, true);
	addText(44521, "Task", TDA, 0, 0xFF9900, false, true);
	
	//Buttoms
	
	addHoverButton(44452, "", 4, 101, 58, "Select Item", -1, 44453, 1);
	addHoveredButton(44453, "Interfaces/Loyalty/HOVER", 3, 101, 58, 44650);
	addHoverButton(44454, "", 4, 101, 58, "Select Item", -1, 44455, 1);
	addHoveredButton(44455, "Interfaces/Loyalty/HOVER", 3, 101, 58, 44651);
	addHoverButton(44456, "", 4, 101, 58, "Select Item", -1, 44457, 1);
	addHoveredButton(44457, "Interfaces/Loyalty/HOVER", 3, 101, 58, 44652);
	addHoverButton(44458, "", 4, 101, 58, "Select Item", -1, 44459, 1);
	addHoveredButton(44459, "Interfaces/Loyalty/HOVER", 3, 101, 58, 44653);
	addHoverButton(44460, "", 4, 101, 58, "Select Item", -1, 44461, 1);
	addHoveredButton(44461, "Interfaces/Loyalty/HOVER", 3, 101, 58, 44654);
	addHoverButton(44462, "", 4, 101, 58, "Select Item ", -1, 44463, 1);
	addHoveredButton(44463, "Interfaces/Loyalty/HOVER", 3, 101, 58, 44655);
	addHoverButton(44464, "", 4, 101, 58, "Select Item ", -1, 44465, 1);
	addHoveredButton(44465, "Interfaces/Loyalty/HOVER", 3, 101, 58, 44656);
	addHoverButton(44466, "", 4, 101, 58, "Select Item", -1, 44467, 1);
	addHoveredButton(44467, "Interfaces/Loyalty/HOVER", 3, 101, 58, 44657);
	addHoverButton(44468, "", 4, 101, 58, "Select Item", -1, 44469, 1);
	addHoveredButton(44469, "Interfaces/Loyalty/HOVER", 3, 101, 58, 44658);
	addHoverButton(44470, "", 4, 101, 58, "Select Item", -1, 44471, 1);
	addHoveredButton(44471, "Interfaces/Loyalty/HOVER", 3, 101, 58, 44659);
	addHoverButton(44472, "", 4, 101, 58, "Select Item", -1, 44473, 1);
	addHoveredButton(44473, "Interfaces/Loyalty/HOVER", 3, 101, 58, 44660);
	addHoverButton(44474, "", 4, 101, 58, "Select Item", -1, 44475, 1);
	addHoveredButton(44475, "Interfaces/Loyalty/HOVER", 3, 101, 58, 44661);
	addHoverButton(44510, "", 4, 101, 58, "Confirm Selection", -1, 44511, 1);
	addHoveredButton(44511, "Interfaces/Loyalty/HOVER", 1, 101, 58, 44662);
	addHoverButton(44512, "", 4, 101, 58, "Confirm Selection", -1, 44513, 1);
	addHoveredButton(44513, "Interfaces/Loyalty/HOVER", 1, 101, 58, 44663);
	addHoverButton(44514, "", 4, 120, 36, "Titles", -1, -1, 1);		//Titles Shop
	addHoverButton(44515, "", 4, 120, 36, "Costumes", -1, -1, 1);	//Costumes Shop
	addHoverButton(44516, "", 4, 120, 36, "Recolour", -1, -1, 1);	//Recolours Shop
	addHoverButton(44517, "", 4, 120, 36, "Spins", -1, -1, 1);	//Spins Shop
	addHoverButton(44520, "", 4, 120, 36, "Task", -1, -1, 1);	//Task Shop
	addHoverButton(44518, "", 4, 11, 11, "Close", -1, 44519, 1);
	addHoveredButton(44519, "Interfaces/Loyalty/HOVER", 7, 11, 11, 44664);
	
	//Items
	
	addText(44486, "Dark Bow", TDA, 0, 0xFFFFD0, false, true);
	addText(44487, "Whip", TDA, 0, 0xFFFFD0, false, true);
	addText(44488, "Robin", TDA, 0, 0xFFFFD0, false, true);
	addText(44489, "Ranger Boots", TDA, 0, 0xFFFFD0, false, true);
	addText(44490, "Inf. Top", TDA, 0, 0xFFFFD0, false, true);
	addText(44491, "Inf. Bottom", TDA, 0, 0xFFFFD0, false, true);
	addText(44492, "Inf. Boots", TDA, 0, 0xFFFFD0, false, true);
	addText(44493, "Dragon(T)", TDA, 0, 0xFFFFD0, false, true);
	addText(44494, "Dragon(or)", TDA, 0, 0xFFFFD0, false, true);
	addText(44495, "S. of Light", TDA, 0, 0xFFFFD0, false, true);
	addText(44496, "H'Ween", TDA, 0, 0xFFFFD0, false, true);
	addText(44497, "Party Hat", TDA, 0, 0xFFFFD0, false, true);
 
	//Prices
	
	addText(44498, "1k Points", TDA, 0, 0xFF9900, false, true);
	addText(44499, "1k Points", TDA, 0, 0xFF9900, false, true);
	addText(44500, "3k Points", TDA, 0, 0xFF9900, false, true);
	addText(44501, "3k Points", TDA, 0, 0xFF9900, false, true);
	addText(44502, "5k Points", TDA, 0, 0xFF9900, false, true);
	addText(44503, "5k Points", TDA, 0, 0xFF9900, false, true);
	addText(44504, "5k Points", TDA, 0, 0xFF9900, false, true);
	addText(44505, "7.5k Points", TDA, 0, 0xFF9900, false, true);
	addText(44506, "7.5k Points", TDA, 0, 0xFF9900, false, true);
	addText(44507, "10k Points", TDA, 0, 0xFF9900, false, true);
	addText(44508, "15k Points", TDA, 0, 0xFF9900, false, true);
	addText(44509, "15k Points", TDA, 0, 0xFF9900, false, true);
	
    setBounds(44451, 10, 10, 0, Interface); //Background
	
	setBounds(44452, 162, 46, 1, Interface);
    setBounds(44453, 162, 46, 2, Interface);
    setBounds(44454, 263, 46, 3, Interface);
    setBounds(44455, 263, 46, 4, Interface);
    setBounds(44456, 364, 46, 5, Interface);
    setBounds(44457, 364, 46, 6, Interface);
    setBounds(44458, 162, 105, 7, Interface);
    setBounds(44459, 162, 105, 8, Interface);
    setBounds(44460, 263, 105, 9, Interface);
    setBounds(44461, 263, 105, 10, Interface);
    setBounds(44462, 364, 105, 11, Interface);
    setBounds(44463, 364, 105, 12, Interface);
    setBounds(44464, 162, 164, 13, Interface);
    setBounds(44465, 162, 164, 14, Interface);
    setBounds(44466, 263, 164, 15, Interface);
    setBounds(44467, 263, 164, 16, Interface);
    setBounds(44468, 364, 164, 17, Interface);
    setBounds(44469, 364, 164, 18, Interface);
    setBounds(44470, 162, 223, 19, Interface);
	setBounds(44471, 162, 223, 20, Interface);
    setBounds(44472, 263, 223, 21, Interface);
    setBounds(44473, 263, 223, 22, Interface);
    setBounds(44474, 364, 223, 23, Interface);
    setBounds(44475, 364, 223, 24, Interface);
    setBounds(44476, 38, 26, 25, Interface);
    setBounds(44477, 142, 23, 26, Interface);
    setBounds(44478, 63, 56, 27, Interface);
    setBounds(44479, 63, 126, 28, Interface);
    setBounds(44480, 63, 90, 29, Interface);
    setBounds(44481, 63, 162, 30, Interface);
    setBounds(44482, 94, 26, 31, Interface);
    setBounds(44483, 323, 291, 60, Interface);
    setBounds(44484, 403, 291, 61, Interface);
	setBounds(44486, 199, 60, 34, Interface);
    setBounds(44487, 301, 60, 35, Interface);
    setBounds(44488, 403, 60, 36, Interface);
    setBounds(44489, 195, 120, 37, Interface);
    setBounds(44490, 301, 120, 38, Interface);
    setBounds(44491, 403, 120, 39, Interface);
    setBounds(44492, 199, 180, 40, Interface);
    setBounds(44493, 301, 180, 41, Interface);
    setBounds(44494, 399, 180, 42, Interface);
    setBounds(44495, 199, 240, 43, Interface);
	setBounds(44496, 299, 240, 44, Interface);
    setBounds(44497, 403, 240, 45, Interface);
    setBounds(44498, 291, 85, 46, Interface);
    setBounds(44499, 190, 85, 47, Interface);
    setBounds(44500, 392, 85, 48, Interface);
    setBounds(44501, 291, 145, 49, Interface);
    setBounds(44502, 186, 145, 50, Interface);
    setBounds(44503, 392, 145, 51, Interface);
    setBounds(44504, 291, 205, 52, Interface);
    setBounds(44505, 190, 205, 53, Interface);
    setBounds(44506, 392, 205, 54, Interface);
    setBounds(44507, 190, 265, 55, Interface);
    setBounds(44508, 291, 265, 56, Interface);
    setBounds(44509, 392, 265, 57, Interface);
	setBounds(44510, 291, 286, 32, Interface);
    setBounds(44511, 291, 286, 33, Interface);
    setBounds(44512, 378, 286, 58, Interface);
    setBounds(44513, 378, 286, 59, Interface);
	setBounds(44514, 20, 45, 62, Interface);
    setBounds(44515, 20, 81, 63, Interface);
    setBounds(44516, 20, 116, 64, Interface);
    setBounds(44517, 20, 149, 65, Interface);
    setBounds(44518, 480, 17, 66, Interface);
    setBounds(44519, 480, 17, 67, Interface);
	setBounds(44520, 20, 185, 68, Interface);
    setBounds(44521, 63, 198, 69, Interface);
	}
	
	
	public static void TaskShop(TextDrawingArea TDA[]) {
    RSInterface Interface = addTabInterface(34450);
	/** Costume Shop Interface Made by
	@author Dr House
	**/
    setChildren(70, Interface);
	
    addSprite(34451, 0, "Interfaces/Loyalty/TASK");
    addText(34476, "My Points:", TDA, 0, 0xFF9900, false, true);
	addText(34477, "Currently viewing: Divination Task Shop", TDA, 1, 0xFF9900, false, true);
    addText(34478, "Titles", TDA, 0, 0xFF9900, false, true);
    addText(34479, "Re-Colour", TDA, 0, 0xFF9900, false, true);
    addText(34480, "Costumes", TDA, 0, 0xFF9900, false, true);
    addText(34481, "Spins", TDA, 0, 0xFF9900, false, true);
    addText(34482, "500000", TDA, 0, 0xFF9900, false, true);
    addText(34483, "Buy", TDA, 0, 0xFF9900, false, true);
    addText(34484, "Cancel", TDA, 0, 0xFF9900, false, true);
    addText(34521, "Task", TDA, 0, 0xFF9900, false, true);
	
	//Buttoms
	
	addHoverButton(34452, "", 4, 101, 58, "Select Item", -1, 34453, 1);
	addHoveredButton(34453, "Interfaces/Loyalty/HOVER", 8, 101, 58, 34650);
	addHoverButton(34454, "", 4, 101, 58, "Select Item", -1, 34455, 1);
	addHoveredButton(34455, "Interfaces/Loyalty/HOVER", 8, 101, 58, 34651);
	addHoverButton(34456, "", 4, 101, 58, "Select Item", -1, 34457, 1);
	addHoveredButton(34457, "Interfaces/Loyalty/HOVER", 8, 101, 58, 34652);
	addHoverButton(34458, "", 4, 101, 58, "Select Item", -1, 34459, 1);
	addHoveredButton(34459, "Interfaces/Loyalty/HOVER", 8, 101, 58, 34653);
	addHoverButton(34460, "", 4, 101, 58, "Select Item", -1, 34461, 1);
	addHoveredButton(34461, "Interfaces/Loyalty/HOVER", 8, 101, 58, 34654);
	addHoverButton(34462, "", 4, 101, 58, "Select Item", -1, 34463, 1);
	addHoveredButton(34463, "Interfaces/Loyalty/HOVER", 8, 101, 58, 34655);
	addHoverButton(34464, "", 4, 101, 58, "Select Item ", -1, 34465, 1);
	addHoveredButton(34465, "Interfaces/Loyalty/HOVER", 8, 101, 58, 34656);
	addHoverButton(34466, "", 4, 101, 58, "Select Item", -1, 34467, 1);
	addHoveredButton(34467, "Interfaces/Loyalty/HOVER", 8, 101, 58, 34657);
	addHoverButton(34468, "", 4, 101, 58, "Select Item", -1, 34469, 1);
	addHoveredButton(34469, "Interfaces/Loyalty/HOVER", 8, 101, 58, 34658);
	addHoverButton(34470, "", 4, 101, 58, "Select Item", -1, 34471, 1);
	addHoveredButton(34471, "Interfaces/Loyalty/HOVER", 8, 101, 58, 34659);
	addHoverButton(34472, "", 4, 101, 58, "Select Item", -1, 34473, 1);
	addHoveredButton(34473, "Interfaces/Loyalty/HOVER", 8, 101, 58, 34660);
	addHoverButton(34474, "", 4, 101, 58, "Select Item", -1, 34475, 1);
	addHoveredButton(34475, "Interfaces/Loyalty/HOVER", 8, 101, 58, 34661);
	addHoverButton(34510, "", 4, 101, 58, "Confirm Selection", -1, 34511, 1);
	addHoveredButton(34511, "Interfaces/Loyalty/HOVER", 1, 101, 58, 34662);
	addHoverButton(34512, "", 4, 101, 58, "Confirm Selection", -1, 34513, 1);
	addHoveredButton(34513, "Interfaces/Loyalty/HOVER", 1, 101, 58, 34663);
	addHoverButton(34514, "", 4, 120, 36, "Titles", -1, -1, 1);		//Titles Shop
	addHoverButton(34515, "", 4, 120, 36, "Costumes", -1, -1, 1);	//Costumes Shop
	addHoverButton(34516, "", 4, 120, 36, "Recolour", -1, -1, 1);	//Recolours Shop
	addHoverButton(34517, "", 4, 120, 36, "Spins", -1, -1, 1);	//Spins Shop
	addHoverButton(34520, "", 4, 120, 36, "Task", -1, -1, 1);	//Spins Shop
	addHoverButton(34518, "", 4, 11, 11, "Close", -1, 34519, 1);
	addHoveredButton(34519, "Interfaces/Loyalty/HOVER", 7, 11, 11, 34664);
	
	//Items
	
	addText(34486, "Item1", TDA, 0, 0xFFFFD0, false, true);
	addText(34487, "Item2", TDA, 0, 0xFFFFD0, false, true);
	addText(34488, "Item3", TDA, 0, 0xFFFFD0, false, true);
	addText(34489, "Item4", TDA, 0, 0xFFFFD0, false, true);
	addText(34490, "Item5", TDA, 0, 0xFFFFD0, false, true);
	addText(34491, "Item6", TDA, 0, 0xFFFFD0, false, true);
	addText(34492, "Item7", TDA, 0, 0xFFFFD0, false, true);
	addText(34493, "Item8", TDA, 0, 0xFFFFD0, false, true);
	addText(34494, "Item9", TDA, 0, 0xFFFFD0, false, true);
	addText(34495, "Item10", TDA, 0, 0xFFFFD0, false, true);
	addText(34496, "Item11", TDA, 0, 0xFFFFD0, false, true);
	addText(34497, "Item12", TDA, 0, 0xFFFFD0, false, true);

	//Prices
	
	addText(34498, "Price", TDA, 0, 0xFF9900, false, true);
	addText(34499, "Price", TDA, 0, 0xFF9900, false, true);
	addText(34500, "Price", TDA, 0, 0xFF9900, false, true);
	addText(34501, "Price", TDA, 0, 0xFF9900, false, true);
	addText(34502, "Price", TDA, 0, 0xFF9900, false, true);
	addText(34503, "Price", TDA, 0, 0xFF9900, false, true);
	addText(34504, "Price", TDA, 0, 0xFF9900, false, true);
	addText(34505, "Price", TDA, 0, 0xFF9900, false, true);
	addText(34506, "Price", TDA, 0, 0xFF9900, false, true);
	addText(34507, "Price", TDA, 0, 0xFF9900, false, true);
	addText(34508, "Price", TDA, 0, 0xFF9900, false, true);
	addText(34509, "Price", TDA, 0, 0xFF9900, false, true);
	
    setBounds(34451, 10, 10, 0, Interface); //Background
	
	setBounds(34452, 162, 47, 1, Interface);
    setBounds(34453, 162, 47, 2, Interface);
    setBounds(34454, 263, 47, 3, Interface);
    setBounds(34455, 263, 47, 4, Interface);
    setBounds(34456, 364, 47, 5, Interface);
    setBounds(34457, 364, 47, 6, Interface);
    setBounds(34458, 162, 107, 7, Interface);
    setBounds(34459, 162, 107, 8, Interface);
    setBounds(34460, 263, 107, 9, Interface);
    setBounds(34461, 263, 107, 10, Interface);
    setBounds(34462, 364, 107, 11, Interface);
    setBounds(34463, 364, 107, 12, Interface);
    setBounds(34464, 162, 166, 13, Interface);
    setBounds(34465, 162, 166, 14, Interface);
    setBounds(34466, 263, 166, 15, Interface);
    setBounds(34467, 263, 166, 16, Interface);
    setBounds(34468, 364, 166, 17, Interface);
    setBounds(34469, 364, 166, 18, Interface);
    setBounds(34470, 162, 226, 19, Interface);
	setBounds(34471, 162, 226, 20, Interface);
    setBounds(34472, 263, 226, 21, Interface);
    setBounds(34473, 263, 226, 22, Interface);
    setBounds(34474, 364, 226, 23, Interface);
    setBounds(34475, 364, 226, 24, Interface);
    setBounds(34476, 38, 26, 25, Interface);
    setBounds(34477, 142, 23, 26, Interface);
    setBounds(34478, 63, 56, 27, Interface);
    setBounds(34479, 63, 126, 28, Interface);
    setBounds(34480, 63, 90, 29, Interface);
    setBounds(34481, 63, 162, 30, Interface);
    setBounds(34482, 94, 26, 31, Interface);
    setBounds(34483, 323, 291, 60, Interface);
    setBounds(34484, 403, 291, 61, Interface);
	setBounds(34486, 199, 60, 34, Interface);
    setBounds(34487, 301, 60, 35, Interface);
    setBounds(34488, 403, 60, 36, Interface);
    setBounds(34489, 195, 120, 37, Interface);
    setBounds(34490, 301, 120, 38, Interface);
    setBounds(34491, 403, 120, 39, Interface);
    setBounds(34492, 199, 180, 40, Interface);
    setBounds(34493, 301, 180, 41, Interface);
    setBounds(34494, 399, 180, 42, Interface);
    setBounds(34495, 199, 240, 43, Interface);
	setBounds(34496, 299, 240, 44, Interface);
    setBounds(34497, 403, 240, 45, Interface);
    setBounds(34498, 291, 85, 46, Interface);
    setBounds(34499, 190, 85, 47, Interface);
    setBounds(34500, 392, 85, 48, Interface);
    setBounds(34501, 291, 145, 49, Interface);
    setBounds(34502, 186, 145, 50, Interface);
    setBounds(34503, 392, 145, 51, Interface);
    setBounds(34504, 291, 205, 52, Interface);
    setBounds(34505, 190, 205, 53, Interface);
    setBounds(34506, 392, 205, 54, Interface);
    setBounds(34507, 190, 265, 55, Interface);
    setBounds(34508, 291, 265, 56, Interface);
    setBounds(34509, 392, 265, 57, Interface);
	setBounds(34510, 291, 286, 32, Interface);
    setBounds(34511, 291, 286, 33, Interface);
    setBounds(34512, 378, 286, 58, Interface);
    setBounds(34513, 378, 286, 59, Interface);
	setBounds(34514, 20, 45, 62, Interface);
    setBounds(34515, 20, 81, 63, Interface);
    setBounds(34516, 20, 116, 64, Interface);
    setBounds(34517, 20, 149, 65, Interface);
    setBounds(34518, 480, 17, 66, Interface);
    setBounds(34519, 480, 17, 67, Interface);
    setBounds(34520, 20, 185, 68, Interface);
    setBounds(34521, 63, 198, 69, Interface);
	}
	public static void addToItemGroup(RSInterface rsi, int w, int h, int x, int y, boolean actions, String action1, String action2, String action3) {
		rsi.width = w;
		rsi.height = h;
		rsi.inv = new int[w * h];
		rsi.invStackSizes = new int[w * h];
		rsi.usableItemInterface = false;
		rsi.isInventoryInterface = false;
		rsi.invSpritePadX = x;
		rsi.invSpritePadY = y;
		rsi.spritesX = new int[20];
		rsi.spritesY = new int[20];
		rsi.sprites = new Sprite[20];
		rsi.itemActions = new String[5];
		if(actions) {
		rsi.itemActions[0] = action1;
		rsi.itemActions[1] = action2;
		rsi.itemActions[2] = action3;
		}
		rsi.type = 2;
	}
	

	
	public static void oakLectern(TextDrawingArea[] TDA) {
	RSInterface rsinterface = addTabInterface(45454);
	addSprite(45455, 1, "Interfaces/tabCreation/SPRITE");
	addHoverButton(45456, "Interfaces/GrandExchange/close", 1, 16, 16, "Close", 0, 45457, 1);
	addHoveredButton(45457, "Interfaces/GrandExchange/close", 2, 16, 16, 45458);
	
	
	addHoverButton(45459, "Interfaces/tabCreation/HOVER", 0, 80, 80, "Create Camelot tab-teleport", 0, 45460, 1);
    addHoveredButton(45460, "Interfaces/tabCreation/HOVER", 0, 80, 80, 45461);	
	
	addHoverButton(45462, "Interfaces/tabCreation/HOVER", 1, 80, 80, "Create Falador tab-teleport", 0, 45463, 1);
    addHoveredButton(45463, "Interfaces/tabCreation/HOVER", 1, 80, 80, 45464);
	
	
	rsinterface.totalChildren(7);
	rsinterface.child(0, 45455, 15, 22);
	rsinterface.child(1, 45456, 480, 27);
	rsinterface.child(2, 45457, 480, 27);
	rsinterface.child(3, 45459, 55, 72);
	rsinterface.child(4, 45460, 55, 72);
	rsinterface.child(5, 45462, 136, 73);
	rsinterface.child(6, 45463, 136, 73);
	
	}	
	
	public static void demonLectern(TextDrawingArea[] TDA) {
	RSInterface rsinterface = addTabInterface(54544);
	addSprite(54545, 2, "Interfaces/tabCreation/SPRITE");
	addHoverButton(54546, "Interfaces/GrandExchange/close", 1, 16, 16, "Close", 0, 54547, 1);
	addHoveredButton(54547, "Interfaces/GrandExchange/close", 2, 16, 16, 54548);
	
	
	addHoverButton(54549, "Interfaces/tabCreation/HOVER", 0, 80, 80, "Create Camelot-teleport tab", 0, 54460, 1);
    addHoveredButton(54460, "Interfaces/tabCreation/HOVER", 0, 80, 80, 54461);	
	
	addHoverButton(54462, "Interfaces/tabCreation/HOVER", 1, 80, 80, "Create Falador-teleport tab", 0, 54463, 1);
    addHoveredButton(54463, "Interfaces/tabCreation/HOVER", 1, 80, 80, 54464);

	addHoverButton(54465, "Interfaces/tabCreation/HOVER", 2, 80, 80, "Create Varrock-teleport tab", 0, 54466, 1);
    addHoveredButton(54466, "Interfaces/tabCreation/HOVER", 2, 80, 80, 54467);	
	
	addHoverButton(54468, "Interfaces/tabCreation/HOVER", 3, 80, 80, "Create Lumbridge-teleport tab", 0, 54469, 1);
    addHoveredButton(54469, "Interfaces/tabCreation/HOVER", 3, 80, 80, 54470);
	
	
	rsinterface.totalChildren(11);
	rsinterface.child(0, 54545, 15, 22);
	rsinterface.child(1, 54546, 480, 27);
	rsinterface.child(2, 54547, 480, 27);
	rsinterface.child(3, 54549, 55, 72);
	rsinterface.child(4, 54460, 55, 72);
	rsinterface.child(5, 54462, 136, 73);
	rsinterface.child(6, 54463, 136, 73);
	rsinterface.child(7, 54465, 217, 73);
	rsinterface.child(8, 54466, 217, 73);
	rsinterface.child(9, 54468, 298, 73);
	rsinterface.child(10, 54469, 298, 73);
	
	}	
	
	public static void eagleLectern(TextDrawingArea[] TDA) {
	RSInterface rsinterface = addTabInterface(65454);
	addSprite(65455, 0, "Interfaces/tabCreation/SPRITE");
	addHoverButton(65456, "Interfaces/GrandExchange/close", 1, 16, 16, "Close", 0, 65457, 1);
	addHoveredButton(65457, "Interfaces/GrandExchange/close", 2, 16, 16, 65458);
	
	
	addHoverButton(65459, "Interfaces/tabCreation/HOVER", 0, 80, 80, "Create Camelot-teleport tab", 0, 65460, 1);
    addHoveredButton(65460, "Interfaces/tabCreation/HOVER", 0, 80, 80, 65461);	
	
	addHoverButton(65462, "Interfaces/tabCreation/HOVER", 1, 80, 80, "Create Falador-teleport tab", 0, 65463, 1);
    addHoveredButton(65463, "Interfaces/tabCreation/HOVER", 1, 80, 80, 65464);

	addHoverButton(65465, "Interfaces/tabCreation/HOVER", 2, 80, 80, "Create Varrock-teleport tab", 0, 65466, 1);
    addHoveredButton(65466, "Interfaces/tabCreation/HOVER", 2, 80, 80, 65467);	
	
	addHoverButton(65468, "Interfaces/tabCreation/HOVER", 3, 80, 80, "Create Lumbridge-teleport tab", 0, 65469, 1);
    addHoveredButton(65469, "Interfaces/tabCreation/HOVER", 3, 80, 80, 65470);
	
	addHoverButton(65471, "Interfaces/tabCreation/HOVER", 4, 80, 80, "Create Ardougne-teleport tab", 0, 65472, 1);
    addHoveredButton(65472, "Interfaces/tabCreation/HOVER", 4, 80, 80, 65473);
		
	addHoverButton(65474, "Interfaces/tabCreation/HOVER", 5, 80, 80, "Create House-teleport tab", 0, 65475, 1);
    addHoveredButton(65475, "Interfaces/tabCreation/HOVER", 5, 80, 80, 65476);
	
	
	rsinterface.totalChildren(15);
	rsinterface.child(0, 65455, 15, 22);
	rsinterface.child(1, 65456, 480, 27);
	rsinterface.child(2, 65457, 480, 27);
	rsinterface.child(3, 65459, 55, 72);
	rsinterface.child(4, 65460, 55, 72);
	rsinterface.child(5, 65462, 136, 73);
	rsinterface.child(6, 65463, 136, 73);
	rsinterface.child(7, 65465, 217, 73);
	rsinterface.child(8, 65466, 217, 73);
	rsinterface.child(9, 65468, 298, 73);
	rsinterface.child(10, 65469, 298, 73);
	rsinterface.child(11, 65471, 379, 73);
	rsinterface.child(12, 65472, 379, 73);
	rsinterface.child(13, 65474, 55, 191);
	rsinterface.child(14, 65475, 55, 191);
	
	}
	
	public static void collectBuy(TextDrawingArea[] TDA) {
		RSInterface rsinterface = addTabInterface(53700);
		int x = 9;
		addSprite(53701, 1, "Interfaces/GE/buyCollect");
		addHoverButton(53702, "Interfaces/GrandExchange2/close", 1, 16, 16, "Close", 0, 53703, 1);
	        addHoveredButton(53703, "Interfaces/GrandExchange2/close", 2, 16, 16, 53704);
		addHoverButton(53758, "Interfaces/GrandExchange2/sprite", 25, 29, 23, "Back", 0, 53759, 1);
	        addHoveredButton(53759, "Interfaces/GrandExchange2/sprite", 26, 29, 23, 53760);
	 	addText(53769, "Choose an item to exchange", TDA, 0, 0x96731A, false, true);
	 	addText(53770, "Select an item from your invertory to sell.", TDA, 0, 0x958E60, false, true);
		addText(53771, "0", TDA, 0, 0xB58338, true, true);
	 	addText(53772, "1 gp", TDA, 0, 0xB58338, true, true);
	 	addText(53773, "0 gp", TDA, 0, 0xB58338, true, true);
		addHoverButton(53793, "Interfaces/GE/collectNoHover", 1, 40, 36, "[GE]", 0, 53794, 1);
        addHoveredButton(53794, "Interfaces/GE/collectHover", 1, 40, 36, 53795);
		addHoverButton(53796, "Interfaces/GE/collectNoHover", 1, 40, 36, "[GE]", 0, 53797, 1);
        addHoveredButton(53797, "Interfaces/GE/collectHover", 1, 40, 36, 53798);
		RSInterface add = addInterface(53780);
	 	addToItemGroup(add, 1, 1, 24, 24, true, "[GE]", "[GE]", "[GE]");
	 	add = addInterface(53781);
	 	addToItemGroup(add, 1, 1, 24, 24, true, "[ITEM]Collect", "[GE]", "[GE]");
	 	add = addInterface(53782);
	 	addToItemGroup(add, 1, 1, 24, 24, true, "[COINS]Collect", "[GE]", "[GE]");
		addText(53784, "", TDA, 0, 0xFFFF00, false, true);
		addText(53785, "", TDA, 0, 0xFFFF00, false, true);
		addText(53787, "N/A", TDA, 0, 0xB58338, false, true);
		addText(53788, "", TDA, 0, 0xFFFF00, true, true);
		addText(53789, "", TDA, 0, 0xFFFF00, true, true);
		addHoverButton(53800, "Interfaces/GE/clickAbort", 1, 20, 20, "Abort offer", 0, 53801, 1);
        addHoveredButton(53801, "Interfaces/GE/clickAbort", 2, 20, 20, 53802);
	        rsinterface.totalChildren(24);
	        rsinterface.child(0, 53701, 4+x, 23);//385, 260
	        rsinterface.child(1, 53702, 464+x, 33);//435, 260
	        rsinterface.child(2, 53703, 464+x, 33);
	        rsinterface.child(3, 53758, 19+x, 284);
	        rsinterface.child(4, 53759, 19+x, 284);
	        rsinterface.child(5, 53769, 202+x, 71);
	        rsinterface.child(6, 53770, 202+x, 98);	        
	        rsinterface.child(7, 53771, 142+x, 185);
	        rsinterface.child(8, 53772, 354+x, 185);
	        rsinterface.child(9, 53773, 252+x, 246);
	        rsinterface.child(10, 53793, 386+x, 256+23);	        
	        rsinterface.child(11, 53794, 386+x, 256+23);
	        rsinterface.child(12, 53796, 435+x, 256+23);
	        rsinterface.child(13, 53797, 435+x, 256+23);
			rsinterface.child(14, 53780, 97+x, 97);
	        rsinterface.child(15, 53781, 385+4+x, 260+23);
			rsinterface.child(16, 53782, 435+4+x, 260+23);
	        rsinterface.child(17, 53784, 385+4+x, 260+23);
			rsinterface.child(18, 53785, 435+4+x, 260+23);
			rsinterface.child(19, 53787, 108, 136);
			rsinterface.child(20, 53788, 214+x, 249+23);
			rsinterface.child(21, 53789, 214+x, 263+23);
			rsinterface.child(22, 53800, 345+x, 250+23);
			rsinterface.child(23, 53801, 345+x, 250+23);
	}


	public static void addSprite(int id, String spriteName) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5; //5
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte) 0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(spriteName);
		tab.sprite2 = imageLoader(spriteName);
		tab.width = 512;
		tab.height = 334;
	}
		public static void addPriceChecker(int index) {
			RSInterface rsi = interfaceCache[index] = new RSInterface();
	   		rsi.itemActions = new String[10];
	    	rsi.spritesX = new int[20];
	    	rsi.invStackSizes = new int[25];
	    	rsi.inv = new int[30];
			rsi.spritesY = new int[20];
	   		rsi.children = new int[0];
	   	 	rsi.childX = new int[0];
	    	rsi.childY = new int[0];
			rsi.itemActions[0] = "Take 1";//need to do it like this o.o how its 100 frame ids
	   		rsi.itemActions[1] = "Take 5";
	   		rsi.itemActions[2] = "Take 10";
	   		rsi.itemActions[3] = "Take All";
			rsi.itemActions[4] = "Take X";
	    	rsi.centerText = true;
			rsi.aBoolean227 = false;
	    	rsi.aBoolean235 = false;
	   		rsi.usableItemInterface = false;
			rsi.isInventoryInterface = false;
	   		rsi.aBoolean259 = true;
	   		rsi.textShadow = false;
	   		rsi.invSpritePadX = 40;
	   		rsi.invSpritePadY = 28; 
			rsi.height = 5;
			rsi.width = 5;
			rsi.parentID = 18246;
	   		rsi.id = 4393;
	    	rsi.type = 2;
		}
	
public static void newQuestInterface(TextDrawingArea[] TDA) {
	RSInterface Interface = addTabInterface(23500);

	setChildren(11, Interface);
	addSprite(23501, 1, "Interfaces/NewQuest/BACKGROUND");// Background
	addSprite(23511, 1, "Interfaces/NewQuest/IMAGE");//Star
	addHover(23502, 3, 0, 23503, 1, "", 17, 17, "Close");//Close Button
	addHovered(23503, 2, "", 17, 17, 23504);//Close Button
	addText(23515, "Start Point text here", 0xFF9900, false, true, 0,0);//Start Point Text
	addText(23513, "Start Requirements text here", 0xFF9900, false, true, 0,0);//Start Requirements Items Text
	addText(23514, "Required items text here", 0xFF9900, false, true, 0,0);//Required Items Text
	addText(23508, "Combat text here", 0xFF9900, false, true, 0,0);//Combat Text
	addText(23509, "Rewards text here 1st line", 0xFF9900, false, true, 0,0);//Rewards Text	
	addText(23516, "Rewards text here 2nd line", 0xFF9900, false, true, 0,0);//Rewards Text	
    addText(23510, "Not Started", TDA, 2, 0xFF9900, false, true);//Quest Progress Text
    addText(23512, "Quest Name", TDA, 2, 0xFF9900, false, true);//Title

	setBounds(23501, 12, 6, 0, Interface);//background
	setBounds(23511, 26, 254, 1, Interface);//Star
	setBounds(23502, 475, 8, 2, Interface);//Close Button
	setBounds(23515, 137, 44, 3, Interface);//Start Point Text
	setBounds(23513, 137, 84, 4, Interface);//Start Requirements Items Text
	setBounds(23514, 137, 134, 5, Interface);//Required Items Text
	setBounds(23508, 137, 184, 6, Interface);//Combat Text
	setBounds(23509, 137, 224, 7, Interface);//Rewards Text
	setBounds(23516, 137, 234, 10, Interface);//Rewards Text
	setBounds(23510, 320, 278, 8, Interface);//Progress
	setBounds(23512, 204, 10, 9, Interface);//Title
}		
	

	

	
		public static void Domtowershop(TextDrawingArea tda[])
    {
        RSInterface rsinterface = addTabInterface(17955);
        addSprite(39525, 0, "Interfaces/Domtowershop/Main");
        addHoverButton(39526, "Interfaces/Domtowershop/basichelmnormal", 0, 37, 75, "Buy Serjeant Cap", -1, 39527, 1);
        addHoveredButton(39527, "Interfaces/Domtowershop/basichelmhov", 1, 37, 75, 39528);
        addHoverButton(39529, "Interfaces/Domtowershop/basicplatenormal", 0, 37, 75, "Buy Serjeant Top", -1, 39530, 1);
        addHoveredButton(39530, "Interfaces/Domtowershop/basicplatehov", 1, 37, 75, 39531);
        addHoverButton(39532, "Interfaces/Domtowershop/basiclegsnormal", 0, 37, 75, "Buy Serjeant Trousers", -1, 39533, 1);
        addHoveredButton(39533, "Interfaces/Domtowershop/basiclegshov", 1, 37, 75, 39534);
        addHoverButton(39535, "Interfaces/Domtowershop/basicshieldnormal", 0, 37, 75, "Buy Serjeant Gloves", -1, 39536, 1);
        addHoveredButton(39536, "Interfaces/Domtowershop/basicshieldhov", 1, 37, 75, 39537);
        addHoverButton(39538, "Interfaces/Domtowershop/basicswordnormal", 0, 37, 75, "Buy Serjeant Boots", -1, 39539, 1);
        addHoveredButton(39539, "Interfaces/Domtowershop/basicswordhov", 1, 37, 75, 39540);
        addHoverButton(39541, "Interfaces/Domtowershop/medhelmnormal", 0, 37, 75, "Buy Commander Cap", -1, 39542, 1);
        addHoveredButton(39542, "Interfaces/Domtowershop/medhelmhov", 1, 37, 75, 39543);
        addHoverButton(39544, "Interfaces/Domtowershop/medplatenormal", 0, 37, 75, "Buy Commander Top", -1, 39545, 1);
        addHoveredButton(39545, "Interfaces/Domtowershop/medplatehov", 1, 37, 75, 39546);
        addHoverButton(39547, "Interfaces/Domtowershop/medlegsnormal", 0, 37, 75, "Buy Commander Trousers", -1, 39548, 1);
        addHoveredButton(39548, "Interfaces/Domtowershop/medlegshov", 1, 37, 75, 39549);
        addHoverButton(39550, "Interfaces/Domtowershop/medshieldnormal", 0, 37, 75, "Buy Commander Gloves", -1, 39551, 1);
        addHoveredButton(39551, "Interfaces/Domtowershop/medshieldhov", 1, 37, 75, 39552);
        addHoverButton(39553, "Interfaces/Domtowershop/medswordnormal", 0, 37, 75, "Buy Commander Boots", -1, 39554, 1);
        addHoveredButton(39554, "Interfaces/Domtowershop/medswordhov", 1, 37, 75, 39555);
        addHoverButton(39556, "Interfaces/Domtowershop/hardhelmnormal", 0, 37, 75, "Buy War-chief Cap", -1, 39557, 1);
        addHoveredButton(39557, "Interfaces/Domtowershop/hardhelmhov", 1, 37, 75, 39558);
        addHoverButton(39559, "Interfaces/Domtowershop/hardplatenormal", 0, 37, 75, "Buy War-chief Top", -1, 39560, 1);
        addHoveredButton(39560, "Interfaces/Domtowershop/hardplatehov", 1, 37, 75, 39561);
        addHoverButton(39562, "Interfaces/Domtowershop/hardlegsnormal", 0, 37, 75, "Buy War-chief Trousers", -1, 39563, 1);
        addHoveredButton(39563, "Interfaces/Domtowershop/hardlegshov", 1, 37, 75, 39564);
        addHoverButton(39565, "Interfaces/Domtowershop/hardshieldnormal", 0, 37, 75, "Buy War-chief Gloves", -1, 39566, 1);
        addHoveredButton(39566, "Interfaces/Domtowershop/hardshieldhov", 1, 37, 75, 39567);
        addHoverButton(39568, "Interfaces/Domtowershop/hardswordnormal", 0, 37, 75, "Buy War-chief Boots", -1, 39569, 1);
        addHoveredButton(39569, "Interfaces/Domtowershop/hardswordhov", 1, 37, 75, 39570);
        addHoverButton(39571, "Interfaces/Domtowershop/extremehelmnormal", 0, 37, 75, "Buy Lord Marshal Cap", -1, 39572, 1);
        addHoveredButton(39572, "Interfaces/Domtowershop/extremehelmhov", 1, 37, 75, 39573);
        addHoverButton(39574, "Interfaces/Domtowershop/extremeplatenormal", 0, 37, 75, "Buy Lord Marshal Top", -1, 39575, 1);
        addHoveredButton(39575, "Interfaces/Domtowershop/extremeplatehov", 1, 37, 75, 39576);
        addHoverButton(39577, "Interfaces/Domtowershop/extremelegsnormal", 0, 37, 75, "Buy Lord Marshal Trousers", -1, 39578, 1);
        addHoveredButton(39578, "Interfaces/Domtowershop/extremelegshov", 1, 37, 75, 39579);
        addHoverButton(39580, "Interfaces/Domtowershop/extremeshieldnormal", 0, 37, 75, "Buy Lord Marshal Gloves", -1, 39581, 1);
        addHoveredButton(39581, "Interfaces/Domtowershop/extremeshieldhov", 1, 37, 75, 39582);
        addHoverButton(39583, "Interfaces/Domtowershop/extremeswordnormal", 0, 37, 75, "Buy Lord Marshal Boots", -1, 39584, 1);
        addHoveredButton(39584, "Interfaces/Domtowershop/extremeswordhov", 1, 37, 75, 39585);
        addHoverButton(39586, "Interfaces/Domtowershop/decbuttonnormal", 0, 144, 30, "Special Clothing", -1, 39587, 1);
        addHoveredButton(39587, "Interfaces/Domtowershop/decbuttonhover", 1, 144, 30, 39588);
        addHoverButton(39589, "Interfaces/Domtowershop/consumebuttonnormal", 0, 144, 30, "Potions & Titles", -1, 39590, 1);
        addHoveredButton(39590, "Interfaces/Domtowershop/consumebuttonhover", 1, 144, 30, 39501);
        addHoverButton(39592, "Interfaces/Domtowershop/miscbuttonnormal", 0, 144, 30, "Weapons & Misc", -1, 39593, 1);
        addHoveredButton(39593, "Interfaces/Domtowershop/miscbuttonhover", 1, 144, 30, 39594);
        addHoverButton(39595, "Interfaces/Domtowershop/close", 0, 16, 16, "Close", -1, 39596, 1);
        addHoveredButton(39596, "Interfaces/Domtowershop/close", 1, 16, 16, 39597);
        addText(39598, "Dominion Tower Rewards", tda, 3, 0xff981f, true, true);
        addText(39599, "Items can be bought with Dominion Tower points only.", tda, 0, 0xffffff, true, true);
        addText(49599, "Special Clothing", tda, 1, 0, false, false);
        addText(39803, "Potions & Titles", tda, 1, 0, false, false);
        addText(39804, "Weapons & Misc", tda, 1, 0, false, false);
        rsinterface.totalChildren(54);
        rsinterface.child(0, 39525, 2, 19);
        rsinterface.child(1, 39526, 77, 90);
        rsinterface.child(2, 39527, 77, 90);
        rsinterface.child(3, 39529, 119, 90);
        rsinterface.child(4, 39530, 119, 90);
        rsinterface.child(5, 39532, 161, 90);
        rsinterface.child(6, 39533, 161, 90);
        rsinterface.child(7, 39535, 203, 90);
        rsinterface.child(8, 39536, 203, 90);
        rsinterface.child(9, 39538, 245, 90);
        rsinterface.child(10, 39539, 245, 90);
        rsinterface.child(11, 39541, 287, 90);
        rsinterface.child(12, 39542, 287, 90);
        rsinterface.child(13, 39544, 329, 90);
        rsinterface.child(14, 39545, 329, 90);
        rsinterface.child(15, 39547, 371, 90);
        rsinterface.child(16, 39548, 371, 90);
        rsinterface.child(17, 39550, 413, 90);
        rsinterface.child(18, 39551, 413, 90);
        rsinterface.child(19, 39553, 455, 90);
        rsinterface.child(20, 39554, 455, 90);
        rsinterface.child(21, 39556, 77, 190);
        rsinterface.child(22, 39557, 77, 190);
        rsinterface.child(23, 39559, 119, 190);
        rsinterface.child(24, 39560, 119, 190);
        rsinterface.child(25, 39562, 161, 190);
        rsinterface.child(26, 39563, 161, 190);
        rsinterface.child(27, 39565, 203, 190);
        rsinterface.child(28, 39566, 203, 190);
        rsinterface.child(29, 39568, 245, 190);
        rsinterface.child(30, 39569, 245, 190);
        rsinterface.child(31, 39571, 287, 190);
        rsinterface.child(32, 39572, 287, 190);
        rsinterface.child(33, 39574, 329, 190);
        rsinterface.child(34, 39575, 329, 190);
        rsinterface.child(35, 39577, 371, 190);
        rsinterface.child(36, 39578, 371, 190);
        rsinterface.child(37, 39580, 413, 190);
        rsinterface.child(38, 39581, 413, 190);
        rsinterface.child(39, 39583, 455, 190);
        rsinterface.child(40, 39584, 455, 190);
        rsinterface.child(41, 39586, 28, 44);
        rsinterface.child(42, 39587, 28, 44);
        rsinterface.child(43, 39589, 185, 44);
        rsinterface.child(44, 39590, 185, 44);
        rsinterface.child(45, 39592, 342, 44);
        rsinterface.child(46, 39593, 342, 44);
        rsinterface.child(47, 39595, 480, 22);
        rsinterface.child(48, 39596, 480, 22);
        rsinterface.child(49, 39598, 253, 22);
        rsinterface.child(50, 39599, 257, 294);
        rsinterface.child(51, 49599, 55, 51);
        rsinterface.child(52, 39803, 212, 51);
        rsinterface.child(53, 39804, 369, 51);
    }
	public static void addTextButton(int i, String s, String tooltip, int k, boolean l, boolean m, TextDrawingArea[] TDA, int j, int w) {
		RSInterface rsinterface = addInterface(i);
		rsinterface.parentID = i;
		rsinterface.id = i;
		//rsinterface.interfaceType = 4;
		rsinterface.atActionType = 1;
		rsinterface.width = w;
		rsinterface.height = 16;
		rsinterface.contentType = 0;
		rsinterface.opacity = (byte)0xFF981F;
	/*	rsinterface.textCentered = l;
		rsinterface.textShadowed = m;*/
		rsinterface.textDrawingAreas = TDA[j];
	//	rsinterface.disabledMessage = s;
	//	rsinterface.enabledMessage = "";
	//	rsinterface.enabledColor = 0xFF981F;
	//	rsinterface.disabledColor = 0xFF981F;
		rsinterface.tooltip = tooltip;
	}
    public static void slayerInterface(TextDrawingArea[] tda) {
        RSInterface rsInterface = addInterface(41000);
        addSprite(41001, 1, "Interfaces/SlayerInterface/IMAGE");
        addHoverButton(41002, "Interfaces/SlayerInterface/IMAGE", 4, 16, 16, "Close window", 0, 41003, 1);
        addHoveredButton(41003, "Interfaces/SlayerInterface/IMAGE", 5, 16, 16, 41004);
        addHoverButton(41005, "", 0, 85, 20, "Buy", 0, 41006, 1);
        addHoverButton(41007, "", 0, 85, 20, "Learn", 0, 41008, 1);
        addHoverButton(41009, "", 0, 85, 20, "Assignment", 0, 41010, 1);
        addText(41011, "Slayer Points: ", tda, 3, 0xFF981F);
        addTextButton(41012, "Slayer Experience                           50", "Buy Slayer Experience", 0xFF981F, false, true, tda, 1, 400);
        addTextButton(41013, "Slayer's Respite                             25", "Buy Slayer's Respite", 0xFF981F, false, true, tda, 1, 401);
        addTextButton(41014, "Slayer Darts                                     35", "Buy Slayer Darts", 0xFF981F, false, true, tda, 1, 402);
        addTextButton(41015, "Broad Arrows                                    25", "Buy Broad Arrows", 0xFF981F, false, true, tda, 1, 403);
        setChildren(11, rsInterface);
        rsInterface.child(0, 41001, 12, 10);
        rsInterface.child(1, 41002, 473, 20);
        rsInterface.child(2, 41003, 473, 20);
        rsInterface.child(3, 41005, 21, 23);
        rsInterface.child(4, 41007, 107, 23);
        rsInterface.child(5, 41009, 193, 23);
        rsInterface.child(6, 41011, 98, 74);
        rsInterface.child(7, 41012, 124, 128);
        rsInterface.child(8, 41013, 125, 160);
        rsInterface.child(9, 41014, 125, 190);
        rsInterface.child(10, 41015, 124, 220);
        
    }
    
    public static void slayerInterfaceSub1(TextDrawingArea[] tda) {
        RSInterface rsInterface = addInterface(41500);
        addSprite(41501, 2, "Interfaces/SlayerInterface/IMAGE");
        addHoverButton(41502, "Interfaces/SlayerInterface/IMAGE", 4, 16, 16, "Close window", 0, 41503, 1);
        addHoveredButton(41503, "Interfaces/SlayerInterface/IMAGE", 5, 16, 16, 41504);
        addHoverButton(41505, "", 0, 85, 20, "Buy", 0, 41506, 1);
        addHoverButton(41507, "", 0, 85, 20, "Learn", 0, 41508, 1);
        addHoverButton(41509, "", 0, 85, 20, "Assignment", 0, 41510, 1);
        addText(41511, "Slayer Points: ", tda, 3, 0xFF981F);
        setChildren(7, rsInterface);
        rsInterface.child(0, 41501, 12, 10);
        rsInterface.child(1, 41502, 473, 20);
        rsInterface.child(2, 41503, 473, 20);
        rsInterface.child(3, 41505, 21, 23);
        rsInterface.child(4, 41507, 107, 23);
        rsInterface.child(5, 41509, 193, 23);
        rsInterface.child(6, 41511, 98, 74);
    }
    
    public static void slayerInterfaceSub2(TextDrawingArea[] tda) {
        RSInterface rsInterface = addInterface(42000);
        addSprite(42001, 3, "Interfaces/SlayerInterface/IMAGE");
        addHoverButton(42002, "Interfaces/SlayerInterface/IMAGE", 4, 16, 16, "Close window", 0, 42003, 1);
        addHoveredButton(42003, "Interfaces/SlayerInterface/IMAGE", 5, 16, 16, 42004);
        addHoverButton(42005, "", 0, 85, 20, "Buy", 0, 42006, 1);
        addHoverButton(42007, "", 0, 85, 20, "Learn", 0, 42008, 1);
        addHoverButton(42009, "", 0, 85, 20, "Assignment", 0, 42010, 1);
        addText(42011, "Slayer Points: ", tda, 3, 0xFF981F);
        addTextButton(42012, "Cancel Task", "Temporarily cancel your current slayer task", 0xFF981F, false, true, tda, 1, 300);
        addTextButton(42013, "Remove Task permanently", "Permanently remove this monster as a task", 0xFF981F, false, true, tda, 1, 305);
        addText(42014, "line 1", tda, 1, 0xFF981F);
        addText(42015, "line 2", tda, 1, 0xFF981F);
        addText(42016, "line 3", tda, 1, 0xFF981F);
        addText(42017, "line 4", tda, 1, 0xFF981F);
        addButton(42018, 6, "Interfaces/SlayerInterface/IMAGE", "Delete removed slayer task");
        addButton(42019, 6, "Interfaces/SlayerInterface/IMAGE", "Delete removed slayer task");
        addButton(42020, 6, "Interfaces/SlayerInterface/IMAGE", "Delete removed slayer task");
        addButton(42021, 6, "Interfaces/SlayerInterface/IMAGE", "Delete removed slayer task");
        setChildren(17, rsInterface);
        rsInterface.child(0, 42001, 12, 10);
        rsInterface.child(1, 42002, 473, 20);
        rsInterface.child(2, 42003, 473, 20);
        rsInterface.child(3, 42005, 21, 23);
        rsInterface.child(4, 42007, 107, 23);
        rsInterface.child(5, 42009, 193, 23);
        rsInterface.child(6, 42011, 98, 74);
        rsInterface.child(7, 42012, 71, 127);
        rsInterface.child(8, 42013, 71, 146);
        rsInterface.child(9, 42014, 71, 216);
        rsInterface.child(10, 42015, 71, 234);
        rsInterface.child(11, 42016, 71, 252);
        rsInterface.child(12, 42017, 71, 270);
        rsInterface.child(13, 42018, 303, 215);
        rsInterface.child(14, 42019, 303, 233);
        rsInterface.child(15, 42020, 303, 251);
        rsInterface.child(16, 42021, 303, 269);
    }
 public static void skillTab602(TextDrawingArea[] tda) {
        RSInterface skill = addInterface(3917);
        addText(27203, "99", 0xFFFF00, false, true, -1, tda, 0);
        addText(27204, "99", 0xFFFF00, false, true, -1, tda, 0);
        addText(27205, "99", 0xFFFF00, false, true, -1, tda, 0);
        addText(27206, "99", 0xFFFF00, false, true, -1, tda, 0);
        int[] logoutID = {
            2450, 2451, 2452
        };
        int[] logoutID2 = {
            2458
        };
        for (int i: logoutID) {
            RSInterface Logout = interfaceCache[i];
            Logout.textColor = 0xFF981F;
            Logout.contentType = 0;
        }
        for (int i: logoutID2) {
            RSInterface Logout = interfaceCache[i];
            Logout.contentType = 0;
        }
        skill.totalChildren(4);
        skill.child(0, 27203, 158, 175);
        skill.child(1, 27204, 171, 186);
        skill.child(2, 27205, 158, 203);
        skill.child(3, 27206, 171, 214);
        String[] spriteNames = {
            "Attack", "HP", "Mine", "Strength", "Agility", "Smith", "Defence", "Herblore", "Fish", "Range", "Thief", "Cook", "Prayer", "Craft", "Fire", "Mage", "Fletch", "Wood", "Rune", "Slay", "Farm", "Construction", "Hunter", "Summon", "Dungeon"
        };
        int[] buttons = {
            8654, 8655, 8656, 8657, 8658, 8659, 8660, 8861, 8662, 8663, 8664, 8665, 8666, 8667, 8668, 8669, 8670, 8671, 8672, 12162, 13928, 27123, 27124, 27125, 27126
        };
        int[] hovers = {
            4040, 4076, 4112, 4046, 4082, 4118, 4052, 4088, 4124, 4058, 4094, 4130, 4064, 4100, 4136, 4070, 4106, 4142, 4160, 2832, 13917, 19005, 19006, 19007, 19008
        };
        /*int[][] text = { { 4004, 4005 }, { 4016, 4017 }, { 4028, 4029 },
                         { 4006, 4007 }, { 4018, 4019 }, { 4030, 4031 }, { 4008, 4009 },
                         { 4020, 4021 }, { 4032, 4033 }, { 4010, 4011 }, { 4022, 4023 },
                         { 4034, 4035 }, { 4012, 4013 }, { 4024, 4025 }, { 4036, 4037 },
                         { 4014, 4015 }, { 4026, 4027 }, { 4038, 4039 }, { 4152, 4153 },
                         { 12166, 12167 }, { 13926, 13927 }, { 18000, 18001 },
                         { 18166, 18170 }, { 18167, 18171 }, { 18168, 18172 } };*/
        int[][] text = {
            {
                4004, 4005
            }, {
                4016, 4017
            }, {
                4028, 4029
            }, {
                4006, 4007
            }, {
                4018, 4019
            }, {
                4030, 4031
            }, {
                4008, 4009
            }, {
                4020, 4021
            }, {
                4032, 4033
            }, {
                4010, 4011
            }, {
                4022, 4023
            }, {
                4034, 4035
            }, {
                4012, 4013
            }, {
                4024, 4025
            }, {
                4036, 4037
            }, {
                4014, 4015
            }, {
                4026, 4027
            }, {
                4038, 4039
            }, {
                4152, 4153
            }, {
                12166, 12167
            }, {
                13926, 13927
            }, {
                18165, 18169
            }, {
                18166, 18170
            }, {
                18167, 18171
            }, {
                18168, 18172
            }
        };
        int[] icons = {
            3965, 3966, 3967, 3968, 3969, 3970, 3971, 3972, 3973,
            3974, 3975, 3976, 3977, 3978, 3979, 3980, 3981, 3982, 4151,
            12165, 13925, 27127, 27128, 27129, 27130
        };
        int[][] buttonCoords = {
            {
                4, 4
            }, {
                66, 4
            }, {
                128, 4
            }, {
                4, 32
            }, {
                66, 32
            }, {
                128, 32
            }, {
                4, 60
            }, {
                66, 60
            }, {
                128, 60
            }, {
                4, 88
            }, {
                66, 88
            }, {
                128, 88
            }, {
                4, 116
            }, {
                66, 116
            }, {
                128, 116
            }, {
                4, 144
            }, {
                66, 144
            }, {
                128, 144
            }, {
                4, 172
            }, {
                66, 172
            }, {
                128, 172
            }, {
                4, 200
            }, {
                66, 200
            }, {
                128, 200
            }, {
                4, 229
            }
        };
        int[][] iconCoords = {
            {
                6, 6
            }, {
                69, 7
            }, {
                131, 6
            }, {
                9, 34
            }, {
                68, 33
            }, {
                131, 36
            }, {
                9, 64
            }, {
                67, 63
            }, {
                131, 61
            }, {
                7, 91
            }, {
                68, 94
            }, {
                133, 90
            }, {
                6, 118
            }, {
                70, 120
            }, {
                130, 118
            }, {
                6, 147
            }, {
                69, 146
            }, {
                132, 146
            }, {
                6, 173
            }, {
                69, 173
            }, {
                130, 174
            }, {
                6, 202
            }, {
                69, 201
            }, {
                131, 202
            }, {
                6, 230
            }
        };
        int[][] textCoords = {
            {
                31, 7, 44, 18
            }, {
                93, 7, 106, 18
            }, {
                155, 7, 168, 18
            }, {
                31, 35, 44, 46
            }, {
                93, 35, 106, 46
            }, {
                155, 35, 168, 46
            }, {
                31, 63, 44, 74
            }, {
                93, 63, 106, 74
            }, {
                155, 63, 168, 74
            }, {
                31, 91, 44, 102
            }, {
                93, 91, 106, 102
            }, {
                155, 91, 168, 102
            }, {
                31, 119, 44, 130
            }, {
                93, 119, 106, 130
            }, {
                155, 119, 168, 130
            }, {
                31, 149, 44, 158
            }, {
                93, 147, 106, 158
            }, {
                155, 147, 168, 158
            }, {
                31, 175, 44, 186
            }, {
                93, 175, 106, 186
            }, {
                155, 175, 168, 186
            }, {
                31, 203, 44, 214
            }, {
                93, 203, 106, 214
            }, {
                155, 203, 168, 214
            }, {
                31, 231, 44, 242
            }
        };
        int[][] newText = {
            {
                18165, 18166, 18167, 18168
            }, {
                18169, 18170, 18171, 18172
            }
        };
        for (int i = 0; i < hovers.length; i++) {
            createSkillHover(hovers[i], 205 + i);
            addSkillButton(buttons[i]);
            addImage(icons[i], "Player/Skill/" + spriteNames[i]);
        }
        for (int i = 0; i < 4; i++) {
            addSkillText(newText[0][i], false, i + 21);
            addSkillText(newText[1][i], true, i + 21);
        }
        skill.children(icons.length + (text.length * 2) + hovers.length + buttons.length + 1);
        int frame = 0;
        RSInterface totalLevel = interfaceCache[3984];
        totalLevel.message = "@yel@Total level: %1";
        totalLevel.textDrawingAreas = fonts[2];
        skill.child(frame, 3984, 74, 237);
        frame++;
        for (int i = 0; i < buttons.length; i++) {
            skill.child(frame, buttons[i], buttonCoords[i][0], buttonCoords[i][1]);
            frame++;
        }
        for (int i = 0; i < icons.length; i++) {
            skill.child(frame, icons[i], iconCoords[i][0], iconCoords[i][1]);
            frame++;
        }
        for (int i = 0; i < text.length; i++) {
            skill.child(frame, text[i][0], textCoords[i][0], textCoords[i][1]);
            frame++;
        }
        for (int i = 0; i < text.length; i++) {
            skill.child(frame, text[i][1], textCoords[i][2], textCoords[i][3]);
            frame++;
        }
        for (int i = 0; i < hovers.length; i++) {
            skill.child(frame, hovers[i], buttonCoords[i][0], buttonCoords[i][1]);
            frame++;
        }
    }
    public void children(int total) {
        children = new int[total];
        childX = new int[total];
        childY = new int[total];
    }
    public static void createSkillHover(int id, int x) {
        RSInterface hover = addInterface(id);
        hover.type = 8;
        hover.message = "TESTING!";
        hover.contentType = x;
        hover.width = 60;
        hover.height = 28;
    }
    public static void addImage(int id, String s) {
        RSInterface image = addInterface(id);
        image.type = 5;
        image.atActionType = 0;
        image.contentType = 0;
        image.width = 100;
        image.height = 100;
        image.sprite1 = getSprite(s);
    }
    public static void addSkillText(int id, boolean max, int skill) {
        RSInterface text = addInterface(id);
        text.id = id;
        text.parentID = id;
        text.type = 4;
        text.atActionType = 0;
        text.width = 15;
        text.height = 12;
        text.textDrawingAreas = fonts[0];
        text.textShadow = true;
        text.centerText = true;
        text.textColor = 16776960;
        if (!max) {
            text.valueIndexArray = new int[1][];
            text.valueIndexArray[0] = new int[3];
            text.valueIndexArray[0][0] = 1;
            text.valueIndexArray[0][1] = skill;
            text.valueIndexArray[0][2] = 0;
        } else {
            text.valueIndexArray = new int[2][];
            text.valueIndexArray[0] = new int[3];
            text.valueIndexArray[0][0] = 1;
            text.valueIndexArray[0][1] = skill;
            text.valueIndexArray[0][2] = 0;
            text.valueIndexArray[1] = new int[1];
            text.valueIndexArray[1][0] = 0;
        }
        text.message = "%1";
    }
    public static Sprite getSprite(String s) {
        Sprite image;
        try {
            image = new Sprite(s);
            if (image != null) {
                return image;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return image;
    }
    public static void addSkillButton(int id) {
        RSInterface button = addInterface(id);
        button.type = 5;
        button.atActionType = 5;
        button.contentType = 0;
        button.width = 60;
        button.height = 27;
        button.sprite1 = CustomSpriteLoader(33225, "");
        button.sprite1 = getSprite("Player/Skill/Button");
        button.tooltip = "View";
    }
    public static void Domtowershop2(TextDrawingArea tda[])
    {
        RSInterface rsinterface = addTabInterface(17965);
        addSprite(39525, 0, "Interfaces/Domtowershop/Main");
        addHoverButton(39601, "Interfaces/Domtowershop/base", 0, 37, 75, "Buy Extreme attack (4)", -1, 39602, 1);
        addHoveredButton(39602, "Interfaces/Domtowershop/standhover", 1, 37, 75, 39603);
        addHoverButton(39604, "Interfaces/Domtowershop/boltsack", 0, 37, 75, "Buy Extreme strength (4)", -1, 39605, 1);
        addHoveredButton(39605, "Interfaces/Domtowershop/boltsack", 1, 37, 75, 39606);
        addHoverButton(39607, "Interfaces/Domtowershop/bow", 0, 37, 75, "Buy Extreme defence (4)", -1, 39608, 1);
        addHoveredButton(39608, "Interfaces/Domtowershop/bow", 1, 37, 75, 39609);
        addHoverButton(39610, "Interfaces/Domtowershop/chamber", 0, 37, 75, "Buy Extreme magic (4)", -1, 39611, 1);
        addHoveredButton(39611, "Interfaces/Domtowershop/chamber", 1, 37, 75, 39612);
        addHoverButton(39613, "Interfaces/Domtowershop/ammo", 0, 37, 75, "Buy Extreme ranging (4)", -1, 39614, 1);
        addHoveredButton(39614, "Interfaces/Domtowershop/ammo", 1, 37, 75, 38);
        addHoverButton(39813, "Interfaces/Domtowershop/ammo1", 0, 37, 75, "Buy Super prayer (4)", -1, 39814, 1);
        addHoveredButton(39814, "Interfaces/Domtowershop/ammo1", 1, 37, 75, 39815);
        addHoverButton(39816, "Interfaces/Domtowershop/ammo2", 0, 37, 75, "Buy Overload (4)", -1, 39817, 1);
        addHoveredButton(39817, "Interfaces/Domtowershop/ammo2", 1, 37, 75, 39818);
        addHoverButton(39616, "Interfaces/Domtowershop/melee", 0, 73, 75, "Buy Usertitle: Gladiator", -1, 39617, 1);
        addHoveredButton(39617, "Interfaces/Domtowershop/melee", 1, 73, 75, 39618);
        addHoverButton(39619, "Interfaces/Domtowershop/range", 0, 73, 75, "Buy Usertitle: survivor", -1, 39620, 1);
        addHoveredButton(39620, "Interfaces/Domtowershop/range", 1, 73, 75, 39621);
        addHoverButton(39622, "Interfaces/Domtowershop/magic", 0, 73, 75, "Buy Usertitle: Dominator", -1, 39623, 1);
        addHoveredButton(39623, "Interfaces/Domtowershop/mage", 1, 73, 75, 39624);
        addHoverButton(39586, "Interfaces/Domtowershop/decbuttonnormal", 0, 144, 30, "Special Clothing", -1, 39587, 1);
        addHoveredButton(39587, "Interfaces/Domtowershop/decbuttonhover", 1, 144, 30, 39588);
        addHoverButton(39589, "Interfaces/Domtowershop/consumebuttonnormal", 0, 144, 30, "Potions & Titles", -1, 39590, 1);
        addHoveredButton(39590, "Interfaces/Domtowershop/consumebuttonhover", 1, 144, 30, 39501);
        addHoverButton(39592, "Interfaces/Domtowershop/miscbuttonnormal", 0, 144, 30, "Weapons & Misc", -1, 39593, 1);
        addHoveredButton(39593, "Interfaces/Domtowershop/miscbuttonhover", 1, 144, 30, 39594);
        addHoverButton(39595, "Interfaces/Domtowershop/close", 0, 16, 16, "Close", -1, 39596, 1);
        addHoveredButton(39596, "Interfaces/Domtowershop/close", 1, 16, 16, 39597);
        addText(39598, "Dominion Tower Rewards", tda, 3, 0xff981f, true, true);
        addText(39599, "Items can be bought with Dominion Tower points only.", tda, 0, 0xffffff, true, true);
        addText(49599, "Special Clothing", tda, 1, 0, false, false);
        addText(39803, "Potions & Titles", tda, 1, 0, false, false);
        addText(39800, "Gladiator", tda, 0, 0xffffff, true, true);
        addText(39801, "Survivor", tda, 0, 0xffffff, true, true);
        addText(39802, "Dominator", tda, 0, 0xffffff, true, true);
        addText(39804, "Weapons & Misc", tda, 1, 0, false, false);
        rsinterface.totalChildren(37);
        rsinterface.child(0, 39525, 2, 19);
        rsinterface.child(1, 39601, 119, 90);
        rsinterface.child(2, 39602, 119, 90);
        rsinterface.child(3, 39604, 161, 90);
        rsinterface.child(4, 39605, 161, 90);
        rsinterface.child(5, 39607, 203, 90);
        rsinterface.child(6, 39608, 203, 90);
        rsinterface.child(7, 39610, 245, 90);
        rsinterface.child(8, 39611, 245, 90);
        rsinterface.child(9, 39613, 287, 90);
        rsinterface.child(10, 39614, 287, 90);
        rsinterface.child(11, 39616, 119, 190);
        rsinterface.child(12, 39617, 119, 190);
        rsinterface.child(13, 39619, 245, 190);
        rsinterface.child(14, 39620, 245, 190);
        rsinterface.child(15, 39622, 371, 190);
        rsinterface.child(16, 39623, 371, 190);
        rsinterface.child(17, 39586, 28, 44);
        rsinterface.child(18, 39587, 28, 44);
        rsinterface.child(19, 39589, 185, 44);
        rsinterface.child(20, 39590, 185, 44);
        rsinterface.child(21, 39592, 342, 44);
        rsinterface.child(22, 39593, 342, 44);
        rsinterface.child(23, 39595, 480, 22);
        rsinterface.child(24, 39596, 480, 22);
        rsinterface.child(25, 39598, 253, 22);
        rsinterface.child(26, 39599, 257, 294);
        rsinterface.child(27, 49599, 55, 51);
        rsinterface.child(28, 39800, 156, 220);
        rsinterface.child(29, 39801, 282, 220);
        rsinterface.child(30, 39802, 408, 220);
        rsinterface.child(31, 39803, 212, 51);
        rsinterface.child(36, 39804, 369, 51);
        rsinterface.child(32, 39813, 329, 90);
        rsinterface.child(33, 39814, 329, 90);
        rsinterface.child(34, 39816, 371, 90);
        rsinterface.child(35, 39817, 371, 90);
    }

    public static void Domtowershop3(TextDrawingArea tda[])
    {
        RSInterface rsinterface = addTabInterface(17957);
        addSprite(39525, 0, "Interfaces/Domtowershop/Main");
        addHoverButton(39700, "Interfaces/Domtowershop/saracape", 0, 37, 75, "Buy Gravite rapier", -1, 39701, 1);
        addHoveredButton(39701, "Interfaces/Domtowershop/saracape", 1, 37, 75, 39702);
        addHoverButton(39703, "Interfaces/Domtowershop/zamcape", 0, 37, 75, "Buy Gravite longsword", -1, 39704, 1);
        addHoveredButton(39704, "Interfaces/Domtowershop/zamcape", 1, 37, 75, 39705);
        addHoverButton(39706, "Interfaces/Domtowershop/sarahood", 0, 37, 75, "Buy Gravite 2h sword", -1, 39707, 1);
        addHoveredButton(39707, "Interfaces/Domtowershop/sarahood", 1, 37, 75, 39708);
        addHoverButton(39709, "Interfaces/Domtowershop/zamhood", 0, 37, 75, "Buy Gravite staff", -1, 39710, 1);
        addHoveredButton(39710, "Interfaces/Domtowershop/zamhood", 1, 37, 75, 39711);
        addHoverButton(39712, "Interfaces/Domtowershop/flag", 0, 37, 75, "Buy Arcane pulse necklace", -1, 39713, 1);
        addHoveredButton(39713, "Interfaces/Domtowershop/flag", 1, 37, 75, 39714);
        addHoverButton(39715, "Interfaces/Domtowershop/kill", 0, 37, 75, "Buy Arcane blast necklace", -1, 39716, 1);
        addHoveredButton(39716, "Interfaces/Domtowershop/kill", 1, 37, 75, 39717);
        addHoverButton(39718, "Interfaces/Domtowershop/hob", 0, 37, 75, "Buy Arcane stream necklace", -1, 39719, 1);
        addHoveredButton(39719, "Interfaces/Domtowershop/hob", 1, 37, 75, 39720);
        addHoverButton(39721, "Interfaces/Domtowershop/nerd", 0, 37, 75, "Buy Twisted bird skull necklace", -1, 39722, 1);
        addHoveredButton(39722, "Interfaces/Domtowershop/nerd", 1, 37, 75, 39723);
        addHoverButton(39724, "Interfaces/Domtowershop/nerdest", 0, 37, 75, "Buy Split dragontooth necklace", -1, 39725, 1);
        addHoveredButton(39725, "Interfaces/Domtowershop/nerdest", 1, 37, 75, 39726);
        addHoverButton(39727, "Interfaces/Domtowershop/guth", 0, 37, 75, "Buy Demon horn necklace", -1, 39728, 1);
        addHoveredButton(39728, "Interfaces/Domtowershop/guth", 1, 37, 75, 39729);
        addHoverButton(39730, "Interfaces/Domtowershop/sara", 0, 37, 75, "Buy Amulet of zealots", -1, 39731, 1);
        addHoveredButton(39731, "Interfaces/Domtowershop/sara", 1, 37, 75, 39732);
        addHoverButton(39733, "Interfaces/Domtowershop/zammy", 0, 37, 75, "Buy Nature staff", -1, 39734, 1);
        addHoveredButton(39734, "Interfaces/Domtowershop/zammy", 1, 37, 75, 39735);
        addHoverButton(39736, "Interfaces/Domtowershop/faith", 0, 37, 75, "Buy Blue cape", -1, 39737, 1);
        addHoveredButton(39737, "Interfaces/Domtowershop/faith", 1, 37, 75, 39738);
        addHoverButton(39739, "Interfaces/Domtowershop/free", 0, 37, 75, "Buy Red cape", -1, 39740, 1);
        addHoveredButton(39740, "Interfaces/Domtowershop/free", 1, 37, 75, 39741);
        addHoverButton(39586, "Interfaces/Domtowershop/decbuttonnormal", 0, 144, 30, "Special Clothing", -1, 39587, 1);
        addHoveredButton(39587, "Interfaces/Domtowershop/decbuttonhover", 1, 144, 30, 39588);
        addHoverButton(39589, "Interfaces/Domtowershop/consumebuttonnormal", 0, 144, 30, "Potions & Titles", -1, 39590, 1);
        addHoveredButton(39590, "Interfaces/Domtowershop/consumebuttonhover", 1, 144, 30, 39501);
        addHoverButton(39592, "Interfaces/Domtowershop/miscbuttonnormal", 0, 144, 30, "Weapons & Misc", -1, 39593, 1);
        addHoveredButton(39593, "Interfaces/Domtowershop/miscbuttonhover", 1, 144, 30, 39594);
        addHoverButton(39595, "Interfaces/Domtowershop/close", 0, 16, 16, "Close", -1, 39596, 1);
        addHoveredButton(39596, "Interfaces/Domtowershop/close", 1, 16, 16, 39597);
        addText(39598, "Dominion Tower Rewards", tda, 3, 0xff981f, true, true);
        addText(39599, "Items can be bought with Dominion Tower points only.", tda, 0, 0xffffff, true, true);
        addText(49599, "Special Clothing", tda, 1, 0, false, false);
        addText(39803, "Potions & Titles", tda, 1, 0, false, false);
        addText(39804, "Weapons & Misc", tda, 1, 0, false, false);
        rsinterface.totalChildren(42);
        rsinterface.child(0, 39525, 2, 19);
        rsinterface.child(1, 39700, 117, 90);
        rsinterface.child(2, 39701, 117, 90);
        rsinterface.child(3, 39703, 159, 90);
        rsinterface.child(4, 39704, 159, 90);
        rsinterface.child(5, 39706, 201, 90);
        rsinterface.child(6, 39707, 201, 90);
        rsinterface.child(7, 39709, 243, 90);
        rsinterface.child(8, 39710, 243, 90);
        rsinterface.child(9, 39712, 285, 90);
        rsinterface.child(10, 39713, 285, 90);
        rsinterface.child(11, 39715, 327, 90);
        rsinterface.child(12, 39716, 327, 90);
        rsinterface.child(13, 39718, 369, 90);
        rsinterface.child(14, 39719, 369, 90);
        rsinterface.child(15, 39721, 411, 90);
        rsinterface.child(16, 39722, 411, 90);
        rsinterface.child(17, 39724, 453, 90);
        rsinterface.child(18, 39725, 453, 90);
        rsinterface.child(19, 39727, 117, 190);
        rsinterface.child(20, 39728, 117, 190);
        rsinterface.child(21, 39730, 201, 190);
        rsinterface.child(22, 39731, 201, 190);
        rsinterface.child(23, 39733, 285, 190);
        rsinterface.child(24, 39734, 285, 190);
        rsinterface.child(25, 39736, 369, 190);
        rsinterface.child(26, 39737, 369, 190);
        rsinterface.child(27, 39739, 453, 190);
        rsinterface.child(28, 39740, 453, 190);
        rsinterface.child(29, 39586, 28, 44);
        rsinterface.child(30, 39587, 28, 44);
        rsinterface.child(31, 39589, 185, 44);
        rsinterface.child(32, 39590, 185, 44);
        rsinterface.child(33, 39592, 342, 44);
        rsinterface.child(34, 39593, 342, 44);
        rsinterface.child(35, 39595, 480, 22);
        rsinterface.child(36, 39596, 480, 22);
        rsinterface.child(37, 39598, 253, 22);
        rsinterface.child(38, 39599, 257, 294);
        rsinterface.child(39, 49599, 55, 51);
        rsinterface.child(40, 39803, 212, 51);
        rsinterface.child(41, 39804, 369, 51);
    }	
	
    public static void DominionTower1(TextDrawingArea tda[])
    {
        RSInterface rsinterface = addTabInterface(46200);
        addSprite(46201, 0, "Interfaces/Dominiontower1/SUM");
        addHoverButton(46202, "Interfaces/Dominiontower1/BOSS", 1, 100, 100, "Fight The Inadequacy!", -1, 46203, 1);
        addHoveredButton(46203, "Interfaces/Dominiontower1/BOSS", 9, 100, 100, 46204);
        addHoverButton(46205, "Interfaces/Dominiontower1/BOSS", 2, 100, 100, "Fight Giant Roc!", -1, 46206, 1);
        addHoveredButton(46206, "Interfaces/Dominiontower1/BOSS", 10, 100, 100, 46207);
        addHoverButton(46208, "Interfaces/Dominiontower1/BOSS", 3, 100, 100, "Fight The Untouchable!", -1, 46209, 1);
        addHoveredButton(46209, "Interfaces/Dominiontower1/BOSS", 11, 100, 100, 46210);
        addHoverButton(46211, "Interfaces/Dominiontower1/BOSS", 4, 100, 100, "Fight The Everlasting!", -1, 46212, 1);
        addHoveredButton(46212, "Interfaces/Dominiontower1/BOSS", 12, 100, 100, 46213);
        addHoverButton(46214, "Interfaces/Dominiontower1/BOSS", 5, 100, 100, "Fight Evil Chicken!", -1, 46215, 1);
        addHoveredButton(46215, "Interfaces/Dominiontower1/BOSS", 13, 100, 100, 46216);
        addHoverButton(46217, "Interfaces/Dominiontower1/BOSS", 6, 100, 100, "Fight Giant scarab!", -1, 46218, 1);
        addHoveredButton(46218, "Interfaces/Dominiontower1/BOSS", 14, 100, 100, 46219);
        addHoverButton(46220, "Interfaces/Dominiontower1/BOSS", 7, 100, 100, "Fight Bouncer!", -1, 46221, 1);
        addHoveredButton(46221, "Interfaces/Dominiontower1/BOSS", 15, 100, 100, 46222);
        addHoverButton(46223, "Interfaces/Dominiontower1/BOSS", 8, 100, 100, "Fight Black Knight Titan!", -1, 46224, 1);
        addHoveredButton(46224, "Interfaces/Dominiontower1/BOSS", 16, 100, 100, 46225);
        addHoverButton(45026, "Interfaces/Dominterface/BUTTON", 3, 100, 34, "Not now", -1, 45027, 1);
        addHoveredButton(45027, "Interfaces/Dominterface/BUTTON", 4, 100, 34, 44028);
        addHoverButton(45029, "Interfaces/Dominterface/BUTTON", 3, 100, 34, "Help", -1, 45030, 1);
        addHoveredButton(45030, "Interfaces/Dominterface/BUTTON", 4, 100, 34, 45031);
        addText(46232, "Dominion Tower Med Class", tda, 1, 0xf5c036, true, true);
        addText(46233, "Not now", tda, 1, 0xf5c036, true, true);
        addText(46234, "Help", tda, 1, 0xf5c036, true, true);
        rsinterface.totalChildren(24);
        rsinterface.child(0, 46201, 0, 0);
        rsinterface.child(1, 46202, 11, 41);
        rsinterface.child(2, 46203, 11, 41);
        rsinterface.child(3, 46205, 133, 41);
        rsinterface.child(4, 46206, 133, 41);
        rsinterface.child(5, 46208, 277, 41);
        rsinterface.child(6, 46209, 277, 41);
        rsinterface.child(7, 46211, 400, 41);
        rsinterface.child(8, 46212, 400, 41);
        rsinterface.child(9, 46214, 11, 155);
        rsinterface.child(10, 46215, 11, 155);
        rsinterface.child(11, 46217, 133, 155);
        rsinterface.child(12, 46218, 133, 155);
        rsinterface.child(13, 46220, 277, 155);
        rsinterface.child(14, 46221, 277, 155);
        rsinterface.child(15, 46223, 400, 155);
        rsinterface.child(16, 46224, 400, 155);
        rsinterface.child(17, 45026, 8, 280);
        rsinterface.child(18, 45027, 8, 280);
        rsinterface.child(19, 45029, 405, 280);
        rsinterface.child(20, 45030, 405, 280);
        rsinterface.child(21, 46232, 255, 11);
        rsinterface.child(22, 46233, 57, 288);
        rsinterface.child(23, 46234, 454, 288);
    }

    public static void DominionTower2(TextDrawingArea tda[])
    {
        RSInterface rsinterface = addTabInterface(48300);
        addSprite(48301, 0, "Interfaces/Dominiontower2/SUM");
        addHoverButton(48302, "Interfaces/Dominiontower2/BOSS", 1, 100, 100, "Fight Dad!", -1, 48303, 1);
        addHoveredButton(48303, "Interfaces/Dominiontower2/BOSS", 9, 100, 100, 48304);
        addHoverButton(48305, "Interfaces/Dominiontower2/BOSS", 2, 100, 100, "Fight Tree spirit!", -1, 48306, 1);
        addHoveredButton(48306, "Interfaces/Dominiontower2/BOSS", 10, 100, 100, 48307);
        addHoverButton(48308, "Interfaces/Dominiontower2/BOSS", 3, 100, 100, "Fight Ket-Zek!", -1, 48309, 1);
        addHoveredButton(48309, "Interfaces/Dominiontower2/BOSS", 11, 100, 100, 48310);
        addHoverButton(48311, "Interfaces/Dominiontower2/BOSS", 4, 100, 100, "Fight Treus Dayth!", -1, 48312, 1);
        addHoveredButton(48312, "Interfaces/Dominiontower2/BOSS", 12, 100, 100, 48313);
        addHoverButton(48314, "Interfaces/Dominiontower2/BOSS", 5, 100, 100, "Fight Tumeken's shadow!", -1, 48315, 1);
        addHoveredButton(48315, "Interfaces/Dominiontower2/BOSS", 13, 100, 100, 48316);
        addHoverButton(48317, "Interfaces/Dominiontower2/BOSS", 6, 100, 100, "Fight The Kendal!", -1, 48318, 1);
        addHoveredButton(48318, "Interfaces/Dominiontower2/BOSS", 14, 100, 100, 48319);
        addHoverButton(48320, "Interfaces/Dominiontower2/BOSS", 7, 100, 100, "Fight Sigmund!", -1, 48321, 1);
        addHoveredButton(48321, "Interfaces/Dominiontower2/BOSS", 15, 100, 100, 48322);
        addHoverButton(48323, "Interfaces/Dominiontower2/BOSS", 8, 100, 100, "Fight Melzar the Mad!", -1, 48324, 1);
        addHoveredButton(48324, "Interfaces/Dominiontower2/BOSS", 16, 100, 100, 48325);
        addHoverButton(45026, "Interfaces/Dominterface/BUTTON", 3, 100, 34, "Not now", -1, 45027, 1);
        addHoveredButton(45027, "Interfaces/Dominterface/BUTTON", 4, 100, 34, 44028);
        addHoverButton(45029, "Interfaces/Dominterface/BUTTON", 3, 100, 34, "Help", -1, 45030, 1);
        addHoveredButton(45030, "Interfaces/Dominterface/BUTTON", 4, 100, 34, 45031);
        addText(48332, "Dominion Tower Easy Class", tda, 1, 0xf5c036, true, true);
        addText(48333, "Not now", tda, 1, 0xf5c036, true, true);
        addText(48334, "Help", tda, 1, 0xf5c036, true, true);
        rsinterface.totalChildren(24);
        rsinterface.child(0, 48301, 0, 0);
        rsinterface.child(1, 48302, 11, 41);
        rsinterface.child(2, 48303, 11, 41);
        rsinterface.child(3, 48305, 133, 41);
        rsinterface.child(4, 48306, 133, 41);
        rsinterface.child(5, 48308, 277, 41);
        rsinterface.child(6, 48309, 277, 41);
        rsinterface.child(7, 48311, 400, 41);
        rsinterface.child(8, 48312, 400, 41);
        rsinterface.child(9, 48314, 11, 155);
        rsinterface.child(10, 48315, 11, 155);
        rsinterface.child(11, 48317, 133, 155);
        rsinterface.child(12, 48318, 133, 155);
        rsinterface.child(13, 48320, 277, 155);
        rsinterface.child(14, 48321, 277, 155);
        rsinterface.child(15, 48323, 400, 155);
        rsinterface.child(16, 48324, 400, 155);
        rsinterface.child(17, 45026, 8, 280);
        rsinterface.child(18, 45027, 8, 280);
        rsinterface.child(19, 45029, 405, 280);
        rsinterface.child(20, 45030, 405, 280);
        rsinterface.child(21, 48332, 255, 11);
        rsinterface.child(22, 48333, 57, 288);
        rsinterface.child(23, 48334, 454, 288);
    }

    public static void Domtowerwin1(TextDrawingArea tda[])
    {
        RSInterface rsinterface = addTabInterface(46500);
        addSprite(46501, 0, "Interfaces/Dominterface/WIN");
        addText(46502, "Current Dominion Tower Points: ", tda, 0, 0xacacac, true, true);
        addText(46503, "133713371337", tda, 0, 0x599eff, true, true);
        addHoverButton(46504, "Interfaces/Dominterface/BUTTON", 3, 100, 34, "Not now", -1, 46505, 1);
        addHoveredButton(46505, "Interfaces/Dominterface/BUTTON", 4, 100, 34, 46506);
        addHoverButton(46507, "Interfaces/Dominterface/BUTTON", 3, 100, 34, "Carry on", -1, 46508, 1);
        addHoveredButton(46508, "Interfaces/Dominterface/BUTTON", 4, 100, 34, 46509);
        addText(46510, "You've Defeated A Boss!", tda, 1, 0xf5c036, true, true);
        addText(46511, "Not now", tda, 1, 0xf5c036, true, true);
        addText(46512, "Carry on", tda, 1, 0xf5c036, true, true);
        rsinterface.totalChildren(10);
        rsinterface.child(0, 46501, 0, 0);
        rsinterface.child(1, 46502, 220, 225);
        rsinterface.child(2, 46503, 333, 225);
        rsinterface.child(3, 46504, 8, 280);
        rsinterface.child(4, 46505, 8, 280);
        rsinterface.child(5, 46507, 405, 280);
        rsinterface.child(6, 46508, 405, 280);
        rsinterface.child(7, 46510, 255, 11);
        rsinterface.child(8, 46511, 57, 288);
        rsinterface.child(9, 46512, 454, 288);
    }

    public static void Dominterface(TextDrawingArea tda[])
    {
        RSInterface rsinterface = addTabInterface(46100);
        addSprite(46101, 1, "Interfaces/Dominterface/BACKGROUND");
        addHoverButton(46102, "Interfaces/Dominterface/EXIT", 1, 17, 17, "Close Window", -1, 46103, 1);
        addHoveredButton(46103, "Interfaces/Dominterface/EXIT", 2, 17, 17, 46104);
        addHoverButton(46105, "Interfaces/Dominterface/BUTTON", 1, 129, 21, "Harder Classes", -1, 46106, 1);
        addHoveredButton(46106, "Interfaces/Dominterface/BUTTON", 2, 129, 21, 46107);
        addText(46108, "Harder Classes", tda, 0, 0xff9b00, true, true);
        addHoverButton(46109, "Interfaces/Dominterface/BUTTON", 1, 129, 21, "Medium Classes", -1, 46110, 1);
        addHoveredButton(46110, "Interfaces/Dominterface/BUTTON", 2, 129, 21, 46111);
        addText(46112, "Medium Classes", tda, 0, 0xff9b00, true, true);
        addHoverButton(46113, "Interfaces/Dominterface/BUTTON", 1, 129, 21, "Easy Classes", -1, 46114, 1);
        addHoveredButton(46114, "Interfaces/Dominterface/BUTTON", 2, 129, 21, 46115);
        addText(46116, "Easy Classes", tda, 0, 0xff9b00, true, true);
        addHoverButton(46117, "Interfaces/Dominterface/BUTTON", 1, 129, 21, "Rewards", -1, 46118, 1);
        addHoveredButton(46118, "Interfaces/Dominterface/BUTTON", 2, 129, 21, 46119);
        addText(46120, "Rewards", tda, 0, 0xff9b00, true, true);
        rsinterface.totalChildren(15);
        rsinterface.child(0, 46101, 152, 32);
        rsinterface.child(1, 46102, 334, 42);
        rsinterface.child(2, 46103, 334, 42);
        rsinterface.child(3, 46105, 191, 88);
        rsinterface.child(4, 46106, 191, 88);
        rsinterface.child(5, 46108, 257, 95);
        rsinterface.child(6, 46109, 191, 128);
        rsinterface.child(7, 46110, 191, 128);
        rsinterface.child(8, 46112, 257, 135);
        rsinterface.child(9, 46113, 191, 168);
        rsinterface.child(10, 46114, 191, 168);
        rsinterface.child(11, 46116, 257, 175);
        rsinterface.child(12, 46117, 191, 208);
        rsinterface.child(13, 46118, 191, 208);
        rsinterface.child(14, 46120, 257, 215);
    }

	public static void DominionTower(TextDrawingArea tda[])
    {
        RSInterface rsinterface = addTabInterface(45000);
        addSprite(45001, 0, "Interfaces/Dominiontower/SUM");
        addHoverButton(45002, "Interfaces/Dominiontower/BOSS", 1, 100, 100, "Fight Decaying avatar!", -1, 45003, 1);
        addHoveredButton(45003, "Interfaces/Dominiontower/BOSS", 9, 100, 100, 45004);
        addHoverButton(45005, "Interfaces/Dominiontower/BOSS", 2, 100, 100, "Fight Dagannoth Mother!", -1, 45006, 1);
        addHoveredButton(45006, "Interfaces/Dominiontower/BOSS", 10, 100, 100, 45007);
        addHoverButton(45008, "Interfaces/Dominiontower/BOSS", 3, 100, 100, "Fight Nezikchened!", -1, 45009, 1);
        addHoveredButton(45009, "Interfaces/Dominiontower/BOSS", 11, 100, 100, 45010);
        addHoverButton(45011, "Interfaces/Dominiontower/BOSS", 4, 100, 100, "Fight Jungle demon!", -1, 45012, 1);
        addHoveredButton(45012, "Interfaces/Dominiontower/BOSS", 12, 100, 100, 45013);
        addHoverButton(45014, "Interfaces/Dominiontower/BOSS", 5, 100, 100, "Fight Arrav!", -1, 45015, 1);
        addHoveredButton(45015, "Interfaces/Dominiontower/BOSS", 13, 100, 100, 45016);
        addHoverButton(45017, "Interfaces/Dominiontower/BOSS", 6, 100, 100, "Fight Barrelchest!", -1, 45018, 1);
        addHoveredButton(45018, "Interfaces/Dominiontower/BOSS", 14, 100, 100, 45019);
        addHoverButton(45020, "Interfaces/Dominiontower/BOSS", 7, 100, 100, "Fight Chronozon!", -1, 45021, 1);
        addHoveredButton(45021, "Interfaces/Dominiontower/BOSS", 15, 100, 100, 45022);
        addHoverButton(45023, "Interfaces/Dominiontower/BOSS", 8, 100, 100, "Fight Balance Elemental!", -1, 45024, 1);
        addHoveredButton(45024, "Interfaces/Dominiontower/BOSS", 16, 100, 100, 45025);
        addHoverButton(45026, "Interfaces/Dominterface/BUTTON", 3, 100, 34, "Not now", -1, 45027, 1);
        addHoveredButton(45027, "Interfaces/Dominterface/BUTTON", 4, 100, 34, 44028);
        addHoverButton(45029, "Interfaces/Dominterface/BUTTON", 3, 100, 34, "Help", -1, 45030, 1);
        addHoveredButton(45030, "Interfaces/Dominterface/BUTTON", 4, 100, 34, 45031);
        addText(45032, "Dominion Tower Hard Class", tda, 1, 0xf5c036, true, true);
        addText(45033, "Not now", tda, 1, 0xf5c036, true, true);
        addText(45034, "Help", tda, 1, 0xf5c036, true, true);
        rsinterface.totalChildren(24);
        rsinterface.child(0, 45001, 0, 0);
        rsinterface.child(1, 45002, 11, 41);
        rsinterface.child(2, 45003, 11, 41);
        rsinterface.child(3, 45005, 133, 41);
        rsinterface.child(4, 45006, 133, 41);
        rsinterface.child(5, 45008, 277, 41);
        rsinterface.child(6, 45009, 277, 41);
        rsinterface.child(7, 45011, 400, 41);
        rsinterface.child(8, 45012, 400, 41);
        rsinterface.child(9, 45014, 11, 155);
        rsinterface.child(10, 45015, 11, 155);
        rsinterface.child(11, 45017, 133, 155);
        rsinterface.child(12, 45018, 133, 155);
        rsinterface.child(13, 45020, 277, 155);
        rsinterface.child(14, 45021, 277, 155);
        rsinterface.child(15, 45023, 400, 155);
        rsinterface.child(16, 45024, 400, 155);
        rsinterface.child(17, 45026, 8, 280);
        rsinterface.child(18, 45027, 8, 280);
        rsinterface.child(19, 45029, 405, 280);
        rsinterface.child(20, 45030, 405, 280);
        rsinterface.child(21, 45032, 255, 11);
        rsinterface.child(22, 45033, 57, 288);
        rsinterface.child(23, 45034, 454, 288);
    }	
	


		public static void addSprite(int id, int spriteId, String spriteName, int dummy) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 11;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.type = 52;
		tab.sprite1 = imageLoader(spriteId, spriteName);
		tab.sprite2 = imageLoader(spriteId, spriteName); 
		tab.width = 512;
		tab.height = 334;
	}	
public static void addToggleButton(int id, int bID, int bID2, String bName, String tT, int configID, int aT, int configFrame, int dummy) {
		RSInterface tab = addTabInterface(id);
		tab.parentID = id;
		tab.id = id;
		tab.type = 5;
		tab.atActionType = aT;
		tab.contentType = 0;// anInt214
		tab.opacity = 0;
		tab.mOverInterToTrigger = -1;// anInt230
		tab.valueCompareType = new int[1];
		tab.requiredValues = new int[1];
		tab.valueCompareType[0] = 1;
		tab.requiredValues[0] = configID;
		tab.valueIndexArray = new int[1][3];
		tab.valueIndexArray[0][0] = 5;
		tab.valueIndexArray[0][1] = configFrame;
		tab.valueIndexArray[0][2] = 0;
		tab.sprite1 = imageLoader(bID, bName);
		tab.sprite2 = imageLoader(bID2, bName);
		tab.width = tab.sprite1.myWidth;
		tab.height = tab.sprite1.myHeight;
		tab.tooltip = tT;
	}	
	
public static void addToggleButton(int id, int bID, int bID2, String bName, String tT, int configID, int aT, int configFrame) {
		RSInterface tab = addTabInterface(id);
		tab.parentID = id;
		tab.id = id;
		tab.type = 11;
		tab.atActionType = aT;
		tab.contentType = 0;// anInt214
		tab.opacity = 0;
		tab.mOverInterToTrigger = -1;// anInt230
		tab.valueCompareType = new int[1];
		tab.requiredValues = new int[1];
		tab.valueCompareType[0] = 1;
		tab.requiredValues[0] = configID;
		tab.valueIndexArray = new int[1][3];
		tab.valueIndexArray[0][0] = 5;
		tab.valueIndexArray[0][1] = configFrame;
		tab.valueIndexArray[0][2] = 0;
		tab.sprite1 = imageLoader(bID, bName);
		tab.sprite2 = imageLoader(bID2, bName);
		tab.width = tab.sprite1.myWidth;
		tab.height = tab.sprite1.myHeight;
		tab.tooltip = tT;
	}
public static void AddInterfaceButton(int i, int j, int hoverId, String name, int W, int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.type = 5;
		RSInterface.atActionType = AT;
		RSInterface.opacity = 0;
		RSInterface.type = hoverId;
		RSInterface.sprite1 = imageLoader(j,name);
		RSInterface.sprite2 = imageLoader(j,name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}	
	
	public static void minigameTeleportInterface(TextDrawingArea[] paramArrayOfTextDrawingArea) {
    RSInterface localRSInterface = addTabInterface(42000);
    setChildren(14, localRSInterface);
    addSprite(42001, 0, "Interfaces/minigametele/SPRITE");
    addHover(42002, 3, 0, 33003, 0, "Interfaces/minigametele/EXIT", 21, 21, "Exit");
    addHovered(42003, 1, "Interfaces/minigametele/EXIT", 21, 21, 42004);
    addButton(42005, 0, "", "Teleport to Duel Arena", 42005, 1, 100, 33);

    addButton(42006, 0, "", "Teleport to Barrows", 42006, 1, 100, 33);

    addButton(42007, 0, "", "Teleport to Warriors Guild", 42007, 1, 130, 33);
    addButton(42008, 0, "", "Teleport to Pest Control", 42008, 1, 100, 33);
    addButton(42009, 0, "", "Teleport to Fight Caves", 42009, 1, 100, 33);

    addButton(42010, 0, "", "Teleport to Fist of Guthix", 42010, 1, 100, 33);
    addButton(42011, 0, "", "Teleport to Castle Wars", 42011, 1, 100, 33);
    addButton(42012, 0, "", "Teleport to Barbarian Assault", 42012, 1, 100, 33);
    addButton(42013, 0, "", "Teleport to Dominion Tower", 42013, 1, 100, 33);
    addButton(42014, 0, "", "Teleport to Recipe for Disaster", 42014, 1, 100, 33);
    addButton(42015, 0, "", "Teleport to Nomad - MiniQuest", 42015, 1, 100, 33);

    setBounds(42001, 4, 6, 0, localRSInterface);
    setBounds(42002, 477, 6, 1, localRSInterface);
    setBounds(42003, 477, 6, 2, localRSInterface);
    setBounds(42005, 50, 72, 3, localRSInterface);
    setBounds(42006, 50, 111, 4, localRSInterface);
    setBounds(42007, 50, 145, 5, localRSInterface);
    setBounds(42008, 50, 190, 6, localRSInterface);
    setBounds(42009, 50, 225, 7, localRSInterface);
    setBounds(42010, 229, 72, 8, localRSInterface);
    setBounds(42011, 229, 111, 9, localRSInterface);
    setBounds(42012, 229, 145, 10, localRSInterface);
    setBounds(42013, 229, 190, 11, localRSInterface);
    setBounds(42014, 229, 225, 12, localRSInterface);
    setBounds(42015, 368, 72, 13, localRSInterface);
  }

  public static void SkillTeleportInterface(TextDrawingArea[] paramArrayOfTextDrawingArea)
  {
    RSInterface localRSInterface = addTabInterface(33000);
    setChildren(40, localRSInterface);

    addSprite(33001, 0, "Interfaces/GabbesSkillTeleport/SPRITE");
    addHover(33002, 3, 0, 33003, 0, "Interfaces/GabbesSkillTeleport/EXIT", 21, 21, "Exit");
    addHovered(33003, 1, "Interfaces/GabbesSkillTeleport/EXIT", 21, 21, 33004);
    addButton(33005, 0, "FISHING", "Teleport to Fishing", 33005, 1, 110, 33);
    addText(33006, "", 14929103, true, true, 115, 2);
    addButton(33007, 0, "COOKING", "Teleport to Cooking", 33007, 1, 110, 33);
    addText(33008, "", 14929103, true, true, 115, 2);
    addButton(33009, 0, "MINING", "Teleport to Mining", 33009, 1, 110, 33);
    addText(33010, "", 14929103, true, true, 115, 2);
    addButton(33011, 0, "SMITHING", "Teleport to Smithing", 33011, 1, 112, 33);
    addText(33012, "", 14929103, true, true, 115, 2);
    addButton(33013, 0, "WOODCUTTING", "Teleport to Woodcutting", 33013, 1, 130, 33);
    addText(33014, "", 14929103, true, true, 115, 2);
    addButton(33015, 0, "FIREMAKING", "Teleport to Firemaking", 33015, 1, 120, 33);
    addText(33016, "", 14929103, true, true, 115, 2);
    addButton(33017, 0, "FARMING", "Teleport to Farming", 33017, 1, 110, 33);
    addText(33018, "", 14929103, true, true, 115, 2);
    addButton(33019, 0, "HERBLORE", "Teleport to Herblore", 33019, 1, 110, 33);
    addText(33020, "", 14929103, true, true, 115, 2);
    addButton(33021, 0, "AGILITY", "Teleport to Agility", 33021, 1, 110, 33);
    addText(33022, "", 14929103, true, true, 115, 2);
    addButton(33023, 0, "HUNTER", "Teleport to Hunter", 33023, 1, 110, 33);
    addText(33024, "", 14929103, true, true, 115, 2);

    addButton(33025, 0, "CONSTRUCTION", "Teleport to Construction", 33025, 1, 120, 33);
    addText(33026, "", 14929103, true, true, 115, 2);

    addButton(33027, 0, "FLETCHING", "Teleport to Fletching", 33027, 1, 111, 33);
    addText(33028, "", 14929103, true, true, 115, 2);

    addButton(33029, 0, "CRAFTING", "Teleport to Crafting", 33029, 1, 110, 33);
    addText(33030, "", 14929103, true, true, 115, 2);

    addButton(33031, 0, "SUMMONING", "Teleport to Summoning", 33031, 1, 110, 33);
    addText(33032, "", 14929103, true, true, 115, 2);

    addButton(33035, 0, "DUNGEONEERING", "Teleport to Dungeoneering", 33035, 1, 120, 33);
    addText(33036, "", 14929103, true, true, 115, 2);

    addButton(33033, 0, "RUNECRAFTING", "Teleport to Runecrafting", 33033, 1, 120, 33);
    addText(33034, "", 14929103, true, true, 115, 2);

    addButton(33037, 0, "THIEVING", "Teleport to Thieving", 33037, 1, 120, 33);
    addText(33038, "", 14929103, true, true, 115, 2);
    addButton(33039, 0, "SLAYER", "Teleport to Slayer", 33039, 1, 120, 33);
    addText(33040, "", 14929103, true, true, 115, 2);

    addText(33041, "Total Level: 2000", 14929103, true, true, 115, 2);

    setBounds(33001, 4, 2, 0, localRSInterface);
    setBounds(33002, 477, 6, 1, localRSInterface);
    setBounds(33003, 477, 6, 2, localRSInterface);

    setBounds(33005, 50, 90, 3, localRSInterface);
    setBounds(33006, 115, 100, 4, localRSInterface);

    setBounds(33007, 50, 130, 5, localRSInterface);
    setBounds(33008, 115, 140, 6, localRSInterface);

    setBounds(33009, 50, 170, 7, localRSInterface);
    setBounds(33010, 115, 180, 8, localRSInterface);

    setBounds(33011, 50, 210, 9, localRSInterface);
    setBounds(33012, 115, 220, 10, localRSInterface);

    setBounds(33013, 160, 90, 11, localRSInterface);
    setBounds(33014, 235, 100, 12, localRSInterface);

    setBounds(33015, 160, 130, 13, localRSInterface);
    setBounds(33016, 235, 140, 14, localRSInterface);

    setBounds(33017, 160, 170, 15, localRSInterface);
    setBounds(33018, 235, 180, 16, localRSInterface);

    setBounds(33019, 160, 210, 17, localRSInterface);
    setBounds(33020, 235, 220, 18, localRSInterface);

    setBounds(33021, 285, 90, 19, localRSInterface);
    setBounds(33022, 340, 100, 20, localRSInterface);

    setBounds(33023, 50, 250, 21, localRSInterface);
    setBounds(33024, 110, 260, 22, localRSInterface);

    setBounds(33025, 160, 250, 23, localRSInterface);
    setBounds(33026, 235, 260, 24, localRSInterface);

    setBounds(33027, 285, 130, 25, localRSInterface);
    setBounds(33028, 350, 140, 26, localRSInterface);

    setBounds(33029, 285, 170, 27, localRSInterface);
    setBounds(33030, 350, 180, 28, localRSInterface);

    setBounds(33031, 285, 210, 29, localRSInterface);
    setBounds(33032, 360, 220, 30, localRSInterface);

    setBounds(33033, 285, 250, 31, localRSInterface);
    setBounds(33034, 360, 260, 32, localRSInterface);

    setBounds(33035, 395, 90, 33, localRSInterface);
    setBounds(33036, 455, 110, 34, localRSInterface);

    setBounds(33037, 399, 130, 35, localRSInterface);
    setBounds(33038, 455, 145, 36, localRSInterface);

    setBounds(33039, 399, 170, 37, localRSInterface);
    setBounds(33040, 455, 183, 38, localRSInterface);

    setBounds(33041, 100, 280, 39, localRSInterface);
  }
	
		public static void addHoverButton1(int i, String imageName, int j, int width, int height, String text, int hoverOver, int aT) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = aT;
		tab.opacity = 0;
		tab.type = hoverOver;
		tab.sprite1 = imageLoader(j, imageName);
		tab.sprite2 = imageLoader(j, imageName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = text;
	}
		private static void addHead(int i, int width, int height, int zoom) {
		RSInterface rsinterface = addTabInterface(i);
		rsinterface.type= 6;
		rsinterface.modelZoom = zoom;
		rsinterface.modelRotation1 = 40;
		rsinterface.modelRotation2 = 1900;//166
		rsinterface.height = height;
		rsinterface.width = width;
	}	
public static void Buy(TextDrawingArea[] TDA) {
	RSInterface rsinterface = addTabInterface(24600);
	addSprite(24601, 0, "Interfaces/GrandExchange/buy");
	addHoverButton(24602, "Interfaces/GrandExchange/close", 1, 16, 16, "Close", 0, 24603, 1);
        addHoveredButton(24603, "Interfaces/GrandExchange/close", 2, 16, 16, 24604);
	addHoverButton(24606, "Interfaces/GrandExchange/sprite", 1, 13, 13, "Decrease Quantity", 0, 24607, 1);
        addHoveredButton(24607, "Interfaces/GrandExchange/sprite", 3, 13, 13, 24608);
	addHoverButton(24610, "Interfaces/GrandExchange/sprite", 2, 13, 13, "Increase Quantity", 0, 24611, 1);
        addHoveredButton(24611, "Interfaces/GrandExchange/sprite", 4, 13, 13, 24612);
	addHoverButton(24614, "Interfaces/GrandExchange/sprite", 5, 35, 25, "Add 1", 0, 24615, 1);
        addHoveredButton(24615, "Interfaces/GrandExchange/sprite", 6, 35, 25, 24616);
	addHoverButton(24618, "Interfaces/GrandExchange/sprite", 7, 35, 25, "Add 10", 0, 24619, 1);
        addHoveredButton(24619, "Interfaces/GrandExchange/sprite", 8, 35, 25, 24620);
	addHoverButton(24622, "Interfaces/GrandExchange/sprite", 9, 35, 25, "Add 100", 0, 24623, 1);
        addHoveredButton(24623, "Interfaces/GrandExchange/sprite", 10, 35, 25, 24624);
	addHoverButton(24626, "Interfaces/GrandExchange/sprite", 11, 35, 25, "Add 1000", 0, 24627, 1);
        addHoveredButton(24627, "Interfaces/GrandExchange/sprite", 12, 35, 25, 24628);
	addHoverButton(24630, "Interfaces/GrandExchange/sprite", 13, 35, 25, "Edit Quantity", 0, 24631, 1);
        addHoveredButton(24631, "Interfaces/GrandExchange/sprite", 14, 35, 25, 24632);
	addHoverButton(24634, "Interfaces/GrandExchange/sprite", 15, 35, 25, "Decrease Price", 0, 24635, 1);
        addHoveredButton(24635, "Interfaces/GrandExchange/sprite", 16, 35, 25, 24636);
	addHoverButton(24638, "Interfaces/GrandExchange/sprite", 17, 35, 25, "Offer Guild Price", 0, 24639, 1);
        addHoveredButton(24639, "Interfaces/GrandExchange/sprite", 18, 35, 25, 24640);
	addHoverButton(24642, "Interfaces/GrandExchange/sprite", 13, 35, 25, "Edit Price", 0, 24643, 1);
        addHoveredButton(24643, "Interfaces/GrandExchange/sprite", 14, 35, 25, 24644);
	addHoverButton(24646, "Interfaces/GrandExchange/sprite", 19, 35, 25, "Increase Price", 0, 24647, 1);
        addHoveredButton(24647, "Interfaces/GrandExchange/sprite", 20, 35, 25, 24648);
	addHoverButton(24650, "Interfaces/GrandExchange/sprite", 21, 120, 43, "Confrim Offer", 0, 24651, 1);
        addHoveredButton(24651, "Interfaces/GrandExchange/sprite", 22, 120, 43, 24652);
	addHoverButton(24654, "Interfaces/GrandExchange/sprite", 23, 40, 36, "Choose Item", 0, 24655, 1);
        addHoveredButton(24655, "Interfaces/GrandExchange/sprite", 24, 40, 36, 24656);
	addHoverButton(24658, "Interfaces/GrandExchange/sprite", 25, 29, 23, "Back", 0, 24659, 1);
        addHoveredButton(24659, "Interfaces/GrandExchange/sprite", 26, 29, 23, 24660);
	addHoverButton(24662, "Interfaces/GrandExchange/sprite", 1, 13, 13, "Decrease Price", 0, 24663, 1);
        addHoveredButton(24663, "Interfaces/GrandExchange/sprite", 3, 13, 13, 24664);
	addHoverButton(24665, "Interfaces/GrandExchange/sprite", 2, 13, 13, "Increase Price", 0, 24666, 1);
        addHoveredButton(24666, "Interfaces/GrandExchange/sprite", 4, 13, 13, 24667); 
 	addText(24669, "Choose an item to exchange", TDA, 0, 0x96731A, false, true);
 	addText(24670, "Click the icone to the left to sreach for items.", TDA, 0, 0x958E60, false, true);
 	addText(24671, "0", TDA, 0, 0xB58338, false, true);
 	addText(24672, "1 gp", TDA, 0, 0xB58338, false, true);
 	addText(24673, "0 gp", TDA, 0, 0xB58338, false, true);
        rsinterface.totalChildren(40);
        rsinterface.child(0, 24601, 4, 23);
        rsinterface.child(1, 24602, 464, 33);
        rsinterface.child(2, 24603, 464, 33);
        rsinterface.child(3, 24606, 46, 184);
        rsinterface.child(4, 24607, 46, 184);
        rsinterface.child(5, 24610, 226, 184);
        rsinterface.child(6, 24611, 226, 184);
        rsinterface.child(7, 24614, 43, 208);
        rsinterface.child(8, 24615, 43, 208);
        rsinterface.child(9, 24618, 84, 208);
        rsinterface.child(10, 24619, 84, 208);
        rsinterface.child(11, 24622, 125, 208);
        rsinterface.child(12, 24623, 125, 208);
        rsinterface.child(13, 24626, 166, 208);
        rsinterface.child(14, 24627, 166, 208);
        rsinterface.child(15, 24630, 207, 208);
        rsinterface.child(16, 24631, 207, 208);
        rsinterface.child(17, 24634, 260, 208);
        rsinterface.child(18, 24635, 260, 208);
        rsinterface.child(19, 24638, 316, 208);
        rsinterface.child(20, 24639, 316, 208);
        rsinterface.child(21, 24642, 357, 208);
        rsinterface.child(22, 24643, 357, 208);
        rsinterface.child(23, 24646, 413, 208);
        rsinterface.child(24, 24647, 413, 208);
        rsinterface.child(25, 24650, 191, 273);
        rsinterface.child(26, 24651, 191, 273);
        rsinterface.child(27, 24654, 93, 95);
        rsinterface.child(28, 24655, 93, 95);
        rsinterface.child(29, 24658, 19, 284);
        rsinterface.child(30, 24659, 19, 284);
        rsinterface.child(31, 24662, 260, 184);
        rsinterface.child(32, 24663, 260, 184);
        rsinterface.child(33, 24665, 435, 184);
        rsinterface.child(34, 24666, 435, 184);
        rsinterface.child(35, 24669, 202, 71);
        rsinterface.child(36, 24670, 202, 98);
        rsinterface.child(37, 24671, 139, 185);
        rsinterface.child(38, 24672, 341, 185);
        rsinterface.child(39, 24673, 238, 246);
	}
		
public static void Sell(TextDrawingArea[] TDA) {
	RSInterface rsinterface = addTabInterface(24700);
	addSprite(24701, 0, "Interfaces/GrandExchange/sell");
	addHoverButton(24702, "Interfaces/GrandExchange/close", 1, 16, 16, "Close", 0, 24703, 1);
        addHoveredButton(24703, "Interfaces/GrandExchange/close", 2, 16, 16, 24704);
	addHoverButton(24706, "Interfaces/GrandExchange/sprite", 1, 13, 13, "Decrease Quantity", 0, 24707, 1);
        addHoveredButton(24707, "Interfaces/GrandExchange/sprite", 3, 13, 13, 24708);
	addHoverButton(24710, "Interfaces/GrandExchange/sprite", 2, 13, 13, "Increase Quantity", 0, 24711, 1);
        addHoveredButton(24711, "Interfaces/GrandExchange/sprite", 4, 13, 13, 24712);
	addHoverButton(24714, "Interfaces/GrandExchange/sprite", 5, 35, 25, "Sell 1", 0, 24715, 1);
        addHoveredButton(24715, "Interfaces/GrandExchange/sprite", 6, 35, 25, 24716);
	addHoverButton(24718, "Interfaces/GrandExchange/sprite", 7, 35, 25, "Sell 10", 0, 24719, 1);
        addHoveredButton(24719, "Interfaces/GrandExchange/sprite", 8, 35, 25, 24720);
	addHoverButton(24722, "Interfaces/GrandExchange/sprite", 9, 35, 25, "Sell 100", 0, 24723, 1);
        addHoveredButton(24723, "Interfaces/GrandExchange/sprite", 10, 35, 25, 24724);
	addHoverButton(24726, "Interfaces/GrandExchange/sprite", 29, 35, 25, "Sell All", 0, 24727, 1);
        addHoveredButton(24727, "Interfaces/GrandExchange/sprite", 30, 35, 25, 24728);
	addHoverButton(24730, "Interfaces/GrandExchange/sprite", 13, 35, 25, "Edit Quantity", 0, 24731, 1);
        addHoveredButton(24731, "Interfaces/GrandExchange/sprite", 14, 35, 25, 24732);
	addHoverButton(24734, "Interfaces/GrandExchange/sprite", 15, 35, 25, "Decrease Price", 0, 24735, 1);
        addHoveredButton(24735, "Interfaces/GrandExchange/sprite", 16, 35, 25, 24736);
	addHoverButton(24738, "Interfaces/GrandExchange/sprite", 17, 35, 25, "Offer Guild Price", 0, 24739, 1);
        addHoveredButton(24739, "Interfaces/GrandExchange/sprite", 18, 35, 25, 24740);
	addHoverButton(24742, "Interfaces/GrandExchange/sprite", 13, 35, 25, "Edit Price", 0, 24743, 1);
        addHoveredButton(24743, "Interfaces/GrandExchange/sprite", 14, 35, 25, 24744);
	addHoverButton(24746, "Interfaces/GrandExchange/sprite", 19, 35, 25, "Increase Price", 0, 24747, 1);
        addHoveredButton(24747, "Interfaces/GrandExchange/sprite", 20, 35, 25, 24748);
	addHoverButton(24750, "Interfaces/GrandExchange/sprite", 21, 120, 43, "Confrim Offer", 0, 24751, 1);
        addHoveredButton(24751, "Interfaces/GrandExchange/sprite", 22, 120, 43, 24752);
	addHoverButton(24758, "Interfaces/GrandExchange/sprite", 25, 29, 23, "Back", 0, 24759, 1);
        addHoveredButton(24759, "Interfaces/GrandExchange/sprite", 26, 29, 23, 24760);
	addHoverButton(24762, "Interfaces/GrandExchange/sprite", 1, 13, 13, "Decrease Price", 0, 24763, 1);
        addHoveredButton(24763, "Interfaces/GrandExchange/sprite", 3, 13, 13, 24764);
	addHoverButton(24765, "Interfaces/GrandExchange/sprite", 2, 13, 13, "Increase Price", 0, 24766, 1);
        addHoveredButton(24766, "Interfaces/GrandExchange/sprite", 4, 13, 13, 24767);
 	addText(24769, "Choose an item to exchange", TDA, 0, 0x96731A, false, true);
 	addText(24770, "Select an item from your invertory to sell.", TDA, 0, 0x958E60, false, true);
	addText(24771, "0", TDA, 0, 0xB58338, false, true);
 	addText(24772, "1 gp", TDA, 0, 0xB58338, false, true);
 	addText(24773, "0 gp", TDA, 0, 0xB58338, false, true);
        rsinterface.totalChildren(38);
        rsinterface.child(0, 24701, 4, 23);
        rsinterface.child(1, 24702, 464, 33);
        rsinterface.child(2, 24703, 464, 33);
        rsinterface.child(3, 24706, 46, 184);
        rsinterface.child(4, 24707, 46, 184);
        rsinterface.child(5, 24710, 226, 184);
        rsinterface.child(6, 24711, 226, 184);
        rsinterface.child(7, 24714, 43, 208);
        rsinterface.child(8, 24715, 43, 208);
        rsinterface.child(9, 24718, 84, 208);
        rsinterface.child(10, 24719, 84, 208);
        rsinterface.child(11, 24722, 125, 208);
        rsinterface.child(12, 24723, 125, 208);
        rsinterface.child(13, 24726, 166, 208);
        rsinterface.child(14, 24727, 166, 208);
        rsinterface.child(15, 24730, 207, 208);
        rsinterface.child(16, 24731, 207, 208);
        rsinterface.child(17, 24734, 260, 208);
        rsinterface.child(18, 24735, 260, 208);
        rsinterface.child(19, 24738, 316, 208);
        rsinterface.child(20, 24739, 316, 208);
        rsinterface.child(21, 24742, 357, 208);
        rsinterface.child(22, 24743, 357, 208);
        rsinterface.child(23, 24746, 413, 208);
        rsinterface.child(24, 24747, 413, 208);
        rsinterface.child(25, 24750, 191, 273);
        rsinterface.child(26, 24751, 191, 273);
        rsinterface.child(27, 24758, 19, 284);
        rsinterface.child(28, 24759, 19, 284);
        rsinterface.child(29, 24762, 260, 184);
        rsinterface.child(30, 24763, 260, 184);
        rsinterface.child(31, 24765, 435, 184);
        rsinterface.child(32, 24766, 435, 184);
        rsinterface.child(33, 24769, 202, 71);
        rsinterface.child(34, 24770, 202, 98);
        rsinterface.child(35, 24771, 139, 185);
        rsinterface.child(36, 24772, 341, 185);
        rsinterface.child(37, 24773, 238, 246);
	}	
	
	

	public static void NewSkillLamp(TextDrawingArea[] tda) {
	RSInterface Interface = addTabInterface(35000);

	setChildren(61, Interface);
	addSprite(35001, 0, "Interfaces/NewSkillLamp/BACKGROUND");// Background
	addHover(35002, 3, 0, 35003, 0, "Interfaces/NewSkillLamp/EXIT", 21, 21, "Exit");//Close Button
	addHovered(35003, 1, "Interfaces/NewSkillLamp/EXIT", 21, 21, 35004);//Close Button
	addSprite(35005, 0, "Interfaces/NewSkillLamp/BANNER");
    addText(35006, "Choose XP Type...", tda, 1, 0xE3CCCF, true, true);
	//Line 1
	addHoverButton(35007, "", 0, 46, 44, "Choose Attack", -1, 35008, 1);
	addHoveredButton(35008, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35009);
	addHoverButton(35010, "", 0, 46, 44, "Choose Magic", -1, 35011, 1);
	addHoveredButton(35011, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35012);
	addHoverButton(35013, "", 0, 46, 44, "Choose Mining", -1, 35014, 1);
	addHoveredButton(35014, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35015);
	addHoverButton(35016, "", 0, 46, 44, "Choose Woodcutting", -1, 35017, 1);
	addHoveredButton(35017, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35018);
	addHoverButton(35019, "", 0, 46, 44, "Choose Agility", -1, 35020, 1);
	addHoveredButton(35020, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35021);
	addHoverButton(35022, "", 0, 46, 44, "Choose Fletching", -1, 35023, 1);
	addHoveredButton(35023, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35024);
	addHoverButton(35025, "", 0, 46, 44, "Choose Thieving", -1, 35026, 1);
	addHoveredButton(35026, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35027);
	//Line 2
	addHoverButton(35028, "", 0, 46, 44, "Choose Strength", -1, 35029, 1);
	addHoveredButton(35029, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35030);
	addHoverButton(35031, "", 0, 46, 44, "Choose Ranged", -1, 35032, 1);
	addHoveredButton(35032, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35033);
	addHoverButton(35034, "", 0, 46, 44, "Choose Smithing", -1, 35035, 1);
	addHoveredButton(35035, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35036);
	addHoverButton(35037, "", 0, 46, 44, "Choose Firemaking", -1, 35038, 1);
	addHoveredButton(35038, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35039);
	addHoverButton(35040, "", 0, 46, 44, "Choose Herblore", -1, 35041, 1);
	addHoveredButton(35041, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35042);
	addHoverButton(35043, "", 0, 46, 44, "Choose Slayer", -1, 35044, 1);
	addHoveredButton(35044, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35045);
	addHoverButton(35046, "", 0, 46, 44, "Choose Construction", -1, 35047, 1);
	addHoveredButton(35047, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35048);
	//Line 3
	addHoverButton(35049, "", 0, 46, 44, "Choose Defence", -1, 35050, 1);
	addHoveredButton(35050, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35051);
	addHoverButton(35052, "", 0, 46, 44, "Choose Prayer", -1, 35053, 1);
	addHoveredButton(35053, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35054);
	addHoverButton(35055, "", 0, 46, 44, "Choose Fishing", -1, 35056, 1);
	addHoveredButton(35056, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35057);
	addHoverButton(35058, "", 0, 46, 44, "Choose Crafting", -1, 35059, 1);
	addHoveredButton(35059, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35060);
	addHoverButton(35061, "", 0, 46, 44, "Choose Farming", -1, 35062, 1);
	addHoveredButton(35062, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35063);
	addHoverButton(35064, "", 0, 46, 44, "Choose Hunter", -1, 35065, 1);
	addHoveredButton(35065, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35066);
	addHoverButton(35067, "", 0, 46, 44, "Choose Summoning", -1, 35068, 1);
	addHoveredButton(35068, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35069);
	//Line 4
	addHoverButton(35070, "", 0, 46, 44, "Choose Constitution", -1, 35071, 1);
	addHoveredButton(35071, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35072);
	addHoverButton(35073, "", 0, 46, 44, "Choose Dungeoneering", -1, 35074, 1);
	addHoveredButton(35074, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35075);
	addHoverButton(35076, "", 0, 46, 44, "Choose Cooking", -1, 35077, 1);
	addHoveredButton(35077, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35078);
	addHoverButton(35079, "", 0, 46, 44, "Choose Runecrafting", -1, 35080, 1);
	addHoveredButton(35080, "Interfaces/NewSkillLamp/CIRCLE", 1, 46, 44, 35081);
	//Other Stuff
	addHover(35082, 3, 0, 35083, 0, "Interfaces/NewSkillLamp/CANCEL", 127, 21, "");
	addHovered(35083, 1, "Interfaces/NewSkillLamp/CANCEL", 127, 21, 35084);
	addHoverButton(35085, "Interfaces/NewSkillLamp/CONFIRM", 0, 127, 21, "Confirm", -1, 35086, 1);
	addHoveredButton(35086, "Interfaces/NewSkillLamp/CONFIRM", 1, 127, 21, 35087);
    addText(35088, "Confirm", tda, 1, 0xE3CCCF, false, true);
    addText(35089, "Not right now", tda, 1, 0xE3CCCF, false, true);

	setBounds(35001, 10, 14, 0, Interface);//background
	setBounds(35002, 470, 20, 1, Interface);//Close Button
	setBounds(35003, 470, 20, 2, Interface);//Close Button
	setBounds(35005, 181, 48, 3, Interface);
	setBounds(35006, 255, 52, 4, Interface);
	//Line 1
	setBounds(35007, 37, 80, 5, Interface);
	setBounds(35008, 37, 80, 6, Interface);
	setBounds(35010, 102, 80, 7, Interface);
	setBounds(35011, 102, 80, 8, Interface);
	setBounds(35013, 167, 80, 9, Interface);
	setBounds(35014, 167, 80, 10, Interface);
	setBounds(35016, 232, 80, 11, Interface);
	setBounds(35017, 232, 80, 12, Interface);
	setBounds(35019, 297, 80, 13, Interface);
	setBounds(35020, 297, 80, 14, Interface);
	setBounds(35022, 362, 80, 15, Interface);
	setBounds(35023, 362, 80, 16, Interface);
	setBounds(35025, 427, 80, 17, Interface);
	setBounds(35026, 427, 80, 18, Interface);
	//Line 2
	setBounds(35028, 37, 138, 19, Interface);
	setBounds(35029, 37, 138, 20, Interface);
	setBounds(35031, 102, 138, 21, Interface);
	setBounds(35032, 102, 138, 22, Interface);
	setBounds(35034, 167, 138, 23, Interface);
	setBounds(35035, 167, 138, 24, Interface);
	setBounds(35037, 232, 138, 25, Interface);
	setBounds(35038, 232, 138, 26, Interface);
	setBounds(35040, 297, 138, 27, Interface);
	setBounds(35041, 297, 138, 28, Interface);
	setBounds(35043, 362, 138, 29, Interface);
	setBounds(35044, 362, 138, 30, Interface);
	setBounds(35046, 427, 138, 31, Interface);
	setBounds(35047, 427, 138, 32, Interface);
	//Line 3
	setBounds(35049, 37, 196, 33, Interface);
	setBounds(35050, 37, 196, 34, Interface);
	setBounds(35052, 102, 196, 35, Interface);
	setBounds(35053, 102, 196, 36, Interface);
	setBounds(35055, 167, 196, 37, Interface);
	setBounds(35056, 167, 196, 38, Interface);
	setBounds(35058, 232, 196, 39, Interface);
	setBounds(35059, 232, 196, 40, Interface);
	setBounds(35061, 297, 196, 41, Interface);
	setBounds(35062, 297, 196, 42, Interface);
	setBounds(35064, 362, 196, 43, Interface);
	setBounds(35065, 362, 196, 44, Interface);
	setBounds(35067, 427, 196, 45, Interface);
	setBounds(35068, 427, 196, 46, Interface);
	//Line 4
	setBounds(35070, 37, 254, 47, Interface);
	setBounds(35071, 37, 254, 48, Interface);
	setBounds(35073, 102, 254, 49, Interface);
	setBounds(35074, 102, 254, 50, Interface);
	setBounds(35076, 167, 254, 51, Interface);
	setBounds(35077, 167, 254, 52, Interface);
	setBounds(35079, 232, 254, 53, Interface);
	setBounds(35080, 232, 254, 54, Interface);
	//Other Stuff
	setBounds(35082, 322, 280, 55, Interface);
	setBounds(35083, 322, 280, 56, Interface);
	setBounds(35085, 322, 250, 57, Interface);
	setBounds(35086, 322, 250, 58, Interface);
	setBounds(35088, 360, 253, 59, Interface);
	setBounds(35089, 350, 283, 60, Interface);
}
	public static void settingsInterface(TextDrawingArea TDA[]) {
		RSInterface rsinterface = addTabInterface(26000);
		int x = 168;
		int y = 49;
		addSprite(26001, 0, "interfaces/settings/base");
		addText(26002, "More Options", 0xe4a146, true, true, 52, 2);
		addInAreaHover(26003, "interfaces/summoning/creation/close", 0, 1, 16,
				16, "Close", 250, 3);
		addText(26004, "Use the new function keys,\\nuntick to use old.",
				0xe4a146, false, true, 52, 0);
		addSprite(26005, 0, "interfaces/settings/split");
		addSprite(26011, 1, "interfaces/settings/split");
		addText(26006, "Use the new style health\\nbars, untick to use old.",
				0xe4a146, false, true, 52, 0);
		addText(26009,
				"Display the new x10 style\\ndamage, untick to use old.",
				0xe4a146, false, true, 52, 0);
		addButton(26007, 4, -1, 2, 3, "interfaces/settings/click", 15, 15,
				"Toggle function keys", 650, 1);
		addButton(26008, 4, -1, 2, 3, "interfaces/settings/click", 15, 15,
				"Toggle halth bars", 651, 1);
		addButton(26010, 4, -1, 2, 3, "interfaces/settings/click", 15, 15,
				"Toggle x10 damage", 652, 1);
		addButton(26012, 4, 26013, 9, 10, "Interfaces/OptionTab/OPTION", 40,
				40, "Toggle Full-Screen", 653, 1);
		addSprite(26013, 17, "Interfaces/OptionTab/OPTION");
		setChildren(17, rsinterface);
		int i = 0;
		setBounds(26001, x + 0, y + 0, i, rsinterface);
		i++;
		setBounds(26002, x + 89, y + 3, i, rsinterface);
		i++;
		setBounds(26003, x + 151, y + 3, i, rsinterface);
		i++;
		setBounds(26004, x + 9, y + 25, i, rsinterface);
		i++;
		setBounds(26006, x + 9, y + 57, i, rsinterface);
		i++;
		setBounds(26007, x + 154, y + 29, i, rsinterface);
		i++;
		setBounds(26008, x + 154, y + 61, i, rsinterface);
		i++;
		setBounds(26009, x + 9, y + 89, i, rsinterface);
		i++;
		setBounds(26005, x + 7, y + 115, i, rsinterface);
		i++;
		setBounds(26010, x + 154, y + 93, i, rsinterface);
		i++;
		setBounds(26005, x + 7, y + 51, i, rsinterface);
		i++;
		setBounds(26005, x + 7, y + 83, i, rsinterface);
		i++;
		setBounds(26011, x + 151, y + 22, i, rsinterface);
		i++;
		setBounds(26011, x + 151, y + 53, i, rsinterface);
		i++;
		setBounds(26011, x + 151, y + 85, i, rsinterface);
		i++;
		setBounds(26012, (x + 7 + 81) - 20, (y + 86 + 77) - 20, i, rsinterface);
		i++;
		setBounds(26013, ((x + 7 + 81) - 20) + 4, ((y + 86 + 77) - 20) + 6, i,
				rsinterface);
		i++;
	}

	public static void SummonTab(TextDrawingArea[] TDA) {
		RSInterface localRSInterface = addTabInterface(17011);
		addSprite(17012, 6, "Interfaces/SummonTab/SUMMON");
		addButton(17013, 7, "Interfaces/SummonTab/SUMMON", "Click");

		addSprite(17014, 6, "Interfaces/SummonTab/SUMMON");
		addConfigButton(17015, 17032, 14, 8, "Interfaces/SummonTab/SUMMON", 20,
				30, "Call Familiar", 1, 5, 300);
		addHoverButton(17018, "Interfaces/SummonTab/SUMMON", 2, 38, 36,
				"Beast of burden Inventory", -1, 17028, 1);
		addHoveredButton(17028, "Interfaces/SummonTab/SUMMON", 12, 38, 36,
				17029);
		addHoverButton(17022, "Interfaces/SummonTab/SUMMON", 1, 38, 36,
				"Call Familiar", -1, 17030, 1);
		addHoveredButton(17030, "Interfaces/SummonTab/SUMMON", 13, 38, 36,
				17031);
		addHoverButton(17023, "Interfaces/SummonTab/SUMMON", 3, 38, 36,
				"Dismiss Familiar", -1, 17033, 1);
		addHoveredButton(17033, "Interfaces/SummonTab/SUMMON", 15, 38, 36,
				17034);
		addSprite(17016, 5, "Interfaces/SummonTab/SUMMON");
		addText(17017, "", TDA, 2, 14329120, false, true);
		addSprite(17019, 9, "Interfaces/SummonTab/SUMMON");
		addText(17021, "", TDA, 0, 16753920, false, true);
		addSprite(17020, 10, "Interfaces/SummonTab/SUMMON");
		addSprite(17024, 11, "Interfaces/SummonTab/SUMMON");
		addText(17025, "", TDA, 0, 16753920, false, true);
		addText(17026, "", TDA, 0, 16753920, false, true);
		addHead2(17027, 75, 55, 2000);
		localRSInterface.totalChildren(19);
		localRSInterface.child(0, 17012, 10, 25);
		localRSInterface.child(1, 17013, 24, 7);
		localRSInterface.child(2, 17014, 10, 25);
		localRSInterface.child(3, 17015, 11, 25);
		localRSInterface.child(4, 17016, 15, 140);
		localRSInterface.child(5, 17017, 45, 143);
		localRSInterface.child(6, 17018, 20, 170);
		localRSInterface.child(7, 17019, 115, 167);
		localRSInterface.child(8, 17020, 143, 170);
		localRSInterface.child(9, 17021, 135, 197);
		localRSInterface.child(10, 17022, 20, 213);
		localRSInterface.child(11, 17023, 67, 193);
		localRSInterface.child(12, 17024, 135, 214);
		localRSInterface.child(13, 17025, 135, 240);
		localRSInterface.child(14, 17026, 21, 59);
		localRSInterface.child(15, 17027, 75, 55);
		localRSInterface.child(16, 17028, 20, 170);
		localRSInterface.child(17, 17030, 20, 213);
		localRSInterface.child(18, 17033, 67, 193);
	}
		

	private static void addHead2(int id, int w, int h, int zoom) {// tewst
		RSInterface rsinterface = addInterface(id);
		rsinterface.type = 6;
		rsinterface.anInt233 = 2;
		rsinterface.mediaID = 4000;//
		rsinterface.modelZoom = zoom;
		rsinterface.modelRotation1 = 40;// 40;//wait
		rsinterface.modelRotation2 = 1900;// 1900;
		rsinterface.height = h;
		rsinterface.width = w;
	}

	public static void addHoverText(int id, String text, String tooltip,
			TextDrawingArea tda[], int idx, int color, boolean center,
			boolean textShadow, int width) {
		RSInterface rsinterface = addInterface(id);
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.type = 4;
		rsinterface.atActionType = 1;
		rsinterface.width = width;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.mOverInterToTrigger = -1;
		rsinterface.centerText = center;
		rsinterface.textShadow = textShadow;
		rsinterface.textDrawingAreas = tda[idx];
		rsinterface.message = text;
		// rsinterface.aString228 = "";
		rsinterface.textColor = color;
		rsinterface.anInt219 = 0;
		// rsinterface.anInt216 = 0xffffff;
		rsinterface.anInt239 = 0;
		rsinterface.tooltip = tooltip;
	}

	public static void questTab(TextDrawingArea[] TDA) {
		RSInterface localRSInterface = addInterface(639);
		setChildren(4, localRSInterface);
		addText(39155, "Quests", 16750623, false, true, 52, TDA, 2);
		addButton(39156, 1, "Interfaces/QuestTab/QUEST", 18, 18,
				"Swap to Achievements", 1);
		addSprite(39157, 0, "Interfaces/QuestTab/QUEST");
		setBounds(39155, 10, 5, 0, localRSInterface);
		setBounds(39156, 165, 5, 1, localRSInterface);
		setBounds(39157, 3, 24, 2, localRSInterface);
		setBounds(39160, 5, 29, 3, localRSInterface);
		localRSInterface = addInterface(39160);
		localRSInterface.height = 214;
		localRSInterface.width = 165;
		localRSInterface.scrollMax = 1700;
		setChildren(105, localRSInterface);
		addText(39161, "", 16750623, false, true, 52, TDA, 2);
		addHoverText(39162, "", "", TDA, 0, 16711680, false, true, 150);
		addText(39163, "Register at:", 16750623, false, true, 52, TDA, 0);
		addHoverText(39164, "Www.divinationofgods.tk", "Register", TDA, 0, 16711680,
				false, true, 150);
		addText(39165, "Register on :", 16750623, false, true, 52, TDA, 0);
		addHoverText(39166, "Www.divinationofgods.tk", "Register", TDA, 0, 16711680,
				false, true, 150);
		setBounds(39161, 8, 0, 0, localRSInterface);
		setBounds(39162, 8, 15, 1, localRSInterface);
		setBounds(39163, 8, 30, 2, localRSInterface);
		setBounds(39164, 8, 45, 3, localRSInterface);
		setBounds(663, 4, 60, 4, localRSInterface);
		int i = 83;
		int j = 5;
		for (int k = 39165; k <= 39264; k++) {
			addHoverText(k, "", "View Quest", TDA, 0, 16711680, false, true,
					150);
			setBounds(k, 8, i, j, localRSInterface);
			j++;
			i += 15;
			i++;
		}
		localRSInterface = addInterface(39265);
		try {
			setChildren(4, localRSInterface);
			addText(39266, " ", 16750623, false, true, -1, TDA, 2);
			addButton(39267, 2, "Interfaces/QuestTab/QUEST", 18, 18,
					"Swap to Quest Diary", 1);
			addSprite(39269, 0, "Interfaces/QuestTab/QUEST");
			setBounds(39266, 10, 5, 0, localRSInterface);
			setBounds(39267, 165, 5, 1, localRSInterface);
			setBounds(39269, 3, 24, 2, localRSInterface);
			setBounds(39268, 5, 29, 3, localRSInterface);
			localRSInterface = addInterface(39268);
			localRSInterface.height = 214;
			localRSInterface.width = 165;
			localRSInterface.scrollMax = 1700;
			setChildren(20, localRSInterface);
			setBounds(39295, 8, 4, 0, localRSInterface);
			setBounds(39296, 8, 16, 1, localRSInterface);
			setBounds(39297, 8, 29, 2, localRSInterface);
			setBounds(39298, 8, 42, 3, localRSInterface);
			setBounds(39299, 8, 54, 4, localRSInterface);
			setBounds(39300, 8, 66, 5, localRSInterface);
			setBounds(39301, 8, 78, 6, localRSInterface);
			setBounds(39302, 8, 90, 7, localRSInterface);
			setBounds(39303, 8, 102, 8, localRSInterface);
			setBounds(39304, 8, 114, 9, localRSInterface);
			setBounds(39305, 8, 126, 10, localRSInterface);
			setBounds(39306, 8, 138, 11, localRSInterface);
			setBounds(39307, 8, 150, 12, localRSInterface);
			setBounds(39308, 8, 162, 13, localRSInterface);
			setBounds(39309, 8, 174, 14, localRSInterface);
			setBounds(39310, 8, 186, 15, localRSInterface);
			setBounds(39311, 8, 198, 16, localRSInterface);
			setBounds(39312, 8, 210, 17, localRSInterface);
			setBounds(39313, 8, 222, 18, localRSInterface);
			setBounds(39314, 8, 234, 19, localRSInterface);
			addHoverText(39295, "", "", TDA, 0, 16750623, false, true, 150);
			addHoverText(39296, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39297, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39298, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39299, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39300, "", "", TDA, 0, 16711680, false, true, 150);
			addHoverText(39301, "", "", TDA, 0, 16750623, false, true, 150);
			addHoverText(39302, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39303, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39304, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39305, "", "", TDA, 0, 16711680, false, true, 150);
			addHoverText(39306, "", "", TDA, 0, 16750623, false, true, 150);
			addHoverText(39307, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39308, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39309, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39310, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39311, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39312, " ", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(39313, "", "", TDA, 0, 16711680, false, true, 150);
			addHoverText(39314, "", "", TDA, 0, 16711680, false, true, 150);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}
	
		public static void achievementTabb2(TextDrawingArea[] TDA) {
		RSInterface localRSInterface = addInterface(48000);
		setChildren(6, localRSInterface);
		addSprite(48001, 0, "Interfaces/Achieve/TASKSTRENGTH");
		addButton(48002, 0, "", 105, 50, "Easy tasks", 1);
		addButton(48003, 0, "", 105, 50, "Advanced tasks", 1);
		addButton(48004, 0, "", 105, 50, "Elite tasks", 1);
		addText(48005, "#", 16750623, false, true, 52, TDA, 2);
		addButton(48006, 0, "", 15, 20, "Swap to Quest tab", 1);
		setBounds(48001, 0, 0, 0, localRSInterface);
		setBounds(48002, 49, 53, 1, localRSInterface);
		setBounds(48003, 49, 100, 2, localRSInterface);
		setBounds(48004, 49, 157, 3, localRSInterface);
		setBounds(48005, 129, 229, 4, localRSInterface);
		setBounds(48006, 170, 3, 5, localRSInterface);
		}
		
		
		public static void achievemnttab(TextDrawingArea[] TDA) {
		RSInterface localRSInterface = addInterface(3332);
		setChildren(4, localRSInterface);
		addButton(33154, 2, "Interfaces/QuestTab/QUEST", 18, 18, "Swap to Quests", 1);
		
		addText(33155, "My Achievements", 16750623, false, true, 52, TDA, 2);
				
		addSprite(33157, 0, "Interfaces/QuestTab/QUEST");
		
		setBounds(33155, 10, 5, 0, localRSInterface);
		setBounds(33157, 3, 24, 1, localRSInterface);
		setBounds(33160, 5, 29, 2, localRSInterface);
		setBounds(33154, 165, 5, 3, localRSInterface);
		localRSInterface = addInterface(33160);
		localRSInterface.height = 214;
		localRSInterface.width = 165;
		localRSInterface.scrollMax = 1700;
		setChildren(105, localRSInterface);
		addText(33161, "", 0x00FFFF, false, true, 52, TDA, 2);
		addHoverText(33162, "", "", TDA, 0, 16711680, false, true, 150);
		addText(33163, "Register at:", 16750623, false, true, 52, TDA, 0);
		addHoverText(33164, "Www.divinationofgods.tk", "Register", TDA, 0, 16711680,
				false, true, 150);
		addText(33165, "Register on :", 16750623, false, true, 52, TDA, 0);
		addHoverText(33166, "Www.divinationofgods.tk", "Register", TDA, 0, 16711680,
				false, true, 150);
		setBounds(33161, 8, 0, 0, localRSInterface);
		setBounds(33162, 8, 15, 1, localRSInterface);
		setBounds(33163, 8, 30, 2, localRSInterface);
		setBounds(33164, 8, 45, 3, localRSInterface);
		setBounds(444, 4, 60, 4, localRSInterface);
		int i = 83;
		int j = 5;
		for (int k = 33165; k <= 33264; k++) {
			addHoverText(k, "", "View Quest", TDA, 0, 16711680, false, true,
					150);
			setBounds(k, 8, i, j, localRSInterface);
			j++;
			i += 15;
			i++;
		}
		localRSInterface = addInterface(33265);
		try {
			setChildren(4, localRSInterface);
			addText(33266, " ", 16750623, false, true, -1, TDA, 2);
			addButton(33267, 2, "Interfaces/QuestTab/QUEST", 18, 18,
					"Swap to Quest Diary", 1);
			addSprite(33269, 0, "Interfaces/QuestTab/QUEST");
			setBounds(33266, 10, 5, 0, localRSInterface);
			setBounds(33267, 165, 5, 1, localRSInterface);
			setBounds(33269, 3, 24, 2, localRSInterface);
			setBounds(33268, 5, 29, 3, localRSInterface);
			localRSInterface = addInterface(33268);
			localRSInterface.height = 214;
			localRSInterface.width = 165;
			localRSInterface.scrollMax = 1700;
			setChildren(20, localRSInterface);
			setBounds(33295, 8, 4, 0, localRSInterface);
			setBounds(33296, 8, 16, 1, localRSInterface);
			setBounds(33297, 8, 29, 2, localRSInterface);
			setBounds(33298, 8, 42, 3, localRSInterface);
			setBounds(33299, 8, 54, 4, localRSInterface);
			setBounds(33300, 8, 66, 5, localRSInterface);
			setBounds(33301, 8, 78, 6, localRSInterface);
			setBounds(33302, 8, 90, 7, localRSInterface);
			setBounds(33303, 8, 102, 8, localRSInterface);
			setBounds(33304, 8, 114, 9, localRSInterface);
			setBounds(33305, 8, 126, 10, localRSInterface);
			setBounds(33306, 8, 138, 11, localRSInterface);
			setBounds(33307, 8, 150, 12, localRSInterface);
			setBounds(33308, 8, 162, 13, localRSInterface);
			setBounds(33309, 8, 174, 14, localRSInterface);
			setBounds(33310, 8, 186, 15, localRSInterface);
			setBounds(33311, 8, 198, 16, localRSInterface);
			setBounds(33312, 8, 210, 17, localRSInterface);
			setBounds(33313, 8, 222, 18, localRSInterface);
			setBounds(33314, 8, 234, 19, localRSInterface);
			addHoverText(33295, "", "", TDA, 0, 16750623, false, true, 150);
			addHoverText(33296, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33297, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33298, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33299, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33300, "", "", TDA, 0, 16711680, false, true, 150);
			addHoverText(33301, "", "", TDA, 0, 16750623, false, true, 150);
			addHoverText(33302, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33303, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33304, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33305, "", "", TDA, 0, 16711680, false, true, 150);
			addHoverText(33306, "", "", TDA, 0, 16750623, false, true, 150);
			addHoverText(33307, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33308, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33309, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33310, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33311, "", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33312, " ", " ", TDA, 0, 16711680, false, true, 150);
			addHoverText(33313, "", "", TDA, 0, 16711680, false, true, 150);
			addHoverText(33314, "", "", TDA, 0, 16711680, false, true, 150);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	/*public static void dungTab(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(638);
		setChildren(4, Interface);
		addText(29155, "Quests", 0xFF981F, false, true, 52, TDA, 2);
		addButton(29156, 2, "Interfaces/QuestTab/QUEST", 18, 18,
				"Swap to Quest Tab", 1);
		addSprite(29157, 0, "Interfaces/QuestTab/QUEST");
		setBounds(29155, 10, 5, 0, Interface);
		setBounds(29156, 165, 5, 1, Interface);
		setBounds(29157, 3, 24, 2, Interface);
		setBounds(29160, 5, 29, 3, Interface);
		Interface = addInterface(29160);
		Interface.height = 214;
		Interface.width = 165;
		Interface.scrollMax = 1700;
		setChildren(105, Interface);
		addText(29161, "", 0xFF981F, false, true, 52, TDA, 2);
		addHoverText(29162, "", "", TDA, 0, 0xff0000, false, true, 150);
		addText(29163, "", 0xFF981F, false, true, 52, TDA, 2);
		addHoverText(29164, "", "", TDA, 0, 0xff0000, false, true, 150);
		addText(29165, "", 0xFF981F, false, true, 52, TDA, 2);
		addHoverText(29166, "", "", TDA, 0, 0xff0000, false, true, 150);
		setBounds(29161, 4, 4, 0, Interface);
		setBounds(29162, 8, 22, 1, Interface);
		setBounds(29163, 4, 35, 2, Interface);
		setBounds(29164, 8, 53, 3, Interface);
		setBounds(663, 4, 67, 4, Interface);
		int Ypos = 83;
		int frameID = 5;
		for (int iD = 29165; iD <= 29264; iD++) {
			addHoverText(iD, "", "Continue"/* "View Quest Journal, "+iD */
			/*
					TDA, 0, 0xff0000, false, true, 150);
			setBounds(iD, 8, Ypos, frameID, Interface);
			frameID++;
			Ypos += 15;
			Ypos++;
		}
		Interface = addInterface(29265);
		try {
			setChildren(4, Interface);
			addText(29266, "Coming soon!", 0xFF981F, false, true, -1, TDA, 2);
			addButton(29267, 1, "Interfaces/QuestTab/QUEST", 18, 18,
					"Swap to Player Info", 1);
			addSprite(29269, 0, "Interfaces/QuestTab/QUEST");
			setBounds(29266, 10, 5, 0, Interface);
			setBounds(29267, 165, 5, 1, Interface);
			setBounds(29269, 3, 24, 2, Interface);
			setBounds(29268, 5, 29, 3, Interface);
			Interface = addInterface(29268);
			Interface.height = 214;
			Interface.width = 165;
			Interface.scrollMax = 1700;
			setChildren(20, Interface);
			setBounds(29295, 8, 4, 0, Interface);
			setBounds(29296, 8, 16, 1, Interface);
			setBounds(29297, 8, 29, 2, Interface);
			setBounds(29298, 8, 42, 3, Interface);
			setBounds(29299, 8, 54, 4, Interface);
			setBounds(29300, 8, 66, 5, Interface);
			setBounds(29301, 8, 78, 6, Interface);
			setBounds(29302, 8, 90, 7, Interface);
			setBounds(29303, 8, 102, 8, Interface);
			setBounds(29304, 8, 114, 9, Interface);
			setBounds(29305, 8, 126, 10, Interface);
			setBounds(29306, 8, 138, 11, Interface);
			setBounds(29307, 8, 150, 12, Interface);
			setBounds(29308, 8, 162, 13, Interface);
			setBounds(29309, 8, 174, 14, Interface);
			setBounds(29310, 8, 186, 15, Interface);
			setBounds(29311, 8, 198, 16, Interface);
			setBounds(29312, 8, 210, 17, Interface);
			setBounds(29313, 8, 222, 18, Interface);
			setBounds(29314, 8, 234, 19, Interface);
			addHoverText(29295, "Please register at", "Please Register", TDA,
					1, 0xFF981F, false, true, 150);
			addHoverText(29296, "Www.divinationofgods.tk", "", TDA, 0, 0xff0000,
					false, true, 150);
			addHoverText(29297, "And advertise/vote daily!", "", TDA, 0,
					0xff0000, false, true, 150);
			addHoverText(29298, "::vote for more players!", "", TDA, 0,
					0xff0000, false, true, 150);
			addHoverText(29299, "More players=More updates!", "", TDA, 0,
					0xff0000, false, true, 150);
			addHoverText(29300, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29301, "", "", TDA, 1, 0xFF981F, false, true, 150);
			addHoverText(29302, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29303, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29304, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29305, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29306, "", "", TDA, 1, 0xFF981F, false, true, 150);
			addHoverText(29307, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29308, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29309, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29310, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29311, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29312, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29313, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29314, "", "", TDA, 0, 0xff0000, false, true, 150);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public static void staffTab(TextDrawingArea[] TDA) {
		RSInterface tab = addTabInterface(24999);
		addText(28024, "Staff List", 0xff9933, true, true, -1, TDA, 1);
			addSprite(16126, 0, "Interfaces/stafftab/SPRITE");
			addSprite(16127, 1, "Interfaces/stafftab/SPRITE");
			addHoverButton(28018, "Interfaces/stafftab/SPRITE", 2, 160, 32, "Request Staff Assistance", -1, 28019, 1);
			addHoveredButton(28019, "Interfaces/stafftab/SPRITE", 3, 160, 32, 28020);
			addText(28000, "None", 0xffffff, false, true, -1, TDA, 1);
			addText(28001, "Offline", 0xff0000, false, true, -1, TDA, 1);
			addText(28002, "None", 0xffffff, false, true, -1, TDA, 1);
			addText(28003, "Offline", 0xff0000, false, true, -1, TDA, 1);
			addText(28004, "None", 0xffffff, false, true, -1, TDA, 1);
			addText(28005, "Offline", 0xff0000, false, true, -1, TDA, 1);
			addText(28006, "None", 0xffffff, false, true, -1, TDA, 1);
			addText(28007, "Offline", 0xff0000, false, true, -1, TDA, 1);
			addText(28008, "None", 0xffffff, false, true, -1, TDA, 1);
			addText(28009, "Offline", 0xff0000, false, true, -1, TDA, 1);
			addText(28010, "None", 0xffffff, false, true, -1, TDA, 1);
			addText(28011, "Offline", 0xff0000, false, true, -1, TDA, 1);
			addText(28012, "None", 0xffffff, false, true, -1, TDA, 1);
			addText(28013, "Offline", 0xff0000, false, true, -1, TDA, 1);
			addText(28014, "None", 0xffffff, false, true, -1, TDA, 1);
			addText(28015, "Offline", 0xff0000, false, true, -1, TDA, 1);
			addText(28016, "None", 0xffffff, false, true, -1, TDA, 1);
			addText(28017, "Offline", 0xff0000, false, true, -1, TDA, 1);
			
			addText(28021, "Owners", 0xff9933, true, true, -1, TDA, 3);
			addText(28022, "Administrators", 0xff9933, true, true, -1, TDA, 3);
			addText(28023, "Moderators", 0xff9933, true, true, -1, TDA, 3);
		tab.totalChildren(32);
			tab.child(0, 28024, 95, 4);
			tab.child(1, 16127, 0, 25);
			tab.child(2, 16126, 0, 221);
			tab.child(3, 16126, 0, 22);
			tab.child(4, 16126, 0, 40);
			tab.child(5, 16126, 0, 73);
			tab.child(6, 16126, 0, 91);
			tab.child(7, 16126, 0, 124);
			tab.child(8, 16126, 0, 141);
			tab.child(9, 28000, 3, 42);
			tab.child(10, 28002, 3, 57);
			tab.child(11, 28004, 3, 93);
			tab.child(12, 28006, 3, 108);
			tab.child(13, 28008, 3, 144);
			tab.child(14, 28010, 3, 159);
			tab.child(15, 28012, 3, 174);
			tab.child(16, 28014, 3, 189);
			tab.child(17, 28016, 3, 204);
			tab.child(18, 28001, 146, 42);
			tab.child(19, 28003, 146, 57);
			tab.child(20, 28005, 146, 93);
			tab.child(21, 28007, 146, 108);
			tab.child(22, 28009, 146, 144);
			tab.child(23, 28011, 146, 159);
			tab.child(24, 28013, 146, 174);
			tab.child(25, 28015, 146, 189);
			tab.child(26, 28017, 146, 204);
			tab.child(27, 28018, 15, 226);
			tab.child(28, 28019, 15, 226);
			tab.child(29, 28021, 95, 24);
			tab.child(30, 28022, 95, 75);
			tab.child(31, 28023, 95, 125);
	}

	public static void customStrings(TextDrawingArea[] tda) {
		RSInterface rsinterface = addTabInterface(17050); // interface id
		addText(17051, "Custom Text", 0xff9040, false, true, 52, tda, 0); // text
																			// shown
		rsinterface.scrollMax = 0; // leave this
		rsinterface.children = new int[1]; // how many children there are ( only
											// added 1
		rsinterface.childX = new int[1]; // children x ( same as above ) needs
											// to be as many strings as there
											// are
		rsinterface.childY = new int[1]; // children y (same as above)
		rsinterface.children[0] = 17051; // first child starting the children
											// each will be + 1 of this one
		rsinterface.childX[0] = 40; // x coord
		rsinterface.childY[0] = 40; // y coord
	}

	public static void GodWars(TextDrawingArea[] TDA) {
		RSInterface rsinterface = addTabInterface(16220);
		addText(16211, "NPC killcount", 0xff9040, false, true, 52, TDA, 0);
		addText(16212, "Armadyl kills", 0xff9040, false, true, 52, TDA, 0);
		addText(16213, "Bandos kills", 0xff9040, false, true, 52, TDA, 0);
		addText(16214, "Saradomin kills", 0xff9040, false, true, 52, TDA, 0);
		addText(16215, "Zamorak kills", 0xff9040, false, true, 52, TDA, 0);
		addText(16216, "0", 0x66FFFF, false, true, 52, TDA, 0);// armadyl
		addText(16217, "0", 0x66FFFF, false, true, 52, TDA, 0);// bandos
		addText(16218, "0", 0x66FFFF, false, true, 52, TDA, 0);// saradomin
		addText(16219, "0", 0x66FFFF, false, true, 52, TDA, 0);// zamorak
		rsinterface.scrollMax = 0;
		rsinterface.children = new int[9];
		rsinterface.childX = new int[9];
		rsinterface.childY = new int[9];
		rsinterface.children[0] = 16211;
		rsinterface.childX[0] = -52 + 375 + 30;
		rsinterface.childY[0] = 7;
		rsinterface.children[1] = 16212;
		rsinterface.childX[1] = -52 + 375 + 30;
		rsinterface.childY[1] = 30;
		rsinterface.children[2] = 16213;
		rsinterface.childX[2] = -52 + 375 + 30;
		rsinterface.childY[2] = 44;
		rsinterface.children[3] = 16214;
		rsinterface.childX[3] = -52 + 375 + 30;
		rsinterface.childY[3] = 58;
		rsinterface.children[4] = 16215;
		rsinterface.childX[4] = -52 + 375 + 30;
		rsinterface.childY[4] = 73;

		rsinterface.children[5] = 16216;
		rsinterface.childX[5] = -52 + 460 + 60;
		rsinterface.childY[5] = 31;
		rsinterface.children[6] = 16217;
		rsinterface.childX[6] = -52 + 460 + 60;
		rsinterface.childY[6] = 45;
		rsinterface.children[7] = 16218;
		rsinterface.childX[7] = -52 + 460 + 60;
		rsinterface.childY[7] = 59;
		rsinterface.children[8] = 16219;
		rsinterface.childX[8] = -52 + 460 + 60;
		rsinterface.childY[8] = 74;
	}

	public static final void minigame(TextDrawingArea[] TDA) {
		RSInterface rsinterface = addTabInterface(45200);
		addText(45201, "Minigames Teleport", 0xff9b00, false, true, 52, TDA, 2);
		addHoverButton(45202, "Interfaces/Minigame/Hover", 0, 172, 24,
				"Duel Arena", -1, 45203, 1);
		addHoveredButton(45203, "Interfaces/Minigame/Hover", 4, 172, 24, 45204);
		addHoverButton(45218, "Interfaces/Minigame/Hover", 0, 172, 24,
				"Barrows", -1, 45219, 1);
		addHoveredButton(45219, "Interfaces/Minigame/Hover", 4, 172, 24, 45220);
		addHoverButton(45221, "Interfaces/Minigame/Hover", 0, 172, 24,
				"Pest Control", -1, 45222, 1);
		addHoveredButton(45222, "Interfaces/Minigame/Hover", 4, 172, 24, 45223);
		addHoverButton(45224, "Interfaces/Minigame/Hover", 0, 172, 24,
				"Tzhaar", -1, 45225, 1);
		addHoveredButton(45225, "Interfaces/Minigame/Hover", 4, 172, 24, 45226);
		addHoverButton(45227, "Interfaces/Minigame/Hover", 0, 172, 24,
				"Warriors Guild", -1, 45228, 1);
		addHoveredButton(45228, "Interfaces/Minigame/Hover", 4, 172, 24, 45229);
		addHoverButton(45233, "Interfaces/Minigame/Back", 0, 16, 16, "Back",
				-1, 45234, 1);
		addHoveredButton(45234, "Interfaces/Minigame/Back", 1, 16, 16, 45235);
		addSprite(45205, 1, "Interfaces/Minigame/DuelArena");
		addSprite(45206, 1, "Interfaces/Minigame/Barrows");
		addSprite(45207, 1, "Interfaces/Minigame/PestControl");
		addSprite(45208, 1, "Interfaces/Minigame/Tzhaar");
		addSprite(45209, 1, "Interfaces/Minigame/Warriors");
		addSprite(45211, 1, "Interfaces/Minigame/Background");
		addText(45212, "Duel Arena", 0xd67b29, true, true, 52, TDA, 2);
		addText(45213, "Barrows", 0xd67b29, true, true, 52, TDA, 2);
		addText(45214, "Pest Control", 0xd67b29, true, true, 52, TDA, 2);
		addText(45215, "Tzhaar", 0xd67b29, true, true, 52, TDA, 2);
		addText(45216, "Warriors Guild", 0xd67b29, true, true, 52, TDA, 2);
		byte childAmount = 24;
		int indexChild = 0;
		setChildren(childAmount, rsinterface);
		setBounds(45211, 0, 26, indexChild, rsinterface);
		indexChild++;
		setBounds(45201, 33, 7, indexChild, rsinterface);
		indexChild++;
		setBounds(45202, 8, 35, indexChild, rsinterface);
		indexChild++;
		setBounds(45203, 8, 35, indexChild, rsinterface);
		indexChild++;
		setBounds(45212, 80, 39, indexChild, rsinterface);
		indexChild++;
		setBounds(45218, 8, 72, indexChild, rsinterface);
		indexChild++;
		setBounds(45219, 8, 72, indexChild, rsinterface);
		indexChild++;
		setBounds(45213, 80, 76, indexChild, rsinterface);
		indexChild++;
		setBounds(45221, 8, 109, indexChild, rsinterface);
		indexChild++;
		setBounds(45222, 8, 109, indexChild, rsinterface);
		indexChild++;
		setBounds(45214, 80, 113, indexChild, rsinterface);
		indexChild++;
		setBounds(45224, 8, 146, indexChild, rsinterface);
		indexChild++;
		setBounds(45225, 8, 146, indexChild, rsinterface);
		indexChild++;
		setBounds(45215, 80, 150, indexChild, rsinterface);
		indexChild++;
		setBounds(45227, 8, 183, indexChild, rsinterface);
		indexChild++;
		setBounds(45228, 8, 183, indexChild, rsinterface);
		indexChild++;
		setBounds(45216, 80, 187, indexChild, rsinterface);
		indexChild++;
		setBounds(45205, 148, 33, indexChild, rsinterface);
		indexChild++;
		setBounds(45206, 148, 70, indexChild, rsinterface);
		indexChild++;
		setBounds(45207, 148, 104, indexChild, rsinterface);
		indexChild++;
		setBounds(45208, 148, 140, indexChild, rsinterface);
		indexChild++;
		setBounds(45209, 148, 179, indexChild, rsinterface);
		indexChild++;
		setBounds(45233, 10, 6, indexChild, rsinterface);
		indexChild++;
		setBounds(45234, 10, 6, indexChild, rsinterface);
		indexChild++;
	}

	public static void runecraftingTable(TextDrawingArea[] TDA) {

	RSInterface Interface = addInterface(27000); //Interface ID
	//Background here
	addSprite(27001, 1, "Interfaces/runecraftingTable/BACKGROUND");
	
	//Buttons Begin
	addHoverButton(27002, "Interfaces/runecraftingTable/SPRITE", 1, 82, 76, "Teleport to @gre@Air Altar", -1, 27002, 1);
	addHoverButton(27003, "Interfaces/runecraftingTable/SPRITE", 1, 82, 76, "Teleport to @gre@Mind Altar", -1, 27003, 1);
	addHoverButton(27004, "Interfaces/runecraftingTable/SPRITE", 1, 82, 76, "Teleport to @gre@Water Altar", -1, 27004, 1);
	addHoverButton(27005, "Interfaces/runecraftingTable/SPRITE", 1, 82, 76, "Teleport to @gre@Earth Altar", -1, 27005, 1);
	addHoverButton(27006, "Interfaces/runecraftingTable/SPRITE", 1, 82, 76, "Teleport to @gre@Fire Altar", -1, 27006, 1);
	addHoverButton(27007, "Interfaces/runecraftingTable/SPRITE", 1, 82, 76, "Teleport to @gre@Cosmic Altar", -1, 27007, 1);
	addHoverButton(27008, "Interfaces/runecraftingTable/SPRITE", 1, 82, 76, "Teleport to @gre@Chaos Altar", -1, 27008, 1);
	addHoverButton(27009, "Interfaces/runecraftingTable/SPRITE", 1, 82, 76, "Teleport to @gre@Astral Altar", -1, 27009, 1);
	addHoverButton(27010, "Interfaces/runecraftingTable/SPRITE", 1, 82, 76, "Teleport to @gre@Nature Altar", -1, 27010, 1);
	addHoverButton(27011, "Interfaces/runecraftingTable/SPRITE", 1, 82, 76, "Teleport to @gre@Law Altar", -1, 27011, 1);
	addHoverButton(27012, "Interfaces/runecraftingTable/SPRITE", 1, 82, 76, "Teleport to @gre@Death Altar", -1, 27012, 1);
	addHoverButton(27013, "Interfaces/runecraftingTable/SPRITE", 1, 82, 76, "Teleport to @gre@Blood Altar", -1, 27013, 1);
	addHoverButton(27014, "Interfaces/runecraftingTable/CLOSE", 1, 25, 25, "Close", -1, 27014, 1);
	//Buttons End
	
	//Set Bounds Begin
	setChildren(14, Interface); //Number of sprites/buttons
	setBounds(27001, 7, 6, 0, Interface); 
	setBounds(27002, 45, 61, 1, Interface); //105122 - Action Button IDs
	setBounds(27003, 155, 61, 2, Interface); //105123
	setBounds(27004, 265, 61, 3, Interface); //105124
	setBounds(27005, 388, 61, 4, Interface); //105125
	setBounds(27006, 40, 145, 5, Interface); //105126
	setBounds(27007, 158, 145, 6, Interface);//105127
	setBounds(27008, 266, 145, 7, Interface); //105128
	setBounds(27009, 385, 145, 8, Interface); //105129
	setBounds(27010, 38, 227, 9, Interface);//105130
	setBounds(27011, 158, 227, 10, Interface); //105131
	setBounds(27012, 265, 227, 11, Interface); //105132
	setBounds(27013, 385, 227, 12, Interface);//105133
	setBounds(27014, 477, 10, 13, Interface); //105134
    //Set Bounds End           
	}
  public static final void boss(TextDrawingArea[] paramArrayOfTextDrawingArea) {
    RSInterface localRSInterface = addTabInterface(45500);
    addText(45501, "Boss Teleport", 16751360, false, true, 52, paramArrayOfTextDrawingArea, 2);
    addHoverButton(45502, "Interfaces/Minigame/Hover", 0, 172, 24, "Nex", -1, 45503, 1);

    addHoveredButton(45503, "Interfaces/Minigame/Hover", 3, 172, 24, 45504);
    addHoverButton(45518, "Interfaces/Minigame/Hover", 0, 172, 24, "King Black Dragon", -1, 45519, 1);

    addHoveredButton(45519, "Interfaces/Minigame/Hover", 3, 172, 24, 45520);
    addHoverButton(45521, "Interfaces/Minigame/Hover", 0, 172, 24, "Dagannoth Kings", -1, 45522, 1);

    addHoveredButton(45522, "Interfaces/Minigame/Hover", 3, 172, 24, 45523);
    addHoverButton(45524, "Interfaces/Minigame/Hover", 0, 172, 24, "Tormented Demons", -1, 45525, 1);

    addHoveredButton(45525, "Interfaces/Minigame/Hover", 3, 172, 24, 45526);
    addHoverButton(45527, "Interfaces/Minigame/Hover", 0, 172, 24, "Corporal Beast", -1, 45528, 1);

    addHoveredButton(45528, "Interfaces/Minigame/Hover", 3, 172, 24, 45529);
    addHoverButton(45533, "Interfaces/Minigame/Back", 0, 16, 16, "Back", -1, 45534, 1);

    addHoveredButton(45534, "Interfaces/Minigame/Back", 1, 16, 16, 45535);
    addSprite(45505, 1, "Interfaces/Minigame/Godwars");
    addSprite(45506, 1, "Interfaces/Minigame/Kbd");
    addSprite(45507, 1, "Interfaces/Minigame/Dagganoths");
    addSprite(45508, 1, "Interfaces/Minigame/Chaos");
    addSprite(45509, 1, "Interfaces/Minigame/Corporeal");
    addSprite(45511, 1, "Interfaces/Minigame/Background");
    addText(45512, "Nex", 14056233, true, true, 52, paramArrayOfTextDrawingArea, 2);
    addText(45513, "King Black Dragon", 14056233, true, true, 52, paramArrayOfTextDrawingArea, 2);
    addText(45514, "Dagannoth Kings", 14056233, true, true, 52, paramArrayOfTextDrawingArea, 2);
    addText(45515, "Tormented Demons", 14056233, true, true, 52, paramArrayOfTextDrawingArea, 2);
    addText(45516, "Corporal Beast", 14056233, true, true, 52, paramArrayOfTextDrawingArea, 2);
    int i = 24;
    int j = 0;
    setChildren(i, localRSInterface);
    setBounds(45511, -1, 26, j, localRSInterface);
    j++;
    setBounds(45501, 33, 7, j, localRSInterface);
    j++;
    setBounds(45502, 8, 35, j, localRSInterface);
    j++;
    setBounds(45503, 8, 35, j, localRSInterface);
    j++;
    setBounds(45512, 80, 39, j, localRSInterface);
    j++;
    setBounds(45518, 8, 72, j, localRSInterface);
    j++;
    setBounds(45519, 8, 72, j, localRSInterface);
    j++;
    setBounds(45513, 80, 76, j, localRSInterface);
    j++;
    setBounds(45521, 8, 109, j, localRSInterface);
    j++;
    setBounds(45522, 8, 109, j, localRSInterface);
    j++;
    setBounds(45514, 80, 113, j, localRSInterface);
    j++;
    setBounds(45524, 8, 146, j, localRSInterface);
    j++;
    setBounds(45525, 8, 146, j, localRSInterface);
    j++;
    setBounds(45515, 80, 150, j, localRSInterface);
    j++;
    setBounds(45527, 8, 183, j, localRSInterface);
    j++;
    setBounds(45528, 8, 183, j, localRSInterface);
    j++;
    setBounds(45516, 80, 187, j, localRSInterface);
    j++;
    setBounds(45505, 148, 33, j, localRSInterface);
    j++;
    setBounds(45506, 148, 70, j, localRSInterface);
    j++;
    setBounds(45507, 148, 104, j, localRSInterface);
    j++;
    setBounds(45508, 148, 144, j, localRSInterface);
    j++;
    setBounds(45509, 148, 179, j, localRSInterface);
    j++;
    setBounds(45533, 10, 6, j, localRSInterface);
    j++;
    setBounds(45534, 10, 6, j, localRSInterface);
    j++;
  }
	public static void teleport(TextDrawingArea[] TDA) {
		RSInterface localRSInterface = addInterface(11650);
		addSprite(11651, 10, "CLICK");
		addHoverButton(11652, "CLICK", 2, 200, 30, "Which Zone?", -1, 11653, 1);
		addHoveredButton(11653, "CLICK", 2, 200, 30, 11654);
		addHoverButton(11655, "CLICK", 3, 200, 30, "Which Zone?", -1, 11656, 1);
		addHoveredButton(11656, "CLICK", 3, 200, 30, 11657);
		addHoverButton(11658, "CLICK", 3, 200, 30, "Which Zone?", -1, 11659, 1);
		addHoveredButton(11659, "CLICK", 3, 200, 30, 11660);
		addHoverButton(11661, "CLICK", 3, 200, 30, "Which Zone?", -1, 11662, 1);
		addHoveredButton(11662, "CLICK", 3, 200, 30, 11663);
		addHoverButton(11664, "CLICK", 3, 200, 30, "Which Zone?", -1, 11665, 1);
		addHoveredButton(11665, "CLICK", 3, 200, 30, 11666);
		addHoverButton(11667, "CLICK", 3, 200, 30, "Which Zone?", -1, 11668, 1);
		addHoveredButton(11668, "CLICK", 3, 200, 30, 11669);
		addHoverButton(11670, "CLICK", 3, 200, 30, "Which Zone?", -1, 11671, 1);
		addHoveredButton(11671, "CLICK", 3, 200, 30, 11672);
		addHoverButton(11673, "CLICK", 1, 200, 30, "Stop Viewing", -1, 11674, 1);
		addHoveredButton(11674, "CLICK", 1, 200, 30, 11675);
		addText(11204, "Yak's/Rock Crabs", TDA, 0, 16750623, false, true);
		addText(11208, "Taverly Dungeon", TDA, 0, 16750623, false, true);
		addText(11212, "Slayer Tower", TDA, 0, 16750623, false, true);
		addText(11216, "Brimhaven Dungeon", TDA, 0, 16750623, false, true);
		addText(11220, "Hill Giants", TDA, 0, 16750623, false, true);
		// addText(11220, "Hill Giants", TDA, false, true, -1, TDA, 2);
		addText(11224, "Dark Beasts", TDA, 0, 16750623, false, true);
		addText(11228, "Strykeworms", TDA, 0, 16750623, false, true);
		localRSInterface.totalChildren(24);
		localRSInterface.child(0, 11651, 0, 0);
		localRSInterface.child(1, 11652, 12, 40);
		localRSInterface.child(2, 11653, 11, 40);
		localRSInterface.child(3, 11655, 12, 65);
		localRSInterface.child(4, 11656, 11, 65);
		localRSInterface.child(5, 11658, 12, 90);
		localRSInterface.child(6, 11659, 11, 90);
		localRSInterface.child(7, 11661, 12, 115);
		localRSInterface.child(8, 11662, 11, 115);
		localRSInterface.child(9, 11664, 12, 143);
		localRSInterface.child(10, 11665, 11, 143);
		localRSInterface.child(11, 11667, 12, 168);
		localRSInterface.child(12, 11668, 11, 168);
		localRSInterface.child(13, 11670, 12, 193);
		localRSInterface.child(14, 11671, 11, 193);
		localRSInterface.child(15, 11673, 38, 236);
		localRSInterface.child(16, 11674, 38, 236);
		localRSInterface.child(17, 11204, 38, 45);
		localRSInterface.child(18, 11208, 38, 70);
		localRSInterface.child(19, 11212, 38, 95);
		localRSInterface.child(20, 11216, 38, 120);
		localRSInterface.child(21, 11220, 38, 147);
		localRSInterface.child(22, 11224, 38, 174);
		localRSInterface.child(23, 11228, 38, 201);
		localRSInterface = addTabInterface(14000);
		localRSInterface.width = 474;
		localRSInterface.height = 213;
		localRSInterface.scrollMax = 305;
		for (int i = 14001; i <= 14030; ++i) {
			addText(i, "", TDA, 1, 16777215, false, true);
		}
		localRSInterface.totalChildren(30);
		int i = 0;
		int j = 5;
		for (int k = 14001; k <= 14030; ++k) {
			localRSInterface.child(i, k, 248, j);
			++i;
			j += 13;
		}
	}

	public static void pouchCreation(TextDrawingArea TDA[]) {
		int totalScrolls = pouchItems.length;
		int xPadding = 53;
		int yPadding = 57;
		int xPos = 13;
		int yPos = 20;
		RSInterface rsinterface = addTabInterface(23471);
		setChildren(7, rsinterface);
		addSprite(23472, 1, "interfaces/summoning/creation/summoning");
		addButton(23475, 0, "interfaces/summoning/creation/tab",
				"Transform Scolls");
		addSprite(23474, 1, "interfaces/summoning/creation/pouch");
		addSprite(23473, 1, "interfaces/summoning/creation/tab");
		addSprite(23476, 0, "interfaces/summoning/creation/scroll");
		addInAreaHover(23477, "interfaces/summoning/creation/close", 0, 1, 16,
				16, "Close", 250, 3);
		RSInterface scroll = addTabInterface(23478);
		setChildren(3 * totalScrolls, scroll);
		for (int i = 0; i < totalScrolls; i++) {
			addInAreaHover(23479 + i * 8, "interfaces/summoning/creation/box",
					0, 1, 48, 52, "nothing", -1, 0);
			int req[] = { 1, 2, 3 };
			addPouch(23480 + i * 8, req, 1, pouchItems[i],
					summoningLevelRequirements[i], pouchNames[i], TDA, i, 5);
			addSprite(23485 + i * 8, pouchItems[i], null, 50, 50);
			setBounds(23479 + i * 8, 36 + (i % 8) * xPadding, 0 + (i / 8)
					* yPadding, 0 + i * 2, scroll);
			setBounds(23480 + i * 8, 43 + (i % 8) * xPadding, 2 + (i / 8)
					* yPadding, 1 + i * 2, scroll);
		}

		for (int i = 0; i < totalScrolls; i++) {
			int drawX = 5 + (i % 8) * xPadding;
			if (drawX > 292)
				drawX -= 90;
			int drawY = 55 + (i / 8) * yPadding;
			if (drawY > 160)
				drawY -= 80;
			setBounds(23481 + i * 8, drawX, drawY, 2 + (totalScrolls - 1) * 2
					+ i, scroll);
		}

		scroll.parentID = 23478;
		scroll.id = 23478;
		scroll.atActionType = 0;
		scroll.contentType = 0;
		scroll.width = 474;
		scroll.height = 257;
		scroll.scrollMax = 570;
		setBounds(23472, xPos, yPos, 0, rsinterface);
		setBounds(23473, xPos + 9, yPos + 9, 1, rsinterface);
		setBounds(23474, xPos + 29, yPos + 10, 2, rsinterface);
		setBounds(23475, xPos + 79, yPos + 9, 3, rsinterface);
		setBounds(23476, xPos + 106, yPos + 10, 4, rsinterface);
		setBounds(23477, xPos + 461, yPos + 10, 5, rsinterface);
		setBounds(23478, 0, yPos + 39, 6, rsinterface);
	}

	public static void scrollCreation(TextDrawingArea TDA[]) {
		int totalScrolls = pouchItems.length;
		int xPadding = 53;
		int yPadding = 57;
		int xPos = 13;
		int yPos = 20;
		RSInterface rsinterface = addTabInterface(22760);
		setChildren(7, rsinterface);
		addSprite(22761, 0, "interfaces/summoning/creation/summoning");
		addButton(22762, 0, "interfaces/summoning/creation/tab",
				"Infuse Pouches");
		addSprite(22763, 0, "interfaces/summoning/creation/pouch");
		addSprite(22764, 1, "interfaces/summoning/creation/tab");
		addSprite(22765, 1, "interfaces/summoning/creation/scroll");
		addInAreaHover(22766, "interfaces/summoning/creation/close", 0, 1, 16,
				16, "Close", 250, 3);
		RSInterface scroll = addTabInterface(22767);
		setChildren(4 * totalScrolls, scroll);
		for (int i = 0; i < totalScrolls; i++) {
			addInAreaHover(22768 + i * 9, "interfaces/summoning/creation/box",
					0, 1, 48, 52, "nothing", -1, 0);
			addScroll(22769 + i * 9, pouchItems[i], 1, scrollItems[i],
					summoningLevelRequirements[i], scrollNames[i], TDA, i, 5);
			addSprite(22776 + i * 9, pouchItems[i], null, 50, 50);
			setBounds(22768 + i * 9, 36 + (i % 8) * xPadding, 0 + (i / 8)
					* yPadding, 0 + i * 3, scroll);
			setBounds(22769 + i * 9, 43 + (i % 8) * xPadding, 2 + (i / 8)
					* yPadding, 1 + i * 3, scroll);
			setBounds(22776 + i * 9, 28 + (i % 8) * xPadding, 28 + (i / 8)
					* yPadding, 2 + i * 3, scroll);
		}

		for (int i = 0; i < totalScrolls; i++) {
			int drawX = 5 + (i % 8) * xPadding;
			if (drawX > 292)
				drawX -= 90;
			int drawY = 55 + (i / 8) * yPadding;
			if (drawY > 160)
				drawY -= 80;
			setBounds(22770 + i * 9, drawX, drawY, 3 + (totalScrolls - 1) * 3
					+ i, scroll);
		}

		scroll.parentID = 22767;
		scroll.id = 22767;
		scroll.atActionType = 0;
		scroll.contentType = 0;
		scroll.width = 474;
		scroll.height = 257;
		scroll.scrollMax = 570;
		setBounds(22761, xPos, yPos, 0, rsinterface);
		setBounds(22762, xPos + 9, yPos + 9, 1, rsinterface);
		setBounds(22763, xPos + 29, yPos + 10, 2, rsinterface);
		setBounds(22764, xPos + 79, yPos + 9, 3, rsinterface);
		setBounds(22765, xPos + 106, yPos + 10, 4, rsinterface);
		setBounds(22766, xPos + 461, yPos + 10, 5, rsinterface);
		setBounds(22767, 0, yPos + 39, 6, rsinterface);
	}

	public static void addScroll(int ID, int r1, int ra1, int r2, int lvl,
			String name, TextDrawingArea TDA[], int imageID, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID + 1;
		rsInterface.width = 32;
		rsInterface.height = 32;
		rsInterface.tooltip = (new StringBuilder()).append("Transform @or1@")
				.append(name).toString();
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[2];
		rsInterface.requiredValues = new int[2];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = lvl - 1;
		rsInterface.valueIndexArray = new int[3][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[3];
		rsInterface.valueIndexArray[1][0] = 1;
		rsInterface.valueIndexArray[1][1] = 6;
		rsInterface.valueIndexArray[1][2] = 0;
		rsInterface.itemSpriteId1 = r2;
		rsInterface.itemSpriteId2 = r2;
		rsInterface.itemSpriteIndex = imageID;
		rsInterface.greyScale = true;
		RSInterface hover = addTabInterface(ID + 1);
		hover.mOverInterToTrigger = -1;
		hover.isMouseoverTriggered = true;
		setChildren(5, hover);
		addSprite(ID + 2, 0, "Interfaces/Lunar/BOX");
		addText(ID + 3, (new StringBuilder()).append("Level ").append(lvl)
				.append(": ").append(name).toString(), 0xff981f, true, true,
				52, 1);
		addText(ID + 4, "This item requires", 0xaf6a1a, true, true, 52, 0);
		addRuneText(ID + 5, ra1, r1, TDA);
		addSprite(ID + 6, r1, null);
		setBounds(ID + 2, 0, 0, 0, hover);
		setBounds(ID + 3, 90, 4, 1, hover);
		setBounds(ID + 4, 90, 19, 2, hover);
		setBounds(ID + 5, 87, 66, 3, hover);
		setBounds(ID + 6, 72, 33, 4, hover);
	}

	public static void addPouch(int ID, int r1[], int ra1, int r2, int lvl,
			String name, TextDrawingArea TDA[], int imageID, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID + 1;
		rsInterface.width = 32;
		rsInterface.height = 32;
		rsInterface.tooltip = (new StringBuilder()).append("Infuse @or1@")
				.append(name).toString();
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[2];
		rsInterface.requiredValues = new int[2];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = lvl - 1;
		rsInterface.valueIndexArray = new int[2 + r1.length][];
		for (int i = 0; i < r1.length; i++) {
			rsInterface.valueIndexArray[i] = new int[4];
			rsInterface.valueIndexArray[i][0] = 4;
			rsInterface.valueIndexArray[i][1] = 3214;
			rsInterface.valueIndexArray[i][2] = r1[i];
			rsInterface.valueIndexArray[i][3] = 0;
		}

		rsInterface.valueIndexArray[1] = new int[3];
		rsInterface.valueIndexArray[1][0] = 1;
		rsInterface.valueIndexArray[1][1] = 6;
		rsInterface.valueIndexArray[1][2] = 0;
		rsInterface.itemSpriteId1 = r2;
		rsInterface.itemSpriteId2 = r2;
		rsInterface.itemSpriteIndex = imageID;
		rsInterface.greyScale = true;
		RSInterface hover = addTabInterface(ID + 1);
		hover.mOverInterToTrigger = -1;
		hover.isMouseoverTriggered = true;
		setChildren(5, hover);
		addSprite(ID + 2, 0, "Interfaces/Lunar/BOX");
		addText(ID + 3, (new StringBuilder()).append("Level ").append(lvl)
				.append(": ").append(name).toString(), 0xff981f, true, true,
				52, 1);
		addText(ID + 4, "This item requires", 0xaf6a1a, true, true, 52, 0);
		addRuneText(ID + 5, ra1, r1[0], TDA);
		addSprite(ID + 6, r1[0], null);
		addSprite(ID + 7, r1[1], null);
		addSprite(ID + 8, r1[2], null);
		setBounds(ID + 2, 0, 0, 0, hover);
		setBounds(ID + 3, 90, 4, 1, hover);
		setBounds(ID + 4, 90, 19, 2, hover);
		setBounds(ID + 5, 87, 66, 3, hover);
		setBounds(ID + 6, 14, 33, 4, hover);
	}

	public static void skillInterface(TextDrawingArea[] wid) {
		RSInterface rsinterface = addTabInterface(3917);
		skillInterface(19746, 255);
		skillInterface(19749, 52);
		addText(29801, "", wid, 0, 0xFFEE33); // Summoning
		addText(29800, "", wid, 0, 0xFFEE33); // Hunter

		addButton(19747, 51, 27700, "Skill/Skill", 62, 32,
				"View @lre@Hunter @whi@Guide", 1);
		addButton(19748, 50, 27701, "Skill/Skill", 62, 32,
				"@lre@Dismiss Summoning Familiar@whi@", 1);

		addText(13984, "Total", wid, 0, 0xFFEE33);
		addText(3985, "", wid, 0, 0xFFEE33);
		addText(13983, "", wid, 0, 0xFFEE33, true, true);
		for (int k = 0; k < boxIds.length; k++) {
			skillInterface(boxIds[k], 256);
		}
		// RSInterface rsinterface = addTabInterface(3917);
		rsinterface.children = new int[63];
		rsinterface.childX = new int[63];
		rsinterface.childY = new int[63];
		rsinterface.children[0] = 3918;
		rsinterface.childX[0] = 0;
		rsinterface.childY[0] = 0;
		rsinterface.children[1] = 3925;
		rsinterface.childX[1] = 0;
		rsinterface.childY[1] = 31;
		rsinterface.children[2] = 3932;
		rsinterface.childX[2] = 0;
		rsinterface.childY[2] = 62;
		rsinterface.children[3] = 3939;
		rsinterface.childX[3] = 0;
		rsinterface.childY[3] = 93;
		rsinterface.children[4] = 3946;
		rsinterface.childX[4] = 0;
		rsinterface.childY[4] = 124;
		rsinterface.children[5] = 3953;
		rsinterface.childX[5] = 0;
		rsinterface.childY[5] = 155;
		rsinterface.children[6] = 4148;
		rsinterface.childX[6] = 0;
		rsinterface.childY[6] = 186;
		rsinterface.children[7] = 19746;
		rsinterface.childX[7] = 70;
		rsinterface.childY[7] = 69;
		rsinterface.children[8] = 19748;
		rsinterface.childX[8] = 1;
		rsinterface.childY[8] = 219;
		rsinterface.children[9] = 19747;
		rsinterface.childX[9] = 64;
		rsinterface.childY[9] = 219;
		rsinterface.children[10] = 14000;
		rsinterface.childX[10] = 10;
		rsinterface.childY[10] = 219;
		rsinterface.children[11] = 19749;
		rsinterface.childX[11] = 128;
		rsinterface.childY[11] = 220;
		rsinterface.children[12] = 13983;
		rsinterface.childX[12] = 158;
		rsinterface.childY[12] = 238;
		rsinterface.children[13] = 3984;
		rsinterface.childX[13] = 300;
		rsinterface.childY[13] = 225;
		rsinterface.children[14] = 3985;
		rsinterface.childX[14] = 130;
		rsinterface.childY[14] = 238;
		rsinterface.children[15] = 29800;
		rsinterface.childX[15] = 98;
		rsinterface.childY[15] = 220;
		rsinterface.children[16] = 29800;
		rsinterface.childX[16] = 107;
		rsinterface.childY[16] = 235;
		rsinterface.children[17] = 29801;
		rsinterface.childX[17] = 36;
		rsinterface.childY[17] = 220;
		rsinterface.children[18] = 29801;
		rsinterface.childX[18] = 45;
		rsinterface.childY[18] = 235;
		rsinterface.children[19] = 4040;
		rsinterface.childX[19] = 5;
		rsinterface.childY[19] = 20;
		rsinterface.children[20] = 8654;
		rsinterface.childX[20] = 0;
		rsinterface.childY[20] = 2;
		rsinterface.children[21] = 8655;
		rsinterface.childX[21] = 64;
		rsinterface.childY[21] = 2;
		rsinterface.children[22] = 4076;
		rsinterface.childX[22] = 20;
		rsinterface.childY[22] = 20;
		rsinterface.children[23] = 8656;
		rsinterface.childX[23] = 128;
		rsinterface.childY[23] = 2;
		rsinterface.children[24] = 4112;
		rsinterface.childX[24] = 20;
		rsinterface.childY[24] = 20;
		rsinterface.children[25] = 8657;
		rsinterface.childX[25] = 0;
		rsinterface.childY[25] = 33;
		rsinterface.children[26] = 4046;
		rsinterface.childX[26] = 20;
		rsinterface.childY[26] = 50;
		rsinterface.children[27] = 8658;
		rsinterface.childX[27] = 64;
		rsinterface.childY[27] = 33;
		rsinterface.children[28] = 4082;
		rsinterface.childX[28] = 20;
		rsinterface.childY[28] = 50;
		rsinterface.children[29] = 8659;
		rsinterface.childX[29] = 128;
		rsinterface.childY[29] = 33;
		rsinterface.children[30] = 4118;
		rsinterface.childX[30] = 20;
		rsinterface.childY[30] = 50;
		rsinterface.children[31] = 8660;
		rsinterface.childX[31] = 0;
		rsinterface.childY[31] = 60 + 10;
		rsinterface.children[32] = 4052;
		rsinterface.childX[32] = 20;
		rsinterface.childY[32] = 83;
		rsinterface.children[33] = 8661;
		rsinterface.childX[33] = 65;
		rsinterface.childY[33] = 60 + 10;
		rsinterface.children[34] = 4088;
		rsinterface.childX[34] = 20;
		rsinterface.childY[34] = 83;
		rsinterface.children[35] = 8662;
		rsinterface.childX[35] = 130;
		rsinterface.childY[35] = 60 + 10;
		rsinterface.children[36] = 4124;
		rsinterface.childX[36] = 20;
		rsinterface.childY[36] = 83;
		rsinterface.children[37] = 8663;
		rsinterface.childX[37] = 0;
		rsinterface.childY[37] = 90 + 10;
		rsinterface.children[38] = 4058;
		rsinterface.childX[38] = 20;
		rsinterface.childY[38] = 120;
		rsinterface.children[39] = 8664;
		rsinterface.childX[39] = 65;
		rsinterface.childY[39] = 90 + 10;
		rsinterface.children[40] = 4094;
		rsinterface.childX[40] = 20;
		rsinterface.childY[40] = 120;
		rsinterface.children[41] = 8665;
		rsinterface.childX[41] = 130;
		rsinterface.childY[41] = 90 + 10;
		rsinterface.children[42] = 4130;
		rsinterface.childX[42] = 20;
		rsinterface.childY[42] = 120;
		rsinterface.children[43] = 8666;
		rsinterface.childX[43] = 0;
		rsinterface.childY[43] = 130;
		rsinterface.children[44] = 4064;
		rsinterface.childX[44] = 20;
		rsinterface.childY[44] = 150;
		rsinterface.children[45] = 8667;
		rsinterface.childX[45] = 65;
		rsinterface.childY[45] = 130;
		rsinterface.children[46] = 4100;
		rsinterface.childX[46] = 20;
		rsinterface.childY[46] = 150;
		rsinterface.children[47] = 8668;
		rsinterface.childX[47] = 130;
		rsinterface.childY[47] = 130;
		rsinterface.children[48] = 4136;
		rsinterface.childX[48] = 20;
		rsinterface.childY[48] = 150;
		rsinterface.children[49] = 8669;
		rsinterface.childX[49] = 0;
		rsinterface.childY[49] = 160;
		rsinterface.children[50] = 4070;
		rsinterface.childX[50] = 20;
		rsinterface.childY[50] = 180;
		rsinterface.children[51] = 8670;
		rsinterface.childX[51] = 65;
		rsinterface.childY[51] = 160;
		rsinterface.children[52] = 4106;
		rsinterface.childX[52] = 20;
		rsinterface.childY[52] = 180;
		rsinterface.children[53] = 8671;
		rsinterface.childX[53] = 130;
		rsinterface.childY[53] = 160;
		rsinterface.children[54] = 4142;
		rsinterface.childX[54] = 20;
		rsinterface.childY[54] = 180;
		rsinterface.children[55] = 8672;
		rsinterface.childX[55] = 0;
		rsinterface.childY[55] = 190;
		rsinterface.children[56] = 4160;
		rsinterface.childX[56] = 20;
		rsinterface.childY[56] = 150;
		rsinterface.children[57] = 4160;
		rsinterface.childX[57] = 20;
		rsinterface.childY[57] = 150;
		rsinterface.children[58] = 12162;
		rsinterface.childX[58] = 65;
		rsinterface.childY[58] = 190;
		rsinterface.children[59] = 2832;
		rsinterface.childX[59] = 20;
		rsinterface.childY[59] = 150;
		rsinterface.children[60] = 13928;
		rsinterface.childX[60] = 130;
		rsinterface.childY[60] = 190;
		rsinterface.children[61] = 13917;
		rsinterface.childX[61] = 20;
		rsinterface.childY[61] = 150;
		rsinterface.children[62] = 13984;
		rsinterface.childX[62] = 145;
		rsinterface.childY[62] = 225;
	}

	public static void skillInterface(int i, int j) {
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.type = 5;
		rsinterface.atActionType = 0;
		rsinterface.contentType = 0;
		rsinterface.width = 26;
		rsinterface.height = 34;
		rsinterface.opacity = 0;
		rsinterface.mOverInterToTrigger = 0;
		rsinterface.sprite1 = imageLoader(j, "Skill/Skill");
		rsinterface.sprite2 = imageLoader(j, "Skill/Skill");
	}

	public static void addButton(int i, int j, int hoverId, String name, int W,
			int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.type = 5;
		RSInterface.atActionType = AT;
		RSInterface.opacity = 0;
		RSInterface.mOverInterToTrigger = hoverId;
		RSInterface.sprite1 = imageLoader(j, name);
		RSInterface.sprite2 = imageLoader(j, name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}

	public static void addText(int id, String text, TextDrawingArea wid[],
			int idx, int color) {
		RSInterface rsinterface = addTabInterface(id);
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.type = 4;
		rsinterface.atActionType = 0;
		rsinterface.width = 174;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.mOverInterToTrigger = -1;
		rsinterface.centerText = false;
		rsinterface.textShadow = true;
		rsinterface.textDrawingAreas = wid[idx];
		rsinterface.message = text;
		rsinterface.textColor = color;
	}

	public static void addSprite(int i, int j, int k) {
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.type = 5;
		rsinterface.atActionType = 1;
		rsinterface.contentType = 0;
		rsinterface.width = 20;
		rsinterface.height = 20;
		rsinterface.opacity = 0;
		rsinterface.mOverInterToTrigger = 52;
		rsinterface.sprite1 = imageLoader(j, "Interfaces/Equipment/SPRITE");
		rsinterface.sprite2 = imageLoader(k, "Interfaces/Equipment/SPRITE");
	}

	public static void itemsOnDeath(TextDrawingArea[] wid) {
		RSInterface rsinterface = addInterface(17100);
		addSprite(17101, 2, 2);
		addHover(17102, 3, 0, 10601, 1, "Interfaces/Equipment/SPRITE", 17, 17,
				"Close Window");
		addHovered(10601, 3, "Interfaces/Equipment/SPRITE", 17, 17, 10602);
		addText(17103, "Items Kept On Death", wid, 2, 0xff981f);
		addText(17104, "Items you will keep on death (if not skulled):", wid,
				1, 0xff981f);
		addText(17105, "Items you will lose on death (if not skulled):", wid,
				1, 0xff981f);
		addText(17106, "Information", wid, 1, 0xff981f);
		addText(17107, "Max items kept on death:", wid, 1, 0xffcc33);
		addText(17108, "~ 3 ~", wid, 1, 0xffcc33);
		rsinterface.scrollMax = 0;
		rsinterface.isMouseoverTriggered = false;
		rsinterface.children = new int[12];
		rsinterface.childX = new int[12];
		rsinterface.childY = new int[12];

		rsinterface.children[0] = 17101;
		rsinterface.childX[0] = 7;
		rsinterface.childY[0] = 8;
		rsinterface.children[1] = 17102;
		rsinterface.childX[1] = 480;
		rsinterface.childY[1] = 17;
		rsinterface.children[2] = 17103;
		rsinterface.childX[2] = 185;
		rsinterface.childY[2] = 18;
		rsinterface.children[3] = 17104;
		rsinterface.childX[3] = 22;
		rsinterface.childY[3] = 50;
		rsinterface.children[4] = 17105;
		rsinterface.childX[4] = 22;
		rsinterface.childY[4] = 110;
		rsinterface.children[5] = 17106;
		rsinterface.childX[5] = 347;
		rsinterface.childY[5] = 47;
		rsinterface.children[6] = 17107;
		rsinterface.childX[6] = 349;
		rsinterface.childY[6] = 270;
		rsinterface.children[7] = 17108;
		rsinterface.childX[7] = 398;
		rsinterface.childY[7] = 298;
		rsinterface.children[8] = 17115;
		rsinterface.childX[8] = 348;
		rsinterface.childY[8] = 64;
		rsinterface.children[9] = 10494;
		rsinterface.childX[9] = 26;
		rsinterface.childY[9] = 74;
		rsinterface.children[10] = 10600;
		rsinterface.childX[10] = 26;
		rsinterface.childY[10] = 133;
		rsinterface.children[11] = 10601;
		rsinterface.childX[11] = 480;
		rsinterface.childY[11] = 17;
	}

	public static void itemsOnDeathDATA(TextDrawingArea[] wid) {
		RSInterface rsinterface = addInterface(17115);
		addText(17109, "", wid, 0, 0xff981f);
		addText(17110, "The normal amount of", wid, 0, 0xff981f);
		addText(17111, "items kept is three.", wid, 0, 0xff981f);
		addText(17112, "", wid, 0, 0xff981f);
		addText(17113, "If you are skulled,", wid, 0, 0xff981f);
		addText(17114, "you will lose all your", wid, 0, 0xff981f);
		addText(17117, "items, unless an item", wid, 0, 0xff981f);
		addText(17118, "protecting prayer is", wid, 0, 0xff981f);
		addText(17119, "used.", wid, 0, 0xff981f);
		addText(17120, "", wid, 0, 0xff981f);
		addText(17121, "Item protecting prayers", wid, 0, 0xff981f);
		addText(17122, "will allow you to keep", wid, 0, 0xff981f);
		addText(17123, "one extra item.", wid, 0, 0xff981f);
		addText(17124, "", wid, 0, 0xff981f);
		addText(17125, "The items kept are", wid, 0, 0xff981f);
		addText(17126, "selected by the server", wid, 0, 0xff981f);
		addText(17127, "and include the most", wid, 0, 0xff981f);
		addText(17128, "expensive items you're", wid, 0, 0xff981f);
		addText(17129, "carrying.", wid, 0, 0xff981f);
		addText(17130, "", wid, 0, 0xff981f);
		rsinterface.parentID = 17115;
		rsinterface.id = 17115;
		rsinterface.type = 0;
		rsinterface.atActionType = 0;
		rsinterface.contentType = 0;
		rsinterface.width = 130;
		rsinterface.height = 197;
		rsinterface.opacity = 0;
		rsinterface.mOverInterToTrigger = -1;
		rsinterface.scrollMax = 280;
		rsinterface.children = new int[20];
		rsinterface.childX = new int[20];
		rsinterface.childY = new int[20];
		rsinterface.children[0] = 17109;
		rsinterface.childX[0] = 0;
		rsinterface.childY[0] = 0;
		rsinterface.children[1] = 17110;
		rsinterface.childX[1] = 0;
		rsinterface.childY[1] = 12;
		rsinterface.children[2] = 17111;
		rsinterface.childX[2] = 0;
		rsinterface.childY[2] = 24;
		rsinterface.children[3] = 17112;
		rsinterface.childX[3] = 0;
		rsinterface.childY[3] = 36;
		rsinterface.children[4] = 17113;
		rsinterface.childX[4] = 0;
		rsinterface.childY[4] = 48;
		rsinterface.children[5] = 17114;
		rsinterface.childX[5] = 0;
		rsinterface.childY[5] = 60;
		rsinterface.children[6] = 17117;
		rsinterface.childX[6] = 0;
		rsinterface.childY[6] = 72;
		rsinterface.children[7] = 17118;
		rsinterface.childX[7] = 0;
		rsinterface.childY[7] = 84;
		rsinterface.children[8] = 17119;
		rsinterface.childX[8] = 0;
		rsinterface.childY[8] = 96;
		rsinterface.children[9] = 17120;
		rsinterface.childX[9] = 0;
		rsinterface.childY[9] = 108;
		rsinterface.children[10] = 17121;
		rsinterface.childX[10] = 0;
		rsinterface.childY[10] = 120;
		rsinterface.children[11] = 17122;
		rsinterface.childX[11] = 0;
		rsinterface.childY[11] = 132;
		rsinterface.children[12] = 17123;
		rsinterface.childX[12] = 0;
		rsinterface.childY[12] = 144;
		rsinterface.children[13] = 17124;
		rsinterface.childX[13] = 0;
		rsinterface.childY[13] = 156;
		rsinterface.children[14] = 17125;
		rsinterface.childX[14] = 0;
		rsinterface.childY[14] = 168;
		rsinterface.children[15] = 17126;
		rsinterface.childX[15] = 0;
		rsinterface.childY[15] = 180;
		rsinterface.children[16] = 17127;
		rsinterface.childX[16] = 0;
		rsinterface.childY[16] = 192;
		rsinterface.children[17] = 17128;
		rsinterface.childX[17] = 0;
		rsinterface.childY[17] = 204;
		rsinterface.children[18] = 17129;
		rsinterface.childX[18] = 0;
		rsinterface.childY[18] = 216;
		rsinterface.children[19] = 17130;
		rsinterface.childX[19] = 0;
		rsinterface.childY[19] = 228;
	}

	public void swapInventoryItems(int i, int j) {
		int k = inv[i];
		inv[i] = inv[j];
		inv[j] = k;
		k = invStackSizes[i];
		invStackSizes[i] = invStackSizes[j];
		invStackSizes[j] = k;
	}

	public static void Construction(TextDrawingArea TDA[]) {
		RSInterface Interface = addInterface(31250);
		setChildren(53, Interface);
		addHoverButton(29561, "Interfaces/Construction/BUTTON", 0, 16, 16,
				"Close", 0, 29562, 1);// CLOSE
		addHoveredButton(29562, "Interfaces/Construction/BUTTON", 1, 16, 16,
				29563);// CLOSE HOVER
		addSprite(31249, 0, "Interfaces/Construction/CONSTRUCTION");// BACKGROUND

		addButton(31251, 0, "Interfaces/Construction/CONS", "Build @or1@Fern");
		addTooltip(31252, "Fern (lvl 1):\n1x Guam, 1x Logs");

		addButton(31254, 1, "Interfaces/Construction/CONS", "Build @or1@Tree");
		addTooltip(31255, "Tree (lvl 5):\n3x Logs");

		addButton(31257, 2, "Interfaces/Construction/CONS", "Build @or1@Chair");
		addTooltip(31258, "Chair (lvl 19):\n10x Nails, 2x Oak plank");

		addButton(31260, 3, "Interfaces/Construction/CONS",
				"Build @or1@Bookcase");
		addTooltip(31261, "Bookcase (lvl 29):\n15x Nails, 3x Oak plank");

		addButton(31263, 4, "Interfaces/Construction/CONS",
				"Build @or1@Greenman's ale");
		addTooltip(31264, "Greenamn's ale (lvl 26):\n15x Nails, 2x Oak plank");

		addButton(31266, 5, "Interfaces/Construction/CONS",
				"Build @or1@Small oven");
		addTooltip(31267, "Small oven (lvl 24):\n2x Iron bar");

		addButton(31269, 6, "Interfaces/Construction/CONS",
				"Build @or1@Carved oak bench");
		addTooltip(31270, "Carved oak bench (lvl 31):\n15x Nails, 3x Oak plank");

		addButton(31272, 7, "Interfaces/Construction/CONS",
				"Build @or1@Painting stand");
		addTooltip(31273, "Painting stand (lvl 41):\n20x Nails, 2x Oak plank");

		addButton(31275, 8, "Interfaces/Construction/CONS", "Build @or1@Bed");
		addTooltip(31276, "Bed (lvl 40):\n20x Nails, 3x Oak plank");

		addButton(31278, 9, "Interfaces/Construction/CONS",
				"Build @or1@Teak drawers");
		addTooltip(31279, "Teak drawers (lvl 51):\n20x Nails, 2x Teak plank");

		addButton(31281, 10, "Interfaces/Construction/CONS",
				"Build @or1@Mithril armour");
		addTooltip(31282,
				"Mithril armour (lvl 28):\n1x Mithril full helm, platebody, platelegs");

		addButton(31284, 11, "Interfaces/Construction/CONS",
				"Build @or1@Adamant armour");
		addTooltip(31285,
				"Adamant armour (lvl 28):\n1x Adamant full helm, platebody, platelegs");

		addButton(31287, 12, "Interfaces/Construction/CONS",
				"Build @or1@Rune armour");
		addTooltip(31288,
				"Rune armour (lvl 28):\n1x Rune full helm, platebody, platelegs");

		addButton(31290, 13, "Interfaces/Construction/CONS",
				"Build @or1@Rune display case");
		addTooltip(31291,
				"Rune display case (lvl 41):\n100x Law rune, 100x Nature rune, 1x Teak plank");

		addButton(31293, 14, "Interfaces/Construction/CONS",
				"Build @or1@Archery target");
		addTooltip(31294, "Archery target (lvl 81):\n25x Nails, 3x Teak plank");

		addButton(31296, 15, "Interfaces/Construction/CONS",
				"Build @or1@Combat stone");
		addTooltip(31297, "Combat stone (lvl 59):\n4x Iron bar");

		addButton(31299, 16, "Interfaces/Construction/CONS",
				"Build @or1@Elemental balance");
		addTooltip(31300, "Elemental balance (lvl 77):\n4x Iron bar");

		addButton(31302, 17, "Interfaces/Construction/CONS",
				"Build @or1@Mahogany prize chest");
		addTooltip(31303,
				"Mahogany prize chest (lvl 54):\n20x Nails, 2x Mahogany plank");

		addButton(31305, 18, "Interfaces/Construction/CONS",
				"Build @or1@Lectern");
		addTooltip(31306, "Lectern (lvl 67):\n40x Nails, 2x Mahogany plank");

		addButton(31308, 19, "Interfaces/Construction/CONS",
				"Build @or1@Crystal of power");
		addTooltip(31309,
				"Crystal of power (lvl 66):\n15x Nails, 2x Mahogany plank, 1x Iron bar");

		addButton(31311, 20, "Interfaces/Construction/CONS", "Build @or1@Altar");
		addTooltip(31312,
				"Altar (lvl 64):\n15x Nails, 2x Mahogany plank, 1x Iron bar");

		addButton(31314, 21, "Interfaces/Construction/CONS",
				"Build @or1@Intense burners");
		addTooltip(31315,
				"Intense burners (lvl 61):\n10x Nails, 2x Mahogany plank, 1x Kwuarm");

		addButton(31317, 22, "Interfaces/Construction/CONS", "Build @or1@Hedge");
		addTooltip(31318, "Hedge (lvl 80):\n2x Logs, 2x Kwuarm");

		addButton(31320, 23, "Interfaces/Construction/CONS",
				"Build @or1@Rocnar");
		addTooltip(31321, "Rocnar (lvl 83):\n2x Adamant bar, 2x Kwuarm");

		addButton(31323, 24, "Interfaces/Construction/CONS",
				"Build @or1@Bank chest");
		addTooltip(31324,
				"Bank chest (lvl 92):\n40x Nails, 2x Mahogany plank, 1x Iron bar");

		setBounds(29561, 413, 9, 1, Interface);// CLOSE
		setBounds(29562, 413, 9, 2, Interface);// CLOSE HOVER
		setBounds(31249, 69, 3, 0, Interface);// BACKOGRUND X Y

		setBounds(31251, 109, 28, 3, Interface);// Build item
		setBounds(31252, 76, 285, 4, Interface);// Requirements

		setBounds(31254, 172, 28, 5, Interface);// Build item
		setBounds(31255, 76, 285, 6, Interface);// Requirements

		setBounds(31257, 236, 28, 7, Interface);// Build item
		setBounds(31258, 76, 285, 8, Interface);// Requirements

		setBounds(31260, 300, 28, 9, Interface);// Build item
		setBounds(31261, 76, 285, 10, Interface);// Requirements

		setBounds(31263, 364, 28, 11, Interface);// Build item
		setBounds(31264, 76, 285, 12, Interface);// Requirements

		setBounds(31266, 109, 76, 13, Interface);// Build item
		setBounds(31267, 76, 285, 14, Interface);// Requirements

		setBounds(31269, 172, 76, 15, Interface);// Build item
		setBounds(31270, 76, 285, 16, Interface);// Requirements

		setBounds(31272, 236, 76, 17, Interface);// Build item
		setBounds(31273, 76, 285, 18, Interface);// Requirements

		setBounds(31275, 300, 76, 19, Interface);// Build item
		setBounds(31276, 76, 285, 20, Interface);// Requirements

		setBounds(31278, 364, 76, 21, Interface);// Build item
		setBounds(31279, 76, 285, 22, Interface);// Requirements

		setBounds(31281, 109, 124, 23, Interface);// Build item
		setBounds(31282, 76, 285, 24, Interface);// Requirements

		setBounds(31284, 172, 124, 25, Interface);// Build item
		setBounds(31285, 76, 285, 26, Interface);// Requirements

		setBounds(31287, 236, 124, 27, Interface);// Build item
		setBounds(31288, 76, 285, 28, Interface);// Requirements

		setBounds(31290, 300, 124, 29, Interface);// Build item
		setBounds(31291, 76, 285, 30, Interface);// Requirements

		setBounds(31293, 364, 124, 31, Interface);// Build item
		setBounds(31294, 76, 285, 32, Interface);// Requirements

		setBounds(31296, 109, 172, 33, Interface);// Build item
		setBounds(31297, 76, 285, 34, Interface);// Requirements

		setBounds(31299, 172, 172, 35, Interface);// Build item
		setBounds(31300, 76, 285, 36, Interface);// Requirements

		setBounds(31302, 236, 172, 37, Interface);// Build item
		setBounds(31303, 76, 285, 38, Interface);// Requirements

		setBounds(31305, 300, 172, 39, Interface);// Build item
		setBounds(31306, 76, 285, 40, Interface);// Requirements

		setBounds(31308, 364, 172, 41, Interface);// Build item
		setBounds(31309, 76, 285, 42, Interface);// Requirements

		setBounds(31311, 109, 220, 43, Interface);// Build item
		setBounds(31312, 76, 285, 44, Interface);// Requirements

		setBounds(31314, 172, 220, 45, Interface);// Build item
		setBounds(31315, 76, 285, 46, Interface);// Requirements

		setBounds(31317, 236, 220, 47, Interface);// Build item
		setBounds(31318, 76, 285, 48, Interface);// Requirements

		setBounds(31320, 300, 220, 49, Interface);// Build item
		setBounds(31321, 76, 285, 50, Interface);// Requirements

		setBounds(31323, 364, 220, 51, Interface);// Build item
		setBounds(31324, 76, 285, 52, Interface);// Requirements

		Interface = addInterface(31330);
		addSprite(31329, 1, "Interfaces/Construction/CONSTRUCTION");// Back

		addHoverButton(31331, "Interfaces/Construction/BUTTON", 2, 90, 44,
				"Choose", 0, 31332, 1);
		addHoveredButton(31332, "Interfaces/Construction/BUTTON", 4, 90, 44,
				31333);

		addHoverButton(31334, "Interfaces/Construction/BUTTON", 2, 90, 44,
				"Choose", 0, 31335, 1);
		addHoveredButton(31335, "Interfaces/Construction/BUTTON", 4, 90, 44,
				31336);

		addText(31337, "Public", 0xFFEE33, false, true, 52, TDA, 2);
		addText(31338, "Private", 0xFFEE33, false, true, 52, TDA, 2);

		addHoverButton(29561, "Interfaces/Construction/BUTTON", 0, 16, 16,
				"Close", 0, 29562, 1);// CLOSE
		addHoveredButton(29562, "Interfaces/Construction/BUTTON", 1, 16, 16,
				29563);// CLOSE HOVER

		setChildren(9, Interface);
		setBounds(31329, 169, 79, 0, Interface);// Back

		setBounds(31331, 195, 95, 1, Interface);// Button 1
		setBounds(31332, 195, 95, 2, Interface);// Button 1

		setBounds(31334, 195, 157, 3, Interface);// Button 2
		setBounds(31335, 195, 157, 4, Interface);// Button 2

		setBounds(31337, 210, 108, 5, Interface);// Text 1
		setBounds(31338, 210, 170, 6, Interface);// Text 2

		setBounds(29561, 289, 85, 7, Interface);// CLOSE
		setBounds(29562, 289, 85, 8, Interface);// CLOSE HOVER
	}

	public static void summoningLevelUp(TextDrawingArea wid[]) {
		RSInterface Interface = addTabInterface(22602);
		setChildren(2, Interface);
		addSprite(22603, 0, "interfaces/summoning/cons2/levelup");
		setBounds(6206, 0, 5, 0, Interface);
		setBounds(22603, 22, 5, 1, Interface);
	}

	public static void newTrade(TextDrawingArea TDA[]) {
		RSInterface Interface = addInterface(3323);
		setChildren(19, Interface);
		addSprite(3324, 6, "Interfaces/TradeTab/TRADE");
		addHover(3442, 3, 0, 3325, 1, "Interfaces/Bank/BANK", 17, 17,
				"Close Window");
		addHovered(3325, 2, "Interfaces/Bank/BANK", 17, 17, 3326);
		addText(3417, "Trading With:", 0xFF981F, true, true, 52, TDA, 2);
		addText(3418, "Trader's Offer", 0xFF981F, false, true, 52, TDA, 1);
		addText(3419, "Your Offer", 0xFF981F, false, true, 52, TDA, 1);
		addText(3421, "Accept", 0x00C000, true, true, 52, TDA, 1);
		addText(3423, "Decline", 0xC00000, true, true, 52, TDA, 1);

		addText(3431, "Waiting For Other Player", 0xFFFFFF, true, true, 52,
				TDA, 1);
		addText(12504,
				"Wealth transfer: 2147,000,000 coins' worth to Zezimablud12",
				0xB9B855, true, true, -1, TDA, 0);
		addText(12505, "1 has\\n 28 free\\n inventory slots.", 0xFF981F, true,
				true, -1, TDA, 0);

		addText(12506,
				"Wealth transfer: 2147,000,000 coins' worth to Zezimablud12",
				0xB9B855, false, true, -1, TDA, 0);
		addText(12507, "Wealth transfer: 2147,000,000 coins' worth to me",
				0xB9B855, false, true, -1, TDA, 0);

		addHover(3420, 1, 0, 3327, 5, "Interfaces/TradeTab/TRADE", 65, 32,
				"Accept");
		addHovered(3327, 2, "Interfaces/TradeTab/TRADE", 65, 32, 3328);
		addHover(3422, 3, 0, 3329, 5, "Interfaces//TradeTab/TRADE", 65, 32,
				"Decline");
		addHovered(3329, 2, "Interfaces/TradeTab/TRADE", 65, 32, 3330);
		setBounds(3324, 0, 16, 0, Interface);
		setBounds(3442, 485, 24, 1, Interface);
		setBounds(3325, 485, 24, 2, Interface);
		setBounds(3417, 258, 25, 3, Interface);
		setBounds(3418, 355, 51, 4, Interface);
		setBounds(3419, 68, 51, 5, Interface);
		setBounds(3420, 223, 120, 6, Interface);
		setBounds(3327, 223, 120, 7, Interface);
		setBounds(3422, 223, 160, 8, Interface);
		setBounds(3329, 223, 160, 9, Interface);
		setBounds(3421, 256, 127, 10, Interface);
		setBounds(3423, 256, 167, 11, Interface);
		setBounds(3431, 256, 272, 12, Interface);
		setBounds(3415, 12, 64, 13, Interface);
		setBounds(3416, 321, 67, 14, Interface);
		setBounds(12505, 256, 67, 16, Interface);
		setBounds(12504, 255, 310, 15, Interface);
		setBounds(12506, 20, 310, 17, Interface);
		setBounds(12507, 380, 310, 18, Interface);
		Interface = addInterface(3443);
		setChildren(15, Interface);
		addSprite(3444, 3, "Interfaces/TradeTab/TRADE");
		AddInterfaceButton(3546, 2, "Interfaces/ShopTab/SHOP", 63, 24,
				"Accept", 1);
		AddInterfaceButton(3548, 2, "Interfaces/ShopTab/SHOP", 63, 24,
				"Decline", 3);
		addText(3547, "Accept", 0x00C000, true, true, 52, TDA, 1);
		addText(3549, "Decline", 0xC00000, true, true, 52, TDA, 1);
		addText(3450, "Trading With:", 0x00FFFF, true, true, 52, TDA, 2);
		addText(3451, "Yourself", 0x00FFFF, true, true, 52, TDA, 2);
		setBounds(3444, 12, 20, 0, Interface);
		setBounds(3442, 470, 32, 1, Interface);
		setBounds(3325, 470, 32, 2, Interface);
		setBounds(3535, 130, 28, 3, Interface);
		setBounds(3536, 105, 47, 4, Interface);
		setBounds(3546, 189, 295, 5, Interface);
		setBounds(3548, 258, 295, 6, Interface);
		setBounds(3547, 220, 299, 7, Interface);
		setBounds(3549, 288, 299, 8, Interface);
		setBounds(3557, 71, 87, 9, Interface);
		setBounds(3558, 315, 87, 10, Interface);
		setBounds(3533, 64, 70, 11, Interface);
		setBounds(3534, 297, 70, 12, Interface);
		setBounds(3450, 95, 289, 13, Interface);
		setBounds(3451, 95, 304, 14, Interface);
	}

	public static void Shop(TextDrawingArea[] TDA) {
		RSInterface rsinterface = addTabInterface(3824);
		setChildren(8, rsinterface);
		addSprite(3825, 0, "Interfaces/Shop/SHOP");
		addHover(3902, 3, 0, 3826, 1, "Interfaces/Shop/CLOSE", 17, 17,
				"Close Window");
		addHovered(3826, 2, "Interfaces/Shop/CLOSE", 17, 17, 3827);
		addText(19679, "", 0xff981f, false, true, 52, TDA, 1);
		addText(19680, "", 0xbf751d, false, true, 52, TDA, 1);
		addButton(19681, 2, "Interfaces/Shop/SHOP", 0, 0, "", 1);
		addSprite(19687, 1, "Interfaces/Shop/ITEMBG");
		setBounds(3825, 6, 8, 0, rsinterface);
		setBounds(3902, 478, 10, 1, rsinterface);
		setBounds(3826, 478, 10, 2, rsinterface);
		setBounds(3900, 26, 44, 3, rsinterface);
		setBounds(3901, 240, 11, 4, rsinterface);
		setBounds(19679, 42, 54, 5, rsinterface);
		setBounds(19680, 150, 54, 6, rsinterface);
		setBounds(19681, 129, 50, 7, rsinterface);
		rsinterface = interfaceCache[3900];
		setChildren(1, rsinterface);
		setBounds(19687, 6, 15, 0, rsinterface);
		rsinterface.invSpritePadX = 15;
		rsinterface.width = 10;
		rsinterface.height = 4;
		rsinterface.invSpritePadY = 25;
		rsinterface = addTabInterface(19682);
		addSprite(19683, 1, "Interfaces/Shop/SHOP");
		addText(19684, "Main Stock", 0xbf751d, false, true, 52, TDA, 1);
		addText(19685, "Store Info", 0xff981f, false, true, 52, TDA, 1);
		addButton(19686, 2, "Interfaces/Shop/SHOP", 95, 19, "Main Stock", 1);
		setChildren(7, rsinterface);
		setBounds(19683, 12, 12, 0, rsinterface);
		setBounds(3901, 240, 21, 1, rsinterface);
		setBounds(19684, 42, 54, 2, rsinterface);
		setBounds(19685, 150, 54, 3, rsinterface);
		setBounds(19686, 23, 50, 4, rsinterface);
		setBounds(3902, 471, 22, 5, rsinterface);
		setBounds(3826, 60, 85, 6, rsinterface);
	}
	
	/*	public static void Shop(TextDrawingArea[] TDA) {
		RSInterface rsinterface = addTabInterface(3824);
		setChildren(8, rsinterface);
		addSprite(3825, 0, "Shop/SHOP");
		
		addHoverButton(3902, "Shop/SPRITE", 1, 13, 13, "Close", -1, 3826, 1);
		addHoveredButton(3826, "Shop/SPRITE", 2, 13, 13, 3827);	
		
		//addHover(3902, 3, 0, 3826, 1, "Shop/CLOSE", 17, 17, "Close Window");
		//addHovered(3826, 2, "Shop/CLOSE", 17, 17, 3827);
		addText(19679, "", 0xff981f, false, true, 52, TDA, 1);
		addText(19680, "", 0xbf751d, false, true, 52, TDA, 1);
		addButton(19681, 2, "Shop/SHOP", 0, 0, "", 1);
		addSprite(19687, 1, "Shop/ITEMBG");
		setBounds(3825, 6, 8, 0, rsinterface);
		setBounds(3902, 487, 10, 1, rsinterface);
		setBounds(3826, 487, 10, 2, rsinterface);
		setBounds(3900, 26, 44, 3, rsinterface);
		setBounds(3901, 240, 11, 4, rsinterface);
		setBounds(19679, 42, 54, 5, rsinterface);
		setBounds(19680, 150, 54, 6, rsinterface);
		setBounds(19681, 129, 50, 7, rsinterface);
		rsinterface = interfaceCache[3900];
		setChildren(1, rsinterface);
		setBounds(19687, 6, 15, 0, rsinterface);
		rsinterface.invSpritePadX = 15;
		rsinterface.width = 10;
		rsinterface.height = 4;
		rsinterface.invSpritePadY = 25;
		rsinterface = addTabInterface(19682);
		addSprite(19683, 1, "Shop/SHOP");
		addText(19684, "Main Stock", 0xbf751d, false, true, 52, TDA, 1);
		addText(19685, "Store Info", 0xff981f, false, true, 52, TDA, 1);
		addButton(19686, 2, "Shop/SHOP", 95, 19, "Main Stock", 1);
		setChildren(7, rsinterface);
		setBounds(19683, 12, 12, 0, rsinterface);
		setBounds(3901, 240, 21, 1, rsinterface);
		setBounds(19684, 42, 54, 2, rsinterface);
		setBounds(19685, 150, 54, 3, rsinterface);
		setBounds(19686, 23, 50, 4, rsinterface);
		setBounds(3902, 471, 22, 5, rsinterface);
		setBounds(2826, 60, 85, 6, rsinterface);
	}*/
		public static void addInvItems(int index)
	{
		RSInterface rsi = interfaceCache[index] = new RSInterface();
		rsi.itemActions = new String[5];
		rsi.spritesX = new int[20];
		rsi.invStackSizes = new int[30];
		rsi.inv = new int[30];
		rsi.spritesY = new int[20];

		rsi.children = new int[0];
		rsi.childX = new int[0];
		rsi.childY = new int[0];
		
		rsi.centerText = false;
		rsi.aBoolean227 = false;
		rsi.aBoolean235 = false;
		rsi.usableItemInterface = false;
		rsi.isInventoryInterface = false;
		//rsi.aBoolean251 = false;
		rsi.aBoolean259 = true;
		//rsi.interfaceShown = false;
		rsi.textShadow = false;
		//rsi.hoverType = -1;
		rsi.invSpritePadX = 12;
		rsi.invSpritePadY = 4;
		rsi.height = 7;
		rsi.width = 4;
		rsi.parentID = 2802;
		rsi.id = 2800;
		rsi.type = 2;
	}
	public static void Bank(TextDrawingArea[] wid){
		RSInterface Interface = addTabInterface(5292);
		setChildren(38, Interface);
		addSprite(5293, 0, "Bank/BANK");
		setBounds(5293, 13, 13, 0, Interface);
		addHover(5384, 3, 0, 5380, 1, "Bank/BANK", 17, 17, "Close Window");
		addHovered(5380, 2, "Bank/BANK", 17, 17, 5379);
		setBounds(5384, 476, 16, 3, Interface);
		setBounds(5380, 476, 16, 4, Interface);	
		addHover(5294, 4, 0, 5295, 3, "Bank/BANK", 114, 25, "Set A Bank PIN");
		addHovered(5295, 4, "Bank/BANK", 114, 25, 5296);
		setBounds(5294, 110, 285, 5, Interface);
		setBounds(5295, 110, 285, 6, Interface);
		addBankHover(22000, 4, 22001, 5, 8, "Bank/BANK", 35, 25, 304, 1, "Swap Withdraw Mode", 22002, 7, 6, "Bank/BANK", 22003, "Switch to insert items \nmode", "Switch to swap items \nmode.", 12, 20);
		setBounds(22000, 25, 285, 7, Interface);
		setBounds(22001, 10, 225, 8, Interface);
		addBankHover(22004, 4, 22005, 13, 15, "Bank/BANK", 35, 25, 0, 1, "Search", 22006, 14, 16, "Bank/BANK", 22007, "Click here to search your \nbank", "Click here to search your \nbank", 12, 20);
		setBounds(22004, 65, 285, 9, Interface);
		setBounds(22005, 50, 225, 10, Interface);
		addBankHover(22008, 4, 22009, 9, 11, "Bank/BANK", 35, 25, 115, 1, "Withdraw", 22010, 10, 12, "Bank/BANK", 22011, "Switch to note withdrawal \nmode", "Switch to item withdrawal \nmode", 12, 20);
		setBounds(22008, 240, 285, 11, Interface);
		setBounds(22009, 225, 225, 12, Interface);
		addBankHover1(22012, 5, 22013, 17, "Bank/BANK", 35, 25, "Deposit carried tems", 22014, 18, "Bank/BANK", 22015, "Empty your backpack into\nyour bank", 0, 20);
		setBounds(22012, 375, 285, 13, Interface);
		setBounds(22013, 360, 225, 14, Interface);
		addBankHover1(22016, 5, 22017, 19, "Bank/BANK", 35, 25, "Deposit worn items", 22018, 20, "Bank/BANK", 22019, "Empty the items your are\nwearing into your bank", 0, 20);
		setBounds(22016, 415, 285, 15, Interface);
		setBounds(22017, 400, 225, 16, Interface);
		addBankHover1(22020, 5, 22021, 21, "Bank/BANK", 35, 25, "Deposit beast of burden inventory.", 22022, 22, "Bank/BANK", 22023, "Empty your BoB's inventory\ninto your bank", 0, 20);
		setBounds(22020, 455, 285, 17, Interface);
		setBounds(22021, 440, 225, 18, Interface);
		setBounds(5383, 170, 15, 1, Interface);
		setBounds(5385, -4, 74, 2, Interface);
		addButton(22024, 0, "BANK/TAB", "Click here to view the full contents of your bank");
		setBounds(22024, 22, 36, 19, Interface);
		addButton(22025, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22025, 70, 36, 20, Interface);
		addButton(22026, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22026, 118, 36, 21, Interface);
		addButton(22027, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22027, 166, 36, 22, Interface);
		addButton(22028, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22028, 214, 36, 23, Interface);
		addButton(22029, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22029, 262, 36, 24, Interface);
		addButton(22030, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22030, 310, 36, 25, Interface);
		addButton(22031, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22031, 358, 36, 26, Interface);
		addButton(22032, 4, "BANK/TAB", "Drag an item here to create a new tab");
		setBounds(22032, 406, 36, 27, Interface);
		addText(22033, "134", wid, 0, 0xB4965A, true, false);
		setBounds(22033, 473, 42, 28, Interface);
		addText(22034, "496", wid, 0, 0xB4965A, true, false);
		setBounds(22034, 473, 57, 29, Interface);
		addBankItem(22035, false);
		setBounds(22035, 77, 39, 30, Interface);
		addBankItem(22036, false);
		setBounds(22036, 125, 39, 31, Interface);
		addBankItem(22037, false);
		setBounds(22037, 173, 39, 32, Interface);
		addBankItem(22038, false);
		setBounds(22038, 221, 39, 33, Interface);
		addBankItem(22039, false);
		setBounds(22039, 269, 39, 34, Interface);
		addBankItem(22040, false);
		setBounds(22040, 317, 39, 35, Interface);
		addBankItem(22041, false);
		setBounds(22041, 365, 39, 36, Interface);
		addBankItem(22042, false);
		setBounds(22042, 413, 39, 37, Interface);
		
		addText(27000, "0", 0xFF981F, false, true, 52, wid, 1);
		addText(27001, "0", 0xFF981F, false, true, 52, wid, 1);
		addText(27002, "0", 0xFF981F, false, true, 52, wid, 1);
		
		Interface = interfaceCache[5385];
		Interface.height = 206;
		Interface.width = 480;
		Interface = interfaceCache[5382];
		Interface.width = 10;
		Interface.invSpritePadX = 12;
		Interface.height = 35;
	}
  	public static void addBankItem(int index, Boolean hasOption)
	{
		RSInterface rsi = interfaceCache[index] = new RSInterface();
		rsi.itemActions = new String[5];
		rsi.spritesX = new int[20];
		rsi.invStackSizes = new int[30];
		rsi.inv = new int[30];
		rsi.spritesY = new int[20];

		rsi.children = new int[0];
		rsi.childX = new int[0];
		rsi.childY = new int[0];
		
		//rsi.hasExamine = false;
		
		rsi.invSpritePadX = 24;
		rsi.invSpritePadY = 24;
		rsi.height = 5;
		rsi.width = 6;
		rsi.parentID = 5292;
		rsi.id = index;
		rsi.type = 2;
	}
	/*public static void Bank() {
		RSInterface Interface = addTabInterface(5292);
		setChildren(19, Interface);
		addSprite(5293, 0, "Interfaces/Bank/BANK");
		setBounds(5293, 13, 13, 0, Interface);
		addHover(5384, 3, 0, 5380, 1, "Interfaces/Bank/BANK", 17, 17,
				"Close Window");
		addHovered(5380, 2, "Interfaces/Bank/BANK", 17, 17, 5379);
		setBounds(5384, 476, 16, 3, Interface);
		setBounds(5380, 476, 16, 4, Interface);
		addHover(5294, 4, 0, 5295, 3, "Interfaces/Bank/BANK", 114, 25,
				"Set A Bank PIN");
		addHovered(5295, 4, "Interfaces/Bank/BANK", 114, 25, 5296);
		setBounds(5294, 110, 285, 5, Interface);
		setBounds(5295, 110, 285, 6, Interface);
		addBankHover(21000, 4, 21001, 5, 8, "Interfaces/Bank/BANK", 35, 25,
				304, 1, "Swap Withdraw Mode", 21002, 7, 6,
				"Interfaces/Bank/BANK", 21003, "Switch to insert items \nmode",
				"Switch to swap items \nmode.", 12, 20);
		setBounds(21000, 25, 285, 7, Interface);
		setBounds(21001, 10, 225, 8, Interface);
		addBankHover(21004, 4, 21005, 13, 15, "Interfaces/Bank/BANK", 35, 25,
				0, 1, "Search", 21006, 14, 16, "Interfaces/Bank/BANK", 21007,
				"Click here to search your \nbank",
				"Click here to search your \nbank", 12, 20);
		setBounds(21004, 65, 285, 9, Interface);
		setBounds(21005, 50, 225, 10, Interface);
		addBankHover(21008, 4, 21009, 9, 11, "Interfaces/Bank/BANK", 35, 25,
				115, 1, "Search", 21010, 10, 12, "Interfaces/Bank/BANK", 21011,
				"Switch to note withdrawal \nmode",
				"Switch to item withdrawal \nmode", 12, 20);
		setBounds(21008, 240, 285, 11, Interface);
		setBounds(21009, 225, 225, 12, Interface);
		addBankHover1(21012, 5, 21013, 17, "Interfaces/Bank/BANK", 35, 25,
				"Deposit carried tems", 21014, 18, "Interfaces/Bank/BANK",
				21015, "Empty your backpack into\nyour bank", 0, 20);
		setBounds(21012, 375, 285, 13, Interface);
		setBounds(21013, 360, 225, 14, Interface);
		addBankHover1(21016, 5, 21017, 19, "Interfaces/Bank/BANK", 35, 25,
				"Deposit worn items", 21018, 20, "Interfaces/Bank/BANK", 21019,
				"Empty the items your are\nwearing into your bank", 0, 20);
		setBounds(21016, 415, 285, 15, Interface);
		setBounds(21017, 400, 225, 16, Interface);
		addBankHover1(21020, 5, 21021, 21, "Interfaces/Bank/BANK", 35, 25,
				"Deposit beast of burden inventory.", 21022, 22,
				"Interfaces/Bank/BANK", 21023,
				"Empty your BoB's inventory\ninto your bank", 0, 20);
		setBounds(21020, 455, 285, 17, Interface);
		setBounds(21021, 440, 225, 18, Interface);
		setBounds(5383, 170, 15, 1, Interface);
		setBounds(5385, -4, 74, 2, Interface);
		Interface = interfaceCache[5385];
		Interface.height = 206;
		Interface.width = 480;
		Interface = interfaceCache[5382];
		Interface.width = 10;
		Interface.invSpritePadX = 12;
		Interface.height = 35;
	}*/

	public static void addBankHover(int interfaceID, int actionType,
			int hoverid, int spriteId, int spriteId2, String NAME, int Width,
			int Height, int configFrame, int configId, String Tooltip,
			int hoverId2, int hoverSpriteId, int hoverSpriteId2,
			String hoverSpriteName, int hoverId3, String hoverDisabledText,
			String hoverEnabledText, int X, int Y) {
		RSInterface hover = addTabInterface(interfaceID);
		hover.id = interfaceID;
		hover.parentID = interfaceID;
		hover.type = 5;
		hover.atActionType = actionType;
		hover.contentType = 0;
		hover.opacity = 0;
		hover.mOverInterToTrigger = hoverid;
		hover.sprite1 = imageLoader(spriteId, NAME);
		hover.sprite2 = imageLoader(spriteId2, NAME);
		hover.width = Width;
		hover.tooltip = Tooltip;
		hover.height = Height;
		hover.valueCompareType = new int[1];
		hover.requiredValues = new int[1];
		hover.valueCompareType[0] = 1;
		hover.requiredValues[0] = configId;
		hover.valueIndexArray = new int[1][3];
		hover.valueIndexArray[0][0] = 5;
		hover.valueIndexArray[0][1] = configFrame;
		hover.valueIndexArray[0][2] = 0;
		hover = addTabInterface(hoverid);
		hover.parentID = hoverid;
		hover.id = hoverid;
		hover.type = 0;
		hover.atActionType = 0;
		hover.width = 550;
		hover.height = 334;
		hover.isMouseoverTriggered = true;
		hover.mOverInterToTrigger = -1;
		addSprite(hoverId2, hoverSpriteId, hoverSpriteId2, hoverSpriteName,
				configId, configFrame);
		addHoverBox(hoverId3, interfaceID, hoverDisabledText, hoverEnabledText,
				configId, configFrame);
		setChildren(2, hover);
		setBounds(hoverId2, 15, 60, 0, hover);
		setBounds(hoverId3, X, Y, 1, hover);
	}

	public static void addBankHover1(int interfaceID, int actionType,
			int hoverid, int spriteId, String NAME, int Width, int Height,
			String Tooltip, int hoverId2, int hoverSpriteId,
			String hoverSpriteName, int hoverId3, String hoverDisabledText,
			int X, int Y) {
		RSInterface hover = addTabInterface(interfaceID);
		hover.id = interfaceID;
		hover.parentID = interfaceID;
		hover.type = 5;
		hover.atActionType = actionType;
		hover.contentType = 0;
		hover.opacity = 0;
		hover.mOverInterToTrigger = hoverid;
		hover.sprite1 = imageLoader(spriteId, NAME);
		hover.width = Width;
		hover.tooltip = Tooltip;
		hover.height = Height;
		hover = addTabInterface(hoverid);
		hover.parentID = hoverid;
		hover.id = hoverid;
		hover.type = 0;
		hover.atActionType = 0;
		hover.width = 550;
		hover.height = 334;
		hover.isMouseoverTriggered = true;
		hover.mOverInterToTrigger = -1;
		addSprite(hoverId2, hoverSpriteId, hoverSpriteId, hoverSpriteName, 0, 0);
		addHoverBox(hoverId3, interfaceID, hoverDisabledText,
				hoverDisabledText, 0, 0);
		setChildren(2, hover);
		setBounds(hoverId2, 15, 60, 0, hover);
		setBounds(hoverId3, X, Y, 1, hover);
	}

	public static void addHoverBox(int id, int ParentID, String text,
			String text2, int configId, int configFrame) {
		RSInterface rsi = addTabInterface(id);
		rsi.id = id;
		rsi.parentID = ParentID;
		rsi.type = 8;
		rsi.disabledText = text;
		rsi.message = text2;
		rsi.valueCompareType = new int[1];
		rsi.requiredValues = new int[1];
		rsi.valueCompareType[0] = 1;
		rsi.requiredValues[0] = configId;
		rsi.valueIndexArray = new int[1][3];
		rsi.valueIndexArray[0][0] = 5;
		rsi.valueIndexArray[0][1] = configFrame;
		rsi.valueIndexArray[0][2] = 0;
	}

	public static void addSprite(int ID, int i, int i2, String name,
			int configId, int configFrame) {
		RSInterface Tab = addTabInterface(ID);
		Tab.id = ID;
		Tab.parentID = ID;
		Tab.type = 5;
		Tab.atActionType = 0;
		Tab.contentType = 0;
		Tab.width = 512;
		Tab.height = 334;
		Tab.opacity = 0;
		Tab.mOverInterToTrigger = -1;
		Tab.valueCompareType = new int[1];
		Tab.requiredValues = new int[1];
		Tab.valueCompareType[0] = 1;
		Tab.requiredValues[0] = configId;
		Tab.valueIndexArray = new int[1][3];
		Tab.valueIndexArray[0][0] = 5;
		Tab.valueIndexArray[0][1] = configFrame;
		Tab.valueIndexArray[0][2] = 0;
		if (name == null) {
			Tab.itemSpriteZoom1 = -1;
			Tab.itemSpriteId1 = i;
			Tab.itemSpriteZoom2 = 70;
			Tab.itemSpriteId2 = i2;
		} else {
			Tab.sprite1 = imageLoader(i, name);
			Tab.sprite2 = imageLoader(i2, name);
		}
	}

	public static void addTransparentSprite(int id, int spriteId,
			String spriteName, int op) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 10;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(spriteId, spriteName);
		tab.sprite2 = imageLoader(spriteId, spriteName);
		tab.width = 512;
		tab.height = 334;
		tab.opacity = (byte) op;
		tab.drawsTransparent = true;
	}

	public static void addPrayerWithTooltip(int i, int configId,
			int configFrame, int requiredValues, int prayerSpriteID, int Hover,
			String tooltip) {
		RSInterface Interface = addTabInterface(i);
		Interface.id = i;
		Interface.parentID = 5608;
		Interface.type = 5;
		Interface.atActionType = 4;
		Interface.contentType = 0;
		Interface.opacity = 0;
		Interface.mOverInterToTrigger = Hover;
		Interface.sprite1 = imageLoader(0, "Interfaces/PrayerTab/PRAYERGLOW");
		Interface.sprite2 = imageLoader(1, "Interfaces/PrayerTab/PRAYERGLOW");
		Interface.width = 34;
		Interface.height = 34;
		Interface.valueCompareType = new int[1];
		Interface.requiredValues = new int[1];
		Interface.valueCompareType[0] = 1;
		Interface.requiredValues[0] = configId;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 5;
		Interface.valueIndexArray[0][1] = configFrame;
		Interface.valueIndexArray[0][2] = 0;
		Interface.tooltip = tooltip;
		Interface = addTabInterface(i + 1);
		Interface.id = i + 1;
		Interface.parentID = 5608;
		Interface.type = 5;
		Interface.atActionType = 0;
		Interface.contentType = 0;
		Interface.opacity = 0;
		Interface.sprite1 = imageLoader(prayerSpriteID,
				"Interfaces/PrayerTab/PRAYERON");
		Interface.sprite2 = imageLoader(prayerSpriteID,
				"Interfaces/PrayerTab/PRAYEROFF");
		Interface.width = 34;
		Interface.height = 34;
		Interface.valueCompareType = new int[1];
		Interface.requiredValues = new int[1];
		Interface.valueCompareType[0] = 2;
		Interface.requiredValues[0] = requiredValues + 1;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 2;
		Interface.valueIndexArray[0][1] = 5;
		Interface.valueIndexArray[0][2] = 0;
	}

	public static void addPrayer(int i, int configId, int configFrame,
			int requiredValues, int prayerSpriteID, String PrayerName, int Hover) {
		RSInterface Interface = addTabInterface(i);
		Interface.id = i;
		Interface.parentID = 22500;
		Interface.type = 5;
		Interface.atActionType = 4;
		Interface.contentType = 0;
		Interface.opacity = 0;
		Interface.mOverInterToTrigger = Hover;
		Interface.sprite1 = imageLoader(0, "Interfaces/CurseTab/GLOW");
		Interface.sprite2 = imageLoader(1, "Interfaces/CurseTab/GLOW");
		Interface.width = 34;
		Interface.height = 34;
		Interface.valueCompareType = new int[1];
		Interface.requiredValues = new int[1];
		Interface.valueCompareType[0] = 1;
		Interface.requiredValues[0] = configId;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 5;
		Interface.valueIndexArray[0][1] = configFrame;
		Interface.valueIndexArray[0][2] = 0;
		Interface.tooltip = "Activate@or1@ " + PrayerName;
		Interface = addTabInterface(i + 1);
		Interface.id = i + 1;
		Interface.parentID = 22500;
		Interface.type = 5;
		Interface.atActionType = 0;
		Interface.contentType = 0;
		Interface.opacity = 0;
		Interface.sprite1 = imageLoader(prayerSpriteID,
				"Interfaces/CurseTab/PRAYON");
		Interface.sprite2 = imageLoader(prayerSpriteID,
				"Interfaces/CurseTab/PRAYOFF");
		Interface.width = 34;
		Interface.height = 34;
		Interface.valueCompareType = new int[1];
		Interface.requiredValues = new int[1];
		Interface.valueCompareType[0] = 2;
		Interface.requiredValues[0] = requiredValues + 1;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 2;
		Interface.valueIndexArray[0][1] = 5;
		Interface.valueIndexArray[0][2] = 0;
	}

	public static void addSpriteWithHover(int id, int spriteId,
			String spriteName, int hover) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte) 0;
		tab.type = hover;
		tab.sprite1 = imageLoader(spriteId, spriteName);
		tab.sprite2 = imageLoader(spriteId, spriteName);
		tab.width = 190;
		tab.height = 47;
	}

	public static void Curses(TextDrawingArea[] TDA) {
		RSInterface Interface = addTabInterface(22500);
		int index = 0;
		addText(687, "99/99", 0xFF981F, false, false, -1, TDA, 1);
		addSprite(22502, 0, "Interfaces/CurseTab/ICON");
		addPrayer(22503, 0, 610, 49, 7, "Protect Item", 22582);// 1
		addPrayer(22505, 0, 611, 49, 4, "Sap Warrior", 22544);// 2
		addPrayer(22507, 0, 612, 51, 5, "Sap Ranger", 22546);// 3
		addPrayer(22509, 0, 613, 53, 3, "Sap Mage", 22548);// 4
		addPrayer(22511, 0, 614, 55, 2, "Sap Spirit", 22550);// 5
		addPrayer(22513, 0, 615, 58, 18, "Berserker", 22552);// 6
		addPrayer(22515, 0, 616, 61, 15, "Deflect Summoning", 22554);// /7
		addPrayer(22517, 0, 617, 64, 17, "Deflect Magic", 22556);// /8
		addPrayer(22519, 0, 618, 67, 16, "Deflect Missiles", 22558);// /9
		addPrayer(22521, 0, 619, 70, 6, "Deflect Melee", 22560);// /10
		addPrayer(22523, 0, 620, 73, 9, "Leech Attack", 22562);// 11
		addPrayer(22525, 0, 621, 75, 10, "Leech Ranged", 22564);// 12
		addPrayer(22527, 0, 622, 77, 11, "Leech Magic", 22566);// 13
		addPrayer(22529, 0, 623, 79, 12, "Leech Defence", 22568);// 14
		addPrayer(22531, 0, 624, 81, 13, "Leech Strength", 22570);// 15
		addPrayer(22533, 0, 625, 83, 14, "Leech Energy", 22572);// 16
		addPrayer(22535, 0, 626, 85, 19, "Leech Special Attack", 22574);// 17
		addPrayer(22537, 0, 627, 88, 1, "Wrath", 22576);// /18
		addPrayer(22539, 0, 628, 91, 8, "Soul Split", 22578);// /19
		addPrayer(22541, 0, 629, 94, 20, "Turmoil", 22580);// 20
		addTooltip(22582,
				"Level 50\nProtect Item\nKeep 1 extra item if you die");
		addTooltip(
				22544,
				"Level 50\nSap Warrior\nDrains 10% of enemy Attack,\nStrength and Defence,\nincreasing to 20% over time");
		addTooltip(
				22546,
				"Level 52\nSap Ranger\nDrains 10% of enemy Ranged\nand Defence, increasing to 20%\nover time");
		addTooltip(
				22548,
				"Level 54\nSap Mage\nDrains 10% of enemy Magic\nand Defence, increasing to 20%\nover time");
		addTooltip(22550,
				"Level 56\nSap Spirit\nDrains enenmy special attack\nenergy");
		addTooltip(22552, "Level 59\nBerserker\nBoosted stats last 15% longer");
		addTooltip(
				22554,
				"Level 62\nDeflect Summoning\nReduces damage dealt from\nSummoning scrolls, prevents the\nuse of a familiar's special\nattack, and can deflect some of\ndamage back to the attacker");
		addTooltip(
				22556,
				"Level 65\nDeflect Magic\nProtects against magical attacks\nand can deflect some of the\ndamage back to the attacker");
		addTooltip(
				22558,
				"Level 68\nDeflect Missiles\nProtects against ranged attacks\nand can deflect some of the\ndamage back to the attacker");
		addTooltip(
				22560,
				"Level 71\nDeflect Melee\nProtects against melee attacks\nand can deflect some of the\ndamage back to the attacker");
		addTooltip(
				22562,
				"Level 74\nLeech Attack\nBoosts Attack by 5%, increasing\nto 10% over time, while draining\nenemy Attack by 10%, increasing\nto 25% over time");
		addTooltip(
				22564,
				"Level 76\nLeech Ranged\nBoosts Ranged by 5%, increasing\nto 10% over time, while draining\nenemy Ranged by 10%,\nincreasing to 25% over\ntime");
		addTooltip(
				22566,
				"Level 78\nLeech Magic\nBoosts Magic by 5%, increasing\nto 10% over time, while draining\nenemy Magic by 10%, increasing\nto 25% over time");
		addTooltip(
				22568,
				"Level 80\nLeech Defence\nBoosts Defence by 5%, increasing\nto 10% over time, while draining\n enemy Defence by10%,\nincreasing to 25% over\ntime");
		addTooltip(
				22570,
				"Level 82\nLeech Strength\nBoosts Strength by 5%, increasing\nto 10% over time, while draining\nenemy Strength by 10%, increasing\n to 25% over time");
		addTooltip(22572,
				"Level 84\nLeech Energy\nDrains enemy run energy, while\nincreasing your own");
		addTooltip(
				22574,
				"Level 86\nLeech Special Attack\nDrains enemy special attack\nenergy, while increasing your\nown");
		addTooltip(22576,
				"Level 89\nWrath\nInflicts damage to nearby\ntarget if you die");
		addTooltip(
				22578,
				"Level 92\nSoul Split\n1/4 of damage dealt is also removed\nfrom opponent's Prayer and\nadded to your Hitpoints");
		addTooltip(
				22580,
				"Level 95\nTurmoil\nIncreases Attack and Defence\nby 15%, plus 15% of enemy's\nlevel, and Strength by 23% plus\n10% of enemy's level");
		setChildren(62, Interface);

		setBounds(687, 85, 241, index, Interface);
		index++;
		setBounds(22502, 65, 241, index, Interface);
		index++;
		setBounds(22503, 2, 5, index, Interface);
		index++;
		setBounds(22504, 8, 8, index, Interface);
		index++;
		setBounds(22505, 40, 5, index, Interface);
		index++;
		setBounds(22506, 47, 12, index, Interface);
		index++;
		setBounds(22507, 76, 5, index, Interface);
		index++;
		setBounds(22508, 82, 11, index, Interface);
		index++;
		setBounds(22509, 113, 5, index, Interface);
		index++;
		setBounds(22510, 116, 8, index, Interface);
		index++;
		setBounds(22511, 150, 5, index, Interface);
		index++;
		setBounds(22512, 155, 10, index, Interface);
		index++;
		setBounds(22513, 2, 45, index, Interface);
		index++;
		setBounds(22514, 9, 48, index, Interface);
		index++;
		setBounds(22515, 39, 45, index, Interface);
		index++;
		setBounds(22516, 42, 47, index, Interface);
		index++;
		setBounds(22517, 76, 45, index, Interface);
		index++;
		setBounds(22518, 79, 48, index, Interface);
		index++;
		setBounds(22519, 113, 45, index, Interface);
		index++;
		setBounds(22520, 116, 48, index, Interface);
		index++;
		setBounds(22521, 151, 45, index, Interface);
		index++;
		setBounds(22522, 154, 48, index, Interface);
		index++;
		setBounds(22523, 2, 82, index, Interface);
		index++;
		setBounds(22524, 6, 86, index, Interface);
		index++;
		setBounds(22525, 40, 82, index, Interface);
		index++;
		setBounds(22526, 42, 86, index, Interface);
		index++;
		setBounds(22527, 77, 82, index, Interface);
		index++;
		setBounds(22528, 79, 86, index, Interface);
		index++;
		setBounds(22529, 114, 83, index, Interface);
		index++;
		setBounds(22530, 119, 87, index, Interface);
		index++;
		setBounds(22531, 153, 83, index, Interface);
		index++;
		setBounds(22532, 156, 86, index, Interface);
		index++;
		setBounds(22533, 2, 120, index, Interface);
		index++;
		setBounds(22534, 7, 125, index, Interface);
		index++;
		setBounds(22535, 40, 120, index, Interface);
		index++;
		setBounds(22536, 45, 124, index, Interface);
		index++;
		setBounds(22537, 78, 120, index, Interface);
		index++;
		setBounds(22538, 86, 124, index, Interface);
		index++;
		setBounds(22539, 114, 120, index, Interface);
		index++;
		setBounds(22540, 120, 125, index, Interface);
		index++;
		setBounds(22541, 151, 120, index, Interface);
		index++;
		setBounds(22542, 153, 127, index, Interface);
		index++;
		setBounds(22582, 10, 40, index, Interface);
		index++;
		setBounds(22544, 20, 40, index, Interface);
		index++;
		setBounds(22546, 20, 40, index, Interface);
		index++;
		setBounds(22548, 20, 40, index, Interface);
		index++;
		setBounds(22550, 20, 40, index, Interface);
		index++;
		setBounds(22552, 10, 80, index, Interface);
		index++;
		setBounds(22554, 10, 80, index, Interface);
		index++;
		setBounds(22556, 10, 80, index, Interface);
		index++;
		setBounds(22558, 10, 80, index, Interface);
		index++;
		setBounds(22560, 10, 80, index, Interface);
		index++;
		setBounds(22562, 10, 120, index, Interface);
		index++;
		setBounds(22564, 10, 120, index, Interface);
		index++;
		setBounds(22566, 10, 120, index, Interface);
		index++;
		setBounds(22568, 5, 120, index, Interface);
		index++;
		setBounds(22570, 5, 120, index, Interface);
		index++;
		setBounds(22572, 10, 160, index, Interface);
		index++;
		setBounds(22574, 10, 160, index, Interface);
		index++;
		setBounds(22576, 10, 160, index, Interface);
		index++;
		setBounds(22578, 10, 160, index, Interface);
		index++;
		setBounds(22580, 10, 160, index, Interface);
		index++;

	}
	public static void unpack(StreamLoader streamLoader,
			TextDrawingArea textDrawingAreas[], StreamLoader streamLoader_1) {
		fonts = textDrawingAreas;
		aMRUNodes_238 = new MRUNodes(70000);
		Stream stream = new Stream(streamLoader.getDataForName("data"));
		int i = -1;
		stream.readUnsignedWord();
		interfaceCache = new RSInterface[70000];
		do {
			if (stream.currentOffset >= stream.buffer.length)
				break;
			int k = stream.readUnsignedWord();
			if (k == 65535) {
				i = stream.readUnsignedWord();
				k = stream.readUnsignedWord();
			}
			RSInterface rsInterface = interfaceCache[k] = new RSInterface();
			rsInterface.id = k;
			rsInterface.parentID = i;
			rsInterface.type = stream.readUnsignedByte();
			rsInterface.atActionType = stream.readUnsignedByte();
			rsInterface.contentType = stream.readUnsignedWord();
			rsInterface.width = stream.readUnsignedWord();
			rsInterface.height = stream.readUnsignedWord();
			rsInterface.opacity = (byte) stream.readUnsignedByte();
			rsInterface.mOverInterToTrigger = stream.readUnsignedByte();
			if (rsInterface.mOverInterToTrigger != 0)
				rsInterface.mOverInterToTrigger = (rsInterface.mOverInterToTrigger - 1 << 8)
						+ stream.readUnsignedByte();
			else
				rsInterface.mOverInterToTrigger = -1;
			int i1 = stream.readUnsignedByte();
			if (i1 > 0) {
				rsInterface.valueCompareType = new int[i1];
				rsInterface.requiredValues = new int[i1];
				for (int j1 = 0; j1 < i1; j1++) {
					rsInterface.valueCompareType[j1] = stream
							.readUnsignedByte();
					rsInterface.requiredValues[j1] = stream.readUnsignedWord();
				}

			}
			int k1 = stream.readUnsignedByte();
			if (k1 > 0) {
				rsInterface.valueIndexArray = new int[k1][];
				for (int l1 = 0; l1 < k1; l1++) {
					int i3 = stream.readUnsignedWord();
					rsInterface.valueIndexArray[l1] = new int[i3];
					for (int l4 = 0; l4 < i3; l4++)
						rsInterface.valueIndexArray[l1][l4] = stream
								.readUnsignedWord();

				}

			}
			if (rsInterface.type == 0) {
				rsInterface.drawsTransparent = false;
				rsInterface.scrollMax = stream.readUnsignedWord();
				rsInterface.isMouseoverTriggered = stream.readUnsignedByte() == 1;
				int i2 = stream.readUnsignedWord();
				rsInterface.children = new int[i2];
				rsInterface.childX = new int[i2];
				rsInterface.childY = new int[i2];
				for (int j3 = 0; j3 < i2; j3++) {
					rsInterface.children[j3] = stream.readUnsignedWord();
					rsInterface.childX[j3] = stream.readSignedWord();
					rsInterface.childY[j3] = stream.readSignedWord();
				}

			}
			if (rsInterface.type == 1) {
				stream.readUnsignedWord();
				stream.readUnsignedByte();
			}
			if (rsInterface.type == 2) {
				rsInterface.inv = new int[rsInterface.width
						* rsInterface.height];
				rsInterface.invStackSizes = new int[rsInterface.width
						* rsInterface.height];
				rsInterface.aBoolean259 = stream.readUnsignedByte() == 1;
				rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
				rsInterface.usableItemInterface = stream.readUnsignedByte() == 1;
				rsInterface.aBoolean235 = stream.readUnsignedByte() == 1;
				rsInterface.invSpritePadX = stream.readUnsignedByte();
				rsInterface.invSpritePadY = stream.readUnsignedByte();
				rsInterface.spritesX = new int[20];
				rsInterface.spritesY = new int[20];
				rsInterface.sprites = new Sprite[20];
				for (int j2 = 0; j2 < 20; j2++) {
					int k3 = stream.readUnsignedByte();
					if (k3 != 1)
						continue;
					rsInterface.spritesX[j2] = stream.readSignedWord();
					rsInterface.spritesY[j2] = stream.readSignedWord();
					String s1 = stream.readString();
					if (streamLoader_1 != null && s1.length() > 0) {
						int i5 = s1.lastIndexOf(",");
						rsInterface.sprites[j2] = method207(
								Integer.parseInt(s1.substring(i5 + 1)),
								streamLoader_1, s1.substring(0, i5));
					}
				}

				rsInterface.itemActions = new String[5];
				for (int l3 = 0; l3 < 5; l3++) {
					rsInterface.itemActions[l3] = stream.readString();
					if (rsInterface.parentID == 3824)
						rsInterface.itemActions[4] = "Buy 200";	
						
						
					
					
					
					if (rsInterface.itemActions[l3].length() == 0)
						rsInterface.itemActions[l3] = null;
					if (rsInterface.parentID == 1644)
						rsInterface.itemActions[2] = "Operate";
				}
			
			}
			if (rsInterface.type == 3)
				rsInterface.aBoolean227 = stream.readUnsignedByte() == 1;
			if (rsInterface.type == 4 || rsInterface.type == 1) {
				rsInterface.centerText = stream.readUnsignedByte() == 1;
				int k2 = stream.readUnsignedByte();
				if (textDrawingAreas != null)
					rsInterface.textDrawingAreas = textDrawingAreas[k2];
				rsInterface.textShadow = stream.readUnsignedByte() == 1;
			}
			if (rsInterface.type == 4) {
				rsInterface.message = stream.readString();
				rsInterface.disabledText = stream.readString();
			}
			if (rsInterface.type == 1 || rsInterface.type == 3
					|| rsInterface.type == 4)
				rsInterface.textColor = stream.readDWord();
			if (rsInterface.type == 3 || rsInterface.type == 4) {
				rsInterface.anInt219 = stream.readDWord();
				rsInterface.textHoverColour = stream.readDWord();
				rsInterface.anInt239 = stream.readDWord();
			}
			if (rsInterface.type == 5) {
				rsInterface.drawsTransparent = false;
				String s = stream.readString();
				if (streamLoader_1 != null && s.length() > 0) {
					int i4 = s.lastIndexOf(",");
					rsInterface.sprite1 = method207(
							Integer.parseInt(s.substring(i4 + 1)),
							streamLoader_1, s.substring(0, i4));
				}
				s = stream.readString();
				if (streamLoader_1 != null && s.length() > 0) {
					int j4 = s.lastIndexOf(",");
					rsInterface.sprite2 = method207(
							Integer.parseInt(s.substring(j4 + 1)),
							streamLoader_1, s.substring(0, j4));
				}
			}
			if (rsInterface.type == 6) {
				int l = stream.readUnsignedByte();
				if (l != 0) {
					rsInterface.anInt233 = 1;
					rsInterface.mediaID = (l - 1 << 8)
							+ stream.readUnsignedByte();
				}
				l = stream.readUnsignedByte();
				if (l != 0) {
					rsInterface.anInt255 = 1;
					rsInterface.anInt256 = (l - 1 << 8)
							+ stream.readUnsignedByte();
				}
				l = stream.readUnsignedByte();
				if (l != 0)
					rsInterface.anInt257 = (l - 1 << 8)
							+ stream.readUnsignedByte();
				else
					rsInterface.anInt257 = -1;
				l = stream.readUnsignedByte();
				if (l != 0)
					rsInterface.anInt258 = (l - 1 << 8)
							+ stream.readUnsignedByte();
				else
					rsInterface.anInt258 = -1;
				rsInterface.modelZoom = stream.readUnsignedWord();
				rsInterface.modelRotation1 = stream.readUnsignedWord();
				rsInterface.modelRotation2 = stream.readUnsignedWord();
			}
			if (rsInterface.type == 7) {
				rsInterface.inv = new int[rsInterface.width
						* rsInterface.height];
				rsInterface.invStackSizes = new int[rsInterface.width
						* rsInterface.height];
				rsInterface.centerText = stream.readUnsignedByte() == 1;
				int l2 = stream.readUnsignedByte();
				if (textDrawingAreas != null)
					rsInterface.textDrawingAreas = textDrawingAreas[l2];
				rsInterface.textShadow = stream.readUnsignedByte() == 1;
				rsInterface.textColor = stream.readDWord();
				rsInterface.invSpritePadX = stream.readSignedWord();
				rsInterface.invSpritePadY = stream.readSignedWord();
				rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
				rsInterface.itemActions = new String[5];
				for (int k4 = 0; k4 < 5; k4++) {
					rsInterface.itemActions[k4] = stream.readString();
					if (rsInterface.itemActions[k4].length() == 0)
						rsInterface.itemActions[k4] = null;
				}

				
			}
			if (rsInterface.atActionType == 2 || rsInterface.type == 2) {
				rsInterface.selectedActionName = stream.readString();
				rsInterface.spellName = stream.readString();
				rsInterface.spellUsableOn = stream.readUnsignedWord();
			}
			if (rsInterface.type == 8)
				rsInterface.message = stream.readString();
			if (rsInterface.atActionType == 1 || rsInterface.atActionType == 4
					|| rsInterface.atActionType == 5
					|| rsInterface.atActionType == 6) {
				rsInterface.tooltip = stream.readString();
				if (rsInterface.tooltip.length() == 0) {
					if (rsInterface.atActionType == 1)
						rsInterface.tooltip = "Ok";
					if (rsInterface.atActionType == 4)
						rsInterface.tooltip = "Select";
					if (rsInterface.atActionType == 5)
						rsInterface.tooltip = "Select";
					if (rsInterface.atActionType == 6)
						rsInterface.tooltip = "Continue";
				}
			}
		} while (true);
		aMRUNodes_238 = null;
	}
		public static void Classes(TextDrawingArea[] TDA){
		RSInterface tab = addTabInterface(17050);
		addSprite(17051, 1, "Interfaces/Classes/CHOOSE");
		addHover(17052, 1, 0, 19151, 1, "Interfaces/Classes/MAGIC", 134, 180, "Choose Mage As A Class");
		addHover(17053, 1, 0, 19151, 3, "Interfaces/Classes/MELEE", 134, 180, "Choose Melee As A Class");
		addHover(17054, 1, 0, 19151, 2, "Interfaces/Classes/RANGE", 134, 180, "Choose Range As A Class");
		tab.totalChildren(4);
		tab.child(0, 17051, 10, 15);
		tab.child(1, 17052, 30, 78);
		tab.child(2, 17053, 182, 78);
		tab.child(3, 17054, 334, 78);
		}
	public static void unpackCustom(StreamLoader streamLoader,
			TextDrawingArea textDrawingAreas[]) {
		
		aMRUNodes_238 = new MRUNodes(65000);
		aClass44 = streamLoader;
		clanChatTabs(textDrawingAreas);
		BountyHunter.initialize(textDrawingAreas);
		Curses(textDrawingAreas);
		prayerMenu(textDrawingAreas);
		emoteTab();
		monsterTele(textDrawingAreas);
		monsterTeleb(textDrawingAreas);
		SettingsTab(textDrawingAreas);
		SkillTeleportInterface(textDrawingAreas);
		minigameTeleportInterface(textDrawingAreas);
		Bank(textDrawingAreas);
		Login(textDrawingAreas);//ace
		Domtowershop3(textDrawingAreas);
		Domtowershop2(textDrawingAreas);
		Domtowershop(textDrawingAreas);	
		DominionTower1(textDrawingAreas);
		DominionTower2(textDrawingAreas);		
		Domtowerwin1(textDrawingAreas);
		Dominterface(textDrawingAreas);
		DominionTower(textDrawingAreas);
		Buy(textDrawingAreas);
		collectBuy(textDrawingAreas);
		oakLectern(textDrawingAreas);
		demonLectern(textDrawingAreas);
		TaskShop(textDrawingAreas);
		RecolourShop(textDrawingAreas);
		CostumeShop(textDrawingAreas);
		TitleShop(textDrawingAreas);
		Sell(textDrawingAreas);
		Classes(textDrawingAreas);
		optionTab(textDrawingAreas);
		questTab(textDrawingAreas);
		achievementTabb2(textDrawingAreas);
		staffTab(textDrawingAreas);
		achievemnttab(textDrawingAreas);
		Shop(textDrawingAreas);
		SummonTab(textDrawingAreas);
		castleWars();
		castleWars2();
		castleWars3();
		newQuestInterface(textDrawingAreas);
		achievementdropdown();
		GodWars(textDrawingAreas);
		bobInterface(textDrawingAreas);
		newTrade(textDrawingAreas);
		Sidebar0(textDrawingAreas);
		friendsTab(textDrawingAreas);
		ignoreTab(textDrawingAreas);
		Pestpanel(textDrawingAreas);
		Pestpanel2(textDrawingAreas);
		PestControl(textDrawingAreas);
		equipmentScreen(textDrawingAreas);
		SAMUELSUGER(textDrawingAreas);
		EquipmentTab(textDrawingAreas);
		magicTab(textDrawingAreas);
		ancientMagicTab(textDrawingAreas);
		configureLunar(textDrawingAreas);
		constructLunar();
		quickPrayers(textDrawingAreas);
		Construction(textDrawingAreas);
		itemsOnDeath(textDrawingAreas);
		itemsOnDeathDATA(textDrawingAreas);
		quickCurses(textDrawingAreas);
		summoningLevelUp(textDrawingAreas);
		scrollCreation(textDrawingAreas);
		pouchCreation(textDrawingAreas);
		settingsInterface(textDrawingAreas);
		NewSkillLamp(textDrawingAreas);
		teleport(textDrawingAreas);
		boss(textDrawingAreas);
		SkillTeleport(textDrawingAreas);
		runecraftingTable(textDrawingAreas);
		minigame(textDrawingAreas);
		RSInterface miasmic = interfaceCache[13095];
		miasmic.spellName = "Miasmic Barrage";
		miasmic.spellUsableOn = 10;
		miasmic.atActionType = 2;
		miasmic.tooltip = "Cast @gre@Miasmic Barrage";
		miasmic.selectedActionName = "Cast On";
		aMRUNodes_238 = null;
	}
	
		public static void SkillTeleport(TextDrawingArea[] TDA) {
	RSInterface tab = addScreenInterface(17560);
	
	addSprite(17561, 0, "Interfaces/SkillTele/BACK");
	
	addHoverButton(17562, "Interfaces/SkillTele/SPRITEs", 1, 176, 25, "Teleport to Construction Place", -1, 17562, 1);
    addHoverButton(17563, "Interfaces/SkillTele/SPRITEs", 2, 160, 25, "Teleport to Cooking Place", -1, 17563, 1);
    addHoverButton(17564, "Interfaces/SkillTele/SPRITEs", 3, 159, 25, "Teleport to Crafting Place", -1, 17564, 1);
    addHoverButton(17565, "Interfaces/SkillTele/SPRITEs", 4, 158, 25, "Teleport to Dungeoneering Place", -1, 17565, 1);
    addHoverButton(17566, "Interfaces/SkillTele/SPRITEs", 5, 158, 27, "Teleport to Farming Place", -1, 17566, 1);
    addHoverButton(17567, "Interfaces/SkillTele/SPRITEs", 6, 159, 25, "Teleport to Firemaking Place", -1, 17567, 1);
    addHoverButton(17568, "Interfaces/SkillTele/SPRITEs", 7, 171, 29, "Teleport to Fishing Place", -1, 17568, 1);
    addHoverButton(17569, "Interfaces/SkillTele/SPRITEs", 8, 157, 24, "Teleport to Fletching Place", -1, 17569, 1);
    addHoverButton(17570, "Interfaces/SkillTele/SPRITEs", 9, 158, 24, "Teleport to Herblore Place", -1, 17570, 1);
    addHoverButton(17571, "Interfaces/SkillTele/SPRITEs", 10, 171, 25, "Teleport to Hunter Place", -1, 17571, 1);
    addHoverButton(17572, "Interfaces/SkillTele/SPRITEs", 11, 174, 35, "Teleport to Mining Place", -1, 17572, 1);
    addHoverButton(17573, "Interfaces/SkillTele/SPRITEs", 12, 174, 25, "Teleport to Runecrafting Place", -1, 17573, 1);
    addHoverButton(17574, "Interfaces/SkillTele/SPRITEs", 13, 154, 24, "Teleport to Slayer Place", -1, 17574, 1);
    addHoverButton(17575, "Interfaces/SkillTele/SPRITEs", 14, 154, 24, "Teleport to Smithing Place", -1, 17575, 1);
    addHoverButton(17576, "Interfaces/SkillTele/SPRITEs", 15, 152, 25, "Teleport to Summoning Place", -1, 17576, 1);
    addHoverButton(17577, "Interfaces/SkillTele/SPRITEs", 16, 157, 26, "Teleport to Thieving Place", -1, 17577, 1);
    addHoverButton(17578, "Interfaces/SkillTele/SPRITEs", 17, 154, 25, "Teleport to Woodcutting Place", -1, 17578, 1);
    addHoverButton(17579, "Interfaces/SkillTele/SPRITEs", 18, 174, 25, "Teleport to Agility Place", -1, 17579, 1);
	addHoverButton(17598, "Interfaces/SkillTele/CLOSE", 37, 25, 25, "Close", -1, 17598, 1);
	
	addHoveredButton(17580, "Interfaces/SkillTele/SPRITE", 19, 176, 25, 17680);
    addHoveredButton(17581, "Interfaces/SkillTele/SPRITE", 20, 160, 25, 17681);
    addHoveredButton(17582, "Interfaces/SkillTele/SPRITE", 21, 159, 25, 17682);
    addHoveredButton(17583, "Interfaces/SkillTele/SPRITE", 22, 158, 25, 17683);
    addHoveredButton(17584, "Interfaces/SkillTele/SPRITE", 23, 158, 27, 17684);
    addHoveredButton(17585, "Interfaces/SkillTele/SPRITE", 24, 159, 25, 17685);
    addHoveredButton(17586, "Interfaces/SkillTele/SPRITE", 25, 171, 29, 17686);
    addHoveredButton(17587, "Interfaces/SkillTele/SPRITE", 26, 157, 24, 17687);
    addHoveredButton(17588, "Interfaces/SkillTele/SPRITE", 27, 158, 24, 17688);
    addHoveredButton(17589, "Interfaces/SkillTele/SPRITE", 28, 171, 25, 17689);
    addHoveredButton(17590, "Interfaces/SkillTele/SPRITE", 29, 174, 35, 17690);
    addHoveredButton(17591, "Interfaces/SkillTele/SPRITE", 30, 174, 25, 17691);
    addHoveredButton(17592, "Interfaces/SkillTele/SPRITE", 31, 154, 24, 17692);
    addHoveredButton(17593, "Interfaces/SkillTele/SPRITE", 32, 154, 24, 17693);
    addHoveredButton(17594, "Interfaces/SkillTele/SPRITE", 33, 152, 25, 17694);
    addHoveredButton(17595, "Interfaces/SkillTele/SPRITE", 34, 157, 26, 17695);
    addHoveredButton(17596, "Interfaces/SkillTele/SPRITE", 35, 154, 25, 17696);
    addHoveredButton(17597, "Interfaces/SkillTele/SPRITE", 36, 174, 25, 17697);
	tab.totalChildren(38);
	
	tab.child(0, 17561, 0, 0);
	
	tab.child(1, 17562, 339, 229);
	tab.child(2, 17563, 12, 83);
	tab.child(3, 17564, 12, 193);
	tab.child(4, 17565, 179, 264);
	tab.child(5, 17566, 12, 158);
	tab.child(6, 17567, 12, 120);
	tab.child(7, 17568, 339, 264);
	tab.child(8, 17569, 179, 83);
	tab.child(9, 17570, 12, 264);
	tab.child(10, 17571, 339, 193);
	tab.child(11, 17572, 339, 83);
	tab.child(12, 17573, 176, 158);	
	tab.child(13, 17574, 339, 120);	
	tab.child(14, 17575, 179, 193);	
	tab.child(15, 17576, 179, 120);	
	tab.child(16, 17577, 179, 229);	
	tab.child(17, 17578, 12, 229);	
	tab.child(18, 17579, 339, 158);	

	tab.child(19, 17580, 339, 229);	
	tab.child(20, 17581, 12, 83);	
	tab.child(21, 17582, 12, 193);	
	tab.child(22, 17583, 179, 264);	
	tab.child(23, 17584, 12, 158);	
	tab.child(24, 17585, 12, 120);	
	tab.child(25, 17586, 339, 264);	
	tab.child(26, 17587, 179, 83);	
	tab.child(27, 17588, 12, 264);	
	tab.child(28, 17589, 339, 193);	
	tab.child(29, 17590, 339, 83);	
	tab.child(30, 17591, 179, 158);	
	tab.child(31, 17592, 339, 120);	
	tab.child(32, 17593, 179, 193);	
	tab.child(33, 17594, 179, 120);	
	tab.child(34, 17595, 179, 229);	
	tab.child(35, 17596, 12, 229);	
	tab.child(36, 17597, 339, 158);	
	
	tab.child(37, 17598, 494, 16);	  
	}
	
						public static void Login(TextDrawingArea[] TDA) {
	RSInterface tab = addScreenInterface(14300);
	addSprite(14309, 1, "Interfaces/Login/BACKGROUND");
	addHoverButton(14310, "Interfaces/Login/FORUM", 1, 96, 30, "Forum", -1, 14311, 1);
	addHoveredButton(14311, "Interfaces/Login/FORUMH", 0, 96, 30, 14351);
	addHoverButton(14312, "Interfaces/Login/WEBSITE", 3, 96, 30, "Website", -1, 14313, 1);
	addHoveredButton(14313, "Interfaces/Login/WEBSITEH", 0, 96, 30, 14352);
	addHover(14314, 3, 0, 14315, 2, "Interfaces/Login/PLAYNOW", 78, 18, "Exit");
	addHoveredButton(14315, "Interfaces/Login/PLAYNOWH", 0, 78, 18, 14353);
	addText(14316, "Hello", 0xff9b00, false, true, 38, TDA, 1); // hello name
	addText(14317, "Your rank is", 0xff9b00, false, true, 38, TDA, 1); //your rank
	addText(14318, "", 0xff9b00, false, true, 38, TDA, 1); //rank
	addText(14319, "", 0xff9b00, false, true, 38, TDA, 1);	//tip of the week
	addText(14320, "Players On:", 0xff9b00, false, true, 38, TDA, 1);	//players online
	addText(14321, "", 0xff9b00, false, true, 38, TDA, 1);	//tip of the week
	addText(14322, "", 0xff9b00, false, true, 38, TDA, 1);	//tip of the week
	tab.totalChildren(14);
	tab.child(0, 14309, 5, 10);
	tab.child(1, 14310, 20, 130);
	tab.child(2, 14311, 20, 130);
	tab.child(3, 14312, 20, 190);
	tab.child(4, 14313, 20, 190);
	tab.child(5, 14314, 31, 264);
	tab.child(6, 14315, 31, 264);
	tab.child(7, 14316, 258, 62);
	tab.child(8, 14317, 260, 139);
	tab.child(9, 14318, 274, 163);
	tab.child(10, 14319, 210, 202);
	tab.child(11, 14320, 39, 85);
	tab.child(12, 14321, 210, 220);
	tab.child(13, 14322, 151, 250);
	}
	

	
		public static int x, y = 0;
	public static void achievementdropdown() {
		RSInterface tab = addTabInterface(23139);
		RSInterface scroll = addTabInterface(23140);
		String directory = "Interfaces/Achieve/achievement";
		addText(23130,
				"Here you can view your\\nachievements. You see\\nthe ones that are\\nto be completed and\\nalso the ones you've\\ncompleted. The ones\\nthat are in red are\\nthose you haven't\\ncompleted yet. You earn\\nachievement points\\nby completing different\\nachievements and also\\ndifferent items and\\nexperience.",
				0xAF6A1B, false, true, 0, 0);
		addText(23131, "You currently have\\n147 achievement points.",
				0xAF6A1B, false, true, 0, 0);
		addText(23110, "Achievements",
				0xAF6A1B, false, true, 0, 2);
		addButton(23132, 1, directory, "", 0, 3, 16, 15);
		tab.totalChildren(6);
		tab.child(0, 23138, 1, 1);
		tab.child(1, 23140, 160, 50);
		tab.child(2, 23130, 20, 80);
		tab.child(3, 23131, 20, 230);
		tab.child(4, 23132, 492, 4);
		tab.child(5, 23110, 28, 45);
		addButton(23138, 0, directory, "", 0, 0, 500, 500);
		final int ACHIEVEMENTS = 100;
		scroll.width = 300;
		scroll.height = 200;
		scroll.scrollMax = 2500;
		scroll.totalChildren(ACHIEVEMENTS);
		for (int i = 23141; i < 23141 + ACHIEVEMENTS; i++) {
			addText(i, "" + i + "", 0xAF6A1A, false, true, 52, 1);
			scroll.child(i - 23141, i, x, y);
			x += 150;
			if (x >= 300) {
				y += 50;
				x = 0;
			}
		}
		RSInterface tab1 = addTabInterface(23133);
		addButton(23134, 4, directory, "", 0, 0, 500, 500);
		addText(23135, "T A S K C O M P L E T E", 0xAF6A1A, false, true, 52, 2);
		addText(23136, "You completed the task:\\nTASKNAME!", 0xAF6A1B,
				true, true, 0, 1);
		tab1.totalChildren(3);
		tab1.child(0, 23134, 158, 0);
		tab1.child(1, 23135, 193, 5);
		tab1.child(2, 23136, 254, 22);
	}
	
		public static void SAMUELSUGER(TextDrawingArea[] TDA) {
		String directory = "Interfaces/Achieve/achievement";
		RSInterface tab = addTabInterface(20000);
		addButton(20001, 4, directory, "Close", 0, 0, 500, 500);
		addText(20002, "T A S K C O M P L E T E", 0xAF6A1A, false, true, 52, 2);
		addText(20003, "You completed the task:", 0xAF6A1B, true, true, 0, 1);
		addText(20004, "TASKNAME", 0xAF6A1B, true, true, 0, 1);
		tab.totalChildren(4);
		tab.child(0, 20001, 158, 0);
		tab.child(1, 20002, 193, 5);
		tab.child(2, 20003, 254, 22);
		tab.child(3, 20004, 254, 37);
		}
		
		
		
		public static void achievement(TextDrawingArea[] TDA) {
        RSInterface tab = addTabInterface(19500);
		RSInterface scroll = addTabInterface(19501);
        addText(19502, "Achievement List", TDA, 2, 0xFF9900, false, true);
		addSprite(19503, 0, "Interfaces/Achieve/ACH");
		addSprite(19504, 3, "Interfaces/Achieve/ACH");
		addSprite(19505, 0, "Interfaces/Achieve/ACH");
		addButton(19590, 1, "QuestTab/QUEST", 18, 18, "Swap to Information", 1);
        tab.totalChildren(6);
        tab.child(0, 19502, 5, 5);
		tab.child(1, 19503, 0, 25);
		tab.child(2, 19504, 0, 28);
		tab.child(3, 19505, 0, 249);
        tab.child(4, 19501, 0, 25);
		tab.child(5, 19590, 165, 3);
		scroll.width = 174; scroll.height = 224; scroll.scrollMax = 1250;
		
		addHoverText(19549, "", "", TDA, 1, 0xFF9900, false, true, 150);
		addText(19506, "Player Killing", TDA, 2, 0xFF9900, false, true);
		addHoverText(19507, "Kill 50 players", "", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19508, "Kill 100 players", "", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19509, "Kill 250 players", "", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19510, "Kill 400 players", "", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19511, "Kill 750 Players", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19512, "Kill 1000 players", "", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19513, "Kill 1250 players", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19514, "Kill 1500 players", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19515, "Kill 2500 players", "View", TDA, 0, 0xff0000, false, true, 150);
		addText(19516, "Skilling", TDA, 2, 0xFF9900, false, true);
		addHoverText(19517, "99 Mining", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19518, "99 Fishing", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19519, "99 Herblore", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19520, "99 Thieving", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19521, "99 Crafting", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19522, "99 Hunter", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19523, "99 Summoning", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19524, "99 Slayer", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19525, "Maxed Account", "View", TDA, 0, 0xff0000, false, true, 150);
		//addText(19526, "Creations", "View", TDA, 2, 0xff0000, false, true, 150);
		addText(19526, "Creations", TDA, 2, 0xFF9900, false, true);
		addHoverText(19527, "Create: Dragonfire shield", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19528, "Create: Amulet of fury", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19529, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19530, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19531, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19532, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19533, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19534, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addText(19535, "", TDA, 2, 0xFF9900, false, true);
		addHoverText(19536, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19537, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19538, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19539, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19540, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19541, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19542, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19543, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19544, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19545, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19546, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19547, "", "View", TDA, 0, 0xff0000, false, true, 150);
		addHoverText(19548, "", "View", TDA, 0, 0xff0000, false, true, 150);
		scroll.totalChildren(44);
		scroll.child(0, 19507, 5, 47);
		scroll.child(1, 19508, 5, 61);
		scroll.child(2, 19509, 5, 75);
		scroll.child(3, 19510, 5, 89);
		scroll.child(4, 19511, 5, 103);
		scroll.child(5, 19506, 5, 20);
		scroll.child(6, 19512, 5, 117);
		scroll.child(7, 19513, 5, 131);
		scroll.child(8, 19514, 5, 145);
		scroll.child(9, 19515, 5, 159);
		scroll.child(10, 19516, 5, 187);
		scroll.child(11, 19517, 5, 215);
		scroll.child(12, 19518, 5, 229);
		scroll.child(13, 19519, 5, 243);
		scroll.child(14, 19520, 5, 257);
		scroll.child(15, 19521, 5, 271);
		scroll.child(16, 19522, 5, 285);
		scroll.child(17, 19523, 5, 299);
		scroll.child(18, 19524, 5, 313);
		scroll.child(19, 19525, 5, 327);
		scroll.child(20, 19526, 5, 350);
		scroll.child(21, 19527, 5, 380);
		scroll.child(22, 19528, 5, 393);
		scroll.child(23, 19529, 5, 411);
		scroll.child(24, 19530, 5, 424);
		scroll.child(25, 19531, 5, 439);
		scroll.child(26, 19532, 5, 453);
		scroll.child(27, 19533, 5, 467);
		scroll.child(28, 19534, 5, 481);
		scroll.child(29, 19535, 5, 509);
		scroll.child(30, 19536, 5, 537);
		scroll.child(31, 19537, 5, 551);
		scroll.child(32, 19538, 5, 565);
		scroll.child(33, 19539, 5, 579);
		scroll.child(34, 19540, 5, 593);
		scroll.child(35, 19541, 5, 607);
		scroll.child(36, 19542, 5, 621);
		scroll.child(37, 19543, 5, 635);
		scroll.child(38, 19544, 5, 649);
		scroll.child(39, 19545, 5, 663);
		scroll.child(40, 19546, 5, 677);
		scroll.child(41, 19547, 5, 691);
		scroll.child(42, 19548, 5, 705);
		scroll.child(43, 19549, 5, 6);
    }
	
	public static void quickCurses(TextDrawingArea TDA[]) {
		int frame = 0;
		RSInterface tab = addTabInterface(17234);
		addText(17235, "Select your quick curses:", TDA, 0, 0xff981f, false,
				true);
		int i = 17202;
		for (int j = 630; i <= 17222 || j <= 656; j++) {
			addConfigButton(i, 17200, 2, 1, "/Interfaces/QuickPrayer/Sprite",
					14, 15, "Select", 0, 1, j);
			i++;
		}

		addHoverButton(17231, "/Interfaces/QuickPrayer/Sprite", 4, 190, 24,
				"Confirm Selection", -1, 17232, 1);
		addHoveredButton(17232, "/Interfaces/QuickPrayer/Sprite", 5, 190, 24,
				17233);
		setChildren(46, tab);
		setBounds(22504, 5, 28, frame++, tab);
		setBounds(22506, 44, 28, frame++, tab);
		setBounds(22508, 79, 31, frame++, tab);
		setBounds(22510, 116, 30, frame++, tab);
		setBounds(22512, 153, 29, frame++, tab);
		setBounds(22514, 5, 68, frame++, tab);
		setBounds(22516, 44, 67, frame++, tab);
		setBounds(22518, 79, 69, frame++, tab);
		setBounds(22520, 116, 70, frame++, tab);
		setBounds(22522, 154, 70, frame++, tab);
		setBounds(22524, 4, 104, frame++, tab);
		setBounds(22526, 44, 107, frame++, tab);
		setBounds(22528, 81, 105, frame++, tab);
		setBounds(22530, 117, 105, frame++, tab);
		setBounds(22532, 156, 107, frame++, tab);
		setBounds(22534, 5, 145, frame++, tab);
		setBounds(22536, 43, 144, frame++, tab);
		setBounds(22538, 83, 144, frame++, tab);
		setBounds(22540, 115, 141, frame++, tab);
		setBounds(22542, 154, 144, frame++, tab);
		setBounds(17229, 0, 25, frame++, tab);
		setBounds(17201, 0, 22, frame++, tab);
		setBounds(17201, 0, 237, frame++, tab);
		setBounds(17202, 2, 25, frame++, tab);
		setBounds(17203, 41, 25, frame++, tab);
		setBounds(17204, 76, 25, frame++, tab);
		setBounds(17205, 113, 25, frame++, tab);
		setBounds(17206, 150, 25, frame++, tab);
		setBounds(17207, 2, 65, frame++, tab);
		setBounds(17208, 41, 65, frame++, tab);
		setBounds(17209, 76, 65, frame++, tab);
		setBounds(17210, 113, 65, frame++, tab);
		setBounds(17211, 150, 65, frame++, tab);
		setBounds(17212, 2, 102, frame++, tab);
		setBounds(17213, 41, 102, frame++, tab);
		setBounds(17214, 76, 102, frame++, tab);
		setBounds(17215, 113, 102, frame++, tab);
		setBounds(17216, 150, 102, frame++, tab);
		setBounds(17217, 2, 141, frame++, tab);
		setBounds(17218, 41, 141, frame++, tab);
		setBounds(17219, 76, 141, frame++, tab);
		setBounds(17220, 113, 141, frame++, tab);
		setBounds(17221, 150, 141, frame++, tab);
		setBounds(17235, 5, 5, frame++, tab);
		setBounds(17231, 0, 237, frame++, tab);
		setBounds(17232, 0, 237, frame++, tab);
	}

	public static void quickPrayers(TextDrawingArea TDA[]) {
		int frame = 0;
		RSInterface tab = addTabInterface(17200);
		addSprite(17201, 3, "/Interfaces/QuickPrayer/Sprite");
		addText(17230, "Select your quick prayers:", TDA, 0, 0xff981f, false,
				true);
		addTransparentSprite(17229, 0, "/Interfaces/QuickPrayer/Sprite", 50);
		int i = 17202;
		for (int j = 630; i <= 17228 || j <= 656; j++) {
			addConfigButton(i, 17200, 2, 1, "/Interfaces/QuickPrayer/Sprite",
					14, 15, "Select", 0, 1, j);
			i++;
		}

		addHoverButton(17231, "/Interfaces/QuickPrayer/Sprite", 4, 190, 24,
				"Confirm Selection", -1, 17232, 1);
		addHoveredButton(17232, "/Interfaces/QuickPrayer/Sprite", 5, 190, 24,
				17233);
		setChildren(58, tab);
		setBounds(25001, 5, 28, frame++, tab);
		setBounds(25003, 44, 28, frame++, tab);
		setBounds(25005, 79, 31, frame++, tab);
		setBounds(25007, 116, 30, frame++, tab);
		setBounds(25009, 153, 29, frame++, tab);
		setBounds(25011, 5, 68, frame++, tab);
		setBounds(25013, 44, 67, frame++, tab);
		setBounds(25015, 79, 69, frame++, tab);
		setBounds(25017, 116, 70, frame++, tab);
		setBounds(25019, 154, 70, frame++, tab);
		setBounds(25021, 4, 104, frame++, tab);
		setBounds(25023, 44, 107, frame++, tab);
		setBounds(25025, 81, 105, frame++, tab);
		setBounds(25027, 117, 105, frame++, tab);
		setBounds(25029, 156, 107, frame++, tab);
		setBounds(25031, 5, 145, frame++, tab);
		setBounds(25033, 43, 144, frame++, tab);
		setBounds(25035, 83, 144, frame++, tab);
		setBounds(25037, 115, 141, frame++, tab);
		setBounds(25039, 154, 144, frame++, tab);
		setBounds(25041, 5, 180, frame++, tab);
		setBounds(25043, 41, 178, frame++, tab);
		setBounds(25045, 79, 183, frame++, tab);
		setBounds(25047, 116, 178, frame++, tab);
		setBounds(25049, 161, 180, frame++, tab);
		setBounds(25051, 4, 219, frame++, tab);
		setBounds(17229, 0, 25, frame++, tab);
		setBounds(17201, 0, 22, frame++, tab);
		setBounds(17201, 0, 237, frame++, tab);
		setBounds(17202, 2, 25, frame++, tab);
		setBounds(17203, 41, 25, frame++, tab);
		setBounds(17204, 76, 25, frame++, tab);
		setBounds(17205, 113, 25, frame++, tab);
		setBounds(17206, 150, 25, frame++, tab);
		setBounds(17207, 2, 65, frame++, tab);
		setBounds(17208, 41, 65, frame++, tab);
		setBounds(17209, 76, 65, frame++, tab);
		setBounds(17210, 113, 65, frame++, tab);
		setBounds(17211, 150, 65, frame++, tab);
		setBounds(17212, 2, 102, frame++, tab);
		setBounds(17213, 41, 102, frame++, tab);
		setBounds(17214, 76, 102, frame++, tab);
		setBounds(17215, 113, 102, frame++, tab);
		setBounds(17216, 150, 102, frame++, tab);
		setBounds(17217, 2, 141, frame++, tab);
		setBounds(17218, 41, 141, frame++, tab);
		setBounds(17219, 76, 141, frame++, tab);
		setBounds(17220, 113, 141, frame++, tab);
		setBounds(17221, 150, 141, frame++, tab);
		setBounds(17222, 2, 177, frame++, tab);
		setBounds(17223, 41, 177, frame++, tab);
		setBounds(17224, 76, 177, frame++, tab);
		setBounds(17225, 113, 177, frame++, tab);
		setBounds(17226, 150, 177, frame++, tab);
		setBounds(17227, 1, 211, frame++, tab);
		setBounds(17230, 5, 5, frame++, tab);
		setBounds(17231, 0, 237, frame++, tab);
		setBounds(17232, 0, 237, frame++, tab);
	}

	public static void prayerMenu(TextDrawingArea TDA[]) {
		RSInterface prayerMenu = addInterface(5608);
		int index = 0;
		int prayIndex = 0;
		int firstRowXPos = 10;
		int firstRowYPos = 50;
		int secondRowXPos = 10;
		int secondRowYPos = 86;
		int thirdRowXPos = 10;
		int thirdRowYPos = 122;
		int fourthRowXPos = 10;
		int fourthRowYPos = 159;
		int fifthRowXPos = 10;
		int fifthRowYPos = 86;
		int sixthRowXPos = 1;
		int sixthRowYPos = 52;
		addText(687, "", 0xff981f, false, true, -1, TDA, 1);
		addSprite(25105, 0, "Interfaces/PrayerTab/PRAYERICON");
		addPrayerWithTooltip(25000, 0, 83, 0, prayIndex, 25052,
				"Activate @lre@Thick Skin");
		prayIndex++;
		addPrayerWithTooltip(25002, 0, 84, 3, prayIndex, 25054,
				"Activate @lre@Burst of Strength");
		prayIndex++;
		addPrayerWithTooltip(25004, 0, 85, 6, prayIndex, 25056,
				"Activate @lre@Clarity of Thought");
		prayIndex++;
		addPrayerWithTooltip(25006, 0, 601, 7, prayIndex, 25058,
				"Activate @lre@Sharp Eye");
		prayIndex++;
		addPrayerWithTooltip(25008, 0, 602, 8, prayIndex, 25060,
				"Activate @lre@Mystic Will");
		prayIndex++;
		addPrayerWithTooltip(25010, 0, 86, 9, prayIndex, 25062,
				"Activate @lre@Rock Skin");
		prayIndex++;
		addPrayerWithTooltip(25012, 0, 87, 12, prayIndex, 25064,
				"Activate @lre@Superhuman Strength");
		prayIndex++;
		addPrayerWithTooltip(25014, 0, 88, 15, prayIndex, 25066,
				"Activate @lre@Improved Reflexes");
		prayIndex++;
		addPrayerWithTooltip(25016, 0, 89, 18, prayIndex, 25068,
				"Activate @lre@Rapid Restore");
		prayIndex++;
		addPrayerWithTooltip(25018, 0, 90, 21, prayIndex, 25070,
				"Activate @lre@Rapid Heal");
		prayIndex++;
		addPrayerWithTooltip(25020, 0, 91, 24, prayIndex, 25072,
				"Activate @lre@Protect Item");
		prayIndex++;
		addPrayerWithTooltip(25022, 0, 603, 25, prayIndex, 25074,
				"Activate @lre@Hawk Eye");
		prayIndex++;
		addPrayerWithTooltip(25024, 0, 604, 26, prayIndex, 25076,
				"Activate @lre@Mystic Lore");
		prayIndex++;
		addPrayerWithTooltip(25026, 0, 92, 27, prayIndex, 25078,
				"Activate @lre@Steel Skin");
		prayIndex++;
		addPrayerWithTooltip(25028, 0, 93, 30, prayIndex, 25080,
				"Activate @lre@Ultimate Strength");
		prayIndex++;
		addPrayerWithTooltip(25030, 0, 94, 33, prayIndex, 25082,
				"Activate @lre@Incredible Reflexes");
		prayIndex++;
		addPrayerWithTooltip(25032, 0, 95, 36, prayIndex, 25084,
				"Activate @lre@Protect from Magic");
		prayIndex++;
		addPrayerWithTooltip(25034, 0, 96, 39, prayIndex, 25086,
				"Activate @lre@Protect from Missles");
		prayIndex++;
		addPrayerWithTooltip(25036, 0, 97, 42, prayIndex, 25088,
				"Activate @lre@Protect from Melee");
		prayIndex++;
		addPrayerWithTooltip(25038, 0, 605, 43, prayIndex, 25090,
				"Activate @lre@Eagle Eye");
		prayIndex++;
		addPrayerWithTooltip(25040, 0, 606, 44, prayIndex, 25092,
				"Activate @lre@Mystic Might");
		prayIndex++;
		addPrayerWithTooltip(25042, 0, 98, 45, prayIndex, 25094,
				"Activate @lre@Retribution");
		prayIndex++;
		addPrayerWithTooltip(25044, 0, 99, 48, prayIndex, 25096,
				"Activate @lre@Redemption");
		prayIndex++;
		addPrayerWithTooltip(25046, 0, 100, 51, prayIndex, 25098,
				"Activate @lre@Smite");
		prayIndex++;
		addPrayerWithTooltip(25048, 0, 607, 59, prayIndex, 25100,
				"Activate @lre@Chivalry");
		prayIndex++;
		addPrayerWithTooltip(25050, 0, 608, 69, prayIndex, 25102,
				"Activate @lre@Piety");
		prayIndex++;
		addTooltip(25052, "Level 01\nThick Skin\nIncreases your Defence by 5%");
		addTooltip(25054,
				"Level 04\nBurst of Strength\nIncreases your Strength by 5%");
		addTooltip(25056,
				"Level 07\nClarity of Thought\nIncreases your Attack by 5%");
		addTooltip(25058, "Level 08\nSharp Eye\nIncreases your Ranged by 5%");
		addTooltip(25060, "Level 09\nMystic Will\nIncreases your Magic by 5%");
		addTooltip(25062, "Level 10\nRock Skin\nIncreases your Defence by 10%");
		addTooltip(25064,
				"Level 13\nSuperhuman Strength\nIncreases your Strength by 10%");
		addTooltip(25066,
				"Level 16\nImproved Reflexes\nIncreases your Attack by 10%");
		addTooltip(
				25068,
				"Level 19\nRapid Restore\n2x restore rate for all stats\nexcept Hitpoints, Summoning\nand Prayer");
		addTooltip(25070,
				"Level 22\nRapid Heal\n2x restore rate for the\nHitpoints stat");
		addTooltip(25072,
				"Level 25\nProtect Item\nKeep 1 extra item if you die");
		addTooltip(25074, "Level 26\nHawk Eye\nIncreases your Ranged by 10%");
		addTooltip(25076, "Level 27\nMystic Lore\nIncreases your Magic by 10%");
		addTooltip(25078, "Level 28\nSteel Skin\nIncreases your Defence by 15%");
		addTooltip(25080,
				"Level 31\nUltimate Strength\nIncreases your Strength by 15%");
		addTooltip(25082,
				"Level 34\nIncredible Reflexes\nIncreases your Attack by 15%");
		addTooltip(25084,
				"Level 37\nProtect from Magic\nProtection from magical attacks");
		addTooltip(25086,
				"Level 40\nProtect from Missles\nProtection from ranged attacks");
		addTooltip(25088,
				"Level 43\nProtect from Melee\nProtection from melee attacks");
		addTooltip(25090, "Level 44\nEagle Eye\nIncreases your Ranged by 15%");
		addTooltip(25092, "Level 45\nMystic Might\nIncreases your Magic by 15%");
		addTooltip(25094,
				"Level 46\nRetribution\nInflicts damage to nearby\ntargets if you die");
		addTooltip(25096,
				"Level 49\nRedemption\nHeals you when damaged\nand Hitpoints falls\nbelow 10%");
		addTooltip(25098,
				"Level 52\nSmite\n1/4 of damage dealt is\nalso removed from\nopponent's Prayer");
		addTooltip(
				25100,
				"Level 60\nChivalry\nIncreases your Defence by 20%,\nStrength by 18%, and Attack by\n15%");
		addTooltip(
				25102,
				"Level 70\nPiety\nIncreases your Defence by 25%,\nStrength by 23%, and Attack by\n20%");
		setChildren(80, prayerMenu);
		setBounds(687, 85, 241, index, prayerMenu);
		index++;
		setBounds(25105, 65, 241, index, prayerMenu);
		index++;
		setBounds(25000, 2, 5, index, prayerMenu);
		index++;
		setBounds(25001, 5, 8, index, prayerMenu);
		index++;
		setBounds(25002, 40, 5, index, prayerMenu);
		index++;
		setBounds(25003, 44, 8, index, prayerMenu);
		index++;
		setBounds(25004, 76, 5, index, prayerMenu);
		index++;
		setBounds(25005, 79, 11, index, prayerMenu);
		index++;
		setBounds(25006, 113, 5, index, prayerMenu);
		index++;
		setBounds(25007, 116, 10, index, prayerMenu);
		index++;
		setBounds(25008, 150, 5, index, prayerMenu);
		index++;
		setBounds(25009, 153, 9, index, prayerMenu);
		index++;
		setBounds(25010, 2, 45, index, prayerMenu);
		index++;
		setBounds(25011, 5, 48, index, prayerMenu);
		index++;
		setBounds(25012, 39, 45, index, prayerMenu);
		index++;
		setBounds(25013, 44, 47, index, prayerMenu);
		index++;
		setBounds(25014, 76, 45, index, prayerMenu);
		index++;
		setBounds(25015, 79, 49, index, prayerMenu);
		index++;
		setBounds(25016, 113, 45, index, prayerMenu);
		index++;
		setBounds(25017, 116, 50, index, prayerMenu);
		index++;
		setBounds(25018, 151, 45, index, prayerMenu);
		index++;
		setBounds(25019, 154, 50, index, prayerMenu);
		index++;
		setBounds(25020, 2, 82, index, prayerMenu);
		index++;
		setBounds(25021, 4, 84, index, prayerMenu);
		index++;
		setBounds(25022, 40, 82, index, prayerMenu);
		index++;
		setBounds(25023, 44, 87, index, prayerMenu);
		index++;
		setBounds(25024, 77, 82, index, prayerMenu);
		index++;
		setBounds(25025, 81, 85, index, prayerMenu);
		index++;
		setBounds(25026, 114, 83, index, prayerMenu);
		index++;
		setBounds(25027, 117, 85, index, prayerMenu);
		index++;
		setBounds(25028, 153, 83, index, prayerMenu);
		index++;
		setBounds(25029, 156, 87, index, prayerMenu);
		index++;
		setBounds(25030, 2, 120, index, prayerMenu);
		index++;
		setBounds(25031, 5, 125, index, prayerMenu);
		index++;
		setBounds(25032, 40, 120, index, prayerMenu);
		index++;
		setBounds(25033, 43, 124, index, prayerMenu);
		index++;
		setBounds(25034, 78, 120, index, prayerMenu);
		index++;
		setBounds(25035, 83, 124, index, prayerMenu);
		index++;
		setBounds(25036, 114, 120, index, prayerMenu);
		index++;
		setBounds(25037, 115, 121, index, prayerMenu);
		index++;
		setBounds(25038, 151, 120, index, prayerMenu);
		index++;
		setBounds(25039, 154, 124, index, prayerMenu);
		index++;
		setBounds(25040, 2, 158, index, prayerMenu);
		index++;
		setBounds(25041, 5, 160, index, prayerMenu);
		index++;
		setBounds(25042, 39, 158, index, prayerMenu);
		index++;
		setBounds(25043, 41, 158, index, prayerMenu);
		index++;
		setBounds(25044, 76, 158, index, prayerMenu);
		index++;
		setBounds(25045, 79, 163, index, prayerMenu);
		index++;
		setBounds(25046, 114, 158, index, prayerMenu);
		index++;
		setBounds(25047, 116, 158, index, prayerMenu);
		index++;
		setBounds(25048, 153, 158, index, prayerMenu);
		index++;
		setBounds(25049, 161, 160, index, prayerMenu);
		index++;
		setBounds(25050, 2, 196, index, prayerMenu);
		index++;
		setBounds(25051, 4, 207, index, prayerMenu);
		setBoundry(++index, 25052, firstRowXPos - 2, firstRowYPos, prayerMenu);
		setBoundry(++index, 25054, firstRowXPos - 5, firstRowYPos, prayerMenu);
		setBoundry(++index, 25056, firstRowXPos, firstRowYPos, prayerMenu);
		setBoundry(++index, 25058, firstRowXPos, firstRowYPos, prayerMenu);
		setBoundry(++index, 25060, firstRowXPos, firstRowYPos, prayerMenu);
		setBoundry(++index, 25062, secondRowXPos - 9, secondRowYPos, prayerMenu);
		setBoundry(++index, 25064, secondRowXPos - 11, secondRowYPos,
				prayerMenu);
		setBoundry(++index, 25066, secondRowXPos, secondRowYPos, prayerMenu);
		setBoundry(++index, 25068, secondRowXPos, secondRowYPos, prayerMenu);
		setBoundry(++index, 25070, secondRowXPos + 25, secondRowYPos,
				prayerMenu);
		setBoundry(++index, 25072, thirdRowXPos, thirdRowYPos, prayerMenu);
		setBoundry(++index, 25074, thirdRowXPos - 2, thirdRowYPos, prayerMenu);
		setBoundry(++index, 25076, thirdRowXPos, thirdRowYPos, prayerMenu);
		setBoundry(++index, 25078, thirdRowXPos - 7, thirdRowYPos, prayerMenu);
		setBoundry(++index, 25080, thirdRowXPos - 10, thirdRowYPos, prayerMenu);
		setBoundry(++index, 25082, fourthRowXPos, fourthRowYPos, prayerMenu);
		setBoundry(++index, 25084, fourthRowXPos - 8, fourthRowYPos, prayerMenu);
		setBoundry(++index, 25086, fourthRowXPos - 7, fourthRowYPos, prayerMenu);
		setBoundry(++index, 25088, fourthRowXPos - 2, fourthRowYPos, prayerMenu);
		setBoundry(++index, 25090, fourthRowXPos - 2, fourthRowYPos, prayerMenu);
		setBoundry(++index, 25092, fifthRowXPos, fifthRowYPos, prayerMenu);
		setBoundry(++index, 25094, fifthRowXPos, fifthRowYPos - 20, prayerMenu);
		setBoundry(++index, 25096, fifthRowXPos, fifthRowYPos - 25, prayerMenu);
		setBoundry(++index, 25098, fifthRowXPos + 15, fifthRowYPos - 25,
				prayerMenu);
		setBoundry(++index, 25100, fifthRowXPos - 12, fifthRowYPos - 20,
				prayerMenu);
		setBoundry(++index, 25102, sixthRowXPos - 2, sixthRowYPos, prayerMenu);
		index++;
	}
	


		public static void castleWars() {
	      RSInterface tab = addTabInterface(27955);
	      int i = 0;
	      String s = "CastleWars/";
	      addSprite(29525, s+"Main");
	      addHoverButton(29526, s+"basichelmnormal", 0, 37, 75, "Buy Basic Decorative helm", 615, 29527, 1);
	      addHoveredButton(29527, s+"basichelmhov", 1, 37, 75, 29528);
	      addHoverButton(29529, s+"basicplatenormal", 0, 37, 75, "Buy Basic Decorative platebody", 615, 29530, 1);
	      addHoveredButton(29530, s+"basicplatehov", 1, 37, 75, 29531);
	      addHoverButton(29532, s+"basiclegsnormal", 0, 37, 75, "Buy Basic Decorative platelegs", 615, 29533, 1);
	      addHoveredButton(29533, s+"basiclegshov", 1, 37, 75, 29534);
	      addHoverButton(29535, s+"basicshieldnormal", 0, 37, 75, "Buy Basic Decorative shield", 615, 29536, 1);
	      addHoveredButton(29536, s+"basicshieldhov", 1, 37, 75, 29537);
	      addHoverButton(29538, s+"basicswordnormal", 0, 37, 75, "Buy Basic Decorative sword", 615, 29539, 1);
	      addHoveredButton(29539, s+"basicswordhov", 1, 37, 75, 29540);
	      addHoverButton(29541, s+"medhelmnormal", 0, 37, 75, "Buy Detailed Decorative helm", 615, 29542, 1);
	      addHoveredButton(29542, s+"medhelmhov", 1, 37, 75, 29543);
	      addHoverButton(29544, s+"medplatenormal", 0, 37, 75, "Buy Detailed Decorative platebody", 615, 29545, 1);
	      addHoveredButton(29545, s+"medplatehov", 1, 37, 75, 29546);
	      addHoverButton(29547, s+"medlegsnormal", 0, 37, 75, "Buy Detailed Decorative platelegs", 615, 29548, 1);
	      addHoveredButton(29548, s+"medlegshov", 1, 37, 75, 29549);
	      addHoverButton(29550, s+"medshieldnormal", 0, 37, 75, "Buy Detailed Decorative shield", 615, 29551, 1);
	      addHoveredButton(29551, s+"medshieldhov", 1, 37, 75, 29552);
	      addHoverButton(29553, s+"medswordnormal", 0, 37, 75, "Buy Detailed Decorative sword", 615, 29554, 1);
	      addHoveredButton(29554, s+"medswordhov", 1, 37, 75, 29555);
	      addHoverButton(29556, s+"hardhelmnormal", 0, 37, 75, "Buy Intricate Decorative helm", 615, 29557, 1);
	      addHoveredButton(29557, s+"hardhelmhov", 1, 37, 75, 29558);
	      addHoverButton(29559, s+"hardplatenormal", 0, 37, 75, "Buy Intricate Decorative platebody", 615, 29560, 1);
	      addHoveredButton(29560, s+"hardplatehov", 1, 37, 75, 29561);
	      addHoverButton(29562, s+"hardlegsnormal", 0, 37, 75, "Buy Intricate Decorative platelegs", 615, 29563, 1);
	      addHoveredButton(29563, s+"hardlegshov", 1, 37, 75, 29564);
	      addHoverButton(29565, s+"hardshieldnormal", 0, 37, 75, "Buy Intricate Decorative shield", 615, 29566, 1);
	      addHoveredButton(29566, s+"hardshieldhov", 1, 37, 75, 29567);
	      addHoverButton(29568, s+"hardswordnormal", 0, 37, 75, "Buy Intricate Decorative sword", 615, 29569, 1);
	      addHoveredButton(29569, s+"hardswordhov", 1, 37, 75, 29570);
	      addHoverButton(29571, s+"extremehelmnormal", 0, 37, 75, "Buy Profround Decorative helm", 615, 29572, 1);
	      addHoveredButton(29572, s+"extremehelmhov", 1, 37, 75, 29573);
	      addHoverButton(29574, s+"extremeplatenormal", 0, 37, 75, "Buy Profround Decorative platebody", 615, 29575, 1);
	      addHoveredButton(29575, s+"extremeplatehov", 1, 37, 75, 29576);
	      addHoverButton(29577, s+"extremelegsnormal", 0, 37, 75, "Buy Profround Decorative platelegs", 615, 29578, 1);
	      addHoveredButton(29578, s+"extremelegshov", 1, 37, 75, 29579);
	      addHoverButton(29580, s+"extremeshieldnormal", 0, 37, 75, "Buy Profround Decorative shield", 615, 29581, 1);
	      addHoveredButton(29581, s+"extremeshieldhov", 1, 37, 75, 29582);
	      addHoverButton(29583, s+"extremeswordnormal", 0, 37, 75, "Buy Profround Decorative sword", 615, 29584, 1);
	      addHoveredButton(29584, s+"extremeswordhov", 1, 37, 75, 29585);
	      addHoverButton(29586, s+"decbuttonnormal", 0, 144, 30, "Decorative Armour", 615, 29587, 1);
	      addHoveredButton(29587, s+"decbuttonhover", 1, 144, 30, 29588);
	      addHoverButton(29589, s+"consumebuttonnormal", 0, 144, 30, "Consumables", 615, 29590, 1);
	      addHoveredButton(29590, s+"consumebuttonhover", 1, 144, 30, 29501);
	      addHoverButton(29592, s+"miscbuttonnormal", 0, 144, 30, "Miscellaneous", 615, 29593, 1);
	      addHoveredButton(29593, s+"miscbuttonhover", 1, 144, 30, 29594);
	      addHoverButton(29595, s+"close", 0, 16, 16, "Close", 615, 29596, 1);
	      addHoveredButton(29596, s+"close", 1, 16, 16, 29597);
	      setChildren(49, tab);
	      setBounds(29525, 2, 19, i, tab);
	      i++;
	      setBounds(29526, 77, 90, i, tab);
	      i++;
	      setBounds(29527, 77, 90, i, tab);
	      i++;
	      setBounds(29529, 119, 90, i, tab);
	      i++;
	      setBounds(29530, 119, 90, i, tab);
	      i++;
	      setBounds(29532, 161, 90, i, tab);
	      i++;
	      setBounds(29533, 161, 90, i, tab);
	      i++;
	      setBounds(29535, 203, 90, i, tab);
	      i++;
	      setBounds(29536, 203, 90, i, tab);
	      i++;
	      setBounds(29538, 245, 90, i, tab);
	      i++;
	      setBounds(29539, 245, 90, i, tab);
	      i++;
	      setBounds(29541, 287, 90, i, tab);
	      i++;
	      setBounds(29542, 287, 90, i, tab);
	      i++;
	      setBounds(29544, 329, 90, i, tab);
	      i++;
	      setBounds(29545, 329, 90, i, tab);
	      i++;
	      setBounds(29547, 371, 90, i, tab);
	      i++;
	      setBounds(29548, 371, 90, i, tab);
	      i++;
	      setBounds(29550, 413, 90, i, tab);
	      i++;
	      setBounds(29551, 413, 90, i, tab);
	      i++;
	      setBounds(29553, 455, 90, i, tab);
	      i++;
	      setBounds(29554, 455, 90, i, tab);
	      i++;
	      setBounds(29556, 77, 190, i, tab);
	      i++;
	      setBounds(29557, 77, 190, i, tab);
	      i++;
	      setBounds(29559, 119, 190, i, tab);
	      i++;
	      setBounds(29560, 119, 190, i, tab);
	      i++;
	      setBounds(29562, 161, 190, i, tab);
	      i++;
	      setBounds(29563, 161, 190, i, tab);
	      i++;
	      setBounds(29565, 203, 190, i, tab);
	      i++;
	      setBounds(29566, 203, 190, i, tab);
	      i++;
	      setBounds(29568, 245, 190, i, tab);
	      i++;
	      setBounds(29569, 245, 190, i, tab);
	      i++;
	      setBounds(29571, 287, 190, i, tab);
	      i++;
	      setBounds(29572, 287, 190, i, tab);
	      i++;
	      setBounds(29574, 329, 190, i, tab);
	      i++;
	      setBounds(29575, 329, 190, i, tab);
	      i++;
	      setBounds(29577, 371, 190, i, tab);
	      i++;
	      setBounds(29578, 371, 190, i, tab);
	      i++;
	      setBounds(29580, 413, 190, i, tab);
	      i++;
	      setBounds(29581, 413, 190, i, tab);
	      i++;
	      setBounds(29583, 455, 190, i, tab);
	      i++;
	      setBounds(29584, 455, 190, i, tab);
	      i++;
	      setBounds(29586, 28, 44, i, tab);
	      i++;
	      setBounds(29587, 28, 44, i, tab);
	      i++;  
	      setBounds(29589, 185, 44, i, tab);
	      i++;
	      setBounds(29590, 185, 44, i, tab);
	      i++;  
	      setBounds(29592, 342, 44, i, tab);
	      i++;
	      setBounds(29593, 342, 44, i, tab);
	      i++;  
	      setBounds(29595, 480, 22, i, tab);
	      i++;
	      setBounds(29596, 480, 22, i, tab);
	      i++; 
	  }
	  public static void castleWars2() {
	      RSInterface tab = addTabInterface(27956);
	      int i = 0;
	      String s = "CastleWars/";
	      addSprite(29600, s+"Main");
	      addHoverButton(29601, s+"base", 0, 37, 75, "Buy Ballista base", 615, 29602, 1);
	      addHoveredButton(29602, s+"standhover", 1, 37, 75, 29603);
	      addHoverButton(29604, s+"boltsack", 0, 37, 75, "Buy Ballista bolt stack", 615, 29605, 1);
	      addHoveredButton(29605, s+"boltsack", 1, 37, 75, 29606);
	      addHoverButton(29607, s+"bow", 0, 37, 75, "Buy Ballista bow", 615, 29608, 1);
	      addHoveredButton(29608, s+"bow", 1, 37, 75, 29609);
	      addHoverButton(29610, s+"chamber", 0, 37, 75, "Buy Ballista chamber", 615, 29611, 1);
	      addHoveredButton(29611, s+"chamber", 1, 37, 75, 29612);
	      addHoverButton(29613, s+"ammo", 0, 37, 75, "Buy Ballista ammo", 615, 29614, 1);
	      addHoveredButton(29614, s+"ammo", 1, 37, 75, 29615);
	      addHoverButton(29616, s+"melee", 0, 37, 75, "Buy Melee potion set", 615, 29617, 1);
	      addHoveredButton(29617, s+"melee", 1, 37, 75, 29618);
	      addHoverButton(29619, s+"range", 0, 37, 75, "Buy Ranged potion set", 615, 29620, 1);
	      addHoveredButton(29620, s+"range", 1, 37, 75, 29621);
	      addHoverButton(29622, s+"magic", 0, 37, 75, "Buy Magic potion set", 615, 29623, 1);
	      addHoveredButton(29623, s+"mage", 1, 37, 75, 29624);
	      addHoverButton(29586, s+"decbuttonnormal", 0, 144, 30, "Decorative Armour", 615, 29587, 1);
	      addHoveredButton(29587, s+"decbuttonhover", 1, 144, 30, 29588);
	      addHoverButton(29589, s+"consumebuttonnormal", 0, 144, 30, "Consumables", 615, 29590, 1);
	      addHoveredButton(29590, s+"consumebuttonhover", 1, 144, 30, 29501);
	      addHoverButton(29592, s+"miscbuttonnormal", 0, 144, 30, "Miscellaneous", 615, 29593, 1);
	      addHoveredButton(29593, s+"miscbuttonhover", 1, 144, 30, 29594);
	      addHoverButton(29595, s+"close", 0, 16, 16, "Close", 615, 29596, 1);
	      addHoveredButton(29596, s+"close", 1, 16, 16, 29597);
	      setChildren(25, tab);
	      setBounds(29600, 2, 19, i, tab);
	      i++;
	      setBounds(29601, 119, 90, i, tab);
	      i++;
	      setBounds(29602, 119, 90, i, tab);
	      i++;
	      setBounds(29604, 161, 90, i, tab);
	      i++;
	      setBounds(29605, 161, 90, i, tab);
	      i++;
	      setBounds(29607, 203, 90, i, tab);
	      i++;
	      setBounds(29608, 203, 90, i, tab);
	      i++;
	      setBounds(29610, 245, 90, i, tab);
	      i++;
	      setBounds(29611, 245, 90, i, tab);
	      i++;
	      setBounds(29613, 287, 90, i, tab);
	      i++;
	      setBounds(29614, 287, 90, i, tab);
	      i++;
	      setBounds(29616, 119, 190, i, tab);
	      i++;
	      setBounds(29617, 119, 190, i, tab);
	      i++;
	      setBounds(29619, 203, 190, i, tab);
	      i++;
	      setBounds(29620, 203, 190, i, tab);
	      i++;
	      setBounds(29622, 287, 190, i, tab);
	      i++;
	      setBounds(29623, 287, 190, i, tab);
	      i++;
	      setBounds(29586, 28, 44, i, tab);
	      i++;
	      setBounds(29587, 28, 44, i, tab);
	      i++;  
	      setBounds(29589, 185, 44, i, tab);
	      i++;
	      setBounds(29590, 185, 44, i, tab);
	      i++;  
	      setBounds(29592, 342, 44, i, tab);
	      i++;
	      setBounds(29593, 342, 44, i, tab);
	      i++;  
	      setBounds(29595, 480, 22, i, tab);
	      i++;
	      setBounds(29596, 480, 22, i, tab);
	      i++; 
	  }
	  public static void castleWars3() {
	      RSInterface tab = addTabInterface(27957);
	      int i = 0;
	      String s = "CastleWars/";
	      addSprite(29525, s+"Main");
	      addHoverButton(29700, s+"saracape", 0, 37, 75, "Buy Saradomin Cloak", 615, 29701, 1);
	      addHoveredButton(29701, s+"saracape", 1, 37, 75, 29702);
	      addHoverButton(29703, s+"zamcape", 0, 37, 75, "Buy Zamorak Cloak", 615, 29704, 1);
	      addHoveredButton(29704, s+"zamcape", 1, 37, 75, 29705);
	      addHoverButton(29706, s+"sarahood", 0, 37, 75, "Buy Saradomin Hood", 615, 29707, 1);
	      addHoveredButton(29707, s+"sarahood", 1, 37, 75, 29708);
	      addHoverButton(29709, s+"zamhood", 0, 37, 75, "Buy Zamorak Hood", 615, 29710, 1);
	      addHoveredButton(29710, s+"zamhood", 1, 37, 75, 29711);
	      addHoverButton(29712, s+"flag", 0, 37, 75, "Buy Castle Wars flag cape", 615, 29713, 1);
	      addHoveredButton(29713, s+"flag", 1, 37, 75, 29714);
	      addHoverButton(29715, s+"kill", 0, 37, 75, "Buy Castle Wars kills cape", 615, 29716, 1);
	      addHoveredButton(29716, s+"kill", 1, 37, 75, 29717);
	      addHoverButton(29718, s+"hob", 0, 37, 75, "Buy Castle Wars hobbyist cape", 615, 29719, 1);
	      addHoveredButton(29719, s+"hob", 1, 37, 75, 29720);
	      addHoverButton(29721, s+"nerd", 0, 37, 75, "Buy Castle Wars enthusiast cape", 615, 29722, 1);
	      addHoveredButton(29722, s+"nerd", 1, 37, 75, 29723);
	      addHoverButton(29724, s+"nerdest", 0, 37, 75, "Buy Castle Wars professional cape", 615, 29725, 1);
	      addHoveredButton(29725, s+"nerdest", 1, 37, 75, 29726);    
	      addHoverButton(29727, s+"guth", 0, 37, 75, "Buy Guthix halo", 615, 29728, 1);
	      addHoveredButton(29728, s+"guth", 1, 37, 75, 29729);     
	      addHoverButton(29730, s+"sara", 0, 37, 75, "Buy Saradomin halo", 615, 29731, 1);
	      addHoveredButton(29731, s+"sara", 1, 37, 75, 29732);      
	      addHoverButton(29733, s+"zammy", 0, 37, 75, "Buy Zamorak halo", 615, 29734, 1);
	      addHoveredButton(29734, s+"zammy", 1, 37, 75, 29735);      
	      addHoverButton(29736, s+"faith", 0, 37, 75, "Buy Faithful shield", 615, 29737, 1);
	      addHoveredButton(29737, s+"faith", 1, 37, 75, 29738);
	      addHoverButton(29739, s+"free", 0, 37, 75, "Castle Wars Handbook", 615, 29740, 1);
	      addHoveredButton(29740, s+"free", 1, 37, 75, 29741);
	      addHoverButton(29586, s+"decbuttonnormal", 0, 144, 30, "Decorative Armour", 615, 29587, 1);
	      addHoveredButton(29587, s+"decbuttonhover", 1, 144, 30, 29588);
	      addHoverButton(29589, s+"consumebuttonnormal", 0, 144, 30, "Consumables", 615, 29590, 1);
	      addHoveredButton(29590, s+"consumebuttonhover", 1, 144, 30, 29501);
	      addHoverButton(29592, s+"miscbuttonnormal", 0, 144, 30, "Miscellaneous", 615, 29593, 1);
	      addHoveredButton(29593, s+"miscbuttonhover", 1, 144, 30, 29594);
	      addHoverButton(29595, s+"close", 0, 16, 16, "Close", 615, 29596, 1);
	      addHoveredButton(29596, s+"close", 1, 16, 16, 29597);
	      setChildren(37, tab);
	      setBounds(29525, 2, 19, i, tab);
	      i++;
	      setBounds(29700, 117, 90, i, tab);
	      i++;
	      setBounds(29701, 117, 90, i, tab);
	      i++;
	      setBounds(29703, 159, 90, i, tab);
	      i++;
	      setBounds(29704, 159, 90, i, tab);
	      i++;
	      setBounds(29706, 201, 90, i, tab);
	      i++;
	      setBounds(29707, 201, 90, i, tab);
	      i++;
	      setBounds(29709, 243, 90, i, tab);
	      i++;
	      setBounds(29710, 243, 90, i, tab);
	      i++;
	      setBounds(29712, 285, 90, i, tab);
	      i++;
	      setBounds(29713, 285, 90, i, tab);
	      i++;
	      setBounds(29715, 327, 90, i, tab);
	      i++;
	      setBounds(29716, 327, 90, i, tab);
	      i++;
	      setBounds(29718, 369, 90, i, tab);
	      i++;
	      setBounds(29719, 369, 90, i, tab);
	      i++;
	      setBounds(29721, 411, 90, i, tab);
	      i++;
	      setBounds(29722, 411, 90, i, tab);
	      i++;
	      setBounds(29724, 453, 90, i, tab);
	      i++;
	      setBounds(29725, 453, 90, i, tab);
	      i++;
	      setBounds(29727, 117, 190, i, tab);
	      i++;
	      setBounds(29728, 117, 190, i, tab);
	      i++;
	      setBounds(29730, 201, 190, i, tab);
	      i++;
	      setBounds(29731, 201, 190, i, tab);
	      i++;
	      setBounds(29733, 285, 190, i, tab);
	      i++;
	      setBounds(29734, 285, 190, i, tab);
	      i++;  
	      setBounds(29736, 369, 190, i, tab);
	      i++;
	      setBounds(29737, 369, 190, i, tab);
	      i++;
	      setBounds(29739, 453, 190, i, tab);
	      i++;
	      setBounds(29740, 453, 190, i, tab);
	      i++;
	      setBounds(29586, 28, 44, i, tab);
	      i++;
	      setBounds(29587, 28, 44, i, tab);
	      i++;  
	      setBounds(29589, 185, 44, i, tab);
	      i++;
	      setBounds(29590, 185, 44, i, tab);
	      i++;  
	      setBounds(29592, 342, 44, i, tab);
	      i++;
	      setBounds(29593, 342, 44, i, tab);
	      i++;  
	      setBounds(29595, 480, 22, i, tab);
	      i++;
	      setBounds(29596, 480, 22, i, tab);
	      i++; 
	  }
	  
	public static void setBoundry(int frame, int ID, int X, int Y,
			RSInterface RSInterface) {
		RSInterface.children[frame] = ID;
		RSInterface.childX[frame] = X;
		RSInterface.childY[frame] = Y;
	}

	public static void addTooltipBox(int id, String text) {
		RSInterface rsi = addInterface(id);
		rsi.id = id;
		rsi.parentID = id;
		rsi.type = 9;
		rsi.message = text;
	}

	public static void addTooltip(int id, String text, int H, int W) {
		RSInterface rsi = addTabInterface(id);
		rsi.id = id;
		rsi.type = 0;
		rsi.isMouseoverTriggered = true;
		rsi.mOverInterToTrigger = -1;
		addTooltipBox(id + 1, text);
		rsi.totalChildren(1);
		rsi.child(0, id + 1, 0, 0);
		rsi.height = H;
		rsi.width = W;
	}

	public static void setChildren(int total, RSInterface i) {
		i.children = new int[total];
		i.childX = new int[total];
		i.childY = new int[total];
	}

	public static void addTooltip(int id, String text) {
		RSInterface rsinterface = addTabInterface(id);
		rsinterface.parentID = id;
		rsinterface.type = 0;
		rsinterface.isMouseoverTriggered = true;
		rsinterface.mOverInterToTrigger = -1;
		addTooltipBox(id + 1, text);
		rsinterface.totalChildren(1);
		rsinterface.child(0, id + 1, 0, 0);
	}

	public static void magicTab(TextDrawingArea tda[]) {
		RSInterface tab = addTabInterface(1151);
		RSInterface homeHover = addTabInterface(1196);
		RSInterface spellButtons = interfaceCache[12424];
		spellButtons.scrollMax = 0;
		spellButtons.height = 260;
		spellButtons.width = 190;
		int spellButton[] = { 1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249,
				1258, 1267, 1274, 1283, 1573, 1290, 1299, 1308, 1315, 1324,
				1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388, 1397, 1404,
				1583, 12038, 1414, 1421, 1430, 1437, 1446, 1453, 1460, 1469,
				15878, 1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512,
				1521, 1530, 1544, 1553, 1563, 1593, 1635, 12426, 12436, 12446,
				12456, 6004, 18471 };
		tab.totalChildren(63);
		tab.child(0, 12424, 13, 24);
		for (int i1 = 0; i1 < spellButton.length; i1++) {
			int yPos = i1 <= 34 ? 183 : 8;
			tab.child(1, 1195, 13, 24);
			tab.child(i1 + 2, spellButton[i1], 5, yPos);
			addButton(1195, 1, "Interfaces/Magic/Home",
					"Cast @gre@Home Teleport");
			RSInterface homeButton = interfaceCache[1195];
			homeButton.mOverInterToTrigger = 1196;
		}

		for (int i2 = 0; i2 < spellButton.length; i2++) {
			if (i2 < 60)
				spellButtons.childX[i2] = spellButtons.childX[i2] + 24;
			if (i2 == 6 || i2 == 12 || i2 == 19 || i2 == 35 || i2 == 41
					|| i2 == 44 || i2 == 49 || i2 == 51)
				spellButtons.childX[i2] = 0;
			spellButtons.childY[6] = 24;
			spellButtons.childY[12] = 48;
			spellButtons.childY[19] = 72;
			spellButtons.childY[49] = 96;
			spellButtons.childY[44] = 120;
			spellButtons.childY[51] = 144;
			spellButtons.childY[35] = 170;
			spellButtons.childY[41] = 192;
		}

		homeHover.isMouseoverTriggered = true;
		addText(1197, "Level 0: Home Teleport", tda, 1, 0xfe981f, true, true);
		RSInterface homeLevel = interfaceCache[1197];
		homeLevel.width = 174;
		homeLevel.height = 68;
		addText(1198, "A teleport which requires no", tda, 0, 0xaf6a1a, true,
				true);
		addText(18998, "runes and no required level that", tda, 0, 0xaf6a1a,
				true, true);
		addText(18999, "teleports you to the main land.", tda, 0, 0xaf6a1a,
				true, true);
		homeHover.totalChildren(4);
		homeHover.child(0, 1197, 3, 4);
		homeHover.child(1, 1198, 91, 23);
		homeHover.child(2, 18998, 91, 34);
		homeHover.child(3, 18999, 91, 45);
	}

	public static void addClickableText(int id, String text, String tooltip,
			TextDrawingArea tda[], int idx, int color, boolean center,
			boolean shadow, int width) {
		RSInterface tab = addTabInterface(id);
		tab.parentID = id;
		tab.id = id;
		tab.type = 4;
		tab.atActionType = 1;
		tab.width = width;
		tab.height = 11;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.mOverInterToTrigger = -1;
		tab.centerText = center;
		tab.textShadow = shadow;
		tab.textDrawingAreas = tda[idx];
		tab.message = text;
		tab.disabledText = "";
		tab.textColor = color;
		tab.anInt219 = 0;
		tab.textHoverColour = 0xffffff;
		tab.anInt239 = 0;
		tab.tooltip = tooltip;
	}

	public static void ancientMagicTab(TextDrawingArea tda[]) {
		RSInterface tab = addInterface(12855);
		addButton(12856, 1, "Interfaces/Magic/Home", "Cast @gre@Home Teleport");
		RSInterface homeButton = interfaceCache[12856];
		homeButton.mOverInterToTrigger = 1196;
		int itfChildren[] = { 12856, 12939, 12987, 13035, 12901, 12861, 13045,
				12963, 13011, 13053, 12919, 12881, 13061, 12951, 12999, 13069,
				12911, 12871, 13079, 12975, 13023, 13087, 12929, 12891, 13095,
				1196, 12940, 12988, 13036, 12902, 12862, 13046, 12964, 13012,
				13054, 12920, 12882, 13062, 12952, 13000, 13070, 12912, 12872,
				13080, 12976, 13024, 13088, 12930, 12892, 13096 };
		tab.totalChildren(itfChildren.length);
		int i1 = 0;
		int xPos = 18;
		int yPos = 8;
		while (i1 < itfChildren.length) {
			if (xPos > 175) {
				xPos = 18;
				yPos += 28;
			}
			if (i1 < 25)
				tab.child(i1, itfChildren[i1], xPos, yPos);
			if (i1 > 24) {
				yPos = i1 >= 41 ? 1 : 181;
				tab.child(i1, itfChildren[i1], 4, yPos);
			}
			i1++;
			xPos += 45;
		}
	}

	public static void drawBlackBox(int xPos, int yPos) {
		DrawingArea.drawPixels(71, yPos - 1, xPos - 2, 0x726451, 1);
		DrawingArea.drawPixels(69, yPos, xPos + 174, 0x726451, 1);
		DrawingArea.drawPixels(1, yPos - 2, xPos - 2, 0x726451, 178);
		DrawingArea.drawPixels(1, yPos + 68, xPos, 0x726451, 174);
		DrawingArea.drawPixels(71, yPos - 1, xPos - 1, 0x2e2b23, 1);
		DrawingArea.drawPixels(71, yPos - 1, xPos + 175, 0x2e2b23, 1);
		DrawingArea.drawPixels(1, yPos - 1, xPos, 0x2e2b23, 175);
		DrawingArea.drawPixels(1, yPos + 69, xPos, 0x2e2b23, 175);
		DrawingArea.method335(0, yPos, 174, 68, 220, xPos);
	}
	public static void addButton(int id, int sid, String spriteName) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = tab.sprite1.myWidth;
		tab.height = tab.sprite2.myHeight;
	}
	public static void addButton(int id, int sid, String spriteName,
			String tooltip) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = tab.sprite1.myWidth;
		tab.height = tab.sprite2.myHeight;
		tab.tooltip = tooltip;
	}

	public static void AddInterfaceButton(int i, int j, String name, int W,
			int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.type = 5;
		RSInterface.atActionType = AT;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.mOverInterToTrigger = 52;
		RSInterface.sprite1 = imageLoader(j, name);
		RSInterface.sprite2 = imageLoader(j, name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}

		
		public static void AddInterfaceButton(int id, int sid, String spriteName, String tooltip, int mOver, int atAction, int width, int height) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = atAction;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.mOverInterToTrigger = mOver;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = tooltip;
		tab.inventoryhover = true;
	}	
	
	public static void AddInterfaceButton(int id, int sid, String spriteName, String tooltip) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = tab.sprite1.myWidth;
		tab.height = tab.sprite2.myHeight;
		tab.tooltip = tooltip;
	}
	public static void addPrayer(int i, int configId, int configFrame,
			int requiredValues, int spriteID, String prayerName) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = 5608;
		tab.type = 5;
		tab.atActionType = 4;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.mOverInterToTrigger = -1;
		tab.sprite1 = imageLoader(0, "PRAYERGLOW");
		tab.sprite2 = imageLoader(1, "PRAYERGLOW");
		tab.width = 34;
		tab.height = 34;
		tab.valueCompareType = new int[1];
		tab.requiredValues = new int[1];
		tab.valueCompareType[0] = 1;
		tab.requiredValues[0] = configId;
		tab.valueIndexArray = new int[1][3];
		tab.valueIndexArray[0][0] = 5;
		tab.valueIndexArray[0][1] = configFrame;
		tab.valueIndexArray[0][2] = 0;
		tab.tooltip = (new StringBuilder()).append("Activate@or2@ ")
				.append(prayerName).toString();
		RSInterface tab2 = addTabInterface(i + 1);
		tab2.id = i + 1;
		tab2.parentID = 5608;
		tab2.type = 5;
		tab2.atActionType = 0;
		tab2.contentType = 0;
		tab2.opacity = 0;
		tab2.mOverInterToTrigger = -1;
		tab2.sprite1 = imageLoader(spriteID, "/PRAYER/PRAYON");
		tab2.sprite2 = imageLoader(spriteID, "/PRAYER/PRAYOFF");
		tab2.width = 34;
		tab2.height = 34;
		tab2.valueCompareType = new int[1];
		tab2.requiredValues = new int[1];
		tab2.valueCompareType[0] = 2;
		tab2.requiredValues[0] = requiredValues + 1;
		tab2.valueIndexArray = new int[1][3];
		tab2.valueIndexArray[0][0] = 2;
		tab2.valueIndexArray[0][1] = 5;
		tab2.valueIndexArray[0][2] = 0;
	}

	public static void friendsTab(TextDrawingArea tda[]) {
		RSInterface tab = addTabInterface(5065);
		RSInterface list = interfaceCache[5066];
		addText(5067, "Friends List", tda, 1, 0xff9933, true, true);
		addText(5070, "Add Friend", tda, 0, 0xff9933, false, true);
		addText(5071, "Delete Friend", tda, 0, 0xff9933, false, true);
		addSprite(16126, 4, "Interfaces/Friends/SPRITE");
		addSprite(16127, 8, "/Interfaces/Friends/SPRITE");
		addHoverButton(5068, "Interfaces/Friends/SPRITE", 6, 72, 32,
				"Add Friend", 201, 5072, 1);
		addHoveredButton(5072, "Interfaces/Friends/SPRITE", 7, 72, 32, 5073);
		addHoverButton(5069, "/Interfaces/Friends/SPRITE", 6, 72, 32,
				"Delete Friend", 202, 5074, 1);
		addHoveredButton(5074, "/Interfaces/Friends/SPRITE", 7, 72, 32, 5075);
		tab.totalChildren(11);
		tab.child(0, 5067, 95, 4);
		tab.child(1, 16127, 0, 25);
		tab.child(2, 16126, 0, 221);
		tab.child(3, 5066, 0, 24);
		tab.child(4, 16126, 0, 22);
		tab.child(5, 5068, 15, 226);
		tab.child(6, 5072, 15, 226);
		tab.child(7, 5069, 103, 226);
		tab.child(8, 5074, 103, 226);
		tab.child(9, 5070, 25, 237);
		tab.child(10, 5071, 106, 237);
		list.height = 196;
		list.width = 174;
		int id = 5092;
		for (int i = 0; id <= 5191 && i <= 99; i++) {
			list.children[i] = id;
			list.childX[i] = 3;
			list.childY[i] = list.childY[i] - 7;
			id++;
		}

		id = 5192;
		for (int i = 100; id <= 5291 && i <= 199; i++) {
			list.children[i] = id;
			list.childX[i] = 131;
			list.childY[i] = list.childY[i] - 7;
			id++;
		}

	}

	public static void ignoreTab(TextDrawingArea tda[]) {
		RSInterface tab = addTabInterface(5715);
		RSInterface list = interfaceCache[5716];
		addText(5717, "Ignore List", tda, 1, 0xff9933, true, true);
		addText(5720, "Add Name", tda, 0, 0xff9933, false, true);
		addText(5721, "Delete Name", tda, 0, 0xff9933, false, true);
		addHoverButton(5718, "/Interfaces/Friends/SPRITE", 6, 72, 32,
				"Add Name", 501, 5722, 1);
		addHoveredButton(5722, "/Interfaces/Friends/SPRITE", 7, 72, 32, 5723);
		addHoverButton(5719, "/Interfaces/Friends/SPRITE", 6, 72, 32,
				"Delete Name", 502, 5724, 1);
		addHoveredButton(5724, "/Interfaces/Friends/SPRITE", 7, 72, 32, 5725);
		tab.totalChildren(11);
		tab.child(0, 5717, 95, 4);
		tab.child(1, 16127, 0, 25);
		tab.child(2, 16126, 0, 221);
		tab.child(3, 5716, 0, 24);
		tab.child(4, 16126, 0, 22);
		tab.child(5, 5718, 15, 226);
		tab.child(6, 5722, 15, 226);
		tab.child(7, 5719, 103, 226);
		tab.child(8, 5724, 103, 226);
		tab.child(9, 5720, 27, 237);
		tab.child(10, 5721, 108, 237);
		list.height = 196;
		list.width = 174;
		int id = 5742;
		for (int i = 0; id <= 5841 && i <= 99; i++) {
			list.children[i] = id;
			list.childX[i] = 3;
			list.childY[i] = list.childY[i] - 7;
			id++;
		}

	}

	private static Sprite CustomSpriteLoader(int id, String s) {
		long l = (TextClass.method585(s) << 8) + (long) id;
		Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
		if (sprite != null)
			return sprite;
		try {
			sprite = new Sprite((new StringBuilder()).append("/Attack/")
					.append(id).append(s).toString());
			aMRUNodes_238.removeFromCache(sprite, l);
		} catch (Exception exception) {
			return null;
		}
		return sprite;
	}

	public static RSInterface addInterface(int id) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
		rsi.id = id;
		rsi.parentID = id;
		rsi.width = 512;
		rsi.height = 334;
		return rsi;
	}

	public static void addText(int id, String text, TextDrawingArea tda[],
			int idx, int color, boolean centered) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
		if (centered)
			rsi.centerText = true;
		rsi.textShadow = true;
		rsi.textDrawingAreas = tda[idx];
		rsi.message = text;
		rsi.textColor = color;
		rsi.id = id;
		rsi.type = 4;
	}

	public static void textColor(int id, int color) {
		RSInterface rsi = interfaceCache[id];
		rsi.textColor = color;
	}

	public static void textSize(int id, TextDrawingArea tda[], int idx) {
		RSInterface rsi = interfaceCache[id];
		rsi.textDrawingAreas = tda[idx];
	}

	public static void addCacheSprite(int id, int sprite1, int sprite2,
			String sprites) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
		rsi.sprite1 = method207(sprite1, aClass44, sprites);
		rsi.sprite2 = method207(sprite2, aClass44, sprites);
		rsi.parentID = id;
		rsi.id = id;
		rsi.type = 5;
	}

	public static void sprite1(int id, int sprite) {
		RSInterface class9 = interfaceCache[id];
		class9.sprite1 = CustomSpriteLoader(sprite, "");
	}//so you cachepacked it? What no

	public static void addActionButton(int id, int sprite, int sprite2,
			int width, int height, String s) {
		RSInterface rsi = interfaceCache[id] = new RSInterface();
		rsi.sprite1 = CustomSpriteLoader(sprite, "");
		if (sprite2 == sprite)
			rsi.sprite2 = CustomSpriteLoader(sprite, "a");
		else
			rsi.sprite2 = CustomSpriteLoader(sprite2, "");
		rsi.tooltip = s;
		rsi.contentType = 0;
		rsi.atActionType = 1;
		rsi.width = width;
		rsi.mOverInterToTrigger = 52;
		rsi.parentID = id;
		rsi.id = id;
		rsi.type = 5;
		rsi.height = height;
	}

	public static void addToggleButton(int id, int sprite, int setconfig,
			int width, int height, String s) {
		RSInterface rsi = addInterface(id);
		rsi.sprite1 = CustomSpriteLoader(sprite, "");
		rsi.sprite2 = CustomSpriteLoader(sprite, "a");
		rsi.requiredValues = new int[1];
		rsi.requiredValues[0] = 1;
		rsi.valueCompareType = new int[1];
		rsi.valueCompareType[0] = 1;
		rsi.valueIndexArray = new int[1][3];
		rsi.valueIndexArray[0][0] = 5;
		rsi.valueIndexArray[0][1] = setconfig;
		rsi.valueIndexArray[0][2] = 0;
		rsi.atActionType = 4;
		rsi.width = width;
		rsi.mOverInterToTrigger = -1;
		rsi.parentID = id;
		rsi.id = id;
		rsi.type = 5;
		rsi.height = height;
		rsi.tooltip = s;
	}

	public void totalChildren(int id, int x, int y) {
		children = new int[id];
		childX = new int[x];
		childY = new int[y];
	}

	public static void removeSomething(int id) {
		@SuppressWarnings("unused")
		RSInterface rsi = interfaceCache[id] = new RSInterface();
	}

	public void specialBar(int id) {
		addActionButton(id - 12, 7587, -1, 150, 26, "Use @gre@Special Attack");
		for (int i = id - 11; i < id; i++)
			removeSomething(i);

		RSInterface rsi = interfaceCache[id - 12];
		rsi.width = 150;
		rsi.height = 26;
		rsi = interfaceCache[id];
		rsi.width = 150;
		rsi.height = 26;
		rsi.child(0, id - 12, 0, 0);
		rsi.child(12, id + 1, 3, 7);
		rsi.child(23, id + 12, 16, 8);
		for (int i = 13; i < 23; i++)
			rsi.childY[i]--;

		rsi = interfaceCache[id + 1];
		rsi.type = 5;
		rsi.sprite1 = CustomSpriteLoader(7600, "");
		for (int i = id + 2; i < id + 12; i++) {
			rsi = interfaceCache[i];
			rsi.type = 5;
		}

		sprite1(id + 2, 7601);
		sprite1(id + 3, 7602);
		sprite1(id + 4, 7603);
		sprite1(id + 5, 7604);
		sprite1(id + 6, 7605);
		sprite1(id + 7, 7606);
		sprite1(id + 8, 7607);
		sprite1(id + 9, 7608);
		sprite1(id + 10, 7609);
		sprite1(id + 11, 7610);
	}

	public static void Sidebar0(TextDrawingArea tda[]) {
		Sidebar0a(1698, 1701, 7499, "Chop", "Hack", "Smash", "Block", 42, 75,
				127, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103,
				tda);
		Sidebar0a(2276, 2279, 7574, "Stab", "Lunge", "Slash", "Block", 43, 75,
				124, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103,
				tda);
		Sidebar0a(2423, 2426, 7599, "Chop", "Slash", "Lunge", "Block", 42, 75,
				125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103,
				tda);
		Sidebar0a(3796, 3799, 7624, "Pound", "Pummel", "Spike", "Block", 39,
				75, 121, 75, 41, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40,
				103, tda);
		Sidebar0a(4679, 4682, 7674, "Lunge", "Swipe", "Pound", "Block", 40, 75,
				124, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103,
				tda);
		Sidebar0a(4705, 4708, 7699, "Chop", "Slash", "Smash", "Block", 42, 75,
				125, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103,
				tda);
		Sidebar0a(5570, 5573, 7724, "Spike", "Impale", "Smash", "Block", 41,
				75, 123, 75, 39, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40,
				103, tda);
		Sidebar0a(7762, 7765, 7800, "Chop", "Slash", "Lunge", "Block", 42, 75,
				125, 75, 40, 128, 125, 128, 122, 103, 40, 50, 122, 50, 40, 103,
				tda);
		Sidebar0b(776, 779, "Reap", "Chop", "Jab", "Block", 42, 75, 126, 75,
				46, 128, 125, 128, 122, 103, 122, 50, 40, 103, 40, 50, tda);
		Sidebar0c(425, 428, 7474, "Pound", "Pummel", "Block", 39, 75, 121, 75,
				42, 128, 40, 103, 40, 50, 122, 50, tda);
		Sidebar0c(1749, 1752, 7524, "Accurate", "Rapid", "Longrange", 33, 75,
				125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
		Sidebar0c(1764, 1767, 7549, "Accurate", "Rapid", "Longrange", 33, 75,
				125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
		Sidebar0c(4446, 4449, 7649, "Accurate", "Rapid", "Longrange", 33, 75,
				125, 75, 29, 128, 40, 103, 40, 50, 122, 50, tda);
		Sidebar0c(5855, 5857, 7749, "Punch", "Kick", "Block", 40, 75, 129, 75,
				42, 128, 40, 50, 122, 50, 40, 103, tda);
		Sidebar0c(6103, 6132, 6117, "Bash", "Pound", "Block", 43, 75, 124, 75,
				42, 128, 40, 103, 40, 50, 122, 50, tda);
		Sidebar0c(8460, 8463, 8493, "Jab", "Swipe", "Fend", 46, 75, 124, 75,
				43, 128, 40, 103, 40, 50, 122, 50, tda);
		Sidebar0c(12290, 12293, 12323, "Flick", "Lash", "Deflect", 44, 75, 127,
				75, 36, 128, 40, 50, 40, 103, 122, 50, tda);
		Sidebar0d(328, 331, "Bash", "Pound", "Focus", 42, 66, 39, 101, 41, 136,
				40, 120, 40, 50, 40, 85, tda);
		RSInterface rsi = addInterface(19300);
		textSize(3983, tda, 0);
		addToggleButton(150, 150, 172, 150, 44, "Auto Retaliate");
		rsi.totalChildren(2, 2, 2);
		rsi.child(0, 3983, 52, 25);
		rsi.child(1, 150, 21, 153);
		rsi = interfaceCache[3983];
		rsi.centerText = true;
		rsi.textColor = 0xff981f;
	}

	public static void Sidebar0a(int id, int id2, int id3, String text1,
			String text2, String text3, String text4, int str1x, int str1y,
			int str2x, int str2y, int str3x, int str3y, int str4x, int str4y,
			int img1x, int img1y, int img2x, int img2y, int img3x, int img3y,
			int img4x, int img4y, TextDrawingArea tda[]) {
		RSInterface rsi = addInterface(id);
		addText(id2, "-2", tda, 3, 0xff981f, true);
		addText(id2 + 11, text1, tda, 0, 0xff981f, false);
		addText(id2 + 12, text2, tda, 0, 0xff981f, false);
		addText(id2 + 13, text3, tda, 0, 0xff981f, false);
		addText(id2 + 14, text4, tda, 0, 0xff981f, false);
		rsi.specialBar(id3);
		rsi.width = 190;
		rsi.height = 261;
		int last = 15;
		int frame = 0;
		rsi.totalChildren(last, last, last);
		rsi.child(frame, id2 + 3, 21, 46);
		frame++;
		rsi.child(frame, id2 + 4, 104, 99);
		frame++;
		rsi.child(frame, id2 + 5, 21, 99);
		frame++;
		rsi.child(frame, id2 + 6, 105, 46);
		frame++;
		rsi.child(frame, id2 + 7, img1x, img1y);
		frame++;
		rsi.child(frame, id2 + 8, img2x, img2y);
		frame++;
		rsi.child(frame, id2 + 9, img3x, img3y);
		frame++;
		rsi.child(frame, id2 + 10, img4x, img4y);
		frame++;
		rsi.child(frame, id2 + 11, str1x, str1y);
		frame++;
		rsi.child(frame, id2 + 12, str2x, str2y);
		frame++;
		rsi.child(frame, id2 + 13, str3x, str3y);
		frame++;
		rsi.child(frame, id2 + 14, str4x, str4y);
		frame++;
		rsi.child(frame, 19300, 0, 0);
		frame++;
		rsi.child(frame, id2, 94, 4);
		frame++;
		rsi.child(frame, id3, 21, 205);
		frame++;
		for (int i = id2 + 3; i < id2 + 7; i++) {
			rsi = interfaceCache[i];
			rsi.sprite1 = CustomSpriteLoader(19301, "");
			rsi.sprite2 = CustomSpriteLoader(19301, "a");
			rsi.width = 68;
			rsi.height = 44;
		}

	}

	public static void Sidebar0b(int id, int id2, String text1, String text2,
			String text3, String text4, int str1x, int str1y, int str2x,
			int str2y, int str3x, int str3y, int str4x, int str4y, int img1x,
			int img1y, int img2x, int img2y, int img3x, int img3y, int img4x,
			int img4y, TextDrawingArea tda[]) {
		RSInterface rsi = addInterface(id);
		addText(id2, "-2", tda, 3, 0xff981f, true);
		addText(id2 + 11, text1, tda, 0, 0xff981f, false);
		addText(id2 + 12, text2, tda, 0, 0xff981f, false);
		addText(id2 + 13, text3, tda, 0, 0xff981f, false);
		addText(id2 + 14, text4, tda, 0, 0xff981f, false);
		rsi.width = 190;
		rsi.height = 261;
		int last = 14;
		int frame = 0;
		rsi.totalChildren(last, last, last);
		rsi.child(frame, id2 + 3, 21, 46);
		frame++;
		rsi.child(frame, id2 + 4, 104, 99);
		frame++;
		rsi.child(frame, id2 + 5, 21, 99);
		frame++;
		rsi.child(frame, id2 + 6, 105, 46);
		frame++;
		rsi.child(frame, id2 + 7, img1x, img1y);
		frame++;
		rsi.child(frame, id2 + 8, img2x, img2y);
		frame++;
		rsi.child(frame, id2 + 9, img3x, img3y);
		frame++;
		rsi.child(frame, id2 + 10, img4x, img4y);
		frame++;
		rsi.child(frame, id2 + 11, str1x, str1y);
		frame++;
		rsi.child(frame, id2 + 12, str2x, str2y);
		frame++;
		rsi.child(frame, id2 + 13, str3x, str3y);
		frame++;
		rsi.child(frame, id2 + 14, str4x, str4y);
		frame++;
		rsi.child(frame, 19300, 0, 0);
		frame++;
		rsi.child(frame, id2, 94, 4);
		frame++;
		for (int i = id2 + 3; i < id2 + 7; i++) {
			rsi = interfaceCache[i];
			rsi.sprite1 = CustomSpriteLoader(19301, "");
			rsi.sprite2 = CustomSpriteLoader(19301, "a");
			rsi.width = 68;
			rsi.height = 44;
		}

	}

	public static void Sidebar0c(int id, int id2, int id3, String text1,
			String text2, String text3, int str1x, int str1y, int str2x,
			int str2y, int str3x, int str3y, int img1x, int img1y, int img2x,
			int img2y, int img3x, int img3y, TextDrawingArea tda[]) {
		RSInterface rsi = addInterface(id);
		addText(id2, "-2", tda, 3, 0xff981f, true);
		addText(id2 + 9, text1, tda, 0, 0xff981f, false);
		addText(id2 + 10, text2, tda, 0, 0xff981f, false);
		addText(id2 + 11, text3, tda, 0, 0xff981f, false);
		rsi.specialBar(id3);
		rsi.width = 190;
		rsi.height = 261;
		int last = 12;
		int frame = 0;
		rsi.totalChildren(last, last, last);
		rsi.child(frame, id2 + 3, 21, 99);
		frame++;
		rsi.child(frame, id2 + 4, 105, 46);
		frame++;
		rsi.child(frame, id2 + 5, 21, 46);
		frame++;
		rsi.child(frame, id2 + 6, img1x, img1y);
		frame++;
		rsi.child(frame, id2 + 7, img2x, img2y);
		frame++;
		rsi.child(frame, id2 + 8, img3x, img3y);
		frame++;
		rsi.child(frame, id2 + 9, str1x, str1y);
		frame++;
		rsi.child(frame, id2 + 10, str2x, str2y);
		frame++;
		rsi.child(frame, id2 + 11, str3x, str3y);
		frame++;
		rsi.child(frame, 19300, 0, 0);
		frame++;
		rsi.child(frame, id2, 94, 4);
		frame++;
		rsi.child(frame, id3, 21, 205);
		frame++;
		for (int i = id2 + 3; i < id2 + 6; i++) {
			rsi = interfaceCache[i];
			rsi.sprite1 = CustomSpriteLoader(19301, "");
			rsi.sprite2 = CustomSpriteLoader(19301, "a");
			rsi.width = 68;
			rsi.height = 44;
		}

	}

	public static void Sidebar0d(int id, int id2, String text1, String text2,
			String text3, int str1x, int str1y, int str2x, int str2y,
			int str3x, int str3y, int img1x, int img1y, int img2x, int img2y,
			int img3x, int img3y, TextDrawingArea tda[]) {
		RSInterface rsi = addInterface(id);
		addText(id2, "-2", tda, 3, 0xff981f, true);
		addText(id2 + 9, text1, tda, 0, 0xff981f, false);
		addText(id2 + 10, text2, tda, 0, 0xff981f, false);
		addText(id2 + 11, text3, tda, 0, 0xff981f, false);
		removeSomething(353);
		addText(354, "Spell", tda, 0, 0xff981f, false);
		addCacheSprite(337, 19, 0, "combaticons");
		addCacheSprite(338, 13, 0, "combaticons2");
		addCacheSprite(339, 14, 0, "combaticons2");
		removeSomething(349);
		addToggleButton(350, 350, 108, 68, 44, "Select");
		rsi.width = 190;
		rsi.height = 261;
		int last = 15;
		int frame = 0;
		rsi.totalChildren(last, last, last);
		rsi.child(frame, id2 + 3, 20, 115);
		frame++;
		rsi.child(frame, id2 + 4, 20, 80);
		frame++;
		rsi.child(frame, id2 + 5, 20, 45);
		frame++;
		rsi.child(frame, id2 + 6, img1x, img1y);
		frame++;
		rsi.child(frame, id2 + 7, img2x, img2y);
		frame++;
		rsi.child(frame, id2 + 8, img3x, img3y);
		frame++;
		rsi.child(frame, id2 + 9, str1x, str1y);
		frame++;
		rsi.child(frame, id2 + 10, str2x, str2y);
		frame++;
		rsi.child(frame, id2 + 11, str3x, str3y);
		frame++;
		rsi.child(frame, 349, 105, 46);
		frame++;
		rsi.child(frame, 350, 104, 106);
		frame++;
		rsi.child(frame, 353, 125, 74);
		frame++;
		rsi.child(frame, 354, 125, 134);
		frame++;
		rsi.child(frame, 19300, 0, 0);
		frame++;
		rsi.child(frame, id2, 94, 4);
		frame++;
	}

	public static void emoteTab() {
		RSInterface tab = addTabInterface(147);
		RSInterface scroll = addTabInterface(148);
		tab.totalChildren(1);
		tab.child(0, 148, 0, 1);
		int ButtonIDs[] = { 168, 169, 164, 165, 162, 163, 13370, 171, 167, 170,
				13366, 13368, 166, 13363, 13364, 13365, 161, 11100, 13362,
				13367, 172, 13369, 13383, 13384, 667, 6503, 6506, 666, 18464,
				18465, 15166, 18686, 18689, 18688, 18691, 18692, 18687, 154,
				22586, 22587, 22588, 22589, 22590, 22591, 22592, 22593, 22594 };
		int EmoteX[] = { 10, 54, 98, 137, 9, 48, 95, 137, 7, 51, 95, 139, 6,
				50, 90, 135, 8, 51, 99, 137, 10, 53, 88, 138, 3, 52, 96, 141,
				5, 53, 88, 142, 10, 51, 139, 88, 12, 49, 97, 140, 10, 50, 90,
				140, 10, 60, 90 };
		int EmoteY[] = { 6, 6, 13, 6, 55, 55, 55, 55, 104, 104, 103, 104, 153,
				153, 153, 153, 203, 202, 203, 202, 250, 250, 255, 249, 300,
				299, 299, 299, 349, 350, 352, 350, 401, 402, 406, 402, 452,
				450, 452, 452, 505, 505, 505, 505, 560, 560, 560 };
		int EmoteIDs[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
				15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 33, 34, 35,
				36, 28, 29, 30, 37, 31, 32, 38, 39, 40, 41, 42, 43, 44, 45, 46 };
		String EmoteTooltip[] = { "Yes", "No", "Bow", "Angry", "Think", "Wave",
				"Shrug", "Cheer", "Beckon", "Laugh", "Jump for Joy", "Yawn",
				"Dance", "Jig", "Spin", "Headbang", "Cry", "Blow Kiss",
				"Panic", "Raspberry", "Clap", "Salute", "Goblin Bow",
				"Goblin Salute", "Glass Box", "Climb Rope", "Lean on Air",
				"Glass Wall", "Idea", "Stomp", "Flap", "Slap Head",
				"Zombie Walk", "Zombie Dance", "Scared", "Zombie Hand",
				"Bunny Hop", "Skill Cape", "Snowman Dance", "Air Guitar",
				"Safety First", "Explore", "Trick", "Freeze & Melt",
				"Give Thanks", "Around The World In Eggty Days",
				"Dramatic Point" };
		scroll.totalChildren(ButtonIDs.length);
		for (int index = 0; index < ButtonIDs.length; index++)
			addButton(ButtonIDs[index], EmoteIDs[index],
					"Interfaces/Emotes/EMOTE", EmoteTooltip[index]);

		for (int index = 0; index < ButtonIDs.length; index++)
			scroll.child(index, ButtonIDs[index], EmoteX[index], EmoteY[index]);

		scroll.width = 173;
		scroll.height = 260;
		scroll.scrollMax = 610;
	}
	public static void optionTab(TextDrawingArea tda[]) {
		RSInterface Interface = addTabInterface(904);
		setChildren(46, Interface);
		addSprite(25801, 0, "Interfaces/OptionTab/OPTION");
		addSprite(25802, 1, "Interfaces/OptionTab/OPTION");
		addSprite(25803, 1, "Interfaces/OptionTab/OPTION");
		addSprite(25804, 1, "Interfaces/OptionTab/OPTION");
		setBounds(25801, 49, 17, 0, Interface);
		setBounds(25802, 49, 54, 1, Interface);
		setBounds(25803, 49, 90, 2, Interface);
		setBounds(25804, 49, 127, 3, Interface);
		addButton(25805, 5, -1, 2, 2, "Interfaces/OptionTab/OPTION", 32, 32,
				"Adjust Brightness", 166, 1);
		setBounds(25805, 9, 8, 4, Interface);
		addButton(25806, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 166, 1);
		addButton(25807, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 166, 2);
		addButton(25808, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 166, 3);
		addButton(25809, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 166, 4);
		setBounds(25806, 57, 16, 5, Interface);
		setBounds(25807, 88, 16, 6, Interface);
		setBounds(25808, 119, 16, 7, Interface);
		setBounds(25809, 153, 16, 8, Interface);
		addButton(25810, 5, -1, 3, 4, "Interfaces/OptionTab/OPTION", 32, 32,
				"Adjust Music Level", 168, 4);
		setBounds(25810, 9, 45, 9, Interface);
		addButton(25811, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 168, 4);
		addButton(25812, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 168, 3);
		addButton(25813, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 168, 2);
		addButton(25814, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 168, 1);
		addButton(25815, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 168, 0);
		setBounds(25811, 54, 53, 10, Interface);
		setBounds(25812, 78, 53, 11, Interface);
		setBounds(25813, 105, 53, 12, Interface);
		setBounds(25814, 131, 53, 13, Interface);
		setBounds(25815, 156, 53, 14, Interface);
		addButton(25816, 5, -1, 5, 6, "Interfaces/OptionTab/OPTION", 32, 32,
				"Adjust Sounds", 169, 4);
		setBounds(25816, 9, 81, 15, Interface);
		addButton(25817, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 169, 4);
		addButton(25818, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 169, 3);
		addButton(25819, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 169, 2);
		addButton(25820, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 169, 1);
		addButton(25821, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 169, 0);
		setBounds(25817, 54, 89, 16, Interface);
		setBounds(25818, 78, 89, 17, Interface);
		setBounds(25819, 105, 89, 18, Interface);
		setBounds(25820, 131, 89, 19, Interface);
		setBounds(25821, 156, 89, 20, Interface);
		addButton(25822, 5, -1, 7, 8, "Interfaces/OptionTab/OPTION", 32, 32,
				"Adjust Sound Effects", 400, 0);
		setBounds(25822, 10, 119, 21, Interface);
		addButton(25823, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 400, 0);
		addButton(25824, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 400, 1);
		addButton(25825, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 400, 2);
		addButton(25826, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 400, 3);
		addButton(25827, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16,
				"Select", 400, 4);
		setBounds(25823, 54, 126, 22, Interface);
		setBounds(25824, 78, 126, 23, Interface);
		setBounds(25825, 105, 126, 24, Interface);
		setBounds(25826, 131, 126, 25, Interface);
		setBounds(25827, 156, 126, 26, Interface);
		addButton(25828, 4, 25829, 9, 10, "Interfaces/OptionTab/OPTION", 40,
				40, "Toggle Mouse Buttons", 170, 1);
		addTooltip(25829, "Toggle Mouse Buttons");
		addButton(25831, 4, 25832, 9, 10, "Interfaces/OptionTab/OPTION", 40,
				40, "Toggle Chat Effects", 171, 1);
		addTooltip(25832, "Toggle Chat Effects");
		addButton(25834, 4, 25835, 9, 10, "Interfaces/OptionTab/OPTION", 40,
				40, "Toggle Split-Level Chat", 287, 1);
		addTooltip(25835, "Toggle Split-Level Chat");
		addButton(25837, 4, 25838, 9, 10, "Interfaces/OptionTab/OPTION", 40,
				40, "Toggle Accept Aid", 427, 0);
		addTooltip(25838, "Toggle Accept Aid");
		addButton(152, 4, 25841, 9, 10, "Interfaces/OptionTab/OPTION", 40, 40,
				"Toggle Run Mode", 173, 1);
		addTooltip(25841, "Toggle Run-Mode");
		addButton(25842, 4, 25843, 9, 10, "Interfaces/OptionTab/OPTION", 40,
				40, "More Options", 175, 1);
		addTooltip(25843, "More client options,\\nincluding fullscreen");
		setBounds(25828, 19, 152, 27, Interface);
		setBounds(25831, 75, 152, 28, Interface);
		setBounds(25834, 131, 152, 29, Interface);
		setBounds(25837, 19, 206, 30, Interface);
		setBounds(152, 75, 206, 31, Interface);
		setBounds(25857, 78, 159, 32, Interface);
		setBounds(25858, 136, 158, 33, Interface);
		setBounds(25859, 23, 212, 34, Interface);
		setBounds(25860, 86, 210, 35, Interface);
		setBounds(25856, 23, 159, 36, Interface);
		setBounds(25829, 19, 130, 37, Interface);
		setBounds(25832, 78, 130, 38, Interface);
		setBounds(25835, 71, 130, 39, Interface);
		setBounds(25838, 19, 185, 40, Interface);
		setBounds(25841, 78, 185, 41, Interface);
		setBounds(25842, 131, 206, 42, Interface);
		setBounds(25861, 139, 217, 43, Interface);
		setBounds(25843, 137, 185, 44, Interface);
		addSprite(25856, 11, "Interfaces/OptionTab/OPTION");
		addSprite(25857, 12, "Interfaces/OptionTab/OPTION");
		addSprite(25858, 13, "Interfaces/OptionTab/OPTION");
		addSprite(25859, 14, "Interfaces/OptionTab/OPTION");
		addSprite(25860, 15, "Interfaces/OptionTab/OPTION");
		addSprite(25861, 12, "sideicon/sideicons");
		addText(149, "100%", 0xff9800, true, true, 52, tda, 1);
		setBounds(149, 94, 230, 45, Interface);
	}

	private static void addButton(int ID, int type, int hoverID, int dS,
			int eS, String NAME, int W, int H, String text, int configFrame,
			int configId) {
		RSInterface rsinterface = addInterface(ID);
		rsinterface.id = ID;
		rsinterface.parentID = ID;
		rsinterface.type = 5;
		rsinterface.atActionType = type;
		rsinterface.opacity = 0;
		rsinterface.mOverInterToTrigger = hoverID;
		rsinterface.sprite1 = imageLoader(dS, NAME);
		rsinterface.sprite2 = imageLoader(eS, NAME);
		rsinterface.width = W;
		rsinterface.height = H;
		rsinterface.tooltip = text;
		rsinterface.isMouseoverTriggered = true;
		rsinterface.valueCompareType = new int[1];
		rsinterface.requiredValues = new int[1];
		rsinterface.valueCompareType[0] = 1;
		rsinterface.requiredValues[0] = configId;
		rsinterface.valueIndexArray = new int[1][3];
		rsinterface.valueIndexArray[0][0] = 5;
		rsinterface.valueIndexArray[0][1] = configFrame;
		rsinterface.valueIndexArray[0][2] = 0;
	}
	
		public static void clanChatTabs(TextDrawingArea[] tda) {
        RSInterface tab = addTabInterface(18128);
		addButton(18250, 0, "/Clan Chat/Lootshare", "Toggle lootshare");
        addHoverButton(18129, "/Clan Chat/SPRITE", 6, 29, 29, "Join Chat", 550, 18130, 1);
        addHoveredButton(18130, "/Clan Chat/SPRITE", 7, 29, 29, 18131);
        addHoverButton(18132, "/Clan Chat/SPRITE", 9, 29, 29, "Leave Chat", -1, 18133, 1);
        addHoveredButton(18133, "/Clan Chat/SPRITE", 10, 29, 29, 18134);
        addSprite(18137, 1, "/Clan Chat/SPRITE");
        addText(18139, "Talking in: Not in chat", tda, 0, 0xff9b00, false, true);
        addText(18140, "Owner: None", tda, 0, 0xff9b00, false, true);
        tab.totalChildren(11);
        tab.child(0, 16126, 0, 221);
        tab.child(1, 16126, 0, 59);
        tab.child(2, 18137, 0, 35);
        tab.child(3, 18143, 0, 37);
        tab.child(4, 18129, 5, 237);
        tab.child(5, 18130, 4, 237);
        tab.child(6, 18132, 25, 237);
        tab.child(7, 18133, 24, 237);
        tab.child(8, 18139, 64, 3);
        tab.child(9, 18140, 70, 18);
		tab.child(10, 18250, 155, 233);
        /* Text area */
        RSInterface list = addTabInterface(18143);
        list.totalChildren(100);
        for(int i = 18144; i <= 18244; i++) {
            addText(i, "", tda, 0, 0xffffff, false, true);
        }
        for(int id = 18144, i = 0; id <= 18243 && i <= 99; id++, i++) {
            list.children[i] = id; list.childX[i] = 5;
            for(int id2 = 18144, i2 = 1; id2 <= 18243 && i2<= 99; id2++, i2++) {
                list.childY[0] = 2;
                list.childY[i2] = list.childY[i2 - 1] + 14;
            }
        }
        list.height = 191; list.width = 175;
        list.scrollMax = 1405;
    }



		public Sprite disabledHover;
	public Sprite enabledHover;

	public static void addText(int i, String s, int k, boolean l, boolean m,
			int a, TextDrawingArea TDA[], int j) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.parentID = i;
		RSInterface.id = i;
		RSInterface.type = 4;
		RSInterface.atActionType = 0;
		RSInterface.width = 0;
		RSInterface.height = 0;
		RSInterface.contentType = 0;
		RSInterface.mOverInterToTrigger = a;
		RSInterface.centerText = l;
		RSInterface.textShadow = m;
		RSInterface.textDrawingAreas = TDA[j];
		RSInterface.message = s;
		RSInterface.disabledText = "";
		RSInterface.textColor = k;
	}
	
		public static void SettingsTab(TextDrawingArea[] tda) {
		RSInterface tab = addTabInterface(3333);
        addSprite(37001, 1, "Switcher/Background");
		addButton(37046, 1, "Switcher/Top", 63, 24, "Settings Menu", 1);
		addText(37050, "Fullscreen", tda, 0, 0xFFFFFF, false, true);
		addText(37051, "Change Appearance", tda, 0, 0xFFFFFF, false, true);
		addText(37052, "Old Style HP Bar", tda, 0, 0xFFFFFF, false, true);
		addText(37053, "Coming Soon", tda, 0, 0xFFFFFF, false, true);
		addText(37054, "Coming Soon", tda, 0, 0xFFFFFF, false, true);
		addText(37055, "Coming Soon", tda, 0, 0xFFFFFF, false, true);
		addText(37056, "Coming Soon", tda, 0, 0xFFFFFF, false, true);
		addText(37057, "GameFrame", tda, 0, 0xFFFFFF, false, true);
		addText(37058, "", tda, 0, 0xFFFFFF, false, true);
		addText(37059, "", tda, 0, 0x67E300, false, true);
		addText(37060, "", tda, 0, 0x67E300, false, true);
		addText(37061, "", tda, 0, 0xF30021, false, true);
		addText(37062, "", tda, 0, 0x67E300, false, true);
		addText(37063, "", tda, 0, 0xF30021, false, true);
		addText(37064, "", tda, 0, 0xF30021, false, true);
		addText(37065, "", tda, 0, 0x67E300, false, true);
		addButton(37002, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37003, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37004, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37005, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37006, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37007, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37008, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addButton(37009, 1, "Switcher/TextBox", 63, 24, "Toggle On/Off", 1);
		addHoverButton(37020, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37021, 1);
		addHoveredButton(37021, "Switcher/SmallButton", 1, 200, 30, 37022);
		addHoverButton(37023, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37024, 1);
		addHoveredButton(37024, "Switcher/SmallButton", 1, 200, 30, 37025);
		addHoverButton(37026, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37027, 1);
		addHoveredButton(37027, "Switcher/SmallButton", 1, 200, 30, 37028);
		addHoverButton(37029, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37030, 1);
		addHoveredButton(37030, "Switcher/SmallButton", 1, 200, 30, 37031);
		addHoverButton(37033, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37034, 1);
		addHoveredButton(37034, "Switcher/SmallButton", 1, 200, 30, 37035);
		addHoverButton(37036, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37037, 1);
		addHoveredButton(37037, "Switcher/SmallButton", 1, 200, 30, 37038);
		addHoverButton(37039, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37040, 1);
		addHoveredButton(37040, "Switcher/SmallButton", 1, 200, 30, 37042);
		addHoverButton(37043, "Switcher/SmallButton", 0, 200, 30, "Toggle On/Off", -1, 37044, 1);
		addHoveredButton(37044, "Switcher/SmallButton", 1, 200, 30, 37045);
       	addHoverButton(37047, "Interfaces/Minigame/Back", 0, 16, 16, "Back", -1, 37048, 1);
       	addHoveredButton(37048, "Interfaces/Minigame/Back", 1, 16, 16, 37049);
		tab.totalChildren(44);
		/*BUTTONS*/
       		tab.child(0, 37001, 5, 0);
       		tab.child(1, 37002, 5, 20);
       		tab.child(2, 37003, 5, 50);
       		tab.child(3, 37004, 5, 80);
       		tab.child(4, 37005, 5, 110);
       		tab.child(5, 37006, 5, 140);
       		tab.child(6, 37007, 5, 170);
       		tab.child(7, 37008, 5, 200);
       		tab.child(8, 37009, 5, 230);
		/*HOVER/HOVERED BUTTONS*/
		tab.child(9, 37020, 150, 20);
		tab.child(10, 37021, 150, 20);
		tab.child(11, 37023, 150, 50);
		tab.child(12, 37024, 150, 50);
		tab.child(13, 37026, 150, 80);
		tab.child(14, 37027, 150, 80);
		tab.child(15, 37029, 150, 110);
		tab.child(16, 37030, 150, 110);
		tab.child(17, 37033, 150, 140);
		tab.child(18, 37034, 150, 140);
		tab.child(19, 37036, 150, 170);
		tab.child(20, 37037, 150, 170);
		tab.child(21, 37039, 150, 200);
		tab.child(22, 37040, 150, 200);
		tab.child(23, 37043, 150, 230);
		tab.child(24, 37044, 150, 230);
		/*TOP SPRITE*/
		tab.child(25, 37046, 35, 0);
		tab.child(26, 37047, 0, 0);
		tab.child(27, 37048, 0, 0);
		/*TEXT*/
		tab.child(43, 37050, 8, 23);
		tab.child(28, 37051, 8, 53);
		tab.child(29, 37052, 8, 83);
		tab.child(30, 37053, 8, 113);
		tab.child(31, 37054, 8, 143);
		tab.child(32, 37055, 8, 173);
		tab.child(33, 37056, 8, 203);
		tab.child(34, 37057, 8, 233);
		//dddd
		tab.child(35, 37058, 159, 23);
		tab.child(36, 37059, 159, 53);
		tab.child(37, 37060, 159, 83);
		tab.child(38, 37061, 159, 113);
		tab.child(39, 37062, 159, 143);
		tab.child(40, 37063, 159, 173);
		tab.child(41, 37064, 159, 203);
		tab.child(42, 37065, 159, 233);


	}
	
			public static void monsterTele(TextDrawingArea[] TDA) {
	RSInterface Interface = addTabInterface(34000); //Interface ID
	//Background here
	addSprite(34001, 1, "Interfaces/monsterTele/BACKGROUND");
	addText(34002, "Monster Teleports", TDA, 2, 0xd67b29, true, true);
	addHoverButton(34003, "Interfaces/monsterTele/HOVER", 1, 124, 26, "Teleport to @gre@Rock Crabs", -1, 34004, 1);
	addHoveredButton(34004, "Interfaces/monsterTele/SHADE", 2, 124, 26, 34005);
	addHoverButton(34006, "Interfaces/monsterTele/HOVER", 1, 124, 26, "Teleport to @gre@Taverly Dungeon", -1, 34007, 1);
	addHoveredButton(34007, "Interfaces/monsterTele/SHADE", 2, 124, 26, 34008);
	addHoverButton(34009, "Interfaces/monsterTele/HOVER", 1, 124, 26, "Teleport to @gre@Slayer Tower", -1, 34010, 1);
	addHoveredButton(34010, "Interfaces/monsterTele/SHADE", 2, 124, 26, 34011);
	addHoverButton(34012, "Interfaces/monsterTele/HOVER", 1, 124, 26, "Teleport to @gre@Rock Crabs", -1, 34013, 1);
	addHoveredButton(34013, "Interfaces/monsterTele/SHADE", 2, 124, 26, 34014);
	addText(34015, "Rock Crabs", TDA, 0, 0xFFFFFF, true, true); //0xd67b29
	addText(34016, "Taverly Dungeon", TDA, 0, 0xFFFFFF, true, true); //0xd67b29
	addText(34017, "Slayer Tower", TDA, 0, 0xFFFFFF, true, true); //0xd67b29
	addText(34018, "Brimhaven Dungeon", TDA, 0, 0xFFFFFF, true, true); //0xd67b29
	addHoverButton(34019, "Interfaces/monsterTele/shadedMageBook", 1, 43, 35, "Back To Your Spell Book", -1, 34020, 1);
	addHoveredButton(34020, "Interfaces/monsterTele/hoveredMageBook", 2, 43, 35, 34021);
	addHoverButton(34022, "Interfaces/monsterTele/shadedArrow", 1, 43, 35, "Next Page", -1, 34023, 1);
	addHoveredButton(34023, "Interfaces/monsterTele/HoveredArrow", 2, 43, 35, 34024);
	
	setChildren(18, Interface); //Number of sprites/buttons
	setBounds(34001, 0, 0, 0, Interface);
	setBounds(34002, 95, 7, 1, Interface);
	setBounds(34003, 25, 36, 2, Interface);
	setBounds(34004, 25, 36, 3, Interface);
	setBounds(34006, 25, 86, 4, Interface);
	setBounds(34007, 25, 86, 5, Interface);
	setBounds(34009, 25, 139, 6, Interface);
	setBounds(34010, 25, 139, 7, Interface);
	setBounds(34012, 25, 188, 8, Interface);
	setBounds(34013, 25, 188, 9, Interface);
	setBounds(34015, 87, 43, 10, Interface);
	setBounds(34016, 87, 93, 11, Interface);
	setBounds(34017, 87, 146, 12, Interface);
	setBounds(34018, 87, 195, 13, Interface);
	setBounds(34019, 0, 221, 14, Interface);
	setBounds(34020, 0, 221, 15, Interface);
	setBounds(34022, 147, 221, 16, Interface);
	setBounds(34023, 147, 221, 17, Interface);
	}
public static void monsterTeleb(TextDrawingArea[] TDA) {
	RSInterface Interface = addTabInterface(39000); //Interface ID
	//Background here
	addSprite(39001, 1, "Interfaces/monsterTeleb/BACKGROUND");
	//Title
	addText(39002, "Monster Teleports", TDA, 2, 0xd67b29, true, true);
	//Tele Buttons
	addHoverButton(39003, "Interfaces/monsterTeleb/HOVER", 1, 124, 26, "Teleport to @gre@Experiments", -1, 39004, 1);
	addHoveredButton(39004, "Interfaces/monsterTeleb/SHADE", 2, 124, 26, 39005);
	addHoverButton(39006, "Interfaces/monsterTeleb/HOVER", 1, 124, 26, "Teleport to @gre@Strykewyrms", -1, 39007, 1);
	addHoveredButton(39007, "Interfaces/monsterTeleb/SHADE", 2, 124, 26, 39008);
	//Tele Button Text
	addText(39009, "Experiments", TDA, 0, 0xFFFFFF, true, true); //0xd67b29
	addText(39010, "Strykewyrms", TDA, 0, 0xFFFFFF, true, true); //0xd67b29
	//Bottom Buttons
	addHoverButton(39011, "Interfaces/monsterTeleb/shadedMageBook", 1, 43, 35, "Back To Your Spell Book", -1, 39012, 1);
	addHoveredButton(39012, "Interfaces/monsterTeleb/hoveredMageBook", 2, 43, 35, 39013);
	addHoverButton(39014, "Interfaces/monsterTeleb/shadedArrow", 1, 43, 35, "Next Page", -1, 39015, 1);
	addHoveredButton(39015, "Interfaces/monsterTeleb/HoveredArrow", 2, 43, 35, 39016);
	
	setChildren(12, Interface); //Number of sprites/buttons
	setBounds(39001, 0, 0, 0, Interface);
	setBounds(39002, 95, 7, 1, Interface);
	setBounds(39003, 25, 36, 2, Interface);
	setBounds(39004, 25, 36, 3, Interface);
	setBounds(39006, 25, 86, 4, Interface);
	setBounds(39007, 25, 86, 5, Interface);
	setBounds(39009, 87, 43, 6, Interface);
	setBounds(39010, 87, 93, 7, Interface);
	setBounds(39011, 147, 221, 8, Interface);
	setBounds(39012, 147, 221, 9, Interface);
	setBounds(39014, 0, 221, 10, Interface);
	setBounds(39015, 0, 221, 11, Interface);
	}
	
	
    public static void Pestpanel(TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addInterface(21005);
        addText(21006, "Next Departure:", 0xCCCBCB, false, true, 52, atextdrawingarea, 1);
        addText(21007, "Players Ready:", 0x5BD230, false, true, 52, atextdrawingarea, 1);
        addText(21008, "(Need 5 to 25 players)", 0xDED36A, false, true, 52, atextdrawingarea, 1);
        addText(21009, "Pest Points:", 0x99FFFF, false, true, 52, atextdrawingarea, 1);
        byte byte0 = 4;
        rsinterface.children = new int[byte0];
        rsinterface.childX = new int[byte0];
        rsinterface.childY = new int[byte0];
        setBounds(21006, 15, 12, 0, rsinterface);
        setBounds(21007, 15, 30, 1, rsinterface);
        setBounds(21008, 15, 48, 2, rsinterface);
        setBounds(21009, 15, 66, 3, rsinterface);
    }

    public static void Pestpanel2(TextDrawingArea atextdrawingarea[])
    {
        RSInterface rsinterface = addInterface(21100);
        addSprite(21101, 0, "Pest Control/PEST1");
        addSprite(21102, 1, "Pest Control/PEST1");
        addSprite(21103, 2, "Pest Control/PEST1");
        addSprite(21104, 3, "Pest Control/PEST1");
        addSprite(21105, 4, "Pest Control/PEST1");
        addSprite(21106, 5, "Pest Control/PEST1");
        addText(21107, "W", 0xcc00cc, false, true, 52, atextdrawingarea, 1);
        addText(21108, "E", 0x0000FF, false, true, 52, atextdrawingarea, 1);
        addText(21109, "SE", 0xffff44, false, true, 52, atextdrawingarea, 1);
        addText(21110, "SW", 0xcc0000, false, true, 52, atextdrawingarea, 1);
        addText(21111, "250", 0x99ff33, false, true, 52, atextdrawingarea, 1);
        addText(21112, "250", 0x99ff33, false, true, 52, atextdrawingarea, 1);
        addText(21113, "250", 0x99ff33, false, true, 52, atextdrawingarea, 1);
        addText(21114, "250", 0x99ff33, false, true, 52, atextdrawingarea, 1);
        addText(21115, "***", 0x99ff33, false, true, 52, atextdrawingarea, 1);
        addText(21116, "***", 0x99ff33, false, true, 52, atextdrawingarea, 1);
        addText(21117, "Time Remaining:", 0xffffff, false, true, 52, atextdrawingarea, 0);
        addText(21118, "10 Minutes", 0xffffff, false, true, 52, atextdrawingarea, 0);
        byte byte0 = 18;
        rsinterface.children = new int[byte0];
        rsinterface.childX = new int[byte0];
        rsinterface.childY = new int[byte0];
        setBounds(21101, 361, 26, 0, rsinterface);
        setBounds(21102, 396, 26, 1, rsinterface);
        setBounds(21103, 436, 26, 2, rsinterface);
        setBounds(21104, 474, 26, 3, rsinterface);
        setBounds(21105, 3, 21, 4, rsinterface);
        setBounds(21106, 3, 50, 5, rsinterface);
        setBounds(21107, 371, 60, 6, rsinterface);
        setBounds(21108, 409, 60, 7, rsinterface);
        setBounds(21109, 443, 60, 8, rsinterface);
        setBounds(21110, 479, 60, 9, rsinterface);
        setBounds(21111, 362, 10, 10, rsinterface);
        setBounds(21112, 398, 10, 11, rsinterface);
        setBounds(21113, 436, 10, 12, rsinterface);
        setBounds(21114, 475, 10, 13, rsinterface);
        setBounds(21115, 32, 32, 14, rsinterface);
        setBounds(21116, 32, 62, 15, rsinterface);
        setBounds(21117, 8, 88, 16, rsinterface);
        setBounds(21118, 87, 88, 17, rsinterface);
    }
	
		/* Pest Control Interface */
	public static void PestControl(TextDrawingArea[] wid) {
		/* Experience Tab */
		RSInterface tab = addTabInterface(18700);
		addSprite(18702, 0, "Interfaces/PestControl/PC");
		addButton(18703, 2, "Interfaces/PestControl/B", 46, 14, "Select", 1);
		addButton(18704, 2, "Interfaces/PestControl/B", 46, 14, "Select", 1);
		addButton(18705, 2, "Interfaces/PestControl/B", 46, 14, "Select", 1);
		addButton(18706, 0, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18707, 0, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18708, 1, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18709, 1, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18710, 2, "Interfaces/PestControl/B", 46, 14, "Select", 1);
		addButton(18711, 2, "Interfaces/PestControl/B", 46, 14, "Select", 1);
		addButton(18712, 0, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18713, 0, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18714, 0, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18715, 0, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18716, 0, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18717, 0, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18718, 0, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18719, 0, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18720, 1, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18721, 1, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18722, 1, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18723, 1, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18724, 1, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18725, 1, "Interfaces/PestControl/B", 48, 14, "Select", 1);
		addButton(18726, 2, "Interfaces/PestControl/B", 46, 14, "Select", 1);
		addButton(18727, 2, "Interfaces/PestControl/B", 46, 14, "Select", 1);
		addButton(18728, 1, "Interfaces/PestControl/SPRITE", 16, 16, "Close Window", 1);
		addText(18729, "10", wid, 0, 0xFFA500, false, true);
		addButton(18774, 0, "Interfaces/PestControl/X", 102, 22, "Equipment", 1);
		addButton(18775, 0, "Interfaces/PestControl/X", 102, 22, "Consumables", 1);
		//addButton(18776, 0, "Interfaces/PestControl/X", 102, 22, "extra", 1);
		tab.totalChildren(31);
		tab.child(0, 18701, 4, 16);
		tab.child(1, 18702, 4, 16);
		tab.child(2, 18703, 280, 127);
		tab.child(3, 18704, 280, 201);
		tab.child(4, 18705, 280, 274);
		tab.child(5, 18706, 338, 127);
		tab.child(6, 18707, 184, 127);
		tab.child(7, 18708, 386, 127);
		tab.child(8, 18709, 386, 201);
		tab.child(9, 18710, 434, 127);
		tab.child(10, 18711, 434, 201);
		tab.child(11, 18712, 30, 201);
		tab.child(12, 18713, 184, 127);
		tab.child(13, 18714, 338, 201);
		tab.child(14, 18715, 184, 127);
		tab.child(15, 18716, 30, 127);
		tab.child(16, 18717, 184, 127);
		tab.child(17, 18718, 184, 201);
		tab.child(18, 18719, 184, 274);
		tab.child(19, 18720, 78, 127);
		tab.child(20, 18721, 232, 127);
		tab.child(21, 18722, 78, 201);
		tab.child(22, 18723, 232, 127);
		tab.child(23, 18724, 232, 201);
		tab.child(24, 18725, 232, 274);
		tab.child(25, 18726, 126, 127);
		tab.child(26, 18727, 126, 201);
		tab.child(27, 18728, 480, 24);
		tab.child(28, 18729, 460, 288);
		tab.child(29, 18774, 124, 46);
		tab.child(30, 18775, 229, 46);
		//tab.child(31, 18776, 334, 46);

		/* Equipment Tab */
		RSInterface tab1 = addTabInterface(18730);
		addSprite(18732, 1, "Interfaces/PestControl/PC");
		addButton(18733, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18734, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18735, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18736, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18737, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18738, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18739, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18740, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18741, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18742, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18743, 5, "Interfaces/PestControl/B", 90, 56, "Select", 1);
		addButton(18728, 1, "Interfaces/PestControl/SPRITE", 16, 16, "Close Window", 1);
		addText(18729, "10", wid, 0, 0xFFA500, false, true);
		addButton(18773, 0, "Interfaces/PestControl/X", 102, 22, "Experience", 1);
		addButton(18775, 0, "Interfaces/PestControl/X", 102, 22, "Consumables", 1);
	//	addButton(18776, 0, "Interfaces/PestControl/X", 102, 22, "extra", 1);
		tab1.totalChildren(17);
		tab1.child(0, 18731, 4, 16);
		tab1.child(1, 18732, 4, 16);
		tab1.child(2, 18733, 30, 127);
		tab1.child(3, 18734, 30, 201);
		tab1.child(4, 18735, 184, 127);
		tab1.child(5, 18736, 338, 127);
		tab1.child(6, 18737, 184, 201);
		tab1.child(7, 18738, 338, 127);
		tab1.child(8, 18739, 338, 201);
		tab1.child(9, 18740, 184, 274);
		tab1.child(10, 18741, 338, 127);
		tab1.child(11, 18742, 338, 201);
		tab1.child(12, 18743, 56, 231);
		tab1.child(13, 18728, 480, 24);
		tab1.child(14, 18729, 460, 288);
		tab1.child(15, 18773, 19, 46);
		tab1.child(16, 18775, 229, 46);
		//tab1.child(17, 18776, 334, 46);

		/* Equipment Tab Void */
		RSInterface tab2 = addTabInterface(18746);
		addSprite(18747, 2, "Interfaces/PestControl/PC");
		addButton(18748, 6, "Interfaces/PestControl/B", 90, 56, "Select", 1);
		addButton(18749, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18750, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18728, 1, "Interfaces/PestControl/SPRITE", 16, 16, "Close Window", 1);
		addText(18729, "10", wid, 0, 0xFFA500, false, true);
		addButton(18773, 0, "Interfaces/PestControl/X", 102, 22, "Experience", 1);
		addButton(18775, 0, "Interfaces/PestControl/X", 102, 22, "Consumables", 1);
	//	addButton(18776, 0, "Interfaces/PestControl/X", 102, 22, "extra", 1);
		tab2.totalChildren(8);
		tab2.child(0, 18747, 4, 16);
		tab2.child(1, 18748, 56, 231);
		tab2.child(2, 18749, 30, 127);
		tab2.child(3, 18750, 184, 127);
		tab2.child(4, 18728, 480, 24);
		tab2.child(5, 18729, 460, 288);
		tab2.child(6, 18773, 19, 46);
		tab2.child(7, 18775, 229, 46);
	//	tab2.child(8, 18776, 334, 46);

		/* Consumables Tab */
		RSInterface tab3 = addTabInterface(18753);
		addSprite(18754, 3, "Interfaces/PestControl/PC");
		addButton(18755, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18756, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18757, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18758, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18759, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18760, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18728, 1, "Interfaces/PestControl/SPRITE", 16, 16, "Close Window", 1);
		addText(18729, "10", wid, 0, 0xFFA500, false, true);
		addButton(18773, 0, "Interfaces/PestControl/X", 102, 22, "Experience", 1);
		addButton(18774, 0, "Interfaces/PestControl/X", 102, 22, "Equipment", 1);
	//	addButton(18776, 0, "Interfaces/PestControl/X", 102, 22, "extra", 1);
		tab3.totalChildren(11);
		tab3.child(0, 18754, 4, 16);
		tab3.child(1, 18755, 30, 127);
		tab3.child(2, 18756, 184, 127);
		tab3.child(3, 18757, 338, 127);
		tab3.child(4, 18758, 30, 201);
		tab3.child(5, 18759, 184, 201);
		tab3.child(6, 18760, 338, 201);
		tab3.child(7, 18728, 480, 24);
		tab3.child(8, 18729, 460, 288);
		tab3.child(9, 18773, 19, 46);
		tab3.child(10, 18774, 124, 46);
		//tab3.child(11, 18776, 334, 46);

		/* extra Tab */
		RSInterface tab4 = addTabInterface(18763);
		addSprite(18764, 4, "Interfaces/PestControl/PC");
		addButton(18765, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18766, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18767, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18768, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18769, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18770, 3, "Interfaces/PestControl/B", 142, 14, "Select", 1);
		addButton(18728, 1, "Interfaces/PestControl/SPRITE", 16, 16, "Close Window", 1);
		addText(18729, "10", wid, 0, 0xFFA500, false, true);
		addButton(18773, 0, "Interfaces/PestControl/X", 102, 22, "Experience", 1);
		addButton(18774, 0, "Interfaces/PestControl/X", 102, 22, "Equipment", 1);
		addButton(18775, 0, "Interfaces/PestControl/X", 102, 22, "Consumables", 1);
		tab4.totalChildren(12);
		tab4.child(0, 18764, 4, 16);
		tab4.child(1, 18765, 30, 127);
		tab4.child(2, 18766, 184, 127);
		tab4.child(3, 18767, 338, 127);
		tab4.child(4, 18768, 30, 201);
		tab4.child(5, 18769, 184, 201);
		tab4.child(6, 18770, 338, 201);
		tab4.child(7, 18728, 480, 24);
		tab4.child(8, 18729, 460, 288);
		tab4.child(9, 18773, 19, 46);
		tab4.child(10, 18774, 124, 46);
		tab4.child(11, 18775, 229, 46);
	}

	public static void addHoverBox(int id, String text) {
		RSInterface rsi = interfaceCache[id];
		rsi.id = id;
		rsi.parentID = id;
		rsi.isMouseoverTriggered = true;
		rsi.type = 8;
		rsi.hoverText = text;
	}

	public static void addText(int id, String text, TextDrawingArea tda[],
			int idx, int color, boolean center, boolean shadow) {
		RSInterface tab = addTabInterface(id);
		tab.parentID = id;
		tab.id = id;
		tab.type = 4;
		tab.atActionType = 0;
		tab.width = 0;
		tab.height = 11;
		tab.contentType = 0;
		tab.mOverInterToTrigger = -1;
		tab.centerText = center;
		tab.textShadow = shadow;
		tab.textDrawingAreas = tda[idx];
		tab.message = text;
		tab.disabledText = "";
		tab.textColor = color;
		tab.anInt219 = 0;
		tab.textHoverColour = 0;
		tab.anInt239 = 0;
	}

	public static void addButton(int id, int sid, String spriteName,
			String tooltip, int w, int h) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = w;
		tab.height = h;
		tab.tooltip = tooltip;
	}

	public static void addConfigButton(int ID, int pID, int bID, int bID2,
			String bName, int width, int height, String tT, int configID,
			int aT, int configFrame) {
		RSInterface Tab = addTabInterface(ID);
		Tab.parentID = pID;
		Tab.id = ID;
		Tab.type = 5;
		Tab.atActionType = aT;
		Tab.contentType = 0;
		Tab.width = width;
		Tab.height = height;
		Tab.opacity = 0;
		Tab.mOverInterToTrigger = -1;
		Tab.valueCompareType = new int[1];
		Tab.requiredValues = new int[1];
		Tab.valueCompareType[0] = 1;
		Tab.requiredValues[0] = configID;
		Tab.valueIndexArray = new int[1][3];
		Tab.valueIndexArray[0][0] = 5;
		Tab.valueIndexArray[0][1] = configFrame;
		Tab.valueIndexArray[0][2] = 0;
		Tab.sprite1 = imageLoader(bID, bName);
		Tab.sprite2 = imageLoader(bID2, bName);
		Tab.tooltip = tT;
	}

	public static void addSprite(int id, int spriteId, String spriteName) {
		addSprite(id, spriteId, spriteName, -1, -1);
	}

	public static void addSprite(int id, int spriteId, String spriteName,
			int zoom1, int zoom2) // summon pouch creation
	{
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.mOverInterToTrigger = 52;
		if (spriteName == null) {
			tab.itemSpriteZoom1 = zoom1;
			tab.itemSpriteId1 = spriteId;
			tab.itemSpriteZoom2 = zoom2;
			tab.itemSpriteId2 = spriteId;
		} else {
			tab.sprite1 = imageLoader(spriteId, spriteName);
			tab.sprite2 = imageLoader(spriteId, spriteName);
		}
		tab.width = 512;
		tab.height = 334;
	}

	public static void addHoverButton(int i, String imageName, int sId,
			int width, int height, String text, int contentType, int hoverOver,
			int aT) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = aT;
		tab.contentType = contentType;
		tab.opacity = 0;
		tab.mOverInterToTrigger = hoverOver;
		tab.sprite1 = imageLoader(sId, imageName);
		tab.sprite2 = imageLoader(sId, imageName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = text;
	}

	public static void addHoveredButton(int i, String imageName, int j, int w,
			int h, int IMAGEID) {
		RSInterface tab = addTabInterface(i);
		tab.parentID = i;
		tab.id = i;
		tab.type = 0;
		tab.atActionType = 0;
		tab.width = w;
		tab.height = h;
		tab.isMouseoverTriggered = true;
		tab.opacity = 0;
		tab.mOverInterToTrigger = -1;
		tab.scrollMax = 0;
		addHoverImage(IMAGEID, j, j, imageName);
		tab.totalChildren(1);
		tab.child(0, IMAGEID, 0, 0);
	}

	public static void addInAreaHover(int i, String imageName, int sId,
			int sId2, int w, int h, String text, int contentType, int actionType) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = actionType;
		tab.contentType = contentType;
		tab.opacity = 0;
		tab.mOverInterToTrigger = i;
		if (sId != -1)
			tab.sprite1 = imageLoader(sId, imageName);
		tab.sprite2 = imageLoader(sId2, imageName);
		tab.width = w;
		tab.height = h;
		tab.tooltip = text;
	}

	public static void addHover(int i, int aT, int contentType, int hoverOver,
			int sId, String imageName, int width, int height, String text) {
		addHoverButton(i, imageName, sId, width, height, text, contentType,
				hoverOver, aT);
	}

	public static void addHovered(int i, int j, String imageName, int w, int h,
			int IMAGEID) {
		addHoveredButton(i, imageName, j, w, h, IMAGEID);
	}

	public static void addHoverImage(int i, int j, int k, String name) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.opacity = 0;
		tab.mOverInterToTrigger = 52;
		tab.sprite1 = imageLoader(j, name);
		tab.sprite2 = imageLoader(k, name);
	}

	public static RSInterface addScreenInterface(int id) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 0;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.opacity = 0;
		tab.mOverInterToTrigger = -1;
		return tab;
	}

	public static RSInterface addTabInterface(int id) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 0;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 700;
		tab.opacity = 0;
		tab.mOverInterToTrigger = -1;
		return tab;
	}

	private static Sprite imageLoader(int i, String s) {
		long l = (TextClass.method585(s) << 8) + (long) i;
		Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
		if (sprite != null)
			return sprite;
		try {
			sprite = new Sprite((new StringBuilder()).append(s).append(" ")
					.append(i).toString());
			aMRUNodes_238.removeFromCache(sprite, l);
		} catch (Exception exception) {
			return null;
		}
		return sprite;
	}

	public void child(int id, int interID, int x, int y) {
		children[id] = interID;
		childX[id] = x;
		childY[id] = y;
	}

	public void totalChildren(int t) {
		children = new int[t];
		childX = new int[t];
		childY = new int[t];
	}

	private Model method206(int i, int j) {
		Model model = (Model) aMRUNodes_264.insertFromCache((i << 16) + j);
		if (model != null || i > 20)
			return model;
		if (i == 1)
			model = Model.method462(j);
		if (i == 2)
			model = EntityDef.forID(j).method160();
		if (i == 3)
			model = client.myPlayer.method453();
		if (i == 4)
			model = ItemDef.forID(j).method202(50);
		if (i == 5)
			model = null;
		if (model != null)
			aMRUNodes_264.removeFromCache(model, (i << 16) + j);
		return model;
	}

	private static Sprite method207(int i, StreamLoader streamLoader, String s) {
		long l = (TextClass.method585(s) << 8) + (long) i;
		Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
		if (sprite != null)
			return sprite;
		try {
			sprite = new Sprite(streamLoader, s, i);
			aMRUNodes_238.removeFromCache(sprite, l);
		} catch (Exception _ex) {
			return null;
		}
		return sprite;
	}

	public static void method208(boolean flag, Model model) {
		int i = 0;
		int j = 5;
		if (flag)
			return;
		aMRUNodes_264.unlinkAll();
		if (model != null && j != 4)
			aMRUNodes_264.removeFromCache(model, (j << 16) + i);
	}

	public Model method209(int j, int k, boolean flag) {
		Model model;
		if (flag)
			model = method206(anInt255, anInt256);
		else
			model = method206(anInt233, mediaID);
		if (model == null)
			return null;
		if (k == -1 && j == -1 && model.anIntArray1640 == null)
			return model;
		Model model_1 = new Model(true, Class36.method532(k)
				& Class36.method532(j), false, model);
		if (k != -1 || j != -1)
			model_1.method469();
		if (k != -1)
			model_1.method470(k);
		if (j != -1)
			model_1.method470(j);
		model_1.method479(64, 768, -50, -10, -50, true);
		return model_1;
	}

	public static void EquipmentTab(TextDrawingArea[] wid) {
		RSInterface Interface = interfaceCache[1644];
		addSprite(15101, 0, "Interfaces/Equipment/bl");// cheap hax
		addSprite(15102, 1, "Interfaces/Equipment/bl");// cheap hax
		addSprite(15109, 2, "Interfaces/Equipment/bl");// cheap hax
		removeSomething(15103);
		removeSomething(15104);
		Interface.children[23] = 15101;
		Interface.childX[23] = 40;
		Interface.childY[23] = 205;
		Interface.children[24] = 15102;
		Interface.childX[24] = 110;
		Interface.childY[24] = 205;
		Interface.children[25] = 15109;
		Interface.childX[25] = 39;
		Interface.childY[25] = 240;
		Interface.children[26] = 27650;
		Interface.childX[26] = 0;
		Interface.childY[26] = 0;
		Interface = addInterface(27650);
/*addSprite(19146, 1, "Equipment/aura");
addAura(10794);
addButton(29145, 2, "Equipment/aura", 36, 36, "Remove", 1);		*/
		addButton(27651, 6, "Interfaces/Equipment/BOX", "Lock/Unlock XP",
				27659, 1, 26, 33);
		addTooltip(27659, "Locks/unlocks your EXP"); // dw just finnish  i dont really care about that small thing ^^
		addButton(27653, 1, "Interfaces/Equipment/BOX", "Show Equipment Stats",
				27655, 1, 40, 39);
		addTooltip(27655, "Show Equipment Stats");
		addButton(27654, 2, "Interfaces/Equipment/BOX", "Items Kept On Death",
				27657, 1, 40, 39);
		addTooltip(27657, "Shows items kept on death.");
		setChildren(10, Interface);//
		setBounds(27651, 84, 215, 0, Interface);
		setBounds(27653, 29, 205, 1, Interface);
		setBounds(27654, 124, 205, 2, Interface);
		setBounds(27659, 39, 240, 3, Interface);
		setBounds(27655, 39, 240, 4, Interface);
		setBounds(27657, 39, 240, 5, Interface);
		setBounds(29145, 37, 5, 6, Interface);
		setBounds(19146, 37, 5, 7, Interface);
		setBounds(10794, 39, 7, 8, Interface);		
	}/*
	public static void EquipmentTab(TextDrawingArea[] wid) {
		RSInterface Interface = interfaceCache[1644];
		addSprite(15101, 0, "Equipment/bl");
		addSprite(15102, 1, "Equipment/bl");
		addSprite(15109, 2, "Equipment/bl");
		addSprite(15110, 3, "Equipment/bl");
		removeSomething(15103);	removeSomething(15104);
		Interface.children[23] = 15101;	Interface.childX[23] = 40;			Interface.childY[23] = 205;
		Interface.children[24] = 15102;	Interface.childX[24] = 110;			Interface.childY[24] = 205;
		Interface.children[25] = 15109;	Interface.childX[25] = 39;			Interface.childY[25] = 240;
		Interface.children[26] = 27650; Interface.childX[26] = 0;			Interface.childY[26] = 0;
		Interface = addInterface(27650);
		addSprite(27665, 0, "Equipment/aura");
addSprite(19146, 1, "Equipment/aura");
addAura(10794);
addButton(29145, 2, "Equipment/aura", 36, 36, "Remove", 1);		
		addHoverButton(27651, "Equipment/CUSTOM", 1, 40, 40, "Show Equipment Screen", 0, 27652, 1, 0);
		addHoveredButton(27652, "Equipment/CUSTOM", 5, 40, 40, 27653);	
		addHoverButton(27657, "Equipment/CUSTOM", 2, 40, 40, "Items Kept on Death", 0, 27658, 1, 0);
		addHoveredButton(27658, "Equipment/CUSTOM", 4, 40, 40, 27659);	
		addHoverButton(27654, "Equipment/CUSTOM", 9, 40, 40, "Toolbelt", 0, 27655, 1, 0);
		addHoveredButton(27655, "Equipment/CUSTOM", 10, 40, 40, 27656);		
		//addHoverButton(27662, "Equipment/CUSTOM", 3, 40, 45, "Price Checker", 0, 27663, 1, 0);
		//addHoveredButton(27663, "Equipment/CUSTOM", 6, 40, 45, 27664);
		setChildren(9, Interface);
		//setBounds(27665, 37, 4, 0, Interface);
		setBounds(27651, 21, 210, 0, Interface);
		setBounds(27652, 21, 210, 1, Interface);
		setBounds(27657, 75, 210, 2, Interface);
		setBounds(27658, 75, 210, 3, Interface);
		setBounds(27654, 129, 210, 4, Interface);
		setBounds(27655, 129, 210, 5, Interface);
        setBounds(29145, 37, 5, 6, Interface);
		setBounds(19146, 37, 5, 7, Interface);
		setBounds(10794, 39, 7, 8, Interface);		
		//setBounds(27662, 127, 2, 6, Interface);
		//setBounds(27663, 127, 2, 7, Interface);
		//setBounds(27665, 37, 4, 6, Interface);
	}	*/
	public static void addAura(int i)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
        rsinterface.itemActions = new String[5];
        rsinterface.spritesX = new int[20];
        rsinterface.invStackSizes = new int[30];
        rsinterface.inv = new int[30];
        rsinterface.spritesY = new int[20];
        rsinterface.children = new int[0];
        rsinterface.childX = new int[0];
        rsinterface.childY = new int[0];
        rsinterface.spritesY[0] = 0;
        rsinterface.invStackSizes[0] = 0;
        rsinterface.inv[0] = 0;
        rsinterface.spritesX[0] = 0;
        rsinterface.itemActions[0] = "Time left";
        rsinterface.itemActions[1] = "Activate";
        rsinterface.itemActions[2] = null;
        rsinterface.itemActions[3] = null;
        rsinterface.itemActions[4] = null;
        rsinterface.centerText = false;
        rsinterface.aBoolean227 = false;
        rsinterface.aBoolean235 = false;
        rsinterface.usableItemInterface = false;
        rsinterface.isInventoryInterface = false;
        rsinterface.aBoolean259 = true;
       // rsinterface.interfaceShown = false;
        rsinterface.textShadow = false;
        rsinterface.width = 6;
        rsinterface.type = -1;
        rsinterface.invSpritePadX = 24;
        rsinterface.parentID = 10794;
        rsinterface.invSpritePadY = 24;
        rsinterface.type = 2;
        rsinterface.height = 5;
    }
	public static void addHoverButton(int i, String imageName, int j, int width, int height, String text, int contentType, int hoverOver, int aT, int dummy) {//hoverable button
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.type = 11;
		tab.atActionType = aT;
		tab.contentType = contentType;
		tab.opacity = 0;
		tab.type = hoverOver;
		tab.sprite1 = imageLoader(j, imageName);
		tab.sprite2 = imageLoader(j, imageName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = text;
	}	

	public static void equipmentScreen(TextDrawingArea[] TDA) {
		RSInterface Interface = addInterface(19148);
		addSprite(19149, 0, "Interfaces/Equipment/CHAR");
		addHover(19150, 3, 0, 19151, 3, "Interfaces/Equipment/CHAR", 21, 21,
				"Close");
		addHovered(19151, 2, "Interfaces/Equipment/CHAR", 21, 21, 19152);
		addText(19154, "Equip Your Character...", 0xFF981F, false, true, 52,
				TDA, 2);
		addText(1673, "Attack bonus", 0xFF981F, false, true, 52, TDA, 2);
		addText(1674, "Defense bonus", 0xFF981F, false, true, 52, TDA, 2);
		addText(1685, "Other bonuses", 0xFF981F, false, true, 52, TDA, 2);
		addText(1675, "Stab:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1676, "Slash:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1677, "Crush:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1678, "Magic:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1679, "Ranged:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1680, "Stab:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1681, "Slash:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1682, "Crush:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1683, "Magic:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1684, "Ranged:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1686, "Strength:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1687, "Prayer:", 0xFF981F, false, true, 52, TDA, 1);
		addText(19155, "0%", 0xFF981F, false, true, 52, TDA, 1);
		addChar(19153);
		int last = 45;
		int frame = 0;
		setChildren(last, Interface);
		setBounds(19149, 12, 20, frame, Interface);
		frame++;
		setBounds(19150, 472, 27, frame, Interface);
		frame++;
		setBounds(19151, 472, 27, frame, Interface);
		frame++;
		setBounds(19153, 193, 190, frame, Interface);
		frame++;
		setBounds(19154, 23, 29, frame, Interface);
		frame++;
		setBounds(1673, 365, 71, frame, Interface);
		frame++;
		setBounds(1674, 365, 163, frame, Interface);
		frame++;
		setBounds(1675, 372, 85, frame, Interface);
		frame++;
		setBounds(1676, 372, 99, frame, Interface);
		frame++;
		setBounds(1677, 372, 113, frame, Interface);
		frame++;
		setBounds(1678, 372, 127, frame, Interface);
		frame++;
		setBounds(1679, 372, 141, frame, Interface);
		frame++;
		setBounds(1680, 372, 177, frame, Interface);
		frame++;
		setBounds(1681, 372, 191, frame, Interface);
		frame++;
		setBounds(1682, 372, 205, frame, Interface);
		frame++;
		setBounds(1683, 372, 219, frame, Interface);
		frame++;
		setBounds(1684, 372, 233, frame, Interface);
		frame++;
		setBounds(1685, 365, 253, frame, Interface);
		frame++;
		setBounds(1686, 372, 267, frame, Interface);
		frame++;
		setBounds(1687, 372, 281, frame, Interface);
		frame++;
		setBounds(19155, 94, 286, frame, Interface);
		frame++;
		setBounds(1645, 83, 106, frame, Interface);
		frame++;
		setBounds(1646, 83, 135, frame, Interface);
		frame++;
		setBounds(1647, 83, 172, frame, Interface);
		frame++;
		setBounds(1648, 83, 213, frame, Interface);
		frame++;
		setBounds(1649, 27, 185, frame, Interface);
		frame++;
		setBounds(1650, 27, 221, frame, Interface);
		frame++;
		setBounds(1651, 139, 185, frame, Interface);
		frame++;
		setBounds(1652, 139, 221, frame, Interface);
		frame++;
		setBounds(1653, 53, 148, frame, Interface);
		frame++;
		setBounds(1654, 112, 148, frame, Interface);
		frame++;
		setBounds(1655, 63, 109, frame, Interface);
		frame++;
		setBounds(1656, 117, 108, frame, Interface);
		frame++;
		setBounds(1657, 83, 71, frame, Interface);
		frame++;
		setBounds(1658, 42, 110, frame, Interface);
		frame++;
		setBounds(1659, 83, 110, frame, Interface);
		frame++;
		setBounds(1660, 124, 110, frame, Interface);
		frame++;
		setBounds(1661, 27, 149, frame, Interface);
		frame++;
		setBounds(1662, 83, 149, frame, Interface);
		frame++;
		setBounds(1663, 139, 149, frame, Interface);
		frame++;
		setBounds(1664, 83, 189, frame, Interface);
		frame++;
		setBounds(1665, 83, 229, frame, Interface);
		frame++;
		setBounds(1666, 27, 229, frame, Interface);
		frame++;
		setBounds(1667, 139, 229, frame, Interface);
		frame++;
		setBounds(1688, 29, 111, frame, Interface);
		frame++;
	}
	
	private static Sprite imageLoader(String s) {
		long l = (TextClass.method585(s) << 8);
		Sprite sprite = (Sprite) aMRUNodes_238.insertFromCache(l);
		if (sprite != null)
			return sprite;
		try {
			sprite = new Sprite(s);
			aMRUNodes_238.removeFromCache(sprite, l);
		} catch (Exception exception) {
			return null;
		}
		return sprite;
	}

	public static void addButton(int id, int sid, String spriteName,
			String tooltip, int mOver, int atAction, int width, int height) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.type = 5;
		tab.atActionType = atAction;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.mOverInterToTrigger = mOver;
		tab.sprite1 = imageLoader(sid, spriteName);
		tab.sprite2 = imageLoader(sid, spriteName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = tooltip;
		// tab.inventoryhover = true;
	}

	public static void addChar(int ID) {
		RSInterface t = interfaceCache[ID] = new RSInterface();
		t.id = ID;
		t.parentID = ID;
		t.type = 6;
		t.atActionType = 0;
		t.contentType = 328;
		t.width = 136;
		t.height = 168;
		t.opacity = 0;
		t.mOverInterToTrigger = 0;
		t.modelZoom = 560;
		t.modelRotation1 = 150;
		t.modelRotation2 = 0;
		t.anInt257 = -1;
		t.anInt258 = -1;
	}

	private static Sprite loadSprite(int i, String s) {
		Sprite sprite = null;
		try {
			sprite = new Sprite((new StringBuilder()).append(s).append(" ")
					.append(i).toString());
			if (sprite != null)
				return sprite;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sprite;
	}

	public static void drawRune(int i, int id, String runeName) {
		RSInterface RSInterface = addTabInterface(i);
		RSInterface.type = 5;
		RSInterface.atActionType = 0;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.mOverInterToTrigger = 52;
		RSInterface.sprite1 = loadSprite(id, "Interfaces/Lunar/RUNE");
		RSInterface.width = 500;
		RSInterface.height = 500;
	}

	public static void addRuneText(int ID, int runeAmount, int RuneID,
			TextDrawingArea font[]) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 4;
		rsInterface.atActionType = 0;
		rsInterface.contentType = 0;
		rsInterface.width = 0;
		rsInterface.height = 14;
		rsInterface.mOverInterToTrigger = -1;
		rsInterface.valueCompareType = new int[1];
		rsInterface.requiredValues = new int[1];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = runeAmount - 1;
		rsInterface.valueIndexArray = new int[1][4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = RuneID;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.centerText = true;
		rsInterface.textDrawingAreas = font[0];
		rsInterface.textShadow = true;
		rsInterface.message = (new StringBuilder()).append("%1/")
				.append(runeAmount).append("").toString();
		rsInterface.disabledText = "";
		rsInterface.textColor = 0xc00000;
		rsInterface.anInt219 = 49152;
	}

	public static void homeTeleport() {
		RSInterface RSInterface = addTabInterface(30000);
		RSInterface.tooltip = "Cast @gre@Lunar Home Teleport";
		RSInterface.id = 30000;
		RSInterface.parentID = 30000;
		RSInterface.type = 5;
		RSInterface.atActionType = 5;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.mOverInterToTrigger = 30001;
		RSInterface.sprite1 = loadSprite(1, "Interfaces/Lunar/SPRITE");
		RSInterface.width = 20;
		RSInterface.height = 20;
		RSInterface hover = addTabInterface(30001);
		hover.mOverInterToTrigger = -1;
		hover.isMouseoverTriggered = true;
		setChildren(1, hover);
		addSprite(30002, 0, "Interfaces/Lunar/SPRITE");
		setBounds(30002, 0, 0, 0, hover);
	}

	public static void addLunar2RunesSmallBox(int ID, int r1, int r2, int ra1,
			int ra2, int rune1, int lvl, String name, String descr,
			TextDrawingArea TDA[], int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast On";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = (new StringBuilder()).append("Cast @gre@")
				.append(name).toString();
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[3];
		rsInterface.requiredValues = new int[3];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = lvl;
		rsInterface.valueIndexArray = new int[3][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[3];
		rsInterface.valueIndexArray[2][0] = 1;
		rsInterface.valueIndexArray[2][1] = 6;
		rsInterface.valueIndexArray[2][2] = 0;
		rsInterface.sprite2 = loadSprite(sid, "Interfaces/Lunar/LUNARON");
		rsInterface.sprite1 = loadSprite(sid, "Interfaces/Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.mOverInterToTrigger = -1;
		hover.isMouseoverTriggered = true;
		setChildren(7, hover);
		addSprite(ID + 2, 0, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, (new StringBuilder()).append("Level ").append(lvl + 1)
				.append(": ").append(name).toString(), 0xff981f, true, true,
				52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xaf6a1a, true, true, 52, 0);
		setBounds(ID + 4, 90, 19, 2, hover);
		setBounds(30016, 37, 35, 3, hover);
		setBounds(rune1, 112, 35, 4, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 50, 66, 5, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 123, 66, 6, hover);
	}

	public static void addLunar3RunesSmallBox(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune1, int rune2, int lvl,
			String name, String descr, TextDrawingArea TDA[], int sid, int suo,
			int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = (new StringBuilder()).append("Cast @gre@")
				.append(name).toString();
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.sprite2 = loadSprite(sid, "Interfaces/Lunar/LUNARON");
		rsInterface.sprite1 = loadSprite(sid, "Interfaces/Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.mOverInterToTrigger = -1;
		hover.isMouseoverTriggered = true;
		setChildren(9, hover);
		addSprite(ID + 2, 0, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, (new StringBuilder()).append("Level ").append(lvl + 1)
				.append(": ").append(name).toString(), 0xff981f, true, true,
				52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xaf6a1a, true, true, 52, 0);
		setBounds(ID + 4, 90, 19, 2, hover);
		setBounds(30016, 14, 35, 3, hover);
		setBounds(rune1, 74, 35, 4, hover);
		setBounds(rune2, 130, 35, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 66, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 66, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 66, 8, hover);
	}

	public static void addLunar3RunesBigBox(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune1, int rune2, int lvl,
			String name, String descr, TextDrawingArea TDA[], int sid, int suo,
			int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = (new StringBuilder()).append("Cast @gre@")
				.append(name).toString();
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.sprite2 = loadSprite(sid, "Interfaces/Lunar/LUNARON");
		rsInterface.sprite1 = loadSprite(sid, "Interfaces/Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.mOverInterToTrigger = -1;
		hover.isMouseoverTriggered = true;
		setChildren(9, hover);
		addSprite(ID + 2, 1, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, (new StringBuilder()).append("Level ").append(lvl + 1)
				.append(": ").append(name).toString(), 0xff981f, true, true,
				52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xaf6a1a, true, true, 52, 0);
		setBounds(ID + 4, 90, 21, 2, hover);
		setBounds(30016, 14, 48, 3, hover);
		setBounds(rune1, 74, 48, 4, hover);
		setBounds(rune2, 130, 48, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 79, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 79, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 79, 8, hover);
	}

	public static void addLunar3RunesLargeBox(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune1, int rune2, int lvl,
			String name, String descr, TextDrawingArea TDA[], int sid, int suo,
			int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.type = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.mOverInterToTrigger = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = (new StringBuilder()).append("Cast @gre@")
				.append(name).toString();
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.sprite2 = loadSprite(sid, "Interfaces/Lunar/LUNARON");
		rsInterface.sprite1 = loadSprite(sid, "Interfaces/Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.isMouseoverTriggered = true;
		hover.mOverInterToTrigger = -1;
		setChildren(9, hover);
		addSprite(ID + 2, 2, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, (new StringBuilder()).append("Level ").append(lvl + 1)
				.append(": ").append(name).toString(), 0xff981f, true, true,
				52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xaf6a1a, true, true, 52, 0);
		setBounds(ID + 4, 90, 34, 2, hover);
		setBounds(30016, 14, 61, 3, hover);
		setBounds(rune1, 74, 61, 4, hover);
		setBounds(rune2, 130, 61, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 92, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 92, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 92, 8, hover);
	}

	public static void addBobStorage(int index) {
		RSInterface rsi = interfaceCache[index] = new RSInterface();
		rsi.itemActions = new String[5];
		rsi.spritesX = new int[20];
		rsi.invStackSizes = new int[30];
		rsi.inv = new int[30];
		rsi.spritesY = new int[20];

		rsi.children = new int[0];
		rsi.childX = new int[0];
		rsi.childY = new int[0];

		rsi.itemActions[0] = "Take 1";
		rsi.itemActions[1] = "Take 5";
		rsi.itemActions[2] = "Take 10";
		rsi.itemActions[3] = "Take All";
		rsi.centerText = false;
		rsi.aBoolean227 = false;
		rsi.aBoolean235 = false;
		rsi.usableItemInterface = false;
		rsi.isInventoryInterface = false;
		// rsi.aBoolean251 = false;
		rsi.aBoolean259 = true;
		// rsi.interfaceShown = false;
		rsi.textShadow = false;
		rsi.type = -1;
		rsi.invSpritePadX = 24;
		rsi.invSpritePadY = 24;
		rsi.height = 5;
		rsi.width = 6;
		rsi.parentID = 2702;
		rsi.id = 2700;
		rsi.type = 2;
	}

	public static void bobInterface(TextDrawingArea[] TDA) {
		RSInterface rsi = addTabInterface(2700);
		addSprite(2701, 20, "Interfaces/SummonTab/SUMMON");
		addBobStorage(2702);
		addHoverButton(2703, "Interfaces/Equipment/SPRITE", 1, 21, 21, "Close",
				250, 2704, 3);
		addHoveredButton(2704, "Interfaces/Equipment/SPRITE", 3, 21, 21, 2705);
		rsi.totalChildren(4);
		rsi.child(0, 2701, 90, 14);
		rsi.child(1, 2702, 100, 56);
		rsi.child(2, 2703, 431, 23);
		rsi.child(3, 2704, 431, 23);
	}

	public static void addText(int i, String s, int k, boolean l, boolean m,
			int a, int j) {
		RSInterface rsinterface = addTabInterface(i);
		rsinterface.parentID = i;
		rsinterface.id = i;
		rsinterface.type = 4;
		rsinterface.atActionType = 0;
		rsinterface.width = 0;
		rsinterface.height = 0;
		rsinterface.contentType = 0;
		rsinterface.mOverInterToTrigger = a;
		rsinterface.centerText = l;
		rsinterface.textShadow = m;
		rsinterface.textDrawingAreas = fonts[j];
		rsinterface.message = s;
		rsinterface.textColor = k;
	}

	public static void configureLunar(TextDrawingArea TDA[]) {
		homeTeleport();
		drawRune(30003, 1, "Fire");
		drawRune(30004, 2, "Water");
		drawRune(30005, 3, "Air");
		drawRune(30006, 4, "Earth");
		drawRune(30007, 5, "Mind");
		drawRune(30008, 6, "Body");
		drawRune(30009, 7, "Death");
		drawRune(30010, 8, "Nature");
		drawRune(30011, 9, "Chaos");
		drawRune(30012, 10, "Law");
		drawRune(30013, 11, "Cosmic");
		drawRune(30014, 12, "Blood");
		drawRune(30015, 13, "Soul");
		drawRune(30016, 14, "Astral");
		addLunar3RunesSmallBox(30017, 9075, 554, 555, 0, 4, 3, 30003, 30004,
				64, "Bake Pie", "Bake pies without a stove", TDA, 0, 16, 2);
		addLunar2RunesSmallBox(30025, 9075, 557, 0, 7, 30006, 65, "Cure Plant",
				"Cure disease on farming patch", TDA, 1, 4, 2);
		addLunar3RunesBigBox(30032, 9075, 564, 558, 0, 0, 0, 30013, 30007, 65,
				"Monster Examine",
				"Detect the combat statistics of a\\nmonster", TDA, 2, 2, 2);
		addLunar3RunesSmallBox(30040, 9075, 564, 556, 0, 0, 1, 30013, 30005,
				66, "NPC Contact", "Speak with varied NPCs", TDA, 3, 0, 2);
		addLunar3RunesSmallBox(30048, 9075, 563, 557, 0, 0, 9, 30012, 30006,
				67, "Cure Other", "Cure poisoned players", TDA, 4, 8, 2);
		addLunar3RunesSmallBox(30056, 9075, 555, 554, 0, 2, 0, 30004, 30003,
				67, "Humidify", "fills certain vessels with water", TDA, 5, 0,
				5);
		addLunar3RunesSmallBox(30064, 9075, 563, 557, 1, 0, 1, 30012, 30006,
				68, "Monster Teleport", "Monster Teleport", TDA, 6, 0, 5);
		addLunar3RunesBigBox(30075, 9075, 563, 557, 1, 0, 3, 30012, 30006, 69,
				"Minigames", "Barrows & Assault", TDA, 7, 0, 5);
		addLunar3RunesSmallBox(30083, 9075, 563, 557, 1, 0, 5, 30012, 30006,
				70, "Boss Teleport", "Boss Teleport", TDA, 8, 0, 5);
		addLunar3RunesSmallBox(30091, 9075, 564, 563, 1, 1, 0, 30013, 30012,
				70, "Cure Me", "Cures Poison", TDA, 9, 0, 5);
		addLunar2RunesSmallBox(30099, 9075, 557, 1, 1, 30006, 70, "Hunter Kit",
				"Get a kit of hunting gear", TDA, 10, 0, 5);
		addLunar3RunesSmallBox(30106, 9075, 563, 555, 1, 0, 0, 30012, 30004,
				71, "Pk Teleport", "Gives your several of options", TDA, 11, 0,
				5);
		addLunar3RunesBigBox(30114, 9075, 563, 555, 1, 0, 4, 30012, 30004, 72,
				"Skills Teleport", "Skills Teleport", TDA, 12, 0, 5);
		addLunar3RunesSmallBox(30122, 9075, 564, 563, 1, 1, 1, 30013, 30012,
				73, "Cure Group", "Cures Poison on players", TDA, 13, 0, 5);
		addLunar3RunesBigBox(30130, 9075, 564, 559, 1, 1, 4, 30013, 30008, 74,
				"Stat Spy",
				"Cast on another player to see their\\nskill levels", TDA, 14,
				8, 2);
		addLunar3RunesBigBox(30138, 9075, 563, 554, 1, 1, 2, 30012, 30003, 74,
				"Barbarian Teleport",
				"Teleports you to the Barbarian\\noutpost", TDA, 15, 0, 5);
		addLunar3RunesBigBox(30146, 9075, 563, 554, 1, 1, 5, 30012, 30003, 75,
				"Tele Group Barbarian",
				"Teleports players to the Barbarian\\noutpost", TDA, 16, 0, 5);
		addLunar3RunesSmallBox(30154, 9075, 554, 556, 1, 5, 9, 30003, 30005,
				76, "Superglass Make", "Make glass without a furnace", TDA, 17,
				16, 2);
		addLunar3RunesSmallBox(30162, 9075, 563, 555, 1, 1, 3, 30012, 30004,
				77, "Khazard Teleport", "Teleports you to Port khazard", TDA,
				18, 0, 5);
		addLunar3RunesSmallBox(30170, 9075, 563, 555, 1, 1, 7, 30012, 30004,
				78, "Tele Group Khazard", "Teleports players to Port khazard",
				TDA, 19, 0, 5);
		addLunar3RunesBigBox(30178, 9075, 564, 559, 1, 0, 4, 30013, 30008, 78,
				"Dream", "Take a rest and restore hitpoints 3\\n times faster",
				TDA, 20, 0, 5);
		addLunar3RunesSmallBox(30186, 9075, 557, 555, 1, 9, 4, 30006, 30004,
				79, "String Jewellery", "String amulets without wool", TDA, 21,
				0, 5);
		addLunar3RunesLargeBox(30194, 9075, 557, 555, 1, 9, 9, 30006, 30004,
				80, "Stat Restore Pot\\nShare",
				"Share a potion with up to 4 nearby\\nplayers", TDA, 22, 0, 5);
		addLunar3RunesSmallBox(30202, 9075, 554, 555, 1, 6, 6, 30003, 30004,
				81, "Magic Imbue", "Combine runes without a talisman", TDA, 23,
				0, 5);
		addLunar3RunesBigBox(30210, 9075, 561, 557, 2, 1, 14, 30010, 30006, 82,
				"Fertile Soil",
				"Fertilise a farming patch with super\\ncompost", TDA, 24, 4, 2);
		addLunar3RunesBigBox(30218, 9075, 557, 555, 2, 11, 9, 30006, 30004, 83,
				"Boost Potion Share",
				"Shares a potion with up to 4 nearby\\nplayers", TDA, 25, 0, 5);
		addLunar3RunesSmallBox(30226, 9075, 563, 555, 2, 2, 9, 30012, 30004,
				84, "Fishing Guild Teleport",
				"Teleports you to the fishing guild", TDA, 26, 0, 5);
		addLunar3RunesLargeBox(30234, 9075, 563, 555, 1, 2, 13, 30012, 30004,
				85, "Tele Group Fishing\\nGuild",
				"Teleports players to the Fishing\\nGuild", TDA, 27, 0, 5);
		addLunar3RunesSmallBox(30242, 9075, 557, 561, 2, 14, 0, 30006, 30010,
				85, "Plank Make", "Turn Logs into planks", TDA, 28, 16, 5);
		addLunar3RunesSmallBox(30250, 9075, 563, 555, 2, 2, 9, 30012, 30004,
				86, "Catherby Teleport", "Teleports you to Catherby", TDA, 29,
				0, 5);
		addLunar3RunesSmallBox(30258, 9075, 563, 555, 2, 2, 14, 30012, 30004,
				87, "Tele Group Catherby", "Teleports players to Catherby",
				TDA, 30, 0, 5);
		addLunar3RunesSmallBox(30266, 9075, 563, 555, 2, 2, 7, 30012, 30004,
				88, "Ice Plateau Teleport", "Teleports you to Ice Plateau",
				TDA, 31, 0, 5);
		addLunar3RunesBigBox(30274, 9075, 563, 555, 2, 2, 15, 30012, 30004, 89,
				"Tele Group Ice\\n Plateau",
				"Teleports players to Ice Plateau", TDA, 32, 0, 5);
		addLunar3RunesBigBox(
				30282,
				9075,
				563,
				561,
				2,
				1,
				0,
				30012,
				30010,
				90,
				"Energy Transfer",
				"Spend hitpoints and SA Energy to\\n give another player hitpoints and run energy",
				TDA, 33, 8, 2);
		addLunar3RunesBigBox(30290, 9075, 563, 565, 2, 2, 0, 30012, 30014, 91,
				"Heal Other",
				"Transfer up to 75% of hitpoints\\n to another player", TDA,
				34, 8, 2);
		addLunar3RunesBigBox(30298, 9075, 560, 557, 2, 1, 9, 30009, 30006, 92,
				"Vengeance Other",
				"Allows another player to rebound\\ndamage to an opponent",
				TDA, 35, 8, 2);
		addLunar3RunesSmallBox(30306, 9075, 560, 557, 3, 1, 9, 30009, 30006,
				93, "Vengeance", "Rebound damage to an opponent", TDA, 36, 0, 5);
		addLunar3RunesBigBox(30314, 9075, 565, 563, 3, 2, 5, 30014, 30012, 94,
				"Heal Group", "Transfer up to 75% of hitpoints to a group",
				TDA, 37, 0, 5);
		addLunar3RunesBigBox(30322, 9075, 564, 563, 2, 1, 0, 30013, 30012, 95,
				"Spellbook Swap",
				"Change to another spellbook for 1\\nspell cast", TDA, 38, 0, 5);
	}

	public static void constructLunar() {
		RSInterface Interface = addTabInterface(29999);
		setChildren(80, Interface);
		setBounds(30000, 11, 10, 0, Interface);
		setBounds(30017, 40, 9, 1, Interface);
		setBounds(30025, 71, 12, 2, Interface);
		setBounds(30032, 103, 10, 3, Interface);
		setBounds(30040, 135, 12, 4, Interface);
		setBounds(30048, 165, 10, 5, Interface);
		setBounds(30056, 8, 38, 6, Interface);
		setBounds(30064, 39, 39, 7, Interface);
		setBounds(30075, 71, 39, 8, Interface);
		setBounds(30083, 103, 39, 9, Interface);
		setBounds(30091, 135, 39, 10, Interface);
		setBounds(30099, 165, 37, 11, Interface);
		setBounds(30106, 12, 68, 12, Interface);
		setBounds(30114, 42, 68, 13, Interface);
		setBounds(30122, 71, 68, 14, Interface);
		setBounds(30130, 103, 68, 15, Interface);
		setBounds(30138, 135, 68, 16, Interface);
		setBounds(30146, 165, 68, 17, Interface);
		setBounds(30154, 14, 97, 18, Interface);
		setBounds(30162, 42, 97, 19, Interface);
		setBounds(30170, 71, 97, 20, Interface);
		setBounds(30178, 101, 97, 21, Interface);
		setBounds(30186, 135, 98, 22, Interface);
		setBounds(30194, 168, 98, 23, Interface);
		setBounds(30202, 11, 125, 24, Interface);
		setBounds(30210, 42, 124, 25, Interface);
		setBounds(30218, 74, 125, 26, Interface);
		setBounds(30226, 103, 125, 27, Interface);
		setBounds(30234, 135, 125, 28, Interface);
		setBounds(30242, 164, 126, 29, Interface);
		setBounds(30250, 10, 155, 30, Interface);
		setBounds(30258, 42, 155, 31, Interface);
		setBounds(30266, 71, 155, 32, Interface);
		setBounds(30274, 103, 155, 33, Interface);
		setBounds(30282, 136, 155, 34, Interface);
		setBounds(30290, 165, 155, 35, Interface);
		setBounds(30298, 13, 185, 36, Interface);
		setBounds(30306, 42, 185, 37, Interface);
		setBounds(30314, 71, 184, 38, Interface);
		setBounds(30322, 104, 184, 39, Interface);
		setBounds(30001, 6, 184, 40, Interface);
		setBounds(30018, 5, 176, 41, Interface);
		setBounds(30026, 5, 176, 42, Interface);
		setBounds(30033, 5, 163, 43, Interface);
		setBounds(30041, 5, 176, 44, Interface);
		setBounds(30049, 5, 176, 45, Interface);
		setBounds(30057, 5, 176, 46, Interface);
		setBounds(30065, 5, 176, 47, Interface);
		setBounds(30076, 5, 163, 48, Interface);
		setBounds(30084, 5, 176, 49, Interface);
		setBounds(30092, 5, 176, 50, Interface);
		setBounds(30100, 5, 176, 51, Interface);
		setBounds(30107, 5, 176, 52, Interface);
		setBounds(30115, 5, 163, 53, Interface);
		setBounds(30123, 5, 176, 54, Interface);
		setBounds(30131, 5, 163, 55, Interface);
		setBounds(30139, 5, 163, 56, Interface);
		setBounds(30147, 5, 163, 57, Interface);
		setBounds(30155, 5, 176, 58, Interface);
		setBounds(30163, 5, 176, 59, Interface);
		setBounds(30171, 5, 176, 60, Interface);
		setBounds(30179, 5, 163, 61, Interface);
		setBounds(30187, 5, 176, 62, Interface);
		setBounds(30195, 5, 149, 63, Interface);
		setBounds(30203, 5, 176, 64, Interface);
		setBounds(30211, 5, 163, 65, Interface);
		setBounds(30219, 5, 163, 66, Interface);
		setBounds(30227, 5, 176, 67, Interface);
		setBounds(30235, 5, 149, 68, Interface);
		setBounds(30243, 5, 176, 69, Interface);
		setBounds(30251, 5, 5, 70, Interface);
		setBounds(30259, 5, 5, 71, Interface);
		setBounds(30267, 5, 5, 72, Interface);
		setBounds(30275, 5, 5, 73, Interface);
		setBounds(30283, 5, 5, 74, Interface);
		setBounds(30291, 5, 5, 75, Interface);
		setBounds(30299, 5, 5, 76, Interface);
		setBounds(30307, 5, 5, 77, Interface);
		setBounds(30323, 5, 5, 78, Interface);
		setBounds(30315, 5, 5, 79, Interface);
	}

	public static void setBounds(int ID, int X, int Y, int frame,
			RSInterface RSinterface) {
		RSinterface.children[frame] = ID;
		RSinterface.childX[frame] = X;
		RSinterface.childY[frame] = Y;
	}

	public static void addButton(int i, int j, String name, int W, int H,
			String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.type = 5;
		RSInterface.atActionType = AT;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.mOverInterToTrigger = 52;
		RSInterface.sprite1 = imageLoader(j, name);
		RSInterface.sprite2 = imageLoader(j, name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}

	public RSInterface() {
		itemSpriteId1 = -1;
		itemSpriteId2 = -1;
		itemSpriteZoom1 = -1;
		itemSpriteZoom2 = -1;
		itemSpriteIndex = 0;
	}

	public static int boxIds[] = { 4041, 4077, 4113, 4047, 4083, 4119, 4053,
			4089, 4125, 4059, 4095, 4131, 4065, 4101, 4137, 4071, 4107, 4143,
			4154, 12168, 13918 };
	public String popupString;
	public String hoverText;
	public static StreamLoader aClass44;
	public boolean drawsTransparent;
	public Sprite sprite1;
		public boolean inventoryhover;
	public int anInt208;
	public Sprite sprites[];
	public static RSInterface interfaceCache[];
	public int requiredValues[];
	public int contentType;
	public int spritesX[];
	public int textHoverColour;
	public int atActionType;
	public String spellName;
	public int anInt219;
	public int width;
	public String tooltip;
	public String selectedActionName;
	public boolean centerText;
	public int scrollPosition;
	public String itemActions[];
	public int valueIndexArray[][];
	public boolean aBoolean227;
	public String disabledText;
	public int mOverInterToTrigger;
	public int invSpritePadX;
	public int textColor;
	public int anInt233;
	public int mediaID;
	public boolean aBoolean235;
	public int parentID;
	public int spellUsableOn;
	private static MRUNodes aMRUNodes_238;
	public int anInt239;
	public int children[];
	public int childX[];
	public boolean usableItemInterface;
	public TextDrawingArea textDrawingAreas;
	public int invSpritePadY;
	public int valueCompareType[];
	public int anInt246;
	public int spritesY[];
	public String message;
	public boolean isInventoryInterface;
	public int id;
	public int invStackSizes[];
	public int inv[];
	public byte opacity;
	private int anInt255;
	private int anInt256;
	public int anInt257;
	public int anInt258;
	public boolean aBoolean259;
	public Sprite sprite2;
	public int scrollMax;
	public int type;
	public int anInt263;
	private static final MRUNodes aMRUNodes_264 = new MRUNodes(30);
	public int anInt265;
	public boolean isMouseoverTriggered;
	public int height;
	public boolean textShadow;
	public int modelZoom;
	public int modelRotation1;
	public int modelRotation2;
	public int childY[];
	public int itemSpriteId1;
	public int itemSpriteId2;
	public int itemSpriteZoom1;
	public int itemSpriteZoom2;
	public int itemSpriteIndex;
	public boolean greyScale;
	public static TextDrawingArea fonts[];
	private static int summoningLevelRequirements[] = { 1, 4, 10, 13, 16, 17,
			18, 19, 22, 23, 25, 28, 29, 31, 32, 33, 34, 34, 34, 34, 36, 40, 41,
			42, 43, 43, 43, 43, 43, 43, 43, 46, 46, 47, 49, 52, 54, 55, 56, 56,
			57, 57, 57, 58, 61, 62, 63, 64, 66, 66, 67, 68, 69, 70, 71, 72, 73,
			74, 75, 76, 76, 77, 78, 79, 79, 79, 80, 83, 83, 85, 86, 88, 89, 92,
			93, 95, 96, 99 };
	private static int pouchItems[] = { 12047, 12043, 12059, 12019, 12009,
			12778, 12049, 12055, 12808, 12067, 12063, 12091, 12800, 12053,
			12065, 12021, 12818, 12780, 12798, 12814, 12073, 12087, 12071,
			12051, 12095, 12097, 12099, 12101, 12103, 12105, 12107, 12075,
			12816, 12041, 12061, 12007, 12035, 12027, 12077, 12531, 12810,
			12812, 12784, 12023, 12085, 12037, 12015, 12045, 12079, 12123,
			12031, 12029, 12033, 12820, 12057, 14623, 12792, 12069, 12011,
			12081, 12782, 12794, 12013, 12802, 12804, 12806, 12025, 12017,
			12788, 12776, 12083, 12039, 12786, 12089, 12796, 12822, 12093,
			12790 };
	private static int scrollItems[] = { 12425, 12445, 12428, 12459, 12533,
			12838, 12460, 12432, 12839, 12430, 12446, 12440, 12834, 12447,
			12433, 12429, 12443, 12443, 12443, 12443, 12461, 12431, 12422,
			12448, 12458, 12458, 12458, 12458, 12458, 12458, 12458, 12462,
			12829, 12426, 12444, 12441, 12454, 12453, 12463, 12424, 12835,
			12836, 12840, 12455, 12468, 12427, 12436, 12467, 12464, 12452,
			12439, 12438, 12423, 12830, 12451, 14622, 12826, 12449, 12450,
			12465, 12841, 12831, 12457, 12824, 12824, 12824, 12442, 12456,
			12837, 12832, 12466, 12434, 12833, 12437, 12827, 12828, 12435,
			12825 };
	private static String scrollNames[] = { "Howl", "Dreadfowl Strike",
			"Egg Spawn", "Slime Spray", "Stony Shell", "Pester",
			"Electric Lash", "Venom Shot", "Fireball Assault", "Cheese Feast",
			"Sandstorm", "Generate Compost", "Explode", "Vampire Touch",
			"Insane Ferocity", "Multichop", "Call of Arms", "Call of Arms",
			"Call of Arms", "Call of Arms", "Bronze Bull Rush", "Unburden",
			"Herbcall", "Evil Flames", "Petrifying gaze", "Petrifying gaze",
			"Petrifying gaze", "Petrifying gaze", "Petrifying gaze",
			"Petrifying gaze", "Petrifying gaze", "Iron Bull Rush",
			"Immense Heat", "Thieving Fingers", "Blood Drain", "Tireless Run",
			"Abyssal Drain", "Dissolve", "Steel Bull Rush", "Fish Rain",
			"Goad", "Ambush", "Rending", "Doomsphere Device", "Dust Cloud",
			"Abyssal Stealth", "Ophidian Incubation", "Poisonous Blast",
			"Mithril Bull Rush", "Toad Bark", "Testudo", "Swallow Whole",
			"Fruitfall", "Famine", "Arctic Blast", "Rise from the Ashes",
			"Volcanic Strength", "Crushing Claw", "Mantis Strike",
			"Adamant Bull Rush", "Inferno", "Deadly Claw", "Acorn Missile",
			"Titan's Consitution", "Titan's Consitution",
			"Titan's Consitution", "Regrowth", "Spike Shot", "Ebon Thunder",
			"Swamp Plague", "Rune Bull Rush", "Healing Aura", "Boil",
			"Magic Focus", "Essence Shipment", "Iron Within", "Winter Storage",
			"Steel of Legends" };
	private static String pouchNames[] = { "Spirit wolf", "Dreadfowl",
			"Spirit spider", "Thorny snail", "Granite crab", "Spirit mosquito",
			"Desert wyrm", "Spirit scorpion", "Spirit tz-kih", "Albino rat",
			"Spirit kalphite", "Compost mound", "Giant chinchompa",
			"Vampire bat", "Honey badger", "Beaver", "Void ravager",
			"Void spinner", "Void torcher", "Void shifter", "Bronze minotaur",
			"Bull ant", "Macaw", "Evil turnip", "Sp. cockatrice",
			"Sp. guthatrice", "Sp. saratrice", "Sp. zamatrice",
			"Sp. pengatrice", "Sp. coraxatrice", "Sp. vulatrice",
			"Iron minotaur", "Pyrelord", "Magpie", "Bloated leech",
			"Spirit terrorbird", "Abyssal parasite", "Spirit jelly",
			"Steel minotaur", "Ibis", "Spirit graahk", "Spirit kyatt",
			"Spirit larupia", "Karam. overlord", "Smoke devil",
			"Abyssal lurker", "Spirit cobra", "Stranger plant",
			"Mithril minotaur", "Barker toad", "War tortoise", "Bunyip",
			"Fruit bat", "Ravenous locust", "Arctic bear", "Phoenix",
			"Obsidian golem", "Granite lobster", "Praying mantis",
			"Adamant minotaur", "Forge regent", "Talon beast", "Giant ent",
			"Fire titan", "Moss titan", "Ice titan", "Hydra",
			"Spirit dagannoth", "Lava titan", "Swamp titan", "Rune minotaur",
			"Unicorn stallion", "Geyser titan", "Wolpertinger",
			"Abyssal titan", "Iron titan", "Pack yak", "Steel titan" };

}