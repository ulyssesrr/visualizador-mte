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

public enum DetalheACJEF implements LayoutCampo {
	TIPO_ID(12, TipoCampo.NUMERICO, "Número do PIS do empregado."),
	DATA_JORNADA(8, TipoCampo.DATA, "Data de início da jornada, no formato “ddmmaaaa”."),
	PRIMEIRO_HORARIO(4, TipoCampo.HORA, "Primeiro horário de entrada da jornada, no formato “hhmm”."),
	COD_HORARIO(4, TipoCampo.NUMERICO, "Código do horário (CH) previsto para a jornada, no formato “nnnn”."),
	HORA_DIURNA(4, TipoCampo.HORA, "Horas diurnas não extraordinárias, no formato “hhmm”."),
	HORA_NOTURNA(4, TipoCampo.HORA, "Horas noturnas não extraordinárias, no formato “hhmm”."),
	HORA_EXTRA_1(4, TipoCampo.HORA, "Horas extras 1, no formato “hhmm”."),
	PERCENTUAL_HE_1(4, TipoCampo.PERCENTUAL, "Percentual do adicional de horas extras 1, onde as 3 primeiras posições indicam a parte inteira e a seguinte a fração decimal."),
	MODALIDADE_HE_1(1, TipoCampo.STRING, "Modalidade da hora extra 1, assinalado com “D” se as horas extras forem diurnas e “N” se forem noturnas."),
	HORA_EXTRA_2(4, TipoCampo.HORA, "Horas extras 2, no formato “hhmm”."),
	PERCENTUAL_HE_2(4, TipoCampo.PERCENTUAL, "Percentual do adicional de horas extras 2, onde as 3 primeiras posições indicam a parte inteira e a seguinte a fração decimal."),
	MODALIDADE_HE_2(1, TipoCampo.STRING, "Modalidade da hora extra 2, assinalado com “D” se as horas extras forem diurnas e “N” se forem noturnas."),
	HORA_EXTRA_3(4, TipoCampo.HORA, "Horas extras 3, no formato “hhmm”."),
	PERCENTUAL_HE_3(4, TipoCampo.PERCENTUAL, "Percentual do adicional de horas extras 3, onde as 3 primeiras posições indicam a parte inteira e a seguinte a fração decimal."),
	MODALIDADE_HE_3(1, TipoCampo.STRING, "Modalidade da hora extra 3, assinalado com “D” se as horas extras forem diurnas e “N” se forem noturnas."),
	HORA_EXTRA_4(4, TipoCampo.HORA, "Horas extras 4, no formato “hhmm”."),
	PERCENTUAL_HE_4(4, TipoCampo.PERCENTUAL, "Percentual do adicional de horas extras 4, onde as 3 primeiras posições indicam a parte inteira e a seguinte a fração decimal."),
	MODALIDADE_HE_4(1, TipoCampo.STRING, "Modalidade da hora extra 4, assinalado com “D” se as horas extras forem diurnas e “N” se forem noturnas."),
	HR_FALTA_ATRASO(4, TipoCampo.HORA, "Horas de faltas e/ou atrasos."),
	SINAL_HR_COMP(1, TipoCampo.NUMERICO, "Sinal de horas para compensar. “1” se for horas a maior e “2” se for horas a menor."),
	SALDO_HR_COMP(4, TipoCampo.HORA, "Saldo de horas para compensar no formato “hhmm”.");
	
	private final int tamanho;
	private final TipoCampo tipo;
	private final String descricao;

	private DetalheACJEF(int tamanho, TipoCampo tipo, String descricao) {
		this.tamanho = tamanho;
		this.tipo = tipo;
		this.descricao = descricao;
	}
	
	public int getTamanho() {
		return tamanho;
	}
	
	public TipoCampo getTipo() {
		return tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	@Override
	public int getNumeroCampo() {
		return this.ordinal() + 3;
	}
	
}