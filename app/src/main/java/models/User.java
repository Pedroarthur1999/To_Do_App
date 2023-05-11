/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author eu
 */
public class User {
    
    private int id;
    private String login;
    private String senha;
    private boolean esta_logado;
    
    
    public User(int id, String login, String senha, boolean esta_logado){
        
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.esta_logado = esta_logado;
                        
    }            
    public User(){
        
       // this.esta_logado = false;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setEstaLogado(boolean esta_logado){
        this.esta_logado = esta_logado;
        
    }
    public boolean getEstaLogado(){
        return esta_logado;
    }

    @Override
    public String toString() {
        return "User{" + "login=" + login + '}';
    }
    
    
    
}
