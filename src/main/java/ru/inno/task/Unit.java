package ru.inno.task;

import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

interface Loadable
{
    void load();
}

class NothingToUndo extends Exception{}

enum ResTypes {GOLD, WATER, FOOD, ENERGY}
interface Command{
    public void perform();
}
public class Unit {

    public Loadable Save() {return new Snapshot();}

    private String world;

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        String oldWorld= this.getWorld();
        this.commands.push(()->{this.world=oldWorld;});
        this.world = world;
    }

    private class Snapshot implements Loadable
    {
        private String name;
        private Integer health;
        private HashMap<ResTypes, Integer> resources;

        public Snapshot ()
        {
            this.health = Unit.this.health;
            this.name = Unit.this.name;
            this.resources = new HashMap<>(Unit.this.resources);

        }
        @Override
        public void load() {
            Unit.this.name = this.name;
            Unit.this.health = this.health;
            Unit.this.resources = new HashMap<>(this.resources);
        }
    }

    private Deque<Command> commands = new ArrayDeque<>();

    private Unit(){};

    public  Unit undo() throws NothingToUndo {
        if (commands.isEmpty()) throw new NothingToUndo();
        commands.pop().perform();
        return this;
    }

    public Unit(String name)
    {
        this.setName(name);
        this.resources = new HashMap<>();

    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();
        String oldName = this.name;
        this.commands.push(()->{this.name = oldName;});
        this.name = name;
    }

    private Integer health =100;


    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        if (health<0 || health>100) throw new IllegalArgumentException();
        Integer oldHealth = this.health;
        this.commands.push(()->{this.health = oldHealth;});
        this.health = health;
    }

    public void doVoice()
    {
        System.out.println("\nI'm " + this.name);
    }

    private HashMap<ResTypes, Integer> resources;

    public HashMap<ResTypes, Integer> getResources() {
        return new HashMap<ResTypes, Integer>(this.resources);
    }

    public void setResources(ResTypes restype, Integer val) {
        if (val<0) throw new IllegalArgumentException();
        if (resources.containsKey(restype)) //если мы изменили сущ. значение
        {
            this.commands.push(()->{this.resources.put(restype, val);});
        }
        else //если мы добавили новое значение
        {
            this.commands.push(()->{this.resources.remove(restype);});
        }
        this.resources.put(restype, val);
    }

    public void printResources()
    {
        this.resources.values().stream().forEach(System.out::println);
    }
}