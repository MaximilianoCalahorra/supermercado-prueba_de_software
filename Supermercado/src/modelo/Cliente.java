package modelo; //Definimos a qué paquete pertenece esta clase.

//Clase Cliente:
public class Cliente
{
	//Atributos:
	private int idCliente;
	private String nombre;
	private long dni;
	private String direccion;
	
	//Constructor:
	public Cliente(int idCliente, String nombre, long dni, String direccion) 
	{
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;
	}

	//Getters:
	public int getIdCliente() 
	{
		return idCliente;
	}
	public String getNombre() 
	{
		return nombre;
	}
	public long getDni()
	{
		return dni;
	}
	public String getDireccion()
	{
		return direccion;
	}
	
	//Setters:
	public void setIdCliente(int idCliente)
	{
		this.idCliente = idCliente;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDni(long dni) 
	{
		this.dni = dni;
	}
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	
	//To String:
	public String toString() 
	{
		return "Cliente:\n"
				+ "ID: #" + idCliente + "\n"
				+ "Nombre: " + nombre + "\n"
				+ "DNI: #" + dni + "\n"
				+ "Direccion: " + direccion + "\n";
	}
	
	//Equals:
	public boolean equals(Cliente cliente) 
	{
		boolean iguales = false;
		if (this.idCliente == cliente.idCliente && this.nombre.equalsIgnoreCase(cliente.nombre) &&
			this.dni == cliente.dni && this.direccion.equalsIgnoreCase(cliente.direccion)) 
		{
			iguales = true;
		}
		return iguales;
	}
}
