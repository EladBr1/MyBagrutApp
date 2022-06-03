package com.example.mybagrutapp;

public class Player
{

    private String titName;
    private String fullName;
    private String sName;
    private String birthday;
    private int age;
    private String height;
    private String pos;
    private String crTeam;
    private int num;
    private String nltTeam;
    private int goals;
    private int asissts;
    private int ntlGoals;
    private String formerTeams;
    private String basicInfo;
    private String wiki;
    private String insta;
    private String image;

    private String key; //the key is required for set values in firebase, not for the value of the player.

    //constructor
    public Player(String titName, String fullName, String sName, String birthday, int age,
                  String height, String pos, String crTeam, int num, String nltTeam, int goals, int asissts, int ntlGoals,
                  String formerTeams, String basicInfo, String wiki, String insta, String image )
    {
        this.titName = titName;
        this.fullName = fullName;
        this.sName = sName;
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
        this.image = image;
    }

    public Player() //empty constructor
    {
    }

    //getters and setters
    public String getTitName() {
        return titName;
    }

    public void setTName(String tName) {
        this.titName = tName;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
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


    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
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

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public String getInsta() {
        return insta;
    }

    public void setInsta( String insta) {
        this.insta = insta;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}