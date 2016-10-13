package Tigress;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

 class Tigress extends Entity {

	private Vector velocity;

	public Tigress(final float x, final float y) {
		super(x, y);
		addImageWithBoundingBox(ResourceManager
				.getImage(TigressGame.TIGRESS_LEFTIMG_RSC));
		velocity = new Vector(0, 0);
	}

	public void setVelocity(final Vector v) {
		velocity = v;
	}

	public Vector getVelocity() {
		return velocity;
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
