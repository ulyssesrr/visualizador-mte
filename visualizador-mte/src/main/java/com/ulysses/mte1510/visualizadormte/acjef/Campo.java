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

public class Campo {
	
	private final LayoutCampo layoutCampo;
	private final String conteudo;

	public Campo(LayoutCampo layoutCampo, String conteudo) {
		this.layoutCampo = layoutCampo;
		this.conteudo = conteudo;
	}
	
	public String getConteudo() {
		return conteudo;
	}
	
	public LayoutCampo getLayoutCampo() {
		return layoutCampo;
	}
}
