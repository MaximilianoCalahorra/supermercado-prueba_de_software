package modelo; //Definimos a qué paquete pertenece esta clase.

import java.util.List;
import java.util.ArrayList;

//Clase AdmProducto:
public class AdmProducto
{
	//Atributo:
	private List<Producto> lstProducto;
	
	//Constructor:
	public AdmProducto() 
	{
		this.lstProducto = new ArrayList<Producto>();
	}

	//Getter:
	public List<Producto> getLstProducto()
	{
		return lstProducto;
	}
	
	//To String:
	public String toString() 
	{
		String admProducto = "Lista de productos:\n";
		for (int i = 0; i < lstProducto.size(); i++) 
		{
			admProducto += lstProducto.get(i).toString();
		}
		admProducto += "\n";
		return admProducto;
	}
	
	//Traer producto:
	public Producto traerProducto(String nombre) 
	{
		Producto productoBuscado = null;
		Producto productoAuxiliar = null;
		int i = 0;
		while (productoBuscado == null && i < lstProducto.size()) 
		{
			productoAuxiliar = lstProducto.get(i);
			if (productoAuxiliar.getNombre().equalsIgnoreCase(nombre)) 
			{
				productoBuscado = productoAuxiliar;
			}
			i++;
		}
		return productoBuscado;
	}
	
	public Producto traerProducto(int idProducto) 
	{
		Producto productoBuscado = null;
		Producto productoAuxiliar = null;
		int i = 0;
		while (productoBuscado == null && i < lstProducto.size()) 
		{
			productoAuxiliar = lstProducto.get(i);
			if (productoAuxiliar.getIdProducto() == idProducto) 
			{
				productoBuscado = productoAuxiliar;
			}
			i++;
		}
		return productoBuscado;
	}
	
	//Agregar producto:
	public boolean agregarProducto(String nombre, float precio) throws Exception
	{
		if (traerProducto(nombre) != null) 
		{
			throw new Exception("Error! El producto " + nombre + " ya existe en la lista de productos.");
		}
		int idProductoNuevo = 1;
		if (lstProducto.size() > 0) 
		{
			int tamanio = lstProducto.size();
			idProductoNuevo = lstProducto.get(tamanio - 1).getIdProducto() + 1;
		}
		return lstProducto.add(new Producto(idProductoNuevo, nombre, precio));
	}
	
	//Modificar producto:
	public boolean modificarProducto(int idProductoModificar, String nombre, float precio) throws Exception
	{
		Producto productoModificar = traerProducto(idProductoModificar);
		if (productoModificar == null) 
		{
			throw new Exception("Error! El producto con ID #" + idProductoModificar + " no se puede modificar porque no existe en la lista de productos.");
		}
		productoModificar.setNombre(nombre);
		productoModificar.setPrecio(precio);
		return true; 
	}
}
