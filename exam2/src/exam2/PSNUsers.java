/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exam2;

import java.io.*;
import java.util.Date;

public class PSNUsers {
    RandomAccessFile raf;
    HashTable users;

    public PSNUsers() throws IOException {
        this.raf=new RandomAccessFile("psn", "rw");
        this.users=new HashTable();
        reloadHashTable();
    }
    private void reloadHashTable() throws IOException {
        raf.seek(0);
        while (raf.getFilePointer()< raf.length()){
            long pos=raf.getFilePointer();
            String username=raf.readUTF();
            boolean active=raf.readBoolean();
            if (active){
                users.add(username, pos);
            }
        }
    }
    public void addUser(String username) throws IOException {
        long pos=raf.getFilePointer();
        raf.writeUTF(username);
        raf.writeInt(0);
        raf.writeInt(0);
        raf.writeBoolean(true); 
        users.add(username, pos);
    }

    public void deactivateUser(String username) throws IOException {
        long pos=users.search(username);
        if (pos!= -1) {
            raf.seek(pos);
            String user=raf.readUTF();
            if (user.equals(username)) {
//            raf.readInt();
//            raf.readInt();
                raf.skipBytes(8);
                raf.writeBoolean(false);
                users.remove(username);
            }
        }
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
                raf.writeUTF(new Date().toString());
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
            int p=raf.readInt();
            int t=raf.readInt();
            boolean a=raf.readBoolean();
            System.out.println("USERNAME:  "+u+"\nPUNTAJE:   "+p+"\n#TROFEOS:  "+t+"\nESTADO ACT:"+a);
            while (raf.getFilePointer()< raf.length()){
                String juego=raf.readUTF();
                String desc=raf.readUTF();
                String tipo=raf.readUTF();
                String fecha=raf.readUTF();
                System.out.println("TROFEO:\t "+fecha+" - "+tipo+" - "+juego+" - "+desc);
            }
        }
    }

}


