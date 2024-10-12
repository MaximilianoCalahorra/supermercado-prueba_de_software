package testsUnitarios;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import modelo.Carrito;
import modelo.Cliente;
import modelo.Producto;
import modelo.ItemCarrito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;

///Clase CarritoUnitTest:
public class CarritoUnitTest
{
	//Atributo:
	private Carrito carrito;

	@BeforeEach
	public void setUp() throws Exception 
	{
		//Instanciamos un carrito:
		carrito = new Carrito(1, LocalDate.of(2023, 5, 10), LocalTime.of(18, 10, 25), mock(Cliente.class));

		//Definimos los mocks de ItemCarrito:
		ItemCarrito item1 = mock(ItemCarrito.class);
		ItemCarrito item2 = mock(ItemCarrito.class);

		//Definimos los mocks de Producto:
		Producto producto1 = mock(Producto.class);
		Producto producto2 = mock(Producto.class);

		//Añadimos los ítems a la lista:
		carrito.getLstItem().add(item1);
		carrito.getLstItem().add(item2);

		//Simulamos el id de cada ítem:
		when(item1.getIdItem()).thenReturn(1);
		when(item2.getIdItem()).thenReturn(2);
		
		//Simulamos el Producto de cada ítem:
		when(item1.getProducto()).thenReturn(producto1);
		when(item2.getProducto()).thenReturn(producto2);
		
		//Simulamos la cantidad de cada ítem:
		when(item1.getCantidad()).thenReturn(2);
		when(item2.getCantidad()).thenReturn(4);

		//Simulamos el subtotal de cada ítem:
		when(item1.calcularSubTotal()).thenReturn(300f);
		when(item2.calcularSubTotal()).thenReturn(500f);

		//Simulamos el id de cada producto:
		when(producto1.getIdProducto()).thenReturn(1);
		when(producto2.getIdProducto()).thenReturn(2);
	}

	@Test
	@DisplayName("Prueba de obtención de un ítem de carrito que existe")
	public void testTraerItemCarritoExiste()
	{
		//Arrange:
		Producto producto = carrito.getLstItem().get(0).getProducto(); //Definimos el producto del ítem a buscar.
		int idItemEsperado = 1; //Definimos el id del ítem que esperamos encontrar.

		//Act:
		int idItemObtenido = carrito.traerItemCarrito(producto).getIdItem(); //Obtenemos el id del ítem buscado.

		//Assert:
		assertEquals(idItemEsperado, idItemObtenido); //Verificamos que el id del ítem encontrado sea el esperado.
	}

	@Test
	@DisplayName("Prueba de obtención de un ítem de carrito que no existe")
	public void testTraerItemCarritoNoExiste() 
	{
		//Arrange:
		Producto producto = mock(Producto.class); //Definimos el producto que tiene que tener el ítem buscado.
		
		//Act:
		ItemCarrito itemObtenido = carrito.traerItemCarrito(producto); //Obtenemos el ítem buscado.

		//Assert:
		assertNull(itemObtenido); //Verificamos que el ítem obtenido sea nulo.
	}

	@Test
	@DisplayName("Prueba de inserción de un ítem cuando ya existe uno para el producto")
	public void testAgregarItemYaExisteProducto() 
	{
		//Arrange:
		ItemCarrito itemCarrito = carrito.getLstItem().get(0); //Obtenemos el ítem carrito al que queremos agregarle cantidad.
		int cantidadActual = itemCarrito.getCantidad(); //Obtenemos la cantidad actual del ítem.
		Producto producto = itemCarrito.getProducto(); //Obtenemos el producto del ítem.
		int cantidadAgregar = 2; //Definimos la cantidad a agregar. 
		int cantidadEsperada = cantidadActual + cantidadAgregar; //La cantidad esperada es la actual más la que vamos a agregar.

		//Act:
		carrito.agregarItem(producto, cantidadAgregar); //Sumamos la cantidad especificada al ítem con el producto indicado.

		//Assert:
		//Verificamos que el ítem haya llamado al set de cantidad para acumular a la actual la que queremos agregar:
		verify(carrito.getLstItem().get(0)).setCantidad(cantidadEsperada); 
	}
    
	@Test
	@DisplayName("Prueba de inserción de un ítem cuando no existe uno para el producto")
	public void testAgregarNuevoItem()
	{
		//Arrange:
		Producto producto = mock(Producto.class); //Definimos el producto del nuevo ítem.
		int cantidad = 2; //Definimos la cantidad del nuevo ítem.
		int tamLista = carrito.getLstItem().size(); //Obtenemos la longitud actual de la lista de ítems.
        int tamListaEsperado = tamLista + 1; //Esperamos que luego de la inserción la longitud aumente en uno.
        
        //Act:
		carrito.agregarItem(producto, cantidad); //Agregamos el ítem con el producto y cantidad indicados.
		tamLista = carrito.getLstItem().size(); //Obtenemos la longitud de la lista luego de la inserción.
 
		//Assert:
	    assertEquals(tamListaEsperado, tamLista); //Verificamos que la longitud obtenida coincida con la esperada.
	}

