package creatures;

import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus() {
        this(5);
    }

    public Color color() {
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    public void move() {
        energy = energy - 0.03;
        if (energy < 0) {
            energy = 0;
        }
    }

    public void stay() {
        energy = energy - 0.01;
        if (energy < 0) {
            energy = 0;
        }
    }

    public Clorus replicate() {
        double half = this.energy / (double) 2;
        this.energy = half;
        Clorus offSpring = new Clorus(half);
        return offSpring;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        boolean anyPlips = false;

        for (Map.Entry<huglife.Direction, huglife.Occupant> entry: neighbors.entrySet()) {
            if (entry.getValue().name().equals("empty")) {
                emptyNeighbors.addFirst(entry.getKey());
            }
            if (entry.getValue().name().equals("plip")) {
                plipNeighbors.addFirst(entry.getKey());
            }
        }
        // Rule 1
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        // r2
        if (plipNeighbors.size() != 0) {
            Direction dir = HugLifeUtils.randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, dir);
        }

        //r3
        if (energy >= 1) {
            Direction dir = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, dir);
        }

        Direction movDir = HugLifeUtils.randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, movDir);
    }
}
