package api.sio.clases;

import java.util.ResourceBundle;

import api.sio.enumerados.CodigoError;

public class Resultado {
	
	private CodigoError codigoError;
	private String mensajeError;
	private Object valor;
	
	public Resultado()
	{
		this.mensajeError = "";
	}
	
	public String getMensajeError() {
		return mensajeError;
	}
	
	public Boolean correcto() 
	{
		if(mensajeError==null)
			return false;
		else
			return mensajeError.equals("");
	}
	
	public Boolean error() 
	{
		if(mensajeError==null)
			return true;
		else
			return !mensajeError.equals("");
	}
	
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
	public void setMensajeErrorGlobal(String mensajeId) 
	{
		ResourceBundle res = ResourceBundle.getBundle("Strings");
		if(res!=null)
		{
			String mensaje = res.getString(mensajeId);
			if(mensaje!=null)
				this.mensajeError = mensaje;
		}
	}
	
	public Object getValor() {
		return valor;
	}
	
	public void setValor(Object valor) {
		this.valor = valor;
	}

	public CodigoError getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(CodigoError codigoError) {
		this.codigoError = codigoError;
	}	
}
