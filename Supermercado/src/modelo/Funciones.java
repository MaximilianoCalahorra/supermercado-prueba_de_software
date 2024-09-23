package modelo; //Definimos a qué paquete pertenece esta clase.

import java.time.LocalDate; //Incluímos la librería para trabajar con fechas.
import java.time.LocalTime; //Incluímos la librería para trabajar con horas.

//Clase Funciones.
public class Funciones 
{	
	//Año bisiesto:
	public static boolean esBisiesto(int anio) 
	{
		return (anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0));
	}
	
	//Fecha válida:
	public static boolean esFechaValida(LocalDate fecha) 
	{
		int dia = fecha.getDayOfMonth(); //Obtenemos el número de día en el mes.
		int mes = fecha.getMonthValue(); //Obtenemos el número de mes.
		int anio = fecha.getYear(); //Obtenemos el número de año.
		boolean valida = false; //Suponemos que la fecha es inválida.
		if (anio > 0) //El año es válido...
		{
			if (mes >= 1 && mes <= 12) //El mes es válido...
			{
				if ((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && (dia >= 1 && dia <= 31)) //Día con mes es válido... 
				{
					valida = true; //La fecha es válida.
				}
				else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia >= 1 && dia <= 30)) //Día con mes es válido..
				{
					valida = true; //La fecha es válida.
				}
				else 
				{
					//El mes es 2.
					if ((dia >= 1 && dia <= 28) || ((dia >= 1 && dia <= 29) && Funciones.esBisiesto(anio))) //28 para febrero y 29 para febrero de año bisiesto...
					{
						valida = true; //La fecha es válida.
					}
				}
			}
		}
		return valida; //Retornamos si la fecha es válida o no.
	}
	
	//Fecha corta:
	public static String traerFechaCorta(LocalDate fecha) 
	{
		String fechaCorta = fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear();
		return fechaCorta;
	}
	
	//Hora corta:
	public static String traerHoraCorta(LocalTime hora) 
	{
		String horaCorta = hora.getHour() + ":" + hora.getMinute();
		return horaCorta;
	}
	
	//Día hábil:
	public static boolean esDiaHabil(LocalDate fecha) 
	{
		int numeroDia = fecha.getDayOfWeek().getValue(); //Obtenemos el número del día en la semana.
		return ((numeroDia >= 1) && (numeroDia <= 5)); //Retorna 'true' si el número es de 1 a 5 (inclusive).
	}
	
	//Nombre día de la semana:
	public static String traerDiaDeLaSemana(LocalDate fecha) 
	{
		int numeroDiaEnLaSemana = fecha.getDayOfWeek().getValue();
		String nombreDia = "";
		switch (numeroDiaEnLaSemana) 
		{
			case 1: nombreDia = "Lunes";
			break;
			case 2: nombreDia = "Martes";
			break;
			case 3: nombreDia = "Miercoles";
			break;
			case 4: nombreDia = "Jueves";
			break;
			case 5: nombreDia = "Viernes";
			break;
			case 6: nombreDia = "Sabado";
			break;
			case 7: nombreDia = "Domingo";
		}
		return nombreDia;
	}
	
	//Nombre mes:
	public static String traerMesEnLetras(LocalDate fecha) 
	{
		int numeroMes = fecha.getMonthValue();
		String nombreMes = "";
		switch (numeroMes) 
		{
			case 1: nombreMes = "Enero";
			break;
			case 2: nombreMes = "Febrero";
			break;
			case 3: nombreMes = "Marzo";
			break;
			case 4: nombreMes = "Abril";
			break;
			case 5: nombreMes = "Mayo";
			break;
			case 6: nombreMes = "Junio";
			break;
			case 7: nombreMes = "Julio";
			break;
			case 8: nombreMes = "Agosto";
			break;
			case 9: nombreMes = "Septiembre";
			break;
			case 10: nombreMes = "Octubre";
			break;
			case 11: nombreMes = "Noviembre";
			break;
			case 12: nombreMes = "Diciembre";
		}
		return nombreMes;
	}
	
	//Fecha larga:
	public static String traerFechaLarga(LocalDate fecha) 
	{
		int numeroDia = fecha.getDayOfMonth();
		int anio = fecha.getYear();
		return Funciones.traerDiaDeLaSemana(fecha) + " " + numeroDia + " de " + Funciones.traerMesEnLetras(fecha) + " del " + anio;
	}
	
	//Cantidad de días de un mes:
	public static int traerCantDiasDeUnMes(int anio, int mes) 
	{
		int cantidadDias = 0;
		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)
		{
			cantidadDias = 31; 
		}
		else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) 
		{
			cantidadDias = 30; 
		}
		else 
		{
			//El mes es 2.
			if (Funciones.esBisiesto(anio)) //Si el año es bisiesto...
			{
				cantidadDias = 29; //La fecha es válida.
			}
			else 
			{
				cantidadDias = 28;
			}
		}
		return cantidadDias;
	}
	
	//Aproximar con 2 decimales:
	public static double aproximar2Decimal(double valor) 
	{
		double parteDecimal = valor % 1; //Nos quedamos con el resto de la división del número entre 1.
		String parteDecimalEnString = Double.toString(parteDecimal); //"0, ... "
		//Las posición 0 la ocupa el 0 y la posición 1 el .
		String primerDecimalEnString = parteDecimalEnString.substring(2, 3); //Nos quedamos con la posición 2.
		String segundoDecimalEnString = parteDecimalEnString.substring(3, 4); //Nos quedamos con la posición 3.
		String tercerDecimalEnString = parteDecimalEnString.substring(4, 5); //Nos quedamos con la posición 4.
		double primerDecimal = Double.parseDouble(primerDecimalEnString); //Valor de la posición 2 a double.
		double segundoDecimal = Double.parseDouble(segundoDecimalEnString); //Valor de la posición 3 a double.
		double tercerDecimal = Double.parseDouble(tercerDecimalEnString); //Valor de la posición 4 a double.
		if ((parteDecimalEnString.length() >= 3) && (tercerDecimal >= 5))
		{
			segundoDecimal += 1;
		}
		primerDecimal *= 0.1;
		segundoDecimal *= 0.01;
		double numeroAproximado = valor - parteDecimal;
		numeroAproximado += primerDecimal + segundoDecimal;
		return numeroAproximado;
		
		/* Otra solución mucho más corta */
		//return ((double)Math.round(valor * 100)) / 100;
		/*Corre la coma dos lugares a la derecha al multiplicar por 100, redondea con el
		Math.round() y divide el resultado entre 100 para volver a la expresión original*/
	}
	
	//Es número:
	public static boolean esNumero(char c) 
	{
		return Character.isDigit(c);
	}
	
	//Es letra:
	public static boolean esLetra(char c) 
	{
		return Character.isLetter(c);
	}
	
	//Es cadena de números:
	public static boolean esCadenaNros(String cadena) 
	{
		int i = 0;
		boolean es = true;
		while ((i < cadena.length()) && (es == true)) 
		{
			if (!Character.isDigit(cadena.charAt(i))) 
			{
				es = false;
			}
			i++;
		}
		return es;
	}
	
	//Es cadena de letras:
	public static boolean esCadenaLetras(String cadena) 
	{
		int i = 0;
		boolean es = true;
		while ((i < cadena.length()) && (es == true)) 
		{
			if (!Character.isLetter(cadena.charAt(i))) 
			{
				es = false;
			}
			i++;
		}
		return es;
	}
	
	//Convertir a double:
	public static double convertirADouble(int n) 
	{
		return ((double)n);
	}
}
