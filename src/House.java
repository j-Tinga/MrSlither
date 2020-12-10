import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class House {
    private TiledMap map;
    private int mapSize;    
    
    public House() throws SlickException {
        this.map = new TiledMap("assets/House.tmx");
        mapSize=0;
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
    
   public void increaseMapSize(){
       if(mapSize <= 8){
           mapSize+=2;
       }
   }
            
    public boolean checkWallCollision(Snake snake){
        return (map.getTileId(snake.getHeadPosition().getX()/32, snake.getHeadPosition().getY()/32, mapSize) != 0);
    }
}
