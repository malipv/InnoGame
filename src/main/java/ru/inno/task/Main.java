package ru.inno.task;

//import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws NothingToUndo {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!\n");
        Unit u1 = new Unit("ork1");
        u1.setHealth(90);
        u1.doVoice();
        //u1.getResources().put(ResTypes.FOOD, -100); !!!
        u1.setResources(ResTypes.GOLD, 20);
        u1.setResources(ResTypes.ENERGY, 67);
        u1.printResources();

        u1.undo().undo();
        u1.printResources();
        u1.setName("qqqwwww");
        u1.doVoice();
        u1.undo();
        u1.doVoice();


        System.out.println("\n\n========================");
        u1.setResources(ResTypes.GOLD, 10);
        u1.setResources(ResTypes.ENERGY, 20);
        u1.printResources();
        u1.doVoice();
        Loadable qs1 = u1.Save();
        u1.setName("PseudoOrk1");
        u1.setResources(ResTypes.ENERGY, 66);
        u1.setResources(ResTypes.GOLD, 66);
        System.out.println("\n---------------");
        u1.printResources();
        u1.doVoice();
        qs1.load(); //восстанавливаемся
        System.out.println("\n---------------");
        u1.printResources();
        u1.doVoice();

        System.out.println("***************************");
        Unit u2 = new Unit("rt");
        u2.setWorld("Inner");
        System.out.println(u2.getWorld());
        u2.setWorld("Outer");
        u2.undo();
        System.out.println(u2.getWorld());

    }
}