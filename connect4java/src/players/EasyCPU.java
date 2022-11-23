package players;

public class EasyCPU extends Computer {

	@Override
	public int makeMove() {
		return randomMove();

	}

	public void test() {
		System.out.println("teste");
	}

}
