package Extras;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Historial {
	
	private final String ruta    = "res/historial/historial.bin";
	private final String rutaXML = "res/historial/winners.xml";
	
	private int numeroEquipos;
	private int numeroRondas;
	private String nombreEquipo;
	private String nombrePlaneta;

	private Calendar fecha; 
	private String mostrarFecha;
	//XML
	private ArrayList<String[]> winners = new ArrayList<String[]>();
	
	public Historial() {
		
	}
	
	public Historial (int equipos, int rondas, String ganador, String planeta, String fecha) {

		this.numeroEquipos  = equipos;
		this.numeroRondas 	= rondas;
		this.nombreEquipo 	= ganador;
		this.nombrePlaneta  = planeta;
		this.mostrarFecha   = fecha;
	}
	//Archivo tipo bin
	public void CargarVarHistorial(int equipos, int rondas, String ganador, String planeta) {
		
		this.numeroEquipos  = equipos;
		this.numeroRondas 	= rondas;
		this.nombreEquipo 	= ganador;
		this.nombrePlaneta  = planeta;
		//Crear una instancia de Calendar y guardar la fecha que se crear el objecto
		this.fecha = Calendar.getInstance();
		fecha.add(Calendar.DATE, 1);
	}
	
	public boolean GuardarHistorial () {

		boolean correcto = true;

		FileOutputStream fos = null;
        DataOutputStream salida = null;

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        try {
        	//El true del final indica que se abre el fichero para añadir datos al final del fichero
            fos = new FileOutputStream(ruta, true);
            salida = new DataOutputStream(fos);

            salida.writeInt(this.numeroEquipos);
            salida.writeInt(this.numeroRondas);
            salida.writeUTF(this.nombreEquipo);
            salida.writeUTF(this.nombrePlaneta);
            //Parchear la fecha en un formato año-mes-dia y guardar en como String
            String fechaStr = format1.format(fecha.getTime());
            salida.writeUTF(fechaStr);

        } catch (FileNotFoundException e) {
        	correcto = false;
            System.out.println(e.getMessage());
        } catch (IOException e) {
        	correcto = false;
        	System.out.println(e.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (salida != null) {
                    salida.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

		return correcto;
	}

	public ArrayList<Historial> MostrarHistorial () {
		
		ArrayList<Historial> allHistorial = new ArrayList<Historial>();
		CargarHistorial(allHistorial);
		
		return allHistorial;
		
	}
	
	//Archivo tipo XML
	public void EjecutarXML() {
		
		 boolean repetido = false;
		 File xml = new File(this.rutaXML);
		 
		 if (xml.isFile()){
            LeerXML(this.winners);

            for (String[] str : this.winners) {

                if(this.nombreEquipo.equals(str[0])){
                    int numVic = Integer.parseInt(str[1]);
                    numVic++;
                    str[1] = Integer.toString(numVic);
                    repetido = true;
                }
			}   
		}
		 

        if (!repetido) {
            String [] win = {this.nombreEquipo, "1"};
            this.winners.add(win);
        } 
		
        CrearXML(this.winners);
	}
	
	public ArrayList<String> MostrarRanking() {
		File xml = new File(this.rutaXML);
		ArrayList<String> ganadores = new ArrayList<String>();
		
		if (xml.isFile()) { 
			//Actualizamos la ArraList de ganadores con el fichero.		
			LeerXML(this.winners);
			
			int[] listaGanador = new int[this.winners.size()];
			int i=0;
			
			for (String[] str : this.winners) {
				
				listaGanador[i] = Integer.parseInt(str[1]);
				i++;
			}
			
			Arrays.sort(listaGanador);
			for (i = listaGanador.length-1; i > -1; i--) {
				int j = 0; 
				for (String[] win : this.winners) {
					if (listaGanador[i] == Integer.parseInt(win[1])) {
//						System.out.println("<| "+"|Nombre del equipo: "+win[0]+" |Victorias: "+win[1]+" |>");
						ganadores.add(" |Nombre del equipo: "+win[0]+" |Victorias: "+win[1]+"|");
						j++;
					}
				}
				
				if(j > 1){
					i -= j;
				}
			}
			this.winners.clear();
		}else {
//			System.out.println("<| No hay ganadores registrados |>");
			ganadores.add("<| No hay ganadores registrados |>");
		}
		
		return ganadores;
		
	}
	
	private void CrearXML(ArrayList<String[]> winners) {
		try {
			 
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// elemento raiz
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("teams");
			doc.appendChild(rootElement);

            for (String[] str : winners) {

				Element win  = doc.createElement("winners");
                Element name = doc.createElement("name");
                Element vic  = doc.createElement("victories");
		
				name.appendChild(doc.createTextNode(str[0]));
                vic.appendChild(doc.createTextNode(str[1]));

                win.appendChild(name);
                win.appendChild(vic);

				rootElement.appendChild(win);
			}
			// escribimos el contenido en un archivo .xml
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(this.rutaXML));
			
			transformer.transform(source, result);
	 
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}
	
	private void LeerXML(ArrayList<String[]> winners) {
		try {

			File fXmlFile = new File(this.rutaXML);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(fXmlFile);

            document.getDocumentElement().normalize();

            NodeList listaEmpleados = document.getElementsByTagName("winners");
            for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
                Node nodo = listaEmpleados.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;

                    String[] win = new String [2];
                    win[0] = element.getElementsByTagName("name").item(0).getTextContent();
                    win[1] = element.getElementsByTagName("victories").item(0).getTextContent();

                    winners.add(win);
                }
            }
			
        } catch (Exception e) {
            e.printStackTrace();
        }   
	}
	
	private boolean CargarHistorial (ArrayList<Historial> allHistorial) {

		boolean correcto = true;
		
		FileInputStream fos = null;
		DataInputStream entrada = null;
		
		try{
			fos = new FileInputStream(ruta);
            entrada = new DataInputStream(fos);
        
    		do{
    			Historial h = new Historial(entrada.readInt(), entrada.readInt(), entrada.readUTF(), entrada.readUTF(), entrada.readUTF());
    			allHistorial.add(h);
    		}while(true);
    	
   		}catch(EOFException e){
   			//... Nos saltara este mensaje
   		}catch(IOException e){
   			correcto = false;
   			System.out.println("Error E/S " + e.getMessage());
   		}finally {
             try {
                 if (fos != null) {
                     fos.close();
                 }
                 if (entrada != null) {
                	 entrada.close();
                 }
             }catch (IOException e) {
                 System.out.println(e.getMessage());
             }
         }

		return correcto;	
	}

	public int getNumeroEquipos() {
		return numeroEquipos;
	}

	public int getNumeroRondas() {
		return numeroRondas;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public String getNombrePlaneta() {
		return nombrePlaneta;
	}

	public String getMostrarFecha() {
		return mostrarFecha;
	}

	
}

