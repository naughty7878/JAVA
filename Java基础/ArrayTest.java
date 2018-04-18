class ArrayTest {

	public static void main(String[] args) {
		//toHex(60);
		//toHex_1(60);
		toHex_2(60);
		trans(60, 15, 4);
		trans(60, 1, 1);
	}


	public static void trans(int num, int base, int offset){
		if(num == 0) {
			System.out.println("0");
			return;
		}
		char[] chs = { '0', '1', '2', '3',
						'4', '5', '6', '7',
						'8', '9', 'A', 'B',
						'C', 'D', 'E', 'F'};

		char[] arr = new char[32];
		int pos = arr.length;
		while(num!=0) {
			int temp = num & base;
			arr[--pos] = chs[temp];
			num = num >>> offset;
		}


		for (int i = pos; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}

	/*
	10 to 16

	1,2,3,4,5,6,7,8,9,10, A, B, C, D, E, F
	1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16

	*/
	public static void toHex_2(int num) {

		if(num == 0) {
			System.out.println("0");
			return;
		}

		char[] chs = { '0', '1', '2', '3',
						'4', '5', '6', '7',
						'8', '9', 'A', 'B',
						'C', 'D', 'E', 'F'};

		char[] arr = new char[8];
		int pos = arr.length;
		while(num!=0) {
			int temp = num & 15;
			arr[--pos] = chs[temp];
			num = num >>> 4;
		}


		for (int i = pos; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}

	public static void toHex_1(int num) {

		char[] chs = { '0', '1', '2', '3',
						'4', '5', '6', '7',
						'8', '9', 'A', 'B',
						'C', 'D', 'E', 'F'};

		for (int i = 0; i < 8; i++) {
			int temp = num & 15;
			System.out.print(chs[temp]);
			num = num >>> 4;
		}
	}


	public static void toHex(int num) {

		for (int i = 0; i < 8; i++) {
			int temp = num & 15;
			if(temp > 9){
				System.out.print((char)(temp-10+'A'));
			}else {
				System.out.print(temp);
			}
			num = num >>> 4;
		}
	}

	
}