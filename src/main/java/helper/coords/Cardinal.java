package helper.coords;

public enum Cardinal {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    /**
     * Note that ScreenCoords units do not matter, this is only to give direction.
     *
     * @param cardinal The cardinal to convert into coords
     * @return The xy coordinates of the direction
     */
    public static ScreenCoords cardinalToDir(Cardinal cardinal) {
        if (cardinal == NORTH) {
            return new ScreenCoords(0, 1);
        } else if (cardinal == SOUTH) {
            return new ScreenCoords(0, -1);
        } else if (cardinal == EAST) {
            return new ScreenCoords(1, 0);
        } else {  // cardinal == WEST
            return new ScreenCoords(-1, 0);
        }
    }
}
