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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ACJEFReader {

	public static void main(String[] args) {
//		FileInputStream fis = new FileInputStream("");
//		BufferedInputStream bis = new BufferedInputStream(fis);
//		InputStreamReader reader = new InputStreamReader(bis);
//		reader.read(cbuf)
//		bis.re
		
		
		
		
	}
	
	public Arquivo load(File f) throws FileNotFoundException, IOException {
//		RandomAccessFile file = new RandomAccessFile("	", "rw");
		RandomAccessFile file = new RandomAccessFile(f, "rw");
		MappedByteBuffer mappedFile = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, file.length());
		
		Charset charset = Charset.forName("ISO-8859-1");
		byte[] numeroSequencialData = new byte[9];
		
		List<Registro> registros = new ArrayList<Registro>();
		do {
			mappedFile.get(numeroSequencialData);
			String numeroSequencialStr = new String(numeroSequencialData, charset);
			int numeroSequencial = Integer.parseInt(numeroSequencialStr);
			
			int tipo = Integer.parseInt(String.valueOf(((char) mappedFile.get())));
			
			System.out.println("TIPO: "+tipo);
			RegistroACJEF layoutRegistro = RegistroACJEF.getClassByTipo(tipo);
			
			if (layoutRegistro != null) {
				LayoutCampo[] layoutCampos = layoutRegistro.getCampos();
				Campo[] campos = new Campo[layoutCampos.length];
				for (int i = 0; i < layoutCampos.length; i++) {
					LayoutCampo layoutCampo = layoutCampos[i];
					
					
					int tamanho = layoutCampo.getTamanho();
					byte[] data = new byte[tamanho];
					mappedFile.get(data);
					String dataStr = new String(data, charset);
//					System.out.println(layoutCampo.getNumeroCampo()+": "+layoutCampo.getDescricao()+" - "+layoutCampo.getTipo().format(dataStr));
					campos[i] = new Campo(layoutCampo, dataStr);
				}
				Registro registro = new Registro(numeroSequencial, layoutRegistro, campos);
				registros.add(registro);
			}
			else {
				System.out.println("RegistroACJEF null para tipo: "+tipo);
			}
		}
		while (mappedFile.remaining() >= 9);
		
		return new Arquivo(f, registros);
	}
}
