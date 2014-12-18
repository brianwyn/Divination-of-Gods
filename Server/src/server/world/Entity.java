package server.world;

public abstract class Entity {

	/**
	 * Represents the type
	 */
	public enum Type {

		/**
		 * An npc being
		 */
		NPC,
		/**
		 * An human being
		 */
		HUMAN;
	}

	public static boolean inMulti(int absX, int absY) {
		if ((absX >= 3136 && absX <= 3327 && absY >= 3519 && absY <= 3607)
				|| (absX >= 3460 && absX <= 3505 && absY >= 9480 && absY <= 9520)
				|| (absX >= 2705 && absX <= 2735 && absY >= 9800 && absY <= 9833)
				|| absX > 3343
				&& absX < 3384
				&& absY > 9619
				&& absY < 9660
				|| (absX >= 3210 && absX <= 3339 && absY >= 9333 && absY <= 9424)
				|| (absX >= 2607 && absX <= 2644 && absY >= 3296 && absY <= 3332)
				|| (absX >= 3359 && absX <= 3367 && absY >= 9636 && absY <= 9644)
				|| (absX >= 2949 && absX <= 3001 && absY >= 3370 && absY <= 3392)
				|| (absX >= 3250 && absX <= 3342 && absY >= 9800 && absY <= 9870)
				|| (absX >= 3190 && absX <= 3327 && absY >= 3648 && absY <= 3839)
				|| (absX >= 3200 && absX <= 3390 && absY >= 3840 && absY <= 3967)
				|| (absX >= 2992 && absX <= 3007 && absY >= 3912 && absY <= 3967)
				|| (absX >= 2946 && absX <= 2959 && absY >= 3816 && absY <= 3831)
				|| (absX >= 3008 && absX <= 3199 && absY >= 3856 && absY <= 3903)
				|| (absX >= 3008 && absX <= 3071 && absY >= 3600 && absY <= 3711)
				|| (absX >= 3072 && absX <= 3327 && absY >= 3608 && absY <= 3647)
				|| (absX >= 2624 && absX <= 2690 && absY >= 2550 && absY <= 2619)
				|| (absX >= 2371 && absX <= 2422 && absY >= 5062 && absY <= 5117)
				|| (absX >= 2896 && absX <= 2927 && absY >= 3595 && absY <= 3630)
				|| (absX >= 2820 && absX <= 2955 && absY >= 5250 && absY <= 5370)
				|| (absX >= 2892 && absX <= 2932 && absY >= 4435 && absY <= 4464)
				|| (absX >= 2453 && absX <= 2476 && absY >= 4768 && absY <= 4790)
				|| (absX >= 2947 && absX <= 3002 && absY >= 9475 && absY <= 9528)
				|| (absX >= 2312 && absX <= 2332 && absY >= 3783 && absY <= 3801)
				|| (absX >= 2256 && absX <= 2287 && absY >= 4680 && absY <= 4711)
				|| (absX >= 2660 && absX <= 2690 && absY >= 3690 && absY <= 3735)
				|| (absX >= 2980 && absX <= 2993 && absY >= 9629 && absY <= 9644)
				|| (absX >= 2365 && absX <= 2434 && absY >= 3068 && absY <= 3138)
				|| (absX >= 2382 && absX <= 2396 && absY >= 9874 && absY <= 9907)) {
			return true;
		}
		return false;
	}

	public static boolean inMulti(int absX, int absY, Type t) {
		// summoning fix for multy
		if (t.equals(Type.NPC)) {
			if (absX >= 0 && absX <= 9999 && absY >= 0 && absY <= 9999) {
				return true;
			}
			return false;
		} else if (t.equals(Type.HUMAN)) {
			if ((absX >= 3136 && absX <= 3327 && absY >= 3519 && absY <= 3607)
					|| (absX >= 3460 && absX <= 3505 && absY >= 9480 && absY <= 9520)
					|| (absX >= 2705 && absX <= 2735 && absY >= 9800 && absY <= 9833)
					|| absX > 3343
					&& absX < 3384
					&& absY > 9619
					&& absY < 9660
					|| (absX >= 3210 && absX <= 3339 && absY >= 9333 && absY <= 9424)
					|| (absX >= 2607 && absX <= 2644 && absY >= 3296 && absY <= 3332)
					|| (absX >= 3359 && absX <= 3367 && absY >= 9636 && absY <= 9644)
					|| (absX >= 2949 && absX <= 3001 && absY >= 3370 && absY <= 3392)
					|| (absX >= 3250 && absX <= 3342 && absY >= 9800 && absY <= 9870)
					|| (absX >= 3190 && absX <= 3327 && absY >= 3648 && absY <= 3839)
					|| (absX >= 3200 && absX <= 3390 && absY >= 3840 && absY <= 3967)
					|| (absX >= 2992 && absX <= 3007 && absY >= 3912 && absY <= 3967)
					|| (absX >= 2946 && absX <= 2959 && absY >= 3816 && absY <= 3831)
					|| (absX >= 3008 && absX <= 3199 && absY >= 3856 && absY <= 3903)
					|| (absX >= 3008 && absX <= 3071 && absY >= 3600 && absY <= 3711)
					|| (absX >= 3072 && absX <= 3327 && absY >= 3608 && absY <= 3647)
					|| (absX >= 2624 && absX <= 2690 && absY >= 2550 && absY <= 2619)
					|| (absX >= 2371 && absX <= 2422 && absY >= 5062 && absY <= 5117)
					|| (absX >= 2896 && absX <= 2927 && absY >= 3595 && absY <= 3630)
					|| (absX >= 2820 && absX <= 2955 && absY >= 5250 && absY <= 5370)
					|| (absX >= 2892 && absX <= 2932 && absY >= 4435 && absY <= 4464)
					|| (absX >= 2453 && absX <= 2476 && absY >= 4768 && absY <= 4790)
					|| (absX >= 2947 && absX <= 3002 && absY >= 9475 && absY <= 9528)
					|| (absX >= 2312 && absX <= 2332 && absY >= 3783 && absY <= 3801)
					|| (absX >= 2256 && absX <= 2287 && absY >= 4680 && absY <= 4711)
					|| (absX >= 2660 && absX <= 2690 && absY >= 3690 && absY <= 3735)
					|| (absX >= 2980 && absX <= 2993 && absY >= 9629 && absY <= 9644)
					|| (absX >= 2365 && absX <= 2434 && absY >= 3068 && absY <= 3138)
					|| (absX >= 2382 && absX <= 2396 && absY >= 9874 && absY <= 9907)) {
				return true;
			}
		}
		return false;
	}
}