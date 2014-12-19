package com;
public class BountyHunter {

	public static void initialize(TextDrawingArea [] textDrawingAreas ) {
		bountyHunter(textDrawingAreas);
		bountyHunter1(textDrawingAreas);
		bountyHunter2(textDrawingAreas);
		bountyHunter3(textDrawingAreas);
		bountyHunter4(textDrawingAreas);
		bountyHunter5(textDrawingAreas);
		bountyHunter6(textDrawingAreas);
		bountyHunter7(textDrawingAreas);
		bountyHunter8(textDrawingAreas);
	}
	
	static void bountyHunter(TextDrawingArea[] tda) {
		RSInterface tab = RSInterface.addInterface(25347);
		tab.totalChildren(9);

		RSInterface.addText(25349, "Target:", 0xffcccc, false, true, -1, tda, 0);
		RSInterface.addText(25350, "None", 0xff6666, true, true, -1, tda, 0);
		RSInterface.addText(25351, "Target likeihood", 0xffcccc, false, true, -1, tda, 0);
		
		RSInterface.addSprite(25352, 1, "Other/SUB");
		
		RSInterface.addTransparentSprite(25348, 0, "Other/bh", 128);//128
		
		tab.child(0,25348,332,3);
		tab.child(1,25349,340,13);
		tab.child(2,25350,480,13);
		tab.child(3,25351,340,34);
		tab.child(4,25352,470,27);

		int main = 28505;
		
		RSInterface.addSprite(main + 1, 2, "PvP/pvp");
		RSInterface.addText(28502, "114 - 126", tda, 1, 0xff9040, false, true);
		RSInterface.addText(28503, "EP: 2%", tda, 1, 0xff9040, false, true);
		RSInterface.addHovered(main + 4, 2, "PvP/pvp", 17, 17, main + 5);
		
		tab.child(5, main + 1, 414, 275);
		tab.child(6, 28502, 424, 300);
		tab.child(7, 28503, 430, 320);
		tab.child(8, main + 4, 414, 275);
	}
	
	static void bountyHunter1(TextDrawingArea[] tda) {
		RSInterface tab = RSInterface.addInterface(25353);
		tab.totalChildren(9);

		RSInterface.addTransparentSprite(25354, 0, "Other/bh", 128);//128
		RSInterface.addText(25355, "Target:", 0xffcccc, false, true, -1, tda, 0);
		RSInterface.addText(25356, "None", 0xff6666, true, true, -1, tda, 0);
		RSInterface.addText(25357, "Target likeihood", 0xffcccc, false, true, -1, tda, 0);
		
		RSInterface.addSprite(25358, 2, "Other/SUB");
		
		
		tab.child(0,25354,332,3);
		tab.child(1,25355,340,13);
		tab.child(2,25356,480,13);
		tab.child(3,25357,340,34);
		tab.child(4,25358,470,27);

		int main = 28505;
		
		RSInterface.addSprite(main + 1, 2, "PvP/pvp");
		RSInterface.addText(28502, "114 - 126", tda, 1, 0xff9040, false, true);
		RSInterface.addText(28503, "EP: 2%", tda, 1, 0xff9040, false, true);
		RSInterface.addHovered(main + 4, 2, "PvP/pvp", 17, 17, main + 5);
		
		tab.child(5, main + 1, 414, 275);
		tab.child(6, 28502, 424, 300);
		tab.child(7, 28503, 430, 320);
		tab.child(8, main + 4, 414, 275);
	}
	
	static void bountyHunter2(TextDrawingArea[] tda) {
		RSInterface tab = RSInterface.addInterface(25359);
		tab.totalChildren(9);

		RSInterface.addTransparentSprite(25360, 0, "Other/bh", 128);//128
		RSInterface.addText(25361, "Target:", 0xffcccc, false, true, -1, tda, 0);
		RSInterface.addText(25362, "None", 0xff6666, true, true, -1, tda, 0);
		RSInterface.addText(25363, "Target likeihood", 0xffcccc, false, true, -1, tda, 0);
		
		RSInterface.addSprite(25364, 3, "Other/SUB");
		
		tab.child(0,25360,332,3);
		tab.child(1,25361,340,13);
		tab.child(2,25362,480,13);
		tab.child(3,25363,340,34);
		tab.child(4,25364,470,27);

		int main = 28505;
		
		RSInterface.addSprite(main + 1, 2, "PvP/pvp");
		RSInterface.addText(28502, "114 - 126", tda, 1, 0xff9040, false, true);
		RSInterface.addText(28503, "EP: 2%", tda, 1, 0xff9040, false, true);
		RSInterface.addHovered(main + 4, 2, "PvP/pvp", 17, 17, main + 5);
		
		tab.child(5, main + 1, 414, 275);
		tab.child(6, 28502, 424, 300);
		tab.child(7, 28503, 430, 320);
		tab.child(8, main + 4, 414, 275);
	}
	
