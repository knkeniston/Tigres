package Tigress;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

 class Poacher extends Entity {

	private Vector velocity;

	public Poacher(final float x, final float y) {
		super(x, y);
		/*addImageWithBoundingBox(ResourceManager
				.getImage(TigressGame.Tigress_IMG_RSC));*/
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
