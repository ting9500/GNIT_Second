//格式处理
public class FormatProcess {
    
	public static String fraction(int range) { 		// mole分子、deno分母
		
		int mole, deno;
		mole = (int) (Math.random() * range);
	    deno = (int) (Math.random() * range + 1);	    
	    return Format(mole, deno);
	}
	
	
	public static String Format(int mole, int deno) {
		String fraction = null;
		int min = 0;
		min = (mole > deno)   ? deno : mole;

		if (mole == 0) {
			fraction = "0";
		} else if (mole % deno == 0) {
			fraction = String.valueOf(mole / deno);
		} else {
			for (int i = min; i >= 2; i--) {
				if (mole % i == 0 && deno % i == 0) {
					mole = mole / i;
					deno = deno / i;
				}
			}
			if (mole > deno) {
				fraction = String.valueOf(mole / deno) + "'" + String.valueOf(mole % deno) + "/" + String.valueOf(deno);
			} else {
				fraction = mole + "/" + deno;
			}
		}
		return fraction;
		
	}


}
