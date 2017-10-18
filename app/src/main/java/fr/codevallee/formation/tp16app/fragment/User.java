package fr.codevallee.formation.tp16app.fragment;

class User {
    protected static final String ARG_POSITION = "position";

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return this.name + ", " + String.valueOf(age);
    }
}
