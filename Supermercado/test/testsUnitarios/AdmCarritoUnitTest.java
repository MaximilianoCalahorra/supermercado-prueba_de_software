package testsUnitarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import modelo.AdmCarrito;
import modelo.Carrito;
import modelo.Cliente;
import modelo.Funciones;
import modelo.ItemCarrito;
import modelo.Producto;

///Clase AdmCarritoUnitTest:
class AdmCarritoUnitTest 
{
	//Atributo:
	private AdmCarrito admCarrito;
	
	@BeforeEach
	public void setUp() throws Exception
	{
		//Instanciamos un administrador de carritos:
		admCarrito = new AdmCarrito(); 
		
		//Definimos las fechas:
		LocalDate fecha1 = LocalDate.of(2024, 9, 20);
		LocalDate fecha2 = LocalDate.of(2024, 9, 22);
		LocalDate fecha3 = LocalDate.of(2024, 9, 24);
		
		//Definimos las horas:
		LocalTime hora1 = LocalTime.of(14, 16, 45);
		LocalTime hora2 = LocalTime.of(11, 27, 14);
		LocalTime hora3 = LocalTime.of(18, 39, 34);
		
		//Definimos los mocks de los clientes:
		Cliente cliente1 = mock(Cliente.class);
		Cliente cliente2 = mock(Cliente.class);
		Cliente cliente3 = mock(Cliente.class);
	
		//Definimos los mocks de los carritos:
		Carrito carrito1 = mock(Carrito.class);
		Carrito carrito2 = mock(Carrito.class);
		Carrito carrito3 = mock(Carrito.class);
		
		//Definimos los mocks de los productos:
		Producto producto1 = mock(Producto.class);
		Producto producto2 = mock(Producto.class);
		Producto producto3 = mock(Producto.class);
		
		//Definimos los mocks de los ítems de carritos:
		ItemCarrito itemCarrito1 = mock(ItemCarrito.class);
		ItemCarrito itemCarrito2 = mock(ItemCarrito.class);
		ItemCarrito itemCarrito3 = mock(ItemCarrito.class);
		ItemCarrito itemCarrito4 = mock(ItemCarrito.class);
		ItemCarrito itemCarrito5 = mock(ItemCarrito.class);
		
		//Definimos los ítems de cada carrito:
		List<ItemCarrito> itemsCarrito1 = new ArrayList<>();
		itemsCarrito1.add(itemCarrito1);
		itemsCarrito1.add(itemCarrito3);
		itemsCarrito1.add(itemCarrito5);
		
		List<ItemCarrito> itemsCarrito2 = new ArrayList<>();
		itemsCarrito2.add(itemCarrito4);
		itemsCarrito2.add(itemCarrito5);
		itemsCarrito2.add(itemCarrito2);
		
		List<ItemCarrito> itemsCarrito3 = new ArrayList<>();
		itemsCarrito3.add(itemCarrito1);
		itemsCarrito3.add(itemCarrito3);
		itemsCarrito3.add(itemCarrito4);
		
		//Simulamos la obtención del id de cada carrito:
		when(carrito1.getIdCarrito()).thenReturn(1);
		when(carrito2.getIdCarrito()).thenReturn(2);
		when(carrito3.getIdCarrito()).thenReturn(3);
		
		//Simulamos la obtención de la fecha de cada carrito:
		when(carrito1.getFecha()).thenReturn(fecha1);
		when(carrito2.getFecha()).thenReturn(fecha2);
		when(carrito3.getFecha()).thenReturn(fecha3);
		
		//Simulamos la obtención de la hora de cada carrito:
		when(carrito1.getHora()).thenReturn(hora1);
		when(carrito2.getHora()).thenReturn(hora2);
		when(carrito3.getHora()).thenReturn(hora3);
		
		//Simulamos la obtención del cliente asociado a cada carrito:
		when(carrito1.getCliente()).thenReturn(cliente1);
		when(carrito2.getCliente()).thenReturn(cliente2);
		when(carrito3.getCliente()).thenReturn(cliente3);
		
		//Simulamos la obtención de los ítems de cada carrito:
		when(carrito1.getLstItem()).thenReturn(itemsCarrito1);
		when(carrito1.getLstItem()).thenReturn(itemsCarrito2);
		when(carrito1.getLstItem()).thenReturn(itemsCarrito3);
		
		//Simulamos la obtención del id asociado al cliente de cada carrito:
		when(cliente1.getIdCliente()).thenReturn(1);
		when(cliente2.getIdCliente()).thenReturn(2);
		when(cliente3.getIdCliente()).thenReturn(3);

		//Simulamos la comparación entre clientes:
		when(cliente1.equals(cliente1)).thenReturn(true);
		when(cliente2.equals(cliente2)).thenReturn(true);
		when(cliente3.equals(cliente3)).thenReturn(true);
		
		//Simulamos la obtención del id de cada producto:
		when(producto1.getIdProducto()).thenReturn(1);
		when(producto2.getIdProducto()).thenReturn(2);
		when(producto3.getIdProducto()).thenReturn(3);
				
		//Simulamos la obtención del producto de cada ítem de carrito:
		when(itemCarrito1.getProducto()).thenReturn(producto1);
		when(itemCarrito2.getProducto()).thenReturn(producto2);
		when(itemCarrito3.getProducto()).thenReturn(producto3);
		when(itemCarrito4.getProducto()).thenReturn(producto2);
		when(itemCarrito5.getProducto()).thenReturn(producto1);
		
		//Agregamos los carritos a la lista de carritos:
		admCarrito.getLstCarrito().add(carrito1);
		admCarrito.getLstCarrito().add(carrito2);
		admCarrito.getLstCarrito().add(carrito3);
		
		//Simulamos el cálculo del total de cada carrito:
		when(carrito1.calcularTotal()).thenReturn(19300.0f);
		when(carrito2.calcularTotal()).thenReturn(25870.0f);
		when(carrito3.calcularTotal()).thenReturn(39180.0f);
	}
	
