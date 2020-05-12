package safari.safariMap;

public interface IPosition {
    int getX();

    int getY();

    void setPosition(int x, int y);

    boolean equals(int x, int y);

    boolean equals(Object o);
}
