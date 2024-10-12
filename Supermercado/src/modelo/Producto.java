package modelo; //Definimos a qué paquete pertenece esta clase.

//Clase Producto:
public class Producto
{
	//Atributos:
	private int idProducto;
	private String nombre;
	private float precio;
	
	//Constructor:
	public Producto(int idProducto, String nombre, float precio)
	{
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
	}

	//Getters:
	public int getIdProducto() 
	{
		return idProducto;
	}
	public String getNombre() 
	{
		return nombre;
	}
	public float getPrecio()
	{
		return precio;
	}
	
	//Setters:
	public void setIdProducto(int idProducto)
	{
		this.idProducto = idProducto;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	public void setPrecio(float precio) 
	{
		this.precio = precio;
	}
	
	//Equals:
	@Override
	public boolean equals(Object o)
	{
	    if(this == o) return true;
	    if(o == null || getClass() != o.getClass()) return false;
	    Producto producto = (Producto) o;
	    return idProducto == producto.idProducto;
	}
	
	//To String:
	public String toString() 
	{
		return "Producto:\n"
			   + "ID: #" + idProducto + "\n"
			   + "Nombre: " + nombre + "\n"
			   + "Precio: $" + precio + "\n";
	}
}