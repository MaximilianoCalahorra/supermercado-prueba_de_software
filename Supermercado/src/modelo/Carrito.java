package modelo; //Definimos a qué paquete pertenece esta clase.

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

//Clase Carrito:
public class Carrito
{
	//Atributos:
	private int idCarrito;
	private LocalDate fecha;
	private LocalTime hora;
	private List<ItemCarrito> lstItem;
	private Cliente cliente;
	
	//Constructor:
	public Carrito(int idCarrito, LocalDate fecha, LocalTime hora, Cliente cliente) 
	{
		this.idCarrito = idCarrito;
		this.fecha = fecha;
		this.hora = hora;
		this.lstItem = new ArrayList<ItemCarrito>();
		this.cliente = cliente;
	}

	//Getters:
	public int getIdCarrito()
	{
		return idCarrito;
	}
	public LocalDate getFecha() 
	{
		return fecha;
	}
	public LocalTime getHora() 
	{
		return hora;
	}
	public List<ItemCarrito> getLstItem() 
	{
		return lstItem;
	}
	public Cliente getCliente()
	{
		return cliente;
	}

	//Setters:
	public void setIdCarrito(int idCarrito) 
	{
		this.idCarrito = idCarrito;
	}
	public void setFecha(LocalDate fecha)
	{
		this.fecha = fecha;
	}
	public void setHora(LocalTime hora) 
	{
		this.hora = hora;
	}
	public void setCliente(Cliente cliente) 
	{
		this.cliente = cliente;
	}
	
	//To String:
	public String toString() 
	{
		String carrito = "Carrito:\n"
						 + "ID: #" + idCarrito + "\n"
						 + "Fecha y hora: " + fecha + " " + hora + "\n"
						 + cliente;
		for (int i = 0; i < lstItem.size(); i++) 
		{
			carrito += lstItem.get(i).toString();
		}
		carrito += "\n";
		return carrito;
	}

	//Traer:
	public ItemCarrito traerItemCarrito(Producto producto) 
	{
		Producto productoAuxiliar = null; //Un Producto auxiliar para comparar el de cada ItemCarrito con el que llega.
		ItemCarrito itemCarritoAuxiliar = null; //Un ItemCarrito auxiliar para trabajar con el ítem de ese producto. 
		int i = 0;
		while (itemCarritoAuxiliar == null && i < lstItem.size()) 
		{
			productoAuxiliar = lstItem.get(i).getProducto(); //Obtenemos el producto del ítem 'i' de la lista.
			if (productoAuxiliar.equals(producto)) //Lo comparamos con el que llega. Si sus ID son iguales...
			{
				itemCarritoAuxiliar = lstItem.get(i); //El ItemCarrito es el de la posición 'i'.
			}
			i++;
		}
		return itemCarritoAuxiliar; //Retornamos el ItemCarrito auxiliar.
	}

	//Agregar:
	public boolean agregarItem(Producto producto, int cantidad) 
	{
		boolean agregado = false; //Todavía no se agregó.
		ItemCarrito itemCarritoAuxiliar = traerItemCarrito(producto);
		if (itemCarritoAuxiliar != null)  //Si se encontró un ItemCarrito con ese producto...
		{
			int cantidadActual = itemCarritoAuxiliar.getCantidad(); //Obtenemos la cantidad.
			itemCarritoAuxiliar.setCantidad(cantidadActual + cantidad); //Seteamos por la que había + la que se agrega.
			agregado = true; //No se agrega un nuevo ItemCarrito con ese producto sino que se incrementa la cantidad.
		}
		else 
		{
			int idItemCarritoNuevo = 1; //Ese será el ID para el primer ItemCarrito de la lista.
			if (lstItem.size() > 0) //Si la lista no está vacía...
			{
				int tamanio = lstItem.size(); //Obtenemos el tamaño actual de la lista.
				idItemCarritoNuevo = lstItem.get(tamanio - 1).getIdItem() + 1; //Obtenemos el último ItemCarrito en
																			   //ella, luego su ID y le sumamos 1.																	
			}
			agregado = lstItem.add(new ItemCarrito(idItemCarritoNuevo, producto, cantidad)); //Agregamos un nuevo
																							 //ItemCarrito a la lista
																							 //con esos datos.
		}
		return agregado;
	}
	//Eliminar:
	public boolean eliminarItem(Producto producto, int cantidad) throws Exception
	{
		if (producto == null) 
		{
			throw new Exception("Error! El producto no existe en la lista de productos.");
		}
		boolean eliminado = false; //Todavía no se eliminó.
		ItemCarrito itemCarritoAuxiliar = traerItemCarrito(producto); //Un ItemCarrito auxiliar es el resultado
																	  //de la búsqueda por la lista según el producto.
		if (itemCarritoAuxiliar == null) //Si no existe un ItemCarrito con ese Producto...
		{
			throw new Exception("Error! El producto que se quiere eliminar del carrito no existe en el.");
		}
		//Si existe...
		int cantidadActual = itemCarritoAuxiliar.getCantidad(); //Obtenemos la cantidad que hay de ese Producto.
		if (cantidad < cantidadActual) //Si lo que se quiere eliminar es menor a la actual...
		{
			itemCarritoAuxiliar.setCantidad(cantidadActual - cantidad); //Seteamos la cantidad del Producto.
			eliminado = true; //No se elimina el ItemCarrito, sino que se actualiza su cantidad.
		}
		else //Si la cantidad que se quiere eliminar es la misma a la disponible... (acepta el > pero produce el mismo efecto)
		{
			eliminado = lstItem.remove(itemCarritoAuxiliar); //Eliminamos el ItemCarrito de la lista.
		}
		return eliminado; 
	}	
	
	//Calcular total:
	public float calcularTotal() 
	{
		float acumulador = 0;
		for (int i = 0; i < lstItem.size(); i++) 
		{
			acumulador += lstItem.get(i).calcularSubTotal();
		}
		return acumulador;
	}
}
