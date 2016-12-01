
public class Rational {
	private final int numerator;
	private final int denominator;
	
	public Rational(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public static void main(String[] args) {
		Rational a = new Rational(1, 2);
		Rational b = new Rational(1, 2);		
		System.out.println(a.plus(b));
	}
	
	/**
	 * 和
	 * @param b
	 * @return
	 */
	public Rational plus(Rational b){
		Rational[] operand = rfcd(this, b);	
		int newNumerator = operand[0].numerator + operand[1].numerator;
		int newDenominator = operand[0].denominator;	
		return reduction(new Rational( newNumerator , newDenominator));
	}
	
	/**
	 * 差
	 * @param b
	 * @return
	 */
	public Rational minus(Rational b){
		Rational[] operand = rfcd(this, b);	
		int newNumerator = operand[0].numerator - operand[1].numerator;
		int newDenominator = operand[0].denominator;	
		return reduction(new Rational( newNumerator , newDenominator));
	}
	
	/**
	 * 积
	 * @param b
	 * @return
	 */
	public Rational times(Rational b){
		Rational[] operand = rfcd(this, b);	
		int newNumerator = operand[0].numerator * operand[1].numerator;
		int newDenominator = operand[0].denominator * operand[0].denominator;
		return reduction(new Rational( newNumerator , newDenominator));
	}
	
	/**
	 * 商
	 * @param b
	 * @return
	 */
	public Rational divides(Rational b){
		Rational[] operand = rfcd(this, b);		
		int newNumerator = operand[0].numerator;
		int newDenominator = operand[1].numerator;
		return reduction(new Rational( newNumerator , newDenominator));
	}
	
	public boolean equals(Rational b){
		if(this == b) return true;
		Rational[] operand = rfcd(this, b);
		
		return operand[0].numerator == operand[1].numerator;
	}
	
	public String toString(){
		return "" + this.numerator + "/" + this.denominator;
	}
	
	/**
	 * 最大公约数（greatest common divisor）
	 * @param p
	 * @param q
	 * @return
	 */
	private int gcd(int p, int q){
		if (q == 0) return p;
		return gcd(q, p%q);
	}
	
	/**
	 * 最小公倍数（lowest common multiple）
	 * @param a
	 * @param b
	 * @return
	 */
	private int lcm(int a, int b){
		return (a * b) / gcd(a, b);
	}
	
	/**
	 * 通分（reduction of fractions to a common denominator）
	 * @param a
	 * @param b
	 * @return
	 */
	private Rational[] rfcd(Rational a, Rational b){
		int lcm = lcm(a.denominator, b.denominator);
		int aNewNumerator = a.numerator * (lcm/a.denominator);
		int bNewNumerator = b.numerator * (lcm/b.denominator);		
		return new Rational[]{new Rational(aNewNumerator, lcm), new Rational(bNewNumerator, lcm)};
	}
	
	/**
	 * 化简（reduction）
	 * @param a
	 * @return
	 */
	private Rational reduction(Rational a){
		int gcd = gcd(a.numerator, a.denominator);		
		return new Rational(a.numerator/gcd, a.denominator/gcd);
	}
}
