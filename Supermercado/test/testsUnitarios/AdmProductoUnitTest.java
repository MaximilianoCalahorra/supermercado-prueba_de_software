package testsUnitarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.AdmProducto;
import modelo.Producto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

///Clase AdmProductoUnitTest:
public class AdmProductoUnitTest
{
	//Atributo:
    private AdmProducto admProducto;

    @BeforeEach
    public void setUp() throws Exception 
    {
    	admProducto = new AdmProducto(); // Instanciamos el administrador de productos.

        //Definimos los mocks de productos:
        Producto producto1 = mock(Producto.class);
        Producto producto2 = mock(Producto.class);

        //Agregamos los mocks de productos a la lista:
        admProducto.getLstProducto().add(producto1);
        admProducto.getLstProducto().add(producto2);

        //Simulamos la obtención del ID y nombre de los productos:
        when(producto1.getIdProducto()).thenReturn(1);
        when(producto1.getNombre()).thenReturn("Producto1");

        when(producto2.getIdProducto()).thenReturn(2);
        when(producto2.getNombre()).thenReturn("Producto2");
    }

    //Caso: Agregar un producto nuevo
    @Test
    public void testAgregarProducto()
    {
        //Arrange:
        int longitudActualListaProductos = admProducto.getLstProducto().size(); //Longitud actual de la lista.
        int longitudEsperadaListaProductos = longitudActualListaProductos + 1;

        //Act:
        try 
        {
        	admProducto.agregarProducto("NuevoProducto", 100.0f); //Agregamos el producto.
        }
        catch(Exception e) 
        {
            fail("No debería fallar: " + e.getMessage());
        }

        //Assert:
        assertEquals(longitudEsperadaListaProductos, admProducto.getLstProducto().size(), "La lista de productos debería aumentar.");
    }

    //Caso: Intentar agregar un producto que ya existe
    @Test
    public void testAgregarProducto_YaExiste()
    {
        //Act y Assert:
        assertThrows(Exception.class, () -> admProducto.agregarProducto("Producto1", 150.0f), "Debería lanzar una excepción, el producto ya existe.");
    }

    //Caso: Modificar un producto existente
    @Test
    public void testModificarProducto() 
    {
        //Arrange:
        int idProducto = 1; //ID del producto a modificar.
        String nuevoNombre = "ProductoModificado";
        float nuevoPrecio = 210.0f;

        //Act:
        try 
        {
        	admProducto.modificarProducto(idProducto, nuevoNombre, nuevoPrecio); //Modificamos el producto.
        } 
        catch(Exception e) 
        {
            fail("No debería fallar: " + e.getMessage());
        }

        //Assert:
        Producto productoModificado = admProducto.getLstProducto().get(0);
        verify(productoModificado).setNombre(nuevoNombre);
        verify(productoModificado).setPrecio(nuevoPrecio);
    }

    //Caso: Intentar modificar un producto que no existe
    @Test
    public void testModificarProducto_ProductoNoExiste()
    {
        //Act y Assert:
        assertThrows(Exception.class, () -> admProducto.modificarProducto(99, "ProductoInexistente", 300.0f), "Debería lanzar una excepción, el producto no existe.");
    }

    //Caso: Traer producto por ID (Producto existe)
    @Test
    public void testTraerProductoPorId_Existe() 
    {
        //Arrange:
        int idProducto = 1;

        //Act:
        Producto producto = admProducto.traerProducto(idProducto);

        //Assert:
        assertNotNull(producto, "El producto debería existir.");
        assertEquals(idProducto, producto.getIdProducto(), "El ID del producto debería ser el esperado.");
    }

    //Caso: Traer producto por ID (Producto no existe)
    @Test
    public void testTraerProductoPorId_NoExiste() 
    {
        //Act:
        Producto producto = admProducto.traerProducto(99); //ID inexistente.

        //Assert:
        assertNull(producto, "El producto no debería existir.");
    }

    //Caso: Traer producto por nombre (Producto existe)
    @Test
    public void testTraerProductoPorNombre_Existe() 
    {
        //Arrange:
        String nombreProducto = "Producto1";

        //Act:
        Producto producto = admProducto.traerProducto(nombreProducto);

        //Assert:
        assertNotNull(producto, "El producto debería existir.");
        assertEquals(nombreProducto, producto.getNombre(), "El nombre del producto debería ser el esperado.");
    }

    //Caso: Traer producto por nombre (Producto no existe)
    @Test
    public void testTraerProductoPorNombre_NoExiste() 
    {
        //Act:
        Producto producto = admProducto.traerProducto("ProductoInexistente"); //Nombre que no existe.

        //Assert:
        assertNull(producto, "El producto no debería existir.");
    }
}