	@Test
	@DisplayName("Prueba de eliminación de un ítem del carrito cuando el producto no existe en ninguno de ellos")
	public void testEliminarItem_ProductoNoExiste() 
	{
		//Act y Assert:
		assertThrows(Exception.class, () -> carrito.eliminarItem(null, 3)); //Verificamos que se lance la excepción de producto nulo.
	}
	
	@Test
	@DisplayName("Prueba de eliminación de un ítem del carrito cuando el ítem del carrito no existe")
	public void testEliminarItem_ItemCarritoNoExiste() 
	{
		//Arrange:
		Producto producto = mock(Producto.class); //Definimos el producto del ítem a eliminar.
		
		//Act y Assert:
		assertThrows(Exception.class, () -> carrito.eliminarItem(producto, 3)); //Verificamos que se lance la excepción de ítem no existente.
	}
	
	@Test
	@DisplayName("Prueba de eliminación de un ítem del carrito cuando la cantidad que se quiere eliminar es menor a la actual")
	public void testEliminarItem_CantidadAEliminarMenorAActual() 
	{
		//Arrange:
		ItemCarrito item = carrito.getLstItem().get(0); //Obtenemos el ítem al que le vamos a reducir su cantidad.
		Producto producto = item.getProducto(); //Definimos el producto del ítem a reducir su cantidad.
		int cantidadActual = item.getCantidad(); //Obtenemos la cantidad actual del ítem.
		int cantidadEliminar = 1; //Definimos la cantidad a eliminar.
		int longitudListaItemsCarrito = carrito.getLstItem().size(); //Obtenemos la longitud actual de la lista de ítems.
		int longitudListaItemsCarritoEsperada = longitudListaItemsCarrito; //Esperamos que no se elimine el ítem.
		
		//Act:
		try 
		{
			carrito.eliminarItem(producto, cantidadEliminar); //Reducimos la cantidad del ítem.
			longitudListaItemsCarrito = carrito.getLstItem().size(); //Obtenemos la longitud de la lista de ítems.
		} 
		catch(Exception e)
		{
			fail("No debería fallar: " + e.getMessage());
		}
		
		//Assert:
		assertEquals(longitudListaItemsCarritoEsperada, longitudListaItemsCarrito); //Verificamos que no se haya eliminado el ítem.
		verify(item).setCantidad(cantidadActual - cantidadEliminar); //Verificamos que se haya seteado la cantidad del ítem con la disminución solicitada.
	}
	
	@Test
	@DisplayName("Prueba de eliminación de un ítem del carrito cuando la cantidad que se quiere eliminar es mayor o igual a la actual")
	public void testEliminarItem_CantidadAEliminarMayorOIgualAActual() 
	{
		//Arrange:
		Producto producto = carrito.getLstItem().get(1).getProducto(); //Obtenemos el producto del ítem a eliminar.
		int cantidadEliminar = 4; //Definimos la cantidad a eliminar.
		int longitudListaItemsCarrito = carrito.getLstItem().size(); //Obtenemos la cantidad de ítems que hay actualmente.
		int longitudListaItemsCarritoEsperada = longitudListaItemsCarrito - 1; //Esperamos que la longitud disminuya en uno.
		
		//Act:
		try 
		{
			carrito.eliminarItem(producto, cantidadEliminar); //Eliminamos el ítem.
			longitudListaItemsCarrito = carrito.getLstItem().size(); //Obtenemos la longitud del listado de ítems.
		} 
		catch(Exception e)
		{
			fail("No debería fallar: " + e.getMessage());
		}
		
		//Assert:
		assertEquals(longitudListaItemsCarritoEsperada, longitudListaItemsCarrito);	//Verificamos que se haya eliminado el ítem.
	}

	@Test
	@DisplayName("Prueba del cálculo del total del carrito")
	public void testCalcularTotal() 
	{
		//Arrange:
		float totalEsperado = 800; //Definimos el total que esperamos obtener.
		
		//Act:
		float totalObtenido = carrito.calcularTotal(); //Obtenemos el total calculado.
		
		//Assert:
		assertEquals(totalEsperado, totalObtenido); //Verificamos que el total obtenido coincida con el esperado.
	}
}
