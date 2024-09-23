package modelo; //Definimos a qué paquete pertenece esta clase.

import java.util.List;
import java.util.ArrayList;

//Clase AdmCliente:
public class AdmCliente
{
	//Atributo:
	private List<Cliente> lstCliente;
	
	//Constructor:
	public AdmCliente() 
	{
		this.lstCliente = new ArrayList<Cliente>();
	}

	//Getter:
	public List<Cliente> getLstCliente()
	{
		return lstCliente;
	}
	
	//To String:
	public String toString() 
	{
		String admCliente = "Lista de clientes:\n";
		for (int i = 0; i < lstCliente.size(); i++) 
		{
			admCliente += lstCliente.get(i).toString();
		}
		admCliente += "\n";
		return admCliente;
	}
	
	//Traer:
	public Cliente traerCliente(long dni)
	{
		Cliente clienteBuscado = null;
		Cliente clienteAuxiliar = null;
		int i = 0;
		while (clienteBuscado == null && i < lstCliente.size())
		{
			clienteAuxiliar = lstCliente.get(i);
			if (clienteAuxiliar.getDni() == dni) 
			{
				clienteBuscado = clienteAuxiliar;
			}
			i++;
		}
		return clienteBuscado;
	}

	public Cliente traerCliente(int idCliente) 
	{
		Cliente clienteBuscado = null;
		Cliente clienteAuxiliar = null;
		int i = 0;
		while (clienteBuscado == null && i < lstCliente.size())
		{
			clienteAuxiliar = lstCliente.get(i);
			if (clienteAuxiliar.getIdCliente() == idCliente) 
			{
				clienteBuscado = clienteAuxiliar;
			}
			i++;
		}
		return clienteBuscado;
	}
	
	//Agregar:
	public boolean agregarCliente(String nombre, long dni, String direccion) throws Exception
	{
		if (traerCliente(dni) != null) 
		{
			throw new Exception("Error! El cliente con DNI #" + dni + " ya existe en la lista de clientes.");
		}
		int idClienteNuevo = 1;
		if (lstCliente.size() > 0) 
		{
			int tamanio = lstCliente.size();
			idClienteNuevo = lstCliente.get(tamanio - 1).getIdCliente() + 1;
		}
		return lstCliente.add(new Cliente(idClienteNuevo, nombre, dni, direccion));
	}
}