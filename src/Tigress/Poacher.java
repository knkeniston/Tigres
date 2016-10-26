package Tigress;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import jig.Vector;

 class Poacher extends MovingEntity {
	
	public static final String[] facingImages = 
		{
			TigressGame.POACHER_LEFTIMG_RSC,
			TigressGame.POACHER_LEFTIMG_RSC,
			TigressGame.POACHER_LEFTIMG_RSC,
			TigressGame.POACHER_LEFTIMG_RSC
		};
	
	private boolean trapped;
	private boolean reset;

	public Poacher(final float x, final float y, Vertex pos) { 
		super(x, y, facingImages, LEFT);
		setVelocity(new Vector(0, 0));
		trapped = false;
		setvPos(pos);
		setFirstPath(true);
		reset = false;
	}
	
	public void setMoving(TigressGame bg) {
		if (hasPassed() || getFirstPath() || reset) {
			reset = false;
			Map<String, Integer> distances = getDistances(bg);
			int minDist = Integer.MAX_VALUE;
			Vertex closest = null;
			for (Cub c : bg.cubs) {
				Vertex pos = c.getvPos();
				if (distances.get(pos.toString()) < minDist) {
					minDist = distances.get(pos.toString());
					closest = pos;
				}
			}
			if (distances.get(bg.tigress.getvPos().toString()) < minDist) {
				minDist = distances.get(bg.tigress.getvPos().toString());
				closest = bg.tigress.getvPos();
			}
			
			LinkedList<Vertex> path;
			if (!getFirstPath()) {
				path = search(getNextPos(), closest);
				setvPos(getNextPos());
			} else {
				path = search(getvPos(), closest);
				setFirstPath(false);
			}
			setNextPos(path.get(0));
			setDirAndVel();
			System.out.println("path: " + path);
			System.out.println("currentpos: " + getvPos().toString());
			System.out.println("nextpos: " + getNextPos().toString());
			System.out.println("closest: " + closest);
			System.out.println("direction: " + getDirection());
			System.out.println("--------------------------------------------");
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
	
	private Map<String, Integer> getDistances(TigressGame bg) {
		Queue<Vertex> frontier = new LinkedList<Vertex>();
		frontier.add(bg.poacher.getvPos());
		Map<String, Integer> distance = new HashMap<String, Integer>();
		distance.put(bg.poacher.getvPos().toString(), 0);
		
		while (!frontier.isEmpty()) {
			Vertex cur = frontier.poll();
			for (Vertex n : cur.getNeighbors()) {
				if (!distance.containsKey(n.toString())) {
					frontier.add(n);
					distance.put(n.toString(), 1 + distance.get(cur.toString()));
				}
			}
		}
		return distance;
	}
	
	public LinkedList<Vertex> search(Vertex start, Vertex goal) {
		LinkedList<Vertex> closedList = new LinkedList<Vertex>();
		LinkedList<Vertex> openList = new LinkedList<Vertex>();
		Map<String, Vertex> parents = new HashMap<String, Vertex>();
		openList.add(start);
		parents.put(start.toString(), null);
 
		while (!openList.isEmpty()) {
			Vertex v = openList.removeFirst();
			if (v.toString().equals(goal.toString())) {
				return constructPath(goal, parents);
			} else {
				closedList.add(v);
				  
				for (Vertex neighbor : v.getNeighbors()) {
					if (!closedList.contains(neighbor) && !openList.contains(neighbor)) {
						parents.put(neighbor.toString(), v);
						openList.add(neighbor);
					}
				}
			}
		}		    
		  
		return null;
	}
	
	protected LinkedList<Vertex> constructPath(Vertex v, Map<String, Vertex> parents) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		while (parents.get(v.toString()) != null) {
			path.addFirst(v);
			v = parents.get(v.toString());
		}
		return path;
	}
	
	protected void setReset() {
		reset = true;
		setvPos(new Vertex(50, 50));
	}
	
}
