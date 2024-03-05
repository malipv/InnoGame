package ru.inno.task;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    @org.junit.jupiter.api.Test
    void getWorld() {
    }

    @org.junit.jupiter.api.Test
    void checkUndo() throws NothingToUndo {

        Unit u = new Unit("Qwerty");
        String oldName = u.getName();
        u.setName("ASDFG");
        u.undo();
        Assert.assertEquals(oldName, u.getName());
    }
}