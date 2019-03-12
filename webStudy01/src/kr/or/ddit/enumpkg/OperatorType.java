package kr.or.ddit.enumpkg;

public enum OperatorType {
	PLUS('+'), MINUS('-'), MULTIPLY('*'), DIVIDE('/');
	
	private char sign;
	private RealOperator realOperator;//구현체를 이 인터페이스로  접근함
	
	OperatorType(char sign, RealOperator realOperatior) {
		this.sign = sign;
		this.realOperator = realOperatior;
	}
	
	public char getSign() {
		return sign;
	}
	
	
	public double operate(double left, double right) {
		return realOperator.realOperator(left, right);
	}
	
	
}
