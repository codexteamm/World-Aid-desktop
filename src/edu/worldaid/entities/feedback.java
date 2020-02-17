/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worldaid.entities;

/**
 *
 * @author HP
 */
public class feedback {

    private int id_feedback;
    private int id_user;
    private String message;

    public feedback(int id_user, String message) {
        this.id_user = id_user;
        this.message = message;
    }

    public feedback() {
    }

    public int getId_feedback() {
        return id_feedback;
    }

    public int getId_user() {
        return id_user;
    }

    public String getMessage() {
        return message;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_feedback(int id_feedback) {
        this.id_feedback = id_feedback;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id_feedback;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final feedback other = (feedback) obj;
        if (this.id_feedback != other.id_feedback) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "feedback{" + "id_feedback=" + id_feedback + ", id_user=" + id_user + ", message=" + message + '}';
    }

}