	@Test
	@DisplayName("Prueba de inserción de un carrito con cliente nulo")
	public void testAgregarCarrito_ClienteNulo() 
	{
		//Arrange:
		LocalDate fecha = LocalDate.of(2024, 9, 25); //Definimos una fecha.
		LocalTime hora = LocalTime.of(13, 3, 15); //Definimos una hora.
		Cliente cliente = null; //Definimos un cliente nulo.
		
		//Act y Assert:
		assertThrows(Exception.class, () -> admCarrito.agregarCarrito(fecha, hora, cliente)); //Verificamos que se lance la excepción de cliente nulo.
	}
	
	@Test
	@DisplayName("Prueba de inserción de un carrito cuando ya existe en la lista")
	public void testAgregarCarrito_YaExiste() 
	{
		//Arrange:
		Carrito carrito = admCarrito.getLstCarrito().get(0);
		LocalDate fecha = carrito.getFecha();
		LocalTime hora = carrito.getHora();
		Cliente cliente = carrito.getCliente();
		
		//Act y Assert:
		assertThrows(Exception.class, () -> admCarrito.agregarCarrito(fecha, hora, cliente)); //Verificamos que se lance la excepción de carrito ya existente.
	}
	
	@Test
	@DisplayName("Prueba de inserción de un carrito cuando no existe en la lista y el cliente existe")
	public void testAgregarCarrito_Agregado() 
	{
		//Arrange:
		LocalDate fecha = LocalDate.of(2024, 9, 26); //Definimos la fecha del nuevo carrito.
		LocalTime hora = LocalTime.of(17, 49, 12); //Definimos la hora del nuevo carrito.
		Cliente cliente = mock(Cliente.class); //Definimos el cliente del nuevo carrito.
		
		int longitudActualListaCarritos = admCarrito.getLstCarrito().size(); //Obtenemos la longitud actual de la lista de carritos.
		int longitudEsperadaListaCarritos = longitudActualListaCarritos + 1; //Esperamos que aumente en uno luego de la inserción.
		int longitudObtenidaListaCarritos = longitudActualListaCarritos; //Inicializamos la longitud obtenida con la actual.
		
		//Act:
		try
		{
			admCarrito.agregarCarrito(fecha, hora, cliente); //Agregamos el carrito a la lista.
			longitudObtenidaListaCarritos = admCarrito.getLstCarrito().size(); //Obtenemos la longitud actual de la lista.
		} 
		catch(Exception e) 
		{
			fail("No debería fallar: " + e.getMessage());
		}
		
		//Assert:
		assertEquals(longitudEsperadaListaCarritos, longitudObtenidaListaCarritos); //Verificamos que la cantidad obtenida sea igual a la esperada.
	}
	
