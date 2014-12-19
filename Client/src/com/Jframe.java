package com;

import java.net.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import javax.swing.filechooser.FileFilter;

import com.sign.signlink;

public class Jframe extends client implements ActionListener {

	private static JMenuItem menuItem;
	public static JFrame frame;
	public static JPanel gamePanel;
	public static TrayIcon trayIcon;
	public static String home = "" + System.getProperty("user.home") + "";

	public void setTray() {
		if (SystemTray.isSupported()) {

			Image icon = Toolkit.getDefaultToolkit().getImage(
					signlink.findcachedir() + "/Sprites/Cursors/icon.png");
			trayIcon = new TrayIcon(icon, "Diviantion of Gods");
			trayIcon.setImageAutoSize(true);
			try {
				SystemTray tray = SystemTray.getSystemTray();
				tray.add(trayIcon);
				trayIcon.displayMessage("Divination of Gods V1",
						"Divination has been launched!",
						TrayIcon.MessageType.INFO);
				PopupMenu menu = new PopupMenu();
				final MenuItem minimizeItem = new MenuItem("Hide Divination");
				MenuItem BLANK = new MenuItem("-");
				MenuItem exitItem = new MenuItem("Quit");
				menu.add(minimizeItem);
				menu.add(BLANK);
				menu.add(exitItem);
				trayIcon.setPopupMenu(menu);
				ActionListener minimizeListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (frame.isVisible()) {
							frame.setVisible(false);
							minimizeItem.setLabel("Show 1# Divination.");
						} else {
							frame.setVisible(true);
							minimizeItem.setLabel("Hide 1# Divination");
						}
					}
				};
				minimizeItem.addActionListener(minimizeListener);
				ActionListener exitListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				};
				exitItem.addActionListener(exitListener);
			} catch (AWTException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		try {
			if (cmd != null) {
				if (cmd.equalsIgnoreCase("Exit")) {
					System.exit(0);
				}
				if (cmd.equalsIgnoreCase("View Images")) {
					if (Desktop.isDesktopSupported()) {
						// if(Desktop.getDesktop().isSupported(Action.OPEN)) {

						Desktop.getDesktop().open(
								new File("" + home + "/InsanityCacheV5/Scre/"));
						// }
					}
				}
				if (cmd.equalsIgnoreCase("Toggle 10x Damage")) {
					newDamage = true;
				}
				if (cmd.equalsIgnoreCase("Untoggle 10x Damage")) {
					newDamage = false;
				}
				if (cmd.equalsIgnoreCase("Website")) {
					openUpWebSite("http://divinationofgods.com/");
				}
				if (cmd.equalsIgnoreCase("Forums")) {
					openUpWebSite("http://divinationofgods.com/");
				}
				if (cmd.equalsIgnoreCase("Vote")) {
					openUpWebSite("http://runelocus.com/top-rsps-list/vote-40379-Divination%20Of%20Gods/");
				}
				if (cmd.equalsIgnoreCase("Donate")) {
					openUpWebSite("http://divinationofgods.com/donate.php");
				}
				if (cmd.equalsIgnoreCase("Highscores")) {
					openUpWebSite("http://divinationofgods.com/hs/highscores.php");
				}
				if (cmd.equalsIgnoreCase("YouTube")) {
					openUpWebSite("http://youtube.com");
				}
				if (cmd.equalsIgnoreCase("Guides")) {
					openUpWebSite("http://divinationofgods.com");
				}
				if (cmd.equalsIgnoreCase("Support")) {
					openUpWebSite("http://divinationofgods.com");
					JOptionPane
							.showMessageDialog(
									this,
									"A link to our support center on our forums has Popped up. Please create a new thread!",
									"Client Support",
									JOptionPane.INFORMATION_MESSAGE);
				}
				if (cmd.equalsIgnoreCase("Client Information")) {
					JOptionPane.showMessageDialog(this, "Divination Ltd!",
							"Client Updates", JOptionPane.INFORMATION_MESSAGE);
				}
				if (cmd.equalsIgnoreCase("World Map")) {
					launchURL("2. WorldMap.bat");
				}
				if (cmd.equalsIgnoreCase("Item List")) {
					launchURL("1. ItemList.bat");
				}
				if (cmd.equalsIgnoreCase("Screenshot")) {
					takeScreenshot(true);
				}/*
				 * if (cmd.equalsIgnoreCase("Standard")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/standard1.png"); Point hotSpot = new
				 * Point(0,0); Cursor cursor = toolkit.createCustomCursor(image,
				 * hotSpot, "Standard"); setCursor(cursor); }
				 * 
				 * if (cmd.equalsIgnoreCase("Sword")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/sword.png"); Point hotSpot = new
				 * Point(0,0); Cursor cursor = toolkit.createCustomCursor(image,
				 * hotSpot, "Sword"); setCursor(cursor); }
				 * 
				 * if (cmd.equalsIgnoreCase("Wand")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/wand.png"); Point hotSpot = new Point(0,0);
				 * Cursor cursor = toolkit.createCustomCursor(image, hotSpot,
				 * "Wand"); setCursor(cursor); }
				 * 
				 * if (cmd.equalsIgnoreCase("Godsword")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/Godsword.png"); Point hotSpot = new
				 * Point(0,0); Cursor cursor = toolkit.createCustomCursor(image,
				 * hotSpot, "Godsword"); setCursor(cursor); }
				 * 
				 * if (cmd.equalsIgnoreCase("Scimitar")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/Scimitar.png"); Point hotSpot = new
				 * Point(0,0); Cursor cursor = toolkit.createCustomCursor(image,
				 * hotSpot, "Scimitar"); setCursor(cursor); }
				 * 
				 * if (cmd.equalsIgnoreCase("Maul")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/Granite maul.png"); Point hotSpot = new
				 * Point(0,5); Cursor cursor = toolkit.createCustomCursor(image,
				 * hotSpot, "Maul"); setCursor(cursor); }
				 * 
				 * if (cmd.equalsIgnoreCase("2hand")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/twohander.png"); Point hotSpot = new
				 * Point(1,1); Cursor cursor = toolkit.createCustomCursor(image,
				 * hotSpot, "2hand"); setCursor(cursor); }
				 * 
				 * if (cmd.equalsIgnoreCase("Halberd")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/Halberd.png"); Point hotSpot = new
				 * Point(0,0); Cursor cursor = toolkit.createCustomCursor(image,
				 * hotSpot, "Halberd"); setCursor(cursor); }
				 * 
				 * if (cmd.equalsIgnoreCase("Warspear")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/warspear.png"); Point hotSpot = new
				 * Point(0,0); Cursor cursor = toolkit.createCustomCursor(image,
				 * hotSpot, "Warspear"); setCursor(cursor); }
				 * 
				 * if (cmd.equalsIgnoreCase("Longsword")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/Longsword.png"); Point hotSpot = new
				 * Point(0,0); Cursor cursor = toolkit.createCustomCursor(image,
				 * hotSpot, "Longsword"); setCursor(cursor); }
				 * 
				 * if (cmd.equalsIgnoreCase("Guitar")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/guitar.png"); Point hotSpot = new
				 * Point(0,0); Cursor cursor = toolkit.createCustomCursor(image,
				 * hotSpot, "Guitar"); setCursor(cursor); }
				 * 
				 * if (cmd.equalsIgnoreCase("Pikachu")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/pikachu.png");
				 * 
				 * Point hotSpot = new Point(0,3); Cursor cursor =
				 * toolkit.createCustomCursor(image, hotSpot, "Pikachu");
				 * setCursor(cursor); }
				 * 
				 * if (cmd.equalsIgnoreCase("Kenny")) { Toolkit toolkit =
				 * Toolkit.getDefaultToolkit(); Image image =
				 * toolkit.getImage(signlink.findcachedir()+
				 * "/Sprites/Cursors/kenny.png"); Point hotSpot = new
				 * Point(0,0); Cursor cursor = toolkit.createCustomCursor(image,
				 * hotSpot, "Kenny"); setCursor(cursor); }
				 */
			}
		} catch (Exception e) {
		}
	}

	public void takeScreenshot(boolean flag) {
		BufferedImage bufferedimage;
		try {
			Robot robot = new Robot();
			Point point = getLocationOnScreen();
			Rectangle rectangle = new Rectangle(point.x, point.y, getWidth(),
					getHeight());
			bufferedimage = robot.createScreenCapture(rectangle);
		} catch (Throwable throwable) {
			JOptionPane.showMessageDialog(frame,
					"An error occured while trying to create a screenshot!",
					"Screenshot Error", 0);
			return;
		}
		String s = null;
		try {
			s = getNearestScreenshotFilename();
		} catch (IOException ioexception) {
			if (flag) {
				JOptionPane
						.showMessageDialog(
								frame,
								"A screenshot directory does not exist, and could not be created!",
								"No Screenshot Directory", 0);
				return;
			}
		}
		if (s == null && flag) {
			JOptionPane.showMessageDialog(frame,
					"There are too many screenshots in the screenshot directory!\n"
							+ "Delete some screen\n" + "shots and try again.",
					"Screenshot Directory Full", 0);
			return;
		}
		if (!flag) {
			final JFileChooser fileChooser = new JFileChooser();
			final JDialog fileDialog = createFileChooserDialog(fileChooser,
					"Save Screenshot", this);
			final BufferedImage si = bufferedimage;
			JFileChooser _tmp = fileChooser;
			fileChooser.setFileSelectionMode(0);
			fileChooser.addChoosableFileFilter(new imageFileFilter());
			fileChooser.setCurrentDirectory(new File("" + home
					+ "/InsanityXCacheV1/Scre/"));
			fileChooser.setSelectedFile(new File(s));
			JFileChooser _tmp1 = fileChooser;
			fileChooser.setDialogType(1);
			fileChooser.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent actionevent) {
					String s1 = actionevent.getActionCommand();
					if (s1.equals("ApproveSelection")) {
						File file = fileChooser.getSelectedFile();
						if (file != null && file.isFile()) {
							int i = JOptionPane
									.showConfirmDialog(
											frame,
											(new StringBuilder())
													.append(file
															.getAbsolutePath())
													.append(" already exists.\n"
															+ "Do you want to replace it?")
													.toString(),
											"Save Screenshot", 2);
							if (i != 0) {
								return;
							}
						}
						try {
							ImageIO.write(si, "png", file);
							// client.pushMessage("Screenshot taken.", 3,
							// "@cr2@ Client");
							// JOptionPane.showMessageDialog(frame,"Screenshot Taken");
						} catch (IOException ioexception2) {
							JOptionPane
									.showMessageDialog(
											frame,
											"An error occured while trying to save the screenshot!\n"
													+ "Please make sure you have\n"
													+ " write access to the screenshot directory.",
											"Screenshot Error", 0);
						}
						fileDialog.dispose();
					} else if (s1.equals("CancelSelection")) {
						fileDialog.dispose();
					}
				}

				{

				}
			});
			fileDialog.setVisible(true);
		} else {
			try {
				ImageIO.write(
						bufferedimage,
						"png",
						new File((new StringBuilder())
								.append("" + home + "/InsanityXCacheV1/Scre/")
								.append(s).toString()));
				JOptionPane
						.showMessageDialog(
								frame,
								"Image has been saved to "
										+ home
										+ "/InsanityXCacheV1/Scre/. You can view your images by pushing File>View Images in the menu dropdowns.",
								"Screenshot manager",
								JOptionPane.INFORMATION_MESSAGE);
				// client.pushMessage("Screenshot taken.", 3, "@cr2@ Client");
				// JOptionPane.showMessageDialog(frame,"Screenshot taken.");
			} catch (IOException ioexception1) {
				JOptionPane.showMessageDialog(frame,
						"An error occured while trying to save the screenshot!\n"
								+ "Please make sure you have\n"
								+ " write access to the screenshot directory.",
						"Screenshot Error", 0);
			}
		}
	}

	public static String getNearestScreenshotFilename() throws IOException {
		File file = new File("" + home + "/InsanityXCacheV1/Scre/");
		int i = 0;
		do {
			String s = " .png";
			if (i < 10) {
				s = s.replaceFirst(" ", (new StringBuilder()).append("000")
						.append(i).toString());
			} else if (i < 100) {
				s = s.replaceFirst(" ", (new StringBuilder()).append("00")
						.append(i).toString());
			} else if (i < 1000) {
				s = s.replaceFirst(" ", (new StringBuilder()).append("0")
						.append(i).toString());
			} else if (i < 10000) {
				s = s.replaceFirst(" ", (new StringBuilder()).append(" ")
						.append(i).toString());
			}
			if ((new File(file, s)).isFile()) {
				i++;
			} else {
				return s;
			}
		} while (i < 10000);
		return null;
	}

	class imageFileFilter extends FileFilter {

		imageFileFilter() {
		}

		@Override
		public boolean accept(File file) {
			String s = file.getName();
			return file.isDirectory() || s.toLowerCase().endsWith(".png")
					&& s.indexOf("$") == -1;
		}

		@Override
		public String getDescription() {
			return "PNG (*.png)";
		}
	}

	public Jframe(String args[]) {
		super();
		try {
			com.sign.signlink.startpriv(InetAddress.getByName(serverip));
			initUI();
			// setTray();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	String[] cursorsButtons = new String[] { "Standard", "Sword", "Wand",
			"Godsword", "Scimitar", "2hand", "Halberd", "Maul", "Warspear",
			"Longsword", "Guitar", "Pikachu", "Kenny" };

	public void initUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
			frame = new JFrame("Divination of Gods");
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
					signlink.findcachedir() + "Cursors/Cursors/icon.png"));
			frame.setLayout(new BorderLayout());
			setFocusTraversalKeysEnabled(false);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			int w = 765;
			int h = 503;
			int x = (dim.width - w) / 2;
			int y = (dim.height - h) / 2;
			frame.setLocation(x, y);
			gamePanel.setPreferredSize(new Dimension(765, 503));
			JMenu fileMenu = new JMenu("  File  ");
			JMenu toolMenu = new JMenu("  Tools  ");
			// JMenu cursorsMenu = new JMenu("Cursors");
			JMenu infoMenu = new JMenu("  Info  ");
			JMenu toggleMenu = new JMenu("  Toggles  ");
			JMenu profileMenu = new JMenu("  Links  ");
			JButton shotMenu = new JButton("Take Screenshot");
			shotMenu.setActionCommand("Screenshot");
			shotMenu.addActionListener(this);
			String[] mainButtons = new String[] { "View Images", "Exit" };
			String[] toolButtons = new String[] { "World Map", "Item List" };
			String[] infoButtons = new String[] { "Client Information",
					"Support" };
			String[] toggleButtons = new String[] { "Toggle 10x Damage",
					"Untoggle 10x Damage" };
			String[] profileButtons = new String[] { "Donate", "Vote", "-",
					"Highscores", "Guides", "YouTube", "Forums" };

			for (String name : mainButtons) {
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-")) {
					fileMenu.addSeparator();
				} else {
					menuItem.addActionListener(this);
					fileMenu.add(menuItem);
				}
			}
			for (String name : toolButtons) {
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					toolMenu.addSeparator();
				else {
					menuItem.addActionListener(this);
					toolMenu.add(menuItem);

				}
			}

			for (String name : infoButtons) {
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					infoMenu.addSeparator();
				else {
					menuItem.addActionListener(this);
					infoMenu.add(menuItem);
				}
			}
			for (String name : cursorsButtons) {
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					// cursorsMenu.addSeparator();
					// else
					// cursorsMenu.add(menuItem);
					menuItem.addActionListener(this);
			}

			for (String name : toggleButtons) {
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					toggleMenu.addSeparator();
				else {
					menuItem.addActionListener(this);
					toggleMenu.add(menuItem);
				}
			}
			for (String name : profileButtons) {
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					toggleMenu.addSeparator();
				else {
					menuItem.addActionListener(this);
					profileMenu.add(menuItem);
				}
			}
			JMenuBar menuBar = new JMenuBar();
			JMenuBar jmenubar = new JMenuBar();

			frame.add(jmenubar);
			menuBar.add(fileMenu);
			menuBar.add(toolMenu);
			menuBar.add(infoMenu);
			// menuBar.add(toggleMenu);
			menuBar.add(profileMenu);
			// menuBar.add(cursorsMenu);
			menuBar.add(shotMenu);
			frame.getContentPane().add(menuBar, BorderLayout.NORTH);
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.pack();

			frame.setVisible(true); // can see the client
			frame.setResizable(false); // resizeable frame
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JDialog createFileChooserDialog(JFileChooser jfilechooser, String s,
			Container container) {
		JDialog jdialog = new JDialog(frame, s, true);
		jdialog.setDefaultCloseOperation(2);
		jdialog.add(jfilechooser);
		jdialog.pack();
		jdialog.setLocationRelativeTo(container);
		return jdialog;
	}

	@Override
	public URL getCodeBase() {
		try {
			return new URL("http://" + serverip + "/cache");
		} catch (Exception e) {
			return super.getCodeBase();
		}
	}

	@Override
	public URL getDocumentBase() {
		return getCodeBase();
	}

	public void loadError(String s) {
		System.out.println("loadError: " + s);
	}

	@Override
	public String getParameter(String key) {
		return "";
	}

	private static void openUpWebSite(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url));
		} catch (Exception e) {
		}
	}

}