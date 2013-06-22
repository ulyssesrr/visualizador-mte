package com.ulysses.mte1510.visualizadormte.acjef;

public enum RegistroACJEF implements LayoutRegistro {
	CABECALHO(1, CabecalhoACJEF.class, "Cabeçalho"),
	HORARIOS_CONTRATUAIS(2, HorarioContratuaisACJEF.class, "Horário Contratuais"),
	DETALHE(3, DetalheACJEF.class, "Detalhe"),
	TRAILER(9, TrailerACJEF.class, "Trailer");
	
	private final int tipoRegistro;
	private final  Class<? extends LayoutCampo> classeRegistro;
	private final String nome;

	private RegistroACJEF(int tipoRegistro, Class<? extends LayoutCampo> classeRegistro, String nome) {
		this.tipoRegistro = tipoRegistro;
		this.classeRegistro = classeRegistro;
		this.nome = nome;
	}
	
	public int getTipoRegistro() {
		return tipoRegistro;
	}
	
	public  Class<? extends LayoutCampo> getClasseRegistro() {
		return classeRegistro;
	}
	
	public static RegistroACJEF getClassByTipo(int tipo) {
		for (RegistroACJEF reg : RegistroACJEF.values()) {
			if (reg.getTipoRegistro() == tipo) {
				return reg;
			}
		}
		return null;
	}

	@Override
	public LayoutCampo[] getCampos() {
		return this.getClasseRegistro().getEnumConstants();
	}
	
	public String getNome() {
		return nome;
	}
}