	static void bountyHunter3(TextDrawingArea[] tda) {
		RSInterface tab = RSInterface.addInterface(25365);
		tab.totalChildren(9);

		RSInterface.addTransparentSprite(25366, 0, "Other/bh", 128);//128
		RSInterface.addText(25367, "Target:", 0xffcccc, false, true, -1, tda, 0);
		RSInterface.addText(25368, "None", 0xff6666, true, true, -1, tda, 0);
		RSInterface.addText(25369, "Target likeihood", 0xffcccc, false, true, -1, tda, 0);
		
		RSInterface.addSprite(25370, 4, "Other/SUB");
		
		
		tab.child(0,25366,332,3);
		tab.child(1,25367,340,13);
		tab.child(2,25368,480,13);
		tab.child(3,25369,340,34);
		tab.child(4,25370,470,27);

		int main = 28505;
		
		RSInterface.addSprite(main + 1, 2, "PvP/pvp");
		RSInterface.addText(28502, "114 - 126", tda, 1, 0xff9040, false, true);
		RSInterface.addText(28503, "EP: 2%", tda, 1, 0xff9040, false, true);
		RSInterface.addHovered(main + 4, 2, "PvP/pvp", 17, 17, main + 5);
		
		tab.child(5, main + 1, 414, 275);
		tab.child(6, 28502, 424, 300);
		tab.child(7, 28503, 430, 320);
		tab.child(8, main + 4, 414, 275);
		
	}
	
	static void bountyHunter4(TextDrawingArea[] tda) {
		RSInterface tab = RSInterface.addInterface(25371);
		tab.totalChildren(9);

		RSInterface.addTransparentSprite(25372, 0, "Other/bh", 128);//128
		RSInterface.addText(25373, "Target:", 0xffcccc, false, true, -1, tda, 0);
		RSInterface.addText(25374, "None", 0xff6666, true, true, -1, tda, 0);
		RSInterface.addText(25375, "Target likeihood", 0xffcccc, false, true, -1, tda, 0);
		
		RSInterface.addSprite(25376, 5, "Other/SUB");
	
		
		tab.child(0,25372,332,3);
		tab.child(1,25373,340,13);
		tab.child(2,25374,480,13);
		tab.child(3,25375,340,34);
		tab.child(4,25376,470,27);

		int main = 28505;
		
		RSInterface.addSprite(main + 1, 2, "PvP/pvp");
		RSInterface.addText(28502, "114 - 126", tda, 1, 0xff9040, false, true);
		RSInterface.addText(28503, "EP: 2%", tda, 1, 0xff9040, false, true);
		RSInterface.addHovered(main + 4, 2, "PvP/pvp", 17, 17, main + 5);
		
		tab.child(5, main + 1, 414, 275);
		tab.child(6, 28502, 424, 300);
		tab.child(7, 28503, 430, 320);
		tab.child(8, main + 4, 414, 275);
		
	}
	
	static void bountyHunter5(TextDrawingArea[] tda) {
		RSInterface tab = RSInterface.addInterface(25377);
		tab.totalChildren(9);

		RSInterface.addTransparentSprite(25378, 0, "Other/bh", 128);//128
		RSInterface.addText(25379, "Target:", 0xffcccc, false, true, -1, tda, 0);
		RSInterface.addText(25380, "None", 0xff6666, true, true, -1, tda, 0);
		RSInterface.addText(25381, "Target likeihood", 0xffcccc, false, true, -1, tda, 0);
		
		RSInterface.addSprite(25382, 6, "Other/SUB");
		
		
		tab.child(0,25378,332,3);
		tab.child(1,25379,340,13);
		tab.child(2,25380,480,13);
		tab.child(3,25381,340,34);
		tab.child(4,25382,470,27);
		
		int main = 28505;
		
		RSInterface.addSprite(main + 1, 2, "PvP/pvp");
		RSInterface.addText(28502, "114 - 126", tda, 1, 0xff9040, false, true);
		RSInterface.addText(28503, "EP: 2%", tda, 1, 0xff9040, false, true);
		RSInterface.addHovered(main + 4, 2, "PvP/pvp", 17, 17, main + 5);
		
		tab.child(5, main + 1, 414, 275);
		tab.child(6, 28502, 424, 300);
		tab.child(7, 28503, 430, 320);
		tab.child(8, main + 4, 414, 275);

	}
	
