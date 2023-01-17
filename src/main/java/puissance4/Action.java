package puissance4;

import java.util.Objects;

public class Action {
    private int column;

    public Action(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public boolean equals(Action a) {
        return this.column == a.getColumn();
    }




}
