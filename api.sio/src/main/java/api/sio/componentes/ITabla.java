package api.sio.componentes;

import java.util.HashMap;

import api.sio.clases.Paginacion;
import api.sio.clases.Resultado;

public interface ITabla
{
	public Resultado consultarNumeroRegistros(HashMap<String,Object> criteriosSeleccion);
	public Resultado consultar(HashMap<String,Object> criteriosSeleccion, Paginacion paginacion);
	public Resultado consultarColumnas(HashMap<String,Object> criteriosSeleccion);
}
