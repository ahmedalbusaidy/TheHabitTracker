package ui;

import model.Habit;

public class Main {
    public static void main(String[] args) {
        Habit h = new Habit("loay", 20);
        System.out.println(h.getStartDate());
    }
}
