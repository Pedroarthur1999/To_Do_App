/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package toDoApp;

import controls.ProjectsControls;
import models.Projects;

public class App {
  
    public static void main(String[] args) {
  
        ProjectsControls projectControl = new ProjectsControls();
        Projects project = new Projects();
        
        project.setName("Academia");
        project.setDescription("Criando projeto");
        System.out.println(project.getCreateDate());
        System.out.println(project.getUpdateDate());
        
        Projects project2 = new Projects();
        
        project2.setName("Dieta");
        project2.setDescription("Usado para calcular a dieta!");
        
        System.out.println(project.getId());
              
        
        
     
    }
}