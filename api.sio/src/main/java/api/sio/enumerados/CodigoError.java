package api.sio.enumerados;


public enum CodigoError 
{
	CUENTA_NO_VERIFICADA (1),
	TABLA_RELACIONADA (2);
	
	private final int id;
	CodigoError(int id)
	{
		this.id = id;
	}
	
	public int getId() {return this.id;}
	
	public static CodigoError getValor(int id) 
	{
        for (CodigoError elemento : values()) 
        {
            if (elemento.getId() == id) 
            {
                return elemento;
            }
        }
        return null;
    }
}
