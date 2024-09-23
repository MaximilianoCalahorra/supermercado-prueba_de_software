package modelo; //Definimos a qué paquete pertenece esta clase.

//Clase ItemCarrito:
public class ItemCarrito 
{
	//Atributos:
	private int idItem;
	private Producto producto;
	private int cantidad;
	
	//Constructor:
	public ItemCarrito(int idItem, Producto producto, int cantidad)
	{
		this.idItem = idItem;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	//Getters:
	public int getIdItem() 
	{
		return idItem;
	}
	public Producto getProducto() 
	{
		return producto;
	}
	public int getCantidad()
	{
		return cantidad;
	}

	//Setters:
	public void setIdItem(int idItem)
	{
		this.idItem = idItem;
	}
	public void setProducto(Producto producto) 
	{
		this.producto = producto;
	}
	public void setCantidad(int cantidad)
	{
		this.cantidad = cantidad;
	}
	
	//Equals:
	public boolean equals(ItemCarrito itemCarrito) 
	{
		boolean iguales = false;
		if (this.idItem == itemCarrito.idItem && this.producto.equals(itemCarrito.producto) &&
			this.cantidad == itemCarrito.cantidad) 
		{
			iguales = true;
		}
		return iguales;
	}
	
	//To String:
	public String toString() 
	{
		return "Item Carrito:\n"
				+ "ID: #" + idItem + "\n"
				+ producto.toString()
				+ "Cantidad: " + cantidad + "\n";
	}
	
	//Calcular sub total:
	public float calcularSubTotal() 
	{
		return producto.getPrecio() * cantidad;
	}
}
