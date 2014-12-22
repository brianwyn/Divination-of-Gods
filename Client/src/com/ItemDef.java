package com;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemDef.java

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.sign.signlink;

public final class ItemDef {

	public static void nullLoader() {
		mruNodes2 = null;
		mruNodes1 = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public static void itemModel() {
		try {
			for (int i = 0; i < 20793; i++) {
				ItemDef itemDef = forID(i);
				System.out.println(i);
				byte abyte0121[] = FileOperations
						.ReadFile((new StringBuilder()).append("./items/")
								.append(itemDef.modelID).append(".mdl")
								.toString());
				FileOperations.WriteFile(
						(new StringBuilder()).append("./dumped/")
								.append(itemDef.modelID).append(".mdl")
								.toString(), abyte0121);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void toLend() {
		ItemDef itemDef = forID(lendTemplateID);
		itemActions = new String[5];
		modelID = itemDef.modelID;
		modelOffset1 = itemDef.modelOffset1;
		modelRotation2 = itemDef.modelRotation2;
		modelOffset2 = itemDef.modelOffset2;
		modelZoom = itemDef.modelZoom;
		modelRotation1 = itemDef.modelRotation1;
		anInt204 = itemDef.anInt204;
		value = 0;
		ItemDef itemDef_1 = forID(lendID);
		anInt166 = itemDef_1.anInt166;
		originalModelColors = itemDef_1.originalModelColors;
		anInt185 = itemDef_1.anInt185;
		anInt200 = itemDef_1.anInt200;
		anInt173 = itemDef_1.anInt173;
		anInt175 = itemDef_1.anInt175;
		groundActions = itemDef_1.groundActions;
		anInt165 = itemDef_1.anInt165;
		name = itemDef_1.name;
		anInt188 = itemDef_1.anInt188;
		membersObject = itemDef_1.membersObject;
		anInt197 = itemDef_1.anInt197;
		anInt164 = itemDef_1.anInt164;
		anInt162 = itemDef_1.anInt162;
		modifiedModelColors = itemDef_1.modifiedModelColors;
		team = itemDef_1.team;
		if (itemDef_1.itemActions != null) {
			for (int i_33_ = 0; i_33_ < 4; i_33_++)
				itemActions[i_33_] = itemDef_1.itemActions[i_33_];

		}
		itemActions[4] = "Discard";
	}

	public boolean method192(int j) {
		int k = anInt175;
		int l = anInt166;
		if (j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if (k == -1)
			return true;
		boolean flag = true;
		if (!Model.method463(k))
			flag = false;
		if (l != -1 && !Model.method463(l))
			flag = false;
		return flag;
	}

	public Model method194(int j) {
		int k = anInt175;
		int l = anInt166;
		if (j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if (k == -1)
			return null;
		Model model = Model.method462(k);
		if (l != -1) {
			Model model_1 = Model.method462(l);
			Model models[] = { model, model_1 };
			model = new Model(2, models);
		}
		if (modifiedModelColors != null) {
			for (int i1 = 0; i1 < modifiedModelColors.length; i1++)
				model.method476(modifiedModelColors[i1],
						originalModelColors[i1]);

		}
		return model;
	}

	public boolean method195(int j) {
		int k = anInt165;
		int l = anInt188;
		int i1 = anInt185;
		if (j == 1) {
			k = anInt200;
			l = anInt164;
			i1 = anInt162;
		}
		if (k == -1)
			return true;
		boolean flag = true;
		if (!Model.method463(k))
			flag = false;
		if (l != -1 && !Model.method463(l))
			flag = false;
		if (i1 != -1 && !Model.method463(i1))
			flag = false;
		return flag;
	}

	public Model method196(int i) {
		int j = anInt165;
		int k = anInt188;
		int l = anInt185;
		if (i == 1) {
			j = anInt200;
			k = anInt164;
			l = anInt162;
		}
		if (j == -1)
			return null;
		Model model = Model.method462(j);
		if (k != -1)
			if (l != -1) {
				Model model_1 = Model.method462(k);
				Model model_3 = Model.method462(l);
				Model model_1s[] = { model, model_1, model_3 };
				model = new Model(3, model_1s);
			} else {
				Model model_2 = Model.method462(k);
				Model models[] = { model, model_2 };
				model = new Model(2, models);
			}
		if (i == 0 && aByte205 != 0)
			model.method475(0, aByte205, 0);
		if (i == 1 && aByte154 != 0)
			model.method475(0, aByte154, 0);
		if (modifiedModelColors != null) {
			for (int i1 = 0; i1 < modifiedModelColors.length; i1++)
				model.method476(modifiedModelColors[i1],
						originalModelColors[i1]);

		}
		return model;
	}

	private void setDefaults() {
		barrows = null;
		modelID = 0;
		name = null;
		description = null;
		originalModelColors = null;
		modifiedModelColors = null;
		modelZoom = 2000;
		modelRotation1 = 0;
		modelRotation2 = 0;
		anInt204 = 0;
		modelOffset1 = 0;
		modelOffset2 = 0;
		stackable = false;
		value = 1;
		membersObject = false;
		groundActions = null;
		itemActions = null;
		lendID = -1;
		lendTemplateID = -1;
		anInt165 = -1;
		anInt188 = -1;
		aByte205 = 0;
		anInt200 = -1;
		anInt164 = -1;
		aByte154 = 0;
		anInt185 = -1;
		anInt162 = -1;
		anInt175 = -1;
		anInt166 = -1;
		anInt197 = -1;
		anInt173 = -1;
		stackIDs = null;
		stackAmounts = null;
		certID = -1;
		certTemplateID = -1;
		anInt167 = 128;
		anInt192 = 128;
		anInt191 = 128;
		anInt196 = 0;
		anInt184 = 0;
		team = 0;
	}

	public static byte[] getData(String s) {
		return FileOperations.ReadFile(s);
	}

	public static void unpackConfig(StreamLoader streamLoader) {
		stream = new Stream(getData((new StringBuilder()).append(loc)
				.append("obj.dat").toString()));
		Stream stream = new Stream(getData((new StringBuilder()).append(loc)
				.append("obj.idx").toString()));
		totalItems = stream.readUnsignedWord();
		streamIndices = new int[totalItems + 20000];
		int i = 2;
		for (int j = 0; j < totalItems; j++) {
			streamIndices[j] = i;
			i += stream.readUnsignedWord();
		}

		cache = new ItemDef[10];
		for (int k = 0; k < 10; k++)
			cache[k] = new ItemDef();

	}

	public static Sprite getSprite(int i, int j, int k, int zoom) {
		if (k == 0 && zoom != -1) {
			Sprite sprite = (Sprite) mruNodes1.insertFromCache(i);
			if (sprite != null && sprite.maxHeight != j
					&& sprite.maxHeight != -1) {
				sprite.unlink();
				sprite = null;
			}
			if (sprite != null)
				return sprite;
		}
		ItemDef itemDef = forID(i);
		if (itemDef.stackIDs == null)
			j = -1;
		if (j > 1) {
			int i1 = -1;
			for (int j1 = 0; j1 < 10; j1++)
				if (j >= itemDef.stackAmounts[j1]
						&& itemDef.stackAmounts[j1] != 0)
					i1 = itemDef.stackIDs[j1];

			if (i1 != -1)
				itemDef = forID(i1);
		}
		Model model = itemDef.method201(1);
		if (model == null)
			return null;
		Sprite sprite = null;
		if (itemDef.certTemplateID != -1) {
			sprite = getSprite(itemDef.certID, 10, -1);
			if (sprite == null)
				return null;
		}
		if (itemDef.lendID != -1) {
			sprite = getSprite(itemDef.lendID, 50, 0);
			if (sprite == null)
				return null;
		}
		Sprite sprite2 = new Sprite(32, 32);
		int k1 = Texture.textureInt1;
		int l1 = Texture.textureInt2;
		int ai[] = Texture.anIntArray1472;
		int ai1[] = DrawingArea.pixels;
		int i2 = DrawingArea.width;
		int j2 = DrawingArea.height;
		int k2 = DrawingArea.topX;
		int l2 = DrawingArea.bottomX;
		int i3 = DrawingArea.topY;
		int j3 = DrawingArea.bottomY;
		Texture.aBoolean1464 = false;
		DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
		DrawingArea.drawPixels(32, 0, 0, 0, 32);
		Texture.method364();
		int k3 = itemDef.modelZoom;
		if (zoom != -1)
			k3 = (itemDef.modelZoom * 100) / zoom;
		if (k == -1)
			k3 = (int) (k3 * 1.5D);
		if (k > 0)
			k3 = (int) (k3 * 1.04D);
		int l3 = Texture.anIntArray1470[itemDef.modelRotation1] * k3 >> 16;
		int i4 = Texture.anIntArray1471[itemDef.modelRotation1] * k3 >> 16;
		model.method482(itemDef.modelRotation2, itemDef.anInt204,
				itemDef.modelRotation1, itemDef.modelOffset1, l3
						+ model.modelHeight / 2 + itemDef.modelOffset2, i4
						+ itemDef.modelOffset2);
		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--) {
				if (sprite2.myPixels[i5 + j4 * 32] != 0)
					continue;
				if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1) {
					sprite2.myPixels[i5 + j4 * 32] = 1;
					continue;
				}
				if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
					sprite2.myPixels[i5 + j4 * 32] = 1;
					continue;
				}
				if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
					sprite2.myPixels[i5 + j4 * 32] = 1;
					continue;
				}
				if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1)
					sprite2.myPixels[i5 + j4 * 32] = 1;
			}

		}

		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--) {
					if (sprite2.myPixels[j5 + k4 * 32] != 0)
						continue;
					if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1) {
						sprite2.myPixels[j5 + k4 * 32] = k;
						continue;
					}
					if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
						sprite2.myPixels[j5 + k4 * 32] = k;
						continue;
					}
					if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
						sprite2.myPixels[j5 + k4 * 32] = k;
						continue;
					}
					if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1)
						sprite2.myPixels[j5 + k4 * 32] = k;
				}

			}

		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--)
					if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0
							&& sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0)
						sprite2.myPixels[k5 + l4 * 32] = 0x302020;

			}

		}
		if (itemDef.certTemplateID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (itemDef.lendID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (k == 0)
			mruNodes1.removeFromCache(sprite2, i);
		DrawingArea.initDrawingArea(j2, i2, ai1);
		DrawingArea.setDrawingArea(j3, k2, l2, i3);
		Texture.textureInt1 = k1;
		Texture.textureInt2 = l1;
		Texture.anIntArray1472 = ai;
		Texture.aBoolean1464 = true;
		sprite2.maxWidth = itemDef.stackable ? 33 : 32;
		sprite2.maxHeight = j;
		return sprite2;
	}

	private void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				return;
			if (i == 1)
				modelID = stream.readUnsignedWord();
			else if (i == 2)
				name = stream.readString();
			else if (i == 3)
				description = stream.readBytes().toString();
			else if (i == 4)
				modelZoom = stream.readUnsignedWord();
			else if (i == 5)
				modelRotation1 = stream.readUnsignedWord();
			else if (i == 6)
				modelRotation2 = stream.readUnsignedWord();
			else if (i == 7) {
				modelOffset1 = stream.readUnsignedWord();
				if (modelOffset1 > 32767)
					modelOffset1 -= 0x10000;
			} else if (i == 8) {
				modelOffset2 = stream.readUnsignedWord();
				if (modelOffset2 > 32767)
					modelOffset2 -= 0x10000;
			} else if (i == 10)
				stream.readUnsignedWord();
			else if (i == 11)
				stackable = true;
			else if (i == 12)
				value = stream.readDWord();
			else if (i == 16)
				membersObject = true;
			else if (i == 23) {
				anInt165 = stream.readUnsignedWord();
				aByte205 = stream.readSignedByte();
			} else if (i == 24)
				anInt188 = stream.readUnsignedWord();
			else if (i == 25) {
				anInt200 = stream.readUnsignedWord();
				aByte154 = stream.readSignedByte();
			} else if (i == 26)
				anInt164 = stream.readUnsignedWord();
			else if (i >= 30 && i < 35) {
				if (groundActions == null)
					groundActions = new String[5];
				groundActions[i - 30] = stream.readString();
				if (groundActions[i - 30].equalsIgnoreCase("hidden"))
					groundActions[i - 30] = null;
			} else if (i >= 35 && i < 40) {
				if (itemActions == null)
					itemActions = new String[5];
				itemActions[i - 35] = stream.readString();
			} else if (i == 40) {
				int j = stream.readUnsignedByte();
				modifiedModelColors = new int[j];
				originalModelColors = new int[j];
				int k = 0;
				while (k < j) {
					modifiedModelColors[k] = stream.readUnsignedWord();
					originalModelColors[k] = stream.readUnsignedWord();
					k++;
				}
			} else if (i == 78)
				anInt185 = stream.readUnsignedWord();
			else if (i == 79)
				anInt162 = stream.readUnsignedWord();
			else if (i == 90)
				anInt175 = stream.readUnsignedWord();
			else if (i == 91)
				anInt197 = stream.readUnsignedWord();
			else if (i == 92)
				anInt166 = stream.readUnsignedWord();
			else if (i == 93)
				anInt173 = stream.readUnsignedWord();
			else if (i == 95)
				anInt204 = stream.readUnsignedWord();
			else if (i == 97)
				certID = stream.readUnsignedWord();
			else if (i == 98)
				certTemplateID = stream.readUnsignedWord();
			else if (i >= 100 && i < 110) {
				if (stackIDs == null) {
					stackIDs = new int[10];
					stackAmounts = new int[10];
				}
				stackIDs[i - 100] = stream.readUnsignedWord();
				stackAmounts[i - 100] = stream.readUnsignedWord();
			} else if (i == 110)
				anInt167 = stream.readUnsignedWord();
			else if (i == 111)
				anInt192 = stream.readUnsignedWord();
			else if (i == 112)
				anInt191 = stream.readUnsignedWord();
			else if (i == 113)
				anInt196 = stream.readSignedByte();
			else if (i == 114)
				anInt184 = stream.readSignedByte() * 5;
			else if (i == 115)
				team = stream.readUnsignedByte();
			else if (i == 121)
				stream.readUnsignedWord();
			else if (i == 122)
				stream.readUnsignedWord();
		} while (true);
	}

	public static void dump() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(
				"./Items.txt"))) {
			bw.write("Name\t\t\tDesc.");
			bw.newLine();
			for (int i = 0; i < 20793; i++) {
				ItemDef def = forID(i);
				bw.write(i + " " + def.name + "\t\t\t" + def.description);
				bw.newLine();
			}
		} catch (IOException e) {

		}
	}

	public static ItemDef forID(int i) {
		for (int j = 0; j < 10; j++)
			if (cache[j].id == i)
				return cache[j];

		cacheIndex = (cacheIndex + 1) % 10;
		ItemDef itemDef = cache[cacheIndex];
		stream.currentOffset = streamIndices[i];
		itemDef.id = i;
		itemDef.setDefaults();
		itemDef.readValues(stream);
		itemDef = ItemDef_2.itemDef(i, itemDef);

		if (itemDef.certTemplateID != -1)
			itemDef.toNote();
		if (itemDef.lendTemplateID != -1)
			itemDef.toLend();
		if (!isMembers && itemDef.membersObject) {
			itemDef.name = "Members Object";
			itemDef.description = "Login to a members' server to use this object.";
			itemDef.groundActions = null;
			itemDef.itemActions = null;
			itemDef.team = 0;
		}
		switch (i) {

		case 3450:
			itemDef.modelID = 23026;
			itemDef.name = "American Freedom Cape";
			itemDef.description = "Would you like some fries with that?";
			itemDef.modelZoom = 1400;
			itemDef.modelRotation1 = 0;
			itemDef.modelRotation2 = 800;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 4;
			itemDef.anInt165 = 23026;
			itemDef.anInt200 = 23026;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 3451:
			itemDef.modelID = 23035;
			itemDef.name = "Cape of the Canadians";
			itemDef.description = "Sorry.";
			itemDef.modelZoom = 1400;
			itemDef.modelRotation1 = 0;
			itemDef.modelRotation2 = 800;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 4;
			itemDef.anInt165 = 23035;
			itemDef.anInt200 = 23035;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 3452:
			itemDef.modelID = 23031;
			itemDef.name = "The United Capedom";
			itemDef.description = "Because nothing is more British.";
			itemDef.modelZoom = 1400;
			itemDef.modelRotation1 = 0;
			itemDef.modelRotation2 = 800;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 4;
			itemDef.anInt165 = 23031;
			itemDef.anInt200 = 23031;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 3453:
			itemDef.modelID = 79926;
			itemDef.name = "Trident of the Seas";
			itemDef.description = "Why is it on land?";
			itemDef.modelZoom = 2350;
			itemDef.modelRotation1 = 1505;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 4;
			itemDef.anInt165 = 79927;
			itemDef.anInt200 = 79927;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Drop";
			break;
		case 3454:
			itemDef.name = "Staff of the dead";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.description = "A ghastly weapon with evil origins.";
			itemDef.modelID = 79928;
			itemDef.anInt165 = 79929;
			itemDef.anInt200 = 79929;
			itemDef.modelRotation1 = 148;
			itemDef.modelRotation2 = 1300;
			itemDef.modelZoom = 1420;
			itemDef.modelOffset1 = -5;
			itemDef.modelOffset2 = 2;
			break;
		case 3455:
			itemDef.name = "Golden katana";
			itemDef.description = "The richest way to die.";
			itemDef.itemActions = new String[] { null, "Wield", null, null,
					"Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 70899;
			itemDef.anInt165 = 70900;
			itemDef.anInt200 = 70900;
			itemDef.modelZoom = 1897;
			itemDef.modelRotation1 = 308;
			itemDef.modelRotation2 = 520;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 1;
			break;
		case 3456:
			itemDef.modelID = 52125;
			itemDef.name = "Seismic Wand";
			itemDef.description = "An ominous wand with gleaming energy";
			itemDef.modelZoom = 1019;
			itemDef.modelRotation1 = 618;
			itemDef.modelRotation2 = 1143;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -5;
			itemDef.anInt165 = 52126;
			itemDef.anInt200 = 52127;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Drop";
			break;
		case 3457:
			itemDef.modelID = 79930;
			itemDef.name = "Armadyl Crossbow";
			itemDef.description = "Faaaaaaabulous.";
			itemDef.modelZoom = 1330;
			itemDef.modelRotation1 = 236;
			itemDef.modelRotation2 = 236;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffset2 = -36;
			itemDef.anInt165 = 79931;
			itemDef.anInt200 = 79931;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Drop";
			break;
		case 3458:
			itemDef.name = "Golden scythe";
			itemDef.description = "Taking souls with style.";
			itemDef.itemActions = new String[] { null, "Wield", null, null,
					"Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 1853;
			itemDef.anInt165 = 1897;
			itemDef.anInt200 = 1897;
			itemDef.modelZoom = 2000;
			itemDef.modelRotation1 = 336;
			itemDef.modelRotation2 = 1391;
			itemDef.modelOffset1 = -8;
			itemDef.modelOffset2 = 36;
			break;
		case 3459:
			itemDef.name = "Karil's pistol crossbow";
			itemDef.description = "Pew pew, motherfucker.";
			itemDef.modelZoom = 1200;
			itemDef.modelRotation1 = 444;
			itemDef.modelRotation2 = 1592;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = 5;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"Drop" };
			itemDef.modelID = 28443;
			itemDef.anInt165 = 79838;
			itemDef.anInt200 = 79838;
			break;
		case 3460:
			itemDef.name = "Pernix gloves";
			itemDef.modelZoom = 537;
			itemDef.modelRotation1 = 380;
			itemDef.modelRotation2 = 65;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 7;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", "Check-charges",
					null, "Drop" };
			itemDef.modelID = 72691;
			itemDef.anInt165 = 73093;
			itemDef.anInt200 = 73093;
			break;
		case 3461:
			itemDef.name = "Torva gloves";
			itemDef.modelZoom = 592;
			itemDef.modelRotation1 = 323;
			itemDef.modelRotation2 = 1810;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = 5;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", "Check-charges",
					null, "Drop" };
			itemDef.modelID = 72194;
			itemDef.anInt165 = 73076;
			itemDef.anInt200 = 73076;
			break;
		case 3462:
			itemDef.name = "Virtus gloves";
			itemDef.modelZoom = 550;
			itemDef.modelRotation1 = 286;
			itemDef.modelRotation2 = 258;
			itemDef.modelOffset1 = 2;
			itemDef.modelOffset2 = -2;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", "Check-charges",
					null, "Drop" };
			itemDef.modelID = 75197;
			itemDef.anInt165 = 73094;
			itemDef.anInt200 = 73094;
			break;
		case 3463:
			itemDef.name = "Torva Boots";
			itemDef.modelZoom = 855;
			itemDef.modelRotation1 = 215;
			itemDef.modelRotation2 = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -32;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", "Check-charges",
					null, "Drop" };
			itemDef.modelID = 72191;
			itemDef.anInt165 = 73049;
			itemDef.anInt200 = 73049;
			break;
		case 3464:
			itemDef.name = "Virtus Boots";
			itemDef.modelZoom = 950;
			itemDef.modelRotation1 = 215;
			itemDef.modelRotation2 = 94;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -135;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", "Check-charges",
					null, "Drop" };
			itemDef.modelID = 75203;
			itemDef.anInt165 = 73065;
			itemDef.anInt200 = 73065;
			break;
		case 3465:
			itemDef.name = "Pernix Boots";
			itemDef.modelZoom = 855;
			itemDef.modelRotation1 = 215;
			itemDef.modelRotation2 = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -32;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", "Check-charges",
					null, "Drop" };
			itemDef.modelID = 72889;
			itemDef.anInt165 = 73066;
			itemDef.anInt200 = 73066;
			break;
		case 3466:
			itemDef.name = "Superior death lotus hood";
			itemDef.modelZoom = 720;
			itemDef.modelRotation1 = 57;
			itemDef.modelRotation2 = 2033;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", "Check-charges",
					null, "Drop" };
			itemDef.modelID = 81365;
			itemDef.anInt165 = 82362;
			itemDef.anInt200 = 82422;
			break;
		case 3467:
			itemDef.name = "Superior death lotus chestplate";
			itemDef.modelZoom = 1579;
			itemDef.modelRotation1 = 500;
			itemDef.modelRotation2 = 2046;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -1;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", "Check-charges",
					null, "Drop" };
			itemDef.modelID = 81361;
			itemDef.anInt165 = 82415;
			itemDef.anInt200 = 82467;
			break;
		case 3468:
			itemDef.name = "Superior death lotus chaps";
			itemDef.modelZoom = 1645;
			itemDef.modelRotation1 = 500;
			itemDef.modelRotation2 = 54;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -1;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", "Check-charges",
					null, "Drop" };
			itemDef.modelID = 81367;
			itemDef.anInt165 = 82367;
			itemDef.anInt200 = 82426;
			break;
		case 3469:
			itemDef.name = "Superior sea singer's hood";
			itemDef.modelZoom = 782;
			itemDef.modelRotation1 = 230;
			itemDef.modelRotation2 = 1907;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = 35;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", "Check-charges",
					null, "Drop" };
			itemDef.modelID = 82916;
			itemDef.anInt165 = 82919;
			itemDef.anInt200 = 82922;
			break;
		case 3470:
			itemDef.name = "Superior sea singer's robe top";
			itemDef.modelZoom = 1382;
			itemDef.modelRotation1 = 433;
			itemDef.modelRotation2 = 102;
			itemDef.modelOffset1 = 9;
			itemDef.modelOffset2 = 8;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", "Check-charges",
					null, "Drop" };
			itemDef.modelID = 82909;
			itemDef.anInt165 = 82921;
			itemDef.anInt200 = 82924;
			break;
		case 3471:
			itemDef.setDefaults();
			itemDef.name = "Superior sea singer's robe bottoms";
			itemDef.modelZoom = 1842;
			itemDef.modelRotation1 = 391;
			itemDef.modelRotation2 = 215;
			itemDef.modelOffset1 = 7;
			itemDef.modelOffset2 = 7;
			itemDef.certTemplateID = -1;
			itemDef.stackable = false;
			itemDef.groundActions = new String[] { null, null, "Take", null,
					null };
			itemDef.itemActions = new String[] { null, "Wear", "Check-charges",
					null, "Drop" };
			itemDef.modelID = 82912;
			itemDef.anInt165 = 82920;
			itemDef.anInt200 = 82923;
			break;
		case 8012:
			itemDef.name = "Target teleport";
			itemDef.description = "Teleports you to your target in Bounty Hunter.";
			break;
		case 13858:
			itemDef.modelID = 67488;
			itemDef.name = "Zuriel's robe top";
			itemDef.description = "Zuriel's robe top";
			itemDef.modelZoom = 1400;
			itemDef.modelRotation1 = 484;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 4;
			itemDef.anInt165 = 66693;
			itemDef.anInt200 = 67085;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13861:
			itemDef.modelID = 67486;
			itemDef.name = "Zuriel's robe bottom";
			itemDef.description = "Zuriel's robe bottom.";
			itemDef.modelZoom = 1500;
			itemDef.modelRotation1 = 333;
			itemDef.modelRotation2 = 201;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 9;
			itemDef.anInt165 = 66604;
			itemDef.anInt200 = 66994;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13864:
			itemDef.modelID = 67487;
			itemDef.name = "Zuriel's hood";
			itemDef.description = "Zuriel's hood.";
			itemDef.modelZoom = 720;
			itemDef.modelRotation1 = 176;
			itemDef.modelRotation2 = 207;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 5;
			itemDef.anInt165 = 66486;
			itemDef.anInt200 = 66869;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 20090:
			itemDef.name = "Legends Cape";
			itemDef.description = "A Cape For Donors";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.modelID = 82;
			itemDef.anInt165 = 83;
			itemDef.anInt200 = 83;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			break;
		case 19336:
			itemDef.modelID = 61414;
			itemDef.name = "Dragon Full Helm (G)";
			itemDef.description = "A helmet crafted of gold and an elegant dragon metal.";
			itemDef.modelZoom = 672;
			itemDef.modelRotation1 = 85;
			itemDef.modelRotation2 = 1867;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -1;
			itemDef.anInt165 = 61347;
			itemDef.anInt200 = 61347;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 19338:
			itemDef.modelID = 61394;
			itemDef.name = "Dragon Platelegs (G)";
			itemDef.description = "Platelegs crafted of gold and an elegant dragon metal.";
			itemDef.modelZoom = 1740;
			itemDef.modelRotation1 = 474;
			itemDef.modelRotation2 = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -5;
			itemDef.anInt165 = 61350;
			itemDef.anInt200 = 61350;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 19337:
			itemDef.modelID = 61395;
			itemDef.name = "Dragon Platebody (g)";
			itemDef.description = "A platebody crafted of gold and an elegant dragon metal.";
			itemDef.modelZoom = 1506;
			itemDef.modelRotation1 = 473;
			itemDef.modelRotation2 = 2042;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.anInt165 = 61356;
			itemDef.anInt200 = 61356;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 6916:
			itemDef.modelID = 61431;
			itemDef.name = "Infinity top";
			itemDef.description = "Infinity robe top";
			itemDef.modelZoom = 1340;
			itemDef.modelRotation1 = 512;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 9;
			itemDef.anInt165 = 61357;
			itemDef.anInt200 = -1;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 6198:
			itemDef.modelID = 61434;
			itemDef.name = "Infinity Hat";
			itemDef.description = "Infinity Hat";
			itemDef.modelZoom = 830;
			itemDef.modelRotation1 = 125;
			itemDef.modelRotation2 = 54;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -6;
			itemDef.anInt165 = 61348;
			itemDef.anInt200 = -1;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";

		case 6920:
			itemDef.modelID = 61419;
			itemDef.name = "Infinity Boots";
			itemDef.description = "Infinity Boots";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 176;
			itemDef.modelRotation2 = 1967;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = -24;
			itemDef.anInt165 = 61336;
			itemDef.anInt200 = -1;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 11728:
			itemDef.modelID = 67131;
			itemDef.name = "Bandos boots";
			itemDef.description = "A pair of bandos boots.";
			itemDef.modelZoom = 743;
			itemDef.modelRotation1 = 158;
			itemDef.modelRotation2 = 159;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -7;
			itemDef.anInt165 = 66320;
			itemDef.anInt200 = 67866;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 6922:
			itemDef.modelID = 61427;
			itemDef.name = "Infinity Gloves";
			itemDef.description = "Infinity Gloves";
			itemDef.modelZoom = 804;
			itemDef.modelRotation1 = 452;
			itemDef.modelRotation2 = 54;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -1;
			itemDef.anInt165 = 61338;
			itemDef.anInt200 = -1;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 6924:
			itemDef.modelID = 61430;
			itemDef.name = "Infinity Bottoms";
			itemDef.description = "Infinity Bottoms";
			itemDef.modelZoom = 1828;
			itemDef.modelRotation1 = 512;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset1 = 2;
			itemDef.modelOffset2 = -4;
			itemDef.anInt165 = 61351;
			itemDef.anInt200 = -1;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			;
			break;

		case 20030:
			itemDef.modelID = 61418;
			itemDef.name = "Dragon Gloves (or)";
			itemDef.description = "A Glove crafted of gold and an elegant dragon metal.";
			itemDef.modelZoom = 658;
			itemDef.modelRotation1 = 302;
			itemDef.modelRotation2 = 1377;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -1;
			itemDef.anInt165 = 61337;
			itemDef.anInt200 = 61337;
			break;
		case 11720:
			itemDef.modelID = 61452;
			itemDef.name = "Armadyl Chestplate";
			itemDef.description = "Armadyl Chestplate.";
			itemDef.modelZoom = 1740;
			itemDef.modelRotation1 = 474;
			itemDef.modelRotation2 = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -5;
			itemDef.anInt165 = 61451;
			itemDef.anInt188 = 61451;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 11722:
			itemDef.modelID = 61361;
			itemDef.name = "Armadyl plateskirt";
			itemDef.description = "Armadyl plateskirt.";
			itemDef.modelZoom = 1740;
			itemDef.modelRotation1 = 474;
			itemDef.modelRotation2 = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -5;
			itemDef.anInt165 = 61445;
			itemDef.anInt200 = 61445;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 20015:
			itemDef.name = "Robin hood hat";
			itemDef.modelID = 3021;
			itemDef.modifiedModelColors = new int[3];
			itemDef.originalModelColors = new int[3];
			itemDef.modifiedModelColors[0] = 15009;
			itemDef.originalModelColors[0] = 3745;
			itemDef.modifiedModelColors[1] = 17294;
			itemDef.originalModelColors[1] = 3982;
			itemDef.modifiedModelColors[2] = 15252;
			itemDef.originalModelColors[2] = 3988;
			itemDef.modelZoom = 650;
			itemDef.modelRotation1 = 2044;
			itemDef.modelRotation2 = 256;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -5;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.anInt165 = 3378;
			itemDef.anInt200 = 3382;
			itemDef.anInt175 = 3378;
			itemDef.anInt197 = 3382;
			break;

		case 14001: // black
			itemDef.modelID = 65270;
			itemDef.name = "@bla@Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.anInt165 = 65297;
			itemDef.anInt200 = 65316;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modifiedModelColors = new int[4];
			itemDef.originalModelColors = new int[4];
			itemDef.modifiedModelColors[0] = 65214;
			itemDef.modifiedModelColors[1] = 65200;
			itemDef.modifiedModelColors[2] = 65186;
			itemDef.modifiedModelColors[3] = 62995;
			itemDef.originalModelColors[0] = 1; // top
			itemDef.originalModelColors[1] = 1; // top
			itemDef.originalModelColors[2] = 1; // bottom
			itemDef.originalModelColors[3] = 1; // bottom
			break;

		case 14002: // grey
			itemDef.modelID = 65270;
			itemDef.name = "Grey Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.anInt165 = 65297;
			itemDef.anInt200 = 65316;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modifiedModelColors = new int[4];
			itemDef.originalModelColors = new int[4];
			itemDef.modifiedModelColors[0] = 65214;
			itemDef.modifiedModelColors[1] = 65200;
			itemDef.modifiedModelColors[2] = 65186;
			itemDef.modifiedModelColors[3] = 62995;
			itemDef.originalModelColors[0] = 10388; // top
			itemDef.originalModelColors[1] = 10388; // top
			itemDef.originalModelColors[2] = 10388; // bottom
			itemDef.originalModelColors[3] = 10388; // bottom
			break;

		case 14003: // white
			itemDef.modelID = 65270;
			itemDef.name = "@whi@Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.anInt165 = 65297;
			itemDef.anInt200 = 65316;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modifiedModelColors = new int[4];
			itemDef.originalModelColors = new int[4];
			itemDef.modifiedModelColors[0] = 65214;
			itemDef.modifiedModelColors[1] = 65200;
			itemDef.modifiedModelColors[2] = 65186;
			itemDef.modifiedModelColors[3] = 62995;
			itemDef.originalModelColors[0] = 127; // top
			itemDef.originalModelColors[1] = 127; // top
			itemDef.originalModelColors[2] = 127; // bottom
			itemDef.originalModelColors[3] = 127; // bottom
			break;

		case 14004: // blue
			itemDef.modelID = 65270;
			itemDef.name = "@blu@Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.anInt165 = 65297;
			itemDef.anInt200 = 65316;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modifiedModelColors = new int[4];
			itemDef.originalModelColors = new int[4];
			itemDef.modifiedModelColors[0] = 65214;
			itemDef.modifiedModelColors[1] = 65200;
			itemDef.modifiedModelColors[2] = 65186;
			itemDef.modifiedModelColors[3] = 62995;
			itemDef.originalModelColors[0] = 43848; // top
			itemDef.originalModelColors[1] = 43848; // top
			itemDef.originalModelColors[2] = 43848; // bottom
			itemDef.originalModelColors[3] = 43848; // bottom
			break;

		case 14005: // green
			itemDef.modelID = 65270;
			itemDef.name = "@gre@Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.anInt165 = 65297;
			itemDef.anInt200 = 65316;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modifiedModelColors = new int[4];
			itemDef.originalModelColors = new int[4];
			itemDef.modifiedModelColors[0] = 65214;
			itemDef.modifiedModelColors[1] = 65200;
			itemDef.modifiedModelColors[2] = 65186;
			itemDef.modifiedModelColors[3] = 62995;
			itemDef.originalModelColors[0] = 22428; // top
			itemDef.originalModelColors[1] = 22428; // top
			itemDef.originalModelColors[2] = 22428; // bottom
			itemDef.originalModelColors[3] = 22428; // bottom
			break;

		case 14006: // cyan
			itemDef.modelID = 65270;
			itemDef.name = "@cya@Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.anInt165 = 65297;
			itemDef.anInt200 = 65316;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modifiedModelColors = new int[4];
			itemDef.originalModelColors = new int[4];
			itemDef.modifiedModelColors[0] = 65214;
			itemDef.modifiedModelColors[1] = 65200;
			itemDef.modifiedModelColors[2] = 65186;
			itemDef.modifiedModelColors[3] = 62995;
			itemDef.originalModelColors[0] = 34503; // top
			itemDef.originalModelColors[1] = 34503; // top
			itemDef.originalModelColors[2] = 34503; // bottom
			itemDef.originalModelColors[3] = 34503; // bottom
			break;

		case 14007: // red
			itemDef.modelID = 65270;
			itemDef.name = "@red@Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.anInt165 = 65297;
			itemDef.anInt200 = 65316;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modifiedModelColors = new int[4];
			itemDef.originalModelColors = new int[4];
			itemDef.modifiedModelColors[0] = 65214;
			itemDef.modifiedModelColors[1] = 65200;
			itemDef.modifiedModelColors[2] = 65186;
			itemDef.modifiedModelColors[3] = 62995;
			itemDef.originalModelColors[0] = 926; // top
			itemDef.originalModelColors[1] = 926; // top
			itemDef.originalModelColors[2] = 926; // bottom
			itemDef.originalModelColors[3] = 926; // bottom
			break;

		case 14008: // purple
			itemDef.modelID = 65270;
			itemDef.name = "Purple Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.anInt165 = 65297;
			itemDef.anInt200 = 65316;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modifiedModelColors = new int[4];
			itemDef.originalModelColors = new int[4];
			itemDef.modifiedModelColors[0] = 65214;
			itemDef.modifiedModelColors[1] = 65200;
			itemDef.modifiedModelColors[2] = 65186;
			itemDef.modifiedModelColors[3] = 62995;
			itemDef.originalModelColors[0] = 44603; // top
			itemDef.originalModelColors[1] = 44603; // top
			itemDef.originalModelColors[2] = 44603; // bottom
			itemDef.originalModelColors[3] = 44603; // bottom
			break;

		case 14009: // yellow
			itemDef.modelID = 65270;
			itemDef.name = "@yel@Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.anInt165 = 65297;
			itemDef.anInt200 = 65316;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modifiedModelColors = new int[4];
			itemDef.originalModelColors = new int[4];
			itemDef.modifiedModelColors[0] = 65214;
			itemDef.modifiedModelColors[1] = 65200;
			itemDef.modifiedModelColors[2] = 65186;
			itemDef.modifiedModelColors[3] = 62995;
			itemDef.originalModelColors[0] = 10939; // top
			itemDef.originalModelColors[1] = 10939; // top
			itemDef.originalModelColors[2] = 10939; // bottom
			itemDef.originalModelColors[3] = 10939; // bottom
			break;

		case 14010: // orange
			itemDef.modelID = 65270;
			itemDef.name = "@or2@Completionist cape";
			itemDef.modelZoom = 1316;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.stackable = false;
			itemDef.value = 19264;
			itemDef.membersObject = true;
			itemDef.anInt165 = 65297;
			itemDef.anInt200 = 65316;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modifiedModelColors = new int[4];
			itemDef.originalModelColors = new int[4];
			itemDef.modifiedModelColors[0] = 65214;
			itemDef.modifiedModelColors[1] = 65200;
			itemDef.modifiedModelColors[2] = 65186;
			itemDef.modifiedModelColors[3] = 62995;
			itemDef.originalModelColors[0] = 3016; // top
			itemDef.originalModelColors[1] = 3016; // top
			itemDef.originalModelColors[2] = 3016; // bottom
			itemDef.originalModelColors[3] = 3016; // bottom
			break;

		case 20016:
			itemDef.name = "Robin hood hat";
			itemDef.modelID = 3021;
			itemDef.modifiedModelColors = new int[3];
			itemDef.originalModelColors = new int[3];
			itemDef.modifiedModelColors[0] = 15009;
			itemDef.originalModelColors[0] = 30847;
			itemDef.modifiedModelColors[1] = 17294;
			itemDef.originalModelColors[1] = 32895;
			itemDef.modifiedModelColors[2] = 15252;
			itemDef.originalModelColors[2] = 30847;
			itemDef.modelZoom = 650;
			itemDef.modelRotation1 = 2044;
			itemDef.modelRotation2 = 256;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = -2;
			itemDef.anInt165 = 3378;
			itemDef.anInt200 = 3382;
			itemDef.anInt175 = 3378;
			itemDef.anInt197 = 3382;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 20017:
			itemDef.name = "Robin hood hat";
			itemDef.modelID = 3021;
			itemDef.modifiedModelColors = new int[3];
			itemDef.originalModelColors = new int[3];
			itemDef.modifiedModelColors[0] = 15009;
			itemDef.originalModelColors[0] = 10015;
			itemDef.modifiedModelColors[1] = 17294;
			itemDef.originalModelColors[1] = 7730;
			itemDef.modifiedModelColors[2] = 15252;
			itemDef.originalModelColors[2] = 7973;
			itemDef.modelZoom = 650;
			itemDef.modelRotation1 = 2044;
			itemDef.modelRotation2 = 256;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = -2;
			itemDef.anInt165 = 3378;
			itemDef.anInt200 = 3382;
			itemDef.anInt175 = 3378;
			itemDef.anInt197 = 3382;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 20018:
			itemDef.name = "Robin hood hat";
			itemDef.modelID = 3021;
			itemDef.modifiedModelColors = new int[3];
			itemDef.originalModelColors = new int[3];
			itemDef.modifiedModelColors[0] = 15009;
			itemDef.originalModelColors[0] = 35489;
			itemDef.modifiedModelColors[1] = 17294;
			itemDef.originalModelColors[1] = 37774;
			itemDef.modifiedModelColors[2] = 15252;
			itemDef.originalModelColors[2] = 35732;
			itemDef.modelZoom = 650;
			itemDef.modelRotation1 = 2044;
			itemDef.modelRotation2 = 256;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = -2;
			itemDef.anInt165 = 3378;
			itemDef.anInt200 = 3382;
			itemDef.anInt175 = 3378;
			itemDef.anInt197 = 3382;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 20073:

			itemDef.modelID = 2635;
			itemDef.name = "Black Party-Hat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.membersObject = true;
			itemDef.anInt165 = 187;
			itemDef.anInt200 = 363;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 926;
			itemDef.originalModelColors[0] = 1;
			break;
		case 20074:

			itemDef.modelID = 2635;
			itemDef.name = "Pink Party-Hat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.membersObject = true;
			itemDef.anInt165 = 187;
			itemDef.anInt200 = 363;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 926;
			itemDef.originalModelColors[0] = 350;
			break;
		case 20075:
			itemDef.modelID = 2635;
			itemDef.name = "Sky Blue Party-Hat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.membersObject = true;
			itemDef.anInt165 = 187;
			itemDef.anInt200 = 363;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 926;
			itemDef.originalModelColors[0] = 689484;
			break;
		case 20076:
			itemDef.modelID = 9001;
			itemDef.name = "Rainbow Partyhat";
			itemDef.description = "Rainbow Partyhat.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 1;
			itemDef.anInt165 = 9000;
			itemDef.anInt200 = 9002;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 20077:
			itemDef.modelID = 2635;
			itemDef.name = "Ice Party-Hat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.membersObject = true;
			itemDef.anInt165 = 187;
			itemDef.anInt200 = 363;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 926;
			itemDef.originalModelColors[0] = 31600;
			break;
		case 20078:
			itemDef.modelID = 2635;
			itemDef.name = "Brown Party-Hat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.membersObject = true;
			itemDef.anInt165 = 187;
			itemDef.anInt200 = 363;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 926;
			itemDef.originalModelColors[0] = 5000;
			break;
		case 20079:
			itemDef.modelID = 2635;
			itemDef.name = "Diamond Party-Hat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.membersObject = true;
			itemDef.anInt165 = 187;
			itemDef.anInt200 = 363;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 926;
			itemDef.originalModelColors[0] = 690000;
			break;
		case 20080:
			itemDef.modelID = 2635;
			itemDef.name = "Lava Party-Hat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.membersObject = true;
			itemDef.anInt165 = 187;
			itemDef.anInt200 = 363;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 926;
			itemDef.originalModelColors[0] = 6073;
			break;
		case 20081:
			itemDef.modelID = 2635;
			itemDef.name = "Lime Party-Hat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.membersObject = true;
			itemDef.anInt165 = 187;
			itemDef.anInt200 = 363;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 926;
			itemDef.originalModelColors[0] = 17350;
			break;
		case 20082:
			itemDef.modelID = 2635;
			itemDef.name = "italo Party-Hat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.membersObject = true;
			itemDef.anInt165 = 187;
			itemDef.anInt200 = 363;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 926;
			itemDef.originalModelColors[0] = 23970;
			break;
		case 20083:
			itemDef.modelID = 2635;
			itemDef.name = "Bandos Party-Hat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.membersObject = true;
			itemDef.anInt165 = 187;
			itemDef.anInt200 = 363;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 926;
			itemDef.originalModelColors[0] = 10;
			break;
		case 20084:
			itemDef.modelID = 15003;
			itemDef.name = "Sniper";
			itemDef.description = "A Sniper Rifle.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 1845;
			itemDef.modelRotation2 = 121;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 1;
			itemDef.anInt165 = 9150;
			itemDef.anInt200 = 9150;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 20087:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 17350;
			itemDef.modifiedModelColors[0] = 933;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.modelRotation1 = 72;
			itemDef.modelRotation2 = 136;
			itemDef.anInt204 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.anInt165 = 189;
			itemDef.anInt200 = 366;
			itemDef.anInt188 = -1;
			itemDef.anInt164 = -1;
			itemDef.anInt175 = -1;
			itemDef.anInt197 = -1;
			itemDef.name = "Lime santa hat";
			itemDef.description = "It's a Lime santa hat.";
			break;
		case 10286:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 6020;
			itemDef.modifiedModelColors[0] = 933;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.modelRotation1 = 72;
			itemDef.modelRotation2 = 136;
			itemDef.anInt204 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.anInt165 = 189;
			itemDef.anInt200 = 366;
			itemDef.anInt188 = -1;
			itemDef.anInt164 = -1;
			itemDef.anInt175 = -1;
			itemDef.anInt197 = -1;
			itemDef.name = "Black santa hat";
			itemDef.description = "It's a Black santa hat.";
			break;
		case 2599:
			itemDef.groundActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modelZoom = 1645;
			itemDef.modelRotation1 = 500;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = 4;
			itemDef.anInt165 = 79914;
			itemDef.anInt200 = 79915;
			itemDef.modelID = 79916;
			itemDef.name = "Tetsu Platebody";
			itemDef.description = "A body made of ancient scraps.";
			break;
		case 2601:
			itemDef.groundActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modelZoom = 2039;
			itemDef.modelRotation1 = 500;
			itemDef.modelRotation2 = 148;
			itemDef.modelOffset1 = 14;
			itemDef.modelOffset2 = 4;
			itemDef.anInt165 = 79917;
			itemDef.anInt200 = 79918;
			itemDef.modelID = 79919;
			itemDef.name = "Tetsu Platelegs";
			itemDef.description = "Tetsu Platelegs.";
			break;
		case 2605:
			itemDef.groundActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Customise";
			itemDef.itemActions[3] = "Features";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modelZoom = 724;
			itemDef.modelRotation1 = 15;
			itemDef.modelRotation2 = 75;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -10;
			itemDef.anInt165 = 79920;
			itemDef.anInt200 = 79921;
			itemDef.modelID = 79920;
			itemDef.name = "Tetsu Helmet";
			itemDef.description = "Tetsu's Helmet";
			break;
		case 686:
			itemDef.name = "Admin's Blade";
			itemDef.description = "Sword filled with the power of the divines! -Mod Jesse";
			itemDef.itemActions = new String[] { null, "Wield", "Heal", null,
					"Ban" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 79922;
			itemDef.anInt165 = 79922;
			itemDef.anInt200 = 79922;
			itemDef.modelZoom = 1829;
			itemDef.modelRotation1 = 513;
			itemDef.modelRotation2 = 546;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 3;
			itemDef.value = 100;
			itemDef.membersObject = true;
			break;
		case 6605:
			itemDef.name = "Sword of Hell";
			itemDef.description = "A Weapon of Revenge";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.modelID = 79923;
			itemDef.anInt165 = 79923;
			itemDef.anInt200 = 79923;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			break;
		case 6607:
			itemDef.name = "@whi@Sword Of Heaven";
			itemDef.description = "A gracious sword from the skies.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 79924;
			itemDef.anInt165 = 79925;
			itemDef.anInt200 = 79925;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation1 = 245;
			itemDef.modelRotation2 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 20085:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 17350;
			itemDef.modifiedModelColors[0] = 926;
			itemDef.modelID = 2438;
			itemDef.modelZoom = 730;
			itemDef.modelRotation1 = 516;
			itemDef.modelRotation2 = 0;
			itemDef.anInt204 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -10;
			itemDef.anInt165 = 3188;
			itemDef.anInt200 = 3192;
			itemDef.anInt188 = -1;
			itemDef.anInt164 = -1;
			itemDef.anInt175 = -1;
			itemDef.anInt197 = -1;
			itemDef.name = "Lime h'ween mask";
			itemDef.description = "It's a Lime h'ween mask.";
			break;
		case 20086:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors[0] = 6073;
			itemDef.modifiedModelColors[0] = 926;
			itemDef.modelID = 2438;
			itemDef.modelZoom = 730;
			itemDef.modelRotation1 = 516;
			itemDef.modelRotation2 = 0;
			itemDef.anInt204 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -10;
			itemDef.anInt165 = 3188;
			itemDef.anInt200 = 3192;
			itemDef.name = "Lava h'ween mask";
			itemDef.description = "It's a Lava h'ween mask.";
			break;
		case 20088:
			itemDef.modelID = 2635;
			itemDef.name = "Purple Party-Hat";
			itemDef.description = "A nice hat from a cracker.";
			itemDef.modelZoom = 440;
			itemDef.modelRotation1 = 76;
			itemDef.modelRotation2 = 1850;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.membersObject = true;
			itemDef.anInt165 = 187;
			itemDef.anInt200 = 363;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 926;
			itemDef.originalModelColors[0] = 2934671;
			break;

		case 20019:
			itemDef.name = "Staff of light";
			itemDef.modelID = 51845;
			itemDef.modifiedModelColors = new int[11];
			itemDef.originalModelColors = new int[11];
			itemDef.modifiedModelColors[0] = 7860;
			itemDef.originalModelColors[0] = 38310;
			itemDef.modifiedModelColors[1] = 7876;
			itemDef.originalModelColors[1] = 38310;
			itemDef.modifiedModelColors[2] = 7892;
			itemDef.originalModelColors[2] = 38310;
			itemDef.modifiedModelColors[3] = 7884;
			itemDef.originalModelColors[3] = 38310;
			itemDef.modifiedModelColors[4] = 7868;
			itemDef.originalModelColors[4] = 38310;
			itemDef.modifiedModelColors[5] = 7864;
			itemDef.originalModelColors[5] = 38310;
			itemDef.modifiedModelColors[6] = 7880;
			itemDef.originalModelColors[6] = 38310;
			itemDef.modifiedModelColors[7] = 7848;
			itemDef.originalModelColors[7] = 38310;
			itemDef.modifiedModelColors[8] = 7888;
			itemDef.originalModelColors[8] = 38310;
			itemDef.modifiedModelColors[9] = 7872;
			itemDef.originalModelColors[9] = 38310;
			itemDef.modifiedModelColors[10] = 7856;
			itemDef.originalModelColors[10] = 38310;
			itemDef.modelZoom = 2256;
			itemDef.modelRotation2 = 456;
			itemDef.modelRotation1 = 513;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.anInt165 = 51795;
			itemDef.anInt200 = 51795;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 20020:
			itemDef.name = "Staff of light";
			itemDef.modelID = 51845;
			itemDef.modifiedModelColors = new int[11];
			itemDef.originalModelColors = new int[11];
			itemDef.modifiedModelColors[0] = 7860;
			itemDef.originalModelColors[0] = 432;
			itemDef.modifiedModelColors[1] = 7876;
			itemDef.originalModelColors[1] = 432;
			itemDef.modifiedModelColors[2] = 7892;
			itemDef.originalModelColors[2] = 432;
			itemDef.modifiedModelColors[3] = 7884;
			itemDef.originalModelColors[3] = 432;
			itemDef.modifiedModelColors[4] = 7868;
			itemDef.originalModelColors[4] = 432;
			itemDef.modifiedModelColors[5] = 7864;
			itemDef.originalModelColors[5] = 432;
			itemDef.modifiedModelColors[6] = 7880;
			itemDef.originalModelColors[6] = 432;
			itemDef.modifiedModelColors[7] = 7848;
			itemDef.originalModelColors[7] = 432;
			itemDef.modifiedModelColors[8] = 7888;
			itemDef.originalModelColors[8] = 432;
			itemDef.modifiedModelColors[9] = 7872;
			itemDef.originalModelColors[9] = 432;
			itemDef.modifiedModelColors[10] = 7856;
			itemDef.originalModelColors[10] = 432;
			itemDef.modelZoom = 2256;
			itemDef.modelRotation2 = 456;
			itemDef.modelRotation1 = 513;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.anInt165 = 51795;
			itemDef.anInt200 = 51795;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 20021:
			itemDef.name = "Staff of light";
			itemDef.modelID = 51845;
			itemDef.modifiedModelColors = new int[11];
			itemDef.originalModelColors = new int[11];
			itemDef.modifiedModelColors[0] = 7860;
			itemDef.originalModelColors[0] = 24006;
			itemDef.modifiedModelColors[1] = 7876;
			itemDef.originalModelColors[1] = 24006;
			itemDef.modifiedModelColors[2] = 7892;
			itemDef.originalModelColors[2] = 24006;
			itemDef.modifiedModelColors[3] = 7884;
			itemDef.originalModelColors[3] = 24006;
			itemDef.modifiedModelColors[4] = 7868;
			itemDef.originalModelColors[4] = 24006;
			itemDef.modifiedModelColors[5] = 7864;
			itemDef.originalModelColors[5] = 24006;
			itemDef.modifiedModelColors[6] = 7880;
			itemDef.originalModelColors[6] = 24006;
			itemDef.modifiedModelColors[7] = 7848;
			itemDef.originalModelColors[7] = 24006;
			itemDef.modifiedModelColors[8] = 7888;
			itemDef.originalModelColors[8] = 24006;
			itemDef.modifiedModelColors[9] = 7872;
			itemDef.originalModelColors[9] = 24006;
			itemDef.modifiedModelColors[10] = 7856;
			itemDef.originalModelColors[10] = 24006;
			itemDef.modelZoom = 2256;
			itemDef.modelRotation2 = 456;
			itemDef.modelRotation1 = 513;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.anInt165 = 51795;
			itemDef.anInt200 = 51795;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 20022:
			itemDef.name = "Staff of light";
			itemDef.modelID = 51845;
			itemDef.modifiedModelColors = new int[11];
			itemDef.originalModelColors = new int[11];
			itemDef.modifiedModelColors[0] = 7860;
			itemDef.originalModelColors[0] = 14285;
			itemDef.modifiedModelColors[1] = 7876;
			itemDef.originalModelColors[1] = 14285;
			itemDef.modifiedModelColors[2] = 7892;
			itemDef.originalModelColors[2] = 14285;
			itemDef.modifiedModelColors[3] = 7884;
			itemDef.originalModelColors[3] = 14285;
			itemDef.modifiedModelColors[4] = 7868;
			itemDef.originalModelColors[4] = 14285;
			itemDef.modifiedModelColors[5] = 7864;
			itemDef.originalModelColors[5] = 14285;
			itemDef.modifiedModelColors[6] = 7880;
			itemDef.originalModelColors[6] = 14285;
			itemDef.modifiedModelColors[7] = 7848;
			itemDef.originalModelColors[7] = 14285;
			itemDef.modifiedModelColors[8] = 7888;
			itemDef.originalModelColors[8] = 14285;
			itemDef.modifiedModelColors[9] = 7872;
			itemDef.originalModelColors[9] = 14285;
			itemDef.modifiedModelColors[10] = 7856;
			itemDef.originalModelColors[10] = 14285;
			itemDef.modelZoom = 2256;
			itemDef.modelRotation2 = 456;
			itemDef.modelRotation1 = 513;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.anInt165 = 51795;
			itemDef.anInt200 = 51795;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 20006:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelID = 70807;
			itemDef.name = "Fishing boots";
			itemDef.modelZoom = 900;
			itemDef.modelRotation1 = 165;
			itemDef.modelRotation2 = 99;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -7;
			itemDef.anInt165 = 70795;
			itemDef.anInt200 = 70799;
			itemDef.description = "A pair of Fishing boots.";
			break;

		case 20005:
			itemDef.name = "Fishing hat";
			itemDef.modelZoom = 760;
			itemDef.modelRotation2 = 11;
			itemDef.modelRotation1 = 81;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelID = 70804;
			itemDef.anInt165 = 70796;
			itemDef.anInt200 = 70800;
			break;

		case 20007:
			itemDef.name = "Fishing Waders";
			itemDef.modelZoom = 1550;
			itemDef.modelRotation2 = 344;
			itemDef.modelRotation1 = 186;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 11;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelID = 70806;
			itemDef.anInt165 = 70797;
			itemDef.anInt200 = 70801;
			break;

		case 20008:
			itemDef.modelID = 70805;
			itemDef.name = "Fishing jacket";
			itemDef.description = "It's a Fishing jacket";
			itemDef.modelZoom = 1122;
			itemDef.modelRotation2 = 488;
			itemDef.modelRotation1 = 3;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 0;
			itemDef.anInt165 = 70798;// male
			itemDef.anInt200 = 70802;// female
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 20400:
			itemDef.name = "Ahrim's wand";
			itemDef.modelZoom = 1714;
			itemDef.modelRotation1 = 416;
			itemDef.modelRotation2 = 1961;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -1;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelID = 79077;
			itemDef.anInt165 = 75056;
			itemDef.anInt200 = 75056;
			break;
		case 20401:
			itemDef.name = "Ahrim's book";
			itemDef.modelZoom = 900;
			itemDef.modelRotation1 = 270;
			itemDef.modelRotation2 = 153;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = 0;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelID = 79076;
			itemDef.anInt165 = 74901;
			itemDef.anInt200 = 74091;
			break;
		case 20402:
			itemDef.name = "Virtus wand";
			itemDef.modelZoom = 1360;
			itemDef.modelRotation1 = 354;
			itemDef.modelRotation2 = 228;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 4;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelID = 79155;
			itemDef.anInt165 = 75065;
			itemDef.anInt200 = 75065;
			break;
		case 20406:
			itemDef.name = "Virtus book";
			itemDef.modelZoom = 790;
			itemDef.modelRotation1 = 234;
			itemDef.modelRotation2 = 87;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -22;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelID = 79154;
			itemDef.anInt165 = 74905;
			itemDef.anInt200 = 74905;
			break;
		case 11716:
			itemDef.name = "Zamorakian spear";
			itemDef.modelZoom = 2040;
			itemDef.modelRotation1 = 291;
			itemDef.modelRotation2 = 2031;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -3;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelID = 72694;
			itemDef.anInt165 = 72114;
			itemDef.anInt200 = 72114;
			break;
		case 4675:
			itemDef.name = "Ancient staff";
			itemDef.modelZoom = 2040;
			itemDef.modelRotation1 = 291;
			itemDef.modelRotation2 = 2031;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -3;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelID = 79241;
			itemDef.anInt165 = 79906;
			itemDef.anInt200 = 79906;
			break;

		case 20403:
			itemDef.modelID = 77093;
			itemDef.name = "Drygore rapier";
			itemDef.description = "A rapier created using kalphite body parts.";
			itemDef.modelZoom = 1053;
			itemDef.modelRotation1 = 228;
			itemDef.modelRotation2 = 458;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -47;
			itemDef.membersObject = true;
			itemDef.anInt165 = 67705;
			itemDef.anInt200 = 67705;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Drop";
			break;
		case 20061:
			itemDef.modelID = 66703;
			itemDef.name = "Drygore Longsword";
			itemDef.description = "A Longsword created using kalphite body parts.";
			itemDef.modelZoom = 1645;
			itemDef.modelRotation1 = 377;
			itemDef.modelRotation2 = 444;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = 0;
			itemDef.membersObject = true;
			itemDef.anInt165 = 66705;
			itemDef.anInt200 = 66705;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Drop";
			break;
		case 20060:
			itemDef.modelID = 66021;
			itemDef.name = "Drygore Mace";
			itemDef.description = "A Mace created using kalphite body parts.";
			itemDef.modelZoom = 1118;
			itemDef.modelRotation1 = 228;
			itemDef.modelRotation2 = 485;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -47;
			itemDef.membersObject = true;
			itemDef.anInt165 = 65871;
			itemDef.anInt200 = 65871;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13320:
			itemDef.modelID = 62692;
			itemDef.name = "Zaryte bow";
			itemDef.modelZoom = 1703;
			itemDef.modelRotation1 = 221;
			itemDef.modelRotation2 = 404;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -13;
			itemDef.anInt165 = 62750;
			itemDef.anInt200 = 62750;
			itemDef.aByte154 = -11;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13362:
			itemDef.modelID = 67442;
			itemDef.name = "Torva full helm";
			itemDef.description = "Torva full helm.";
			itemDef.modelZoom = 724;
			itemDef.modelRotation1 = 81;
			itemDef.modelRotation2 = 1670;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -24;
			itemDef.anInt165 = 66475;
			itemDef.anInt200 = 66858;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13358:
			itemDef.modelID = 67440;
			itemDef.name = "Torva platebody";
			itemDef.description = "Torva Platebody.";
			itemDef.modelZoom = 1513;
			itemDef.modelRotation1 = 566;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -8;
			itemDef.anInt165 = 66680;
			itemDef.anInt200 = 67072;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13360:
			itemDef.modelID = 67437;
			itemDef.name = "Torva platelegs";
			itemDef.description = "Torva platelegs.";
			itemDef.modelZoom = 1550;
			itemDef.modelRotation1 = 344;
			itemDef.modelRotation2 = 186;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 11;
			itemDef.anInt165 = 66593;
			itemDef.anInt200 = 66983;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			break;

		case 14990:
			itemDef.modelID = 62692;
			itemDef.name = "Zaryte bow";
			itemDef.modelZoom = 1703;
			itemDef.modelRotation1 = 221;
			itemDef.modelRotation2 = 404;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -13;
			itemDef.anInt165 = 62750;
			itemDef.anInt200 = 62750;
			itemDef.aByte154 = -11;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			break;

		case 9666:
		case 11814:
		case 11816:
		case 11818:
		case 11820:
		case 11822:
		case 11824:
		case 11826:
		case 11828:
		case 11830:
		case 11832:
		case 11834:
		case 11836:
		case 11838:
		case 11840:
		case 11842:
		case 11844:
		case 11846:
		case 11848:
		case 11850:
		case 11852:
		case 11854:
		case 11856:
		case 11858:
		case 11860:
		case 11862:
		case 11864:
		case 11866:
		case 11868:
		case 11870:
		case 11874:
		case 11876:
		case 11878:
		case 11882:
		case 11886:
		case 11890:
		case 11894:
		case 11898:
		case 11902:
		case 11904:
		case 11906:
		case 11926:
		case 11928:
		case 11930:
		case 11938:
		case 11942:
		case 11944:
		case 11946:
		case 14525:
		case 14527:
		case 14529:
		case 14531:
		case 19588:
		case 19592:
		case 19596:
		case 11908:
		case 11910:
		case 11912:
		case 11914:
		case 11916:
		case 11618:
		case 11920:
		case 11922:
		case 11924:
		case 11960:
		case 11962:
		case 11967:
		case 11982:
		case 19586:
		case 19584:
		case 19590:
		case 19594:
		case 19598:
			itemDef.itemActions = new String[6];
			itemDef.itemActions[0] = "Open";
			break;

		/* customs */

		case 14048:
			itemDef.name = "Dominion sword";
			itemDef.description = "It's an Dominion sword";
			itemDef.itemActions = new String[] { null, "Wield", "Check-state",
					null, "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 3832;
			itemDef.anInt165 = 2306;
			itemDef.anInt200 = 2306;
			itemDef.modelZoom = 1829;
			itemDef.modelRotation2 = 513;
			itemDef.modelRotation1 = 546;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 3;
			itemDef.value = 100;
			itemDef.membersObject = true;
			break;
		case 19003:
			itemDef.name = "Blade of InsanityX";
			itemDef.description = "It's an Dominion sword";
			itemDef.itemActions = new String[] { null, "Wield", "Check-state",
					null, "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75011;
			itemDef.anInt165 = 75010;
			itemDef.anInt200 = 75010;
			itemDef.modelZoom = 1829;
			itemDef.modelRotation2 = 513;
			itemDef.modelRotation1 = 546;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 3;
			itemDef.value = 100;
			itemDef.membersObject = true;
			break;
		case 20004:
			itemDef.name = "Dual Dharok's Greataxe";
			itemDef.description = "Dharok Got Mainstream";
			itemDef.itemActions = new String[] { null, "Wield", "Inspect",
					null, "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 64;
			itemDef.anInt165 = 66;
			itemDef.anInt200 = 66;
			itemDef.modelZoom = 1829;
			itemDef.modelRotation2 = 513;
			itemDef.modelRotation1 = 546;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 3;
			itemDef.value = 100;
			itemDef.membersObject = true;
			break;
		case 20260:
			itemDef.name = "Gravity Hammer";
			itemDef.description = "Halos Official Gravity Hammer";
			itemDef.itemActions = new String[] { null, "Wield The Beast",
					"Be Impressed By The", null, "Loose Hope..." };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 57;
			itemDef.anInt165 = 58;
			itemDef.anInt200 = 58;
			itemDef.modelZoom = 1829;
			itemDef.modelRotation2 = 513;
			itemDef.modelRotation1 = 546;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 3;
			itemDef.value = 100;
			itemDef.membersObject = true;
			break;
		case 20300:
			itemDef.name = "Oblitary Sword";
			itemDef.description = "The Sword Of A Thousand Tales...";
			itemDef.itemActions = new String[] { null, "Wield", "Battle Cry",
					null, "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 55;
			itemDef.anInt165 = 56;
			itemDef.anInt200 = 56;
			itemDef.modelZoom = 1829;
			itemDef.modelRotation2 = 513;
			itemDef.modelRotation1 = 546;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 3;
			itemDef.value = 100;
			itemDef.membersObject = true;
			break;
		case 931:
			itemDef.name = "lawn Mower";
			itemDef.description = "Karmascapes Unique Redneck Object";
			itemDef.itemActions = new String[] { null, "Use the", null, null,
					"Break your" };
			itemDef.groundActions = new String[] { null, null,
					"LETS CUT GRASS", null,
					"Don't want no grass cuttings today Y'all" };
			itemDef.modelID = 40;
			itemDef.anInt165 = 41;
			itemDef.anInt200 = 41;
			itemDef.modelZoom = 1000;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 3;
			itemDef.value = 100;
			itemDef.membersObject = true;
			break;
		case 14049:
			itemDef.name = "Dominion staff";
			itemDef.description = "It's an Dominion staff";
			itemDef.itemActions = new String[] { null, "Wield", "Check-state",
					null, "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 3829;
			itemDef.anInt165 = 2305;
			itemDef.anInt200 = 2305;
			itemDef.modelZoom = 1872;
			itemDef.modelRotation2 = 288;
			itemDef.modelRotation1 = 1685;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.value = 100;
			itemDef.membersObject = true;
			break;
		case 14050:
			itemDef.name = "Dominion crossbow";
			itemDef.description = "It's an Dominion crossbow";
			itemDef.itemActions = new String[] { null, "Wield", "Check-state",
					null, "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 3839;
			itemDef.anInt165 = 2304;
			itemDef.anInt200 = 2304;
			itemDef.modelZoom = 1490;
			itemDef.modelRotation2 = 362;
			itemDef.modelRotation1 = 791;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 1;
			itemDef.value = 100;
			itemDef.membersObject = true;
			break;
		case 19974:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15031;
			itemDef.anInt200 = 15032;
			itemDef.anInt165 = 15032;
			itemDef.modelZoom = 800;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "Taser";
			itemDef.description = "An electric shocker.";

			break;
		case 19973:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15008;
			itemDef.anInt200 = 15030;
			itemDef.anInt165 = 15030;
			itemDef.modelZoom = 800;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "Silent Colt";
			itemDef.description = "Colt .45m with a silencer";
			break;
		case 19964:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";

			itemDef.modelID = 15024;
			itemDef.anInt200 = 15026;
			itemDef.anInt165 = 15026;
			itemDef.modelZoom = 800;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "Silent Uzi";
			itemDef.description = "An uzi with a silencer attachment.";
			break;
		case 19963:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15024;
			itemDef.anInt200 = 15025;
			itemDef.anInt165 = 15025;
			itemDef.modelZoom = 800;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "Uzi";
			itemDef.description = "An sub machinegun.";
			break;
		case 19962:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15021;
			itemDef.anInt200 = 15023;
			itemDef.anInt165 = 15023;
			itemDef.modelZoom = 800;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "Silent MP5";
			itemDef.description = "An MP5 with a silencer attachment.";
			break;
		case 19961:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15021;
			itemDef.anInt200 = 15022;
			itemDef.anInt165 = 15022;
			itemDef.modelZoom = 950;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "MP5";
			itemDef.description = "A sub machinegun.";
			break;
		case 19960:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15018;
			itemDef.anInt200 = 15020;
			itemDef.anInt165 = 15020;
			itemDef.modelZoom = 2000;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "10x Dragunov";
			itemDef.description = "A dragunov with a 10x scope attachment.";
			break;
		case 19959:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15018;
			itemDef.anInt200 = 15019;
			itemDef.anInt165 = 15019;
			itemDef.modelZoom = 800;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "Dragunov";
			itemDef.description = "A powerful sniper rifle.";
			break;
		case 19958:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15014;
			itemDef.anInt200 = 15017;
			itemDef.anInt165 = 15017;
			itemDef.modelZoom = 1300;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "Silent Lr300";
			itemDef.description = "A pistol with a silencer.";
			break;
		case 19957:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15014;
			itemDef.anInt200 = 15016;
			itemDef.anInt165 = 15016;
			itemDef.modelZoom = 1300;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "Lr300 Launcher";
			itemDef.description = "A pistol with a launcher attached.";
			break;
		case 19956:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15014;
			itemDef.anInt200 = 15015;
			itemDef.anInt165 = 15015;
			itemDef.modelZoom = 1300;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "Lr300";
			itemDef.description = "A powerful assault rifle.";
			break;
		case 19955:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15010;
			itemDef.anInt200 = 15013;
			itemDef.anInt165 = 15013;
			itemDef.modelZoom = 1300;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "Silent AK-47";
			itemDef.description = "A AK-47 with a silencer.";
			break;
		case 19953:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15010;
			itemDef.anInt200 = 15011;
			itemDef.anInt165 = 15011;
			itemDef.modelZoom = 1300;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "AK-47";
			itemDef.description = "A powerful assault rifle.";
			break;
		case 19952:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";

			itemDef.modelID = 15008;
			itemDef.anInt200 = 15009;
			itemDef.anInt165 = 15009;
			itemDef.modelZoom = 1000;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.stackable = false;
			itemDef.name = "Colt45";
			itemDef.description = "A .45mm pistol";
			break;
		case 19950:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 15006;
			itemDef.anInt200 = 15007;
			itemDef.anInt165 = 15007;
			itemDef.modelZoom = 800;
			itemDef.modelRotation1 = 498;
			itemDef.modelRotation2 = 1300;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -1;
			itemDef.stackable = false;
			itemDef.name = "Desert Eagle";
			itemDef.description = "A powerful 50 cal pistol";
			break;
		case 19984:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 24;
			itemDef.anInt204 = 0;
			itemDef.modelZoom = 1616;
			itemDef.modelRotation2 = 396;
			itemDef.modelRotation1 = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;

			itemDef.anInt165 = 25;
			itemDef.anInt200 = 25;
			itemDef.anInt175 = -1;
			itemDef.anInt197 = -1;
			itemDef.name = "Double Halo Sword";
			itemDef.description = "xxx";
			break;
		case 19985:
			itemDef.name = "Annihilation";
			itemDef.description = "It's an Annihilation";
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 71202;
			itemDef.anInt165 = 71221;
			itemDef.anInt200 = 71221;
			itemDef.modelZoom = 1616;
			itemDef.modelRotation1 = 273;
			itemDef.modelRotation2 = 213;
			itemDef.modelOffset1 = -5;
			itemDef.modelOffset2 = 0;
			itemDef.value = 100;
			break;
		case 19986:
			itemDef.name = "Decimation";
			itemDef.description = "It's an Decimation";
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 71195;
			itemDef.anInt165 = 71247;
			itemDef.anInt200 = 71247;
			itemDef.modelZoom = 1382;
			itemDef.modelRotation1 = 148;
			itemDef.modelRotation2 = 808;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -13;
			itemDef.value = 100;
			break;
		case 19987:
			itemDef.name = "Obliteration";
			itemDef.description = "It's an Obliteration";
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 71203;
			itemDef.anInt165 = 71222;
			itemDef.anInt200 = 71222;
			itemDef.modelZoom = 2829;
			itemDef.modelRotation1 = 310;
			itemDef.modelRotation2 = 0;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = 4;
			itemDef.value = 100;
			break;
		case 19988:
			itemDef.modelID = 70000;
			itemDef.name = "Master Corruption";
			itemDef.description = "Attunes your soul to the powers of darkness";
			itemDef.modelZoom = 1855;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.modelRotation2 = 500;
			itemDef.modelRotation1 = 17;
			itemDef.anInt165 = 70001;
			itemDef.anInt200 = 70002;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 19989:
			itemDef.modelID = 70003;
			itemDef.name = "Master Salvation";
			itemDef.description = "Attunes your soul to the powers of light";
			itemDef.modelZoom = 1855;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.modelRotation2 = 500;
			itemDef.modelRotation1 = 17;
			itemDef.anInt165 = 70004;
			itemDef.anInt200 = 70005;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 20065:
			itemDef.name = "Mariah's Cookies";
			itemDef.description = "These Look NOMMY";
			itemDef.itemActions = new String[] { "eat", null,
					"Smell The Goodness", null, "Die Cookie" };
			itemDef.groundActions = new String[] { null, null,
					"OMG COOKIE GIMME", null, null };
			itemDef.modelID = 73008;
			itemDef.anInt165 = 73009;
			itemDef.anInt200 = 73009;
			itemDef.modelZoom = 1829;
			itemDef.modelRotation2 = 513;
			itemDef.modelRotation1 = 546;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 3;
			itemDef.value = 100;
			itemDef.membersObject = true;
			break;
		case 19992:
			itemDef.name = "Pink Dildo";
			itemDef.description = "Have fun Girls";
			itemDef.modelID = 18;
			itemDef.anInt165 = 19;
			itemDef.anInt200 = 19;
			itemDef.modelZoom = 1316;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 19993:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 11;
			itemDef.anInt204 = 0;
			itemDef.modelZoom = 1616;
			itemDef.modelRotation2 = 396;
			itemDef.modelRotation1 = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;

			itemDef.anInt165 = 12;
			itemDef.anInt200 = 12;
			itemDef.anInt175 = -1;
			itemDef.anInt197 = -1;
			itemDef.name = "Halo Sword";
			itemDef.description = ".";
			break;
		case 18743:
			itemDef.modelID = 3288;
			itemDef.name = "Skiller Cape";
			itemDef.modelZoom = 1385;
			itemDef.modelRotation2 = 500;
			itemDef.modelRotation1 = 2000;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.value = 1;
			itemDef.membersObject = true;
			itemDef.anInt165 = 3287;
			itemDef.anInt200 = 3287;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Skull";
			itemDef.itemActions[4] = "Drop";
			itemDef.anInt175 = 14;
			itemDef.anInt197 = 7;
			break;
		case 12743:
			itemDef.modelID = 6;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelZoom = 2000;
			itemDef.modelRotation2 = 500;
			itemDef.modelRotation1 = 0;
			itemDef.anInt204 = 14;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffset2 = 1;
			itemDef.anInt165 = 7;
			itemDef.anInt200 = 7;
			itemDef.name = "@red@ Donator Cape @red@";
			itemDef.description = "Donator Cape";
			break;
		case 12742:
			itemDef.modelID = 4;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelZoom = 2000;
			itemDef.modelRotation2 = 500;
			itemDef.modelRotation1 = 0;
			itemDef.anInt204 = 14;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffset2 = 1;
			itemDef.anInt165 = 5;
			itemDef.anInt200 = 5;
			itemDef.name = "@gre@ Donator Cape @gre@";
			itemDef.description = "Donator Cape";
			break;
		case 19001:
			itemDef.name = "Levitation Dagger";
			itemDef.description = "A Dagger forged by the Gods.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75009;
			itemDef.anInt165 = 75009;
			itemDef.anInt200 = 75009;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19002:
			itemDef.name = "Sword of Ages";
			itemDef.description = "It's a Sword of Ages.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75010;
			itemDef.anInt165 = 75010;
			itemDef.anInt200 = 75010;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19004:
			itemDef.name = "Trigor Warspear";
			itemDef.description = "Trigor Warspear";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75012;
			itemDef.anInt165 = 75013;
			itemDef.anInt200 = 75013;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19007:
			itemDef.name = "Hexa Sword";
			itemDef.description = "It's A Sword.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75014;
			itemDef.anInt165 = 75015;
			itemDef.anInt200 = 75015;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19008:
			itemDef.name = "Saradomin Light";
			itemDef.description = "It's StarLight Sword.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75017;
			itemDef.anInt165 = 75016;
			itemDef.anInt200 = 75016;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19009:
			itemDef.name = "Chainsaw";
			itemDef.description = "It's a chainsaw.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75018;
			itemDef.anInt165 = 75019;
			itemDef.anInt200 = 75019;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19010:
			itemDef.name = "Doom BattleAxe";
			itemDef.description = "It's A Sword.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75020;
			itemDef.anInt165 = 75021;
			itemDef.anInt200 = 75021;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19011:
			itemDef.name = "Dragon GodSword";
			itemDef.description = "It's A Sword.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75023;
			itemDef.anInt165 = 75022;
			itemDef.anInt200 = 75022;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19012:
			itemDef.name = "Double Battle Axe";
			itemDef.description = "It's An Axe.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75024;
			itemDef.anInt165 = 75025;
			itemDef.anInt200 = 75025;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19013:
			itemDef.name = "The Blue Curve";
			itemDef.description = "It's a E Sword.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75026;
			itemDef.anInt165 = 75027;
			itemDef.anInt200 = 75027;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19014:
			itemDef.name = "FlameBow";
			itemDef.description = "It's a E Sword.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75028;
			itemDef.anInt165 = 75029;
			itemDef.anInt200 = 75029;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19015:
			itemDef.name = "DoomSword";
			itemDef.description = "It's a Doom Sword.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75030;
			itemDef.anInt165 = 75031;
			itemDef.anInt200 = 75031;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19016:
			itemDef.name = "Sword Of Hell";
			itemDef.description = "It's a Hell Sword.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75032;
			itemDef.anInt165 = 75033;
			itemDef.anInt200 = 75033;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 667:
			itemDef.name = "Divine Spear";
			itemDef.description = "It's a spear of the divines.";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75032;
			itemDef.anInt165 = 79911;
			itemDef.anInt200 = 79911;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19018:
			itemDef.name = "Chaotic Claw MainHand";
			itemDef.description = "It's a Claw!";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75039;
			itemDef.anInt165 = 75038;
			itemDef.anInt200 = 75038;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19019:
			itemDef.name = "Chaotic Claw OffHand";
			itemDef.description = "It's a Claw!";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75037;
			itemDef.anInt165 = 75036;
			itemDef.anInt200 = 75036;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19020:
			itemDef.name = "Monkey Ring";
			itemDef.description = "It's an Monkey Ring";
			itemDef.itemActions = new String[] { null, "Wear", "Customise",
					"Features", "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75040;
			itemDef.anInt165 = 75041;
			itemDef.anInt200 = 75041;
			itemDef.modelZoom = 1316;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.membersObject = true;
			break;
		case 4315:
			itemDef.name = "Mods Cape";
			itemDef.description = "For Player Moderators.";
			itemDef.itemActions = new String[] { null, "Wear", "Customise",
					"Features", "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75042;
			itemDef.anInt165 = 79910;
			itemDef.anInt200 = 79910;
			itemDef.modelZoom = 1316;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.membersObject = true;
			break;
		case 4317:
			itemDef.name = "Irons Cape";
			itemDef.description = "For the iron accounts.";
			itemDef.itemActions = new String[] { null, "Wear", "Customise",
					"Features", "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75042;
			itemDef.anInt165 = 79911;
			itemDef.anInt200 = 79911;
			itemDef.modelZoom = 1316;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.membersObject = true;
			break;
		case 4319:
			itemDef.name = "Slayer Mastery Cape";
			itemDef.description = "For those who slay to much.";
			itemDef.itemActions = new String[] { null, "Wear", "Customise",
					"Features", "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75042;
			itemDef.anInt165 = 79913;
			itemDef.anInt200 = 79914;
			itemDef.modelZoom = 1316;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.membersObject = true;
			break;
		case 19021:
			itemDef.name = "Admin Cape";
			itemDef.description = "Only for the staff of Divination of Gods";
			itemDef.itemActions = new String[] { null, "Wear", "Customise",
					"Features", "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 79907;
			itemDef.anInt165 = 79907;
			itemDef.anInt200 = 79907;
			itemDef.modelZoom = 1316;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.membersObject = true;
			break;
		case 19022:
			itemDef.name = "Forum Mod Cape";
			itemDef.description = "It's a cape for Forum Mods";
			itemDef.itemActions = new String[] { null, "Wear", "Customise",
					"Features", "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75047;
			itemDef.anInt165 = 75047;
			itemDef.anInt200 = 75047;
			itemDef.modelZoom = 1316;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.value = 5000000;
			itemDef.membersObject = true;
			break;
		case 19023:
			itemDef.name = "Mod Cape";
			itemDef.description = "It's an Mod's Cape";
			itemDef.itemActions = new String[] { null, "Wear", "Customise",
					"Features", "Destroy" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75045;
			itemDef.anInt165 = 75045;
			itemDef.anInt200 = 75045;
			itemDef.modelZoom = 1316;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.value = 5000000;
			itemDef.membersObject = true;
			break;
		case 19027:
			itemDef.modelID = 75056;
			itemDef.name = "Reggae Wings";
			itemDef.modelZoom = 1385;
			itemDef.modelRotation2 = 500;
			itemDef.modelRotation1 = 2000;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.stackable = false;
			itemDef.value = 1;
			itemDef.membersObject = true;
			itemDef.anInt165 = 75057;
			itemDef.anInt200 = 75057;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Skull";
			itemDef.itemActions[4] = "Drop";
			itemDef.anInt175 = 14;
			itemDef.anInt197 = 7;
			break;
		case 19028:
			itemDef.name = "Reggae Claws";
			itemDef.description = "It's a Reggae pair of Claws!";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75048;
			itemDef.anInt165 = 75049;
			itemDef.anInt200 = 75049;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19029:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 75050;
			itemDef.modelZoom = 540;
			itemDef.modelRotation2 = 72;
			itemDef.modelRotation1 = 136;
			itemDef.anInt204 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.anInt165 = 75051;
			itemDef.anInt200 = 75051;
			itemDef.anInt188 = -1;
			itemDef.anInt164 = -1;
			itemDef.anInt175 = -1;
			itemDef.anInt197 = -1;
			itemDef.name = "Reggae Hat";
			itemDef.description = "It's a Reggae Hat.";
			break;
		case 19030:
			itemDef.name = "Connors Whip";
			itemDef.description = "It's a Whip Made for and by Connor!";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75054;
			itemDef.anInt165 = 75055;
			itemDef.anInt200 = 75055;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19031:
			itemDef.name = "Twin Ghosties";
			itemDef.description = "Its a Ghosty";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75058;
			itemDef.anInt165 = 75059;
			itemDef.anInt200 = 75059;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19032:
			itemDef.modelID = 75065;
			itemDef.name = "Blue Angel Wings";
			itemDef.modelZoom = 850;
			itemDef.modelRotation1 = 252;
			itemDef.modelRotation2 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.anInt165 = 75064;
			itemDef.anInt165 = 75064;
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			break;
		case 19034:
			itemDef.modelID = 75068;
			itemDef.name = "Brown Wings";
			itemDef.modelZoom = 850;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.membersObject = true;
			itemDef.anInt165 = 75069;
			itemDef.anInt200 = 75069;
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			break;
		case 19035:
			itemDef.modelID = 75070;
			itemDef.name = "Black Wings";
			itemDef.modelZoom = 850;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.membersObject = true;
			itemDef.anInt165 = 75071;
			itemDef.anInt200 = 75071;
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			break;
		case 19036:
			itemDef.modelID = 75078;
			itemDef.name = "Yellow Angel Wings";
			itemDef.modelZoom = 850;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.anInt165 = 75077;
			itemDef.anInt200 = 75077;
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			break;
		case 19037:
			itemDef.modelID = 75079;
			itemDef.name = "Purple Angel Wings";
			itemDef.modelZoom = 850;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.anInt165 = 75080;
			itemDef.anInt200 = 75080;
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			break;
		case 19038:
			itemDef.modelID = 75081;
			itemDef.name = "Pink Angel Wings";
			itemDef.modelZoom = 850;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.anInt165 = 75082;
			itemDef.anInt200 = 75082;
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			break;
		case 19039:
			itemDef.modelID = 75083;
			itemDef.name = "Green Angel Wings";
			itemDef.modelZoom = 850;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.anInt165 = 75084;
			itemDef.anInt200 = 75084;
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			break;
		case 19040:
			itemDef.modelID = 75085;
			itemDef.name = "Gold Angel Wings";
			itemDef.modelZoom = 850;
			itemDef.modelRotation2 = 252;
			itemDef.modelRotation1 = 1020;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 24;
			itemDef.anInt165 = 75086;
			itemDef.anInt200 = 75086;
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			break;
		case 19041:
			itemDef.name = "Blood Blades";
			itemDef.description = "Blades of the Blood";
			itemDef.itemActions = new String[] { null, "Wear", null, null,
					"drop" };
			itemDef.groundActions = new String[] { null, null, "take", null,
					null };
			itemDef.modelID = 75087;
			itemDef.anInt165 = 75087;
			itemDef.anInt200 = 75087;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 245;
			itemDef.modelRotation1 = 1035;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.value = 1000;
			itemDef.membersObject = true;
			break;
		case 19043:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 75090;
			itemDef.modelZoom = 1579;
			itemDef.modelRotation2 = 533;
			itemDef.modelRotation1 = 333;
			itemDef.anInt165 = 75091;
			itemDef.anInt200 = 75091;
			itemDef.stackable = false;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = 0;
			itemDef.name = "@gre@Lime @pur@Death Cape";
			itemDef.description = "A cape worn by those who've overacheived in obtaining Customs.";
			break;

		}
		return itemDef;
	}

	public void toNote() {
		ItemDef itemDef = forID(certTemplateID);
		modelID = itemDef.modelID;
		modelZoom = itemDef.modelZoom;
		modelRotation1 = itemDef.modelRotation1;
		modelRotation2 = itemDef.modelRotation2;
		anInt204 = itemDef.anInt204;
		modelOffset1 = itemDef.modelOffset1;
		modelOffset2 = itemDef.modelOffset2;
		modifiedModelColors = itemDef.modifiedModelColors;
		originalModelColors = itemDef.originalModelColors;
		ItemDef itemDef_1 = forID(certID);
		name = itemDef_1.name;
		membersObject = itemDef_1.membersObject;
		value = itemDef_1.value;
		String s = "a";
		char c = itemDef_1.name.charAt(0);
		if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
			s = "an";
		description = (new StringBuilder())
				.append("Swap this note at any bank for ").append(s)
				.append(" ").append(itemDef_1.name).append(".").toString();
		stackable = true;
	}

	public static Sprite getSprite(int i, int j, int k) {
		if (k == 0) {
			Sprite sprite = (Sprite) mruNodes1.insertFromCache(i);
			if (sprite != null && sprite.maxHeight != j
					&& sprite.maxHeight != -1) {
				sprite.unlink();
				sprite = null;
			}
			if (sprite != null)
				return sprite;
		}
		ItemDef itemDef = forID(i);
		if (itemDef.stackIDs == null)
			j = -1;
		if (j > 1) {
			int i1 = -1;
			for (int j1 = 0; j1 < 10; j1++)
				if (j >= itemDef.stackAmounts[j1]
						&& itemDef.stackAmounts[j1] != 0)
					i1 = itemDef.stackIDs[j1];

			if (i1 != -1)
				itemDef = forID(i1);
		}
		Model model = itemDef.method201(1);
		if (model == null)
			return null;
		Sprite sprite = null;
		if (itemDef.certTemplateID != -1) {
			sprite = getSprite(itemDef.certID, 10, -1);
			if (sprite == null)
				return null;
		}
		if (itemDef.lendTemplateID != -1) {
			sprite = getSprite(itemDef.lendID, 50, 0);
			if (sprite == null)
				return null;
		}
		Sprite sprite2 = new Sprite(32, 32);
		int k1 = Texture.textureInt1;
		int l1 = Texture.textureInt2;
		int ai[] = Texture.anIntArray1472;
		int ai1[] = DrawingArea.pixels;
		int i2 = DrawingArea.width;
		int j2 = DrawingArea.height;
		int k2 = DrawingArea.topX;
		int l2 = DrawingArea.bottomX;
		int i3 = DrawingArea.topY;
		int j3 = DrawingArea.bottomY;
		Texture.aBoolean1464 = false;
		DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
		DrawingArea.drawPixels(32, 0, 0, 0, 32);
		Texture.method364();
		int k3 = itemDef.modelZoom;
		if (k == -1)
			k3 = (int) (k3 * 1.5D);
		if (k > 0)
			k3 = (int) (k3 * 1.04D);
		int l3 = Texture.anIntArray1470[itemDef.modelRotation1] * k3 >> 16;
		int i4 = Texture.anIntArray1471[itemDef.modelRotation1] * k3 >> 16;
		model.method482(itemDef.modelRotation2, itemDef.anInt204,
				itemDef.modelRotation1, itemDef.modelOffset1, l3
						+ model.modelHeight / 2 + itemDef.modelOffset2, i4
						+ itemDef.modelOffset2);
		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--) {
				if (sprite2.myPixels[i5 + j4 * 32] != 0)
					continue;
				if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1) {
					sprite2.myPixels[i5 + j4 * 32] = 1;
					continue;
				}
				if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1) {
					sprite2.myPixels[i5 + j4 * 32] = 1;
					continue;
				}
				if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1) {
					sprite2.myPixels[i5 + j4 * 32] = 1;
					continue;
				}
				if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1)
					sprite2.myPixels[i5 + j4 * 32] = 1;
			}

		}

		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--) {
					if (sprite2.myPixels[j5 + k4 * 32] != 0)
						continue;
					if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1) {
						sprite2.myPixels[j5 + k4 * 32] = k;
						continue;
					}
					if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1) {
						sprite2.myPixels[j5 + k4 * 32] = k;
						continue;
					}
					if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1) {
						sprite2.myPixels[j5 + k4 * 32] = k;
						continue;
					}
					if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1)
						sprite2.myPixels[j5 + k4 * 32] = k;
				}

			}

		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--)
					if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0
							&& sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0)
						sprite2.myPixels[k5 + l4 * 32] = 0x302020;

			}

		}
		if (itemDef.certTemplateID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (itemDef.lendTemplateID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (k == 0)
			mruNodes1.removeFromCache(sprite2, i);
		DrawingArea.initDrawingArea(j2, i2, ai1);
		DrawingArea.setDrawingArea(j3, k2, l2, i3);
		Texture.textureInt1 = k1;
		Texture.textureInt2 = l1;
		Texture.anIntArray1472 = ai;
		Texture.aBoolean1464 = true;
		if (itemDef.stackable)
			sprite2.maxWidth = 33;
		else
			sprite2.maxWidth = 32;
		sprite2.maxHeight = j;
		return sprite2;
	}

	public Model method201(int i) {
		if (stackIDs != null && i > 1) {
			int j = -1;
			for (int k = 0; k < 10; k++)
				if (i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];

			if (j != -1)
				return forID(j).method201(1);
		}
		Model model = (Model) mruNodes2.insertFromCache(id);
		if (model != null)
			return model;
		model = Model.method462(modelID);
		if (model == null)
			return null;
		if (anInt167 != 128 || anInt192 != 128 || anInt191 != 128)
			model.method478(anInt167, anInt191, anInt192);
		if (modifiedModelColors != null) {
			for (int l = 0; l < modifiedModelColors.length; l++)
				model.method476(modifiedModelColors[l], originalModelColors[l]);

		}
		model.method479(64 + anInt196, 768 + anInt184, -50, -10, -50, true);
		model.aBoolean1659 = true;
		mruNodes2.removeFromCache(model, id);
		return model;
	}

	public Model method202(int i) {
		if (stackIDs != null && i > 1) {
			int j = -1;
			for (int k = 0; k < 10; k++)
				if (i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];

			if (j != -1)
				return forID(j).method202(1);
		}
		Model model = Model.method462(modelID);
		if (model == null)
			return null;
		if (modifiedModelColors != null) {
			for (int l = 0; l < modifiedModelColors.length; l++)
				model.method476(modifiedModelColors[l], originalModelColors[l]);

		}
		return model;
	}

	public ItemDef() {
		id = -1;
	}

	public String barrows;
	public int lendID;
	public int lendTemplateID;
	public static String loc = signlink.findcachedir();
	public static int ITEMAMOUNT = 18635;
	public byte aByte154;
	public int value;
	public int modifiedModelColors[];
	public int id;
	static MRUNodes mruNodes1 = new MRUNodes(100);
	public static MRUNodes mruNodes2 = new MRUNodes(50);
	public int originalModelColors[];
	public boolean membersObject;
	public int anInt162;
	public int certTemplateID;
	public int anInt164;
	public int anInt165;
	public int anInt166;
	public int anInt167;
	public String groundActions[];
	public int modelOffset1;
	public String name;
	public static ItemDef cache[];
	public int anInt173;
	public int modelID;
	public int anInt175;
	public boolean stackable;
	public String description;
	public int certID;
	public static int cacheIndex;
	public int modelZoom;
	public static boolean isMembers = true;
	public static Stream stream;
	public int anInt184;
	public int anInt185;
	public int anInt188;
	public static boolean needsItemThroughCharFix = false;
	public String itemActions[];
	public int modelRotation1;
	public int anInt191;
	public int anInt192;
	public int stackIDs[];
	public int modelOffset2;
	public static int streamIndices[];
	public int anInt196;
	public int anInt197;
	public int modelRotation2;
	public int anInt200;
	public int stackAmounts[];
	public int team;
	public static int totalItems;
	public int anInt204;
	public byte aByte205;

}
