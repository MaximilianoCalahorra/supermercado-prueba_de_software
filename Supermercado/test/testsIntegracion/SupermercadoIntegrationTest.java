package testsIntegracion;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import modelo.Carrito;
import modelo.Cliente;
import modelo.ItemCarrito;
import modelo.Producto;
import modelo.Supermercado;

///Clase SupermercadoIntegrationTest:
class SupermercadoIntegrationTest 
{
	//Atributo:
	private Supermercado supermercado;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		supermercado = new Supermercado(); //Instanciamos un supermercado.
		
		//Definimos los productos:
		Producto producto1 = new Producto(1, "Harina integral", 1200);
		Producto producto2 = new Producto(2, "Dulce de leche", 2500);
		Producto producto3 = new Producto(3, "Leche", 1500);
		Producto producto4 = new Producto(4, "Shampoo", 6000);
		Producto producto5 = new Producto(5, "Detergente", 1500);
		Producto producto6 = new Producto(6, "Pan", 2500);
		
		//Definimos los clientes:
		Cliente cliente1 = new Cliente(1, "Juliana González", 34918491, "Av. Alsina 7189, Lanús");
		Cliente cliente2 = new Cliente(2, "Juan López", 28391049, "Av. Hipólito Yrigoyen 1893, Lomas de Zamora");
		Cliente cliente3 = new Cliente(3, "Macarena Cáceres", 23139481, "Av. Mitre 729, Avellaneda");
		Cliente cliente4 = new Cliente(4, "Pablo Sánchez", 18397208, "Av. Belgrano 6193, Avellaneda");
		
		//Definimos los carritos:
		Carrito carrito1 = new Carrito(1, LocalDate.of(2024, 8, 31), LocalTime.of(14, 18, 23), cliente1);
		Carrito carrito2 = new Carrito(2, LocalDate.of(2024, 9, 5), LocalTime.of(10, 27, 49), cliente2);
		Carrito carrito3 = new Carrito(3, LocalDate.of(2024, 9, 10), LocalTime.of(18, 12, 45), cliente2);
		Carrito carrito4 = new Carrito(4, LocalDate.of(2024, 9, 25), LocalTime.of(15, 19, 11), cliente3);
		Carrito carrito5 = new Carrito(5, LocalDate.of(2024, 10, 1), LocalTime.of(9, 34, 21), cliente1);
		
		//Definimos los ítems de los carritos:
		ItemCarrito itemCarrito1 = new ItemCarrito(1, producto1, 3);
		ItemCarrito itemCarrito2 = new ItemCarrito(2, producto2, 2);
		ItemCarrito itemCarrito3 = new ItemCarrito(3, producto3, 6);
		ItemCarrito itemCarrito4 = new ItemCarrito(4, producto4, 2);
		ItemCarrito itemCarrito5 = new ItemCarrito(5, producto5, 2);
		ItemCarrito itemCarrito6 = new ItemCarrito(6, producto1, 4);
		ItemCarrito itemCarrito7 = new ItemCarrito(7, producto2, 4);
		ItemCarrito itemCarrito8 = new ItemCarrito(8, producto3, 10);
		ItemCarrito itemCarrito9 = new ItemCarrito(9, producto4, 1);
		ItemCarrito itemCarrito10 = new ItemCarrito(10, producto5, 3);
		ItemCarrito itemCarrito11 = new ItemCarrito(11, producto1, 2);
		ItemCarrito itemCarrito12 = new ItemCarrito(12, producto2, 1);
		ItemCarrito itemCarrito13 = new ItemCarrito(13, producto3, 4);
		ItemCarrito itemCarrito14 = new ItemCarrito(14, producto4, 3);
		ItemCarrito itemCarrito15 = new ItemCarrito(15, producto5, 1);
		
		//Agregamos ítems a cada carrito:
		//Carrito 1:
		carrito1.getLstItem().add(itemCarrito1);
		carrito1.getLstItem().add(itemCarrito8);
		carrito1.getLstItem().add(itemCarrito5);
		