	@Test
	@DisplayName("Prueba del cálculo del total de todos los carritos sumados en un mes y año específicos con un mes inválido")
	public void testCalcularTotalMesAnio_MesInvalido() 
	{
		//Arrange:
		int mes = 13; //Definimos el mes del caso de prueba.
		int anio = 2024; //Definimos el año del caso de prueba.
		
		//Act y Assert:
		assertThrows(Exception.class, () -> admCarrito.calcularTotal(mes, anio)); //Verificamos que se lance la excepción por mes inválido.
	}
	
	@Test
	@DisplayName("Prueba del cálculo del total de todos los carritos sumados en un mes y año específicos con un mes válido")
	public void testCalcularTotalMesAnio_MesValido() 
	{
		//Arrange:
		int mes = 9; //Definimos el mes del caso de prueba.
		int anio = 2024; //Definimos el año del caso de prueba.
		float totalEsperado = 84350; //Definimos el total que esperamos obtener.
		float totalObtenido = 0; //Inicializamos el total obtenido.
		
		//Simulamos el método estático Funciones.traerCantDiasDeUnMes:
        try(MockedStatic<Funciones> funcionesMock = mockStatic(Funciones.class)) 
        {
            //Simulamos el retorno de traerCantDiasDeUnMes:
            funcionesMock.when(() -> Funciones.traerCantDiasDeUnMes(anio, mes)).thenReturn(30);
            
            //Act:
            try 
            {
                totalObtenido = admCarrito.calcularTotal(mes, anio); //Obtenemos el total de los carritos según el mes y año.
            }
            catch(Exception e)
            {
                fail("No debería fallar: " + e.getMessage());
            }
        }

        //Assert:
        assertEquals(totalEsperado, totalObtenido); //Verificamos que el total obtenido sea el esperado.
	}
	
	@Test
	@DisplayName("Prueba del cálculo del total de todos los carritos en una fecha en específico")
	public void testCalcularTotalFecha() 
	{
		//Arrange:
		LocalDate fecha = LocalDate.of(2024, 9, 22); //Definimos la fecha del caso de prueba.
		float totalEsperado = 25870; //Definimos el total que esperamos obtener.
		float totalObtenido = 0; //Inicializamos el total obtenido.
		
		//Act:
		totalObtenido = admCarrito.calcularTotal(fecha); //Obtenemos el total de los carritos según la fecha.
		
		//Assert:
		assertEquals(totalEsperado, totalObtenido); //Verificamos que el total obtenido sea el esperado.
	}
	
	@Test
	@DisplayName("Prueba del cálculo del total de todos los carritos entre dos fechas (extremos incluidos)")
	public void testCalcularTotalFechaAFecha() 
	{
		//Arrange:
		LocalDate fechaDesde = LocalDate.of(2024, 9, 22); //Definimos la fecha de inicio del caso de prueba.
		LocalDate fechaHasta = LocalDate.of(2024, 9, 24); //Definimos la fecha de fin del caso de prueba.
		float totalEsperado = 65050; //Definimos el total que esperamos obtener.
		float totalObtenido = 0; //Inicializamos el total obtenido.
		
		//Act:
		totalObtenido = admCarrito.calcularTotal(fechaDesde, fechaHasta); //Obtenemos el total de los carritos según las fechas.
		
		//Assert:
		assertEquals(totalEsperado, totalObtenido); //Verificamos que el total obtenido sea el esperado.
	}
	
