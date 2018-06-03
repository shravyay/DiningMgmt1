/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncg.dining.mgmt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author komalkubsad
 */
@Entity
@Table (name = "customize")
public class Customize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customID;
    private long studentId;
    private String studentName;
    private String item;
    private String dayOfSpecial;

    public long getCustomID() {
        return customID;
    }

    public void setCustomID(long customID) {
        this.customID = customID;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDayOfSpecial() {
        return dayOfSpecial;
    }

    public void setDayOfSpecial(String dayOfSpecial) {
        this.dayOfSpecial = dayOfSpecial;
    }
}
