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
package com.ulysses.mte1510.visualizadormte.acjef;

import com.ulysses.mte1510.visualizadormte.sequencial.TipoCampo;

public enum HorarioContratuaisACJEF implements LayoutCampo {
	COD_HORARIO(4, TipoCampo.NUMERICO, "Código do Horário (CH), no formato nnnn."),
	HR_ENTRADA(4, TipoCampo.HORA, "Entrada, no formato “hhmm”."),
	HR_SAIDA(4, TipoCampo.HORA, "Saída, no formato “hhmm”."),
	HR_INI_INTERVALO(4, TipoCampo.HORA, "Início intervalo, no formato “hhmm”."),
	HR_FIM_INTERVALO(4, TipoCampo.HORA, "Fim intervalo, no formato “hhmm”.");
	
	private final int tamanho;
	private final TipoCampo tipo;
	private final String descricao;

	private HorarioContratuaisACJEF(int tamanho, TipoCampo tipo, String descricao) {
		this.tamanho = tamanho;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	@Override
	public int getNumeroCampo() {
		return this.ordinal() + 3;
	}

	@Override
	public int getTamanho() {
		return tamanho;
	}

	@Override
	public TipoCampo getTipo() {
		return tipo;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}
}
