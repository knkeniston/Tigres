package Tigress;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

 class Tigress extends Entity {
	 
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int FRONT = 2;
	public static final int BACK = 3;
	
	public static final String[] facingImages = 
		{
			TigressGame.TIGRESS_LEFTIMG_RSC,
			TigressGame.TIGRESS_RIGHTIMG_RSC,
			TigressGame.TIGRESS_FRONTIMG_RSC,
			TigressGame.TIGRESS_BACKIMG_RSC
		};

	private Vector velocity;
	private int facing;
	private String curImage;

	public Tigress(final float x, final float y) {
		super(x, y);
		velocity = new Vector(0, 0);
		facing = LEFT;
		curImage = TigressGame.TIGRESS_LEFTIMG_RSC;
		addImageWithBoundingBox(ResourceManager
				.getImage(curImage));
	}

	public void setVelocity(final Vector v) {
		velocity = v;
		if (v.getX() != 0 || v.getY() != 0) {
			if (v.getX() < 0) {
				setFacing(LEFT);
			} else if (v.getX() > 0) {
				setFacing(RIGHT);
			} else if (v.getY() < 0) {
				setFacing(BACK);
			} else {
				setFacing(FRONT);
			}
		}
	}

	public Vector getVelocity() {
		return velocity;
	}
	
	public void setFacing(final int direction) {
		if (facing != direction) {
			removeImage(ResourceManager.getImage(curImage));
			addImageWithBoundingBox(ResourceManager
					.getImage(facingImages[direction]));
			curImage = facingImages[direction];
		}
		facing = direction;
	}

	public int getFacing() {
		return facing;
	}

	/**
	 * Update the Tigress
	 * 
	 * @param delta
	 *            the number of milliseconds since the last update
	 */
	public void update(final int delta) {
		translate(velocity.scale(delta));
	}
}
