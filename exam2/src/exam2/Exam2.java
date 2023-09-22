/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exam2;

import java.io.IOException;

/**
 *
 * @author pcast
 */
public class Exam2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            PSNUsers psnUsers = new PSNUsers();

           psnUsers.addUser("usuario1");
                                psnUsers.addUser("usuar8");

       //     psnUsers.addUser("usuario2");

         //   psnUsers.addTrophieTo("usuario1", "Juego1", "Trofeo1", Trophy_e.PLATINO);
          //  psnUsers.addTrophieTo("usuario1", "Juego2", "Trofeo2", Trophy_e.ORO);
      //     psnUsers.playerInfo("usuario1");


           psnUsers.deactivateUser("usuario1");
           psnUsers.playerInfo("usuario1");

       //     psnUsers.playerInfo("usuario2");
        }catch(IOException e){
            System.out.println("nosss");
        }
    }
    
}