	static void bountyHunter6(TextDrawingArea[] tda) {
		RSInterface tab = RSInterface.addInterface(25383);
		tab.totalChildren(9);

		RSInterface.addTransparentSprite(25384, 0, "Other/bh", 128);//128
		RSInterface.addText(25385, "Target:", 0xffcccc, false, true, -1, tda, 0);
		RSInterface.addText(25386, "None", 0xff6666, true, true, -1, tda, 0);
		RSInterface.addText(25387, "Target likeihood", 0xffcccc, false, true, -1, tda, 0);
		
		RSInterface.addSprite(25388, 7, "Other/SUB");
		
		tab.child(0,25384,332,3);
		tab.child(1,25385,340,13);
		tab.child(2,25386,480,13);
		tab.child(3,25387,340,34);
		tab.child(4,25388,470,27);

		int main = 28505;
		
		RSInterface.addSprite(main + 1, 2, "PvP/pvp");
		RSInterface.addText(28502, "114 - 126", tda, 1, 0xff9040, false, true);
		RSInterface.addText(28503, "EP: 2%", tda, 1, 0xff9040, false, true);
		RSInterface.addHovered(main + 4, 2, "PvP/pvp", 17, 17, main + 5);
		
		tab.child(5, main + 1, 414, 275);
		tab.child(6, 28502, 424, 300);
		tab.child(7, 28503, 430, 320);
		tab.child(8, main + 4, 414, 275);
	}
	
	static void bountyHunter7(TextDrawingArea[] tda) {
		RSInterface tab = RSInterface.addInterface(25389);
		tab.totalChildren(9);

		RSInterface.addTransparentSprite(25390, 0, "Other/bh", 128);//128
		RSInterface.addText(25391, "Target:", 0xffcccc, false, true, -1, tda, 0);
		RSInterface.addText(25392, "None", 0xff6666, true, true, -1, tda, 0);
		RSInterface.addText(25393, "Target likeihood", 0xffcccc, false, true, -1, tda, 0);
		
		RSInterface.addSprite(25394, 8, "Other/SUB");
		
		tab.child(0,25390,332,3);
		tab.child(1,25391,340,13);
		tab.child(2,25392,480,13);
		tab.child(3,25393,340,34);
		tab.child(4,25394,470,27);
		
		int main = 28505;
		
		RSInterface.addSprite(main + 1, 2, "PvP/pvp");
		RSInterface.addText(28502, "114 - 126", tda, 1, 0xff9040, false, true);
		RSInterface.addText(28503, "EP: 2%", tda, 1, 0xff9040, false, true);
		RSInterface.addHovered(main + 4, 2, "PvP/pvp", 17, 17, main + 5);
		
		tab.child(5, main + 1, 414, 275);
		tab.child(6, 28502, 424, 300);
		tab.child(7, 28503, 430, 320);
		tab.child(8, main + 4, 414, 275);
	}
	
	static void bountyHunter8(TextDrawingArea[] tda) {
		RSInterface tab = RSInterface.addInterface(26389);
		tab.totalChildren(9);

		RSInterface.addTransparentSprite(26390, 0, "Other/bh", 128);//128
		RSInterface.addText(26391, "Target:", 0xffcccc, false, true, -1, tda, 0);
		RSInterface.addText(26392, "None", 0xff6666, true, true, -1, tda, 0);
		RSInterface.addText(26393, "Target likeihood", 0xffcccc, false, true, -1, tda, 0);
		
		RSInterface.addSprite(26394, 9, "Other/SUB");
		
		tab.child(0,26390,332,3);
		tab.child(1,26391,340,13);
		tab.child(2,26392,480,13);
		tab.child(3,26393,340,34);
		tab.child(4,26394,470,27);
		
		int main = 26505;
		
		RSInterface.addSprite(main + 1, 2, "PvP/pvp");
		RSInterface.addText(26502, "114 - 126", tda, 1, 0xff9040, false, true);
		RSInterface.addText(26503, "EP: 2%", tda, 1, 0xff9040, false, true);
		RSInterface.addHovered(main + 4, 2, "PvP/pvp", 17, 17, main + 5);
		
		tab.child(5, main + 1, 414, 275);
		tab.child(6, 26502, 424, 300);
		tab.child(7, 26503, 430, 320);
		tab.child(8, main + 4, 414, 275);
		
	}
	
}