		//Carrito 2:
		carrito2.getLstItem().add(itemCarrito7);
		carrito2.getLstItem().add(itemCarrito14);
		carrito2.getLstItem().add(itemCarrito15);
		
		//Carrito 3:
		carrito3.getLstItem().add(itemCarrito4);
		carrito3.getLstItem().add(itemCarrito6);
		carrito3.getLstItem().add(itemCarrito10);
		
		//Carrito 4:
		carrito4.getLstItem().add(itemCarrito12);
		carrito4.getLstItem().add(itemCarrito13);
		carrito4.getLstItem().add(itemCarrito11);
		
		//Carrito 5:
		carrito5.getLstItem().add(itemCarrito2);
		carrito5.getLstItem().add(itemCarrito3);
		carrito5.getLstItem().add(itemCarrito9);
		
		//Agregamos los productos al supermercado:
		supermercado.getAdmProducto().getLstProducto().add(producto1);
		supermercado.getAdmProducto().getLstProducto().add(producto2);
		supermercado.getAdmProducto().getLstProducto().add(producto3);
		supermercado.getAdmProducto().getLstProducto().add(producto4);
		supermercado.getAdmProducto().getLstProducto().add(producto5);
		supermercado.getAdmProducto().getLstProducto().add(producto6);
		
		//Agregamos los clientes al supermercado:
		supermercado.getAdmCliente().getLstCliente().add(cliente1);
		supermercado.getAdmCliente().getLstCliente().add(cliente2);
		supermercado.getAdmCliente().getLstCliente().add(cliente3);
		supermercado.getAdmCliente().getLstCliente().add(cliente4);
		
