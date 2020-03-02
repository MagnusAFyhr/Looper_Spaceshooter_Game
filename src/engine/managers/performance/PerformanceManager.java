package engine.managers.performance;


/**
 *
 * This class will display helpful stats during development of the game; keyboard input should toggle display on/off
 *  - FPS, Ticks per sec
 *  - Player position in map
 *  - Map width/height
 *  - World objects count
 *  - GameWindow objects count
 *  - etc.
 *
 * Should be displayed on the left and right edges of the screen as plain text; vertically centered
 *
 */

public class PerformanceManager {

    private long lastUpdateNanoTime = 0;
    private long lastRenderNanoTime = 0;

    /** Constructor **/
    public PerformanceManager() { }

    /** Methods **/



    /** Helper Methods **/
    public float startUpdate(long currentNanoTime) {
        if (lastUpdateNanoTime == 0) {
            lastUpdateNanoTime = currentNanoTime;
            return 0.0f;

        } else {
            float elapsed = (float)((currentNanoTime - lastUpdateNanoTime) / Math.pow(10, 9));
            return elapsed;
        }
    }
    public void stopUpdate() {
        // calculate TPS?

        // update last update time
        lastUpdateNanoTime = System.nanoTime();
    }

    public float startRender(long currentNanoTime) {
        if (lastRenderNanoTime == 0) {
            lastRenderNanoTime = currentNanoTime;
            return 0.0f;

        } else {
            float elapsed = (float)((currentNanoTime - lastRenderNanoTime) / Math.pow(10, 9));
            return elapsed;
        }
    }
    public void stopRender() {
        // calculate FPS?

        // update last render time
        lastRenderNanoTime = System.nanoTime();
    }


    public boolean canUpdate() { return true; }
    public boolean canRender() { return true; }


}
