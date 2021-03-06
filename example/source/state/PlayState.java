package example.source.state;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import example.source.background.Background;
import example.source.background.Ground;
import example.source.hero.Hero;
import source.Camera.CameraStyles;
import source.State.AbstractState;
import source.State.StateManager;

public class PlayState extends AbstractState {

    private final Hero hero;
    private final Background background;
    private final Ground ground;

    public PlayState() {
        hero = new Hero(new Vector2(30f, 80f));
        background = new Background(10);
        ground = new Ground();
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        hero.moveRight();
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        hero.setMoving(false);
        return true;
    }

    @Override
    public void update(float delta) {
        hero.update();
        /* Style 1: Center on screen. */
//        CameraStyles.centerOnScreen(StateManager.getInstance().getCamera(), WorldDimensions.WORLD_WIDTH, WorldDimensions.WORLD_HEIGHT);
        /* Style 2: Lock on target, ridigly. */
//        CameraStyles.lockOnTarget(StateManager.getInstance().getCamera(), hero.getPosition());
        /* Style 3: Smoothly lerp to target with a lerp amount = 0.075f. */
        CameraStyles.lerpToTarget(StateManager.getInstance().getCamera(), hero.getPosition(), 0.075f);
    }

    @Override
    public void render(Batch batch) {
        batch.setProjectionMatrix(StateManager.getInstance().getCamera().combined);
        batch.begin();
        background.render(batch);
        ground.render(batch);
        hero.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        /* Dispose stuff here... */
    }
}
