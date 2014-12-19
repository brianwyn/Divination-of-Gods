package com;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSFrame.java

import java.awt.*;

final class RSFrame extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RSFrame(RSApplet rsapplet, int width, int height,
			boolean undecorative, boolean resizable) {
		toolkit = Toolkit.getDefaultToolkit();
		screenSize = toolkit.getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight(); 
		rsApplet = rsapplet;
		setTitle("Divination of Gods");
		setUndecorated(undecorative);
		setResizable(resizable);
		setVisible(true);
		Insets insets = getInsets();
		setSize(width + insets.left + insets.right, height + insets.top
				+ insets.bottom);
		setLocation((screenWidth - width) / 2, (screenHeight - height) / 2);
		requestFocus();
		toFront();
	}
	public static RSFrame frame;
	

	public int getFrameWidth() {
		Insets insets = getInsets();
		return getWidth() - (insets.left + insets.right);
	}

	public int getFrameHeight() {
		Insets insets = getInsets();
		return getHeight() - (insets.top + insets.bottom);
	}

	public RSFrame(RSApplet rsapplet, int width, int height) {
		this(rsapplet, width, height, false, false);
	}

	@Override
	public Graphics getGraphics() {
		Graphics g = super.getGraphics();
		Insets insets = getInsets();
		g.translate(insets.left, insets.top);
		return g;
	}

	@Override
	public void update(Graphics g) {
		rsApplet.update(g);
	}

	@Override
	public void paint(Graphics g) {
		rsApplet.paint(g);
	}

	private final RSApplet rsApplet;
	public Toolkit toolkit;
	public Dimension screenSize;
	public int screenWidth;
	public int screenHeight;
}
