import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class House {
    private TiledMap map;
    private int mapSize;
    private int minWidth;
    private int minHeight;
    private int tilesWidth;
    private int tilesHeight;
    
    public House() throws SlickException {
        this.map = new TiledMap("assets/House.tmx");
        mapSize=0;
        minWidth = 224;
        tilesWidth = 14;
        minHeight = 128;
        tilesHeight = 7;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }
    
    public int getTilesWidth() {
        return tilesWidth;
    }

    public void setTilesWidth(int tilesWidth) {
        this.tilesWidth = tilesWidth;
    }

    public int getTilesHeight() {
        return tilesHeight;
    }

    public void setTilesHeight(int tilesHeight) {
        this.tilesHeight = tilesHeight;
    }

    
    public void increaseMapSize(Snake snake, HouseUpgrade houseUpgrade){   //call this method to increase Map Size
       if(mapSize <= 7 && snake.getMoney() >= houseUpgrade.getHouseCost()){
           mapSize+=2;
           tilesWidth += 2;
           tilesHeight += 2;
           minWidth -= mapSize * 32; 
           snake.deductMoney(houseUpgrade.getHouseCost());
           
           if(mapSize == 7){
               houseUpgrade.moveObject(new Position(-32,-32));
           }
       }
    }
            
    public boolean checkWallCollision(Snake snake){
        return (map.getTileId(snake.getHeadPosition().getX()/32, snake.getHeadPosition().getY()/32, mapSize) != 0);
    }
}
