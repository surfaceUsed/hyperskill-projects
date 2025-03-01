package game;

class GameView {

    private static final String BORDER_TOP_BOTTOM = "---------";
    private static final char BORDER_SIDES = '|';
    private static final char NEW_LINE = '\n';

    GameView() {}

    String createGrid(char[][] tab) {

        StringBuilder sb = new StringBuilder();

        sb.append(BORDER_TOP_BOTTOM).append(NEW_LINE);

        for (char[] chars : tab) {
            sb.append(BORDER_SIDES + " ");
            for (char c : chars) {
                sb.append(c).append(" ");
            }
            sb.append(BORDER_SIDES).append(NEW_LINE);
        }

        return sb.append(BORDER_TOP_BOTTOM).append(NEW_LINE).toString();
    }
}