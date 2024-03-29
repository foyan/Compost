package ch.hszt.kfh.compost;

public class Tools {
	
	public static AdditionResult add(boolean[] a, boolean[] b) {
		boolean[] r = new boolean[a.length];
		boolean carry = false;
		for (int i = r.length - 1; i >= 0; --i) {
			r[i] = a[i] ^ b[i] ^ carry;
			carry = (a[i] && b[i]) || (a[i] && carry) || (b[i] && carry);
		}
		return new AdditionResult(r, carry && (a[0] == b[0]));
	}
	
	public static int fromComplement(boolean[] data) {
				
		boolean negative = data[0];
		if (negative) {
			// ... invert ...
			for (int i = 0; i < data.length; i++) {
				data[i] = !data[i];
			}
			for (int i = data.length - 1; i >= 0; --i) {
				data[i] = !data[i];
				if (data[i]) {
					break;
				}
			}
		}
		// ... Potenzieren...
		return fromBinary(data) * (negative ? -1 : 1);
	}
	
	public static int fromComplement(boolean[] msb, boolean[] lsb) {
		boolean[] b = new boolean[msb.length + lsb.length];
		for (int i = 0; i < msb.length; i++) {
			b[i] = msb[i];
		}
		for (int i = 0; i < lsb.length; i++) {
			b[i + msb.length] = lsb[i];
		}
		return fromComplement(b);
	}
	
	public static int fromComplement(boolean[] b1, boolean[] b2, boolean[] b3, boolean[] b4) {
		boolean[] b = new boolean[b1.length + b2.length + b3.length + b4.length];
		for (int i = 0; i < b1.length; i++) {
			b[i] = b1[i];
		}
		for (int i = 0; i < b2.length; i++) {
			b[i + b1.length] = b2[i];
		}
		for (int  i = 0; i < b3.length; i++) {
			b[i + b1.length + b2.length] = b3[i];
		}
		for (int i = 0; i < b4.length; i++) {
			b[i + b1.length + b2.length + b3.length] = b4[i];
		}
		return fromComplement(b);
	}

	public static int fromBinary(boolean[] data) {
		int n = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[data.length - 1 - i]) {
				n += Math.pow(2, i);
			}
		}
		return n;
	}
	
	public static boolean[] toBinary(int n, int size) {
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
		return b;
	}
	
	public static boolean[] toComplement(int n, int size) {
		boolean negative = n < 0;
		if (negative) {
			n = -n;
		}
		boolean[] b = toBinary(n, size);
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
	
	public static String fromBooleanArray(boolean[] b, boolean makeByteGap) {
		String s = "";
		for (int i = 0; i < b.length; i++) {
			s += b[i] ? "1" : "0";
			if (makeByteGap && (i+1) % 8 == 0 && i != b.length - 1) {
				s += " ";
			}
		}
		return s;
	}

	private static final char[] hexChars = new char[] { '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F' };

	public static String toHex(boolean[] b, boolean makeByteGaps) {
		String s = "";
		int n = 0;
		for (int i = 0; i < b.length; i++) {
			int e = 3 - (i % 4);
			if (b[i]) {
				n += Math.pow(2, e);
			}
			if (i % 4 == 3) {
				s += hexChars[n];
				n = 0;
			}
			if (makeByteGaps && i % 8 == 0 && !"".equals(s)) {
				s += " ";
			}
		}
		return s;
	}

}
