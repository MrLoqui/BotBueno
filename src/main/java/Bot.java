import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.util.Scanner;
import java.io.File;

import java.io.FileNotFoundException;


public class Bot extends TelegramLongPollingBot {



    @Override
    public void onUpdateReceived(Update update)  {

            //Se crea el objeto para poder enviar mensajes
            SendMessage enviarMensaje = new SendMessage();
            //Se crea el objeto  para recibir un mensaje
            String mensajeRecibido = update.getMessage().getText();
            boolean encontrado = false;

            // Se obtiene el mensaje escrito por el usuario quitando espacios para que no haya problemas
             mensajeRecibido = mensajeRecibido.replace( ' ', '_' );

            //El nombre de todos los campeones
            File file = new File( "Campeones.txt" );
            Scanner sc = null;
            try {
                sc = new Scanner( file );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // Busca el nombre de todos los personajes y si encuentra envia la informacion
            while (sc.hasNextLine()) {
                //Si existe busca el link del campeon y lo muestra
                if (sc.nextLine().equals( mensajeRecibido )) {
                    enviarMensaje.setText( "Las maestrias de " + mensajeRecibido + " son: euw.op.gg/champion/" + mensajeRecibido + "/statistics" );
                    encontrado = true;
                }
            }
            //Si no existe te envia un mensaje de error

            if (encontrado == false) {
                System.out.println( "" );
                enviarMensaje.setText( "Escriba el nombre del campeon de forma correcta" );
            }
            //Aqui se envia cualquiera de los dos mensajes
            enviarMensaje.setChatId( update.getMessage().getChatId() );
            try {
                execute( enviarMensaje );
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        }



    @Override
            public String getBotUsername () {
                return "jorgeDAM_bot";
            }

            @Override
            public String getBotToken () {
                return "1196014519:AAEInQfjaBPunnhBGgAcGq8l_LuczlV-Vl4";
            }

    }