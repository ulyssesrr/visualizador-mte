/*
 * (C) Copyright 2013 Ulysses Rangel Ribeiro
 * 
 * This file is part of Visualisador MTE.
 * 
 * Visualisador MTE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * Visualisador MTE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Visualisador MTE.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ulysses.mte1510.visualizadormte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.ulysses.mte1510.visualizadormte.acjef.ACJEFReader;
import com.ulysses.mte1510.visualizadormte.acjef.Arquivo;


/**
 * @author ulysses
 *
 */
public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MI_OPEN_CMD = "open";
	private static final String MI_ABOUT_CMD = "about";
	private static final String MI_EXIT_CMD = "exit";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (LookAndFeelInfo lafi : UIManager.getInstalledLookAndFeels()) {
			System.out.println(lafi.getName());
			if (lafi.getName().equals("Nimbus")) {
				try {
					UIManager.setLookAndFeel(lafi.getClassName());
					break;
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if (!UIManager.getLookAndFeel().getName().equals("Nimbus")) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		GUI frame = new GUI();
		frame.setVisible(true);
	}



	private JTabbedPane tabPane;
	
	public GUI() {
		super("Visualizador MTE - Arquivos da Portaria 1510/2009 - v0.5");
		this.setSize(640, 480);
//		this.setExtendedState(Jthis.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		FrameActionListener fal = new FrameActionListener();
		
		JMenu fileMenu = new JMenu("Arquivo");
		
		
		JMenuItem openItem = new JMenuItem("Abrir ACJEF...");
		openItem.setActionCommand(MI_OPEN_CMD);
		openItem.addActionListener(fal);
		
		
		JMenuItem exitItem = new JMenuItem("Sair");
		exitItem.setActionCommand(MI_EXIT_CMD);
		exitItem.addActionListener(fal);
		
		
		fileMenu.add(openItem);
		fileMenu.add(exitItem);
		
		
		JMenu helpMenu = new JMenu("Ajuda");
		
		JMenuItem aboutItem = new JMenuItem("Sobre");
		aboutItem.setActionCommand(MI_ABOUT_CMD);
		aboutItem.addActionListener(fal);
		
		helpMenu.add(aboutItem);
		
		JMenuBar menuBar = new JMenuBar();
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		this.setJMenuBar(menuBar);
		
		tabPane = new JTabbedPane();
		this.setContentPane(tabPane);
	}
	
	public void addArquivo(Arquivo arquivo) {
		ArquivoPanel arquivoPanel = new ArquivoPanel(arquivo);
		tabPane.insertTab("ACJEF - "+arquivo.getFile().getName(), null, new JScrollPane(arquivoPanel), "", tabPane.getTabCount());
	}

	class FrameActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			final String cmd = e.getActionCommand();
			
			if (cmd.equals(MI_OPEN_CMD)) {
				File path = null;
				if (tabPane.getTabCount() > 0) {
					JScrollPane scrollPane = (JScrollPane) tabPane.getComponentAt(tabPane.getTabCount() - 1);
					ArquivoPanel arquivoPanel = (ArquivoPanel) scrollPane.getViewport().getView();
					Arquivo arquivo = arquivoPanel.getArquivo();
					path = arquivo.getFile().getParentFile();
				}
				
				JFileChooser chooser = new JFileChooser(path);
				int openRet = chooser.showOpenDialog(GUI.this);
				if (openRet == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					try {
						Arquivo arquivo = new ACJEFReader().load(selectedFile);
						GUI.this.addArquivo(arquivo);
						
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(GUI.this, "Arquivo não encontrado: "+e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(GUI.this, "Erro lendo arquivo: "+e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					} catch (Throwable t) {
						JOptionPane.showMessageDialog(GUI.this, "Erro abrindo arquivo: "+selectedFile.getAbsolutePath()+"\n"+t.getClass().getSimpleName()+": "+t.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
			else if (cmd.equals(MI_ABOUT_CMD)) {
				JOptionPane.showMessageDialog(GUI.this, "Visualizador de Arquivos da Portaria 1510/2009 do MTE\n\nUlysses Rangel Ribeiro - 2013", "Sobre", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (cmd.equals(MI_EXIT_CMD)) {
				int response = JOptionPane.showConfirmDialog(GUI.this, "Deseja realmente sair?", "Confirmar saída", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		}
		
	}
}