	@Test
	@DisplayName("Prueba de eliminación de un carrito inexistente")
	public void testEliminarCarrito_NoExiste() 
	{
		//Arrange:
		int idCarritoEliminar = 4; //Definimos el id del carrito a eliminar.
		
		//Act y Assert:
		assertThrows(Exception.class, () -> admCarrito.eliminarCarrito(idCarritoEliminar)); //Verificamos que se lance la excepción de carrito inexistente.
	}
	
	@Test
	@DisplayName("Prueba de eliminación de un carrito existente")
	public void testEliminarCarrito_Eliminado() 
	{
		//Arrange:
		int idCarritoEliminar = 1; //Definimos el id del carrito a eliminar.
		int longitudActualListaCarritos = admCarrito.getLstCarrito().size(); //Obtenemos la longitud actual del listado de carritos.
		int longitudEsperadaListaCarritos = longitudActualListaCarritos - 1; //Esperamos que se elimine el carrito y haya uno menos en la lista.
		int longitudObtenidaListaCarritos = longitudActualListaCarritos; //Inicializamos la cantidad obtenida con la actual.
		
		//Act:
		try 
		{
			admCarrito.eliminarCarrito(idCarritoEliminar); //Eliminamos el carrito.
			longitudObtenidaListaCarritos = admCarrito.getLstCarrito().size(); //Obtenemos la longitud actual del listado.
		} 
		catch(Exception e)
		{
			fail("No debería fallar: " + e.getMessage());
		}
		
		//Assert:
		assertEquals(longitudEsperadaListaCarritos, longitudObtenidaListaCarritos); //Verificamos que se haya eliminado el cliente.
	}
	
	@Test
	@DisplayName("Prueba de obtención de un carrito por su id cuando no existe el carrito")
	public void testTraerCarritoPorId_NoExiste() 
	{
		//Arrange:
		Carrito carritoObtenido = null; //Definimos un carrito como nulo.
		
		//Act:
		carritoObtenido = admCarrito.traerCarrito(4); //Obtenemos el carrito con el id indicado.
		
		//Assert:
		assertNull(carritoObtenido); //Verificamos que no se haya encontrado el carrito y sea nulo.
	}
	
	@Test
	@DisplayName("Prueba de obtención de un carrito por su id cuando existe el carrito")
	public void testTraerCarritoPorId_Existe() 
	{
		int idCarritoEsperado = 1; //Definimos el id que esperamos que tenga el carrito obtenido.
		int idCarritoObtenido = 0; //Inicializamos el id del carrito obtenido.
		
		//Act:
		idCarritoObtenido = admCarrito.traerCarrito(idCarritoEsperado).getIdCarrito(); //Obtenemos el carrito buscado y de él su id.
		
		//Assert:
		assertEquals(idCarritoEsperado, idCarritoObtenido); //Verificamos que el id del carrito encontrado sea el esperado.
	}
	
	@Test
	@DisplayName("Prueba de obtención de un carrito por fecha, hora y cliente cuando no existe el carrito")
	public void testTraerCarritoPorFechaHoraCliente_NoExiste() 
	{
		//Arrange:
		Carrito carritoObtenido = null; //Definimos un carrito como nulo.
		LocalDate fecha = LocalDate.of(2024, 9, 25); //Definimos la fecha del carrito buscado.
		LocalTime hora = LocalTime.of(15, 15, 15); //Definimos la hora del carrito buscado.
		Cliente cliente = mock(Cliente.class); //Definimos el cliente del carrito buscado.
				
		//Act:
		carritoObtenido = admCarrito.traerCarrito(fecha, hora, cliente); //Obtenemos el carrito con la fecha, hora y cliente indicados.
		
		//Assert:
		assertNull(carritoObtenido); //Verificamos que no se haya encontrado el carrito y sea nulo.
	}
	
