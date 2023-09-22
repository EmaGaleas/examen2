/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exam2;

import java.io.*;
import java.util.Date;
import javax.swing.JOptionPane;

public class PSNUsers {
    RandomAccessFile raf;
    HashTable users;

    public PSNUsers() {
        try {
            this.raf = new RandomAccessFile("psn", "rw");
            this.users = new HashTable();
            reloadHashTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void reloadHashTable(){
        try {
            raf.seek(0);
            while (raf.getFilePointer()< raf.length()) {
                long pos=raf.getFilePointer();
                String username=raf.readUTF();
                users.add(username, pos);
                raf.skipBytes(9); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addUser(String username) throws IOException {
        if (users.search(username) != -1){
            JOptionPane.showMessageDialog(null, "YA EXISTE");
            return;
        }
        JOptionPane.showMessageDialog(null, "AGREGADO CORRECTAMENTE");
        raf.seek(raf.length());
        long pos = raf.length();
        raf.writeUTF(username);
        users.add(username, pos);
        raf.writeBoolean(true); 
        raf.writeInt(0); //puntos
        raf.writeInt(0); //trofeso
    }
//////////
    public void deactivateUser(String username) throws IOException {
        raf.seek(0);
        while (raf.getFilePointer() < raf.length()) {
            long pos = raf.getFilePointer(); 
            String user = raf.readUTF();

            if (user.equals(username)) {
                raf.seek(pos); 
                raf.readUTF(); 
                raf.writeBoolean(false); 
                JOptionPane.showMessageDialog(null, "USUARIO DESACTIVADO");           
                System.out.println("Usuario desactivado");
                return; 
            } else {
                raf.readBoolean();
                raf.readInt(); 
                raf.readInt(); 
            }
        }
                JOptionPane.showMessageDialog(null, "NO EXISTE");
        System.out.println("El usuario no existe");
    }



    public void addTrophieTo(String username, String trophyGame, String trophyName, Trophy_e type) throws IOException {
        long pos=users.search(username);
        if (pos!= -1) {
            raf.seek(pos);
            String user=raf.readUTF();
            if (user.equals(username)){
                int p=raf.readInt();
                int t=raf.readInt();
                raf.writeBoolean(true);
                raf.writeUTF(trophyGame);
                raf.writeUTF(trophyName);
                raf.writeUTF(type.name());
                Date fecha = new Date();
                raf.writeUTF(fecha.toString());
                raf.seek(pos+4); 
                raf.writeInt(p+type.points);
                raf.writeInt(t+1);
            }
        }
    }
    
    public void playerInfo(String username) throws IOException {
        long pos=users.search(username);
        if (pos!= -1){
            raf.seek(pos);
            String u=raf.readUTF();
            boolean a=raf.readBoolean();
            int p=raf.readInt();
            int t=raf.readInt();
            JOptionPane.showMessageDialog(null, "USERNAME:  "+u+"\nPUNTAJE:   "+p+"\n#TROFEOS:  "+t+"\nESTADO ACT:"+a);
            System.out.println("USERNAME:  "+u+"\nPUNTAJE:   "+p+"\n#TROFEOS:  "+t+"\nESTADO ACT:"+a);
            while (raf.getFilePointer()< raf.length()){
                String juego=raf.readUTF();
                String desc=raf.readUTF();
                String tipo=raf.readUTF();
                String fecha=raf.readUTF();
                System.out.println("TROFEO:\t "+fecha+" - "+tipo+" - "+juego+" - "+desc);
            }
        }else{
            JOptionPane.showMessageDialog(null, "NO EXISTE");
        }
         

    }

}


