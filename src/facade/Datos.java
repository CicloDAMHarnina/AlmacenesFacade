package facade;

import java.util.TreeSet;

import indice.AlmacenIndice;
import individual.AlmacenIndividual;
import individual.AlmacenIndividualSet;
import modelo.Articulo;
import modelo.Cliente;
import modelo.Pedido;
import modelo.Proveedor;
import ruta.AlmacenRutaDestino;
import ruta.AlmacenRutaMapeada;

//La suite de test tiene que daos en verde. Luego podeis probar esta clase
public class Datos {
	// VAmos a expresar los diferentes almacenes
	private String pathIndiceCliente = "/data/clientes/index.data";
	private String pathDatosCliente = "/data/clientes/clientes.data";
	private String pathDatosProveedores = "/data/proveedores/proveedores.data";
	private String pathMapaArticulos = "/data/articulos";
	private String pathArchivosMapaArticulos = "/articulos.map";
	private String pathPedidos = "/data/pedidos";
	private String pathNumeroPedido = "/data/pedidos/numPedido.data";

	// Cliente
	private AlmacenIndice<Cliente, String> clientes;
	// Proveedores
	private AlmacenIndividualSet<Proveedor> proveedores;
	// Articulo
	private AlmacenRutaMapeada<Articulo, String> articulos;
	// Pedidos
	private AlmacenRutaDestino<Pedido> pedidos;
	// numeroUltimoPedido
	private AlmacenIndividual<Integer> numeroPedido;

	public Datos() {

		clientes = new AlmacenIndice<Cliente, String>(pathIndiceCliente, pathDatosCliente);
		proveedores = new AlmacenIndividualSet<>(new TreeSet<Proveedor>(), pathDatosProveedores);
		articulos = new AlmacenRutaMapeada<>("art", pathArchivosMapaArticulos, pathMapaArticulos);
		pedidos = new AlmacenRutaDestino<>(pathPedidos, "ped");
		numeroPedido = new AlmacenIndividual<>();
		Integer obtener = numeroPedido.obtener(pathNumeroPedido);
		if (obtener == null) {
			numeroPedido.grabar(pathNumeroPedido, 0);
		}
	}

	public boolean grabar(Cliente cliente) {
		return clientes.grabar(cliente, cliente.getKey());
	}

	public boolean grabar(Proveedor proveedor) {
		return proveedores.grabar(proveedor);
	}

	public boolean grabar(Articulo articulo) {
		return articulos.grabar(articulo, articulo.getKey(), articulo.getIdArticulo());
	}

	public boolean grabar(Pedido pedido) {
		return pedidos.grabar(pedido.getCliente().getDni(), String.valueOf(pedido.getNumero()), pedido);
	}

	public boolean grabar(Integer integer) {
		return numeroPedido.grabar(pathNumeroPedido, integer);
	}

	/// Igual con el resto de metodos de obtener. Pondre uno de prueba
	public void obtener(Cliente cliente, String clave) {
		cliente = clientes.obtener(clave);
	};

	public void obtener(Pedido pedido,String dniCliente,int numeroPedido){
		pedido=pedidos.obtener(dniCliente, String.valueOf(numeroPedido));
	}
}
