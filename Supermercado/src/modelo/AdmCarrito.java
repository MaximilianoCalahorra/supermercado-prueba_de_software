package modelo; //Definimos a qué paquete pertenece esta clase.

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

//Clase AdmCarrito:
public class AdmCarrito
{
	//Atributo:
	private List<Carrito> lstCarrito;
	
	//Constructor:
	public AdmCarrito() 
	{
		this.lstCarrito = new ArrayList<Carrito>();
	}

	//Getter:
	public List<Carrito> getLstCarrito()
	{
		return lstCarrito;
	}
	
	//To String:
	public String toString() 
	{
		String admCarrito = "Lista de carritos:\n";
		for (int i = 0; i < lstCarrito.size(); i++) 
		{
			admCarrito += lstCarrito.get(i).toString();
		}
		admCarrito += "\n";
		return admCarrito;
	}
	
	//Traer:
	public Carrito traerCarrito(LocalDate fecha, LocalTime hora, Cliente cliente) 
	{
		Carrito carritoBuscado = null;
		Carrito carritoAuxiliar = null;
		int i = 0;
		while (carritoBuscado == null && i < lstCarrito.size()) 
		{
			carritoAuxiliar = lstCarrito.get(i);
			if (carritoAuxiliar.getFecha().equals(fecha) && carritoAuxiliar.getHora().equals(hora) &&
				carritoAuxiliar.getCliente().equals(cliente)) 
			{
				carritoBuscado = carritoAuxiliar;
			}
			i++;
		}
		return carritoBuscado;
	}

	public Carrito traerCarrito(int idCarrito) 
	{
		Carrito carritoBuscado = null;
		Carrito carritoAuxiliar = null;
		int i = 0;
		while (carritoBuscado == null && i < lstCarrito.size()) 
		{
			carritoAuxiliar = lstCarrito.get(i);
			if (carritoAuxiliar.getIdCarrito() == idCarrito) 
			{
				carritoBuscado = carritoAuxiliar;
			}
			i++;
		}
		return carritoBuscado;
	}
	
	public Carrito traerCarritoConProducto(int idProducto) 
	{
		Carrito carritoBuscado = null;
		Carrito carritoAuxiliar = null;
		ItemCarrito itemCarritoConProducto = null;
		ItemCarrito itemCarritoAuxiliar = null;
		int i = 0;
		int j = 0;
		while (carritoBuscado == null && i < lstCarrito.size()) 
		{
			carritoAuxiliar = lstCarrito.get(i);
			while (itemCarritoConProducto == null && j < carritoAuxiliar.getLstItem().size()) 
			{
				itemCarritoAuxiliar = carritoAuxiliar.getLstItem().get(j);
				if (itemCarritoAuxiliar.getProducto().getIdProducto() == idProducto)
				{
					itemCarritoConProducto = itemCarritoAuxiliar;
				}
				j++;
			}
			if (itemCarritoConProducto != null) 
			{
				carritoBuscado = carritoAuxiliar;
			}
			i++;
		}
		carritoBuscado.toString();
		return carritoBuscado;
	}
	
	public Carrito traerCarritoDeCliente(int idCliente) 
	{
		Carrito carritoDeCliente = null;
		Carrito carritoAuxiliar = null;
		int i = 0;
		while (carritoDeCliente == null && i < lstCarrito.size()) 
		{
			carritoAuxiliar = lstCarrito.get(i);
			if (carritoAuxiliar.getCliente().getIdCliente() == idCliente) 
			{
				carritoDeCliente = carritoAuxiliar;
			}
			i++;
		}
		return carritoDeCliente;
	}
	
	//Agregar:
	public boolean agregarCarrito(LocalDate fecha, LocalTime hora, Cliente cliente) throws Exception
	{
		if (cliente == null) 
		{
			throw new Exception("Error! El cliente no existe en la lista de clientes.");
		}
		if (traerCarrito(fecha, hora, cliente) != null) 
		{
			throw new Exception("Error! El carrito ya existe en la lista de carritos.");
		}
		int idCarritoNuevo = 1;
		if (lstCarrito.size() > 0) 
		{
			int tamanio = lstCarrito.size();
			idCarritoNuevo = lstCarrito.get(tamanio - 1).getIdCarrito() + 1;
		}
		return lstCarrito.add(new Carrito(idCarritoNuevo, fecha, hora, cliente));
	}
	
	//Eliminar:
	public boolean eliminarCarrito(int idCarrito) throws Exception
	{
		Carrito carritoEliminar = traerCarrito(idCarrito);
		if (carritoEliminar == null) 
		{
			throw new Exception("Error! El carrito con ID #" + idCarrito + " no se puede eliminar porque no existe en la lista de carritos.");
		}
		return lstCarrito.remove(carritoEliminar);	
	}
	
	//Calcular total:
	//Fecha a fecha:
	public float calcularTotal(LocalDate fechaInicio, LocalDate fechaFin) 
	{
		float total = 0;
		Carrito carritoAuxiliar = null;
		for (int i = 0; i < lstCarrito.size(); i++) 
		{
			carritoAuxiliar = lstCarrito.get(i);
			if (!(carritoAuxiliar.getFecha().isBefore(fechaInicio)) && !(carritoAuxiliar.getFecha().isAfter(fechaFin))) 
			{
				total += carritoAuxiliar.calcularTotal();
			}
		}
		return total;
	}
	
	//Fecha específica:
	public float calcularTotal(LocalDate fecha) 
	{
		float total = 0;
		Carrito carritoAuxiliar = null;
		for (int i = 0; i < lstCarrito.size(); i++) 
		{
			carritoAuxiliar = lstCarrito.get(i);
			if (carritoAuxiliar.getFecha().equals(fecha)) 
			{
				total += carritoAuxiliar.calcularTotal();
			}
		}
		return total;
	}
	
	//Por mes y año en específico:
	public float calcularTotal(int mes, int anio) throws Exception
	{
		if (mes < 1 || mes > 12) 
		{
			throw new Exception("Error! El mes es invalido.");
		}
		LocalDate fechaInicio = LocalDate.of(anio, mes, 1);
		int ultimoDia = Funciones.traerCantDiasDeUnMes(anio, mes);
		LocalDate fechaFin = LocalDate.of(anio, mes, ultimoDia);
		return calcularTotal(fechaInicio, fechaFin);
	}
}