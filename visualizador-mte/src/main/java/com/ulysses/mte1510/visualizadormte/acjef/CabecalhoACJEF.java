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

/**
 * @author ulysses
 *
 */
public enum CabecalhoACJEF implements LayoutCampo {
//	SEQUENCIAL(9, TipoCampo.NUMERICO, "Seqüencial do registro no arquivo."),
//	TIPO(1, TipoCampo.NUMERICO, "Tipo do registro, “1”."),
	TIPO_ID(1, TipoCampo.NUMERICO, "Tipo de identificador do empregador, “1” para CNPJ ou “2” para CPF."),
	CNPJ_CPF_EMPREGADOR(14, TipoCampo.NUMERICO, "CNPJ ou CPF do empregador."),
	CEI_EMPREGADOR(12, TipoCampo.NUMERICO, "CEI do empregador, quando existir."),
	RAZAO_SOCIAL(150, TipoCampo.STRING, "Razão social ou nome do empregador."),
	DATA_INICIAL(8, TipoCampo.DATA, "Data inicial dos registros no arquivo, no formato “ddmmaaaa”."),
	DATA_FINAL(8, TipoCampo.DATA, "Data final dos registros no arquivo, no formato “ddmmaaaa”."),
	DATA_GERACAO(8, TipoCampo.DATA, "Data de geração do arquivo, no formato “ddmmaaaa”."),
	HORARIO_GERACAO(4, TipoCampo.HORA, "Horário da geração do arquivo, no formato “hhmm”.");
	
	private final int tamanho;
	private final TipoCampo tipo;
	private final String descricao;

	private CabecalhoACJEF(int tamanho, TipoCampo tipo, String descricao) {
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