	@Test
	@DisplayName("Prueba de obtención de un carrito por fecha, hora y cliente cuando existe el carrito")
	public void testTraerCarritoPorFechaHoraCliente_Existe() 
	{
		//Arrange:
		Carrito carrito = admCarrito.getLstCarrito().get(0); //Obtenemos el carrito que queremos encontrar para pasar esos datos al método.
		LocalDate fecha = carrito.getFecha(); //Cargamos la fecha del carrito buscado.
		LocalTime hora = carrito.getHora(); //Cargamos la hora del carrito buscado.
		Cliente cliente = carrito.getCliente(); //Cargamos el cliente del carrito buscado.
		
		int idCarritoEsperado = 1; //Definimos el id esperado del carrito.
		int idCarritoObtenido = 0; //Inicializamos el id del carrito obtenido.
		
		//Act:
		idCarritoObtenido = admCarrito.traerCarrito(fecha, hora, cliente).getIdCarrito(); //Obtenemos el id del carrito que encontramos.
		
		//Assert:
		assertEquals(idCarritoEsperado, idCarritoObtenido); //Verificamos que el id del carrito encontrado sea el esperado.
	}
	
	@Test
	@DisplayName("Prueba de obtención de un carrito con determinado producto cuando no hay carritos con ese producto")
	public void testTraerCarritoPorProducto_NoExiste() 
	{
		//Arrange:
		Carrito carritoObtenido = null; //Definimos un carrito como nulo.
		
		//Act:
		carritoObtenido = admCarrito.traerCarritoConProducto(4); //Obtenemos el carrito que tiene el producto del id indicado.
		
		//Assert:
		assertNull(carritoObtenido); //Verificamos que no exista un carrito con ese producto.
	}
	
	@Test
	@DisplayName("Prueba de obtención de un carrito con determinado producto cuando hay al menos un carrito con ese producto")
	public void testTraerCarritoPorProducto_Existe() 
	{
		//Arrange:
		int idCarritoEsperado = 1; //Definimos el id del carrito que esperamos obtener.
		int idCarritoObtenido = 0; //Inicializamos el id del carrito buscado.
		
		//Act:
		idCarritoObtenido = admCarrito.traerCarritoConProducto(1).getIdCarrito(); //Obtenemos el id del carrito que tiene el producto del id indicado.
		
		//Assert:
		assertEquals(idCarritoEsperado, idCarritoObtenido); //Verificamos que el id del carrito obtenido sea el id esperado.
	}
	
	@Test
	@DisplayName("Prueba de obtención de un carrito de determinado cliente cuando el cliente no tiene carritos asociados")
	public void testTraerCarritoDeCliente_NoExiste() 
	{
		//Arrange:
		Carrito carritoObtenido = null; //Definimos un carrito como nulo.
		
		//Act:
		carritoObtenido = admCarrito.traerCarritoDeCliente(4); //Obtenemos el carrito del cliente con el id indicado.
				
		//Assert:
		assertNull(carritoObtenido); //Verificamos que el cliente no tenga asociado algún carrito.
	}
	
	@Test
	@DisplayName("Prueba de obtención de un carrito de determinado cliente cuando el cliente tiene por lo menos un carrito asociado")
	public void testTraerCarritoDeCliente_Existe() 
	{
		//Arrange:
		int idCarritoEsperado = 1; //Definimos el id del carrito que queremos encontrar.
		int idCarritoObtenido = 0; //Inicializamos el id del carrito encontrado.
		
		//Act:
		idCarritoObtenido = admCarrito.traerCarritoDeCliente(1).getIdCarrito(); //Obtenemos el id del carrito del cliente con el id indicado.
		
		//Assert:
		assertEquals(idCarritoEsperado, idCarritoObtenido); //Verificamos que el id del carrito obtenido sea el esperado.
	}
}
