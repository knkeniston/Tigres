package Tigress;

import java.util.Random;
import jig.ResourceManager;
import jig.Vector;

 class Cub extends MovingEntity {
	
	public static final String[] facingImages = 
		{
			TigressGame.CUB_IMG_RSC,
			TigressGame.CUB_IMG_RSC,
			TigressGame.CUB_IMG_RSC,
			TigressGame.CUB_IMG_RSC
		};
	
	private boolean held;
	private boolean inNest;
	private int waitTime;
	Random rand = new Random();

	public Cub(final float x, final float y) {
		super(x, y, facingImages, LEFT);
		setVelocity(new Vector(0, 0));
		held = false; 
		inNest = false;
		setFirstPath(true);
		waitTime = rand.nextInt(200);
	}
	
	/**
	 * Sets whether the cub is being held or not.
	 * @param val: value to set held as
	 */
	public void setHeld(boolean val) {
		if (!val) {
			setFacing(LEFT);
		} else if (getCurImage() != null) {
			removeImage(ResourceManager.getImage(getCurImage()));
			setCurImage(null);
			setFacing(-1);
		}
		held = val;
	}
	
	/**
	 * Sets whether the cub is in the nest or not.
	 * @param val: true if in nest, false if not
	 */
	public void setNest(boolean val) {
		inNest = val;
	}
	
	public void setMoving(TigressGame bg) {
		if ((hasPassed() || getFirstPath()) && waitTime <= 0) {
			if (getFirstPath())
				setFirstPath(false);
			//nextPos = path.get(0);
			Vertex v = bg.vPos.get(getvPos().toString());
			int r = rand.nextInt(v.neighbors.size());
			int c = 0;
			for (Vertex n : v.getNeighbors()) {
				if (c == r) setNextPos(n);
				c++;
			}
			setDirAndVel();
			waitTime = rand.nextInt(200);
		}
		if (hasPassed()) {
			setVelocity(new Vector(0f, 0f));
			setvPos(getNextPos());
		}
		if (waitTime > 0)
			waitTime--;
	}	
}
