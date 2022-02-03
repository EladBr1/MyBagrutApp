package com.example.mybagrutapp;

import android.graphics.Bitmap;
import android.widget.Button;

public class Player
{

    private String tName;
    private Bitmap profilePic;
    private String fullName;
    private String birthday;
    private double height;
    private String pos;
    private String crTeam;
    private int num;
    private String nltTeam;
    private int goals;
    private int asists;
    private int ntlGoals;
    private String formerTeams;
    private String basicInfo;
    private Button wiki;
    private Button insta;

    public Player(String tName, Bitmap profilePic, String fullName, String birthday, double height, String pos, String crTeam, int num, String nltTeam, int goals, int asists, int ntlGoals, String formerTeams, String basicInfo, Button wiki, Button insta)
    {
        this.tName = tName;
        this.profilePic = profilePic;
        this.fullName = fullName;
        this.birthday = birthday;
        this.height = height;
        this.pos = pos;
        this.crTeam = crTeam;
        this.num = num;
        this.nltTeam = nltTeam;
        this.goals = goals;
        this.asists = asists;
        this.ntlGoals = ntlGoals;
        this.formerTeams = formerTeams;
        this.basicInfo = basicInfo;
        this.wiki = wiki;
        this.insta = insta;
    }

    public Player()
    {
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Bitmap getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getCrTeam() {
        return crTeam;
    }

    public void setCrTeam(String crTeam) {
        this.crTeam = crTeam;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNltTeam() {
        return nltTeam;
    }

    public void setNltTeam(String nltTeam) {
        this.nltTeam = nltTeam;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAsists() {
        return asists;
    }

    public void setAsists(int asists) {
        this.asists = asists;
    }

    public int getNtlGoals() {
        return ntlGoals;
    }

    public void setNtlGoals(int ntlGoals) {
        this.ntlGoals = ntlGoals;
    }

    public String getFormerTeams() {
        return formerTeams;
    }

    public void setFormerTeams(String formerTeams) {
        this.formerTeams = formerTeams;
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public Button getWiki() {
        return wiki;
    }

    public void setWiki(Button wiki) {
        this.wiki = wiki;
    }

    public Button getInsta() {
        return insta;
    }

    public void setInsta(Button insta) {
        this.insta = insta;
    }
}