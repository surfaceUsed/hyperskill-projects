public class Player {

    private final String playerName;
    private boolean isBot = false;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public boolean isPlayerBot() {
        return this.isBot;
    }

    public void setIsBot(boolean isBot) {
        this.isBot = isBot;
    }

    @Override
    public String toString() {
        return this.playerName + ", isBot: " + this.isBot;
    }
}
