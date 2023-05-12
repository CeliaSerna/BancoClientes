package com.cursoceat.controller;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.Scanner;

import com.cursoceat.xml.schema.Clientes;
import com.cursoceat.xml.schema.Clientes.Cliente;

public class Controller {
	
	static Clientes miCliente = new Clientes();
	
	static Scanner entrada = new Scanner(System.in);
	
	static int opcion ;
	
	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub
		
		do {
		System.out.println("**** BANCO TOMELLOSO ****");
		System.out.println("*************************");
		System.out.println("1- Alta nuevo cliente");
		System.out.println("2- Generar XML");
		System.out.println("3- Leer XML");
		System.out.println("4- Salir");
		System.out.println("*************************");
		opcion = entrada.nextInt();
		
		switch(opcion) {
		case 1: generarXML();
		break;
		case 2:{ System.out.println("Archivo XML generado correctamente");
				System.out.println("----------------------------------");
				}
		break;
		case 3: leerXML();
		break;
		case 4: System.out.println("****** Hasta luego ******");
		break;
		
		}
		}while (opcion !=4);
	
	}
	
	public static void generarXML() throws JAXBException {
			
		Cliente nuevoCliente = new Cliente();
		
		System.out.println("Escriba su dni");
		String dni= new Scanner(System.in).next();
		nuevoCliente.setDni(dni);

		System.out.println("Escriba su nombre completo");
		String nombreCom= new Scanner(System.in).nextLine();
		nuevoCliente.setNombreComple(nombreCom);
		
		System.out.println("Escriba su fecha de nacimiento");
		String fechaNaci=new Scanner(System.in).next();
		nuevoCliente.setFechaNaci(fechaNaci);
		
		System.out.println("Escriba el dinero que va a ingresar");
		double saldo=new Scanner(System.in).nextDouble();
		nuevoCliente.setSaldo(saldo);
		
		miCliente.getCliente().add(nuevoCliente);
	     	
	
		JAXBContext miContexto = JAXBContext.newInstance(Clientes.class);
		Marshaller miLectura = miContexto.createMarshaller();
		miLectura.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
		miLectura.marshal(miCliente, new File("C:\\Users\\PROGRAMACION\\Documentos\\JAVA\\BancoClientes\\registro.xml"));

	
	}
	
	public static void leerXML() throws JAXBException{
		System.out.println("**** BANCO TOMELLOSO ****");
		System.out.println("*************************");
		System.out.println("Leer XML");
		System.out.println("*************************");
		
	File miArchivo = new File("C:\\Users\\PROGRAMACION\\Documentos\\JAVA\\BancoClientes\\registro.xml");
	JAXBContext miContexto = JAXBContext.newInstance(Clientes.class);
	
	Unmarshaller miLectura= miContexto.createUnmarshaller();
	
	Clientes miCliente = (Clientes)miLectura.unmarshal(miArchivo);
	
		for(Cliente c: miCliente.getCliente()) {
			System.out.println("DNI: "+c.getDni());
			System.out.println("Nombre: "+c.getNombreComple());
			System.out.println("Fecha de nacimiento: "+c.getFechaNaci());
			System.out.println("Saldo: "+c.getSaldo() + "€");
			System.out.println("-----------------");
		}
	

}
}
