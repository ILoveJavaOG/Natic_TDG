package de.ilovejava.tdgapi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import de.ilovejava.hider.API_EntityHider;
import de.ilovejava.hider.API_EntityHider.Policy;
import de.ilovejava.tdgsavestock.Util_SaveStock;
import de.ilovejava.utils.Util_Utils;

public class API_TDG {
	public API_TDG() {}
	private API_EntityHider hides = new API_EntityHider(Util_Utils.getInstance(), Policy.BLACKLIST);
	
public void addIcon(Player p, Location loc, String name, ItemStack item, int positionY) {
		
		Vector playerDirection = p.getLocation().getDirection();
        Vector direction = playerDirection.normalize();
        direction.multiply(-2);
        loc.setDirection(direction);
        float yaw = (float)Math.toDegrees(Math.atan2(p.getLocation().getZ() - loc.getZ(), p.getLocation().getX() - loc.getX())) - 90;
        float pitch = (float)Math.toDegrees(Math.atan2(p.getLocation().getZ() - loc.getZ(), p.getLocation().getX() - loc.getX())) - 90;
        loc.setYaw(yaw);
        loc.setPitch(pitch);
        if (positionY == 2) {
        	loc.add(0.0, 1.5, 0.0);
        }
        if (positionY == 3) {
        	loc.add(0.0, 3.0, 0.0);
        }
		ArmorStand a = (ArmorStand) p.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		a.setVisible(false);
		a.setCustomName(name.replace("&", "§"));
		a.setCustomNameVisible(true);
		a.setHelmet(item);
		a.setArms(true);
		
		Util_SaveStock.getShowStands().get(p).add(a);
		Util_SaveStock.getStand().add(a);
		Location locb = a.getLocation();
		for (Player all : Bukkit.getOnlinePlayers()) {
			if(all != p) {
				hides.hideEntity(all, a);
			}
		}
	    
	    new BukkitRunnable() {
	        	
	        @Override
	        public void run() {
	        	if (a.isValid()) {
	        		a.teleport(locb);
	        		a.setFireTicks(0);
	                if (getTargetEntity(p) == a) {
	                	a.setGravity(true);
		                a.setVelocity(p.getLocation().toVector().subtract(a.getLocation().toVector()).multiply(0.1));
		                a.teleport(locb);
	                } else {
	                    a.setGravity(false);
	                }
	                if(p.getLocation().distanceSquared(Util_SaveStock.getLastLoc().get(p)) >= 120){
		        		this.cancel();
		        		deleteTDG(p);
		        	}
	                if(!p.isOnline()) {
	                	this.cancel();
	                	a.remove();
	                }
	                if(!Util_SaveStock.getHides().contains(p)) {
	                	this.cancel();
	                	a.remove();
	                }
	        	}
	        }
	    }.runTaskTimer(Util_Utils.getInstance(), 0, 0);
	}

	public void deleteTDG(Player p) {
		for(ArmorStand ar : Util_SaveStock.getShowStands().get(p)) {
			ar.remove();
		}
		
		Util_SaveStock.getHides().remove(p);
		Util_SaveStock.getLastLoc().remove(p);
		Util_SaveStock.getShowStands().remove(p);
		
	}

	public static Location setPosition(Location loc, int positionX, int positionY, double x1, double x2, double x3, double x4, double x5 , double x6, double x7, double x8, double x9) {
		
		if (positionX == 1) {
			return getLocationBF(getLeftSide(loc, x1), -2.0);
		}
		if (positionX == 2) {
			return getLocationBF(getLeftSide(loc, x2), -1.5);
		}
		if (positionX == 3) {
			return getLocationBF(getLeftSide(loc, x3), -1.0);
		}
		if (positionX == 4) {
			return getLocationBF(getLeftSide(loc, x4), -0.5);
		}
		if (positionX == 5) {
			return getLeftSide(loc, 0);
		}
		if (positionX == 6) {
			return getLocationBF(getRightSide(loc, x6), -0.5);
		}
		if (positionX == 7) {
			return getLocationBF(getRightSide(loc, x7), -1.0);
		}
		if (positionX == 8) {
			return getLocationBF(getRightSide(loc, x8), -1.5);
		}
		if (positionX == 9) {
			return getLocationBF(getRightSide(loc, x9), -2.0);
		}
		return null;
	}
	
	public static Location getRightSide(Location location, double distance) {
        float angle = location.getYaw() / 60;
        return location.clone().subtract(new Vector(Math.cos(angle), 0, Math.sin(angle)).normalize().multiply(distance));
    }
 
    public static Location getLeftSide(Location location, double distance) {
        float angle = location.getYaw() / 60;
        return location.clone().add(new Vector(Math.cos(angle), 0, Math.sin(angle)).normalize().multiply(distance));
    }
	
	public static Location getLocationBF(Location loc, Double dist) {
		Vector vec = loc.getDirection().normalize().multiply(dist);
		return loc.add(vec);
	}
	
	
	 public static Player getTargetPlayer(final Player player) {
	        return getTarget(player, player.getWorld().getPlayers());
	    }
	 
	    public static Entity getTargetEntity(final Entity entity) {
	        return getTarget(entity, entity.getWorld().getEntities());
	    }
	 
	    public static <T extends Entity> T getTarget(final Entity entity,
	            final Iterable<T> entities) {
	        if (entity == null)
	            return null;
	        T target = null;
	        final double threshold = 1;
	        for (final T other : entities) {
	            final Vector n = other.getLocation().toVector()
	                    .subtract(entity.getLocation().toVector());
	            if (entity.getLocation().getDirection().normalize().crossProduct(n)
	                    .lengthSquared() < threshold
	                    && n.normalize().dot(
	                            entity.getLocation().getDirection().normalize()) >= 0) {
	                if (target == null
	                        || target.getLocation().distanceSquared(
	                                entity.getLocation()) > other.getLocation()
	                                .distanceSquared(entity.getLocation()))
	                    target = other;
	            }
	        }
	        return target;
	    }
}
