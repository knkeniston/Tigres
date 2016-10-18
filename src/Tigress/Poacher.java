package Tigress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

 class Poacher extends MovingEntity {
	
	public static final String[] facingImages = 
		{
			TigressGame.POACHER_LEFTIMG_RSC/*,
			TigressGame.TIGRESS_RIGHTIMG_RSC,
			TigressGame.TIGRESS_FRONTIMG_RSC,
			TigressGame.TIGRESS_BACKIMG_RSC*/
		};
	
	private boolean trapped;

	public Poacher(final float x, final float y) { 
		super(x, y, facingImages, LEFT);
		setVelocity(new Vector(0, 0));
		trapped = false;
	}
	
	private void nearestTiger(ArrayList<Cub> cubs, Tigress tigress, ArrayList<Vertex> vertices) {
		Map<Vector, Entity> m = new HashMap<Vector, Entity>();
		m.put(tigress.getPosition(), tigress);
		for (Cub c : cubs)
			m.put(c.getPosition(), c);
		for (Vector pos : m.keySet()) {
			
		}
	}
	
	/**
	 * Set whether the poacher is trapped in a vine or not.
	 * @param val: true if yes, false if not
	 */
	public void setTrapped(boolean val) {
		trapped = val;
	}
	
	/**
	 * @return trapped: true if trapped in vine, false if not
	 */
	public boolean isTrapped() {
		return trapped;
	}
	
}
