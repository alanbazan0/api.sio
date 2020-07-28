package api.sio.componentes;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import api.sio.clases.Paginacion;
import api.sio.clases.Resultado;

public abstract class TablaBase extends HttpServlet  implements ITabla
{
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(TablaBase.class); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TablaBase() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected Gson crearGson()
    {
    	GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("dd/MM/yyyy HH:mm:ss").create();
		
//		gsonBuilder.registerTypeAdapter(new TypeToken<HashMap<String, Object>>(){}.getType(),  new MapDeserializerDoubleAsIntFix());
//		gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
//		gsonBuilder.registerTypeAdapterFactory(StringAdapter.FACTORY);
		
		Gson gson = gsonBuilder.create();
		
		return gson;
    }
    
    protected void ejecutar(HttpServletRequest request, HttpServletResponse response)
    {
    	response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		
		Gson gson = crearGson();
		
		Resultado resultado = new Resultado();
		try 
		{
			if(request.getParameter("accion")!=null)
			{
				 String accion = request.getParameter("accion");
				 if(accion.equals("consultarNumeroRegistros"))
				 {
				   HashMap<String,Object> criteriosSeleccion  = gson.fromJson(request.getParameter("criteriosSeleccion"), new TypeToken<HashMap<String, Object>>() {}.getType());
				   resultado = consultarNumeroRegistros(criteriosSeleccion);
				 } 
				 else if(accion.equals("consultar"))
				 {
					HashMap<String,Object> criteriosSeleccion  = gson.fromJson(request.getParameter("criteriosSeleccion"), new TypeToken<HashMap<String, Object>>() {}.getType());
					Paginacion  paginacion = gson.fromJson(request.getParameter("paginacion"), new TypeToken<Paginacion>() {}.getType());
					Resultado resultadoConsultar = consultar(criteriosSeleccion, paginacion);	
					if(resultadoConsultar.correcto())
					{
						Resultado resultadoColumnas = consultarColumnas(criteriosSeleccion);
						if(resultadoColumnas.correcto())
						{
							HashMap<String, Object> valor = new HashMap<String, Object>();
							valor.put("registros", resultadoConsultar.getValor());
							valor.put("columnas", resultadoColumnas.getValor());
							resultado.setValor(valor);
						}
						else
							resultado = resultadoColumnas;
					}
					else 
						resultado = resultadoConsultar;
					
				 }
				 else if(accion.equals("consultarColumnas"))
				 {
					HashMap<String,Object> criteriosSeleccion  = gson.fromJson(request.getParameter("criteriosSeleccion"), new TypeToken<HashMap<String, Object>>() {}.getType());
					resultado = consultarColumnas(criteriosSeleccion);	
				 }
				 else
					resultado.setMensajeError("Acción no implementada!");
			}
			else
				resultado.setMensajeError("Acción incorrecta, no se encontró el parametro accion");
			
		
		}
		catch(Exception ex)
		{
			LOGGER.fatal(ex);
			resultado.setMensajeError(ex.getMessage());
		}
		finally
		{
			try 
			{
				response.getWriter().append(gson.toJson(resultado));
			} 
			catch (Exception e) 
			{
				LOGGER.fatal(e);
			}
			
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {		
		ejecutar(request, response);
	}

	

}
