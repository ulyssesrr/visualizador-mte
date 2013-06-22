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

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import com.ulysses.mte1510.visualizadormte.acjef.Arquivo;
import com.ulysses.mte1510.visualizadormte.acjef.Campo;
import com.ulysses.mte1510.visualizadormte.acjef.Registro;
import com.ulysses.mte1510.visualizadormte.sequencial.FormatException;
import com.ulysses.mte1510.visualizadormte.sequencial.TipoCampo;

/**
 * @author ulysses
 *
 */
public class ArquivoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Arquivo arquivo;

	public ArquivoPanel(Arquivo arquivo) {
		super();
		this.arquivo = arquivo;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0;
		
		for (Registro registro : arquivo.getRegistros()) {
			TitledBorder registroBorder = BorderFactory.createTitledBorder(registro.getLayoutRegistro().getNome()+" - "+registro.getNumeroSequencial());
			JTable table = new JTable(new RegistroTableModel(registro));
//			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumnAdjuster tca = new TableColumnAdjuster(table);
			tca.adjustColumns();

			JPanel panel = new JPanel();
			panel.setBorder(registroBorder);
			panel.setLayout(new BorderLayout());
			panel.add(table.getTableHeader(), BorderLayout.NORTH);
			panel.add(table, BorderLayout.CENTER);
			gbc.gridy++;
			this.add(panel, gbc);
		}
	}
	
	public Arquivo getArquivo() {
		return arquivo;
	}
	
	static class RegistroTableModel extends AbstractTableModel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private static final String[] COLUMN_NAMES = {"Numero do Campo", "Tamanho do Campo", "Tipo do Campo", "Conteudo Formatado", "Conteudo Original", "Descrição"};

		private final Registro registro;

		public RegistroTableModel(Registro registro) {
			this.registro = registro;
		}
		
		@Override
		public int getRowCount() {
			return registro.getCampos().length;
		}

		@Override
		public int getColumnCount() {
			return COLUMN_NAMES.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Campo campo = registro.getCampos()[rowIndex];
			switch (columnIndex) {
				case 0:
					return campo.getLayoutCampo().getNumeroCampo();
				case 1:
					return campo.getLayoutCampo().getTamanho();
				case 2:
					TipoCampo tipo = campo.getLayoutCampo().getTipo();
					return tipo.getTipoMTE()+" ("+tipo.name()+")";
				case 3:
					try {
						return campo.getLayoutCampo().getTipo().format(campo.getConteudo());
					} catch (FormatException e) {
						return "<INVÁLIDO>";
					}
				case 4:
					return campo.getConteudo();
				case 5:
					return campo.getLayoutCampo().getDescricao();
				default:
					throw new IllegalStateException("Coluna não implementada: "+columnIndex);
			}
		}
		
		@Override
		public String getColumnName(int column) {
			return COLUMN_NAMES[column];
		}
	}
}
