package testsUnitarios;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import modelo.AdmTarjeta;
import modelo.Cliente;
import modelo.Tarjeta;

///Clase AdmTarjetaUnitTest:
class AdmTarjetaUnitTest 
{
	//Atributo:
	private AdmTarjeta admTarjeta;

	@BeforeEach
	public void setUp() throws Exception
	{	
		//Instanciamos un administrador de tarjetas:
		admTarjeta = new AdmTarjeta();
		
		//Definimos los mocks de las tarjetas:
		Tarjeta tarjeta1 = mock(Tarjeta.class);
		Tarjeta tarjeta2 = mock(Tarjeta.class);
		Tarjeta tarjeta3 = mock(Tarjeta.class);
		
		
		//Simulamos la obtención del id de cada tarjeta:
		when(tarjeta1.getIdTarjeta()).thenReturn(1);
		when(tarjeta2.getIdTarjeta()).thenReturn(2);
		when(tarjeta3.getIdTarjeta()).thenReturn(3);
		
		//Simulamos la obtención del número de cada tarjeta:
		when(tarjeta1.getNumero()).thenReturn("1111");
		when(tarjeta2.getNumero()).thenReturn("2222");
		when(tarjeta3.getNumero()).thenReturn("3333");
		
		//Agregamos las tarjetas a la lista:
		admTarjeta.getLstTarjeta().add(tarjeta1);
		admTarjeta.getLstTarjeta().add(tarjeta2);
		admTarjeta.getLstTarjeta().add(tarjeta3);
	}

	//TraerTarjeta
	@Test
	@DisplayName("Prueba de obtención de una tarjeta por su id cuando no existe la tarjeta")
	public void testTraerTarjetaInt_NoExiste() 
	{
		//Arrange:
		Tarjeta tarjetaObtenida = null; //Definimos la tarjeta como nula.

		//Act:
		tarjetaObtenida = admTarjeta.traerTarjeta(4); //Obtenemos la tarjeta con el id indicado.

		//Assert:
		assertNull(tarjetaObtenida); //Verificamos que no se haya encontrado la tarjeta y sea nula.
	}
	
	@Test
	@DisplayName("Prueba de obtención de una tarjeta por su id cuando existe la tarjeta")
	public void testTraerTarjetaInt_Existe() 
	{
		//Arrange:
		int idTarjetaEsperado = 1;
		int idTarjetaObtenido = 0;
		
		//Act:
		idTarjetaObtenido = admTarjeta.traerTarjeta(idTarjetaEsperado).getIdTarjeta();
		
		//Assert:
		assertEquals(idTarjetaEsperado, idTarjetaObtenido);
	}

	@Test
	@DisplayName("Prueba de obtención de una tarjeta por su número cuando no existe la tarjeta")
	public void testTraerTarjetaString_NoExiste() 
	{
		//Arrange:
		Tarjeta tarjetaObtenida = null; //Definimos la tarjeta como nula.

		//Act:
		tarjetaObtenida = admTarjeta.traerTarjeta("0000"); //Obtenemos la tarjeta con un número inexistente.

		//Assert:
		assertNull(tarjetaObtenida); //Verificamos que no se haya encontrado la tarjeta y sea nula.
	}
	
	@Test
	@DisplayName("Prueba de obtención de una tarjeta por su numero cuando existe la tarjeta")
	public void testTraerTarjetaString_Existe()
	{
		//Arrange:
		String numeroTarjetaEsperado = "1111";
		String numeroTarjetaObtenido = null;
		
		//Act:
		numeroTarjetaObtenido = admTarjeta.traerTarjeta(numeroTarjetaEsperado).getNumero();
		
		//Assert:
		assertEquals(numeroTarjetaEsperado, numeroTarjetaObtenido);
	}
	
	//AgregarTarjeta
	@Test
	@DisplayName("Prueba de inserción una tarjeta ya existente")
	public void testAgregarTarjeta_Existe() 
	{
		//Arrange:
		String numero = "1111"; //Definimos un número de tarjeta existente.
	    LocalDate fechaVenc = LocalDate.of(2030, 11, 5);
	    Cliente cliente = mock(Cliente.class);
		
		//Act & Assert:
		assertThrows(Exception.class, () -> admTarjeta.agregarTarjeta(numero, fechaVenc, cliente));
	}
	
