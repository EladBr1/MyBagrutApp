package com.example.mybagrutapp;

import android.graphics.Bitmap;

import java.net.URL;

public class Player
{

    private String titName;
    private Bitmap profilePic;
    private String fullName;
    private String birthday;
    private int age;
    private double height;
    private String pos;
    private String crTeam;
    private int num;
    private String nltTeam;
    private int goals;
    private int asissts;
    private int ntlGoals;
    private String formerTeams;
    private String basicInfo;
    private URL wiki;
    private URL insta;

    public Player(String titName, Bitmap profilePic, String fullName, String birthday, int age,
                  double height, String pos, String crTeam, int num, String nltTeam, int goals, int asissts, int ntlGoals, String formerTeams, String basicInfo, URL wiki, URL insta)
    {
        this.titName = titName;
        this.profilePic = profilePic;
        this.fullName = fullName;
        this.birthday = birthday;
        this.age = age;
        this.height = height;
        this.pos = pos;
        this.crTeam = crTeam;
        this.num = num;
        this.nltTeam = nltTeam;
        this.goals = goals;
        this.asissts = asissts;
        this.ntlGoals = ntlGoals;
        this.formerTeams = formerTeams;
        this.basicInfo = basicInfo;
        this.wiki = wiki;
        this.insta = insta;
    }

    public Player()
    {
    }

    public String getTitName() {
        return titName;
    }

    public void setTName(String tName) {
        this.titName = tName;
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


    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }


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

    public int getAsissts() {
        return asissts;
    }

    public void setAsissts(int asists) {
        this.asissts = asists;
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

    public URL getWiki() {
        return wiki;
    }

    public void setWiki(URL wiki) {
        this.wiki = wiki;
    }

    public URL getInsta() {
        return insta;
    }

    public void setInsta(URL insta) {
        this.insta = insta;
    }
}