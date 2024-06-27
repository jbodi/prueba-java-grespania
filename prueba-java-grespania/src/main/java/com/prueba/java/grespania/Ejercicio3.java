import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class Ejercicio3 extends Thread {
    public static void main(String[] args) {
        ejecutarEjercicio3();
    }

    public static void ejecutarEjercicio3() {
        // Creamos primero el fichero
        File fichero = crearFichero("ficheroPrueba.txt");

        // Enviamos el fichero a una URL ficticia
        String urlFicticia = "http://example.com/convertFile";
        enviarFicheroURLFicticia(urlFicticia, fichero);

        // Simulamos la conversión y recibir el archivo en el directorio.
        Ejercicio3 hilo = new Ejercicio3();
        hilo.start();
        // Esperamos a que acabe el hilo "la conversión" para continuar con la ejecución
        while (hilo.isAlive()) {
        }

        // Leemos el archivo que se ha dejado en nuestro directorio local
        File archivoRecibido = leerArchivoLocal();

        try {
            Scanner sc = new Scanner(archivoRecibido);
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
            System.out.println(sb.toString());
            sc.close();
        } catch (Exception e) {
            System.out.println("Error al leer fichero");
            e.printStackTrace();
        }
    }

    public static File leerArchivoLocal() {
        return new File("prueba-java-grespania\\resources\\file.txt");
    }

    public static void enviarFicheroURLFicticia(String urlFicticia, File ficheroAConvertir) {

        try {
            URL url = new URL(urlFicticia);

            // Creamos la conexión HTTP a la URL, suponemos un POST
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("Content-Length", String.format("%d", ficheroAConvertir.length()));

            // Creamos la request
            FileInputStream fis = new FileInputStream(ficheroAConvertir);
            OutputStream os = conn.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesLeidos = fis.read(buffer);
            while (bytesLeidos != -1) {
                os.write(buffer, 0, bytesLeidos);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Archivo enviado con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al enviar el fichero");
            e.printStackTrace();
        }

    }

    public static File crearFichero(String nombreFichero) {
        File fichero = new File(nombreFichero);
        try {
            fichero.createNewFile();
        } catch (Exception e) {
            System.out.println("Error al crear el fichero");
            e.printStackTrace();
        }

        return fichero;
    }

    public void run() {
        System.out.println("Convirtiendo archivo, espere 10 segundos.");
        try {
            Thread.sleep(10000); // Simular tiempo de conversión, 10 segundos esperando.
        } catch (InterruptedException e) {
            System.out.println("Error al convertir archivo");
            e.printStackTrace();
        }
    }
}
