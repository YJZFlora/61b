package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
    @Test
    public void test1() {
        Clorus c = new Clorus(0.6);


        // attack
        HashMap<Direction, Occupant> buttonPlip = new HashMap<Direction, Occupant>();
        buttonPlip.put(Direction.TOP, new Clorus());
        buttonPlip.put(Direction.BOTTOM, new Clorus());
        buttonPlip.put(Direction.LEFT, new Clorus());
        buttonPlip.put(Direction.RIGHT, new Empty());
        Action actual = c.chooseAction(buttonPlip);
        Action expected = new Action(Action.ActionType.MOVE, Direction.RIGHT);

        assertEquals(expected, actual);


    }
}
