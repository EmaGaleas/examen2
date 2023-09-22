/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exam2;


public class HashTable {
    Entry list;
    public HashTable() {
        this.list=null;
    }
    public void add(String user, long pos){
        Entry a=new Entry(user, pos);
        if(list==null){
            list=a;
        }else{
            Entry tmp=list;
            while(tmp.siguiente!=null){
                tmp=tmp.siguiente;
            }
            tmp.siguiente=a;
        }
    }
    public void remove(String user){
        long codigo=search(user);
        if(list== null){
            return;
        }
        if(list.pos== codigo){
            list=list.siguiente;
            return;
        }
        Entry tmp=list;
        while(tmp.siguiente!= null){
            if(tmp.siguiente.pos== codigo){
                tmp.siguiente=tmp.siguiente.siguiente;
                return;
            }
            tmp=tmp.siguiente;
        }
    }
    public long search(String user){
        Entry tmp=list;
        while (tmp!= null) {
            if (tmp.username.equals(user)){
                return tmp.pos;
            }
            tmp=tmp.siguiente;
        }
        return -1;
    }
    
}
