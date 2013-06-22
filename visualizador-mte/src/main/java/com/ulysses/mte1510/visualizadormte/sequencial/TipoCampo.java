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
package com.ulysses.mte1510.visualizadormte.sequencial;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum TipoCampo {
	STRING("Alfanumérico"){
		public String format(String val) {
			return val.trim();
		}
	},
	NUMERICO("Numérico"){
		public String format(String val) {
			return String.valueOf(Long.parseLong(val));
		}
	},
	DATA("Numérico"){
		public String format(String val) throws FormatException {
			try {
				Date data = DATE_IN_FORMAT.parse(val);
				return DATE_OUT_FORMAT.format(data);
			}
			catch (ParseException e) {
				throw new FormatException("Formato de data inválido: "+val, e);
			}
		}
	},
	HORA("Numérico"){
		public String format(String val) throws FormatException {
			try {
				Date data = TIME_IN_FORMAT.parse(val);
				return TIME_OUT_FORMAT.format(data);
			}
			catch (ParseException e) {
				throw new FormatException("Formato de data inválido: "+val, e);
			}
		}
	},
	PERCENTUAL("Numérico"){
		public String format(String val) throws FormatException {
			try {
				int inteiro = Integer.parseInt(val.substring(0, 3));
				double decimal = Double.parseDouble(val.substring(3, 4)) / 10;
				double valor = inteiro + decimal;
				return String.valueOf(valor)+'%';
			}
			catch (NumberFormatException e) {
				throw new FormatException("Formato de data inválido: "+val, e);
			}
		}
	};
	
	private final String tipoMTE;
	
	private static SimpleDateFormat DATE_IN_FORMAT = new SimpleDateFormat("ddMMyyyy");
	private static SimpleDateFormat DATE_OUT_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	private static SimpleDateFormat TIME_IN_FORMAT = new SimpleDateFormat("HHmm");
	private static SimpleDateFormat TIME_OUT_FORMAT = new SimpleDateFormat("HH:mm");


	private TipoCampo(String tipoMTE) {
		this.tipoMTE = tipoMTE;
		
	}
	
	public String getTipoMTE() {
		return tipoMTE;
	}
	
	public abstract String format(String val) throws FormatException;
}
