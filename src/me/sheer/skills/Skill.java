package me.sheer.skills;

import me.sheer.LoreListener.LoreListener;

public class Skill {
	private int exp;
	private int level;
	private int maxLevel;
	private String name;

	public Skill(String n) {
		name = n;
		exp = 0;
		level = 1;
		maxLevel = 100;
		LoreListener.skills.add(this);
	}
	
	public static Skill getSkill(String n) {
		for (Skill s : LoreListener.skills) {
			if (s.getName().equals(n)) {
				return s;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public void addExp(int n) {
		exp = Math.max(0, exp + n);
		if (level != getLevelAtExp(exp)) {
			level = getLevelAtExp(exp);
		}
	}
	
	public int getExp() {
		return exp;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getMaxLevel() {
		return maxLevel;
	}
	
	public static int getExpOfLevel(int n) {
		return (int)(1040.4308 * Math.pow(1.454, (n-2)/4.0))-947;
	}
	
	public static int getLevelAtExp(int n) {
		int level = (int)(4 * Math.log((n + 947)/1040.4308)/Math.log(1.454)+2);
		if (Skill.getExpOfLevel(level + 1) - n <= 0) {
			return level + 1;
		} else {
			return level;
		}
	}
}
