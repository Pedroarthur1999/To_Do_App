/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

public class Tasks {

    private int id;
    private int id_project;
    private String name;
    private String description;
    private Date completed;
    private String notes;
    private Date created_at;
    private Date update_at;
    private Boolean task_completed;

    public Tasks(int id, String name, String description, Date completed, String notes, Date created_at, Date update_at, Boolean task_completed) {            
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.notes = notes;
        this.created_at = created_at;
        this.update_at = update_at;
        this.task_completed = this.task_completed;
    }

    public Boolean getTask_completed() {
        return task_completed;
    }

    public void setTask_completed(Boolean task_completed) {
        this.task_completed = task_completed;
    }

    public Tasks() {
        this.created_at = new Date();
        this.update_at = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    @Override
    public String toString() {
        return "Tasks{" + "id=" + id + ", name=" + name + ", description=" + description + ", completed=" + completed + ", notes=" + notes + ", created_at=" + created_at + ", update_at=" + update_at + '}';
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

}
