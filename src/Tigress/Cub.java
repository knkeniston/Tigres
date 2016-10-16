package Tigress;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

 class Cub extends MovingEntity {
	
	public static final String[] facingImages = 
		{
			TigressGame.CUB_IMG_RSC/*,
			TigressGame.TIGRESS_RIGHTIMG_RSC,
			TigressGame.TIGRESS_FRONTIMG_RSC,
			TigressGame.TIGRESS_BACKIMG_RSC*/
		};
	
	private boolean held;
	private boolean inNest;

	public Cub(final float x, final float y) {
		super(x, y, facingImages, LEFT);
		setVelocity(new Vector(0, 0));
		held = false; 
		inNest = false;
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
}