	@Test
	@DisplayName("Prueba de inserción de una tarjeta inexistente pero con cliente existente")
	public void testAgregarTarjeta_NoExiste_ClienteExiste()
	{
		//Arrange:
		int longActual = admTarjeta.getLstTarjeta().size();
		int longEsperada = longActual + 1;
		
		String numero = "4444"; //Definimos un número de tarjeta inexistente.
	    LocalDate fechaVenc = LocalDate.of(2030, 11, 5);
	    Cliente cliente = mock(Cliente.class);
		
		//Act:
	    try
	    {
			admTarjeta.agregarTarjeta(numero, fechaVenc, cliente);
			longActual = admTarjeta.getLstTarjeta().size();
		} 
	    catch(Exception e)
	    {
			fail("No debería fallar: " + e.getMessage());
		}
	    
	    //Assert:
		assertEquals(longEsperada, longActual);
	}

	//ModificarTarjeta
	@Test
	@DisplayName("Prueba de modificación de una tarjeta inexistente")
	public void testModificarTarjeta_NoExiste()
	{
		//Arrange:
		int idTarjetaAModificar = 0;
	    
		//Act & Assert:
	    assertThrows(Exception.class, () -> admTarjeta.modificarTarjeta(idTarjetaAModificar, "4444", LocalDate.of(2030, 11, 5), mock(Cliente.class)));
	}
	
	@Test
	@DisplayName("Prueba de modificación de una tarjeta existente pero con un número usado por otra tarjeta")
	public void testModificarTarjeta_NumeroExistente() 
	{
		//Arrange:
		int idTarjetaAModificar = 1; //Definimos un id de tarjeta ya existente.
		String numeroTarjetaAModificar = "2222"; //Definimos un número de tarjeta ya ocupado por otra.

		//Act & Assert:
		assertThrows(Exception.class, () -> admTarjeta.modificarTarjeta(idTarjetaAModificar, numeroTarjetaAModificar, LocalDate.of(2030, 11, 5), mock(Cliente.class)));
	}
	
	@Test
	@DisplayName("Prueba de modificación de una tarjeta existente y número unico")
	public void testModificarTarjeta_Modificada() 
	{	
		//Arrange:
	    int id = 1;
	    String numeroNuevo = "4444"; //Nuevo número a establecer (que no está en uso).
	    LocalDate fechaVenc = LocalDate.of(2030, 11, 5);
	    Cliente cliente = mock(Cliente.class);

	    //Act:
	    try 
	    {
			admTarjeta.modificarTarjeta(id, numeroNuevo, fechaVenc, cliente);
		} 
	    catch(Exception e)
	    {
			fail("No debería fallar: " + e.getMessage());
		}
	    
	    //Assert:
	    Tarjeta tarjetaModificada = admTarjeta.traerTarjeta(id);
	    verify(tarjetaModificada).setNumero(numeroNuevo);
	    verify(tarjetaModificada).setFechaVencimiento(fechaVenc);
	    verify(tarjetaModificada).setCliente(cliente);
	}

	//EliminarTarjeta
	@Test
	@DisplayName("Prueba de eliminación de una tarjeta inexistente")
	public void testEliminarTarjeta_NoExiste()
	{
		//Arrange:
		String numeroTarjetaAEliminar = null;

		//Act y Assert:
		assertThrows(Exception.class, () -> admTarjeta.eliminarTarjeta(numeroTarjetaAEliminar));
	}

	@Test
	@DisplayName("Prueba de eliminación de una tarjeta existente")
	public void testEliminarTarjeta_Eliminada()
	{
		//Arrange:
		String numeroTarjetaAEliminar = "1111";
		int longActual = admTarjeta.getLstTarjeta().size();
		int longEsperada = longActual - 1;
		
		//Act:
		try 
		{
			admTarjeta.eliminarTarjeta(numeroTarjetaAEliminar);
			longActual = admTarjeta.getLstTarjeta().size();
		}
		catch(Exception e)
		{
			fail("No debería fallar: " + e.getMessage());
		}
		
		//Assert:
		assertEquals(longEsperada, longActual);
	}
}
