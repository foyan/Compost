package ch.hszt.kfh.compost;

public class Tools {
	
	public static boolean[] add(boolean[] a, boolean[] b) {
		return null;
	}
	
	public static boolean[] toComplement(int n, int size) {
		boolean negative = n < 0;
		if (negative) {
			n = -n;
		}
		boolean[] b = new boolean[size];
		int i = 0;
		while (true) {
			int a = n / 2;
			int r = n - a * 2;
			n = a;
			b[size - (++i)] = r == 1;
			if (a == 0) {
				break;
			}
		}
		if (negative) {
			for (int j = 0; j < size; j++) {
				b[j] = !b[j];
			}
			for (int j = size - 1; j >= 0; --j) {
				b[j] = !b[j];
				if (b[j]) {
					break;
				}
			}
		}
		return b;
	}
	
	public static boolean[] toBooleanArray(String s) {
		boolean[] b = new boolean[s.length()];
		for (int i = 0; i < s.length(); i++) {
			b[i] = s.charAt(i) == '1';
		}
		return b;
	}

}
