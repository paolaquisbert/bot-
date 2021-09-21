/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import java.util.List;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author DISPLAY
 */
public class hello_bot extends TelegramLongPollingBot{
    int  act=0; //0=Enviar Bienvenido... // -1=
    int suma=0;
    int valor=0;
    public static boolean isNumeric(String str) { 
    try {  
      Double.parseDouble(str);  
      return true;
    } catch(NumberFormatException e){  
      return false;  
    }  
  }
    @Override
    public String getBotToken() {
        return "2018293755:AAFuiyGDM8VYZlXzcJgx7jw5DVxe22-21fk";
    }
    
    public void Menu(Update update){
       SendMessage message = new SendMessage();
       message.setChatId(update.getMessage().getChatId().toString()); //Define a quien le vamos a enviar el mensaje
                message.setText("Bienvenido al Bot Calculadora\nSeleccione una de las siguientes opciones\n1.- Sumar 2 numeros\n2.-Calcular Fibonacci\n");
                try {
                    execute(message); // Envia el mensaje
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
    }
    
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Llego mensaje: " + update.toString());
        SendMessage message = new SendMessage();
            if(act==0)
            {
                Menu(update);
                act=-1;
            }
            else
            {
                
                if((isNumeric(update.getMessage().getText()) == true) && (Integer.parseInt(update.getMessage().getText())==1 || Integer.parseInt(update.getMessage().getText())==2) )
                {
                    String x=update.getMessage().getText();
                    int xint = Integer.parseInt(x); //Convierte a entero
                    if((xint==1 || xint==2) && valor==0)
                    {
                        valor=1;
                        act=xint;
                    }
                }
                else
                {
                    if(valor==0)
                    {
                        Menu(update);
                    }
                }
            }
            
        if(update.hasMessage()==true && act>0) { // Verificamos que tenga mensaje
            switch(act)
            {
                case 1:
                    message.setChatId(update.getMessage().getChatId().toString()); //Define a quien le vamos a enviar el mensaje
                    message.setText("Ingrese el primer número:");
                    try {
                        execute(message); // Envia el mensaje
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    act=3;
                    break;
                case 2: 
                    message.setChatId(update.getMessage().getChatId().toString()); //Define a quien le vamos a enviar el mensaje
                    message.setText("Funcionalidad no implementada, intente otro día");
                    try {
                        execute(message); // Envia el mensaje
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    act=-1;
                    valor=0;
                    Menu(update);
                    break;
                case 3:
                    if(isNumeric(update.getMessage().getText()) == true)
                    {
                        suma = suma + Integer.parseInt(update.getMessage().getText());
                        message.setChatId(update.getMessage().getChatId().toString()); //Define a quien le vamos a enviar el mensaje
                        message.setText("Ingrese el segundo número:");
                        try {
                            execute(message); // Envia el mensaje
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        act=4;
                    }
                    else
                    {
                        Menu(update);
                        act=-1;
                        valor=0;
                        suma=0;
                    }
                    
                    break;
                case 4:
                    if(isNumeric(update.getMessage().getText()) == true)
                    {
                        suma = suma + Integer.parseInt (update.getMessage().getText());
                        message.setChatId(update.getMessage().getChatId().toString()); //Define a quien le vamos a enviar el mensaje
                        message.setText("El resultado de la suma es: " + suma);
                        try {
                            execute(message); // Envia el mensaje
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        suma=0;
                        act=-1;
                        valor=0;
                        Menu(update);
                    }
                    else
                    {
                        Menu(update);
                        act=-1;
                        valor=0;
                        suma=0;
                    }
                    break;
            }
            
            
            // Creo el objeto para enviar un mensaje
            
            //Recibir 
        }else {
            // Creo el objeto para enviar un mensaje
            
        }
    }

    
    @Override
    public String getBotUsername() {
        return "ucb_java_bot";
    }

    
    
}
