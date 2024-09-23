package modelo;

import java.time.LocalDate;

///Clase Tarjeta:
public class Tarjeta
{
	//Atributos:
	private int idTarjeta;
	private String numero;
	private LocalDate fechaVencimiento;
	private Cliente cliente;
	
	//Constructor:
	public Tarjeta(int idTarjeta, String numero, LocalDate fechaVencimiento, Cliente cliente) 
	{
		this.idTarjeta = idTarjeta;
		this.numero = numero;
		this.fechaVencimiento = fechaVencimiento;
		this.cliente = cliente;
	}

	//Getters:
	public int getIdTarjeta()
	{
		return idTarjeta;
	}

	public String getNumero() 
	{
		return numero;
	}

	public LocalDate getFechaVencimiento() 
	{
		return fechaVencimiento;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	//Setters:
	public void setIdTarjeta(int idTarjeta)
	{
		this.idTarjeta = idTarjeta;
	}

	public void setNumero(String numero) 
	{
		this.numero = numero;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento)
	{
		this.fechaVencimiento = fechaVencimiento;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}	
	
	//To String:
	public String toString() 
	{
		return "Tarjeta:\n"
				+ "ID: #" + idTarjeta + "\n"
				+ "Número: #" + numero + "\n"
				+ "Fecha de vencimiento: " + fechaVencimiento + "\n"
				+ cliente.toString();
	}
		
	//Equals:
	public boolean equals(Tarjeta tarjeta) 
	{
		boolean iguales = false;
		if (this.idTarjeta == tarjeta.idTarjeta && this.numero.equalsIgnoreCase(tarjeta.numero) &&
			this.fechaVencimiento.equals(tarjeta.fechaVencimiento) && this.cliente.equals(tarjeta.cliente))
		{
			iguales = true;
		}
		return iguales;
	}
}
