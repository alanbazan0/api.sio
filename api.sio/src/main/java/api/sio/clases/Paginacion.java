package api.sio.clases;

public class Paginacion {
	
	private int tamanoPagina;
	
	private int paginaActual;

	public int getTamanoPagina() {
		return tamanoPagina;
	}

	public void setTamanoPagina(int tamanoPagina) {
		this.tamanoPagina = tamanoPagina;
	}

	public int getPaginaActual() {
		return paginaActual * 100;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public Paginacion(int tamanoPagina, int paginaActual) {
		this.tamanoPagina = tamanoPagina;
		this.paginaActual = paginaActual;
	}
	
	public Paginacion() {
		this.tamanoPagina = 0;
		this.paginaActual = 0;
	}
	
	public int getInicio()
	{
		return this.paginaActual * this.tamanoPagina;
	}
	
	public int getLimite()
	{
		return getTamanoPagina();
	}

}