		//Agregamos los carritos al supermercado:
		supermercado.getAdmCarrito().getLstCarrito().add(carrito1);
		supermercado.getAdmCarrito().getLstCarrito().add(carrito2);
		supermercado.getAdmCarrito().getLstCarrito().add(carrito3);
		supermercado.getAdmCarrito().getLstCarrito().add(carrito4);
		supermercado.getAdmCarrito().getLstCarrito().add(carrito5);
	}
	
	@Test
	@DisplayName("Prueba de cálculo del total de un cliente existente")
	public void testCalcularTotalPorCliente_ClienteExiste() 
	{
		//Arrange:
	    Cliente cliente = supermercado.getAdmCliente().getLstCliente().get(0); //Cliente 1 del setup.
	    float totalEsperado = 41600; //Ajusta según los valores de los carritos configurados.
	    float totalObtenido = 0; //Inicializamos el total obtenido.

	    //Act:
	    try 
	    {
	    	totalObtenido = supermercado.calcularTotal(cliente); //Obtenemos el total del cliente.
	    }
	    catch(Exception e) 
	    {
	    	fail("No debería fallar: " + e.getMessage());
	    }
	    
	    //Assert:
	    assertEquals(totalEsperado, totalObtenido, 0.01, "El total calculado es correcto."); //Verificamos que el total obtenido sea el esperado.
	}

	@Test
	@DisplayName("Prueba de cálculo del total para un cliente que no existe")
	public void testCalcularTotalPorCliente_ClienteNoExiste() 
	{
		//Act:
		Cliente cliente = new Cliente(5, "Esteban Estévez", 29019381, "Av. 9 de Julio 7183, CABA"); //Un cliente que no existe en el setup.
		
		//Act y Assert:
		//Verificamos que se lance la excepción de cliente inexistente:
	    assertThrows(Exception.class, () -> supermercado.calcularTotal(cliente), "Debería lanzar excepción para cliente no existente.");
	}

	@Test
	@DisplayName("Prueba de cálculo del total por mes y año para un cliente")
	public void testCalcularTotalClientePorMesAnio_MesValido() 
	{
		//Arrange:
	    Cliente cliente = supermercado.getAdmCliente().getLstCliente().get(0); //Cliente 1 del setup.
	    int mes = 8; //Definimos el mes del caso de prueba.
	    int anio = 2024; //Definimos el año del caso de prueba.
	    float totalEsperado = 21600; //Ajusta según los valores de los carritos configurados.
	    float totalObtenido = 0; //Inicializamos el total obtenido.

	    //Act:
	    try 
	    {
	    	totalObtenido = supermercado.calcularTotal(mes, anio, cliente); //Obtenemos el total del cliente en el mes del año indicado.
	    } 
	    catch(Exception e) 
	    {
	    	fail("No debería fallar: " + e.getMessage());
	    }
	    
	    //Assert:
	    assertEquals(totalEsperado, totalObtenido, 0.01, "El total calculado es correcto."); //Verificamos que el total obtenido sea el esperado.
	}

	@Test
	@DisplayName("Prueba de cálculo del total para un mes inválido fuera de rango")
	public void testCalcularTotalClientePorMesAnio_MesFueraDeRango()
	{
		//Arrange:
	    Cliente cliente = supermercado.getAdmCliente().getLstCliente().get(0); //Cliente 1 del setup.
	    int mesInvalido = 13; //Mes fuera de rango.
	    int anio = 2024; //Definimos el año del caso de prueba.

	    //Act y Assert:
	    //Verificamos que se lance la excepción por mes inválido:
	    assertThrows(Exception.class, () -> supermercado.calcularTotal(mesInvalido, anio, cliente), "Debería lanzar excepción para mes fuera de rango.");
	}
	
	@Test
	@DisplayName("Prueba de cálculo del total de un cliente por fecha cuando el cliente es nulo")
	public void calcularTotalClientePorFecha_ClienteNulo() 
	{
	    //Arrange:
	    LocalDate fecha = LocalDate.of(2024, 8, 31);
	    Cliente clienteNulo = null;
	    
	    //Act y Assert:
	    assertThrows(Exception.class, () -> supermercado.calcularTotal(fecha, clienteNulo)); //Verificamos que se lance la excepción por cliente nulo.
	}
	
	@Test
	@DisplayName("Prueba de cálculo del total de un cliente por fecha cuando el cliente no existe")
	public void calcularTotalClientePorFecha_ClienteNoExiste() 
	{
	    //Arrange:
	    LocalDate fecha = LocalDate.of(2024, 8, 31);
	    Cliente clienteNoExiste = new Cliente(5, "Cliente Inexistente", 12345678, "Dirección Inexistente");
	    
	    //Act y Assert:
	    assertThrows(Exception.class, () -> supermercado.calcularTotal(fecha, clienteNoExiste)); //Verificamos que se lance la excepción por cliente inexistente. 
	}
	
	@Test
	@DisplayName("Prueba de cálculo del total de un cliente por fecha cuando el cliente existe")
	public void calcularTotalClientePorFecha_ClienteExiste() 
	{
	    //Arrange:
	    LocalDate fecha = LocalDate.of(2024, 8, 31); //Usamos una fecha que tenga carritos asociados en el setUp.
	    Cliente clienteExiste = supermercado.getAdmCliente().getLstCliente().get(0); //Obtener el cliente 1 del setUp.
	    float totalEsperado = 21600; //Definimos el total que debería retornar el método que estamos probando.
	    float totalObtenido = 0; //Inicializamos el total obtenido.
	    
	    //Act:
	    try 
	    {
	        totalObtenido = supermercado.calcularTotal(fecha, clienteExiste); //Calcular el total para este cliente en esta fecha.
	    } 
	    catch (Exception e) 
	    {
	        fail("No debería fallar: " + e.getMessage());
	    }
	    
	    //Assert:
	    assertEquals(totalEsperado, totalObtenido, "El total calculado es el esperado."); //Verificamos que sea el total esperado.
	}
	
	@Test
	@DisplayName( "Prueba de Calcular el total entre fechas cuando el Cliente = NULL")
	public void testCalcularTotalClienteEntreFechas_ClienteNulo() 
	{	
		//Arrange:
		LocalDate fechaInicio = LocalDate.of(2024, 7, 23);
		LocalDate fechaFin = LocalDate.of(2024, 8, 23);
		Cliente cliente = null;
		
		//Act y Assert:
		assertThrows(Exception.class, () -> supermercado.calcularTotal(fechaInicio, fechaFin, cliente)); //Verificamos que se lance la excepción de que el cliente es NULL.
	}
	
	@Test
	@DisplayName( "Prueba de Calcular el total entre fechas cuando el Cliente No Existe")
	public void testCalcularTotalClienteEntreFechas_ClienteNoExiste() 
	{
		//Arrange:
		LocalDate fechaInicio = LocalDate.of(2024, 7, 23);
		LocalDate fechaFin = LocalDate.of(2024, 8, 23);
		Cliente cliente = new Cliente(5, "Francisco Lopez", 19697708, "Av. Belgrano 6193, Burzaco"); //Pasamos un cliente que no pertenece a la lista de clientes.
		
		//Act y Assert:
		assertThrows(Exception.class, () -> supermercado.calcularTotal(fechaInicio, fechaFin, cliente)); //Verificamos que se lance la excepción de que el cliente No Existe.
    }
	
	@Test
	@DisplayName( "Prueba de Calcular el total entre fechas del Cliente Existente")
	public void testCalcularTotalClienteEntreFechas_ClienteExiste() 
	{
		//Arrange:
		LocalDate fechaInicio = LocalDate.of(2024, 8, 31);
		LocalDate fechaFin = LocalDate.of(2024, 10, 1);
		Cliente cliente = supermercado.traerCliente(34918491);		
		
		//Assert:
		float totalEsperado = 41600; //Definimos el total que debería retornar el método que estamos probando.
		float totalObtenido = 0; //Inicializamos el total obtenido.
		
		//Act:
		try 
		{
			totalObtenido = supermercado.calcularTotal(fechaInicio, fechaFin, cliente); //Obtenemos el total del cliente.
		}
		catch(Exception e) 
		{
			fail("No debería fallar: " + e.getMessage());
		}
		
		//Assert:
		assertEquals(totalEsperado, totalObtenido); //Verificamos que sea el total esperado.
    }
	
	@Test
	@DisplayName("Prueba de cálculo del total de un cliente por su DNI cuando el mismo no existe")
	public void calcularTotalClientePorDni_ClienteNoExiste() 
	{
		//Arrange:
		long dniCliente = 34918490; //Definimos el DNI del cliente del que se quiere obtener su total.
		
		//Act y Assert:
		assertThrows(Exception.class, () -> supermercado.calcularTotal(dniCliente)); //Verificamos que no se haya podido calcular el total del cliente.
	}
	
	@Test
	@DisplayName("Prueba de cálculo del total de un cliente por su DNI cuando el mismo existe")
	public void calcularTotalClientePorDni_ClienteExiste() 
	{
		//Arrange:
		long dniCliente = 34918491; //Definimos el DNI del cliente del que se quiere obtener su total.
		float totalEsperado = 41600; //Definimos el total que debería retornar el método que estamos probando.
		float totalObtenido = 0; //Inicializamos el total obtenido.
		
		//Act:
		try 
		{
			totalObtenido = supermercado.calcularTotal(dniCliente); //Obtenemos el total del cliente.
		}
		catch(Exception e) 
		{
			fail("No debería fallar: " + e.getMessage());
		}
		
		//Assert:
		assertEquals(totalEsperado, totalObtenido); //Verificamos que sea el total esperado.
	}
	
	@Test
	@DisplayName("Prueba de eliminación de un cliente cuando el mismo no existe")
	public void eliminarCliente_ClienteNoExiste() 
	{
		//Arrange:
		int idCliente = 5; //Definimos el id del cliente a eliminar.
				
		//Act y Assert:
		assertThrows(Exception.class, () -> supermercado.eliminarCliente(idCliente)); //Verificamos que el cliente no haya podido ser eliminado.
	}
	
	@Test
	@DisplayName("Prueba de eliminación de un cliente cuando el mismo tiene al menos un carrito asociado")
	public void eliminarCliente_ClienteConCarrito() 
	{
		//Arrange:
		int idCliente = 1; //Definimos el id del cliente a eliminar.
		
		//Act y Assert:
		assertThrows(Exception.class, () -> supermercado.eliminarCliente(idCliente)); //Verificamos que el cliente no haya podido ser eliminado.
	}
	
	@Test
	@DisplayName("Prueba de eliminación de un cliente cuando el mismo existe y no tiene algún carrito asociado")
	public void eliminarCliente_Eliminado() 
	{
		//Arrange:
		int longitudListaClientes = supermercado.getAdmCliente().getLstCliente().size(); //Obtenemos la longitud actual de la lista de clientes.
		int longitudListaClientesEsperada = longitudListaClientes - 1; //Esperamos que se elimine el cliente y disminuya en uno la longitud.
		int longitudListaClientesObtenida = longitudListaClientes; //Inicializamos la longitud obtenida con la actual.
		
		//Act:
		try 
		{
			supermercado.eliminarCliente(4); //Intentamos eliminar el cliente.
			longitudListaClientesObtenida = supermercado.getAdmCliente().getLstCliente().size(); //Obtenemos la longitud de la lista luego del intento de eliminación.
		}
		catch(Exception e) 
		{
			fail("No debería fallar: " + e.getMessage());
		}

		//Assert:
		assertEquals(longitudListaClientesEsperada, longitudListaClientesObtenida); //Verificamos si se eliminó.
	}
	
	@Test
	@DisplayName("Prueba de eliminación de un producto que no existe")
	public void eliminarProducto_NoExiste() 
	{
		//Arrange:
		int idProductoEliminar = 7; //Definimos el id del producto a eliminar.
		
		//Act y Assert:
		assertThrows(Exception.class, () -> supermercado.eliminarProducto(idProductoEliminar)); //Verificamos que se lance la excepción de producto inexistente.
	}
	
	@Test
	@DisplayName("Prueba de eliminación de un producto que está en al menos un carrito")
	public void eliminarProducto_EnCarrito()
	{
		//Arrange:
		int idProductoEliminar = 1; //Definimos el id del producto a eliminar.
		
		//Act y Assert:
		assertThrows(Exception.class, () -> supermercado.eliminarProducto(idProductoEliminar)); //Verificamos que se lance la excepción de producto en carrito.
	}
	
	@Test
	@DisplayName("Prueba de eliminación de un producto que existe y no está en algún carrito")
	public void eliminarProducto_Eliminado()
	{
		//Arrange:
		int longitudActualListaProductos = supermercado.getAdmProducto().getLstProducto().size(); //Obtenemos la longitud actual del listado de productos.
		int longitudEsperadaListaProductos = longitudActualListaProductos - 1; //Definimos la longitud que esperamos luego de la eliminación del producto.
		int longitudObtenidaListaProductos = longitudActualListaProductos; //Inicializamos la longitud obtenida luego de la eliminación.
		
		//Act:
		try 
		{
			supermercado.eliminarProducto(6); //Eliminamos el producto con el id indicado.
			longitudObtenidaListaProductos = supermercado.getAdmProducto().getLstProducto().size(); //Obtenemos la longitud actual de la lista.
		}
		catch(Exception e) 
		{
			fail("No debería fallar: " + e.getMessage());
		}
		
		//Assert:
		assertEquals(longitudEsperadaListaProductos, longitudObtenidaListaProductos); //Verificamos que se haya eliminado el producto.
	}
}
