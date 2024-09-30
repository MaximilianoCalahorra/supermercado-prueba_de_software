package testsUnitarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.AdmCliente;
import modelo.Cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

///Clase AdmClienteUnitTest:
public class AdmClienteUnitTest
{
	//Atributo:
    private AdmCliente admCliente;

    @BeforeEach
    public void setUp() throws Exception
    {
    	admCliente = new AdmCliente(); //Instanciamos un administrador de clientes.
    	
    	//Definimos los mocks de clientes:
		Cliente cliente1 = mock(Cliente.class);
		Cliente cliente2 = mock(Cliente.class);
		Cliente cliente3 = mock(Cliente.class);
		
		//Agregamos los mocks de clientes al listado del administrador:
		admCliente.getLstCliente().add(cliente1);
		admCliente.getLstCliente().add(cliente2);
		admCliente.getLstCliente().add(cliente3);		
		
		//Simulamos la obtención del id de cada cliente:
		when(cliente1.getIdCliente()).thenReturn(1);
		when(cliente2.getIdCliente()).thenReturn(2);
		when(cliente3.getIdCliente()).thenReturn(3);
		
		//Simulamos la obtención del DNI de cada cliente:
		when(cliente1.getDni()).thenReturn(11111111L);
		when(cliente2.getDni()).thenReturn(22222222L);
		when(cliente3.getDni()).thenReturn(33333333L);
    }
    
    //Caso: Agregar un cliente nuevo
    @Test
    public void testAgregarNuevoCliente()
    {
        //Arrange:
        int longitudActualListaClientes = admCliente.getLstCliente().size(); //Obtenemos la longitud actual de la lista de clientes.
		int longitudEsperadaListaClientes = longitudActualListaClientes + 1; //Esperamos que aumente en uno luego de la inserción.
		int longitudObtenidaListaClientes = longitudActualListaClientes; //Inicializamos la longitud obtenida con la actual.	
        
        //Act:
		try
		{
			admCliente.agregarCliente("Jose Sand", 12345678L, "Calle Falsa 123"); //Agregamos el cliente a la lista.
			longitudObtenidaListaClientes = admCliente.getLstCliente().size(); //Obtenemos la longitud actual de la lista.
		} 
		catch(Exception e) 
		{
			fail("No debería fallar: " + e.getMessage());
		}
		
		//Assert:
		assertEquals(longitudEsperadaListaClientes, longitudObtenidaListaClientes); //Verificamos que la cantidad obtenida sea igual a la esperada.
    }

    //Caso: Intentar agregar un cliente con DNI duplicado
    @Test
    public void testAgregarClienteDuplicado()
    {
        //Act y Assert:
    	assertThrows(Exception.class, () -> admCliente.agregarCliente("Lautaro Acosta", 11111111L, "Otra Calle 456")); //Verificamos que se lance la excepción de cliente ya existente.
    }

    //Caso: Traer cliente por ID (Cliente existe)
    @Test
    public void testTraerClientePorIdExistente()
    {
    	//Arrange:
    	int idCliente = 1; //Definimos el id del cliente a encontrar.
    	
    	//Act:
    	Cliente cliente = admCliente.traerCliente(idCliente); //Obtenemos el cliente con ese id.
    	
    	//Assert:
    	assertNotNull(cliente, "El cliente debería existir."); //Verificamos que hayamos encontrado un cliente.
    	//Verificamos que el id del cliente encontrado sea el esperado:
        assertEquals(idCliente, cliente.getIdCliente(), "El ID del cliente debería ser" + idCliente + "."); 
    }

    //Caso: Traer cliente por ID (Cliente no existe)
    @Test
    public void testTraerClientePorIdNoExistente() 
    {
    	//Act:
    	Cliente cliente = admCliente.traerCliente(99); //Buscamos el cliente con ese id.
    	
    	//Assert:
        assertNull(cliente, "El cliente no debería existir."); //Verificamos que no se haya encontrado el cliente.
    }

    //Caso: Traer cliente por DNI (Cliente existe)
    @Test
    public void testTraerClientePorDniExistente()
    {
    	//Arrange:
    	long dniCliente = 11111111L; //Definimos el DNI del cliente a obtener.
    	
    	//Act:
        Cliente cliente = admCliente.traerCliente(dniCliente); //Obtenemos el cliente con el DNI indicado.
        
        //Assert:
        assertNotNull(cliente, "El cliente debería existir."); //Verificamos que hayamos obtenido un cliente.
        assertEquals(dniCliente, cliente.getDni(), "El DNI del cliente debería ser 87654321."); //Verificamos que el DNI del cliente obtenido sea el esperado.
    }

    //Caso: Traer cliente por DNI (Cliente no existe)
    @Test
    public void testTraerClientePorDniNoExistente()
    {
        //Act:
        Cliente cliente = admCliente.traerCliente(99999999L); //Buscamos el cliente con ese DNI.
        
        //Assert:
        assertNull(cliente, "El cliente no debería existir."); //Verificamos que no se haya encontrado el cliente.
    }
}
