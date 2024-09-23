package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

///Clase AdmTarjeta:
public class AdmTarjeta
{
	//Atributo:
	private List<Tarjeta> lstTarjeta;
	
	//Constructor:
	public AdmTarjeta() 
	{
		this.lstTarjeta = new ArrayList<Tarjeta>();
	}
	
	//Getter:
	public List<Tarjeta> getLstTarjeta()
	{
		return lstTarjeta;
	}
	
	//To String:
	public String toString() 
	{
		String admTarjeta = "Lista de tarjetas:\n";
		for (int i = 0; i < lstTarjeta.size(); i++) 
		{
			admTarjeta += lstTarjeta.get(i).toString();
		}		
		admTarjeta += "\n";
		return admTarjeta;
	}
	
	//Traer:
	public Tarjeta traerTarjeta(int idTarjeta) 
	{
		Tarjeta tarjetaBuscada = null;
		Tarjeta tarjetaAuxiliar = null;
		int i = 0;
		while (tarjetaBuscada == null && i < lstTarjeta.size())
		{
			tarjetaAuxiliar = lstTarjeta.get(i);
			if (tarjetaAuxiliar.getIdTarjeta() == idTarjeta) 
			{
				tarjetaBuscada = tarjetaAuxiliar;
			}
			i++;
		}
		return tarjetaBuscada;
	}
	
	public Tarjeta traerTarjeta(String numero) 
	{
		Tarjeta tarjetaBuscada = null;
		Tarjeta tarjetaAuxiliar = null;
		int i = 0;
		while (tarjetaBuscada == null && i < lstTarjeta.size())
		{
			tarjetaAuxiliar = lstTarjeta.get(i);
			if (tarjetaAuxiliar.getNumero().equals(numero)) 
			{
				tarjetaBuscada = tarjetaAuxiliar;
			}
			i++;
		}
		return tarjetaBuscada;
	}
	
	//Agregar:
	public boolean agregarTarjeta(String numero, LocalDate fechaVencimiento, Cliente cliente) throws Exception
	{
		if(cliente == null) 
		{
			throw new Exception("Error! El cliente no existe en la lista de clientes.");
		}
		
		if(traerTarjeta(numero) != null) 
		{
			throw new Exception("Error! Ya existe una tarjeta con el número #" + numero + " en la lista de tarjetas.");
		}
		
		int idTarjetaNueva = 1;
		if (lstTarjeta.size() > 0) 
		{
			int tamanio = lstTarjeta.size();
			idTarjetaNueva = lstTarjeta.get(tamanio - 1).getIdTarjeta() + 1;
		}
		return lstTarjeta.add(new Tarjeta(idTarjetaNueva, numero, fechaVencimiento, cliente));
	}
	
	//Modificar:
	public boolean modificarTarjeta(int idTarjetaModificar, String numero, LocalDate fechaVencimiento, Cliente cliente) throws Exception
	{
		Tarjeta tarjetaModificar = traerTarjeta(idTarjetaModificar);
		Tarjeta tarjetaConEseNumero = traerTarjeta(numero);
		if (tarjetaModificar == null) 
		{
			throw new Exception("Error! La tarjeta con ID #" + idTarjetaModificar + " no se puede modificar porque no existe en la lista de tarjetas.");
		}
		
		if(tarjetaConEseNumero != null && tarjetaModificar.getIdTarjeta() != tarjetaConEseNumero.getIdTarjeta()) 
		{
			throw new Exception("Error! Ya existe otra tarjeta con el número #" + numero + " en la lista de tarjetas.");
		}
		
		tarjetaModificar.setNumero(numero);
		tarjetaModificar.setFechaVencimiento(fechaVencimiento);
		tarjetaModificar.setCliente(cliente);
		return true; 
	}
	
	//Eliminar:
	public boolean eliminarTarjeta(String numero) throws Exception
	{
		Tarjeta tarjetaEliminar = traerTarjeta(numero);
		if (tarjetaEliminar == null) 
		{
			throw new Exception("Error! La tarjeta con número #" + numero + " no se puede eliminar porque no existe en la lista de tarjetas.");
		}
		return lstTarjeta.remove(tarjetaEliminar);	
	}
}
